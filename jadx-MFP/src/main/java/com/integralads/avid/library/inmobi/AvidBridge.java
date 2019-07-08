package com.integralads.avid.library.inmobi;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class AvidBridge {
    private static String avidJS;

    public static void setAvidJs(@NonNull String str) {
        avidJS = str;
    }

    public static boolean isAvidJsReady() {
        return !TextUtils.isEmpty(avidJS);
    }

    public static String getAvidJs() {
        return avidJS;
    }
}
