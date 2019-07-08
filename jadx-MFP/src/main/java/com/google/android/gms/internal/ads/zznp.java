package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

public abstract class zznp {
    public final zzfs zzaad;
    private final String zzbdc;
    public final String zzbde;
    private final long zzbdm;
    public final long zzbdn;
    public final List<zznm> zzbdo;
    private final zzno zzbdp;

    private zznp(String str, long j, zzfs zzfs, String str2, zznt zznt, List<zznm> list) {
        List<zznm> list2;
        this.zzbdc = str;
        this.zzbdm = j;
        this.zzaad = zzfs;
        this.zzbde = str2;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.zzbdo = list2;
        this.zzbdp = zznt.zza(this);
        this.zzbdn = zzqe.zza(zznt.zzbdw, 1000000, zznt.zzcr);
    }

    public abstract String zzf();

    public abstract zzno zzgi();

    public abstract zznd zzgj();

    public final zzno zzgh() {
        return this.zzbdp;
    }
}
