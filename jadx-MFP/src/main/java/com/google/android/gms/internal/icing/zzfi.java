package com.google.android.gms.internal.icing;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzfi<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzgz;
    private final int zzng;
    /* access modifiers changed from: private */
    public List<zzfp> zznh;
    /* access modifiers changed from: private */
    public Map<K, V> zzni;
    private volatile zzfr zznj;
    /* access modifiers changed from: private */
    public Map<K, V> zznk;
    private volatile zzfl zznl;

    static <FieldDescriptorType extends zzde<FieldDescriptorType>> zzfi<FieldDescriptorType, Object> zzai(int i) {
        return new zzfj(i);
    }

    private zzfi(int i) {
        this.zzng = i;
        this.zznh = Collections.emptyList();
        this.zzni = Collections.emptyMap();
        this.zznk = Collections.emptyMap();
    }

    public void zzaj() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzgz) {
            if (this.zzni.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzni);
            }
            this.zzni = map;
            if (this.zznk.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zznk);
            }
            this.zznk = map2;
            this.zzgz = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzgz;
    }

    public final int zzdh() {
        return this.zznh.size();
    }

    public final Entry<K, V> zzaj(int i) {
        return (Entry) this.zznh.get(i);
    }

    public final Iterable<Entry<K, V>> zzdi() {
        if (this.zzni.isEmpty()) {
            return zzfm.zzdn();
        }
        return this.zzni.entrySet();
    }

    public int size() {
        return this.zznh.size() + this.zzni.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((K) comparable) >= 0 || this.zzni.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        if (zza >= 0) {
            return ((zzfp) this.zznh.get(zza)).getValue();
        }
        return this.zzni.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzdk();
        int zza = zza(k);
        if (zza >= 0) {
            return ((zzfp) this.zznh.get(zza)).setValue(v);
        }
        zzdk();
        if (this.zznh.isEmpty() && !(this.zznh instanceof ArrayList)) {
            this.zznh = new ArrayList(this.zzng);
        }
        int i = -(zza + 1);
        if (i >= this.zzng) {
            return zzdl().put(k, v);
        }
        int size = this.zznh.size();
        int i2 = this.zzng;
        if (size == i2) {
            zzfp zzfp = (zzfp) this.zznh.remove(i2 - 1);
            zzdl().put((Comparable) zzfp.getKey(), zzfp.getValue());
        }
        this.zznh.add(i, new zzfp(this, k, v));
        return null;
    }

    public void clear() {
        zzdk();
        if (!this.zznh.isEmpty()) {
            this.zznh.clear();
        }
        if (!this.zzni.isEmpty()) {
            this.zzni.clear();
        }
    }

    public V remove(Object obj) {
        zzdk();
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        if (zza >= 0) {
            return zzak(zza);
        }
        if (this.zzni.isEmpty()) {
            return null;
        }
        return this.zzni.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzak(int i) {
        zzdk();
        V value = ((zzfp) this.zznh.remove(i)).getValue();
        if (!this.zzni.isEmpty()) {
            Iterator it = zzdl().entrySet().iterator();
            this.zznh.add(new zzfp(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int size = this.zznh.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) ((zzfp) this.zznh.get(size)).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) ((zzfp) this.zznh.get(i2)).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.zznj == null) {
            this.zznj = new zzfr(this, null);
        }
        return this.zznj;
    }

    /* access modifiers changed from: 0000 */
    public final Set<Entry<K, V>> zzdj() {
        if (this.zznl == null) {
            this.zznl = new zzfl(this, null);
        }
        return this.zznl;
    }

    /* access modifiers changed from: private */
    public final void zzdk() {
        if (this.zzgz) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzdl() {
        zzdk();
        if (this.zzni.isEmpty() && !(this.zzni instanceof TreeMap)) {
            this.zzni = new TreeMap();
            this.zznk = ((TreeMap) this.zzni).descendingMap();
        }
        return (SortedMap) this.zzni;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfi)) {
            return super.equals(obj);
        }
        zzfi zzfi = (zzfi) obj;
        int size = size();
        if (size != zzfi.size()) {
            return false;
        }
        int zzdh = zzdh();
        if (zzdh != zzfi.zzdh()) {
            return entrySet().equals(zzfi.entrySet());
        }
        for (int i = 0; i < zzdh; i++) {
            if (!zzaj(i).equals(zzfi.zzaj(i))) {
                return false;
            }
        }
        if (zzdh != size) {
            return this.zzni.equals(zzfi.zzni);
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzdh(); i2++) {
            i += ((zzfp) this.zznh.get(i2)).hashCode();
        }
        return this.zzni.size() > 0 ? i + this.zzni.hashCode() : i;
    }

    /* synthetic */ zzfi(int i, zzfj zzfj) {
        this(i);
    }
}
