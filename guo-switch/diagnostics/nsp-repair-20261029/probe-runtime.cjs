const fs = require('node:fs');
const vm = require('node:vm');
const child = require('node:child_process');
const crypto = require('node:crypto');
const path = require('node:path');
const os = require('node:os');
const root = __dirname;
const folder = process.env.NSP_JS_DIR || path.join(root, 'modified-romfs');
const traces = [];
function safeError(error) { return String(error?.message || error).replace(/https?:\/\/\S+/g, '[URL]').slice(0, 200); }
function request(url, options = {}) {
  const config = ['silent','show-error','location','http1.1','max-time = 20','max-filesize = 8388608',
    'url = '+JSON.stringify(String(url)), 'request = '+JSON.stringify(options.method || 'GET')];
  for (const [key, value] of Object.entries(options.headers || {})) config.push('header = '+JSON.stringify(key+': '+value));
  if (options.body != null) config.push('data-binary = '+JSON.stringify(String(options.body)));
  const temporary=fs.mkdtempSync(path.join(os.tmpdir(),'minireel-probe-'));
  let proc,rawHeaders;
  try{
    proc=child.spawnSync('curl', ['--config','-','--dump-header',path.join(temporary,'headers'),'--write-out','\n%{http_code}'], {input:config.join('\n')+'\n',maxBuffer:16*1024*1024});
    rawHeaders=fs.readFileSync(path.join(temporary,'headers'),'utf8');
  }finally{fs.rmSync(temporary,{recursive:true,force:true});}
  const output = proc.stdout || Buffer.alloc(0), marker=output.lastIndexOf(10);
  const status=Number(output.subarray(marker+1).toString()),bytes=output.subarray(0,marker);
  const host=new URL(url).hostname;
  traces.push({path:host.endsWith('fqnovel.com')||host==='log.snssdk.com'?new URL(url).pathname:'media_range',status,bytes:bytes.length,curl_exit:proc.status});
  if(proc.status!==0)throw new Error('HTTP transfer failed '+proc.status);
  if(status<200||status>=300)throw new Error('HTTP '+status);
  const headers={};for(const line of rawHeaders.split(/\r?\n/)){if(/^HTTP\//.test(line)){for(const k of Object.keys(headers))delete headers[k];continue;}const p=line.indexOf(':');if(p>0)headers[line.slice(0,p).trim().toLowerCase()]=line.slice(p+1).trim();}
  return {status,headers,data:options.responseType==='arraybuffer'?bytes:bytes.toString('utf8')};
}
request.post=(url,body,options={})=>request(url,{...options,method:'POST',body:Buffer.isBuffer(body)?body.toString():body});
const context=vm.createContext({Buffer,URLSearchParams,URL,require,console,axios:request,nativeCrypto:crypto,log(){},setResult:x=>x,SwitchConfig:{source:'phone'}});
vm.runInContext(fs.readFileSync(path.join(folder,'hongguo.cjs'),'utf8'),context);
vm.runInContext(fs.readFileSync(path.join(folder,'phone_source.js'),'utf8'),context);
async function run(expression) { return await vm.runInContext(expression,context); }
module.exports={context,run,traces,request,safeError};
if(require.main===module)(async()=>{
  const id=process.argv[2]||'7701336636495252504';
  const result={id};
  try {
    const info=await run('PhoneSource.videoInfo('+JSON.stringify(id)+')');
    result.rows=info.rows.map(r=>({key:r.key,quality:r.quality,codec:r.item.codec_type,has_key:!!r.item.phone_key,host:new URL(r.item.main_url).hostname}));
  }catch(error){result.error=safeError(error);}
  result.traces=traces;console.log(JSON.stringify(result,null,2));
})().catch(error=>{console.error(safeError(error));process.exitCode=1;});
