package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzark
public final class zzabc implements CustomRenderedAd {
    private final zzabd zzczr;

    public zzabc(zzabd zzabd) {
        this.zzczr = zzabd;
    }

    public final String getBaseUrl() {
        try {
            return this.zzczr.zzrk();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final String getContent() {
        try {
            return this.zzczr.getContent();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final void onAdRendered(View view) {
        try {
            this.zzczr.zzh(view != null ? ObjectWrapper.wrap(view) : null);
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void recordClick() {
        try {
            this.zzczr.recordClick();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzczr.recordImpression();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }
}
