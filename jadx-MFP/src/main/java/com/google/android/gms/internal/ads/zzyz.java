package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

@zzark
public final class zzyz {
    private VideoOptions zzblb;
    private boolean zzbli;
    private AppEventListener zzblk;
    private String zzboa;
    private zzvt zzciw;
    private AdListener zzciz;
    private AdSize[] zzckq;
    private final zzalf zzcmj;
    private final AtomicBoolean zzcmk;
    /* access modifiers changed from: private */
    public final VideoController zzcml;
    @VisibleForTesting
    private final zzww zzcmm;
    private Correlator zzcmn;
    private zzxl zzcmo;
    private OnCustomRenderedAdLoadedListener zzcmp;
    private ViewGroup zzcmq;
    private int zzcmr;
    private final zzwe zzvn;

    private static boolean zzcj(int i) {
        return i == 1;
    }

    public zzyz(ViewGroup viewGroup) {
        this(viewGroup, null, false, zzwe.zzckj, 0);
    }

    public zzyz(ViewGroup viewGroup, int i) {
        this(viewGroup, null, false, zzwe.zzckj, i);
    }

    public zzyz(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, zzwe.zzckj, 0);
    }

    public zzyz(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, int i) {
        this(viewGroup, attributeSet, false, zzwe.zzckj, i);
    }

    @VisibleForTesting
    private zzyz(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzwe zzwe, zzxl zzxl, int i) {
        this.zzcmj = new zzalf();
        this.zzcml = new VideoController();
        this.zzcmm = new zzza(this);
        this.zzcmq = viewGroup;
        this.zzvn = zzwe;
        this.zzcmo = null;
        this.zzcmk = new AtomicBoolean(false);
        this.zzcmr = i;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                zzwi zzwi = new zzwi(context, attributeSet);
                this.zzckq = zzwi.zzt(z);
                this.zzboa = zzwi.getAdUnitId();
                if (viewGroup.isInEditMode()) {
                    zzbat zzpv = zzwu.zzpv();
                    AdSize adSize = this.zzckq[0];
                    int i2 = this.zzcmr;
                    zzwf zzwf = new zzwf(context, adSize);
                    zzwf.zzckp = zzcj(i2);
                    zzpv.zza(viewGroup, zzwf, "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                zzwu.zzpv().zza(viewGroup, new zzwf(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }

    @VisibleForTesting
    private zzyz(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzwe zzwe, int i) {
        this(viewGroup, attributeSet, z, zzwe, null, i);
    }

    public final void destroy() {
        try {
            if (this.zzcmo != null) {
                this.zzcmo.destroy();
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final AdListener getAdListener() {
        return this.zzciz;
    }

    public final AdSize getAdSize() {
        try {
            if (this.zzcmo != null) {
                zzwf zzif = this.zzcmo.zzif();
                if (zzif != null) {
                    return zzif.zzpp();
                }
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
        AdSize[] adSizeArr = this.zzckq;
        if (adSizeArr != null) {
            return adSizeArr[0];
        }
        return null;
    }

    public final AdSize[] getAdSizes() {
        return this.zzckq;
    }

    public final String getAdUnitId() {
        if (this.zzboa == null) {
            zzxl zzxl = this.zzcmo;
            if (zzxl != null) {
                try {
                    this.zzboa = zzxl.getAdUnitId();
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
        return this.zzboa;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzblk;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzcmp;
    }

    public final void zza(zzyx zzyx) {
        zzxl zzxl;
        try {
            if (this.zzcmo == null) {
                if ((this.zzckq == null || this.zzboa == null) && this.zzcmo == null) {
                    throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                }
                Context context = this.zzcmq.getContext();
                zzwf zza = zza(context, this.zzckq, this.zzcmr);
                if ("search_v2".equals(zza.zzckk)) {
                    zzxl = (zzxl) new zzwm(zzwu.zzpw(), context, zza, this.zzboa).zzd(context, false);
                } else {
                    zzwk zzwk = new zzwk(zzwu.zzpw(), context, zza, this.zzboa, this.zzcmj);
                    zzxl = (zzxl) zzwk.zzd(context, false);
                }
                this.zzcmo = zzxl;
                this.zzcmo.zza((zzxa) new zzvx(this.zzcmm));
                if (this.zzciw != null) {
                    this.zzcmo.zza((zzwx) new zzvu(this.zzciw));
                }
                if (this.zzblk != null) {
                    this.zzcmo.zza((zzxt) new zzwh(this.zzblk));
                }
                if (this.zzcmp != null) {
                    this.zzcmo.zza((zzabg) new zzabj(this.zzcmp));
                }
                if (this.zzcmn != null) {
                    this.zzcmo.zza((zzxz) this.zzcmn.zzba());
                }
                if (this.zzblb != null) {
                    this.zzcmo.zza(new zzzw(this.zzblb));
                }
                this.zzcmo.setManualImpressionsEnabled(this.zzbli);
                try {
                    IObjectWrapper zzie = this.zzcmo.zzie();
                    if (zzie != null) {
                        this.zzcmq.addView((View) ObjectWrapper.unwrap(zzie));
                    }
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
            if (this.zzcmo.zzb(zzwe.zza(this.zzcmq.getContext(), zzyx))) {
                this.zzcmj.zzj(zzyx.zzqk());
            }
        } catch (RemoteException e2) {
            zzbbd.zzd("#007 Could not call remote method.", e2);
        }
    }

    public final void pause() {
        try {
            if (this.zzcmo != null) {
                this.zzcmo.pause();
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void recordManualImpression() {
        if (!this.zzcmk.getAndSet(true)) {
            try {
                if (this.zzcmo != null) {
                    this.zzcmo.zzih();
                }
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        try {
            if (this.zzcmo != null) {
                this.zzcmo.resume();
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setAdListener(AdListener adListener) {
        this.zzciz = adListener;
        this.zzcmm.zza(adListener);
    }

    public final void zza(zzvt zzvt) {
        try {
            this.zzciw = zzvt;
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzwx) zzvt != null ? new zzvu(zzvt) : null);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (this.zzckq == null) {
            zza(adSizeArr);
            return;
        }
        throw new IllegalStateException("The ad size can only be set once on AdView.");
    }

    public final void zza(AdSize... adSizeArr) {
        this.zzckq = adSizeArr;
        try {
            if (this.zzcmo != null) {
                this.zzcmo.zza(zza(this.zzcmq.getContext(), this.zzckq, this.zzcmr));
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
        this.zzcmq.requestLayout();
    }

    public final void setAdUnitId(String str) {
        if (this.zzboa == null) {
            this.zzboa = str;
            return;
        }
        throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzblk = appEventListener;
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzxt) appEventListener != null ? new zzwh(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzcmp = onCustomRenderedAdLoadedListener;
        try {
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzabg) onCustomRenderedAdLoadedListener != null ? new zzabj(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzbli = z;
        try {
            if (this.zzcmo != null) {
                this.zzcmo.setManualImpressionsEnabled(this.zzbli);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setCorrelator(Correlator correlator) {
        this.zzcmn = correlator;
        try {
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzxz) this.zzcmn == null ? null : this.zzcmn.zzba());
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzcmo != null) {
                return this.zzcmo.zzje();
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
        return null;
    }

    public final boolean isLoading() {
        try {
            if (this.zzcmo != null) {
                return this.zzcmo.isLoading();
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
        return false;
    }

    public final VideoController getVideoController() {
        return this.zzcml;
    }

    public final zzyp zzbc() {
        zzxl zzxl = this.zzcmo;
        if (zzxl == null) {
            return null;
        }
        try {
            return zzxl.getVideoController();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        zzzw zzzw;
        this.zzblb = videoOptions;
        try {
            if (this.zzcmo != null) {
                zzxl zzxl = this.zzcmo;
                if (videoOptions == null) {
                    zzzw = null;
                } else {
                    zzzw = new zzzw(videoOptions);
                }
                zzxl.zza(zzzw);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final VideoOptions getVideoOptions() {
        return this.zzblb;
    }

    public final boolean zza(zzxl zzxl) {
        if (zzxl == null) {
            return false;
        }
        try {
            IObjectWrapper zzie = zzxl.zzie();
            if (zzie == null || ((View) ObjectWrapper.unwrap(zzie)).getParent() != null) {
                return false;
            }
            this.zzcmq.addView((View) ObjectWrapper.unwrap(zzie));
            this.zzcmo = zzxl;
            return true;
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
            return false;
        }
    }

    private static zzwf zza(Context context, AdSize[] adSizeArr, int i) {
        zzwf zzwf = new zzwf(context, adSizeArr);
        zzwf.zzckp = zzcj(i);
        return zzwf;
    }
}
