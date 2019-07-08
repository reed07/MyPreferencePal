package com.myfitnesspal.feature.externalsync.impl.googlefit.util;

import com.uacf.core.util.Strings;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class GoogleFitDateTimeUtils {
    public static Date getEndOfDay(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 23);
        instance.set(12, 59);
        instance.set(13, 59);
        instance.set(14, 999);
        return instance.getTime();
    }

    public static Date getStartOfDay(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTime();
    }

    public static String format(String str, Date date) {
        return format(str, null, date);
    }

    public static String format(String str, String str2, Date date) {
        if (Strings.isEmpty(str)) {
            return "";
        }
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        if (Strings.notEmpty(str2)) {
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(str2));
        }
        return simpleDateFormat.format(date);
    }
}
