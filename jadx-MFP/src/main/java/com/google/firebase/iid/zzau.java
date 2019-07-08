package com.google.firebase.iid;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.firebase_messaging.zza;

final class zzau extends zza {
    private final /* synthetic */ zzat zzcw;

    zzau(zzat zzat, Looper looper) {
        this.zzcw = zzat;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.zzcw.zzb(message);
    }
}
