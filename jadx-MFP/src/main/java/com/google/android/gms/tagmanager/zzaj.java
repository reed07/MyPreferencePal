package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

@VisibleForTesting
final class zzaj extends zzbq {
    private static final String ID = zza.CONTAINER_VERSION.toString();
    private final String version;

    public zzaj(String str) {
        super(ID, new String[0]);
        this.version = str;
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        String str = this.version;
        return str == null ? zzgj.zzqq() : zzgj.zzj(str);
    }
}
