package Q;

import io.flutter.embedding.engine.FlutterJNI;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Z.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final FlutterJNI f477a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f478b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicBoolean f479c = new AtomicBoolean(false);

    public g(FlutterJNI flutterJNI, int i2) {
        this.f477a = flutterJNI;
        this.f478b = i2;
    }

    @Override // Z.e
    public final void a(ByteBuffer byteBuffer) {
        if (this.f479c.getAndSet(true)) {
            throw new IllegalStateException("Reply already submitted");
        }
        int i2 = this.f478b;
        FlutterJNI flutterJNI = this.f477a;
        if (byteBuffer == null) {
            flutterJNI.invokePlatformMessageEmptyResponseCallback(i2);
        } else {
            flutterJNI.invokePlatformMessageResponseCallback(i2, byteBuffer, byteBuffer.position());
        }
    }
}
