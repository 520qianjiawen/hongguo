package p014o;

import H.b;
import P.e;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f1174a = new b(12);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f1175b = {112, 114, 111, 0};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f1176c = {112, 114, 109, 0};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final byte[] f1177d = {48, 49, 53, 0};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f1178e = {48, 49, 48, 0};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f1179f = {48, 48, 57, 0};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[] f1180g = {48, 48, 53, 0};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final byte[] f1181h = {48, 48, 49, 0};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final byte[] f1182i = {48, 48, 49, 0};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final byte[] f1183j = {48, 48, 50, 0};

    public static byte[] a(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                try {
                    deflaterOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            deflater.end();
            throw th3;
        }
    }

    public static byte[] b(b[] bVarArr, byte[] bArr) throws IOException {
        int length = 0;
        for (b bVar : bVarArr) {
            length += ((((bVar.f1170g * 2) + 7) & (-8)) / 8) + (bVar.f1168e * 2) + d(bVar.f1164a, bVar.f1165b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + bVar.f1169f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        if (Arrays.equals(bArr, f1179f)) {
            for (b bVar2 : bVarArr) {
                p(byteArrayOutputStream, bVar2, d(bVar2.f1164a, bVar2.f1165b, bArr));
                r(byteArrayOutputStream, bVar2);
                int[] iArr = bVar2.f1171h;
                int length2 = iArr.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length2) {
                    int i4 = iArr[i2];
                    u(byteArrayOutputStream, i4 - i3);
                    i2++;
                    i3 = i4;
                }
                q(byteArrayOutputStream, bVar2);
            }
        } else {
            for (b bVar3 : bVarArr) {
                p(byteArrayOutputStream, bVar3, d(bVar3.f1164a, bVar3.f1165b, bArr));
            }
            for (b bVar4 : bVarArr) {
                r(byteArrayOutputStream, bVar4);
                int[] iArr2 = bVar4.f1171h;
                int length3 = iArr2.length;
                int i5 = 0;
                int i6 = 0;
                while (i5 < length3) {
                    int i7 = iArr2[i5];
                    u(byteArrayOutputStream, i7 - i6);
                    i5++;
                    i6 = i7;
                }
                q(byteArrayOutputStream, bVar4);
            }
        }
        if (byteArrayOutputStream.size() == length) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + length);
    }

    public static boolean c(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        boolean z2 = true;
        for (File file2 : fileArrListFiles) {
            z2 = c(file2) && z2;
        }
        return z2;
    }

    public static String d(String str, String str2, byte[] bArr) {
        byte[] bArr2 = f1181h;
        boolean zEquals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = f1180g;
        Object obj = (zEquals || Arrays.equals(bArr, bArr3)) ? ":" : "!";
        if (str.length() <= 0) {
            if ("!".equals(obj)) {
                return str2.replace(":", "!");
            }
            if (":".equals(obj)) {
                return str2.replace("!", ":");
            }
        } else {
            if (str2.equals("classes.dex")) {
                return str;
            }
            if (str2.contains("!") || str2.contains(":")) {
                if ("!".equals(obj)) {
                    return str2.replace(":", "!");
                }
                if (":".equals(obj)) {
                    return str2.replace("!", ":");
                }
            } else if (!str2.endsWith(".apk")) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append((Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!");
                sb.append(str2);
                return sb.toString();
            }
        }
        return str2;
    }

    public static void e(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
        }
    }

    public static byte[] f(InputStream inputStream, int i2) throws IOException {
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i3, i2 - i3);
            if (i4 < 0) {
                throw new IllegalStateException("Not enough bytes to read: " + i2);
            }
            i3 += i4;
        }
        return bArr;
    }

    public static int[] g(ByteArrayInputStream byteArrayInputStream, int i2) {
        int[] iArr = new int[i2];
        int iM = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            iM += (int) m(byteArrayInputStream, 2);
            iArr[i3] = iM;
        }
        return iArr;
    }

    public static byte[] h(FileInputStream fileInputStream, int i2, int i3) {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i3];
            byte[] bArr2 = new byte[2048];
            int i4 = 0;
            int iInflate = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i4 < i2) {
                int i5 = fileInputStream.read(bArr2);
                if (i5 < 0) {
                    throw new IllegalStateException("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i2 + " bytes");
                }
                inflater.setInput(bArr2, 0, i5);
                try {
                    iInflate += inflater.inflate(bArr, iInflate, i3 - iInflate);
                    i4 += i5;
                } catch (DataFormatException e2) {
                    throw new IllegalStateException(e2.getMessage());
                }
            }
            if (i4 == i2) {
                if (!inflater.finished()) {
                    throw new IllegalStateException("Inflater did not finish");
                }
                inflater.end();
                return bArr;
            }
            throw new IllegalStateException("Didn't read enough bytes during decompression. expected=" + i2 + " actual=" + i4);
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }

    public static b[] i(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, b[] bVarArr) throws IOException {
        byte[] bArr3 = f1182i;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, f1183j)) {
                throw new IllegalStateException("Unsupported meta version");
            }
            int iM = (int) m(fileInputStream, 2);
            byte[] bArrH = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                throw new IllegalStateException("Content found after the end of file");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrH);
            try {
                b[] bVarArrK = k(byteArrayInputStream, bArr2, iM, bVarArr);
                byteArrayInputStream.close();
                return bVarArrK;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(f1177d, bArr2)) {
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (!Arrays.equals(bArr, bArr3)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int iM2 = (int) m(fileInputStream, 1);
        byte[] bArrH2 = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrH2);
        try {
            b[] bVarArrJ = j(byteArrayInputStream2, iM2, bVarArr);
            byteArrayInputStream2.close();
            return bVarArrJ;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static b[] j(ByteArrayInputStream byteArrayInputStream, int i2, b[] bVarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new b[0];
        }
        if (i2 != bVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i2];
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int iM = (int) m(byteArrayInputStream, 2);
            iArr[i3] = (int) m(byteArrayInputStream, 2);
            strArr[i3] = new String(f(byteArrayInputStream, iM), StandardCharsets.UTF_8);
        }
        for (int i4 = 0; i4 < i2; i4++) {
            b bVar = bVarArr[i4];
            if (!bVar.f1165b.equals(strArr[i4])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i5 = iArr[i4];
            bVar.f1168e = i5;
            bVar.f1171h = g(byteArrayInputStream, i5);
        }
        return bVarArr;
    }

    public static b[] k(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i2, b[] bVarArr) throws IOException {
        if (byteArrayInputStream.available() == 0) {
            return new b[0];
        }
        if (i2 != bVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            m(byteArrayInputStream, 2);
            String str = new String(f(byteArrayInputStream, (int) m(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long jM = m(byteArrayInputStream, 4);
            int iM = (int) m(byteArrayInputStream, 2);
            b bVar = null;
            if (bVarArr.length > 0) {
                int iIndexOf = str.indexOf("!");
                if (iIndexOf < 0) {
                    iIndexOf = str.indexOf(":");
                }
                String strSubstring = iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
                for (int i4 = 0; i4 < bVarArr.length; i4++) {
                    if (bVarArr[i4].f1165b.equals(strSubstring)) {
                        bVar = bVarArr[i4];
                        break;
                    }
                }
            }
            if (bVar == null) {
                throw new IllegalStateException("Missing profile key: ".concat(str));
            }
            bVar.f1167d = jM;
            int[] iArrG = g(byteArrayInputStream, iM);
            if (Arrays.equals(bArr, f1181h)) {
                bVar.f1168e = iM;
                bVar.f1171h = iArrG;
            }
        }
        return bVarArr;
    }

    public static b[] l(FileInputStream fileInputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, f1178e)) {
            throw new IllegalStateException("Unsupported version");
        }
        int iM = (int) m(fileInputStream, 1);
        byte[] bArrH = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrH);
        try {
            b[] bVarArrN = n(byteArrayInputStream, str, iM);
            byteArrayInputStream.close();
            return bVarArrN;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long m(InputStream inputStream, int i2) throws IOException {
        byte[] bArrF = f(inputStream, i2);
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j2 += ((long) (bArrF[i3] & 255)) << (i3 * 8);
        }
        return j2;
    }

    public static b[] n(ByteArrayInputStream byteArrayInputStream, String str, int i2) throws IOException {
        int i3 = 0;
        if (byteArrayInputStream.available() == 0) {
            return new b[0];
        }
        b[] bVarArr = new b[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            int iM = (int) m(byteArrayInputStream, 2);
            int iM2 = (int) m(byteArrayInputStream, 2);
            bVarArr[i4] = new b(str, new String(f(byteArrayInputStream, iM), StandardCharsets.UTF_8), m(byteArrayInputStream, 4), iM2, (int) m(byteArrayInputStream, 4), (int) m(byteArrayInputStream, 4), new int[iM2], new TreeMap());
        }
        int i5 = 0;
        while (i5 < i2) {
            b bVar = bVarArr[i5];
            int iAvailable = byteArrayInputStream.available();
            int i6 = bVar.f1169f;
            int i7 = bVar.f1170g;
            TreeMap treeMap = bVar.f1172i;
            int i8 = iAvailable - i6;
            int iM3 = i3;
            while (byteArrayInputStream.available() > i8) {
                iM3 += (int) m(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(iM3), 1);
                int iM4 = (int) m(byteArrayInputStream, 2);
                while (iM4 > 0) {
                    m(byteArrayInputStream, 2);
                    int iM5 = (int) m(byteArrayInputStream, 1);
                    if (iM5 != 6 && iM5 != 7) {
                        while (iM5 > 0) {
                            m(byteArrayInputStream, 1);
                            int i9 = i3;
                            int i10 = i5;
                            for (int iM6 = (int) m(byteArrayInputStream, 1); iM6 > 0; iM6--) {
                                m(byteArrayInputStream, 2);
                            }
                            iM5--;
                            i3 = i9;
                            i5 = i10;
                        }
                    }
                    iM4--;
                    i3 = i3;
                    i5 = i5;
                }
            }
            int i11 = i3;
            int i12 = i5;
            if (byteArrayInputStream.available() != i8) {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
            bVar.f1171h = g(byteArrayInputStream, bVar.f1168e);
            BitSet bitSetValueOf = BitSet.valueOf(f(byteArrayInputStream, (((i7 * 2) + 7) & (-8)) / 8));
            for (int i13 = i11; i13 < i7; i13++) {
                int i14 = bitSetValueOf.get(i13) ? 2 : i11;
                if (bitSetValueOf.get(i13 + i7)) {
                    i14 |= 4;
                }
                if (i14 != 0) {
                    Integer numValueOf = (Integer) treeMap.get(Integer.valueOf(i13));
                    if (numValueOf == null) {
                        numValueOf = Integer.valueOf(i11);
                    }
                    treeMap.put(Integer.valueOf(i13), Integer.valueOf(i14 | numValueOf.intValue()));
                }
            }
            i5 = i12 + 1;
            i3 = i11;
        }
        return bVarArr;
    }

    public static boolean o(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, b[] bVarArr) throws IOException {
        long j2;
        ArrayList arrayList;
        int length;
        byte[] bArr2 = f1177d;
        int i2 = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = f1178e;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] bArrB = b(bVarArr, bArr3);
                t(byteArrayOutputStream, bVarArr.length, 1);
                t(byteArrayOutputStream, bArrB.length, 4);
                byte[] bArrA = a(bArrB);
                t(byteArrayOutputStream, bArrA.length, 4);
                byteArrayOutputStream.write(bArrA);
                return true;
            }
            byte[] bArr4 = f1180g;
            if (Arrays.equals(bArr, bArr4)) {
                t(byteArrayOutputStream, bVarArr.length, 1);
                for (b bVar : bVarArr) {
                    int size = bVar.f1172i.size() * 4;
                    String strD = d(bVar.f1164a, bVar.f1165b, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    u(byteArrayOutputStream, strD.getBytes(charset).length);
                    u(byteArrayOutputStream, bVar.f1171h.length);
                    t(byteArrayOutputStream, size, 4);
                    t(byteArrayOutputStream, bVar.f1166c, 4);
                    byteArrayOutputStream.write(strD.getBytes(charset));
                    Iterator it = bVar.f1172i.keySet().iterator();
                    while (it.hasNext()) {
                        u(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        u(byteArrayOutputStream, 0);
                    }
                    for (int i3 : bVar.f1171h) {
                        u(byteArrayOutputStream, i3);
                    }
                }
                return true;
            }
            byte[] bArr5 = f1179f;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] bArrB2 = b(bVarArr, bArr5);
                t(byteArrayOutputStream, bVarArr.length, 1);
                t(byteArrayOutputStream, bArrB2.length, 4);
                byte[] bArrA2 = a(bArrB2);
                t(byteArrayOutputStream, bArrA2.length, 4);
                byteArrayOutputStream.write(bArrA2);
                return true;
            }
            byte[] bArr6 = f1181h;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            u(byteArrayOutputStream, bVarArr.length);
            for (b bVar2 : bVarArr) {
                String str = bVar2.f1164a;
                TreeMap treeMap = bVar2.f1172i;
                String strD2 = d(str, bVar2.f1165b, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                u(byteArrayOutputStream, strD2.getBytes(charset2).length);
                u(byteArrayOutputStream, treeMap.size());
                u(byteArrayOutputStream, bVar2.f1171h.length);
                t(byteArrayOutputStream, bVar2.f1166c, 4);
                byteArrayOutputStream.write(strD2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    u(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i4 : bVar2.f1171h) {
                    u(byteArrayOutputStream, i4);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            u(byteArrayOutputStream2, bVarArr.length);
            int i5 = 2;
            int i6 = 2;
            for (b bVar3 : bVarArr) {
                t(byteArrayOutputStream2, bVar3.f1166c, 4);
                t(byteArrayOutputStream2, bVar3.f1167d, 4);
                t(byteArrayOutputStream2, bVar3.f1170g, 4);
                String strD3 = d(bVar3.f1164a, bVar3.f1165b, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = strD3.getBytes(charset3).length;
                u(byteArrayOutputStream2, length2);
                i6 = i6 + 14 + length2;
                byteArrayOutputStream2.write(strD3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i6 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i6 + ", does not match actual size " + byteArray.length);
            }
            o oVar = new o(1, byteArray, false);
            byteArrayOutputStream2.close();
            arrayList2.add(oVar);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i7 = 0;
            int i8 = 0;
            while (i7 < bVarArr.length) {
                try {
                    b bVar4 = bVarArr[i7];
                    u(byteArrayOutputStream3, i7);
                    u(byteArrayOutputStream3, bVar4.f1168e);
                    i8 = i8 + 4 + (bVar4.f1168e * i5);
                    int[] iArr = bVar4.f1171h;
                    int length3 = iArr.length;
                    int i9 = i2;
                    int i10 = i5;
                    int i11 = i9;
                    while (i11 < length3) {
                        int i12 = iArr[i11];
                        u(byteArrayOutputStream3, i12 - i9);
                        i11++;
                        i9 = i12;
                    }
                    i7++;
                    i5 = i10;
                    i2 = 0;
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                        throw th;
                    }
                }
            }
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i8 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i8 + ", does not match actual size " + byteArray2.length);
            }
            o oVar2 = new o(3, byteArray2, true);
            byteArrayOutputStream3.close();
            arrayList2.add(oVar2);
            ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
            int i13 = 0;
            int i14 = 0;
            while (i13 < bVarArr.length) {
                try {
                    b bVar5 = bVarArr[i13];
                    Iterator it3 = bVar5.f1172i.entrySet().iterator();
                    int iIntValue = 0;
                    while (it3.hasNext()) {
                        iIntValue |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
                    try {
                        q(byteArrayOutputStream5, bVar5);
                        byte[] byteArray3 = byteArrayOutputStream5.toByteArray();
                        byteArrayOutputStream5.close();
                        ByteArrayOutputStream byteArrayOutputStream6 = new ByteArrayOutputStream();
                        try {
                            r(byteArrayOutputStream6, bVar5);
                            byte[] byteArray4 = byteArrayOutputStream6.toByteArray();
                            byteArrayOutputStream6.close();
                            u(byteArrayOutputStream4, i13);
                            int length4 = byteArray3.length + 2 + byteArray4.length;
                            int i15 = i14 + 6;
                            ArrayList arrayList4 = arrayList3;
                            t(byteArrayOutputStream4, length4, 4);
                            u(byteArrayOutputStream4, iIntValue);
                            byteArrayOutputStream4.write(byteArray3);
                            byteArrayOutputStream4.write(byteArray4);
                            i14 = i15 + length4;
                            i13++;
                            arrayList3 = arrayList4;
                        } catch (Throwable th3) {
                            try {
                                byteArrayOutputStream6.close();
                                throw th3;
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                                throw th3;
                            }
                        }
                    } catch (Throwable th5) {
                        try {
                            byteArrayOutputStream5.close();
                            throw th5;
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                            throw th5;
                        }
                    }
                } catch (Throwable th7) {
                    try {
                        byteArrayOutputStream4.close();
                        throw th7;
                    } catch (Throwable th8) {
                        th7.addSuppressed(th8);
                        throw th7;
                    }
                }
            }
            ArrayList arrayList5 = arrayList3;
            byte[] byteArray5 = byteArrayOutputStream4.toByteArray();
            if (i14 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i14 + ", does not match actual size " + byteArray5.length);
            }
            o oVar3 = new o(4, byteArray5, true);
            byteArrayOutputStream4.close();
            arrayList2.add(oVar3);
            long j3 = 4;
            long size2 = j3 + j3 + 4 + ((long) (arrayList2.size() * 16));
            t(byteArrayOutputStream, arrayList2.size(), 4);
            int i16 = 0;
            while (i16 < arrayList2.size()) {
                o oVar4 = (o) arrayList2.get(i16);
                int i17 = oVar4.f1194a;
                byte[] bArr7 = oVar4.f1195b;
                if (i17 == 1) {
                    j2 = 0;
                } else if (i17 == 2) {
                    j2 = 1;
                } else if (i17 == 3) {
                    j2 = 2;
                } else if (i17 == 4) {
                    j2 = 3;
                } else {
                    if (i17 != 5) {
                        throw null;
                    }
                    j2 = 4;
                }
                t(byteArrayOutputStream, j2, 4);
                t(byteArrayOutputStream, size2, 4);
                if (oVar4.f1196c) {
                    long length5 = bArr7.length;
                    byte[] bArrA3 = a(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(bArrA3);
                    t(byteArrayOutputStream, bArrA3.length, 4);
                    t(byteArrayOutputStream, length5, 4);
                    length = bArrA3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    t(byteArrayOutputStream, bArr7.length, 4);
                    t(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += (long) length;
                i16++;
                arrayList5 = arrayList;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i18 = 0; i18 < arrayList6.size(); i18++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i18));
            }
            return true;
        } catch (Throwable th9) {
            try {
                byteArrayOutputStream2.close();
                throw th9;
            } catch (Throwable th10) {
                th9.addSuppressed(th10);
                throw th9;
            }
        }
    }

    public static void p(ByteArrayOutputStream byteArrayOutputStream, b bVar, String str) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        u(byteArrayOutputStream, str.getBytes(charset).length);
        u(byteArrayOutputStream, bVar.f1168e);
        t(byteArrayOutputStream, bVar.f1169f, 4);
        t(byteArrayOutputStream, bVar.f1166c, 4);
        t(byteArrayOutputStream, bVar.f1170g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void q(ByteArrayOutputStream byteArrayOutputStream, b bVar) throws IOException {
        byte[] bArr = new byte[(((bVar.f1170g * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : bVar.f1172i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            if ((iIntValue2 & 2) != 0) {
                int i2 = iIntValue / 8;
                bArr[i2] = (byte) (bArr[i2] | (1 << (iIntValue % 8)));
            }
            if ((iIntValue2 & 4) != 0) {
                int i3 = iIntValue + bVar.f1170g;
                int i4 = i3 / 8;
                bArr[i4] = (byte) ((1 << (i3 % 8)) | bArr[i4]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void r(ByteArrayOutputStream byteArrayOutputStream, b bVar) throws IOException {
        int i2 = 0;
        for (Map.Entry entry : bVar.f1172i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                u(byteArrayOutputStream, iIntValue - i2);
                u(byteArrayOutputStream, 0);
                i2 = iIntValue;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:105:0x018d A[Catch: all -> 0x018a, TRY_ENTER, TryCatch #21 {all -> 0x018a, blocks: (B:92:0x0169, B:94:0x0175, B:105:0x018d, B:106:0x0192), top: B:246:0x0169, outer: #27 }] */
    /* JADX WARN: Code duplicated, block: B:112:0x019c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x019e A[Catch: IllegalStateException -> 0x0184, IOException -> 0x0186, FileNotFoundException -> 0x0188, TRY_LEAVE, TryCatch #27 {FileNotFoundException -> 0x0188, IOException -> 0x0186, IllegalStateException -> 0x0184, blocks: (B:90:0x0161, B:95:0x017f, B:113:0x019e, B:111:0x019b, B:110:0x0198, B:92:0x0169, B:94:0x0175, B:105:0x018d, B:106:0x0192, B:107:0x0193), top: B:261:0x0161, inners: #21, #29 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:130:0x01de A[Catch: all -> 0x01ec, TRY_LEAVE, TryCatch #7 {all -> 0x01ec, blocks: (B:128:0x01d2, B:130:0x01de, B:139:0x01ef), top: B:234:0x01d2, outer: #28 }] */
    /* JADX WARN: Code duplicated, block: B:139:0x01ef A[Catch: all -> 0x01ec, TRY_ENTER, TRY_LEAVE, TryCatch #7 {all -> 0x01ec, blocks: (B:128:0x01d2, B:130:0x01de, B:139:0x01ef), top: B:234:0x01d2, outer: #28 }] */
    /* JADX WARN: Code duplicated, block: B:150:0x020c  */
    /* JADX WARN: Code duplicated, block: B:154:0x0218  */
    /* JADX WARN: Code duplicated, block: B:155:0x021c  */
    /* JADX WARN: Code duplicated, block: B:163:0x0238 A[Catch: all -> 0x025a, TRY_LEAVE, TryCatch #31 {all -> 0x025a, blocks: (B:160:0x0230, B:161:0x0232, B:163:0x0238), top: B:248:0x0230 }] */
    /* JADX WARN: Code duplicated, block: B:204:0x0287  */
    /* JADX WARN: Code duplicated, block: B:208:0x0291  */
    /* JADX WARN: Code duplicated, block: B:213:0x029e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:215:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:246:0x0169 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:255:0x0220 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:260:0x01cd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:261:0x0161 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:262:0x023d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0175 A[Catch: all -> 0x018a, TRY_LEAVE, TryCatch #21 {all -> 0x018a, blocks: (B:92:0x0169, B:94:0x0175, B:105:0x018d, B:106:0x0192), top: B:246:0x0169, outer: #27 }] */
    /* JADX WARN: Multi-variable type inference failed */
    public static void s(Context context, Executor executor, e eVar, boolean z2) {
        char c2;
        FileInputStream fileInputStreamG;
        byte[] bArr;
        b[] bVarArrL;
        e eVar2;
        b[] bVarArr;
        byte[] bArr2;
        byte[] bArr3;
        boolean z3;
        ByteArrayInputStream byteArrayInputStream;
        FileOutputStream fileOutputStream;
        Throwable th;
        byte[] bArr4;
        int i2;
        boolean z4;
        ByteArrayOutputStream byteArrayOutputStream;
        int i3;
        e eVar3;
        FileInputStream fileInputStreamG2;
        boolean z5;
        boolean z6;
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        ApplicationInfo applicationInfo = applicationContext.getApplicationInfo();
        AssetManager assets = applicationContext.getAssets();
        String name = new File(applicationInfo.sourceDir).getName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            File filesDir = context.getFilesDir();
            if (!z2) {
                File file = new File(filesDir, "profileinstaller_profileWrittenFor_lastUpdateTime.dat");
                if (file.exists()) {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                        try {
                            long j2 = dataInputStream.readLong();
                            dataInputStream.close();
                            z6 = j2 == packageInfo.lastUpdateTime;
                            if (z6) {
                                eVar.h(2, null);
                            }
                        } catch (Throwable th2) {
                            try {
                                dataInputStream.close();
                                throw th2;
                            } catch (Throwable th3) {
                                th2.addSuppressed(th3);
                                throw th2;
                            }
                        }
                    } catch (IOException unused) {
                        z6 = false;
                    }
                } else {
                    z6 = false;
                }
                if (z6) {
                    Log.d("ProfileInstaller", "Skipping profile installation for " + context.getPackageName());
                    n.c(context, false);
                    return;
                }
            }
            Log.d("ProfileInstaller", "Installing profile for " + context.getPackageName());
            int i4 = Build.VERSION.SDK_INT;
            File file2 = new File(new File("/data/misc/profiles/cur/0", packageName), "primary.prof");
            e eVar4 = new e(assets, executor, eVar, name, file2);
            byte[] bArr5 = (byte[]) eVar4.f423d;
            if (bArr5 == null) {
                eVar4.h(3, Integer.valueOf(i4));
            } else {
                try {
                    try {
                        if (file2.exists()) {
                            if (!file2.canWrite()) {
                                eVar4.h(4, null);
                            }
                            if (z4 || !z2) {
                                z5 = 0;
                            } else {
                                z5 = c2;
                            }
                            n.c(context, z5);
                        }
                        try {
                            file2.createNewFile();
                        } catch (IOException unused2) {
                            c2 = 1;
                            eVar4.h(4, null);
                            z4 = false;
                        }
                        fileInputStreamG = eVar4.g(assets, "dexopt/baseline.prof");
                    } catch (FileNotFoundException e2) {
                        eVar.h(6, e2);
                        fileInputStreamG = null;
                    } catch (IOException e3) {
                        eVar.h(7, e3);
                        fileInputStreamG = null;
                    }
                    if (fileInputStreamG != null) {
                        try {
                            if (!Arrays.equals(bArr, f(fileInputStreamG, 4))) {
                                throw new IllegalStateException("Invalid magic");
                            }
                            bVarArrL = l(fileInputStreamG, f(fileInputStreamG, 4), (String) eVar4.f425f);
                            try {
                                fileInputStreamG.close();
                            } catch (IOException e4) {
                                eVar.h(7, e4);
                            }
                            eVar4.f426g = bVarArrL;
                        } catch (IOException e5) {
                            eVar.h(7, e5);
                            try {
                                fileInputStreamG.close();
                            } catch (IOException e6) {
                                eVar.h(7, e6);
                            }
                            bVarArrL = null;
                        } catch (IllegalStateException e7) {
                            eVar.h(8, e7);
                            fileInputStreamG.close();
                            bVarArrL = null;
                        }
                    }
                    b[] bVarArr2 = (b[]) eVar4.f426g;
                    if (bVarArr2 != null && (i3 = Build.VERSION.SDK_INT) <= 34) {
                        if (i3 == 24 || i3 == 25) {
                            try {
                                fileInputStreamG2 = eVar4.g(assets, "dexopt/baseline.profm");
                                if (fileInputStreamG2 == null) {
                                    try {
                                        if (Arrays.equals(f1176c, f(fileInputStreamG2, 4))) {
                                            throw new IllegalStateException("Invalid magic");
                                        }
                                        eVar4.f426g = i(fileInputStreamG2, f(fileInputStreamG2, 4), bArr5, bVarArr2);
                                        fileInputStreamG2.close();
                                        eVar3 = eVar4;
                                    } catch (Throwable th4) {
                                        try {
                                            fileInputStreamG2.close();
                                            throw th4;
                                        } catch (Throwable th5) {
                                            th4.addSuppressed(th5);
                                            throw th4;
                                        }
                                    }
                                } else {
                                    if (fileInputStreamG2 != null) {
                                        fileInputStreamG2.close();
                                    }
                                    eVar3 = null;
                                }
                            } catch (FileNotFoundException e8) {
                                eVar.h(9, e8);
                                eVar3 = null;
                            } catch (IOException e9) {
                                eVar.h(7, e9);
                                eVar3 = null;
                            } catch (IllegalStateException e10) {
                                eVar4.f426g = null;
                                eVar.h(8, e10);
                                eVar3 = null;
                            }
                            if (eVar3 != null) {
                                eVar4 = eVar3;
                            }
                        } else {
                            switch (i3) {
                                case 31:
                                case 32:
                                case 33:
                                case 34:
                                    fileInputStreamG2 = eVar4.g(assets, "dexopt/baseline.profm");
                                    if (fileInputStreamG2 == null) {
                                        if (fileInputStreamG2 != null) {
                                            fileInputStreamG2.close();
                                        }
                                        eVar3 = null;
                                    } else {
                                        if (Arrays.equals(f1176c, f(fileInputStreamG2, 4))) {
                                            throw new IllegalStateException("Invalid magic");
                                        }
                                        eVar4.f426g = i(fileInputStreamG2, f(fileInputStreamG2, 4), bArr5, bVarArr2);
                                        fileInputStreamG2.close();
                                        eVar3 = eVar4;
                                    }
                                    if (eVar3 != null) {
                                        eVar4 = eVar3;
                                        break;
                                    }
                                default:
                                    eVar2 = (e) eVar4.f422c;
                                    bVarArr = (b[]) eVar4.f426g;
                                    bArr2 = (byte[]) eVar4.f423d;
                                    if (bVarArr != null && bArr2 != null) {
                                        if (eVar4.f420a) {
                                            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                                        }
                                        try {
                                            byteArrayOutputStream = new ByteArrayOutputStream();
                                            try {
                                                byteArrayOutputStream.write(bArr);
                                                byteArrayOutputStream.write(bArr2);
                                                if (o(byteArrayOutputStream, bArr2, bVarArr)) {
                                                    eVar4.f427h = byteArrayOutputStream.toByteArray();
                                                    byteArrayOutputStream.close();
                                                    eVar4.f426g = null;
                                                } else {
                                                    eVar2.h(5, null);
                                                    eVar4.f426g = null;
                                                    byteArrayOutputStream.close();
                                                }
                                            } catch (Throwable th6) {
                                                try {
                                                    byteArrayOutputStream.close();
                                                    throw th6;
                                                } catch (Throwable th7) {
                                                    th6.addSuppressed(th7);
                                                    throw th6;
                                                }
                                            }
                                        } catch (IOException e11) {
                                            eVar2.h(7, e11);
                                        } catch (IllegalStateException e12) {
                                            eVar2.h(8, e12);
                                        }
                                    }
                                    bArr3 = (byte[]) eVar4.f427h;
                                    if (bArr3 != null) {
                                        z3 = false;
                                        c2 = 1;
                                    } else {
                                        try {
                                            if (eVar4.f420a) {
                                                throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                                            }
                                            try {
                                                try {
                                                    byteArrayInputStream = new ByteArrayInputStream(bArr3);
                                                    try {
                                                        fileOutputStream = new FileOutputStream((File) eVar4.f424e);
                                                        try {
                                                            try {
                                                                bArr4 = new byte[512];
                                                                while (true) {
                                                                    i2 = byteArrayInputStream.read(bArr4);
                                                                    if (i2 > 0) {
                                                                        fileOutputStream.write(bArr4, 0, i2);
                                                                    } else {
                                                                        c2 = 1;
                                                                        try {
                                                                            eVar4.h(1, null);
                                                                            fileOutputStream.close();
                                                                            byteArrayInputStream.close();
                                                                            eVar4.f427h = null;
                                                                            eVar4.f426g = null;
                                                                            z3 = true;
                                                                        } catch (Throwable th8) {
                                                                            th = th8;
                                                                        }
                                                                    }
                                                                    th = th;
                                                                    try {
                                                                        fileOutputStream.close();
                                                                        throw th;
                                                                    } catch (Throwable th9) {
                                                                        th.addSuppressed(th9);
                                                                        throw th;
                                                                    }
                                                                }
                                                            } catch (Throwable th10) {
                                                                th = th10;
                                                                Throwable th11 = th;
                                                                try {
                                                                    byteArrayInputStream.close();
                                                                    throw th11;
                                                                } catch (Throwable th12) {
                                                                    th11.addSuppressed(th12);
                                                                    throw th11;
                                                                }
                                                            }
                                                        } catch (Throwable th13) {
                                                            th = th13;
                                                        }
                                                    } catch (Throwable th14) {
                                                        th = th14;
                                                    }
                                                } catch (FileNotFoundException e13) {
                                                    e = e13;
                                                    c2 = 1;
                                                    eVar4.h(6, e);
                                                    eVar4.f427h = null;
                                                    eVar4.f426g = null;
                                                    z3 = false;
                                                } catch (IOException e14) {
                                                    e = e14;
                                                    c2 = 1;
                                                    eVar4.h(7, e);
                                                    eVar4.f427h = null;
                                                    eVar4.f426g = null;
                                                    z3 = false;
                                                }
                                            } catch (FileNotFoundException e15) {
                                                e = e15;
                                                eVar4.h(6, e);
                                                eVar4.f427h = null;
                                                eVar4.f426g = null;
                                                z3 = false;
                                            } catch (IOException e16) {
                                                e = e16;
                                                eVar4.h(7, e);
                                                eVar4.f427h = null;
                                                eVar4.f426g = null;
                                                z3 = false;
                                            }
                                        } catch (Throwable th15) {
                                            eVar4.f427h = null;
                                            eVar4.f426g = null;
                                            throw th15;
                                        }
                                    }
                                    if (z3) {
                                        e(packageInfo, filesDir);
                                    }
                                    z4 = z3;
                                    if (z4) {
                                        z5 = 0;
                                    } else {
                                        z5 = 0;
                                    }
                                    n.c(context, z5);
                            }
                        }
                    }
                    eVar2 = (e) eVar4.f422c;
                    bVarArr = (b[]) eVar4.f426g;
                    bArr2 = (byte[]) eVar4.f423d;
                    if (bVarArr != null) {
                        if (eVar4.f420a) {
                            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                        }
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        byteArrayOutputStream.write(bArr);
                        byteArrayOutputStream.write(bArr2);
                        if (o(byteArrayOutputStream, bArr2, bVarArr)) {
                            eVar2.h(5, null);
                            eVar4.f426g = null;
                            byteArrayOutputStream.close();
                        } else {
                            eVar4.f427h = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            eVar4.f426g = null;
                        }
                    }
                    bArr3 = (byte[]) eVar4.f427h;
                    if (bArr3 != null) {
                        if (eVar4.f420a) {
                            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                        }
                        byteArrayInputStream = new ByteArrayInputStream(bArr3);
                        fileOutputStream = new FileOutputStream((File) eVar4.f424e);
                        bArr4 = new byte[512];
                        while (true) {
                            i2 = byteArrayInputStream.read(bArr4);
                            if (i2 > 0) {
                                fileOutputStream.write(bArr4, 0, i2);
                            } else {
                                c2 = 1;
                                eVar4.h(1, null);
                                fileOutputStream.close();
                                byteArrayInputStream.close();
                                eVar4.f427h = null;
                                eVar4.f426g = null;
                                z3 = true;
                            }
                            th = th;
                            fileOutputStream.close();
                            throw th;
                        }
                    }
                    z3 = false;
                    c2 = 1;
                    if (z3) {
                        e(packageInfo, filesDir);
                    }
                    z4 = z3;
                    if (z4) {
                        z5 = 0;
                    } else {
                        z5 = 0;
                    }
                    n.c(context, z5);
                } catch (Throwable th16) {
                    try {
                        fileInputStreamG.close();
                        throw th16;
                    } catch (IOException e17) {
                        eVar.h(7, e17);
                        throw th16;
                    }
                }
                eVar4.f420a = true;
                bArr = f1175b;
                c2 = '\b';
            }
            c2 = 1;
            z4 = false;
            if (z4) {
                z5 = 0;
            } else {
                z5 = 0;
            }
            n.c(context, z5);
        } catch (PackageManager.NameNotFoundException e18) {
            eVar.h(7, e18);
            n.c(context, false);
        }
    }

    public static void t(ByteArrayOutputStream byteArrayOutputStream, long j2, int i2) throws IOException {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((j2 >> (i3 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void u(ByteArrayOutputStream byteArrayOutputStream, int i2) throws IOException {
        t(byteArrayOutputStream, i2, 2);
    }
}
