package io.flutter.view;

import android.opengl.Matrix;
import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c implements P.m, P.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f931a;

    public /* synthetic */ c(Object obj) {
        this.f931a = obj;
    }

    public void a(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
        int i2;
        m mVar;
        int i3;
        int i4;
        m mVar2;
        String str;
        float f2;
        float f3;
        Integer num;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        for (ByteBuffer byteBuffer2 : byteBufferArr) {
            byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
        }
        n nVar = (n) this.f931a;
        HashMap map = nVar.f1029g;
        io.flutter.plugin.platform.h hVar = nVar.f1027e;
        ArrayList arrayList = nVar.f1037p;
        ArrayList arrayList2 = new ArrayList();
        while (true) {
            i2 = 16;
            if (!byteBuffer.hasRemaining()) {
                break;
            }
            m mVarB = nVar.b(byteBuffer.getInt());
            ArrayList arrayList3 = mVarB.X;
            n nVar2 = mVarB.f992a;
            mVarB.f975H = true;
            mVarB.f981N = mVarB.f1013r;
            mVarB.f982O = mVarB.f1011p;
            mVarB.f976I = mVarB.f996c;
            mVarB.f977J = mVarB.f998d;
            mVarB.f978K = mVarB.f1003g;
            mVarB.f979L = mVarB.f1004h;
            mVarB.f980M = mVarB.f1008l;
            mVarB.f996c = byteBuffer.getLong();
            mVarB.f998d = byteBuffer.getInt();
            mVarB.f999e = byteBuffer.getInt();
            mVarB.f1001f = byteBuffer.getInt();
            mVarB.f1003g = byteBuffer.getInt();
            mVarB.f1004h = byteBuffer.getInt();
            mVarB.f1005i = byteBuffer.getInt();
            mVarB.f1006j = byteBuffer.getInt();
            mVarB.f1007k = byteBuffer.getInt();
            byteBuffer.getInt();
            mVarB.f1008l = byteBuffer.getFloat();
            mVarB.m = byteBuffer.getFloat();
            mVarB.f1009n = byteBuffer.getFloat();
            mVarB.E = byteBuffer.getInt();
            mVarB.f1010o = n.c(byteBuffer, strArr);
            mVarB.f1011p = n.c(byteBuffer, strArr);
            mVarB.f1012q = m.d(byteBuffer, byteBufferArr);
            mVarB.f1013r = n.c(byteBuffer, strArr);
            mVarB.f1014s = m.d(byteBuffer, byteBufferArr);
            mVarB.f1015t = n.c(byteBuffer, strArr);
            mVarB.f1016u = m.d(byteBuffer, byteBufferArr);
            mVarB.v = n.c(byteBuffer, strArr);
            mVarB.f1017w = m.d(byteBuffer, byteBufferArr);
            mVarB.f1018x = n.c(byteBuffer, strArr);
            mVarB.f1019y = m.d(byteBuffer, byteBufferArr);
            mVarB.f1020z = n.c(byteBuffer, strArr);
            mVarB.f970A = n.c(byteBuffer, strArr);
            mVarB.B = n.c(byteBuffer, strArr);
            mVarB.f971C = n.c(byteBuffer, strArr);
            mVarB.f972D = n.c(byteBuffer, strArr);
            mVarB.f973F = byteBuffer.getInt();
            byteBuffer.getInt();
            mVarB.f983P = byteBuffer.getFloat();
            mVarB.f984Q = byteBuffer.getFloat();
            mVarB.f985R = byteBuffer.getFloat();
            mVarB.f986S = byteBuffer.getFloat();
            float[] fArr = mVarB.f987T;
            if (fArr == null) {
                fArr = new float[16];
            }
            for (int i5 = 0; i5 < 16; i5++) {
                fArr[i5] = byteBuffer.getFloat();
            }
            mVarB.f987T = fArr;
            float[] fArr2 = mVarB.U;
            if (fArr2 == null) {
                fArr2 = new float[16];
            }
            for (int i6 = 0; i6 < 16; i6++) {
                fArr2[i6] = byteBuffer.getFloat();
            }
            mVarB.U = fArr2;
            mVarB.f995b0 = true;
            mVarB.d0 = true;
            int i7 = byteBuffer.getInt();
            ArrayList arrayList4 = mVarB.f989W;
            arrayList4.clear();
            for (int i8 = 0; i8 < i7; i8++) {
                m mVarB2 = nVar2.b(byteBuffer.getInt());
                mVarB2.f988V = mVarB;
                arrayList4.add(mVarB2);
            }
            int i9 = byteBuffer.getInt();
            arrayList3.clear();
            for (int i10 = 0; i10 < i9; i10++) {
                m mVarB3 = nVar2.b(byteBuffer.getInt());
                mVarB3.f988V = mVarB;
                arrayList3.add(mVarB3);
            }
            int i11 = byteBuffer.getInt();
            if (i11 == 0) {
                mVarB.f990Y = null;
            } else {
                ArrayList arrayList5 = mVarB.f990Y;
                if (arrayList5 == null) {
                    mVarB.f990Y = new ArrayList(i11);
                } else {
                    arrayList5.clear();
                }
                for (int i12 = 0; i12 < i11; i12++) {
                    j jVarA = nVar2.a(byteBuffer.getInt());
                    int i13 = jVarA.f962c;
                    if (i13 == 1) {
                        mVarB.f991Z = jVarA;
                    } else if (i13 == 2) {
                        mVarB.f993a0 = jVarA;
                    } else {
                        mVarB.f990Y.add(jVarA);
                    }
                    mVarB.f990Y.add(jVarA);
                }
            }
            if (!mVarB.h(14)) {
                if (mVarB.h(6)) {
                    nVar.m = mVarB;
                }
                if (mVarB.f975H) {
                    arrayList2.add(mVarB);
                }
                int i14 = mVarB.f1005i;
                if (i14 != -1 && !hVar.h(i14)) {
                    hVar.r(mVarB.f1005i);
                }
            }
        }
        HashSet hashSet = new HashSet();
        m mVar3 = (m) map.get(0);
        ArrayList arrayList6 = new ArrayList();
        if (mVar3 != null) {
            float[] fArr3 = new float[16];
            Matrix.setIdentityM(fArr3, 0);
            mVar3.l(fArr3, hashSet, false, false);
            mVar3.a(arrayList6);
        }
        int size = arrayList6.size();
        m mVar4 = null;
        int i15 = 0;
        while (i15 < size) {
            Object obj = arrayList6.get(i15);
            i15++;
            m mVar5 = (m) obj;
            if (!arrayList.contains(Integer.valueOf(mVar5.f994b))) {
                mVar4 = mVar5;
            }
        }
        if (mVar4 == null && !arrayList6.isEmpty()) {
            mVar4 = (m) arrayList6.get(arrayList6.size() - 1);
        }
        int i16 = 28;
        if (mVar4 != null && (mVar4.f994b != nVar.f1038q || arrayList6.size() != arrayList.size())) {
            nVar.f1038q = mVar4.f994b;
            String strC = mVar4.c();
            if (strC == null) {
                strC = " ";
            }
            if (Build.VERSION.SDK_INT >= 28) {
                nVar.f1023a.setAccessibilityPaneTitle(strC);
            } else {
                AccessibilityEvent accessibilityEventD = nVar.d(mVar4.f994b, 32);
                accessibilityEventD.getText().add(strC);
                nVar.h(accessibilityEventD);
            }
        }
        arrayList.clear();
        int size2 = arrayList6.size();
        int i17 = 0;
        while (i17 < size2) {
            Object obj2 = arrayList6.get(i17);
            i17++;
            arrayList.add(Integer.valueOf(((m) obj2).f994b));
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            m mVar6 = (m) ((Map.Entry) it.next()).getValue();
            if (!hashSet.contains(mVar6)) {
                mVar6.f988V = null;
                if (mVar6.f1005i != -1 && (num = nVar.f1032j) != null) {
                    View viewPlatformViewOfNode = nVar.f1026d.platformViewOfNode(num.intValue());
                    hVar.r(mVar6.f1005i);
                    if (viewPlatformViewOfNode == null) {
                        nVar.g(nVar.f1032j.intValue(), 65536);
                        nVar.f1032j = null;
                    }
                }
                int i18 = mVar6.f1005i;
                if (i18 != -1) {
                    hVar.r(i18);
                }
                m mVar7 = nVar.f1031i;
                if (mVar7 == mVar6) {
                    nVar.g(mVar7.f994b, 65536);
                    nVar.f1031i = null;
                }
                if (nVar.m == mVar6) {
                    nVar.m = null;
                }
                if (nVar.f1036o == mVar6) {
                    nVar.f1036o = null;
                }
                it.remove();
            }
        }
        int i19 = 0;
        nVar.i(0, 1);
        int size3 = arrayList2.size();
        int i20 = 0;
        while (i20 < size3) {
            Object obj3 = arrayList2.get(i20);
            i20++;
            m mVar8 = (m) obj3;
            if (!Float.isNaN(mVar8.f1008l) && !Float.isNaN(mVar8.f980M) && mVar8.f980M != mVar8.f1008l) {
                AccessibilityEvent accessibilityEventD2 = nVar.d(mVar8.f994b, 4096);
                float f4 = mVar8.f1008l;
                float f5 = mVar8.m;
                if (Float.isInfinite(f5)) {
                    if (f4 > 70000.0f) {
                        f4 = 70000.0f;
                    }
                    f5 = 100000.0f;
                }
                if (Float.isInfinite(mVar8.f1009n)) {
                    f2 = f5 + 100000.0f;
                    if (f4 < -70000.0f) {
                        f4 = -70000.0f;
                    }
                    f3 = f4 + 100000.0f;
                } else {
                    float f6 = mVar8.f1009n;
                    f2 = f5 - f6;
                    f3 = f4 - f6;
                }
                int i21 = mVar8.f977J;
                if ((i21 & 16) != 0 || (i21 & 32) != 0) {
                    accessibilityEventD2.setScrollY((int) f3);
                    accessibilityEventD2.setMaxScrollY((int) f2);
                } else if ((i21 & 4) != 0 || (i21 & 8) != 0) {
                    accessibilityEventD2.setScrollX((int) f3);
                    accessibilityEventD2.setMaxScrollX((int) f2);
                }
                int i22 = mVar8.f1006j;
                if (i22 > 0) {
                    accessibilityEventD2.setItemCount(i22);
                    accessibilityEventD2.setFromIndex(mVar8.f1007k);
                    ArrayList arrayList7 = mVar8.X;
                    int size4 = arrayList7.size();
                    int i23 = i19;
                    int i24 = i23;
                    while (i24 < size4) {
                        Object obj4 = arrayList7.get(i24);
                        i24++;
                        if (!((m) obj4).h(14)) {
                            i23++;
                        }
                    }
                    accessibilityEventD2.setToIndex((mVar8.f1007k + i23) - 1);
                }
                nVar.h(accessibilityEventD2);
            }
            if (mVar8.h(i2) && (((str = mVar8.f1011p) != null || mVar8.f982O != null) && (str == null || !str.equals(mVar8.f982O)))) {
                nVar.i(mVar8.f994b, 1);
            }
            if (Build.VERSION.SDK_INT >= 36) {
                if (mVar8.f975H && (mVar8.h(2) != mVar8.f(2) || mVar8.h(26) != mVar8.f(26) || mVar8.h(18) != mVar8.f(18))) {
                    nVar.i(mVar8.f994b, 8192);
                }
                if (mVar8.f975H && (mVar8.h(i16) != mVar8.f(i16) || mVar8.h(27) != mVar8.f(27))) {
                    nVar.i(mVar8.f994b, 16384);
                }
            }
            m mVar9 = nVar.f1031i;
            if (mVar9 != null && mVar9.f994b == mVar8.f994b && !mVar8.f(3) && mVar8.h(3)) {
                AccessibilityEvent accessibilityEventD3 = nVar.d(mVar8.f994b, 4);
                accessibilityEventD3.getText().add(mVar8.f1011p);
                nVar.h(accessibilityEventD3);
            }
            m mVar10 = nVar.m;
            if (mVar10 != null && (i3 = mVar10.f994b) == (i4 = mVar8.f994b) && ((mVar2 = nVar.f1035n) == null || mVar2.f994b != i3)) {
                nVar.f1035n = mVar10;
                nVar.h(nVar.d(i4, 8));
            } else if (mVar10 == null) {
                nVar.f1035n = null;
            }
            m mVar11 = nVar.m;
            if (mVar11 != null && mVar11.f994b == mVar8.f994b && mVar8.f(5) && mVar8.h(5) && ((mVar = nVar.f1031i) == null || mVar.f994b == nVar.m.f994b)) {
                String str2 = mVar8.f981N;
                if (str2 == null) {
                    str2 = "";
                }
                String str3 = mVar8.f1013r;
                String str4 = str3 != null ? str3 : "";
                AccessibilityEvent accessibilityEventD4 = nVar.d(mVar8.f994b, i2);
                accessibilityEventD4.setBeforeText(str2);
                accessibilityEventD4.getText().add(str4);
                int i25 = 0;
                while (i25 < str2.length() && i25 < str4.length() && str2.charAt(i25) == str4.charAt(i25)) {
                    i25++;
                }
                if (i25 < str2.length() || i25 < str4.length()) {
                    accessibilityEventD4.setFromIndex(i25);
                    int length = str2.length() - 1;
                    int length2 = str4.length() - 1;
                    while (length >= i25 && length2 >= i25 && str2.charAt(length) == str4.charAt(length2)) {
                        length--;
                        length2--;
                    }
                    accessibilityEventD4.setRemovedCount((length - i25) + 1);
                    accessibilityEventD4.setAddedCount((length2 - i25) + 1);
                } else {
                    accessibilityEventD4 = null;
                }
                if (accessibilityEventD4 != null) {
                    nVar.h(accessibilityEventD4);
                }
                if (mVar8.f978K != mVar8.f1003g || mVar8.f979L != mVar8.f1004h) {
                    AccessibilityEvent accessibilityEventD5 = nVar.d(mVar8.f994b, 8192);
                    accessibilityEventD5.getText().add(str4);
                    accessibilityEventD5.setFromIndex(mVar8.f1003g);
                    accessibilityEventD5.setToIndex(mVar8.f1004h);
                    accessibilityEventD5.setItemCount(str4.length());
                    nVar.h(accessibilityEventD5);
                }
            }
            i16 = 28;
            i19 = 0;
            i2 = 16;
        }
    }
}
