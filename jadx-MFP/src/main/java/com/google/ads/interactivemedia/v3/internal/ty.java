package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public final class ty implements sn {
    private final sn a;
    private long b;
    private Uri c = Uri.EMPTY;
    private Map<String, List<String>> d = Collections.emptyMap();

    public ty(sn snVar) {
        this.a = (sn) qi.a(snVar);
    }

    public final void d() {
        this.b = 0;
    }

    public final long e() {
        return this.b;
    }

    public final Uri f() {
        return this.c;
    }

    public final Map<String, List<String>> g() {
        return this.d;
    }

    public final void a(tz tzVar) {
        this.a.a(tzVar);
    }

    public final long a(sr srVar) throws IOException {
        this.c = srVar.a;
        this.d = Collections.emptyMap();
        long a2 = this.a.a(srVar);
        this.c = (Uri) qi.a(a());
        this.d = b();
        return a2;
    }

    public final int a(byte[] bArr, int i, int i2) throws IOException {
        int a2 = this.a.a(bArr, i, i2);
        if (a2 != -1) {
            this.b += (long) a2;
        }
        return a2;
    }

    public final Uri a() {
        return this.a.a();
    }

    public final Map<String, List<String>> b() {
        return this.a.b();
    }

    public final void c() throws IOException {
        this.a.c();
    }
}
