package Z;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class p implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f595a = new p();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f596b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f597c;

    static {
        f596b = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
        f597c = Charset.forName("UTF8");
    }

    public static void c(ByteBuffer byteBuffer, int i2) {
        int iPosition = byteBuffer.position() % i2;
        if (iPosition != 0) {
            byteBuffer.position((byteBuffer.position() + i2) - iPosition);
        }
    }

    public static int d(ByteBuffer byteBuffer) {
        if (!byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Message corrupted");
        }
        int i2 = byteBuffer.get() & 255;
        if (i2 < 254) {
            return i2;
        }
        return i2 == 254 ? byteBuffer.getChar() : byteBuffer.getInt();
    }

    public static void g(o oVar, int i2) {
        int size = oVar.size() % i2;
        if (size != 0) {
            for (int i3 = 0; i3 < i2 - size; i3++) {
                oVar.write(0);
            }
        }
    }

    public static void h(o oVar, int i2) {
        if (f596b) {
            oVar.write(i2);
            oVar.write(i2 >>> 8);
            oVar.write(i2 >>> 16);
            oVar.write(i2 >>> 24);
            return;
        }
        oVar.write(i2 >>> 24);
        oVar.write(i2 >>> 16);
        oVar.write(i2 >>> 8);
        oVar.write(i2);
    }

    public static void i(o oVar, long j2) {
        if (f596b) {
            oVar.write((byte) j2);
            oVar.write((byte) (j2 >>> 8));
            oVar.write((byte) (j2 >>> 16));
            oVar.write((byte) (j2 >>> 24));
            oVar.write((byte) (j2 >>> 32));
            oVar.write((byte) (j2 >>> 40));
            oVar.write((byte) (j2 >>> 48));
            oVar.write((byte) (j2 >>> 56));
            return;
        }
        oVar.write((byte) (j2 >>> 56));
        oVar.write((byte) (j2 >>> 48));
        oVar.write((byte) (j2 >>> 40));
        oVar.write((byte) (j2 >>> 32));
        oVar.write((byte) (j2 >>> 24));
        oVar.write((byte) (j2 >>> 16));
        oVar.write((byte) (j2 >>> 8));
        oVar.write((byte) j2);
    }

    public static void j(o oVar, int i2) {
        if (i2 < 254) {
            oVar.write(i2);
            return;
        }
        if (i2 > 65535) {
            oVar.write(255);
            h(oVar, i2);
            return;
        }
        oVar.write(254);
        if (f596b) {
            oVar.write(i2);
            oVar.write(i2 >>> 8);
        } else {
            oVar.write(i2 >>> 8);
            oVar.write(i2);
        }
    }

    @Override // Z.l
    public final Object a(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        byteBuffer.order(ByteOrder.nativeOrder());
        Object objE = e(byteBuffer);
        if (byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Message corrupted");
        }
        return objE;
    }

    @Override // Z.l
    public final ByteBuffer b(Object obj) {
        if (obj == null) {
            return null;
        }
        o oVar = new o();
        k(oVar, obj);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(oVar.size());
        byteBufferAllocateDirect.put(oVar.a(), 0, oVar.size());
        return byteBufferAllocateDirect;
    }

    public final Object e(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            return f(byteBuffer.get(), byteBuffer);
        }
        throw new IllegalArgumentException("Message corrupted");
    }

    public Object f(byte b2, ByteBuffer byteBuffer) {
        Charset charset = f597c;
        int i2 = 0;
        switch (b2) {
            case 0:
                return null;
            case 1:
                return Boolean.TRUE;
            case 2:
                return Boolean.FALSE;
            case 3:
                return Integer.valueOf(byteBuffer.getInt());
            case 4:
                return Long.valueOf(byteBuffer.getLong());
            case 5:
                byte[] bArr = new byte[d(byteBuffer)];
                byteBuffer.get(bArr);
                return new BigInteger(new String(bArr, charset), 16);
            case 6:
                c(byteBuffer, 8);
                return Double.valueOf(byteBuffer.getDouble());
            case 7:
                byte[] bArr2 = new byte[d(byteBuffer)];
                byteBuffer.get(bArr2);
                return new String(bArr2, charset);
            case 8:
                byte[] bArr3 = new byte[d(byteBuffer)];
                byteBuffer.get(bArr3);
                return bArr3;
            case 9:
                int iD = d(byteBuffer);
                int[] iArr = new int[iD];
                c(byteBuffer, 4);
                byteBuffer.asIntBuffer().get(iArr);
                byteBuffer.position((iD * 4) + byteBuffer.position());
                return iArr;
            case 10:
                int iD2 = d(byteBuffer);
                long[] jArr = new long[iD2];
                c(byteBuffer, 8);
                byteBuffer.asLongBuffer().get(jArr);
                byteBuffer.position((iD2 * 8) + byteBuffer.position());
                return jArr;
            case 11:
                int iD3 = d(byteBuffer);
                double[] dArr = new double[iD3];
                c(byteBuffer, 8);
                byteBuffer.asDoubleBuffer().get(dArr);
                byteBuffer.position((iD3 * 8) + byteBuffer.position());
                return dArr;
            case 12:
                int iD4 = d(byteBuffer);
                ArrayList arrayList = new ArrayList(iD4);
                while (i2 < iD4) {
                    arrayList.add(e(byteBuffer));
                    i2++;
                }
                return arrayList;
            case 13:
                int iD5 = d(byteBuffer);
                HashMap map = new HashMap();
                while (i2 < iD5) {
                    map.put(e(byteBuffer), e(byteBuffer));
                    i2++;
                }
                return map;
            case 14:
                int iD6 = d(byteBuffer);
                float[] fArr = new float[iD6];
                c(byteBuffer, 4);
                byteBuffer.asFloatBuffer().get(fArr);
                byteBuffer.position((iD6 * 4) + byteBuffer.position());
                return fArr;
            default:
                throw new IllegalArgumentException("Message corrupted");
        }
    }

    public void k(o oVar, Object obj) {
        int i2 = 0;
        if (obj == null || obj.equals(null)) {
            oVar.write(0);
            return;
        }
        if (obj instanceof Boolean) {
            oVar.write(((Boolean) obj).booleanValue() ? 1 : 2);
            return;
        }
        boolean z2 = obj instanceof Number;
        Charset charset = f597c;
        if (z2) {
            if ((obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                oVar.write(3);
                h(oVar, ((Number) obj).intValue());
                return;
            }
            if (obj instanceof Long) {
                oVar.write(4);
                i(oVar, ((Long) obj).longValue());
                return;
            }
            if ((obj instanceof Float) || (obj instanceof Double)) {
                oVar.write(6);
                g(oVar, 8);
                i(oVar, Double.doubleToLongBits(((Number) obj).doubleValue()));
                return;
            } else {
                if (!(obj instanceof BigInteger)) {
                    throw new IllegalArgumentException("Unsupported Number type: " + obj.getClass());
                }
                oVar.write(5);
                byte[] bytes = ((BigInteger) obj).toString(16).getBytes(charset);
                j(oVar, bytes.length);
                oVar.write(bytes, 0, bytes.length);
                return;
            }
        }
        if (obj instanceof CharSequence) {
            oVar.write(7);
            byte[] bytes2 = obj.toString().getBytes(charset);
            j(oVar, bytes2.length);
            oVar.write(bytes2, 0, bytes2.length);
            return;
        }
        if (obj instanceof byte[]) {
            oVar.write(8);
            byte[] bArr = (byte[]) obj;
            j(oVar, bArr.length);
            oVar.write(bArr, 0, bArr.length);
            return;
        }
        if (obj instanceof int[]) {
            oVar.write(9);
            int[] iArr = (int[]) obj;
            j(oVar, iArr.length);
            g(oVar, 4);
            int length = iArr.length;
            while (i2 < length) {
                h(oVar, iArr[i2]);
                i2++;
            }
            return;
        }
        if (obj instanceof long[]) {
            oVar.write(10);
            long[] jArr = (long[]) obj;
            j(oVar, jArr.length);
            g(oVar, 8);
            int length2 = jArr.length;
            while (i2 < length2) {
                i(oVar, jArr[i2]);
                i2++;
            }
            return;
        }
        if (obj instanceof double[]) {
            oVar.write(11);
            double[] dArr = (double[]) obj;
            j(oVar, dArr.length);
            g(oVar, 8);
            int length3 = dArr.length;
            while (i2 < length3) {
                i(oVar, Double.doubleToLongBits(dArr[i2]));
                i2++;
            }
            return;
        }
        if (obj instanceof List) {
            oVar.write(12);
            List list = (List) obj;
            j(oVar, list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                k(oVar, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            oVar.write(13);
            Map map = (Map) obj;
            j(oVar, map.size());
            for (Map.Entry entry : map.entrySet()) {
                k(oVar, entry.getKey());
                k(oVar, entry.getValue());
            }
            return;
        }
        if (!(obj instanceof float[])) {
            throw new IllegalArgumentException("Unsupported value: '" + obj + "' of type '" + obj.getClass() + "'");
        }
        oVar.write(14);
        float[] fArr = (float[]) obj;
        j(oVar, fArr.length);
        g(oVar, 4);
        int length4 = fArr.length;
        while (i2 < length4) {
            h(oVar, Float.floatToIntBits(fArr[i2]));
            i2++;
        }
    }
}
