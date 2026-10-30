package e;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Collection {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ H.e f697d;

    public f(H.e eVar) {
        this.f697d = eVar;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final void clear() {
        ((a) this.f697d.f177g).clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return ((a) this.f697d.f177g).g(obj) >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return ((a) this.f697d.f177g).f682f == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new c(this.f697d, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        H.e eVar = this.f697d;
        int iG = ((a) eVar.f177g).g(obj);
        if (iG < 0) {
            return false;
        }
        eVar.c(iG);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        H.e eVar = this.f697d;
        int i2 = ((a) eVar.f177g).f682f;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < i2) {
            if (collection.contains(eVar.b(i3, 1))) {
                eVar.c(i3);
                i3--;
                i2--;
                z2 = true;
            }
            i3++;
        }
        return z2;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        H.e eVar = this.f697d;
        int i2 = ((a) eVar.f177g).f682f;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < i2) {
            if (!collection.contains(eVar.b(i3, 1))) {
                eVar.c(i3);
                i3--;
                i2--;
                z2 = true;
            }
            i3++;
        }
        return z2;
    }

    @Override // java.util.Collection
    public final int size() {
        return ((a) this.f697d.f177g).f682f;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        H.e eVar = this.f697d;
        int i2 = ((a) eVar.f177g).f682f;
        Object[] objArr = new Object[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = eVar.b(i3, 1);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.f697d.k(objArr, 1);
    }
}
