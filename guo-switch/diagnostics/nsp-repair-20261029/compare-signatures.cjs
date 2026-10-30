const fs=require('fs'),vm=require('vm'),crypto=require('crypto');
const {run,context}=require('./probe-runtime.cjs');
const util={md5:x=>crypto.createHash('md5').update(x).digest(),unhex:x=>Buffer.from(x,'hex'),hex:x=>Buffer.from(x).toString('hex')};
const ref=vm.createContext({Buffer,module:{exports:{}},require:n=>n==='crypto'?crypto:n.endsWith('/crypto')?util:{},console});
vm.runInContext(fs.readFileSync(__dirname+'/reference/pure-sign.js','utf8'),ref);
for(const c of [context,ref])vm.runInContext('Math.random=()=>0.123456789; Date.now=()=>1793325000123;',c);
(async()=>{
 const body=JSON.stringify({biz_param:{video_platform:1024,need_all_video_definition:true},mixed_video_id_map:{'1004':['7701336636495252504']}});
 const results={};
 const check=(name,a,b)=>{results[name]=a===b;};
 for(const c of [context,ref]){c.body=Buffer.from(body);c.q='ac=wifi&device_id=1234567890123456';c.ts=1793325000;c.testBytes=Buffer.from(Array.from({length:777},(_,i)=>i&255));}
 check('gorgon',vm.runInContext('xGorgon(q,body,ts,12345)',context),vm.runInContext('xGorgon(q,body,ts,12345)',ref));
 check('helios',vm.runInContext('helios(ts)',context),vm.runInContext('heliosWith(ts,Math.floor(0.123456789*0x100000000))',ref));
 check('sm3',vm.runInContext('sm3(q).toString("hex")',context),vm.runInContext('sm3(Buffer.from(q)).toString("hex")',ref));
 for(let delta=0;delta<4;delta++){
  for(const c of [context,ref])c.ts=1793325000+delta;
  check('hashF13-'+delta,vm.runInContext('hashF13(sm3(q),crypto.createHash("md5").update(body).digest(),le32(ts),ts).toString("hex")',context),vm.runInContext('hashF13(sm3(Buffer.from(q)),md5(body),le32(ts),ts).toString("hex")',ref));
  const expr='new AesV3(Buffer.from("f1593376766ea98d34f31b057a9d5be4","hex"),ts).encrypt(testBytes,Buffer.from("1fe109a4125283f418de9e051a969e12","hex")).toString("hex")';
  check('AesV3-'+delta,vm.runInContext(expr,context),vm.runInContext(expr,ref));
 }
 console.log(JSON.stringify(results,null,2));
})();
