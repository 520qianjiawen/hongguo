package Y;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f581a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f582b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f583c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f584d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f585e;

    public q(String str, int i2, int i3, int i4, int i5) {
        if (!(i2 == -1 && i3 == -1) && (i2 < 0 || i3 < 0)) {
            throw new IndexOutOfBoundsException("invalid selection: (" + i2 + ", " + i3 + ")");
        }
        if (!(i4 == -1 && i5 == -1) && (i4 < 0 || i4 > i5)) {
            throw new IndexOutOfBoundsException("invalid composing range: (" + i4 + ", " + i5 + ")");
        }
        if (i5 > str.length()) {
            throw new IndexOutOfBoundsException("invalid composing start: " + i4);
        }
        if (i2 > str.length()) {
            throw new IndexOutOfBoundsException("invalid selection start: " + i2);
        }
        if (i3 > str.length()) {
            throw new IndexOutOfBoundsException("invalid selection end: " + i3);
        }
        this.f581a = str;
        this.f582b = i2;
        this.f583c = i3;
        this.f584d = i4;
        this.f585e = i5;
    }

    public static q a(JSONObject jSONObject) {
        return new q(jSONObject.getString("text"), jSONObject.getInt("selectionBase"), jSONObject.getInt("selectionExtent"), jSONObject.getInt("composingBase"), jSONObject.getInt("composingExtent"));
    }
}
