package com.myfitnesspal.shared.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class CalendarUtils {

    public static final class FetchType {
        public static final int FETCH_AFTER = 2;
        public static final int FETCH_BEFORE = 1;
        public static final int NEW_FETCH = 0;
    }

    public static Calendar getCalendarFromDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance;
    }

    public static boolean areDatesEqual(Calendar calendar, Calendar calendar2) {
        if (calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2) && calendar.get(5) == calendar2.get(5)) {
            return true;
        }
        return false;
    }

    public static List<Calendar> fetchDates(int i, Calendar calendar, int i2, boolean z) {
        int i3;
        ArrayList arrayList = new ArrayList(i2);
        int i4 = z ? 7 : 1;
        for (int i5 = 0; i5 < i2; i5++) {
            Calendar calendar2 = (Calendar) calendar.clone();
            switch (i) {
                case 0:
                    i3 = ((i2 >>> 1) - ((i2 - 1) - i5)) * i4;
                    break;
                case 1:
                    i3 = ((-(i2 - 1)) + (i5 - 1)) * i4;
                    break;
                case 2:
                    i3 = (i5 + 1) * i4;
                    break;
                default:
                    i3 = 0;
                    break;
            }
            calendar2.add(5, i3);
            arrayList.add(calendar2);
        }
        return arrayList;
    }
}
