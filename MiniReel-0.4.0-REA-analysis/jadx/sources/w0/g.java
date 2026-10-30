package w0;

import p0.h;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends f {
    public static String a(String str, String str2) {
        h.e(str2, "delimiter");
        int iIndexOf = str.indexOf(str2, 0);
        if (iIndexOf == -1) {
            return str;
        }
        String strSubstring = str.substring(str2.length() + iIndexOf, str.length());
        h.d(strSubstring, "substring(...)");
        return strSubstring;
    }
}
