package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzv;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzbgm {
    public static zzbcb<zzbgg> zza(Context context, zzbbi zzbbi, String str, zzcu zzcu, zzv zzv) {
        zzbca zzm = zzbbq.zzm(null);
        zzbgn zzbgn = new zzbgn(context, zzcu, zzbbi, zzv, str);
        return zzbbq.zza((zzbcb<A>) zzm, (zzbbl<? super A, ? extends B>) zzbgn, zzbcg.zzepo);
    }

    public static zzbgg zza(Context context, zzbht zzbht, String str, boolean z, boolean z2, @Nullable zzcu zzcu, zzbbi zzbbi, zzaba zzaba, zzbo zzbo, zzv zzv, zzum zzum) throws zzbgq {
        zzaan.initialize(context);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcrc)).booleanValue()) {
            return zzbhz.zza(context, zzbht, str, z, z2, zzcu, zzbbi, zzaba, zzbo, zzv, zzum);
        }
        try {
            zzbgo zzbgo = new zzbgo(context, zzbht, str, z, z2, zzcu, zzbbi, zzaba, zzbo, zzv, zzum);
            return (zzbgg) zzbak.zzb(zzbgo);
        } catch (Throwable th) {
            throw new zzbgq("Webview initialization failed.", th);
        }
    }
}
