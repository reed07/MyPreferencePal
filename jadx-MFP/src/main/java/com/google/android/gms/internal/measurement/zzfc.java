package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzfc extends zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zza zzaea;
    private final /* synthetic */ zze zzafa;

    zzfc(zze zze, Activity activity, zza zza) {
        this.zzafa = zze;
        this.val$activity = activity;
        this.zzaea = zza;
        super(zzea.this);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        zzea.this.zzadr.onActivitySaveInstanceState(ObjectWrapper.wrap(this.val$activity), this.zzaea, this.zzaev);
    }
}
