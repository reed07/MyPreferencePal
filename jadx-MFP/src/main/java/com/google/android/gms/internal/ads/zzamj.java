package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zzark
public final class zzamj<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    /* access modifiers changed from: private */
    public final zzalm zzdnz;

    public zzamj(zzalm zzalm) {
        this.zzdnz = zzalm;
    }

    public final void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onClick.");
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzamk(this));
            return;
        }
        try {
            this.zzdnz.onAdClicked();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onDismissScreen.");
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzeo("#008 Must be called on the main UI thread.");
            zzbat.zztu.post(new zzamn(this));
            return;
        }
        try {
            this.zzdnz.onAdClosed();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
        sb.append("Adapter called onFailedToReceiveAd with error. ");
        sb.append(valueOf);
        zzbbd.zzdn(sb.toString());
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzamo(this, errorCode));
            return;
        }
        try {
            this.zzdnz.onAdFailedToLoad(zzamv.zza(errorCode));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onLeaveApplication.");
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzamp(this));
            return;
        }
        try {
            this.zzdnz.onAdLeftApplication();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onPresentScreen.");
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzamq(this));
            return;
        }
        try {
            this.zzdnz.onAdOpened();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onReceivedAd.");
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzamr(this));
            return;
        }
        try {
            this.zzdnz.onAdLoaded();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbd.zzdn("Adapter called onDismissScreen.");
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzams(this));
            return;
        }
        try {
            this.zzdnz.onAdClosed();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
        sb.append("Adapter called onFailedToReceiveAd with error ");
        sb.append(valueOf);
        sb.append(".");
        zzbbd.zzdn(sb.toString());
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzamt(this, errorCode));
            return;
        }
        try {
            this.zzdnz.onAdFailedToLoad(zzamv.zza(errorCode));
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbd.zzdn("Adapter called onLeaveApplication.");
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzamu(this));
            return;
        }
        try {
            this.zzdnz.onAdLeftApplication();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbd.zzdn("Adapter called onPresentScreen.");
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzaml(this));
            return;
        }
        try {
            this.zzdnz.onAdOpened();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbd.zzdn("Adapter called onReceivedAd.");
        zzwu.zzpv();
        if (!zzbat.zzaar()) {
            zzbbd.zzd("#008 Must be called on the main UI thread.", null);
            zzbat.zztu.post(new zzamm(this));
            return;
        }
        try {
            this.zzdnz.onAdLoaded();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }
}
