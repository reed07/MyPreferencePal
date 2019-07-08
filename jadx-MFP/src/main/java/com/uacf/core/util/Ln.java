package com.uacf.core.util;

import android.util.Log;
import com.myfitnesspal.shared.constants.Constants;
import com.uacf.core.logging.BaseLogConfig;
import com.uacf.core.logging.LogConfig;
import com.uacf.core.logging.LogcatPrinter;
import com.uacf.core.logging.MultiPrinter;
import com.uacf.core.logging.Printer;
import com.uacf.core.logging.RollingLogHelper;

public final class Ln {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int REPORT = 8;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static LogConfig config = new BaseLogConfig();
    private static Printer printer = new LogcatPrinter();
    private static RollingLogHelper rollingLogHelper = null;

    public static String logLevelToString(int i) {
        switch (i) {
            case 2:
                return "VERBOSE";
            case 3:
                return "DEBUG";
            case 4:
                return "INFO";
            case 5:
                return "WARN";
            case 6:
                return "ERROR";
            case 7:
                return "ASSERT";
            default:
                return "GENERAL";
        }
    }

    private Ln() {
    }

    public static void clear() {
        printer.clear();
    }

    public static void setEnabled(Class<? extends Printer> cls, boolean z) {
        if (printer.getClass().isAssignableFrom(cls)) {
            printer.setEnabled(z);
            return;
        }
        Printer printer2 = printer;
        if (printer2 instanceof MultiPrinter) {
            ((MultiPrinter) printer2).setEnabled(cls, z);
        }
    }

    public static LogConfig getConfig() {
        return config;
    }

    public static void setConfig(LogConfig logConfig) {
        config = logConfig;
    }

    public static void forceDebugLogging(boolean z) {
        config.forceDebugLogging(z);
    }

    public static void setLoggingLevel(int i) {
        config.setLoggingLevel(i);
    }

    public static Printer getPrinter() {
        return printer;
    }

    public static void setPrinter(Printer printer2) {
        printer = printer2;
    }

    public static int v(Throwable th) {
        if (config.getLoggingLevel() <= 2) {
            return printer.println(2, Log.getStackTraceString(th));
        }
        return 0;
    }

