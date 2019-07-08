package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: IMASDK */
final class bc extends Handler {
    private final /* synthetic */ ay a;

    bc(ay ayVar, Looper looper) {
        this.a = ayVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.a.a(message);
    }
}
