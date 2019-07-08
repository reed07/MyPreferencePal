package com.google.android.gms.internal.fitness;

import android.util.Log;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.fitness.result.DataReadResult;

final class zzds extends zzbi {
    private final ResultHolder<DataReadResult> zzev;
    private int zzfp;
    private DataReadResult zzfq;

    private zzds(ResultHolder<DataReadResult> resultHolder) {
        this.zzfp = 0;
        this.zzfq = null;
        this.zzev = resultHolder;
    }

    public final void zza(DataReadResult dataReadResult) {
        synchronized (this) {
            if (Log.isLoggable("Fitness", 2)) {
                int i = this.zzfp;
                StringBuilder sb = new StringBuilder(33);
                sb.append("Received batch result ");
                sb.append(i);
                Log.v("Fitness", sb.toString());
            }
            if (this.zzfq == null) {
                this.zzfq = dataReadResult;
            } else {
                this.zzfq.zzb(dataReadResult);
            }
            this.zzfp++;
            if (this.zzfp == this.zzfq.zzaa()) {
                this.zzev.setResult(this.zzfq);
            }
        }
    }

    /* synthetic */ zzds(ResultHolder resultHolder, zzdk zzdk) {
        this(resultHolder);
    }
}
