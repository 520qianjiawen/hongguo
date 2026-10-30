package j0;

import h0.i;

/* JADX INFO: loaded from: classes.dex */
public final class a implements h0.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f1070d = new a();

    @Override // h0.d
    public final i getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    @Override // h0.d
    public final void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    public final String toString() {
        return "This continuation is already complete";
    }
}
