package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzvh extends zzvf {
    private static final Class<?> zzcae = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzvh() {
        super();
    }

    /* access modifiers changed from: 0000 */
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzxj.zzp(obj, j);
        if (list instanceof zzve) {
            obj2 = ((zzve) list).zzxc();
        } else if (!zzcae.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzwg) || !(list instanceof zzuu)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzuu zzuu = (zzuu) list;
                if (zzuu.zztz()) {
                    zzuu.zzsw();
                }
                return;
            }
        } else {
            return;
        }
        zzxj.zza(obj, j, obj2);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> list;
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            if (zzc instanceof zzve) {
                list = new zzvd<>(i);
            } else if (!(zzc instanceof zzwg) || !(zzc instanceof zzuu)) {
                list = new ArrayList<>(i);
            } else {
                list = ((zzuu) zzc).zzal(i);
            }
            zzxj.zza(obj, j, (Object) list);
            return list;
        } else if (zzcae.isAssignableFrom(zzc.getClass())) {
            ArrayList arrayList = new ArrayList(zzc.size() + i);
            arrayList.addAll(zzc);
            zzxj.zza(obj, j, (Object) arrayList);
            return arrayList;
        } else if (zzc instanceof zzxg) {
            zzvd zzvd = new zzvd(zzc.size() + i);
            zzvd.addAll((zzxg) zzc);
            zzxj.zza(obj, j, (Object) zzvd);
            return zzvd;
        } else if (!(zzc instanceof zzwg) || !(zzc instanceof zzuu)) {
            return zzc;
        } else {
            zzuu zzuu = (zzuu) zzc;
            if (zzuu.zztz()) {
                return zzc;
            }
            zzuu zzal = zzuu.zzal(zzc.size() + i);
            zzxj.zza(obj, j, (Object) zzal);
            return zzal;
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
        zzxj.zza(obj, j, (Object) zzc);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzxj.zzp(obj, j);
    }
}
