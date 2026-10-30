package H;

import Y.k;
import Z.f;
import Z.m;
import Z.n;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.LocaleSpan;
import android.text.style.TtsSpan;
import android.text.style.URLSpan;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.o;
import io.flutter.view.q;
import java.io.File;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class e implements K.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f174d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f175e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f176f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f177g;

    public e(e.a aVar) {
        this.f177g = aVar;
    }

    public static boolean f(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            return set.size() == set2.size() && set.containsAll(set2);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public SpannableString a() {
        if (((String) this.f174d) == null) {
            return null;
        }
        SpannableString spannableString = new SpannableString((String) this.f174d);
        List<q> list = (List) this.f175e;
        if (list != null) {
            for (q qVar : list) {
                int iA = p014o.c.a(qVar.f1049c);
                if (iA == 0) {
                    spannableString.setSpan(new TtsSpan.Builder("android.type.verbatim").build(), qVar.f1047a, qVar.f1048b, 0);
                } else if (iA == 1) {
                    spannableString.setSpan(new LocaleSpan(Locale.forLanguageTag(((o) qVar).f1046d)), qVar.f1047a, qVar.f1048b, 0);
                }
            }
        }
        String str = (String) this.f177g;
        if (str != null && !str.isEmpty()) {
            spannableString.setSpan(new URLSpan((String) this.f177g), 0, ((String) this.f174d).length(), 0);
        }
        String str2 = (String) this.f176f;
        if (str2 != null && !str2.isEmpty()) {
            spannableString.setSpan(new LocaleSpan(Locale.forLanguageTag((String) this.f176f)), 0, ((String) this.f174d).length(), 0);
        }
        return spannableString;
    }

    public Object b(int i2, int i3) {
        return ((e.a) this.f177g).f681e[(i2 << 1) + i3];
    }

    public void c(int i2) {
        ((e.a) this.f177g).h(i2);
    }

    @Override // K.c
    public void d(Object obj, String str) {
        this.f175e = "sqlite_error";
        this.f176f = str;
        this.f177g = obj;
    }

    @Override // K.c
    public void e(Object obj) {
        this.f174d = obj;
    }

    public File g(Context context) {
        ((b) this.f175e).getClass();
        return new File(context.getDir("lib", 0), System.mapLibraryName("flutter"));
    }

    public void h(String str, Object obj, k kVar) {
        ((f) this.f174d).b((String) this.f175e, ((n) this.f176f).d(new a(obj, str)), kVar == null ? null : new Z.a(1, this, kVar));
    }

    public void i(String str, Object... objArr) {
        String str2 = String.format(Locale.US, str, objArr);
        if (((C.a) this.f177g) != null) {
            FlutterJNI.lambda$loadLibrary$0(str2);
        }
    }

    public void j(m mVar) {
        String str = (String) this.f175e;
        f fVar = (f) this.f174d;
        b bVar = (b) this.f177g;
        if (bVar != null) {
            fVar.a(str, mVar != null ? new a(13, this, mVar) : null, bVar);
        } else {
            fVar.i(str, mVar != null ? new a(13, this, mVar) : null);
        }
    }

    public Object[] k(Object[] objArr, int i2) {
        int i3 = ((e.a) this.f177g).f682f;
        if (objArr.length < i3) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i3);
        }
        for (int i4 = 0; i4 < i3; i4++) {
            objArr[i4] = b(i4, i2);
        }
        if (objArr.length > i3) {
            objArr[i3] = null;
        }
        return objArr;
    }

    public e(f fVar, String str) {
        this(fVar, str, Z.q.f598a, null);
    }

    public e(f fVar, String str, n nVar, b bVar) {
        this.f174d = fVar;
        this.f175e = str;
        this.f176f = nVar;
        this.f177g = bVar;
    }
}
