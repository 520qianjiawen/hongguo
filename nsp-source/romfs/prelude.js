// SPDX-License-Identifier: GPL-3.0-only
globalThis.Buffer = SwitchDeps.Buffer;
// Browserify's core Buffer shim lacks the Node BigInt methods used by Helios.
for(const little of [true,false]){
  const suffix=little?'LE':'BE';
  Buffer.prototype['readBigUInt64'+suffix]=function(offset=0){
    if(!Number.isInteger(offset)||offset<0||offset+8>this.length)throw new RangeError('Buffer bounds');
    let value=0n;for(let i=0;i<8;i++)value|=BigInt(this[offset+i])<<BigInt((little?i:7-i)*8);return value;
  };
  Buffer.prototype['writeBigUInt64'+suffix]=function(value,offset=0){
    if(!Number.isInteger(offset)||offset<0||offset+8>this.length||value<0n||value>0xffffffffffffffffn)throw new RangeError('Buffer bounds');
    for(let i=0;i<8;i++)this[offset+i]=Number((value>>BigInt((little?i:7-i)*8))&255n);return offset+8;
  };
}
globalThis.console = {log(){},warn(){},error(){}};
globalThis.log = ()=>{};
class URLSearchParams {
  constructor(value='') {
    this.rows=[];
    if(typeof value==='object')this.rows=Object.entries(value).map(([k,v])=>[String(k),String(v)]);
    else for(const pair of String(value).replace(/^\?/,'').split('&')) {
      if(!pair)continue;const pos=pair.indexOf('=');
      const dec=s=>decodeURIComponent(s.replace(/\+/g,' '));
      this.rows.push([dec(pos<0?pair:pair.slice(0,pos)),dec(pos<0?'':pair.slice(pos+1))]);
    }
  }
  get(key){const row=this.rows.find(r=>r[0]===key);return row?row[1]:null;}
  set(key,value){this.rows=this.rows.filter(r=>r[0]!==key);this.rows.push([String(key),String(value)]);}
  entries(){return this.rows[Symbol.iterator]();}
  [Symbol.iterator](){return this.entries();}
  toString(){const enc=s=>encodeURIComponent(s).replace(/%20/g,'+');return this.rows.map(([k,v])=>enc(k)+'='+enc(v)).join('&');}
}
globalThis.URLSearchParams=URLSearchParams;
const nativeCrypto={
  createHash(algorithm){let parts=[];return {
    update(data,encoding){parts.push(Buffer.from(data,encoding));return this;},
    digest(encoding){const bytes=Buffer.concat(parts);const result=algorithm==='sm3'?Buffer.from(SwitchDeps.sm3(Array.from(bytes)),'hex'):Buffer.from(__hash(algorithm,bytes.toString('hex')),'hex');return encoding?result.toString(encoding):result;}
  };},
  createDecipheriv(algorithm,key,iv){let automatic=true,processed=0;return {
    setAutoPadding(value){automatic=value;return this;},
    update(data){data=Buffer.from(data);let counter=Buffer.from(iv),input=data,skip=0;
      if(algorithm==='aes-128-ctr'){
        const blocks=Math.floor(processed/16);skip=processed%16;
        const base=(counter.readBigUInt64BE(0)<<64n)|counter.readBigUInt64BE(8),next=(base+BigInt(blocks))&((1n<<128n)-1n);
        counter.writeBigUInt64BE(next>>64n,0);counter.writeBigUInt64BE(next&((1n<<64n)-1n),8);
        if(skip)input=Buffer.concat([Buffer.alloc(skip),data]);
      }
      let out=Buffer.from(__aes(algorithm,Buffer.from(key).toString('hex'),counter.toString('hex'),input.toString('hex')),'hex');
      if(skip)out=out.subarray(skip);processed+=data.length;
      if(automatic && algorithm.endsWith('-cbc') && out.length){const n=out[out.length-1];if(n<1||n>16||n>out.length||!out.subarray(out.length-n).every(b=>b===n))throw new Error('Invalid AES padding');out=out.subarray(0,out.length-n);}return out;},
    final(){return Buffer.alloc(0);}
  };}
};
globalThis.require=name=>{if(name==='node:crypto')return nativeCrypto;throw new Error('Unsupported module');};
async function request(url,options={}){
  const response=JSON.parse(__http(String(url),options.method||'GET',JSON.stringify(options.headers||{}),String(options.body||'')));
  const bytes=Buffer.from(response.hex,'hex');
  if(response.status<200||response.status>=300)throw new Error('内容源 HTTP '+response.status);
  return {status:response.status,headers:response.headers,data:options.responseType==='arraybuffer'?bytes:bytes.toString('utf8')};
}
globalThis.axios=request;
axios.post=(url,body,options={})=>request(url,{...options,method:'POST',body:Buffer.isBuffer(body)?body.toString('utf8'):typeof body==='string'?body:JSON.stringify(body)});

// ---- Fast hex codecs for large buffers (v1.2.0 fix) ----
// The bundled Buffer shim converts hex with per-byte substr+parseInt on decode
// and per-byte string concatenation on encode. Every media byte crosses the
// JS/native bridge as hex (range fetch in, decrypted result out, read result
// back to the player), so those loops — not the AES calls — dominate read and
// open time in QuickJS. For large inputs use table-driven codecs with no
// per-byte allocations. Any malformed pair delegates the whole conversion to
// the original implementation, preserving its exact semantics; small inputs
// always use the original path.
{
  const NIB = new Int16Array(256).fill(-1);
  for (let i = 0; i < 10; i++) NIB[48 + i] = i;
  for (let i = 0; i < 6; i++) { NIB[97 + i] = 10 + i; NIB[65 + i] = 10 + i; }
  const HEX2 = new Array(256);
  for (let i = 0; i < 256; i++) HEX2[i] = (i < 16 ? '0' : '') + i.toString(16);
  const origFrom = Buffer.from.bind(Buffer);
  const origToString = Buffer.prototype.toString;
  function fastHexDecode(str) {
    const n = str.length >> 1;
    const out = Buffer.alloc(n);
    for (let i = 0; i < n; i++) {
      const c1 = str.charCodeAt(i * 2), c2 = str.charCodeAt(i * 2 + 1);
      const hi = c1 < 256 ? NIB[c1] : -1, lo = c2 < 256 ? NIB[c2] : -1;
      if (hi < 0 || lo < 0) return null;
      out[i] = (hi << 4) | lo;
    }
    return out;
  }
  function fastHexEncode(buf, start, end) {
    let out = '';
    const CHUNK = 32768;
    for (let base = start; base < end; base += CHUNK) {
      const stop = Math.min(end, base + CHUNK);
      const parts = new Array(stop - base);
      for (let i = base; i < stop; i++) parts[i - base] = HEX2[buf[i]];
      out += parts.join('');
    }
    return out;
  }
  Buffer.from = function (value, encoding, length) {
    if (typeof value === 'string' && typeof encoding === 'string' && encoding.toLowerCase() === 'hex' && value.length >= 16384) {
      const fast = fastHexDecode(value);
      if (fast) return fast;
    }
    return origFrom(value, encoding, length);
  };
  Buffer.prototype.toString = function (encoding, start, end) {
    if (typeof encoding === 'string' && encoding.toLowerCase() === 'hex') {
      const len = this.length;
      let s = start || 0; if (s < 0) s = 0;
      let e = (end === undefined || end === null || end === 0) ? len : end;
      if (e < 0 || e > len) e = len;
      if (e >= s && e - s >= 8192) return fastHexEncode(this, s, Math.max(s, e));
    }
    return origToString.call(this, encoding, start, end);
  };
}
