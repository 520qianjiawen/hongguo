package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import p017r.a;
import p017r.c;
import p017r.d;

/* JADX INFO: loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f651d;

    public ParcelImpl(Parcel parcel) {
        this.f651d = new c(parcel).g();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        new c(parcel).i(this.f651d);
    }
}
