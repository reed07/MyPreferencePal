package com.myfitnesspal.framework.mixin;

import android.os.Looper;

public final class Preconditions {
    public static void calledFromMainThread() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalThreadStateException("must be called by the main thread");
        }
    }
}
