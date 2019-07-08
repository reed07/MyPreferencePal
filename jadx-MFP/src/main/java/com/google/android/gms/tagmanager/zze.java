package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zze extends zzbq {
    private static final String ID = zza.ADVERTISER_ID.toString();
    private final zza zzazh;

    public zze(Context context) {
        this(zza.zzo(context));
    }

    public final boolean zznk() {
        return false;
    }

    @VisibleForTesting
    private zze(zza zza) {
        super(ID, new String[0]);
        this.zzazh = zza;
        this.zzazh.zzne();
    }

    public final zzp zzc(Map<String, zzp> map) {
        String zzne = this.zzazh.zzne();
        return zzne == null ? zzgj.zzqq() : zzgj.zzj(zzne);
    }
}
