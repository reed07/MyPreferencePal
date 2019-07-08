package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzmm extends zzmc {
    private volatile boolean zzaxq;
    private final long zzayv;
    private final int zzbas;
    private final zzmf zzbat;
    private volatile int zzbau;
    private volatile boolean zzbav;

    public zzmm(zzov zzov, zzoz zzoz, zzfs zzfs, int i, Object obj, long j, long j2, int i2, int i3, long j3, zzmf zzmf) {
        super(zzov, zzoz, zzfs, i, obj, j, j2, i2);
        this.zzbas = i3;
        this.zzayv = j3;
        this.zzbat = zzmf;
    }

    public final int zzfz() {
        return this.zzbaw + this.zzbas;
    }

    public final boolean zzga() {
        return this.zzbav;
    }

    public final long zzfv() {
        return (long) this.zzbau;
    }

    public final void cancelLoad() {
        this.zzaxq = true;
    }

    public final boolean zzfe() {
        return this.zzaxq;
    }

    public final void zzff() throws IOException, InterruptedException {
        zzhx zzhx;
        zzoz zza = zzqe.zza(this.zzazo, this.zzbau);
        try {
            zzhx = new zzhx(this.zzagy, zza.zzbfu, this.zzagy.zza(zza));
            if (this.zzbau == 0) {
                zzmd zzft = zzft();
                zzft.zzae(this.zzayv);
                this.zzbat.zza((zzmh) zzft);
            }
            zzhz zzhz = this.zzbat.zzaxu;
            boolean z = false;
            int i = 0;
            while (i == 0 && !this.zzaxq) {
                i = zzhz.zza(zzhx, null);
            }
            if (i != 1) {
                z = true;
            }
            zzpo.checkState(z);
            this.zzbau = (int) (zzhx.getPosition() - this.zzazo.zzbfu);
            zzqe.zza(this.zzagy);
            this.zzbav = true;
        } catch (Throwable th) {
            zzqe.zza(this.zzagy);
            throw th;
        }
    }
}
