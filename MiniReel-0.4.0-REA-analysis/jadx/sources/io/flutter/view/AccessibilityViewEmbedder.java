package io.flutter.view;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.AccessibilityRecord;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class AccessibilityViewEmbedder {
    private static final String TAG = "AccessibilityBridge";
    private int nextFlutterId;
    private final View rootAccessibilityView;
    private final r reflectionAccessors = new r();
    private final SparseArray<s> flutterIdToOrigin = new SparseArray<>();
    private final Map<s, Integer> originToFlutterId = new HashMap();
    private final Map<View, Rect> embeddedViewToDisplayBounds = new HashMap();

    public AccessibilityViewEmbedder(View view, int i2) {
        this.rootAccessibilityView = view;
        this.nextFlutterId = i2;
    }

    private void addChildrenToFlutterNode(AccessibilityNodeInfo accessibilityNodeInfo, View view, AccessibilityNodeInfo accessibilityNodeInfo2) {
        Long l2;
        int iIntValue;
        for (int i2 = 0; i2 < accessibilityNodeInfo.getChildCount(); i2++) {
            r rVar = this.reflectionAccessors;
            Method method = rVar.f1055f;
            Field field = rVar.f1054e;
            Method method2 = rVar.f1053d;
            if (method2 == null && (field == null || method == null)) {
                l2 = null;
            } else if (method2 != null) {
                try {
                    l2 = (Long) method2.invoke(accessibilityNodeInfo, Integer.valueOf(i2));
                } catch (IllegalAccessException e2) {
                    Log.w(TAG, "Failed to access getChildId method.", e2);
                    l2 = null;
                } catch (InvocationTargetException e3) {
                    Log.w(TAG, "The getChildId method threw an exception when invoked.", e3);
                    l2 = null;
                }
            } else {
                try {
                    l2 = (Long) method.invoke(field.get(accessibilityNodeInfo), Integer.valueOf(i2));
                    l2.getClass();
                } catch (ArrayIndexOutOfBoundsException e4) {
                    e = e4;
                    Log.w(TAG, "The longArrayGetIndex method threw an exception when invoked.", e);
                    l2 = null;
                } catch (IllegalAccessException e5) {
                    Log.w(TAG, "Failed to access longArrayGetIndex method or the childNodeId field.", e5);
                    l2 = null;
                } catch (InvocationTargetException e6) {
                    e = e6;
                    Log.w(TAG, "The longArrayGetIndex method threw an exception when invoked.", e);
                    l2 = null;
                }
            }
            if (l2 != null) {
                int iLongValue = (int) (l2.longValue() >> 32);
                s sVar = new s(view, iLongValue);
                if (this.originToFlutterId.containsKey(sVar)) {
                    iIntValue = this.originToFlutterId.get(sVar).intValue();
                } else {
                    int i3 = this.nextFlutterId;
                    this.nextFlutterId = i3 + 1;
                    cacheVirtualIdMappings(view, iLongValue, i3);
                    iIntValue = i3;
                }
                accessibilityNodeInfo2.addChild(this.rootAccessibilityView, iIntValue);
            }
        }
    }

    private void cacheVirtualIdMappings(View view, int i2, int i3) {
        s sVar = new s(view, i2);
        this.originToFlutterId.put(sVar, Integer.valueOf(i3));
        this.flutterIdToOrigin.put(i3, sVar);
    }

    private AccessibilityNodeInfo convertToFlutterNode(AccessibilityNodeInfo accessibilityNodeInfo, int i2, View view) {
        AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(this.rootAccessibilityView, i2);
        accessibilityNodeInfoObtain.setPackageName(this.rootAccessibilityView.getContext().getPackageName());
        accessibilityNodeInfoObtain.setSource(this.rootAccessibilityView, i2);
        accessibilityNodeInfoObtain.setClassName(accessibilityNodeInfo.getClassName());
        Rect rect = this.embeddedViewToDisplayBounds.get(view);
        copyAccessibilityFields(accessibilityNodeInfo, accessibilityNodeInfoObtain);
        setFlutterNodesTranslateBounds(accessibilityNodeInfo, rect, accessibilityNodeInfoObtain);
        addChildrenToFlutterNode(accessibilityNodeInfo, view, accessibilityNodeInfoObtain);
        setFlutterNodeParent(accessibilityNodeInfo, view, accessibilityNodeInfoObtain);
        return accessibilityNodeInfoObtain;
    }

    private void copyAccessibilityFields(AccessibilityNodeInfo accessibilityNodeInfo, AccessibilityNodeInfo accessibilityNodeInfo2) {
        accessibilityNodeInfo2.setAccessibilityFocused(accessibilityNodeInfo.isAccessibilityFocused());
        accessibilityNodeInfo2.setCheckable(accessibilityNodeInfo.isCheckable());
        accessibilityNodeInfo2.setChecked(accessibilityNodeInfo.isChecked());
        accessibilityNodeInfo2.setContentDescription(accessibilityNodeInfo.getContentDescription());
        accessibilityNodeInfo2.setEnabled(accessibilityNodeInfo.isEnabled());
        accessibilityNodeInfo2.setClickable(accessibilityNodeInfo.isClickable());
        accessibilityNodeInfo2.setFocusable(accessibilityNodeInfo.isFocusable());
        accessibilityNodeInfo2.setFocused(accessibilityNodeInfo.isFocused());
        accessibilityNodeInfo2.setLongClickable(accessibilityNodeInfo.isLongClickable());
        accessibilityNodeInfo2.setMovementGranularities(accessibilityNodeInfo.getMovementGranularities());
        accessibilityNodeInfo2.setPassword(accessibilityNodeInfo.isPassword());
        accessibilityNodeInfo2.setScrollable(accessibilityNodeInfo.isScrollable());
        accessibilityNodeInfo2.setSelected(accessibilityNodeInfo.isSelected());
        accessibilityNodeInfo2.setText(accessibilityNodeInfo.getText());
        accessibilityNodeInfo2.setVisibleToUser(accessibilityNodeInfo.isVisibleToUser());
        accessibilityNodeInfo2.setEditable(accessibilityNodeInfo.isEditable());
        accessibilityNodeInfo2.setCanOpenPopup(accessibilityNodeInfo.canOpenPopup());
        accessibilityNodeInfo2.setCollectionInfo(accessibilityNodeInfo.getCollectionInfo());
        accessibilityNodeInfo2.setCollectionItemInfo(accessibilityNodeInfo.getCollectionItemInfo());
        accessibilityNodeInfo2.setContentInvalid(accessibilityNodeInfo.isContentInvalid());
        accessibilityNodeInfo2.setDismissable(accessibilityNodeInfo.isDismissable());
        accessibilityNodeInfo2.setInputType(accessibilityNodeInfo.getInputType());
        accessibilityNodeInfo2.setLiveRegion(accessibilityNodeInfo.getLiveRegion());
        accessibilityNodeInfo2.setMultiLine(accessibilityNodeInfo.isMultiLine());
        accessibilityNodeInfo2.setRangeInfo(accessibilityNodeInfo.getRangeInfo());
        accessibilityNodeInfo2.setError(accessibilityNodeInfo.getError());
        accessibilityNodeInfo2.setMaxTextLength(accessibilityNodeInfo.getMaxTextLength());
        accessibilityNodeInfo2.setContextClickable(accessibilityNodeInfo.isContextClickable());
        accessibilityNodeInfo2.setDrawingOrder(accessibilityNodeInfo.getDrawingOrder());
        accessibilityNodeInfo2.setImportantForAccessibility(accessibilityNodeInfo.isImportantForAccessibility());
        if (Build.VERSION.SDK_INT >= 26) {
            accessibilityNodeInfo2.setAvailableExtraData(accessibilityNodeInfo.getAvailableExtraData());
            accessibilityNodeInfo2.setHintText(accessibilityNodeInfo.getHintText());
            accessibilityNodeInfo2.setShowingHintText(accessibilityNodeInfo.isShowingHintText());
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0028  */
    /* JADX WARN: Code duplicated, block: B:15:0x002e  */
    /* JADX WARN: Code duplicated, block: B:17:0x004a  */
    /* JADX WARN: Code duplicated, block: B:20:0x0054  */
    /* JADX WARN: Code duplicated, block: B:23:0x005e  */
    /* JADX WARN: Code duplicated, block: B:26:0x0068  */
    private void setFlutterNodeParent(AccessibilityNodeInfo accessibilityNodeInfo, View view, AccessibilityNodeInfo accessibilityNodeInfo2) {
        Long l2;
        Parcel parcelObtain;
        long j2;
        Method method = this.reflectionAccessors.f1051b;
        Long lValueOf = null;
        if (method != null) {
            try {
                l2 = (Long) method.invoke(accessibilityNodeInfo, null);
                l2.getClass();
            } catch (IllegalAccessException e2) {
                Log.w(TAG, "Failed to access getParentNodeId method.", e2);
                if (Build.VERSION.SDK_INT < 26) {
                    Log.w(TAG, "Unexpected Android version. Unable to find the parent ID.");
                } else {
                    AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(accessibilityNodeInfo);
                    parcelObtain = Parcel.obtain();
                    parcelObtain.setDataPosition(0);
                    accessibilityNodeInfoObtain.writeToParcel(parcelObtain, 0);
                    parcelObtain.setDataPosition(0);
                    j2 = parcelObtain.readLong();
                    if (r.b(j2, 0)) {
                        parcelObtain.readInt();
                    }
                    if (r.b(j2, 1)) {
                        parcelObtain.readLong();
                    }
                    if (r.b(j2, 2)) {
                        parcelObtain.readInt();
                    }
                    lValueOf = r.b(j2, 3) ? Long.valueOf(parcelObtain.readLong()) : null;
                    parcelObtain.recycle();
                }
                l2 = lValueOf;
            } catch (InvocationTargetException e3) {
                Log.w(TAG, "The getParentNodeId method threw an exception when invoked.", e3);
                if (Build.VERSION.SDK_INT < 26) {
                    Log.w(TAG, "Unexpected Android version. Unable to find the parent ID.");
                } else {
                    AccessibilityNodeInfo accessibilityNodeInfoObtain2 = AccessibilityNodeInfo.obtain(accessibilityNodeInfo);
                    parcelObtain = Parcel.obtain();
                    parcelObtain.setDataPosition(0);
                    accessibilityNodeInfoObtain2.writeToParcel(parcelObtain, 0);
                    parcelObtain.setDataPosition(0);
                    j2 = parcelObtain.readLong();
                    if (r.b(j2, 0)) {
                        parcelObtain.readInt();
                    }
                    if (r.b(j2, 1)) {
                        parcelObtain.readLong();
                    }
                    if (r.b(j2, 2)) {
                        parcelObtain.readInt();
                    }
                    if (r.b(j2, 3)) {
                    }
                    parcelObtain.recycle();
                }
                l2 = lValueOf;
            }
        } else {
            if (Build.VERSION.SDK_INT < 26) {
                Log.w(TAG, "Unexpected Android version. Unable to find the parent ID.");
            } else {
                AccessibilityNodeInfo accessibilityNodeInfoObtain3 = AccessibilityNodeInfo.obtain(accessibilityNodeInfo);
                parcelObtain = Parcel.obtain();
                parcelObtain.setDataPosition(0);
                accessibilityNodeInfoObtain3.writeToParcel(parcelObtain, 0);
                parcelObtain.setDataPosition(0);
                j2 = parcelObtain.readLong();
                if (r.b(j2, 0)) {
                    parcelObtain.readInt();
                }
                if (r.b(j2, 1)) {
                    parcelObtain.readLong();
                }
                if (r.b(j2, 2)) {
                    parcelObtain.readInt();
                }
                if (r.b(j2, 3)) {
                }
                parcelObtain.recycle();
            }
            l2 = lValueOf;
        }
        if (l2 == null) {
            return;
        }
        Integer num = this.originToFlutterId.get(new s(view, (int) (l2.longValue() >> 32)));
        if (num != null) {
            accessibilityNodeInfo2.setParent(this.rootAccessibilityView, num.intValue());
        }
    }

    private void setFlutterNodesTranslateBounds(AccessibilityNodeInfo accessibilityNodeInfo, Rect rect, AccessibilityNodeInfo accessibilityNodeInfo2) {
        Rect rect2 = new Rect();
        accessibilityNodeInfo.getBoundsInParent(rect2);
        accessibilityNodeInfo2.setBoundsInParent(rect2);
        Rect rect3 = new Rect();
        accessibilityNodeInfo.getBoundsInScreen(rect3);
        rect3.offset(rect.left, rect.top);
        accessibilityNodeInfo2.setBoundsInScreen(rect3);
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i2) {
        AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo;
        s sVar = this.flutterIdToOrigin.get(i2);
        if (sVar == null) {
            return null;
        }
        View view = sVar.f1056a;
        if (!this.embeddedViewToDisplayBounds.containsKey(view) || view.getAccessibilityNodeProvider() == null || (accessibilityNodeInfoCreateAccessibilityNodeInfo = view.getAccessibilityNodeProvider().createAccessibilityNodeInfo(sVar.f1057b)) == null) {
            return null;
        }
        return convertToFlutterNode(accessibilityNodeInfoCreateAccessibilityNodeInfo, i2, view);
    }

    public Integer getRecordFlutterId(View view, AccessibilityRecord accessibilityRecord) {
        Long lA = r.a(this.reflectionAccessors, accessibilityRecord);
        if (lA == null) {
            return null;
        }
        return this.originToFlutterId.get(new s(view, (int) (lA.longValue() >> 32)));
    }

    public AccessibilityNodeInfo getRootNode(View view, int i2, Rect rect) {
        Long l2;
        AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = view.createAccessibilityNodeInfo();
        Method method = this.reflectionAccessors.f1050a;
        if (method == null) {
            l2 = null;
        } else {
            try {
                l2 = (Long) method.invoke(accessibilityNodeInfoCreateAccessibilityNodeInfo, null);
            } catch (IllegalAccessException e2) {
                Log.w(TAG, "Failed to access getSourceNodeId method.", e2);
                l2 = null;
            } catch (InvocationTargetException e3) {
                Log.w(TAG, "The getSourceNodeId method threw an exception when invoked.", e3);
                l2 = null;
            }
        }
        if (l2 == null) {
            return null;
        }
        this.embeddedViewToDisplayBounds.put(view, rect);
        cacheVirtualIdMappings(view, (int) (l2.longValue() >> 32), i2);
        return convertToFlutterNode(accessibilityNodeInfoCreateAccessibilityNodeInfo, i2, view);
    }

    public boolean onAccessibilityHoverEvent(int i2, MotionEvent motionEvent) {
        s sVar = this.flutterIdToOrigin.get(i2);
        if (sVar == null) {
            return false;
        }
        View view = sVar.f1056a;
        Rect rect = this.embeddedViewToDisplayBounds.get(view);
        int pointerCount = motionEvent.getPointerCount();
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i3 = 0; i3 < motionEvent.getPointerCount(); i3++) {
            MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
            pointerPropertiesArr[i3] = pointerProperties;
            motionEvent.getPointerProperties(i3, pointerProperties);
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            motionEvent.getPointerCoords(i3, pointerCoords);
            MotionEvent.PointerCoords pointerCoords2 = new MotionEvent.PointerCoords(pointerCoords);
            pointerCoordsArr[i3] = pointerCoords2;
            pointerCoords2.x -= rect.left;
            pointerCoords2.y -= rect.top;
        }
        return view.dispatchGenericMotionEvent(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getPointerCount(), pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags()));
    }

    public boolean performAction(int i2, int i3, Bundle bundle) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        s sVar = this.flutterIdToOrigin.get(i2);
        if (sVar == null || (accessibilityNodeProvider = sVar.f1056a.getAccessibilityNodeProvider()) == null) {
            return false;
        }
        return accessibilityNodeProvider.performAction(sVar.f1057b, i3, bundle);
    }

    public View platformViewOfNode(int i2) {
        s sVar = this.flutterIdToOrigin.get(i2);
        if (sVar == null) {
            return null;
        }
        return sVar.f1056a;
    }

    public boolean requestSendAccessibilityEvent(View view, View view2, AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(accessibilityEvent);
        Long lA = r.a(this.reflectionAccessors, accessibilityEvent);
        if (lA != null) {
            int iLongValue = (int) (lA.longValue() >> 32);
            Integer num = this.originToFlutterId.get(new s(view, iLongValue));
            if (num == null) {
                int i2 = this.nextFlutterId;
                this.nextFlutterId = i2 + 1;
                Integer numValueOf = Integer.valueOf(i2);
                cacheVirtualIdMappings(view, iLongValue, i2);
                num = numValueOf;
            }
            accessibilityEventObtain.setSource(this.rootAccessibilityView, num.intValue());
            accessibilityEventObtain.setClassName(accessibilityEvent.getClassName());
            accessibilityEventObtain.setPackageName(accessibilityEvent.getPackageName());
            for (int i3 = 0; i3 < accessibilityEventObtain.getRecordCount(); i3++) {
                AccessibilityRecord record = accessibilityEventObtain.getRecord(i3);
                Long lA2 = r.a(this.reflectionAccessors, record);
                if (lA2 != null) {
                    s sVar = new s(view, (int) (lA2.longValue() >> 32));
                    if (this.originToFlutterId.containsKey(sVar)) {
                        record.setSource(this.rootAccessibilityView, this.originToFlutterId.get(sVar).intValue());
                    }
                }
            }
            return this.rootAccessibilityView.getParent().requestSendAccessibilityEvent(view2, accessibilityEventObtain);
        }
        return false;
    }
}
