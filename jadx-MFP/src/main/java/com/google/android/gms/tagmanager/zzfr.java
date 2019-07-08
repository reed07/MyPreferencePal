package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

final class zzfr implements zzfq {
    private Handler handler;
    final /* synthetic */ zzfn zzbgl;

    private zzfr(zzfn zzfn) {
        this.zzbgl = zzfn;
        this.handler = new Handler(this.zzbgl.zzbga.getMainLooper(), new zzfs(this));
    }

    public final void zzqh() {
        this.handler.removeMessages(1, zzfn.zzbfz);
        this.handler.sendMessage(obtainMessage());
    }

    public final void cancel() {
        this.handler.removeMessages(1, zzfn.zzbfz);
    }

    public final void zzh(long j) {
        this.handler.removeMessages(1, zzfn.zzbfz);
        this.handler.sendMessageDelayed(obtainMessage(), j);
    }

    private final Message obtainMessage() {
        return this.handler.obtainMessage(1, zzfn.zzbfz);
    }

    /* synthetic */ zzfr(zzfn zzfn, zzfo zzfo) {
        this(zzfn);
    }
}
