package com.facebook.ads.internal.w.b;

import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.Locale;

public class v {
    @VisibleForTesting
    public static a a;

    public interface a {
        long a();
    }

    public static long a() {
        a aVar = a;
        return aVar != null ? aVar.a() : System.currentTimeMillis();
    }

    public static String a(double d) {
        try {
            return String.format(Locale.US, "%.3f", new Object[]{Double.valueOf(d)});
        } catch (Exception e) {
            Log.e(v.class.getSimpleName(), "Can't format time.", e);
            return "1.234";
        }
    }

    public static String a(long j) {
        return Long.toString(j);
    }

    @Deprecated
    public static String b(long j) {
        return a(((double) j) / 1000.0d);
    }
}
