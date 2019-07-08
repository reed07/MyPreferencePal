package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzbrx extends zzbrv {
    private static final Class<?> zzfrt = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzbrx() {
        super();
    }

    /* access modifiers changed from: 0000 */
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzbua.zzp(obj, j);
        if (list instanceof zzbru) {
            obj2 = ((zzbru) list).zzanp();
        } else if (!zzfrt.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzbsx) || !(list instanceof zzbrk)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzbrk zzbrk = (zzbrk) list;
                if (zzbrk.zzaki()) {
                    zzbrk.zzakj();
                }
                return;
            }
        } else {
            return;
        }
        zzbua.zza(obj, j, obj2);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> list;
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            if (zzc instanceof zzbru) {
                list = new zzbrt<>(i);
            } else if (!(zzc instanceof zzbsx) || !(zzc instanceof zzbrk)) {
                list = new ArrayList<>(i);
            } else {
                list = ((zzbrk) zzc).zzel(i);
            }
            zzbua.zza(obj, j, (Object) list);
            return list;
        } else if (zzfrt.isAssignableFrom(zzc.getClass())) {
            ArrayList arrayList = new ArrayList(zzc.size() + i);
            arrayList.addAll(zzc);
            zzbua.zza(obj, j, (Object) arrayList);
            return arrayList;
        } else if (zzc instanceof zzbtx) {
            zzbrt zzbrt = new zzbrt(zzc.size() + i);
            zzbrt.addAll((zzbtx) zzc);
            zzbua.zza(obj, j, (Object) zzbrt);
            return zzbrt;
        } else if (!(zzc instanceof zzbsx) || !(zzc instanceof zzbrk)) {
            return zzc;
        } else {
            zzbrk zzbrk = (zzbrk) zzc;
            if (zzbrk.zzaki()) {
                return zzc;
            }
            zzbrk zzel = zzbrk.zzel(zzc.size() + i);
            zzbua.zza(obj, j, (Object) zzel);
            return zzel;
        }
    }

    /* access modifiers changed from: 0000 */
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzc = zzc(obj2, j);
        List zza = zza(obj, j, zzc.size());
        int size = zza.size();
        int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzc);
        }
        if (size > 0) {
            zzc = zza;
        }
        zzbua.zza(obj, j, (Object) zzc);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzbua.zzp(obj, j);
    }
}
