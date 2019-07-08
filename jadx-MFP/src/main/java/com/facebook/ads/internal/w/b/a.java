package com.facebook.ads.internal.w.b;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class a {
    private static boolean a = false;
    private static boolean b = false;

    @Nullable
    public static synchronized String a(String str) {
        synchronized (a.class) {
            if (!a()) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("fb.e2e.");
            sb.append(str);
            String property = System.getProperty(sb.toString());
            return property;
        }
    }

    public static synchronized boolean a() {
        boolean z;
        synchronized (a.class) {
            if (!b) {
                a = "true".equals(System.getProperty("fb.running_e2e"));
                b = true;
            }
            z = a;
        }
        return z;
    }

    public static synchronized boolean b(String str) {
        boolean z;
        synchronized (a.class) {
            z = !TextUtils.isEmpty(a(str));
        }
        return z;
    }
}
