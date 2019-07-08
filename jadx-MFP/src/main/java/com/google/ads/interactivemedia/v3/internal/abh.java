package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class abh extends xj<T1> {
    private final /* synthetic */ Class a;
    private final /* synthetic */ abg b;

    abh(abg abg, Class cls) {
        this.b = abg;
        this.a = cls;
    }

    public final void write(abx abx, T1 t1) throws IOException {
        this.b.a.write(abx, t1);
    }

    public final T1 read(abu abu) throws IOException {
        T1 read = this.b.a.read(abu);
        if (read == null || this.a.isInstance(read)) {
            return read;
        }
        StringBuilder sb = new StringBuilder("Expected a ");
        sb.append(this.a.getName());
        sb.append(" but was ");
        sb.append(read.getClass().getName());
        throw new xh(sb.toString());
    }
}
