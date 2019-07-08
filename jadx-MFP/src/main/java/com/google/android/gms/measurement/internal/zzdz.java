package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzdz implements Runnable {
    private final /* synthetic */ boolean zzarz;
    private final /* synthetic */ zzdx zzasa;
    private final /* synthetic */ zzdx zzasb;
    private final /* synthetic */ zzdy zzasc;

    zzdz(zzdy zzdy, boolean z, zzdx zzdx, zzdx zzdx2) {
        this.zzasc = zzdy;
        this.zzarz = z;
        this.zzasa = zzdx;
        this.zzasb = zzdx2;
    }

    public final void run() {
        boolean z;
        boolean z2 = false;
        if (this.zzasc.zzgv().zzbk(this.zzasc.zzgk().zzal())) {
            z = this.zzarz && this.zzasc.zzart != null;
            if (z) {
                zzdy zzdy = this.zzasc;
                zzdy.zza(zzdy.zzart, true);
            }
        } else {
            if (this.zzarz && this.zzasc.zzart != null) {
                zzdy zzdy2 = this.zzasc;
                zzdy2.zza(zzdy2.zzart, true);
            }
            z = false;
        }
        zzdx zzdx = this.zzasa;
        if (zzdx == null || zzdx.zzarr != this.zzasb.zzarr || !zzfx.zzv(this.zzasa.zzarq, this.zzasb.zzarq) || !zzfx.zzv(this.zzasa.zzuw, this.zzasb.zzuw)) {
            z2 = true;
        }
        if (z2) {
            Bundle bundle = new Bundle();
            zzdy.zza(this.zzasb, bundle, true);
            zzdx zzdx2 = this.zzasa;
            if (zzdx2 != null) {
                if (zzdx2.zzuw != null) {
                    bundle.putString("_pn", this.zzasa.zzuw);
                }
                bundle.putString("_pc", this.zzasa.zzarq);
                bundle.putLong("_pi", this.zzasa.zzarr);
            }
            if (this.zzasc.zzgv().zzbk(this.zzasc.zzgk().zzal()) && z) {
                long zzlp = this.zzasc.zzgo().zzlp();
                if (zzlp > 0) {
                    this.zzasc.zzgr().zza(bundle, zzlp);
                }
            }
            this.zzasc.zzgj().zza("auto", "_vs", bundle);
        }
        zzdy zzdy3 = this.zzasc;
        zzdy3.zzart = this.zzasb;
        zzdy3.zzgl().zza(this.zzasb);
    }
}
