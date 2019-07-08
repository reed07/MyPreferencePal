package com.myfitnesspal.shared.util;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueId {
    private static final AtomicInteger NEXT = new AtomicInteger(4000);

    public static int next() {
        return NEXT.incrementAndGet();
    }
}
