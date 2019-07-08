package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Message;

/* compiled from: IMASDK */
public final class aeh {
    private final Handler a;

    public aeh(Handler handler) {
        this.a = handler;
    }

    /* access modifiers changed from: protected */
    public final void a(int i) {
        this.a.removeMessages(1);
    }

    /* access modifiers changed from: protected */
    public final boolean b(int i) {
        return this.a.sendEmptyMessage(0);
    }

    /* access modifiers changed from: protected */
    public final boolean a(int i, long j) {
        return this.a.sendEmptyMessageDelayed(1, j);
    }

    /* access modifiers changed from: protected */
    public final boolean c(int i) {
        Handler handler = this.a;
        return handler.sendMessageAtFrontOfQueue(Message.obtain(handler, 2));
    }
}
