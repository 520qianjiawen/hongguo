package com.github.dart_lang.jni;

import V.b;

/* JADX INFO: loaded from: classes.dex */
public class JniPlugin implements b {
    static {
        System.loadLibrary("dartjni");
        setClassLoader(JniPlugin.class.getClassLoader());
    }

    public static native void setClassLoader(ClassLoader classLoader);

    @Override // V.b
    public void onAttachedToEngine(V.a aVar) {
    }

    @Override // V.b
    public void onDetachedFromEngine(V.a aVar) {
    }
}
