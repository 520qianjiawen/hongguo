package g0;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends p000a.a {
    public static List T(Object... objArr) {
        if (objArr.length <= 0) {
            return m.f733d;
        }
        List listAsList = Arrays.asList(objArr);
        p0.h.d(listAsList, "asList(...)");
        return listAsList;
    }
}
