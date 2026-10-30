package O;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import java.nio.ByteBuffer;
import java.util.Locale;

/* JADX INFO: renamed from: O.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0013k extends View implements io.flutter.embedding.engine.renderer.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ImageReader f326a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Image f327b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Bitmap f328c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public io.flutter.embedding.engine.renderer.j f329d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f330e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f331f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f332g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0013k(Context context, int i2, int i3, int i4) {
        super(context, null);
        ImageReader imageReaderF = f(i2, i3);
        this.f330e = false;
        this.f332g = false;
        this.f326a = imageReaderF;
        this.f331f = i4;
        setAlpha(0.0f);
        this.f330e = p000a.a.z(getContext());
    }

    public static ImageReader f(int i2, int i3) {
        if (i2 <= 0) {
            Locale locale = Locale.US;
            Log.w("FlutterImageView", "ImageReader width must be greater than 0, but given width=" + i2 + ", set width=1");
            i2 = 1;
        }
        if (i3 <= 0) {
            Locale locale2 = Locale.US;
            Log.w("FlutterImageView", "ImageReader height must be greater than 0, but given height=" + i3 + ", set height=1");
            i3 = 1;
        }
        return Build.VERSION.SDK_INT >= 29 ? ImageReader.newInstance(i2, i3, 1, 3, 768L) : ImageReader.newInstance(i2, i3, 1, 3);
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void a(io.flutter.embedding.engine.renderer.j jVar) {
        if (p014o.c.a(this.f331f) == 0) {
            Surface surface = this.f326a.getSurface();
            jVar.f795c = surface;
            jVar.f793a.onSurfaceWindowChanged(surface);
        }
        setAlpha(1.0f);
        this.f329d = jVar;
        this.f332g = true;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void c() {
        if (this.f332g) {
            setAlpha(0.0f);
            e();
            this.f328c = null;
            Image image = this.f327b;
            if (image != null) {
                image.close();
                this.f327b = null;
            }
            invalidate();
            this.f332g = false;
        }
    }

    public final boolean e() {
        if (!this.f332g) {
            return false;
        }
        Image imageAcquireLatestImage = this.f326a.acquireLatestImage();
        if (imageAcquireLatestImage != null) {
            Image image = this.f327b;
            if (image != null) {
                image.close();
                this.f327b = null;
            }
            this.f327b = imageAcquireLatestImage;
            invalidate();
        }
        return imageAcquireLatestImage != null;
    }

    public final void g(int i2, int i3) {
        if (this.f329d == null) {
            return;
        }
        if (i2 == this.f326a.getWidth() && i3 == this.f326a.getHeight()) {
            return;
        }
        Image image = this.f327b;
        if (image != null) {
            image.close();
            this.f327b = null;
        }
        this.f326a.close();
        this.f326a = f(i2, i3);
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public io.flutter.embedding.engine.renderer.j getAttachedRenderer() {
        return this.f329d;
    }

    public ImageReader getImageReader() {
        return this.f326a;
    }

    public Surface getSurface() {
        return this.f326a.getSurface();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Image image = this.f327b;
        if (image != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                HardwareBuffer hardwareBuffer = image.getHardwareBuffer();
                ColorSpace.Named unused = ColorSpace.Named.SRGB;
                this.f328c = Bitmap.wrapHardwareBuffer(hardwareBuffer, ColorSpace.get(ColorSpace.Named.SRGB));
                hardwareBuffer.close();
            } else {
                Image.Plane[] planes = image.getPlanes();
                if (planes.length == 1) {
                    Image.Plane plane = planes[0];
                    int rowStride = plane.getRowStride() / plane.getPixelStride();
                    int height = this.f327b.getHeight();
                    Bitmap bitmap = this.f328c;
                    if (bitmap == null || bitmap.getWidth() != rowStride || this.f328c.getHeight() != height) {
                        this.f328c = Bitmap.createBitmap(rowStride, height, Bitmap.Config.ARGB_8888);
                    }
                    ByteBuffer buffer = plane.getBuffer();
                    buffer.rewind();
                    this.f328c.copyPixelsFromBuffer(buffer);
                }
            }
        }
        Bitmap bitmap2 = this.f328c;
        if (bitmap2 != null) {
            canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        if (!this.f330e) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        setMeasuredDimension(Math.max(View.MeasureSpec.getSize(i2), mode == 0 ? 1 : 0), Math.max(View.MeasureSpec.getSize(i3), View.MeasureSpec.getMode(i3) == 0 ? 1 : 0));
    }

    @Override // android.view.View
    public final void onSizeChanged(int i2, int i3, int i4, int i5) {
        if (!(i2 == this.f326a.getWidth() && i3 == this.f326a.getHeight()) && this.f331f == 1 && this.f332g) {
            g(i2, i3);
            io.flutter.embedding.engine.renderer.j jVar = this.f329d;
            Surface surface = this.f326a.getSurface();
            jVar.f795c = surface;
            jVar.f793a.onSurfaceWindowChanged(surface);
        }
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void b() {
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void d() {
    }
}
