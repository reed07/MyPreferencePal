package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class wu<T> extends xj<T> {
    private xj<T> a;

    wu() {
    }

    public final void a(xj<T> xjVar) {
        if (this.a == null) {
            this.a = xjVar;
            return;
        }
        throw new AssertionError();
    }

    public final T read(abu abu) throws IOException {
        xj<T> xjVar = this.a;
        if (xjVar != null) {
            return xjVar.read(abu);
        }
        throw new IllegalStateException();
    }

    public final void write(abx abx, T t) throws IOException {
        xj<T> xjVar = this.a;
        if (xjVar != null) {
            xjVar.write(abx, t);
            return;
        }
        throw new IllegalStateException();
    }
}
