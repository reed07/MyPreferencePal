package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.icing.zzab;
import com.google.android.gms.internal.icing.zzai.zzd;

final class zzp extends zzr {
    private final /* synthetic */ zza[] zzff;

    zzp(zzo zzo, zza[] zzaArr) {
        this.zzff = zzaArr;
        super(null);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzab zzab) throws RemoteException {
        zzab.zza(new zzd(this), this.zzff);
    }
}
