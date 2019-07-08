package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.zza;
import com.google.android.gms.fitness.request.zzae;
import com.google.android.gms.fitness.request.zzbh;
import com.google.android.gms.fitness.request.zzd;
import com.google.android.gms.internal.fitness.zzbt;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.internal.fitness.zzp;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzc extends UnregisterListenerMethod<zzp, BleScanCallback> {
    private final /* synthetic */ ListenerHolder zzg;

    zzc(BleClient bleClient, ListenerKey listenerKey, ListenerHolder listenerHolder) {
        this.zzg = listenerHolder;
        super(listenerKey);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void unregisterListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzp zzp = (zzp) anyClient;
        zza zzb = zzd.zzt().zzb(this.zzg);
        if (zzb == null) {
            taskCompletionSource.setResult(Boolean.valueOf(false));
            return;
        }
        ((zzbt) zzp.getService()).zza(new zzbh((zzae) zzb, (zzcq) zzen.zzb(taskCompletionSource)));
    }
}
