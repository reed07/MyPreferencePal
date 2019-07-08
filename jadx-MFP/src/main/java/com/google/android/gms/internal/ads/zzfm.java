package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class zzfm extends Handler {
    private final /* synthetic */ zzfl zzxr;

    zzfm(zzfl zzfl, Looper looper) {
        this.zzxr = zzfl;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.zzxr.zza(message);
    }
}
