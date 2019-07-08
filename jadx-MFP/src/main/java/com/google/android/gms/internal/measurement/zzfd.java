package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzfd extends zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zze zzafa;

    zzfd(zze zze, Activity activity) {
        this.zzafa = zze;
        this.val$activity = activity;
        super(zzea.this);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        zzea.this.zzadr.onActivityDestroyed(ObjectWrapper.wrap(this.val$activity), this.zzaev);
    }
}
