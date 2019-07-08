package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.fitness.data.BleDevice;

final class zzb implements Notifier<BleScanCallback> {
    private final /* synthetic */ BleDevice zzgf;

    zzb(zza zza, BleDevice bleDevice) {
        this.zzgf = bleDevice;
    }

    public final void onNotifyListenerFailed() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((BleScanCallback) obj).onDeviceFound(this.zzgf);
    }
}
