package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzcz {
    boolean zzadg = true;
    String zzadi;
    String zzapk;
    String zzapl;
    Boolean zzaqe;
    zzan zzaqz;
    final Context zzri;

    @VisibleForTesting
    public zzcz(Context context, zzan zzan) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzri = applicationContext;
        if (zzan != null) {
            this.zzaqz = zzan;
            this.zzadi = zzan.zzadi;
            this.zzapk = zzan.origin;
            this.zzapl = zzan.zzadh;
            this.zzadg = zzan.zzadg;
            if (zzan.zzadj != null) {
                this.zzaqe = Boolean.valueOf(zzan.zzadj.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
