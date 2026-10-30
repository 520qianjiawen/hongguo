package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    private static final /* synthetic */ d[] $VALUES;
    public static final b Companion;
    public static final d ON_ANY;
    public static final d ON_CREATE;
    public static final d ON_DESTROY;
    public static final d ON_PAUSE;
    public static final d ON_RESUME;
    public static final d ON_START;
    public static final d ON_STOP;

    static {
        d dVar = new d("ON_CREATE", 0);
        ON_CREATE = dVar;
        d dVar2 = new d("ON_START", 1);
        ON_START = dVar2;
        d dVar3 = new d("ON_RESUME", 2);
        ON_RESUME = dVar3;
        d dVar4 = new d("ON_PAUSE", 3);
        ON_PAUSE = dVar4;
        d dVar5 = new d("ON_STOP", 4);
        ON_STOP = dVar5;
        d dVar6 = new d("ON_DESTROY", 5);
        ON_DESTROY = dVar6;
        d dVar7 = new d("ON_ANY", 6);
        ON_ANY = dVar7;
        $VALUES = new d[]{dVar, dVar2, dVar3, dVar4, dVar5, dVar6, dVar7};
        Companion = new b();
    }

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) $VALUES.clone();
    }
}
