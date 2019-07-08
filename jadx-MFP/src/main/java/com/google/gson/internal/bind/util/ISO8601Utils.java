package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.text.Typography;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);
    private static final String UTC_ID = "UTC";

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        char c = '-';
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        TimeZone timeZone;
        String str3 = str;
        ParsePosition parsePosition2 = parsePosition;
        try {
            int index = parsePosition.getIndex();
            int i7 = index + 4;
            int parseInt = parseInt(str3, index, i7);
            if (checkOffset(str3, i7, '-')) {
                i7++;
            }
            int i8 = i7 + 2;
            int parseInt2 = parseInt(str3, i7, i8);
            if (checkOffset(str3, i8, '-')) {
                i8++;
            }
            int i9 = i8 + 2;
            int parseInt3 = parseInt(str3, i8, i9);
            boolean checkOffset = checkOffset(str3, i9, 'T');
            if (checkOffset || str.length() > i9) {
                if (checkOffset) {
                    int i10 = i9 + 1;
                    int i11 = i10 + 2;
                    i5 = parseInt(str3, i10, i11);
                    if (checkOffset(str3, i11, ':')) {
                        i11++;
                    }
                    i2 = i11 + 2;
                    i4 = parseInt(str3, i11, i2);
                    if (checkOffset(str3, i2, ':')) {
                        i2++;
                    }
                    if (str.length() > i2) {
                        char charAt = str3.charAt(i2);
                        if (!(charAt == 'Z' || charAt == '+' || charAt == '-')) {
                            int i12 = i2 + 2;
                            int parseInt4 = parseInt(str3, i2, i12);
                            i = 59;
                            if (parseInt4 <= 59 || parseInt4 >= 63) {
                                i = parseInt4;
                            }
                            if (checkOffset(str3, i12, '.')) {
                                int i13 = i12 + 1;
                                i2 = indexOfNonDigit(str3, i13 + 1);
                                int min = Math.min(i2, i13 + 3);
                                int parseInt5 = parseInt(str3, i13, min);
                                switch (min - i13) {
                                    case 1:
                                        parseInt5 *= 100;
                                        break;
                                    case 2:
                                        parseInt5 *= 10;
                                        break;
                                }
                                i3 = parseInt5;
                            } else {
                                i2 = i12;
                                i3 = 0;
                            }
                        }
                    }
                    i3 = 0;
                    i = 0;
                } else {
                    i2 = i9;
                    i5 = 0;
                    i4 = 0;
                    i3 = 0;
                    i = 0;
                }
                if (str.length() > i2) {
                    char charAt2 = str3.charAt(i2);
                    if (charAt2 == 'Z') {
                        timeZone = TIMEZONE_UTC;
                        i6 = i2 + 1;
                    } else {
                        if (charAt2 != '+') {
                            if (charAt2 != '-') {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Invalid time zone indicator '");
                                sb.append(charAt2);
                                sb.append("'");
                                throw new IndexOutOfBoundsException(sb.toString());
                            }
                        }
                        String substring = str3.substring(i2);
                        if (substring.length() < 5) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(substring);
                            sb2.append("00");
                            substring = sb2.toString();
                        }
                        i6 = i2 + substring.length();
                        if (!"+0000".equals(substring)) {
                            if (!"+00:00".equals(substring)) {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("GMT");
                                sb3.append(substring);
                                String sb4 = sb3.toString();
                                TimeZone timeZone2 = TimeZone.getTimeZone(sb4);
                                String id = timeZone2.getID();
                                if (!id.equals(sb4)) {
                                    if (!id.replace(":", "").equals(sb4)) {
                                        StringBuilder sb5 = new StringBuilder();
                                        sb5.append("Mismatching time zone indicator: ");
                                        sb5.append(sb4);
                                        sb5.append(" given, resolves to ");
                                        sb5.append(timeZone2.getID());
                                        throw new IndexOutOfBoundsException(sb5.toString());
                                    }
                                }
                                timeZone = timeZone2;
                            }
                        }
                        timeZone = TIMEZONE_UTC;
                    }
                    GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);
                    gregorianCalendar.setLenient(false);
                    gregorianCalendar.set(1, parseInt);
                    gregorianCalendar.set(2, parseInt2 - 1);
                    gregorianCalendar.set(5, parseInt3);
                    gregorianCalendar.set(11, i5);
                    gregorianCalendar.set(12, i4);
                    gregorianCalendar.set(13, i);
                    gregorianCalendar.set(14, i3);
                    parsePosition2.setIndex(i6);
                    return gregorianCalendar.getTime();
                }
                throw new IllegalArgumentException("No time zone indicator");
            }
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(parseInt, parseInt2 - 1, parseInt3);
            parsePosition2.setIndex(i9);
            return gregorianCalendar2.getTime();
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException e) {
            if (str3 == null) {
                str2 = null;
            } else {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(Typography.quote);
                sb6.append(str3);
                sb6.append(Typography.quote);
                str2 = sb6.toString();
            }
            String message = e.getMessage();
            if (message == null || message.isEmpty()) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append("(");
                sb7.append(e.getClass().getName());
                sb7.append(")");
                message = sb7.toString();
            }
            StringBuilder sb8 = new StringBuilder();
            sb8.append("Failed to parse date [");
            sb8.append(str2);
            sb8.append("]: ");
            sb8.append(message);
            ParseException parseException = new ParseException(sb8.toString(), parsePosition.getIndex());
            parseException.initCause(e);
            throw parseException;
        }
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i3 = -digit;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid number: ");
                sb.append(str.substring(i, i2));
                throw new NumberFormatException(sb.toString());
            }
        } else {
            i4 = i;
            i3 = 0;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
                i4 = i5;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid number: ");
                sb2.append(str.substring(i, i2));
                throw new NumberFormatException(sb2.toString());
            }
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
