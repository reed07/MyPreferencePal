package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Map;

/* compiled from: IMASDK */
public final class zs<T> extends xj<T> {
    private final ys<T> a;
    private final Map<String, zt> b;

    zs(ys<T> ysVar, Map<String, zt> map) {
        this.a = ysVar;
        this.b = map;
    }

    public final T read(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        T a2 = this.a.a();
        try {
            abu.c();
            while (abu.e()) {
                zt ztVar = (zt) this.b.get(abu.g());
                if (ztVar != null) {
                    if (ztVar.c) {
                        ztVar.a(abu, (Object) a2);
                    }
                }
                abu.n();
            }
            abu.d();
            return a2;
        } catch (IllegalStateException e) {
            throw new xh((Throwable) e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void write(abx abx, T t) throws IOException {
        if (t == null) {
            abx.f();
            return;
        }
        abx.d();
        try {
            for (zt ztVar : this.b.values()) {
                if (ztVar.a(t)) {
                    abx.a(ztVar.a);
                    ztVar.a(abx, (Object) t);
                }
            }
            abx.e();
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }
}
