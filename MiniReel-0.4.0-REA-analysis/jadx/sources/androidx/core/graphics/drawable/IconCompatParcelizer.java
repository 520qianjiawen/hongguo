package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.charset.Charset;
import p017r.b;
import p017r.c;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(b bVar) {
        IconCompat iconCompat = new IconCompat();
        int i2 = iconCompat.f616a;
        if (bVar.e(1)) {
            i2 = ((c) bVar).f1225e.readInt();
        }
        iconCompat.f616a = i2;
        byte[] bArr = iconCompat.f618c;
        if (bVar.e(2)) {
            Parcel parcel = ((c) bVar).f1225e;
            int i3 = parcel.readInt();
            if (i3 < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i3];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.f618c = bArr;
        iconCompat.f619d = bVar.f(iconCompat.f619d, 3);
        int i4 = iconCompat.f620e;
        if (bVar.e(4)) {
            i4 = ((c) bVar).f1225e.readInt();
        }
        iconCompat.f620e = i4;
        int i5 = iconCompat.f621f;
        if (bVar.e(5)) {
            i5 = ((c) bVar).f1225e.readInt();
        }
        iconCompat.f621f = i5;
        iconCompat.f622g = (ColorStateList) bVar.f(iconCompat.f622g, 6);
        String string = iconCompat.f624i;
        if (bVar.e(7)) {
            string = ((c) bVar).f1225e.readString();
        }
        iconCompat.f624i = string;
        String string2 = iconCompat.f625j;
        if (bVar.e(8)) {
            string2 = ((c) bVar).f1225e.readString();
        }
        iconCompat.f625j = string2;
        iconCompat.f623h = PorterDuff.Mode.valueOf(iconCompat.f624i);
        switch (iconCompat.f616a) {
            case -1:
                Parcelable parcelable = iconCompat.f619d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.f617b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.f619d;
                if (parcelable2 != null) {
                    iconCompat.f617b = parcelable2;
                    return iconCompat;
                }
                byte[] bArr3 = iconCompat.f618c;
                iconCompat.f617b = bArr3;
                iconCompat.f616a = 3;
                iconCompat.f620e = 0;
                iconCompat.f621f = bArr3.length;
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str = new String(iconCompat.f618c, Charset.forName("UTF-16"));
                iconCompat.f617b = str;
                if (iconCompat.f616a == 2 && iconCompat.f625j == null) {
                    iconCompat.f625j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.f617b = iconCompat.f618c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, b bVar) {
        bVar.getClass();
        iconCompat.f624i = iconCompat.f623h.name();
        switch (iconCompat.f616a) {
            case -1:
                iconCompat.f619d = (Parcelable) iconCompat.f617b;
                break;
            case 1:
            case 5:
                iconCompat.f619d = (Parcelable) iconCompat.f617b;
                break;
            case 2:
                iconCompat.f618c = ((String) iconCompat.f617b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.f618c = (byte[]) iconCompat.f617b;
                break;
            case 4:
            case 6:
                iconCompat.f618c = iconCompat.f617b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i2 = iconCompat.f616a;
        if (-1 != i2) {
            bVar.h(1);
            ((c) bVar).f1225e.writeInt(i2);
        }
        byte[] bArr = iconCompat.f618c;
        if (bArr != null) {
            bVar.h(2);
            Parcel parcel = ((c) bVar).f1225e;
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.f619d;
        if (parcelable != null) {
            bVar.h(3);
            ((c) bVar).f1225e.writeParcelable(parcelable, 0);
        }
        int i3 = iconCompat.f620e;
        if (i3 != 0) {
            bVar.h(4);
            ((c) bVar).f1225e.writeInt(i3);
        }
        int i4 = iconCompat.f621f;
        if (i4 != 0) {
            bVar.h(5);
            ((c) bVar).f1225e.writeInt(i4);
        }
        ColorStateList colorStateList = iconCompat.f622g;
        if (colorStateList != null) {
            bVar.h(6);
            ((c) bVar).f1225e.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.f624i;
        if (str != null) {
            bVar.h(7);
            ((c) bVar).f1225e.writeString(str);
        }
        String str2 = iconCompat.f625j;
        if (str2 != null) {
            bVar.h(8);
            ((c) bVar).f1225e.writeString(str2);
        }
    }
}
