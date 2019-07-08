package com.uacf.core.util;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

public final class DateTimeComparators {
    public static final Comparator<Date> HMS_COMPARATOR = new Comparator<Date>() {
        public int compare(Date date, Date date2) {
            if (date == date2) {
                return 0;
            }
            if (date == null) {
                return -1;
            }
            if (date2 == null) {
                return 1;
            }
            Calendar createCalendar = createCalendar(date);
            Calendar createCalendar2 = createCalendar(date2);
            int access$000 = DateTimeComparators.compareValues(createCalendar.get(10), createCalendar2.get(10));
            if (access$000 == 0) {
                access$000 = DateTimeComparators.compareValues(createCalendar.get(12), createCalendar2.get(12));
            }
            if (access$000 == 0) {
                access$000 = DateTimeComparators.compareValues(createCalendar.get(13), createCalendar2.get(13));
            }
            return access$000;
        }

        private Calendar createCalendar(Date date) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return instance;
        }
    };
    public static final Comparator<Date> YMDHMS_COMPARATOR = new Comparator<Date>() {
        public int compare(Date date, Date date2) {
            if (DateTimeComparators.YMD_COMPARATOR.compare(date, date2) == 0) {
                return DateTimeComparators.HMS_COMPARATOR.compare(date, date2);
            }
            return 1;
        }
    };
    public static final Comparator<Date> YMD_COMPARATOR = new Comparator<Date>() {
        public int compare(Date date, Date date2) {
            if (date == date2) {
                return 0;
            }
            if (date == null) {
                return -1;
            }
            if (date2 == null) {
                return 1;
            }
            Calendar createCalendar = createCalendar(date);
            Calendar createCalendar2 = createCalendar(date2);
            int access$000 = DateTimeComparators.compareValues(createCalendar.get(1), createCalendar2.get(1));
            if (access$000 == 0) {
                access$000 = DateTimeComparators.compareValues(createCalendar.get(2), createCalendar2.get(2));
            }
            if (access$000 == 0) {
                access$000 = DateTimeComparators.compareValues(createCalendar.get(5), createCalendar2.get(5));
            }
            return access$000;
        }

        private Calendar createCalendar(Date date) {
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            instance.setTimeInMillis(date.getTime());
            return instance;
        }
    };

    /* access modifiers changed from: private */
    public static int compareValues(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }
}
