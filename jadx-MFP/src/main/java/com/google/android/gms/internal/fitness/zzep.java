package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzep implements ResultHolder<Status> {
    private final /* synthetic */ TaskCompletionSource zzgc;

    zzep(TaskCompletionSource taskCompletionSource) {
        this.zzgc = taskCompletionSource;
    }

    public final void setFailedResult(Status status) {
        throw new UnsupportedOperationException("This method should never get invoked");
    }

    public final /* synthetic */ void setResult(Object obj) {
        Status status = (Status) obj;
        TaskUtil.setResultOrApiException(status, Boolean.valueOf(status.isSuccess()), this.zzgc);
    }
}
