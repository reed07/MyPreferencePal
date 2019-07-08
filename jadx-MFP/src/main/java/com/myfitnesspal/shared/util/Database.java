package com.myfitnesspal.shared.util;

import com.uacf.core.util.Ln;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
    private static ThreadLocal<SimpleDateFormat> simpleDateFormat = createThreadLocal("yyyy-MM-dd");
    private static ThreadLocal<SimpleDateFormat> simpleDateTimeFormat = createThreadLocal("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<SimpleDateFormat> simpleTimeFormat = createThreadLocal("HH:mm:ss");

    private static ThreadLocal<SimpleDateFormat> createThreadLocal(final String str) {
        return new ThreadLocal<SimpleDateFormat>() {
            /* access modifiers changed from: protected */
            public SimpleDateFormat initialValue() {
                return new SimpleDateFormat(str);
            }
        };
    }

    public static String encodeDate(Date date) {
        return ((SimpleDateFormat) simpleDateFormat.get()).format(date);
    }

    public static String encodeTime(Date date) {
        return ((SimpleDateFormat) simpleTimeFormat.get()).format(date);
    }

    public static String encodeDateAndTime(Date date) {
        return date != null ? ((SimpleDateFormat) simpleDateTimeFormat.get()).format(date) : "2001-01-01 03:00:00";
    }

    public static Date decodeDateString(String str) {
        Date date = new Date();
        if (str.length() == 19) {
            try {
                return ((SimpleDateFormat) simpleDateTimeFormat.get()).parse(str);
            } catch (ParseException e) {
                Ln.e(e);
                return date;
            }
        } else if (str.length() != 10) {
            return date;
        } else {
            try {
                return ((SimpleDateFormat) simpleDateFormat.get()).parse(str);
            } catch (ParseException e2) {
                Ln.e(e2);
                return date;
            }
        }
    }

    public static Date decodeDateAndTimeStringAsLocalTime(String str) {
        try {
            return DateTimeUtils.convertUtcToLocal(((SimpleDateFormat) simpleDateTimeFormat.get()).parse(str));
        } catch (ParseException unused) {
            return new Date();
        }
    }

    public static Date decodeDateAndTimeString(String str) {
        try {
            return ((SimpleDateFormat) simpleDateTimeFormat.get()).parse(str);
        } catch (ParseException unused) {
            return null;
        }
    }

    public static Date decodeTimeString(String str) {
        try {
            return ((SimpleDateFormat) simpleTimeFormat.get()).parse(str);
        } catch (ParseException unused) {
            return null;
        }
    }
}
