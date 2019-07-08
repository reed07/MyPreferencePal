package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzbgg;

@zzark
@VisibleForTesting
public final class zzi {
    public final int index;
    public final ViewGroup parent;
    public final LayoutParams zzdrp;
    public final Context zzsp;

    public zzi(zzbgg zzbgg) throws zzg {
        this.zzdrp = zzbgg.getLayoutParams();
        ViewParent parent2 = zzbgg.getParent();
        this.zzsp = zzbgg.zzadg();
        if (parent2 == null || !(parent2 instanceof ViewGroup)) {
            throw new zzg("Could not get the parent of the WebView for an overlay.");
        }
        this.parent = (ViewGroup) parent2;
        this.index = this.parent.indexOfChild(zzbgg.getView());
        this.parent.removeView(zzbgg.getView());
        zzbgg.zzav(true);
    }
}
