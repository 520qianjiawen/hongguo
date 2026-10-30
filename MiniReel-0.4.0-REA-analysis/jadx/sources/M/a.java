package M;

import O.B;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f267a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f268b;

    public void a(boolean z2) {
        if (this.f267a) {
            throw new IllegalStateException("The onKeyEventHandledCallback should be called exactly once.");
        }
        this.f267a = true;
        B b2 = (B) this.f268b;
        int i2 = b2.f276b - 1;
        b2.f276b = i2;
        boolean z3 = z2 | b2.f277c;
        b2.f277c = z3;
        if (i2 != 0 || z3) {
            return;
        }
        b2.f278d.e(b2.f275a);
    }
}
