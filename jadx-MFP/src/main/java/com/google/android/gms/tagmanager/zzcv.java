package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzcv extends zzbq {
    private static final String ID = zza.INSTALL_REFERRER.toString();
    private static final String zzazi = zzb.COMPONENT.toString();
    private final Context zzri;

    public zzcv(Context context) {
        super(ID, new String[0]);
        this.zzri = context;
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        String zzg = zzcw.zzg(this.zzri, ((zzp) map.get(zzazi)) != null ? zzgj.zzc((zzp) map.get(zzazi)) : null);
        return zzg != null ? zzgj.zzj(zzg) : zzgj.zzqq();
    }
}
