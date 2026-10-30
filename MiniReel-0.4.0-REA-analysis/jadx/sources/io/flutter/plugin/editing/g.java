package io.flutter.plugin.editing;

import Y.q;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class g extends SpannableStringBuilder {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f824d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f825e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f826f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f827g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f828h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f829i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f830j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f831k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f832l;
    public int m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f833n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final e f834o;

    public g(q qVar, View view) {
        this.f834o = new e(view, this);
        if (qVar != null) {
            f(qVar);
        }
    }

    public final void a(f fVar) {
        if (this.f825e > 0) {
            Log.e("ListenableEditingState", "adding a listener " + fVar.toString() + " in a listener callback");
        }
        if (this.f824d <= 0) {
            this.f826f.add(fVar);
        } else {
            Log.w("ListenableEditingState", "a listener was added to EditingState while a batch edit was in progress");
            this.f827g.add(fVar);
        }
    }

    public final void b() {
        this.f824d++;
        if (this.f825e > 0) {
            Log.e("ListenableEditingState", "editing state should not be changed in a listener callback");
        }
        if (this.f824d != 1 || this.f826f.isEmpty()) {
            return;
        }
        this.f830j = toString();
        this.f831k = Selection.getSelectionStart(this);
        this.f832l = Selection.getSelectionEnd(this);
        this.m = BaseInputConnection.getComposingSpanStart(this);
        this.f833n = BaseInputConnection.getComposingSpanEnd(this);
    }

    public final void c() {
        int i2 = this.f824d;
        if (i2 == 0) {
            Log.e("ListenableEditingState", "endBatchEdit called without a matching beginBatchEdit");
            return;
        }
        ArrayList arrayList = this.f826f;
        ArrayList arrayList2 = this.f827g;
        if (i2 == 1) {
            int size = arrayList2.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList2.get(i3);
                i3++;
                this.f825e++;
                ((f) obj).a(true);
                this.f825e--;
            }
            if (!arrayList.isEmpty()) {
                arrayList.size();
                d(!toString().equals(this.f830j), (this.f831k == Selection.getSelectionStart(this) && this.f832l == Selection.getSelectionEnd(this)) ? false : true, (this.m == BaseInputConnection.getComposingSpanStart(this) && this.f833n == BaseInputConnection.getComposingSpanEnd(this)) ? false : true);
            }
        }
        arrayList.addAll(arrayList2);
        arrayList2.clear();
        this.f824d--;
    }

    public final void d(boolean z2, boolean z3, boolean z4) {
        if (z2 || z3 || z4) {
            ArrayList arrayList = this.f826f;
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                this.f825e++;
                ((f) obj).a(z2);
                this.f825e--;
            }
        }
    }

    public final void e(f fVar) {
        if (this.f825e > 0) {
            Log.e("ListenableEditingState", "removing a listener " + fVar.toString() + " in a listener callback");
        }
        this.f826f.remove(fVar);
        if (this.f824d > 0) {
            this.f827g.remove(fVar);
        }
    }

    public final void f(q qVar) {
        b();
        replace(0, length(), (CharSequence) qVar.f581a);
        int i2 = qVar.f582b;
        if (i2 >= 0) {
            Selection.setSelection(this, i2, qVar.f583c);
        } else {
            Selection.removeSelection(this);
        }
        int i3 = qVar.f584d;
        int i4 = qVar.f585e;
        if (i3 < 0 || i3 >= i4) {
            BaseInputConnection.removeComposingSpans(this);
        } else {
            this.f834o.setComposingRegion(i3, i4);
        }
        this.f828h.clear();
        c();
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public final void setSpan(Object obj, int i2, int i3, int i4) {
        super.setSpan(obj, i2, i3, i4);
        String string = toString();
        int selectionStart = Selection.getSelectionStart(this);
        int selectionEnd = Selection.getSelectionEnd(this);
        int composingSpanStart = BaseInputConnection.getComposingSpanStart(this);
        int composingSpanEnd = BaseInputConnection.getComposingSpanEnd(this);
        i iVar = new i();
        iVar.f843e = selectionStart;
        iVar.f844f = selectionEnd;
        iVar.f845g = composingSpanStart;
        iVar.f846h = composingSpanEnd;
        iVar.f839a = string;
        iVar.f840b = "";
        iVar.f841c = -1;
        iVar.f842d = -1;
        this.f828h.add(iVar);
    }

    @Override // android.text.SpannableStringBuilder, java.lang.CharSequence
    public final String toString() {
        String str = this.f829i;
        if (str != null) {
            return str;
        }
        String string = super.toString();
        this.f829i = string;
        return string;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder replace(int i2, int i3, CharSequence charSequence, int i4, int i5) {
        if (this.f825e > 0) {
            Log.e("ListenableEditingState", "editing state should not be changed in a listener callback");
        }
        String string = toString();
        int i6 = i3 - i2;
        boolean z2 = i6 != i5 - i4;
        for (int i7 = 0; i7 < i6 && !z2; i7++) {
            z2 |= charAt(i2 + i7) != charSequence.charAt(i4 + i7);
        }
        if (z2) {
            this.f829i = null;
        }
        int selectionStart = Selection.getSelectionStart(this);
        int selectionEnd = Selection.getSelectionEnd(this);
        int composingSpanStart = BaseInputConnection.getComposingSpanStart(this);
        int composingSpanEnd = BaseInputConnection.getComposingSpanEnd(this);
        SpannableStringBuilder spannableStringBuilderReplace = super.replace(i2, i3, charSequence, i4, i5);
        int selectionStart2 = Selection.getSelectionStart(this);
        int selectionEnd2 = Selection.getSelectionEnd(this);
        int composingSpanStart2 = BaseInputConnection.getComposingSpanStart(this);
        int composingSpanEnd2 = BaseInputConnection.getComposingSpanEnd(this);
        i iVar = new i();
        iVar.f843e = selectionStart2;
        iVar.f844f = selectionEnd2;
        iVar.f845g = composingSpanStart2;
        iVar.f846h = composingSpanEnd2;
        String string2 = charSequence.toString();
        iVar.f839a = string;
        iVar.f840b = string2;
        iVar.f841c = i2;
        iVar.f842d = i3;
        this.f828h.add(iVar);
        if (this.f824d > 0) {
            return spannableStringBuilderReplace;
        }
        d(z2, (Selection.getSelectionStart(this) == selectionStart && Selection.getSelectionEnd(this) == selectionEnd) ? false : true, (BaseInputConnection.getComposingSpanStart(this) == composingSpanStart && BaseInputConnection.getComposingSpanEnd(this) == composingSpanEnd) ? false : true);
        return spannableStringBuilderReplace;
    }
}
