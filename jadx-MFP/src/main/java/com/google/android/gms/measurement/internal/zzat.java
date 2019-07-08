package com.google.android.gms.measurement.internal;

import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;

final class zzat implements Runnable {
    private final /* synthetic */ int zzamf;
    private final /* synthetic */ String zzamg;
    private final /* synthetic */ Object zzamh;
    private final /* synthetic */ Object zzami;
    private final /* synthetic */ Object zzamj;
    private final /* synthetic */ zzas zzamk;

    zzat(zzas zzas, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzamk = zzas;
        this.zzamf = i;
        this.zzamg = str;
        this.zzamh = obj;
        this.zzami = obj2;
        this.zzamj = obj3;
    }

    public final void run() {
        zzbd zzgu = this.zzamk.zzada.zzgu();
        if (!zzgu.isInitialized()) {
            this.zzamk.zza(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.zzamk.zzalu == 0) {
            if (this.zzamk.zzgv().zzdw()) {
                zzas zzas = this.zzamk;
                zzas.zzgw();
                zzas.zzalu = 'C';
            } else {
                zzas zzas2 = this.zzamk;
                zzas2.zzgw();
                zzas2.zzalu = 'c';
            }
        }
        if (this.zzamk.zzade < 0) {
            zzas zzas3 = this.zzamk;
            zzas3.zzade = zzas3.zzgv().zzhh();
        }
        char charAt = "01VDIWEA?".charAt(this.zzamf);
        char zza = this.zzamk.zzalu;
        long zzb = this.zzamk.zzade;
        String zza2 = zzas.zza(true, this.zzamg, this.zzamh, this.zzami, this.zzamj);
        StringBuilder sb = new StringBuilder(String.valueOf(zza2).length() + 24);
        sb.append(InternalAvidAdSessionContext.AVID_API_LEVEL);
        sb.append(charAt);
        sb.append(zza);
        sb.append(zzb);
        sb.append(":");
        sb.append(zza2);
        String sb2 = sb.toString();
        if (sb2.length() > 1024) {
            sb2 = this.zzamg.substring(0, 1024);
        }
        zzgu.zzanb.zzc(sb2, 1);
    }
}
