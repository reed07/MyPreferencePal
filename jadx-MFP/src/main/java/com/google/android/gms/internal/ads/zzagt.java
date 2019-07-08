package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzagt implements zzahi {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzdgs;

    zzagt(zzags zzags, String str, String str2) {
        this.val$name = str;
        this.zzdgs = str2;
    }

    public final void zzb(zzahj zzahj) throws RemoteException {
        if (zzahj.zzdgy != null) {
            zzahj.zzdgy.onAppEvent(this.val$name, this.zzdgs);
        }
    }
}
