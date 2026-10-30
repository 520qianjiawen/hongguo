package p0;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object[] f1202d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1203e;

    public a(Object[] objArr) {
        this.f1202d = objArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f1203e < this.f1202d.length;
    }

    @Override // java.util.Iterator
    public final Object next() {
        try {
            Object[] objArr = this.f1202d;
            int i2 = this.f1203e;
            this.f1203e = i2 + 1;
            return objArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f1203e--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
