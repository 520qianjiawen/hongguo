package S;

import C0.x;
import P.l;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class b extends x {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f494f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(l lVar, int i2) {
        super(8, lVar);
        this.f494f = i2;
    }

    @Override // C0.x
    public final Bitmap l(ByteBuffer byteBuffer, d dVar) {
        switch (this.f494f) {
            case 0:
                Bitmap bitmapL = super.l(byteBuffer, dVar);
                if (bitmapL != null) {
                    return bitmapL;
                }
                int iRemaining = byteBuffer.remaining();
                byte[] bArr = new byte[iRemaining];
                byteBuffer.get(bArr);
                byteBuffer.rewind();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, iRemaining, options);
                if (dVar.f498c == 0) {
                    return p000a.a.b(bitmapDecodeByteArray, dVar.f500e);
                }
                Matrix matrix = new Matrix();
                matrix.postRotate(dVar.f498c);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, true);
                bitmapDecodeByteArray.recycle();
                return p000a.a.b(bitmapCreateBitmap, dVar.f500e);
            default:
                return p000a.a.b(super.l(byteBuffer, dVar), dVar.f500e);
        }
    }
}
