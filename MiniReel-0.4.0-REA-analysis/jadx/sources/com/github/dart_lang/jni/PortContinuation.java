package com.github.dart_lang.jni;

import h0.d;
import h0.i;
import x0.AbstractC0039x;

/* JADX INFO: loaded from: classes.dex */
public class PortContinuation<T> implements d {
    private final long port;

    static {
        System.loadLibrary("dartjni");
    }

    public PortContinuation(long j2) {
        this.port = j2;
    }

    private native void _resumeWith(long j2, Object obj);

    @Override // h0.d
    public i getContext() {
        return AbstractC0039x.f1377b;
    }

    @Override // h0.d
    public void resumeWith(Object obj) {
        _resumeWith(this.port, obj);
    }
}
