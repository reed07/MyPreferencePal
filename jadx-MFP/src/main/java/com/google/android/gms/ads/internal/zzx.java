package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebView;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzals;
import com.google.android.gms.internal.ads.zzalv;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzawr;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbgq;
import com.google.android.gms.internal.ads.zzbhr;
import com.google.android.gms.internal.ads.zzbht;
import com.google.android.gms.internal.ads.zzsc;
import com.google.android.gms.internal.ads.zzsg;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import com.google.android.gms.internal.ads.zzyp;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzx extends zzh implements OnGlobalLayoutListener, OnScrollChangedListener {
    private boolean zzbli;
    private boolean zzbmz;
    private WeakReference<Object> zzbna = new WeakReference<>(null);

    public zzx(Context context, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi, zzv zzv) {
        super(context, zzwf, str, zzalg, zzbbi, zzv);
    }

    /* access modifiers changed from: protected */
    public final zzbgg zza(zzaxg zzaxg, @Nullable zzw zzw, @Nullable zzawr zzawr) throws zzbgq {
        zzwf zzwf;
        AdSize adSize;
        if (this.zzbls.zzbst.zzckm == null && this.zzbls.zzbst.zzcko) {
            zzbw zzbw = this.zzbls;
            if (zzaxg.zzehy.zzcko) {
                zzwf = this.zzbls.zzbst;
            } else {
                String str = zzaxg.zzehy.zzdyg;
                if (str != null) {
                    String[] split = str.split("[xX]");
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    adSize = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } else {
                    adSize = this.zzbls.zzbst.zzpp();
                }
                zzwf = new zzwf(this.zzbls.zzsp, adSize);
            }
            zzbw.zzbst = zzwf;
        }
        return super.zza(zzaxg, zzw, zzawr);
    }

    public final boolean zzb(zzwb zzwb) {
        zzwb zzwb2 = zzwb;
        this.zzbmz = false;
        this.zzblx = null;
        if (zzwb2.zzcjg != this.zzbli) {
            zzwb zzwb3 = new zzwb(zzwb2.versionCode, zzwb2.zzcjb, zzwb2.extras, zzwb2.zzcjc, zzwb2.zzcjd, zzwb2.zzcje, zzwb2.zzcjf, zzwb2.zzcjg || this.zzbli, zzwb2.zzcjh, zzwb2.zzcji, zzwb2.zzcjj, zzwb2.zzcjk, zzwb2.zzcjl, zzwb2.zzcjm, zzwb2.zzcjn, zzwb2.zzcjo, zzwb2.zzcjp, zzwb2.zzcjq, null, zzwb2.zzcjs, zzwb2.zzcjt);
            zzwb2 = zzwb3;
        }
        return super.zzb(zzwb2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007c, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcuc)).booleanValue() != false) goto L_0x007e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(@android.support.annotation.Nullable com.google.android.gms.internal.ads.zzaxf r5, com.google.android.gms.internal.ads.zzaxf r6) {
        /*
            r4 = this;
            boolean r0 = super.zza(r5, r6)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzbls
            boolean r0 = r0.zzmj()
            if (r0 == 0) goto L_0x0025
            boolean r5 = r4.zzd(r5, r6)
            if (r5 != 0) goto L_0x0025
            com.google.android.gms.internal.ads.zzum r5 = r6.zzehw
            if (r5 == 0) goto L_0x0021
            com.google.android.gms.internal.ads.zzum r5 = r6.zzehw
            com.google.android.gms.internal.ads.zzuo$zza$zzb r6 = com.google.android.gms.internal.ads.zzuo.zza.zzb.AD_FAILED_TO_LOAD
            r5.zza(r6)
        L_0x0021:
            r4.zzbr(r1)
            return r1
        L_0x0025:
            r4.zzb(r6, r1)
            boolean r5 = r6.zzdyu
            r0 = 0
            if (r5 == 0) goto L_0x0064
            r4.zzd(r6)
            com.google.android.gms.ads.internal.zzbv.zzme()
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzbls
            com.google.android.gms.ads.internal.zzbx r5 = r5.zzbsq
            com.google.android.gms.internal.ads.zzbct.zza(r5, r4)
            com.google.android.gms.ads.internal.zzbv.zzme()
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzbls
            com.google.android.gms.ads.internal.zzbx r5 = r5.zzbsq
            com.google.android.gms.internal.ads.zzbct.zza(r5, r4)
            boolean r5 = r6.zzehi
            if (r5 != 0) goto L_0x0081
            com.google.android.gms.ads.internal.zzy r5 = new com.google.android.gms.ads.internal.zzy
            r5.<init>(r4)
            com.google.android.gms.internal.ads.zzbgg r1 = r6.zzdrv
            if (r1 == 0) goto L_0x0058
            com.google.android.gms.internal.ads.zzbgg r1 = r6.zzdrv
            com.google.android.gms.internal.ads.zzbhn r1 = r1.zzadl()
            goto L_0x0059
        L_0x0058:
            r1 = r0
        L_0x0059:
            if (r1 == 0) goto L_0x0081
            com.google.android.gms.ads.internal.zzz r2 = new com.google.android.gms.ads.internal.zzz
            r2.<init>(r6, r5)
            r1.zza(r2)
            goto L_0x0081
        L_0x0064:
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzbls
            boolean r5 = r5.zzmk()
            if (r5 == 0) goto L_0x007e
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r5 = com.google.android.gms.internal.ads.zzaan.zzcuc
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r5 = r2.zzd(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0081
        L_0x007e:
            r4.zza(r6, r1)
        L_0x0081:
            com.google.android.gms.internal.ads.zzbgg r5 = r6.zzdrv
            if (r5 == 0) goto L_0x00a5
            com.google.android.gms.internal.ads.zzbgg r5 = r6.zzdrv
            com.google.android.gms.internal.ads.zzbgw r5 = r5.zzabu()
            com.google.android.gms.internal.ads.zzbgg r1 = r6.zzdrv
            com.google.android.gms.internal.ads.zzbhn r1 = r1.zzadl()
            if (r1 == 0) goto L_0x0096
            r1.zzaeg()
        L_0x0096:
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzbls
            com.google.android.gms.internal.ads.zzzw r1 = r1.zzbtj
            if (r1 == 0) goto L_0x00a5
            if (r5 == 0) goto L_0x00a5
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzbls
            com.google.android.gms.internal.ads.zzzw r1 = r1.zzbtj
            r5.zzb(r1)
        L_0x00a5:
            boolean r5 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()
            if (r5 == 0) goto L_0x0142
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzbls
            boolean r5 = r5.zzmj()
            if (r5 == 0) goto L_0x011e
            com.google.android.gms.internal.ads.zzbgg r5 = r6.zzdrv
            if (r5 == 0) goto L_0x0139
            org.json.JSONObject r5 = r6.zzehh
            if (r5 == 0) goto L_0x00c4
            com.google.android.gms.internal.ads.zzrf r5 = r4.zzblu
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzbls
            com.google.android.gms.internal.ads.zzwf r0 = r0.zzbst
            r5.zza(r0, r6)
        L_0x00c4:
            com.google.android.gms.internal.ads.zzbgg r5 = r6.zzdrv
            android.view.View r0 = r5.getView()
            com.google.android.gms.internal.ads.zzsc r5 = new com.google.android.gms.internal.ads.zzsc
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzbls
            android.content.Context r1 = r1.zzsp
            r5.<init>(r1, r0)
            com.google.android.gms.internal.ads.zzaww r1 = com.google.android.gms.ads.internal.zzbv.zzmf()
            com.google.android.gms.ads.internal.zzbw r2 = r4.zzbls
            android.content.Context r2 = r2.zzsp
            boolean r1 = r1.zzv(r2)
            if (r1 == 0) goto L_0x0103
            com.google.android.gms.internal.ads.zzwb r1 = r6.zzdwg
            boolean r1 = zza(r1)
            if (r1 == 0) goto L_0x0103
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzbls
            java.lang.String r1 = r1.zzbsn
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0103
            com.google.android.gms.internal.ads.zzawv r1 = new com.google.android.gms.internal.ads.zzawv
            com.google.android.gms.ads.internal.zzbw r2 = r4.zzbls
            android.content.Context r2 = r2.zzsp
            com.google.android.gms.ads.internal.zzbw r3 = r4.zzbls
            java.lang.String r3 = r3.zzbsn
            r1.<init>(r2, r3)
            r5.zza(r1)
        L_0x0103:
            boolean r1 = r6.zzmu()
            if (r1 == 0) goto L_0x010f
            com.google.android.gms.internal.ads.zzbgg r1 = r6.zzdrv
            r5.zza(r1)
            goto L_0x0139
        L_0x010f:
            com.google.android.gms.internal.ads.zzbgg r1 = r6.zzdrv
            com.google.android.gms.internal.ads.zzbhn r1 = r1.zzadl()
            com.google.android.gms.ads.internal.zzaa r2 = new com.google.android.gms.ads.internal.zzaa
            r2.<init>(r5, r6)
            r1.zza(r2)
            goto L_0x0139
        L_0x011e:
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzbls
            android.view.View r5 = r5.zzbtv
            if (r5 == 0) goto L_0x0139
            org.json.JSONObject r5 = r6.zzehh
            if (r5 == 0) goto L_0x0139
            com.google.android.gms.internal.ads.zzrf r5 = r4.zzblu
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzbls
            com.google.android.gms.internal.ads.zzwf r0 = r0.zzbst
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzbls
            android.view.View r1 = r1.zzbtv
            r5.zza(r0, r6, r1)
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzbls
            android.view.View r0 = r5.zzbtv
        L_0x0139:
            boolean r5 = r6.zzdyd
            if (r5 != 0) goto L_0x0142
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzbls
            r5.zzj(r0)
        L_0x0142:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzx.zza(com.google.android.gms.internal.ads.zzaxf, com.google.android.gms.internal.ads.zzaxf):boolean");
    }

    private final boolean zzd(@Nullable zzaxf zzaxf, zzaxf zzaxf2) {
        if (zzaxf2.zzdyd) {
            View zze = zzas.zze(zzaxf2);
            if (zze == null) {
                zzaxz.zzeo("Could not get mediation view");
                return false;
            }
            View nextView = this.zzbls.zzbsq.getNextView();
            if (nextView != null) {
                if (nextView instanceof zzbgg) {
                    ((zzbgg) nextView).destroy();
                }
                this.zzbls.zzbsq.removeView(nextView);
            }
            if (!zzas.zzf(zzaxf2)) {
                try {
                    if (zzbv.zzmf().zzv(this.zzbls.zzsp)) {
                        new zzsc(this.zzbls.zzsp, zze).zza((zzsg) new zzawv(this.zzbls.zzsp, this.zzbls.zzbsn));
                    }
                    if (zzaxf2.zzehl != null) {
                        this.zzbls.zzbsq.setMinimumWidth(zzaxf2.zzehl.widthPixels);
                        this.zzbls.zzbsq.setMinimumHeight(zzaxf2.zzehl.heightPixels);
                    }
                    zzg(zze);
                } catch (Exception e) {
                    zzbv.zzlj().zza(e, "BannerAdManager.swapViews");
                    zzaxz.zzc("Could not add mediation view to view hierarchy.", e);
                    return false;
                }
            }
        } else if (!(zzaxf2.zzehl == null || zzaxf2.zzdrv == null)) {
            zzaxf2.zzdrv.zza(zzbht.zzb(zzaxf2.zzehl));
            this.zzbls.zzbsq.removeAllViews();
            this.zzbls.zzbsq.setMinimumWidth(zzaxf2.zzehl.widthPixels);
            this.zzbls.zzbsq.setMinimumHeight(zzaxf2.zzehl.heightPixels);
            zzg(zzaxf2.zzdrv.getView());
        }
        if (this.zzbls.zzbsq.getChildCount() > 1) {
            this.zzbls.zzbsq.showNext();
        }
        if (zzaxf != null) {
            View nextView2 = this.zzbls.zzbsq.getNextView();
            if (nextView2 instanceof zzbgg) {
                ((zzbgg) nextView2).destroy();
            } else if (nextView2 != null) {
                this.zzbls.zzbsq.removeView(nextView2);
            }
            this.zzbls.zzmi();
        }
        this.zzbls.zzbsq.setVisibility(0);
        return true;
    }

    /* access modifiers changed from: protected */
    public final void zzil() {
        zzbgg zzbgg = this.zzbls.zzbsu != null ? this.zzbls.zzbsu.zzdrv : null;
        if (!this.zzbmz && zzbgg != null) {
            zzc(zzbgg);
        }
        super.zzil();
    }

    private final void zzc(zzbgg zzbgg) {
        if (zzjj()) {
            WebView webView = zzbgg.getWebView();
            if (webView != null) {
                View view = zzbgg.getView();
                if (view != null && zzbv.zzlw().zzk(this.zzbls.zzsp)) {
                    int i = this.zzbls.zzbsp.zzeou;
                    int i2 = this.zzbls.zzbsp.zzeov;
                    StringBuilder sb = new StringBuilder(23);
                    sb.append(i);
                    sb.append(".");
                    sb.append(i2);
                    this.zzblx = zzbv.zzlw().zza(sb.toString(), webView, "", "javascript", zzit());
                    if (this.zzblx != null) {
                        zzbv.zzlw().zza(this.zzblx, view);
                        zzbgg.zzaa(this.zzblx);
                        zzbv.zzlw().zzo(this.zzblx);
                        this.zzbmz = true;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zziu() {
        boolean z;
        zzbv.zzlf();
        if (!zzayh.zzn(this.zzbls.zzsp, "android.permission.INTERNET")) {
            zzwu.zzpv().zza(this.zzbls.zzbsq, this.zzbls.zzbst, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        } else {
            z = true;
        }
        zzbv.zzlf();
        if (!zzayh.zzah(this.zzbls.zzsp)) {
            zzwu.zzpv().zza(this.zzbls.zzbsq, this.zzbls.zzbst, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!z && this.zzbls.zzbsq != null) {
            this.zzbls.zzbsq.setVisibility(0);
        }
        return z;
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzbli = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    public final void onGlobalLayout() {
        zzd(this.zzbls.zzbsu);
    }

    public final void onScrollChanged() {
        zzd(this.zzbls.zzbsu);
    }

    /* access modifiers changed from: protected */
    public final void zza(@Nullable zzaxf zzaxf, boolean z) {
        if (zzjj()) {
            zzbgg zzbgg = zzaxf != null ? zzaxf.zzdrv : null;
            if (zzbgg != null) {
                if (!this.zzbmz) {
                    zzc(zzbgg);
                }
                if (this.zzblx != null) {
                    zzbgg.zza("onSdkImpression", (Map<String, ?>) new ArrayMap<String,Object>());
                }
            }
        }
        super.zza(zzaxf, z);
        if (zzas.zzf(zzaxf)) {
            zzab zzab = new zzab(this);
            if (zzaxf != null && zzas.zzf(zzaxf)) {
                zzbgg zzbgg2 = zzaxf.zzdrv;
                Object view = zzbgg2 != null ? zzbgg2.getView() : null;
                if (view == null) {
                    zzaxz.zzeo("AdWebView is null");
                    return;
                }
                try {
                    List list = zzaxf.zzdnb != null ? zzaxf.zzdnb.zzdll : null;
                    if (list != null) {
                        if (!list.isEmpty()) {
                            zzals zzuu = zzaxf.zzdnc != null ? zzaxf.zzdnc.zzuu() : null;
                            zzalv zzuv = zzaxf.zzdnc != null ? zzaxf.zzdnc.zzuv() : null;
                            if (list.contains(InternalAvidAdSessionContext.AVID_API_LEVEL) && zzuu != null) {
                                zzuu.zzl(ObjectWrapper.wrap(view));
                                if (!zzuu.getOverrideImpressionRecording()) {
                                    zzuu.recordImpression();
                                }
                                zzbgg2.zza("/nativeExpressViewClicked", zzas.zza(zzuu, (zzalv) null, zzab));
                                return;
                            } else if (!list.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES) || zzuv == null) {
                                zzaxz.zzeo("No matching template id and mapper");
                                return;
                            } else {
                                zzuv.zzl(ObjectWrapper.wrap(view));
                                if (!zzuv.getOverrideImpressionRecording()) {
                                    zzuv.recordImpression();
                                }
                                zzbgg2.zza("/nativeExpressViewClicked", zzas.zza((zzals) null, zzuv, zzab));
                                return;
                            }
                        }
                    }
                    zzaxz.zzeo("No template ids present in mediation response");
                } catch (RemoteException e) {
                    zzaxz.zzc("Error occurred while recording impression and registering for clicks", e);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void zzd(@Nullable zzaxf zzaxf) {
        if (zzaxf != null && !zzaxf.zzehi && this.zzbls.zzbsq != null && zzbv.zzlf().zza((View) this.zzbls.zzbsq, this.zzbls.zzsp) && this.zzbls.zzbsq.getGlobalVisibleRect(new Rect(), null)) {
            if (!(zzaxf == null || zzaxf.zzdrv == null || zzaxf.zzdrv.zzadl() == null)) {
                zzaxf.zzdrv.zzadl().zza((zzbhr) null);
            }
            zza(zzaxf, false);
            zzaxf.zzehi = true;
        }
    }

    @Nullable
    public final zzyp getVideoController() {
        Preconditions.checkMainThread("getVideoController must be called from the main thread.");
        if (this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null) {
            return null;
        }
        return this.zzbls.zzbsu.zzdrv.zzabu();
    }

    public final void zzjv() {
        this.zzblr.zzku();
    }
}
