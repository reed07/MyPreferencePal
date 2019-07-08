package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzv;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzbhz {
    public static zzbgg zza(Context context, zzbht zzbht, String str, boolean z, boolean z2, @Nullable zzcu zzcu, zzbbi zzbbi, zzaba zzaba, zzbo zzbo, zzv zzv, zzum zzum) throws zzbgq {
        try {
            zzbia zzbia = new zzbia(context, zzbht, str, z, z2, zzcu, zzbbi, zzaba, zzbo, zzv);
            return (zzbgg) zzbak.zzb(zzbia);
        } catch (Throwable th) {
            zzbv.zzlj().zza(th, "AdWebViewFactory.newAdWebView2");
            throw new zzbgq("Webview initialization failed.", th);
        }
    }
}
