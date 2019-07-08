package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzbrt extends zzbpo<String> implements zzbru, RandomAccess {
    private static final zzbrt zzfro;
    private static final zzbru zzfrp = zzfro;
    private final List<Object> zzfrq;

    public zzbrt() {
        this(10);
    }

    public zzbrt(int i) {
        this(new ArrayList<>(i));
    }

    private zzbrt(ArrayList<Object> arrayList) {
        this.zzfrq = arrayList;
    }

    public final int size() {
        return this.zzfrq.size();
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzakk();
        if (collection instanceof zzbru) {
            collection = ((zzbru) collection).zzano();
        }
        boolean addAll = this.zzfrq.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzakk();
        this.zzfrq.clear();
        this.modCount++;
    }

    public final void zzap(zzbpu zzbpu) {
        zzakk();
        this.zzfrq.add(zzbpu);
        this.modCount++;
    }

    public final Object zzfp(int i) {
        return this.zzfrq.get(i);
    }

    private static String zzu(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbpu) {
            return ((zzbpu) obj).zzakn();
        }
        return zzbrf.zzw((byte[]) obj);
    }

    public final List<?> zzano() {
        return Collections.unmodifiableList(this.zzfrq);
    }

    public final zzbru zzanp() {
        return zzaki() ? new zzbtx(this) : this;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        String str = (String) obj;
        zzakk();
        return zzu(this.zzfrq.set(i, str));
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* synthetic */ Object remove(int i) {
        zzakk();
        Object remove = this.zzfrq.remove(i);
        this.modCount++;
        return zzu(remove);
    }

    public final /* bridge */ /* synthetic */ boolean zzaki() {
        return super.zzaki();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        String str = (String) obj;
        zzakk();
        this.zzfrq.add(i, str);
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ zzbrk zzel(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzfrq);
            return new zzbrt(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzfrq.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbpu) {
            zzbpu zzbpu = (zzbpu) obj;
            String zzakn = zzbpu.zzakn();
            if (zzbpu.zzako()) {
                this.zzfrq.set(i, zzakn);
            }
            return zzakn;
        }
        byte[] bArr = (byte[]) obj;
        String zzw = zzbrf.zzw(bArr);
        if (zzbrf.zzv(bArr)) {
            this.zzfrq.set(i, zzw);
        }
        return zzw;
    }

    static {
        zzbrt zzbrt = new zzbrt();
        zzfro = zzbrt;
        zzbrt.zzakj();
    }
}
