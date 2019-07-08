package com.google.android.gms.internal.ads;

import java.util.Map.Entry;

final class zzbrq<K> implements Entry<K, Object> {
    private Entry<K, zzbro> zzfrj;

    private zzbrq(Entry<K, zzbro> entry) {
        this.zzfrj = entry;
    }

    public final K getKey() {
        return this.zzfrj.getKey();
    }

    public final Object getValue() {
        if (((zzbro) this.zzfrj.getValue()) == null) {
            return null;
        }
        return zzbro.zzanm();
    }

    public final zzbro zzann() {
        return (zzbro) this.zzfrj.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzbsl) {
            return ((zzbro) this.zzfrj.getValue()).zzl((zzbsl) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
