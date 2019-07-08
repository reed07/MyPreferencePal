package com.myfitnesspal.feature.externalsync.service;

public final class ExternalSyncCooldown {
    private static final long COOLDOWN_DURATION_MILLIS = 900000;
    private static long cooldownTime;

    public static void set() {
        cooldownTime = System.currentTimeMillis();
    }

    public static void clear() {
        cooldownTime = 0;
    }

    public static boolean active() {
        return System.currentTimeMillis() - cooldownTime < COOLDOWN_DURATION_MILLIS;
    }
}
