package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzvi extends zzvf {
    private zzvi() {
        super();
    }

    /* access modifiers changed from: 0000 */
    public final <L> List<L> zza(Object obj, long j) {
        zzuu zzd = zzd(obj, j);
        if (zzd.zztz()) {
            return zzd;
        }
        int size = zzd.size();
        zzuu zzal = zzd.zzal(size == 0 ? 10 : size << 1);
        zzxj.zza(obj, j, (Object) zzal);
        return zzal;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(Object obj, long j) {
        zzd(obj, j).zzsw();
    }

    /* access modifiers changed from: 0000 */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzuu zzd = zzd(obj, j);
        zzuu zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zztz()) {
                zzd = zzd.zzal(size2 + size);
            }
            zzd.addAll(zzd2);
        }
        if (size > 0) {
            zzd2 = zzd;
        }
        zzxj.zza(obj, j, (Object) zzd2);
    }

    private static <E> zzuu<E> zzd(Object obj, long j) {
        return (zzuu) zzxj.zzp(obj, j);
    }
}
