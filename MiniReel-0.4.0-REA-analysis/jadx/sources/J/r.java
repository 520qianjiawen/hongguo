package J;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class r implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g f242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Y.k f244f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ s f245g;

    public r(s sVar, g gVar, String str, Y.k kVar) {
        this.f245g = sVar;
        this.f242d = gVar;
        this.f243e = str;
        this.f244f = kVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (s.f249i) {
            g gVar = this.f242d;
            if (gVar != null) {
                s.a(this.f245g, gVar);
            }
            try {
                if (a.a(s.f250j)) {
                    Log.d("Sqflite", "delete database " + this.f243e);
                }
                SQLiteDatabase.deleteDatabase(new File(this.f243e));
            } catch (Exception e2) {
                Log.e("Sqflite", "error " + e2 + " while closing database " + s.f253n);
            }
        }
        this.f244f.d(null);
    }
}
