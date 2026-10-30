package Y;

import Z.r;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f523c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final G.f f524d;

    public d(Q.b bVar) {
        G.f fVar = new G.f(bVar, "flutter/lifecycle", r.f600b);
        this.f521a = 0;
        this.f522b = 0;
        this.f523c = true;
        this.f524d = fVar;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0029  */
    public final void a(int i2, boolean z2) {
        int i3;
        String str;
        int i4 = this.f521a;
        if (i4 == i2 && z2 == this.f523c) {
            return;
        }
        if (i2 == 0 && i4 == 0) {
            this.f523c = z2;
            return;
        }
        int iA = p014o.c.a(i2);
        if (iA != 0) {
            if (iA == 1) {
                i3 = z2 ? 2 : 3;
            } else if (iA == 2 || iA == 3 || iA == 4) {
                i3 = i2;
            } else {
                i3 = 0;
            }
        } else {
            i3 = i2;
        }
        this.f521a = i2;
        this.f523c = z2;
        if (i3 == this.f522b) {
            return;
        }
        StringBuilder sb = new StringBuilder("AppLifecycleState.");
        if (i3 == 1) {
            str = "DETACHED";
        } else if (i3 == 2) {
            str = "RESUMED";
        } else if (i3 == 3) {
            str = "INACTIVE";
        } else if (i3 == 4) {
            str = "HIDDEN";
        } else {
            if (i3 != 5) {
                throw null;
            }
            str = "PAUSED";
        }
        sb.append(str.toLowerCase(Locale.ROOT));
        this.f524d.g(sb.toString(), null);
        this.f522b = i3;
    }
}
