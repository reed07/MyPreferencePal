package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzajv;
import com.google.android.gms.internal.ads.zzaka;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxj;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbbo;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzbcb;
import com.google.android.gms.internal.ads.zzbcg;
import com.google.android.gms.internal.ads.zzwu;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzad {
    private Context mContext;
    private final Object mLock = new Object();
    private long zzbnh = 0;

    public final void zza(Context context, zzbbi zzbbi, String str, @Nullable Runnable runnable) {
        zza(context, zzbbi, true, null, str, null, runnable);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void zza(Context context, zzbbi zzbbi, boolean z, @Nullable zzaxj zzaxj, String str, @Nullable String str2, @Nullable Runnable runnable) {
        if (zzbv.zzlm().elapsedRealtime() - this.zzbnh < DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) {
            zzaxz.zzeo("Not retrying to fetch app settings");
            return;
        }
        this.zzbnh = zzbv.zzlm().elapsedRealtime();
        boolean z2 = true;
        if (zzaxj != null) {
            if (!(zzbv.zzlm().currentTimeMillis() - zzaxj.zzyc() > ((Long) zzwu.zzpz().zzd(zzaan.zzcvb)).longValue()) && zzaxj.zzyd()) {
                z2 = false;
            }
        }
        if (z2) {
            if (context == null) {
                zzaxz.zzeo("Context not provided to fetch application settings");
            } else if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    applicationContext = context;
                }
                this.mContext = applicationContext;
                zzajv zza = zzbv.zzlu().zzb(this.mContext, zzbbi).zza("google.afma.config.fetchAppSettings", zzaka.zzdkb, zzaka.zzdkb);
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str)) {
                        jSONObject.put("app_id", str);
                    } else if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("ad_unit_id", str2);
                    }
                    jSONObject.put("is_init", z);
                    jSONObject.put("pn", context.getPackageName());
                    zzbcb zzj = zza.zzj(jSONObject);
                    zzbcb zza2 = zzbbq.zza(zzj, zzae.zzbni, zzbcg.zzepp);
                    if (runnable != null) {
                        zzj.zza(runnable, zzbcg.zzepp);
                    }
                    zzbbo.zza(zza2, "ConfigLoader.maybeFetchNewAppSettings");
                } catch (Exception e) {
                    zzaxz.zzb("Error requesting application settings", e);
                }
            } else {
                zzaxz.zzeo("App settings could not be fetched. Required parameters missing");
            }
        }
    }
}
