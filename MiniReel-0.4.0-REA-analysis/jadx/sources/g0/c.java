package g0;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractList implements List {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Object[] f728g = new Object[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f729d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f730e = f728g;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f731f;

    public final void a(int i2, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.f730e.length;
        while (i2 < length && it.hasNext()) {
            this.f730e[i2] = it.next();
            i2++;
        }
        int i3 = this.f729d;
        for (int i4 = 0; i4 < i3 && it.hasNext(); i4++) {
            this.f730e[i4] = it.next();
        }
        this.f731f = collection.size() + this.f731f;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i2, Object obj) {
        int length;
        int i3 = this.f731f;
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
        }
        if (i2 == i3) {
            addLast(obj);
            return;
        }
        if (i2 == 0) {
            addFirst(obj);
            return;
        }
        g();
        b(this.f731f + 1);
        int iF = f(this.f729d + i2);
        int i4 = this.f731f;
        if (i2 < ((i4 + 1) >> 1)) {
            if (iF == 0) {
                Object[] objArr = this.f730e;
                p0.h.e(objArr, "<this>");
                iF = objArr.length;
            }
            int i5 = iF - 1;
            int i6 = this.f729d;
            if (i6 == 0) {
                Object[] objArr2 = this.f730e;
                p0.h.e(objArr2, "<this>");
                length = objArr2.length - 1;
            } else {
                length = i6 - 1;
            }
            int i7 = this.f729d;
            if (i5 >= i7) {
                Object[] objArr3 = this.f730e;
                objArr3[length] = objArr3[i7];
                d.T(objArr3, objArr3, i7, i7 + 1, i5 + 1);
            } else {
                Object[] objArr4 = this.f730e;
                d.T(objArr4, objArr4, i7 - 1, i7, objArr4.length);
                Object[] objArr5 = this.f730e;
                objArr5[objArr5.length - 1] = objArr5[0];
                d.T(objArr5, objArr5, 0, 1, i5 + 1);
            }
            this.f730e[i5] = obj;
            this.f729d = length;
        } else {
            int iF2 = f(this.f729d + i4);
            if (iF < iF2) {
                Object[] objArr6 = this.f730e;
                d.T(objArr6, objArr6, iF + 1, iF, iF2);
            } else {
                Object[] objArr7 = this.f730e;
                d.T(objArr7, objArr7, 1, 0, iF2);
                Object[] objArr8 = this.f730e;
                objArr8[0] = objArr8[objArr8.length - 1];
                d.T(objArr8, objArr8, iF + 1, iF, objArr8.length - 1);
            }
            this.f730e[iF] = obj;
        }
        this.f731f++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i2, Collection collection) {
        p0.h.e(collection, "elements");
        int i3 = this.f731f;
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
        }
        if (collection.isEmpty()) {
            return false;
        }
        if (i2 == this.f731f) {
            return addAll(collection);
        }
        g();
        b(collection.size() + this.f731f);
        int iF = f(this.f729d + this.f731f);
        int iF2 = f(this.f729d + i2);
        int size = collection.size();
        if (i2 >= ((this.f731f + 1) >> 1)) {
            int i4 = iF2 + size;
            if (iF2 < iF) {
                int i5 = size + iF;
                Object[] objArr = this.f730e;
                if (i5 <= objArr.length) {
                    d.T(objArr, objArr, i4, iF2, iF);
                } else if (i4 >= objArr.length) {
                    d.T(objArr, objArr, i4 - objArr.length, iF2, iF);
                } else {
                    int length = iF - (i5 - objArr.length);
                    d.T(objArr, objArr, 0, length, iF);
                    Object[] objArr2 = this.f730e;
                    d.T(objArr2, objArr2, i4, iF2, length);
                }
            } else {
                Object[] objArr3 = this.f730e;
                d.T(objArr3, objArr3, size, 0, iF);
                Object[] objArr4 = this.f730e;
                if (i4 >= objArr4.length) {
                    d.T(objArr4, objArr4, i4 - objArr4.length, iF2, objArr4.length);
                } else {
                    d.T(objArr4, objArr4, 0, objArr4.length - size, objArr4.length);
                    Object[] objArr5 = this.f730e;
                    d.T(objArr5, objArr5, i4, iF2, objArr5.length - size);
                }
            }
            a(iF2, collection);
            return true;
        }
        int i6 = this.f729d;
        int length2 = i6 - size;
        if (iF2 < i6) {
            Object[] objArr6 = this.f730e;
            d.T(objArr6, objArr6, length2, i6, objArr6.length);
            if (size >= iF2) {
                Object[] objArr7 = this.f730e;
                d.T(objArr7, objArr7, objArr7.length - size, 0, iF2);
            } else {
                Object[] objArr8 = this.f730e;
                d.T(objArr8, objArr8, objArr8.length - size, 0, size);
                Object[] objArr9 = this.f730e;
                d.T(objArr9, objArr9, 0, size, iF2);
            }
        } else if (length2 >= 0) {
            Object[] objArr10 = this.f730e;
            d.T(objArr10, objArr10, length2, i6, iF2);
        } else {
            Object[] objArr11 = this.f730e;
            length2 += objArr11.length;
            int i7 = iF2 - i6;
            int length3 = objArr11.length - length2;
            if (length3 >= i7) {
                d.T(objArr11, objArr11, length2, i6, iF2);
            } else {
                d.T(objArr11, objArr11, length2, i6, i6 + length3);
                Object[] objArr12 = this.f730e;
                d.T(objArr12, objArr12, 0, this.f729d + length3, iF2);
            }
        }
        this.f729d = length2;
        a(d(iF2 - size), collection);
        return true;
    }

    public final void addFirst(Object obj) {
        g();
        b(this.f731f + 1);
        int length = this.f729d;
        if (length == 0) {
            Object[] objArr = this.f730e;
            p0.h.e(objArr, "<this>");
            length = objArr.length;
        }
        int i2 = length - 1;
        this.f729d = i2;
        this.f730e[i2] = obj;
        this.f731f++;
    }

    public final void addLast(Object obj) {
        g();
        b(this.f731f + 1);
        this.f730e[f(this.f729d + this.f731f)] = obj;
        this.f731f++;
    }

    public final void b(int i2) {
        if (i2 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f730e;
        if (i2 <= objArr.length) {
            return;
        }
        if (objArr == f728g) {
            if (i2 < 10) {
                i2 = 10;
            }
            this.f730e = new Object[i2];
            return;
        }
        int length = objArr.length;
        int i3 = length + (length >> 1);
        if (i3 - i2 < 0) {
            i3 = i2;
        }
        if (i3 - 2147483639 > 0) {
            i3 = i2 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i3];
        d.T(objArr, objArr2, 0, this.f729d, objArr.length);
        Object[] objArr3 = this.f730e;
        int length2 = objArr3.length;
        int i4 = this.f729d;
        d.T(objArr3, objArr2, length2 - i4, 0, i4);
        this.f729d = 0;
        this.f730e = objArr2;
    }

    public final int c(int i2) {
        Object[] objArr = this.f730e;
        p0.h.e(objArr, "<this>");
        if (i2 == objArr.length - 1) {
            return 0;
        }
        return i2 + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            g();
            e(this.f729d, f(this.f729d + this.f731f));
        }
        this.f729d = 0;
        this.f731f = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int d(int i2) {
        return i2 < 0 ? i2 + this.f730e.length : i2;
    }

    public final void e(int i2, int i3) {
        if (i2 < i3) {
            Object[] objArr = this.f730e;
            p0.h.e(objArr, "<this>");
            Arrays.fill(objArr, i2, i3, (Object) null);
        } else {
            Object[] objArr2 = this.f730e;
            Arrays.fill(objArr2, i2, objArr2.length, (Object) null);
            Object[] objArr3 = this.f730e;
            p0.h.e(objArr3, "<this>");
            Arrays.fill(objArr3, 0, i3, (Object) null);
        }
    }

    public final int f(int i2) {
        Object[] objArr = this.f730e;
        return i2 >= objArr.length ? i2 - objArr.length : i2;
    }

    public final void g() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i2) {
        int i3 = this.f731f;
        if (i2 >= 0 && i2 < i3) {
            return this.f730e[f(this.f729d + i2)];
        }
        throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
    }

    public final Object h(int i2) {
        int i3 = this.f731f;
        if (i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
        }
        if (i2 == i3 - 1) {
            return removeLast();
        }
        if (i2 == 0) {
            return removeFirst();
        }
        g();
        int iF = f(this.f729d + i2);
        Object[] objArr = this.f730e;
        Object obj = objArr[iF];
        int i4 = this.f731f;
        if (i2 < (i4 >> 1)) {
            int i5 = this.f729d;
            if (iF >= i5) {
                d.T(objArr, objArr, i5 + 1, i5, iF);
            } else {
                d.T(objArr, objArr, 1, 0, iF);
                Object[] objArr2 = this.f730e;
                objArr2[0] = objArr2[objArr2.length - 1];
                int i6 = this.f729d;
                d.T(objArr2, objArr2, i6 + 1, i6, objArr2.length - 1);
            }
            Object[] objArr3 = this.f730e;
            int i7 = this.f729d;
            objArr3[i7] = null;
            this.f729d = c(i7);
        } else {
            int iF2 = f((i4 - 1) + this.f729d);
            if (iF <= iF2) {
                Object[] objArr4 = this.f730e;
                d.T(objArr4, objArr4, iF, iF + 1, iF2 + 1);
            } else {
                Object[] objArr5 = this.f730e;
                d.T(objArr5, objArr5, iF, iF + 1, objArr5.length);
                Object[] objArr6 = this.f730e;
                objArr6[objArr6.length - 1] = objArr6[0];
                d.T(objArr6, objArr6, 0, 1, iF2 + 1);
            }
            this.f730e[iF2] = null;
        }
        this.f731f--;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i2;
        int iF = f(this.f729d + this.f731f);
        int length = this.f729d;
        if (length < iF) {
            while (length < iF) {
                if (p0.h.a(obj, this.f730e[length])) {
                    i2 = this.f729d;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iF) {
            return -1;
        }
        int length2 = this.f730e.length;
        while (length < length2) {
            if (p0.h.a(obj, this.f730e[length])) {
                i2 = this.f729d;
            } else {
                length++;
            }
        }
        for (int i3 = 0; i3 < iF; i3++) {
            if (p0.h.a(obj, this.f730e[i3])) {
                length = i3 + this.f730e.length;
                i2 = this.f729d;
            }
        }
        return -1;
        return length - i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.f731f == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i2;
        int iF = f(this.f729d + this.f731f);
        int i3 = this.f729d;
        if (i3 < iF) {
            length = iF - 1;
            if (i3 <= length) {
                while (!p0.h.a(obj, this.f730e[length])) {
                    if (length != i3) {
                        length--;
                    }
                }
                i2 = this.f729d;
                return length - i2;
            }
            return -1;
        }
        if (i3 > iF) {
            for (int i4 = iF - 1; -1 < i4; i4--) {
                if (p0.h.a(obj, this.f730e[i4])) {
                    length = i4 + this.f730e.length;
                    i2 = this.f729d;
                    return length - i2;
                }
            }
            Object[] objArr = this.f730e;
            p0.h.e(objArr, "<this>");
            length = objArr.length - 1;
            int i5 = this.f729d;
            if (i5 <= length) {
                while (!p0.h.a(obj, this.f730e[length])) {
                    if (length != i5) {
                        length--;
                    }
                }
                i2 = this.f729d;
                return length - i2;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ Object remove(int i2) {
        return h(i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int iF;
        p0.h.e(collection, "elements");
        boolean z2 = false;
        z2 = false;
        z2 = false;
        if (!isEmpty() && this.f730e.length != 0) {
            int iF2 = f(this.f729d + this.f731f);
            int i2 = this.f729d;
            if (i2 < iF2) {
                iF = i2;
                while (i2 < iF2) {
                    Object obj = this.f730e[i2];
                    if (collection.contains(obj)) {
                        z2 = true;
                    } else {
                        this.f730e[iF] = obj;
                        iF++;
                    }
                    i2++;
                }
                Object[] objArr = this.f730e;
                p0.h.e(objArr, "<this>");
                Arrays.fill(objArr, iF, iF2, (Object) null);
            } else {
                int length = this.f730e.length;
                boolean z3 = false;
                int i3 = i2;
                while (i2 < length) {
                    Object[] objArr2 = this.f730e;
                    Object obj2 = objArr2[i2];
                    objArr2[i2] = null;
                    if (collection.contains(obj2)) {
                        z3 = true;
                    } else {
                        this.f730e[i3] = obj2;
                        i3++;
                    }
                    i2++;
                }
                iF = f(i3);
                for (int i4 = 0; i4 < iF2; i4++) {
                    Object[] objArr3 = this.f730e;
                    Object obj3 = objArr3[i4];
                    objArr3[i4] = null;
                    if (collection.contains(obj3)) {
                        z3 = true;
                    } else {
                        this.f730e[iF] = obj3;
                        iF = c(iF);
                    }
                }
                z2 = z3;
            }
            if (z2) {
                g();
                this.f731f = d(iF - this.f729d);
            }
        }
        return z2;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        g();
        Object[] objArr = this.f730e;
        int i2 = this.f729d;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.f729d = c(i2);
        this.f731f--;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        g();
        int iF = f((this.f731f - 1) + this.f729d);
        Object[] objArr = this.f730e;
        Object obj = objArr[iF];
        objArr[iF] = null;
        this.f731f--;
        return obj;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i2, int i3) {
        int i4 = this.f731f;
        if (i2 < 0 || i3 > i4) {
            throw new IndexOutOfBoundsException("fromIndex: " + i2 + ", toIndex: " + i3 + ", size: " + i4);
        }
        if (i2 > i3) {
            throw new IllegalArgumentException("fromIndex: " + i2 + " > toIndex: " + i3);
        }
        int i5 = i3 - i2;
        if (i5 == 0) {
            return;
        }
        if (i5 == this.f731f) {
            clear();
            return;
        }
        if (i5 == 1) {
            h(i2);
            return;
        }
        g();
        if (i2 < this.f731f - i3) {
            int iF = f(this.f729d + (i2 - 1));
            int iF2 = f(this.f729d + (i3 - 1));
            while (i2 > 0) {
                int i6 = iF + 1;
                int iMin = Math.min(i2, Math.min(i6, iF2 + 1));
                Object[] objArr = this.f730e;
                int i7 = iF2 - iMin;
                int i8 = iF - iMin;
                d.T(objArr, objArr, i7 + 1, i8 + 1, i6);
                iF = d(i8);
                iF2 = d(i7);
                i2 -= iMin;
            }
            int iF3 = f(this.f729d + i5);
            e(this.f729d, iF3);
            this.f729d = iF3;
        } else {
            int iF4 = f(this.f729d + i3);
            int iF5 = f(this.f729d + i2);
            int i9 = this.f731f;
            while (true) {
                i9 -= i3;
                if (i9 <= 0) {
                    break;
                }
                Object[] objArr2 = this.f730e;
                i3 = Math.min(i9, Math.min(objArr2.length - iF4, objArr2.length - iF5));
                Object[] objArr3 = this.f730e;
                int i10 = iF4 + i3;
                d.T(objArr3, objArr3, iF5, iF4, i10);
                iF4 = f(i10);
                iF5 = f(iF5 + i3);
            }
            int iF6 = f(this.f729d + this.f731f);
            e(d(iF6 - i5), iF6);
        }
        this.f731f -= i5;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iF;
        p0.h.e(collection, "elements");
        boolean z2 = false;
        z2 = false;
        z2 = false;
        if (!isEmpty() && this.f730e.length != 0) {
            int iF2 = f(this.f729d + this.f731f);
            int i2 = this.f729d;
            if (i2 < iF2) {
                iF = i2;
                while (i2 < iF2) {
                    Object obj = this.f730e[i2];
                    if (collection.contains(obj)) {
                        this.f730e[iF] = obj;
                        iF++;
                    } else {
                        z2 = true;
                    }
                    i2++;
                }
                Object[] objArr = this.f730e;
                p0.h.e(objArr, "<this>");
                Arrays.fill(objArr, iF, iF2, (Object) null);
            } else {
                int length = this.f730e.length;
                boolean z3 = false;
                int i3 = i2;
                while (i2 < length) {
                    Object[] objArr2 = this.f730e;
                    Object obj2 = objArr2[i2];
                    objArr2[i2] = null;
                    if (collection.contains(obj2)) {
                        this.f730e[i3] = obj2;
                        i3++;
                    } else {
                        z3 = true;
                    }
                    i2++;
                }
                iF = f(i3);
                for (int i4 = 0; i4 < iF2; i4++) {
                    Object[] objArr3 = this.f730e;
                    Object obj3 = objArr3[i4];
                    objArr3[i4] = null;
                    if (collection.contains(obj3)) {
                        this.f730e[iF] = obj3;
                        iF = c(iF);
                    } else {
                        z3 = true;
                    }
                }
                z2 = z3;
            }
            if (z2) {
                g();
                this.f731f = d(iF - this.f729d);
            }
        }
        return z2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i2, Object obj) {
        int i3 = this.f731f;
        if (i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
        }
        int iF = f(this.f729d + i2);
        Object[] objArr = this.f730e;
        Object obj2 = objArr[iF];
        objArr[iF] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f731f;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[this.f731f]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        h(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        p0.h.e(objArr, "array");
        int length = objArr.length;
        int i2 = this.f731f;
        if (length < i2) {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), i2);
            p0.h.c(objNewInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            objArr = (Object[]) objNewInstance;
        }
        int iF = f(this.f729d + this.f731f);
        int i3 = this.f729d;
        if (i3 < iF) {
            d.T(this.f730e, objArr, 0, i3, iF);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.f730e;
            d.T(objArr2, objArr, 0, this.f729d, objArr2.length);
            Object[] objArr3 = this.f730e;
            d.T(objArr3, objArr, objArr3.length - this.f729d, 0, iF);
        }
        int i4 = this.f731f;
        if (i4 < objArr.length) {
            objArr[i4] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        p0.h.e(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        g();
        b(collection.size() + this.f731f);
        a(f(this.f729d + this.f731f), collection);
        return true;
    }
}
