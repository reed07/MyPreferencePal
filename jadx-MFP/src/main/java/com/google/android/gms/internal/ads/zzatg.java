package com.google.android.gms.internal.ads;

final class zzatg implements zzbcq<zzajr> {
    private final /* synthetic */ zzatf zzeab;

    zzatg(zzatf zzatf) {
        this.zzeab = zzatf;
    }

    public final /* synthetic */ void zzh(Object obj) {
        try {
            ((zzajr) obj).zzb("AFMA_getAdapterLessMediationAd", this.zzeab.zzdzz);
        } catch (Exception e) {
            zzaxz.zzb("Error requesting an ad url", e);
            zzatd.zzdzv.zzbv(this.zzeab.zzeaa);
        }
    }
}
