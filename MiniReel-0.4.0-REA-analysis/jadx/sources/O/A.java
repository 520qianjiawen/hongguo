package O;

import android.util.Log;
import android.view.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class A implements C {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Z.f f271d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap f272e = new HashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap f273f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final D0.i f274g;

    public A(Z.f fVar) {
        HashMap map = new HashMap();
        this.f273f = map;
        this.f274g = new D0.i();
        this.f271d = fVar;
        G.b bVar = H.f284a;
        G g2 = new G();
        g2.f283a = false;
        G g3 = new G[]{g2}[0];
        g3.getClass();
        map.put(4294967556L, g3);
    }

    public final void a(x xVar, final M.a aVar) {
        long j2;
        long j3;
        byte[] bytes = null;
        Z.e eVar = aVar == null ? null : new Z.e() { // from class: O.y
            @Override // Z.e
            public final void a(ByteBuffer byteBuffer) {
                Boolean boolValueOf = Boolean.FALSE;
                if (byteBuffer != null) {
                    byteBuffer.rewind();
                    if (byteBuffer.capacity() != 0) {
                        boolValueOf = Boolean.valueOf(byteBuffer.get() != 0);
                    }
                } else {
                    Log.w("KeyEmbedderResponder", "A null reply was received when sending a key event to the framework.");
                }
                aVar.a(boolValueOf.booleanValue());
            }
        };
        try {
            String str = xVar.f383g;
            if (str != null) {
                bytes = str.getBytes("UTF-8");
            }
            int length = bytes == null ? 0 : bytes.length;
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(length + 56);
            byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocateDirect.putLong(length);
            byteBufferAllocateDirect.putLong(xVar.f377a);
            int i2 = xVar.f378b;
            if (i2 == 1) {
                j2 = 0;
            } else if (i2 == 2) {
                j2 = 1;
            } else {
                if (i2 != 3) {
                    throw null;
                }
                j2 = 2;
            }
            byteBufferAllocateDirect.putLong(j2);
            byteBufferAllocateDirect.putLong(xVar.f379c);
            byteBufferAllocateDirect.putLong(xVar.f380d);
            byteBufferAllocateDirect.putLong(xVar.f381e ? 1L : 0L);
            int i3 = xVar.f382f;
            if (i3 == 1) {
                j3 = 0;
            } else if (i3 == 2) {
                j3 = 1;
            } else if (i3 == 3) {
                j3 = 2;
            } else if (i3 == 4) {
                j3 = 3;
            } else {
                if (i3 != 5) {
                    throw null;
                }
                j3 = 4;
            }
            byteBufferAllocateDirect.putLong(j3);
            if (bytes != null) {
                byteBufferAllocateDirect.put(bytes);
            }
            this.f271d.b("flutter/keydata", byteBufferAllocateDirect, eVar);
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError("UTF-8 not supported");
        }
    }

    public final void b(boolean z2, Long l2, Long l3, long j2) {
        x xVar = new x();
        xVar.f377a = j2;
        xVar.f378b = z2 ? 1 : 2;
        xVar.f380d = l2.longValue();
        xVar.f379c = l3.longValue();
        xVar.f383g = null;
        xVar.f381e = true;
        xVar.f382f = 1;
        if (l3.longValue() != 0 && l2.longValue() != 0) {
            if (!z2) {
                l2 = null;
            }
            c(l3, l2);
        }
        a(xVar, null);
    }

    public final void c(Long l2, Long l3) {
        HashMap map = this.f272e;
        if (l3 != null) {
            if (((Long) map.put(l2, l3)) != null) {
                throw new AssertionError("The key was not empty");
            }
        } else if (((Long) map.remove(l2)) == null) {
            throw new AssertionError("The key was empty");
        }
    }

    /* JADX WARN: Code duplicated, block: B:143:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:144:0x0304  */
    /* JADX WARN: Instruction removed from duplicated block: B:143:0x02f5, please report this as an issue */
    @Override // O.C
    public final void q(final KeyEvent keyEvent, M.a aVar) {
        Long lValueOf;
        boolean z2;
        Long l2;
        String str;
        int i2;
        G g2;
        int i3;
        char cCharValue;
        String str2;
        Boolean[] boolArr;
        int i4;
        boolean[] zArr;
        final long j2;
        int i5;
        int i6;
        this = this;
        if (keyEvent.getScanCode() != 0 || keyEvent.getKeyCode() != 0) {
            long scanCode = keyEvent.getScanCode();
            if (scanCode == 0) {
                lValueOf = Long.valueOf((((long) keyEvent.getKeyCode()) & 4294967295L) | 73014444032L);
            } else {
                lValueOf = (Long) H.f284a.get(Long.valueOf(scanCode));
                if (lValueOf == null) {
                    lValueOf = Long.valueOf((((long) keyEvent.getScanCode()) & 4294967295L) | 73014444032L);
                }
            }
            Long l3 = lValueOf;
            Long lValueOf2 = (Long) H.f285b.get(Long.valueOf(keyEvent.getKeyCode()));
            if (lValueOf2 == null) {
                lValueOf2 = Long.valueOf((((long) keyEvent.getKeyCode()) & 4294967295L) | 73014444032L);
            }
            Long l4 = lValueOf2;
            ArrayList arrayList = new ArrayList();
            F[] fArr = H.f286c;
            int length = fArr.length;
            int i7 = 0;
            while (true) {
                int i8 = 2;
                HashMap map = this.f272e;
                if (i7 >= length) {
                    Long l5 = l4;
                    HashMap map2 = this.f273f;
                    for (G g3 : map2.values()) {
                        int metaState = keyEvent.getMetaState();
                        g3.getClass();
                        boolean z3 = (metaState & 1048576) != 0;
                        if (4294967556L != l5.longValue() && g3.f283a != z3) {
                            boolean zContainsKey = map.containsKey(458809L);
                            boolean z4 = !zContainsKey;
                            if (!zContainsKey) {
                                g3.f283a = !g3.f283a;
                            }
                            this.b(z4, 4294967556L, 458809L, keyEvent.getEventTime());
                            if (zContainsKey) {
                                g3.f283a = !g3.f283a;
                            }
                            b(zContainsKey, 4294967556L, 458809L, keyEvent.getEventTime());
                        }
                        this = this;
                    }
                    int action = keyEvent.getAction();
                    if (action == 0) {
                        z2 = true;
                    } else {
                        if (action != 1) {
                            this = this;
                            break;
                        }
                        z2 = false;
                    }
                    Long l6 = (Long) map.get(l3);
                    if (!z2) {
                        this = this;
                        l2 = l3;
                        if (l6 == null) {
                            break;
                        }
                        str = null;
                        i2 = 2;
                    } else {
                        if (l6 == null) {
                            i3 = 1;
                        } else {
                            if (keyEvent.getRepeatCount() > 0) {
                                i3 = 3;
                            } else {
                                this = this;
                                l2 = l3;
                                this.b(false, l6, l2, keyEvent.getEventTime());
                                i3 = 1;
                            }
                            cCharValue = this.f274g.a(keyEvent.getUnicodeChar()).charValue();
                            if (cCharValue != 0) {
                                str2 = "" + cCharValue;
                            } else {
                                str2 = null;
                            }
                            i2 = i3;
                            str = str2;
                        }
                        l2 = l3;
                        cCharValue = this.f274g.a(keyEvent.getUnicodeChar()).charValue();
                        if (cCharValue != 0) {
                            str2 = "" + cCharValue;
                        } else {
                            str2 = null;
                        }
                        i2 = i3;
                        str = str2;
                    }
                    if (i2 != 3) {
                        this.c(l2, z2 ? l5 : null);
                    }
                    if (i2 == 1 && (g2 = (G) map2.get(l5)) != null) {
                        g2.f283a = !g2.f283a;
                    }
                    x xVar = new x();
                    int source = keyEvent.getSource();
                    if (source == 513) {
                        xVar.f382f = 2;
                    } else if (source == 1025) {
                        xVar.f382f = 3;
                    } else if (source == 16777232) {
                        xVar.f382f = 4;
                    } else if (source != 33554433) {
                        xVar.f382f = 1;
                    } else {
                        xVar.f382f = 5;
                    }
                    xVar.f377a = keyEvent.getEventTime();
                    xVar.f378b = i2;
                    xVar.f380d = l5.longValue();
                    xVar.f379c = l2.longValue();
                    xVar.f383g = str;
                    xVar.f381e = false;
                    this.a(xVar, aVar);
                    int size = arrayList.size();
                    int i9 = 0;
                    while (i9 < size) {
                        Object obj = arrayList.get(i9);
                        i9++;
                        ((Runnable) obj).run();
                    }
                    return;
                }
                F f2 = fArr[i7];
                boolean z5 = (f2.f281a & keyEvent.getMetaState()) != 0;
                long jLongValue = l4.longValue();
                long jLongValue2 = l3.longValue();
                E[] eArr = f2.f282b;
                boolean[] zArr2 = new boolean[2];
                Boolean[] boolArr2 = new Boolean[2];
                int i10 = 0;
                boolean z6 = false;
                while (i10 < i8) {
                    final E e2 = eArr[i10];
                    Long l7 = l4;
                    boolean zContainsKey2 = map.containsKey(Long.valueOf(e2.f279a));
                    zArr2[i10] = zContainsKey2;
                    int i11 = i7;
                    if (e2.f280b == jLongValue) {
                        boolean z7 = keyEvent.getRepeatCount() > 0;
                        int action2 = keyEvent.getAction();
                        if (action2 != 0) {
                            i5 = 1;
                            if (action2 != 1) {
                                throw new AssertionError("Unexpected event type");
                            }
                            i6 = 2;
                        } else {
                            i5 = 1;
                            i6 = z7 ? 3 : 1;
                        }
                        int iA = p014o.c.a(i6);
                        if (iA == 0) {
                            map = map;
                            boolArr = boolArr2;
                            i11 = i11;
                            i4 = 2;
                            long j3 = jLongValue2;
                            eArr = eArr;
                            zArr = zArr2;
                            j2 = j3;
                            boolArr[i10] = Boolean.FALSE;
                            if (!z5) {
                                final int i12 = 0;
                                arrayList.add(new Runnable(this) { // from class: O.z

                                    /* JADX INFO: renamed from: e, reason: collision with root package name */
                                    public final /* synthetic */ A f386e;

                                    {
                                        this.f386e = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        switch (i12) {
                                            case 0:
                                                A a2 = this.f386e;
                                                a2.getClass();
                                                a2.b(false, Long.valueOf(e2.f280b), Long.valueOf(j2), keyEvent.getEventTime());
                                                break;
                                            default:
                                                A a3 = this.f386e;
                                                a3.getClass();
                                                a3.b(false, Long.valueOf(e2.f280b), Long.valueOf(j2), keyEvent.getEventTime());
                                                break;
                                        }
                                    }
                                });
                            }
                        } else if (iA != i5) {
                            i4 = 2;
                            if (iA != 2) {
                                map = map;
                                boolArr = boolArr2;
                                i11 = i11;
                                i4 = 2;
                                long j4 = jLongValue2;
                                eArr = eArr;
                                zArr = zArr2;
                                j2 = j4;
                            } else {
                                if (z5) {
                                    boolArr = boolArr2;
                                    zArr = zArr2;
                                    j2 = jLongValue2;
                                } else {
                                    Boolean[] boolArr3 = boolArr2;
                                    final int i13 = 1;
                                    boolArr = boolArr3;
                                    zArr = zArr2;
                                    j2 = jLongValue2;
                                    arrayList.add(new Runnable(this) { // from class: O.z

                                        /* JADX INFO: renamed from: e, reason: collision with root package name */
                                        public final /* synthetic */ A f386e;

                                        {
                                            this.f386e = this;
                                        }

                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            switch (i13) {
                                                case 0:
                                                    A a2 = this.f386e;
                                                    a2.getClass();
                                                    a2.b(false, Long.valueOf(e2.f280b), Long.valueOf(j2), keyEvent.getEventTime());
                                                    break;
                                                default:
                                                    A a3 = this.f386e;
                                                    a3.getClass();
                                                    a3.b(false, Long.valueOf(e2.f280b), Long.valueOf(j2), keyEvent.getEventTime());
                                                    break;
                                            }
                                        }
                                    });
                                }
                                boolArr[i10] = Boolean.valueOf(zArr[i10]);
                            }
                        } else {
                            map = map;
                            boolArr = boolArr2;
                            i11 = i11;
                            i4 = 2;
                            long j5 = jLongValue2;
                            eArr = eArr;
                            zArr = zArr2;
                            j2 = j5;
                            boolArr[i10] = Boolean.valueOf(zArr[i10]);
                        }
                        z6 = true;
                    } else {
                        boolArr = boolArr2;
                        i11 = i11;
                        i4 = 2;
                        map = map;
                        long j6 = jLongValue2;
                        eArr = eArr;
                        zArr = zArr2;
                        j2 = j6;
                        z6 = z6 || zContainsKey2;
                    }
                    i10++;
                    i8 = i4;
                    i7 = i11;
                    zArr2 = zArr;
                    boolArr2 = boolArr;
                    l4 = l7;
                    long j7 = j2;
                    map = map;
                    eArr = eArr;
                    jLongValue2 = j7;
                }
                int i14 = i7;
                E[] eArr2 = eArr;
                boolean[] zArr3 = zArr2;
                Boolean[] boolArr4 = boolArr2;
                Long l8 = l4;
                int i15 = i8;
                boolean z8 = keyEvent.getDeviceId() == -1;
                if (z5) {
                    for (int i16 = 0; i16 < i15; i16++) {
                        if (boolArr4[i16] == null) {
                            if (z6 || z8) {
                                boolArr4[i16] = Boolean.valueOf(zArr3[i16]);
                            } else {
                                boolArr4[i16] = Boolean.TRUE;
                                z6 = true;
                            }
                        }
                    }
                    if (!z6 && !z8) {
                        boolArr4[0] = Boolean.TRUE;
                    }
                } else {
                    for (int i17 = 0; i17 < i15; i17++) {
                        if (boolArr4[i17] == null) {
                            boolArr4[i17] = Boolean.FALSE;
                        }
                    }
                }
                for (int i18 = 0; i18 < i15; i18++) {
                    if (zArr3[i18] != boolArr4[i18].booleanValue()) {
                        E e3 = eArr2[i18];
                        b(boolArr4[i18].booleanValue(), Long.valueOf(e3.f280b), Long.valueOf(e3.f279a), keyEvent.getEventTime());
                    }
                }
                this = this;
                i7 = i14 + 1;
                l4 = l8;
            }
        }
        this.b(true, 0L, 0L, 0L);
        aVar.a(true);
    }
}
