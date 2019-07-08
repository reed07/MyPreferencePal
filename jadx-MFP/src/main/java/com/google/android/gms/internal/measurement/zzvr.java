package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzvr implements zzvq {
    zzvr() {
    }

    public final Map<?, ?> zzac(Object obj) {
        return (zzvp) obj;
    }

    public final zzvo<?, ?> zzah(Object obj) {
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzad(Object obj) {
        return (zzvp) obj;
    }

    public final boolean zzae(Object obj) {
        return !((zzvp) obj).isMutable();
    }

    public final Object zzaf(Object obj) {
        ((zzvp) obj).zzsw();
        return obj;
    }

    public final Object zzag(Object obj) {
        return zzvp.zzxg().zzxh();
    }

    public final Object zzc(Object obj, Object obj2) {
        zzvp zzvp = (zzvp) obj;
        zzvp zzvp2 = (zzvp) obj2;
        if (!zzvp2.isEmpty()) {
            if (!zzvp.isMutable()) {
                zzvp = zzvp.zzxh();
            }
            zzvp.zza(zzvp2);
        }
        return zzvp;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzvp zzvp = (zzvp) obj;
        if (zzvp.isEmpty()) {
            return 0;
        }
        Iterator it = zzvp.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Entry entry = (Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
