package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e f627d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e f628e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f629f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f630g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e f631h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ e[] f632i;

    static {
        e eVar = new e("DESTROYED", 0);
        f627d = eVar;
        e eVar2 = new e("INITIALIZED", 1);
        f628e = eVar2;
        e eVar3 = new e("CREATED", 2);
        f629f = eVar3;
        e eVar4 = new e("STARTED", 3);
        f630g = eVar4;
        e eVar5 = new e("RESUMED", 4);
        f631h = eVar5;
        f632i = new e[]{eVar, eVar2, eVar3, eVar4, eVar5};
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) f632i.clone();
    }
}
