package io.uacf.thumbprint.ui.internal.password;

import android.text.TextUtils;

final class UacfPasswordValidator {
    UacfPasswordValidator() {
    }

    public static boolean isValidPassword(String str) {
        return !TextUtils.isEmpty(str) && meetsLengthRequirement(str);
    }

    public static boolean meetsLengthRequirement(String str) {
        return str != null && str.length() >= 6;
    }
}