    public static int v(Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 2) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        writeToLogFile(strings);
        return printer.println(2, strings);
    }

    public static int v(Throwable th, Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 2) {
            return 0;
        }
        String strings = Strings.toString(obj);
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        sb.append(strings);
        sb.append(10);
        sb.append(Log.getStackTraceString(th));
        String sb2 = sb.toString();
        writeToLogFile(sb2);
        return printer.println(2, sb2);
    }

    public static int d(Throwable th) {
        if (config.getLoggingLevel() <= 3) {
            return printer.println(3, Log.getStackTraceString(th));
        }
        return 0;
    }

    public static int d(Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 3) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return printer.println(3, strings);
    }

    public static int d(Throwable th, Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 3) {
            return 0;
        }
        String strings = Strings.toString(obj);
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        sb.append(strings);
        sb.append(10);
        sb.append(Log.getStackTraceString(th));
        return printer.println(3, sb.toString());
    }

    public static int i(Throwable th) {
        writeToLogFile(Log.getStackTraceString(th));
        if (config.getLoggingLevel() <= 4) {
            return printer.println(4, Log.getStackTraceString(th));
        }
        return 0;
    }

    public static int i(Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 4) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        writeToLogFile(strings);
        return printer.println(4, strings);
    }

    public static int i(Throwable th, Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 4) {
            return 0;
        }
        String strings = Strings.toString(obj);
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        sb.append(strings);
        sb.append(10);
        sb.append(Log.getStackTraceString(th));
        String sb2 = sb.toString();
        writeToLogFile(sb2);
        return printer.println(4, sb2);
    }

    public static int w(Throwable th) {
        if (config.getLoggingLevel() <= 5) {
            return printer.println(5, Log.getStackTraceString(th));
        }
        return 0;
    }

    public static int w(Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 5) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return printer.println(5, strings);
    }

    public static int w(Throwable th, Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 5) {
            return 0;
        }
        String strings = Strings.toString(obj);
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        sb.append(strings);
        sb.append(10);
        sb.append(Log.getStackTraceString(th));
        return printer.println(5, sb.toString());
    }

    public static int e(Throwable th) {
        return e(th, null, null);
    }

    public static int e(Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 6) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        writeToLogFile(strings);
        return printer.println(6, strings);
    }

    public static int e(Throwable th, Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 6) {
            return 0;
        }
        writeToLogFile(getLogMessage(th, obj, objArr));
        printer.printException(th, obj, objArr);
        return 0;
    }

    public static int r(Throwable th) {
        return r(th, null, null);
    }

    public static int r(Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 8) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return printer.println(8, strings);
    }

    public static int r(Throwable th, Object obj, Object... objArr) {
        if (config.getLoggingLevel() > 8) {
            return 0;
        }
        writeToLogFile(getLogMessage(th, obj, objArr));
        printer.printException(th, obj, objArr);
        return 0;
    }

    public static void dbytes(String str, String str2) {
        byte[] bytes = str2.getBytes();
        dbytes(str, bytes, 0, bytes.length);
    }

    public static void dbytes(String str, byte[] bArr, int i, int i2) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        d("%s: start = %s, limit = %s", str, Integer.valueOf(i), Integer.valueOf(i2));
        for (int i3 = i; i3 < i + i2; i3 += 8) {
            String str10 = "%s: %s %s %s %s %s %s %s %s";
            Object[] objArr = new Object[9];
            objArr[0] = str;
            int i4 = i3 + 0;
            if (i4 < bArr.length) {
                str2 = String.format("%02x", new Object[]{Byte.valueOf(bArr[i4])});
            } else {
                str2 = Constants.TWO_HYPHENS;
            }
            objArr[1] = str2;
            int i5 = i3 + 1;
            if (i5 < bArr.length) {
                str3 = String.format("%02x", new Object[]{Byte.valueOf(bArr[i5])});
            } else {
                str3 = Constants.TWO_HYPHENS;
            }
            objArr[2] = str3;
            int i6 = i3 + 2;
            if (i6 < bArr.length) {
                str4 = String.format("%02x", new Object[]{Byte.valueOf(bArr[i6])});
            } else {
                str4 = Constants.TWO_HYPHENS;
            }
            objArr[3] = str4;
            int i7 = i3 + 3;
            if (i7 < bArr.length) {
                str5 = String.format("%02x", new Object[]{Byte.valueOf(bArr[i7])});
            } else {
                str5 = Constants.TWO_HYPHENS;
            }
            objArr[4] = str5;
            int i8 = i3 + 4;
            if (i8 < bArr.length) {
                str6 = String.format("%02x", new Object[]{Byte.valueOf(bArr[i8])});
            } else {
                str6 = Constants.TWO_HYPHENS;
            }
            objArr[5] = str6;
            int i9 = i3 + 5;
            if (i9 < bArr.length) {
                str7 = String.format("%02x", new Object[]{Byte.valueOf(bArr[i9])});
            } else {
                str7 = Constants.TWO_HYPHENS;
            }
            objArr[6] = str7;
            int i10 = i3 + 6;
            if (i10 < bArr.length) {
                str8 = String.format("%02x", new Object[]{Byte.valueOf(bArr[i10])});
            } else {
                str8 = Constants.TWO_HYPHENS;
            }
            objArr[7] = str8;
            int i11 = i3 + 7;
            if (i11 < bArr.length) {
                str9 = String.format("%02x", new Object[]{Byte.valueOf(bArr[i11])});
            } else {
                str9 = Constants.TWO_HYPHENS;
            }
            objArr[8] = str9;
            d(str10, objArr);
        }
    }

    public static boolean isDebugEnabled() {
        return config.getLoggingLevel() <= 3;
    }

    public static boolean isVerboseEnabled() {
        return config.getLoggingLevel() <= 2;
    }

    public static RollingLogHelper getRollingLogHelper() {
        return rollingLogHelper;
    }

    public static void setRollingLogHelper(RollingLogHelper rollingLogHelper2) {
        rollingLogHelper = rollingLogHelper2;
    }

    private static void writeToLogFile(String str) {
        RollingLogHelper rollingLogHelper2 = rollingLogHelper;
        if (rollingLogHelper2 != null) {
            rollingLogHelper2.writeToLog(str);
        }
    }

    private static String getLogMessage(Throwable th, Object obj, Object[] objArr) {
        String strings = Strings.toString(obj);
        String stackTraceString = Log.getStackTraceString(th);
        if (!Strings.notEmpty(strings)) {
            return stackTraceString;
        }
        StringBuilder sb = new StringBuilder();
        if (objArr != null && objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        sb.append(strings);
        sb.append(10);
        sb.append(stackTraceString);
        return sb.toString();
    }
}
