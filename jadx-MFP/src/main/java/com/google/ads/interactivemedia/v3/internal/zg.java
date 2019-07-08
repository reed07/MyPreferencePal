package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* compiled from: IMASDK */
public final class zg extends xj<Date> {
    public static final xl a = new zh();
    private final List<DateFormat> b = new ArrayList();

    public zg() {
        this.b.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.b.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (yl.b()) {
            this.b.add(qi.a(2, 2));
        }
    }

    private final synchronized Date a(String str) {
        for (DateFormat parse : this.b) {
            try {
                return parse.parse(str);
            } catch (ParseException unused) {
            }
        }
        try {
            return abp.a(str, new ParsePosition(0));
        } catch (ParseException e) {
            throw new xh(str, e);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final synchronized void write(abx abx, Date date) throws IOException {
        if (date == null) {
            abx.f();
        } else {
            abx.b(((DateFormat) this.b.get(0)).format(date));
        }
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return a(abu.h());
        }
        abu.j();
        return null;
    }
}
