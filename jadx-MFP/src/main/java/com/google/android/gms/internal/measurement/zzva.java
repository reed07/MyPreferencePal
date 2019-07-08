package com.google.android.gms.internal.measurement;

import java.util.Map.Entry;

final class zzva<K> implements Entry<K, Object> {
    private Entry<K, zzuy> zzbzu;

    private zzva(Entry<K, zzuy> entry) {
        this.zzbzu = entry;
    }

    public final K getKey() {
        return this.zzbzu.getKey();
    }

    public final Object getValue() {
        if (((zzuy) this.zzbzu.getValue()) == null) {
            return null;
        }
        return zzuy.zzwz();
    }

    public final zzuy zzxa() {
        return (zzuy) this.zzbzu.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzvv) {
            return ((zzuy) this.zzbzu.getValue()).zzi((zzvv) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
