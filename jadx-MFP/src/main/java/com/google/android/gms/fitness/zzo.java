package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.zzal;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.request.zzar;
import com.google.android.gms.internal.fitness.zzas;
import com.google.android.gms.internal.fitness.zzcd;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzo extends UnregisterListenerMethod<zzas, OnDataPointListener> {
    private final /* synthetic */ ListenerHolder zzg;

    zzo(SensorsClient sensorsClient, ListenerKey listenerKey, ListenerHolder listenerHolder) {
        this.zzg = listenerHolder;
        super(listenerKey);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void unregisterListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzas zzas = (zzas) anyClient;
        zzal zzd = zzan.zzw().zzd(this.zzg);
        if (zzd == null) {
            taskCompletionSource.setResult(Boolean.valueOf(false));
            return;
        }
        ((zzcd) zzas.getService()).zza(new zzar((zzt) zzd, (PendingIntent) null, (zzcq) zzen.zzb(taskCompletionSource)));
    }
}
