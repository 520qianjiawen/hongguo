package J;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f257a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f258b;

    public t(String str, List list) {
        this.f257a = str;
        this.f258b = list == null ? new ArrayList() : list;
    }

    public static Object b(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof List)) {
            return obj;
        }
        List list = (List) obj;
        byte[] bArr = new byte[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            bArr[i2] = (byte) ((Integer) list.get(i2)).intValue();
        }
        return bArr;
    }

    public final Object[] a() {
        ArrayList arrayList = new ArrayList();
        List list = this.f258b;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(b(it.next()));
            }
        }
        return arrayList.toArray(new Object[0]);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        String str = this.f257a;
        if (str != null) {
            if (!str.equals(tVar.f257a)) {
                return false;
            }
        } else if (tVar.f257a != null) {
            return false;
        }
        List list = this.f258b;
        int size = list.size();
        List list2 = tVar.f258b;
        List list3 = tVar.f258b;
        if (size != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if ((list.get(i2) instanceof byte[]) && (list3.get(i2) instanceof byte[])) {
                if (!Arrays.equals((byte[]) list.get(i2), (byte[]) list3.get(i2))) {
                    return false;
                }
            } else if (!list.get(i2).equals(list3.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        String str = this.f257a;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f257a);
        List list = this.f258b;
        if (list == null || list.isEmpty()) {
            str = "";
        } else {
            str = " " + list;
        }
        sb.append(str);
        return sb.toString();
    }
}
