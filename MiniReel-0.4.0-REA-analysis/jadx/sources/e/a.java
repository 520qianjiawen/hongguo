package e;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Map {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Object[] f676h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static int f677i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static Object[] f678j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static int f679k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f680d = b.f684a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f681e = b.f685b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f682f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public H.e f683g;

    public static int b(int[] iArr, int i2, int i3) {
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            try {
                int i7 = iArr[i6];
                if (i7 < i3) {
                    i5 = i6 + 1;
                } else {
                    if (i7 <= i3) {
                        return i6;
                    }
                    i4 = i6 - 1;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
        return ~i5;
    }

    public static void c(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (a.class) {
                try {
                    if (f679k < 10) {
                        objArr[0] = f678j;
                        objArr[1] = iArr;
                        for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                            objArr[i3] = null;
                        }
                        f678j = objArr;
                        f679k++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (a.class) {
                try {
                    if (f677i < 10) {
                        objArr[0] = f676h;
                        objArr[1] = iArr;
                        for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                            objArr[i4] = null;
                        }
                        f676h = objArr;
                        f677i++;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    public final void a(int i2) {
        if (i2 == 8) {
            synchronized (a.class) {
                try {
                    Object[] objArr = f678j;
                    if (objArr != null) {
                        this.f681e = objArr;
                        f678j = (Object[]) objArr[0];
                        this.f680d = (int[]) objArr[1];
                        objArr[1] = null;
                        objArr[0] = null;
                        f679k--;
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else if (i2 == 4) {
            synchronized (a.class) {
                try {
                    Object[] objArr2 = f676h;
                    if (objArr2 != null) {
                        this.f681e = objArr2;
                        f676h = (Object[]) objArr2[0];
                        this.f680d = (int[]) objArr2[1];
                        objArr2[1] = null;
                        objArr2[0] = null;
                        f677i--;
                        return;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
        this.f680d = new int[i2];
        this.f681e = new Object[i2 << 1];
    }

    @Override // java.util.Map
    public final void clear() {
        int i2 = this.f682f;
        if (i2 > 0) {
            int[] iArr = this.f680d;
            Object[] objArr = this.f681e;
            this.f680d = b.f684a;
            this.f681e = b.f685b;
            this.f682f = 0;
            c(iArr, objArr, i2);
        }
        if (this.f682f > 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    public final int d(int i2, Object obj) {
        int i3 = this.f682f;
        if (i3 == 0) {
            return -1;
        }
        int iB = b(this.f680d, i3, i2);
        if (iB < 0 || obj.equals(this.f681e[iB << 1])) {
            return iB;
        }
        int i4 = iB + 1;
        while (i4 < i3 && this.f680d[i4] == i2) {
            if (obj.equals(this.f681e[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iB - 1; i5 >= 0 && this.f680d[i5] == i2; i5--) {
            if (obj.equals(this.f681e[i5 << 1])) {
                return i5;
            }
        }
        return ~i4;
    }

    public final int e(Object obj) {
        return obj == null ? f() : d(obj.hashCode(), obj);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        if (this.f683g == null) {
            this.f683g = new H.e(this);
        }
        H.e eVar = this.f683g;
        if (((d) eVar.f174d) == null) {
            eVar.f174d = new d(eVar, 0);
        }
        return (d) eVar.f174d;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        int i2;
        int i3;
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof a) {
                a aVar = (a) obj;
                if (this.f682f == aVar.f682f) {
                    for (0; i3 < this.f682f; i3 + 1) {
                        Object[] objArr = this.f681e;
                        int i4 = i3 << 1;
                        Object obj2 = objArr[i4];
                        Object obj3 = objArr[i4 + 1];
                        Object orDefault = aVar.getOrDefault(obj2, null);
                        if (obj3 == null) {
                            i3 = (orDefault == null && aVar.containsKey(obj2)) ? i3 + 1 : 0;
                        } else if (obj3.equals(orDefault)) {
                        }
                    }
                    return true;
                }
            } else if (obj instanceof Map) {
                Map map = (Map) obj;
                if (this.f682f == map.size()) {
                    for (0; i2 < this.f682f; i2 + 1) {
                        Object[] objArr2 = this.f681e;
                        int i5 = i2 << 1;
                        Object obj4 = objArr2[i5];
                        Object obj5 = objArr2[i5 + 1];
                        Object obj6 = map.get(obj4);
                        if (obj5 == null) {
                            i2 = (obj6 == null && map.containsKey(obj4)) ? i2 + 1 : 0;
                        } else if (obj5.equals(obj6)) {
                        }
                    }
                    return true;
                }
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final int f() {
        int i2 = this.f682f;
        if (i2 == 0) {
            return -1;
        }
        int iB = b(this.f680d, i2, 0);
        if (iB < 0 || this.f681e[iB << 1] == null) {
            return iB;
        }
        int i3 = iB + 1;
        while (i3 < i2 && this.f680d[i3] == 0) {
            if (this.f681e[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iB - 1; i4 >= 0 && this.f680d[i4] == 0; i4--) {
            if (this.f681e[i4 << 1] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    public final int g(Object obj) {
        int i2 = this.f682f * 2;
        Object[] objArr = this.f681e;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return getOrDefault(obj, null);
    }

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        int iE = e(obj);
        return iE >= 0 ? this.f681e[(iE << 1) + 1] : obj2;
    }

    public final Object h(int i2) {
        Object[] objArr = this.f681e;
        int i3 = i2 << 1;
        Object obj = objArr[i3 + 1];
        int i4 = this.f682f;
        int i5 = 0;
        if (i4 <= 1) {
            c(this.f680d, objArr, i4);
            this.f680d = b.f684a;
            this.f681e = b.f685b;
        } else {
            int i6 = i4 - 1;
            int[] iArr = this.f680d;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    int i8 = i6 - i2;
                    System.arraycopy(iArr, i7, iArr, i2, i8);
                    Object[] objArr2 = this.f681e;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i3, i8 << 1);
                }
                Object[] objArr3 = this.f681e;
                int i9 = i6 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                a(i4 > 8 ? i4 + (i4 >> 1) : 8);
                if (i4 != this.f682f) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.f680d, 0, i2);
                    System.arraycopy(objArr, 0, this.f681e, 0, i3);
                }
                if (i2 < i6) {
                    int i10 = i2 + 1;
                    int i11 = i6 - i2;
                    System.arraycopy(iArr, i10, this.f680d, i2, i11);
                    System.arraycopy(objArr, i10 << 1, this.f681e, i3, i11 << 1);
                }
            }
            i5 = i6;
        }
        if (i4 != this.f682f) {
            throw new ConcurrentModificationException();
        }
        this.f682f = i5;
        return obj;
    }

    @Override // java.util.Map
    public final int hashCode() {
        int[] iArr = this.f680d;
        Object[] objArr = this.f681e;
        int i2 = this.f682f;
        int i3 = 1;
        int i4 = 0;
        int iHashCode = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return iHashCode;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.f682f <= 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.f683g == null) {
            this.f683g = new H.e(this);
        }
        H.e eVar = this.f683g;
        if (((d) eVar.f175e) == null) {
            eVar.f175e = new d(eVar, 1);
        }
        return (d) eVar.f175e;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        int i2;
        int iD;
        int i3 = this.f682f;
        if (obj == null) {
            iD = f();
            i2 = 0;
        } else {
            int iHashCode = obj.hashCode();
            i2 = iHashCode;
            iD = d(iHashCode, obj);
        }
        if (iD >= 0) {
            int i4 = (iD << 1) + 1;
            Object[] objArr = this.f681e;
            Object obj3 = objArr[i4];
            objArr[i4] = obj2;
            return obj3;
        }
        int i5 = ~iD;
        int[] iArr = this.f680d;
        if (i3 >= iArr.length) {
            int i6 = 8;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 < 4) {
                i6 = 4;
            }
            Object[] objArr2 = this.f681e;
            a(i6);
            if (i3 != this.f682f) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f680d;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f681e, 0, objArr2.length);
            }
            c(iArr, objArr2, i3);
        }
        if (i5 < i3) {
            int[] iArr3 = this.f680d;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.f681e;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.f682f - i5) << 1);
        }
        int i8 = this.f682f;
        if (i3 == i8) {
            int[] iArr4 = this.f680d;
            if (i5 < iArr4.length) {
                iArr4[i5] = i2;
                Object[] objArr4 = this.f681e;
                int i9 = i5 << 1;
                objArr4[i9] = obj;
                objArr4[i9 + 1] = obj2;
                this.f682f = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int size = map.size() + this.f682f;
        int i2 = this.f682f;
        int[] iArr = this.f680d;
        if (iArr.length < size) {
            Object[] objArr = this.f681e;
            a(size);
            if (this.f682f > 0) {
                System.arraycopy(iArr, 0, this.f680d, 0, i2);
                System.arraycopy(objArr, 0, this.f681e, 0, i2 << 1);
            }
            c(iArr, objArr, i2);
        }
        if (this.f682f != i2) {
            throw new ConcurrentModificationException();
        }
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Object putIfAbsent(Object obj, Object obj2) {
        Object orDefault = getOrDefault(obj, null);
        return orDefault == null ? put(obj, obj2) : orDefault;
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return h(iE);
        }
        return null;
    }

    @Override // java.util.Map
    public final Object replace(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE < 0) {
            return null;
        }
        int i2 = (iE << 1) + 1;
        Object[] objArr = this.f681e;
        Object obj3 = objArr[i2];
        objArr[i2] = obj2;
        return obj3;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f682f;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f682f * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f682f; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            int i3 = i2 << 1;
            Object obj = this.f681e[i3];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object obj2 = this.f681e[i3 + 1];
            if (obj2 != this) {
                sb.append(obj2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.f683g == null) {
            this.f683g = new H.e(this);
        }
        H.e eVar = this.f683g;
        if (((f) eVar.f176f) == null) {
            eVar.f176f = new f(eVar);
        }
        return (f) eVar.f176f;
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE < 0) {
            return false;
        }
        Object obj3 = this.f681e[(iE << 1) + 1];
        if (obj2 != obj3 && (obj2 == null || !obj2.equals(obj3))) {
            return false;
        }
        h(iE);
        return true;
    }

    @Override // java.util.Map
    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int iE = e(obj);
        if (iE < 0) {
            return false;
        }
        int i2 = (iE << 1) + 1;
        Object obj4 = this.f681e[i2];
        if (obj4 != obj2 && (obj2 == null || !obj2.equals(obj4))) {
            return false;
        }
        Object[] objArr = this.f681e;
        Object obj5 = objArr[i2];
        objArr[i2] = obj3;
        return true;
    }
}
