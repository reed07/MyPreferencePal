package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzacc extends zzach {
    private final Object mLock;
    @Nullable
    private zzals zzdbv;
    @Nullable
    private zzalv zzdbw;
    @Nullable
    private zzaly zzdbx;
    private final zzace zzdby;
    @Nullable
    private zzacd zzdbz;
    private boolean zzdca;
    private boolean zzdcb;

    public zzacc(Context context, zzace zzace, zzcu zzcu, zzals zzals, zzacf zzacf) {
        this(context, zzace, zzcu, zzacf);
        this.zzdbv = zzals;
    }

    @Nullable
    public final zzbgg zzsp() {
        return null;
    }

    public final void zzsq() {
    }

    public final void zzsr() {
    }

    public zzacc(Context context, zzace zzace, zzcu zzcu, zzalv zzalv, zzacf zzacf) {
        this(context, zzace, zzcu, zzacf);
        this.zzdbw = zzalv;
    }

    public zzacc(Context context, zzace zzace, zzcu zzcu, zzaly zzaly, zzacf zzacf) {
        this(context, zzace, zzcu, zzacf);
        this.zzdbx = zzaly;
    }

    private zzacc(Context context, zzace zzace, zzcu zzcu, zzacf zzacf) {
        super(context, zzace, null, zzcu, null, zzacf, null, null);
        this.zzdca = false;
        this.zzdcb = false;
        this.mLock = new Object();
        this.zzdby = zzace;
    }

    @Nullable
    public final View zza(OnClickListener onClickListener, boolean z) {
        IObjectWrapper iObjectWrapper;
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                View zza = this.zzdbz.zza(onClickListener, z);
                return zza;
            }
            try {
                iObjectWrapper = this.zzdbx != null ? this.zzdbx.zzvb() : this.zzdbv != null ? this.zzdbv.zzvb() : this.zzdbw != null ? this.zzdbw.zzvb() : null;
            } catch (RemoteException e) {
                zzaxz.zzc("Failed to call getAdChoicesContent", e);
                iObjectWrapper = null;
            }
            if (iObjectWrapper == null) {
                return null;
            }
            View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
            return view;
        }
    }

    public final boolean zzsj() {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                boolean zzsj = this.zzdbz.zzsj();
                return zzsj;
            }
            boolean zzjo = this.zzdby.zzjo();
            return zzjo;
        }
    }

    public final boolean zzsk() {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                boolean zzsk = this.zzdbz.zzsk();
                return zzsk;
            }
            boolean zzjq = this.zzdby.zzjq();
            return zzjq;
        }
    }

    public final boolean zzsl() {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                boolean zzsl = this.zzdbz.zzsl();
                return zzsl;
            }
            boolean zzjp = this.zzdby.zzjp();
            return zzjp;
        }
    }

    public final void zza(zzaet zzaet) {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                this.zzdbz.zza(zzaet);
            }
        }
    }

    public final void cancelUnconfirmedClick() {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                this.zzdbz.cancelUnconfirmedClick();
            }
        }
    }

    public final void setClickConfirmingView(View view) {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                this.zzdbz.setClickConfirmingView(view);
            }
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map) {
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        synchronized (this.mLock) {
            this.zzdcg = true;
            if (this.zzdbz != null) {
                this.zzdbz.zza(view, map);
                this.zzdby.recordImpression();
            } else {
                try {
                    if (this.zzdbx != null && !this.zzdbx.getOverrideImpressionRecording()) {
                        this.zzdbx.recordImpression();
                        this.zzdby.recordImpression();
                    } else if (this.zzdbv != null && !this.zzdbv.getOverrideImpressionRecording()) {
                        this.zzdbv.recordImpression();
                        this.zzdby.recordImpression();
                    } else if (this.zzdbw != null && !this.zzdbw.getOverrideImpressionRecording()) {
                        this.zzdbw.recordImpression();
                        this.zzdby.recordImpression();
                    }
                } catch (RemoteException e) {
                    zzaxz.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    public final void zzsm() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on main UI thread.");
        synchronized (this.mLock) {
            this.zzdch = true;
            if (this.zzdbz != null) {
                this.zzdbz.zzsm();
            }
        }
    }

    public final void zzd(MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                this.zzdbz.zzd(motionEvent);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.view.View r3, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4, android.os.Bundle r5, android.view.View r6) {
        /*
            r2 = this;
            java.lang.String r0 = "performClick must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            boolean r1 = r2.zzdcb     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0014
            boolean r1 = r2.zzsk()     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0014
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            return
        L_0x0014:
            com.google.android.gms.internal.ads.zzacd r1 = r2.zzdbz     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0023
            com.google.android.gms.internal.ads.zzacd r1 = r2.zzdbz     // Catch:{ all -> 0x0028 }
            r1.zza(r3, r4, r5, r6)     // Catch:{ all -> 0x0028 }
            com.google.android.gms.internal.ads.zzace r3 = r2.zzdby     // Catch:{ all -> 0x0028 }
            r3.onAdClicked()     // Catch:{ all -> 0x0028 }
            goto L_0x0026
        L_0x0023:
            r2.zzl(r3)     // Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            return
        L_0x0028:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacc.zza(android.view.View, java.util.Map, android.os.Bundle, android.view.View):void");
    }

    public final void zzsi() {
        this.zzdcb = true;
        zzacd zzacd = this.zzdbz;
        if (zzacd != null) {
            zzacd.zzsi();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void recordCustomClickGesture() {
        /*
            r2 = this;
            java.lang.String r0 = "recordCustomClickGesture must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzacd r1 = r2.zzdbz     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0020
            boolean r1 = r2.zzdcb     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0015
            com.google.android.gms.internal.ads.zzacd r1 = r2.zzdbz     // Catch:{ all -> 0x004b }
            r1.zzsi()     // Catch:{ all -> 0x004b }
        L_0x0015:
            com.google.android.gms.internal.ads.zzacd r1 = r2.zzdbz     // Catch:{ all -> 0x004b }
            r1.recordCustomClickGesture()     // Catch:{ all -> 0x004b }
            com.google.android.gms.internal.ads.zzace r1 = r2.zzdby     // Catch:{ all -> 0x004b }
            r1.onAdClicked()     // Catch:{ all -> 0x004b }
            goto L_0x0049
        L_0x0020:
            boolean r1 = r2.zzdcb     // Catch:{ all -> 0x004b }
            if (r1 != 0) goto L_0x002b
            java.lang.String r1 = "Custom click reporting for 3p ads failed. enableCustomClickGesture is not set."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r1)     // Catch:{ all -> 0x004b }
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return
        L_0x002b:
            boolean r1 = r2.zzsk()     // Catch:{ all -> 0x004b }
            if (r1 != 0) goto L_0x0038
            java.lang.String r1 = "Custom click reporting for 3p ads failed. Ad unit id not whitelisted."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r1)     // Catch:{ all -> 0x004b }
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return
        L_0x0038:
            com.google.android.gms.internal.ads.zzacw r1 = r2.zzst()     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0049
            com.google.android.gms.internal.ads.zzacw r1 = r2.zzst()     // Catch:{ all -> 0x004b }
            android.view.View r1 = r1.zzsw()     // Catch:{ all -> 0x004b }
            r2.zzl(r1)     // Catch:{ all -> 0x004b }
        L_0x0049:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return
        L_0x004b:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacc.recordCustomClickGesture():void");
    }

    private final void zzl(View view) {
        try {
            if (this.zzdbx != null && !this.zzdbx.getOverrideClickHandling()) {
                this.zzdbx.zzk(ObjectWrapper.wrap(view));
                this.zzdby.onAdClicked();
            } else if (this.zzdbv == null || this.zzdbv.getOverrideClickHandling()) {
                if (this.zzdbw != null && !this.zzdbw.getOverrideClickHandling()) {
                    this.zzdbw.zzk(ObjectWrapper.wrap(view));
                    this.zzdby.onAdClicked();
                }
            } else {
                this.zzdbv.zzk(ObjectWrapper.wrap(view));
                this.zzdby.onAdClicked();
            }
        } catch (RemoteException e) {
            zzaxz.zzc("Failed to call performClick", e);
        }
    }

    public final void zza(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        synchronized (this.mLock) {
            this.zzdca = true;
            HashMap zzb = zzb(map);
            HashMap zzb2 = zzb(map2);
            try {
                if (this.zzdbx != null) {
                    this.zzdbx.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                } else if (this.zzdbv != null) {
                    this.zzdbv.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzdbv.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzdbw != null) {
                    this.zzdbw.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzdbw.zzl(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e) {
                zzaxz.zzc("Failed to call prepareAd", e);
            }
            this.zzdca = false;
        }
    }

    private static HashMap<String, View> zzb(Map<String, WeakReference<View>> map) {
        HashMap<String, View> hashMap = new HashMap<>();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Entry entry : map.entrySet()) {
                View view = (View) ((WeakReference) entry.getValue()).get();
                if (view != null) {
                    hashMap.put((String) entry.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    public final void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            try {
                if (this.zzdbx != null) {
                    this.zzdbx.zzm(ObjectWrapper.wrap(view));
                } else if (this.zzdbv != null) {
                    this.zzdbv.zzm(ObjectWrapper.wrap(view));
                } else if (this.zzdbw != null) {
                    this.zzdbw.zzm(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e) {
                zzaxz.zzc("Failed to call untrackView", e);
            }
        }
    }

    public final boolean zzsn() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdca;
        }
        return z;
    }

    public final void zzc(@Nullable zzacd zzacd) {
        synchronized (this.mLock) {
            this.zzdbz = zzacd;
        }
    }

    public final zzacd zzso() {
        zzacd zzacd;
        synchronized (this.mLock) {
            zzacd = this.zzdbz;
        }
        return zzacd;
    }

    public final void zzjl() {
        zzacd zzacd = this.zzdbz;
        if (zzacd != null) {
            zzacd.zzjl();
        }
    }

    public final void zzjm() {
        zzacd zzacd = this.zzdbz;
        if (zzacd != null) {
            zzacd.zzjm();
        }
    }
}
