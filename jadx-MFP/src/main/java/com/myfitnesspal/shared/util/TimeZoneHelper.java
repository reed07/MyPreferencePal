package com.myfitnesspal.shared.util;

import android.content.Context;
import com.myfitnesspal.shared.model.v1.Timezone;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

public class TimeZoneHelper {
    static ArrayList<Timezone> allTimezones = new ArrayList<>();
    static Context context;

    public static void setContext(Context context2) {
        context = context2;
    }

    public static ArrayList<Timezone> allTimezones() {
        ArrayList<Timezone> arrayList = allTimezones;
        if (arrayList == null || arrayList.size() == 0) {
            allTimezones = loadAllTimezones();
        }
        return allTimezones;
    }

    private static ArrayList<Timezone> loadAllTimezones() {
        return new Plist().parseTimezonePlist(context);
    }

    public static Timezone timezoneWithName(String str) {
        Iterator it = allTimezones().iterator();
        while (it.hasNext()) {
            Timezone timezone = (Timezone) it.next();
            if (Strings.equalsIgnoreCase(timezone.getTimezone_name(), str)) {
                return timezone;
            }
        }
        return null;
    }

    public static Timezone timezoneWithIdentifier(String str) {
        Iterator it = allTimezones().iterator();
        while (it.hasNext()) {
            Timezone timezone = (Timezone) it.next();
            if (Strings.equalsIgnoreCase(timezone.getTimezone_identifier(), str)) {
                return timezone;
            }
        }
        return null;
    }

    public static Timezone currentTimezoneFromLocale() {
        Timezone timezoneFromId = timezoneFromId(TimeZone.getDefault().getID().split(",")[0]);
        return timezoneFromId != null ? timezoneFromId : timezoneWithName("Pacific Time (US & Canada)");
    }

    public static Timezone timezoneFromId(String str) {
        Iterator it = allTimezones().iterator();
        while (it.hasNext()) {
            Timezone timezone = (Timezone) it.next();
            if (Strings.equalsIgnoreCase(str, timezone.getTimezone_identifier())) {
                return timezone;
            }
        }
        return null;
    }

    public static Date localTimeOfDayFromOffsetFromMidnightUTC(int i, String str) {
        int uTCOffsetInSeconds = getUTCOffsetInSeconds(str);
        Calendar instance = Calendar.getInstance();
        int i2 = (i + uTCOffsetInSeconds) % 86400;
        while (i2 < 0) {
            i2 += 86400;
        }
        instance.set(11, i2 / 3600);
        instance.set(12, (i2 / 60) % 60);
        return instance.getTime();
    }

    public static int offsetFromMidnightUTCFromLocalTimeOfDay(Calendar calendar, String str) {
        int uTCOffsetInSeconds = (((calendar.get(11) * 3600) + (calendar.get(12) * 60)) - getUTCOffsetInSeconds(str)) % 86400;
        while (uTCOffsetInSeconds < 0) {
            uTCOffsetInSeconds += 86400;
        }
        return uTCOffsetInSeconds;
    }

    private static int getUTCOffsetInSeconds(String str) {
        if (!Strings.notEmpty(str)) {
            return 0;
        }
        int offset = TimeZone.getTimeZone(str).getOffset(new Date().getTime());
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" --- ");
        int i = offset / 1000;
        sb.append(i);
        Ln.v(sb.toString(), new Object[0]);
        return i;
    }
}
