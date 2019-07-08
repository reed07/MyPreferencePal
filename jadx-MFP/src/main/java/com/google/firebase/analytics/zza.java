package com.google.firebase.analytics;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

final class zza implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zzbsq;

    zza(FirebaseAnalytics firebaseAnalytics) {
        this.zzbsq = firebaseAnalytics;
    }

    public final /* synthetic */ Object call() throws Exception {
        String zza = this.zzbsq.zzgc();
        if (zza != null) {
            return zza;
        }
        String zzag = this.zzbsq.zzada.zzgj().zzag(120000);
        if (zzag != null) {
            this.zzbsq.zzcp(zzag);
            return zzag;
        }
        throw new TimeoutException();
    }
}
