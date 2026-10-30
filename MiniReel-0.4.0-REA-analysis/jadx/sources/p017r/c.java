package p017r;

import android.os.Parcel;
import android.util.SparseIntArray;
import e.a;

/* JADX INFO: loaded from: classes.dex */
public final class c extends b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f1224d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f1225e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1226f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1227g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f1228h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1229i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1230j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1231k;

    public c(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new a(), new a(), new a());
    }

    @Override // p017r.b
    public final c a() {
        Parcel parcel = this.f1225e;
        int iDataPosition = parcel.dataPosition();
        int i2 = this.f1230j;
        if (i2 == this.f1226f) {
            i2 = this.f1227g;
        }
        return new c(parcel, iDataPosition, i2, this.f1228h + "  ", this.f1221a, this.f1222b, this.f1223c);
    }

    @Override // p017r.b
    public final boolean e(int i2) {
        while (this.f1230j < this.f1227g) {
            int i3 = this.f1231k;
            if (i3 == i2) {
                return true;
            }
            if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            int i4 = this.f1230j;
            Parcel parcel = this.f1225e;
            parcel.setDataPosition(i4);
            int i5 = parcel.readInt();
            this.f1231k = parcel.readInt();
            this.f1230j += i5;
        }
        return this.f1231k == i2;
    }

    @Override // p017r.b
    public final void h(int i2) {
        int i3 = this.f1229i;
        SparseIntArray sparseIntArray = this.f1224d;
        Parcel parcel = this.f1225e;
        if (i3 >= 0) {
            int i4 = sparseIntArray.get(i3);
            int iDataPosition = parcel.dataPosition();
            parcel.setDataPosition(i4);
            parcel.writeInt(iDataPosition - i4);
            parcel.setDataPosition(iDataPosition);
        }
        this.f1229i = i2;
        sparseIntArray.put(i2, parcel.dataPosition());
        parcel.writeInt(0);
        parcel.writeInt(i2);
    }

    public c(Parcel parcel, int i2, int i3, String str, a aVar, a aVar2, a aVar3) {
        super(aVar, aVar2, aVar3);
        this.f1224d = new SparseIntArray();
        this.f1229i = -1;
        this.f1231k = -1;
        this.f1225e = parcel;
        this.f1226f = i2;
        this.f1227g = i3;
        this.f1230j = i2;
        this.f1228h = str;
    }
}
