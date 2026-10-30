const {run,request,traces,safeError,context}=require('./probe-runtime.cjs');
require('vm').runInContext(require('fs').readFileSync(__dirname+'/fullsign-compat.js','utf8'),context);
(async()=>{
 const id=process.argv[2]||'7701336636495252504',results=[];
 for(const version of ['73932','71332']){
 const name=version==='73932'?'7.3.9.32':'7.1.3.32';
 const identity=()=>String(1000000000000000+Math.floor(Math.random()*281474976710656));
 const device={ac:'wifi',channel:'update_64',aid:'8662',app_name:'novelread',version_code:version,version_name:name,device_platform:'android',os:'android',ssmix:'a',device_type:'25053RT47C',device_brand:'Redmi',language:'zh',os_api:'36',os_version:'16',manifest_version_code:version,resolution:'1280*2772',dpi:'520',update_version_code:version,host_abi:'arm64-v8a',dragon_device_type:'phone',pv_player:version,compliance_status:'0',need_personal_recommend:'1',player_so_load:'1',is_android_pad_screen:'0',iid:identity(),device_id:identity()};
 const body=JSON.stringify({biz_param:{detail_page_version:0,device_level:3,disable_digg_stat:false,need_all_video_definition:true,need_mp4_align:false,use_os_player:false,use_server_dns:false,video_platform:1024},mixed_video_id_map:{'1004':[id]}});
 const signed=await run('signedVideoRequest('+JSON.stringify(body)+',VIDEO_API.replace("/video_model/","/multi_video_model/"),'+JSON.stringify(device)+')');
 Object.assign(signed.headers,await run('MiniReelFullSign.sign('+JSON.stringify(signed.url)+',Buffer.from('+JSON.stringify(body)+'),Math.floor(Date.now()/1000))'));
 signed.headers['User-Agent']=signed.headers['User-Agent'].replace('/71332','/'+version);signed.headers['x-ss-stub']=signed.headers['x-ss-stub'].toUpperCase();
 const row={version};try{const r=await request(signed.url,{method:'POST',body:signed.body,headers:signed.headers});row.bytes=r.data.length;const d=JSON.parse(r.data);row.code=d.code??d.Code;row.message=d.message??d.Message;row.top_keys=Object.keys(d);row.data_keys=Object.keys(d.data||{}).slice(0,5);const raw=d.data?.[id]?.video_model||d.data?.video_model;const model=typeof raw==='string'?JSON.parse(raw):raw;row.model_keys=Object.keys(model||{});row.variants=Object.keys(model?.video_list||{}).length;}catch(e){row.error=safeError(e);}results.push(row);
 }
 console.log(JSON.stringify({results,traces},null,2));
})().catch(e=>{console.error(safeError(e));process.exitCode=1;});
