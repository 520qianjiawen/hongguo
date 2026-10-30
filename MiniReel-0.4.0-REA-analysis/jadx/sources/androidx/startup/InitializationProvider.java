package androidx.startup;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public class InitializationProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalStateException("Not allowed.");
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x003d */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onCreate() {
        /*
            r5 = this;
            android.content.Context r0 = r5.getContext()
            if (r0 == 0) goto L4c
            android.content.Context r1 = r0.getApplicationContext()
            if (r1 == 0) goto L4a
            p.a r0 = p015p.a.c(r0)
            android.content.Context r1 = r0.f1201c
            java.lang.String r2 = "Startup"
            java.lang.String r2 = p000a.a.Q(r2)     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            android.os.Trace.beginSection(r2)     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            android.content.ComponentName r2 = new android.content.ComponentName     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            java.lang.String r3 = r1.getPackageName()     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            java.lang.Class<androidx.startup.InitializationProvider> r4 = androidx.startup.InitializationProvider.class
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            r3 = 128(0x80, float:1.8E-43)
            android.content.pm.ProviderInfo r1 = r1.getProviderInfo(r2, r3)     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            android.os.Bundle r1 = r1.metaData     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            r0.a(r1)     // Catch: java.lang.Throwable -> L3d android.content.pm.PackageManager.NameNotFoundException -> L3f
            android.os.Trace.endSection()
            goto L4a
        L3d:
            r0 = move-exception
            goto L46
        L3f:
            r0 = move-exception
            H.c r1 = new H.c     // Catch: java.lang.Throwable -> L3d
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L3d
            throw r1     // Catch: java.lang.Throwable -> L3d
        L46:
            android.os.Trace.endSection()
            throw r0
        L4a:
            r0 = 1
            return r0
        L4c:
            H.c r0 = new H.c
            java.lang.String r1 = "Context cannot be null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.startup.InitializationProvider.onCreate():boolean");
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new IllegalStateException("Not allowed.");
    }
}
