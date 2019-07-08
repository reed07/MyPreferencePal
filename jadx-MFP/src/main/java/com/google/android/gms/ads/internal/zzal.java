package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.gmsg.zzag;
import com.google.android.gms.ads.internal.gmsg.zzah;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzakr;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzasm;
import com.google.android.gms.internal.ads.zzatv;
import com.google.android.gms.internal.ads.zzauk;
import com.google.android.gms.internal.ads.zzawd;
import com.google.android.gms.internal.ads.zzawr;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbgm;
import com.google.android.gms.internal.ads.zzbgq;
import com.google.android.gms.internal.ads.zzbhn;
import com.google.android.gms.internal.ads.zzbhq;
import com.google.android.gms.internal.ads.zzbht;
import com.google.android.gms.internal.ads.zzsc;
import com.google.android.gms.internal.ads.zzsg;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzal extends zzh implements zzah, zzy {
    private transient boolean zzbom;
    private int zzbon = -1;
    /* access modifiers changed from: private */
    public boolean zzboo;
    /* access modifiers changed from: private */
    public float zzbop;
    /* access modifiers changed from: private */
    public boolean zzboq;
    private zzawv zzbor;
    private String zzbos;
    private final String zzbot;
    private final zzauk zzbou;

    public zzal(Context context, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi, zzv zzv) {
        super(context, zzwf, str, zzalg, zzbbi, zzv);
        boolean z = false;
        this.zzbom = false;
        if (zzwf != null && "reward_mb".equals(zzwf.zzckk)) {
            z = true;
        }
        this.zzbot = z ? "/Rewarded" : "/Interstitial";
        this.zzbou = z ? new zzauk(this.zzbls, this.zzbma, new zzan(this), this, this) : null;
    }

    private final boolean zzn(boolean z) {
        return this.zzbou != null && z;
    }

    public final boolean zza(zzwb zzwb, zzaba zzaba) {
        if (this.zzbls.zzbsu != null) {
            zzaxz.zzeo("An interstitial is already loading. Aborting.");
            return false;
        }
        if (this.zzbor == null && zza(zzwb) && zzbv.zzmf().zzv(this.zzbls.zzsp) && !TextUtils.isEmpty(this.zzbls.zzbsn)) {
            this.zzbor = new zzawv(this.zzbls.zzsp, this.zzbls.zzbsn);
        }
        return super.zza(zzwb, zzaba);
    }

    public final void zza(zzaxg zzaxg, zzaba zzaba) {
        if (zzaxg.errorCode != -2) {
            super.zza(zzaxg, zzaba);
            return;
        }
        if (zzn(zzaxg.zzehj != null)) {
            this.zzbou.zzxd();
            return;
        }
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcrz)).booleanValue()) {
            super.zza(zzaxg, zzaba);
            return;
        }
        boolean z = !zzaxg.zzehy.zzdyd;
        if (zza(zzaxg.zzeag.zzdwg) && z) {
            this.zzbls.zzbsv = zzb(zzaxg);
        }
        super.zza(this.zzbls.zzbsv, zzaba);
    }

    /* access modifiers changed from: protected */
    public final zzbgg zza(zzaxg zzaxg, @Nullable zzw zzw, @Nullable zzawr zzawr) throws zzbgq {
        zzbv.zzlg();
        zzbgg zza = zzbgm.zza(this.zzbls.zzsp, zzbht.zzb(this.zzbls.zzbst), this.zzbls.zzbst.zzckk, false, false, this.zzbls.zzbso, this.zzbls.zzbsp, this.zzbln, this, this.zzbly, zzaxg.zzehw);
        zza.zzadl().zza(this, this, null, this, this, true, this, zzw, this, zzawr);
        zza(zza);
        zza.zzfb(zzaxg.zzeag.zzdws);
        zza.zza("/reward", (zzu<? super zzbgg>) new zzag<Object>(this));
        return zza;
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzwb zzwb, zzaxf zzaxf, boolean z) {
        if (this.zzbls.zzmj() && zzaxf.zzdrv != null) {
            zzbv.zzlh();
            zzayp.zzi(zzaxf.zzdrv);
        }
        return this.zzblr.zzkv();
    }

    public final boolean zza(@Nullable zzaxf zzaxf, zzaxf zzaxf2) {
        if (zzn(zzaxf2.zzdyd)) {
            return zzauk.zza(zzaxf, zzaxf2);
        }
        if (!super.zza(zzaxf, zzaxf2)) {
            return false;
        }
        if (!(this.zzbls.zzmj() || this.zzbls.zzbtv == null || zzaxf2.zzehh == null)) {
            this.zzblu.zza(this.zzbls.zzbst, zzaxf2, this.zzbls.zzbtv);
        }
        zzb(zzaxf2, false);
        return true;
    }

    public final void zziw() {
        recordImpression();
        super.zziw();
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null)) {
            zzbhn zzadl = this.zzbls.zzbsu.zzdrv.zzadl();
            if (zzadl != null) {
                zzadl.zzaeg();
            }
        }
        if (!(!zzbv.zzmf().zzv(this.zzbls.zzsp) || this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null)) {
            zzbv.zzmf().zze(this.zzbls.zzbsu.zzdrv.getContext(), this.zzbos);
        }
        zzawv zzawv = this.zzbor;
        if (zzawv != null) {
            zzawv.zzai(true);
        }
        if (this.zzblx != null && this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdrv != null) {
            this.zzbls.zzbsu.zzdrv.zza("onSdkImpression", (Map<String, ?>) new HashMap<String,Object>());
        }
    }

    public final void zziv() {
        super.zziv();
        this.zzblu.zzh(this.zzbls.zzbsu);
        zzawv zzawv = this.zzbor;
        if (zzawv != null) {
            zzawv.zzai(false);
        }
        this.zzblx = null;
    }

    /* access modifiers changed from: protected */
    public final void zzii() {
        zzke();
        super.zzii();
    }

    public final void zzo(boolean z) {
        this.zzbls.zzbpa = z;
    }

    public final void zza(boolean z, float f) {
        this.zzboo = z;
        this.zzbop = f;
    }

    public final void showInterstitial() {
        Bitmap bitmap;
        String str;
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        if (zzn(this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdyd)) {
            this.zzbou.zzah(this.zzboq);
            return;
        }
        if (zzbv.zzmf().zzv(this.zzbls.zzsp)) {
            this.zzbos = zzbv.zzmf().zzw(this.zzbls.zzsp);
            String valueOf = String.valueOf(this.zzbos);
            String valueOf2 = String.valueOf(this.zzbot);
            this.zzbos = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        if (this.zzbls.zzbsu == null) {
            zzaxz.zzeo("The interstitial has not loaded.");
            return;
        }
        if (!this.zzbom) {
            if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcxp)).booleanValue()) {
                return;
            }
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcra)).booleanValue()) {
            zzbv.zzlf();
            if (zzayh.zzap(this.zzbls.zzsp)) {
                zzaxz.zzeo("It is not recommended to show an interstitial when app is not in foreground.");
                return;
            }
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcsw)).booleanValue()) {
            if (this.zzbls.zzsp.getApplicationContext() != null) {
                str = this.zzbls.zzsp.getApplicationContext().getPackageName();
            } else {
                str = this.zzbls.zzsp.getPackageName();
            }
            if (!this.zzbom) {
                zzaxz.zzeo("It is not recommended to show an interstitial before onAdLoaded completes.");
                Bundle bundle = new Bundle();
                bundle.putString("appid", str);
                bundle.putString("action", "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            zzbv.zzlf();
            if (!zzayh.zzao(this.zzbls.zzsp)) {
                zzaxz.zzeo("It is not recommended to show an interstitial when app is not in foreground.");
                Bundle bundle2 = new Bundle();
                bundle2.putString("appid", str);
                bundle2.putString("action", "show_interstitial_app_not_in_foreground");
                zzb(bundle2);
            }
        }
        if (!this.zzbls.zzmk()) {
            if (this.zzbls.zzbsu.zzdyd && this.zzbls.zzbsu.zzdnc != null) {
                try {
                    this.zzbls.zzbsu.zzdnc.setImmersiveMode(this.zzboq);
                    this.zzbls.zzbsu.zzdnc.showInterstitial();
                } catch (RemoteException e) {
                    zzaxz.zzc("Could not show interstitial.", e);
                    zzke();
                }
            } else if (this.zzbls.zzbsu.zzdrv == null) {
                zzaxz.zzeo("The interstitial failed to load.");
            } else if (this.zzbls.zzbsu.zzdrv.zzadq()) {
                zzaxz.zzeo("The interstitial is already showing.");
            } else {
                this.zzbls.zzbsu.zzdrv.zzav(true);
                this.zzbls.zzj(this.zzbls.zzbsu.zzdrv.getView());
                if (this.zzbls.zzbsu.zzehh != null) {
                    this.zzblu.zza(this.zzbls.zzbst, this.zzbls.zzbsu);
                }
                if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                    zzaxf zzaxf = this.zzbls.zzbsu;
                    if (zzaxf.zzmu()) {
                        new zzsc(this.zzbls.zzsp, zzaxf.zzdrv.getView()).zza((zzsg) zzaxf.zzdrv);
                    } else {
                        zzaxf.zzdrv.zzadl().zza((zzbhq) new zzam(this, zzaxf));
                    }
                }
                if (this.zzbls.zzbpa) {
                    zzbv.zzlf();
                    bitmap = zzayh.zzar(this.zzbls.zzsp);
                } else {
                    bitmap = null;
                }
                this.zzbon = zzbv.zzma().zzb(bitmap);
                if (bitmap != null) {
                    new zzao(this, this.zzbon).zzyz();
                    return;
                }
                boolean z = this.zzbls.zzbpa;
                zzbv.zzlf();
                zzaq zzaq = new zzaq(z, zzayh.zzay(this.zzbls.zzsp), false, BitmapDescriptorFactory.HUE_RED, -1, this.zzboq, this.zzbls.zzbsu.zzbph, this.zzbls.zzbsu.zzbpi);
                int requestedOrientation = this.zzbls.zzbsu.zzdrv.getRequestedOrientation();
                AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this, this, this, this.zzbls.zzbsu.zzdrv, requestedOrientation == -1 ? this.zzbls.zzbsu.orientation : requestedOrientation, this.zzbls.zzbsp, this.zzbls.zzbsu.zzdyi, zzaq);
                zzbv.zzld();
                zzl.zza(this.zzbls.zzsp, adOverlayInfoParcel, true);
            }
        }
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzboq = z;
    }

    private final void zzb(Bundle bundle) {
        zzbv.zzlf().zzb(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, "gmob-apps", bundle, false);
    }

    /* access modifiers changed from: protected */
    public final void zzil() {
        zzbgg zzbgg = this.zzbls.zzbsu != null ? this.zzbls.zzbsu.zzdrv : null;
        zzaxg zzaxg = this.zzbls.zzbsv;
        if (!(zzaxg == null || zzaxg.zzehy == null || !zzaxg.zzehy.zzdzc || zzbgg == null || !zzbv.zzlw().zzk(this.zzbls.zzsp))) {
            int i = this.zzbls.zzbsp.zzeou;
            int i2 = this.zzbls.zzbsp.zzeov;
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append(".");
            sb.append(i2);
            this.zzblx = zzbv.zzlw().zza(sb.toString(), zzbgg.getWebView(), "", "javascript", zzit());
            if (!(this.zzblx == null || zzbgg.getView() == null)) {
                zzbv.zzlw().zza(this.zzblx, zzbgg.getView());
                zzbgg.zzaa(this.zzblx);
                zzbv.zzlw().zzo(this.zzblx);
            }
        }
        super.zzil();
        this.zzbom = true;
    }

    public final void zzke() {
        zzbv.zzma().zzb(Integer.valueOf(this.zzbon));
        if (this.zzbls.zzmj()) {
            this.zzbls.zzmh();
            this.zzbls.zzbsu = null;
            this.zzbls.zzbpa = false;
            this.zzbom = false;
        }
    }

    public final void zzkf() {
        if (zzn(this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdyd)) {
            this.zzbou.zzxe();
            zzio();
            return;
        }
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzehm == null)) {
            zzbv.zzlf();
            zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, this.zzbls.zzbsu.zzehm);
        }
        zzio();
    }

    public final void zzb(zzawd zzawd) {
        if (zzn(this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdyd)) {
            zza(this.zzbou.zzd(zzawd));
            return;
        }
        if (this.zzbls.zzbsu != null) {
            if (this.zzbls.zzbsu.zzdyt != null) {
                zzbv.zzlf();
                zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, this.zzbls.zzbsu.zzdyt);
            }
            if (this.zzbls.zzbsu.zzdyr != null) {
                zzawd = this.zzbls.zzbsu.zzdyr;
            }
        }
        zza(zzawd);
    }

    public final void zzkg() {
        if (zzn(this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdyd)) {
            this.zzbou.zzxf();
        }
        zzip();
    }

    @VisibleForTesting
    private static zzaxg zzb(zzaxg zzaxg) {
        zzaxg zzaxg2 = zzaxg;
        try {
            String jSONObject = zzatv.zzb(zzaxg2.zzehy).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzaxg2.zzeag.zzbsn);
            zzakq zzakq = new zzakq(jSONObject, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1);
            zzasm zzasm = zzaxg2.zzehy;
            zzakr zzakr = new zzakr(Collections.singletonList(zzakq), ((Long) zzwu.zzpz().zzd(zzaan.zzctf)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzasm.zzdlu, zzasm.zzdlv, "", -1, 0, 1, null, 0, -1, -1, false);
            zzasm zzasm2 = new zzasm(zzaxg2.zzeag, zzasm.zzbde, zzasm.zzdyb, Collections.emptyList(), Collections.emptyList(), zzasm.zzdyc, true, zzasm.zzdye, Collections.emptyList(), zzasm.zzdlx, zzasm.orientation, zzasm.zzdyg, zzasm.zzdyh, zzasm.zzdyi, zzasm.zzdyj, zzasm.zzdyk, null, zzasm.zzdym, zzasm.zzckn, zzasm.zzdwn, zzasm.zzdyn, zzasm.zzdyo, zzasm.zzcgx, zzasm.zzcko, zzasm.zzckp, null, Collections.emptyList(), Collections.emptyList(), zzasm.zzdyu, zzasm.zzdyv, zzasm.zzdxb, zzasm.zzdxc, zzasm.zzdlu, zzasm.zzdlv, zzasm.zzdyw, null, zzasm.zzdyy, zzasm.zzdyz, zzasm.zzdxn, zzasm.zzbph, 0, zzasm.zzdzc, Collections.emptyList(), zzasm.zzbpi, zzasm.zzdzd, zzasm.zzdze, zzasm.zzdzf);
            zzaxg zzaxg3 = new zzaxg(zzaxg2.zzeag, zzasm2, zzakr, zzaxg2.zzbst, zzaxg2.errorCode, zzaxg2.zzehn, zzaxg2.zzeho, null, zzaxg2.zzehw, null);
            return zzaxg3;
        } catch (JSONException e) {
            zzaxz.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return zzaxg2;
        }
    }

    public final void zzjv() {
        zzd zzadh = this.zzbls.zzbsu.zzdrv.zzadh();
        if (zzadh != null) {
            zzadh.close();
        }
    }
}
