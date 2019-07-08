package com.brightcove.player.util;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class StringUtil {
    public static final String LONG_TIME_FORMAT = "%d:%02d:%02d";
    public static final String SHORTER_TIME_FORMAT = "%01d:%02d";
    public static final String SHORT_TIME_FORMAT = "%02d:%02d";
    public static final String ZERO_TIME_STRING = "0:00";

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String join(List<String> list, String str) {
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append((String) list.get(i));
            if (i < list.size() - 1) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String stringForTime(long j) {
        if (j <= 0) {
            return ZERO_TIME_STRING;
        }
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        Formatter formatter = new Formatter(Locale.getDefault());
        if (j5 > 0) {
            return formatter.format(LONG_TIME_FORMAT, new Object[]{Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)}).toString();
        } else if (j4 < 10) {
            return formatter.format(SHORTER_TIME_FORMAT, new Object[]{Long.valueOf(j4), Long.valueOf(j3)}).toString();
        } else {
            return formatter.format(SHORT_TIME_FORMAT, new Object[]{Long.valueOf(j4), Long.valueOf(j3)}).toString();
        }
    }

    public static String join(String[] strArr, String str) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        return join(Arrays.asList(strArr), str);
    }

    public static CharSequence replaceAll(CharSequence charSequence, String[] strArr, CharSequence[] charSequenceArr) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        while (true) {
            boolean z = false;
            for (String str : strArr) {
                int indexOf = TextUtils.indexOf(spannableStringBuilder, str);
                if (indexOf >= 0) {
                    spannableStringBuilder.setSpan(str, indexOf, str.length() + indexOf, 33);
                    z = true;
                }
            }
            if (!z) {
                return spannableStringBuilder;
            }
            for (int i = 0; i < strArr.length; i++) {
                int spanStart = spannableStringBuilder.getSpanStart(strArr[i]);
                int spanEnd = spannableStringBuilder.getSpanEnd(strArr[i]);
                if (spanStart >= 0) {
                    spannableStringBuilder.replace(spanStart, spanEnd, charSequenceArr[i]);
                }
            }
        }
    }
}
