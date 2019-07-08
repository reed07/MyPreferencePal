package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzc;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzauo extends zzc implements zzavr {
    private static zzauo zzeeh;
    private boolean zzboq;
    @VisibleForTesting
    private final zzawv zzbor;
    private boolean zzeei;
    private final zzauk zzeej;

    public static zzauo zzxg() {
        return zzeeh;
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzwb zzwb, zzaxf zzaxf, boolean z) {
        return false;
    }

    public zzauo(Context context, zzv zzv, zzwf zzwf, zzalg zzalg, zzbbi zzbbi) {
        super(context, zzwf, null, zzalg, zzbbi, zzv);
        zzeeh = this;
        this.zzbor = new zzawv(context, null);
        zzauk zzauk = new zzauk(this.zzbls, this.zzbma, this, this, this);
        this.zzeej = zzauk;
    }

    public final void zza(zzavh zzavh) {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(zzavh.zzbsn)) {
            zzaxz.zzeo("Invalid ad unit id. Aborting.");
            zzayh.zzelc.post(new zzaup(this));
            return;
        }
        this.zzeei = false;
        this.zzbls.zzbsn = zzavh.zzbsn;
        this.zzbor.setAdUnitId(zzavh.zzbsn);
        super.zzb(zzavh.zzdwg);
    }

    public final void zza(zzaxg zzaxg, zzaba zzaba) {
        if (zzaxg.errorCode != -2) {
            zzayh.zzelc.post(new zzauq(this, zzaxg));
            return;
        }
        this.zzbls.zzbsv = zzaxg;
        if (zzaxg.zzehj == null) {
            this.zzbls.zzbsv = zzc(zzaxg);
        }
        this.zzeej.zzxd();
    }

    @Nullable
    public final zzavy zzdd(String str) {
        return this.zzeej.zzdd(str);
    }

    public final boolean zza(zzaxf zzaxf, zzaxf zzaxf2) {
        zzb(zzaxf2, false);
        return zzauk.zza(zzaxf, zzaxf2);
    }

    public final void pause() {
        this.zzeej.pause();
    }

    public final void resume() {
        this.zzeej.resume();
    }

    public final void destroy() {
        this.zzeej.destroy();
        super.destroy();
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzboq = z;
    }

    public final void zzxh() {
        Preconditions.checkMainThread("showAd must be called on the main UI thread.");
        if (!isLoaded()) {
            zzaxz.zzeo("The reward video has not loaded.");
        } else {
            this.zzeej.zzah(this.zzboq);
        }
    }

    public final boolean isLoaded() {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return this.zzbls.zzbsr == null && this.zzbls.zzbss == null && this.zzbls.zzbsu != null;
    }

    public final void onRewardedVideoAdOpened() {
        if (zzbv.zzmf().zzv(this.zzbls.zzsp)) {
            this.zzbor.zzai(true);
        }
        zza(this.zzbls.zzbsu, false);
        zzik();
    }

    public final void onRewardedVideoStarted() {
        this.zzeej.zzxe();
        zzio();
    }

    public final void onRewardedVideoCompleted() {
        this.zzeej.zzxf();
        zzip();
    }

    public final void onRewardedVideoAdClosed() {
        if (zzbv.zzmf().zzv(this.zzbls.zzsp)) {
            this.zzbor.zzai(false);
        }
        zzii();
    }

    /* access modifiers changed from: protected */
    public final void zzii() {
        this.zzbls.zzbsu = null;
        super.zzii();
    }

    public final void zzc(@Nullable zzawd zzawd) {
        zzawd zzd = this.zzeej.zzd(zzawd);
        if (zzbv.zzmf().zzv(this.zzbls.zzsp) && zzd != null) {
            zzbv.zzmf().zza(this.zzbls.zzsp, zzbv.zzmf().zzz(this.zzbls.zzsp), this.zzbls.zzbsn, zzd.type, zzd.zzefo);
        }
        zza(zzd);
    }

    public final void zzkh() {
        onAdClicked();
    }

    public final void onRewardedVideoAdLeftApplication() {
        zzij();
    }

    public final void onContextChanged(Context context) {
        this.zzeej.onContextChanged(context);
    }

    private static zzaxg zzc(zzaxg zzaxg) {
        zzaxg zzaxg2 = zzaxg;
        zzaxz.v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            JSONObject zzb = zzatv.zzb(zzaxg2.zzehy);
            zzb.remove("impression_urls");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzaxg2.zzeag.zzbsn);
            zzakq zzakq = new zzakq(zzb.toString(), null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1);
            zzakr zzakr = new zzakr(Arrays.asList(new zzakq[]{zzakq}), ((Long) zzwu.zzpz().zzd(zzaan.zzctf)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, null, 0, -1, -1, false);
            zzaxg zzaxg3 = new zzaxg(zzaxg2.zzeag, zzaxg2.zzehy, zzakr, zzaxg2.zzbst, zzaxg2.errorCode, zzaxg2.zzehn, zzaxg2.zzeho, zzaxg2.zzehh, zzaxg2.zzehw, null);
            return zzaxg3;
        } catch (JSONException e) {
            zzaxz.zzb("Unable to generate ad state for non-mediated rewarded video.", e);
            zzaxg zzaxg4 = new zzaxg(zzaxg2.zzeag, zzaxg2.zzehy, null, zzaxg2.zzbst, 0, zzaxg2.zzehn, zzaxg2.zzeho, zzaxg2.zzehh, zzaxg2.zzehw, null);
            return zzaxg4;
        }
    }
}
