package s0;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class a extends r0.a {
    @Override // r0.a
    public final Random a() {
        ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
        h.d(threadLocalRandomCurrent, "current(...)");
        return threadLocalRandomCurrent;
    }
}
