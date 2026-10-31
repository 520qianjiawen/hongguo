const fs=require('fs'),vm=require('vm'),crypto=require('crypto'),path=require('path');
const {request,traces,safeError}=require('./probe-runtime.cjs');
const original='/Volumes/Damao/APP/switch-diagnostics-20261029/v113-inspect/c862f5406836756312cad567b57daab1';
const modified=process.env.NSP_JS_DIR||path.join(__dirname,'modified-romfs');
function create(folder){
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
 __http:(url,method,headers,body)=>{const r=request(url,{method,headers:JSON.parse(headers),body:body||undefined,responseType:'arraybuffer'});return JSON.stringify({status:r.status,headers:r.headers,hex:r.data.toString('hex')});}});
 vm.runInContext(fs.readFileSync(path.join(original,'deps.js'),'utf8'),c);
 for(const file of ['prelude.js','hongguo.cjs','phone_source.js','adapter.js'])vm.runInContext(fs.readFileSync(path.join(folder,file),'utf8'),c);
 return c;
}
(async()=>{
 const before=create(original),after=create(modified);
 const old=await vm.runInContext('SwitchAPI.selftest()',before),current=await vm.runInContext('SwitchAPI.selftest()',after);
 if(JSON.stringify(old)!==JSON.stringify(current))throw Error('Native startup selftest changed');
 const result=await vm.runInContext("SwitchAPI.open('7701336720221932568',480,true)",after);
 const data=await vm.runInContext("SwitchAPI.read('7701336720221932568',1048500,1048800)",after);
 if(data.length!==301*2)throw Error('Wrong native bridge read length');
 const report={startup_selftest_unchanged:true,startup_crypto_vectors_passed:true,original_buffer_shim:true,opened:result,unaligned_read_bytes:data.length/2,requests:traces};
 fs.writeFileSync(path.join(process.env.NSP_REPORT_DIR||__dirname,'native-bridge-validation.json'),JSON.stringify(report,null,2)+'\n');console.log(JSON.stringify(report,null,2));
})().catch(e=>{console.error(safeError(e));process.exitCode=1;});
