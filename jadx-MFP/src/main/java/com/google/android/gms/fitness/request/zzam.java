package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.fitness.data.DataPoint;

final class zzam implements Notifier<OnDataPointListener> {
    private final /* synthetic */ DataPoint zzho;

    zzam(zzal zzal, DataPoint dataPoint) {
        this.zzho = dataPoint;
    }

    public final void onNotifyListenerFailed() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((OnDataPointListener) obj).onDataPoint(this.zzho);
    }
}
