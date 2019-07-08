package com.uacf.core.util;

import android.text.method.DigitsKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class TextViewUtils {
    private static final String ACCEPTED_NUMERIC_DIGITS = "0123456789";

    public static void setText(TextView textView, String str, Object... objArr) {
        if (textView != null) {
            if (ArrayUtil.notEmpty(objArr)) {
                str = String.format(str, objArr);
            }
            textView.setText(str);
        }
    }

    public static void setText(TextView textView, Object obj) {
        if (textView != null) {
            textView.setText(Strings.toString(obj));
        }
    }

    public static void setInputType(TextView textView, int i) {
        if (textView != null) {
            textView.setInputType(i);
        }
    }

    public static void setTextColor(TextView textView, int i) {
        if (textView != null && i > 0) {
            textView.setTextColor(i);
        }
    }

    public static void setCursorToEnd(EditText editText) {
        editText.setSelection(Strings.toString(editText.getText()).length());
    }

    public static void setDecimalKeyListener(EditText editText) {
        DecimalFormatSymbols instance = DecimalFormatSymbols.getInstance(Locale.getDefault());
        StringBuilder sb = new StringBuilder();
        sb.append(ACCEPTED_NUMERIC_DIGITS);
        sb.append(instance.getDecimalSeparator());
        editText.setKeyListener(DigitsKeyListener.getInstance(sb.toString()));
    }
}
