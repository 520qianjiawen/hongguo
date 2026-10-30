package io.flutter.view;

import android.graphics.Rect;
import android.opengl.Matrix;
import android.text.SpannableString;
import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f970A;
    public String B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f971C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public String f972D;
    public int E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f973F;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public long f976I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f977J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f978K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f979L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public float f980M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public String f981N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public String f982O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public float f983P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public float f984Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public float f985R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public float f986S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public float[] f987T;
    public float[] U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public m f988V;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public ArrayList f990Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public j f991Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f992a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public j f993a0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f996c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public float[] f997c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f998d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f999e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public float[] f1000e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1001f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public Rect f1002f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1003g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1004h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1005i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1006j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1007k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f1008l;
    public float m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f1009n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f1010o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f1011p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList f1012q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f1013r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f1014s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f1015t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ArrayList f1016u;
    public String v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ArrayList f1017w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f1018x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ArrayList f1019y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public String f1020z;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f994b = -1;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f974G = -1;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f975H = false;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final ArrayList f989W = new ArrayList();
    public final ArrayList X = new ArrayList();

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public boolean f995b0 = true;
    public boolean d0 = true;

    public m(n nVar) {
        this.f992a = nVar;
    }

    public static ArrayList d(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) {
        int i2 = byteBuffer.getInt();
        if (i2 == -1) {
            return null;
        }
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = byteBuffer.getInt();
            int i5 = byteBuffer.getInt();
            int i6 = p014o.c.b(2)[byteBuffer.getInt()];
            int iA = p014o.c.a(i6);
            if (iA == 0) {
                byteBuffer.getInt();
                p pVar = new p();
                pVar.f1047a = i4;
                pVar.f1048b = i5;
                pVar.f1049c = i6;
                arrayList.add(pVar);
            } else if (iA == 1) {
                ByteBuffer byteBuffer2 = byteBufferArr[byteBuffer.getInt()];
                o oVar = new o();
                oVar.f1047a = i4;
                oVar.f1048b = i5;
                oVar.f1049c = i6;
                oVar.f1046d = StandardCharsets.UTF_8.decode(byteBuffer2).toString();
                arrayList.add(oVar);
            }
        }
        return arrayList;
    }

    public static void k(float[] fArr, float[] fArr2, float[] fArr3) {
        Matrix.multiplyMV(fArr, 0, fArr2, 0, fArr3, 0);
        float f2 = fArr[3];
        fArr[0] = fArr[0] / f2;
        fArr[1] = fArr[1] / f2;
        fArr[2] = fArr[2] / f2;
        fArr[3] = 0.0f;
    }

    public final void a(ArrayList arrayList) {
        if (h(12)) {
            arrayList.add(this);
        }
        ArrayList arrayList2 = this.f989W;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            i2++;
            ((m) obj).a(arrayList);
        }
    }

    public final String b() {
        String str = this.B;
        return (str == null || str.isEmpty()) ? this.f992a.f1034l : this.B;
    }

    public final String c() {
        String str;
        if (h(13) && (str = this.f1011p) != null && !str.isEmpty()) {
            return this.f1011p;
        }
        ArrayList arrayList = this.f989W;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            String strC = ((m) obj).c();
            if (strC != null && !strC.isEmpty()) {
                return strC;
            }
        }
        return null;
    }

    public final CharSequence e() {
        H.e eVar = new H.e();
        eVar.f174d = this.f1013r;
        eVar.f175e = this.f1014s;
        eVar.f176f = b();
        SpannableString spannableStringA = eVar.a();
        H.e eVar2 = new H.e();
        eVar2.f174d = this.f1011p;
        eVar2.f175e = this.f1012q;
        eVar2.f177g = this.f970A;
        eVar2.f176f = b();
        SpannableString spannableStringA2 = eVar2.a();
        H.e eVar3 = new H.e();
        eVar3.f174d = this.f1018x;
        eVar3.f175e = this.f1019y;
        eVar3.f176f = b();
        CharSequence[] charSequenceArr = {spannableStringA, spannableStringA2, eVar3.a()};
        CharSequence charSequenceConcat = null;
        for (int i2 = 0; i2 < 3; i2++) {
            CharSequence charSequence = charSequenceArr[i2];
            if (charSequence != null && charSequence.length() > 0) {
                charSequenceConcat = (charSequenceConcat == null || charSequenceConcat.length() == 0) ? charSequence : TextUtils.concat(charSequenceConcat, ", ", charSequence);
            }
        }
        return charSequenceConcat;
    }

    public final boolean f(int i2) {
        return (this.f976I & ((long) C0.z.e(i2))) != 0;
    }

    public final boolean g(g gVar) {
        return (gVar.f959d & this.f998d) != 0;
    }

    public final boolean h(int i2) {
        return (this.f996c & ((long) C0.z.e(i2))) != 0;
    }

    public final m i(float[] fArr, boolean z2) {
        float f2 = fArr[3];
        boolean z3 = false;
        float f3 = fArr[0] / f2;
        float f4 = fArr[1] / f2;
        if (f3 < this.f983P || f3 >= this.f985R || f4 < this.f984Q || f4 >= this.f986S) {
            return null;
        }
        float[] fArr2 = new float[4];
        ArrayList arrayList = this.X;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + 1;
            m mVar = (m) arrayList.get(i2);
            if (!mVar.h(14)) {
                if (mVar.f995b0) {
                    mVar.f995b0 = false;
                    if (mVar.f997c0 == null) {
                        mVar.f997c0 = new float[16];
                    }
                    if (mVar.U == null) {
                        float[] fArr3 = new float[16];
                        mVar.U = fArr3;
                        Matrix.setIdentityM(fArr3, 0);
                    }
                    if (!Matrix.invertM(mVar.f997c0, 0, mVar.U, 0)) {
                        Arrays.fill(mVar.f997c0, 0.0f);
                    }
                }
                float[] fArr4 = fArr;
                Matrix.multiplyMV(fArr2, 0, mVar.f997c0, 0, fArr4, 0);
                m mVarI = mVar.i(fArr2, z2);
                if (mVarI != null) {
                    return mVarI;
                }
                fArr = fArr4;
            }
            i2 = i3;
        }
        if (z2 && this.f1005i != -1) {
            z3 = true;
        }
        if (j() || z3) {
            return this;
        }
        return null;
    }

    public final boolean j() {
        if (h(12)) {
            return false;
        }
        if (h(22)) {
            return true;
        }
        if (h(32)) {
            return false;
        }
        int i2 = this.f998d;
        int i3 = n.f1022z;
        if ((i2 & (-61)) != 0 || (this.f996c & ((long) 10682871)) != 0) {
            return true;
        }
        String str = this.f1011p;
        if (str != null && !str.isEmpty()) {
            return true;
        }
        String str2 = this.f1013r;
        if (str2 != null && !str2.isEmpty()) {
            return true;
        }
        String str3 = this.f1018x;
        return (str3 == null || str3.isEmpty()) ? false : true;
    }

    public final void l(float[] fArr, HashSet hashSet, boolean z2, boolean z3) {
        float[] fArr2;
        hashSet.add(this);
        if (this.d0) {
            z2 = true;
        }
        int i2 = 0;
        if (z2) {
            if (this.f1000e0 == null) {
                this.f1000e0 = new float[16];
            }
            if (z3) {
                if (this.U == null) {
                    float[] fArr3 = new float[16];
                    this.U = fArr3;
                    Matrix.setIdentityM(fArr3, 0);
                }
                fArr2 = this.U;
            } else {
                if (this.f987T == null) {
                    float[] fArr4 = new float[16];
                    this.f987T = fArr4;
                    Matrix.setIdentityM(fArr4, 0);
                }
                fArr2 = this.f987T;
            }
            Matrix.multiplyMM(this.f1000e0, 0, fArr, 0, fArr2, 0);
            float[] fArr5 = {this.f983P, this.f984Q, 0.0f, 1.0f};
            float[] fArr6 = new float[4];
            float[] fArr7 = new float[4];
            float[] fArr8 = new float[4];
            float[] fArr9 = new float[4];
            k(fArr6, this.f1000e0, fArr5);
            fArr5[0] = this.f985R;
            fArr5[1] = this.f984Q;
            k(fArr7, this.f1000e0, fArr5);
            fArr5[0] = this.f985R;
            fArr5[1] = this.f986S;
            k(fArr8, this.f1000e0, fArr5);
            fArr5[0] = this.f983P;
            fArr5[1] = this.f986S;
            k(fArr9, this.f1000e0, fArr5);
            if (this.f1002f0 == null) {
                this.f1002f0 = new Rect();
            }
            this.f1002f0.set(Math.round(Math.min(fArr6[0], Math.min(fArr7[0], Math.min(fArr8[0], fArr9[0])))), Math.round(Math.min(fArr6[1], Math.min(fArr7[1], Math.min(fArr8[1], fArr9[1])))), Math.round(Math.max(fArr6[0], Math.max(fArr7[0], Math.max(fArr8[0], fArr9[0])))), Math.round(Math.max(fArr6[1], Math.max(fArr7[1], Math.max(fArr8[1], fArr9[1])))));
            this.d0 = false;
        }
        ArrayList arrayList = this.f989W;
        int size = arrayList.size();
        int i3 = -1;
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayList.get(i4);
            i4++;
            m mVar = (m) obj;
            mVar.f974G = i3;
            i3 = mVar.f994b;
            mVar.l(this.f1000e0, hashSet, z2, false);
        }
        ArrayList arrayList2 = this.X;
        int size2 = arrayList2.size();
        while (i2 < size2) {
            Object obj2 = arrayList2.get(i2);
            i2++;
            m mVar2 = (m) obj2;
            if (!hashSet.contains(mVar2)) {
                mVar2.l(this.f1000e0, hashSet, z2, true);
            }
        }
    }
}
