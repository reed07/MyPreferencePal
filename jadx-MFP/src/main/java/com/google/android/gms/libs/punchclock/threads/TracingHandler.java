package com.google.android.gms.libs.punchclock.threads;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.CallSuper;

public class TracingHandler extends Handler {
    private static volatile zza zzbu;

    public interface zza {
    }

    public TracingHandler() {
    }

    public TracingHandler(Callback callback) {
        super(callback);
    }

    public TracingHandler(Looper looper) {
        super(looper);
    }

    public TracingHandler(Looper looper, Callback callback) {
        super(looper, callback);
    }

    public boolean sendMessageAtTime(Message message, long j) {
        return super.sendMessageAtTime(message, j);
    }

    public boolean sendMessageAtFrontOfQueueTraced(Message message) {
        return sendMessageAtFrontOfQueue(message);
    }

    public boolean postAtFrontOfQueueTraced(Runnable runnable) {
        return postAtFrontOfQueue(runnable);
    }

    public final void dispatchMessage(Message message) {
        dispatchMessageTraced(message);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void dispatchMessageTraced(Message message) {
        super.dispatchMessage(message);
    }
}
