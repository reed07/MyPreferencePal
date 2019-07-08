package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCodec.CryptoInfo.Pattern;

@TargetApi(24)
final class zzhm {
    private final CryptoInfo zzagd;
    private final Pattern zzagf;

    private zzhm(CryptoInfo cryptoInfo) {
        this.zzagd = cryptoInfo;
        this.zzagf = new Pattern(0, 0);
    }

    /* access modifiers changed from: private */
    public final void set(int i, int i2) {
        this.zzagf.set(i, i2);
        this.zzagd.setPattern(this.zzagf);
    }
}
