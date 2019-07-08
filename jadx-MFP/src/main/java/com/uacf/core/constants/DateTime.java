package com.uacf.core.constants;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public final class DateTime {

    public static final class Format {
        public static SimpleDateFormat newIso8601DateFormat() {
            return newSimpleDateFormat("yyyy-MM-dd");
        }

        public static SimpleDateFormat newIso8601DateTimeFormat() {
            SimpleDateFormat newSimpleDateFormat = newSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            newSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return newSimpleDateFormat;
        }

        public static SimpleDateFormat newIso8601DateTimeFormatWithMs() {
            SimpleDateFormat newSimpleDateFormat = newSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
            newSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return newSimpleDateFormat;
        }

        public static SimpleDateFormat newDatabaseDateTimeFormat() {
            return newSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        public static SimpleDateFormat newDatabaseTimeFormat() {
            return newSimpleDateFormat("HH:mm:ss");
        }

        private static SimpleDateFormat newSimpleDateFormat(String str) {
            return new SimpleDateFormat(str, Locale.ENGLISH);
        }

        public static final SimpleDateFormat new24HourDatabaseDateTimeFormat() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        }

        public static SimpleDateFormat newFacebookDateFormat() {
            return newSimpleDateFormat("MM/dd/yyyy");
        }
    }
}
