package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Set;

@zzark
public final class zzaoa extends zzaok {
    private static final Set<String> zzdpf = CollectionUtils.setOf("top-left", "top-right", "top-center", TtmlNode.CENTER, "bottom-left", "bottom-right", "bottom-center");
    private final Object mLock = new Object();
    private zzaol zzdgd;
    private final zzbgg zzdin;
    private final Activity zzdow;
    private String zzdpg = "top-right";
    private boolean zzdph = true;
    private int zzdpi = 0;
    private int zzdpj = 0;
    private int zzdpk = 0;
    private int zzdpl = 0;
    private zzbht zzdpm;
    private ImageView zzdpn;
    private LinearLayout zzdpo;
    private PopupWindow zzdpp;
    private RelativeLayout zzdpq;
    private ViewGroup zzdpr;
    private int zzvt = -1;
    private int zzvu = -1;

    public zzaoa(zzbgg zzbgg, zzaol zzaol) {
        super(zzbgg, "resize");
        this.zzdin = zzbgg;
        this.zzdow = zzbgg.zzabw();
        this.zzdgd = zzaol;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzk(java.util.Map<java.lang.String, java.lang.String> r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.mLock
            monitor-enter(r0)
            android.app.Activity r1 = r13.zzdow     // Catch:{ all -> 0x0324 }
            if (r1 != 0) goto L_0x000e
            java.lang.String r14 = "Not an activity context. Cannot resize."
            r13.zzda(r14)     // Catch:{ all -> 0x0324 }
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x000e:
            com.google.android.gms.internal.ads.zzbgg r1 = r13.zzdin     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbht r1 = r1.zzadj()     // Catch:{ all -> 0x0324 }
            if (r1 != 0) goto L_0x001d
            java.lang.String r14 = "Webview is not yet available, size is not set."
            r13.zzda(r14)     // Catch:{ all -> 0x0324 }
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x001d:
            com.google.android.gms.internal.ads.zzbgg r1 = r13.zzdin     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbht r1 = r1.zzadj()     // Catch:{ all -> 0x0324 }
            boolean r1 = r1.zzafb()     // Catch:{ all -> 0x0324 }
            if (r1 == 0) goto L_0x0030
            java.lang.String r14 = "Is interstitial. Cannot resize an interstitial."
            r13.zzda(r14)     // Catch:{ all -> 0x0324 }
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x0030:
            com.google.android.gms.internal.ads.zzbgg r1 = r13.zzdin     // Catch:{ all -> 0x0324 }
            boolean r1 = r1.zzadq()     // Catch:{ all -> 0x0324 }
            if (r1 == 0) goto L_0x003f
            java.lang.String r14 = "Cannot resize an expanded banner."
            r13.zzda(r14)     // Catch:{ all -> 0x0324 }
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x003f:
            java.lang.String r1 = "width"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0324 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0324 }
            if (r1 != 0) goto L_0x005e
            com.google.android.gms.ads.internal.zzbv.zzlf()     // Catch:{ all -> 0x0324 }
            java.lang.String r1 = "width"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0324 }
            int r1 = com.google.android.gms.internal.ads.zzayh.zzdy(r1)     // Catch:{ all -> 0x0324 }
            r13.zzvt = r1     // Catch:{ all -> 0x0324 }
        L_0x005e:
            java.lang.String r1 = "height"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0324 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0324 }
            if (r1 != 0) goto L_0x007d
            com.google.android.gms.ads.internal.zzbv.zzlf()     // Catch:{ all -> 0x0324 }
            java.lang.String r1 = "height"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0324 }
            int r1 = com.google.android.gms.internal.ads.zzayh.zzdy(r1)     // Catch:{ all -> 0x0324 }
            r13.zzvu = r1     // Catch:{ all -> 0x0324 }
        L_0x007d:
            java.lang.String r1 = "offsetX"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0324 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0324 }
            if (r1 != 0) goto L_0x009c
            com.google.android.gms.ads.internal.zzbv.zzlf()     // Catch:{ all -> 0x0324 }
            java.lang.String r1 = "offsetX"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0324 }
            int r1 = com.google.android.gms.internal.ads.zzayh.zzdy(r1)     // Catch:{ all -> 0x0324 }
            r13.zzdpk = r1     // Catch:{ all -> 0x0324 }
        L_0x009c:
            java.lang.String r1 = "offsetY"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0324 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0324 }
            if (r1 != 0) goto L_0x00bb
            com.google.android.gms.ads.internal.zzbv.zzlf()     // Catch:{ all -> 0x0324 }
            java.lang.String r1 = "offsetY"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0324 }
            int r1 = com.google.android.gms.internal.ads.zzayh.zzdy(r1)     // Catch:{ all -> 0x0324 }
            r13.zzdpl = r1     // Catch:{ all -> 0x0324 }
        L_0x00bb:
            java.lang.String r1 = "allowOffscreen"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0324 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0324 }
            if (r1 != 0) goto L_0x00d7
            java.lang.String r1 = "allowOffscreen"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0324 }
            boolean r1 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ all -> 0x0324 }
            r13.zzdph = r1     // Catch:{ all -> 0x0324 }
        L_0x00d7:
            java.lang.String r1 = "customClosePosition"
            java.lang.Object r14 = r14.get(r1)     // Catch:{ all -> 0x0324 }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x0324 }
            boolean r1 = android.text.TextUtils.isEmpty(r14)     // Catch:{ all -> 0x0324 }
            if (r1 != 0) goto L_0x00e7
            r13.zzdpg = r14     // Catch:{ all -> 0x0324 }
        L_0x00e7:
            int r14 = r13.zzvt     // Catch:{ all -> 0x0324 }
            r1 = 1
            r2 = 0
            if (r14 < 0) goto L_0x00f3
            int r14 = r13.zzvu     // Catch:{ all -> 0x0324 }
            if (r14 < 0) goto L_0x00f3
            r14 = 1
            goto L_0x00f4
        L_0x00f3:
            r14 = 0
        L_0x00f4:
            if (r14 != 0) goto L_0x00fd
            java.lang.String r14 = "Invalid width and height options. Cannot resize."
            r13.zzda(r14)     // Catch:{ all -> 0x0324 }
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x00fd:
            android.app.Activity r14 = r13.zzdow     // Catch:{ all -> 0x0324 }
            android.view.Window r14 = r14.getWindow()     // Catch:{ all -> 0x0324 }
            if (r14 == 0) goto L_0x031d
            android.view.View r3 = r14.getDecorView()     // Catch:{ all -> 0x0324 }
            if (r3 != 0) goto L_0x010d
            goto L_0x031d
        L_0x010d:
            int[] r3 = r13.zzvl()     // Catch:{ all -> 0x0324 }
            if (r3 != 0) goto L_0x011a
            java.lang.String r14 = "Resize location out of screen or close button is not visible."
            r13.zzda(r14)     // Catch:{ all -> 0x0324 }
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x011a:
            com.google.android.gms.internal.ads.zzwu.zzpv()     // Catch:{ all -> 0x0324 }
            android.app.Activity r4 = r13.zzdow     // Catch:{ all -> 0x0324 }
            int r5 = r13.zzvt     // Catch:{ all -> 0x0324 }
            int r4 = com.google.android.gms.internal.ads.zzbat.zza(r4, r5)     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzwu.zzpv()     // Catch:{ all -> 0x0324 }
            android.app.Activity r5 = r13.zzdow     // Catch:{ all -> 0x0324 }
            int r6 = r13.zzvu     // Catch:{ all -> 0x0324 }
            int r5 = com.google.android.gms.internal.ads.zzbat.zza(r5, r6)     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbgg r6 = r13.zzdin     // Catch:{ all -> 0x0324 }
            android.view.View r6 = r6.getView()     // Catch:{ all -> 0x0324 }
            android.view.ViewParent r6 = r6.getParent()     // Catch:{ all -> 0x0324 }
            if (r6 == 0) goto L_0x0316
            boolean r7 = r6 instanceof android.view.ViewGroup     // Catch:{ all -> 0x0324 }
            if (r7 == 0) goto L_0x0316
            r7 = r6
            android.view.ViewGroup r7 = (android.view.ViewGroup) r7     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbgg r8 = r13.zzdin     // Catch:{ all -> 0x0324 }
            android.view.View r8 = r8.getView()     // Catch:{ all -> 0x0324 }
            r7.removeView(r8)     // Catch:{ all -> 0x0324 }
            android.widget.PopupWindow r7 = r13.zzdpp     // Catch:{ all -> 0x0324 }
            if (r7 != 0) goto L_0x017f
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6     // Catch:{ all -> 0x0324 }
            r13.zzdpr = r6     // Catch:{ all -> 0x0324 }
            com.google.android.gms.ads.internal.zzbv.zzlf()     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbgg r6 = r13.zzdin     // Catch:{ all -> 0x0324 }
            android.view.View r6 = r6.getView()     // Catch:{ all -> 0x0324 }
            android.graphics.Bitmap r6 = com.google.android.gms.internal.ads.zzayh.zzt(r6)     // Catch:{ all -> 0x0324 }
            android.widget.ImageView r7 = new android.widget.ImageView     // Catch:{ all -> 0x0324 }
            android.app.Activity r8 = r13.zzdow     // Catch:{ all -> 0x0324 }
            r7.<init>(r8)     // Catch:{ all -> 0x0324 }
            r13.zzdpn = r7     // Catch:{ all -> 0x0324 }
            android.widget.ImageView r7 = r13.zzdpn     // Catch:{ all -> 0x0324 }
            r7.setImageBitmap(r6)     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbgg r6 = r13.zzdin     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbht r6 = r6.zzadj()     // Catch:{ all -> 0x0324 }
            r13.zzdpm = r6     // Catch:{ all -> 0x0324 }
            android.view.ViewGroup r6 = r13.zzdpr     // Catch:{ all -> 0x0324 }
            android.widget.ImageView r7 = r13.zzdpn     // Catch:{ all -> 0x0324 }
            r6.addView(r7)     // Catch:{ all -> 0x0324 }
            goto L_0x0184
        L_0x017f:
            android.widget.PopupWindow r6 = r13.zzdpp     // Catch:{ all -> 0x0324 }
            r6.dismiss()     // Catch:{ all -> 0x0324 }
        L_0x0184:
            android.widget.RelativeLayout r6 = new android.widget.RelativeLayout     // Catch:{ all -> 0x0324 }
            android.app.Activity r7 = r13.zzdow     // Catch:{ all -> 0x0324 }
            r6.<init>(r7)     // Catch:{ all -> 0x0324 }
            r13.zzdpq = r6     // Catch:{ all -> 0x0324 }
            android.widget.RelativeLayout r6 = r13.zzdpq     // Catch:{ all -> 0x0324 }
            r6.setBackgroundColor(r2)     // Catch:{ all -> 0x0324 }
            android.widget.RelativeLayout r6 = r13.zzdpq     // Catch:{ all -> 0x0324 }
            android.view.ViewGroup$LayoutParams r7 = new android.view.ViewGroup$LayoutParams     // Catch:{ all -> 0x0324 }
            r7.<init>(r4, r5)     // Catch:{ all -> 0x0324 }
            r6.setLayoutParams(r7)     // Catch:{ all -> 0x0324 }
            com.google.android.gms.ads.internal.zzbv.zzlf()     // Catch:{ all -> 0x0324 }
            android.widget.RelativeLayout r6 = r13.zzdpq     // Catch:{ all -> 0x0324 }
            android.widget.PopupWindow r6 = com.google.android.gms.internal.ads.zzayh.zza(r6, r4, r5, r2)     // Catch:{ all -> 0x0324 }
            r13.zzdpp = r6     // Catch:{ all -> 0x0324 }
            android.widget.PopupWindow r6 = r13.zzdpp     // Catch:{ all -> 0x0324 }
            r6.setOutsideTouchable(r1)     // Catch:{ all -> 0x0324 }
            android.widget.PopupWindow r6 = r13.zzdpp     // Catch:{ all -> 0x0324 }
            r6.setTouchable(r1)     // Catch:{ all -> 0x0324 }
            android.widget.PopupWindow r6 = r13.zzdpp     // Catch:{ all -> 0x0324 }
            boolean r7 = r13.zzdph     // Catch:{ all -> 0x0324 }
            if (r7 != 0) goto L_0x01b9
            r7 = 1
            goto L_0x01ba
        L_0x01b9:
            r7 = 0
        L_0x01ba:
            r6.setClippingEnabled(r7)     // Catch:{ all -> 0x0324 }
            android.widget.RelativeLayout r6 = r13.zzdpq     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbgg r7 = r13.zzdin     // Catch:{ all -> 0x0324 }
            android.view.View r7 = r7.getView()     // Catch:{ all -> 0x0324 }
            r8 = -1
            r6.addView(r7, r8, r8)     // Catch:{ all -> 0x0324 }
            android.widget.LinearLayout r6 = new android.widget.LinearLayout     // Catch:{ all -> 0x0324 }
            android.app.Activity r7 = r13.zzdow     // Catch:{ all -> 0x0324 }
            r6.<init>(r7)     // Catch:{ all -> 0x0324 }
            r13.zzdpo = r6     // Catch:{ all -> 0x0324 }
            android.widget.RelativeLayout$LayoutParams r6 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzwu.zzpv()     // Catch:{ all -> 0x0324 }
            android.app.Activity r7 = r13.zzdow     // Catch:{ all -> 0x0324 }
            r9 = 50
            int r7 = com.google.android.gms.internal.ads.zzbat.zza(r7, r9)     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzwu.zzpv()     // Catch:{ all -> 0x0324 }
            android.app.Activity r10 = r13.zzdow     // Catch:{ all -> 0x0324 }
            int r9 = com.google.android.gms.internal.ads.zzbat.zza(r10, r9)     // Catch:{ all -> 0x0324 }
            r6.<init>(r7, r9)     // Catch:{ all -> 0x0324 }
            java.lang.String r7 = r13.zzdpg     // Catch:{ all -> 0x0324 }
            int r9 = r7.hashCode()     // Catch:{ all -> 0x0324 }
            switch(r9) {
                case -1364013995: goto L_0x0227;
                case -1012429441: goto L_0x021d;
                case -655373719: goto L_0x0213;
                case 1163912186: goto L_0x0209;
                case 1288627767: goto L_0x01ff;
                case 1755462605: goto L_0x01f5;
                default: goto L_0x01f4;
            }     // Catch:{ all -> 0x0324 }
        L_0x01f4:
            goto L_0x0231
        L_0x01f5:
            java.lang.String r9 = "top-center"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0324 }
            if (r7 == 0) goto L_0x0231
            r7 = 1
            goto L_0x0232
        L_0x01ff:
            java.lang.String r9 = "bottom-center"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0324 }
            if (r7 == 0) goto L_0x0231
            r7 = 4
            goto L_0x0232
        L_0x0209:
            java.lang.String r9 = "bottom-right"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0324 }
            if (r7 == 0) goto L_0x0231
            r7 = 5
            goto L_0x0232
        L_0x0213:
            java.lang.String r9 = "bottom-left"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0324 }
            if (r7 == 0) goto L_0x0231
            r7 = 3
            goto L_0x0232
        L_0x021d:
            java.lang.String r9 = "top-left"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0324 }
            if (r7 == 0) goto L_0x0231
            r7 = 0
            goto L_0x0232
        L_0x0227:
            java.lang.String r9 = "center"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0324 }
            if (r7 == 0) goto L_0x0231
            r7 = 2
            goto L_0x0232
        L_0x0231:
            r7 = -1
        L_0x0232:
            r8 = 14
            r9 = 9
            r10 = 11
            r11 = 12
            r12 = 10
            switch(r7) {
                case 0: goto L_0x0265;
                case 1: goto L_0x025e;
                case 2: goto L_0x0258;
                case 3: goto L_0x0251;
                case 4: goto L_0x024a;
                case 5: goto L_0x0243;
                default: goto L_0x023f;
            }     // Catch:{ all -> 0x0324 }
        L_0x023f:
            r6.addRule(r12)     // Catch:{ all -> 0x0324 }
            goto L_0x026c
        L_0x0243:
            r6.addRule(r11)     // Catch:{ all -> 0x0324 }
            r6.addRule(r10)     // Catch:{ all -> 0x0324 }
            goto L_0x026f
        L_0x024a:
            r6.addRule(r11)     // Catch:{ all -> 0x0324 }
            r6.addRule(r8)     // Catch:{ all -> 0x0324 }
            goto L_0x026f
        L_0x0251:
            r6.addRule(r11)     // Catch:{ all -> 0x0324 }
            r6.addRule(r9)     // Catch:{ all -> 0x0324 }
            goto L_0x026f
        L_0x0258:
            r7 = 13
            r6.addRule(r7)     // Catch:{ all -> 0x0324 }
            goto L_0x026f
        L_0x025e:
            r6.addRule(r12)     // Catch:{ all -> 0x0324 }
            r6.addRule(r8)     // Catch:{ all -> 0x0324 }
            goto L_0x026f
        L_0x0265:
            r6.addRule(r12)     // Catch:{ all -> 0x0324 }
            r6.addRule(r9)     // Catch:{ all -> 0x0324 }
            goto L_0x026f
        L_0x026c:
            r6.addRule(r10)     // Catch:{ all -> 0x0324 }
        L_0x026f:
            android.widget.LinearLayout r7 = r13.zzdpo     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzaob r8 = new com.google.android.gms.internal.ads.zzaob     // Catch:{ all -> 0x0324 }
            r8.<init>(r13)     // Catch:{ all -> 0x0324 }
            r7.setOnClickListener(r8)     // Catch:{ all -> 0x0324 }
            android.widget.LinearLayout r7 = r13.zzdpo     // Catch:{ all -> 0x0324 }
            java.lang.String r8 = "Close button"
            r7.setContentDescription(r8)     // Catch:{ all -> 0x0324 }
            android.widget.RelativeLayout r7 = r13.zzdpq     // Catch:{ all -> 0x0324 }
            android.widget.LinearLayout r8 = r13.zzdpo     // Catch:{ all -> 0x0324 }
            r7.addView(r8, r6)     // Catch:{ all -> 0x0324 }
            android.widget.PopupWindow r6 = r13.zzdpp     // Catch:{ RuntimeException -> 0x02ce }
            android.view.View r14 = r14.getDecorView()     // Catch:{ RuntimeException -> 0x02ce }
            com.google.android.gms.internal.ads.zzwu.zzpv()     // Catch:{ RuntimeException -> 0x02ce }
            android.app.Activity r7 = r13.zzdow     // Catch:{ RuntimeException -> 0x02ce }
            r8 = r3[r2]     // Catch:{ RuntimeException -> 0x02ce }
            int r7 = com.google.android.gms.internal.ads.zzbat.zza(r7, r8)     // Catch:{ RuntimeException -> 0x02ce }
            com.google.android.gms.internal.ads.zzwu.zzpv()     // Catch:{ RuntimeException -> 0x02ce }
            android.app.Activity r8 = r13.zzdow     // Catch:{ RuntimeException -> 0x02ce }
            r9 = r3[r1]     // Catch:{ RuntimeException -> 0x02ce }
            int r8 = com.google.android.gms.internal.ads.zzbat.zza(r8, r9)     // Catch:{ RuntimeException -> 0x02ce }
            r6.showAtLocation(r14, r2, r7, r8)     // Catch:{ RuntimeException -> 0x02ce }
            r14 = r3[r2]     // Catch:{ all -> 0x0324 }
            r6 = r3[r1]     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzaol r7 = r13.zzdgd     // Catch:{ all -> 0x0324 }
            if (r7 == 0) goto L_0x02b7
            com.google.android.gms.internal.ads.zzaol r7 = r13.zzdgd     // Catch:{ all -> 0x0324 }
            int r8 = r13.zzvt     // Catch:{ all -> 0x0324 }
            int r9 = r13.zzvu     // Catch:{ all -> 0x0324 }
            r7.zza(r14, r6, r8, r9)     // Catch:{ all -> 0x0324 }
        L_0x02b7:
            com.google.android.gms.internal.ads.zzbgg r14 = r13.zzdin     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbht r4 = com.google.android.gms.internal.ads.zzbht.zzr(r4, r5)     // Catch:{ all -> 0x0324 }
            r14.zza(r4)     // Catch:{ all -> 0x0324 }
            r14 = r3[r2]     // Catch:{ all -> 0x0324 }
            r1 = r3[r1]     // Catch:{ all -> 0x0324 }
            r13.zzh(r14, r1)     // Catch:{ all -> 0x0324 }
            java.lang.String r14 = "resized"
            r13.zzdc(r14)     // Catch:{ all -> 0x0324 }
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x02ce:
            r14 = move-exception
            java.lang.String r1 = "Cannot show popup window: "
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x0324 }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ all -> 0x0324 }
            int r2 = r14.length()     // Catch:{ all -> 0x0324 }
            if (r2 == 0) goto L_0x02e4
            java.lang.String r14 = r1.concat(r14)     // Catch:{ all -> 0x0324 }
            goto L_0x02e9
        L_0x02e4:
            java.lang.String r14 = new java.lang.String     // Catch:{ all -> 0x0324 }
            r14.<init>(r1)     // Catch:{ all -> 0x0324 }
        L_0x02e9:
            r13.zzda(r14)     // Catch:{ all -> 0x0324 }
            android.widget.RelativeLayout r14 = r13.zzdpq     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbgg r1 = r13.zzdin     // Catch:{ all -> 0x0324 }
            android.view.View r1 = r1.getView()     // Catch:{ all -> 0x0324 }
            r14.removeView(r1)     // Catch:{ all -> 0x0324 }
            android.view.ViewGroup r14 = r13.zzdpr     // Catch:{ all -> 0x0324 }
            if (r14 == 0) goto L_0x0314
            android.view.ViewGroup r14 = r13.zzdpr     // Catch:{ all -> 0x0324 }
            android.widget.ImageView r1 = r13.zzdpn     // Catch:{ all -> 0x0324 }
            r14.removeView(r1)     // Catch:{ all -> 0x0324 }
            android.view.ViewGroup r14 = r13.zzdpr     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbgg r1 = r13.zzdin     // Catch:{ all -> 0x0324 }
            android.view.View r1 = r1.getView()     // Catch:{ all -> 0x0324 }
            r14.addView(r1)     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbgg r14 = r13.zzdin     // Catch:{ all -> 0x0324 }
            com.google.android.gms.internal.ads.zzbht r1 = r13.zzdpm     // Catch:{ all -> 0x0324 }
            r14.zza(r1)     // Catch:{ all -> 0x0324 }
        L_0x0314:
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x0316:
            java.lang.String r14 = "Webview is detached, probably in the middle of a resize or expand."
            r13.zzda(r14)     // Catch:{ all -> 0x0324 }
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x031d:
            java.lang.String r14 = "Activity context is not ready, cannot get window or decor view."
            r13.zzda(r14)     // Catch:{ all -> 0x0324 }
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            return
        L_0x0324:
            r14 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0324 }
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaoa.zzk(java.util.Map):void");
    }

    public final void zzx(boolean z) {
        synchronized (this.mLock) {
            if (this.zzdpp != null) {
                this.zzdpp.dismiss();
                this.zzdpq.removeView(this.zzdin.getView());
                if (this.zzdpr != null) {
                    this.zzdpr.removeView(this.zzdpn);
                    this.zzdpr.addView(this.zzdin.getView());
                    this.zzdin.zza(this.zzdpm);
                }
                if (z) {
                    zzdc("default");
                    if (this.zzdgd != null) {
                        this.zzdgd.zzjk();
                    }
                }
                this.zzdpp = null;
                this.zzdpq = null;
                this.zzdpr = null;
                this.zzdpo = null;
            }
        }
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int[] zzvl() {
        /*
            r9 = this;
            com.google.android.gms.internal.ads.zzayh r0 = com.google.android.gms.ads.internal.zzbv.zzlf()
            android.app.Activity r1 = r9.zzdow
            int[] r0 = r0.zzh(r1)
            com.google.android.gms.internal.ads.zzayh r1 = com.google.android.gms.ads.internal.zzbv.zzlf()
            android.app.Activity r2 = r9.zzdow
            int[] r1 = r1.zzi(r2)
            r2 = 0
            r3 = r0[r2]
            r4 = 1
            r0 = r0[r4]
            int r5 = r9.zzvt
            r6 = 2
            r7 = 50
            if (r5 < r7) goto L_0x0119
            if (r5 <= r3) goto L_0x0025
            goto L_0x0119
        L_0x0025:
            int r8 = r9.zzvu
            if (r8 < r7) goto L_0x0112
            if (r8 <= r0) goto L_0x002d
            goto L_0x0112
        L_0x002d:
            if (r8 != r0) goto L_0x0039
            if (r5 != r3) goto L_0x0039
            java.lang.String r0 = "Cannot resize to a full-screen ad."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)
            r0 = 0
            goto L_0x011f
        L_0x0039:
            boolean r0 = r9.zzdph
            if (r0 == 0) goto L_0x0110
            java.lang.String r0 = r9.zzdpg
            r5 = -1
            int r8 = r0.hashCode()
            switch(r8) {
                case -1364013995: goto L_0x007a;
                case -1012429441: goto L_0x0070;
                case -655373719: goto L_0x0066;
                case 1163912186: goto L_0x005c;
                case 1288627767: goto L_0x0052;
                case 1755462605: goto L_0x0048;
                default: goto L_0x0047;
            }
        L_0x0047:
            goto L_0x0084
        L_0x0048:
            java.lang.String r8 = "top-center"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0084
            r0 = 1
            goto L_0x0085
        L_0x0052:
            java.lang.String r8 = "bottom-center"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0084
            r0 = 4
            goto L_0x0085
        L_0x005c:
            java.lang.String r8 = "bottom-right"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0084
            r0 = 5
            goto L_0x0085
        L_0x0066:
            java.lang.String r8 = "bottom-left"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0084
            r0 = 3
            goto L_0x0085
        L_0x0070:
            java.lang.String r8 = "top-left"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0084
            r0 = 0
            goto L_0x0085
        L_0x007a:
            java.lang.String r8 = "center"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0084
            r0 = 2
            goto L_0x0085
        L_0x0084:
            r0 = -1
        L_0x0085:
            switch(r0) {
                case 0: goto L_0x00f6;
                case 1: goto L_0x00e5;
                case 2: goto L_0x00ce;
                case 3: goto L_0x00bf;
                case 4: goto L_0x00aa;
                case 5: goto L_0x0097;
                default: goto L_0x0088;
            }
        L_0x0088:
            int r0 = r9.zzdpi
            int r5 = r9.zzdpk
            int r0 = r0 + r5
            int r5 = r9.zzvt
            int r0 = r0 + r5
            int r0 = r0 - r7
            int r5 = r9.zzdpj
            int r8 = r9.zzdpl
            int r5 = r5 + r8
            goto L_0x0100
        L_0x0097:
            int r0 = r9.zzdpi
            int r5 = r9.zzdpk
            int r0 = r0 + r5
            int r5 = r9.zzvt
            int r0 = r0 + r5
            int r0 = r0 - r7
            int r5 = r9.zzdpj
            int r8 = r9.zzdpl
            int r5 = r5 + r8
            int r8 = r9.zzvu
            int r5 = r5 + r8
            int r5 = r5 - r7
            goto L_0x0100
        L_0x00aa:
            int r0 = r9.zzdpi
            int r5 = r9.zzdpk
            int r0 = r0 + r5
            int r5 = r9.zzvt
            int r5 = r5 / r6
            int r0 = r0 + r5
            int r0 = r0 + -25
            int r5 = r9.zzdpj
            int r8 = r9.zzdpl
            int r5 = r5 + r8
            int r8 = r9.zzvu
            int r5 = r5 + r8
            int r5 = r5 - r7
            goto L_0x0100
        L_0x00bf:
            int r0 = r9.zzdpi
            int r5 = r9.zzdpk
            int r0 = r0 + r5
            int r5 = r9.zzdpj
            int r8 = r9.zzdpl
            int r5 = r5 + r8
            int r8 = r9.zzvu
            int r5 = r5 + r8
            int r5 = r5 - r7
            goto L_0x0100
        L_0x00ce:
            int r0 = r9.zzdpi
            int r5 = r9.zzdpk
            int r0 = r0 + r5
            int r5 = r9.zzvt
            int r5 = r5 / r6
            int r0 = r0 + r5
            int r0 = r0 + -25
            int r5 = r9.zzdpj
            int r8 = r9.zzdpl
            int r5 = r5 + r8
            int r8 = r9.zzvu
            int r8 = r8 / r6
            int r5 = r5 + r8
            int r5 = r5 + -25
            goto L_0x0100
        L_0x00e5:
            int r0 = r9.zzdpi
            int r5 = r9.zzdpk
            int r0 = r0 + r5
            int r5 = r9.zzvt
            int r5 = r5 / r6
            int r0 = r0 + r5
            int r0 = r0 + -25
            int r5 = r9.zzdpj
            int r8 = r9.zzdpl
            int r5 = r5 + r8
            goto L_0x0100
        L_0x00f6:
            int r0 = r9.zzdpi
            int r5 = r9.zzdpk
            int r0 = r0 + r5
            int r5 = r9.zzdpj
            int r8 = r9.zzdpl
            int r5 = r5 + r8
        L_0x0100:
            if (r0 < 0) goto L_0x010e
            int r0 = r0 + r7
            if (r0 > r3) goto L_0x010e
            r0 = r1[r2]
            if (r5 < r0) goto L_0x010e
            int r5 = r5 + r7
            r0 = r1[r4]
            if (r5 <= r0) goto L_0x0110
        L_0x010e:
            r0 = 0
            goto L_0x011f
        L_0x0110:
            r0 = 1
            goto L_0x011f
        L_0x0112:
            java.lang.String r0 = "Height is too small or too large."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)
            r0 = 0
            goto L_0x011f
        L_0x0119:
            java.lang.String r0 = "Width is too small or too large."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)
            r0 = 0
        L_0x011f:
            if (r0 != 0) goto L_0x0123
            r0 = 0
            return r0
        L_0x0123:
            boolean r0 = r9.zzdph
            if (r0 == 0) goto L_0x0138
            int[] r0 = new int[r6]
            int r1 = r9.zzdpi
            int r3 = r9.zzdpk
            int r1 = r1 + r3
            r0[r2] = r1
            int r1 = r9.zzdpj
            int r2 = r9.zzdpl
            int r1 = r1 + r2
            r0[r4] = r1
            return r0
        L_0x0138:
            com.google.android.gms.internal.ads.zzayh r0 = com.google.android.gms.ads.internal.zzbv.zzlf()
            android.app.Activity r1 = r9.zzdow
            int[] r0 = r0.zzh(r1)
            com.google.android.gms.internal.ads.zzayh r1 = com.google.android.gms.ads.internal.zzbv.zzlf()
            android.app.Activity r3 = r9.zzdow
            int[] r1 = r1.zzi(r3)
            r0 = r0[r2]
            int r3 = r9.zzdpi
            int r5 = r9.zzdpk
            int r3 = r3 + r5
            int r5 = r9.zzdpj
            int r7 = r9.zzdpl
            int r5 = r5 + r7
            if (r3 >= 0) goto L_0x015c
            r0 = 0
            goto L_0x0165
        L_0x015c:
            int r7 = r9.zzvt
            int r8 = r3 + r7
            if (r8 <= r0) goto L_0x0164
            int r0 = r0 - r7
            goto L_0x0165
        L_0x0164:
            r0 = r3
        L_0x0165:
            r3 = r1[r2]
            if (r5 >= r3) goto L_0x016c
            r5 = r1[r2]
            goto L_0x0178
        L_0x016c:
            int r3 = r9.zzvu
            int r7 = r5 + r3
            r8 = r1[r4]
            if (r7 <= r8) goto L_0x0178
            r1 = r1[r4]
            int r5 = r1 - r3
        L_0x0178:
            int[] r1 = new int[r6]
            r1[r2] = r0
            r1[r4] = r5
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaoa.zzvl():int[]");
    }

    public final void zza(int i, int i2, boolean z) {
        synchronized (this.mLock) {
            this.zzdpi = i;
            this.zzdpj = i2;
            if (this.zzdpp != null && z) {
                int[] zzvl = zzvl();
                if (zzvl != null) {
                    PopupWindow popupWindow = this.zzdpp;
                    zzwu.zzpv();
                    int zza = zzbat.zza((Context) this.zzdow, zzvl[0]);
                    zzwu.zzpv();
                    popupWindow.update(zza, zzbat.zza((Context) this.zzdow, zzvl[1]), this.zzdpp.getWidth(), this.zzdpp.getHeight());
                    zzh(zzvl[0], zzvl[1]);
                } else {
                    zzx(true);
                }
            }
        }
    }

    private final void zzh(int i, int i2) {
        zzb(i, i2 - zzbv.zzlf().zzi(this.zzdow)[0], this.zzvt, this.zzvu);
    }

    public final boolean zzvm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdpp != null;
        }
        return z;
    }

    public final void zzi(int i, int i2) {
        this.zzdpi = i;
        this.zzdpj = i2;
    }
}
