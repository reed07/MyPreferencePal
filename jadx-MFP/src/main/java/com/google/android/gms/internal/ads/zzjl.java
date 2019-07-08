package com.google.android.gms.internal.ads;

final class zzjl {
    public final zzii zzakw;
    public final zzju zzaqt = new zzju();
    public zzjs zzaqu;
    public zzjf zzaqv;
    public int zzaqw;
    public int zzaqx;
    public int zzaqy;
    public zzij zzaqz;
    public zzjt zzara;

    public zzjl(zzii zzii) {
        this.zzakw = zzii;
    }

    public final void zza(zzjs zzjs, zzjf zzjf) {
        this.zzaqu = (zzjs) zzpo.checkNotNull(zzjs);
        this.zzaqv = (zzjf) zzpo.checkNotNull(zzjf);
        this.zzakw.zzf(zzjs.zzaad);
        reset();
    }

    public final void reset() {
        zzju zzju = this.zzaqt;
        zzju.zzasx = 0;
        zzju.zzatk = 0;
        zzju.zzate = false;
        zzju.zzatj = false;
        zzju.zzatg = null;
        this.zzaqw = 0;
        this.zzaqy = 0;
        this.zzaqx = 0;
        this.zzaqz = null;
        this.zzara = null;
    }
}
