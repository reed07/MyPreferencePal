package com.myfitnesspal.shared.util;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import com.myfitnesspal.android.R;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    private static long SECONDS_PER_DAY = 86400;

    public static String getMainDate(Context context, Calendar calendar) {
        String handleTodayYestTomorrow = handleTodayYestTomorrow(context, calendar);
        return Strings.equals(handleTodayYestTomorrow, (String) null) ? DateTimeUtils.getLongLocaleFormattedDate(context, calendar, false) : handleTodayYestTomorrow;
    }

    public static String getMainDateSimpleFormat(Context context, Calendar calendar) {
        String handleTodayYestTomorrow = handleTodayYestTomorrow(context, calendar);
        return Strings.equals(handleTodayYestTomorrow, (String) null) ? DateTimeUtils.getSimpleLocaleFormattedDate(context, calendar, false) : handleTodayYestTomorrow;
    }

    private static String handleTodayYestTomorrow(Context context, Calendar calendar) {
        if (DateTimeUtils.isDateToday(calendar)) {
            return context.getString(R.string.today);
        }
        if (DateTimeUtils.isDateYesterday(calendar)) {
            return context.getString(R.string.yesterday);
        }
        if (DateTimeUtils.isDateTomorrow(calendar)) {
            return context.getString(R.string.tomorrow);
        }
        return null;
    }

    public static int getMainDateColorGreyTheme(Context context, Calendar calendar) {
        if (DateTimeUtils.isDateToday(calendar) || DateTimeUtils.isDateYesterday(calendar) || DateTimeUtils.isDateTomorrow(calendar)) {
            return context.getResources().getColor(R.color.date_bar_text_black);
        }
        return context.getResources().getColor(R.color.other_date);
    }

    public static Spanned getTimestamp(Context context, Date date) {
        return getTimestamp(context, date, true);
    }

    public static Spanned getTimestamp(Context context, Date date, boolean z) {
        try {
            Calendar currentTime = getCurrentTime();
            Calendar lastLoginDate = getLastLoginDate(date);
            long lastLoginDateSecondsAgo = getLastLoginDateSecondsAgo(lastLoginDate, currentTime);
            int color = context.getResources().getColor(R.color.friends_lb_lost);
            int color2 = context.getResources().getColor(R.color.friends_last_login_date_exceeds_10_days);
            if (lastLoginDateSecondsAgo <= SECONDS_PER_DAY && lastLoginDate.get(5) == currentTime.get(5)) {
                String string = context.getResources().getString(R.string.today);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
                if (z) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(color), 0, string.length(), 33);
                }
                return spannableStringBuilder;
            } else if (lastLoginDateSecondsAgo <= SECONDS_PER_DAY) {
                String string2 = context.getResources().getString(R.string.yesterday);
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(string2);
                if (z) {
                    spannableStringBuilder2.setSpan(new ForegroundColorSpan(color), 0, string2.length(), 33);
                }
                return spannableStringBuilder2;
            } else if (lastLoginDateSecondsAgo < SECONDS_PER_DAY || lastLoginDateSecondsAgo > SECONDS_PER_DAY * 2) {
                lastLoginDate.set(5, lastLoginDate.get(5) - 1);
                long timeInMillis = (currentTime.getTimeInMillis() - lastLoginDate.getTimeInMillis()) / 1000;
                if (timeInMillis <= SECONDS_PER_DAY * 30) {
                    int i = (int) ((float) (timeInMillis / SECONDS_PER_DAY));
                    String format = String.format(context.getResources().getString(R.string.days_ago), new Object[]{Integer.toString(i)});
                    SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(format);
                    if (z) {
                        if (i >= 10) {
                            spannableStringBuilder3.setSpan(new ForegroundColorSpan(color2), 0, format.length(), 33);
                        } else {
                            spannableStringBuilder3.setSpan(new ForegroundColorSpan(color), 0, format.length(), 33);
                        }
                    }
                    return spannableStringBuilder3;
                } else if (timeInMillis <= SECONDS_PER_DAY * 365) {
                    int i2 = (int) (timeInMillis / ((long) ((int) (SECONDS_PER_DAY * 30))));
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format(context.getResources().getString(i2 == 1 ? R.string.month_ago : R.string.months_ago), new Object[]{Integer.toString(i2)}));
                    sb.append(" ");
                    String sb2 = sb.toString();
                    SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder(sb2);
                    if (z) {
                        spannableStringBuilder4.setSpan(new ForegroundColorSpan(color2), 0, sb2.length(), 33);
                    }
                    return spannableStringBuilder4;
                } else {
                    String string3 = context.getResources().getString(R.string.inactive);
                    SpannableStringBuilder spannableStringBuilder5 = new SpannableStringBuilder(string3);
                    if (z) {
                        spannableStringBuilder5.setSpan(new ForegroundColorSpan(color2), 0, string3.length(), 33);
                    }
                    return spannableStringBuilder5;
                }
            } else {
                String string4 = context.getResources().getString(R.string.yesterday);
                SpannableStringBuilder spannableStringBuilder6 = new SpannableStringBuilder(string4);
                if (z) {
                    spannableStringBuilder6.setSpan(new ForegroundColorSpan(color), 0, string4.length(), 33);
                }
                return spannableStringBuilder6;
            }
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public static boolean isLastLoginDate60DaysOrMore(Date date) {
        return getLastLoginDateSecondsAgo(getLastLoginDate(date), getCurrentTime()) >= SECONDS_PER_DAY * 60;
    }

    public static Calendar getCurrentTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(10, 0);
        return instance;
    }

    private static Calendar getLastLoginDate(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(DateTimeUtils.convertUtcToLocal(date));
        gregorianCalendar.set(13, 0);
        gregorianCalendar.set(12, 0);
        gregorianCalendar.set(10, 0);
        return gregorianCalendar;
    }

    private static long getLastLoginDateSecondsAgo(Calendar calendar, Calendar calendar2) {
        return (calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / 1000;
    }

    public static boolean areDatesEqualIgnoreTime(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return areDatesEqualIgnoreTime(instance, instance2);
    }

    public static boolean areDatesEqualIgnoreTime(Calendar calendar, Calendar calendar2) {
        boolean z = true;
        if (calendar == null && calendar2 == null) {
            return true;
        }
        if (calendar == null || calendar2 == null) {
            return false;
        }
        if (!(calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2) && calendar.get(5) == calendar2.get(5))) {
            z = false;
        }
        return z;
    }
}
