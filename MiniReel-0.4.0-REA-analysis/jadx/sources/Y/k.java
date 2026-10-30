package Y;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f552a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f554c;

    public /* synthetic */ k(int i2, Object obj, Object obj2) {
        this.f552a = i2;
        this.f554c = obj;
        this.f553b = obj2;
    }

    public final void a(String str, String str2, Object obj) {
        switch (this.f552a) {
            case 0:
                Log.e("RestorationChannel", "Error " + str + " while sending restoration data to framework: " + str2);
                break;
            default:
                ((Q.g) this.f553b).a(((Z.n) ((H.e) ((H.a) this.f554c).f171f).f176f).f(str, str2, obj));
                break;
        }
    }

    public final void b() {
        switch (this.f552a) {
            case 0:
                break;
            default:
                ((Q.g) this.f553b).a(null);
                break;
        }
    }

    public final void d(Object obj) {
        switch (this.f552a) {
            case 0:
                ((l) this.f554c).f556b = (byte[]) this.f553b;
                break;
            default:
                ((Q.g) this.f553b).a(((Z.n) ((H.e) ((H.a) this.f554c).f171f).f176f).a(obj));
                break;
        }
    }

    private final void c() {
    }
}
