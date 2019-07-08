package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzarc implements zzbbn<zzbgg> {
    private final /* synthetic */ String val$message;
    private final /* synthetic */ JSONObject zzdve;

    zzarc(zzaqt zzaqt, String str, JSONObject jSONObject) {
        this.val$message = str;
        this.zzdve = jSONObject;
    }

    public final void zzb(Throwable th) {
    }

    public final /* synthetic */ void zzl(Object obj) {
        ((zzbgg) obj).zza(this.val$message, this.zzdve);
    }
}
