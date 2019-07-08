package com.myfitnesspal.framework.mvvm;

import java.util.concurrent.atomic.AtomicInteger;

public class ViewModelPropertyId {
    private static final AtomicInteger nextId = new AtomicInteger(Integer.MIN_VALUE);

    public static int next() {
        return nextId.incrementAndGet();
    }
}
