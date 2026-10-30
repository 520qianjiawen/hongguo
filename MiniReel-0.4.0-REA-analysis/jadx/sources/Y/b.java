package Y;

import android.window.BackEvent;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H.e f519a;

    public b(Q.b bVar, int i2) {
        switch (i2) {
            case 1:
                H.b bVar2 = new H.b(10);
                H.e eVar = new H.e(bVar, "flutter/navigation", Z.k.f594a, null);
                this.f519a = eVar;
                eVar.j(bVar2);
                break;
            default:
                H.b bVar3 = new H.b(7);
                H.e eVar2 = new H.e(bVar, "flutter/backgesture", Z.q.f598a, null);
                this.f519a = eVar2;
                eVar2.j(bVar3);
                break;
        }
    }

    public static HashMap a(BackEvent backEvent) {
        HashMap map = new HashMap(3);
        float touchX = backEvent.getTouchX();
        float touchY = backEvent.getTouchY();
        map.put("touchOffset", (Float.isNaN(touchX) || Float.isNaN(touchY)) ? null : Arrays.asList(Float.valueOf(touchX), Float.valueOf(touchY)));
        map.put("progress", Float.valueOf(backEvent.getProgress()));
        map.put("swipeEdge", Integer.valueOf(backEvent.getSwipeEdge()));
        return map;
    }
}
