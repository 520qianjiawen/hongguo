package D0;

import android.view.KeyCharacterMap;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f133a;

    public i() {
        this.f133a = 0;
    }

    public Character a(int i2) {
        char c2 = (char) i2;
        if ((Integer.MIN_VALUE & i2) != 0) {
            int i3 = i2 & Integer.MAX_VALUE;
            int i4 = this.f133a;
            if (i4 != 0) {
                this.f133a = KeyCharacterMap.getDeadChar(i4, i3);
            } else {
                this.f133a = i3;
            }
        } else {
            int i5 = this.f133a;
            if (i5 != 0) {
                int deadChar = KeyCharacterMap.getDeadChar(i5, i2);
                if (deadChar > 0) {
                    c2 = (char) deadChar;
                }
                this.f133a = 0;
            }
        }
        return Character.valueOf(c2);
    }

    public i(int i2) {
        this.f133a = i2;
    }
}
