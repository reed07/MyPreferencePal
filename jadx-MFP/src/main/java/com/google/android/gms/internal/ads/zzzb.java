package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
public final class zzzb {
    private final Context mContext;
    private AppEventListener zzblk;
    private String zzboa;
    private boolean zzboq;
    private zzvt zzciw;
    private AdListener zzciz;
    private AdMetadataListener zzcja;
    private final zzalf zzcmj;
    private Correlator zzcmn;
    private zzxl zzcmo;
    private OnCustomRenderedAdLoadedListener zzcmp;
    private boolean zzcmt;
    private RewardedVideoAdListener zzhy;
    private final zzwe zzvn;

    public zzzb(Context context) {
        this(context, zzwe.zzckj, null);
    }

    public zzzb(Context context, PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zzwe.zzckj, publisherInterstitialAd);
    }

    @VisibleForTesting
    private zzzb(Context context, zzwe zzwe, PublisherInterstitialAd publisherInterstitialAd) {
        this.zzcmj = new zzalf();
        this.mContext = context;
        this.zzvn = zzwe;
    }

    public final AdListener getAdListener() {
        return this.zzciz;
    }

    public final String getAdUnitId() {
        return this.zzboa;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzblk;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzcmp;
    }

    public final boolean isLoaded() {
        try {
            if (this.zzcmo == null) {
                return false;
            }
            return this.zzcmo.isReady();
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
            return false;
        }
    }

    public final boolean isLoading() {
        try {
            if (this.zzcmo == null) {
                return false;
            }
            return this.zzcmo.isLoading();
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
            return false;
        }
    }

    public final void zza(zzyx zzyx) {
        try {
            if (this.zzcmo == null) {
                String str = "loadAd";
                if (this.zzboa == null) {
                    zzbl(str);
                }
                zzwf zzpo = this.zzcmt ? zzwf.zzpo() : new zzwf();
                zzwj zzpw = zzwu.zzpw();
                Context context = this.mContext;
                zzwn zzwn = new zzwn(zzpw, context, zzpo, this.zzboa, this.zzcmj);
                this.zzcmo = (zzxl) zzwn.zzd(context, false);
                if (this.zzciz != null) {
                    this.zzcmo.zza((zzxa) new zzvx(this.zzciz));
                }
                if (this.zzciw != null) {
                    this.zzcmo.zza((zzwx) new zzvu(this.zzciw));
                }
                if (this.zzcja != null) {
                    this.zzcmo.zza((zzxq) new zzwa(this.zzcja));
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
                if (this.zzhy != null) {
                    this.zzcmo.zza((zzavb) new zzavg(this.zzhy));
                }
                this.zzcmo.setImmersiveMode(this.zzboq);
            }
            if (this.zzcmo.zzb(zzwe.zza(this.mContext, zzyx))) {
                this.zzcmj.zzj(zzyx.zzqk());
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setAdListener(AdListener adListener) {
        try {
            this.zzciz = adListener;
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzxa) adListener != null ? new zzvx(adListener) : null);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void zza(zzvt zzvt) {
        try {
            this.zzciw = zzvt;
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzwx) zzvt != null ? new zzvu(zzvt) : null);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setAdUnitId(String str) {
        if (this.zzboa == null) {
            this.zzboa = str;
            return;
        }
        throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
    }

    public final void setAdMetadataListener(AdMetadataListener adMetadataListener) {
        try {
            this.zzcja = adMetadataListener;
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzxq) adMetadataListener != null ? new zzwa(adMetadataListener) : null);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final Bundle getAdMetadata() {
        try {
            if (this.zzcmo != null) {
                return this.zzcmo.getAdMetadata();
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
        return new Bundle();
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzblk = appEventListener;
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzxt) appEventListener != null ? new zzwh(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        try {
            this.zzcmp = onCustomRenderedAdLoadedListener;
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzabg) onCustomRenderedAdLoadedListener != null ? new zzabj(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setCorrelator(Correlator correlator) {
        this.zzcmn = correlator;
        try {
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzxz) this.zzcmn == null ? null : this.zzcmn.zzba());
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        try {
            this.zzhy = rewardedVideoAdListener;
            if (this.zzcmo != null) {
                this.zzcmo.zza((zzavb) rewardedVideoAdListener != null ? new zzavg(rewardedVideoAdListener) : null);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void zza(boolean z) {
        this.zzcmt = true;
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzcmo != null) {
                return this.zzcmo.zzje();
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
        return null;
    }

    public final void show() {
        try {
            zzbl("show");
            this.zzcmo.showInterstitial();
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setImmersiveMode(boolean z) {
        try {
            this.zzboq = z;
            if (this.zzcmo != null) {
                this.zzcmo.setImmersiveMode(z);
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    private final void zzbl(String str) {
        if (this.zzcmo == null) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 63);
            sb.append("The ad unit ID must be set on InterstitialAd before ");
            sb.append(str);
            sb.append(" is called.");
            throw new IllegalStateException(sb.toString());
        }
    }
}
