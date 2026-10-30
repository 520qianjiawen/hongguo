package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import p017r.b;
import p017r.c;
import p017r.d;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(b bVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        d dVarG = remoteActionCompat.f609a;
        boolean z2 = true;
        if (bVar.e(1)) {
            dVarG = bVar.g();
        }
        remoteActionCompat.f609a = (IconCompat) dVarG;
        CharSequence charSequence = remoteActionCompat.f610b;
        if (bVar.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((c) bVar).f1225e);
        }
        remoteActionCompat.f610b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.f611c;
        if (bVar.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((c) bVar).f1225e);
        }
        remoteActionCompat.f611c = charSequence2;
        remoteActionCompat.f612d = (PendingIntent) bVar.f(remoteActionCompat.f612d, 4);
        boolean z3 = remoteActionCompat.f613e;
        if (bVar.e(5)) {
            z3 = ((c) bVar).f1225e.readInt() != 0;
        }
        remoteActionCompat.f613e = z3;
        boolean z4 = remoteActionCompat.f614f;
        if (!bVar.e(6)) {
            z2 = z4;
        } else if (((c) bVar).f1225e.readInt() == 0) {
            z2 = false;
        }
        remoteActionCompat.f614f = z2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, b bVar) {
        bVar.getClass();
        IconCompat iconCompat = remoteActionCompat.f609a;
        bVar.h(1);
        bVar.i(iconCompat);
        CharSequence charSequence = remoteActionCompat.f610b;
        bVar.h(2);
        Parcel parcel = ((c) bVar).f1225e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.f611c;
        bVar.h(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.f612d;
        bVar.h(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z2 = remoteActionCompat.f613e;
        bVar.h(5);
        parcel.writeInt(z2 ? 1 : 0);
        boolean z3 = remoteActionCompat.f614f;
        bVar.h(6);
        parcel.writeInt(z3 ? 1 : 0);
    }
}
