package Z;

import android.util.Log;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class a implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f586a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f587b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f588c;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.f586a = i2;
        this.f588c = obj;
        this.f587b = obj2;
    }

    @Override // Z.e
    public final void a(ByteBuffer byteBuffer) {
        switch (this.f586a) {
            case 0:
                G.f fVar = (G.f) this.f588c;
                try {
                    ((c) this.f587b).o(((l) fVar.f166f).a(byteBuffer));
                } catch (RuntimeException e2) {
                    Log.e("BasicMessageChannel#" + ((String) fVar.f165e), "Failed to handle message reply", e2);
                    return;
                }
                break;
            default:
                H.e eVar = (H.e) this.f588c;
                Y.k kVar = (Y.k) this.f587b;
                try {
                    if (byteBuffer == null) {
                        kVar.b();
                    } else {
                        try {
                            kVar.d(((n) eVar.f176f).c(byteBuffer));
                        } catch (i e3) {
                            kVar.a(e3.f591d, e3.getMessage(), e3.f592e);
                        }
                    }
                } catch (RuntimeException e4) {
                    Log.e("MethodChannel#" + ((String) eVar.f175e), "Failed to handle method call result", e4);
                    return;
                }
                break;
        }
    }
}
