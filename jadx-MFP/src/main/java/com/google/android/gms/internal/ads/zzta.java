package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;

final class zzta implements ValueCallback<String> {
    private final /* synthetic */ zzsz zzbyp;

    zzta(zzsz zzsz) {
        this.zzbyp = zzsz;
    }

    public final /* synthetic */ void onReceiveValue(Object obj) {
        this.zzbyp.zzbyk.zza(this.zzbyp.zzbym, this.zzbyp.zzbyn, (String) obj, this.zzbyp.zzbyo);
    }
}
