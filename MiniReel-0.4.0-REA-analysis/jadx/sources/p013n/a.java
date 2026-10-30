package p013n;

import android.media.MediaDataSource;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class a extends MediaDataSource {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f1108d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ f f1109e;

    public a(f fVar) {
        this.f1109e = fVar;
    }

    @Override // android.media.MediaDataSource
    public final long getSize() {
        return -1L;
    }

    @Override // android.media.MediaDataSource
    public final int readAt(long j2, byte[] bArr, int i2, int i3) {
        if (i3 == 0) {
            return 0;
        }
        if (j2 < 0) {
            return -1;
        }
        try {
            long j3 = this.f1108d;
            f fVar = this.f1109e;
            if (j3 != j2) {
                if (j3 >= 0 && j2 >= j3 + ((long) fVar.f1110d.available())) {
                    return -1;
                }
                fVar.b(j2);
                this.f1108d = j2;
            }
            if (i3 > fVar.f1110d.available()) {
                i3 = fVar.f1110d.available();
            }
            int i4 = fVar.read(bArr, i2, i3);
            if (i4 >= 0) {
                this.f1108d += (long) i4;
                return i4;
            }
        } catch (IOException unused) {
        }
        this.f1108d = -1L;
        return -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
