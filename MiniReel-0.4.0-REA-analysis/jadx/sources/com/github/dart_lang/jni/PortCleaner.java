package com.github.dart_lang.jni;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/* JADX INFO: loaded from: classes.dex */
class PortCleaner {
    private final ReferenceQueue<Object> queue = new ReferenceQueue<>();
    private final PortPhantom list = new PortPhantom();

    static {
        System.loadLibrary("dartjni");
    }

    public PortCleaner() {
        Thread thread = new Thread(new Runnable() { // from class: com.github.dart_lang.jni.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f670d.lambda$new$0();
            }
        }, "PortCleaner");
        thread.setDaemon(true);
        thread.start();
    }

    private static native void clean(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        while (true) {
            try {
                PortPhantom portPhantom = (PortPhantom) this.queue.remove();
                portPhantom.remove();
                long j2 = portPhantom.port;
                if (j2 != 0) {
                    clean(j2);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void register(Object obj, long j2) {
        new PortPhantom(obj, j2);
    }

    public class PortPhantom extends PhantomReference<Object> {
        PortPhantom next;
        final long port;
        PortPhantom prev;

        public PortPhantom(Object obj, long j2) {
            super(obj, PortCleaner.this.queue);
            this.prev = this;
            this.next = this;
            this.port = j2;
            insert();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void remove() {
            synchronized (PortCleaner.this.list) {
                PortPhantom portPhantom = this.next;
                portPhantom.prev = this.prev;
                this.prev.next = portPhantom;
                this.prev = this;
                this.next = this;
            }
        }

        public void insert() {
            synchronized (PortCleaner.this.list) {
                this.prev = PortCleaner.this.list;
                PortPhantom portPhantom = PortCleaner.this.list.next;
                this.next = portPhantom;
                portPhantom.prev = this;
                PortCleaner.this.list.next = this;
            }
        }

        public PortPhantom() {
            super(null, null);
            this.prev = this;
            this.next = this;
            this.port = 0L;
        }
    }
}
