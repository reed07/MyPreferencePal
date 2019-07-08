package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzwm extends zzwt<zzxl> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzwf zzckz;
    private final /* synthetic */ String zzcla;
    private final /* synthetic */ zzwj zzclc;

    zzwm(zzwj zzwj, Context context, zzwf zzwf, String str) {
        this.zzclc = zzwj;
        this.val$context = context;
        this.zzckz = zzwf;
        this.zzcla = str;
    }

    public final /* synthetic */ Object zzpq() {
        zzwj.zza(this.val$context, "search");
        return new zzzl();
    }

    public final /* synthetic */ Object zzpr() throws RemoteException {
        return this.zzclc.zzckr.zza(this.val$context, this.zzckz, this.zzcla, null, 3);
    }

    public final /* synthetic */ Object zza(zzxw zzxw) throws RemoteException {
        return zzxw.createSearchAdManager(ObjectWrapper.wrap(this.val$context), this.zzckz, this.zzcla, 14300000);
    }
}
