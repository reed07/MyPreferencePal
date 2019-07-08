package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzafg extends zzaeo {
    /* access modifiers changed from: private */
    public final OnPublisherAdViewLoadedListener zzdel;

    public zzafg(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener) {
        this.zzdel = onPublisherAdViewLoadedListener;
    }

    public final void zza(zzxl zzxl, IObjectWrapper iObjectWrapper) {
        if (zzxl != null && iObjectWrapper != null) {
            PublisherAdView publisherAdView = new PublisherAdView((Context) ObjectWrapper.unwrap(iObjectWrapper));
            AppEventListener appEventListener = null;
            try {
                if (zzxl.zzis() instanceof zzvx) {
                    zzvx zzvx = (zzvx) zzxl.zzis();
                    publisherAdView.setAdListener(zzvx != null ? zzvx.getAdListener() : null);
                }
            } catch (RemoteException e) {
                zzbbd.zzb("", e);
            }
            try {
                if (zzxl.zzir() instanceof zzwh) {
                    zzwh zzwh = (zzwh) zzxl.zzir();
                    if (zzwh != null) {
                        appEventListener = zzwh.getAppEventListener();
                    }
                    publisherAdView.setAppEventListener(appEventListener);
                }
            } catch (RemoteException e2) {
                zzbbd.zzb("", e2);
            }
            zzbat.zztu.post(new zzafh(this, publisherAdView, zzxl));
        }
    }
}
