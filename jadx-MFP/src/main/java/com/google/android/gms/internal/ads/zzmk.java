package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzmk implements zzlv {
    private final int index;
    public final zzmj<T> zzbap;
    private final zzls zzbaq;
    private final /* synthetic */ zzmj zzbar;

    public zzmk(zzmj zzmj, zzmj<T> zzmj2, zzls zzls, int i) {
        this.zzbar = zzmj;
        this.zzbap = zzmj2;
        this.zzbaq = zzls;
        this.index = i;
    }

    public final void zzev() throws IOException {
    }

    public final boolean isReady() {
        return this.zzbar.zzaxm || (!this.zzbar.zzfd() && this.zzbaq.zzfm());
    }

    public final void zzz(long j) {
        if (!this.zzbar.zzaxm || j <= this.zzbaq.zzfc()) {
            this.zzbaq.zze(j, true);
        } else {
            this.zzbaq.zzfp();
        }
    }

    public final int zzb(zzfu zzfu, zzho zzho, boolean z) {
        if (this.zzbar.zzfd()) {
            return -3;
        }
        return this.zzbaq.zza(zzfu, zzho, z, this.zzbar.zzaxm, this.zzbar.zzaxj);
    }

    public final void release() {
        zzpo.checkState(this.zzbar.zzbae[this.index]);
        this.zzbar.zzbae[this.index] = false;
    }
}
