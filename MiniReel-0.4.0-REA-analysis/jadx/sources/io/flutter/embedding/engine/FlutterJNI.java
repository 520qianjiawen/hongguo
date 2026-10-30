package io.flutter.embedding.engine;

import C0.x;
import G.f;
import O.AbstractC0003a;
import O.AbstractC0012j;
import O.C0013k;
import O.r;
import O.s;
import O.t;
import P.b;
import P.m;
import P.n;
import Q.j;
import R.a;
import S.d;
import Z.e;
import Z.p;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaExtractor;
import android.os.Build;
import android.os.LocaleList;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Choreographer;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import io.flutter.embedding.engine.mutatorsstack.FlutterMutatorsStack;
import io.flutter.embedding.engine.renderer.SurfaceTextureWrapper;
import io.flutter.embedding.engine.renderer.k;
import io.flutter.embedding.engine.renderer.l;
import io.flutter.plugin.platform.i;
import io.flutter.view.A;
import io.flutter.view.B;
import io.flutter.view.FlutterCallbackInformation;
import io.flutter.view.TextureRegistry$ImageConsumer;
import io.flutter.view.c;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import p013n.g;

/* JADX INFO: loaded from: classes.dex */
public class FlutterJNI {
    private static final String TAG = "FlutterJNI";
    private static n asyncWaitForVsyncDelegate = null;
    private static float displayDensity = -1.0f;
    private static float displayHeight = -1.0f;
    private static float displayWidth = -1.0f;
    private static boolean initCalled = false;
    private static boolean loadLibraryCalled = false;
    private static boolean prefetchDefaultFontManagerCalled = false;
    private static float refreshRateFPS = 60.0f;
    private static String vmServiceUri;
    private m accessibilityDelegate;
    private a deferredComponentManager;
    private p001a0.a localizationPlugin;
    private Long nativeShellHolderId;
    private j platformMessageHandler;
    private io.flutter.plugin.platform.j platformViewsController;
    private i platformViewsController2;
    private Y.n settingsChannel;
    private ReentrantReadWriteLock shellHolderLock = new ReentrantReadWriteLock();
    private final Set<b> engineLifecycleListeners = new CopyOnWriteArraySet();
    private final Set<k> flutterUiDisplayListeners = new CopyOnWriteArraySet();
    private final Set<l> flutterUiResizeListeners = new CopyOnWriteArraySet();
    private final Looper mainLooper = Looper.getMainLooper();

