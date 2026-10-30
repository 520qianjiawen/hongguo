package io.flutter.plugin.editing;

import O.t;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.InputMethodManager;
import io.flutter.embedding.engine.FlutterJNI;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class d extends BaseInputConnection implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f810a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f811b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final H.a f812c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g f813d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final EditorInfo f814e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ExtractedTextRequest f815f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f816g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CursorAnchorInfo.Builder f817h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ExtractedText f818i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final InputMethodManager f819j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final DynamicLayout f820k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final p002b0.a f821l;
    public final G.f m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f822n;

    public d(t tVar, int i2, H.a aVar, G.f fVar, g gVar, EditorInfo editorInfo) {
        FlutterJNI flutterJNI = new FlutterJNI();
        super(tVar, true);
        this.f816g = false;
        this.f818i = new ExtractedText();
        this.f822n = 0;
        this.f810a = tVar;
        this.f811b = i2;
        this.f812c = aVar;
        this.f813d = gVar;
        gVar.a(this);
        this.f814e = editorInfo;
        this.m = fVar;
        this.f821l = new p002b0.a(1, flutterJNI);
        this.f820k = new DynamicLayout(gVar, new TextPaint(), Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.f819j = (InputMethodManager) tVar.getContext().getSystemService("input_method");
    }

    @Override // io.flutter.plugin.editing.f
    public final void a(boolean z2) {
        g gVar = this.f813d;
        gVar.getClass();
        int selectionStart = Selection.getSelectionStart(gVar);
        int selectionEnd = Selection.getSelectionEnd(gVar);
        int composingSpanStart = BaseInputConnection.getComposingSpanStart(gVar);
        int composingSpanEnd = BaseInputConnection.getComposingSpanEnd(gVar);
        InputMethodManager inputMethodManager = this.f819j;
        t tVar = this.f810a;
        inputMethodManager.updateSelection(tVar, selectionStart, selectionEnd, composingSpanStart, composingSpanEnd);
        ExtractedTextRequest extractedTextRequest = this.f815f;
        if (extractedTextRequest != null) {
            inputMethodManager.updateExtractedText(tVar, extractedTextRequest.token, c(extractedTextRequest));
        }
        if (this.f816g) {
            inputMethodManager.updateCursorAnchorInfo(tVar, b());
        }
    }

    public final CursorAnchorInfo b() {
        CursorAnchorInfo.Builder builder = this.f817h;
        if (builder == null) {
            this.f817h = new CursorAnchorInfo.Builder();
        } else {
            builder.reset();
        }
        CursorAnchorInfo.Builder builder2 = this.f817h;
        g gVar = this.f813d;
        gVar.getClass();
        int selectionStart = Selection.getSelectionStart(gVar);
        gVar.getClass();
        builder2.setSelectionRange(selectionStart, Selection.getSelectionEnd(gVar));
        gVar.getClass();
        int composingSpanStart = BaseInputConnection.getComposingSpanStart(gVar);
        gVar.getClass();
        int composingSpanEnd = BaseInputConnection.getComposingSpanEnd(gVar);
        if (composingSpanStart < 0 || composingSpanEnd <= composingSpanStart) {
            this.f817h.setComposingText(-1, "");
        } else {
            this.f817h.setComposingText(composingSpanStart, gVar.toString().subSequence(composingSpanStart, composingSpanEnd));
        }
        return this.f817h.build();
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean beginBatchEdit() {
        this.f813d.b();
        this.f822n++;
        return super.beginBatchEdit();
    }

    public final ExtractedText c(ExtractedTextRequest extractedTextRequest) {
        ExtractedText extractedText = this.f818i;
        extractedText.startOffset = 0;
        extractedText.partialStartOffset = -1;
        extractedText.partialEndOffset = -1;
        CharSequence string = this.f813d;
        string.getClass();
        extractedText.selectionStart = Selection.getSelectionStart(string);
        string.getClass();
        extractedText.selectionEnd = Selection.getSelectionEnd(string);
        if (extractedTextRequest == null || (extractedTextRequest.flags & 1) == 0) {
            string = string.toString();
        }
        extractedText.text = string;
        return extractedText;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final void closeConnection() {
        super.closeConnection();
        this.f813d.e(this);
        while (this.f822n > 0) {
            endBatchEdit();
            this.f822n--;
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i2, Bundle bundle) {
        int i3;
        if (Build.VERSION.SDK_INT >= 25 && (i2 & 1) != 0) {
            try {
                inputContentInfo.requestPermission();
                if (inputContentInfo.getDescription().getMimeTypeCount() > 0) {
                    inputContentInfo.requestPermission();
                    Uri contentUri = inputContentInfo.getContentUri();
                    String mimeType = inputContentInfo.getDescription().getMimeType(0);
                    Context context = this.f810a.getContext();
                    if (contentUri != null) {
                        try {
                            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(contentUri);
                            if (inputStreamOpenInputStream != null) {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                byte[] bArr = new byte[65536];
                                while (true) {
                                    try {
                                        i3 = inputStreamOpenInputStream.read(bArr);
                                    } catch (IOException unused) {
                                        i3 = -1;
                                    }
                                    if (i3 == -1) {
                                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                                        HashMap map = new HashMap();
                                        map.put("mimeType", mimeType);
                                        map.put("data", byteArray);
                                        map.put("uri", contentUri.toString());
                                        ((H.e) this.f812c.f170e).h("TextInputClient.performAction", Arrays.asList(Integer.valueOf(this.f811b), "TextInputAction.commitContent", map), null);
                                        inputContentInfo.releasePermission();
                                        return true;
                                    }
                                    byteArrayOutputStream.write(bArr, 0, i3);
                                }
                            }
                        } catch (FileNotFoundException unused2) {
                            inputContentInfo.releasePermission();
                            return false;
                        }
                    }
                    inputContentInfo.releasePermission();
                }
            } catch (Exception unused3) {
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:179:0x02c2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:196:0x010c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:205:0x01ab A[ADDED_TO_REGION, EDGE_INSN: B:205:0x01ab->B:108:0x01ab BREAK  A[LOOP:4: B:143:0x022c->B:210:?], REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:62:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:64:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:75:0x0131  */
    /* JADX WARN: Code duplicated, block: B:78:0x0138 A[EDGE_INSN: B:78:0x0138->B:18:0x0040 BREAK  A[LOOP:2: B:63:0x00fb->B:200:?]] */
    /* JADX WARN: Code duplicated, block: B:79:0x013f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:80:0x0141  */
    /* JADX WARN: Code duplicated, block: B:82:0x014c  */
    /* JADX WARN: Code duplicated, block: B:88:0x016d  */
    /* JADX WARN: Code duplicated, block: B:89:0x016f  */
    /* JADX WARN: Code duplicated, block: B:90:0x0170 A[PHI: r11 r13
      0x0170: PHI (r11v5 int) = (r11v4 int), (r11v7 int) binds: [B:79:0x013f, B:89:0x016f] A[DONT_GENERATE, DONT_INLINE]
      0x0170: PHI (r13v16 int) = (r13v14 int), (r13v18 int) binds: [B:79:0x013f, B:89:0x016f] A[DONT_GENERATE, DONT_INLINE]] */
    public final boolean d(boolean z2, boolean z3) {
        int iCharCount;
        int iCharCount2;
        int iCharCount3;
        int iCharCount4;
        int iMin;
        int iCodePointBefore;
        int iCharCount5;
        int iCharCount6;
        boolean z4;
        int iCharCount7;
        int iCodePointBefore2;
        int iCharCount8;
        int iCharCount9;
        int iCodePointBefore3;
        int i2;
        int iCharCount10;
        g gVar = this.f813d;
        int selectionStart = Selection.getSelectionStart(gVar);
        int selectionEnd = Selection.getSelectionEnd(gVar);
        int i3 = 0;
        if (selectionStart < 0 || selectionEnd < 0) {
            return false;
        }
        p002b0.a aVar = this.f821l;
        if (z2) {
            FlutterJNI flutterJNI = (FlutterJNI) aVar.f661e;
            if (selectionEnd > 1 && (iCharCount6 = selectionEnd - (iCharCount5 = Character.charCount((iCodePointBefore = Character.codePointBefore(gVar, selectionEnd))))) != 0) {
                if (iCodePointBefore == 10) {
                    if (Character.codePointBefore(gVar, iCharCount6) == 13) {
                        iCharCount5++;
                    }
                } else if (flutterJNI.isCodePointRegionalIndicator(iCodePointBefore)) {
                    int iCodePointBefore4 = Character.codePointBefore(gVar, iCharCount6);
                    int iCharCount11 = iCharCount6 - Character.charCount(iCodePointBefore4);
                    int i4 = 1;
                    while (iCharCount11 > 0 && flutterJNI.isCodePointRegionalIndicator(iCodePointBefore4)) {
                        iCodePointBefore4 = Character.codePointBefore(gVar, iCharCount11);
                        iCharCount11 -= Character.charCount(iCodePointBefore4);
                        i4++;
                    }
                    if (i4 % 2 == 0) {
                        iCharCount5 += 2;
                    }
                } else if (iCodePointBefore == 8419) {
                    int iCodePointBefore5 = Character.codePointBefore(gVar, iCharCount6);
                    int iCharCount12 = iCharCount6 - Character.charCount(iCodePointBefore5);
                    if (iCharCount12 > 0 && flutterJNI.isCodePointVariantSelector(iCodePointBefore5)) {
                        int iCodePointBefore6 = Character.codePointBefore(gVar, iCharCount12);
                        if (p002b0.a.q(iCodePointBefore6)) {
                            iCharCount10 = Character.charCount(iCodePointBefore6) + Character.charCount(iCodePointBefore5);
                            iCharCount5 += iCharCount10;
                        }
                    } else if (p002b0.a.q(iCodePointBefore5)) {
                        iCharCount10 = Character.charCount(iCodePointBefore5);
                        iCharCount5 += iCharCount10;
                    }
                } else {
                    if (iCodePointBefore == 917631) {
                        int iCodePointBefore7 = Character.codePointBefore(gVar, iCharCount6);
                        iCharCount6 -= Character.charCount(iCodePointBefore7);
                        iCodePointBefore = iCodePointBefore7;
                        while (iCharCount6 > 0 && 917536 <= iCodePointBefore && iCodePointBefore <= 917630) {
                            iCharCount5 += Character.charCount(iCodePointBefore);
                            iCodePointBefore = Character.codePointBefore(gVar, iCharCount6);
                            iCharCount6 -= Character.charCount(iCodePointBefore);
                        }
                        if (flutterJNI.isCodePointEmoji(iCodePointBefore)) {
                            iCharCount5 += Character.charCount(iCodePointBefore);
                        } else {
                            i2 = selectionEnd - 2;
                        }
                    }
                    if (flutterJNI.isCodePointVariantSelector(iCodePointBefore)) {
                        iCodePointBefore = Character.codePointBefore(gVar, iCharCount6);
                        if (flutterJNI.isCodePointEmoji(iCodePointBefore)) {
                            iCharCount5 += Character.charCount(iCodePointBefore);
                            iCharCount6 -= iCharCount5;
                            if (flutterJNI.isCodePointEmoji(iCodePointBefore)) {
                                z4 = false;
                                iCharCount7 = 0;
                                do {
                                    if (z4) {
                                        iCharCount5 = Character.charCount(iCodePointBefore) + iCharCount7 + 1 + iCharCount5;
                                        z4 = false;
                                    }
                                    if (!flutterJNI.isCodePointEmojiModifier(iCodePointBefore)) {
                                        if (iCharCount6 > 0) {
                                            iCodePointBefore3 = Character.codePointBefore(gVar, iCharCount6);
                                            iCharCount6 -= Character.charCount(iCodePointBefore3);
                                            if (iCodePointBefore3 == 8205) {
                                                int iCodePointBefore8 = Character.codePointBefore(gVar, iCharCount6);
                                                iCharCount6 -= Character.charCount(iCodePointBefore8);
                                                if (iCharCount6 > 0 || !flutterJNI.isCodePointVariantSelector(iCodePointBefore8)) {
                                                    iCharCount7 = 0;
                                                } else {
                                                    iCodePointBefore8 = Character.codePointBefore(gVar, iCharCount6);
                                                    iCharCount7 = Character.charCount(iCodePointBefore8);
                                                    iCharCount6 -= Character.charCount(iCodePointBefore8);
                                                }
                                                iCodePointBefore = iCodePointBefore8;
                                                z4 = true;
                                            } else {
                                                iCodePointBefore = iCodePointBefore3;
                                                iCharCount7 = 0;
                                            }
                                        } else {
                                            iCharCount7 = 0;
                                        }
                                        if (iCharCount6 != 0 || !z4) {
                                            break;
                                        }
                                    } else {
                                        iCodePointBefore2 = Character.codePointBefore(gVar, iCharCount6);
                                        iCharCount8 = iCharCount6 - Character.charCount(iCodePointBefore2);
                                        if (iCharCount8 > 0 && flutterJNI.isCodePointVariantSelector(iCodePointBefore2)) {
                                            iCodePointBefore2 = Character.codePointBefore(gVar, iCharCount8);
                                            if (!flutterJNI.isCodePointEmoji(iCodePointBefore2)) {
                                                break;
                                            }
                                            iCharCount9 = Character.charCount(iCodePointBefore2);
                                            Character.charCount(iCodePointBefore2);
                                        } else {
                                            iCharCount9 = 0;
                                        }
                                        if (!flutterJNI.isCodePointEmojiModifierBase(iCodePointBefore2)) {
                                            break;
                                        }
                                        iCharCount5 += Character.charCount(iCodePointBefore2) + iCharCount9;
                                        break;
                                    }
                                } while (flutterJNI.isCodePointEmoji(iCodePointBefore));
                            }
                        }
                    } else if (flutterJNI.isCodePointEmoji(iCodePointBefore)) {
                        z4 = false;
                        iCharCount7 = 0;
                        do {
                            if (z4) {
                                iCharCount5 = Character.charCount(iCodePointBefore) + iCharCount7 + 1 + iCharCount5;
                                z4 = false;
                            }
                            if (!flutterJNI.isCodePointEmojiModifier(iCodePointBefore)) {
                                iCodePointBefore2 = Character.codePointBefore(gVar, iCharCount6);
                                iCharCount8 = iCharCount6 - Character.charCount(iCodePointBefore2);
                                if (iCharCount8 > 0) {
                                    iCharCount9 = 0;
                                    if (!flutterJNI.isCodePointEmojiModifierBase(iCodePointBefore2)) {
                                        break;
                                    }
                                    iCharCount5 += Character.charCount(iCodePointBefore2) + iCharCount9;
                                    break;
                                }
                                iCharCount9 = 0;
                                if (!flutterJNI.isCodePointEmojiModifierBase(iCodePointBefore2)) {
                                    break;
                                }
                                iCharCount5 += Character.charCount(iCodePointBefore2) + iCharCount9;
                                break;
                            }
                            if (iCharCount6 > 0) {
                                iCodePointBefore3 = Character.codePointBefore(gVar, iCharCount6);
                                iCharCount6 -= Character.charCount(iCodePointBefore3);
                                if (iCodePointBefore3 == 8205) {
                                    int iCodePointBefore9 = Character.codePointBefore(gVar, iCharCount6);
                                    iCharCount6 -= Character.charCount(iCodePointBefore9);
                                    if (iCharCount6 > 0) {
                                        iCharCount7 = 0;
                                    } else {
                                        iCharCount7 = 0;
                                    }
                                    iCodePointBefore = iCodePointBefore9;
                                    z4 = true;
                                } else {
                                    iCodePointBefore = iCodePointBefore3;
                                    iCharCount7 = 0;
                                }
                            } else {
                                iCharCount7 = 0;
                            }
                            if (iCharCount6 != 0) {
                                break;
                            }
                        } while (flutterJNI.isCodePointEmoji(iCodePointBefore));
                    }
                }
                i2 = selectionEnd - iCharCount5;
            } else {
                i2 = 0;
            }
            iMin = Math.max(i2, 0);
        } else {
            FlutterJNI flutterJNI2 = (FlutterJNI) aVar.f661e;
            int length = gVar.length();
            int i5 = length - 1;
            if (selectionEnd >= i5) {
                i3 = length;
            } else {
                int iCodePointAt = Character.codePointAt(gVar, selectionEnd);
                int iCharCount13 = Character.charCount(iCodePointAt);
                int i6 = selectionEnd + iCharCount13;
                if (i6 != 0) {
                    if (iCodePointAt != 10) {
                        if (!flutterJNI2.isCodePointRegionalIndicator(iCodePointAt)) {
                            if (p002b0.a.q(iCodePointAt)) {
                                iCharCount13 += Character.charCount(iCodePointAt);
                            }
                            if (iCodePointAt != 8419) {
                                if (flutterJNI2.isCodePointEmoji(iCodePointAt)) {
                                    boolean z5 = false;
                                    int i7 = 0;
                                    do {
                                        if (z5) {
                                            iCharCount13 = Character.charCount(iCodePointAt) + i7 + 1 + iCharCount13;
                                            z5 = false;
                                        }
                                        if (flutterJNI2.isCodePointEmojiModifier(iCodePointAt)) {
                                            break;
                                        }
                                        if (i6 < length) {
                                            int iCodePointAt2 = Character.codePointAt(gVar, i6);
                                            int iCharCount14 = Character.charCount(iCodePointAt2) + i6;
                                            if (iCodePointAt2 != 8419) {
                                                if (flutterJNI2.isCodePointEmojiModifier(iCodePointAt2)) {
                                                    iCharCount4 = Character.charCount(iCodePointAt2);
                                                } else if (flutterJNI2.isCodePointVariantSelector(iCodePointAt2)) {
                                                    iCharCount4 = Character.charCount(iCodePointAt2);
                                                } else {
                                                    if (iCodePointAt2 == 8205) {
                                                        int iCodePointAt3 = Character.codePointAt(gVar, iCharCount14);
                                                        int iCharCount15 = Character.charCount(iCodePointAt3) + iCharCount14;
                                                        if (iCharCount15 >= length || !flutterJNI2.isCodePointVariantSelector(iCodePointAt3)) {
                                                            iCodePointAt = iCodePointAt3;
                                                            i6 = iCharCount15;
                                                            z5 = true;
                                                        } else {
                                                            int iCodePointAt4 = Character.codePointAt(gVar, iCharCount15);
                                                            int iCharCount16 = Character.charCount(iCodePointAt4);
                                                            int iCharCount17 = Character.charCount(iCodePointAt4) + iCharCount15;
                                                            i7 = iCharCount16;
                                                            i6 = iCharCount17;
                                                            iCodePointAt = iCodePointAt4;
                                                            z5 = true;
                                                        }
                                                        if (i6 < length || !z5) {
                                                            break;
                                                        }
                                                    } else {
                                                        iCodePointAt = iCodePointAt2;
                                                        i6 = iCharCount14;
                                                    }
                                                    i7 = 0;
                                                    if (i6 < length) {
                                                        break;
                                                    }
                                                }
                                                iCharCount13 += iCharCount4;
                                                break;
                                            }
                                            int iCodePointBefore10 = Character.codePointBefore(gVar, iCharCount14);
                                            int iCharCount18 = Character.charCount(iCodePointBefore10) + iCharCount14;
                                            if (iCharCount18 < length && flutterJNI2.isCodePointVariantSelector(iCodePointBefore10)) {
                                                int iCodePointAt5 = Character.codePointAt(gVar, iCharCount18);
                                                if (!p002b0.a.q(iCodePointAt5)) {
                                                    break;
                                                }
                                                iCharCount2 = Character.charCount(iCodePointBefore10);
                                                iCharCount3 = Character.charCount(iCodePointAt5);
                                                iCharCount13 += iCharCount3 + iCharCount2;
                                                break;
                                            }
                                            if (!p002b0.a.q(iCodePointBefore10)) {
                                                break;
                                            }
                                            iCharCount = Character.charCount(iCodePointBefore10);
                                            iCharCount13 += iCharCount;
                                            break;
                                        }
                                        i7 = 0;
                                        if (i6 < length) {
                                            break;
                                            break;
                                        }
                                    } while (flutterJNI2.isCodePointEmoji(iCodePointAt));
                                }
                            } else {
                                int iCodePointBefore11 = Character.codePointBefore(gVar, i6);
                                int iCharCount19 = Character.charCount(iCodePointBefore11) + i6;
                                if (iCharCount19 < length && flutterJNI2.isCodePointVariantSelector(iCodePointBefore11)) {
                                    int iCodePointAt6 = Character.codePointAt(gVar, iCharCount19);
                                    if (p002b0.a.q(iCodePointAt6)) {
                                        iCharCount2 = Character.charCount(iCodePointBefore11);
                                        iCharCount3 = Character.charCount(iCodePointAt6);
                                        iCharCount13 += iCharCount3 + iCharCount2;
                                        break;
                                    }
                                } else if (p002b0.a.q(iCodePointBefore11)) {
                                    iCharCount = Character.charCount(iCodePointBefore11);
                                    iCharCount13 += iCharCount;
                                    break;
                                }
                            }
                        } else if (i6 >= i5 || !flutterJNI2.isCodePointRegionalIndicator(Character.codePointAt(gVar, i6))) {
                            i3 = i6;
                        } else {
                            int iCharCount20 = selectionEnd;
                            while (iCharCount20 > 0 && flutterJNI2.isCodePointRegionalIndicator(Character.codePointBefore(gVar, selectionEnd))) {
                                iCharCount20 -= Character.charCount(Character.codePointBefore(gVar, selectionEnd));
                                i3++;
                            }
                            if (i3 % 2 == 0) {
                                iCharCount13 += 2;
                            }
                        }
                    } else if (Character.codePointAt(gVar, i6) == 13) {
                        iCharCount13++;
                    }
                    i3 = selectionEnd + iCharCount13;
                }
            }
            iMin = Math.min(i3, gVar.length());
        }
        if (selectionStart != selectionEnd || z3) {
            setSelection(selectionStart, iMin);
            return true;
        }
        setSelection(iMin, iMin);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i2, int i3) {
        g gVar = this.f813d;
        gVar.getClass();
        if (Selection.getSelectionStart(gVar) == -1) {
            return true;
        }
        return super.deleteSurroundingText(i2, i3);
    }

    public final boolean e(boolean z2, boolean z3) {
        g gVar = this.f813d;
        int selectionStart = Selection.getSelectionStart(gVar);
        int selectionEnd = Selection.getSelectionEnd(gVar);
        boolean z4 = false;
        if (selectionStart < 0 || selectionEnd < 0) {
            return false;
        }
        if (selectionStart == selectionEnd && !z3) {
            z4 = true;
        }
        beginBatchEdit();
        DynamicLayout dynamicLayout = this.f820k;
        if (z4) {
            if (z2) {
                Selection.moveUp(gVar, dynamicLayout);
            } else {
                Selection.moveDown(gVar, dynamicLayout);
            }
            int selectionStart2 = Selection.getSelectionStart(gVar);
            setSelection(selectionStart2, selectionStart2);
        } else {
            if (z2) {
                Selection.extendUp(gVar, dynamicLayout);
            } else {
                Selection.extendDown(gVar, dynamicLayout);
            }
            setSelection(Selection.getSelectionStart(gVar), Selection.getSelectionEnd(gVar));
        }
        endBatchEdit();
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean endBatchEdit() {
        boolean zEndBatchEdit = super.endBatchEdit();
        this.f822n--;
        this.f813d.c();
        return zEndBatchEdit;
    }

    @Override // android.view.inputmethod.BaseInputConnection
    public final Editable getEditable() {
        return this.f813d;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i2) {
        this.f815f = (i2 & 1) != 0 ? extractedTextRequest : null;
        return c(extractedTextRequest);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean performContextMenuAction(int i2) {
        beginBatchEdit();
        boolean z2 = true;
        g gVar = this.f813d;
        if (i2 == 16908319) {
            setSelection(0, gVar.length());
        } else {
            t tVar = this.f810a;
            if (i2 == 16908320) {
                int selectionStart = Selection.getSelectionStart(gVar);
                int selectionEnd = Selection.getSelectionEnd(gVar);
                if (selectionStart != selectionEnd) {
                    int iMin = Math.min(selectionStart, selectionEnd);
                    int iMax = Math.max(selectionStart, selectionEnd);
                    ((ClipboardManager) tVar.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", gVar.subSequence(iMin, iMax)));
                    gVar.delete(iMin, iMax);
                    setSelection(iMin, iMin);
                }
            } else if (i2 == 16908321) {
                int selectionStart2 = Selection.getSelectionStart(gVar);
                int selectionEnd2 = Selection.getSelectionEnd(gVar);
                if (selectionStart2 != selectionEnd2) {
                    ((ClipboardManager) tVar.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", gVar.subSequence(Math.min(selectionStart2, selectionEnd2), Math.max(selectionStart2, selectionEnd2))));
                }
            } else if (i2 == 16908322) {
                ClipData primaryClip = ((ClipboardManager) tVar.getContext().getSystemService("clipboard")).getPrimaryClip();
                if (primaryClip != null) {
                    CharSequence charSequenceCoerceToText = primaryClip.getItemAt(0).coerceToText(tVar.getContext());
                    int iMax2 = Math.max(0, Selection.getSelectionStart(gVar));
                    int iMax3 = Math.max(0, Selection.getSelectionEnd(gVar));
                    int iMin2 = Math.min(iMax2, iMax3);
                    int iMax4 = Math.max(iMax2, iMax3);
                    if (iMin2 != iMax4) {
                        gVar.delete(iMin2, iMax4);
                    }
                    gVar.insert(iMin2, charSequenceCoerceToText);
                    int length = charSequenceCoerceToText.length() + iMin2;
                    setSelection(length, length);
                }
            } else {
                z2 = false;
            }
        }
        endBatchEdit();
        return z2;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean performEditorAction(int i2) {
        int i3 = this.f811b;
        H.a aVar = this.f812c;
        if (i2 == 0) {
            ((H.e) aVar.f170e).h("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i3), "TextInputAction.unspecified"), null);
            return true;
        }
        if (i2 == 1) {
            ((H.e) aVar.f170e).h("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i3), "TextInputAction.newline"), null);
            return true;
        }
        if (i2 == 2) {
            ((H.e) aVar.f170e).h("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i3), "TextInputAction.go"), null);
            return true;
        }
        if (i2 == 3) {
            ((H.e) aVar.f170e).h("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i3), "TextInputAction.search"), null);
            return true;
        }
        if (i2 == 4) {
            ((H.e) aVar.f170e).h("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i3), "TextInputAction.send"), null);
            return true;
        }
        if (i2 == 5) {
            ((H.e) aVar.f170e).h("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i3), "TextInputAction.next"), null);
            return true;
        }
        if (i2 != 7) {
            ((H.e) aVar.f170e).h("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i3), "TextInputAction.done"), null);
            return true;
        }
        ((H.e) aVar.f170e).h("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i3), "TextInputAction.previous"), null);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        H.a aVar = this.f812c;
        aVar.getClass();
        HashMap map = new HashMap();
        map.put("action", str);
        if (bundle != null) {
            HashMap map2 = new HashMap();
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj instanceof byte[]) {
                    map2.put(str2, bundle.getByteArray(str2));
                } else if (obj instanceof Byte) {
                    map2.put(str2, Byte.valueOf(bundle.getByte(str2)));
                } else if (obj instanceof char[]) {
                    map2.put(str2, bundle.getCharArray(str2));
                } else if (obj instanceof Character) {
                    map2.put(str2, Character.valueOf(bundle.getChar(str2)));
                } else if (obj instanceof CharSequence[]) {
                    map2.put(str2, bundle.getCharSequenceArray(str2));
                } else if (obj instanceof CharSequence) {
                    map2.put(str2, bundle.getCharSequence(str2));
                } else if (obj instanceof float[]) {
                    map2.put(str2, bundle.getFloatArray(str2));
                } else if (obj instanceof Float) {
                    map2.put(str2, Float.valueOf(bundle.getFloat(str2)));
                }
            }
            map.put("data", map2);
        }
        ((H.e) aVar.f170e).h("TextInputClient.performPrivateCommand", Arrays.asList(Integer.valueOf(this.f811b), map), null);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean requestCursorUpdates(int i2) {
        if ((i2 & 1) != 0) {
            this.f819j.updateCursorAnchorInfo(this.f810a, b());
        }
        this.f816g = (i2 & 2) != 0;
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean sendKeyEvent(KeyEvent keyEvent) {
        return this.m.c(keyEvent);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean setComposingText(CharSequence charSequence, int i2) {
        beginBatchEdit();
        boolean zCommitText = charSequence.length() == 0 ? super.commitText(charSequence, i2) : super.setComposingText(charSequence, i2);
        endBatchEdit();
        return zCommitText;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean setSelection(int i2, int i3) {
        beginBatchEdit();
        boolean selection = super.setSelection(i2, i3);
        endBatchEdit();
        return selection;
    }
}
