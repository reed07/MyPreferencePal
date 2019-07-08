package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzlj implements zzlv {
    /* access modifiers changed from: private */
    public final int track;
    private final /* synthetic */ zzlc zzaxn;

    public zzlj(zzlc zzlc, int i) {
        this.zzaxn = zzlc;
        this.track = i;
    }

    public final boolean isReady() {
        return this.zzaxn.zzap(this.track);
    }

    public final void zzev() throws IOException {
        this.zzaxn.zzev();
    }

    public final int zzb(zzfu zzfu, zzho zzho, boolean z) {
        return this.zzaxn.zza(this.track, zzfu, zzho, z);
    }

    public final void zzz(long j) {
        this.zzaxn.zzd(this.track, j);
    }
}
