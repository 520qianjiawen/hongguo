package e;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Set {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f691d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ H.e f692e;

    public /* synthetic */ d(H.e eVar, int i2) {
        this.f691d = i2;
        this.f692e = eVar;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        switch (this.f691d) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        switch (this.f691d) {
            case 0:
                H.e eVar = this.f692e;
                int i2 = ((a) eVar.f177g).f682f;
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    ((a) eVar.f177g).put(entry.getKey(), entry.getValue());
                }
                return i2 != ((a) eVar.f177g).f682f;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        switch (this.f691d) {
            case 0:
                ((a) this.f692e.f177g).clear();
                break;
            default:
                ((a) this.f692e.f177g).clear();
                break;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        switch (this.f691d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                H.e eVar = this.f692e;
                int iE = ((a) eVar.f177g).e(key);
                if (iE < 0) {
                    return false;
                }
                Object objB = eVar.b(iE, 1);
                Object value = entry.getValue();
                return objB == value || (objB != null && objB.equals(value));
            default:
                return ((a) this.f692e.f177g).e(obj) >= 0;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        switch (this.f691d) {
            case 0:
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        return false;
                    }
                }
                return true;
            default:
                a aVar = (a) this.f692e.f177g;
                Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!aVar.containsKey(it2.next())) {
                        return false;
                    }
                }
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        switch (this.f691d) {
            case 0:
                break;
        }
        return H.e.f(this, obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        switch (this.f691d) {
            case 0:
                H.e eVar = this.f692e;
                int iHashCode = 0;
                for (int i2 = ((a) eVar.f177g).f682f - 1; i2 >= 0; i2--) {
                    Object objB = eVar.b(i2, 0);
                    Object objB2 = eVar.b(i2, 1);
                    iHashCode += (objB == null ? 0 : objB.hashCode()) ^ (objB2 == null ? 0 : objB2.hashCode());
                }
                return iHashCode;
            default:
                H.e eVar2 = this.f692e;
                int iHashCode2 = 0;
                for (int i3 = ((a) eVar2.f177g).f682f - 1; i3 >= 0; i3--) {
                    Object objB3 = eVar2.b(i3, 0);
                    iHashCode2 += objB3 == null ? 0 : objB3.hashCode();
                }
                return iHashCode2;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        switch (this.f691d) {
            case 0:
                return ((a) this.f692e.f177g).f682f == 0;
            default:
                return ((a) this.f692e.f177g).f682f == 0;
        }
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        switch (this.f691d) {
            case 0:
                return new e(this.f692e);
            default:
                return new c(this.f692e, 0);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        switch (this.f691d) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                H.e eVar = this.f692e;
                int iE = ((a) eVar.f177g).e(obj);
                if (iE < 0) {
                    return false;
                }
                eVar.c(iE);
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        switch (this.f691d) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                a aVar = (a) this.f692e.f177g;
                int size = aVar.size();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    aVar.remove(it.next());
                }
                return size != aVar.size();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        switch (this.f691d) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                a aVar = (a) this.f692e.f177g;
                int size = aVar.size();
                Iterator it = aVar.keySet().iterator();
                while (it.hasNext()) {
                    if (!collection.contains(it.next())) {
                        it.remove();
                    }
                }
                return size != aVar.size();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        switch (this.f691d) {
            case 0:
                break;
        }
        return ((a) this.f692e.f177g).f682f;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        switch (this.f691d) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                H.e eVar = this.f692e;
                int i2 = ((a) eVar.f177g).f682f;
                Object[] objArr = new Object[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    objArr[i3] = eVar.b(i3, 0);
                }
                return objArr;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        switch (this.f691d) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                return this.f692e.k(objArr, 0);
        }
    }
}
