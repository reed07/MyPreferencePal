package com.google.ads.interactivemedia.v3.internal;

import com.myfitnesspal.shared.constants.Constants.Extras;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* compiled from: IMASDK */
final class aax extends xj<Calendar> {
    aax() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        abu.c();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (abu.f() != abw.END_OBJECT) {
            String g = abu.g();
            int m = abu.m();
            if ("year".equals(g)) {
                i = m;
            } else if (Extras.MONTH.equals(g)) {
                i2 = m;
            } else if ("dayOfMonth".equals(g)) {
                i3 = m;
            } else if ("hourOfDay".equals(g)) {
                i4 = m;
            } else if ("minute".equals(g)) {
                i5 = m;
            } else if ("second".equals(g)) {
                i6 = m;
            }
        }
        abu.d();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(i, i2, i3, i4, i5, i6);
        return gregorianCalendar;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        Calendar calendar = (Calendar) obj;
        if (calendar == null) {
            abx.f();
            return;
        }
        abx.d();
        abx.a("year");
        abx.a((long) calendar.get(1));
        abx.a(Extras.MONTH);
        abx.a((long) calendar.get(2));
        abx.a("dayOfMonth");
        abx.a((long) calendar.get(5));
        abx.a("hourOfDay");
        abx.a((long) calendar.get(11));
        abx.a("minute");
        abx.a((long) calendar.get(12));
        abx.a("second");
        abx.a((long) calendar.get(13));
        abx.e();
    }
}
