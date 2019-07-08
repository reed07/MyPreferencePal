package com.google.android.gms.internal.ads;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzrg implements OnGlobalLayoutListener, OnScrollChangedListener {
    private final Object mLock = new Object();
    private boolean zzbqq = false;
    private zzbai zzbua;
    private final Context zzbup;
    private final WeakReference<zzaxf> zzbur;
    private WeakReference<ViewTreeObserver> zzbus;
    private final zzsq zzbut;
    protected final zzre zzbuu;
    private final WindowManager zzbuv;
    private final PowerManager zzbuw;
    private final KeyguardManager zzbux;
    private final DisplayMetrics zzbuy;
    @Nullable
    private zzrn zzbuz;
    private boolean zzbva;
    private boolean zzbvb = false;
    private boolean zzbvc;
    private boolean zzbvd;
    private boolean zzbve;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzbvf;
    private final HashSet<zzrd> zzbvg = new HashSet<>();
    private final HashSet<zzsb> zzbvh = new HashSet<>();
    private final Rect zzbvi = new Rect();
    private final zzrj zzbvj;
    private float zzbvk;

    public zzrg(Context context, zzwf zzwf, zzaxf zzaxf, zzbbi zzbbi, zzsq zzsq) {
        this.zzbur = new WeakReference<>(zzaxf);
        this.zzbut = zzsq;
        this.zzbus = new WeakReference<>(null);
        this.zzbvc = true;
        this.zzbve = false;
        this.zzbua = new zzbai(200);
        zzre zzre = new zzre(UUID.randomUUID().toString(), zzbbi, zzwf.zzckk, zzaxf.zzehh, zzaxf.zzmu(), zzwf.zzckn);
        this.zzbuu = zzre;
        this.zzbuv = (WindowManager) context.getSystemService("window");
        this.zzbuw = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzbux = (KeyguardManager) context.getSystemService("keyguard");
        this.zzbup = context;
        this.zzbvj = new zzrj(this, new Handler());
        this.zzbup.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.zzbvj);
        this.zzbuy = context.getResources().getDisplayMetrics();
        Display defaultDisplay = this.zzbuv.getDefaultDisplay();
        this.zzbvi.right = defaultDisplay.getWidth();
        this.zzbvi.bottom = defaultDisplay.getHeight();
        zzmw();
    }

    public final void zzmw() {
        this.zzbvk = zzaza.zzbb(this.zzbup);
    }

    public final void zzmx() {
        synchronized (this.mLock) {
            if (this.zzbvc) {
                this.zzbvd = true;
                try {
                    JSONObject zznb = zznb();
                    zznb.put("doneReasonCode", "u");
                    zza(zznb, true);
                } catch (JSONException e) {
                    zzaxz.zzb("JSON failure while processing active view data.", e);
                } catch (RuntimeException e2) {
                    zzaxz.zzb("Failure while processing active view data.", e2);
                }
                String str = "Untracking ad unit: ";
                String valueOf = String.valueOf(this.zzbuu.zzmt());
                zzaxz.zzdn(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00cf, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzbu(int r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            java.util.HashSet<com.google.android.gms.internal.ads.zzsb> r1 = r7.zzbvh     // Catch:{ all -> 0x00d0 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00d0 }
        L_0x0009:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x00d0 }
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x001f
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x00d0 }
            com.google.android.gms.internal.ads.zzsb r2 = (com.google.android.gms.internal.ads.zzsb) r2     // Catch:{ all -> 0x00d0 }
            boolean r2 = r2.zznf()     // Catch:{ all -> 0x00d0 }
            if (r2 == 0) goto L_0x0009
            r1 = 1
            goto L_0x0020
        L_0x001f:
            r1 = 0
        L_0x0020:
            if (r1 == 0) goto L_0x00ce
            boolean r1 = r7.zzbvc     // Catch:{ all -> 0x00d0 }
            if (r1 != 0) goto L_0x0028
            goto L_0x00ce
        L_0x0028:
            com.google.android.gms.internal.ads.zzsq r1 = r7.zzbut     // Catch:{ all -> 0x00d0 }
            android.view.View r1 = r1.zznc()     // Catch:{ all -> 0x00d0 }
            if (r1 == 0) goto L_0x0040
            com.google.android.gms.internal.ads.zzayh r2 = com.google.android.gms.ads.internal.zzbv.zzlf()     // Catch:{ all -> 0x00d0 }
            android.os.PowerManager r5 = r7.zzbuw     // Catch:{ all -> 0x00d0 }
            android.app.KeyguardManager r6 = r7.zzbux     // Catch:{ all -> 0x00d0 }
            boolean r2 = r2.zza(r1, r5, r6)     // Catch:{ all -> 0x00d0 }
            if (r2 == 0) goto L_0x0040
            r2 = 1
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            if (r1 == 0) goto L_0x0053
            if (r2 == 0) goto L_0x0053
            android.graphics.Rect r5 = new android.graphics.Rect     // Catch:{ all -> 0x00d0 }
            r5.<init>()     // Catch:{ all -> 0x00d0 }
            r6 = 0
            boolean r5 = r1.getGlobalVisibleRect(r5, r6)     // Catch:{ all -> 0x00d0 }
            if (r5 == 0) goto L_0x0053
            r5 = 1
            goto L_0x0054
        L_0x0053:
            r5 = 0
        L_0x0054:
            com.google.android.gms.internal.ads.zzsq r6 = r7.zzbut     // Catch:{ all -> 0x00d0 }
            boolean r6 = r6.zznd()     // Catch:{ all -> 0x00d0 }
            if (r6 == 0) goto L_0x0061
            r7.zzmx()     // Catch:{ all -> 0x00d0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x0061:
            if (r8 != r4) goto L_0x0071
            com.google.android.gms.internal.ads.zzbai r6 = r7.zzbua     // Catch:{ all -> 0x00d0 }
            boolean r6 = r6.tryAcquire()     // Catch:{ all -> 0x00d0 }
            if (r6 != 0) goto L_0x0071
            boolean r6 = r7.zzbve     // Catch:{ all -> 0x00d0 }
            if (r5 != r6) goto L_0x0071
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x0071:
            if (r5 != 0) goto L_0x007b
            boolean r6 = r7.zzbve     // Catch:{ all -> 0x00d0 }
            if (r6 != 0) goto L_0x007b
            if (r8 != r4) goto L_0x007b
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x007b:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            org.json.JSONObject r8 = r7.zza(r1, r8)     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            r7.zza(r8, r3)     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            r7.zzbve = r5     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            goto L_0x0091
        L_0x0089:
            r8 = move-exception
            goto L_0x008c
        L_0x008b:
            r8 = move-exception
        L_0x008c:
            java.lang.String r1 = "Active view update failed."
            com.google.android.gms.internal.ads.zzaxz.zza(r1, r8)     // Catch:{ all -> 0x00d0 }
        L_0x0091:
            com.google.android.gms.internal.ads.zzsq r8 = r7.zzbut     // Catch:{ all -> 0x00d0 }
            com.google.android.gms.internal.ads.zzsq r8 = r8.zzne()     // Catch:{ all -> 0x00d0 }
            android.view.View r8 = r8.zznc()     // Catch:{ all -> 0x00d0 }
            if (r8 == 0) goto L_0x00c9
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r7.zzbus     // Catch:{ all -> 0x00d0 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x00d0 }
            android.view.ViewTreeObserver r1 = (android.view.ViewTreeObserver) r1     // Catch:{ all -> 0x00d0 }
            android.view.ViewTreeObserver r8 = r8.getViewTreeObserver()     // Catch:{ all -> 0x00d0 }
            if (r8 == r1) goto L_0x00c9
            r7.zzna()     // Catch:{ all -> 0x00d0 }
            boolean r2 = r7.zzbva     // Catch:{ all -> 0x00d0 }
            if (r2 == 0) goto L_0x00ba
            if (r1 == 0) goto L_0x00c2
            boolean r1 = r1.isAlive()     // Catch:{ all -> 0x00d0 }
            if (r1 == 0) goto L_0x00c2
        L_0x00ba:
            r7.zzbva = r4     // Catch:{ all -> 0x00d0 }
            r8.addOnScrollChangedListener(r7)     // Catch:{ all -> 0x00d0 }
            r8.addOnGlobalLayoutListener(r7)     // Catch:{ all -> 0x00d0 }
        L_0x00c2:
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x00d0 }
            r1.<init>(r8)     // Catch:{ all -> 0x00d0 }
            r7.zzbus = r1     // Catch:{ all -> 0x00d0 }
        L_0x00c9:
            r7.zzmy()     // Catch:{ all -> 0x00d0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x00ce:
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x00d0:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzrg.zzbu(int):void");
    }

    private final void zzmy() {
        zzrn zzrn = this.zzbuz;
        if (zzrn != null) {
            zzrn.zza(this);
        }
    }

    public final boolean zzmz() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbvc;
        }
        return z;
    }

    private static int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzc(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = (String) map.get("hashCode");
        if (TextUtils.isEmpty(str) || !str.equals(this.zzbuu.zzmt())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final void zzd(Map<String, String> map) {
        zzbu(3);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzsb zzsb, Map<String, String> map) {
        String str = "Received request to untrack: ";
        String valueOf = String.valueOf(this.zzbuu.zzmt());
        zzaxz.zzdn(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzb(zzsb);
    }

    /* access modifiers changed from: 0000 */
    public final void zze(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            boolean z = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("isVisible")) || "true".equals(map.get("isVisible"));
            Iterator it = this.zzbvg.iterator();
            while (it.hasNext()) {
                ((zzrd) it.next()).zza(this, z);
            }
        }
    }

    private final void zzna() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzbus.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    private final JSONObject zznb() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzbuu.zzmr()).put("activeViewJSON", this.zzbuu.zzms()).put("timestamp", zzbv.zzlm().elapsedRealtime()).put("adFormat", this.zzbuu.zzmq()).put("hashCode", this.zzbuu.zzmt()).put("isMraid", this.zzbuu.zzmu()).put("isStopped", this.zzbvb).put("isPaused", this.zzbqq).put("isNative", this.zzbuu.zzmv()).put("isScreenOn", isScreenOn()).put("appMuted", zzbv.zzlk().zzkk()).put("appVolume", (double) zzbv.zzlk().zzkj()).put("deviceVolume", (double) this.zzbvk);
        return jSONObject;
    }

    private static JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    private final void zza(JSONObject jSONObject, boolean z) {
        try {
            JSONObject zza = zza(jSONObject);
            ArrayList arrayList = new ArrayList(this.zzbvh);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzsb) obj).zzb(zza, z);
            }
        } catch (Throwable th) {
            zzaxz.zzb("Skipping active view message.", th);
        }
    }

    public final void zza(zzsb zzsb) {
        if (this.zzbvh.isEmpty()) {
            synchronized (this.mLock) {
                if (this.zzbvf == null) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    this.zzbvf = new zzrh(this);
                    zzbv.zzmc().zza(this.zzbup, this.zzbvf, intentFilter);
                }
            }
            zzbu(3);
        }
        this.zzbvh.add(zzsb);
        try {
            zzsb.zzb(zza(zza(this.zzbut.zznc(), (Boolean) null)), false);
        } catch (JSONException e) {
            zzaxz.zzb("Skipping measurement update for new client.", e);
        }
    }

    public final void zzb(zzsb zzsb) {
        this.zzbvh.remove(zzsb);
        zzsb.zzng();
        if (this.zzbvh.isEmpty()) {
            synchronized (this.mLock) {
                zzna();
                synchronized (this.mLock) {
                    if (this.zzbvf != null) {
                        try {
                            zzbv.zzmc().zza(this.zzbup, this.zzbvf);
                        } catch (IllegalStateException e) {
                            zzaxz.zzb("Failed trying to unregister the receiver", e);
                        } catch (Exception e2) {
                            zzbv.zzlj().zza(e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                        }
                        this.zzbvf = null;
                    }
                }
                this.zzbup.getContentResolver().unregisterContentObserver(this.zzbvj);
                int i = 0;
                this.zzbvc = false;
                zzmy();
                ArrayList arrayList = new ArrayList(this.zzbvh);
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zzb((zzsb) obj);
                }
            }
        }
    }

    private final JSONObject zza(@Nullable View view, @Nullable Boolean bool) throws JSONException {
        if (view == null) {
            return zznb().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
        }
        boolean isAttachedToWindow = zzbv.zzlh().isAttachedToWindow(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Exception e) {
            zzaxz.zzb("Failure getting view location.", e);
        }
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect2, null);
        Rect rect3 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect3);
        Rect rect4 = new Rect();
        view.getHitRect(rect4);
        JSONObject zznb = zznb();
        zznb.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put(Keywords.POSITION_TOP, zza(this.zzbvi.top, this.zzbuy)).put("bottom", zza(this.zzbvi.bottom, this.zzbuy)).put(TtmlNode.LEFT, zza(this.zzbvi.left, this.zzbuy)).put(TtmlNode.RIGHT, zza(this.zzbvi.right, this.zzbuy))).put("adBox", new JSONObject().put(Keywords.POSITION_TOP, zza(rect.top, this.zzbuy)).put("bottom", zza(rect.bottom, this.zzbuy)).put(TtmlNode.LEFT, zza(rect.left, this.zzbuy)).put(TtmlNode.RIGHT, zza(rect.right, this.zzbuy))).put("globalVisibleBox", new JSONObject().put(Keywords.POSITION_TOP, zza(rect2.top, this.zzbuy)).put("bottom", zza(rect2.bottom, this.zzbuy)).put(TtmlNode.LEFT, zza(rect2.left, this.zzbuy)).put(TtmlNode.RIGHT, zza(rect2.right, this.zzbuy))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put(Keywords.POSITION_TOP, zza(rect3.top, this.zzbuy)).put("bottom", zza(rect3.bottom, this.zzbuy)).put(TtmlNode.LEFT, zza(rect3.left, this.zzbuy)).put(TtmlNode.RIGHT, zza(rect3.right, this.zzbuy))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put(Keywords.POSITION_TOP, zza(rect4.top, this.zzbuy)).put("bottom", zza(rect4.bottom, this.zzbuy)).put(TtmlNode.LEFT, zza(rect4.left, this.zzbuy)).put(TtmlNode.RIGHT, zza(rect4.right, this.zzbuy))).put("screenDensity", (double) this.zzbuy.density);
        if (bool == null) {
            bool = Boolean.valueOf(zzbv.zzlf().zza(view, this.zzbuw, this.zzbux));
        }
        zznb.put("isVisible", bool.booleanValue());
        return zznb;
    }

    @VisibleForTesting
    private final boolean isScreenOn() {
        if (VERSION.SDK_INT >= 20) {
            return this.zzbuw.isInteractive();
        }
        return this.zzbuw.isScreenOn();
    }

    public final void onScrollChanged() {
        zzbu(1);
    }

    public final void onGlobalLayout() {
        zzbu(2);
    }

    public final void zza(zzrn zzrn) {
        synchronized (this.mLock) {
            this.zzbuz = zzrn;
        }
    }

    public final void stop() {
        synchronized (this.mLock) {
            this.zzbvb = true;
            zzbu(3);
        }
    }

    public final void pause() {
        synchronized (this.mLock) {
            this.zzbqq = true;
            zzbu(3);
        }
    }

    public final void resume() {
        synchronized (this.mLock) {
            this.zzbqq = false;
            zzbu(3);
        }
    }
}
