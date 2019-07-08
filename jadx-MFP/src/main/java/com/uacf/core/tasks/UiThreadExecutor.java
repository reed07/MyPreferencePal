package com.uacf.core.tasks;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class UiThreadExecutor implements Executor {
    private final Handler handler = new Handler(Looper.getMainLooper());

    UiThreadExecutor() {
    }

    public void execute(@NonNull Runnable runnable) {
        this.handler.post(runnable);
    }
}
