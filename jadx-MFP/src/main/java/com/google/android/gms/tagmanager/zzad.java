package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzl;
import com.google.android.gms.internal.measurement.zzo;
import com.google.android.gms.internal.measurement.zzre;

final class zzad implements zzdh<zzre> {
    private final /* synthetic */ zzy zzbau;

    private zzad(zzy zzy) {
        this.zzbau = zzy;
    }

    public final void zznx() {
    }

    public final void zzu(int i) {
        if (!this.zzbau.zzbap) {
            this.zzbau.zzao(0);
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzo zzo;
        zzre zzre = (zzre) obj;
        if (zzre.zzbqf != null) {
            zzo = zzre.zzbqf;
        } else {
            zzl zzl = zzre.zzqg;
            zzo zzo2 = new zzo();
            zzo2.zzqg = zzl;
            zzo2.zzqf = null;
            zzo2.zzqh = zzl.version;
            zzo = zzo2;
        }
        this.zzbau.zza(zzo, zzre.zzbqe, true);
    }

    /* synthetic */ zzad(zzy zzy, zzz zzz) {
        this(zzy);
    }
}
