package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

@VisibleForTesting
final class zzk {
    /* access modifiers changed from: private */
    public final zzx zzex;
    /* access modifiers changed from: private */
    public final TaskCompletionSource<Void> zzey = new TaskCompletionSource<>();
    final /* synthetic */ zzj zzez;

    public zzk(zzj zzj, zzx zzx) {
        this.zzez = zzj;
        this.zzex = zzx;
    }

    public final Task<Void> getTask() {
        return this.zzey.getTask();
    }

    public final void execute() {
        synchronized (this.zzez.zzev) {
            Preconditions.checkState(this.zzez.zzew == 0);
            this.zzez.zzew = 1;
        }
        this.zzez.zzet.doWrite((TaskApiCall<A, TResult>) new zzm<A,TResult>(this)).addOnFailureListener((Executor) this.zzez, (OnFailureListener) new zzl(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Exception exc) {
        zzk zzk;
        synchronized (this.zzez.zzev) {
            if (this.zzez.zzev.peek() == this) {
                this.zzez.zzev.remove();
                this.zzez.zzew = 0;
                zzk = (zzk) this.zzez.zzev.peek();
            } else {
                zzk = null;
            }
        }
        this.zzey.trySetException(exc);
        if (zzk != null) {
            zzk.execute();
        }
    }
}
