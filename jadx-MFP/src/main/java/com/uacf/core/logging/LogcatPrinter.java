package com.uacf.core.logging;

import android.util.Log;

public class LogcatPrinter extends PrinterBase {
    protected static String processMessage(String str) {
        return str;
    }

    public int println(int i, String str) {
        if (!this.enabled) {
            return 0;
        }
        return Log.println(i, getScope(), processMessage(str));
    }

    /* access modifiers changed from: protected */
    public String getScope() {
        return Thread.currentThread().getStackTrace()[6].getFileName();
    }
}
