package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.ads.zzbit;
import com.google.android.gms.internal.ads.zzzc;
import com.google.android.gms.internal.ads.zzzf;

public class MobileAds {

    public static final class Settings {
        private final zzzf zzvz = new zzzf();

        @Deprecated
        public final String getTrackingId() {
            return null;
        }

        @Deprecated
        public final boolean isGoogleAnalyticsEnabled() {
            return false;
        }

        @Deprecated
        public final Settings setGoogleAnalyticsEnabled(boolean z) {
            return this;
        }

        @Deprecated
        public final Settings setTrackingId(String str) {
            return this;
        }

        /* access modifiers changed from: 0000 */
        public final zzzf zzbb() {
            return this.zzvz;
        }
    }

    @RequiresPermission
    public static void initialize(Context context, String str) {
        initialize(context, str, null);
    }

    @RequiresPermission
    @Deprecated
    public static void initialize(Context context, String str, Settings settings) {
        zzzf zzzf;
        zzzc zzqq = zzzc.zzqq();
        if (settings == null) {
            zzzf = null;
        } else {
            zzzf = settings.zzbb();
        }
        zzqq.zza(context, str, zzzf);
    }

    public static void initialize(Context context) {
        initialize(context, null, null);
    }

    public static RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        return zzzc.zzqq().getRewardedVideoAdInstance(context);
    }

    public static void setAppVolume(float f) {
        zzzc.zzqq().setAppVolume(f);
    }

    public static void setAppMuted(boolean z) {
        zzzc.zzqq().setAppMuted(z);
    }

    public static void openDebugMenu(Context context, String str) {
        zzzc.zzqq().openDebugMenu(context, str);
    }

    @KeepForSdk
    public static void getVersionString() {
        zzzc.zzqq().zzkl();
    }

    @KeepForSdk
    public static void registerRtbAdapter(Class<? extends zzbit> cls) {
        zzzc.zzqq().registerRtbAdapter(cls);
    }

    private MobileAds() {
    }
}
