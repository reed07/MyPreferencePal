package com.uacf.core.logging;

import android.util.Log;
import com.uacf.core.util.Strings;

public abstract class PrinterBase implements Printer {
    protected boolean enabled = true;

    public void clear() {
    }

    public int println(int i, String str) {
        return 0;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public void printException(Throwable th) {
        if (this.enabled) {
            printException(th, null, null);
        }
    }

    public void printException(Throwable th, Object obj, Object... objArr) {
        if (this.enabled) {
            String strings = Strings.toString(obj);
            String stackTraceString = Log.getStackTraceString(th);
            if (Strings.notEmpty(strings)) {
                StringBuilder sb = new StringBuilder();
                if (objArr != null && objArr.length > 0) {
                    strings = String.format(strings, objArr);
                }
                sb.append(strings);
                sb.append(10);
                sb.append(stackTraceString);
                stackTraceString = sb.toString();
            }
            println(6, stackTraceString);
        }
    }
}
