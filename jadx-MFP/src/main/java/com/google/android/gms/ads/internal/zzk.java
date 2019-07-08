package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzapl;
import com.google.android.gms.internal.ads.zzawr;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbgq;

final class zzk implements Runnable {
    final /* synthetic */ zzaxg zzblz;
    final /* synthetic */ zzh zzbmf;
    final /* synthetic */ zzawr zzbmg;
    private final /* synthetic */ zzaba zzbmh;

    zzk(zzh zzh, zzaxg zzaxg, zzawr zzawr, zzaba zzaba) {
        this.zzbmf = zzh;
        this.zzblz = zzaxg;
        this.zzbmg = zzawr;
        this.zzbmh = zzaba;
    }

    public final void run() {
        if (this.zzblz.zzehy.zzdym && this.zzbmf.zzbls.zzbto != null) {
            String str = null;
            if (this.zzblz.zzehy.zzbde != null) {
                zzbv.zzlf();
                str = zzayh.zzdx(this.zzblz.zzehy.zzbde);
            }
            zzabb zzabb = new zzabb(this.zzbmf, str, this.zzblz.zzehy.zzdyb);
            this.zzbmf.zzbls.zzbtw = 1;
            try {
                this.zzbmf.zzblq = false;
                this.zzbmf.zzbls.zzbto.zza(zzabb);
                return;
            } catch (RemoteException e) {
                zzaxz.zzd("#007 Could not call remote method.", e);
                this.zzbmf.zzblq = true;
            }
        }
        zzw zzw = new zzw(this.zzbmf.zzbls.zzsp, this.zzbmg, this.zzblz.zzehy.zzdyv);
        try {
            zzbgg zza = this.zzbmf.zza(this.zzblz, zzw, this.zzbmg);
            zza.zzay(this.zzbmf.zzbls.zzbsv.zzehy.zzdzf);
            zza.setOnTouchListener(new zzm(this, zzw));
            zza.setOnClickListener(new zzn(this, zzw));
            this.zzbmf.zzbls.zzbtw = 0;
            zzbw zzbw = this.zzbmf.zzbls;
            zzbv.zzle();
            Context context = this.zzbmf.zzbls.zzsp;
            zzh zzh = this.zzbmf;
            zzbw.zzbss = zzapl.zza(context, zzh, this.zzblz, zzh.zzbls.zzbso, zza, this.zzbmf.zzbma, this.zzbmf, this.zzbmh);
        } catch (zzbgq e2) {
            zzaxz.zzb("Could not obtain webview.", e2);
            zzayh.zzelc.post(new zzl(this));
        }
    }
}
