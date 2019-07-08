package com.shinobicontrols.charts;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateRange extends Range<Date> {
    private static Calendar c = new GregorianCalendar();

    DateRange() {
        super(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    public DateRange(Date date, Date date2) {
        super((double) date.getTime(), (double) date2.getTime());
    }

    public Date getMinimum() {
        return new Date((long) this.a);
    }

    public Date getMaximum() {
        return new Date((long) this.b);
    }

    public double getSpan() {
        return b() / 1000.0d;
    }
}
