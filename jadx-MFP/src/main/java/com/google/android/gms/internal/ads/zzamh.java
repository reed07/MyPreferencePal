package com.google.android.gms.internal.ads;

import android.location.Location;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@zzark
public final class zzamh implements NativeMediationAdRequest {
    private final zzacp zzbnx;
    private final int zzcjv;
    private final boolean zzckh;
    private final List<String> zzdmp = new ArrayList();
    private final int zzdnw;
    private final Map<String, Boolean> zzdof = new HashMap();
    private final Date zzih;
    private final Set<String> zzij;
    private final boolean zzik;
    private final Location zzil;

    public zzamh(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, zzacp zzacp, List<String> list, boolean z2) {
        this.zzih = date;
        this.zzcjv = i;
        this.zzij = set;
        this.zzil = location;
        this.zzik = z;
        this.zzdnw = i2;
        this.zzbnx = zzacp;
        this.zzckh = z2;
        String str = "custom:";
        if (list != null) {
            for (String str2 : list) {
                if (str2.startsWith(str)) {
                    String[] split = str2.split(":", 3);
                    if (split.length == 3) {
                        if ("true".equals(split[2])) {
                            this.zzdof.put(split[1], Boolean.valueOf(true));
                        } else if ("false".equals(split[2])) {
                            this.zzdof.put(split[1], Boolean.valueOf(false));
                        }
                    }
                } else {
                    this.zzdmp.add(str2);
                }
            }
        }
    }

    @Deprecated
    public final Date getBirthday() {
        return this.zzih;
    }

    @Deprecated
    public final int getGender() {
        return this.zzcjv;
    }

    public final Set<String> getKeywords() {
        return this.zzij;
    }

    public final Location getLocation() {
        return this.zzil;
    }

    public final boolean isTesting() {
        return this.zzik;
    }

    public final int taggedForChildDirectedTreatment() {
        return this.zzdnw;
    }

    public final NativeAdOptions getNativeAdOptions() {
        if (this.zzbnx == null) {
            return null;
        }
        Builder requestMultipleImages = new Builder().setReturnUrlsForImageAssets(this.zzbnx.zzdcs).setImageOrientation(this.zzbnx.zzdct).setRequestMultipleImages(this.zzbnx.zzdcu);
        if (this.zzbnx.versionCode >= 2) {
            requestMultipleImages.setAdChoicesPlacement(this.zzbnx.zzdcv);
        }
        if (this.zzbnx.versionCode >= 3 && this.zzbnx.zzdcw != null) {
            requestMultipleImages.setVideoOptions(new VideoOptions(this.zzbnx.zzdcw));
        }
        return requestMultipleImages.build();
    }

    public final boolean isAdMuted() {
        return zzzc.zzqq().zzkk();
    }

    public final float getAdVolume() {
        return zzzc.zzqq().zzkj();
    }

    public final boolean isAppInstallAdRequested() {
        List<String> list = this.zzdmp;
        return list != null && (list.contains(InternalAvidAdSessionContext.AVID_API_LEVEL) || this.zzdmp.contains("6"));
    }

    public final boolean isUnifiedNativeAdRequested() {
        List<String> list = this.zzdmp;
        return list != null && list.contains("6");
    }

    public final boolean isContentAdRequested() {
        List<String> list = this.zzdmp;
        return list != null && (list.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES) || this.zzdmp.contains("6"));
    }

    public final boolean zzvg() {
        List<String> list = this.zzdmp;
        return list != null && list.contains("3");
    }

    public final Map<String, Boolean> zzvh() {
        return this.zzdof;
    }

    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzckh;
    }
}
