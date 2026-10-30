package io.flutter.view;

import android.R;
import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import io.flutter.embedding.engine.FlutterJNI;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class n extends AccessibilityNodeProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1023a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final G.f f1024b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AccessibilityManager f1025c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AccessibilityViewEmbedder f1026d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final io.flutter.plugin.platform.h f1027e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ContentResolver f1028f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f1029g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final HashMap f1030h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m f1031i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Integer f1032j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1033k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f1034l;
    public m m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f1035n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public m f1036o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f1037p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1038q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C0.x f1039r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1040s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1041t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final d f1042u;
    public final e v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final i f1043w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final l f1044x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final k f1045y;
    public static final int[] B = p014o.c.b(33);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final int f1022z = 267386881;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final int f1021A = -1;

    public n(View view, G.f fVar, AccessibilityManager accessibilityManager, ContentResolver contentResolver, io.flutter.plugin.platform.h hVar) {
        AccessibilityViewEmbedder accessibilityViewEmbedder = new AccessibilityViewEmbedder(view, 65536);
        this.f1029g = new HashMap();
        this.f1030h = new HashMap();
        this.f1033k = 0;
        this.f1037p = new ArrayList();
        this.f1038q = 0;
        this.f1040s = false;
        this.f1041t = false;
        c cVar = new c(this);
        d dVar = new d(this);
        this.f1042u = dVar;
        int i2 = Build.VERSION.SDK_INT;
        i c0015a = i2 >= 34 ? new C0015a(this) : new C.a();
        this.f1043w = c0015a;
        this.f1023a = view;
        this.f1024b = fVar;
        this.f1025c = accessibilityManager;
        this.f1028f = contentResolver;
        this.f1026d = accessibilityViewEmbedder;
        this.f1027e = hVar;
        fVar.f166f = cVar;
        ((FlutterJNI) fVar.f165e).setAccessibilityDelegate(cVar);
        dVar.onAccessibilityStateChanged(accessibilityManager.isEnabled());
        accessibilityManager.addAccessibilityStateChangeListener(dVar);
        e eVar = new e(this, accessibilityManager);
        this.v = eVar;
        eVar.onTouchExplorationStateChanged(accessibilityManager.isTouchExplorationEnabled());
        accessibilityManager.addTouchExplorationStateChangeListener(eVar);
        this.f1033k |= 128;
        k kVar = new k(this);
        this.f1045y = kVar;
        m(kVar.f935a, kVar.a());
        contentResolver.registerContentObserver(Settings.Global.getUriFor("transition_animation_scale"), false, kVar);
        if (i2 >= 31 && view.getResources() != null) {
            m(4, h.a(view.getResources().getConfiguration()));
        }
        l lVar = new l(this);
        this.f1044x = lVar;
        m(lVar.f935a, lVar.a());
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("accessibility_display_inversion_enabled"), false, lVar);
        if (i2 >= 34) {
            k();
            Context context = view.getContext();
            UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
            if (uiModeManager != null) {
                uiModeManager.addContrastChangeListener(context.getMainExecutor(), Y.a.d(c0015a));
            }
        }
        hVar.d(this);
    }

    public static String c(ByteBuffer byteBuffer, String[] strArr) {
        int i2 = byteBuffer.getInt();
        if (i2 == f1021A) {
            return null;
        }
        return strArr[i2];
    }

    public final j a(int i2) {
        Integer numValueOf = Integer.valueOf(i2);
        HashMap map = this.f1030h;
        j jVar = (j) map.get(numValueOf);
        if (jVar != null) {
            return jVar;
        }
        j jVar2 = new j();
        jVar2.f962c = -1;
        jVar2.f961b = i2;
        jVar2.f960a = f1022z + i2;
        map.put(Integer.valueOf(i2), jVar2);
        return jVar2;
    }

    public final m b(int i2) {
        Integer numValueOf = Integer.valueOf(i2);
        HashMap map = this.f1029g;
        m mVar = (m) map.get(numValueOf);
        if (mVar != null) {
            return mVar;
        }
        m mVar2 = new m(this);
        mVar2.f994b = i2;
        map.put(Integer.valueOf(i2), mVar2);
        return mVar2;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i2) {
        H.b bVar;
        boolean zH;
        int i3;
        String str;
        int i4;
        int i5;
        int i6 = 1;
        j(true);
        if (i2 >= 65536) {
            return this.f1026d.createAccessibilityNodeInfo(i2);
        }
        HashMap map = this.f1029g;
        View view = this.f1023a;
        if (i2 == -1) {
            AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(view);
            view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoObtain);
            if (map.containsKey(0)) {
                accessibilityNodeInfoObtain.addChild(view, 0);
            }
            accessibilityNodeInfoObtain.setImportantForAccessibility(false);
            return accessibilityNodeInfoObtain;
        }
        m mVar = (m) map.get(Integer.valueOf(i2));
        if (mVar == null) {
            return null;
        }
        int i7 = mVar.f1005i;
        io.flutter.plugin.platform.h hVar = this.f1027e;
        if (i7 != -1 && hVar.h(i7)) {
            hVar.r(mVar.f1005i);
            return null;
        }
        AccessibilityNodeInfo accessibilityNodeInfoObtain2 = AccessibilityNodeInfo.obtain(view, i2);
        accessibilityNodeInfoObtain2.setImportantForAccessibility((mVar.h(12) || (mVar.e() == null && mVar.f998d == 0)) ? false : true);
        accessibilityNodeInfoObtain2.setViewIdResourceName("");
        String str2 = mVar.f1010o;
        if (str2 != null) {
            accessibilityNodeInfoObtain2.setViewIdResourceName(str2);
        }
        accessibilityNodeInfoObtain2.setPackageName(view.getContext().getPackageName());
        accessibilityNodeInfoObtain2.setClassName("android.view.View");
        accessibilityNodeInfoObtain2.setSource(view, i2);
        int iA = p014o.c.a(B[mVar.E]);
        if (iA == 12) {
            bVar = v.f1061b;
        } else if (iA == 23) {
            bVar = v.f1060a;
        } else if (iA != 25) {
            switch (iA) {
                case 14:
                    bVar = v.f1062c;
                    break;
                case 15:
                case 16:
                case 17:
                    bVar = v.f1065f;
                    break;
                case 18:
                    bVar = v.f1063d;
                    break;
                default:
                    bVar = v.f1066g;
                    break;
            }
        } else {
            bVar = v.f1064e;
        }
        accessibilityNodeInfoObtain2.setFocusable(mVar.j());
        n nVar = mVar.f992a;
        m mVar2 = nVar.m;
        if (mVar2 != null) {
            accessibilityNodeInfoObtain2.setFocused(mVar2.f994b == mVar.f994b);
        }
        m mVar3 = nVar.f1031i;
        if (mVar3 != null) {
            accessibilityNodeInfoObtain2.setAccessibilityFocused(mVar3.f994b == mVar.f994b);
        }
        m mVar4 = nVar.f1031i;
        if (mVar4 == null || mVar4.f994b != mVar.f994b) {
            accessibilityNodeInfoObtain2.addAction(64);
        } else {
            accessibilityNodeInfoObtain2.addAction(128);
        }
        if (mVar.g(g.SET_SELECTION)) {
            accessibilityNodeInfoObtain2.addAction(131072);
        }
        if (mVar.g(g.COPY)) {
            accessibilityNodeInfoObtain2.addAction(16384);
        }
        if (mVar.g(g.CUT)) {
            accessibilityNodeInfoObtain2.addAction(65536);
        }
        if (mVar.g(g.PASTE)) {
            accessibilityNodeInfoObtain2.addAction(32768);
        }
        if (mVar.g(g.SET_TEXT)) {
            accessibilityNodeInfoObtain2.addAction(2097152);
        }
        if (mVar.g(g.DISMISS)) {
            accessibilityNodeInfoObtain2.setDismissable(true);
            accessibilityNodeInfoObtain2.addAction(1048576);
        }
        g gVar = g.TAP;
        if (mVar.g(gVar)) {
            if (mVar.f991Z != null) {
                accessibilityNodeInfoObtain2.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, mVar.f991Z.f964e));
                accessibilityNodeInfoObtain2.setClickable(true);
            } else {
                accessibilityNodeInfoObtain2.addAction(16);
                accessibilityNodeInfoObtain2.setClickable(true);
            }
        }
        if (mVar.g(g.LONG_PRESS)) {
            if (mVar.f993a0 != null) {
                accessibilityNodeInfoObtain2.addAction(new AccessibilityNodeInfo.AccessibilityAction(32, mVar.f993a0.f964e));
                accessibilityNodeInfoObtain2.setLongClickable(true);
            } else {
                accessibilityNodeInfoObtain2.addAction(32);
                accessibilityNodeInfoObtain2.setLongClickable(true);
            }
        }
        if (mVar.h(16)) {
            accessibilityNodeInfoObtain2.setLiveRegion(1);
        }
        accessibilityNodeInfoObtain2.setSelected(mVar.h(3));
        if (Build.VERSION.SDK_INT >= 28) {
            accessibilityNodeInfoObtain2.setHeading(mVar.f973F > 0);
        }
        ArrayList arrayList = mVar.f990Y;
        if (arrayList != null) {
            int size = arrayList.size();
            int i8 = 0;
            while (i8 < size) {
                Object obj = arrayList.get(i8);
                i8++;
                j jVar = (j) obj;
                accessibilityNodeInfoObtain2.addAction(new AccessibilityNodeInfo.AccessibilityAction(jVar.f960a, jVar.f963d));
                i6 = i6;
            }
        }
        int i9 = i6;
        if (mVar.h(5)) {
            accessibilityNodeInfoObtain2.setPassword(mVar.h(11));
            if (!mVar.h(21)) {
                accessibilityNodeInfoObtain2.setClassName("android.widget.EditText");
            }
            accessibilityNodeInfoObtain2.setEditable(!mVar.h(21));
            int i10 = mVar.f1003g;
            if (i10 != -1 && (i5 = mVar.f1004h) != -1) {
                accessibilityNodeInfoObtain2.setTextSelection(i10, i5);
            }
            m mVar5 = nVar.f1031i;
            if (mVar5 != null && mVar5.f994b == mVar.f994b) {
                accessibilityNodeInfoObtain2.setLiveRegion(i9);
            }
            if (mVar.g(g.MOVE_CURSOR_FORWARD_BY_CHARACTER)) {
                accessibilityNodeInfoObtain2.addAction(256);
                i4 = 1;
            } else {
                i4 = 0;
            }
            if (mVar.g(g.MOVE_CURSOR_BACKWARD_BY_CHARACTER)) {
                accessibilityNodeInfoObtain2.addAction(512);
                i4 = 1;
            }
            if (mVar.g(g.MOVE_CURSOR_FORWARD_BY_WORD)) {
                accessibilityNodeInfoObtain2.addAction(256);
                i4 |= 2;
            }
            if (mVar.g(g.MOVE_CURSOR_BACKWARD_BY_WORD)) {
                accessibilityNodeInfoObtain2.addAction(512);
                i4 |= 2;
            }
            accessibilityNodeInfoObtain2.setMovementGranularities(i4);
            if (mVar.f999e >= 0) {
                String str3 = mVar.f1013r;
                accessibilityNodeInfoObtain2.setMaxTextLength(((str3 == null ? 0 : str3.length()) - mVar.f1001f) + mVar.f999e);
            }
        }
        if (mVar.h(4)) {
            zH = true;
        } else {
            String str4 = mVar.f970A;
            zH = (str4 == null || str4.isEmpty()) ? mVar.h(23) : false;
        }
        if (zH) {
            accessibilityNodeInfoObtain2.setClassName("android.widget.Button");
        }
        if (mVar.h(15)) {
            accessibilityNodeInfoObtain2.setClassName("android.widget.ImageView");
        }
        if (!mVar.g(gVar) && mVar.h(24)) {
            accessibilityNodeInfoObtain2.addAction(16);
            accessibilityNodeInfoObtain2.setClickable(true);
        }
        g gVar2 = g.INCREASE;
        boolean zG = mVar.g(gVar2);
        g gVar3 = g.DECREASE;
        if (zG || mVar.g(gVar3)) {
            accessibilityNodeInfoObtain2.setClassName("android.widget.SeekBar");
            if (mVar.g(gVar2)) {
                accessibilityNodeInfoObtain2.addAction(4096);
            }
            if (mVar.g(gVar3)) {
                accessibilityNodeInfoObtain2.addAction(8192);
            }
        }
        g gVar4 = g.SCROLL_LEFT;
        boolean zG2 = mVar.g(gVar4);
        g gVar5 = g.SCROLL_DOWN;
        g gVar6 = g.SCROLL_UP;
        g gVar7 = g.SCROLL_RIGHT;
        if (zG2 || mVar.g(gVar6) || mVar.g(gVar7) || mVar.g(gVar5)) {
            accessibilityNodeInfoObtain2.setScrollable(true);
            if (mVar.h(19)) {
                if (mVar.g(gVar4) || mVar.g(gVar7)) {
                    accessibilityNodeInfoObtain2.setClassName("android.widget.HorizontalScrollView");
                } else {
                    accessibilityNodeInfoObtain2.setClassName("android.widget.ScrollView");
                }
            }
        }
        if (mVar.g(gVar4) || mVar.g(gVar6)) {
            accessibilityNodeInfoObtain2.addAction(4096);
        }
        if (mVar.g(gVar7) || mVar.g(gVar5)) {
            accessibilityNodeInfoObtain2.addAction(8192);
        }
        if (nVar.l(mVar)) {
            if (mVar.g(gVar4) || mVar.g(gVar7)) {
                if (Build.VERSION.SDK_INT < 33) {
                    accessibilityNodeInfoObtain2.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(1, mVar.f1006j, false));
                } else {
                    accessibilityNodeInfoObtain2.setCollectionInfo(A.a.v(mVar.f1006j));
                }
            } else if (Build.VERSION.SDK_INT < 33) {
                accessibilityNodeInfoObtain2.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(mVar.f1006j, 1, false));
            } else {
                accessibilityNodeInfoObtain2.setCollectionInfo(A.a.l(mVar.f1006j));
            }
        }
        m mVar6 = mVar.f988V;
        if (mVar6 != null && nVar.l(mVar6) && mVar.f988V.h(19)) {
            m mVar7 = mVar.f988V;
            ArrayList arrayList2 = mVar7.f989W;
            boolean z2 = (mVar7.g(gVar4) || mVar7.g(gVar7)) ? false : true;
            int iIndexOf = arrayList2.indexOf(mVar);
            if (z2) {
                if (Build.VERSION.SDK_INT < 33) {
                    accessibilityNodeInfoObtain2.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(iIndexOf, 1, 0, 1, mVar.h(10)));
                } else {
                    accessibilityNodeInfoObtain2.setCollectionItemInfo(A.a.m(iIndexOf, mVar.h(10)));
                }
            } else if (Build.VERSION.SDK_INT < 33) {
                accessibilityNodeInfoObtain2.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(0, 1, iIndexOf, 1, mVar.h(10)));
            } else {
                accessibilityNodeInfoObtain2.setCollectionItemInfo(A.a.w(iIndexOf, mVar.h(10)));
            }
        }
        if (mVar.h(5)) {
            H.e eVar = new H.e();
            eVar.f174d = mVar.f1013r;
            eVar.f175e = mVar.f1014s;
            eVar.f176f = mVar.b();
            accessibilityNodeInfoObtain2.setText(eVar.a());
            if (Build.VERSION.SDK_INT >= 28) {
                H.e eVar2 = new H.e();
                eVar2.f174d = mVar.f1011p;
                eVar2.f175e = mVar.f1012q;
                eVar2.f177g = mVar.f970A;
                eVar2.f176f = mVar.b();
                SpannableString spannableStringA = eVar2.a();
                H.e eVar3 = new H.e();
                eVar3.f174d = mVar.f1018x;
                eVar3.f175e = mVar.f1019y;
                eVar3.f176f = mVar.b();
                CharSequence[] charSequenceArr = {spannableStringA, eVar3.a()};
                CharSequence charSequenceConcat = null;
                for (int i11 = 0; i11 < 2; i11++) {
                    CharSequence charSequence = charSequenceArr[i11];
                    if (charSequence != null && charSequence.length() > 0) {
                        charSequenceConcat = (charSequenceConcat == null || charSequenceConcat.length() == 0) ? charSequence : TextUtils.concat(charSequenceConcat, ", ", charSequence);
                    }
                }
                i3 = 3;
                accessibilityNodeInfoObtain2.setHintText(charSequenceConcat);
            } else {
                i3 = 3;
            }
        } else {
            i3 = 3;
            if (!mVar.h(12)) {
                CharSequence charSequenceE = mVar.e();
                if (Build.VERSION.SDK_INT < 28 && mVar.f1020z != null) {
                    charSequenceE = ((Object) (charSequenceE != null ? charSequenceE : "")) + "\n" + mVar.f1020z;
                }
                if (charSequenceE != null) {
                    accessibilityNodeInfoObtain2.setContentDescription(charSequenceE);
                }
            }
        }
        int i12 = Build.VERSION.SDK_INT;
        if (i12 >= 28 && (str = mVar.f1020z) != null) {
            accessibilityNodeInfoObtain2.setTooltipText(str);
            if (mVar.e() == null) {
                accessibilityNodeInfoObtain2.setContentDescription(mVar.f1020z);
            }
        }
        boolean zH2 = mVar.h(1);
        boolean zH3 = mVar.h(17);
        accessibilityNodeInfoObtain2.setCheckable(zH2 || zH3);
        if (zH2) {
            if (mVar.h(9)) {
                accessibilityNodeInfoObtain2.setClassName("android.widget.RadioButton");
            } else {
                accessibilityNodeInfoObtain2.setClassName("android.widget.CheckBox");
            }
            boolean zH4 = mVar.h(2);
            boolean zH5 = mVar.h(26);
            if (i12 >= 36) {
                accessibilityNodeInfoObtain2.setChecked(zH5 ? 2 : zH4 ? 1 : 0);
            } else {
                accessibilityNodeInfoObtain2.setChecked(zH4);
            }
        } else if (zH3) {
            accessibilityNodeInfoObtain2.setClassName("android.widget.Switch");
            boolean zH6 = mVar.h(18);
            if (i12 >= 36) {
                accessibilityNodeInfoObtain2.setChecked(zH6 ? 1 : 0);
            } else {
                accessibilityNodeInfoObtain2.setChecked(zH6);
            }
        }
        if (i12 >= 36 && mVar.h(27)) {
            if (!mVar.h(28)) {
                i3 = 1;
            }
            accessibilityNodeInfoObtain2.setExpandedState(i3);
            if (mVar.g(g.EXPAND)) {
                accessibilityNodeInfoObtain2.addAction(262144);
            }
            if (mVar.g(g.COLLAPSE)) {
                accessibilityNodeInfoObtain2.addAction(524288);
            }
        }
        bVar.b(accessibilityNodeInfoObtain2, mVar);
        m mVar8 = mVar.f988V;
        if (mVar8 != null) {
            accessibilityNodeInfoObtain2.setParent(view, mVar8.f994b);
        } else {
            accessibilityNodeInfoObtain2.setParent(view);
        }
        int i13 = mVar.f974G;
        if (i13 != -1) {
            accessibilityNodeInfoObtain2.setTraversalAfter(view, i13);
        }
        Rect rect = mVar.f1002f0;
        m mVar9 = mVar.f988V;
        if (mVar9 != null) {
            Rect rect2 = mVar9.f1002f0;
            Rect rect3 = new Rect(rect);
            rect3.offset(-rect2.left, -rect2.top);
            accessibilityNodeInfoObtain2.setBoundsInParent(rect3);
        } else {
            accessibilityNodeInfoObtain2.setBoundsInParent(rect);
        }
        Rect rect4 = new Rect(rect);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect4.offset(iArr[0], iArr[1]);
        accessibilityNodeInfoObtain2.setBoundsInScreen(rect4);
        accessibilityNodeInfoObtain2.setVisibleToUser(true);
        accessibilityNodeInfoObtain2.setEnabled(!mVar.h(7) || mVar.h(8));
        ArrayList arrayList3 = mVar.f989W;
        int size2 = arrayList3.size();
        int i14 = 0;
        while (i14 < size2) {
            Object obj2 = arrayList3.get(i14);
            i14++;
            m mVar10 = (m) obj2;
            if (!mVar10.h(14)) {
                int i15 = mVar10.f1005i;
                if (i15 != -1) {
                    hVar.r(i15);
                    hVar.h(mVar10.f1005i);
                }
                accessibilityNodeInfoObtain2.addChild(view, mVar10.f994b);
            }
        }
        return accessibilityNodeInfoObtain2;
    }

    public final AccessibilityEvent d(int i2, int i3) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i3);
        View view = this.f1023a;
        accessibilityEventObtain.setPackageName(view.getContext().getPackageName());
        accessibilityEventObtain.setSource(view, i2);
        return accessibilityEventObtain;
    }

    public final boolean e(MotionEvent motionEvent, boolean z2) {
        m mVarI;
        if (this.f1025c.isTouchExplorationEnabled()) {
            HashMap map = this.f1029g;
            if (!map.isEmpty()) {
                m mVarI2 = ((m) map.get(0)).i(new float[]{motionEvent.getX(), motionEvent.getY(), 0.0f, 1.0f}, z2);
                if (mVarI2 == null || mVarI2.f1005i == -1) {
                    if (motionEvent.getAction() == 9 || motionEvent.getAction() == 7) {
                        float x2 = motionEvent.getX();
                        float y2 = motionEvent.getY();
                        if (!map.isEmpty() && (mVarI = ((m) map.get(0)).i(new float[]{x2, y2, 0.0f, 1.0f}, z2)) != this.f1036o) {
                            if (mVarI != null) {
                                g(mVarI.f994b, 128);
                            }
                            m mVar = this.f1036o;
                            if (mVar != null) {
                                g(mVar.f994b, 256);
                            }
                            this.f1036o = mVarI;
                        }
                    } else {
                        if (motionEvent.getAction() != 10) {
                            motionEvent.toString();
                            return false;
                        }
                        m mVar2 = this.f1036o;
                        if (mVar2 != null) {
                            g(mVar2.f994b, 256);
                            this.f1036o = null;
                        }
                    }
                    return true;
                }
                if (!z2) {
                    return this.f1026d.onAccessibilityHoverEvent(mVarI2.f994b, motionEvent);
                }
            }
        }
        return false;
    }

    public final boolean f(m mVar, int i2, Bundle bundle, boolean z2) {
        int i3;
        int i4 = bundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
        boolean z3 = bundle.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN");
        int i5 = mVar.f1003g;
        int i6 = mVar.f1004h;
        if (i6 >= 0 && i5 >= 0) {
            if (i4 != 1) {
                if (i4 != 2) {
                    if (i4 != 4) {
                        if (i4 == 8 || i4 == 16) {
                            if (z2) {
                                mVar.f1004h = mVar.f1013r.length();
                            } else {
                                mVar.f1004h = 0;
                            }
                        }
                    } else if (z2 && i6 < mVar.f1013r.length()) {
                        Matcher matcher = Pattern.compile("(?!^)(\\n)").matcher(mVar.f1013r.substring(mVar.f1004h));
                        if (matcher.find()) {
                            mVar.f1004h = matcher.start(1) + mVar.f1004h;
                        } else {
                            mVar.f1004h = mVar.f1013r.length();
                        }
                    } else if (!z2 && mVar.f1004h > 0) {
                        Matcher matcher2 = Pattern.compile("(?s:.*)(\\n)").matcher(mVar.f1013r.substring(0, mVar.f1004h));
                        if (matcher2.find()) {
                            mVar.f1004h = matcher2.start(1);
                        } else {
                            mVar.f1004h = 0;
                        }
                    }
                } else if (z2 && i6 < mVar.f1013r.length()) {
                    Matcher matcher3 = Pattern.compile("\\p{L}(\\b)").matcher(mVar.f1013r.substring(mVar.f1004h));
                    matcher3.find();
                    if (matcher3.find()) {
                        mVar.f1004h = matcher3.start(1) + mVar.f1004h;
                    } else {
                        mVar.f1004h = mVar.f1013r.length();
                    }
                } else if (!z2 && mVar.f1004h > 0) {
                    Matcher matcher4 = Pattern.compile("(?s:.*)(\\b)\\p{L}").matcher(mVar.f1013r.substring(0, mVar.f1004h));
                    if (matcher4.find()) {
                        mVar.f1004h = matcher4.start(1);
                    }
                }
            } else if (z2 && i6 < mVar.f1013r.length()) {
                mVar.f1004h++;
            } else if (!z2 && (i3 = mVar.f1004h) > 0) {
                mVar.f1004h = i3 - 1;
            }
            if (!z3) {
                mVar.f1003g = mVar.f1004h;
            }
        }
        if (i5 != mVar.f1003g || i6 != mVar.f1004h) {
            String str = mVar.f1013r;
            if (str == null) {
                str = "";
            }
            AccessibilityEvent accessibilityEventD = d(mVar.f994b, 8192);
            accessibilityEventD.getText().add(str);
            accessibilityEventD.setFromIndex(mVar.f1003g);
            accessibilityEventD.setToIndex(mVar.f1004h);
            accessibilityEventD.setItemCount(str.length());
            h(accessibilityEventD);
        }
        G.f fVar = this.f1024b;
        if (i4 == 1) {
            if (z2) {
                g gVar = g.MOVE_CURSOR_FORWARD_BY_CHARACTER;
                if (mVar.g(gVar)) {
                    fVar.b(i2, gVar, Boolean.valueOf(z3));
                    return true;
                }
            }
            if (!z2) {
                g gVar2 = g.MOVE_CURSOR_BACKWARD_BY_CHARACTER;
                if (mVar.g(gVar2)) {
                    fVar.b(i2, gVar2, Boolean.valueOf(z3));
                    return true;
                }
            }
        } else if (i4 == 2) {
            if (z2) {
                g gVar3 = g.MOVE_CURSOR_FORWARD_BY_WORD;
                if (mVar.g(gVar3)) {
                    fVar.b(i2, gVar3, Boolean.valueOf(z3));
                    return true;
                }
            }
            if (!z2) {
                g gVar4 = g.MOVE_CURSOR_BACKWARD_BY_WORD;
                if (mVar.g(gVar4)) {
                    fVar.b(i2, gVar4, Boolean.valueOf(z3));
                    return true;
                }
            }
        } else if (i4 == 4 || i4 == 8 || i4 == 16) {
            return true;
        }
        return false;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo findFocus(int i2) {
        if (i2 == 1) {
            m mVar = this.m;
            if (mVar != null) {
                return createAccessibilityNodeInfo(mVar.f994b);
            }
        } else if (i2 != 2) {
            return null;
        }
        m mVar2 = this.f1031i;
        if (mVar2 != null) {
            return createAccessibilityNodeInfo(mVar2.f994b);
        }
        Integer num = this.f1032j;
        if (num != null) {
            return createAccessibilityNodeInfo(num.intValue());
        }
        return null;
    }

    public final void g(int i2, int i3) {
        if (this.f1025c.isEnabled()) {
            h(d(i2, i3));
        }
    }

    public final void h(AccessibilityEvent accessibilityEvent) {
        if (this.f1025c.isEnabled()) {
            View view = this.f1023a;
            view.getParent().requestSendAccessibilityEvent(view, accessibilityEvent);
        }
    }

    public final void i(int i2, int i3) {
        AccessibilityEvent accessibilityEventD = d(i2, 2048);
        accessibilityEventD.setContentChangeTypes(i3);
        h(accessibilityEventD);
    }

    public final void j(boolean z2) {
        if (this.f1040s == z2) {
            return;
        }
        this.f1040s = z2;
        m(1, z2);
    }

    public final void k() {
        UiModeManager uiModeManager = (UiModeManager) this.f1023a.getContext().getSystemService("uimode");
        if (uiModeManager == null) {
            m(6, false);
        } else {
            m(6, uiModeManager.getContrast() > 0.0f);
        }
    }

    public final boolean l(m mVar) {
        if (mVar.f1006j > 1) {
            m mVar2 = this.f1031i;
            m mVar3 = null;
            if (mVar2 != null) {
                m mVar4 = mVar2.f988V;
                while (true) {
                    if (mVar4 == null) {
                        mVar4 = null;
                        break;
                    }
                    if (mVar4 == mVar) {
                        break;
                    }
                    mVar4 = mVar4.f988V;
                }
                if (mVar4 != null) {
                    return true;
                }
            }
            m mVar5 = this.f1031i;
            C0016b c0016b = new C0016b();
            if (mVar5 != null) {
                for (m mVar6 = mVar5.f988V; mVar6 != null; mVar6 = mVar6.f988V) {
                    if (c0016b.test(mVar6)) {
                        mVar3 = mVar6;
                        break;
                    }
                }
                if (mVar3 != null) {
                }
            }
            return true;
        }
        return false;
    }

    public final void m(int i2, boolean z2) {
        if (z2) {
            this.f1033k = C0.z.d(i2) | this.f1033k;
        } else {
            this.f1033k = (~C0.z.d(i2)) & this.f1033k;
        }
        ((FlutterJNI) this.f1024b.f165e).setAccessibilityFeatures(this.f1033k);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final boolean performAction(int i2, int i3, Bundle bundle) {
        if (i2 >= 65536) {
            boolean zPerformAction = this.f1026d.performAction(i2, i3, bundle);
            if (zPerformAction && i3 == 128) {
                this.f1032j = null;
            }
            return zPerformAction;
        }
        Integer numValueOf = Integer.valueOf(i2);
        HashMap map = this.f1029g;
        m mVar = (m) map.get(numValueOf);
        if (mVar != null) {
            g gVar = g.INCREASE;
            g gVar2 = g.DECREASE;
            G.f fVar = this.f1024b;
            switch (i3) {
                case 16:
                    fVar.a(i2, g.TAP);
                    return true;
                case 32:
                    fVar.a(i2, g.LONG_PRESS);
                    return true;
                case 64:
                    if (this.f1031i == null) {
                        this.f1023a.invalidate();
                    }
                    this.f1031i = mVar;
                    fVar.a(i2, g.DID_GAIN_ACCESSIBILITY_FOCUS);
                    HashMap map2 = new HashMap();
                    map2.put("type", "didGainFocus");
                    map2.put("nodeId", Integer.valueOf(mVar.f994b));
                    ((G.f) fVar.f164d).g(map2, null);
                    g(i2, 32768);
                    if (!mVar.g(gVar) && !mVar.g(gVar2)) {
                        return true;
                    }
                    g(i2, 4);
                    return true;
                case 128:
                    m mVar2 = this.f1031i;
                    if (mVar2 != null && mVar2.f994b == i2) {
                        this.f1031i = null;
                    }
                    Integer num = this.f1032j;
                    if (num != null && num.intValue() == i2) {
                        this.f1032j = null;
                    }
                    fVar.a(i2, g.DID_LOSE_ACCESSIBILITY_FOCUS);
                    g(i2, 65536);
                    return true;
                case 256:
                    return f(mVar, i2, bundle, true);
                case 512:
                    return f(mVar, i2, bundle, false);
                case 4096:
                    g gVar3 = g.SCROLL_UP;
                    if (mVar.g(gVar3)) {
                        fVar.a(i2, gVar3);
                        return true;
                    }
                    g gVar4 = g.SCROLL_LEFT;
                    if (mVar.g(gVar4)) {
                        fVar.a(i2, gVar4);
                        return true;
                    }
                    if (mVar.g(gVar)) {
                        mVar.f1013r = mVar.f1015t;
                        mVar.f1014s = mVar.f1016u;
                        g(i2, 4);
                        fVar.a(i2, gVar);
                        return true;
                    }
                    break;
                case 8192:
                    g gVar5 = g.SCROLL_DOWN;
                    if (mVar.g(gVar5)) {
                        fVar.a(i2, gVar5);
                        return true;
                    }
                    g gVar6 = g.SCROLL_RIGHT;
                    if (mVar.g(gVar6)) {
                        fVar.a(i2, gVar6);
                        return true;
                    }
                    if (mVar.g(gVar2)) {
                        mVar.f1013r = mVar.v;
                        mVar.f1014s = mVar.f1017w;
                        g(i2, 4);
                        fVar.a(i2, gVar2);
                        return true;
                    }
                    break;
                case 16384:
                    fVar.a(i2, g.COPY);
                    return true;
                case 32768:
                    fVar.a(i2, g.PASTE);
                    return true;
                case 65536:
                    fVar.a(i2, g.CUT);
                    return true;
                case 131072:
                    HashMap map3 = new HashMap();
                    if (bundle != null && bundle.containsKey("ACTION_ARGUMENT_SELECTION_START_INT") && bundle.containsKey("ACTION_ARGUMENT_SELECTION_END_INT")) {
                        map3.put("base", Integer.valueOf(bundle.getInt("ACTION_ARGUMENT_SELECTION_START_INT")));
                        map3.put("extent", Integer.valueOf(bundle.getInt("ACTION_ARGUMENT_SELECTION_END_INT")));
                    } else {
                        map3.put("base", Integer.valueOf(mVar.f1004h));
                        map3.put("extent", Integer.valueOf(mVar.f1004h));
                    }
                    fVar.b(i2, g.SET_SELECTION, map3);
                    m mVar3 = (m) map.get(Integer.valueOf(i2));
                    mVar3.f1003g = ((Integer) map3.get("base")).intValue();
                    mVar3.f1004h = ((Integer) map3.get("extent")).intValue();
                    return true;
                case 262144:
                    fVar.a(i2, g.EXPAND);
                    return true;
                case 524288:
                    fVar.a(i2, g.COLLAPSE);
                    return true;
                case 1048576:
                    fVar.a(i2, g.DISMISS);
                    return true;
                case 2097152:
                    String string = (bundle == null || !bundle.containsKey("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE")) ? "" : bundle.getString("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE");
                    fVar.b(i2, g.SET_TEXT, string);
                    mVar.f1013r = string;
                    mVar.f1014s = null;
                    return true;
                case R.id.accessibilityActionShowOnScreen:
                    fVar.a(i2, g.SHOW_ON_SCREEN);
                    return true;
                default:
                    j jVar = (j) this.f1030h.get(Integer.valueOf(i3 - f1022z));
                    if (jVar != null) {
                        fVar.b(i2, g.CUSTOM_ACTION, Integer.valueOf(jVar.f961b));
                        return true;
                    }
                    break;
            }
        }
        return false;
    }
}
