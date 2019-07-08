package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzwl extends zzwt<zzaop> {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzwj zzclc;

    zzwl(zzwj zzwj, Activity activity) {
        this.zzclc = zzwj;
        this.val$activity = activity;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpq() {
        zzwj.zza(this.val$activity, "ad_overlay");
        return null;
    }

    public final /* synthetic */ Object zzpr() throws RemoteException {
        return this.zzclc.zzckx.zze(this.val$activity);
    }

    public final /* synthetic */ Object zza(zzxw zzxw) throws RemoteException {
        return zzxw.createAdOverlay(ObjectWrapper.wrap(this.val$activity));
    }
}
