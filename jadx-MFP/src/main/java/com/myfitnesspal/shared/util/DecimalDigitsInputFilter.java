package com.myfitnesspal.shared.util;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;

public class DecimalDigitsInputFilter implements InputFilter {
    public static final String PATTERN_FORMAT = "(([0-9]{0,%s})?)?((\\.|\\,)[0-9]{0,%s})?";
    Pattern mPattern;

    public DecimalDigitsInputFilter(int i, int i2) {
        this.mPattern = Pattern.compile(String.format(PATTERN_FORMAT, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        StringBuilder sb = new StringBuilder(spanned);
        sb.replace(i3, i4, charSequence.subSequence(i, i2).toString());
        if (!this.mPattern.matcher(sb.toString()).matches()) {
            return charSequence.length() == 0 ? spanned.subSequence(i3, i4) : "";
        }
        return null;
    }
}
