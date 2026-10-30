package g0;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends p000a.a {
    public static final void T(Object[] objArr, Object[] objArr2, int i2, int i3, int i4) {
        p0.h.e(objArr, "<this>");
        p0.h.e(objArr2, "destination");
        System.arraycopy(objArr, i3, objArr2, i2, i4 - i3);
    }

    public static Object U(Object[] objArr) {
        p0.h.e(objArr, "<this>");
        if (objArr.length != 0) {
            return objArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }
}
