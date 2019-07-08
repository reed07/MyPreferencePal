package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class yj implements xl, Cloneable {
    public static final yj a = new yj();
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private List<we> e = Collections.emptyList();
    private List<we> f = Collections.emptyList();

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final yj clone() {
        try {
            return (yj) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final yj a(we weVar, boolean z, boolean z2) {
        yj a2 = clone();
        a2.e = new ArrayList(this.e);
        a2.e.add(weVar);
        return a2;
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        Class a2 = abt.a();
        boolean a3 = a(a2);
        boolean z = a3 || b(a2, true);
        boolean z2 = a3 || b(a2, false);
        if (!z && !z2) {
            return null;
        }
        yk ykVar = new yk(this, z2, z, woVar, abt);
        return ykVar;
    }

    public final boolean a(Field field, boolean z) {
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.b != -1.0d && !a((xo) field.getAnnotation(xo.class), (xp) field.getAnnotation(xp.class))) || field.isSynthetic()) {
            return true;
        }
        if ((!this.d && c(field.getType())) || b(field.getType())) {
            return true;
        }
        List<we> list = z ? this.e : this.f;
        if (!list.isEmpty()) {
            wf wfVar = new wf(field);
            for (we a2 : list) {
                if (a2.a(wfVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean a(Class<?> cls) {
        if (this.b != -1.0d && !a((xo) cls.getAnnotation(xo.class), (xp) cls.getAnnotation(xp.class))) {
            return true;
        }
        if ((this.d || !c(cls)) && !b(cls)) {
            return false;
        }
        return true;
    }

    public final boolean a(Class<?> cls, boolean z) {
        return a(cls) || b(cls, z);
    }

    private final boolean b(Class<?> cls, boolean z) {
        for (we a2 : z ? this.e : this.f) {
            a2.a();
        }
        return false;
    }

    private static boolean b(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private final boolean c(Class<?> cls) {
        if (cls.isMemberClass()) {
            if (!((cls.getModifiers() & 8) != 0)) {
                return true;
            }
        }
        return false;
    }

    private final boolean a(xo xoVar, xp xpVar) {
        if (xoVar == null || xoVar.a() <= this.b) {
            if (xpVar == null || xpVar.a() > this.b) {
                return true;
            }
        }
        return false;
    }
}
