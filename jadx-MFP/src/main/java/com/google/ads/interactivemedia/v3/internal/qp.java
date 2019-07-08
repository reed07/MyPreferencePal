package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class qp extends qr {
    public final int a;
    public final long b;
    public final long c;
    public final boolean d;
    public final int e;
    public final long f;
    public final int g;
    public final long h;
    public final boolean i;
    public final boolean j;
    public final fa k;
    public final List<qq> l;
    public final long m;

    public qp(int i2, String str, List<String> list, long j2, long j3, boolean z, int i3, long j4, int i4, long j5, boolean z2, boolean z3, boolean z4, fa faVar, List<qq> list2) {
        String str2 = str;
        List<String> list3 = list;
        super(str, list, z2);
        this.a = i2;
        this.c = j3;
        this.d = z;
        this.e = i3;
        this.f = j4;
        this.g = i4;
        this.h = j5;
        this.i = z3;
        this.j = z4;
        this.k = faVar;
        this.l = Collections.unmodifiableList(list2);
        if (!list2.isEmpty()) {
            qq qqVar = (qq) list2.get(list2.size() - 1);
            this.m = qqVar.e + qqVar.c;
        } else {
            this.m = 0;
        }
        long j6 = j2 == -9223372036854775807L ? -9223372036854775807L : j2 >= 0 ? j2 : this.m + j2;
        this.b = j6;
    }

    public final /* bridge */ /* synthetic */ Object a(List list) {
        return this;
    }

    public final long a() {
        return this.c + this.m;
    }
}
