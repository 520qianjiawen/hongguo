const {run,request,traces,safeError,context}=require('./probe-runtime.cjs');
require('vm').runInContext(require('fs').readFileSync(__dirname+'/registered-player-sign.js','utf8'),context);
const cfg=require('/Volumes/Damao/APP/guo/MiniReel-0.4.0-REA-analysis/sources.json').sources.hongguo;
const crypto=require('crypto');
(async()=>{
 const now=Date.now(),version=73932; const header={display_name:'红果免费短剧',aid:8662,app_version:'7.3.9.32',version_code:version,manifest_version_code:version,update_version_code:version,channel:'update_64',package:'com.phoenix.read',app_language:'zh',os:'Android',os_version:'16',os_api:36,device_model:'25053RT47C',device_brand:'Redmi',device_manufacturer:'Xiaomi',cpu_abi:'arm64-v8a',density_dpi:520,display_density:'xxhdpi',resolution:'2772x1280',language:'zh',timezone:8,access:'wifi',openudid:crypto.randomBytes(8).toString('hex'),clientudid:crypto.randomUUID(),cdid:crypto.randomUUID(),region:'CN',tz_name:'Asia/Shanghai',tz_offset:28800,sim_region:'cn',device_platform:'android',not_request_sender:0,is_system_app:0,sdk_flavor:'china',rom:'OS2.0',rom_version:'BP2A.250605.031.A3'};
 const query=await run('pythonUrlEncode('+JSON.stringify({...cfg.appParameters,req_id:crypto.randomUUID(),_rticket:String(now)})+')');
 const reg=await request('https://log.snssdk.com/service/2/device_register/?'+query,{method:'POST',body:JSON.stringify({magic_tag:'ss_app_log',header,_gen_time:now}),headers:{'User-Agent':cfg.appUserAgent,Accept:'application/json','Content-Type':'application/json; charset=utf-8'}});
 let data;try{data=JSON.parse(reg.data);}catch(e){console.log(JSON.stringify({registration_bytes:reg.data.length,error:safeError(e),traces},null,2));return;}
 const device=data.device_id_str||String(data.device_id||''),install=data.install_id_str||String(data.install_id||'');
 const results=[];const vid='7701336636495252504';
 for(const [path,kind,platform,mode] of [['/novel/player/multi_video_model/v1/','1004',3,'minimal'],['/novel/player/multi_video_model/v1/','1',3,'minimal'],['/novel/player/multi_video_model/v1/','1004',1024,'minimal'],['/novel/player/multi_video_model/preload/v1','1',1024,'minimal']]){
  const stamp=Date.now(),values={...cfg.appParameters,device_id:device,iid:install,_rticket:String(stamp)};
  const query=await run('pythonUrlEncode('+JSON.stringify(Object.fromEntries(Object.entries(values).sort()))+')');
  const payload={biz_param:{detail_page_version:0,device_level:3,disable_digg_stat:false,need_all_video_definition:true,need_mp4_align:false,use_os_player:false,use_server_dns:false,video_platform:platform},...(path.includes('preload')?{dr_scene:'preload'}:{}),mixed_video_id_map:{[kind]:[vid]}};
  const body=JSON.stringify(payload),sign=await run('signRegisteredPlayer('+JSON.stringify(query)+','+JSON.stringify(body)+','+stamp+','+JSON.stringify(device)+')');
  if(mode==='minimal')Object.assign(sign,{'X-Gorgon':await run('xGorgon('+JSON.stringify(query)+',Buffer.from('+JSON.stringify(body)+'),'+Math.floor(stamp/1000)+',12345)'),'X-Argus':await run('le32('+Math.floor(stamp/1000)+').toString("base64")'),'X-Ladon':await run('be32('+Math.floor(stamp/1000)+').toString("base64")')});
  const r=await request(cfg.appBaseUrl+path+'?'+query,{method:'POST',body,headers:{'User-Agent':cfg.appUserAgent,Accept:'application/json','X-XS-From-Web':'0','Sdk-Version':'2','Content-Type':'application/json; charset=utf-8',...sign}});
  let d;try{d=JSON.parse(r.data);}catch(_){};results.push({path,kind,platform,mode,bytes:r.data.length,code:d?.code??d?.Code,message:d?.message??d?.Message,keys:Object.keys(d?.data||{})});
 }
 console.log(JSON.stringify({registration_bytes:reg.data.length,device_present:!!device&&device!=='0',install_present:!!install&&install!=='0',results,traces},null,2));
})();
