package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: IMASDK */
public class uj {
    private final Handler a;

    public Looper a() {
        return this.a.getLooper();
    }

    public Message a(int i, Object obj) {
        return this.a.obtainMessage(i, obj);
    }

    public Message a(int i, int i2, int i3) {
        return this.a.obtainMessage(i, i2, 0);
    }

    public Message a(int i, int i2, int i3, Object obj) {
        return this.a.obtainMessage(0, i2, i3, obj);
    }

    public boolean a(int i) {
        return this.a.sendEmptyMessage(i);
    }

    public boolean a(int i, long j) {
        return this.a.sendEmptyMessageAtTime(2, j);
    }

    public void b(int i) {
        this.a.removeMessages(2);
    }

    public uj(Handler handler) {
        this.a = handler;
    }
}
