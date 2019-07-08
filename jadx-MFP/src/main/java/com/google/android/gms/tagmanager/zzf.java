package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzf extends zzbq {
    private static final String ID = zza.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza zzazh;

    public zzf(Context context) {
        this(zza.zzo(context));
    }

    public final boolean zznk() {
        return false;
    }

    @VisibleForTesting
    private zzf(zza zza) {
        super(ID, new String[0]);
        this.zzazh = zza;
    }

    public final zzp zzc(Map<String, zzp> map) {
        return zzgj.zzj(Boolean.valueOf(!this.zzazh.isLimitAdTrackingEnabled()));
    }
}
