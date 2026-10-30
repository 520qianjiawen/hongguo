package defpackage;

import Z.o;
import Z.p;
import java.nio.ByteBuffer;
import java.util.List;
import p0.h;
import p000a.a;

/* JADX INFO: loaded from: classes.dex */
public final class g extends p {
    @Override // Z.p
    public final Object f(byte b2, ByteBuffer byteBuffer) {
        h.e(byteBuffer, "buffer");
        if (b2 == -127) {
            Object objE = e(byteBuffer);
            List list = objE instanceof List ? (List) objE : null;
            if (list != null) {
                return new b((Boolean) list.get(0));
            }
        } else {
            if (b2 != -126) {
                return super.f(b2, byteBuffer);
            }
            Object objE2 = e(byteBuffer);
            List list2 = objE2 instanceof List ? (List) objE2 : null;
            if (list2 != null) {
                return new a((Boolean) list2.get(0));
            }
        }
        return null;
    }

    @Override // Z.p
    public final void k(o oVar, Object obj) {
        if (obj instanceof b) {
            oVar.write(129);
            k(oVar, a.A(((b) obj).f659a));
        } else if (!(obj instanceof a)) {
            super.k(oVar, obj);
        } else {
            oVar.write(130);
            k(oVar, a.A(((a) obj).f601a));
        }
    }
}
