package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.Map;

final class zzajb implements zzu<zzajr> {
    private final /* synthetic */ zzaii zzdji;
    private final /* synthetic */ zzait zzdjj;
    private final /* synthetic */ zzcu zzdjk;
    private final /* synthetic */ zzbaj zzdjl;

    zzajb(zzait zzait, zzcu zzcu, zzaii zzaii, zzbaj zzbaj) {
        this.zzdjj = zzait;
        this.zzdjk = zzcu;
        this.zzdji = zzaii;
        this.zzdjl = zzbaj;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        synchronized (this.zzdjj.mLock) {
            zzaxz.zzen("JS Engine is requesting an update");
            if (this.zzdjj.zzdiz == 0) {
                zzaxz.zzen("Starting reload.");
                this.zzdjj.zzdiz = 2;
                this.zzdjj.zza(this.zzdjk);
            }
            this.zzdji.zzb("/requestReload", (zzu) this.zzdjl.get());
        }
    }
}
