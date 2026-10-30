package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Boolean f601a;

    public a(Boolean bool) {
        this.f601a = bool;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return p000a.a.m(p000a.a.A(this.f601a), p000a.a.A(((a) obj).f601a));
    }

    public final int hashCode() {
        return p000a.a.A(this.f601a).hashCode();
    }

    public final String toString() {
        return "IsEnabledMessage(enabled=" + this.f601a + ")";
    }
}
