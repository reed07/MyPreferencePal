package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback.Stub;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzn extends Stub {
    private final /* synthetic */ TaskCompletionSource zzfc;
    private final /* synthetic */ zzm zzfd;

    zzn(zzm zzm, TaskCompletionSource taskCompletionSource) {
        this.zzfd = zzm;
        this.zzfc = taskCompletionSource;
    }

    public final void onResult(Status status) throws RemoteException {
        if (this.zzfc.trySetResult(null)) {
            if (status.isSuccess()) {
                this.zzfd.zzfb.zzey.setResult(null);
                return;
            }
            this.zzfd.zzfb.zzey.setException(zzae.zza(status, "Indexing error, please try again."));
        }
    }
}
