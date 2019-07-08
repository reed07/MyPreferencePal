package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzen extends zzcr {
    private final ResultHolder<Status> zzev;

    public zzen(ResultHolder<Status> resultHolder) {
        this.zzev = resultHolder;
    }

    public final void onResult(Status status) {
        this.zzev.setResult(status);
    }

    public static zzen zza(TaskCompletionSource<Void> taskCompletionSource) {
        return new zzen(new zzeo(taskCompletionSource));
    }

    public static zzen zzb(TaskCompletionSource<Boolean> taskCompletionSource) {
        return new zzen(new zzep(taskCompletionSource));
    }
}
