package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzee extends zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ String zzaeb;
    private final /* synthetic */ String zzaec;

    zzee(zzea zzea, Activity activity, String str, String str2) {
        this.zzadv = zzea;
        this.val$activity = activity;
        this.zzaeb = str;
        this.zzaec = str2;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.setCurrentScreen(ObjectWrapper.wrap(this.val$activity), this.zzaeb, this.zzaec, this.timestamp);
    }
}
