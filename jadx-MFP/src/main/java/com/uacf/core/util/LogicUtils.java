package com.uacf.core.util;

public final class LogicUtils {
    public static final boolean checkFlags(long j, long j2) {
        return (j & j2) == j2;
    }

    public static final long clearFlags(long j, long j2) {
        return j & (~j2);
    }

    public static final long setFlags(long j, long j2) {
        return j | j2;
    }
}
