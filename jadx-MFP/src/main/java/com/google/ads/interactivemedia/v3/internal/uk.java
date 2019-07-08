package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import android.util.Log;

/* compiled from: IMASDK */
public final class uk {
    private static int a = 0;
    private static boolean b = true;

    public static void a(String str, String str2, Throwable th) {
        if (!b) {
            Log.w(str, a(str2, th));
        }
        Log.w(str, str2, th);
    }

    public static void b(String str, String str2, Throwable th) {
        if (!b) {
            Log.e(str, a(str2, th));
        }
        Log.e(str, str2, th);
    }

    private static String a(String str, Throwable th) {
        if (th == null) {
            return str;
        }
        String message = th.getMessage();
        if (TextUtils.isEmpty(message)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(message).length());
        sb.append(str);
        sb.append(" - ");
        sb.append(message);
        return sb.toString();
    }
}
