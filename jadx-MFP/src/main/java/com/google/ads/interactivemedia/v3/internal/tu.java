package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public final class tu<T> implements to {
    public final sr a;
    public final int b;
    private final ty c;
    private final tv<? extends T> d;
    private volatile T e;

    public tu(sn snVar, Uri uri, int i, tv<? extends T> tvVar) {
        this(snVar, new sr(uri, 1), i, tvVar);
    }

    public final void a() {
    }

    private tu(sn snVar, sr srVar, int i, tv<? extends T> tvVar) {
        this.c = new ty(snVar);
        this.a = srVar;
        this.b = i;
        this.d = tvVar;
    }

    public final T c() {
        return this.e;
    }

    public final long d() {
        return this.c.e();
    }

    public final Uri e() {
        return this.c.f();
    }

    public final Map<String, List<String>> f() {
        return this.c.g();
    }

    public final void b() throws IOException {
        this.c.d();
        sq sqVar = new sq(this.c, this.a);
        try {
            sqVar.a();
            this.e = this.d.a((Uri) qi.a(this.c.a()), sqVar);
        } finally {
            vf.a((Closeable) sqVar);
        }
    }
}
