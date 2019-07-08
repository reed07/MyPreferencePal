package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;

/* compiled from: IMASDK */
public abstract class cq {
    public static final cq a = new cr();

    public abstract int a(Object obj);

    public abstract cs a(int i, cs csVar, boolean z);

    public abstract ct a(int i, ct ctVar, boolean z, long j);

    public abstract Object a(int i);

    public abstract int b();

    public abstract int d();

    public final boolean a() {
        return b() == 0;
    }

    private final int e() {
        if (a()) {
            return -1;
        }
        return b() - 1;
    }

    public final int c() {
        return a() ? -1 : 0;
    }

    public final ct a(int i, ct ctVar) {
        return a(i, ctVar, false, 0);
    }

    public final int a(int i, cs csVar, ct ctVar, int i2, boolean z) {
        int i3 = a(i, csVar, false).b;
        if (a(i3, ctVar).c != i) {
            return i + 1;
        }
        switch (i2) {
            case 0:
                if (i3 != e()) {
                    i3++;
                    break;
                } else {
                    i3 = -1;
                    break;
                }
            case 1:
                break;
            case 2:
                if (i3 != e()) {
                    i3++;
                    break;
                } else {
                    i3 = c();
                    break;
                }
            default:
                throw new IllegalStateException();
        }
        if (i3 == -1) {
            return -1;
        }
        return a(i3, ctVar).b;
    }

    public final Pair<Object, Long> a(ct ctVar, cs csVar, int i, long j) {
        return (Pair) qi.a(a(ctVar, csVar, i, j, 0));
    }

    public final Pair<Object, Long> a(ct ctVar, cs csVar, int i, long j, long j2) {
        qi.a(i, 0, b());
        a(i, ctVar, false, j2);
        if (j == -9223372036854775807L) {
            j = ctVar.d;
            if (j == -9223372036854775807L) {
                return null;
            }
        }
        int i2 = ctVar.b;
        long j3 = ctVar.f + j;
        long j4 = a(i2, csVar, true).c;
        while (j4 != -9223372036854775807L && j3 >= j4 && i2 < ctVar.c) {
            j3 -= j4;
            i2++;
            j4 = a(i2, csVar, true).c;
        }
        return Pair.create(qi.a(csVar.a), Long.valueOf(j3));
    }

    public final cs a(Object obj, cs csVar) {
        return a(a(obj), csVar, true);
    }
}
