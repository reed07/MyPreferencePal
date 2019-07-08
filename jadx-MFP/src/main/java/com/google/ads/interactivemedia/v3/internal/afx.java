package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: IMASDK */
public class afx {
    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    private afx() {
    }

    /* synthetic */ afx(byte b) {
        this();
    }

    public static void a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static int a(int i, int i2) {
        return a(i, i2, "index");
    }

    public static int a(int i, int i2, String str) {
        if (i >= 0 && i < i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(c(i, i2, str));
    }

    private static String c(int i, int i2, String str) {
        if (i < 0) {
            return a("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return a("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            StringBuilder sb = new StringBuilder(26);
            sb.append("negative size: ");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static int b(int i, int i2) {
        return b(i, i2, "index");
    }

    public static int b(int i, int i2, String str) {
        if (i >= 0 && i <= i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(d(i, i2, str));
    }

    private static String d(int i, int i2, String str) {
        if (i < 0) {
            return a("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return a("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            StringBuilder sb = new StringBuilder(26);
            sb.append("negative size: ");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static void a(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            throw new IndexOutOfBoundsException(b(i, i2, i3));
        }
    }

    private static String b(int i, int i2, int i3) {
        if (i < 0 || i > i3) {
            return d(i, i3, "start index");
        }
        if (i2 < 0 || i2 > i3) {
            return d(i2, i3, "end index");
        }
        return a("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
    }

    public static boolean a(String str) {
        return afy.a(str);
    }

    public static String a(String str, Object... objArr) {
        String valueOf = String.valueOf(str);
        int i = 0;
        for (int i2 = 0; i2 < objArr.length; i2++) {
            objArr[i2] = b(objArr[i2]);
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int i3 = 0;
        while (i < objArr.length) {
            int indexOf = valueOf.indexOf("%s", i3);
            if (indexOf == -1) {
                break;
            }
            sb.append(valueOf, i3, indexOf);
            int i4 = i + 1;
            sb.append(objArr[i]);
            int i5 = i4;
            i3 = indexOf + 2;
            i = i5;
        }
        sb.append(valueOf, i3, valueOf.length());
        if (i < objArr.length) {
            sb.append(" [");
            int i6 = i + 1;
            sb.append(objArr[i]);
            while (i6 < objArr.length) {
                sb.append(", ");
                int i7 = i6 + 1;
                sb.append(objArr[i6]);
                i6 = i7;
            }
            sb.append(']');
        }
        return sb.toString();
    }

    private static String b(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception e) {
            String name = obj.getClass().getName();
            String hexString = Integer.toHexString(System.identityHashCode(obj));
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
            sb.append(name);
            sb.append('@');
            sb.append(hexString);
            String sb2 = sb.toString();
            Logger logger = Logger.getLogger("com.google.common.base.Strings");
            Level level = Level.WARNING;
            String str = "com.google.common.base.Strings";
            String str2 = "lenientToString";
            String str3 = "Exception during lenientFormat for ";
            String valueOf = String.valueOf(sb2);
            logger.logp(level, str, str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), e);
            String name2 = e.getClass().getName();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 9 + String.valueOf(name2).length());
            sb3.append("<");
            sb3.append(sb2);
            sb3.append(" threw ");
            sb3.append(name2);
            sb3.append(">");
            return sb3.toString();
        }
    }
}
