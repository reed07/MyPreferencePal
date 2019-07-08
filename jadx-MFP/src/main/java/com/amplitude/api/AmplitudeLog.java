package com.amplitude.api;

import android.util.Log;

public class AmplitudeLog {
    protected static AmplitudeLog instance = new AmplitudeLog();
    private volatile boolean enableLogging = true;
    private volatile int logLevel = 4;

    public static AmplitudeLog getLogger() {
        return instance;
    }

    private AmplitudeLog() {
    }

    /* access modifiers changed from: 0000 */
    public int d(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 3) {
            return 0;
        }
        return Log.d(str, str2);
    }

    /* access modifiers changed from: 0000 */
    public int e(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 6) {
            return 0;
        }
        return Log.e(str, str2);
    }

    /* access modifiers changed from: 0000 */
    public int e(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 6) {
            return 0;
        }
        return Log.e(str, str2, th);
    }

    /* access modifiers changed from: 0000 */
    public int i(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 4) {
            return 0;
        }
        return Log.i(str, str2);
    }

    /* access modifiers changed from: 0000 */
    public int w(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return Log.w(str, str2);
    }

    /* access modifiers changed from: 0000 */
    public int w(String str, Throwable th) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return Log.w(str, th);
    }
}
