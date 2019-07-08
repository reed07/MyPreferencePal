package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.request.zzao;
import com.google.android.gms.internal.fitness.zzas;
import com.google.android.gms.internal.fitness.zzcd;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzn extends RegisterListenerMethod<zzas, OnDataPointListener> {
    private final /* synthetic */ ListenerHolder zzg;
    private final /* synthetic */ SensorRequest zzy;

    zzn(SensorsClient sensorsClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2, SensorRequest sensorRequest) {
        this.zzg = listenerHolder2;
        this.zzy = sensorRequest;
        super(listenerHolder);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void registerListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzcd) ((zzas) anyClient).getService()).zza(new zzao(this.zzy, zzan.zzw().zzc(this.zzg), null, zzen.zza(taskCompletionSource)));
    }
}
