package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.lang.reflect.InvocationTargetException;
import p009i.a;

/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final PorterDuff.Mode f615k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f617b;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f625j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f616a = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f618c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Parcelable f619d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f620e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f621f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f622g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f623h = f615k;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f624i = null;

    public final String toString() {
        String str;
        int iIntValue;
        if (this.f616a == -1) {
            return String.valueOf(this.f617b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (this.f616a) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb.append(str);
        switch (this.f616a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f617b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f617b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.f625j);
                sb.append(" id=");
                int i2 = this.f616a;
                if (i2 == -1) {
                    int i3 = Build.VERSION.SDK_INT;
                    Object obj = this.f617b;
                    if (i3 >= 28) {
                        iIntValue = a.a(obj);
                    } else {
                        try {
                            iIntValue = ((Integer) obj.getClass().getMethod("getResId", null).invoke(obj, null)).intValue();
                        } catch (IllegalAccessException e2) {
                            Log.e("IconCompat", "Unable to get icon resource", e2);
                            iIntValue = 0;
                        } catch (NoSuchMethodException e3) {
                            Log.e("IconCompat", "Unable to get icon resource", e3);
                            iIntValue = 0;
                        } catch (InvocationTargetException e4) {
                            Log.e("IconCompat", "Unable to get icon resource", e4);
                            iIntValue = 0;
                        }
                    }
                } else {
                    if (i2 != 2) {
                        throw new IllegalStateException("called getResId() on " + this);
                    }
                    iIntValue = this.f620e;
                }
                sb.append(String.format("0x%08x", Integer.valueOf(iIntValue)));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f620e);
                if (this.f621f != 0) {
                    sb.append(" off=");
                    sb.append(this.f621f);
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f617b);
                break;
        }
        if (this.f622g != null) {
            sb.append(" tint=");
            sb.append(this.f622g);
        }
        if (this.f623h != f615k) {
            sb.append(" mode=");
            sb.append(this.f623h);
        }
        sb.append(")");
        return sb.toString();
    }
}
