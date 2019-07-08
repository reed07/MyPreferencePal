package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

@zzark
public final class zzaxc implements zzaxe {
    public final zzbcb<String> zza(String str, PackageInfo packageInfo) {
        return zzbbq.zzm(str);
    }

    public final zzbcb<Info> zzad(Context context) {
        zzbcl zzbcl = new zzbcl();
        zzwu.zzpv();
        if (zzbat.zzbh(context)) {
            zzayf.zzc(new zzaxd(this, context, zzbcl));
        }
        return zzbcl;
    }
}
