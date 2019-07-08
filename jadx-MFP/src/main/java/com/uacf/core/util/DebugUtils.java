package com.uacf.core.util;

import android.os.Debug;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class DebugUtils {
    public static String stackTraceToString(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static void waitForDebugger() {
        if (!Debug.isDebuggerConnected() && !Debug.waitingForDebugger()) {
            Ln.d("ATTACH DEBUGGER NOW", new Object[0]);
            Debug.waitForDebugger();
        }
    }
}
