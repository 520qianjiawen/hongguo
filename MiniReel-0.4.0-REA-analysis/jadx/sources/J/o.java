package J;

import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f227d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ H.a f228e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Y.k f229f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ g f230g;

    public /* synthetic */ o(H.a aVar, g gVar, Y.k kVar) {
        this.f227d = 3;
        this.f228e = aVar;
        this.f230g = gVar;
        this.f229f = kVar;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f227d;
        final int i3 = 3;
        final int i4 = 2;
        final int i5 = 1;
        final int i6 = 0;
        Y.k kVar = this.f229f;
        H.a aVar = this.f228e;
        final g gVar = this.f230g;
        switch (i2) {
            case 0:
                HashMap map = s.f246f;
                final K.b bVar = new K.b(aVar, kVar);
                gVar.m(bVar, new Runnable() { // from class: J.c
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        switch (i6) {
                            case 0:
                                g gVar2 = gVar;
                                HashMap map2 = gVar2.f197g;
                                K.b bVar2 = bVar;
                                H.a aVar2 = bVar2.f263j;
                                Integer num = (Integer) aVar2.x("cursorId");
                                int iIntValue = num.intValue();
                                boolean zEquals = Boolean.TRUE.equals(aVar2.x("cancel"));
                                if (a.a(gVar2.f194d)) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(gVar2.i());
                                    sb.append("cursor ");
                                    sb.append(iIntValue);
                                    sb.append(zEquals ? " cancel" : " next");
                                    Log.d("Sqflite", sb.toString());
                                }
                                n nVar = null;
                                if (zEquals) {
                                    n nVar2 = (n) map2.get(num);
                                    if (nVar2 != null) {
                                        gVar2.c(nVar2);
                                    }
                                    bVar2.e(null);
                                    return;
                                }
                                n nVar3 = (n) map2.get(num);
                                boolean z2 = false;
                                try {
                                    try {
                                        if (nVar3 == null) {
                                            throw new IllegalStateException("Cursor " + iIntValue + " not found");
                                        }
                                        Cursor cursor = nVar3.f226c;
                                        HashMap mapD = g.d(cursor, Integer.valueOf(nVar3.f225b));
                                        if (!cursor.isLast() && !cursor.isAfterLast()) {
                                            z2 = true;
                                        }
                                        if (z2) {
                                            mapD.put("cursorId", num);
                                        }
                                        bVar2.e(mapD);
                                        if (z2) {
                                            return;
                                        }
                                        gVar2.c(nVar3);
                                        return;
                                    } catch (Exception e2) {
                                        gVar2.j(e2, bVar2);
                                        if (nVar3 != null) {
                                            gVar2.c(nVar3);
                                        } else {
                                            nVar = nVar3;
                                        }
                                        if (0 != 0 || nVar == null) {
                                            return;
                                        }
                                        gVar2.c(nVar);
                                        return;
                                    }
                                } catch (Throwable th) {
                                    if (0 == 0 && nVar3 != null) {
                                        gVar2.c(nVar3);
                                    }
                                    throw th;
                                }
                            case 1:
                                gVar.f(bVar);
                                return;
                            case 2:
                                gVar.g(bVar);
                                return;
                            case 3:
                                gVar.e(bVar);
                                return;
                            default:
                                K.b bVar3 = bVar;
                                Object objP = bVar3.p("inTransaction");
                                Boolean bool = objP instanceof Boolean ? (Boolean) objP : null;
                                boolean z3 = Boolean.TRUE.equals(bool) && bVar3.x() && ((Integer) bVar3.p("transactionId")) == null;
                                g gVar3 = gVar;
                                if (z3) {
                                    int i7 = gVar3.f201k + 1;
                                    gVar3.f201k = i7;
                                    gVar3.f202l = Integer.valueOf(i7);
                                }
                                if (!gVar3.h(bVar3)) {
                                    if (z3) {
                                        gVar3.f202l = null;
                                        return;
                                    }
                                    return;
                                } else if (z3) {
                                    HashMap map3 = new HashMap();
                                    map3.put("transactionId", gVar3.f202l);
                                    bVar3.e(map3);
                                    return;
                                } else {
                                    if (Boolean.FALSE.equals(bool)) {
                                        gVar3.f202l = null;
                                    }
                                    bVar3.e(null);
                                    return;
                                }
                        }
                    }
                });
                break;
            case 1:
                HashMap map2 = s.f246f;
                final K.b bVar2 = new K.b(aVar, kVar);
                gVar.m(bVar2, new Runnable() { // from class: J.c
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        switch (i3) {
                            case 0:
                                g gVar2 = gVar;
                                HashMap map3 = gVar2.f197g;
                                K.b bVar3 = bVar2;
                                H.a aVar2 = bVar3.f263j;
                                Integer num = (Integer) aVar2.x("cursorId");
                                int iIntValue = num.intValue();
                                boolean zEquals = Boolean.TRUE.equals(aVar2.x("cancel"));
                                if (a.a(gVar2.f194d)) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(gVar2.i());
                                    sb.append("cursor ");
                                    sb.append(iIntValue);
                                    sb.append(zEquals ? " cancel" : " next");
                                    Log.d("Sqflite", sb.toString());
                                }
                                n nVar = null;
                                if (zEquals) {
                                    n nVar2 = (n) map3.get(num);
                                    if (nVar2 != null) {
                                        gVar2.c(nVar2);
                                    }
                                    bVar3.e(null);
                                    return;
                                }
                                n nVar3 = (n) map3.get(num);
                                boolean z2 = false;
                                try {
                                    try {
                                        if (nVar3 == null) {
                                            throw new IllegalStateException("Cursor " + iIntValue + " not found");
                                        }
                                        Cursor cursor = nVar3.f226c;
                                        HashMap mapD = g.d(cursor, Integer.valueOf(nVar3.f225b));
                                        if (!cursor.isLast() && !cursor.isAfterLast()) {
                                            z2 = true;
                                        }
                                        if (z2) {
                                            mapD.put("cursorId", num);
                                        }
                                        bVar3.e(mapD);
                                        if (z2) {
                                            return;
                                        }
                                        gVar2.c(nVar3);
                                        return;
                                    } catch (Exception e2) {
                                        gVar2.j(e2, bVar3);
                                        if (nVar3 != null) {
                                            gVar2.c(nVar3);
                                        } else {
                                            nVar = nVar3;
                                        }
                                        if (0 != 0 || nVar == null) {
                                            return;
                                        }
                                        gVar2.c(nVar);
                                        return;
                                    }
                                } catch (Throwable th) {
                                    if (0 == 0 && nVar3 != null) {
                                        gVar2.c(nVar3);
                                    }
                                    throw th;
                                }
                            case 1:
                                gVar.f(bVar2);
                                return;
                            case 2:
                                gVar.g(bVar2);
                                return;
                            case 3:
                                gVar.e(bVar2);
                                return;
                            default:
                                K.b bVar4 = bVar2;
                                Object objP = bVar4.p("inTransaction");
                                Boolean bool = objP instanceof Boolean ? (Boolean) objP : null;
                                boolean z3 = Boolean.TRUE.equals(bool) && bVar4.x() && ((Integer) bVar4.p("transactionId")) == null;
                                g gVar3 = gVar;
                                if (z3) {
                                    int i7 = gVar3.f201k + 1;
                                    gVar3.f201k = i7;
                                    gVar3.f202l = Integer.valueOf(i7);
                                }
                                if (!gVar3.h(bVar4)) {
                                    if (z3) {
                                        gVar3.f202l = null;
                                        return;
                                    }
                                    return;
                                } else if (z3) {
                                    HashMap map4 = new HashMap();
                                    map4.put("transactionId", gVar3.f202l);
                                    bVar4.e(map4);
                                    return;
                                } else {
                                    if (Boolean.FALSE.equals(bool)) {
                                        gVar3.f202l = null;
                                    }
                                    bVar4.e(null);
                                    return;
                                }
                        }
                    }
                });
                break;
            case 2:
                HashMap map3 = s.f246f;
                final K.b bVar3 = new K.b(aVar, kVar);
                gVar.m(bVar3, new Runnable() { // from class: J.c
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        switch (i5) {
                            case 0:
                                g gVar2 = gVar;
                                HashMap map4 = gVar2.f197g;
                                K.b bVar4 = bVar3;
                                H.a aVar2 = bVar4.f263j;
                                Integer num = (Integer) aVar2.x("cursorId");
                                int iIntValue = num.intValue();
                                boolean zEquals = Boolean.TRUE.equals(aVar2.x("cancel"));
                                if (a.a(gVar2.f194d)) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(gVar2.i());
                                    sb.append("cursor ");
                                    sb.append(iIntValue);
                                    sb.append(zEquals ? " cancel" : " next");
                                    Log.d("Sqflite", sb.toString());
                                }
                                n nVar = null;
                                if (zEquals) {
                                    n nVar2 = (n) map4.get(num);
                                    if (nVar2 != null) {
                                        gVar2.c(nVar2);
                                    }
                                    bVar4.e(null);
                                    return;
                                }
                                n nVar3 = (n) map4.get(num);
                                boolean z2 = false;
                                try {
                                    try {
                                        if (nVar3 == null) {
                                            throw new IllegalStateException("Cursor " + iIntValue + " not found");
                                        }
                                        Cursor cursor = nVar3.f226c;
                                        HashMap mapD = g.d(cursor, Integer.valueOf(nVar3.f225b));
                                        if (!cursor.isLast() && !cursor.isAfterLast()) {
                                            z2 = true;
                                        }
                                        if (z2) {
                                            mapD.put("cursorId", num);
                                        }
                                        bVar4.e(mapD);
                                        if (z2) {
                                            return;
                                        }
                                        gVar2.c(nVar3);
                                        return;
                                    } catch (Exception e2) {
                                        gVar2.j(e2, bVar4);
                                        if (nVar3 != null) {
                                            gVar2.c(nVar3);
                                        } else {
                                            nVar = nVar3;
                                        }
                                        if (0 != 0 || nVar == null) {
                                            return;
                                        }
                                        gVar2.c(nVar);
                                        return;
                                    }
                                } catch (Throwable th) {
                                    if (0 == 0 && nVar3 != null) {
                                        gVar2.c(nVar3);
                                    }
                                    throw th;
                                }
                            case 1:
                                gVar.f(bVar3);
                                return;
                            case 2:
                                gVar.g(bVar3);
                                return;
                            case 3:
                                gVar.e(bVar3);
                                return;
                            default:
                                K.b bVar5 = bVar3;
                                Object objP = bVar5.p("inTransaction");
                                Boolean bool = objP instanceof Boolean ? (Boolean) objP : null;
                                boolean z3 = Boolean.TRUE.equals(bool) && bVar5.x() && ((Integer) bVar5.p("transactionId")) == null;
                                g gVar3 = gVar;
                                if (z3) {
                                    int i7 = gVar3.f201k + 1;
                                    gVar3.f201k = i7;
                                    gVar3.f202l = Integer.valueOf(i7);
                                }
                                if (!gVar3.h(bVar5)) {
                                    if (z3) {
                                        gVar3.f202l = null;
                                        return;
                                    }
                                    return;
                                } else if (z3) {
                                    HashMap map5 = new HashMap();
                                    map5.put("transactionId", gVar3.f202l);
                                    bVar5.e(map5);
                                    return;
                                } else {
                                    if (Boolean.FALSE.equals(bool)) {
                                        gVar3.f202l = null;
                                    }
                                    bVar5.e(null);
                                    return;
                                }
                        }
                    }
                });
                break;
            case 3:
                HashMap map4 = s.f246f;
                try {
                    gVar.f199i.setLocale(Locale.forLanguageTag((String) aVar.x("locale")));
                    kVar.d(null);
                } catch (Exception e2) {
                    kVar.a("sqlite_error", "Error calling setLocale: " + e2.getMessage(), null);
                    return;
                }
                break;
            case 4:
                HashMap map5 = s.f246f;
                final K.b bVar4 = new K.b(aVar, kVar);
                final int i7 = 4;
                gVar.m(bVar4, new Runnable() { // from class: J.c
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        switch (i7) {
                            case 0:
                                g gVar2 = gVar;
                                HashMap map6 = gVar2.f197g;
                                K.b bVar5 = bVar4;
                                H.a aVar2 = bVar5.f263j;
                                Integer num = (Integer) aVar2.x("cursorId");
                                int iIntValue = num.intValue();
                                boolean zEquals = Boolean.TRUE.equals(aVar2.x("cancel"));
                                if (a.a(gVar2.f194d)) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(gVar2.i());
                                    sb.append("cursor ");
                                    sb.append(iIntValue);
                                    sb.append(zEquals ? " cancel" : " next");
                                    Log.d("Sqflite", sb.toString());
                                }
                                n nVar = null;
                                if (zEquals) {
                                    n nVar2 = (n) map6.get(num);
                                    if (nVar2 != null) {
                                        gVar2.c(nVar2);
                                    }
                                    bVar5.e(null);
                                    return;
                                }
                                n nVar3 = (n) map6.get(num);
                                boolean z2 = false;
                                try {
                                    try {
                                        if (nVar3 == null) {
                                            throw new IllegalStateException("Cursor " + iIntValue + " not found");
                                        }
                                        Cursor cursor = nVar3.f226c;
                                        HashMap mapD = g.d(cursor, Integer.valueOf(nVar3.f225b));
                                        if (!cursor.isLast() && !cursor.isAfterLast()) {
                                            z2 = true;
                                        }
                                        if (z2) {
                                            mapD.put("cursorId", num);
                                        }
                                        bVar5.e(mapD);
                                        if (z2) {
                                            return;
                                        }
                                        gVar2.c(nVar3);
                                        return;
                                    } catch (Exception e3) {
                                        gVar2.j(e3, bVar5);
                                        if (nVar3 != null) {
                                            gVar2.c(nVar3);
                                        } else {
                                            nVar = nVar3;
                                        }
                                        if (0 != 0 || nVar == null) {
                                            return;
                                        }
                                        gVar2.c(nVar);
                                        return;
                                    }
                                } catch (Throwable th) {
                                    if (0 == 0 && nVar3 != null) {
                                        gVar2.c(nVar3);
                                    }
                                    throw th;
                                }
                            case 1:
                                gVar.f(bVar4);
                                return;
                            case 2:
                                gVar.g(bVar4);
                                return;
                            case 3:
                                gVar.e(bVar4);
                                return;
                            default:
                                K.b bVar6 = bVar4;
                                Object objP = bVar6.p("inTransaction");
                                Boolean bool = objP instanceof Boolean ? (Boolean) objP : null;
                                boolean z3 = Boolean.TRUE.equals(bool) && bVar6.x() && ((Integer) bVar6.p("transactionId")) == null;
                                g gVar3 = gVar;
                                if (z3) {
                                    int i8 = gVar3.f201k + 1;
                                    gVar3.f201k = i8;
                                    gVar3.f202l = Integer.valueOf(i8);
                                }
                                if (!gVar3.h(bVar6)) {
                                    if (z3) {
                                        gVar3.f202l = null;
                                        return;
                                    }
                                    return;
                                } else if (z3) {
                                    HashMap map7 = new HashMap();
                                    map7.put("transactionId", gVar3.f202l);
                                    bVar6.e(map7);
                                    return;
                                } else {
                                    if (Boolean.FALSE.equals(bool)) {
                                        gVar3.f202l = null;
                                    }
                                    bVar6.e(null);
                                    return;
                                }
                        }
                    }
                });
                break;
            case 5:
                HashMap map6 = s.f246f;
                final K.b bVar5 = new K.b(aVar, kVar);
                gVar.m(bVar5, new Runnable() { // from class: J.c
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        switch (i4) {
                            case 0:
                                g gVar2 = gVar;
                                HashMap map7 = gVar2.f197g;
                                K.b bVar6 = bVar5;
                                H.a aVar2 = bVar6.f263j;
                                Integer num = (Integer) aVar2.x("cursorId");
                                int iIntValue = num.intValue();
                                boolean zEquals = Boolean.TRUE.equals(aVar2.x("cancel"));
                                if (a.a(gVar2.f194d)) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(gVar2.i());
                                    sb.append("cursor ");
                                    sb.append(iIntValue);
                                    sb.append(zEquals ? " cancel" : " next");
                                    Log.d("Sqflite", sb.toString());
                                }
                                n nVar = null;
                                if (zEquals) {
                                    n nVar2 = (n) map7.get(num);
                                    if (nVar2 != null) {
                                        gVar2.c(nVar2);
                                    }
                                    bVar6.e(null);
                                    return;
                                }
                                n nVar3 = (n) map7.get(num);
                                boolean z2 = false;
                                try {
                                    try {
                                        if (nVar3 == null) {
                                            throw new IllegalStateException("Cursor " + iIntValue + " not found");
                                        }
                                        Cursor cursor = nVar3.f226c;
                                        HashMap mapD = g.d(cursor, Integer.valueOf(nVar3.f225b));
                                        if (!cursor.isLast() && !cursor.isAfterLast()) {
                                            z2 = true;
                                        }
                                        if (z2) {
                                            mapD.put("cursorId", num);
                                        }
                                        bVar6.e(mapD);
                                        if (z2) {
                                            return;
                                        }
                                        gVar2.c(nVar3);
                                        return;
                                    } catch (Exception e3) {
                                        gVar2.j(e3, bVar6);
                                        if (nVar3 != null) {
                                            gVar2.c(nVar3);
                                        } else {
                                            nVar = nVar3;
                                        }
                                        if (0 != 0 || nVar == null) {
                                            return;
                                        }
                                        gVar2.c(nVar);
                                        return;
                                    }
                                } catch (Throwable th) {
                                    if (0 == 0 && nVar3 != null) {
                                        gVar2.c(nVar3);
                                    }
                                    throw th;
                                }
                            case 1:
                                gVar.f(bVar5);
                                return;
                            case 2:
                                gVar.g(bVar5);
                                return;
                            case 3:
                                gVar.e(bVar5);
                                return;
                            default:
                                K.b bVar7 = bVar5;
                                Object objP = bVar7.p("inTransaction");
                                Boolean bool = objP instanceof Boolean ? (Boolean) objP : null;
                                boolean z3 = Boolean.TRUE.equals(bool) && bVar7.x() && ((Integer) bVar7.p("transactionId")) == null;
                                g gVar3 = gVar;
                                if (z3) {
                                    int i8 = gVar3.f201k + 1;
                                    gVar3.f201k = i8;
                                    gVar3.f202l = Integer.valueOf(i8);
                                }
                                if (!gVar3.h(bVar7)) {
                                    if (z3) {
                                        gVar3.f202l = null;
                                        return;
                                    }
                                    return;
                                } else if (z3) {
                                    HashMap map8 = new HashMap();
                                    map8.put("transactionId", gVar3.f202l);
                                    bVar7.e(map8);
                                    return;
                                } else {
                                    if (Boolean.FALSE.equals(bool)) {
                                        gVar3.f202l = null;
                                    }
                                    bVar7.e(null);
                                    return;
                                }
                        }
                    }
                });
                break;
            default:
                HashMap map7 = s.f246f;
                Boolean bool = Boolean.TRUE;
                boolean zEquals = bool.equals(aVar.x("noResult"));
                boolean zEquals2 = bool.equals(aVar.x("continueOnError"));
                List list = (List) aVar.x("operations");
                ArrayList arrayList = new ArrayList();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    K.a aVar2 = new K.a((Map) it.next(), zEquals);
                    String strR = aVar2.r();
                    strR.getClass();
                    byte b2 = -1;
                    switch (strR.hashCode()) {
                        case -1319569547:
                            if (strR.equals("execute")) {
                                b2 = 0;
                            }
                            break;
                        case -1183792455:
                            if (strR.equals("insert")) {
                                b2 = 1;
                            }
                            break;
                        case -838846263:
                            if (strR.equals("update")) {
                                b2 = 2;
                            }
                            break;
                        case 107944136:
                            if (strR.equals("query")) {
                                b2 = 3;
                            }
                            break;
                    }
                    H.e eVar = aVar2.f260j;
                    switch (b2) {
                        case 0:
                            if (gVar.h(aVar2)) {
                                aVar2.e(null);
                                aVar2.U(arrayList);
                            } else if (!zEquals2) {
                                kVar.a((String) eVar.f175e, (String) eVar.f176f, eVar.f177g);
                            } else {
                                aVar2.T(arrayList);
                            }
                            break;
                        case 1:
                            if (gVar.e(aVar2)) {
                                aVar2.U(arrayList);
                            } else if (!zEquals2) {
                                kVar.a((String) eVar.f175e, (String) eVar.f176f, eVar.f177g);
                            } else {
                                aVar2.T(arrayList);
                            }
                            break;
                        case 2:
                            if (gVar.g(aVar2)) {
                                aVar2.U(arrayList);
                            } else if (!zEquals2) {
                                kVar.a((String) eVar.f175e, (String) eVar.f176f, eVar.f177g);
                            } else {
                                aVar2.T(arrayList);
                            }
                            break;
                        case 3:
                            if (gVar.f(aVar2)) {
                                aVar2.U(arrayList);
                            } else if (!zEquals2) {
                                kVar.a((String) eVar.f175e, (String) eVar.f176f, eVar.f177g);
                            } else {
                                aVar2.T(arrayList);
                            }
                            break;
                        default:
                            kVar.a("bad_param", "Batch method '" + strR + "' not supported", null);
                            break;
                    }
                }
                if (!zEquals) {
                    kVar.d(arrayList);
                } else {
                    kVar.d(null);
                }
                break;
        }
    }

    public /* synthetic */ o(H.a aVar, Y.k kVar, g gVar, int i2) {
        this.f227d = i2;
        this.f228e = aVar;
        this.f229f = kVar;
        this.f230g = gVar;
    }

    public /* synthetic */ o(g gVar, H.a aVar, Y.k kVar) {
        this.f227d = 6;
        this.f230g = gVar;
        this.f228e = aVar;
        this.f229f = kVar;
    }
}
