package com.myfitnesspal.shared.util;

public final class LinearRandomNumberGenerator {
    private static long rng_seed;
    private static long startTime;
    public static long totalTime;

    public static void seed_rng(int i) {
        rng_seed = (long) (i ^ 1674262698);
    }

    public static long rng_next() {
        startTime = System.currentTimeMillis();
        rng_seed = (rng_seed * 16807) % 2147483647L;
        totalTime += System.currentTimeMillis() - startTime;
        return rng_seed;
    }
}
