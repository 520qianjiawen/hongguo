package defpackage;

import p000a.a;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Boolean f659a;

    public b(Boolean bool) {
        this.f659a = bool;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return a.m(a.A(this.f659a), a.A(((b) obj).f659a));
    }

    public final int hashCode() {
        return a.A(this.f659a).hashCode();
    }

    public final String toString() {
        return "ToggleMessage(enable=" + this.f659a + ")";
    }
}
