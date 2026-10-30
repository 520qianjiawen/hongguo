const {run,request,traces,safeError}=require('./probe-runtime.cjs');
const cfg=require('/Volumes/Damao/APP/guo/MiniReel-0.4.0-REA-analysis/sources.json').sources.hongguo;
(async()=>{
 const id='7701336636495252504'; const results=[];
 for(const [endpoint,kind] of [['/novel/player/multi_video_model/preload/v1','1'],['/novel/player/multi_video_model/v1/','1004'],['/novel/player/multi_video_model/v1/','1']]) {
  const body=JSON.stringify({biz_param:{detail_page_version:0,device_level:3,disable_digg_stat:false,disable_video_relate_book:false,need_all_video_definition:true,need_mp4_align:false,use_os_player:false,use_server_dns:false,video_platform:1024},...(endpoint.includes('preload')?{dr_scene:'preload'}:{}),mixed_video_id_map:{[kind]:[id]}});
  const now=Date.now(),seconds=Math.floor(now/1000),values={...cfg.appParameters,device_id:'1'+String(Math.floor(Math.random()*1e15)).padStart(15,'0'),iid:'1'+String(Math.floor(Math.random()*1e15)).padStart(15,'0'),ts:String(seconds),_rticket:String(now)};
  const query=await run('pythonUrlEncode('+JSON.stringify(Object.fromEntries(Object.entries(values).sort()))+')');
  const headers={'User-Agent':cfg.appUserAgent,Accept:'application/json; charset=utf-8,application/x-protobuf','Content-Type':'application/json; charset=utf-8','X-XS-From-Web':'0','Sdk-Version':'2','passport-sdk-version':'50561','X-TT-Request-Tag':'t=0;n=0','X-Khronos':String(seconds),'X-SS-Req-Ticket':String(now),'X-Argus':Buffer.from(new Uint32Array([seconds]).buffer).toString('base64'),'X-Ladon':Buffer.from([(seconds>>>24)&255,(seconds>>>16)&255,(seconds>>>8)&255,seconds&255]).toString('base64'),'X-SS-STUB':require('crypto').createHash('md5').update(body).digest('hex').toUpperCase(),'X-Gorgon':await run('xGorgon('+JSON.stringify(query)+',Buffer.from('+JSON.stringify(body)+'),'+seconds+',12345)')};
  const row={endpoint,kind};
  try{const resp=await request(cfg.appBaseUrl+endpoint+'?'+query,{method:'POST',body,headers});row.bytes=resp.data.length;
   const data=JSON.parse(resp.data);row.code=data.code??data.Code;row.top_keys=Object.keys(data);row.data_keys=Object.keys(data.data||{}).slice(0,5);
   const raw=data.data?.[id]?.video_model||data.data?.video_model;const model=typeof raw==='string'?JSON.parse(raw):raw;row.model_keys=Object.keys(model||{});row.variant_count=Object.keys(model?.video_list||{}).length;
   if(model) {try{const parsed=await run('PhoneSource.rowsFromModel('+JSON.stringify(model)+')');row.compatible_variants=parsed.rows.length;}catch(e){row.parse_error=safeError(e);}}
  }catch(error){row.error=safeError(error);}
  results.push(row);
 }
 console.log(JSON.stringify({results,traces},null,2));
})().catch(error=>{console.error(safeError(error));process.exitCode=1;});
