package com.uacf.core.tasks;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class BackgroundThreadExecutor extends ThreadPoolExecutor {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int MAX_POOL_SIZE;

    static {
        int i = CPU_COUNT;
        CORE_POOL_SIZE = i + 1;
        MAX_POOL_SIZE = (i * 2) + 1;
    }

    BackgroundThreadExecutor() {
        super(CORE_POOL_SIZE, MAX_POOL_SIZE, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
        allowCoreThreadTimeOut(true);
    }
}
