package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzark
@ParametersAreNonnullByDefault
public final class zzact extends zzadl implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener, zzacw {
    @VisibleForTesting
    static final String[] zzdcy = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy("mLock")
    @VisibleForTesting
    private zzacd zzdbj;
    @Nullable
    @VisibleForTesting
    private View zzddc;
    @VisibleForTesting
    private Point zzdde = new Point();
    @VisibleForTesting
    private Point zzddf = new Point();
    @Nullable
    @VisibleForTesting
    private WeakReference<zzsc> zzddg = new WeakReference<>(null);
    private final WeakReference<View> zzddj;
    private final Map<String, WeakReference<View>> zzddk = new HashMap();
    private final Map<String, WeakReference<View>> zzddl = new HashMap();
    private final Map<String, WeakReference<View>> zzddm = new HashMap();

    public zzact(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        zzbv.zzme();
        zzbct.zza(view, (OnGlobalLayoutListener) this);
        zzbv.zzme();
        zzbct.zza(view, (OnScrollChangedListener) this);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.zzddj = new WeakReference<>(view);
        for (Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            View view2 = (View) entry.getValue();
            if (view2 != null) {
                this.zzddk.put(str, new WeakReference(view2));
                if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str) && !UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
                    view2.setOnTouchListener(this);
                    view2.setClickable(true);
                    view2.setOnClickListener(this);
                }
            }
        }
        this.zzddm.putAll(this.zzddk);
        for (Entry entry2 : hashMap2.entrySet()) {
            View view3 = (View) entry2.getValue();
            if (view3 != null) {
                this.zzddl.put((String) entry2.getKey(), new WeakReference(view3));
                view3.setOnTouchListener(this);
            }
        }
        this.zzddm.putAll(this.zzddl);
        zzaan.initialize(view.getContext());
    }

    public final void unregisterNativeAd() {
        synchronized (this.mLock) {
            this.zzddc = null;
            this.zzdbj = null;
            this.zzdde = null;
            this.zzddf = null;
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper) {
        int i;
        View view;
        synchronized (this.mLock) {
            ViewGroup viewGroup = null;
            zzm(null);
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (!(unwrap instanceof zzach)) {
                zzaxz.zzeo("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            zzach zzach = (zzach) unwrap;
            if (!zzach.zzsl()) {
                zzaxz.e("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
                return;
            }
            View view2 = (View) this.zzddj.get();
            if (!(this.zzdbj == null || view2 == null)) {
                this.zzdbj.zzb(view2, this.zzddm);
            }
            synchronized (this.mLock) {
                i = 0;
                if (this.zzdbj instanceof zzach) {
                    zzach zzach2 = (zzach) this.zzdbj;
                    View view3 = (View) this.zzddj.get();
                    if (!(zzach2 == null || zzach2.getContext() == null || view3 == null || !zzbv.zzmf().zzv(view3.getContext()))) {
                        zzawv zzsu = zzach2.zzsu();
                        if (zzsu != null) {
                            zzsu.zzai(false);
                        }
                        zzsc zzsc = (zzsc) this.zzddg.get();
                        if (!(zzsc == null || zzsu == null)) {
                            zzsc.zzb(zzsu);
                        }
                    }
                }
            }
            if (!(this.zzdbj instanceof zzacc) || !((zzacc) this.zzdbj).zzsn()) {
                this.zzdbj = zzach;
                if (zzach instanceof zzacc) {
                    ((zzacc) zzach).zzc(null);
                }
            } else {
                ((zzacc) this.zzdbj).zzc(zzach);
            }
            String[] strArr = {NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW};
            while (true) {
                if (i >= 2) {
                    view = null;
                    break;
                }
                WeakReference weakReference = (WeakReference) this.zzddm.get(strArr[i]);
                if (weakReference != null) {
                    view = (View) weakReference.get();
                    break;
                }
                i++;
            }
            if (view == null) {
                zzaxz.zzeo("Ad choices asset view is not provided.");
            } else {
                if (view instanceof ViewGroup) {
                    viewGroup = (ViewGroup) view;
                }
                if (viewGroup != null) {
                    this.zzddc = zzach.zza((OnClickListener) this, true);
                    if (this.zzddc != null) {
                        this.zzddm.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzddc));
                        this.zzddk.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzddc));
                        viewGroup.removeAllViews();
                        viewGroup.addView(this.zzddc);
                    }
                }
            }
            zzach.zza(view2, this.zzddk, this.zzddl, (OnTouchListener) this, (OnClickListener) this);
            zzayh.zzelc.post(new zzacu(this, zzach));
            zzm(view2);
            this.zzdbj.zzj(view2);
            synchronized (this.mLock) {
                if (this.zzdbj instanceof zzach) {
                    zzach zzach3 = (zzach) this.zzdbj;
                    View view4 = (View) this.zzddj.get();
                    if (!(zzach3 == null || zzach3.getContext() == null || view4 == null || !zzbv.zzmf().zzv(view4.getContext()))) {
                        zzsc zzsc2 = (zzsc) this.zzddg.get();
                        if (zzsc2 == null) {
                            zzsc2 = new zzsc(view4.getContext(), view4);
                            this.zzddg = new WeakReference<>(zzsc2);
                        }
                        zzsc2.zza((zzsg) zzach3.zzsu());
                    }
                }
            }
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzdbj.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    /* access modifiers changed from: private */
    public final boolean zza(String[] strArr) {
        for (String str : strArr) {
            if (this.zzddk.get(str) != null) {
                return true;
            }
        }
        for (String str2 : strArr) {
            if (this.zzddl.get(str2) != null) {
                return false;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzach r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            java.lang.String[] r1 = zzdcy     // Catch:{ all -> 0x003b }
            int r2 = r1.length     // Catch:{ all -> 0x003b }
            r3 = 0
        L_0x0007:
            if (r3 >= r2) goto L_0x001f
            r4 = r1[r3]     // Catch:{ all -> 0x003b }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r6.zzddm     // Catch:{ all -> 0x003b }
            java.lang.Object r4 = r5.get(r4)     // Catch:{ all -> 0x003b }
            java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4     // Catch:{ all -> 0x003b }
            if (r4 == 0) goto L_0x001c
            java.lang.Object r1 = r4.get()     // Catch:{ all -> 0x003b }
            android.view.View r1 = (android.view.View) r1     // Catch:{ all -> 0x003b }
            goto L_0x0020
        L_0x001c:
            int r3 = r3 + 1
            goto L_0x0007
        L_0x001f:
            r1 = 0
        L_0x0020:
            boolean r2 = r1 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x003b }
            if (r2 != 0) goto L_0x0029
            r7.zzsr()     // Catch:{ all -> 0x003b }
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x0029:
            com.google.android.gms.internal.ads.zzacv r2 = new com.google.android.gms.internal.ads.zzacv     // Catch:{ all -> 0x003b }
            r2.<init>(r6, r1)     // Catch:{ all -> 0x003b }
            boolean r3 = r7 instanceof com.google.android.gms.internal.ads.zzacc     // Catch:{ all -> 0x003b }
            if (r3 == 0) goto L_0x0036
            r7.zzb(r1, r2)     // Catch:{ all -> 0x003b }
            goto L_0x0039
        L_0x0036:
            r7.zza(r1, r2)     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x003b:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzact.zza(com.google.android.gms.internal.ads.zzach):void");
    }

    private final void zzm(@Nullable View view) {
        zzacd zzacd;
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                if (this.zzdbj instanceof zzacc) {
                    zzacd = ((zzacc) this.zzdbj).zzso();
                } else {
                    zzacd = this.zzdbj;
                }
                if (zzacd != null) {
                    zzacd.zzm(view);
                }
            }
        }
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                View view = (View) this.zzddj.get();
                if (view != null) {
                    this.zzdbj.zzc(view, this.zzddm);
                }
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                View view = (View) this.zzddj.get();
                if (view != null) {
                    this.zzdbj.zzc(view, this.zzddm);
                }
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzdbj == null) {
                return false;
            }
            View view2 = (View) this.zzddj.get();
            if (view2 == null) {
                return false;
            }
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
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

    public final synchronized Map<String, WeakReference<View>> zzsv() {
        return this.zzddm;
    }

    @Nullable
    public final View zzsw() {
        return (View) this.zzddj.get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0095, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0096 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0096 }
            return
        L_0x0009:
            java.lang.ref.WeakReference<android.view.View> r1 = r9.zzddj     // Catch:{ all -> 0x0096 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0096 }
            r7 = r1
            android.view.View r7 = (android.view.View) r7     // Catch:{ all -> 0x0096 }
            if (r7 != 0) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x0096 }
            return
        L_0x0016:
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ all -> 0x0096 }
            r5.<init>()     // Catch:{ all -> 0x0096 }
            java.lang.String r1 = "x"
            android.graphics.Point r2 = r9.zzdde     // Catch:{ all -> 0x0096 }
            int r2 = r2.x     // Catch:{ all -> 0x0096 }
            int r2 = r9.zzck(r2)     // Catch:{ all -> 0x0096 }
            float r2 = (float) r2     // Catch:{ all -> 0x0096 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0096 }
            java.lang.String r1 = "y"
            android.graphics.Point r2 = r9.zzdde     // Catch:{ all -> 0x0096 }
            int r2 = r2.y     // Catch:{ all -> 0x0096 }
            int r2 = r9.zzck(r2)     // Catch:{ all -> 0x0096 }
            float r2 = (float) r2     // Catch:{ all -> 0x0096 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0096 }
            java.lang.String r1 = "start_x"
            android.graphics.Point r2 = r9.zzddf     // Catch:{ all -> 0x0096 }
            int r2 = r2.x     // Catch:{ all -> 0x0096 }
            int r2 = r9.zzck(r2)     // Catch:{ all -> 0x0096 }
            float r2 = (float) r2     // Catch:{ all -> 0x0096 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0096 }
            java.lang.String r1 = "start_y"
            android.graphics.Point r2 = r9.zzddf     // Catch:{ all -> 0x0096 }
            int r2 = r2.y     // Catch:{ all -> 0x0096 }
            int r2 = r9.zzck(r2)     // Catch:{ all -> 0x0096 }
            float r2 = (float) r2     // Catch:{ all -> 0x0096 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0096 }
            android.view.View r1 = r9.zzddc     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x008d
            android.view.View r1 = r9.zzddc     // Catch:{ all -> 0x0096 }
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x008d
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0096 }
            boolean r1 = r1 instanceof com.google.android.gms.internal.ads.zzacc     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x0081
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0096 }
            com.google.android.gms.internal.ads.zzacc r1 = (com.google.android.gms.internal.ads.zzacc) r1     // Catch:{ all -> 0x0096 }
            com.google.android.gms.internal.ads.zzacd r1 = r1.zzso()     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x0094
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0096 }
            com.google.android.gms.internal.ads.zzacc r1 = (com.google.android.gms.internal.ads.zzacc) r1     // Catch:{ all -> 0x0096 }
            com.google.android.gms.internal.ads.zzacd r2 = r1.zzso()     // Catch:{ all -> 0x0096 }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r9.zzddm     // Catch:{ all -> 0x0096 }
            r8 = 0
            r3 = r10
            r2.zza(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0096 }
            goto L_0x0094
        L_0x0081:
            com.google.android.gms.internal.ads.zzacd r2 = r9.zzdbj     // Catch:{ all -> 0x0096 }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r9.zzddm     // Catch:{ all -> 0x0096 }
            r8 = 0
            r3 = r10
            r2.zza(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0096 }
            goto L_0x0094
        L_0x008d:
            com.google.android.gms.internal.ads.zzacd r1 = r9.zzdbj     // Catch:{ all -> 0x0096 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r9.zzddm     // Catch:{ all -> 0x0096 }
            r1.zza(r10, r2, r5, r7)     // Catch:{ all -> 0x0096 }
        L_0x0094:
            monitor-exit(r0)     // Catch:{ all -> 0x0096 }
            return
        L_0x0096:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0096 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzact.onClick(android.view.View):void");
    }

    @VisibleForTesting
    private final int zzck(int i) {
        int zzb;
        synchronized (this.mLock) {
            zzwu.zzpv();
            zzb = zzbat.zzb(this.zzdbj.getContext(), i);
        }
        return zzb;
    }
}
