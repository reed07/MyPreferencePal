package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzv;

final /* synthetic */ class zzbgn implements zzbbl {
    private final zzcu zzdjb;
    private final Context zzdks;
    private final String zzdun;
    private final zzbbi zzeyr;
    private final zzv zzeys;

    zzbgn(Context context, zzcu zzcu, zzbbi zzbbi, zzv zzv, String str) {
        this.zzdks = context;
        this.zzdjb = zzcu;
        this.zzeyr = zzbbi;
        this.zzeys = zzv;
        this.zzdun = str;
    }

    public final zzbcb zzf(Object obj) {
        Context context = this.zzdks;
        zzcu zzcu = this.zzdjb;
        zzbbi zzbbi = this.zzeyr;
        zzv zzv = this.zzeys;
        String str = this.zzdun;
        zzbv.zzlg();
        zzbgg zza = zzbgm.zza(context, zzbht.zzaey(), "", false, false, zzcu, zzbbi, null, null, zzv, zzum.zzoi());
        zzbck zzn = zzbck.zzn(zza);
        zza.zzadl().zza((zzbho) new zzbgp(zzn));
        zza.loadUrl(str);
        return zzn;
    }
}
