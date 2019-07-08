package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: IMASDK */
final class abn extends xj<AtomicBoolean> {
    abn() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        return new AtomicBoolean(abu.i());
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        abx.a(((AtomicBoolean) obj).get());
    }
}
