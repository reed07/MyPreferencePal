package com.uacf.core.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public abstract class Debouncer<T> {
    private static final int DEBOUNCED_MESSAGE = 1000;
    private int delayMillis;
    private Handler handler;

    private class InternalHandler extends Handler {
        public InternalHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 1000) {
                Debouncer.this.onDebounced(message.obj);
            } else {
                super.handleMessage(message);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCalled(T t) {
    }

    /* access modifiers changed from: protected */
    public abstract void onDebounced(T t);

    public Debouncer(int i) {
        this(i, null);
    }

    public Debouncer(int i, Looper looper) {
        this.delayMillis = i;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.handler = new InternalHandler(looper);
    }

    public final void call(T t) {
        this.handler.removeMessages(1000);
        Message obtainMessage = this.handler.obtainMessage(1000);
        obtainMessage.obj = t;
        this.handler.sendMessageDelayed(obtainMessage, (long) this.delayMillis);
        onCalled(t);
    }

    public final void call() {
        call(null);
    }
}
