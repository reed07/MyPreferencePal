package com.google.android.gms.internal.ads;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class zzbto extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzbtf zzftq;

    private zzbto(zzbtf zzbtf) {
        this.zzftq = zzbtf;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new zzbtn(this.zzftq, null);
    }

    public int size() {
        return this.zzftq.size();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zzftq.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzftq.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzftq.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzftq.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzbto(zzbtf zzbtf, zzbtg zzbtg) {
        this(zzbtf);
    }
}
