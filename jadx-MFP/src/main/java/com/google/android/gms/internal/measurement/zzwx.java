package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class zzwx extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzwo zzcca;

    private zzwx(zzwo zzwo) {
        this.zzcca = zzwo;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new zzww(this.zzcca, null);
    }

    public int size() {
        return this.zzcca.size();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zzcca.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzcca.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzcca.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzcca.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzwx(zzwo zzwo, zzwp zzwp) {
        this(zzwo);
    }
}
