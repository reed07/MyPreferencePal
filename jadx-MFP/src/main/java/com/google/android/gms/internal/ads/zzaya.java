package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.ads.internal.zzbv;

@zzark
public final class zzaya extends Handler {
    public zzaya(Looper looper) {
        super(looper);
    }

    public final void handleMessage(Message message) {
        try {
            super.handleMessage(message);
        } catch (Exception e) {
            zzbv.zzlj().zza(e, "AdMobHandler.handleMessage");
        }
    }

    public final void dispatchMessage(Message message) {
        try {
            super.dispatchMessage(message);
        } catch (Throwable th) {
            zzbv.zzlf();
            zzayh.zza(zzbv.zzlj().getApplicationContext(), th);
            throw th;
        }
    }
}
