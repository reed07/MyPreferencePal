package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzge extends zzbq {
    private static final String ID = zza.TIME.toString();

    public zzge() {
        super(ID, new String[0]);
    }

    public final boolean zznk() {
        return false;
    }

    public final zzp zzc(Map<String, zzp> map) {
        return zzgj.zzj(Long.valueOf(System.currentTimeMillis()));
    }
}
