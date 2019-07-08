package com.google.android.gms.internal.icing;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;

@ShowFirstParty
@VisibleForTesting
public final class zzy {
    private zzj zzaj;
    private long zzak = -1;
    private int zzal = -1;
    private zzg zzan;
    private boolean zzao = false;
    private int zzap = -1;
    private int zzaq = 0;

    public final zzy zza(zzj zzj) {
        this.zzaj = zzj;
        return this;
    }

    public final zzy zza(long j) {
        this.zzak = j;
        return this;
    }

    public final zzy zzb(int i) {
        this.zzal = i;
        return this;
    }

    public final zzy zza(zzg zzg) {
        this.zzan = zzg;
        return this;
    }

    public final zzy zzd(boolean z) {
        this.zzao = z;
        return this;
    }

    public final zzy zzc(int i) {
        this.zzaq = i;
        return this;
    }

    public final zzx zzd() {
        zzx zzx = new zzx(this.zzaj, this.zzak, this.zzal, null, this.zzan, this.zzao, this.zzap, this.zzaq, null);
        return zzx;
    }
}
