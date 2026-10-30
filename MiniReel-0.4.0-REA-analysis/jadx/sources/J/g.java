package J;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static Boolean f190n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f191a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f192b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f193c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f194d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f195e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public k f198h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SQLiteDatabase f199i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Integer f202l;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f196f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f197g = new HashMap();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f200j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f201k = 0;
    public int m = 0;

    public g(Context context, String str, int i2, boolean z2, int i3) {
        this.f195e = context;
        this.f192b = str;
        this.f191a = z2;
        this.f193c = i2;
        this.f194d = i3;
    }

    public static void a(SQLiteStatement sQLiteStatement, Object[] objArr) {
        int i2 = 0;
        while (i2 < objArr.length) {
            Object obj = objArr[i2];
            i2++;
            if (obj == null) {
                sQLiteStatement.bindNull(i2);
            } else if (obj instanceof byte[]) {
                sQLiteStatement.bindBlob(i2, (byte[]) obj);
            } else if ((obj instanceof Double) || (obj instanceof Float)) {
                sQLiteStatement.bindDouble(i2, ((Number) obj).doubleValue());
            } else if (obj instanceof Number) {
                sQLiteStatement.bindLong(i2, ((Number) obj).longValue());
            } else if (obj instanceof Boolean) {
                sQLiteStatement.bindLong(i2, ((Boolean) obj).booleanValue() ? 1L : 0L);
            } else {
                sQLiteStatement.bindString(i2, obj.toString());
            }
        }
    }

    public static HashMap d(Cursor cursor, Integer num) {
        Object objValueOf;
        HashMap map = null;
        ArrayList arrayList = null;
        int columnCount = 0;
        while (cursor.moveToNext()) {
            if (map == null) {
                arrayList = new ArrayList();
                map = new HashMap();
                columnCount = cursor.getColumnCount();
                map.put("columns", Arrays.asList(cursor.getColumnNames()));
                map.put("rows", arrayList);
            }
            ArrayList arrayList2 = new ArrayList(columnCount);
            for (int i2 = 0; i2 < columnCount; i2++) {
                int type = cursor.getType(i2);
                if (type == 1) {
                    objValueOf = Long.valueOf(cursor.getLong(i2));
                } else if (type == 2) {
                    objValueOf = Double.valueOf(cursor.getDouble(i2));
                } else if (type != 3) {
                    objValueOf = type != 4 ? null : cursor.getBlob(i2);
                } else {
                    objValueOf = cursor.getString(i2);
                }
                arrayList2.add(objValueOf);
            }
            arrayList.add(arrayList2);
            if (num != null && arrayList.size() >= num.intValue()) {
                break;
            }
        }
        return map == null ? new HashMap() : map;
    }

    public final void b() {
        HashMap map = this.f197g;
        if (!map.isEmpty() && this.f194d >= 1) {
            Log.d("Sqflite", i() + map.size() + " cursor(s) are left opened");
        }
        this.f199i.close();
    }

    public final void c(n nVar) {
        try {
            int i2 = nVar.f224a;
            if (a.a(this.f194d)) {
                Log.d("Sqflite", i() + "closing cursor " + i2);
            }
            this.f197g.remove(Integer.valueOf(i2));
            nVar.f226c.close();
        } catch (Exception unused) {
        }
    }

    public final boolean e(p000a.a aVar) {
        t tVarU = aVar.u();
        if (this.f194d >= 1) {
            Log.d("Sqflite", i() + tVarU);
        }
        try {
            SQLiteStatement sQLiteStatementCompileStatement = this.f199i.compileStatement(tVarU.f257a);
            a(sQLiteStatementCompileStatement, tVarU.a());
            long jExecuteInsert = sQLiteStatementCompileStatement.executeInsert();
            if (aVar.s()) {
                aVar.e(null);
                return true;
            }
            aVar.e(Long.valueOf(jExecuteInsert));
            return true;
        } catch (Exception e2) {
            j(e2, aVar);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [J.t, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v0, types: [a.a] */
    public final boolean f(p000a.a aVar) throws Throwable {
        Integer num = (Integer) aVar.p("cursorPageSize");
        final ?? U = aVar.u();
        if (this.f194d >= 1) {
            Log.d("Sqflite", i() + U);
        }
        n nVar = null;
        try {
            try {
                U = this.f199i.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: J.d
                    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
                    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                        List list = U.f258b;
                        if (list != null) {
                            int size = list.size();
                            int i2 = 0;
                            while (i2 < size) {
                                Object objB = t.b(list.get(i2));
                                int i3 = i2 + 1;
                                if (objB == null) {
                                    sQLiteQuery.bindNull(i3);
                                } else if (objB instanceof byte[]) {
                                    sQLiteQuery.bindBlob(i3, (byte[]) objB);
                                } else if (objB instanceof Double) {
                                    sQLiteQuery.bindDouble(i3, ((Double) objB).doubleValue());
                                } else if (objB instanceof Integer) {
                                    sQLiteQuery.bindLong(i3, ((Integer) objB).intValue());
                                } else if (objB instanceof Long) {
                                    sQLiteQuery.bindLong(i3, ((Long) objB).longValue());
                                } else if (objB instanceof String) {
                                    sQLiteQuery.bindString(i3, (String) objB);
                                } else {
                                    if (!(objB instanceof Boolean)) {
                                        throw new IllegalArgumentException("Could not bind " + objB + " from index " + i2 + ": Supported types are null, byte[], double, long, boolean and String");
                                    }
                                    sQLiteQuery.bindLong(i3, ((Boolean) objB).booleanValue() ? 1L : 0L);
                                }
                                i2 = i3;
                            }
                        }
                        return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
                    }
                }, U.f257a, a.f183a, null);
                try {
                    HashMap mapD = d(U, num);
                    if (num != null && !U.isLast() && !U.isAfterLast()) {
                        int i2 = this.m + 1;
                        this.m = i2;
                        mapD.put("cursorId", Integer.valueOf(i2));
                        n nVar2 = new n(i2, num.intValue(), U);
                        try {
                            this.f197g.put(Integer.valueOf(i2), nVar2);
                            nVar = nVar2;
                        } catch (Exception e2) {
                            e = e2;
                            nVar = nVar2;
                            j(e, aVar);
                            if (nVar != null) {
                                c(nVar);
                            }
                            if (nVar != null || U == 0) {
                                return false;
                            }
                            U.close();
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            nVar = nVar2;
                            if (nVar == null && U != 0) {
                                U.close();
                            }
                            throw th;
                        }
                    }
                    aVar.e(mapD);
                    if (nVar == null) {
                        U.close();
                    }
                    return true;
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e = e4;
            U = 0;
        } catch (Throwable th3) {
            th = th3;
            U = 0;
        }
    }

    public final boolean g(p000a.a aVar) {
        t tVarU = aVar.u();
        if (this.f194d >= 1) {
            Log.d("Sqflite", i() + tVarU);
        }
        try {
            SQLiteStatement sQLiteStatementCompileStatement = this.f199i.compileStatement(tVarU.f257a);
            a(sQLiteStatementCompileStatement, tVarU.a());
            int iExecuteUpdateDelete = sQLiteStatementCompileStatement.executeUpdateDelete();
            if (aVar.s()) {
                aVar.e(null);
                return true;
            }
            aVar.e(Integer.valueOf(iExecuteUpdateDelete));
            return true;
        } catch (Exception e2) {
            j(e2, aVar);
            return false;
        }
    }

    public final boolean h(p000a.a aVar) {
        t tVarU = aVar.u();
        if (this.f194d >= 1) {
            Log.d("Sqflite", i() + tVarU);
        }
        Object objP = aVar.p("inTransaction");
        Boolean bool = objP instanceof Boolean ? (Boolean) objP : null;
        try {
            this.f199i.execSQL(tVarU.f257a, tVarU.a());
            synchronized (this) {
                try {
                    if (Boolean.TRUE.equals(bool)) {
                        this.f200j++;
                    } else if (Boolean.FALSE.equals(bool)) {
                        this.f200j--;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        } catch (Exception e2) {
            j(e2, aVar);
            return false;
        }
    }

    public final String i() {
        StringBuilder sb = new StringBuilder("[");
        Thread threadCurrentThread = Thread.currentThread();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f193c);
        sb2.append(",");
        sb2.append(threadCurrentThread.getName());
        sb2.append("(");
        sb2.append(Build.VERSION.SDK_INT >= 36 ? threadCurrentThread.threadId() : threadCurrentThread.getId());
        sb2.append(")");
        sb.append(sb2.toString());
        sb.append("] ");
        return sb.toString();
    }

    public final void j(Exception exc, p000a.a aVar) {
        if (exc instanceof SQLiteCantOpenDatabaseException) {
            aVar.d(null, "open_failed " + this.f192b);
        } else {
            if (exc instanceof SQLException) {
                String message = exc.getMessage();
                t tVarU = aVar.u();
                HashMap map = new HashMap();
                map.put("sql", tVarU.f257a);
                map.put("arguments", tVarU.f258b);
                aVar.d(map, message);
                return;
            }
            String message2 = exc.getMessage();
            t tVarU2 = aVar.u();
            HashMap map2 = new HashMap();
            map2.put("sql", tVarU2.f257a);
            map2.put("arguments", tVarU2.f258b);
            aVar.d(map2, message2);
        }
    }

    public final synchronized boolean k() {
        return this.f200j > 0;
    }

    public final void l() {
        if (f190n == null) {
            Context context = this.f195e;
            boolean z2 = false;
            try {
                String packageName = context.getPackageName();
                if ((Build.VERSION.SDK_INT >= 33 ? context.getPackageManager().getApplicationInfo(packageName, PackageManager.ApplicationInfoFlags.of(128L)) : context.getPackageManager().getApplicationInfo(packageName, 128)).metaData.getBoolean("com.tekartik.sqflite.wal_enabled", false)) {
                    z2 = true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            f190n = Boolean.valueOf(z2);
            if (z2 && a.a(this.f194d)) {
                Log.d("Sqflite", i() + "[sqflite] WAL enabled");
            }
        }
        this.f199i = SQLiteDatabase.openDatabase(this.f192b, null, f190n.booleanValue() ? 805306368 : 268435456);
    }

    public final void m(K.b bVar, Runnable runnable) {
        Integer num = (Integer) bVar.p("transactionId");
        Integer num2 = this.f202l;
        if (num2 == null) {
            runnable.run();
            return;
        }
        ArrayList arrayList = this.f196f;
        if (num == null || !(num.equals(num2) || num.intValue() == -1)) {
            arrayList.add(new K.d(runnable));
            return;
        }
        runnable.run();
        if (this.f202l != null || arrayList.isEmpty()) {
            return;
        }
        this.f198h.a(this, new e(0, this));
    }
}
