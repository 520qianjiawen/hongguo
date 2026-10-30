import collections
import hashlib
import json
import pathlib
import re
import zipfile

ROOT = pathlib.Path(__file__).resolve().parent
APK = ROOT.parent / 'MiniReel-0.4.0-android-tv-armeabi-v7a.apk'

def save_json(name, obj):
    (ROOT / name).write_text(json.dumps(obj, ensure_ascii=False, indent=2) + '\n')

with zipfile.ZipFile(APK) as z:
    members = []
    for i in z.infolist():
        data = z.read(i)
        members.append(dict(path=i.filename, bytes=len(data), sha256=hashlib.sha256(data).hexdigest()))
    save_json('member-inventory.json', dict(apk=str(APK), sha256=hashlib.sha256(APK.read_bytes()).hexdigest(), members=members))
    for name in ['assets/flutter_assets/assets/config/sources.json', 'assets/flutter_assets/License', 'assets/flutter_assets/NativeAssetsManifest.json']:
        (ROOT / pathlib.PurePosixPath(name).name).write_bytes(z.read(name))
    app = z.read('lib/armeabi-v7a/libapp.so')
    rows = [dict(offset=m.start(), value=m.group().decode('ascii')) for m in re.finditer(rb'[\x20-\x7e]{5,}', app)]
    save_json('libapp-ascii-strings.json', rows)
    features = [r for r in rows if re.search(r'minireel|hongguo|episode|drama|favorite|history|search|repository|datasource|settings|source_config|update|https?://|package:', r['value'], re.I)]
    save_json('feature-string-candidates.json', features)
    urls = []
    for row in rows:
        for m in re.finditer(r'https?://[^\s<>\x00"\x27]+', row['value']):
            urls.append(dict(offset=row['offset'] + m.start(), value=m.group()))
    save_json('url-string-candidates.json', urls)
    print(json.dumps(dict(members=len(members), strings=len(rows), feature_candidates=len(features), urls=len(urls)), indent=2))
