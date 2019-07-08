package com.google.android.gms.internal.measurement;

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

class zzwo<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzbqa;
    private final int zzcbt;
    /* access modifiers changed from: private */
    public List<zzwv> zzcbu;
    /* access modifiers changed from: private */
    public Map<K, V> zzcbv;
    private volatile zzwx zzcbw;
    /* access modifiers changed from: private */
    public Map<K, V> zzcbx;
    private volatile zzwr zzcby;

    static <FieldDescriptorType extends zzuh<FieldDescriptorType>> zzwo<FieldDescriptorType, Object> zzbw(int i) {
        return new zzwp(i);
    }

    private zzwo(int i) {
        this.zzcbt = i;
        this.zzcbu = Collections.emptyList();
        this.zzcbv = Collections.emptyMap();
        this.zzcbx = Collections.emptyMap();
    }

    public void zzsw() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzbqa) {
            if (this.zzcbv.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzcbv);
            }
            this.zzcbv = map;
            if (this.zzcbx.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzcbx);
            }
            this.zzcbx = map2;
            this.zzbqa = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzbqa;
    }

    public final int zzyc() {
        return this.zzcbu.size();
    }

    public final Entry<K, V> zzbx(int i) {
        return (Entry) this.zzcbu.get(i);
    }

    public final Iterable<Entry<K, V>> zzyd() {
        if (this.zzcbv.isEmpty()) {
            return zzws.zzyi();
        }
        return this.zzcbv.entrySet();
    }

    public int size() {
        return this.zzcbu.size() + this.zzcbv.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((K) comparable) >= 0 || this.zzcbv.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        if (zza >= 0) {
            return ((zzwv) this.zzcbu.get(zza)).getValue();
        }
        return this.zzcbv.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzyf();
        int zza = zza(k);
        if (zza >= 0) {
            return ((zzwv) this.zzcbu.get(zza)).setValue(v);
        }
        zzyf();
        if (this.zzcbu.isEmpty() && !(this.zzcbu instanceof ArrayList)) {
            this.zzcbu = new ArrayList(this.zzcbt);
        }
        int i = -(zza + 1);
        if (i >= this.zzcbt) {
            return zzyg().put(k, v);
        }
        int size = this.zzcbu.size();
        int i2 = this.zzcbt;
        if (size == i2) {
            zzwv zzwv = (zzwv) this.zzcbu.remove(i2 - 1);
            zzyg().put((Comparable) zzwv.getKey(), zzwv.getValue());
        }
        this.zzcbu.add(i, new zzwv(this, k, v));
        return null;
    }

    public void clear() {
        zzyf();
        if (!this.zzcbu.isEmpty()) {
            this.zzcbu.clear();
        }
        if (!this.zzcbv.isEmpty()) {
            this.zzcbv.clear();
        }
    }

    public V remove(Object obj) {
        zzyf();
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        if (zza >= 0) {
            return zzby(zza);
        }
        if (this.zzcbv.isEmpty()) {
            return null;
        }
        return this.zzcbv.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzby(int i) {
        zzyf();
        V value = ((zzwv) this.zzcbu.remove(i)).getValue();
        if (!this.zzcbv.isEmpty()) {
            Iterator it = zzyg().entrySet().iterator();
            this.zzcbu.add(new zzwv(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int size = this.zzcbu.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) ((zzwv) this.zzcbu.get(size)).getKey());
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
            int compareTo2 = k.compareTo((Comparable) ((zzwv) this.zzcbu.get(i2)).getKey());
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
        if (this.zzcbw == null) {
            this.zzcbw = new zzwx(this, null);
        }
        return this.zzcbw;
    }

    /* access modifiers changed from: 0000 */
    public final Set<Entry<K, V>> zzye() {
        if (this.zzcby == null) {
            this.zzcby = new zzwr(this, null);
        }
        return this.zzcby;
    }

    /* access modifiers changed from: private */
    public final void zzyf() {
        if (this.zzbqa) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzyg() {
        zzyf();
        if (this.zzcbv.isEmpty() && !(this.zzcbv instanceof TreeMap)) {
            this.zzcbv = new TreeMap();
            this.zzcbx = ((TreeMap) this.zzcbv).descendingMap();
        }
        return (SortedMap) this.zzcbv;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzwo)) {
            return super.equals(obj);
        }
        zzwo zzwo = (zzwo) obj;
        int size = size();
        if (size != zzwo.size()) {
            return false;
        }
        int zzyc = zzyc();
        if (zzyc != zzwo.zzyc()) {
            return entrySet().equals(zzwo.entrySet());
        }
        for (int i = 0; i < zzyc; i++) {
            if (!zzbx(i).equals(zzwo.zzbx(i))) {
                return false;
            }
        }
        if (zzyc != size) {
            return this.zzcbv.equals(zzwo.zzcbv);
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzyc(); i2++) {
            i += ((zzwv) this.zzcbu.get(i2)).hashCode();
        }
        return this.zzcbv.size() > 0 ? i + this.zzcbv.hashCode() : i;
    }

    /* synthetic */ zzwo(int i, zzwp zzwp) {
        this(i);
    }
}
