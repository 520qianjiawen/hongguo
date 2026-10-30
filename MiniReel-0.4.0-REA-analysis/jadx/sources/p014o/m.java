package p014o;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1188b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f1189c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f1190d;

    public m(int i2, int i3, long j2, long j3) {
        this.f1187a = i2;
        this.f1188b = i3;
        this.f1189c = j2;
        this.f1190d = j3;
    }

    public static m a(File file) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            m mVar = new m(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
            dataInputStream.close();
            return mVar;
        } catch (Throwable th) {
            try {
                dataInputStream.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public final void b(File file) throws IOException {
        file.delete();
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            dataOutputStream.writeInt(this.f1187a);
            dataOutputStream.writeInt(this.f1188b);
            dataOutputStream.writeLong(this.f1189c);
            dataOutputStream.writeLong(this.f1190d);
            dataOutputStream.close();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof m)) {
            m mVar = (m) obj;
            if (this.f1188b == mVar.f1188b && this.f1189c == mVar.f1189c && this.f1187a == mVar.f1187a && this.f1190d == mVar.f1190d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f1188b), Long.valueOf(this.f1189c), Integer.valueOf(this.f1187a), Long.valueOf(this.f1190d));
    }
}
