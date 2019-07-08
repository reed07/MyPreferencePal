package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzfr implements Callable<String> {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzfn zzaug;

    zzfr(zzfn zzfn, zzk zzk) {
        this.zzaug = zzfn;
        this.zzaqn = zzk;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzg zzg;
        if (this.zzaug.zzgv().zzba(this.zzaqn.packageName)) {
            zzg = this.zzaug.zzg(this.zzaqn);
        } else {
            zzg = this.zzaug.zzjt().zzbm(this.zzaqn.packageName);
        }
        if (zzg != null) {
            return zzg.getAppInstanceId();
        }
        this.zzaug.zzgt().zzjj().zzby("App info was null when attempting to get app instance id");
        return null;
    }
}
