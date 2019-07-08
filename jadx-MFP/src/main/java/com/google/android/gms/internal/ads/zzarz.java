package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

final class zzarz implements zzasb {
    private final /* synthetic */ Context val$context;

    zzarz(Context context) {
        this.val$context = context;
    }

    public final boolean zza(zzbbi zzbbi) {
        zzwu.zzpv();
        boolean zzc = zzbat.zzc(this.val$context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        boolean z = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcwr)).booleanValue() && zzbbi.zzeow;
        zzbv.zzlf();
        return !zzary.zze(this.val$context, zzbbi.zzeow) || !zzc || z || zzayh.zzec(this.val$context.getPackageName());
    }
}
