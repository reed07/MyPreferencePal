package com.amazon.device.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class DtbThreadService {
    private static long SCHEDULE_INTERVAL = 10;
    private static DtbThreadService threadServiceInstance = new DtbThreadService();
    /* access modifiers changed from: private */
    public final ExecutorService executor = Executors.newFixedThreadPool(1);
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private DtbThreadService() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                DtbLog.debug("App is shutting down, terminating the fixed thread pool");
                DtbThreadService.this.executor.shutdown();
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                DtbLog.debug("App is shutting down, terminating the thread pool");
                DtbThreadService.this.scheduler.shutdown();
            }
        });
    }

    public static DtbThreadService getInstance() {
        return threadServiceInstance;
    }

    public void execute(Runnable runnable) {
        this.executor.execute(runnable);
    }

    public void schedule(Runnable runnable) {
        this.scheduler.schedule(runnable, SCHEDULE_INTERVAL, TimeUnit.SECONDS);
    }

    public static void executeOnMainThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
