package Z;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public final class q implements n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f598a;

    static {
        p pVar = p.f595a;
        f598a = new q();
    }

    @Override // Z.n
    public final ByteBuffer a(Object obj) throws IOException {
        o oVar = new o();
        oVar.write(0);
        p.f595a.k(oVar, obj);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(oVar.size());
        byteBufferAllocateDirect.put(oVar.a(), 0, oVar.size());
        return byteBufferAllocateDirect;
    }

    @Override // Z.n
    public final H.a b(ByteBuffer byteBuffer) {
        byteBuffer.order(ByteOrder.nativeOrder());
        p pVar = p.f595a;
        Object objE = pVar.e(byteBuffer);
        Object objE2 = pVar.e(byteBuffer);
        if (!(objE instanceof String) || byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Method call corrupted");
        }
        return new H.a(objE2, (String) objE);
    }

    @Override // Z.n
    public final Object c(ByteBuffer byteBuffer) {
        byteBuffer.order(ByteOrder.nativeOrder());
        byte b2 = byteBuffer.get();
        if (b2 != 0) {
            if (b2 == 1) {
            }
            throw new IllegalArgumentException("Envelope corrupted");
        }
        Object objE = p.f595a.e(byteBuffer);
        if (!byteBuffer.hasRemaining()) {
            return objE;
        }
        p pVar = p.f595a;
        Object objE2 = pVar.e(byteBuffer);
        Object objE3 = pVar.e(byteBuffer);
        Object objE4 = pVar.e(byteBuffer);
        if ((objE2 instanceof String) && ((objE3 == null || (objE3 instanceof String)) && !byteBuffer.hasRemaining())) {
            throw new i((String) objE2, (String) objE3, objE4);
        }
        throw new IllegalArgumentException("Envelope corrupted");
    }

    @Override // Z.n
    public final ByteBuffer d(H.a aVar) {
        o oVar = new o();
        p pVar = p.f595a;
        pVar.k(oVar, (String) aVar.f170e);
        pVar.k(oVar, aVar.f171f);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(oVar.size());
        byteBufferAllocateDirect.put(oVar.a(), 0, oVar.size());
        return byteBufferAllocateDirect;
    }

    @Override // Z.n
    public final ByteBuffer e(String str, String str2) throws IOException {
        o oVar = new o();
        oVar.write(1);
        p pVar = p.f595a;
        pVar.k(oVar, "error");
        pVar.k(oVar, str);
        pVar.k(oVar, null);
        pVar.k(oVar, str2);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(oVar.size());
        byteBufferAllocateDirect.put(oVar.a(), 0, oVar.size());
        return byteBufferAllocateDirect;
    }

    @Override // Z.n
    public final ByteBuffer f(String str, String str2, Object obj) throws IOException {
        o oVar = new o();
        oVar.write(1);
        p pVar = p.f595a;
        pVar.k(oVar, str);
        pVar.k(oVar, str2);
        if (obj instanceof Throwable) {
            pVar.k(oVar, Log.getStackTraceString((Throwable) obj));
        } else {
            pVar.k(oVar, obj);
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(oVar.size());
        byteBufferAllocateDirect.put(oVar.a(), 0, oVar.size());
        return byteBufferAllocateDirect;
    }
}
