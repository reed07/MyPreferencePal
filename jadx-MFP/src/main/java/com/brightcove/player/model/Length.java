package com.brightcove.player.model;

import android.util.Log;
import com.brightcove.player.model.StyledElement.Unit;
import com.brightcove.player.util.StringUtil;
import java.io.Serializable;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Length implements Serializable {
    public static final Pattern PATTERN = Pattern.compile("^([0-9.-]+)(%|px|ems|pt)$");
    public static final String TAG = "Length";
    protected Unit unit;
    protected Double value;

    public Length(String str) {
        if (!StringUtil.isEmpty(str)) {
            Matcher matcher = PATTERN.matcher(str);
            if (!matcher.find() || matcher.groupCount() < 2) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("unmatched: ");
                sb.append(str);
                Log.w(str2, sb.toString());
                return;
            }
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            if (StringUtil.isEmpty(group)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("invalid number format: ");
                sb2.append(group);
                throw new IllegalArgumentException(sb2.toString());
            } else if (!StringUtil.isEmpty(group2)) {
                this.value = Double.valueOf(Double.parseDouble(group));
                this.unit = Unit.fromString(group2.toUpperCase(Locale.US));
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("invalid unit format: ");
                sb3.append(group2);
                throw new IllegalArgumentException(sb3.toString());
            }
        } else {
            throw new IllegalArgumentException("must provide a non-empty expression String");
        }
    }

    public Length(double d, Unit unit2) {
        if (unit2 != null) {
            this.value = Double.valueOf(d);
            this.unit = unit2;
            return;
        }
        throw new IllegalArgumentException("must provide a Unit");
    }

    public double getValue() {
        return this.value.doubleValue();
    }

    public Unit getUnit() {
        return this.unit;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Length length = (Length) obj;
        return Double.compare(length.value.doubleValue(), this.value.doubleValue()) == 0 && this.unit == length.unit;
    }

    public int hashCode() {
        long doubleToLongBits = this.value.doubleValue() != 0.0d ? Double.doubleToLongBits(this.value.doubleValue()) : 0;
        return (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + this.unit.hashCode();
    }
}
