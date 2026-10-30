package Z;

import java.nio.ByteBuffer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: classes.dex */
public final class j implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f593a = new j();

    @Override // Z.l
    public final Object a(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        try {
            r.f600b.getClass();
            JSONTokener jSONTokener = new JSONTokener(r.c(byteBuffer));
            Object objNextValue = jSONTokener.nextValue();
            if (jSONTokener.more()) {
                throw new IllegalArgumentException("Invalid JSON");
            }
            return objNextValue;
        } catch (JSONException e2) {
            throw new IllegalArgumentException("Invalid JSON", e2);
        }
    }

    @Override // Z.l
    public final ByteBuffer b(Object obj) {
        if (obj == null) {
            return null;
        }
        Object objS = p000a.a.S(obj);
        if (objS instanceof String) {
            r rVar = r.f600b;
            String strQuote = JSONObject.quote((String) objS);
            rVar.getClass();
            return r.d(strQuote);
        }
        r rVar2 = r.f600b;
        String string = objS.toString();
        rVar2.getClass();
        return r.d(string);
    }
}
