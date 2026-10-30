"""Read-only reproduction of the public web requests used by HongguoProvider.

Retains structural facts and redacted fixtures, never media URL credentials.
Does not register devices, sign App requests, or execute the APK.
"""
import concurrent.futures
import hashlib
import json
import pathlib
import re
import subprocess
from datetime import datetime, timezone

OUT = pathlib.Path(__file__).resolve().parent
BASE = 'https://hongguoduanju.com'
UA = 'Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36'

def fetch(path):
    result = subprocess.run(['curl', '--silent', '--show-error', '--location', '--compressed',
        '--max-time', '20', '--max-filesize', '4194304', '--user-agent', UA,
        '--referer', BASE + '/', '--header', 'Accept: application/json,text/html;q=0.9,*/*;q=0.8',
        '--header', 'Accept-Language: zh-CN,zh;q=0.9', BASE + path], capture_output=True)
    if result.returncode:
        raise RuntimeError('curl failed: ' + str(result.returncode))
    body = result.stdout.decode('utf-8')
    marker = re.search(r'(?:window\.)?_ROUTER_DATA\s*=\s*', body)
    if not marker:
        raise RuntimeError('No router data')
    data, _ = json.JSONDecoder().raw_decode(body[marker.end():])
    return data['loaderData'], hashlib.sha256(result.stdout).hexdigest()

def loader(entries, names):
    for name in names:
        if isinstance(entries.get(name), dict):
            return entries[name]
    for key, value in entries.items():
        if any(key.startswith(n.rstrip('$')) for n in names) and isinstance(value, dict):
            return value
    raise RuntimeError('Loader not found: ' + ','.join(entries))

def encrypted(info):
    if not isinstance(info, dict):
        return True
    if any(info.get(k) not in (None, '') for k in ['spade_a', 'kid', 'key_id']):
        return True
    if 'encrypt' in info and info['encrypt'] not in (None, False, 0, '0', ''):
        return True
    if info.get('encryption_method') not in (None, '', 'none'):
        return True
    return 'encrypt_info' in info and encrypted(info['encrypt_info'])

def sanitize(value, key=''):
    if isinstance(value, dict):
        return {k: sanitize(v, k) for k, v in value.items()}
    if isinstance(value, list):
        return [sanitize(v, key) for v in value]
    if isinstance(value, str):
        if re.search(r'url|cookie|token|seed|spade|key_id|^kid$|authorization', key, re.I):
            return '' if not value else 'REDACTED_NONEMPTY'
        if re.search(r'https?://', value):
            return 'REDACTED_URL'
    return value

def probe(series):
    detail_entries, digest = fetch('/detail?series_id=' + series)
    detail = loader(detail_entries, ['detail_page', 'detail_'])['seriesDetail']
    vids = detail.get('vid_list', [])
    if isinstance(vids, dict):
        vids = next((vids[k] for k in ['list', 'items', 'data'] if isinstance(vids.get(k), list)), [])
    record = {'series_id': series, 'title': detail.get('series_title', detail.get('series_name')),
              'detail_sha256': digest, 'episode_count': len(vids), 'episodes': []}
    for index in [0, 1]:
        if index >= len(vids):
            continue
        vid = str(vids[index])
        entries, digest = fetch('/player/' + series + '/' + vid)
        data = loader(entries, ['player_', 'player_page'])
        info = data.get('video_player_info', {})
        mismatch = str(data.get('vid', '')) != vid or str(data.get('series_id', '')) != series
        outcome = ('站点只返回了试看集，无法播放所选分集' if mismatch else
                   '该集需要 App 取流，当前版本尚未接入' if encrypted(info) else
                   '该集没有公开播放地址，可能需要登录或 App 授权' if not info else 'continue_to_url_parser')
        row = {'episode_number': index + 1, 'requested_vid': vid, 'returned_vid': data.get('vid'),
               'returned_series_id': data.get('series_id'), 'response_sha256': digest,
               'loader_fields': sorted(data), 'protected_playback': data.get('protectedPlayback'),
               'video_player_info_present': 'video_player_info' in data, 'video_info_type': type(info).__name__,
               'video_info_fields': sorted(info) if isinstance(info, dict) else [],
               'encryption_markers_present': [k for k in ['spade_a', 'kid', 'key_id', 'encrypt', 'encryption_method', 'encrypt_info'] if isinstance(info, dict) and k in info],
               'url_field_types': {k: type(v).__name__ for k, v in info.items() if 'url' in k} if isinstance(info, dict) else {},
               'provider_branch': outcome}
        fixture = {'series_id': series, 'episode_id': vid, 'page': {'loaderData': {'player_page': sanitize(data)}}}
        (OUT / ('fixture-' + series + '-' + str(index + 1) + '.json')).write_text(json.dumps(fixture, ensure_ascii=False, indent=2))
        record['episodes'].append(row)
    return record

if __name__ == '__main__':
    # Small fixed sample of public category entries, first and second episodes.
    series_ids = ['7701334722449787928', '7694653385668037657', '7699683909419666457']
    result = {'checked_at_utc': datetime.now(timezone.utc).isoformat(), 'request_user_agent': UA, 'samples': []}
    with concurrent.futures.ThreadPoolExecutor(max_workers=3) as pool:
        for series_id, future in zip(series_ids, [pool.submit(probe, s) for s in series_ids]):
            try:
                result['samples'].append(future.result())
            except Exception as error:
                result['samples'].append({'series_id': series_id, 'error': str(error)})
    (OUT / 'web-probe-results.json').write_text(json.dumps(result, ensure_ascii=False, indent=2) + '\n')
    print(json.dumps(result, ensure_ascii=False, indent=2))
