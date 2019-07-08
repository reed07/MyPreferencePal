package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/* compiled from: IMASDK */
final class aaw extends xj<Timestamp> {
    private final /* synthetic */ xj a;

    aaw(aav aav, xj xjVar) {
        this.a = xjVar;
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        Date date = (Date) this.a.read(abu);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        this.a.write(abx, (Timestamp) obj);
    }
}
