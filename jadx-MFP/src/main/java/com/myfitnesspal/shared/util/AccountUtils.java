package com.myfitnesspal.shared.util;

import java.util.Calendar;
import java.util.Date;

public final class AccountUtils {
    public static boolean validateAge(Calendar calendar) {
        return calendar != null && validateAge(calendar.getTime());
    }

    public static boolean validateAge(Date date) {
        return DateTimeUtils.isDateOlderThanAge(date, 18);
    }
}
