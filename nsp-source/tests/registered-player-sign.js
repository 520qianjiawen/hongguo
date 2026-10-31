// SPDX-License-Identifier: GPL-3.0-only
// Registered-client protocol port of qq294716498/HongguoTV-Phone-TV-NoAds,
// native/core/provider_hongguo_player_sign.go, commit 8eafa3908e9191c0a5ae186d5beff11e3a971f3c.
// Uses only the Switch Buffer shim, BigInt and nativeCrypto.createHash.
globalThis.signRegisteredPlayer = (() => {
  const mask64 = (1n << 64n) - 1n;
  const hash = (kind, bytes) => Buffer.from(nativeCrypto.createHash(kind).update(bytes).digest());
  const md5 = bytes => hash('md5', bytes);
  const sm3 = bytes => hash('sm3', bytes);
  const rot64 = (v, n) => ((v >> BigInt(n)) | (v << BigInt(64 - n))) & mask64;
  function read64(bytes, offset) {
    let value = 0n;
    for (let i = 7; i >= 0; i--) value = (value << 8n) | BigInt(bytes[offset + i]);
    return value;
  }
  function write64(bytes, offset, value) {
    value &= mask64;
    for (let i = 0; i < 8; i++) bytes[offset + i] = Number((value >> BigInt(i * 8)) & 255n);
  }
  function pad(bytes) {
    const n = 16 - bytes.length % 16;
    return Buffer.concat([Buffer.from(bytes), Buffer.alloc(n, n)]);
  }
  function varint(number) {
    let value = BigInt(number);
    const result = [];
    while (value > 127n) { result.push(Number(value & 127n) | 128); value >>= 7n; }
    result.push(Number(value));
    return Buffer.from(result);
  }
  const integerField = (field, value) => Buffer.concat([varint(field * 8), varint(value)]);
  const bytesField = (field, bytes) => {
    bytes = typeof bytes === 'string' ? Buffer.from(bytes, 'utf8') : Buffer.from(bytes);
    return Buffer.concat([varint(field * 8 + 2), varint(bytes.length), bytes]);
  };
  const xtime = value => ((value << 1) ^ ((value & 128) ? 0x11b : 0)) & 255;
  function multiply(a, b) {
    let result = 0;
    while (b) { if (b & 1) result ^= a; a = xtime(a); b >>>= 1; }
    return result;
  }
  const sbox = Array.from({length: 256}, (_, value) => {
    let inverse = 0;
    if (value) {
      inverse = 1;
      let base = value, power = 254;
      while (power) { if (power & 1) inverse = multiply(inverse, base); base = multiply(base, base); power >>>= 1; }
    }
    let result = inverse ^ 0x63;
    for (let n = 1; n <= 4; n++) result ^= ((inverse << n) | (inverse >>> (8 - n))) & 255;
    return result;
  });
  function aesCbcEncrypt(bytes, key, iv) {
    if (key.length !== 16 || iv.length !== 16) throw new Error('Registered signature AES parameters invalid');
    const expanded = Buffer.alloc(176);
    Buffer.from(key).copy(expanded);
    let roundConstant = 1;
    for (let offset = 16; offset < 176; offset += 4) {
      let temp = Array.from(expanded.subarray(offset - 4, offset));
      if (offset % 16 === 0) {
        temp = [sbox[temp[1]] ^ roundConstant, sbox[temp[2]], sbox[temp[3]], sbox[temp[0]]];
        roundConstant = xtime(roundConstant);
      }
      for (let i = 0; i < 4; i++) expanded[offset + i] = expanded[offset - 16 + i] ^ temp[i];
    }
    const data = pad(bytes), output = Buffer.alloc(data.length);
    let previous = Buffer.from(iv);
    for (let offset = 0; offset < data.length; offset += 16) {
      const state = Buffer.alloc(16);
      for (let i = 0; i < 16; i++) state[i] = data[offset + i] ^ previous[i] ^ expanded[i];
      for (let round = 1; round <= 10; round++) {
        const old = Buffer.from(state);
        for (let column = 0; column < 4; column++)
          for (let row = 0; row < 4; row++) state[column * 4 + row] = sbox[old[((column + row) % 4) * 4 + row]];
        if (round < 10) for (let column = 0; column < 4; column++) {
          const start = column * 4;
          const a = state[start], b = state[start + 1], c = state[start + 2], d = state[start + 3], sum = a ^ b ^ c ^ d;
          state[start] = a ^ sum ^ xtime(a ^ b);
          state[start + 1] = b ^ sum ^ xtime(b ^ c);
          state[start + 2] = c ^ sum ^ xtime(c ^ d);
          state[start + 3] = d ^ sum ^ xtime(d ^ a);
        }
        for (let i = 0; i < 16; i++) state[i] ^= expanded[round * 16 + i];
      }
      state.copy(output, offset);
      previous = state;
    }
    return output;
  }
  function gorgon(query, body, stamp) {
    const payload = Buffer.alloc(20);
    md5(Buffer.from(query)).copy(payload, 0, 0, 4);
    if (body !== null) md5(body).copy(payload, 4, 0, 4);
    Buffer.from([0, 6, 11, 28]).copy(payload, 12);
    payload.writeUInt32BE(stamp, 16);
    const key = Buffer.from('44b9b9d9a4aef9fca493aa757ca3c2c4a496938f', 'hex');
    for (let i = 0; i < payload.length; i++) payload[i] ^= key[i];
    for (let i = 0; i < payload.length; i++) {
      const value = (((payload[i] << 4) | (payload[i] >>> 4)) & 255) ^ payload[(i + 1) % 20];
      let reversed = 0;
      for (let bit = 0; bit < 8; bit++) reversed = (reversed << 1) | ((value >>> bit) & 1);
      payload[i] = reversed ^ 255 ^ 20;
    }
    return '8404401c0000' + payload.toString('hex');
  }
  function argus(query, body, stamp, deviceId, nonce) {
    const key = Buffer.from('ac1adaae95a7af94a5114ab3b3a97dd80050aa0a39314c40528caec95256c28c', 'hex');
    const bodyHash = sm3(body === null ? Buffer.alloc(16) : md5(body));
    const queryHash = sm3(Buffer.from(query));
    const counters = Buffer.concat([integerField(1, 1), integerField(2, 1), integerField(3, 1), integerField(7, 3348294860)]);
    const environment = Buffer.concat([bytesField(1, '25053RT47C'), bytesField(2, '16'), bytesField(3, 'update_64'), integerField(4, 2147483648)]);
    const message = Buffer.concat([
      integerField(1, 0x20200929 * 2), integerField(2, 2), integerField(3, nonce.readUInt32LE(6) & 0x7fffffff),
      bytesField(4, '8662'), bytesField(5, deviceId), bytesField(6, '1611921764'), bytesField(7, '7.3.5.32'),
      bytesField(8, 'v04.07.01-ml-android'), integerField(9, 135135744), bytesField(10, Buffer.alloc(8)), integerField(11, 0),
      integerField(12, BigInt(stamp) * 2n), bytesField(13, bodyHash.subarray(0, 6)), bytesField(14, queryHash.subarray(0, 6)),
      bytesField(15, counters), bytesField(16, Buffer.alloc(0)), bytesField(20, 'none'), integerField(21, 738),
      bytesField(23, environment), integerField(25, 2)
    ]);
    const data = pad(message);
    const derived = sm3(Buffer.concat([key, Buffer.from([0x3c, 0xcc, 0x56, 0x7b]), key]));
    const rounds = [];
    for (let i = 0; i < 4; i++) rounds.push(read64(derived, i * 8));
    for (let i = 4; i < 72; i++) {
      let mixed = rot64(rounds[i - 1], 3) ^ rounds[i - 3];
      mixed ^= rot64(mixed, 1);
      rounds.push(((~rounds[i - 4]) ^ mixed ^ ((0x3dc94c3a046d678bn >> BigInt((i - 4) % 62)) & 1n) ^ 3n) & mask64);
    }
    for (let offset = 0; offset < data.length; offset += 16) {
      let left = read64(data, offset), right = read64(data, offset + 8);
      for (const round of rounds) {
        const next = (left ^ (rot64(right, 63) & rot64(right, 56)) ^ rot64(right, 62) ^ round) & mask64;
        left = right; right = next;
      }
      write64(data, offset, left); write64(data, offset + 8, right);
    }
    const xor = Buffer.from([0xd0, 0x4f, 0xfd, 0xff, 0xd0, 0x4f, 0xfd, 0xff]);
    for (let i = 0; i < data.length; i++) data[i] ^= xor[i % xor.length];
    const reversed = Buffer.concat([xor, data]).reverse();
    const container = Buffer.concat([Buffer.from([0xa6, 0xe7, 0x83, 0xee, 0x70, 0x01, 0x10, 0x09, 0x18]), reversed, Buffer.from([0x56, 0x7b])]);
    const cipher = aesCbcEncrypt(container, md5(key.subarray(0, 16)), md5(key.subarray(16)));
    return Buffer.concat([Buffer.from([0x3c, 0xcc]), cipher]).toString('base64');
  }
  function ladon(aid, stamp, prefix) {
    const digest = Buffer.from(md5(Buffer.concat([prefix, Buffer.from(aid)])).toString('hex'), 'ascii');
    let left = read64(digest, 0), right = read64(digest, 8);
    const queue = [read64(digest, 16), read64(digest, 24)], keys = [];
    for (let i = 0; i < 34; i++) {
      keys.push(left);
      const mixed = ((rot64(right, 8) + left) & mask64) ^ BigInt(i);
      queue.push(mixed);
      left = (mixed ^ rot64(left, 61)) & mask64;
      right = queue.shift();
    }
    const data = pad(Buffer.from(String(stamp) + '-1611921764-' + aid));
    for (let offset = 0; offset < data.length; offset += 16) {
      let a = read64(data, offset), b = read64(data, offset + 8);
      for (const key of keys) { b = key ^ ((a + rot64(b, 8)) & mask64); a = (b ^ rot64(a, 61)) & mask64; }
      write64(data, offset, a); write64(data, offset + 8, b);
    }
    return Buffer.concat([prefix, data]).toString('base64');
  }
  function signRegisteredPlayer(query, body, timestampMs, deviceId, nonceBuffer) {
    if (typeof query !== 'string' || !Number.isSafeInteger(timestampMs) || timestampMs < 0 || !/^\d{1,32}$/.test(deviceId))
      throw new Error('Registered signature input invalid');
    const stamp = Math.floor(timestampMs / 1000) >>> 0;
    body = body == null ? null : Buffer.from(body);
    const nonce = nonceBuffer == null ? Buffer.from(Array.from({length: 11}, () => Math.floor(Math.random() * 256))) : Buffer.from(nonceBuffer);
    if (nonce.length !== 11) throw new Error('Registered signature nonce invalid');
    let aid = '8662';
    for (const field of query.split('&')) if (field.startsWith('aid=')) aid = decodeURIComponent(field.slice(4));
    const headers = {'X-Khronos': String(stamp), 'X-Gorgon': gorgon(query, body, stamp), 'X-SS-Req-Ticket': String(timestampMs),
      'X-Argus': argus(query, body, stamp, deviceId, nonce), 'X-Ladon': ladon(aid, stamp, nonce.subarray(2, 6))};
    if (body !== null) headers['X-SS-STUB'] = md5(body).toString('hex').toUpperCase();
    return headers;
  }
  return signRegisteredPlayer;
})();
