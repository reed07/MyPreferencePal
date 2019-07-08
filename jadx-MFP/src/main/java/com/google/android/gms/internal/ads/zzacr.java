package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzacr extends zzadg implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener, zzacw {
    @VisibleForTesting
    private static final String[] zzdcy = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    @Nullable
    @VisibleForTesting
    private FrameLayout zzbld;
    @Nullable
    @VisibleForTesting
    private zzacd zzdbj;
    private final FrameLayout zzdcz;
    private View zzdda;
    @VisibleForTesting
    private Map<String, WeakReference<View>> zzddb = Collections.synchronizedMap(new HashMap());
    @Nullable
    @VisibleForTesting
    private View zzddc;
    @VisibleForTesting
    private boolean zzddd = false;
    @VisibleForTesting
    private Point zzdde = new Point();
    @VisibleForTesting
    private Point zzddf = new Point();
    @VisibleForTesting
    private WeakReference<zzsc> zzddg = new WeakReference<>(null);

    @TargetApi(21)
    public zzacr(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzdcz = frameLayout;
        this.zzbld = frameLayout2;
        zzbv.zzme();
        zzbct.zza((View) this.zzdcz, (OnGlobalLayoutListener) this);
        zzbv.zzme();
        zzbct.zza((View) this.zzdcz, (OnScrollChangedListener) this);
        this.zzdcz.setOnTouchListener(this);
        this.zzdcz.setOnClickListener(this);
        if (frameLayout2 != null && PlatformVersion.isAtLeastLollipop()) {
            frameLayout2.setElevation(Float.MAX_VALUE);
        }
        zzaan.initialize(this.zzdcz.getContext());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r4, com.google.android.gms.dynamic.IObjectWrapper r5) {
        /*
            r3 = this;
            java.lang.Object r5 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r5)
            android.view.View r5 = (android.view.View) r5
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzddb     // Catch:{ all -> 0x0040 }
            if (r1 != 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x000f:
            if (r5 != 0) goto L_0x0017
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r3.zzddb     // Catch:{ all -> 0x0040 }
            r5.remove(r4)     // Catch:{ all -> 0x0040 }
            goto L_0x003c
        L_0x0017:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzddb     // Catch:{ all -> 0x0040 }
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0040 }
            r2.<init>(r5)     // Catch:{ all -> 0x0040 }
            r1.put(r4, r2)     // Catch:{ all -> 0x0040 }
            java.lang.String r1 = "1098"
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0040 }
            if (r1 != 0) goto L_0x003e
            java.lang.String r1 = "3011"
            boolean r4 = r1.equals(r4)     // Catch:{ all -> 0x0040 }
            if (r4 == 0) goto L_0x0032
            goto L_0x003e
        L_0x0032:
            r5.setOnTouchListener(r3)     // Catch:{ all -> 0x0040 }
            r4 = 1
            r5.setClickable(r4)     // Catch:{ all -> 0x0040 }
            r5.setOnClickListener(r3)     // Catch:{ all -> 0x0040 }
        L_0x003c:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x003e:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0040:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacr.zzb(java.lang.String, com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    public final IObjectWrapper zzbm(String str) {
        synchronized (this.mLock) {
            View view = null;
            if (this.zzddb == null) {
                return null;
            }
            WeakReference weakReference = (WeakReference) this.zzddb.get(str);
            if (weakReference != null) {
                view = (View) weakReference.get();
            }
            IObjectWrapper wrap = ObjectWrapper.wrap(view);
            return wrap;
        }
    }

    private final void zzm(@Nullable View view) {
        zzacd zzacd = this.zzdbj;
        if (zzacd != null) {
            if (zzacd instanceof zzacc) {
                zzacd = ((zzacc) zzacd).zzso();
            }
            if (zzacd != null) {
                zzacd.zzm(view);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0214, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x01d2 A[Catch:{ Exception -> 0x0155 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00dd A[Catch:{ Exception -> 0x0155 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0128 A[Catch:{ Exception -> 0x0155 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0149 A[Catch:{ Exception -> 0x0155 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x017d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r12) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.mLock
            monitor-enter(r0)
            r1 = 0
            r11.zzm(r1)     // Catch:{ all -> 0x0218 }
            java.lang.Object r12 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r12)     // Catch:{ all -> 0x0218 }
            boolean r2 = r12 instanceof com.google.android.gms.internal.ads.zzach     // Catch:{ all -> 0x0218 }
            if (r2 != 0) goto L_0x0016
            java.lang.String r12 = "Not an instance of native engine. This is most likely a transient error"
            com.google.android.gms.internal.ads.zzaxz.zzeo(r12)     // Catch:{ all -> 0x0218 }
            monitor-exit(r0)     // Catch:{ all -> 0x0218 }
            return
        L_0x0016:
            r2 = 1
            r11.zzddd = r2     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzach r12 = (com.google.android.gms.internal.ads.zzach) r12     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzacd r3 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x0028
            com.google.android.gms.internal.ads.zzacd r3 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r4 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r11.zzddb     // Catch:{ all -> 0x0218 }
            r3.zzb(r4, r5)     // Catch:{ all -> 0x0218 }
        L_0x0028:
            com.google.android.gms.internal.ads.zzacd r3 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            boolean r3 = r3 instanceof com.google.android.gms.internal.ads.zzach     // Catch:{ all -> 0x0218 }
            r9 = 0
            if (r3 == 0) goto L_0x0063
            com.google.android.gms.internal.ads.zzacd r3 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzach r3 = (com.google.android.gms.internal.ads.zzach) r3     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x0063
            android.content.Context r4 = r3.getContext()     // Catch:{ all -> 0x0218 }
            if (r4 == 0) goto L_0x0063
            com.google.android.gms.internal.ads.zzaww r4 = com.google.android.gms.ads.internal.zzbv.zzmf()     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r5 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            android.content.Context r5 = r5.getContext()     // Catch:{ all -> 0x0218 }
            boolean r4 = r4.zzv(r5)     // Catch:{ all -> 0x0218 }
            if (r4 == 0) goto L_0x0063
            com.google.android.gms.internal.ads.zzawv r3 = r3.zzsu()     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x0054
            r3.zzai(r9)     // Catch:{ all -> 0x0218 }
        L_0x0054:
            java.lang.ref.WeakReference<com.google.android.gms.internal.ads.zzsc> r4 = r11.zzddg     // Catch:{ all -> 0x0218 }
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzsc r4 = (com.google.android.gms.internal.ads.zzsc) r4     // Catch:{ all -> 0x0218 }
            if (r4 == 0) goto L_0x0063
            if (r3 == 0) goto L_0x0063
            r4.zzb(r3)     // Catch:{ all -> 0x0218 }
        L_0x0063:
            com.google.android.gms.internal.ads.zzacd r3 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            boolean r3 = r3 instanceof com.google.android.gms.internal.ads.zzacc     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x007b
            com.google.android.gms.internal.ads.zzacd r3 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzacc r3 = (com.google.android.gms.internal.ads.zzacc) r3     // Catch:{ all -> 0x0218 }
            boolean r3 = r3.zzsn()     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x007b
            com.google.android.gms.internal.ads.zzacd r3 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzacc r3 = (com.google.android.gms.internal.ads.zzacc) r3     // Catch:{ all -> 0x0218 }
            r3.zzc(r12)     // Catch:{ all -> 0x0218 }
            goto L_0x0087
        L_0x007b:
            r11.zzdbj = r12     // Catch:{ all -> 0x0218 }
            boolean r3 = r12 instanceof com.google.android.gms.internal.ads.zzacc     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x0087
            r3 = r12
            com.google.android.gms.internal.ads.zzacc r3 = (com.google.android.gms.internal.ads.zzacc) r3     // Catch:{ all -> 0x0218 }
            r3.zzc(r1)     // Catch:{ all -> 0x0218 }
        L_0x0087:
            android.widget.FrameLayout r3 = r11.zzbld     // Catch:{ all -> 0x0218 }
            if (r3 != 0) goto L_0x008d
            monitor-exit(r0)     // Catch:{ all -> 0x0218 }
            return
        L_0x008d:
            android.widget.FrameLayout r3 = r11.zzbld     // Catch:{ all -> 0x0218 }
            r3.setClickable(r9)     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r3 = r11.zzbld     // Catch:{ all -> 0x0218 }
            r3.removeAllViews()     // Catch:{ all -> 0x0218 }
            boolean r3 = r12.zzsj()     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x00cb
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r11.zzddb     // Catch:{ all -> 0x0218 }
            if (r4 == 0) goto L_0x00c3
            java.lang.String r4 = "1098"
            java.lang.String r5 = "3011"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5}     // Catch:{ all -> 0x0218 }
            r5 = 0
        L_0x00aa:
            r6 = 2
            if (r5 >= r6) goto L_0x00c3
            r6 = r4[r5]     // Catch:{ all -> 0x0218 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r7 = r11.zzddb     // Catch:{ all -> 0x0218 }
            java.lang.Object r6 = r7.get(r6)     // Catch:{ all -> 0x0218 }
            java.lang.ref.WeakReference r6 = (java.lang.ref.WeakReference) r6     // Catch:{ all -> 0x0218 }
            if (r6 == 0) goto L_0x00c0
            java.lang.Object r4 = r6.get()     // Catch:{ all -> 0x0218 }
            android.view.View r4 = (android.view.View) r4     // Catch:{ all -> 0x0218 }
            goto L_0x00c4
        L_0x00c0:
            int r5 = r5 + 1
            goto L_0x00aa
        L_0x00c3:
            r4 = r1
        L_0x00c4:
            boolean r5 = r4 instanceof android.view.ViewGroup     // Catch:{ all -> 0x0218 }
            if (r5 == 0) goto L_0x00cb
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4     // Catch:{ all -> 0x0218 }
            goto L_0x00cc
        L_0x00cb:
            r4 = r1
        L_0x00cc:
            if (r3 == 0) goto L_0x00d1
            if (r4 == 0) goto L_0x00d1
            goto L_0x00d2
        L_0x00d1:
            r2 = 0
        L_0x00d2:
            android.view.View r3 = r12.zza(r11, r2)     // Catch:{ all -> 0x0218 }
            r11.zzddc = r3     // Catch:{ all -> 0x0218 }
            android.view.View r3 = r11.zzddc     // Catch:{ all -> 0x0218 }
            r10 = -1
            if (r3 == 0) goto L_0x0119
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r11.zzddb     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x00ef
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r11.zzddb     // Catch:{ all -> 0x0218 }
            java.lang.String r5 = "1007"
            java.lang.ref.WeakReference r6 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0218 }
            android.view.View r7 = r11.zzddc     // Catch:{ all -> 0x0218 }
            r6.<init>(r7)     // Catch:{ all -> 0x0218 }
            r3.put(r5, r6)     // Catch:{ all -> 0x0218 }
        L_0x00ef:
            if (r2 == 0) goto L_0x00fa
            r4.removeAllViews()     // Catch:{ all -> 0x0218 }
            android.view.View r2 = r11.zzddc     // Catch:{ all -> 0x0218 }
            r4.addView(r2)     // Catch:{ all -> 0x0218 }
            goto L_0x0119
        L_0x00fa:
            android.content.Context r2 = r12.getContext()     // Catch:{ all -> 0x0218 }
            com.google.android.gms.ads.formats.AdChoicesView r3 = new com.google.android.gms.ads.formats.AdChoicesView     // Catch:{ all -> 0x0218 }
            r3.<init>(r2)     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x0218 }
            r2.<init>(r10, r10)     // Catch:{ all -> 0x0218 }
            r3.setLayoutParams(r2)     // Catch:{ all -> 0x0218 }
            android.view.View r2 = r11.zzddc     // Catch:{ all -> 0x0218 }
            r3.addView(r2)     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r2 = r11.zzbld     // Catch:{ all -> 0x0218 }
            if (r2 == 0) goto L_0x0119
            android.widget.FrameLayout r2 = r11.zzbld     // Catch:{ all -> 0x0218 }
            r2.addView(r3)     // Catch:{ all -> 0x0218 }
        L_0x0119:
            android.widget.FrameLayout r4 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r11.zzddb     // Catch:{ all -> 0x0218 }
            r6 = 0
            r3 = r12
            r7 = r11
            r8 = r11
            r3.zza(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0218 }
            android.view.View r2 = r11.zzdda     // Catch:{ all -> 0x0218 }
            if (r2 != 0) goto L_0x013f
            android.view.View r2 = new android.view.View     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r3 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            android.content.Context r3 = r3.getContext()     // Catch:{ all -> 0x0218 }
            r2.<init>(r3)     // Catch:{ all -> 0x0218 }
            r11.zzdda = r2     // Catch:{ all -> 0x0218 }
            android.view.View r2 = r11.zzdda     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x0218 }
            r3.<init>(r10, r9)     // Catch:{ all -> 0x0218 }
            r2.setLayoutParams(r3)     // Catch:{ all -> 0x0218 }
        L_0x013f:
            android.widget.FrameLayout r2 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            android.view.View r3 = r11.zzdda     // Catch:{ all -> 0x0218 }
            android.view.ViewParent r3 = r3.getParent()     // Catch:{ all -> 0x0218 }
            if (r2 == r3) goto L_0x0150
            android.widget.FrameLayout r2 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            android.view.View r3 = r11.zzdda     // Catch:{ all -> 0x0218 }
            r2.addView(r3)     // Catch:{ all -> 0x0218 }
        L_0x0150:
            com.google.android.gms.internal.ads.zzbgg r2 = r12.zzsp()     // Catch:{ Exception -> 0x0155 }
            goto L_0x016b
        L_0x0155:
            r2 = move-exception
            com.google.android.gms.ads.internal.zzbv.zzlh()     // Catch:{ all -> 0x0218 }
            boolean r3 = com.google.android.gms.internal.ads.zzayp.zzaaa()     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x0165
            java.lang.String r2 = "Privileged processes cannot create HTML overlays."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r2)     // Catch:{ all -> 0x0218 }
            goto L_0x016a
        L_0x0165:
            java.lang.String r3 = "Error obtaining overlay."
            com.google.android.gms.internal.ads.zzaxz.zzb(r3, r2)     // Catch:{ all -> 0x0218 }
        L_0x016a:
            r2 = r1
        L_0x016b:
            if (r2 == 0) goto L_0x017a
            android.widget.FrameLayout r3 = r11.zzbld     // Catch:{ all -> 0x0218 }
            if (r3 == 0) goto L_0x017a
            android.widget.FrameLayout r3 = r11.zzbld     // Catch:{ all -> 0x0218 }
            android.view.View r2 = r2.getView()     // Catch:{ all -> 0x0218 }
            r3.addView(r2)     // Catch:{ all -> 0x0218 }
        L_0x017a:
            java.lang.Object r2 = r11.mLock     // Catch:{ all -> 0x0218 }
            monitor-enter(r2)     // Catch:{ all -> 0x0218 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r11.zzddb     // Catch:{ all -> 0x0215 }
            r12.zzf(r3)     // Catch:{ all -> 0x0215 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r11.zzddb     // Catch:{ all -> 0x0215 }
            if (r3 == 0) goto L_0x01a1
            java.lang.String[] r3 = zzdcy     // Catch:{ all -> 0x0215 }
            int r4 = r3.length     // Catch:{ all -> 0x0215 }
        L_0x0189:
            if (r9 >= r4) goto L_0x01a1
            r5 = r3[r9]     // Catch:{ all -> 0x0215 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r11.zzddb     // Catch:{ all -> 0x0215 }
            java.lang.Object r5 = r6.get(r5)     // Catch:{ all -> 0x0215 }
            java.lang.ref.WeakReference r5 = (java.lang.ref.WeakReference) r5     // Catch:{ all -> 0x0215 }
            if (r5 == 0) goto L_0x019e
            java.lang.Object r1 = r5.get()     // Catch:{ all -> 0x0215 }
            android.view.View r1 = (android.view.View) r1     // Catch:{ all -> 0x0215 }
            goto L_0x01a1
        L_0x019e:
            int r9 = r9 + 1
            goto L_0x0189
        L_0x01a1:
            boolean r3 = r1 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x0215 }
            if (r3 != 0) goto L_0x01a7
            monitor-exit(r2)     // Catch:{ all -> 0x0215 }
            goto L_0x01b8
        L_0x01a7:
            com.google.android.gms.internal.ads.zzacs r3 = new com.google.android.gms.internal.ads.zzacs     // Catch:{ all -> 0x0215 }
            r3.<init>(r11, r1)     // Catch:{ all -> 0x0215 }
            boolean r4 = r12 instanceof com.google.android.gms.internal.ads.zzacc     // Catch:{ all -> 0x0215 }
            if (r4 == 0) goto L_0x01b4
            r12.zzb(r1, r3)     // Catch:{ all -> 0x0215 }
            goto L_0x01b7
        L_0x01b4:
            r12.zza(r1, r3)     // Catch:{ all -> 0x0215 }
        L_0x01b7:
            monitor-exit(r2)     // Catch:{ all -> 0x0215 }
        L_0x01b8:
            r12.zza(r11)     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r1 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            r12.zzi(r1)     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r12 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            r11.zzm(r12)     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzacd r12 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r1 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            r12.zzj(r1)     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzacd r12 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            boolean r12 = r12 instanceof com.google.android.gms.internal.ads.zzach     // Catch:{ all -> 0x0218 }
            if (r12 == 0) goto L_0x0213
            com.google.android.gms.internal.ads.zzacd r12 = r11.zzdbj     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzach r12 = (com.google.android.gms.internal.ads.zzach) r12     // Catch:{ all -> 0x0218 }
            if (r12 == 0) goto L_0x0213
            android.content.Context r1 = r12.getContext()     // Catch:{ all -> 0x0218 }
            if (r1 == 0) goto L_0x0213
            com.google.android.gms.internal.ads.zzaww r1 = com.google.android.gms.ads.internal.zzbv.zzmf()     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r2 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            android.content.Context r2 = r2.getContext()     // Catch:{ all -> 0x0218 }
            boolean r1 = r1.zzv(r2)     // Catch:{ all -> 0x0218 }
            if (r1 == 0) goto L_0x0213
            java.lang.ref.WeakReference<com.google.android.gms.internal.ads.zzsc> r1 = r11.zzddg     // Catch:{ all -> 0x0218 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0218 }
            com.google.android.gms.internal.ads.zzsc r1 = (com.google.android.gms.internal.ads.zzsc) r1     // Catch:{ all -> 0x0218 }
            if (r1 != 0) goto L_0x020c
            com.google.android.gms.internal.ads.zzsc r1 = new com.google.android.gms.internal.ads.zzsc     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r2 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            android.content.Context r2 = r2.getContext()     // Catch:{ all -> 0x0218 }
            android.widget.FrameLayout r3 = r11.zzdcz     // Catch:{ all -> 0x0218 }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0218 }
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0218 }
            r2.<init>(r1)     // Catch:{ all -> 0x0218 }
            r11.zzddg = r2     // Catch:{ all -> 0x0218 }
        L_0x020c:
            com.google.android.gms.internal.ads.zzawv r12 = r12.zzsu()     // Catch:{ all -> 0x0218 }
            r1.zza(r12)     // Catch:{ all -> 0x0218 }
        L_0x0213:
            monitor-exit(r0)     // Catch:{ all -> 0x0218 }
            return
        L_0x0215:
            r12 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0215 }
            throw r12     // Catch:{ all -> 0x0218 }
        L_0x0218:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0218 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacr.zza(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0093, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0094 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return
        L_0x0009:
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0094 }
            r1.cancelUnconfirmedClick()     // Catch:{ all -> 0x0094 }
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ all -> 0x0094 }
            r5.<init>()     // Catch:{ all -> 0x0094 }
            java.lang.String r1 = "x"
            android.graphics.Point r2 = r9.zzdde     // Catch:{ all -> 0x0094 }
            int r2 = r2.x     // Catch:{ all -> 0x0094 }
            int r2 = r9.zzck(r2)     // Catch:{ all -> 0x0094 }
            float r2 = (float) r2     // Catch:{ all -> 0x0094 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0094 }
            java.lang.String r1 = "y"
            android.graphics.Point r2 = r9.zzdde     // Catch:{ all -> 0x0094 }
            int r2 = r2.y     // Catch:{ all -> 0x0094 }
            int r2 = r9.zzck(r2)     // Catch:{ all -> 0x0094 }
            float r2 = (float) r2     // Catch:{ all -> 0x0094 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0094 }
            java.lang.String r1 = "start_x"
            android.graphics.Point r2 = r9.zzddf     // Catch:{ all -> 0x0094 }
            int r2 = r2.x     // Catch:{ all -> 0x0094 }
            int r2 = r9.zzck(r2)     // Catch:{ all -> 0x0094 }
            float r2 = (float) r2     // Catch:{ all -> 0x0094 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0094 }
            java.lang.String r1 = "start_y"
            android.graphics.Point r2 = r9.zzddf     // Catch:{ all -> 0x0094 }
            int r2 = r2.y     // Catch:{ all -> 0x0094 }
            int r2 = r9.zzck(r2)     // Catch:{ all -> 0x0094 }
            float r2 = (float) r2     // Catch:{ all -> 0x0094 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0094 }
            android.view.View r1 = r9.zzddc     // Catch:{ all -> 0x0094 }
            if (r1 == 0) goto L_0x0089
            android.view.View r1 = r9.zzddc     // Catch:{ all -> 0x0094 }
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x0094 }
            if (r1 == 0) goto L_0x0089
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0094 }
            boolean r1 = r1 instanceof com.google.android.gms.internal.ads.zzacc     // Catch:{ all -> 0x0094 }
            if (r1 == 0) goto L_0x007b
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0094 }
            com.google.android.gms.internal.ads.zzacc r1 = (com.google.android.gms.internal.ads.zzacc) r1     // Catch:{ all -> 0x0094 }
            com.google.android.gms.internal.ads.zzacd r1 = r1.zzso()     // Catch:{ all -> 0x0094 }
            if (r1 == 0) goto L_0x0092
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0094 }
            com.google.android.gms.internal.ads.zzacc r1 = (com.google.android.gms.internal.ads.zzacc) r1     // Catch:{ all -> 0x0094 }
            com.google.android.gms.internal.ads.zzacd r2 = r1.zzso()     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r9.zzddb     // Catch:{ all -> 0x0094 }
            android.widget.FrameLayout r7 = r9.zzdcz     // Catch:{ all -> 0x0094 }
            r8 = 0
            r3 = r10
            r2.zza(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0094 }
            goto L_0x0092
        L_0x007b:
            com.google.android.gms.internal.ads.zzacd r2 = r9.zzdbj     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r9.zzddb     // Catch:{ all -> 0x0094 }
            android.widget.FrameLayout r7 = r9.zzdcz     // Catch:{ all -> 0x0094 }
            r8 = 0
            r3 = r10
            r2.zza(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0094 }
            goto L_0x0092
        L_0x0089:
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0094 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r9.zzddb     // Catch:{ all -> 0x0094 }
            android.widget.FrameLayout r3 = r9.zzdcz     // Catch:{ all -> 0x0094 }
            r1.zza(r10, r2, r5, r3)     // Catch:{ all -> 0x0094 }
        L_0x0092:
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return
        L_0x0094:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacr.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                this.zzdbj.zzc(this.zzdcz, this.zzddb);
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                this.zzdbj.zzc(this.zzdcz, this.zzddb);
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzdbj == null) {
                return false;
            }
            int[] iArr = new int[2];
            this.zzdcz.getLocationOnScreen(iArr);
            Point point = new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
            this.zzdde = point;
            if (motionEvent.getAction() == 0) {
                this.zzddf = point;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation((float) point.x, (float) point.y);
            this.zzdbj.zzd(obtain);
            obtain.recycle();
            return false;
        }
    }

    public final void destroy() {
        synchronized (this.mLock) {
            if (this.zzbld != null) {
                this.zzbld.removeAllViews();
            }
            this.zzbld = null;
            this.zzddb = null;
            this.zzddc = null;
            this.zzdbj = null;
            this.zzdde = null;
            this.zzddf = null;
            this.zzddg = null;
            this.zzdda = null;
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, int i) {
        if (zzbv.zzmf().zzv(this.zzdcz.getContext())) {
            WeakReference<zzsc> weakReference = this.zzddg;
            if (weakReference != null) {
                zzsc zzsc = (zzsc) weakReference.get();
                if (zzsc != null) {
                    zzsc.zznh();
                }
            }
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        this.zzdbj.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final synchronized Map<String, WeakReference<View>> zzsv() {
        return this.zzddb;
    }

    public final View zzsw() {
        return this.zzdcz;
    }

    @VisibleForTesting
    private final int zzck(int i) {
        zzwu.zzpv();
        return zzbat.zzb(this.zzdbj.getContext(), i);
    }
}
