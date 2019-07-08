package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Currency;

/* compiled from: IMASDK */
final class aau extends xj<Currency> {
    aau() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        return Currency.getInstance(abu.h());
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        abx.b(((Currency) obj).getCurrencyCode());
    }
}
