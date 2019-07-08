package com.facebook.ads.internal.a;

import android.text.TextUtils;
import java.util.Locale;

public enum d {
    NONE,
    INSTALLED,
    NOT_INSTALLED;

    public static d a(String str) {
        if (TextUtils.isEmpty(str)) {
            return NONE;
        }
        try {
            return valueOf(str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            return NONE;
        }
    }
}
