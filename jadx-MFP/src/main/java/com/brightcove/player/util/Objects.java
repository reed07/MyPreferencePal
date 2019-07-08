package com.brightcove.player.util;

public class Objects {
    public static <T> T firstNonNull(T t, T t2) {
        return t != null ? t : t2;
    }

    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static <T> T firstNonNull(T t, T t2, T t3) {
        return t != null ? t : firstNonNull(t2, t3);
    }
}
