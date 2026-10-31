const fs=require('fs'),vm=require('vm'),os=require('os'),path=require('path'),child=require('child_process');
const {context,run,traces,safeError}=require('./probe-runtime.cjs');
vm.runInContext(fs.readFileSync(path.join(process.env.NSP_JS_DIR||path.join(__dirname,'modified-romfs'),'adapter.js'),'utf8'),context);
(async()=>{
 const directory=fs.mkdtempSync(path.join(os.tmpdir(),'minireel-playback-'));
 const results=[];
 try{
  for(const id of ['7701336636495252504','7701336720221932568']){
   const row={id};const firstTrace=traces.length;
   try{
    const info=await run('SwitchAPI.open('+JSON.stringify(id)+',480,true)');Object.assign(row,info);
    const file=path.join(directory,id+'.mp4'),fd=fs.openSync(file,'w',0o600);
    try{for(let start=0;start<info.total;start+=1048576){const end=Math.min(info.total-1,start+1048575);const hex=await run('SwitchAPI.read('+JSON.stringify(id)+','+start+','+end+')');fs.writeSync(fd,Buffer.from(hex,'hex'));}}finally{fs.closeSync(fd);}
    const probe=child.spawnSync('/opt/homebrew/bin/ffprobe',['-v','error','-show_entries','stream=codec_name,width,height','-show_entries','format=duration','-of','json',file],{encoding:'utf8',maxBuffer:2*1024*1024});
    row.ffprobe_exit=probe.status;row.media=JSON.parse(probe.stdout||'{}');
    const decode=child.spawnSync('/opt/homebrew/bin/ffmpeg',['-hide_banner','-v','error','-i',file,'-t','15','-f','null','-'],{encoding:'utf8',maxBuffer:2*1024*1024});
    row.decode_15s_exit=decode.status;row.decode_errors=decode.stderr.trim().slice(0,1000);row.pass=probe.status===0&&decode.status===0&&!decode.stderr.trim();
   }catch(e){row.error=safeError(e);row.pass=false;}
   row.requests=traces.slice(firstTrace);results.push(row);
  }
 }finally{fs.rmSync(directory,{recursive:true,force:true});}
 const report={checked_at:new Date().toISOString(),all_passed:results.every(r=>r.pass),results};
 fs.writeFileSync(path.join(process.env.NSP_REPORT_DIR||__dirname,'playback-validation.json'),JSON.stringify(report,null,2)+'\n');console.log(JSON.stringify(report,null,2));
 if(!report.all_passed)process.exitCode=1;
})().catch(e=>{console.error(safeError(e));process.exitCode=1;});
