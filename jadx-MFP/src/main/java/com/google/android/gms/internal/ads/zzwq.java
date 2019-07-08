package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzwq extends zzwt<zzadf> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzwj zzclc;
    private final /* synthetic */ FrameLayout zzcld;
    private final /* synthetic */ FrameLayout zzcle;

    zzwq(zzwj zzwj, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        this.zzclc = zzwj;
        this.zzcld = frameLayout;
        this.zzcle = frameLayout2;
        this.val$context = context;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpq() {
        zzwj.zza(this.val$context, "native_ad_view_delegate");
        return new zzzo();
    }

    public final /* synthetic */ Object zzpr() throws RemoteException {
        return this.zzclc.zzcku.zzb(this.val$context, this.zzcld, this.zzcle);
    }

    public final /* synthetic */ Object zza(zzxw zzxw) throws RemoteException {
        return zzxw.createNativeAdViewDelegate(ObjectWrapper.wrap(this.zzcld), ObjectWrapper.wrap(this.zzcle));
    }
}
