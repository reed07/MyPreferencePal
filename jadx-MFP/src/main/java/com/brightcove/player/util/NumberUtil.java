package com.brightcove.player.util;

public class NumberUtil {
    public static int safeLongToInt(long j) {
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(j);
        sb.append("Cannot cast long to int without change its value.");
        throw new IllegalArgumentException(sb.toString());
    }
}
