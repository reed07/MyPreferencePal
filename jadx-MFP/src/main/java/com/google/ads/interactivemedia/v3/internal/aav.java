package com.google.ads.interactivemedia.v3.internal;

import java.sql.Timestamp;
import java.util.Date;

/* compiled from: IMASDK */
final class aav implements xl {
    aav() {
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        if (abt.a() != Timestamp.class) {
            return null;
        }
        return new aaw(this, woVar.a(Date.class));
    }
}
