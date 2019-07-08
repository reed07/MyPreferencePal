package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzae;
import com.google.android.gms.fitness.request.zzd;
import com.google.android.gms.internal.fitness.zzbt;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.internal.fitness.zzp;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzb extends RegisterListenerMethod<zzp, BleScanCallback> {
    private final /* synthetic */ ListenerHolder zzg;
    private final /* synthetic */ List zzh;
    private final /* synthetic */ int zzi;

    zzb(BleClient bleClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2, List list, int i) {
        this.zzg = listenerHolder2;
        this.zzh = list;
        this.zzi = i;
        super(listenerHolder);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void registerListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzbt) ((zzp) anyClient).getService()).zza(new StartBleScanRequest(this.zzh, (zzae) zzd.zzt().zza(this.zzg), this.zzi, (zzcq) zzen.zza(taskCompletionSource)));
    }
}
