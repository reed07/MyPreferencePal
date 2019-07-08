package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;

final class zzc implements Notifier<BleScanCallback> {
    zzc(zza zza) {
    }

    public final void onNotifyListenerFailed() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((BleScanCallback) obj).onScanStopped();
    }
}
