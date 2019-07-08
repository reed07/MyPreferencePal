package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzags extends zzxu {
    private final /* synthetic */ zzagj zzdgq;

    zzags(zzagj zzagj) {
        this.zzdgq = zzagj;
    }

    public final void onAppEvent(String str, String str2) throws RemoteException {
        this.zzdgq.zzbnj.add(new zzagt(this, str, str2));
    }
}
