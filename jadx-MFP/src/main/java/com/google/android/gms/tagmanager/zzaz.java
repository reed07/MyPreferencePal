package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.List;
import java.util.Map;

final class zzaz extends zzgh {
    private static final String ID = zza.DATA_LAYER_WRITE.toString();
    private static final String VALUE = zzb.VALUE.toString();
    private static final String zzbbv = zzb.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzazr;

    public zzaz(DataLayer dataLayer) {
        super(ID, VALUE);
        this.zzazr = dataLayer;
    }

    public final void zze(Map<String, zzp> map) {
        zzp zzp = (zzp) map.get(VALUE);
        if (!(zzp == null || zzp == zzgj.zzqk())) {
            Object zzh = zzgj.zzh(zzp);
            if (zzh instanceof List) {
                for (Object next : (List) zzh) {
                    if (next instanceof Map) {
                        this.zzazr.push((Map) next);
                    }
                }
            }
        }
        zzp zzp2 = (zzp) map.get(zzbbv);
        if (zzp2 != null && zzp2 != zzgj.zzqk()) {
            String zzc = zzgj.zzc(zzp2);
            if (zzc != zzgj.zzqp()) {
                this.zzazr.zzdh(zzc);
            }
        }
    }
}
