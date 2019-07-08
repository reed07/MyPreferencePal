package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzvb<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zzbzv;

    public zzvb(Iterator<Entry<K, Object>> it) {
        this.zzbzv = it;
    }

    public final boolean hasNext() {
        return this.zzbzv.hasNext();
    }

    public final void remove() {
        this.zzbzv.remove();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zzbzv.next();
        return entry.getValue() instanceof zzuy ? new zzva(entry) : entry;
    }
}
