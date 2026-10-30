package G;

import O.B;
import O.C;
import O.D;
import O.t;
import Z.h;
import Z.n;
import Z.q;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.text.Selection;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.plugin.editing.m;
import io.flutter.view.g;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import p020w.k;
import p022z.l;

/* JADX INFO: loaded from: classes.dex */
public final class f implements h, Z.d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static f f163g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f164d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f165e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f166f;

    public /* synthetic */ f(Object obj, Object obj2, Object obj3) {
        this.f164d = obj;
        this.f165e = obj2;
        this.f166f = obj3;
    }

    public static f d() {
        if (f163g == null) {
            H.b bVar = new H.b(2);
            N.a aVar = new N.a();
            aVar.f270a = 0;
            ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool(aVar);
            FlutterJNI flutterJNI = new FlutterJNI();
            T.c cVar = new T.c();
            cVar.f507a = false;
            cVar.f508b = false;
            cVar.f512f = flutterJNI;
            cVar.f513g = executorServiceNewCachedThreadPool;
            f fVar = new f();
            fVar.f164d = cVar;
            fVar.f165e = bVar;
            fVar.f166f = executorServiceNewCachedThreadPool;
            f163g = fVar;
        }
        return f163g;
    }

    public void a(int i2, g gVar) {
        ((FlutterJNI) this.f165e).dispatchSemanticsAction(i2, gVar);
    }

    public void b(int i2, g gVar, Serializable serializable) {
        ((FlutterJNI) this.f165e).dispatchSemanticsAction(i2, gVar, serializable);
    }

    public boolean c(KeyEvent keyEvent) {
        C[] cArr = (C[]) this.f164d;
        if (((HashSet) this.f165e).remove(keyEvent)) {
            return false;
        }
        if (cArr.length <= 0) {
            e(keyEvent);
            return true;
        }
        B b2 = new B(this, keyEvent);
        for (C c2 : cArr) {
            M.a aVar = new M.a();
            aVar.f268b = b2;
            aVar.f267a = false;
            c2.q(keyEvent, aVar);
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x008e  */
    /* JADX WARN: Code duplicated, block: B:38:0x00ae  */
    public void e(KeyEvent keyEvent) {
        io.flutter.plugin.editing.d dVar;
        int selectionStart;
        int selectionEnd;
        int unicodeChar;
        int iMin;
        int iMax;
        HashSet hashSet = (HashSet) this.f165e;
        D d2 = (D) this.f166f;
        if (d2 != null) {
            t tVar = (t) d2;
            m mVar = tVar.f363l;
            boolean zE = false;
            if (mVar.f854b.isAcceptingText() && (dVar = mVar.f862j) != null) {
                io.flutter.plugin.editing.g gVar = dVar.f813d;
                if (keyEvent.getAction() == 0) {
                    if (keyEvent.getKeyCode() == 21) {
                        zE = dVar.d(true, keyEvent.isShiftPressed());
                    } else if (keyEvent.getKeyCode() == 22) {
                        zE = dVar.d(false, keyEvent.isShiftPressed());
                    } else if (keyEvent.getKeyCode() == 19) {
                        zE = dVar.e(true, keyEvent.isShiftPressed());
                    } else if (keyEvent.getKeyCode() == 20) {
                        zE = dVar.e(false, keyEvent.isShiftPressed());
                    } else if (keyEvent.getKeyCode() == 66 || keyEvent.getKeyCode() == 160) {
                        EditorInfo editorInfo = dVar.f814e;
                        if ((editorInfo.inputType & 131072) == 0) {
                            dVar.performEditorAction(editorInfo.imeOptions & 255);
                        } else {
                            selectionStart = Selection.getSelectionStart(gVar);
                            selectionEnd = Selection.getSelectionEnd(gVar);
                            unicodeChar = keyEvent.getUnicodeChar();
                            if (selectionStart >= 0 && selectionEnd >= 0 && unicodeChar != 0) {
                                iMin = Math.min(selectionStart, selectionEnd);
                                iMax = Math.max(selectionStart, selectionEnd);
                                dVar.beginBatchEdit();
                                if (iMin != iMax) {
                                    gVar.delete(iMin, iMax);
                                }
                                gVar.insert(iMin, (CharSequence) String.valueOf((char) unicodeChar));
                                int i2 = iMin + 1;
                                dVar.setSelection(i2, i2);
                                dVar.endBatchEdit();
                            }
                        }
                        zE = true;
                    } else {
                        selectionStart = Selection.getSelectionStart(gVar);
                        selectionEnd = Selection.getSelectionEnd(gVar);
                        unicodeChar = keyEvent.getUnicodeChar();
                        if (selectionStart >= 0) {
                            iMin = Math.min(selectionStart, selectionEnd);
                            iMax = Math.max(selectionStart, selectionEnd);
                            dVar.beginBatchEdit();
                            if (iMin != iMax) {
                                gVar.delete(iMin, iMax);
                            }
                            gVar.insert(iMin, (CharSequence) String.valueOf((char) unicodeChar));
                            int i3 = iMin + 1;
                            dVar.setSelection(i3, i3);
                            dVar.endBatchEdit();
                            zE = true;
                        }
                    }
                }
            }
            if (zE) {
                return;
            }
            hashSet.add(keyEvent);
            tVar.getRootView().dispatchKeyEvent(keyEvent);
            if (hashSet.remove(keyEvent)) {
                Log.w("KeyboardManager", "A redispatched key event was consumed before reaching KeyboardManager");
            }
        }
    }

    public void f(Activity activity, k kVar) {
        WeakHashMap weakHashMap = (WeakHashMap) this.f166f;
        p0.h.e(activity, "activity");
        p0.h.e(kVar, "newLayout");
        ReentrantLock reentrantLock = (ReentrantLock) this.f165e;
        reentrantLock.lock();
        try {
            if (kVar.equals((k) weakHashMap.get(activity))) {
                reentrantLock.unlock();
                return;
            }
            reentrantLock.unlock();
            for (p022z.k kVar2 : ((l) ((p002b0.a) this.f164d).f661e).f1415b) {
                if (kVar2.f1409a.equals(activity)) {
                    kVar2.f1411c = kVar;
                    kVar2.f1410b.accept(kVar);
                }
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public void g(Serializable serializable, Z.c cVar) {
        ((Z.f) this.f164d).b((String) this.f165e, ((Z.l) this.f166f).b(serializable), cVar == null ? null : new Z.a(0, this, cVar));
    }

    @Override // Z.h
    public void h() {
        I.a aVar = (I.a) this.f166f;
        if (aVar != null) {
            ((Context) this.f164d).unregisterReceiver(aVar);
            this.f166f = null;
        }
    }

    public void i(Z.b bVar) {
        ((Z.f) this.f164d).i((String) this.f165e, bVar == null ? null : new H.a(11, this, bVar));
    }

    @Override // Z.d
    public void j(ByteBuffer byteBuffer, Q.g gVar) {
        AtomicReference atomicReference = (AtomicReference) this.f165e;
        h hVar = (h) this.f164d;
        f fVar = (f) this.f166f;
        String str = (String) fVar.f165e;
        n nVar = (n) fVar.f166f;
        H.a aVarB = nVar.b(byteBuffer);
        String str2 = (String) aVarB.f170e;
        Object obj = aVarB.f171f;
        if (!str2.equals("listen")) {
            if (!str2.equals("cancel")) {
                gVar.a(null);
                return;
            }
            if (((Z.g) atomicReference.getAndSet(null)) == null) {
                gVar.a(nVar.f("error", "No active stream to cancel", null));
                return;
            }
            try {
                hVar.h();
                gVar.a(nVar.a(null));
                return;
            } catch (RuntimeException e2) {
                Log.e("EventChannel#" + str, "Failed to close event stream", e2);
                gVar.a(nVar.f("error", e2.getMessage(), null));
                return;
            }
        }
        Z.g gVar2 = new Z.g(this);
        if (((Z.g) atomicReference.getAndSet(gVar2)) != null) {
            try {
                hVar.h();
            } catch (RuntimeException e3) {
                Log.e("EventChannel#" + str, "Failed to close existing event stream", e3);
            }
        }
        try {
            hVar.k(obj, gVar2);
            gVar.a(nVar.a(null));
        } catch (RuntimeException e4) {
            atomicReference.set(null);
            Log.e("EventChannel#" + str, "Failed to open event stream", e4);
            gVar.a(nVar.f("error", e4.getMessage(), null));
        }
    }

    @Override // Z.h
    public void k(Object obj, Z.g gVar) {
        AudioManager audioManager = (AudioManager) this.f165e;
        Context context = (Context) this.f164d;
        Map map = obj instanceof Map ? (Map) obj : null;
        Object obj2 = map != null ? map.get("fetchInitialVolume") : null;
        Boolean bool = obj2 instanceof Boolean ? (Boolean) obj2 : null;
        boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
        I.a aVar = (I.a) this.f166f;
        if (aVar != null) {
            context.unregisterReceiver(aVar);
            this.f166f = null;
        }
        I.a aVar2 = new I.a(gVar, audioManager);
        this.f166f = aVar2;
        context.registerReceiver(aVar2, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
        if (zBooleanValue) {
            gVar.a(Double.valueOf(p000a.a.v(audioManager)));
        }
    }

    public void l(h hVar) {
        f fVar;
        String str = (String) this.f165e;
        Z.f fVar2 = (Z.f) this.f164d;
        if (hVar == null) {
            fVar = null;
        } else {
            f fVar3 = new f();
            fVar3.f166f = this;
            fVar3.f165e = new AtomicReference(null);
            fVar3.f164d = hVar;
            fVar = fVar3;
        }
        fVar2.i(str, fVar);
    }

    public f(Z.f fVar, String str) {
        q qVar = q.f598a;
        this.f164d = fVar;
        this.f165e = str;
        this.f166f = qVar;
    }
}
