package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;

final class zzqt implements Callback, FrameCallback {
    private static final zzqt zzbkk = new zzqt();
    private final Handler handler;
    public volatile long zzbkj;
    private final HandlerThread zzbkl = new HandlerThread("ChoreographerOwner:Handler");
    private Choreographer zzbkm;
    private int zzbkn;

    public static zzqt zzhv() {
        return zzbkk;
    }

    private zzqt() {
        this.zzbkl.start();
        this.handler = new Handler(this.zzbkl.getLooper(), this);
        this.handler.sendEmptyMessage(0);
    }

    public final void zzhw() {
        this.handler.sendEmptyMessage(1);
    }

    public final void zzhx() {
        this.handler.sendEmptyMessage(2);
    }

    public final void doFrame(long j) {
        this.zzbkj = j;
        this.zzbkm.postFrameCallbackDelayed(this, 500);
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.zzbkm = Choreographer.getInstance();
                return true;
            case 1:
                this.zzbkn++;
                if (this.zzbkn == 1) {
                    this.zzbkm.postFrameCallback(this);
                }
                return true;
            case 2:
                this.zzbkn--;
                if (this.zzbkn == 0) {
                    this.zzbkm.removeFrameCallback(this);
                    this.zzbkj = 0;
                }
                return true;
            default:
                return false;
        }
    }
}
