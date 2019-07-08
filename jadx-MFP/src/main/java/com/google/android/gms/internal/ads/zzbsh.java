package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzbsh implements zzbsg {
    zzbsh() {
    }

    public final Map<?, ?> zzw(Object obj) {
        return (zzbsf) obj;
    }

    public final zzbse<?, ?> zzab(Object obj) {
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzx(Object obj) {
        return (zzbsf) obj;
    }

    public final boolean zzy(Object obj) {
        return !((zzbsf) obj).isMutable();
    }

    public final Object zzz(Object obj) {
        ((zzbsf) obj).zzakj();
        return obj;
    }

    public final Object zzaa(Object obj) {
        return zzbsf.zzant().zzanu();
    }

    public final Object zzc(Object obj, Object obj2) {
        zzbsf zzbsf = (zzbsf) obj;
        zzbsf zzbsf2 = (zzbsf) obj2;
        if (!zzbsf2.isEmpty()) {
            if (!zzbsf.isMutable()) {
                zzbsf = zzbsf.zzanu();
            }
            zzbsf.zza(zzbsf2);
        }
        return zzbsf;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzbsf zzbsf = (zzbsf) obj;
        if (zzbsf.isEmpty()) {
            return 0;
        }
        Iterator it = zzbsf.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Entry entry = (Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
