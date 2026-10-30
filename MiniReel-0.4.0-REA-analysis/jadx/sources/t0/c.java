package t0;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a {
    static {
        new c(1, 0, 1);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        if (isEmpty() && ((c) obj).isEmpty()) {
            return true;
        }
        c cVar = (c) obj;
        return this.f1260d == cVar.f1260d && this.f1261e == cVar.f1261e;
    }

    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.f1260d * 31) + this.f1261e;
    }

    public final boolean isEmpty() {
        return this.f1260d > this.f1261e;
    }

    public final String toString() {
        return this.f1260d + ".." + this.f1261e;
    }
}
