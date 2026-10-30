package C0;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List f57a;

    static {
        try {
            Iterator it = Arrays.asList(new y0.b()).iterator();
            p0.h.e(it, "<this>");
            f57a = v0.c.a(new v0.a(new v0.f(it)));
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }
}
