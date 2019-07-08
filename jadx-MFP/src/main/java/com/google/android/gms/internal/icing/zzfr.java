package com.google.android.gms.internal.icing;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class zzfr extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzfi zznn;

    private zzfr(zzfi zzfi) {
        this.zznn = zzfi;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new zzfq(this.zznn, null);
    }

    public int size() {
        return this.zznn.size();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zznn.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zznn.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zznn.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zznn.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzfr(zzfi zzfi, zzfj zzfj) {
        this(zzfi);
    }
}
