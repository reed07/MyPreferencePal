package com.brightcove.player.model;

import android.util.Log;
import com.brightcove.player.model.StyledElement.Unit;
import com.brightcove.player.util.StringUtil;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LengthVtt extends Length {
    public static final Pattern PATTERN_LINE = Pattern.compile("^([0-9.-]+)$");
    public static final Pattern PATTERN_PERCENTAGE = Pattern.compile("^([0-9.]+)(%)$");
    public static final String TAG = "LengthVtt";

    public LengthVtt(String str) {
        this(0.0d, Unit.UNDEFINED);
        if (!StringUtil.isEmpty(str)) {
            Matcher matcher = PATTERN_PERCENTAGE.matcher(str);
            if (!matcher.find() || matcher.groupCount() < 2) {
                Matcher matcher2 = PATTERN_LINE.matcher(str);
                if (!matcher2.find() || matcher2.groupCount() != 1) {
                    String str2 = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("unmatched: ");
                    sb.append(str);
                    Log.w(str2, sb.toString());
                    return;
                }
                String group = matcher2.group(1);
                if (!StringUtil.isEmpty(group)) {
                    this.value = Double.valueOf(Double.parseDouble(group));
                    this.unit = Unit.LINE;
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("invalid number format: ");
                sb2.append(group);
                throw new IllegalArgumentException(sb2.toString());
            }
            String group2 = matcher.group(1);
            String group3 = matcher.group(2);
            if (StringUtil.isEmpty(group2)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("invalid number format: ");
                sb3.append(group2);
                throw new IllegalArgumentException(sb3.toString());
            } else if (!StringUtil.isEmpty(group3)) {
                this.value = Double.valueOf(Double.parseDouble(group2));
                this.unit = Unit.fromString(group3.toUpperCase(Locale.US));
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("invalid unit format: ");
                sb4.append(group3);
                throw new IllegalArgumentException(sb4.toString());
            }
        } else {
            throw new IllegalArgumentException("must provide a non-empty expression String");
        }
    }

    public LengthVtt(double d, Unit unit) {
        super(d, unit);
    }
}
