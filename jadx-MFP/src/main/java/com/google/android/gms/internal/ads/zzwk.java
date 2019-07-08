package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzwk extends zzwt<zzxl> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzwf zzckz;
    private final /* synthetic */ String zzcla;
    private final /* synthetic */ zzalg zzclb;
    private final /* synthetic */ zzwj zzclc;

    zzwk(zzwj zzwj, Context context, zzwf zzwf, String str, zzalg zzalg) {
        this.zzclc = zzwj;
        this.val$context = context;
        this.zzckz = zzwf;
        this.zzcla = str;
        this.zzclb = zzalg;
    }

    public final /* synthetic */ Object zzpq() {
        zzwj.zza(this.val$context, "banner");
        return new zzzl();
    }

    public final /* synthetic */ Object zzpr() throws RemoteException {
        return this.zzclc.zzckr.zza(this.val$context, this.zzckz, this.zzcla, this.zzclb, 1);
    }

    public final /* synthetic */ Object zza(zzxw zzxw) throws RemoteException {
        return zzxw.createBannerAdManager(ObjectWrapper.wrap(this.val$context), this.zzckz, this.zzcla, this.zzclb, 14300000);
    }
}
