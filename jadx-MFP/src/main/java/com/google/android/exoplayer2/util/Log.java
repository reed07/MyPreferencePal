package com.google.android.exoplayer2.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public final class Log {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_OFF = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_WARNING = 2;
    private static int logLevel = 0;
    private static boolean logStackTraces = true;

    private Log() {
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public boolean getLogStackTraces() {
        return logStackTraces;
    }

    public static void setLogLevel(int i) {
        logLevel = i;
    }

    public static void setLogStackTraces(boolean z) {
        logStackTraces = z;
    }

    public static void d(String str, String str2) {
        if (logLevel == 0) {
            android.util.Log.d(str, str2);
        }
    }

    public static void d(String str, String str2, @Nullable Throwable th) {
        if (!logStackTraces) {
            d(str, appendThrowableMessage(str2, th));
        }
        if (logLevel == 0) {
            android.util.Log.d(str, str2, th);
        }
    }

    public static void i(String str, String str2) {
        if (logLevel <= 1) {
            android.util.Log.i(str, str2);
        }
    }

    public static void i(String str, String str2, @Nullable Throwable th) {
        if (!logStackTraces) {
            i(str, appendThrowableMessage(str2, th));
        }
        if (logLevel <= 1) {
            android.util.Log.i(str, str2, th);
        }
    }

    public static void w(String str, String str2) {
        if (logLevel <= 2) {
            android.util.Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, @Nullable Throwable th) {
        if (!logStackTraces) {
            w(str, appendThrowableMessage(str2, th));
        }
        if (logLevel <= 2) {
            android.util.Log.w(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        if (logLevel <= 3) {
            android.util.Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, @Nullable Throwable th) {
        if (!logStackTraces) {
            e(str, appendThrowableMessage(str2, th));
        }
        if (logLevel <= 3) {
            android.util.Log.e(str, str2, th);
        }
    }

    private static String appendThrowableMessage(String str, @Nullable Throwable th) {
        if (th == null) {
            return str;
        }
        String message = th.getMessage();
        if (!TextUtils.isEmpty(message)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" - ");
            sb.append(message);
            str = sb.toString();
        }
        return str;
    }
}
