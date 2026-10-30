package p019t;

import C0.z;
import android.util.Log;
import g0.b;
import g0.m;
import java.util.ArrayList;
import java.util.List;
import o0.l;
import p0.h;
import p000a.a;
import p014o.c;

/* JADX INFO: loaded from: classes.dex */
public final class f extends a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f1247i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f1248j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f1249k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i f1250l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Code duplicated, block: B:15:0x0053  */
    public f(Object obj, String str, a aVar, int i2) {
        List listA;
        super(28);
        h.e(obj, "value");
        z.g("verificationMode", i2);
        this.f1247i = obj;
        this.f1248j = str;
        this.f1249k = i2;
        String strL = a.l(obj, str);
        h.e(strL, "message");
        i iVar = new i(strL);
        StackTraceElement[] stackTrace = iVar.getStackTrace();
        h.d(stackTrace, "stackTrace");
        int length = stackTrace.length - 2;
        length = length < 0 ? 0 : length;
        if (length < 0) {
            throw new IllegalArgumentException(("Requested element count " + length + " is less than zero.").toString());
        }
        if (length != 0) {
            int length2 = stackTrace.length;
            if (length >= length2) {
                int length3 = stackTrace.length;
                if (length3 != 0) {
                    listA = length3 != 1 ? new ArrayList(new b(stackTrace, false)) : a.A(stackTrace[0]);
                } else {
                    listA = m.f733d;
                }
            } else if (length == 1) {
                listA = a.A(stackTrace[length2 - 1]);
            } else {
                ArrayList arrayList = new ArrayList(length);
                for (int i3 = length2 - length; i3 < length2; i3++) {
                    arrayList.add(stackTrace[i3]);
                }
                listA = arrayList;
            }
        } else {
            listA = m.f733d;
        }
        iVar.setStackTrace((StackTraceElement[]) listA.toArray(new StackTraceElement[0]));
        this.f1250l = iVar;
    }

    @Override // p000a.a
    public final Object i() throws i {
        int iA = c.a(this.f1249k);
        if (iA == 0) {
            throw this.f1250l;
        }
        if (iA != 1) {
            if (iA == 2) {
                return null;
            }
            throw new H.c();
        }
        String strL = a.l(this.f1247i, this.f1248j);
        h.e(strL, "message");
        Log.d("g", strL);
        return null;
    }

    @Override // p000a.a
    public final a K(String str, l lVar) {
        return this;
    }
}
