package com.uacf.core.logging;

import android.util.Log;

public class BaseLogConfig implements LogConfig {
    private boolean forceDebugLogging;
    protected int logLevel = 2;
    protected String scope = "";

    public BaseLogConfig() {
    }

    public BaseLogConfig(boolean z, String str) {
        int i = 2;
        if (!z) {
            i = 4;
        }
        try {
            this.logLevel = i;
            this.scope = str;
            StringBuilder sb = new StringBuilder();
            sb.append("BaseLogConfig: level = ");
            sb.append(this.logLevel);
            sb.append(", scope = ");
            sb.append(str);
            Log.e("LOGGING", sb.toString());
        } catch (Exception e) {
            Log.e("LOGGING", "Error configuring logger for package", e);
        }
    }

    public void setLoggingLevel(int i) {
        this.logLevel = i;
    }

    public int getLoggingLevel() {
        if (this.forceDebugLogging) {
            return 3;
        }
        return this.logLevel;
    }

    public void forceDebugLogging(boolean z) {
        this.forceDebugLogging = z;
    }
}
