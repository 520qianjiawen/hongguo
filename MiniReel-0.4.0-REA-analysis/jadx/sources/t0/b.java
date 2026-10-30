package t0;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1263d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1264e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1265f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1266g;

    public b(int i2, int i3, int i4) {
        this.f1263d = i4;
        this.f1264e = i3;
        boolean z2 = false;
        if (i4 <= 0 ? i2 >= i3 : i2 <= i3) {
            z2 = true;
        }
        this.f1265f = z2;
        this.f1266g = z2 ? i2 : i3;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f1265f;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return Integer.valueOf(nextInt());
    }

    public final int nextInt() {
        int i2 = this.f1266g;
        if (i2 != this.f1264e) {
            this.f1266g = this.f1263d + i2;
            return i2;
        }
        if (!this.f1265f) {
            throw new NoSuchElementException();
        }
        this.f1265f = false;
        return i2;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
