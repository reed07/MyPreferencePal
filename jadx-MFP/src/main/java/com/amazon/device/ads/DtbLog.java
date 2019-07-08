package com.amazon.device.ads;

import android.util.Log;

class DtbLog {
    private static boolean androidLogAvailable;
    private static boolean isCallerInfoEnabled = false;
    private static DTBLogLevel logLevel = DTBLogLevel.Warn;

    DtbLog() {
    }

    static {
        try {
            Log.isLoggable("1234", 7);
            androidLogAvailable = true;
        } catch (Throwable unused) {
            androidLogAvailable = false;
        }
    }

    private static String getTag() {
        return isCallerInfoEnabled ? getCallerInfo() : "Amazon DTB Ads API";
    }

    public static void info(String str) {
        if (logLevel.intValue() <= DTBLogLevel.Info.intValue() && androidLogAvailable) {
            Log.i(getTag(), str);
        }
    }

    public static void info(String str, String str2) {
        if (logLevel.intValue() <= DTBLogLevel.Info.intValue() && androidLogAvailable) {
            Log.i(str, str2);
        }
    }

    public static void warn(String str) {
        if (logLevel.intValue() <= DTBLogLevel.Warn.intValue() && androidLogAvailable) {
            Log.w(getTag(), str);
        }
    }

    public static void warn(String str, String str2) {
        if (logLevel.intValue() <= DTBLogLevel.Warn.intValue() && androidLogAvailable) {
            Log.w(str, str2);
        }
    }

    public static void debug(String str) {
        if (logLevel.intValue() <= DTBLogLevel.Debug.intValue() && androidLogAvailable) {
            Log.d(getTag(), str);
        }
    }

    public static void debug(String str, String str2) {
        if (logLevel.intValue() <= DTBLogLevel.Debug.intValue() && androidLogAvailable) {
            Log.d(str, str2);
        }
    }

    public static void debugError(String str) {
        if (logLevel.intValue() <= DTBLogLevel.Debug.intValue() && androidLogAvailable) {
            String tag = getTag();
            StringBuilder sb = new StringBuilder();
            sb.append("DTBERROR::");
            sb.append(str);
            Log.d(tag, sb.toString());
        }
    }

    public static void error(String str) {
        if (logLevel.intValue() <= DTBLogLevel.Error.intValue() && androidLogAvailable) {
            Log.e(getTag(), str);
        }
    }

    public static void error(String str, String str2) {
        if (logLevel.intValue() <= DTBLogLevel.Error.intValue() && androidLogAvailable) {
            Log.e(str, str2);
        }
    }

    public static void fatal(String str, String str2, Exception exc) {
        if (logLevel.intValue() <= DTBLogLevel.Fatal.intValue() && androidLogAvailable) {
            Log.e(str, str2, exc);
        }
    }

    private static String getCallerInfo() {
        String str;
        if (!isCallerInfoEnabled) {
            return "Amazon DTB Ads API";
        }
        StackTraceElement stackTraceElement = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null) {
            int i = 0;
            while (true) {
                if (i >= stackTrace.length) {
                    break;
                }
                String className = stackTrace[i].getClassName();
                if (className.startsWith("dalvik") || className.startsWith("java")) {
                    i++;
                } else {
                    int i2 = i + 2;
                    if (i2 >= stackTrace.length) {
                        i2 = i;
                    }
                    stackTraceElement = stackTrace[i2];
                }
            }
        }
        if (stackTraceElement != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(stackTraceElement.getClassName());
            sb.append(":");
            sb.append(stackTraceElement.getMethodName());
            sb.append(":");
            sb.append(stackTraceElement.getLineNumber());
            str = sb.toString();
        } else {
            str = "Amazon DTB Ads API";
        }
        return str;
    }
}
