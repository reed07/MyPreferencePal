package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.icing.zzab;
import com.google.android.gms.internal.icing.zzag;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzr extends TaskApiCall<zzag, Void> implements ResultHolder<Status> {
    private TaskCompletionSource<Void> zzfg;

    private zzr() {
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzab zzab) throws RemoteException;

    public void setFailedResult(Status status) {
        Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success.");
        this.zzfg.setException(zzae.zza(status, status.getStatusMessage()));
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzag zzag = (zzag) anyClient;
        this.zzfg = taskCompletionSource;
        zza((zzab) zzag.getService());
    }

    public /* synthetic */ void setResult(Object obj) {
        Status status = (Status) obj;
        if (status.isSuccess()) {
            this.zzfg.setResult(null);
        } else {
            this.zzfg.setException(zzae.zza(status, "User Action indexing error, please try again."));
        }
    }

    /* synthetic */ zzr(zzp zzp) {
        this();
    }
}
