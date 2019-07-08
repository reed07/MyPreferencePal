package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

class ac {
    Range<Date> a;
    private final Calendar b = new GregorianCalendar();

    ac() {
        this.b.clear();
    }

    /* access modifiers changed from: 0000 */
    public List<Range<Date>> a(RepeatedTimePeriod repeatedTimePeriod, Range<Date> range) {
        this.a = range;
        ArrayList arrayList = new ArrayList();
        if (Range.a(range)) {
            return arrayList;
        }
        Date b2 = b(repeatedTimePeriod, range);
        Date date = (Date) range.getMaximum();
        DateFrequency dateFrequency = repeatedTimePeriod.b;
        DateFrequency dateFrequency2 = repeatedTimePeriod.c;
        while (b2.before(date)) {
            arrayList.add(new DateRange(b2, a(b2, dateFrequency)));
            b2 = a(b2, dateFrequency2);
        }
        return arrayList;
    }

    private Date b(RepeatedTimePeriod repeatedTimePeriod, Range<Date> range) {
        Date start = repeatedTimePeriod.getStart();
        Date date = (Date) range.getMinimum();
        DateFrequency frequency = repeatedTimePeriod.getFrequency();
        this.b.clear();
        this.b.setTime(start);
        while (start.before(date)) {
            start = a(start, frequency);
        }
        while (start.after(date)) {
            start = b(start, frequency);
        }
        return this.b.getTime();
    }

    private Date a(Date date, DateFrequency dateFrequency) {
        return a(date, dateFrequency, true);
    }

    private Date b(Date date, DateFrequency dateFrequency) {
        return a(date, dateFrequency, false);
    }

    private Date a(Date date, DateFrequency dateFrequency, boolean z) {
        int i = z ? dateFrequency.a : -dateFrequency.a;
        this.b.setTime(date);
        this.b.add(dateFrequency.b.a, i);
        return this.b.getTime();
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Range<Date> range) {
        Range<Date> range2 = this.a;
        return range2 == null || !range2.d(range);
    }
}
