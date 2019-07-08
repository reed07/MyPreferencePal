package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxv;
import com.google.android.gms.internal.ads.zzayh;

@zzark
final class zzj extends zzaxv {
    final /* synthetic */ zzd zzdrn;

    private zzj(zzd zzd) {
        this.zzdrn = zzd;
    }

    public final void onStop() {
    }

    public final void zzki() {
        Bitmap zza = zzbv.zzma().zza(Integer.valueOf(this.zzdrn.zzdqu.zzdsc.zzbpf));
        if (zza != null) {
            zzayh.zzelc.post(new zzk(this, zzbv.zzlh().zza(this.zzdrn.mActivity, zza, this.zzdrn.zzdqu.zzdsc.zzbpd, this.zzdrn.zzdqu.zzdsc.zzbpe)));
        }
    }

    /* synthetic */ zzj(zzd zzd, zzf zzf) {
        this(zzd);
    }
}
