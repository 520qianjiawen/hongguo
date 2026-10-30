package J;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class s implements V.b, Z.m {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static String f251k;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static k f254o;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f255d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public H.e f256e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final HashMap f246f = new HashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final HashMap f247g = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f248h = new Object();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Object f249i = new Object();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static int f250j = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static int f252l = 0;
    public static int m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static int f253n = 0;

    public static void a(s sVar, g gVar) {
        try {
            if (gVar.f194d >= 1) {
                Log.d("Sqflite", gVar.i() + "closing database ");
            }
            gVar.b();
        } catch (Exception e2) {
            Log.e("Sqflite", "error " + e2 + " while closing database " + f253n);
        }
        synchronized (f248h) {
            try {
                if (f247g.isEmpty() && f254o != null) {
                    if (gVar.f194d >= 1) {
                        Log.d("Sqflite", gVar.i() + "stopping thread");
                    }
                    f254o.b();
                    f254o = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static g b(H.a aVar, Y.k kVar) {
        Integer num = (Integer) aVar.x("id");
        int iIntValue = num.intValue();
        g gVar = (g) f247g.get(num);
        if (gVar != null) {
            return gVar;
        }
        kVar.a("sqlite_error", "database_closed " + iIntValue, null);
        return null;
    }

    public static HashMap c(int i2, boolean z2, boolean z3) {
        HashMap map = new HashMap();
        map.put("id", Integer.valueOf(i2));
        if (z2) {
            map.put("recovered", Boolean.TRUE);
        }
        if (z3) {
            map.put("recoveredInTransaction", Boolean.TRUE);
        }
        return map;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // Z.m
    public final void m(final H.a aVar, final Y.k kVar) {
        final int i2;
        g gVar;
        String str = (String) aVar.f170e;
        str.getClass();
        int i3 = 5;
        int i4 = 4;
        int i5 = 2;
        int i6 = 1;
        boolean zExists = false;
        Object[] objArr = 0;
        byte b2 = -1;
        switch (str.hashCode()) {
            case -1319569547:
                if (str.equals("execute")) {
                    b2 = 0;
                }
                break;
            case -1253581933:
                if (str.equals("closeDatabase")) {
                    b2 = 1;
                }
                break;
            case -1249474914:
                if (str.equals("options")) {
                    b2 = 2;
                }
                break;
            case -1183792455:
                if (str.equals("insert")) {
                    b2 = 3;
                }
                break;
            case -838846263:
                if (str.equals("update")) {
                    b2 = 4;
                }
                break;
            case -396289107:
                if (str.equals("androidSetLocale")) {
                    b2 = 5;
                }
                break;
            case -263511994:
                if (str.equals("deleteDatabase")) {
                    b2 = 6;
                }
                break;
            case -198450538:
                if (str.equals("debugMode")) {
                    b2 = 7;
                }
                break;
            case -17190427:
                if (str.equals("openDatabase")) {
                    b2 = 8;
                }
                break;
            case 93509434:
                if (str.equals("batch")) {
                    b2 = 9;
                }
                break;
            case 95458899:
                if (str.equals("debug")) {
                    b2 = 10;
                }
                break;
            case 107944136:
                if (str.equals("query")) {
                    b2 = 11;
                }
                break;
            case 956410295:
                if (str.equals("databaseExists")) {
                    b2 = 12;
                }
                break;
            case 1193546321:
                if (str.equals("queryCursorNext")) {
                    b2 = 13;
                }
                break;
            case 1385449135:
                if (str.equals("getPlatformVersion")) {
                    b2 = 14;
                }
                break;
            case 1863829223:
                if (str.equals("getDatabasesPath")) {
                    b2 = 15;
                }
                break;
        }
        g gVar2 = null;
        switch (b2) {
            case 0:
                g gVarB = b(aVar, kVar);
                if (gVarB == null) {
                    return;
                }
                f254o.a(gVarB, new o(aVar, kVar, gVarB, i4));
                return;
            case 1:
                Integer num = (Integer) aVar.x("id");
                int iIntValue = num.intValue();
                g gVarB2 = b(aVar, kVar);
                if (gVarB2 == null) {
                    return;
                }
                if (gVarB2.f194d >= 1) {
                    Log.d("Sqflite", gVarB2.i() + "closing " + iIntValue + " " + gVarB2.f192b);
                }
                String str2 = gVarB2.f192b;
                synchronized (f248h) {
                    try {
                        f247g.remove(num);
                        if (gVarB2.f191a) {
                            f246f.remove(str2);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                f254o.a(gVarB2, new q(this, gVarB2, kVar));
                return;
            case 2:
                Object objX = aVar.x("androidThreadPriority");
                if (objX != null) {
                    f252l = ((Integer) objX).intValue();
                }
                Object objX2 = aVar.x("androidThreadCount");
                if (objX2 != null && !objX2.equals(Integer.valueOf(m))) {
                    m = ((Integer) objX2).intValue();
                    k kVar2 = f254o;
                    if (kVar2 != null) {
                        kVar2.b();
                        f254o = null;
                    }
                }
                Integer num2 = (Integer) aVar.x("logLevel");
                if (num2 != null) {
                    f250j = num2.intValue();
                }
                kVar.d(null);
                return;
            case 3:
                g gVarB3 = b(aVar, kVar);
                if (gVarB3 == null) {
                    return;
                }
                f254o.a(gVarB3, new o(aVar, kVar, gVarB3, i6));
                return;
            case 4:
                g gVarB4 = b(aVar, kVar);
                if (gVarB4 == null) {
                    return;
                }
                f254o.a(gVarB4, new o(aVar, kVar, gVarB4, i3));
                return;
            case 5:
                g gVarB5 = b(aVar, kVar);
                if (gVarB5 == null) {
                    return;
                }
                f254o.a(gVarB5, new o(aVar, gVarB5, kVar));
                return;
            case 6:
                String str3 = (String) aVar.x("path");
                synchronized (f248h) {
                    try {
                        if (a.a(f250j)) {
                            Log.d("Sqflite", "Look for " + str3 + " in " + f246f.keySet());
                        }
                        HashMap map = f246f;
                        Integer num3 = (Integer) map.get(str3);
                        if (num3 != null) {
                            HashMap map2 = f247g;
                            g gVar3 = (g) map2.get(num3);
                            if (gVar3 != null && gVar3.f199i.isOpen()) {
                                if (a.a(f250j)) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(gVar3.i());
                                    sb.append("found single instance ");
                                    sb.append(gVar3.k() ? "(in transaction) " : "");
                                    sb.append(num3);
                                    sb.append(" ");
                                    sb.append(str3);
                                    Log.d("Sqflite", sb.toString());
                                }
                                map2.remove(num3);
                                map.remove(str3);
                                gVar2 = gVar3;
                            }
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                    break;
                }
                r rVar = new r(this, gVar2, str3, kVar);
                k kVar3 = f254o;
                if (kVar3 != null) {
                    kVar3.a(gVar2, rVar);
                    return;
                } else {
                    rVar.run();
                    return;
                }
            case 7:
                boolean zEquals = Boolean.TRUE.equals(aVar.f171f);
                if (!zEquals) {
                    f250j = 0;
                } else if (zEquals) {
                    f250j = 1;
                }
                kVar.d(null);
                return;
            case 8:
                final String str4 = (String) aVar.x("path");
                final Boolean bool = (Boolean) aVar.x("readOnly");
                final boolean z2 = str4 == null || str4.equals(":memory:");
                boolean z3 = (Boolean.FALSE.equals(aVar.x("singleInstance")) || z2) ? false : true;
                if (z3) {
                    synchronized (f248h) {
                        try {
                            if (a.a(f250j)) {
                                Log.d("Sqflite", "Look for " + str4 + " in " + f246f.keySet());
                            }
                            Integer num4 = (Integer) f246f.get(str4);
                            if (num4 != null && (gVar = (g) f247g.get(num4)) != null) {
                                if (gVar.f199i.isOpen()) {
                                    if (a.a(f250j)) {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append(gVar.i());
                                        sb2.append("re-opened single instance ");
                                        sb2.append(gVar.k() ? "(in transaction) " : "");
                                        sb2.append(num4);
                                        sb2.append(" ");
                                        sb2.append(str4);
                                        Log.d("Sqflite", sb2.toString());
                                    }
                                    kVar.d(c(num4.intValue(), true, gVar.k()));
                                    return;
                                }
                                if (a.a(f250j)) {
                                    Log.d("Sqflite", gVar.i() + "single instance database of " + str4 + " not opened");
                                }
                            }
                        } catch (Throwable th3) {
                            throw th3;
                        }
                    }
                }
                Object obj = f248h;
                synchronized (obj) {
                    i2 = f253n + 1;
                    f253n = i2;
                    break;
                }
                final g gVar4 = new g(this.f255d, str4, i2, z3, f250j);
                synchronized (obj) {
                    try {
                        if (f254o == null) {
                            int i7 = m;
                            int i8 = f252l;
                            k mVar = i7 == 1 ? new m(i8) : new l(i7, i8);
                            f254o = mVar;
                            mVar.start();
                            if (gVar4.f194d >= 1) {
                                Log.d("Sqflite", gVar4.i() + "starting worker pool with priority " + f252l);
                            }
                        }
                        gVar4.f198h = f254o;
                        if (gVar4.f194d >= 1) {
                            Log.d("Sqflite", gVar4.i() + "opened " + i2 + " " + str4);
                        }
                        final boolean z4 = z3;
                        f254o.a(gVar4, new Runnable() { // from class: J.p
                            /* JADX WARN: Code duplicated, block: B:19:0x0057 A[Catch: all -> 0x004b, Exception -> 0x00b8, TryCatch #1 {Exception -> 0x00b8, blocks: (B:17:0x004e, B:19:0x0057, B:20:0x0065), top: B:45:0x004e, outer: #2 }] */
                            /* JADX WARN: Code duplicated, block: B:20:0x0065 A[Catch: all -> 0x004b, Exception -> 0x00b8, TRY_LEAVE, TryCatch #1 {Exception -> 0x00b8, blocks: (B:17:0x004e, B:19:0x0057, B:20:0x0065), top: B:45:0x004e, outer: #2 }] */
                            /* JADX WARN: Code duplicated, block: B:23:0x006b A[DONT_INVERT] */
                            /* JADX WARN: Code duplicated, block: B:27:0x0079 A[Catch: all -> 0x0077, TryCatch #0 {all -> 0x0077, blocks: (B:24:0x006d, B:27:0x0079, B:28:0x0082), top: B:43:0x006d, outer: #2 }] */
                            /* JADX WARN: Code duplicated, block: B:31:0x0087 A[Catch: all -> 0x004b, TryCatch #2 {, blocks: (B:6:0x0018, B:8:0x002c, B:10:0x0032, B:12:0x0038, B:13:0x0049, B:17:0x004e, B:19:0x0057, B:21:0x0068, B:22:0x006a, B:29:0x0083, B:31:0x0087, B:32:0x00ac, B:36:0x00b7, B:20:0x0065, B:38:0x00b9, B:39:0x00c1, B:24:0x006d, B:27:0x0079, B:28:0x0082), top: B:46:0x0018, inners: #0, #1 }] */
                            /* JADX WARN: Code duplicated, block: B:43:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
                            /* JADX WARN: Instruction removed from duplicated block: B:31:0x0087, please report this as an issue */
                            @Override // java.lang.Runnable
                            public final void run() {
                                boolean z5 = z2;
                                String str5 = str4;
                                Y.k kVar4 = kVar;
                                Boolean bool2 = bool;
                                g gVar5 = gVar4;
                                H.a aVar2 = aVar;
                                boolean z6 = z4;
                                int i9 = i2;
                                synchronized (s.f249i) {
                                    if (z5) {
                                        if (Boolean.TRUE.equals(bool2)) {
                                            gVar5.f199i = SQLiteDatabase.openDatabase(gVar5.f192b, null, 1, new f());
                                        } else {
                                            gVar5.l();
                                        }
                                        synchronized (s.f248h) {
                                            if (!z6) {
                                                s.f247g.put(Integer.valueOf(i9), gVar5);
                                                if (gVar5.f194d >= 1) {
                                                    Log.d("Sqflite", gVar5.i() + "opened " + i9 + " " + str5);
                                                }
                                                kVar4.d(s.c(i9, false, false));
                                                return;
                                            }
                                            s.f246f.put(str5, Integer.valueOf(i9));
                                            s.f247g.put(Integer.valueOf(i9), gVar5);
                                            if (gVar5.f194d >= 1) {
                                                Log.d("Sqflite", gVar5.i() + "opened " + i9 + " " + str5);
                                            }
                                            kVar4.d(s.c(i9, false, false));
                                            return;
                                        }
                                    }
                                    File file = new File(new File(str5).getParent());
                                    if (!file.exists() && !file.mkdirs() && !file.exists()) {
                                        kVar4.a("sqlite_error", "open_failed " + str5, null);
                                        return;
                                    }
                                    try {
                                        if (Boolean.TRUE.equals(bool2)) {
                                            gVar5.f199i = SQLiteDatabase.openDatabase(gVar5.f192b, null, 1, new f());
                                        } else {
                                            gVar5.l();
                                        }
                                        synchronized (s.f248h) {
                                            if (!z6) {
                                                try {
                                                    s.f246f.put(str5, Integer.valueOf(i9));
                                                    s.f247g.put(Integer.valueOf(i9), gVar5);
                                                } catch (Throwable th4) {
                                                    throw th4;
                                                }
                                            } else {
                                                s.f247g.put(Integer.valueOf(i9), gVar5);
                                            }
                                        }
                                        if (gVar5.f194d >= 1) {
                                            Log.d("Sqflite", gVar5.i() + "opened " + i9 + " " + str5);
                                        }
                                        kVar4.d(s.c(i9, false, false));
                                        return;
                                    } catch (Exception e2) {
                                        gVar5.j(e2, new K.b(aVar2, kVar4));
                                        return;
                                    }
                                    throw th;
                                }
                            }
                        });
                    } catch (Throwable th4) {
                        throw th4;
                    }
                    break;
                }
                return;
            case 9:
                g gVarB6 = b(aVar, kVar);
                if (gVarB6 == null) {
                    return;
                }
                f254o.a(gVarB6, new o(gVarB6, aVar, kVar));
                return;
            case 10:
                String str5 = (String) aVar.x("cmd");
                HashMap map3 = new HashMap();
                if ("get".equals(str5)) {
                    int i9 = f250j;
                    if (i9 > 0) {
                        map3.put("logLevel", Integer.valueOf(i9));
                    }
                    HashMap map4 = f247g;
                    if (!map4.isEmpty()) {
                        HashMap map5 = new HashMap();
                        for (Map.Entry entry : map4.entrySet()) {
                            g gVar5 = (g) entry.getValue();
                            HashMap map6 = new HashMap();
                            map6.put("path", gVar5.f192b);
                            map6.put("singleInstance", Boolean.valueOf(gVar5.f191a));
                            int i10 = gVar5.f194d;
                            if (i10 > 0) {
                                map6.put("logLevel", Integer.valueOf(i10));
                            }
                            map5.put(((Integer) entry.getKey()).toString(), map6);
                        }
                        map3.put("databases", map5);
                    }
                }
                kVar.d(map3);
                return;
            case 11:
                g gVarB7 = b(aVar, kVar);
                if (gVarB7 == null) {
                    return;
                }
                f254o.a(gVarB7, new o(aVar, kVar, gVarB7, i5));
                return;
            case 12:
                try {
                    zExists = new File((String) aVar.x("path")).exists();
                    break;
                } catch (Exception unused) {
                }
                kVar.d(Boolean.valueOf(zExists));
                return;
            case 13:
                g gVarB8 = b(aVar, kVar);
                if (gVarB8 == null) {
                    return;
                }
                f254o.a(gVarB8, new o(aVar, kVar, gVarB8, objArr == true ? 1 : 0));
                return;
            case 14:
                kVar.d("Android " + Build.VERSION.RELEASE);
                return;
            case 15:
                if (f251k == null) {
                    f251k = this.f255d.getDatabasePath("tekartik_sqflite.db").getParent();
                }
                kVar.d(f251k);
                return;
            default:
                kVar.b();
                return;
        }
    }

    @Override // V.b
    public final void onAttachedToEngine(V.a aVar) {
        Context context = aVar.f515a;
        Z.f fVar = aVar.f517c;
        this.f255d = context;
        Z.q qVar = Z.q.f598a;
        fVar.getClass();
        H.e eVar = new H.e(fVar, "com.tekartik.sqflite", qVar, fVar.f(new Z.k()));
        this.f256e = eVar;
        eVar.j(this);
    }

    @Override // V.b
    public final void onDetachedFromEngine(V.a aVar) {
        this.f255d = null;
        this.f256e.j(null);
        this.f256e = null;
    }
}
