package com.uacf.core.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class CurrentThreadExecutor implements Executor {
    CurrentThreadExecutor() {
    }

    public void execute(@NonNull Runnable runnable) {
        runnable.run();
    }
}
