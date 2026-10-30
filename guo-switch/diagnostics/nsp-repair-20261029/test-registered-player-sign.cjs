const assert=require('node:assert/strict');
const crypto=require('node:crypto'),fs=require('node:fs'),os=require('node:os'),path=require('node:path'),vm=require('node:vm'),child=require('node:child_process');
const upstream="package core\n\nimport (\n\t\"crypto/aes\"\n\t\"crypto/cipher\"\n\t\"crypto/md5\"\n\t\"crypto/rand\"\n\t\"encoding/base64\"\n\t\"encoding/binary\"\n\t\"encoding/hex\"\n\t\"fmt\"\n\t\"math/bits\"\n\t\"net/http\"\n\t\"strconv\"\n\t\"time\"\n)\n\ntype hongguoPlayerNonce [11]byte\n\nfunc newHongguoPlayerNonce() (hongguoPlayerNonce, error) {\n\tvar nonce hongguoPlayerNonce\n\t_, err := rand.Read(nonce[:])\n\treturn nonce, err\n}\n\nfunc signHongguoPlayerRequest(request *http.Request, body []byte, now time.Time) {\n\tstamp := uint32(now.Unix())\n\tqueryHash := md5.Sum([]byte(request.URL.RawQuery))\n\tvar payload [20]byte\n\tcopy(payload[:4], queryHash[:4])\n\tif body != nil {\n\t\tbodyHash := md5.Sum(body)\n\t\tcopy(payload[4:8], bodyHash[:4])\n\t\trequest.Header.Set(\"X-SS-STUB\", fmt.Sprintf(\"%X\", bodyHash))\n\t}\n\tcopy(payload[12:16], []byte{0, 6, 11, 28})\n\tbinary.BigEndian.PutUint32(payload[16:], stamp)\n\tkey := [...]byte{0x44, 0xb9, 0xb9, 0xd9, 0xa4, 0xae, 0xf9, 0xfc, 0xa4, 0x93, 0xaa, 0x75, 0x7c, 0xa3, 0xc2, 0xc4, 0xa4, 0x96, 0x93, 0x8f}\n\tfor index := range payload {\n\t\tpayload[index] ^= key[index]\n\t}\n\tfor index := range payload {\n\t\tmixed := bits.RotateLeft8(payload[index], 4) ^ payload[(index+1)%len(payload)]\n\t\tpayload[index] = bits.Reverse8(mixed) ^ 0xff ^ byte(len(payload))\n\t}\n\tsignature := append([]byte{0x84, 0x04, 0x40, 0x1c, 0, 0}, payload[:]...)\n\trequest.Header.Set(\"X-Khronos\", strconv.FormatUint(uint64(stamp), 10))\n\trequest.Header.Set(\"X-Gorgon\", hex.EncodeToString(signature))\n\trequest.Header.Set(\"X-SS-Req-Ticket\", strconv.FormatInt(now.UnixMilli(), 10))\n\n\tnonce, err := newHongguoPlayerNonce()\n\tif err != nil {\n\t\treturn\n\t}\n\trequest.Header.Set(\"X-Argus\", hongguoPlayerArgus(request.URL.RawQuery, body, stamp, request.URL.Query().Get(\"device_id\"), nonce))\n\trequest.Header.Set(\"X-Ladon\", hongguoPlayerLadon(request.URL.Query().Get(\"aid\"), stamp, nonce[2:6]))\n}\n\nfunc hongguoPlayerArgus(query string, body []byte, timestamp uint32, deviceID string, nonce hongguoPlayerNonce) string {\n\tkey, _ := hex.DecodeString(\"ac1adaae95a7af94a5114ab3b3a97dd80050aa0a39314c40528caec95256c28c\")\n\tvarint := func(message []byte, field, value uint64) []byte {\n\t\tmessage = binary.AppendUvarint(message, field*8)\n\t\treturn binary.AppendUvarint(message, value)\n\t}\n\tbytesField := func(message []byte, field uint64, value []byte) []byte {\n\t\tmessage = binary.AppendUvarint(message, field*8+2)\n\t\tmessage = binary.AppendUvarint(message, uint64(len(value)))\n\t\treturn append(message, value...)\n\t}\n\tbodyHash := hongguoSM3(make([]byte, 16))\n\tif body != nil {\n\t\tstub := md5.Sum(body)\n\t\tbodyHash = hongguoSM3(stub[:])\n\t}\n\tqueryHash := hongguoSM3([]byte(query))\n\tcounters := varint(varint(varint(varint(nil, 1, 1), 2, 1), 3, 1), 7, 3348294860)\n\tenvironment := bytesField(nil, 1, []byte(\"25053RT47C\"))\n\tenvironment = bytesField(environment, 2, []byte(\"16\"))\n\tenvironment = bytesField(environment, 3, []byte(\"update_64\"))\n\tenvironment = varint(environment, 4, 2147483648)\n\tmessage := varint(nil, 1, 0x20200929*2)\n\tmessage = varint(message, 2, 2)\n\tmessage = varint(message, 3, uint64(binary.LittleEndian.Uint32(nonce[6:10])&0x7fffffff))\n\tmessage = bytesField(message, 4, []byte(\"8662\"))\n\tmessage = bytesField(message, 5, []byte(deviceID))\n\tmessage = bytesField(message, 6, []byte(\"1611921764\"))\n\tmessage = bytesField(message, 7, []byte(\"7.3.5.32\"))\n\tmessage = bytesField(message, 8, []byte(\"v04.07.01-ml-android\"))\n\tmessage = varint(message, 9, 135135744)\n\tmessage = bytesField(message, 10, make([]byte, 8))\n\tmessage = varint(message, 11, 0)\n\tmessage = varint(message, 12, uint64(timestamp)*2)\n\tmessage = bytesField(message, 13, bodyHash[:6])\n\tmessage = bytesField(message, 14, queryHash[:6])\n\tmessage = bytesField(message, 15, counters)\n\tmessage = bytesField(message, 16, nil)\n\tmessage = bytesField(message, 20, []byte(\"none\"))\n\tmessage = varint(message, 21, 738)\n\tmessage = bytesField(message, 23, environment)\n\tmessage = varint(message, 25, 2)\n\tdata := hongguoCommentPad(message)\n\tseed := append(append(append([]byte(nil), key...), 0x3c, 0xcc, 0x56, 0x7b), key...)\n\tderived := hongguoSM3(seed)\n\tvar rounds [72]uint64\n\tfor i := 0; i < 4; i++ {\n\t\trounds[i] = binary.LittleEndian.Uint64(derived[i*8:])\n\t}\n\tfor i := 4; i < len(rounds); i++ {\n\t\tmixed := bits.RotateLeft64(rounds[i-1], -3) ^ rounds[i-3]\n\t\tmixed ^= bits.RotateLeft64(mixed, -1)\n\t\trounds[i] = ^rounds[i-4] ^ mixed ^ (uint64(0x3dc94c3a046d678b)>>uint((i-4)%62))&1 ^ 3\n\t}\n\tfor offset := 0; offset < len(data); offset += 16 {\n\t\tleft, right := binary.LittleEndian.Uint64(data[offset:]), binary.LittleEndian.Uint64(data[offset+8:])\n\t\tfor _, round := range rounds {\n\t\t\tleft, right = right, left^(bits.RotateLeft64(right, 1)&bits.RotateLeft64(right, 8))^bits.RotateLeft64(right, 2)^round\n\t\t}\n\t\tbinary.LittleEndian.PutUint64(data[offset:], left)\n\t\tbinary.LittleEndian.PutUint64(data[offset+8:], right)\n\t}\n\txor := []byte{0xd0, 0x4f, 0xfd, 0xff, 0xd0, 0x4f, 0xfd, 0xff}\n\tfor i := range data {\n\t\tdata[i] ^= xor[i%8]\n\t}\n\tdata = append(xor, data...)\n\tfor left, right := 0, len(data)-1; left < right; left, right = left+1, right-1 {\n\t\tdata[left], data[right] = data[right], data[left]\n\t}\n\tcontainer := append([]byte{0xa6, 0xe7, 0x83, 0xee, 0x70, 0x01, 0x10, 0x09, 0x18}, data...)\n\tcontainer = hongguoCommentPad(append(container, 0x56, 0x7b))\n\taesKey, iv := md5.Sum(key[:16]), md5.Sum(key[16:])\n\tblock, _ := aes.NewCipher(aesKey[:])\n\tcipher.NewCBCEncrypter(block, iv[:]).CryptBlocks(container, container)\n\treturn base64.StdEncoding.EncodeToString(append([]byte{0x3c, 0xcc}, container...))\n}\n\nfunc hongguoPlayerLadon(aid string, timestamp uint32, prefix []byte) string {\n\thash := md5.Sum(append(append([]byte(nil), prefix...), aid...))\n\tdigest := []byte(hex.EncodeToString(hash[:]))\n\tleft, right := binary.LittleEndian.Uint64(digest), binary.LittleEndian.Uint64(digest[8:])\n\tqueue := []uint64{binary.LittleEndian.Uint64(digest[16:]), binary.LittleEndian.Uint64(digest[24:])}\n\tvar keys [34]uint64\n\tfor i := range keys {\n\t\tkeys[i] = left\n\t\tmixed := (bits.RotateLeft64(right, -8) + left) ^ uint64(i)\n\t\tqueue = append(queue, mixed)\n\t\tleft = mixed ^ bits.RotateLeft64(left, 3)\n\t\tright, queue = queue[0], queue[1:]\n\t}\n\tpayload := []byte(strconv.FormatUint(uint64(timestamp), 10) + \"-1611921764-\" + aid)\n\tdata := hongguoCommentPad(payload)\n\tfor offset := 0; offset < len(data); offset += 16 {\n\t\tleft, right := binary.LittleEndian.Uint64(data[offset:]), binary.LittleEndian.Uint64(data[offset+8:])\n\t\tfor _, key := range keys {\n\t\t\tright = key ^ (left + bits.RotateLeft64(right, -8))\n\t\t\tleft = right ^ bits.RotateLeft64(left, 3)\n\t\t}\n\t\tbinary.LittleEndian.PutUint64(data[offset:], left)\n\t\tbinary.LittleEndian.PutUint64(data[offset+8:], right)\n\t}\n\treturn base64.StdEncoding.EncodeToString(append(append([]byte(nil), prefix...), data...))\n}\n";
const sourcePath=path.join(__dirname,'registered-player-sign.js');
const context=vm.createContext({Buffer,nativeCrypto:crypto});
vm.runInContext(fs.readFileSync(sourcePath,'utf8').replace('return signRegisteredPlayer;','signRegisteredPlayer.__aesCbc = aesCbcEncrypt; return signRegisteredPlayer;'),context);
let checks=0;
const encrypt=context.signRegisteredPlayer.__aesCbc;
for(const length of [0,1,15,16,17,31,32,127,256]){
 const bytes=Buffer.from(Array.from({length},(_,i)=>(i*37+11)&255)),key=Buffer.from('00112233445566778899aabbccddeeff','hex'),iv=Buffer.from('ffeeddccbbaa99887766554433221100','hex');
 const cipher=crypto.createCipheriv('aes-128-cbc',key,iv),expected=Buffer.concat([cipher.update(bytes),cipher.final()]);
 assert.equal(encrypt(bytes,key,iv).toString('hex'),expected.toString('hex'),'AES CBC PKCS7 length '+length);checks++;
}
const helper=String.raw`
func hongguoSM3(value []byte) []byte {
 c:=exec.Command("/opt/homebrew/opt/openssl@3/bin/openssl","dgst","-sm3","-binary")
 c.Stdin=bytes.NewReader(value)
 result,err:=c.Output();if err!=nil{panic(err)};return result
}
func hongguoCommentPad(value []byte) []byte {
 n:=16-len(value)%16
 return append(append([]byte(nil),value...),bytes.Repeat([]byte{byte(n)},n)...)
}
type vector struct {Query string; Body *string; Stamp int64; Device string; Nonce string}
func main(){
 var v vector;if err:=json.NewDecoder(os.Stdin).Decode(&v);err!=nil{panic(err)}
 var body []byte;if v.Body!=nil{body=[]byte(*v.Body)}
 request,_:=http.NewRequest("POST","https://example.test/play?"+v.Query,nil)
 signHongguoPlayerRequest(request,body,time.UnixMilli(v.Stamp))
 raw,_:=hex.DecodeString(v.Nonce);var nonce hongguoPlayerNonce;copy(nonce[:],raw)
 request.Header.Set("X-Argus",hongguoPlayerArgus(v.Query,body,uint32(v.Stamp/1000),v.Device,nonce))
 request.Header.Set("X-Ladon",hongguoPlayerLadon(request.URL.Query().Get("aid"),uint32(v.Stamp/1000),nonce[2:6]))
 result:=map[string]string{}
 for _,k:=range []string{"X-Khronos","X-Gorgon","X-SS-Req-Ticket","X-Argus","X-Ladon","X-SS-STUB"}{if x:=request.Header.Get(k);x!=""{result[k]=x}}
 json.NewEncoder(os.Stdout).Encode(result)
}
`;
const directory=fs.mkdtempSync(path.join(os.tmpdir(),'registered-player-sign-'));
try{
 const go=upstream.replace('package core','package main').replace('import (','import (\n "bytes"\n "os"\n "os/exec"\n "encoding/json"')+helper;
 fs.writeFileSync(path.join(directory,'main.go'),go);
 let proc=child.spawnSync('go',['build','-o',path.join(directory,'reference'),path.join(directory,'main.go')],{encoding:'utf8',timeout:60000});assert.equal(proc.status,0,proc.stderr);
 const vectors=[
 {Query:'aid=8662&device_id=1234567890123456&foo=a%20b',Body:'{"series_id":"7701334722449787928"}',Stamp:1793325000123,Device:'1234567890123456',Nonce:'000102030405060708090a'},
 {Query:'aid=8662&device_id=1905892595378490',Body:null,Stamp:1793325001999,Device:'1905892595378490',Nonce:'ffff000102030405060708'},
 {Query:'aid=8662&device_id=1234567890123456789',Body:'{"text":"中文 🤖"}',Stamp:1793325002555,Device:'1234567890123456789',Nonce:'1f2e3d4c5b6a798897a6b5'},
 {Query:'aid=8662&device_id=1234567890123456',Body:'',Stamp:1793325003000,Device:'1234567890123456',Nonce:'f0e0d0c0b0a09080706050'}
 ];
 for(const v of vectors){
  const proc=child.spawnSync(path.join(directory,'reference'),[],{input:JSON.stringify(v),encoding:'utf8',timeout:10000});assert.equal(proc.status,0,proc.stderr);
  const expected=JSON.parse(proc.stdout),got=context.signRegisteredPlayer(v.Query,v.Body,v.Stamp,v.Device,Buffer.from(v.Nonce,'hex'));
  assert.deepEqual(JSON.parse(JSON.stringify(got)),expected,'Go reference vector '+v.Stamp);checks++;
 }
 assert.throws(()=>context.signRegisteredPlayer('aid=8662','{}',1793325000123,'123',Buffer.alloc(10)));checks++;
 console.log(JSON.stringify({passed:true,checks,aes_vectors:9,go_reference_vectors:vectors.length,reference_commit:'8eafa3908e9191c0a5ae186d5beff11e3a971f3c'}));
}finally{fs.rmSync(directory,{recursive:true,force:true});}

