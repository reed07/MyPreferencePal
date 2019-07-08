package com.google.android.gms.internal.icing;

import java.util.Map.Entry;

final class zzdw<K> implements Entry<K, Object> {
    private Entry<K, zzdu> zzli;

    private zzdw(Entry<K, zzdu> entry) {
        this.zzli = entry;
    }

    public final K getKey() {
        return this.zzli.getKey();
    }

    public final Object getValue() {
        if (((zzdu) this.zzli.getValue()) == null) {
            return null;
        }
        return zzdu.zzce();
    }

    public final zzdu zzcf() {
        return (zzdu) this.zzli.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzeq) {
            return ((zzdu) this.zzli.getValue()).zzh((zzeq) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
