package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaoq;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzwu;
import java.util.Collections;

@zzark
public class zzd extends zzaoq implements zzw {
    @VisibleForTesting
    private static final int zzdqt = Color.argb(0, 0, 0, 0);
    protected final Activity mActivity;
    @VisibleForTesting
    zzbgg zzdin;
    @VisibleForTesting
    AdOverlayInfoParcel zzdqu;
    @VisibleForTesting
    private zzi zzdqv;
    @VisibleForTesting
    private zzo zzdqw;
    @VisibleForTesting
    private boolean zzdqx = false;
    @VisibleForTesting
    private FrameLayout zzdqy;
    @VisibleForTesting
    private CustomViewCallback zzdqz;
    @VisibleForTesting
    private boolean zzdra = false;
    @VisibleForTesting
    private boolean zzdrb = false;
    @VisibleForTesting
    private zzh zzdrc;
    @VisibleForTesting
    private boolean zzdrd = false;
    @VisibleForTesting
    int zzdre = 0;
    private final Object zzdrf = new Object();
    private Runnable zzdrg;
    private boolean zzdrh;
    private boolean zzdri;
    private boolean zzdrj = false;
    private boolean zzdrk = false;
    private boolean zzdrl = true;

    public zzd(Activity activity) {
        this.mActivity = activity;
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
    }

    public final void onRestart() {
    }

    public final void close() {
        this.zzdre = 2;
        this.mActivity.finish();
    }

