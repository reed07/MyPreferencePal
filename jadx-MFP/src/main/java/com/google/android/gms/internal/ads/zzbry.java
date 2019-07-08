package com.google.android.gms.internal.ads;

import java.util.List;

final class zzbry extends zzbrv {
    private zzbry() {
        super();
    }

    /* access modifiers changed from: 0000 */
    public final <L> List<L> zza(Object obj, long j) {
        zzbrk zzd = zzd(obj, j);
        if (zzd.zzaki()) {
            return zzd;
        }
        int size = zzd.size();
        zzbrk zzel = zzd.zzel(size == 0 ? 10 : size << 1);
        zzbua.zza(obj, j, (Object) zzel);
        return zzel;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(Object obj, long j) {
        zzd(obj, j).zzakj();
    }

    /* access modifiers changed from: 0000 */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzbrk zzd = zzd(obj, j);
        zzbrk zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zzaki()) {
                zzd = zzd.zzel(size2 + size);
            }
            zzd.addAll(zzd2);
        }
        if (size > 0) {
            zzd2 = zzd;
        }
        zzbua.zza(obj, j, (Object) zzd2);
    }

    private static <E> zzbrk<E> zzd(Object obj, long j) {
        return (zzbrk) zzbua.zzp(obj, j);
    }
}
