package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.zzu;

public final class zzal extends zzu {
    private final ListenerHolder<OnDataPointListener> zzhn;

    private zzal(ListenerHolder<OnDataPointListener> listenerHolder) {
        this.zzhn = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    public final void zzc(DataPoint dataPoint) throws RemoteException {
        this.zzhn.notifyListener(new zzam(this, dataPoint));
    }

    public final void release() {
        this.zzhn.clear();
    }

    /* synthetic */ zzal(ListenerHolder listenerHolder, zzam zzam) {
        this(listenerHolder);
    }
}
