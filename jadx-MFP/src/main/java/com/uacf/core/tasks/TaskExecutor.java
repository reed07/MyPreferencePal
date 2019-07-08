package com.uacf.core.tasks;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

class TaskExecutor {
    private static ThreadPoolExecutor backgroundThreadPoolExecutor;
    private static CurrentThreadExecutor currentThreadPoolExecutor;
    private static ScheduledExecutorService scheduledThreadPoolExecutor;
    private static UiThreadExecutor uiThreadPoolExecutor;

    TaskExecutor() {
    }

    static Executor getExecutorForType(ExecutorType executorType) {
        switch (executorType) {
            case BACKGROUND_THREAD:
                return getBackgroundThreadExecutor();
            case UI_THREAD:
                return getUiThreadExecutor();
            default:
                return getCurrentThreadExecutor();
        }
    }

    private static ExecutorService getBackgroundThreadExecutor() {
        if (backgroundThreadPoolExecutor == null) {
            synchronized (TaskExecutor.class) {
                backgroundThreadPoolExecutor = new BackgroundThreadExecutor();
            }
        }
        return backgroundThreadPoolExecutor;
    }

    private static Executor getUiThreadExecutor() {
        if (uiThreadPoolExecutor == null) {
            synchronized (TaskExecutor.class) {
                uiThreadPoolExecutor = new UiThreadExecutor();
            }
        }
        return uiThreadPoolExecutor;
    }

    private static Executor getCurrentThreadExecutor() {
        if (currentThreadPoolExecutor == null) {
            synchronized (TaskExecutor.class) {
                currentThreadPoolExecutor = new CurrentThreadExecutor();
            }
        }
        return currentThreadPoolExecutor;
    }

    static ScheduledExecutorService getScheduledThreadExecutor() {
        if (scheduledThreadPoolExecutor == null) {
            synchronized (TaskExecutor.class) {
                scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(1);
            }
        }
        return scheduledThreadPoolExecutor;
    }
}
