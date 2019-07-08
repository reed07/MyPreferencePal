package com.google.ads.interactivemedia.v3.internal;

import java.util.HashSet;

/* compiled from: IMASDK */
public final class br {
    private static final HashSet<String> a = new HashSet<>();
    private static String b = "goog.exo.core";

    public static synchronized String a() {
        String str;
        synchronized (br.class) {
            str = b;
        }
        return str;
    }

    public static synchronized void a(String str) {
        synchronized (br.class) {
            if (a.add(str)) {
                String str2 = b;
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 2 + String.valueOf(str).length());
                sb.append(str2);
                sb.append(", ");
                sb.append(str);
                b = sb.toString();
            }
        }
    }
}
