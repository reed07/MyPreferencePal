package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.support.annotation.Nullable;

final class SystemClock implements Clock {
    SystemClock() {
    }

    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    public long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }

    public void sleep(long j) {
        android.os.SystemClock.sleep(j);
    }

    public HandlerWrapper createHandler(Looper looper, @Nullable Callback callback) {
        return new SystemHandlerWrapper(new Handler(looper, callback));
    }
}
