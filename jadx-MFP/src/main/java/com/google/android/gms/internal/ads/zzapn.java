package com.google.android.gms.internal.ads;

import android.content.Context;

@zzark
public class zzapn extends zzapf {
    zzapn(Context context, zzaxg zzaxg, zzbgg zzbgg, zzapm zzapm) {
        super(context, zzaxg, zzbgg, zzapm);
    }

    /* access modifiers changed from: protected */
    public void zzwb() {
    }

    /* access modifiers changed from: protected */
    public final void zzvz() {
        if (this.zzdsl.errorCode == -2) {
            this.zzdin.zzadl().zza((zzbho) this);
            zzwb();
            zzaxz.zzdn("Loading HTML in WebView.");
            this.zzdin.zzc(this.zzdsl.zzbde, this.zzdsl.zzdyb, null);
        }
    }
}
