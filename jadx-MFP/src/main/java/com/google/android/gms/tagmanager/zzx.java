package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener;

final class zzx extends Handler {
    private final ContainerAvailableListener zzbag;
    private final /* synthetic */ zzv zzbah;

    public zzx(zzv zzv, ContainerAvailableListener containerAvailableListener, Looper looper) {
        this.zzbah = zzv;
        super(looper);
        this.zzbag = containerAvailableListener;
    }

    public final void handleMessage(Message message) {
        if (message.what != 1) {
            zzdi.e("Don't know how to handle this message.");
            return;
        }
        this.zzbag.onContainerAvailable(this.zzbah, (String) message.obj);
    }
}
