package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzri;
import com.google.android.gms.internal.measurement.zzrm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zzfi {
    private final Set<zzrm> zzbfj = new HashSet();
    private final Map<zzrm, List<zzri>> zzbft = new HashMap();
    private final Map<zzrm, List<zzri>> zzbfu = new HashMap();
    private final Map<zzrm, List<String>> zzbfv = new HashMap();
    private final Map<zzrm, List<String>> zzbfw = new HashMap();
    private zzri zzbfx;

    public final Set<zzrm> zzpx() {
        return this.zzbfj;
    }

    public final void zza(zzrm zzrm) {
        this.zzbfj.add(zzrm);
    }

    public final Map<zzrm, List<zzri>> zzpy() {
        return this.zzbft;
    }

    public final Map<zzrm, List<String>> zzpz() {
        return this.zzbfv;
    }

    public final Map<zzrm, List<String>> zzqa() {
        return this.zzbfw;
    }

    public final void zza(zzrm zzrm, zzri zzri) {
        List list = (List) this.zzbft.get(zzrm);
        if (list == null) {
            list = new ArrayList();
            this.zzbft.put(zzrm, list);
        }
        list.add(zzri);
    }

    public final void zza(zzrm zzrm, String str) {
        List list = (List) this.zzbfv.get(zzrm);
        if (list == null) {
            list = new ArrayList();
            this.zzbfv.put(zzrm, list);
        }
        list.add(str);
    }

    public final Map<zzrm, List<zzri>> zzqb() {
        return this.zzbfu;
    }

    public final void zzb(zzrm zzrm, zzri zzri) {
        List list = (List) this.zzbfu.get(zzrm);
        if (list == null) {
            list = new ArrayList();
            this.zzbfu.put(zzrm, list);
        }
        list.add(zzri);
    }

    public final void zzb(zzrm zzrm, String str) {
        List list = (List) this.zzbfw.get(zzrm);
        if (list == null) {
            list = new ArrayList();
            this.zzbfw.put(zzrm, list);
        }
        list.add(str);
    }

    public final zzri zzqc() {
        return this.zzbfx;
    }

    public final void zzb(zzri zzri) {
        this.zzbfx = zzri;
    }
}
