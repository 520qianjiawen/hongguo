package p003c;

import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class a extends p000a.a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static volatile a f662j;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f663i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(int i2) {
        super(11);
        switch (i2) {
            case 1:
                super(11);
                this.f663i = new Object();
                Executors.newFixedThreadPool(4, new b());
                break;
            default:
                this.f663i = new a(1);
                break;
        }
    }
}
