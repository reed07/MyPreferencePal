package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.SystemClock;

/* compiled from: IMASDK */
final class vc implements ua {
    vc() {
    }

    public final long a() {
        return SystemClock.elapsedRealtime();
    }

    public final long b() {
        return SystemClock.uptimeMillis();
    }

    public final uj a(Looper looper, Callback callback) {
        return new uj(new Handler(looper, callback));
    }
}
