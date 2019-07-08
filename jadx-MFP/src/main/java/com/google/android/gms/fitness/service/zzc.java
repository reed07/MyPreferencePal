package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.zzt;
import java.util.List;

final class zzc implements SensorEventDispatcher {
    private final zzt zzhr;

    zzc(zzt zzt) {
        this.zzhr = (zzt) Preconditions.checkNotNull(zzt);
    }

    public final void publish(DataPoint dataPoint) throws RemoteException {
        dataPoint.zzg();
        this.zzhr.zzc(dataPoint);
    }

    public final void publish(List<DataPoint> list) throws RemoteException {
        for (DataPoint publish : list) {
            publish(publish);
        }
    }
}
