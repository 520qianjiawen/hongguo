package io.flutter.embedding.engine.renderer;

import android.hardware.SyncFence;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.view.Surface;
import io.flutter.view.TextureRegistry$ImageConsumer;
import io.flutter.view.TextureRegistry$SurfaceProducer;
import io.flutter.view.x;
import io.flutter.view.y;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class FlutterRenderer$ImageReaderSurfaceProducer implements TextureRegistry$SurfaceProducer, TextureRegistry$ImageConsumer, x {
    private static final boolean CLEANUP_ON_MEMORY_PRESSURE = true;
    private static final int MAX_DEQUEUED_IMAGES = 2;
    private static final int MAX_IMAGES = 7;
    private static final String TAG = "ImageReaderSurfaceProducer";
    private static final boolean VERBOSE_LOGS = false;
    private static final boolean trimOnMemoryPressure = true;
    private final long id;
    private boolean released;
    final /* synthetic */ j this$0;
    private boolean ignoringFence = VERBOSE_LOGS;
    private int requestedWidth = 1;
    private int requestedHeight = 1;
    private boolean createNewReader = true;
    boolean notifiedDestroy = VERBOSE_LOGS;
    private long lastDequeueTime = 0;
    private long lastQueueTime = 0;
    private long lastScheduleTime = 0;
    private int numTrims = 0;
    private final Object lock = new Object();
    private final ArrayDeque<e> imageReaderQueue = new ArrayDeque<>();
    private final HashMap<ImageReader, e> perImageReaders = new HashMap<>();
    private ArrayList<c> lastDequeuedImage = new ArrayList<>();
    private e lastReaderDequeuedFrom = null;
    y callback = null;

    public FlutterRenderer$ImageReaderSurfaceProducer(j jVar, long j2) {
        this.this$0 = jVar;
        this.id = j2;
    }

    private void cleanup() {
        synchronized (this.lock) {
            try {
                for (e eVar : this.perImageReaders.values()) {
                    if (this.lastReaderDequeuedFrom == eVar) {
                        this.lastReaderDequeuedFrom = null;
                    }
                    eVar.f759c = true;
                    eVar.f757a.close();
                    eVar.f758b.clear();
                }
                this.perImageReaders.clear();
                if (this.lastDequeuedImage.size() > 0) {
                    ArrayList<c> arrayList = this.lastDequeuedImage;
                    int size = arrayList.size();
                    int i2 = 0;
                    while (i2 < size) {
                        c cVar = arrayList.get(i2);
                        i2++;
                        cVar.f754a.close();
                    }
                    this.lastDequeuedImage.clear();
                }
                e eVar2 = this.lastReaderDequeuedFrom;
                if (eVar2 != null) {
                    eVar2.f759c = true;
                    eVar2.f757a.close();
                    eVar2.f758b.clear();
                    this.lastReaderDequeuedFrom = null;
                }
                this.imageReaderQueue.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private ImageReader createImageReader29() {
        return ImageReader.newInstance(this.requestedWidth, this.requestedHeight, 34, 7, 256L);
    }

    private ImageReader createImageReader33() {
        J.b.i();
        ImageReader.Builder builderE = J.b.e(this.requestedWidth, this.requestedHeight);
        builderE.setMaxImages(7);
        builderE.setImageFormat(34);
        builderE.setUsage(256L);
        return builderE.build();
    }

    private e getActiveReader() {
        synchronized (this.lock) {
            try {
                if (!this.createNewReader) {
                    e eVarPeekLast = this.imageReaderQueue.peekLast();
                    if (eVarPeekLast.f757a.getSurface().isValid()) {
                        return eVarPeekLast;
                    }
                }
                this.createNewReader = VERBOSE_LOGS;
                return getOrCreatePerImageReader(createImageReader());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private e getOrCreatePerImageReader(ImageReader imageReader) {
        e eVar = this.perImageReaders.get(imageReader);
        if (eVar != null) {
            return eVar;
        }
        e eVarCreatePerImageReader = createPerImageReader(imageReader);
        this.perImageReaders.put(imageReader, eVarCreatePerImageReader);
        this.imageReaderQueue.add(eVarCreatePerImageReader);
        return eVarCreatePerImageReader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$dequeueImage$0() {
        if (this.released) {
            return;
        }
        this.this$0.f793a.scheduleFrame();
    }

    private void maybeWaitOnFence(Image image) {
        if (image == null || this.ignoringFence) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            waitOnFence(image);
        } else {
            this.ignoringFence = true;
        }
    }

    private void releaseInternal() {
        cleanup();
        this.released = true;
        this.this$0.f(this);
        this.this$0.f799g.remove(this);
    }

    @Override // io.flutter.view.TextureRegistry$ImageConsumer
    public Image acquireLatestImage() {
        c cVarDequeueImage = dequeueImage();
        if (cVarDequeueImage == null) {
            return null;
        }
        Image image = cVarDequeueImage.f754a;
        maybeWaitOnFence(image);
        return image;
    }

    public ImageReader createImageReader() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 33) {
            return createImageReader33();
        }
        if (i2 >= 29) {
            return createImageReader29();
        }
        throw new UnsupportedOperationException("ImageReaderPlatformViewRenderTarget requires API version 29+");
    }

    public e createPerImageReader(ImageReader imageReader) {
        return new e(this, imageReader);
    }

    public double deltaMillis(long j2) {
        return j2 / 1000000.0d;
    }

    public c dequeueImage() {
        c cVar;
        boolean z2;
        synchronized (this.lock) {
            try {
                Iterator<e> it = this.imageReaderQueue.iterator();
                cVar = null;
                while (true) {
                    boolean zHasNext = it.hasNext();
                    z2 = VERBOSE_LOGS;
                    if (!zHasNext) {
                        break;
                    }
                    e next = it.next();
                    ArrayDeque arrayDeque = next.f758b;
                    c cVar2 = arrayDeque.isEmpty() ? null : (c) arrayDeque.removeFirst();
                    if (cVar2 != null) {
                        while (this.lastDequeuedImage.size() > MAX_DEQUEUED_IMAGES) {
                            this.lastDequeuedImage.remove(0).f754a.close();
                        }
                        this.lastDequeuedImage.add(cVar2);
                        this.lastReaderDequeuedFrom = next;
                        cVar = cVar2;
                        break;
                    }
                    cVar = cVar2;
                }
                pruneImageReaderQueue();
                Iterator<e> it2 = this.imageReaderQueue.iterator();
                while (it2.hasNext()) {
                    if (!it2.next().f758b.isEmpty()) {
                        z2 = true;
                        break;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z2) {
            this.this$0.f797e.post(new b(this, 0));
        }
        return cVar;
    }

    public void disableFenceForTest() {
        this.ignoringFence = true;
    }

    public void finalize() throws Throwable {
        try {
            if (this.released) {
                return;
            }
            releaseInternal();
            j jVar = this.this$0;
            jVar.f797e.post(new h(this.id, jVar.f793a));
        } finally {
            super.finalize();
        }
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public Surface getForcedNewSurface() {
        this.createNewReader = true;
        return getSurface();
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public int getHeight() {
        return this.requestedHeight;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public Surface getSurface() {
        return getActiveReader().f757a.getSurface();
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public int getWidth() {
        return this.requestedWidth;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public boolean handlesCropAndRotation() {
        return VERBOSE_LOGS;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public long id() {
        return this.id;
    }

    public int numImageReaders() {
        int size;
        synchronized (this.lock) {
            size = this.imageReaderQueue.size();
        }
        return size;
    }

    public int numImages() {
        int size;
        synchronized (this.lock) {
            try {
                Iterator<e> it = this.imageReaderQueue.iterator();
                size = 0;
                while (it.hasNext()) {
                    size += it.next().f758b.size();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return size;
    }

    public int numTrims() {
        int i2;
        synchronized (this.lock) {
            i2 = this.numTrims;
        }
        return i2;
    }

    public void onImage(ImageReader imageReader, Image image) {
        c cVar;
        synchronized (this.lock) {
            e orCreatePerImageReader = getOrCreatePerImageReader(imageReader);
            ArrayDeque arrayDeque = orCreatePerImageReader.f758b;
            if (orCreatePerImageReader.f759c) {
                cVar = null;
            } else {
                FlutterRenderer$ImageReaderSurfaceProducer flutterRenderer$ImageReaderSurfaceProducer = orCreatePerImageReader.f760d;
                System.nanoTime();
                c cVar2 = new c(flutterRenderer$ImageReaderSurfaceProducer, image);
                arrayDeque.add(cVar2);
                while (arrayDeque.size() > MAX_DEQUEUED_IMAGES) {
                    ((c) arrayDeque.removeFirst()).f754a.close();
                }
                cVar = cVar2;
            }
        }
        if (cVar == null) {
            return;
        }
        this.this$0.f793a.scheduleFrame();
    }

    @Override // io.flutter.view.x
    public void onTrimMemory(int i2) {
        if (i2 < 40) {
            return;
        }
        synchronized (this.lock) {
            this.numTrims++;
        }
        y yVar = this.callback;
        if (yVar != null) {
            this.notifiedDestroy = true;
            ((G.e) yVar).c();
        }
        cleanup();
        this.createNewReader = true;
    }

    public int pendingDequeuedImages() {
        return this.lastDequeuedImage.size();
    }

    public void pruneImageReaderQueue() {
        e eVarPeekFirst;
        while (this.imageReaderQueue.size() > 1 && (eVarPeekFirst = this.imageReaderQueue.peekFirst()) != null) {
            ImageReader imageReader = eVarPeekFirst.f757a;
            ArrayDeque arrayDeque = eVarPeekFirst.f758b;
            if (!arrayDeque.isEmpty() || eVarPeekFirst.f760d.lastReaderDequeuedFrom == eVarPeekFirst) {
                return;
            }
            this.imageReaderQueue.removeFirst();
            this.perImageReaders.remove(imageReader);
            eVarPeekFirst.f759c = true;
            imageReader.close();
            arrayDeque.clear();
        }
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public void release() {
        if (this.released) {
            return;
        }
        releaseInternal();
        j jVar = this.this$0;
        jVar.f793a.unregisterTexture(this.id);
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public void scheduleFrame() {
        this.this$0.f793a.scheduleFrame();
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public void setCallback(y yVar) {
        this.callback = yVar;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public void setSize(int i2, int i3) {
        int iMax = Math.max(1, i2);
        int iMax2 = Math.max(1, i3);
        if (this.requestedWidth == iMax && this.requestedHeight == iMax2) {
            return;
        }
        this.createNewReader = true;
        this.requestedHeight = iMax2;
        this.requestedWidth = iMax;
    }

    public void waitOnFence(Image image) {
        try {
            SyncFence fence = image.getFence();
            try {
                fence.awaitForever();
                fence.close();
            } catch (Throwable th) {
                if (fence != null) {
                    try {
                        fence.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException unused) {
        }
    }
}
