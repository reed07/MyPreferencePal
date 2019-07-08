package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public abstract class oy {
    public final bs a;
    public final String b;
    public final long c;
    public final List<ou> d;
    private final ox e;

    private oy(long j, bs bsVar, String str, pb pbVar, List<ou> list) {
        List<ou> list2;
        this.a = bsVar;
        this.b = str;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.d = list2;
        this.e = pbVar.a(this);
        this.c = vf.c(pbVar.c, 1000000, pbVar.b);
    }

    public abstract ox d();

    public abstract ok e();

    public abstract String f();

    public final ox c() {
        return this.e;
    }

    /* synthetic */ oy(long j, bs bsVar, String str, pb pbVar, List list, byte b2) {
        this(j, bsVar, str, pbVar, list);
    }
}
