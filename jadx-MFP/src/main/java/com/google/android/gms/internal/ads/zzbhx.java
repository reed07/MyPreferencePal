package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

final class zzbhx implements OnAttachStateChangeListener {
    private final /* synthetic */ zzawr zzeyn;
    private final /* synthetic */ zzbhu zzfay;

    zzbhx(zzbhu zzbhu, zzawr zzawr) {
        this.zzfay = zzbhu;
        this.zzeyn = zzawr;
    }

    public final void onViewDetachedFromWindow(View view) {
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzfay.zza(view, this.zzeyn, 10);
    }
}
