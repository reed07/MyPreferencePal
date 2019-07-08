package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: IMASDK */
final class ws extends xj<AtomicLong> {
    private final /* synthetic */ xj a;

    ws(xj xjVar) {
        this.a = xjVar;
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        return new AtomicLong(((Number) this.a.read(abu)).longValue());
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        this.a.write(abx, Long.valueOf(((AtomicLong) obj).get()));
    }
}
