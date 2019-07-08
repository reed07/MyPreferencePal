package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.IOException;

/* compiled from: IMASDK */
public final class mm extends ln implements mi {
    private final Uri a;
    private final so b;
    private final ft c;
    private final ti d;
    private final String e;
    private final int f;
    private final Object g;
    private long h = -9223372036854775807L;
    private boolean i;
    private tz j;

    mm(Uri uri, so soVar, ft ftVar, ti tiVar, String str, int i2, Object obj) {
        this.a = uri;
        this.b = soVar;
        this.c = ftVar;
        this.d = tiVar;
        this.e = str;
        this.f = i2;
        this.g = obj;
    }

    public final void a() throws IOException {
    }

    public final void b() {
    }

    public final void a(tz tzVar) {
        this.j = tzVar;
        b(this.h, this.i);
    }

    public final ll a(lo loVar, sf sfVar) {
        sn a2 = this.b.a();
        tz tzVar = this.j;
        if (tzVar != null) {
            a2.a(tzVar);
        }
        me meVar = new me(this.a, a2, this.c.a(), this.d, a(loVar), this, sfVar, this.e, this.f);
        return meVar;
    }

    public final void a(ll llVar) {
        ((me) llVar).f();
    }

    public final void a(long j2, boolean z) {
        if (j2 == -9223372036854775807L) {
            j2 = this.h;
        }
        if (this.h != j2 || this.i != z) {
            b(j2, z);
        }
    }

    private final void b(long j2, boolean z) {
        this.h = j2;
        this.i = z;
        mw mwVar = new mw(this.h, this.i, false, this.g);
        a((cq) mwVar, (Object) null);
    }
}
