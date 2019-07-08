package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.mopub.common.AdType;

final class zzws extends zzwt<zzauw> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzalg zzclb;
    private final /* synthetic */ zzwj zzclc;

    zzws(zzwj zzwj, Context context, zzalg zzalg) {
        this.zzclc = zzwj;
        this.val$context = context;
        this.zzclb = zzalg;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpq() {
        zzwj.zza(this.val$context, AdType.REWARDED_VIDEO);
        return new zzzq();
    }

    public final /* synthetic */ Object zzpr() throws RemoteException {
        return this.zzclc.zzckv.zza(this.val$context, this.zzclb);
    }

    public final /* synthetic */ Object zza(zzxw zzxw) throws RemoteException {
        return zzxw.createRewardedVideoAd(ObjectWrapper.wrap(this.val$context), this.zzclb, 14300000);
    }
}
