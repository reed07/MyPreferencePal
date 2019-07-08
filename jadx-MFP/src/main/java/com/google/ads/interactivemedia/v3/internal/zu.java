package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* compiled from: IMASDK */
public final class zu extends xj<Date> {
    public static final xl a = new zv();
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final synchronized Date read(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        try {
            return new Date(this.b.parse(abu.h()).getTime());
        } catch (ParseException e) {
            throw new xh((Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final synchronized void write(abx abx, Date date) throws IOException {
        abx.b(date == null ? null : this.b.format(date));
    }
}
