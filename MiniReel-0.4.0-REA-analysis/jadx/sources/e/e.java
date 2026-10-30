package e;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Iterator, Map.Entry {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f693d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ H.e f696g;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f695f = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f694e = -1;

    public e(H.e eVar) {
        this.f696g = eVar;
        this.f693d = ((a) eVar.f177g).f682f - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.f695f) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i2 = this.f694e;
        H.e eVar = this.f696g;
        Object objB = eVar.b(i2, 0);
        if (key != objB && (key == null || !key.equals(objB))) {
            return false;
        }
        Object value = entry.getValue();
        Object objB2 = eVar.b(this.f694e, 1);
        return value == objB2 || (value != null && value.equals(objB2));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (!this.f695f) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.f696g.b(this.f694e, 0);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (!this.f695f) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.f696g.b(this.f694e, 1);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f694e < this.f693d;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.f695f) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i2 = this.f694e;
        H.e eVar = this.f696g;
        Object objB = eVar.b(i2, 0);
        Object objB2 = eVar.b(this.f694e, 1);
        return (objB == null ? 0 : objB.hashCode()) ^ (objB2 != null ? objB2.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f694e++;
        this.f695f = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f695f) {
            throw new IllegalStateException();
        }
        this.f696g.c(this.f694e);
        this.f694e--;
        this.f693d--;
        this.f695f = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (!this.f695f) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i2 = (this.f694e << 1) + 1;
        Object[] objArr = ((a) this.f696g.f177g).f681e;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
