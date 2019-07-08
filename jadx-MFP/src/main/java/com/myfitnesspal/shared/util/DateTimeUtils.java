package com.myfitnesspal.shared.util;

import android.content.Context;
import android.text.format.DateFormat;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.DateTime;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class DateTimeUtils {
    private static final String[] DAYS_OF_WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static final String DB_DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
    public static final String FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ssZ";
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
            int compare = Integer.compare(createCalendar.get(10), createCalendar2.get(10));
            if (compare == 0) {
                compare = Integer.compare(createCalendar.get(12), createCalendar2.get(12));
            }
            if (compare == 0) {
                compare = Integer.compare(createCalendar.get(13), createCalendar2.get(13));
            }
            return compare;
        }

        private Calendar createCalendar(Date date) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return instance;
        }
    };
    public static final NumberFormat LATIN_NUMBER_FORMAT = NumberFormat.getInstance(Locale.ENGLISH);
    private static final String VALIDATION_FOR_TIME_HH_MM = "[0-2]*[0-9]:[0-5]*[0-9]";
    private static final String VALIDATION_FOR_TIME_HH_MM_SS = "[0-2]*[0-9]:[0-5]*[0-9]:[0-5]*[0-9]";
    public static final Comparator<Date> YMDHMS_COMPARATOR = new Comparator<Date>() {
        public int compare(Date date, Date date2) {
            if (DateTimeUtils.YMD_COMPARATOR.compare(date, date2) == 0) {
                return DateTimeUtils.HMS_COMPARATOR.compare(date, date2);
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
            int compare = Integer.compare(createCalendar.get(1), createCalendar2.get(1));
            if (compare == 0) {
                compare = Integer.compare(createCalendar.get(2), createCalendar2.get(2));
            }
            if (compare == 0) {
                compare = Integer.compare(createCalendar.get(5), createCalendar2.get(5));
            }
            return compare;
        }

        private Calendar createCalendar(Date date) {
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            instance.setTimeInMillis(date.getTime());
            return instance;
        }
    };

    static {
        LATIN_NUMBER_FORMAT.setGroupingUsed(false);
    }

    public static Date parse(String str, String str2) {
        return parse(str, str2, null);
    }

    public static Date parse(String str, String str2, String str3) {
        if (Strings.isEmpty(str) || Strings.isEmpty(str2)) {
            return null;
        }
        try {
            return new SimpleDateFormat(str).parse(str2);
        } catch (ParseException e) {
            Ln.e(e);
            return null;
        }
    }

    public static Date parseTime(Context context, String str) {
        try {
            return DateFormat.getTimeFormat(context).parse(str);
        } catch (ParseException e) {
            Ln.e(e);
            return null;
        }
    }

    public static String format(String str, Date date) {
        return format(str, (TimeZone) null, date);
    }

    public static String format(String str, String str2, Date date) {
        return format(str, TimeZone.getTimeZone(str2), date);
    }

    public static String format(String str, TimeZone timeZone, Date date) {
        if (Strings.isEmpty(str)) {
            return "";
        }
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        if (timeZone != null) {
            simpleDateFormat.setTimeZone(timeZone);
        }
        simpleDateFormat.setNumberFormat(LATIN_NUMBER_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String formatAsHoursAndMinutes(Date date) {
        return format("HH:mm:00", date);
    }

    public static String formatAsHoursAndMinutes(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        return formatAsHoursAndMinutes(instance.getTime());
    }

    public static String formatExtraBrief(Date date) {
        return format("MMM. d", date);
    }

    public static String formatBrief(Date date) {
        return format("EEE, MMM d", date);
    }

    public static String formatCanonical(Date date) {
        return format("MMM d, yyyy", date);
    }

    public static Date mondayOnOrPriorTo(Date date) {
        if (date == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(7);
        if (i == 1) {
            i += 7;
        }
        instance.add(5, 2 - i);
        return instance.getTime();
    }

    public static Date startDayOnOrPriorTo(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i2 = instance.get(7);
        if (i == 0) {
            instance.add(5, -6);
        } else {
            modifyDateBasedOnStartDay(instance, i2, i);
        }
        instance.set(14, 0);
        return instance.getTime();
    }

    private static void modifyDateBasedOnStartDay(Calendar calendar, int i, int i2) {
        if (i < i2) {
            i += 7;
        }
        calendar.add(5, i2 - i);
    }

    public static Date convertUtcToLocal(Date date) {
        if (date == null) {
            return null;
        }
        long time = date.getTime();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(time);
        return new Date(time + ((long) instance.get(15)) + ((long) instance.get(16)));
    }

    public static String formatHumanReadableTime(Context context, Date date) {
        if (date == null || context == null) {
            return "";
        }
        long calculateDelta = calculateDelta(date);
        if (calculateDelta <= 0) {
            return context.getResources().getString(R.string.few_seconds_ago);
        }
        if (calculateDelta == 1) {
            return context.getResources().getString(R.string.minute_ago);
        }
        if (calculateDelta < 60) {
            return String.format(context.getResources().getString(R.string.minutes_ago), new Object[]{Long.toString(calculateDelta)});
        }
        double d = (double) calculateDelta;
        if (d < 90.0d) {
            return context.getResources().getString(R.string.hour_ago);
        }
        if (calculateDelta >= 1440) {
            return formatHumanReadableTimeGreaterThan24Hours(context, calculateDelta);
        }
        return String.format(context.getResources().getString(R.string.hours_ago), new Object[]{Long.toString(Math.round(d / 60.0d))});
    }

    private static long calculateDelta(Date date) {
        return -Math.round(((double) (date.getTime() - new Date().getTime())) / 60000.0d);
    }

    public static String formatHumanReadableTimeRoundUpTo24Hours(Context context, Date date) {
        if (date == null || context == null) {
            return "";
        }
        long calculateDelta = calculateDelta(date);
        if (calculateDelta < 1440) {
            return String.format(context.getResources().getString(R.string.today), new Object[0]);
        }
        return formatHumanReadableTimeGreaterThan24Hours(context, calculateDelta);
    }

    private static String formatHumanReadableTimeGreaterThan24Hours(Context context, long j) {
        if (j < 2880) {
            return context.getResources().getString(R.string.yesterday);
        }
        if (j < 9360) {
            return String.format(context.getResources().getString(R.string.days_ago), new Object[]{Long.toString(Math.round(((double) j) / 1440.0d))});
        } else if (j < 15120) {
            return context.getResources().getString(R.string.week_ago);
        } else {
            if (j < 35280) {
                return String.format(context.getResources().getString(R.string.weeks_ago), new Object[]{Long.toString(Math.round(((double) j) / 10080.0d))});
            }
            double d = (double) j;
            if (d < 64800.0d) {
                return context.getResources().getString(R.string.one_month_ago);
            }
            return String.format(context.getResources().getString(R.string.months_ago), new Object[]{Long.toString(Math.round(d / 43200.0d))});
        }
    }

    public static Date hours(int i, Date date) {
        if (i < 0 || i > 23) {
            throw new IllegalArgumentException("hourCountPastBeginningOfDay must be in [0, 23]");
        } else if (date != null) {
            Calendar calendarFromDate = CalendarUtils.getCalendarFromDate(date);
            calendarFromDate.set(11, i);
            calendarFromDate.set(12, 0);
            return calendarFromDate.getTime();
        } else {
            throw new IllegalArgumentException("date must not be null");
        }
    }

    public static Date offsetDate(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(5, i);
        return instance.getTime();
    }

    public static boolean isOnSameDayAsDate(Date date, Date date2) {
        if (date == null || date2 == null) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date).equals(simpleDateFormat.format(date2));
    }

    public static String formatDb(Date date) {
        return format(DB_DATE_FORMAT, date);
    }

    public static Date parseDb(String str) {
        return parse(DB_DATE_FORMAT, str);
    }

    public static Date parseGivenFormat(String str, String str2) {
        return parse(str, str2);
    }

    public static String stringWithFormattedDate(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static String getMediumLocaleFormattedDate(Context context, Date date) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date);
        return new SimpleDateFormat(instance.get(1) != instance2.get(1) ? "MMM d, yyyy" : "MMM d").format(date);
    }

    public static String getFullLocaleFormatterDate(Context context, Date date) {
        return DateFormat.getLongDateFormat(context).format(date);
    }

    public static String getNormalLocaleFormattedDate(Date date) {
        return java.text.DateFormat.getDateInstance().format(date);
    }

    public static String getShortLocaleFormattedDate(Date date) {
        return java.text.DateFormat.getDateInstance(3).format(date);
    }

    public static String getLongLocaleFormattedDate(Context context, Calendar calendar, boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (z) {
            str = context.getResources().getString(R.string.week_of);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(DateFormat.format("EEEE", calendar));
            sb2.append(",");
            str = sb2.toString();
        }
        sb.append(str);
        sb.append(" ");
        sb.append(getMediumLocaleFormattedDate(context, calendar.getTime()));
        return sb.toString();
    }

    public static String getSimpleLocaleFormattedDate(Context context, Calendar calendar, boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (z) {
            str = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(DateFormat.format("EEEE", calendar));
            sb2.append(",");
            str = sb2.toString();
        }
        sb.append(str);
        sb.append(" ");
        sb.append(getMediumLocaleFormattedDate(context, calendar.getTime()));
        return sb.toString();
    }

    public static boolean isDateToday(Calendar calendar) {
        boolean z = false;
        if (calendar == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        if (calendar.get(5) == instance.get(5) && calendar.get(2) == instance.get(2) && calendar.get(1) == instance.get(1)) {
            z = true;
        }
        return z;
    }

    public static boolean isDateToday(Date date) {
        if (date == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return isDateToday(instance);
    }

    public static boolean isDateToday(long j) {
        if (j <= 0) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return isDateToday(instance);
    }

    public static boolean isDateYesterday(Calendar calendar) {
        boolean z = false;
        if (calendar == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(6, -1);
        if (calendar.get(5) == instance.get(5) && calendar.get(2) == instance.get(2) && calendar.get(1) == instance.get(1)) {
            z = true;
        }
        return z;
    }

    public static boolean isDateTomorrow(Calendar calendar) {
        boolean z = false;
        if (calendar == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(6, 1);
        if (calendar.get(5) == instance.get(5) && calendar.get(2) == instance.get(2) && calendar.get(1) == instance.get(1)) {
            z = true;
        }
        return z;
    }

    public static boolean isDateWeekly(Calendar calendar, Date date) {
        boolean z = false;
        if (calendar == null || date == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (calendar.get(5) == instance.get(5) && calendar.get(2) == instance.get(2) && calendar.get(1) == instance.get(1)) {
            z = true;
        }
        return z;
    }

    public static boolean isSameDay(Date date, Date date2) {
        if (date == null || date2 == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        boolean z = true;
        if (!(instance.get(1) == instance2.get(1) && instance.get(6) == instance2.get(6))) {
            z = false;
        }
        return z;
    }

    public static String localeFormattedTime(Context context, String str) {
        if (!isTimeValid(str)) {
            return null;
        }
        String[] split = str.split(":");
        Calendar instance = Calendar.getInstance();
        instance.set(11, NumberUtils.tryParseInt(split[0]));
        instance.set(12, NumberUtils.tryParseInt(split[1]));
        return DateFormat.getTimeFormat(context).format(instance.getTime());
    }

    public static String localeFormattedTime(Context context, Date date) {
        return (date == null || context == null) ? "" : DateFormat.getTimeFormat(context).format(date);
    }

    public static String localeFormattedTime(Context context, int i, int i2) {
        if (i < 0 || i2 < 0) {
            return "";
        }
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        return DateFormat.getTimeFormat(context).format(instance.getTime());
    }

    public static int[] getTimeComponents(String str) throws NumberFormatException {
        if (Strings.isEmpty(str)) {
            return null;
        }
        String str2 = str.contains(":") ? ":" : "\\.";
        int[] iArr = {0, 0};
        String[] split = str.split(" ");
        if (split != null) {
            String[] split2 = split[0].split(str2);
            if (split2 != null && split2.length >= 2) {
                iArr[0] = NumberUtils.tryParseInt(split2[0]);
                iArr[1] = NumberUtils.tryParseInt(split2[1]);
                return iArr;
            }
        }
        return null;
    }

    public static boolean isTimeValid(String str) {
        return str != null && (str.matches(VALIDATION_FOR_TIME_HH_MM) || str.matches(VALIDATION_FOR_TIME_HH_MM_SS));
    }

    public static boolean isDateOlderThanAge(Date date, int i) {
        if (date == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        Calendar instance2 = Calendar.getInstance();
        instance2.add(1, -i);
        return !instance.after(instance2);
    }

    public static boolean isDateOlderThanDays(Date date, int i) {
        if (date == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        Calendar instance2 = Calendar.getInstance();
        instance2.add(5, -i);
        return !instance.after(instance2);
    }

    public static Calendar getCalendarFromDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance;
    }

    public static Calendar getCalendarFromDate(String str) {
        return getCalendarFromDate(parseDb(str));
    }

    public static boolean isDateInFuture(Date date) {
        return date != null && date.after(Calendar.getInstance().getTime());
    }

    public static int getNumberOfDaysSince(Date date) {
        int i = 0;
        if (date == null) {
            return 0;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        Calendar instance2 = Calendar.getInstance();
        instance2.set(11, 0);
        instance2.set(12, 0);
        instance2.set(13, 0);
        instance2.set(14, 0);
        int i2 = instance.before(instance2) ? 1 : -1;
        while (!instance.equals(instance2)) {
            instance.add(5, i2);
            i += i2;
        }
        return i;
    }

    public static int getNumberOfDaysSince(String str) {
        Date parseDb = parseDb(str);
        if (isDateInFuture(parseDb)) {
            parseDb = Calendar.getInstance().getTime();
        }
        return getNumberOfDaysSince(parseDb);
    }

    public static long getNumberOfDaysBetween(Calendar calendar, Calendar calendar2) {
        long timeInMillis = calendar.getTimeInMillis();
        return TimeUnit.DAYS.convert(calendar2.getTimeInMillis() - timeInMillis, TimeUnit.MILLISECONDS);
    }

    public static long getNumberOfDaysBetween(String str, String str2, String str3) {
        return getNumberOfDaysBetween(getCalendarFromDate(parse(str, str2)), getCalendarFromDate(parse(str, str3)));
    }

    public static long getNumberOfDaysBetween(Date date, Date date2) {
        return getNumberOfDaysBetween(getCalendarFromDate(date), getCalendarFromDate(date2));
    }

    public static long getTimeRemaining(Date date) {
        return date.getTime() - new Date().getTime();
    }

    public static boolean isDateInThePast(Date date) {
        return TimeUnit.SECONDS.convert(getCalendarFromDate(new Date()).getTimeInMillis() - getCalendarFromDate(date).getTimeInMillis(), TimeUnit.SECONDS) > 0;
    }

    public static int getAgeInYears(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar instance = Calendar.getInstance();
        long timeInMillis = instance.getTimeInMillis();
        instance.setTime(date);
        instance.setTime(new Date(timeInMillis - instance.getTimeInMillis()));
        return instance.get(1) - 1970;
    }

    public static boolean isEmpty(Date date) {
        return date == null || date.getTime() == 0;
    }

    public static String getDayOfTheWeek(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return DAYS_OF_WEEK[instance.get(7) - 1];
    }

    public static int getDayOfWeekIndex(String str) throws IllegalArgumentException {
        if (Strings.notEmpty(str)) {
            char c = 65535;
            switch (str.hashCode()) {
                case -2114201671:
                    if (str.equals(DateTime.SATURDAY)) {
                        c = 10;
                        break;
                    }
                    break;
                case -1266285217:
                    if (str.equals(DateTime.FRIDAY)) {
                        c = 8;
                        break;
                    }
                    break;
                case -1068502768:
                    if (str.equals(DateTime.MONDAY)) {
                        c = 0;
                        break;
                    }
                    break;
                case -977343923:
                    if (str.equals(DateTime.TUESDAY)) {
                        c = 2;
                        break;
                    }
                    break;
                case -891186736:
                    if (str.equals(DateTime.SUNDAY)) {
                        c = 12;
                        break;
                    }
                    break;
                case 101661:
                    if (str.equals(DateTime.FRI)) {
                        c = 9;
                        break;
                    }
                    break;
                case 108300:
                    if (str.equals(DateTime.MON)) {
                        c = 1;
                        break;
                    }
                    break;
                case 113638:
                    if (str.equals(DateTime.SAT)) {
                        c = 11;
                        break;
                    }
                    break;
                case 114252:
                    if (str.equals(DateTime.SUN)) {
                        c = 13;
                        break;
                    }
                    break;
                case 114817:
                    if (str.equals(DateTime.THU)) {
                        c = 7;
                        break;
                    }
                    break;
                case 115204:
                    if (str.equals(DateTime.TUE)) {
                        c = 3;
                        break;
                    }
                    break;
                case 117590:
                    if (str.equals(DateTime.WED)) {
                        c = 5;
                        break;
                    }
                    break;
                case 1393530710:
                    if (str.equals(DateTime.WEDNESDAY)) {
                        c = 4;
                        break;
                    }
                    break;
                case 1572055514:
                    if (str.equals(DateTime.THURSDAY)) {
                        c = 6;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                    return 2;
                case 2:
                case 3:
                    return 3;
                case 4:
                case 5:
                    return 4;
                case 6:
                case 7:
                    return 5;
                case 8:
                case 9:
                    return 6;
                case 10:
                case 11:
                    return 7;
                case 12:
                case 13:
                    return 1;
            }
        }
        throw new IllegalArgumentException();
    }

    public static int getDayOfWeekIndex(String str, int i) throws IllegalArgumentException {
        return ((getDayOfWeekIndex(str) - i) + 7) % 7;
    }

    public static String getDayString(Integer num) {
        switch (num.intValue()) {
            case 1:
                return DateTime.SUNDAY;
            case 2:
                return DateTime.MONDAY;
            case 3:
                return DateTime.TUESDAY;
            case 4:
                return DateTime.WEDNESDAY;
            case 5:
                return DateTime.THURSDAY;
            case 6:
                return DateTime.FRIDAY;
            case 7:
                return DateTime.SATURDAY;
            default:
                return "";
        }
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getFormattedDayOFWeek(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            r1 = 0
            switch(r0) {
                case -2114201671: goto L_0x0045;
                case -1266285217: goto L_0x003b;
                case -1068502768: goto L_0x0031;
                case -977343923: goto L_0x0027;
                case -891186736: goto L_0x001d;
                case 1393530710: goto L_0x0013;
                case 1572055514: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x004f
        L_0x0009:
            java.lang.String r0 = "thursday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 3
            goto L_0x0050
        L_0x0013:
            java.lang.String r0 = "wednesday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 2
            goto L_0x0050
        L_0x001d:
            java.lang.String r0 = "sunday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 6
            goto L_0x0050
        L_0x0027:
            java.lang.String r0 = "tuesday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 1
            goto L_0x0050
        L_0x0031:
            java.lang.String r0 = "monday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 0
            goto L_0x0050
        L_0x003b:
            java.lang.String r0 = "friday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 4
            goto L_0x0050
        L_0x0045:
            java.lang.String r0 = "saturday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 5
            goto L_0x0050
        L_0x004f:
            r2 = -1
        L_0x0050:
            switch(r2) {
                case 0: goto L_0x006c;
                case 1: goto L_0x0068;
                case 2: goto L_0x0064;
                case 3: goto L_0x0060;
                case 4: goto L_0x005c;
                case 5: goto L_0x0058;
                case 6: goto L_0x0054;
                default: goto L_0x0053;
            }
        L_0x0053:
            return r1
        L_0x0054:
            r2 = 2131889287(0x7f120c87, float:1.9413233E38)
            return r2
        L_0x0058:
            r2 = 2131889039(0x7f120b8f, float:1.941273E38)
            return r2
        L_0x005c:
            r2 = 2131887947(0x7f12074b, float:1.9410515E38)
            return r2
        L_0x0060:
            r2 = 2131889330(0x7f120cb2, float:1.941332E38)
            return r2
        L_0x0064:
            r2 = 2131889591(0x7f120db7, float:1.941385E38)
            return r2
        L_0x0068:
            r2 = 2131889395(0x7f120cf3, float:1.9413452E38)
            return r2
        L_0x006c:
            r2 = 2131888379(0x7f1208fb, float:1.9411392E38)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.util.DateTimeUtils.getFormattedDayOFWeek(java.lang.String):int");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getLongDayOfWeek(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            r1 = 0
            switch(r0) {
                case -2114201671: goto L_0x0045;
                case -1266285217: goto L_0x003b;
                case -1068502768: goto L_0x0031;
                case -977343923: goto L_0x0027;
                case -891186736: goto L_0x001d;
                case 1393530710: goto L_0x0013;
                case 1572055514: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x004f
        L_0x0009:
            java.lang.String r0 = "thursday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 3
            goto L_0x0050
        L_0x0013:
            java.lang.String r0 = "wednesday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 2
            goto L_0x0050
        L_0x001d:
            java.lang.String r0 = "sunday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 6
            goto L_0x0050
        L_0x0027:
            java.lang.String r0 = "tuesday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 1
            goto L_0x0050
        L_0x0031:
            java.lang.String r0 = "monday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 0
            goto L_0x0050
        L_0x003b:
            java.lang.String r0 = "friday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 4
            goto L_0x0050
        L_0x0045:
            java.lang.String r0 = "saturday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 5
            goto L_0x0050
        L_0x004f:
            r2 = -1
        L_0x0050:
            switch(r2) {
                case 0: goto L_0x006c;
                case 1: goto L_0x0068;
                case 2: goto L_0x0064;
                case 3: goto L_0x0060;
                case 4: goto L_0x005c;
                case 5: goto L_0x0058;
                case 6: goto L_0x0054;
                default: goto L_0x0053;
            }
        L_0x0053:
            return r1
        L_0x0054:
            r2 = 2131889288(0x7f120c88, float:1.9413235E38)
            return r2
        L_0x0058:
            r2 = 2131889040(0x7f120b90, float:1.9412732E38)
            return r2
        L_0x005c:
            r2 = 2131887948(0x7f12074c, float:1.9410517E38)
            return r2
        L_0x0060:
            r2 = 2131889331(0x7f120cb3, float:1.9413323E38)
            return r2
        L_0x0064:
            r2 = 2131889592(0x7f120db8, float:1.9413852E38)
            return r2
        L_0x0068:
            r2 = 2131889396(0x7f120cf4, float:1.9413454E38)
            return r2
        L_0x006c:
            r2 = 2131888380(0x7f1208fc, float:1.9411394E38)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.util.DateTimeUtils.getLongDayOfWeek(java.lang.String):int");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getWeeklyOnDayOfWeek(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            r1 = 0
            switch(r0) {
                case -2114201671: goto L_0x0045;
                case -1266285217: goto L_0x003b;
                case -1068502768: goto L_0x0031;
                case -977343923: goto L_0x0027;
                case -891186736: goto L_0x001d;
                case 1393530710: goto L_0x0013;
                case 1572055514: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x004f
        L_0x0009:
            java.lang.String r0 = "thursday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 3
            goto L_0x0050
        L_0x0013:
            java.lang.String r0 = "wednesday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 2
            goto L_0x0050
        L_0x001d:
            java.lang.String r0 = "sunday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 6
            goto L_0x0050
        L_0x0027:
            java.lang.String r0 = "tuesday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 1
            goto L_0x0050
        L_0x0031:
            java.lang.String r0 = "monday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 0
            goto L_0x0050
        L_0x003b:
            java.lang.String r0 = "friday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 4
            goto L_0x0050
        L_0x0045:
            java.lang.String r0 = "saturday"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 5
            goto L_0x0050
        L_0x004f:
            r2 = -1
        L_0x0050:
            switch(r2) {
                case 0: goto L_0x006c;
                case 1: goto L_0x0068;
                case 2: goto L_0x0064;
                case 3: goto L_0x0060;
                case 4: goto L_0x005c;
                case 5: goto L_0x0058;
                case 6: goto L_0x0054;
                default: goto L_0x0053;
            }
        L_0x0053:
            return r1
        L_0x0054:
            r2 = 2131889607(0x7f120dc7, float:1.9413882E38)
            return r2
        L_0x0058:
            r2 = 2131889606(0x7f120dc6, float:1.941388E38)
            return r2
        L_0x005c:
            r2 = 2131889604(0x7f120dc4, float:1.9413876E38)
            return r2
        L_0x0060:
            r2 = 2131889608(0x7f120dc8, float:1.9413884E38)
            return r2
        L_0x0064:
            r2 = 2131889610(0x7f120dca, float:1.9413888E38)
            return r2
        L_0x0068:
            r2 = 2131889609(0x7f120dc9, float:1.9413886E38)
            return r2
        L_0x006c:
            r2 = 2131889605(0x7f120dc5, float:1.9413878E38)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.util.DateTimeUtils.getWeeklyOnDayOfWeek(java.lang.String):int");
    }

    public static int getFormattedDayOFWeek(int i) {
        switch (i) {
            case 1:
                return R.string.sunday;
            case 2:
                return R.string.monday;
            case 3:
                return R.string.tuesday;
            case 4:
                return R.string.wednesday;
            case 5:
                return R.string.thursday;
            case 6:
                return R.string.friday;
            case 7:
                return R.string.saturday;
            default:
                throw new IllegalArgumentException("day");
        }
    }

    public static String getDayStringForDayIndex(Integer num) {
        int intValue = num.intValue() + 2;
        if (intValue > 7) {
            intValue %= 7;
        }
        return getDayString(Integer.valueOf(intValue));
    }

    public static String diaryDateAnalyticsFormat(Date date) {
        return Format.newIso8601DateFormat().format(date);
    }

    public static Date setToMidnight(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        resetTime(instance);
        return instance.getTime();
    }

    public static void resetTime(Calendar calendar) {
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(10, 0);
    }

    public static String getDateStringISOFormatFromDate(Date date) {
        return Format.newIso8601DateFormat().format(date);
    }

    public static String getDateStringFromDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return String.format("%d-%d-%d", new Object[]{Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(2)), Integer.valueOf(instance.get(1))});
    }

    public static String getDateStringFromCalendarDate(Calendar calendar) {
        return getDateStringFromDate(calendar.getTime());
    }

    public static String getDateStringFromDate(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static long getDateTimeFromNow(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2);
        return instance.getTime().getTime();
    }
}
