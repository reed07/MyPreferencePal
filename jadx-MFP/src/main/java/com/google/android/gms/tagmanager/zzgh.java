package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

abstract class zzgh extends zzbq {
    public zzgh(String str, String... strArr) {
        super(str, strArr);
    }

    public abstract void zze(Map<String, zzp> map);

    public boolean zznk() {
        return false;
    }

    public zzp zzc(Map<String, zzp> map) {
        zze(map);
        return zzgj.zzqq();
    }
}
