package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzbrr<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zzfrk;

    public zzbrr(Iterator<Entry<K, Object>> it) {
        this.zzfrk = it;
    }

    public final boolean hasNext() {
        return this.zzfrk.hasNext();
    }

    public final void remove() {
        this.zzfrk.remove();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zzfrk.next();
        return entry.getValue() instanceof zzbro ? new zzbrq(entry) : entry;
    }
}
