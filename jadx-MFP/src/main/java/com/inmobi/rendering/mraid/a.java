package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Events;
import com.myfitnesspal.shared.util.DateTimeUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: CalendarUtil */
public class a {
    private static final SimpleDateFormat[] a = {new SimpleDateFormat("yyyy-MM-dd'T'hh:mmZ", Locale.US), new SimpleDateFormat(DateTimeUtils.FORMAT_ISO8601, Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US), new SimpleDateFormat("yyyy-MM-dd", Locale.US), new SimpleDateFormat("yyyy-MM", Locale.US), new SimpleDateFormat("yyyyMMddHHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMddHHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US), new SimpleDateFormat("yyyyMM", Locale.US), new SimpleDateFormat("yyyy", Locale.US)};
    private static String b = a.class.getSimpleName();

    @TargetApi(14)
    public static int a(Context context) {
        Cursor query = context.getContentResolver().query(Events.CONTENT_URI, new String[]{"_id", "title"}, null, null, null);
        int i = 0;
        if (query != null && query.moveToLast()) {
            int columnIndex = query.getColumnIndex("title");
            int columnIndex2 = query.getColumnIndex("_id");
            String string = query.getString(columnIndex);
            String string2 = query.getString(columnIndex2);
            if (string != null) {
                i = Integer.parseInt(string2);
            }
            query.close();
        }
        return i;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String a(String str) {
        Date date;
        String str2 = null;
        if (str != null && !"".equals(str)) {
            SimpleDateFormat[] simpleDateFormatArr = a;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= simpleDateFormatArr.length) {
                    date = null;
                    break;
                }
                try {
                    date = simpleDateFormatArr[i2].parse(str);
                    break;
                } catch (ParseException unused) {
                    i2++;
                }
            }
            if (date != null) {
                DateFormat[] dateFormatArr = {new SimpleDateFormat("yyyyMMdd'T'HHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMdd'T'HHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US)};
                while (true) {
                    if (i >= 3) {
                        break;
                    }
                    try {
                        str2 = dateFormatArr[i].format(Long.valueOf(date.getTime()));
                        break;
                    } catch (IllegalArgumentException unused2) {
                        i++;
                    }
                }
                return str2;
            }
        }
        return null;
    }

    public static GregorianCalendar b(String str) {
        SimpleDateFormat[] simpleDateFormatArr = a;
        int length = simpleDateFormatArr.length;
        int i = 0;
        while (i < length) {
            SimpleDateFormat simpleDateFormat = simpleDateFormatArr[i];
            try {
                Date parse = simpleDateFormat.parse(str);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(parse);
                new StringBuilder("Date format: ").append(simpleDateFormat.toPattern());
                return gregorianCalendar;
            } catch (ParseException unused) {
                new StringBuilder("Skipping format: ").append(simpleDateFormat.toPattern());
                i++;
            }
        }
        return null;
    }

    public static String a(JSONArray jSONArray, int i, int i2) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (i3 < jSONArray.length()) {
            try {
                int i4 = jSONArray.getInt(i3);
                if (i4 >= i && i4 <= i2) {
                    if (i4 != 0) {
                        sb.append(i4);
                        sb.append(",");
                    }
                }
                i3++;
            } catch (JSONException e) {
                new StringBuilder("Could not parse day ").append(e.getMessage());
                return null;
            }
        }
        String sb2 = sb.toString();
        int length = sb2.length();
        if (length == 0) {
            return null;
        }
        int i5 = length - 1;
        if (sb2.charAt(i5) == ',') {
            sb2 = sb2.substring(0, i5);
        }
        return sb2;
    }

    public static String a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                sb.append(jSONArray.get(i));
                sb.append(",");
                i++;
            } catch (JSONException e) {
                new StringBuilder("Could not parse day object ").append(e.toString());
                return null;
            }
        }
        String sb2 = sb.toString();
        int length = sb2.length();
        if (length == 0) {
            return null;
        }
        int i2 = length - 1;
        if (sb2.charAt(i2) == ',') {
            sb2 = sb2.substring(0, i2);
        }
        return sb2;
    }
}
