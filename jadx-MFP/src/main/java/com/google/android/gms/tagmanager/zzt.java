package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzt extends zzbq {
    private static final String ID = zza.CONSTANT.toString();
    private static final String VALUE = zzb.VALUE.toString();

    public static String zznm() {
        return ID;
    }

    public final boolean zznk() {
        return true;
    }

    public zzt() {
        super(ID, VALUE);
    }

    public static String zznn() {
        return VALUE;
    }

    public final zzp zzc(Map<String, zzp> map) {
        return (zzp) map.get(VALUE);
    }
}
