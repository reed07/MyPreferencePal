package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzwp extends zzwt<zzyc> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzwj zzclc;

    zzwp(zzwj zzwj, Context context) {
        this.zzclc = zzwj;
        this.val$context = context;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpq() {
        zzwj.zza(this.val$context, "mobile_ads_settings");
        return new zzzn();
    }

    public final /* synthetic */ Object zzpr() throws RemoteException {
        return this.zzclc.zzckt.zzi(this.val$context);
    }

    public final /* synthetic */ Object zza(zzxw zzxw) throws RemoteException {
        return zzxw.getMobileAdsSettingsManagerWithClientJarVersion(ObjectWrapper.wrap(this.val$context), 14300000);
    }
}
