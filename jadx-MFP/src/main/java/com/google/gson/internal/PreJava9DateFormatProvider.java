package com.google.gson.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PreJava9DateFormatProvider {
    public static DateFormat getUSDateFormat(int i) {
        return new SimpleDateFormat(getDateFormatPattern(i), Locale.US);
    }

    public static DateFormat getUSDateTimeFormat(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getDatePartOfDateTimePattern(i));
        sb.append(" ");
        sb.append(getTimePartOfDateTimePattern(i2));
        return new SimpleDateFormat(sb.toString(), Locale.US);
    }

    private static String getDateFormatPattern(int i) {
        switch (i) {
            case 0:
                return "EEEE, MMMM d, y";
            case 1:
                return "MMMM d, y";
            case 2:
                return "MMM d, y";
            case 3:
                return "M/d/yy";
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown DateFormat style: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private static String getDatePartOfDateTimePattern(int i) {
        switch (i) {
            case 0:
                return "EEEE, MMMM d, yyyy";
            case 1:
                return "MMMM d, yyyy";
            case 2:
                return "MMM d, yyyy";
            case 3:
                return "M/d/yy";
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown DateFormat style: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private static String getTimePartOfDateTimePattern(int i) {
        switch (i) {
            case 0:
            case 1:
                return "h:mm:ss a z";
            case 2:
                return "h:mm:ss a";
            case 3:
                return "h:mm a";
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown DateFormat style: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }
}
