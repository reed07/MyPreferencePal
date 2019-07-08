package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

abstract class zzdy extends zzef {
    public zzdy(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzgi zzgi, zzgi zzgi2, Map<String, zzp> map);

    /* access modifiers changed from: protected */
    public final boolean zza(zzp zzp, zzp zzp2, Map<String, zzp> map) {
        zzgi zzd = zzgj.zzd(zzp);
        zzgi zzd2 = zzgj.zzd(zzp2);
        if (zzd == zzgj.zzqo() || zzd2 == zzgj.zzqo()) {
            return false;
        }
        return zza(zzd, zzd2, map);
    }
}
