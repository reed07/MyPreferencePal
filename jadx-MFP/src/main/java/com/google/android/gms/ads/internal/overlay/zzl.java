package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzayh;

@zzark
public final class zzl {
    public static void zza(Context context, AdOverlayInfoParcel adOverlayInfoParcel, boolean z) {
        if (adOverlayInfoParcel.zzdsa == 4 && adOverlayInfoParcel.zzdru == null) {
            if (adOverlayInfoParcel.zzdrt != null) {
                adOverlayInfoParcel.zzdrt.onAdClicked();
            }
            zzbv.zzlc();
            zza.zza(context, adOverlayInfoParcel.zzdrs, adOverlayInfoParcel.zzdrz);
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel.zzbsp.zzeow);
        intent.putExtra("shouldCallOnOverlayOpened", z);
        AdOverlayInfoParcel.zza(intent, adOverlayInfoParcel);
        if (!PlatformVersion.isAtLeastLollipop()) {
            intent.addFlags(524288);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        zzbv.zzlf();
        zzayh.zza(context, intent);
    }
}
