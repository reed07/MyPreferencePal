package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* compiled from: IMASDK */
public final class zw extends xj<Time> {
    public static final xl a = new zx();
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final synchronized Time read(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        try {
            return new Time(this.b.parse(abu.h()).getTime());
        } catch (ParseException e) {
            throw new xh((Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final synchronized void write(abx abx, Time time) throws IOException {
        abx.b(time == null ? null : this.b.format(time));
    }
}
