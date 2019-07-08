package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class yk extends xj<T> {
    private xj<T> a;
    private final /* synthetic */ boolean b;
    private final /* synthetic */ boolean c;
    private final /* synthetic */ wo d;
    private final /* synthetic */ abt e;
    private final /* synthetic */ yj f;

    yk(yj yjVar, boolean z, boolean z2, wo woVar, abt abt) {
        this.f = yjVar;
        this.b = z;
        this.c = z2;
        this.d = woVar;
        this.e = abt;
    }

    public final T read(abu abu) throws IOException {
        if (!this.b) {
            return a().read(abu);
        }
        abu.n();
        return null;
    }

    public final void write(abx abx, T t) throws IOException {
        if (this.c) {
            abx.f();
        } else {
            a().write(abx, t);
        }
    }

    private final xj<T> a() {
        xj<T> xjVar = this.a;
        if (xjVar != null) {
            return xjVar;
        }
        xj<T> a2 = this.d.a((xl) this.f, this.e);
        this.a = a2;
        return a2;
    }
}
