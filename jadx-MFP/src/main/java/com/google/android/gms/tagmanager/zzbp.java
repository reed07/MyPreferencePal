package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

@VisibleForTesting
final class zzbp extends zzbq {
    private static final String ID = zza.EVENT.toString();
    private final zzfb zzazs;

    public zzbp(zzfb zzfb) {
        super(ID, new String[0]);
        this.zzazs = zzfb;
    }

    public final boolean zznk() {
        return false;
    }

    public final zzp zzc(Map<String, zzp> map) {
        String zzpt = this.zzazs.zzpt();
        if (zzpt == null) {
            return zzgj.zzqq();
        }
        return zzgj.zzj(zzpt);
    }
}
