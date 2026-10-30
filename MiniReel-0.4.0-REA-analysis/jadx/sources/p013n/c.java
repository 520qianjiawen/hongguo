package p013n;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1115a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1116b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f1117c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f1118d;

    public c(byte[] bArr, int i2, int i3) {
        this(-1L, bArr, i2, i3);
    }

    public static c a(long j2, ByteOrder byteOrder) {
        long[] jArr = {j2};
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[g.f1126C[4]]);
        byteBufferWrap.order(byteOrder);
        byteBufferWrap.putInt((int) jArr[0]);
        return new c(byteBufferWrap.array(), 4, 1);
    }

    public static c b(e eVar, ByteOrder byteOrder) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[g.f1126C[5]]);
        byteBufferWrap.order(byteOrder);
        e eVar2 = new e[]{eVar}[0];
        byteBufferWrap.putInt((int) eVar2.f1123a);
        byteBufferWrap.putInt((int) eVar2.f1124b);
        return new c(byteBufferWrap.array(), 5, 1);
    }

    public static c c(int i2, ByteOrder byteOrder) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[g.f1126C[3]]);
        byteBufferWrap.order(byteOrder);
        byteBufferWrap.putShort((short) new int[]{i2}[0]);
        return new c(byteBufferWrap.array(), 3, 1);
    }

    public final double d(ByteOrder byteOrder) throws Throwable {
        Object objG = g(byteOrder);
        if (objG == null) {
            throw new NumberFormatException("NULL can't be converted to a double value");
        }
        if (objG instanceof String) {
            return Double.parseDouble((String) objG);
        }
        if (objG instanceof long[]) {
            long[] jArr = (long[]) objG;
            if (jArr.length == 1) {
                return jArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (objG instanceof int[]) {
            int[] iArr = (int[]) objG;
            if (iArr.length == 1) {
                return iArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (objG instanceof double[]) {
            double[] dArr = (double[]) objG;
            if (dArr.length == 1) {
                return dArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (!(objG instanceof e[])) {
            throw new NumberFormatException("Couldn't find a double value");
        }
        e[] eVarArr = (e[]) objG;
        if (eVarArr.length != 1) {
            throw new NumberFormatException("There are more than one component");
        }
        e eVar = eVarArr[0];
        return eVar.f1123a / eVar.f1124b;
    }

    public final int e(ByteOrder byteOrder) throws Throwable {
        Object objG = g(byteOrder);
        if (objG == null) {
            throw new NumberFormatException("NULL can't be converted to a integer value");
        }
        if (objG instanceof String) {
            return Integer.parseInt((String) objG);
        }
        if (objG instanceof long[]) {
            long[] jArr = (long[]) objG;
            if (jArr.length == 1) {
                return (int) jArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (!(objG instanceof int[])) {
            throw new NumberFormatException("Couldn't find a integer value");
        }
        int[] iArr = (int[]) objG;
        if (iArr.length == 1) {
            return iArr[0];
        }
        throw new NumberFormatException("There are more than one component");
    }

    public final String f(ByteOrder byteOrder) throws Throwable {
        Object objG = g(byteOrder);
        if (objG == null) {
            return null;
        }
        if (objG instanceof String) {
            return (String) objG;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        if (objG instanceof long[]) {
            long[] jArr = (long[]) objG;
            while (i2 < jArr.length) {
                sb.append(jArr[i2]);
                i2++;
                if (i2 != jArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
        if (objG instanceof int[]) {
            int[] iArr = (int[]) objG;
            while (i2 < iArr.length) {
                sb.append(iArr[i2]);
                i2++;
                if (i2 != iArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
        if (objG instanceof double[]) {
            double[] dArr = (double[]) objG;
            while (i2 < dArr.length) {
                sb.append(dArr[i2]);
                i2++;
                if (i2 != dArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
        if (!(objG instanceof e[])) {
            return null;
        }
        e[] eVarArr = (e[]) objG;
        while (i2 < eVarArr.length) {
            sb.append(eVarArr[i2].f1123a);
            sb.append('/');
            sb.append(eVarArr[i2].f1124b);
            i2++;
            if (i2 != eVarArr.length) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:103:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0032: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]) (LINE:51), block:B:17:0x0032 */
    /* JADX WARN: Type inference failed for: r14v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v19, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v23, types: [int[]] */
    /* JADX WARN: Type inference failed for: r14v24, types: [long[]] */
    /* JADX WARN: Type inference failed for: r14v25, types: [n.e[]] */
    /* JADX WARN: Type inference failed for: r14v26, types: [int[]] */
    /* JADX WARN: Type inference failed for: r14v27, types: [int[]] */
    /* JADX WARN: Type inference failed for: r14v28, types: [n.e[]] */
    /* JADX WARN: Type inference failed for: r14v29, types: [double[]] */
    /* JADX WARN: Type inference failed for: r14v30, types: [java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r14v31, types: [double[]] */
    public final Serializable g(ByteOrder byteOrder) throws Throwable {
        b bVar;
        InputStream inputStream;
        ?? str;
        byte b2;
        byte[] bArr = this.f1118d;
        InputStream inputStream2 = null;
        try {
            try {
                bVar = new b(bArr);
                try {
                    bVar.f1112f = byteOrder;
                    int i2 = this.f1115a;
                    int length = 0;
                    int i3 = this.f1116b;
                    switch (i2) {
                        case 1:
                        case 6:
                            if (bArr.length == 1 && (b2 = bArr[0]) >= 0 && b2 <= 1) {
                                String str2 = new String(new char[]{(char) (b2 + 48)});
                                try {
                                    bVar.close();
                                    return str2;
                                } catch (IOException e2) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e2);
                                    return str2;
                                }
                            }
                            str = new String(bArr, g.f1134L);
                            break;
                            break;
                        case 2:
                        case 7:
                            if (i3 >= g.f1127D.length) {
                                int i4 = 0;
                                while (true) {
                                    byte[] bArr2 = g.f1127D;
                                    if (i4 >= bArr2.length) {
                                        length = bArr2.length;
                                    } else if (bArr[i4] == bArr2[i4]) {
                                        i4++;
                                    }
                                }
                            }
                            StringBuilder sb = new StringBuilder();
                            while (length < i3) {
                                byte b3 = bArr[length];
                                if (b3 == 0) {
                                    str = sb.toString();
                                } else {
                                    if (b3 >= 32) {
                                        sb.append((char) b3);
                                    } else {
                                        sb.append('?');
                                    }
                                    length++;
                                }
                                break;
                            }
                            str = sb.toString();
                            break;
                        case 3:
                            str = new int[i3];
                            while (length < i3) {
                                str[length] = bVar.readUnsignedShort();
                                length++;
                            }
                            break;
                        case 4:
                            str = new long[i3];
                            while (length < i3) {
                                str[length] = ((long) bVar.readInt()) & 4294967295L;
                                length++;
                            }
                            break;
                        case 5:
                            str = new e[i3];
                            while (length < i3) {
                                str[length] = new e(((long) bVar.readInt()) & 4294967295L, ((long) bVar.readInt()) & 4294967295L);
                                length++;
                            }
                            break;
                        case 8:
                            str = new int[i3];
                            while (length < i3) {
                                str[length] = bVar.readShort();
                                length++;
                            }
                            break;
                        case 9:
                            str = new int[i3];
                            while (length < i3) {
                                str[length] = bVar.readInt();
                                length++;
                            }
                            break;
                        case 10:
                            str = new e[i3];
                            while (length < i3) {
                                str[length] = new e(bVar.readInt(), bVar.readInt());
                                length++;
                            }
                            break;
                        case 11:
                            str = new double[i3];
                            while (length < i3) {
                                str[length] = bVar.readFloat();
                                length++;
                            }
                            break;
                        case 12:
                            str = new double[i3];
                            while (length < i3) {
                                str[length] = bVar.readDouble();
                                length++;
                            }
                            break;
                        default:
                            try {
                                bVar.close();
                                return null;
                            } catch (IOException e3) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e3);
                                return null;
                            }
                    }
                    try {
                        bVar.close();
                        return str;
                    } catch (IOException e4) {
                        Log.e("ExifInterface", "IOException occurred while closing InputStream", e4);
                        return str;
                    }
                } catch (IOException e5) {
                    e = e5;
                    Log.w("ExifInterface", "IOException occurred during reading a value", e);
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (IOException e6) {
                            Log.e("ExifInterface", "IOException occurred while closing InputStream", e6);
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                inputStream2 = inputStream;
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e7) {
                        Log.e("ExifInterface", "IOException occurred while closing InputStream", e7);
                    }
                }
                throw th;
            }
        } catch (IOException e8) {
            e = e8;
            bVar = null;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream2 != null) {
                inputStream2.close();
            }
            throw th;
        }
    }

    public final String toString() {
        return "(" + g.B[this.f1115a] + ", data length:" + this.f1118d.length + ")";
    }

    public c(long j2, byte[] bArr, int i2, int i3) {
        this.f1115a = i2;
        this.f1116b = i3;
        this.f1117c = j2;
        this.f1118d = bArr;
    }
}