    private static void asyncWaitForVsync(long j2) {
        n nVar = asyncWaitForVsyncDelegate;
        if (nVar == null) {
            throw new IllegalStateException("An AsyncWaitForVsyncDelegate must be registered with FlutterJNI before asyncWaitForVsync() is invoked.");
        }
        c cVar = (c) nVar;
        cVar.getClass();
        Choreographer choreographer = Choreographer.getInstance();
        B b2 = (B) cVar.f931a;
        A a2 = b2.f928c;
        if (a2 != null) {
            a2.f922a = j2;
            b2.f928c = null;
        } else {
            a2 = new A(b2, j2);
        }
        choreographer.postFrameCallback(a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    public static Bitmap decodeImage(ByteBuffer byteBuffer, long j2) {
        S.b bVar;
        x xVar = 0;
        xVar = 0;
        if (Build.VERSION.SDK_INT < 28) {
            return null;
        }
        P.l lVar = new P.l(j2);
        d dVar = new d();
        int iRemaining = byteBuffer.remaining();
        byte[] bArr = new byte[iRemaining];
        byteBuffer.get(bArr);
        byteBuffer.rewind();
        int iE = 1;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, iRemaining, options);
            dVar.f499d = options.outMimeType;
            dVar.f501f = options.outHeight;
            dVar.f502g = options.outWidth;
        } catch (Exception e2) {
            Log.e("BitmapMetadataReader", "Failed to decode image for mime type", e2);
        }
        if ("image/heif".equals(dVar.f499d)) {
            try {
                S.c cVar = new S.c(bArr);
                MediaExtractor mediaExtractor = new MediaExtractor();
                mediaExtractor.setDataSource(cVar);
                p000a.a.I(dVar, mediaExtractor);
            } catch (Exception e3) {
                Log.e("MediaMetadataReader", "Failed to decode HEIF image using MediaExtractor", e3);
            }
            nativeImageHeaderCallback(lVar.f456a, dVar.f496a, dVar.f497b);
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    g gVar = new g(byteArrayInputStream);
                    p013n.c cVarC = gVar.c("Orientation");
                    if (cVarC != null) {
                        try {
                            iE = cVarC.e(gVar.f1154e);
                        } catch (NumberFormatException unused) {
                        }
                    }
                    dVar.f500e = iE;
                    byteArrayInputStream.close();
                } catch (Throwable th) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e4) {
                Log.e("ExifMetadataReader", "Failed to read EXIF metadata", e4);
            }
        }
        if ("image/heif".equals(dVar.f499d)) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 == 36) {
                bVar = new S.b(xVar, 0);
            } else if (i2 < 36) {
                bVar = new S.b(xVar, 1);
            }
            xVar = bVar;
        }
        if (xVar == 0) {
            xVar = new x(8, lVar);
        }
        return xVar.l(byteBuffer, dVar);
    }

    private void ensureAttachedToNative() {
        if (this.nativeShellHolderId == null) {
            throw new RuntimeException("Cannot execute operation because FlutterJNI is not attached to native.");
        }
    }

    private void ensureNotAttachedToNative() {
        if (this.nativeShellHolderId != null) {
            throw new RuntimeException("Cannot execute operation because FlutterJNI is attached to native.");
        }
    }

    private void ensureRunningOnMainThread() {
        if (Looper.myLooper() == this.mainLooper) {
            return;
        }
        throw new RuntimeException("Methods marked with @UiThread must be executed on the main thread. Current thread: " + Thread.currentThread().getName());
    }

    public static String getVMServiceUri() {
        return vmServiceUri;
    }

    private void handlePlatformMessageResponse(int i2, ByteBuffer byteBuffer) {
        e eVar;
        j jVar = this.platformMessageHandler;
        if (jVar == null || (eVar = (e) ((Q.i) jVar).f488i.remove(Integer.valueOf(i2))) == null) {
            return;
        }
        try {
            eVar.a(byteBuffer);
            if (byteBuffer == null || !byteBuffer.isDirect()) {
                return;
            }
            byteBuffer.limit(0);
        } catch (Error e2) {
            Thread threadCurrentThread = Thread.currentThread();
            if (threadCurrentThread.getUncaughtExceptionHandler() == null) {
                throw e2;
            }
            threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, e2);
        } catch (Exception e3) {
            Log.e("DartMessenger", "Uncaught exception in binary message reply handler", e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadLibrary$0(String str) {
    }

    private native long nativeAttach(FlutterJNI flutterJNI);

    private native void nativeCleanupMessageData(long j2);

    private native void nativeDeferredComponentInstallFailure(int i2, String str, boolean z2);

    private native void nativeDestroy(long j2);

    private native void nativeDispatchEmptyPlatformMessage(long j2, String str, int i2);

    private native void nativeDispatchPlatformMessage(long j2, String str, ByteBuffer byteBuffer, int i2, int i3);

    private native void nativeDispatchPointerDataPacket(long j2, ByteBuffer byteBuffer, int i2);

    private native void nativeDispatchSemanticsAction(long j2, int i2, int i3, ByteBuffer byteBuffer, int i4);

    private native boolean nativeFlutterTextUtilsIsEmoji(int i2);

    private native boolean nativeFlutterTextUtilsIsEmojiModifier(int i2);

    private native boolean nativeFlutterTextUtilsIsEmojiModifierBase(int i2);

    private native boolean nativeFlutterTextUtilsIsRegionalIndicator(int i2);

    private native boolean nativeFlutterTextUtilsIsVariationSelector(int i2);

    private native Bitmap nativeGetBitmap(long j2);

    private native boolean nativeGetIsSoftwareRenderingEnabled();

    public static native void nativeImageHeaderCallback(long j2, int i2, int i3);

    private static native void nativeInit(Context context, String[] strArr, String str, String str2, String str3, long j2, int i2);

    private native void nativeInvokePlatformMessageEmptyResponseCallback(long j2, int i2);

    private native void nativeInvokePlatformMessageResponseCallback(long j2, int i2, ByteBuffer byteBuffer, int i3);

    private native boolean nativeIsSurfaceControlEnabled(long j2);

    private native void nativeLoadDartDeferredLibrary(long j2, int i2, String[] strArr);

    @Deprecated
    public static native FlutterCallbackInformation nativeLookupCallbackInformation(long j2);

    private native void nativeMarkTextureFrameAvailable(long j2, long j3);

    private native void nativeNotifyLowMemoryWarning(long j2);

    private native void nativeOnVsync(long j2, long j3, long j4);

    private static native void nativePrefetchDefaultFontManager();

    private native void nativeRegisterImageTexture(long j2, long j3, WeakReference<TextureRegistry$ImageConsumer> weakReference, boolean z2);

    private native void nativeRegisterTexture(long j2, long j3, WeakReference<SurfaceTextureWrapper> weakReference);

    private native void nativeRunBundleAndSnapshotFromLibrary(long j2, String str, String str2, String str3, AssetManager assetManager, List<String> list, long j3);

    private native void nativeScheduleFrame(long j2);

    private native void nativeSetAccessibilityFeatures(long j2, int i2);

    private native void nativeSetSemanticsEnabled(long j2, boolean z2);

    private native void nativeSetViewportMetrics(long j2, float f2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int[] iArr, int[] iArr2, int[] iArr3, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24);

    private native FlutterJNI nativeSpawn(long j2, String str, String str2, String str3, List<String> list, long j3);

    private native void nativeSurfaceChanged(long j2, int i2, int i3);

    private native void nativeSurfaceCreated(long j2, Surface surface);

    private native void nativeSurfaceDestroyed(long j2);

    private native void nativeSurfaceWindowChanged(long j2, Surface surface);

    private native void nativeUnregisterTexture(long j2, long j3);

    private native void nativeUpdateDisplayMetrics(long j2);

    private native void nativeUpdateJavaAssetManager(long j2, AssetManager assetManager, String str);

    private native void nativeUpdateRefreshRate(float f2);

    private void onPreEngineRestart() {
        Iterator<b> it = this.engineLifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    private void setApplicationLocale(String str) {
        ensureRunningOnMainThread();
        m mVar = this.accessibilityDelegate;
        if (mVar != null) {
            ((io.flutter.view.n) ((c) mVar).f931a).f1034l = str;
        }
    }

    private void updateCustomAccessibilityActions(ByteBuffer byteBuffer, String[] strArr) {
        ensureRunningOnMainThread();
        m mVar = this.accessibilityDelegate;
        if (mVar != null) {
            c cVar = (c) mVar;
            cVar.getClass();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            io.flutter.view.n nVar = (io.flutter.view.n) cVar.f931a;
            nVar.getClass();
            while (byteBuffer.hasRemaining()) {
                io.flutter.view.j jVarA = nVar.a(byteBuffer.getInt());
                jVarA.f962c = byteBuffer.getInt();
                jVarA.f963d = io.flutter.view.n.c(byteBuffer, strArr);
                jVarA.f964e = io.flutter.view.n.c(byteBuffer, strArr);
            }
        }
    }

    private void updateSemantics(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
        ensureRunningOnMainThread();
        m mVar = this.accessibilityDelegate;
        if (mVar != null) {
            ((c) mVar).a(byteBuffer, strArr, byteBufferArr);
        }
    }

    public boolean IsSurfaceControlEnabled() {
        return nativeIsSurfaceControlEnabled(this.nativeShellHolderId.longValue());
    }

    public void addEngineLifecycleListener(b bVar) {
        ensureRunningOnMainThread();
        this.engineLifecycleListeners.add(bVar);
    }

    public void addIsDisplayingFlutterUiListener(k kVar) {
        ensureRunningOnMainThread();
        this.flutterUiDisplayListeners.add(kVar);
    }

    public void addResizingFlutterUiListener(l lVar) {
        ensureRunningOnMainThread();
        this.flutterUiResizeListeners.add(lVar);
    }

    public void applyTransactions() {
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("");
        }
        ArrayList arrayList = iVar.f888l;
        SurfaceControl.Transaction transactionH = AbstractC0012j.h();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            transactionH = transactionH.merge(AbstractC0012j.j(arrayList.get(i2)));
        }
        transactionH.apply();
        arrayList.clear();
    }

    public void attachToNative() {
        ensureRunningOnMainThread();
        ensureNotAttachedToNative();
        this.shellHolderLock.writeLock().lock();
        try {
            this.nativeShellHolderId = Long.valueOf(performNativeAttach(this));
        } finally {
            this.shellHolderLock.writeLock().unlock();
        }
    }

    public void cleanupMessageData(long j2) {
        nativeCleanupMessageData(j2);
    }

    public String[] computePlatformResolvedLocale(String[] strArr) {
        Locale locale;
        Locale localeLookup;
        if (this.localizationPlugin == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < strArr.length; i2 += 3) {
            String str = strArr[i2];
            String str2 = strArr[i2 + 1];
            String str3 = strArr[i2 + 2];
            Locale.Builder builder = new Locale.Builder();
            if (!str.isEmpty()) {
                builder.setLanguage(str);
            }
            if (!str2.isEmpty()) {
                builder.setRegion(str2);
            }
            if (!str3.isEmpty()) {
                builder.setScript(str3);
            }
            arrayList.add(builder.build());
        }
        Context context = this.localizationPlugin.f608b;
        if (arrayList.isEmpty()) {
            localeLookup = null;
        } else if (Build.VERSION.SDK_INT >= 26) {
            ArrayList arrayList2 = new ArrayList();
            LocaleList locales = context.getResources().getConfiguration().getLocales();
            int size = locales.size();
            for (int i3 = 0; i3 < size; i3++) {
                Locale locale2 = locales.get(i3);
                String language = locale2.getLanguage();
                if (!locale2.getScript().isEmpty()) {
                    language = language + "-" + locale2.getScript();
                }
                if (!locale2.getCountry().isEmpty()) {
                    language = language + "-" + locale2.getCountry();
                }
                arrayList2.add(AbstractC0003a.l(language));
                AbstractC0003a.n();
                arrayList2.add(AbstractC0003a.l(locale2.getLanguage()));
                AbstractC0003a.n();
                arrayList2.add(AbstractC0003a.l(locale2.getLanguage() + "-*"));
            }
            localeLookup = Locale.lookup(arrayList2, arrayList);
            if (localeLookup == null) {
                localeLookup = (Locale) arrayList.get(0);
            }
        } else {
            LocaleList locales2 = context.getResources().getConfiguration().getLocales();
            int i4 = 0;
            while (true) {
                if (i4 < locales2.size()) {
                    Locale locale3 = locales2.get(i4);
                    int size2 = arrayList.size();
                    int i5 = 0;
                    while (true) {
                        if (i5 < size2) {
                            Object obj = arrayList.get(i5);
                            i5++;
                            locale = (Locale) obj;
                            if (locale3.equals(locale)) {
                            }
                        } else {
                            int size3 = arrayList.size();
                            int i6 = 0;
                            while (true) {
                                if (i6 < size3) {
                                    Object obj2 = arrayList.get(i6);
                                    i6++;
                                    locale = (Locale) obj2;
                                    if (locale3.getLanguage().equals(locale.toLanguageTag())) {
                                    }
                                } else {
                                    int size4 = arrayList.size();
                                    int i7 = 0;
                                    while (true) {
                                        if (i7 < size4) {
                                            Object obj3 = arrayList.get(i7);
                                            i7++;
                                            locale = (Locale) obj3;
                                            if (locale3.getLanguage().equals(locale.getLanguage())) {
                                            }
                                        } else {
                                            i4++;
                                        }
                                    }
                                }
                            }
                        }
                        localeLookup = locale;
                    }
                } else {
                    localeLookup = (Locale) arrayList.get(0);
                }
            }
        }
        return localeLookup == null ? new String[0] : new String[]{localeLookup.getLanguage(), localeLookup.getCountry(), localeLookup.getScript()};
    }

    public FlutterOverlaySurface createOverlaySurface() {
        ensureRunningOnMainThread();
        io.flutter.plugin.platform.j jVar = this.platformViewsController;
        if (jVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to position an overlay surface");
        }
        Context context = jVar.f895f.getContext();
        int width = jVar.f895f.getWidth();
        int height = jVar.f895f.getHeight();
        io.flutter.plugin.platform.a aVar = jVar.f900k;
        io.flutter.plugin.platform.b bVar = new io.flutter.plugin.platform.b(context, width, height, 2);
        bVar.f869h = aVar;
        int i2 = jVar.f906r;
        jVar.f906r = i2 + 1;
        jVar.f904p.put(i2, bVar);
        return new FlutterOverlaySurface(i2, bVar.getSurface());
    }

    public FlutterOverlaySurface createOverlaySurface2() {
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to position an overlay surface");
        }
        if (iVar.f889n == null) {
            SurfaceControl.Builder builderG = AbstractC0012j.g();
            builderG.setBufferSize(iVar.f882f.getWidth(), iVar.f882f.getHeight());
            builderG.setFormat(1);
            builderG.setName("Flutter Overlay Surface");
            builderG.setOpaque(false);
            builderG.setHidden(false);
            SurfaceControl surfaceControlBuild = builderG.build();
            SurfaceControl.Transaction transactionBuildReparentTransaction = iVar.f882f.getRootSurfaceControl().buildReparentTransaction(surfaceControlBuild);
            transactionBuildReparentTransaction.setLayer(surfaceControlBuild, 1000);
            transactionBuildReparentTransaction.apply();
            iVar.f889n = AbstractC0012j.f(surfaceControlBuild);
            iVar.f890o = surfaceControlBuild;
        }
        return new FlutterOverlaySurface(0, iVar.f889n);
    }

    public SurfaceControl.Transaction createTransaction() {
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("");
        }
        SurfaceControl.Transaction transactionH = AbstractC0012j.h();
        iVar.f888l.add(transactionH);
        return transactionH;
    }

    public void deferredComponentInstallFailure(int i2, String str, boolean z2) {
        ensureRunningOnMainThread();
        nativeDeferredComponentInstallFailure(i2, str, z2);
    }

    public void destroyOverlaySurface2() {
        ensureRunningOnMainThread();
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to destroy an overlay surface");
        }
        Surface surface = iVar.f889n;
        if (surface != null) {
            surface.release();
            iVar.f889n = null;
            iVar.f890o = null;
        }
    }

    public void destroyOverlaySurfaces() {
        ensureRunningOnMainThread();
        io.flutter.plugin.platform.j jVar = this.platformViewsController;
        if (jVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to destroy an overlay surface");
        }
        jVar.c();
    }

    public void detachFromNativeAndReleaseResources() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        this.shellHolderLock.writeLock().lock();
        try {
            nativeDestroy(this.nativeShellHolderId.longValue());
            this.nativeShellHolderId = null;
        } finally {
            this.shellHolderLock.writeLock().unlock();
        }
    }

    public void dispatchEmptyPlatformMessage(String str, int i2) {
        ensureRunningOnMainThread();
        if (isAttached()) {
            nativeDispatchEmptyPlatformMessage(this.nativeShellHolderId.longValue(), str, i2);
            return;
        }
        Log.w(TAG, "Tried to send a platform message to Flutter, but FlutterJNI was detached from native C++. Could not send. Channel: " + str + ". Response ID: " + i2);
    }

    public void dispatchPlatformMessage(String str, ByteBuffer byteBuffer, int i2, int i3) {
        ensureRunningOnMainThread();
        if (isAttached()) {
            nativeDispatchPlatformMessage(this.nativeShellHolderId.longValue(), str, byteBuffer, i2, i3);
            return;
        }
        Log.w(TAG, "Tried to send a platform message to Flutter, but FlutterJNI was detached from native C++. Could not send. Channel: " + str + ". Response ID: " + i3);
    }

    public void dispatchPointerDataPacket(ByteBuffer byteBuffer, int i2) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeDispatchPointerDataPacket(this.nativeShellHolderId.longValue(), byteBuffer, i2);
    }

    public void dispatchSemanticsAction(int i2, io.flutter.view.g gVar) {
        dispatchSemanticsAction(i2, gVar, null);
    }

    public void endFrame2() {
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("");
        }
        ArrayList arrayList = iVar.m;
        SurfaceControl.Transaction transactionH = AbstractC0012j.h();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            transactionH = transactionH.merge(AbstractC0012j.j(arrayList.get(i2)));
        }
        arrayList.clear();
        iVar.f882f.invalidate();
        iVar.f882f.getRootSurfaceControl().applyTransactionOnDraw(transactionH);
    }

    public Bitmap getBitmap() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        return nativeGetBitmap(this.nativeShellHolderId.longValue());
    }

    public boolean getIsSoftwareRenderingEnabled() {
        return nativeGetIsSoftwareRenderingEnabled();
    }

    /* JADX WARN: Code duplicated, block: B:22:0x006e  */
    public float getScaledFontSize(float f2, int i2) {
        Y.m mVar;
        Y.n nVar = this.settingsChannel;
        DisplayMetrics displayMetrics = null;
        if (nVar != null) {
            f fVar = nVar.f564a;
            ConcurrentLinkedQueue concurrentLinkedQueue = (ConcurrentLinkedQueue) fVar.f164d;
            if (((Y.m) fVar.f165e) == null) {
                fVar.f165e = (Y.m) concurrentLinkedQueue.poll();
            }
            while (true) {
                mVar = (Y.m) fVar.f165e;
                if (mVar == null || mVar.f562a >= i2) {
                    break;
                }
                fVar.f165e = (Y.m) concurrentLinkedQueue.poll();
            }
            if (mVar == null) {
                Log.e("SettingsChannel", "Cannot find config with generation: " + i2 + ", after exhausting the queue.");
            } else if (mVar.f562a != i2) {
                Log.e("SettingsChannel", "Cannot find config with generation: " + i2 + ", the oldest config is now: " + ((Y.m) fVar.f165e).f562a);
            } else if (mVar != null) {
                displayMetrics = mVar.f563b;
            }
            mVar = null;
            if (mVar != null) {
                displayMetrics = mVar.f563b;
            }
        }
        if (displayMetrics != null) {
            return TypedValue.applyDimension(2, f2, displayMetrics) / displayMetrics.density;
        }
        Log.e(TAG, "getScaledFontSize called with configurationId " + i2 + ", which can't be found.");
        return -1.0f;
    }

    public void handlePlatformMessage(String str, ByteBuffer byteBuffer, int i2, long j2) {
        Q.f fVar;
        boolean z2;
        j jVar = this.platformMessageHandler;
        if (jVar == null) {
            nativeCleanupMessageData(j2);
            return;
        }
        Q.i iVar = (Q.i) jVar;
        synchronized (iVar.f486g) {
            try {
                fVar = (Q.f) iVar.f484e.get(str);
                z2 = iVar.f487h.get() && fVar == null;
                if (z2) {
                    if (!iVar.f485f.containsKey(str)) {
                        iVar.f485f.put(str, new LinkedList());
                    }
                    ((List) iVar.f485f.get(str)).add(new Q.d(j2, byteBuffer, i2));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z2) {
            return;
        }
        iVar.d(str, fVar, byteBuffer, i2, j2);
    }

    public void hideOverlaySurface2() {
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to destroy an overlay surface");
        }
        if (iVar.f890o == null) {
            return;
        }
        SurfaceControl.Transaction transactionH = AbstractC0012j.h();
        iVar.f888l.add(transactionH);
        transactionH.setVisibility(iVar.f890o, false);
    }

    public void hidePlatformView2(int i2) {
        ensureRunningOnMainThread();
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to hide a platform view");
        }
        if (iVar.f886j.get(i2) != null) {
            throw new ClassCastException();
        }
    }

    public void init(Context context, String[] strArr, String str, String str2, String str3, long j2, int i2) {
        if (initCalled) {
            Log.w(TAG, "FlutterJNI.init called more than once");
        }
        nativeInit(context, strArr, str, str2, str3, j2, i2);
        initCalled = true;
    }

    public void invokePlatformMessageEmptyResponseCallback(int i2) {
        this.shellHolderLock.readLock().lock();
        try {
            if (isAttached()) {
                nativeInvokePlatformMessageEmptyResponseCallback(this.nativeShellHolderId.longValue(), i2);
            } else {
                Log.w(TAG, "Tried to send a platform message response, but FlutterJNI was detached from native C++. Could not send. Response ID: " + i2);
            }
        } finally {
            this.shellHolderLock.readLock().unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v9, types: [io.flutter.embedding.engine.FlutterJNI] */
    public void invokePlatformMessageResponseCallback(int i2, ByteBuffer byteBuffer, int i3) throws Throwable {
        FlutterJNI flutterJNIIsAttached;
        if (!byteBuffer.isDirect()) {
            throw new IllegalArgumentException("Expected a direct ByteBuffer.");
        }
        this.shellHolderLock.readLock().lock();
        try {
            flutterJNIIsAttached = isAttached();
            try {
                if (flutterJNIIsAttached != 0) {
                    FlutterJNI flutterJNI = this;
                    flutterJNI.nativeInvokePlatformMessageResponseCallback(this.nativeShellHolderId.longValue(), i2, byteBuffer, i3);
                    flutterJNIIsAttached = flutterJNI;
                } else {
                    flutterJNIIsAttached = this;
                    Log.w(TAG, "Tried to send a platform message response, but FlutterJNI was detached from native C++. Could not send. Response ID: " + i2);
                }
                flutterJNIIsAttached.shellHolderLock.readLock().unlock();
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                flutterJNIIsAttached.shellHolderLock.readLock().unlock();
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            flutterJNIIsAttached = this;
        }
    }

    public boolean isAttached() {
        return this.nativeShellHolderId != null;
    }

    public boolean isCodePointEmoji(int i2) {
        return nativeFlutterTextUtilsIsEmoji(i2);
    }

    public boolean isCodePointEmojiModifier(int i2) {
        return nativeFlutterTextUtilsIsEmojiModifier(i2);
    }

    public boolean isCodePointEmojiModifierBase(int i2) {
        return nativeFlutterTextUtilsIsEmojiModifierBase(i2);
    }

    public boolean isCodePointRegionalIndicator(int i2) {
        return nativeFlutterTextUtilsIsRegionalIndicator(i2);
    }

    public boolean isCodePointVariantSelector(int i2) {
        return nativeFlutterTextUtilsIsVariationSelector(i2);
    }

    public void loadDartDeferredLibrary(int i2, String[] strArr) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeLoadDartDeferredLibrary(this.nativeShellHolderId.longValue(), i2, strArr);
    }

    public void loadLibrary(Context context) throws Throwable {
        String[] strArrE;
        InputStream inputStream;
        InputStream inputStream2;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        if (loadLibraryCalled) {
            Log.w(TAG, "FlutterJNI.loadLibrary called more than once");
        }
        C.a aVar = new C.a();
        H.b bVar = new H.b(1);
        H.b bVar2 = new H.b(0);
        H.e eVar = new H.e();
        eVar.f174d = new HashSet();
        eVar.f175e = bVar;
        eVar.f176f = bVar2;
        eVar.f177g = aVar;
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        }
        eVar.i("Beginning load of %s...", "flutter");
        H.b bVar3 = (H.b) eVar.f175e;
        HashSet hashSet = (HashSet) eVar.f174d;
        if (hashSet.contains("flutter")) {
            eVar.i("%s already loaded previously!", "flutter");
        } else {
            H.a aVar2 = null;
            try {
                bVar3.getClass();
                System.loadLibrary("flutter");
                hashSet.add("flutter");
                eVar.i("%s (%s) was loaded normally!", "flutter", null);
            } catch (UnsatisfiedLinkError e2) {
                eVar.i("Loading the library normally failed: %s", Log.getStackTraceString(e2));
                eVar.i("%s (%s) was not loaded normally, re-linking...", "flutter", null);
                File fileG = eVar.g(context);
                if (!fileG.exists()) {
                    File dir = context.getDir("lib", 0);
                    File fileG2 = eVar.g(context);
                    bVar3.getClass();
                    File[] fileArrListFiles = dir.listFiles(new H.d(System.mapLibraryName("flutter")));
                    if (fileArrListFiles != null) {
                        for (File file : fileArrListFiles) {
                            if (!file.getAbsolutePath().equals(fileG2.getAbsolutePath())) {
                                file.delete();
                            }
                        }
                    }
                    H.b bVar4 = (H.b) eVar.f176f;
                    String[] strArr = Build.SUPPORTED_ABIS;
                    if (strArr.length <= 0) {
                        String str = Build.CPU_ABI2;
                        strArr = (str == null || str.length() == 0) ? new String[]{Build.CPU_ABI} : new String[]{Build.CPU_ABI, str};
                    }
                    String strMapLibraryName = System.mapLibraryName("flutter");
                    bVar4.getClass();
                    try {
                        H.a aVarC = H.b.c(context, strArr, strMapLibraryName, eVar);
                        try {
                            if (aVarC == null) {
                                try {
                                    strArrE = H.b.e(context, strMapLibraryName);
                                } catch (Exception e3) {
                                    strArrE = new String[]{e3.toString()};
                                }
                                throw new H.c(strMapLibraryName, strArr, strArrE);
                            }
                            ZipFile zipFile = (ZipFile) aVarC.f170e;
                            int i2 = 0;
                            while (true) {
                                int i3 = i2 + 1;
                                if (i2 >= 5) {
                                    if (((C.a) eVar.f177g) == null) {
                                        break;
                                    }
                                    lambda$loadLibrary$0("FATAL! Couldn't extract the library from the APK!");
                                    break;
                                }
                                eVar.i("Found %s! Extracting...", strMapLibraryName);
                                try {
                                    if (fileG.exists() || fileG.createNewFile()) {
                                        try {
                                            inputStream2 = zipFile.getInputStream((ZipEntry) aVarC.f171f);
                                            try {
                                                fileOutputStream2 = new FileOutputStream(fileG);
                                                try {
                                                    byte[] bArr = new byte[4096];
                                                    long j2 = 0;
                                                    while (true) {
                                                        int i4 = inputStream2.read(bArr);
                                                        if (i4 == -1) {
                                                            break;
                                                        }
                                                        fileOutputStream2.write(bArr, 0, i4);
                                                        j2 += (long) i4;
                                                    }
                                                    fileOutputStream2.flush();
                                                    fileOutputStream2.getFD().sync();
                                                    if (j2 == fileG.length()) {
                                                        H.b.a(inputStream2);
                                                        H.b.a(fileOutputStream2);
                                                        fileG.setReadable(true, false);
                                                        fileG.setExecutable(true, false);
                                                        fileG.setWritable(true);
                                                        break;
                                                    }
                                                    H.b.a(inputStream2);
                                                    H.b.a(fileOutputStream2);
                                                } catch (FileNotFoundException unused) {
                                                    H.b.a(inputStream2);
                                                    H.b.a(fileOutputStream2);
                                                    i2 = i3;
                                                } catch (IOException unused2) {
                                                    H.b.a(inputStream2);
                                                    H.b.a(fileOutputStream2);
                                                    i2 = i3;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    inputStream = inputStream2;
                                                    fileOutputStream = fileOutputStream2;
                                                    H.b.a(inputStream);
                                                    H.b.a(fileOutputStream);
                                                    throw th;
                                                }
                                            } catch (FileNotFoundException unused3) {
                                                fileOutputStream2 = null;
                                                H.b.a(inputStream2);
                                                H.b.a(fileOutputStream2);
                                                i2 = i3;
                                            } catch (IOException unused4) {
                                                fileOutputStream2 = null;
                                                H.b.a(inputStream2);
                                                H.b.a(fileOutputStream2);
                                                i2 = i3;
                                            } catch (Throwable th2) {
                                                th = th2;
                                                inputStream = inputStream2;
                                                fileOutputStream = null;
                                                H.b.a(inputStream);
                                                H.b.a(fileOutputStream);
                                                throw th;
                                            }
                                        } catch (FileNotFoundException unused5) {
                                            inputStream2 = null;
                                        } catch (IOException unused6) {
                                            inputStream2 = null;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            inputStream = null;
                                        }
                                    }
                                } catch (IOException unused7) {
                                }
                                i2 = i3;
                            }
                            try {
                                zipFile.close();
                            } catch (IOException unused8) {
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            aVar2 = aVarC;
                            if (aVar2 != null) {
                                try {
                                    ((ZipFile) aVar2.f170e).close();
                                } catch (IOException unused9) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                }
                String absolutePath = fileG.getAbsolutePath();
                bVar3.getClass();
                System.load(absolutePath);
                hashSet.add("flutter");
                eVar.i("%s (%s) was re-linked!", "flutter", null);
            }
        }
        loadLibraryCalled = true;
    }

    public void markTextureFrameAvailable(long j2) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeMarkTextureFrameAvailable(this.nativeShellHolderId.longValue(), j2);
    }

    public void maybeResizeSurfaceView(int i2, int i3) {
        boolean z2;
        Iterator<l> it = this.flutterUiResizeListeners.iterator();
        while (it.hasNext()) {
            View view = ((r) it.next()).f347a.f356e;
            if (view != null) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                boolean z3 = true;
                if (view.getHeight() != i3) {
                    layoutParams.height = i3;
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (view.getWidth() != i2) {
                    layoutParams.width = i2;
                } else {
                    z3 = z2;
                }
                if (z3) {
                    view.setLayoutParams(layoutParams);
                }
            } else {
                Log.e("FlutterView", "Flutter engine view not set.");
            }
        }
    }

    public void notifyLowMemoryWarning() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeNotifyLowMemoryWarning(this.nativeShellHolderId.longValue());
    }

    public void onBeginFrame() {
        ensureRunningOnMainThread();
        io.flutter.plugin.platform.j jVar = this.platformViewsController;
        if (jVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to begin the frame");
        }
        jVar.f909u.clear();
        jVar.v.clear();
    }

    public void onDisplayOverlaySurface(int i2, int i3, int i4, int i5, int i6) {
        ensureRunningOnMainThread();
        io.flutter.plugin.platform.j jVar = this.platformViewsController;
        if (jVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to position an overlay surface");
        }
        SparseArray sparseArray = jVar.f904p;
        if (sparseArray.get(i2) == null) {
            throw new IllegalStateException("The overlay surface (id:" + i2 + ") doesn't exist");
        }
        jVar.f();
        View view = (io.flutter.plugin.platform.b) sparseArray.get(i2);
        if (view.getParent() == null) {
            jVar.f895f.addView(view);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i5, i6);
        layoutParams.leftMargin = i3;
        layoutParams.topMargin = i4;
        view.setLayoutParams(layoutParams);
        view.setVisibility(0);
        view.bringToFront();
        jVar.f909u.add(Integer.valueOf(i2));
    }

    public void onDisplayPlatformView(int i2, int i3, int i4, int i5, int i6, int i7, int i8, FlutterMutatorsStack flutterMutatorsStack) {
        ensureRunningOnMainThread();
        io.flutter.plugin.platform.j jVar = this.platformViewsController;
        if (jVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to position a platform view");
        }
        jVar.f();
        if (jVar.f902n.get(i2) != null) {
            throw new ClassCastException();
        }
    }

    public void onDisplayPlatformView2(int i2, int i3, int i4, int i5, int i6, int i7, int i8, FlutterMutatorsStack flutterMutatorsStack) {
        ensureRunningOnMainThread();
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to position a platform view");
        }
        if (iVar.f886j.get(i2) != null) {
            throw new ClassCastException();
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.View, io.flutter.embedding.engine.renderer.m] */
    public void onEndFrame() {
        ?? r3;
        ensureRunningOnMainThread();
        io.flutter.plugin.platform.j jVar = this.platformViewsController;
        if (jVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to end the frame");
        }
        boolean z2 = false;
        if (!jVar.f907s || !jVar.v.isEmpty()) {
            if (jVar.f907s) {
                C0013k c0013k = jVar.f895f.f355d;
                if (c0013k != null ? c0013k.e() : false) {
                    z2 = true;
                }
            }
            jVar.e(z2);
            return;
        }
        jVar.f907s = false;
        t tVar = jVar.f895f;
        J.e eVar = new J.e(4, jVar);
        C0013k c0013k2 = tVar.f355d;
        if (c0013k2 == null || (r3 = tVar.f357f) == 0) {
            return;
        }
        tVar.f356e = r3;
        tVar.f357f = null;
        io.flutter.embedding.engine.renderer.j jVar2 = tVar.f360i.f393a;
        if (jVar2 != null) {
            r3.d();
            jVar2.a(new s(tVar, jVar2, eVar));
            return;
        }
        c0013k2.c();
        C0013k c0013k3 = tVar.f355d;
        if (c0013k3 != null) {
            c0013k3.f326a.close();
            tVar.removeView(tVar.f355d);
            tVar.f355d = null;
        }
        eVar.run();
    }

    public void onFirstFrame() {
        ensureRunningOnMainThread();
        Iterator<k> it = this.flutterUiDisplayListeners.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
    }

    public void onRenderingStopped() {
        ensureRunningOnMainThread();
        Iterator<k> it = this.flutterUiDisplayListeners.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public void onSurfaceChanged(int i2, int i3) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSurfaceChanged(this.nativeShellHolderId.longValue(), i2, i3);
    }

    public void onSurfaceCreated(Surface surface) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSurfaceCreated(this.nativeShellHolderId.longValue(), surface);
    }

    public void onSurfaceDestroyed() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        onRenderingStopped();
        nativeSurfaceDestroyed(this.nativeShellHolderId.longValue());
    }

    public void onSurfaceWindowChanged(Surface surface) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSurfaceWindowChanged(this.nativeShellHolderId.longValue(), surface);
    }

    public void onVsync(long j2, long j3, long j4) {
        nativeOnVsync(j2, j3, j4);
    }

    public long performNativeAttach(FlutterJNI flutterJNI) {
        return nativeAttach(flutterJNI);
    }

    public void prefetchDefaultFontManager() {
        if (prefetchDefaultFontManagerCalled) {
            Log.w(TAG, "FlutterJNI.prefetchDefaultFontManager called more than once");
        }
        nativePrefetchDefaultFontManager();
        prefetchDefaultFontManagerCalled = true;
    }

    public void registerImageTexture(long j2, TextureRegistry$ImageConsumer textureRegistry$ImageConsumer, boolean z2) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeRegisterImageTexture(this.nativeShellHolderId.longValue(), j2, new WeakReference<>(textureRegistry$ImageConsumer), z2);
    }

    public void registerTexture(long j2, SurfaceTextureWrapper surfaceTextureWrapper) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeRegisterTexture(this.nativeShellHolderId.longValue(), j2, new WeakReference<>(surfaceTextureWrapper));
    }

    public void removeEngineLifecycleListener(b bVar) {
        ensureRunningOnMainThread();
        this.engineLifecycleListeners.remove(bVar);
    }

    public void removeIsDisplayingFlutterUiListener(k kVar) {
        ensureRunningOnMainThread();
        this.flutterUiDisplayListeners.remove(kVar);
    }

    public void removeResizingFlutterUiListener(l lVar) {
        ensureRunningOnMainThread();
        this.flutterUiResizeListeners.remove(lVar);
    }

    public void requestDartDeferredLibrary(int i2) {
        Log.e(TAG, "No DeferredComponentManager found. Android setup must be completed before using split AOT deferred components.");
    }

    public void runBundleAndSnapshotFromLibrary(String str, String str2, String str3, AssetManager assetManager, List<String> list, long j2) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeRunBundleAndSnapshotFromLibrary(this.nativeShellHolderId.longValue(), str, str2, str3, assetManager, list, j2);
    }

    public void scheduleFrame() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeScheduleFrame(this.nativeShellHolderId.longValue());
    }

    public void setAccessibilityDelegate(m mVar) {
        ensureRunningOnMainThread();
        this.accessibilityDelegate = mVar;
    }

    public void setAccessibilityFeatures(int i2) {
        ensureRunningOnMainThread();
        if (isAttached()) {
            setAccessibilityFeaturesInNative(i2);
        }
    }

    public void setAccessibilityFeaturesInNative(int i2) {
        nativeSetAccessibilityFeatures(this.nativeShellHolderId.longValue(), i2);
    }

    public void setAsyncWaitForVsyncDelegate(n nVar) {
        asyncWaitForVsyncDelegate = nVar;
    }

    public void setDeferredComponentManager(a aVar) {
        ensureRunningOnMainThread();
        if (aVar != null) {
            aVar.a();
        }
    }

    public void setLocalizationPlugin(p001a0.a aVar) {
        ensureRunningOnMainThread();
        this.localizationPlugin = aVar;
    }

    public void setPlatformMessageHandler(j jVar) {
        ensureRunningOnMainThread();
        this.platformMessageHandler = jVar;
    }

    public void setPlatformViewsController(io.flutter.plugin.platform.j jVar) {
        ensureRunningOnMainThread();
        this.platformViewsController = jVar;
    }

    public void setPlatformViewsController2(i iVar) {
        ensureRunningOnMainThread();
        this.platformViewsController2 = iVar;
    }

    public void setRefreshRateFPS(float f2) {
        refreshRateFPS = f2;
        updateRefreshRate();
    }

    public void setSemanticsEnabled(boolean z2) {
        ensureRunningOnMainThread();
        if (isAttached()) {
            setSemanticsEnabledInNative(z2);
        }
    }

    public void setSemanticsEnabledInNative(boolean z2) {
        nativeSetSemanticsEnabled(this.nativeShellHolderId.longValue(), z2);
    }

    public void setSemanticsTreeEnabled(boolean z2) {
        ensureRunningOnMainThread();
        m mVar = this.accessibilityDelegate;
        if (mVar == null || z2) {
            return;
        }
        io.flutter.view.n nVar = (io.flutter.view.n) ((c) mVar).f931a;
        nVar.f1029g.clear();
        io.flutter.view.m mVar2 = nVar.f1031i;
        if (mVar2 != null) {
            nVar.g(mVar2.f994b, 65536);
        }
        nVar.f1031i = null;
        nVar.f1036o = null;
        nVar.i(0, 1);
    }

    public void setSettingsChannel(Y.n nVar) {
        ensureRunningOnMainThread();
        this.settingsChannel = nVar;
    }

    public void setViewportMetrics(float f2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int[] iArr, int[] iArr2, int[] iArr3, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSetViewportMetrics(this.nativeShellHolderId.longValue(), f2, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, iArr, iArr2, iArr3, i17, i18, i19, i20, i21, i22, i23, i24);
    }

    public void showOverlaySurface2() {
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to destroy an overlay surface");
        }
        if (iVar.f890o == null) {
            return;
        }
        SurfaceControl.Transaction transactionH = AbstractC0012j.h();
        iVar.f888l.add(transactionH);
        transactionH.setVisibility(iVar.f890o, true);
    }

    public FlutterJNI spawn(String str, String str2, String str3, List<String> list, long j2) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        FlutterJNI flutterJNINativeSpawn = nativeSpawn(this.nativeShellHolderId.longValue(), str, str2, str3, list, j2);
        Long l2 = flutterJNINativeSpawn.nativeShellHolderId;
        if ((l2 == null || l2.longValue() == 0) ? false : true) {
            return flutterJNINativeSpawn;
        }
        throw new IllegalStateException("Failed to spawn new JNI connected shell from existing shell.");
    }

    public void swapTransactions() {
        i iVar = this.platformViewsController2;
        if (iVar == null) {
            throw new RuntimeException("");
        }
        synchronized (iVar) {
            iVar.m.clear();
            iVar.m.addAll(iVar.f888l);
            iVar.f888l.clear();
        }
    }

    public void unregisterTexture(long j2) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeUnregisterTexture(this.nativeShellHolderId.longValue(), j2);
    }

    public void updateDisplayMetrics(int i2, float f2, float f3, float f4) {
        displayWidth = f2;
        displayHeight = f3;
        displayDensity = f4;
        if (loadLibraryCalled) {
            nativeUpdateDisplayMetrics(this.nativeShellHolderId.longValue());
        }
    }

    public void updateJavaAssetManager(AssetManager assetManager, String str) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeUpdateJavaAssetManager(this.nativeShellHolderId.longValue(), assetManager, str);
    }

    public void updateRefreshRate() {
        if (loadLibraryCalled) {
            nativeUpdateRefreshRate(refreshRateFPS);
        }
    }

    public void dispatchSemanticsAction(int i2, io.flutter.view.g gVar, Object obj) {
        ByteBuffer byteBufferB;
        int iPosition;
        ensureAttachedToNative();
        if (obj != null) {
            byteBufferB = p.f595a.b(obj);
            iPosition = byteBufferB.position();
        } else {
            byteBufferB = null;
            iPosition = 0;
        }
        dispatchSemanticsAction(i2, gVar.f959d, byteBufferB, iPosition);
    }

    public void dispatchSemanticsAction(int i2, int i3, ByteBuffer byteBuffer, int i4) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeDispatchSemanticsAction(this.nativeShellHolderId.longValue(), i2, i3, byteBuffer, i4);
    }
}
