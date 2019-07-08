package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: IMASDK */
public final class wx extends wz implements Iterable<wz> {
    private final List<wz> a = new ArrayList();

    public final void a(wz wzVar) {
        if (wzVar == null) {
            wzVar = xb.a;
        }
        this.a.add(wzVar);
    }

    public final Iterator<wz> iterator() {
        return this.a.iterator();
    }

    public final Number a() {
        if (this.a.size() == 1) {
            return ((wz) this.a.get(0)).a();
        }
        throw new IllegalStateException();
    }

    public final String b() {
        if (this.a.size() == 1) {
            return ((wz) this.a.get(0)).b();
        }
        throw new IllegalStateException();
    }

    public final double c() {
        if (this.a.size() == 1) {
            return ((wz) this.a.get(0)).c();
        }
        throw new IllegalStateException();
    }

    public final long d() {
        if (this.a.size() == 1) {
            return ((wz) this.a.get(0)).d();
        }
        throw new IllegalStateException();
    }

    public final int e() {
        if (this.a.size() == 1) {
            return ((wz) this.a.get(0)).e();
        }
        throw new IllegalStateException();
    }

    public final boolean f() {
        if (this.a.size() == 1) {
            return ((wz) this.a.get(0)).f();
        }
        throw new IllegalStateException();
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof wx) && ((wx) obj).a.equals(this.a));
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
