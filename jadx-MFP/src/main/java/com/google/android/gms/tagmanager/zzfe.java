package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzri;
import com.google.android.gms.internal.measurement.zzrm;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zzfe implements zzfg {
    private final /* synthetic */ Map zzbfn;
    private final /* synthetic */ Map zzbfo;
    private final /* synthetic */ Map zzbfp;
    private final /* synthetic */ Map zzbfq;

    zzfe(zzfb zzfb, Map map, Map map2, Map map3, Map map4) {
        this.zzbfn = map;
        this.zzbfo = map2;
        this.zzbfp = map3;
        this.zzbfq = map4;
    }

    public final void zza(zzrm zzrm, Set<zzri> set, Set<zzri> set2, zzeq zzeq) {
        List list = (List) this.zzbfn.get(zzrm);
        this.zzbfo.get(zzrm);
        if (list != null) {
            set.addAll(list);
            zzeq.zzpc();
        }
        List list2 = (List) this.zzbfp.get(zzrm);
        this.zzbfq.get(zzrm);
        if (list2 != null) {
            set2.addAll(list2);
            zzeq.zzpd();
        }
    }
}
