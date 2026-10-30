package p001a0;

import C0.x;
import H.e;
import android.content.Context;
import android.content.res.Configuration;
import android.os.LocaleList;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H.a f607a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f608b;

    public a(Context context, H.a aVar) {
        x xVar = new x(28, this);
        this.f608b = context;
        this.f607a = aVar;
        aVar.f171f = xVar;
    }

    public static Locale a(String str) {
        Locale.Builder builder = new Locale.Builder();
        String[] strArrSplit = str.replace('_', '-').split("-");
        builder.setLanguage(strArrSplit[0]);
        int i2 = 1;
        if (strArrSplit.length > 1 && strArrSplit[1].length() == 4) {
            builder.setScript(strArrSplit[1]);
            i2 = 2;
        }
        if (strArrSplit.length > i2 && strArrSplit[i2].length() >= 2 && strArrSplit[i2].length() <= 3) {
            builder.setRegion(strArrSplit[i2]);
        }
        return builder.build();
    }

    public final void b(Configuration configuration) {
        ArrayList arrayList = new ArrayList();
        LocaleList locales = configuration.getLocales();
        int size = locales.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            arrayList.add(locales.get(i3));
        }
        ArrayList arrayList2 = new ArrayList();
        int size2 = arrayList.size();
        while (i2 < size2) {
            Object obj = arrayList.get(i2);
            i2++;
            Locale locale = (Locale) obj;
            locale.getLanguage();
            locale.getCountry();
            locale.getVariant();
            arrayList2.add(locale.getLanguage());
            arrayList2.add(locale.getCountry());
            arrayList2.add(locale.getScript());
            arrayList2.add(locale.getVariant());
        }
        ((e) this.f607a.f170e).h("setLocale", arrayList2, null);
    }
}
