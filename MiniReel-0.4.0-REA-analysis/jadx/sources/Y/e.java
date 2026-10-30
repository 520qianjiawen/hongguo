package Y;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e f525d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ e[] f526e;

    static {
        e eVar = new e("PLAIN_TEXT", 0);
        f525d = eVar;
        f526e = new e[]{eVar};
    }

    public static e a(String str) throws NoSuchFieldException {
        for (e eVar : values()) {
            eVar.getClass();
            if ("text/plain".equals(str)) {
                return eVar;
            }
        }
        throw new NoSuchFieldException("No such ClipboardContentFormat: " + str);
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) f526e.clone();
    }
}
