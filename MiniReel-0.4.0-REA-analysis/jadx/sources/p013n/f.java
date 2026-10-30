package p013n;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class f extends b {
    public f(byte[] bArr) {
        super(bArr);
        this.f1110d.mark(Integer.MAX_VALUE);
    }

    public final void b(long j2) throws IOException {
        int i2 = this.f1111e;
        if (i2 > j2) {
            this.f1111e = 0;
            this.f1110d.reset();
        } else {
            j2 -= (long) i2;
        }
        a((int) j2);
    }

    public f(InputStream inputStream) {
        super(inputStream);
        if (inputStream.markSupported()) {
            this.f1110d.mark(Integer.MAX_VALUE);
            return;
        }
        throw new IllegalArgumentException("Cannot create SeekableByteOrderedDataInputStream with stream that does not support mark/reset");
    }
}
