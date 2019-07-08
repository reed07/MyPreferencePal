package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.BleDevice;

public final class zza extends zzaf {
    private final ListenerHolder<BleScanCallback> zzge;

    private zza(ListenerHolder<BleScanCallback> listenerHolder) {
        this.zzge = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    public final void onDeviceFound(BleDevice bleDevice) throws RemoteException {
        this.zzge.notifyListener(new zzb(this, bleDevice));
    }

    public final void onScanStopped() throws RemoteException {
        this.zzge.notifyListener(new zzc(this));
    }

    public final void release() {
        this.zzge.clear();
    }

    /* synthetic */ zza(ListenerHolder listenerHolder, zzb zzb) {
        this(listenerHolder);
    }
}
