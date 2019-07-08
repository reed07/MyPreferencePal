package com.uacf.core.util;

import android.text.method.DigitsKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import lanchon.dexpatcher.annotation.DexAction;
import lanchon.dexpatcher.annotation.DexEdit;
import lanchon.dexpatcher.annotation.DexIgnore;

@DexIgnore
public final class TextViewUtils {
    private static final String ACCEPTED_NUMERIC_DIGITS = "0123456789";

    public static void setText(TextView textView, String str, Object... objArr) {

    }

    public static void setText(TextView textView, Object obj) {

    }

    public static void setInputType(TextView textView, int i) {

    }

    public static void setTextColor(TextView textView, int i) {
    }

    public static void setCursorToEnd(EditText editText) {
    }

    public static void setDecimalKeyListener(EditText editText) {
    }
}
