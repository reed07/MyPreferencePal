package com.google.android.exoplayer2.util;

import android.os.Handler.Callback;
import android.os.Looper;
import android.support.annotation.Nullable;

public interface Clock {
    public static final Clock DEFAULT = new SystemClock();

    HandlerWrapper createHandler(Looper looper, @Nullable Callback callback);

    long elapsedRealtime();

    void sleep(long j);

    long uptimeMillis();
}
