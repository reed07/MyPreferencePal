package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzgl extends zzbq {
    private static final String ID = zza.UPPERCASE_STRING.toString();
    private static final String zzbcf = zzb.ARG0.toString();

    public zzgl() {
        super(ID, zzbcf);
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        return zzgj.zzj(zzgj.zzc((zzp) map.get(zzbcf)).toUpperCase());
    }
}
