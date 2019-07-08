package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzabq extends zzagc implements OnGlobalLayoutListener, OnScrollChangedListener, zzacf {
    private zzbgg zzdaq;
    private zzacd zzdar;
    private boolean zzdas = false;
    private boolean zzdat = false;

    public zzabq(zzbgg zzbgg) {
        this.zzdaq = zzbgg;
    }

    public final String getCustomTemplateId() {
        return "";
    }

    public final String zzrv() {
        return "";
    }

    public final zzabm zzrw() {
        return null;
    }

    public final void zzb(zzacd zzacd) {
        this.zzdar = zzacd;
    }

    public final View zzrx() {
        zzbgg zzbgg = this.zzdaq;
        if (zzbgg == null) {
            return null;
        }
        return zzbgg.getView();
    }

    public final void onGlobalLayout() {
        zzrz();
    }

    public final void onScrollChanged() {
        zzrz();
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzagd zzagd) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzdas) {
            zzaxz.e("Instream ad is destroyed already.");
            zza(zzagd, 2);
        } else if (this.zzdaq.zzabu() == null) {
            zzaxz.e("Instream internal error: can not get video controller.");
            zza(zzagd, 0);
        } else if (this.zzdat) {
            zzaxz.e("Instream ad should not be used again.");
            zza(zzagd, 1);
        } else {
            this.zzdat = true;
            zzry();
            ((ViewGroup) ObjectWrapper.unwrap(iObjectWrapper)).addView(this.zzdaq.getView(), new LayoutParams(-1, -1));
            zzbv.zzme();
            zzbct.zza(this.zzdaq.getView(), (OnGlobalLayoutListener) this);
            zzbv.zzme();
            zzbct.zza(this.zzdaq.getView(), (OnScrollChangedListener) this);
            zzrz();
            try {
                zzagd.zztf();
            } catch (RemoteException e) {
                zzaxz.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final zzyp getVideoController() throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzdas) {
            zzaxz.e("Instream ad is destroyed already.");
            return null;
        }
        zzbgg zzbgg = this.zzdaq;
        if (zzbgg == null) {
            return null;
        }
        return zzbgg.zzabu();
    }

    public final void destroy() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (!this.zzdas) {
            zzry();
            zzacd zzacd = this.zzdar;
            if (zzacd != null) {
                zzacd.zzsr();
                this.zzdar.zzsq();
            }
            this.zzdar = null;
            this.zzdaq = null;
            this.zzdas = true;
        }
    }

    private final void zzry() {
        zzbgg zzbgg = this.zzdaq;
        if (zzbgg != null) {
            ViewParent parent = zzbgg.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView((View) this.zzdaq);
            }
        }
    }

    private final void zzrz() {
        zzacd zzacd = this.zzdar;
        if (zzacd != null) {
            zzbgg zzbgg = this.zzdaq;
            if (zzbgg != null) {
                zzacd.zzc(zzbgg.getView(), Collections.emptyMap());
            }
        }
    }

    private static void zza(zzagd zzagd, int i) {
        try {
            zzagd.zzcl(i);
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
