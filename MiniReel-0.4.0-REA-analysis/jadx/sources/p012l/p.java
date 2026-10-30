package p012l;

import android.os.Build;
import android.view.View;
import java.util.Objects;
import p008h.a;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f1100b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f1101a;

    static {
        i gVar;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            gVar = new h();
        } else {
            gVar = i2 >= 29 ? new g() : new f();
        }
        gVar.b().f1102a.a().f1102a.b().f1102a.c();
    }

    public p(r rVar) {
        this.f1101a = rVar;
    }

    public r a() {
        return this.f1101a;
    }

    public r b() {
        return this.f1101a;
    }

    public r c() {
        return this.f1101a;
    }

    public b e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return l() == pVar.l() && k() == pVar.k() && Objects.equals(i(), pVar.i()) && Objects.equals(g(), pVar.g()) && Objects.equals(e(), pVar.e());
    }

    public a f() {
        return i();
    }

    public a g() {
        return a.f736e;
    }

    public a h() {
        return i();
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(l()), Boolean.valueOf(k()), i(), g(), e());
    }

    public a i() {
        return a.f736e;
    }

    public a j() {
        return i();
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return false;
    }

    public boolean m(int i2) {
        return true;
    }

    public void d(View view) {
    }

    public void n(a[] aVarArr) {
    }

    public void o(r rVar) {
    }

    public void p(a aVar) {
    }
}
