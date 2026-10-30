package S;

import android.media.MediaDataSource;

/* JADX INFO: loaded from: classes.dex */
public final class c extends MediaDataSource {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ byte[] f495d;

    public c(byte[] bArr) {
        this.f495d = bArr;
    }

    @Override // android.media.MediaDataSource
    public final long getSize() {
        return this.f495d.length;
    }

    @Override // android.media.MediaDataSource
    public final int readAt(long j2, byte[] bArr, int i2, int i3) {
        byte[] bArr2 = this.f495d;
        if (j2 >= bArr2.length) {
            return -1;
        }
        if (((long) i3) + j2 > bArr2.length) {
            i3 = (int) (((long) bArr2.length) - j2);
        }
        System.arraycopy(bArr2, (int) j2, bArr, i2, i3);
        return i3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
