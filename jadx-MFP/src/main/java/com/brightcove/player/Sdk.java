package com.brightcove.player;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Locale;

public final class Sdk {
    private Sdk() {
    }

    @NonNull
    public static String getVersionName() {
        return TextUtils.isEmpty("1.0") ? "?" : "1.0";
    }

    @NonNull
    public static String makeHttpUserAgent(@NonNull String str, @Nullable String str2) {
        String str3;
        Locale locale = Locale.getDefault();
        String str4 = "%s/%s (Linux;Android %s)%s";
        Object[] objArr = new Object[4];
        objArr[0] = str;
        objArr[1] = getVersionName();
        objArr[2] = VERSION.RELEASE;
        if (str2 == null) {
            str3 = "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            sb.append(str2);
            str3 = sb.toString();
        }
        objArr[3] = str3;
        return String.format(locale, str4, objArr);
    }
}
