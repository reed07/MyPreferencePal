package com.brightcove.player.logging;

import android.support.annotation.NonNull;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class Log {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Level {
    }

    private Log() {
    }

    public static boolean isLoggable(@NonNull String str, int i) {
        return android.util.Log.isLoggable(str, i);
    }

    private static int println(int i, @NonNull String str, @NonNull String str2, Throwable th, Object... objArr) {
        if (isLoggable(str, i)) {
            StringBuilder sb = new StringBuilder();
            sb.append("[Thread #");
            sb.append(Thread.currentThread().getId());
            sb.append("] ");
            sb.append(String.format(Locale.getDefault(), str2, objArr));
            if (th != null) {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                sb.append(": ");
                sb.append(stringWriter.toString());
            }
            android.util.Log.println(i, str, sb.toString());
        }
        return 0;
    }

    public static int v(@NonNull String str, @NonNull String str2, Object... objArr) {
        return println(2, str, str2, null, objArr);
    }

    public static int v(@NonNull String str, @NonNull String str2, Throwable th, Object... objArr) {
        return println(2, str, str2, th, objArr);
    }

    public static int d(@NonNull String str, @NonNull String str2, Object... objArr) {
        return println(3, str, str2, null, objArr);
    }

    public static int d(@NonNull String str, @NonNull String str2, Throwable th, Object... objArr) {
        return println(3, str, str2, th, objArr);
    }

    public static int i(@NonNull String str, @NonNull String str2, Object... objArr) {
        return println(4, str, str2, null, objArr);
    }

    public static int i(@NonNull String str, @NonNull String str2, Throwable th, Object... objArr) {
        return println(4, str, str2, th, objArr);
    }

    public static int w(@NonNull String str, @NonNull String str2, Object... objArr) {
        return println(5, str, str2, null, objArr);
    }

    public static int w(@NonNull String str, @NonNull String str2, Throwable th, Object... objArr) {
        return println(5, str, str2, th, objArr);
    }

    public static int e(@NonNull String str, @NonNull String str2, Object... objArr) {
        return println(6, str, str2, null, objArr);
    }

    public static int e(@NonNull String str, @NonNull String str2, Throwable th, Object... objArr) {
        return println(6, str, str2, th, objArr);
    }
}
