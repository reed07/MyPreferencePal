package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.common.internal.Preconditions;

@zzark
public final class zzame implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener {
    private final zzalm zzdnz;
    private NativeAdMapper zzdoa;
    private UnifiedNativeAdMapper zzdob;
    private NativeCustomTemplateAd zzdoc;

    public zzame(zzalm zzalm) {
        this.zzdnz = zzalm;
    }

    public final void zza(MediationBannerAdapter mediationBannerAdapter, String str, String str2) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAppEvent.");
        try {
            this.zzdnz.onAppEvent(str, str2);
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClicked(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdClicked.");
        try {
            this.zzdnz.onAdClicked();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClosed(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdClosed.");
        try {
            this.zzdnz.onAdClosed();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdFailedToLoad(MediationBannerAdapter mediationBannerAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error. ");
        sb.append(i);
        zzbbd.zzdn(sb.toString());
        try {
            this.zzdnz.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLeftApplication(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdLeftApplication.");
        try {
            this.zzdnz.onAdLeftApplication();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdOpened(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdOpened.");
        try {
            this.zzdnz.onAdOpened();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdLoaded.");
        try {
            this.zzdnz.onAdLoaded();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClicked(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdClicked.");
        try {
            this.zzdnz.onAdClicked();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClosed(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdClosed.");
        try {
            this.zzdnz.onAdClosed();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdFailedToLoad(MediationInterstitialAdapter mediationInterstitialAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error ");
        sb.append(i);
        sb.append(".");
        zzbbd.zzdn(sb.toString());
        try {
            this.zzdnz.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLeftApplication(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdLeftApplication.");
        try {
            this.zzdnz.onAdLeftApplication();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdOpened(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdOpened.");
        try {
            this.zzdnz.onAdOpened();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdLoaded.");
        try {
            this.zzdnz.onAdLoaded();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, NativeAdMapper nativeAdMapper) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdLoaded.");
        this.zzdoa = nativeAdMapper;
        this.zzdob = null;
        zza(mediationNativeAdapter, this.zzdob, this.zzdoa);
        try {
            this.zzdnz.onAdLoaded();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, UnifiedNativeAdMapper unifiedNativeAdMapper) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdLoaded.");
        this.zzdob = unifiedNativeAdMapper;
        this.zzdoa = null;
        zza(mediationNativeAdapter, this.zzdob, this.zzdoa);
        try {
            this.zzdnz.onAdLoaded();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    private static void zza(MediationNativeAdapter mediationNativeAdapter, @Nullable UnifiedNativeAdMapper unifiedNativeAdMapper, @Nullable NativeAdMapper nativeAdMapper) {
        if (!(mediationNativeAdapter instanceof AdMobAdapter)) {
            VideoController videoController = new VideoController();
            videoController.zza(new zzamb());
            if (unifiedNativeAdMapper != null && unifiedNativeAdMapper.hasVideoContent()) {
                unifiedNativeAdMapper.zza(videoController);
            }
            if (nativeAdMapper != null && nativeAdMapper.hasVideoContent()) {
                nativeAdMapper.zza(videoController);
            }
        }
    }

    public final void zza(MediationNativeAdapter mediationNativeAdapter, NativeCustomTemplateAd nativeCustomTemplateAd) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        String str = "Adapter called onAdLoaded with template id ";
        String valueOf = String.valueOf(nativeCustomTemplateAd.getCustomTemplateId());
        zzbbd.zzdn(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        this.zzdoc = nativeCustomTemplateAd;
        try {
            this.zzdnz.onAdLoaded();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zza(MediationNativeAdapter mediationNativeAdapter, NativeCustomTemplateAd nativeCustomTemplateAd, String str) {
        if (nativeCustomTemplateAd instanceof zzaea) {
            try {
                this.zzdnz.zzb(((zzaea) nativeCustomTemplateAd).zzsx(), str);
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        } else {
            zzbbd.zzeo("Unexpected native custom template ad type.");
        }
    }

    public final void onAdFailedToLoad(MediationNativeAdapter mediationNativeAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error ");
        sb.append(i);
        sb.append(".");
        zzbbd.zzdn(sb.toString());
        try {
            this.zzdnz.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdOpened(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdOpened.");
        try {
            this.zzdnz.onAdOpened();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClosed(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdClosed.");
        try {
            this.zzdnz.onAdClosed();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLeftApplication(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onAdLeftApplication.");
        try {
            this.zzdnz.onAdLeftApplication();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClicked(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper = this.zzdoa;
        UnifiedNativeAdMapper unifiedNativeAdMapper = this.zzdob;
        if (this.zzdoc == null) {
            if (nativeAdMapper == null && unifiedNativeAdMapper == null) {
                zzbbd.zzd("#007 Could not call remote method.", null);
                return;
            } else if (unifiedNativeAdMapper != null && !unifiedNativeAdMapper.getOverrideClickHandling()) {
                zzbbd.zzdn("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            } else if (nativeAdMapper != null && !nativeAdMapper.getOverrideClickHandling()) {
                zzbbd.zzdn("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            }
        }
        zzbbd.zzdn("Adapter called onAdClicked.");
        try {
            this.zzdnz.onAdClicked();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdImpression(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper = this.zzdoa;
        UnifiedNativeAdMapper unifiedNativeAdMapper = this.zzdob;
        if (this.zzdoc == null) {
            if (nativeAdMapper == null && unifiedNativeAdMapper == null) {
                zzbbd.zzd("#007 Could not call remote method.", null);
                return;
            } else if (unifiedNativeAdMapper != null && !unifiedNativeAdMapper.getOverrideImpressionRecording()) {
                zzbbd.zzdn("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            } else if (nativeAdMapper != null && !nativeAdMapper.getOverrideImpressionRecording()) {
                zzbbd.zzdn("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            }
        }
        zzbbd.zzdn("Adapter called onAdImpression.");
        try {
            this.zzdnz.onAdImpression();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onVideoEnd(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbd.zzdn("Adapter called onVideoEnd.");
        try {
            this.zzdnz.onVideoEnd();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    public final NativeAdMapper zzvd() {
        return this.zzdoa;
    }

    public final UnifiedNativeAdMapper zzve() {
        return this.zzdob;
    }

    public final NativeCustomTemplateAd zzvf() {
        return this.zzdoc;
    }
}
