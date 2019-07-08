package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

@VisibleForTesting
final class zzas extends zzbq {
    private static final String ID = zza.CUSTOM_VAR.toString();
    private static final String NAME = zzb.NAME.toString();
    private static final String zzbbk = zzb.DEFAULT_VALUE.toString();
    private final DataLayer zzazr;

    public zzas(DataLayer dataLayer) {
        super(ID, NAME);
        this.zzazr = dataLayer;
    }

    public final boolean zznk() {
        return false;
    }

    public final zzp zzc(Map<String, zzp> map) {
        Object obj = this.zzazr.get(zzgj.zzc((zzp) map.get(NAME)));
        if (obj != null) {
            return zzgj.zzj(obj);
        }
        zzp zzp = (zzp) map.get(zzbbk);
        if (zzp != null) {
            return zzp;
        }
        return zzgj.zzqq();
    }
}
