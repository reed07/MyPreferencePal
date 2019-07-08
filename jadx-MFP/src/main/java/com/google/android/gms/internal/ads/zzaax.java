package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;

final class zzaax extends zzaau {
    zzaax() {
    }

    public final String zzf(@Nullable String str, String str2) {
        String zzbo = zzbo(str);
        String zzbo2 = zzbo(str2);
        if (TextUtils.isEmpty(zzbo)) {
            return zzbo2;
        }
        if (TextUtils.isEmpty(zzbo2)) {
            return zzbo;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(zzbo).length() + 1 + String.valueOf(zzbo2).length());
        sb.append(zzbo);
        sb.append(",");
        sb.append(zzbo2);
        return sb.toString();
    }

    @Nullable
    private static String zzbo(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i = 0;
        int length = str.length();
        while (i < str.length() && str.charAt(i) == ',') {
            i++;
        }
        while (length > 0 && str.charAt(length - 1) == ',') {
            length--;
        }
        if (length < i) {
            return null;
        }
        if (i == 0 && length == str.length()) {
            return str;
        }
        return str.substring(i, length);
    }
}
