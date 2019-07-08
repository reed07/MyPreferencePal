package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzdz extends zzca<String> implements zzea, RandomAccess {
    private static final zzdz zzln;
    private static final zzea zzlo = zzln;
    private final List<Object> zzlp;

    public zzdz() {
        this(10);
    }

    public zzdz(int i) {
        this(new ArrayList<>(i));
    }

    private zzdz(ArrayList<Object> arrayList) {
        this.zzlp = arrayList;
    }

    public final int size() {
        return this.zzlp.size();
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzak();
        if (collection instanceof zzea) {
            collection = ((zzea) collection).zzcg();
        }
        boolean addAll = this.zzlp.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzak();
        this.zzlp.clear();
        this.modCount++;
    }

    public final void zzc(zzce zzce) {
        zzak();
        this.zzlp.add(zzce);
        this.modCount++;
    }

    public final Object zzad(int i) {
        return this.zzlp.get(i);
    }

    private static String zzg(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzce) {
            return ((zzce) obj).zzao();
        }
        return zzdl.zze((byte[]) obj);
    }

    public final List<?> zzcg() {
        return Collections.unmodifiableList(this.zzlp);
    }

    public final zzea zzch() {
        return zzai() ? new zzga(this) : this;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        String str = (String) obj;
        zzak();
        return zzg(this.zzlp.set(i, str));
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
        zzak();
        Object remove = this.zzlp.remove(i);
        this.modCount++;
        return zzg(remove);
    }

    public final /* bridge */ /* synthetic */ boolean zzai() {
        return super.zzai();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        String str = (String) obj;
        zzak();
        this.zzlp.add(i, str);
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzlp);
            return new zzdz(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzlp.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzce) {
            zzce zzce = (zzce) obj;
            String zzao = zzce.zzao();
            if (zzce.zzap()) {
                this.zzlp.set(i, zzao);
            }
            return zzao;
        }
        byte[] bArr = (byte[]) obj;
        String zze = zzdl.zze(bArr);
        if (zzdl.zzd(bArr)) {
            this.zzlp.set(i, zze);
        }
        return zze;
    }

    static {
        zzdz zzdz = new zzdz();
        zzln = zzdz;
        zzdz.zzaj();
    }
}