    public final void zzvo() {
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzdqu;
        if (adOverlayInfoParcel != null && this.zzdqx) {
            setRequestedOrientation(adOverlayInfoParcel.orientation);
        }
        if (this.zzdqy != null) {
            this.mActivity.setContentView(this.zzdrc);
            this.zzdri = true;
            this.zzdqy.removeAllViews();
            this.zzdqy = null;
        }
        CustomViewCallback customViewCallback = this.zzdqz;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
            this.zzdqz = null;
        }
        this.zzdqx = false;
    }

    public final void zzvp() {
        this.zzdre = 1;
        this.mActivity.finish();
    }

    public final void onBackPressed() {
        this.zzdre = 0;
    }

    public final boolean zzvq() {
        this.zzdre = 0;
        zzbgg zzbgg = this.zzdin;
        if (zzbgg == null) {
            return true;
        }
        boolean zzads = zzbgg.zzads();
        if (!zzads) {
            this.zzdin.zza("onbackblocked", Collections.emptyMap());
        }
        return zzads;
    }

    public void onCreate(Bundle bundle) {
        this.mActivity.requestWindowFeature(1);
        this.zzdra = bundle != null ? bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;
        try {
            this.zzdqu = AdOverlayInfoParcel.zzc(this.mActivity.getIntent());
            if (this.zzdqu != null) {
                if (this.zzdqu.zzbsp.zzeov > 7500000) {
                    this.zzdre = 3;
                }
                if (this.mActivity.getIntent() != null) {
                    this.zzdrl = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
                }
                if (this.zzdqu.zzdsc != null) {
                    this.zzdrb = this.zzdqu.zzdsc.zzbpa;
                } else {
                    this.zzdrb = false;
                }
                if (this.zzdrb && this.zzdqu.zzdsc.zzbpf != -1) {
                    new zzj(this, null).zzyz();
                }
                if (bundle == null) {
                    if (this.zzdqu.zzdru != null && this.zzdrl) {
                        this.zzdqu.zzdru.zziw();
                    }
                    if (!(this.zzdqu.zzdsa == 1 || this.zzdqu.zzdrt == null)) {
                        this.zzdqu.zzdrt.onAdClicked();
                    }
                }
                this.zzdrc = new zzh(this.mActivity, this.zzdqu.zzdsb, this.zzdqu.zzbsp.zzdp);
                this.zzdrc.setId(1000);
                switch (this.zzdqu.zzdsa) {
                    case 1:
                        zzae(false);
                        return;
                    case 2:
                        this.zzdqv = new zzi(this.zzdqu.zzdrv);
                        zzae(false);
                        return;
                    case 3:
                        zzae(true);
                        return;
                    default:
                        throw new zzg("Could not determine ad overlay type.");
                }
            } else {
                throw new zzg("Could not get info for ad overlay.");
            }
        } catch (zzg e) {
            zzaxz.zzeo(e.getMessage());
            this.zzdre = 3;
            this.mActivity.finish();
        }
    }

    public final void onStart() {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwm)).booleanValue()) {
            zzbgg zzbgg = this.zzdin;
            if (zzbgg == null || zzbgg.isDestroyed()) {
                zzaxz.zzeo("The webview does not exist. Ignoring action.");
            } else {
                zzbv.zzlh();
                zzayp.zzj(this.zzdin);
            }
        }
    }

    public final void onResume() {
        if (this.zzdqu.zzdru != null) {
            this.zzdqu.zzdru.onResume();
        }
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcwm)).booleanValue()) {
            zzbgg zzbgg = this.zzdin;
            if (zzbgg == null || zzbgg.isDestroyed()) {
                zzaxz.zzeo("The webview does not exist. Ignoring action.");
            } else {
                zzbv.zzlh();
                zzayp.zzj(this.zzdin);
            }
        }
    }

    public final void onPause() {
        zzvo();
        if (this.zzdqu.zzdru != null) {
            this.zzdqu.zzdru.onPause();
        }
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcwm)).booleanValue() && this.zzdin != null && (!this.mActivity.isFinishing() || this.zzdqv == null)) {
            zzbv.zzlh();
            zzayp.zzi(this.zzdin);
        }
        zzvs();
    }

    public final void zzq(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwl)).booleanValue() && PlatformVersion.isAtLeastN()) {
            Configuration configuration = (Configuration) ObjectWrapper.unwrap(iObjectWrapper);
            zzbv.zzlf();
            if (zzayh.zza(this.mActivity, configuration)) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(1024);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzdra);
    }

    public final void onStop() {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwm)).booleanValue() && this.zzdin != null && (!this.mActivity.isFinishing() || this.zzdqv == null)) {
            zzbv.zzlh();
            zzayp.zzi(this.zzdin);
        }
        zzvs();
    }

    public final void onDestroy() {
        zzbgg zzbgg = this.zzdin;
        if (zzbgg != null) {
            this.zzdrc.removeView(zzbgg.getView());
        }
        zzvs();
    }

    private final void zzad(boolean z) {
        int intValue = ((Integer) zzwu.zzpz().zzd(zzaan.zzcwo)).intValue();
        zzp zzp = new zzp();
        zzp.size = 50;
        zzp.paddingLeft = z ? intValue : 0;
        zzp.paddingRight = z ? 0 : intValue;
        zzp.paddingTop = 0;
        zzp.paddingBottom = intValue;
        this.zzdqw = new zzo(this.mActivity, zzp, this);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        zza(z, this.zzdqu.zzdrx);
        this.zzdrc.addView(this.zzdqw, layoutParams);
    }

    public final void zzay() {
        this.zzdri = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(boolean r7, boolean r8) {
        /*
            r6 = this;
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzaan.zzcrt
            com.google.android.gms.internal.ads.zzaak r1 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r6.zzdqu
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.zzaq r0 = r0.zzdsc
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r6.zzdqu
            com.google.android.gms.ads.internal.zzaq r0 = r0.zzdsc
            boolean r0 = r0.zzbph
            if (r0 == 0) goto L_0x0026
            r0 = 1
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zzaan.zzcru
            com.google.android.gms.internal.ads.zzaak r4 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r3 = r4.zzd(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r6.zzdqu
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.ads.internal.zzaq r3 = r3.zzdsc
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r6.zzdqu
            com.google.android.gms.ads.internal.zzaq r3 = r3.zzdsc
            boolean r3 = r3.zzbpi
            if (r3 == 0) goto L_0x004b
            r3 = 1
            goto L_0x004c
        L_0x004b:
            r3 = 0
        L_0x004c:
            if (r7 == 0) goto L_0x0062
            if (r8 == 0) goto L_0x0062
            if (r0 == 0) goto L_0x0062
            if (r3 != 0) goto L_0x0062
            com.google.android.gms.internal.ads.zzaok r7 = new com.google.android.gms.internal.ads.zzaok
            com.google.android.gms.internal.ads.zzbgg r4 = r6.zzdin
            java.lang.String r5 = "useCustomClose"
            r7.<init>(r4, r5)
            java.lang.String r4 = "Custom close has been disabled for interstitial ads in this ad slot."
            r7.zzda(r4)
        L_0x0062:
            com.google.android.gms.ads.internal.overlay.zzo r7 = r6.zzdqw
            if (r7 == 0) goto L_0x0071
            if (r3 != 0) goto L_0x006e
            if (r8 == 0) goto L_0x006d
            if (r0 != 0) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r1 = 0
        L_0x006e:
            r7.zzaf(r1)
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.zza(boolean, boolean):void");
    }

    public final void zzvr() {
        this.zzdrc.removeView(this.zzdqw);
        zzad(true);
    }

    public final void setRequestedOrientation(int i) {
        if (this.mActivity.getApplicationInfo().targetSdkVersion >= ((Integer) zzwu.zzpz().zzd(zzaan.zzcyg)).intValue()) {
            if (this.mActivity.getApplicationInfo().targetSdkVersion <= ((Integer) zzwu.zzpz().zzd(zzaan.zzcyh)).intValue()) {
                if (VERSION.SDK_INT >= ((Integer) zzwu.zzpz().zzd(zzaan.zzcyi)).intValue()) {
                    if (VERSION.SDK_INT <= ((Integer) zzwu.zzpz().zzd(zzaan.zzcyj)).intValue()) {
                        return;
                    }
                }
            }
        }
        this.mActivity.setRequestedOrientation(i);
    }

    public final void zza(View view, CustomViewCallback customViewCallback) {
        this.zzdqy = new FrameLayout(this.mActivity);
        this.zzdqy.setBackgroundColor(-16777216);
        this.zzdqy.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzdqy);
        this.zzdri = true;
        this.zzdqz = customViewCallback;
        this.zzdqx = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0124 A[SYNTHETIC, Splitter:B:58:0x0124] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0211  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzae(boolean r18) throws com.google.android.gms.ads.internal.overlay.zzg {
        /*
            r17 = this;
            r1 = r17
            boolean r0 = r1.zzdri
            r2 = 1
            if (r0 != 0) goto L_0x000c
            android.app.Activity r0 = r1.mActivity
            r0.requestWindowFeature(r2)
        L_0x000c:
            android.app.Activity r0 = r1.mActivity
            android.view.Window r0 = r0.getWindow()
            if (r0 == 0) goto L_0x0262
            boolean r3 = com.google.android.gms.common.util.PlatformVersion.isAtLeastN()
            if (r3 == 0) goto L_0x003e
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zzaan.zzcwl
            com.google.android.gms.internal.ads.zzaak r4 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r3 = r4.zzd(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x003e
            com.google.android.gms.ads.internal.zzbv.zzlf()
            android.app.Activity r3 = r1.mActivity
            android.content.res.Resources r4 = r3.getResources()
            android.content.res.Configuration r4 = r4.getConfiguration()
            boolean r3 = com.google.android.gms.internal.ads.zzayh.zza(r3, r4)
            goto L_0x003f
        L_0x003e:
            r3 = 1
        L_0x003f:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r1.zzdqu
            com.google.android.gms.ads.internal.zzaq r4 = r4.zzdsc
            r5 = 0
            if (r4 == 0) goto L_0x0050
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r1.zzdqu
            com.google.android.gms.ads.internal.zzaq r4 = r4.zzdsc
            boolean r4 = r4.zzbpb
            if (r4 == 0) goto L_0x0050
            r4 = 1
            goto L_0x0051
        L_0x0050:
            r4 = 0
        L_0x0051:
            boolean r6 = r1.zzdrb
            if (r6 == 0) goto L_0x0057
            if (r4 == 0) goto L_0x007b
        L_0x0057:
            if (r3 == 0) goto L_0x007b
            r3 = 1024(0x400, float:1.435E-42)
            r0.setFlags(r3, r3)
            boolean r3 = com.google.android.gms.common.util.PlatformVersion.isAtLeastKitKat()
            if (r3 == 0) goto L_0x007b
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzdqu
            com.google.android.gms.ads.internal.zzaq r3 = r3.zzdsc
            if (r3 == 0) goto L_0x007b
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzdqu
            com.google.android.gms.ads.internal.zzaq r3 = r3.zzdsc
            boolean r3 = r3.zzbpg
            if (r3 == 0) goto L_0x007b
            android.view.View r3 = r0.getDecorView()
            r4 = 4098(0x1002, float:5.743E-42)
            r3.setSystemUiVisibility(r4)
        L_0x007b:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzdqu
            com.google.android.gms.internal.ads.zzbgg r3 = r3.zzdrv
            r4 = 0
            if (r3 == 0) goto L_0x008b
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzdqu
            com.google.android.gms.internal.ads.zzbgg r3 = r3.zzdrv
            com.google.android.gms.internal.ads.zzbhn r3 = r3.zzadl()
            goto L_0x008c
        L_0x008b:
            r3 = r4
        L_0x008c:
            if (r3 == 0) goto L_0x0093
            boolean r3 = r3.zzmu()
            goto L_0x0094
        L_0x0093:
            r3 = 0
        L_0x0094:
            r1.zzdrd = r5
            if (r3 == 0) goto L_0x00d8
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r6 = r1.zzdqu
            int r6 = r6.orientation
            com.google.android.gms.internal.ads.zzayp r7 = com.google.android.gms.ads.internal.zzbv.zzlh()
            int r7 = r7.zzzw()
            if (r6 != r7) goto L_0x00b8
            android.app.Activity r6 = r1.mActivity
            android.content.res.Resources r6 = r6.getResources()
            android.content.res.Configuration r6 = r6.getConfiguration()
            int r6 = r6.orientation
            if (r6 != r2) goto L_0x00b5
            r5 = 1
        L_0x00b5:
            r1.zzdrd = r5
            goto L_0x00d8
        L_0x00b8:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r6 = r1.zzdqu
            int r6 = r6.orientation
            com.google.android.gms.internal.ads.zzayp r7 = com.google.android.gms.ads.internal.zzbv.zzlh()
            int r7 = r7.zzzx()
            if (r6 != r7) goto L_0x00d8
            android.app.Activity r6 = r1.mActivity
            android.content.res.Resources r6 = r6.getResources()
            android.content.res.Configuration r6 = r6.getConfiguration()
            int r6 = r6.orientation
            r7 = 2
            if (r6 != r7) goto L_0x00d6
            r5 = 1
        L_0x00d6:
            r1.zzdrd = r5
        L_0x00d8:
            boolean r5 = r1.zzdrd
            r6 = 46
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.String r6 = "Delay onShow to next orientation change: "
            r7.append(r6)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.google.android.gms.internal.ads.zzaxz.zzdn(r5)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r5 = r1.zzdqu
            int r5 = r5.orientation
            r1.setRequestedOrientation(r5)
            com.google.android.gms.internal.ads.zzayp r5 = com.google.android.gms.ads.internal.zzbv.zzlh()
            boolean r0 = r5.zza(r0)
            if (r0 == 0) goto L_0x0106
            java.lang.String r0 = "Hardware acceleration on the AdActivity window enabled."
            com.google.android.gms.internal.ads.zzaxz.zzdn(r0)
        L_0x0106:
            boolean r0 = r1.zzdrb
            if (r0 != 0) goto L_0x0112
            com.google.android.gms.ads.internal.overlay.zzh r0 = r1.zzdrc
            r5 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0.setBackgroundColor(r5)
            goto L_0x0119
        L_0x0112:
            com.google.android.gms.ads.internal.overlay.zzh r0 = r1.zzdrc
            int r5 = zzdqt
            r0.setBackgroundColor(r5)
        L_0x0119:
            android.app.Activity r0 = r1.mActivity
            com.google.android.gms.ads.internal.overlay.zzh r5 = r1.zzdrc
            r0.setContentView(r5)
            r1.zzdri = r2
            if (r18 == 0) goto L_0x01f9
            com.google.android.gms.ads.internal.zzbv.zzlg()     // Catch:{ Exception -> 0x01eb }
            android.app.Activity r6 = r1.mActivity     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv     // Catch:{ Exception -> 0x01eb }
            if (r0 == 0) goto L_0x0139
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.internal.ads.zzbht r0 = r0.zzadj()     // Catch:{ Exception -> 0x01eb }
            r7 = r0
            goto L_0x013a
        L_0x0139:
            r7 = r4
        L_0x013a:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv     // Catch:{ Exception -> 0x01eb }
            if (r0 == 0) goto L_0x014a
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv     // Catch:{ Exception -> 0x01eb }
            java.lang.String r0 = r0.zzadk()     // Catch:{ Exception -> 0x01eb }
            r8 = r0
            goto L_0x014b
        L_0x014a:
            r8 = r4
        L_0x014b:
            r9 = 1
            r11 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.internal.ads.zzbbi r12 = r0.zzbsp     // Catch:{ Exception -> 0x01eb }
            r13 = 0
            r14 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv     // Catch:{ Exception -> 0x01eb }
            if (r0 == 0) goto L_0x0163
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.ads.internal.zzv r0 = r0.zzid()     // Catch:{ Exception -> 0x01eb }
            r15 = r0
            goto L_0x0164
        L_0x0163:
            r15 = r4
        L_0x0164:
            com.google.android.gms.internal.ads.zzum r16 = com.google.android.gms.internal.ads.zzum.zzoi()     // Catch:{ Exception -> 0x01eb }
            r10 = r3
            com.google.android.gms.internal.ads.zzbgg r0 = com.google.android.gms.internal.ads.zzbgm.zza(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x01eb }
            r1.zzdin = r0     // Catch:{ Exception -> 0x01eb }
            com.google.android.gms.internal.ads.zzbgg r0 = r1.zzdin
            com.google.android.gms.internal.ads.zzbhn r5 = r0.zzadl()
            r6 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.ads.internal.gmsg.zzb r7 = r0.zzdsd
            r8 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.ads.internal.gmsg.zzd r9 = r0.zzdrw
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.ads.internal.overlay.zzt r10 = r0.zzdrz
            r11 = 1
            r12 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv
            if (r0 == 0) goto L_0x0199
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv
            com.google.android.gms.internal.ads.zzbhn r0 = r0.zzadl()
            com.google.android.gms.ads.internal.zzw r4 = r0.zzaea()
            r13 = r4
            goto L_0x019a
        L_0x0199:
            r13 = r4
        L_0x019a:
            r14 = 0
            r15 = 0
            r5.zza(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            com.google.android.gms.internal.ads.zzbgg r0 = r1.zzdin
            com.google.android.gms.internal.ads.zzbhn r0 = r0.zzadl()
            com.google.android.gms.ads.internal.overlay.zze r4 = new com.google.android.gms.ads.internal.overlay.zze
            r4.<init>(r1)
            r0.zza(r4)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            java.lang.String r0 = r0.url
            if (r0 == 0) goto L_0x01bd
            com.google.android.gms.internal.ads.zzbgg r0 = r1.zzdin
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r1.zzdqu
            java.lang.String r4 = r4.url
            r0.loadUrl(r4)
            goto L_0x01d5
        L_0x01bd:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            java.lang.String r0 = r0.zzdry
            if (r0 == 0) goto L_0x01e3
            com.google.android.gms.internal.ads.zzbgg r4 = r1.zzdin
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            java.lang.String r5 = r0.zzbde
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            java.lang.String r6 = r0.zzdry
            java.lang.String r7 = "text/html"
            java.lang.String r8 = "UTF-8"
            r9 = 0
            r4.loadDataWithBaseURL(r5, r6, r7, r8, r9)
        L_0x01d5:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv
            if (r0 == 0) goto L_0x0206
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv
            r0.zzb(r1)
            goto L_0x0206
        L_0x01e3:
            com.google.android.gms.ads.internal.overlay.zzg r0 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "No URL or HTML to display in ad overlay."
            r0.<init>(r2)
            throw r0
        L_0x01eb:
            r0 = move-exception
            java.lang.String r2 = "Error obtaining webview."
            com.google.android.gms.internal.ads.zzaxz.zzb(r2, r0)
            com.google.android.gms.ads.internal.overlay.zzg r0 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "Could not obtain webview for the overlay."
            r0.<init>(r2)
            throw r0
        L_0x01f9:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv
            r1.zzdin = r0
            com.google.android.gms.internal.ads.zzbgg r0 = r1.zzdin
            android.app.Activity r4 = r1.mActivity
            r0.zzbo(r4)
        L_0x0206:
            com.google.android.gms.internal.ads.zzbgg r0 = r1.zzdin
            r0.zza(r1)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv
            if (r0 == 0) goto L_0x021e
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzdqu
            com.google.android.gms.internal.ads.zzbgg r0 = r0.zzdrv
            com.google.android.gms.dynamic.IObjectWrapper r0 = r0.zzadp()
            com.google.android.gms.ads.internal.overlay.zzh r4 = r1.zzdrc
            zzb(r0, r4)
        L_0x021e:
            com.google.android.gms.internal.ads.zzbgg r0 = r1.zzdin
            android.view.ViewParent r0 = r0.getParent()
            if (r0 == 0) goto L_0x0235
            boolean r4 = r0 instanceof android.view.ViewGroup
            if (r4 == 0) goto L_0x0235
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            com.google.android.gms.internal.ads.zzbgg r4 = r1.zzdin
            android.view.View r4 = r4.getView()
            r0.removeView(r4)
        L_0x0235:
            boolean r0 = r1.zzdrb
            if (r0 == 0) goto L_0x023e
            com.google.android.gms.internal.ads.zzbgg r0 = r1.zzdin
            r0.zzady()
        L_0x023e:
            com.google.android.gms.ads.internal.overlay.zzh r0 = r1.zzdrc
            com.google.android.gms.internal.ads.zzbgg r4 = r1.zzdin
            android.view.View r4 = r4.getView()
            r5 = -1
            r0.addView(r4, r5, r5)
            if (r18 != 0) goto L_0x0253
            boolean r0 = r1.zzdrd
            if (r0 != 0) goto L_0x0253
            r17.zzvv()
        L_0x0253:
            r1.zzad(r3)
            com.google.android.gms.internal.ads.zzbgg r0 = r1.zzdin
            boolean r0 = r0.zzadn()
            if (r0 == 0) goto L_0x0261
            r1.zza(r3, r2)
        L_0x0261:
            return
        L_0x0262:
            com.google.android.gms.ads.internal.overlay.zzg r0 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "Invalid activity, no window available."
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.zzae(boolean):void");
    }

    private final void zzvs() {
        if (this.mActivity.isFinishing() && !this.zzdrj) {
            this.zzdrj = true;
            zzbgg zzbgg = this.zzdin;
            if (zzbgg != null) {
                zzbgg.zzdh(this.zzdre);
                synchronized (this.zzdrf) {
                    if (!this.zzdrh && this.zzdin.zzadu()) {
                        this.zzdrg = new zzf(this);
                        zzayh.zzelc.postDelayed(this.zzdrg, ((Long) zzwu.zzpz().zzd(zzaan.zzcrs)).longValue());
                        return;
                    }
                }
            }
            zzvt();
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void zzvt() {
        if (!this.zzdrk) {
            this.zzdrk = true;
            zzbgg zzbgg = this.zzdin;
            if (zzbgg != null) {
                this.zzdrc.removeView(zzbgg.getView());
                zzi zzi = this.zzdqv;
                if (zzi != null) {
                    this.zzdin.zzbo(zzi.zzsp);
                    this.zzdin.zzav(false);
                    this.zzdqv.parent.addView(this.zzdin.getView(), this.zzdqv.index, this.zzdqv.zzdrp);
                    this.zzdqv = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzdin.zzbo(this.mActivity.getApplicationContext());
                }
                this.zzdin = null;
            }
            AdOverlayInfoParcel adOverlayInfoParcel = this.zzdqu;
            if (!(adOverlayInfoParcel == null || adOverlayInfoParcel.zzdru == null)) {
                this.zzdqu.zzdru.zziv();
            }
            AdOverlayInfoParcel adOverlayInfoParcel2 = this.zzdqu;
            if (!(adOverlayInfoParcel2 == null || adOverlayInfoParcel2.zzdrv == null)) {
                zzb(this.zzdqu.zzdrv.zzadp(), this.zzdqu.zzdrv.getView());
            }
        }
    }

    private static void zzb(@Nullable IObjectWrapper iObjectWrapper, @Nullable View view) {
        if (iObjectWrapper != null && view != null) {
            zzbv.zzlw().zza(iObjectWrapper, view);
        }
    }

    public final void zzvu() {
        if (this.zzdrd) {
            this.zzdrd = false;
            zzvv();
        }
    }

    private final void zzvv() {
        this.zzdin.zzvv();
    }

    public final void zzvw() {
        this.zzdrc.zzdro = true;
    }

    public final void zzvx() {
        synchronized (this.zzdrf) {
            this.zzdrh = true;
            if (this.zzdrg != null) {
                zzayh.zzelc.removeCallbacks(this.zzdrg);
                zzayh.zzelc.post(this.zzdrg);
            }
        }
    }
}
