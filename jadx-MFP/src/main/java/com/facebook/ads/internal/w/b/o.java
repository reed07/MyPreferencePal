package com.facebook.ads.internal.w.b;

import java.util.UUID;

public class o {
    private static final String a = "o";
    private static volatile boolean b = false;
    private static double c;
    private static String d;

    public static void a() {
        if (!b) {
            synchronized (a) {
                if (!b) {
                    b = true;
                    c = ((double) System.currentTimeMillis()) / 1000.0d;
                    d = UUID.randomUUID().toString();
                }
            }
        }
    }

    public static double b() {
        return c;
    }

    public static String c() {
        return d;
    }
}
