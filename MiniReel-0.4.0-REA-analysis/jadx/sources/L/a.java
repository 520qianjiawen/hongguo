package L;

import H.e;
import V.b;
import Y.k;
import Z.m;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import g0.d;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class a implements m, b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f265d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f266e;

    public static String b(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bArr);
        byte[] bArrDigest = messageDigest.digest();
        h.b(bArrDigest);
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArrDigest.length * 2];
        int length = bArrDigest.length;
        for (int i2 = 0; i2 < length; i2++) {
            byte b2 = bArrDigest[i2];
            int i3 = i2 * 2;
            cArr2[i3] = cArr[(b2 & 255) >>> 4];
            cArr2[i3 + 1] = cArr[b2 & 15];
        }
        return new String(cArr2);
    }

    public final String a(PackageManager packageManager) {
        try {
            if (Build.VERSION.SDK_INT < 28) {
                Context context = this.f265d;
                h.b(context);
                Signature[] signatureArr = packageManager.getPackageInfo(context.getPackageName(), 64).signatures;
                if (signatureArr != null && signatureArr.length != 0 && d.U(signatureArr) != null) {
                    byte[] byteArray = ((Signature) d.U(signatureArr)).toByteArray();
                    h.d(byteArray, "toByteArray(...)");
                    return b(byteArray);
                }
                return null;
            }
            Context context2 = this.f265d;
            h.b(context2);
            SigningInfo signingInfo = packageManager.getPackageInfo(context2.getPackageName(), 134217728).signingInfo;
            if (signingInfo == null) {
                return null;
            }
            if (signingInfo.hasMultipleSigners()) {
                Signature[] apkContentsSigners = signingInfo.getApkContentsSigners();
                h.d(apkContentsSigners, "getApkContentsSigners(...)");
                byte[] byteArray2 = ((Signature) d.U(apkContentsSigners)).toByteArray();
                h.d(byteArray2, "toByteArray(...)");
                return b(byteArray2);
            }
            Signature[] signingCertificateHistory = signingInfo.getSigningCertificateHistory();
            h.d(signingCertificateHistory, "getSigningCertificateHistory(...)");
            byte[] byteArray3 = ((Signature) d.U(signingCertificateHistory)).toByteArray();
            h.d(byteArray3, "toByteArray(...)");
            return b(byteArray3);
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException unused) {
            return null;
        }
    }

    @Override // Z.m
    public final void m(H.a aVar, k kVar) {
        String string;
        CharSequence charSequenceLoadLabel;
        h.e(aVar, "call");
        try {
            if (!h.a((String) aVar.f170e, "getAll")) {
                kVar.b();
                return;
            }
            Context context = this.f265d;
            h.b(context);
            PackageManager packageManager = context.getPackageManager();
            Context context2 = this.f265d;
            h.b(context2);
            PackageInfo packageInfo = packageManager.getPackageInfo(context2.getPackageName(), 0);
            String strA = a(packageManager);
            Context context3 = this.f265d;
            h.b(context3);
            PackageManager packageManager2 = context3.getPackageManager();
            Context context4 = this.f265d;
            h.b(context4);
            String packageName = context4.getPackageName();
            int i2 = Build.VERSION.SDK_INT;
            String initiatingPackageName = i2 >= 30 ? packageManager2.getInstallSourceInfo(packageName).getInitiatingPackageName() : packageManager2.getInstallerPackageName(packageName);
            long j2 = packageInfo.firstInstallTime;
            long j3 = packageInfo.lastUpdateTime;
            HashMap map = new HashMap();
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            String str = "";
            if (applicationInfo == null || (charSequenceLoadLabel = applicationInfo.loadLabel(packageManager)) == null || (string = charSequenceLoadLabel.toString()) == null) {
                string = "";
            }
            map.put("appName", string);
            Context context5 = this.f265d;
            h.b(context5);
            map.put("packageName", context5.getPackageName());
            String str2 = packageInfo.versionName;
            if (str2 != null) {
                str = str2;
            }
            map.put("version", str);
            map.put("buildNumber", String.valueOf(i2 >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode));
            if (strA != null) {
                map.put("buildSignature", strA);
            }
            if (initiatingPackageName != null) {
                map.put("installerStore", initiatingPackageName);
            }
            map.put("installTime", String.valueOf(j2));
            map.put("updateTime", String.valueOf(j3));
            kVar.d(map);
        } catch (PackageManager.NameNotFoundException e2) {
            kVar.a("Name not found", e2.getMessage(), null);
        }
    }

    @Override // V.b
    public final void onAttachedToEngine(V.a aVar) {
        h.e(aVar, "binding");
        this.f265d = aVar.f515a;
        e eVar = new e(aVar.f517c, "dev.fluttercommunity.plus/package_info");
        this.f266e = eVar;
        eVar.j(this);
    }

    @Override // V.b
    public final void onDetachedFromEngine(V.a aVar) {
        h.e(aVar, "binding");
        this.f265d = null;
        e eVar = this.f266e;
        h.b(eVar);
        eVar.j(null);
        this.f266e = null;
    }
}
