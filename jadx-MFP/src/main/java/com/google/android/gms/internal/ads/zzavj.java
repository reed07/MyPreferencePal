package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzark
public final class zzavj implements RewardedVideoAd {
    private final Context mContext;
    private final Object mLock = new Object();
    private String zzbtr;
    @NonNull
    private final zzauw zzeem;
    private final zzavg zzeen = new zzavg(null);
    private String zzeeo;

    public zzavj(Context context, @Nullable zzauw zzauw) {
        if (zzauw == null) {
            zzauw = new zzzq();
        }
        this.zzeem = zzauw;
        this.mContext = context.getApplicationContext();
    }

    private final void zza(String str, zzyx zzyx) {
        synchronized (this.mLock) {
            if (this.zzeem != null) {
                try {
                    this.zzeem.zza(new zzavh(zzwe.zza(this.mContext, zzyx), str));
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void loadAd(String str, AdRequest adRequest) {
        zza(str, adRequest.zzaz());
    }

    public final void loadAd(String str, PublisherAdRequest publisherAdRequest) {
        zza(str, publisherAdRequest.zzaz());
    }

    public final void show() {
        synchronized (this.mLock) {
            if (this.zzeem != null) {
                try {
                    this.zzeem.show();
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        synchronized (this.mLock) {
            this.zzeen.setRewardedVideoAdListener(rewardedVideoAdListener);
            if (this.zzeem != null) {
                try {
                    this.zzeem.zza((zzavb) this.zzeen);
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void setAdMetadataListener(AdMetadataListener adMetadataListener) {
        synchronized (this.mLock) {
            if (this.zzeem != null) {
                try {
                    this.zzeem.zza((zzxq) new zzwa(adMetadataListener));
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final Bundle getAdMetadata() {
        synchronized (this.mLock) {
            if (this.zzeem != null) {
                try {
                    Bundle adMetadata = this.zzeem.getAdMetadata();
                    return adMetadata;
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
            Bundle bundle = new Bundle();
            return bundle;
        }
    }

    public final void setUserId(String str) {
        synchronized (this.mLock) {
            this.zzbtr = str;
            if (this.zzeem != null) {
                try {
                    this.zzeem.setUserId(str);
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final boolean isLoaded() {
        synchronized (this.mLock) {
            if (this.zzeem == null) {
                return false;
            }
            try {
                boolean isLoaded = this.zzeem.isLoaded();
                return isLoaded;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return false;
            }
        }
    }

    public final void pause() {
        pause(null);
    }

    public final void pause(Context context) {
        synchronized (this.mLock) {
            if (this.zzeem != null) {
                try {
                    this.zzeem.zze(ObjectWrapper.wrap(context));
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void resume() {
        resume(null);
    }

    public final void resume(Context context) {
        synchronized (this.mLock) {
            if (this.zzeem != null) {
                try {
                    this.zzeem.zzf(ObjectWrapper.wrap(context));
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void destroy() {
        destroy(null);
    }

    public final void destroy(Context context) {
        synchronized (this.mLock) {
            this.zzeen.setRewardedVideoAdListener(null);
            if (this.zzeem != null) {
                try {
                    this.zzeem.zzg(ObjectWrapper.wrap(context));
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        RewardedVideoAdListener rewardedVideoAdListener;
        synchronized (this.mLock) {
            rewardedVideoAdListener = this.zzeen.getRewardedVideoAdListener();
        }
        return rewardedVideoAdListener;
    }

    public final String getUserId() {
        String str;
        synchronized (this.mLock) {
            str = this.zzbtr;
        }
        return str;
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzeem != null) {
                return this.zzeem.getMediationAdapterClassName();
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
        return null;
    }

    public final void setImmersiveMode(boolean z) {
        synchronized (this.mLock) {
            if (this.zzeem != null) {
                try {
                    this.zzeem.setImmersiveMode(z);
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void setCustomData(String str) {
        synchronized (this.mLock) {
            if (this.zzeem != null) {
                try {
                    this.zzeem.setCustomData(str);
                    this.zzeeo = str;
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final String getCustomData() {
        String str;
        synchronized (this.mLock) {
            str = this.zzeeo;
        }
        return str;
    }
}
