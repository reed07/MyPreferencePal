package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class xk extends xj<T> {
    private final /* synthetic */ xj a;

    xk(xj xjVar) {
        this.a = xjVar;
    }

    public final void write(abx abx, T t) throws IOException {
        if (t == null) {
            abx.f();
        } else {
            this.a.write(abx, t);
        }
    }

    public final T read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return this.a.read(abu);
        }
        abu.j();
        return null;
    }
}
