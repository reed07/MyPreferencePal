package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* compiled from: IMASDK */
final class wd extends xj<Date> {
    private final Class<? extends Date> a;
    private final List<DateFormat> b = new ArrayList();

    wd(Class<? extends Date> cls, String str) {
        this.a = a(cls);
        this.b.add(new SimpleDateFormat(str, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.b.add(new SimpleDateFormat(str));
        }
    }

    public wd(Class<? extends Date> cls, int i, int i2) {
        this.a = a(cls);
        this.b.add(DateFormat.getDateTimeInstance(i, i2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.b.add(DateFormat.getDateTimeInstance(i, i2));
        }
        if (yl.b()) {
            this.b.add(qi.a(i, i2));
        }
    }

    private static Class<? extends Date> a(Class<? extends Date> cls) {
        if (cls == Date.class || cls == java.sql.Date.class || cls == Timestamp.class) {
            return cls;
        }
        StringBuilder sb = new StringBuilder("Date type must be one of ");
        sb.append(Date.class);
        sb.append(", ");
        sb.append(Timestamp.class);
        sb.append(", or ");
        sb.append(java.sql.Date.class);
        sb.append(" but was ");
        sb.append(cls);
        throw new IllegalArgumentException(sb.toString());
    }

    private final Date a(String str) {
        synchronized (this.b) {
            for (DateFormat parse : this.b) {
                try {
                    Date parse2 = parse.parse(str);
                    return parse2;
                } catch (ParseException unused) {
                }
            }
            try {
                Date a2 = abp.a(str, new ParsePosition(0));
                return a2;
            } catch (ParseException e) {
                throw new xh(str, e);
            }
        }
    }

    public final String toString() {
        DateFormat dateFormat = (DateFormat) this.b.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            StringBuilder sb = new StringBuilder("DefaultDateTypeAdapter(");
            sb.append(((SimpleDateFormat) dateFormat).toPattern());
            sb.append(')');
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder("DefaultDateTypeAdapter(");
        sb2.append(dateFormat.getClass().getSimpleName());
        sb2.append(')');
        return sb2.toString();
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        Date a2 = a(abu.h());
        Class<? extends Date> cls = this.a;
        if (cls == Date.class) {
            return a2;
        }
        if (cls == Timestamp.class) {
            return new Timestamp(a2.getTime());
        }
        if (cls == java.sql.Date.class) {
            return new java.sql.Date(a2.getTime());
        }
        throw new AssertionError();
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        Date date = (Date) obj;
        if (date == null) {
            abx.f();
            return;
        }
        synchronized (this.b) {
            abx.b(((DateFormat) this.b.get(0)).format(date));
        }
    }
}
