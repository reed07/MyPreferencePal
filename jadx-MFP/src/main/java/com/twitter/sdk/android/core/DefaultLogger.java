package com.twitter.sdk.android.core;

import android.util.Log;

public class DefaultLogger implements Logger {
    private int logLevel = 4;

    public boolean isLoggable(String str, int i) {
        return this.logLevel <= i;
    }

    public void d(String str, String str2, Throwable th) {
        if (isLoggable(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    public void w(String str, String str2, Throwable th) {
        if (isLoggable(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    public void e(String str, String str2, Throwable th) {
        if (isLoggable(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    public void d(String str, String str2) {
        d(str, str2, null);
    }

    public void w(String str, String str2) {
        w(str, str2, null);
    }

    public void e(String str, String str2) {
        e(str, str2, null);
    }

    public void log(int i, String str, String str2) {
        log(i, str, str2, false);
    }

    public void log(int i, String str, String str2, boolean z) {
        if (z || isLoggable(str, i)) {
            Log.println(i, str, str2);
        }
    }
}
