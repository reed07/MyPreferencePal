package com.google.android.gms.internal.icing;

final class zzee extends zzeb {
    private zzee() {
        super();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Object obj, long j) {
        zzc(obj, j).zzaj();
    }

    /* access modifiers changed from: 0000 */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzdq zzc = zzc(obj, j);
        zzdq zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzai()) {
                zzc = zzc.zzj(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzgd.zza(obj, j, (Object) zzc2);
    }

    private static <E> zzdq<E> zzc(Object obj, long j) {
        return (zzdq) zzgd.zzo(obj, j);
    }
}
