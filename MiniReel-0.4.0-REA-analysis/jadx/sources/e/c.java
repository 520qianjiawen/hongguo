package e;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f686d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f687e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f688f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f689g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ H.e f690h;

    public c(H.e eVar, int i2) {
        this.f690h = eVar;
        this.f686d = i2;
        this.f687e = ((a) eVar.f177g).f682f;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f688f < this.f687e;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object objB = this.f690h.b(this.f688f, this.f686d);
        this.f688f++;
        this.f689g = true;
        return objB;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f689g) {
            throw new IllegalStateException();
        }
        int i2 = this.f688f - 1;
        this.f688f = i2;
        this.f687e--;
        this.f689g = false;
        this.f690h.c(i2);
    }
}
