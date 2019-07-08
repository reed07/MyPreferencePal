package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzzc {
    private static final Object lock = new Object();
    @GuardedBy("lock")
    private static zzzc zzcmu;
    private zzyc zzcmv;
    private RewardedVideoAd zzcmw;

    public static zzzc zzqq() {
        zzzc zzzc;
        synchronized (lock) {
            if (zzcmu == null) {
                zzcmu = new zzzc();
            }
            zzzc = zzcmu;
        }
        return zzzc;
    }

    public final void zza(Context context, String str, zzzf zzzf) {
        synchronized (lock) {
            if (this.zzcmv == null) {
                if (context != null) {
                    try {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("measurementEnabled", false);
                        zzakm.zza(context, str, bundle);
                        this.zzcmv = (zzyc) new zzwp(zzwu.zzpw(), context).zzd(context, false);
                        this.zzcmv.zza();
                        this.zzcmv.zza(new zzalf());
                        if (str != null) {
                            this.zzcmv.zza(str, ObjectWrapper.wrap(new zzzd(this, context)));
                        }
                    } catch (RemoteException e) {
                        zzbbd.zzc("MobileAdsSettingManager initialization failed", e);
                    }
                } else {
                    throw new IllegalArgumentException("Context cannot be null.");
                }
            }
        }
    }

    public final RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        synchronized (lock) {
            if (this.zzcmw != null) {
                RewardedVideoAd rewardedVideoAd = this.zzcmw;
                return rewardedVideoAd;
            }
            this.zzcmw = new zzavj(context, (zzauw) new zzws(zzwu.zzpw(), context, new zzalf()).zzd(context, false));
            RewardedVideoAd rewardedVideoAd2 = this.zzcmw;
            return rewardedVideoAd2;
        }
    }

    public final void setAppVolume(float f) {
        boolean z = true;
        Preconditions.checkArgument(BitmapDescriptorFactory.HUE_RED <= f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        if (this.zzcmv == null) {
            z = false;
        }
        Preconditions.checkState(z, "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzcmv.setAppVolume(f);
        } catch (RemoteException e) {
            zzbbd.zzb("Unable to set app volume.", e);
        }
    }

    public final float zzkj() {
        zzyc zzyc = this.zzcmv;
        float f = 1.0f;
        if (zzyc == null) {
            return 1.0f;
        }
        try {
            f = zzyc.zzkj();
        } catch (RemoteException e) {
            zzbbd.zzb("Unable to get app volume.", e);
        }
        return f;
    }

    public final void setAppMuted(boolean z) {
        Preconditions.checkState(this.zzcmv != null, "MobileAds.initialize() must be called prior to setting app muted state.");
        try {
            this.zzcmv.setAppMuted(z);
        } catch (RemoteException e) {
            zzbbd.zzb("Unable to set app mute state.", e);
        }
    }

    public final boolean zzkk() {
        zzyc zzyc = this.zzcmv;
        boolean z = false;
        if (zzyc == null) {
            return false;
        }
        try {
            z = zzyc.zzkk();
        } catch (RemoteException e) {
            zzbbd.zzb("Unable to get app mute state.", e);
        }
        return z;
    }

    public final void openDebugMenu(Context context, String str) {
        Preconditions.checkState(this.zzcmv != null, "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzcmv.zzb(ObjectWrapper.wrap(context), str);
        } catch (RemoteException e) {
            zzbbd.zzb("Unable to open debug menu.", e);
        }
    }

    public final String zzkl() {
        try {
            this.zzcmv.zzkl();
        } catch (RemoteException e) {
            zzbbd.zzb("Unable to get version string.", e);
        }
        return "";
    }

    public final void registerRtbAdapter(Class<? extends zzbit> cls) {
        try {
            this.zzcmv.zzau(cls.getCanonicalName());
        } catch (RemoteException e) {
            zzbbd.zzb("Unable to register RtbAdapter", e);
        }
    }

    private zzzc() {
    }
}
