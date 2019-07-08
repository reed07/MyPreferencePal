package com.twitter.sdk.android.core;

public interface Logger {
    void d(String str, String str2);

    void d(String str, String str2, Throwable th);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void log(int i, String str, String str2);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);
}
