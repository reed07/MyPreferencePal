package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzark
public final class zzawc implements MediationRewardedVideoAdListener {
    private final zzavz zzefn;

    public zzawc(zzavz zzavz) {
        this.zzefn = zzavz;
    }

    public final void onInitializationSucceeded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onInitializationSucceeded.");
        try {
            this.zzefn.zzs(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onInitializationFailed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onInitializationFailed.");
        try {
            this.zzefn.zzc(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), i);
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdLoaded.");
        try {
            this.zzefn.zzt(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdOpened(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdOpened.");
        try {
            this.zzefn.zzu(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onVideoStarted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onVideoStarted.");
        try {
            this.zzefn.zzv(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClosed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdClosed.");
        try {
            this.zzefn.zzw(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onRewarded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, RewardItem rewardItem) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onRewarded.");
        if (rewardItem != null) {
            try {
                this.zzefn.zza(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), new zzawd(rewardItem));
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        } else {
            this.zzefn.zza(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), new zzawd("", 1));
        }
    }

    public final void onAdClicked(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdClicked.");
        try {
            this.zzefn.zzx(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdFailedToLoad(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdFailedToLoad.");
        try {
            this.zzefn.zzd(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), i);
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLeftApplication(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdLeftApplication.");
        try {
            this.zzefn.zzy(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onVideoCompleted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onVideoCompleted.");
        try {
            this.zzefn.zzz(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zzc(Bundle bundle) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdMetadataChanged.");
        try {
            this.zzefn.zzc(bundle);
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }
}
