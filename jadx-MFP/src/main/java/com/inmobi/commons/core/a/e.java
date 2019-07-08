package com.inmobi.commons.core.a;

import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: InMobiCrashHandler */
public class e implements UncaughtExceptionHandler {
    private static final String b = "e";
    private UncaughtExceptionHandler a;

    e(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.a = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (stackTrace[i].getClassName().contains("com.inmobi.")) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            try {
                a.a().a(new d(thread, th));
            } catch (Exception unused) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(th.getMessage());
                sb.append(")");
            }
        }
        this.a.uncaughtException(thread, th);
    }
}
