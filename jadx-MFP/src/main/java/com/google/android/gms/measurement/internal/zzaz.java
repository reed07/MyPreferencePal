package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.Map;

@WorkerThread
final class zzaz implements Runnable {
    private final String packageName;
    private final int status;
    private final zzay zzamp;
    private final Throwable zzamq;
    private final byte[] zzamr;
    private final Map<String, List<String>> zzams;

    private zzaz(String str, zzay zzay, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        Preconditions.checkNotNull(zzay);
        this.zzamp = zzay;
        this.status = i;
        this.zzamq = th;
        this.zzamr = bArr;
        this.packageName = str;
        this.zzams = map;
    }

    public final void run() {
        this.zzamp.zza(this.packageName, this.status, this.zzamq, this.zzamr, this.zzams);
    }
}
