package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzed extends zzeb {
    private static final Class<?> zzls = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzed() {
        super();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Object obj, long j) {
        Object obj2;
        List list = (List) zzgd.zzo(obj, j);
        if (list instanceof zzea) {
            obj2 = ((zzea) list).zzch();
        } else if (!zzls.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzfb) || !(list instanceof zzdq)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzdq zzdq = (zzdq) list;
                if (zzdq.zzai()) {
                    zzdq.zzaj();
                }
                return;
            }
        } else {
            return;
        }
        zzgd.zza(obj, j, obj2);
    }

    /* access modifiers changed from: 0000 */
    public final <E> void zza(Object obj, Object obj2, long j) {
        Object obj3;
        List zzb = zzb(obj2, j);
        int size = zzb.size();
        List zzb2 = zzb(obj, j);
        if (zzb2.isEmpty()) {
            if (zzb2 instanceof zzea) {
                obj3 = new zzdz(size);
            } else if (!(zzb2 instanceof zzfb) || !(zzb2 instanceof zzdq)) {
                obj3 = new ArrayList(size);
            } else {
                obj3 = ((zzdq) zzb2).zzj(size);
            }
            zzgd.zza(obj, j, obj3);
            zzb2 = obj3;
        } else if (zzls.isAssignableFrom(zzb2.getClass())) {
            ArrayList arrayList = new ArrayList(zzb2.size() + size);
            arrayList.addAll(zzb2);
            zzgd.zza(obj, j, (Object) arrayList);
            zzb2 = arrayList;
        } else if (zzb2 instanceof zzga) {
            zzca zzdz = new zzdz(zzb2.size() + size);
            zzdz.addAll((zzga) zzb2);
            zzgd.zza(obj, j, (Object) zzdz);
            zzb2 = zzdz;
        } else if ((zzb2 instanceof zzfb) && (zzb2 instanceof zzdq)) {
            zzdq zzdq = (zzdq) zzb2;
            if (!zzdq.zzai()) {
                List zzj = zzdq.zzj(zzb2.size() + size);
                zzgd.zza(obj, j, (Object) zzj);
                zzb2 = zzj;
            }
        }
        int size2 = zzb2.size();
        int size3 = zzb.size();
        if (size2 > 0 && size3 > 0) {
            zzb2.addAll(zzb);
        }
        if (size2 > 0) {
            zzb = zzb2;
        }
        zzgd.zza(obj, j, zzb);
    }

    private static <E> List<E> zzb(Object obj, long j) {
        return (List) zzgd.zzo(obj, j);
    }
}
