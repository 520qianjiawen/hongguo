const fs=require('fs'),vm=require('vm'),crypto=require('crypto'),path=require('path');
const {request,traces,safeError}=require('./probe-runtime.cjs');
const original='/Volumes/Damao/APP/switch-diagnostics-20261029/v113-inspect/c862f5406836756312cad567b57daab1';
const folder=process.argv[2]||path.join(__dirname,'modified-romfs');
const c=vm.createContext({console,SwitchConfig:{source:'phone'},__hash:(algorithm,hex)=>crypto.createHash(algorithm).update(Buffer.from(hex,'hex')).digest('hex'),
 __aes:(algorithm,key,iv,data)=>{
  if(algorithm==='cenc-batch'){
    const keyBuf=Buffer.from(key,'hex'),descs=Buffer.from(iv,'hex'),out=Buffer.from(data,'hex');
    for(let i=0;i<descs.length/24;i++){
      const off=i*24,sOff=descs.readUInt32LE(off),sLen=descs.readUInt32LE(off+4),counter=descs.subarray(off+8,off+24);
      const d=crypto.createDecipheriv('aes-128-ctr',keyBuf,counter);d.setAutoPadding(false);
      Buffer.concat([d.update(out.subarray(sOff,sOff+sLen)),d.final()]).copy(out,sOff);
    }
    return out.toString('hex');
  }
  const d=crypto.createDecipheriv(algorithm,Buffer.from(key,'hex'),Buffer.from(iv,'hex'));d.setAutoPadding(false);return Buffer.concat([d.update(Buffer.from(data,'hex')),d.final()]).toString('hex');
 },
 __http:(url,method,headers,body)=>{const t=Date.now();const r=request(url,{method,headers:JSON.parse(headers),body:body||undefined,responseType:'arraybuffer'});traces[traces.length-1].ms=Date.now()-t;return JSON.stringify({status:r.status,headers:r.headers,hex:r.data.toString('hex')});}});
vm.runInContext(fs.readFileSync(path.join(original,'deps.js'),'utf8'),c);
for(const file of ['prelude.js','hongguo.cjs','phone_source.js','adapter.js'])vm.runInContext(fs.readFileSync(path.join(folder,file),'utf8'),c);
(async()=>{
 const phases=[];
 for(const [name,expr]of [['prefetch',"SwitchAPI.prefetch('7701336720221932568',480)"],['first_open_after_prefetch',"SwitchAPI.open('7701336720221932568',480,true)"],['explicit_retry',"SwitchAPI.open('7701336720221932568',480,true)"]]){
  const start=Date.now(),count=traces.length,value=await vm.runInContext(expr,c);
  phases.push({name,ms:Date.now()-start,requests:traces.slice(count),result:value});
 }
 console.log(JSON.stringify({folder,phases},null,2));
})().catch(e=>{console.error(safeError(e));process.exitCode=1;});
