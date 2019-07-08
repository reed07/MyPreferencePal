package org.apache.sanselan.util;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class Debug {
    private static long counter = 0;
    public static String newline = "\r\n";
    private static final SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SSS");

    public static String getDebug(String str) {
        return str;
    }

    public static void debug(String str) {
        System.out.println(str);
    }

    public static void debug() {
        newline();
    }

    public static void newline() {
        System.out.print(newline);
    }

    public static String getDebug(String str, int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (iArr == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" (");
            sb.append(null);
            sb.append(")");
            sb.append(newline);
            stringBuffer.append(sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(" (");
            sb2.append(iArr.length);
            sb2.append(")");
            sb2.append(newline);
            stringBuffer.append(sb2.toString());
            for (int append : iArr) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("\t");
                sb3.append(append);
                sb3.append(newline);
                stringBuffer.append(sb3.toString());
            }
            stringBuffer.append(newline);
        }
        return stringBuffer.toString();
    }

    public static String getDebug(String str, byte[] bArr) {
        return getDebug(str, bArr, Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    public static String getDebug(String str, byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bArr == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" (");
            sb.append(null);
            sb.append(")");
            sb.append(newline);
            stringBuffer.append(sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(" (");
            sb2.append(bArr.length);
            sb2.append(")");
            sb2.append(newline);
            stringBuffer.append(sb2.toString());
            int i2 = 0;
            while (i2 < i && i2 < bArr.length) {
                byte b = bArr[i2] & 255;
                char c = (b == 0 || b == 10 || b == 11 || b == 13) ? ' ' : (char) b;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("\t");
                sb3.append(i2);
                sb3.append(": ");
                sb3.append(b);
                sb3.append(" (");
                sb3.append(c);
                sb3.append(", 0x");
                sb3.append(Integer.toHexString(b));
                sb3.append(")");
                sb3.append(newline);
                stringBuffer.append(sb3.toString());
                i2++;
            }
            if (bArr.length > i) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("\t...");
                sb4.append(newline);
                stringBuffer.append(sb4.toString());
            }
            stringBuffer.append(newline);
        }
        return stringBuffer.toString();
    }

    public static String getDebug(String str, char[] cArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (cArr == null) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(" (");
            sb2.append(null);
            sb2.append(")");
            sb.append(getDebug(sb2.toString()));
            sb.append(newline);
            stringBuffer.append(sb.toString());
        } else {
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append(" (");
            sb4.append(cArr.length);
            sb4.append(")");
            sb3.append(getDebug(sb4.toString()));
            sb3.append(newline);
            stringBuffer.append(sb3.toString());
            for (int i = 0; i < cArr.length; i++) {
                StringBuilder sb5 = new StringBuilder();
                StringBuilder sb6 = new StringBuilder();
                sb6.append("\t");
                sb6.append(cArr[i]);
                sb6.append(" (");
                sb6.append(cArr[i] & 255);
                sb5.append(getDebug(sb6.toString()));
                sb5.append(")");
                sb5.append(newline);
                stringBuffer.append(sb5.toString());
            }
            stringBuffer.append(newline);
        }
        return stringBuffer.toString();
    }

    public static void debug(String str, Map map) {
        debug(getDebug(str, map));
    }

    public static String getDebug(String str, Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        if (map == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" map: ");
            sb.append(null);
            return getDebug(sb.toString());
        }
        ArrayList arrayList = new ArrayList(map.keySet());
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(" map: ");
        sb3.append(arrayList.size());
        sb2.append(getDebug(sb3.toString()));
        sb2.append(newline);
        stringBuffer.append(sb2.toString());
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj = arrayList.get(i);
            Object obj2 = map.get(obj);
            StringBuilder sb4 = new StringBuilder();
            StringBuilder sb5 = new StringBuilder();
            sb5.append("\t");
            sb5.append(i);
            sb5.append(": '");
            sb5.append(obj);
            sb5.append("' -> '");
            sb5.append(obj2);
            sb5.append("'");
            sb4.append(getDebug(sb5.toString()));
            sb4.append(newline);
            stringBuffer.append(sb4.toString());
        }
        stringBuffer.append(newline);
        return stringBuffer.toString();
    }

    public static void debug(String str, Object obj) {
        if (obj == null) {
            debug(str, "null");
        } else if (obj instanceof char[]) {
            debug(str, (char[]) obj);
        } else if (obj instanceof byte[]) {
            debug(str, (byte[]) obj);
        } else if (obj instanceof int[]) {
            debug(str, (int[]) obj);
        } else if (obj instanceof String) {
            debug(str, (String) obj);
        } else if (obj instanceof List) {
            debug(str, (List) obj);
        } else if (obj instanceof Map) {
            debug(str, (Map) obj);
        } else if (obj instanceof File) {
            debug(str, (File) obj);
        } else if (obj instanceof Date) {
            debug(str, (Date) obj);
        } else if (obj instanceof Calendar) {
            debug(str, (Calendar) obj);
        } else {
            debug(str, obj.toString());
        }
    }

    public static String getType(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof Object[]) {
            StringBuilder sb = new StringBuilder();
            sb.append("[Object[]: ");
            sb.append(((Object[]) obj).length);
            sb.append("]");
            return sb.toString();
        } else if (obj instanceof char[]) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[char[]: ");
            sb2.append(((char[]) obj).length);
            sb2.append("]");
            return sb2.toString();
        } else if (obj instanceof byte[]) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("[byte[]: ");
            sb3.append(((byte[]) obj).length);
            sb3.append("]");
            return sb3.toString();
        } else if (obj instanceof short[]) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("[short[]: ");
            sb4.append(((short[]) obj).length);
            sb4.append("]");
            return sb4.toString();
        } else if (obj instanceof int[]) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("[int[]: ");
            sb5.append(((int[]) obj).length);
            sb5.append("]");
            return sb5.toString();
        } else if (obj instanceof long[]) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("[long[]: ");
            sb6.append(((long[]) obj).length);
            sb6.append("]");
            return sb6.toString();
        } else if (obj instanceof float[]) {
            StringBuilder sb7 = new StringBuilder();
            sb7.append("[float[]: ");
            sb7.append(((float[]) obj).length);
            sb7.append("]");
            return sb7.toString();
        } else if (obj instanceof double[]) {
            StringBuilder sb8 = new StringBuilder();
            sb8.append("[double[]: ");
            sb8.append(((double[]) obj).length);
            sb8.append("]");
            return sb8.toString();
        } else if (!(obj instanceof boolean[])) {
            return obj.getClass().getName();
        } else {
            StringBuilder sb9 = new StringBuilder();
            sb9.append("[boolean[]: ");
            sb9.append(((boolean[]) obj).length);
            sb9.append("]");
            return sb9.toString();
        }
    }

    public static void debug(String str, byte[] bArr) {
        debug(getDebug(str, bArr));
    }

    public static void debug(String str, char[] cArr) {
        debug(getDebug(str, cArr));
    }

    public static void debug(String str, Calendar calendar) {
        String str2;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        if (calendar == null) {
            str2 = "null";
        } else {
            str2 = simpleDateFormat.format(calendar.getTime());
        }
        debug(str, str2);
    }

    public static void debug(String str, Date date) {
        String str2;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        if (date == null) {
            str2 = "null";
        } else {
            str2 = simpleDateFormat.format(date);
        }
        debug(str, str2);
    }

    public static void debug(String str, File file) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        sb.append(file == null ? "null" : file.getPath());
        debug(sb.toString());
    }

    public static void debug(String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        sb.append(i);
        debug(sb.toString());
    }

    public static void debug(String str, int[] iArr) {
        debug(getDebug(str, iArr));
    }

    public static void debug(String str, List list) {
        StringBuilder sb = new StringBuilder();
        sb.append(" [");
        long j = counter;
        counter = 1 + j;
        sb.append(j);
        sb.append("]");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(" (");
        sb3.append(list.size());
        sb3.append(")");
        sb3.append(sb2);
        debug(sb3.toString());
        for (int i = 0; i < list.size(); i++) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("\t");
            sb4.append(list.get(i).toString());
            sb4.append(sb2);
            debug(sb4.toString());
        }
        debug();
    }

    public static void debug(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        debug(sb.toString());
    }

    public static void debug(Throwable th) {
        debug(getDebug(th));
    }

    public static String getDebug(Throwable th) {
        return getDebug(th, -1);
    }

    public static String getDebug(Throwable th, int i) {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        String lowerCase = timestamp.format(new Date()).toLowerCase();
        stringBuffer.append(newline);
        StringBuilder sb = new StringBuilder();
        sb.append("Throwable: ");
        if (th == null) {
            str = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("(");
            sb2.append(th.getClass().getName());
            sb2.append(")");
            str = sb2.toString();
        }
        sb.append(str);
        sb.append(":");
        sb.append(lowerCase);
        sb.append(newline);
        stringBuffer.append(sb.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Throwable: ");
        sb3.append(th == null ? "null" : th.getLocalizedMessage());
        sb3.append(newline);
        stringBuffer.append(sb3.toString());
        stringBuffer.append(newline);
        stringBuffer.append(getStackTrace(th, i));
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Caught here:");
        sb4.append(newline);
        stringBuffer.append(sb4.toString());
        stringBuffer.append(getStackTrace(new Exception(), i, 1));
        stringBuffer.append(newline);
        return stringBuffer.toString();
    }

    public static String getStackTrace(Throwable th, int i) {
        return getStackTrace(th, i, 0);
    }

    public static String getStackTrace(Throwable th, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (th != null) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                while (i2 < stackTrace.length && (i < 0 || i2 < i)) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    StringBuilder sb = new StringBuilder();
                    sb.append("\tat ");
                    sb.append(stackTraceElement.getClassName());
                    sb.append(".");
                    sb.append(stackTraceElement.getMethodName());
                    sb.append("(");
                    sb.append(stackTraceElement.getFileName());
                    sb.append(":");
                    sb.append(stackTraceElement.getLineNumber());
                    sb.append(")");
                    sb.append(newline);
                    stringBuffer.append(sb.toString());
                    i2++;
                }
                if (i >= 0 && stackTrace.length > i) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\t...");
                    sb2.append(newline);
                    stringBuffer.append(sb2.toString());
                }
            }
            stringBuffer.append(newline);
        }
        return stringBuffer.toString();
    }
}
