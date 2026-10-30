package com.github.dart_lang.jni_flutter;

import P.d;
import V.b;
import W.a;
import android.app.Activity;
import android.content.Context;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class JniFlutterPlugin implements b, a {
    private static Context context;
    private static final ConcurrentHashMap<Long, JniFlutterPlugin> pluginMap = new ConcurrentHashMap<>();
    private volatile Activity activity;
    private long engineId;

    public static Activity getActivity(long j2) {
        JniFlutterPlugin jniFlutterPlugin = pluginMap.get(Long.valueOf(j2));
        Objects.requireNonNull(jniFlutterPlugin);
        return jniFlutterPlugin.activity;
    }

    public static Context getApplicationContext() {
        return context;
    }

    private void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override // W.a
    public void onAttachedToActivity(W.b bVar) {
        setActivity(((d) bVar).f414a);
    }

    @Override // V.b
    public void onAttachedToEngine(V.a aVar) {
        long j2 = aVar.f516b.v;
        this.engineId = j2;
        context = aVar.f515a;
        pluginMap.put(Long.valueOf(j2), this);
    }

    @Override // W.a
    public void onDetachedFromActivity() {
        setActivity(null);
    }

    @Override // W.a
    public void onDetachedFromActivityForConfigChanges() {
        setActivity(null);
    }

    @Override // V.b
    public void onDetachedFromEngine(V.a aVar) {
        this.activity = null;
        pluginMap.remove(Long.valueOf(this.engineId));
    }

    @Override // W.a
    public void onReattachedToActivityForConfigChanges(W.b bVar) {
        setActivity(((d) bVar).f414a);
    }
}
