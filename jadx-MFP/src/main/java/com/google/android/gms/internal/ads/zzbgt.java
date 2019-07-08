package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
@VisibleForTesting
final class zzbgt extends WebView implements OnGlobalLayoutListener, DownloadListener, zzbgg {
    @GuardedBy("this")
    private boolean mIsDestroyed;
    private final zzv zzbly;
    private final zzbbi zzbob;
    private zzbas zzbuf;
    private final WindowManager zzbuv;
    private final DisplayMetrics zzbwk;
    @Nullable
    private final zzcu zzdcf;
    private int zzdqf = -1;
    private int zzdqg = -1;
    private int zzdqi = -1;
    private int zzdqj = -1;
    private final zzum zzdvr;
    @GuardedBy("this")
    private Boolean zzeiy;
    @GuardedBy("this")
    private String zzetu = "";
    private zzaay zzetv;
    private final zzbhs zzezf;
    private final zzbo zzezg;
    private final float zzezh;
    private boolean zzezi = false;
    private boolean zzezj = false;
    private zzbgh zzezk;
    @GuardedBy("this")
    private zzd zzezl;
    @GuardedBy("this")
    private IObjectWrapper zzezm;
    @GuardedBy("this")
    private zzbht zzezn;
    @GuardedBy("this")
    private boolean zzezo;
    @GuardedBy("this")
    private boolean zzezp;
    @GuardedBy("this")
    private boolean zzezq;
    @GuardedBy("this")
    private int zzezr;
    @GuardedBy("this")
    private boolean zzezs = true;
    @GuardedBy("this")
    private boolean zzezt = false;
    @GuardedBy("this")
    private zzbgw zzezu;
    @GuardedBy("this")
    private boolean zzezv;
    @GuardedBy("this")
    private boolean zzezw;
    @GuardedBy("this")
    private zzacb zzezx;
    @GuardedBy("this")
    private int zzezy;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public int zzezz;
    private zzaay zzfaa;
    private zzaay zzfab;
    private zzaaz zzfac;
    private WeakReference<OnClickListener> zzfad;
    @GuardedBy("this")
    private zzd zzfae;
    @GuardedBy("this")
    private boolean zzfaf;
    private Map<String, zzbfk> zzfag;
    @GuardedBy("this")
    private String zzvv;

    static zzbgt zzb(Context context, zzbht zzbht, String str, boolean z, boolean z2, @Nullable zzcu zzcu, zzbbi zzbbi, zzaba zzaba, zzbo zzbo, zzv zzv, zzum zzum) {
        Context context2 = context;
        zzbgt zzbgt = new zzbgt(new zzbhs(context), zzbht, str, z, z2, zzcu, zzbbi, zzaba, zzbo, zzv, zzum);
        return zzbgt;
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this;
    }

    public final zzbdq zzabt() {
        return null;
    }

    @VisibleForTesting
    private zzbgt(zzbhs zzbhs, zzbht zzbht, String str, boolean z, boolean z2, @Nullable zzcu zzcu, zzbbi zzbbi, zzaba zzaba, zzbo zzbo, zzv zzv, zzum zzum) {
        super(zzbhs);
        this.zzezf = zzbhs;
        this.zzezn = zzbht;
        this.zzvv = str;
        this.zzezp = z;
        this.zzezr = -1;
        this.zzdcf = zzcu;
        this.zzbob = zzbbi;
        this.zzezg = zzbo;
        this.zzbly = zzv;
        this.zzbuv = (WindowManager) getContext().getSystemService("window");
        zzbv.zzlf();
        this.zzbwk = zzayh.zza(this.zzbuv);
        this.zzezh = this.zzbwk.density;
        this.zzdvr = zzum;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            zzaxz.zzb("Unable to enable Javascript.", e);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbv.zzlf().zza((Context) zzbhs, zzbbi.zzdp, settings);
        zzbv.zzlh().zza(getContext(), settings);
        setDownloadListener(this);
        zzaer();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzbgz.zzk(this), "googleAdsJsInterface");
        }
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        this.zzbuf = new zzbas(this.zzezf.zzabw(), this, this, null);
        zzaev();
        this.zzfac = new zzaaz(new zzaba(true, "make_wv", this.zzvv));
        this.zzfac.zzrf().zzc(zzaba);
        this.zzetv = zzaat.zzb(this.zzfac.zzrf());
        this.zzfac.zza("native:view_create", this.zzetv);
        this.zzfab = null;
        this.zzfaa = null;
        zzbv.zzlh().zzaz(zzbhs);
        zzbv.zzlj().zzyn();
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzbgh) {
            this.zzezk = (zzbgh) webViewClient;
        }
    }

    public final zzv zzid() {
        return this.zzbly;
    }

    private final boolean zzaeo() {
        int i;
        int i2;
        boolean z = false;
        if (!this.zzezk.zzmu() && !this.zzezk.zzaeb()) {
            return false;
        }
        zzwu.zzpv();
        DisplayMetrics displayMetrics = this.zzbwk;
        int zzb = zzbat.zzb(displayMetrics, displayMetrics.widthPixels);
        zzwu.zzpv();
        DisplayMetrics displayMetrics2 = this.zzbwk;
        int zzb2 = zzbat.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity zzabw = this.zzezf.zzabw();
        if (zzabw == null || zzabw.getWindow() == null) {
            i2 = zzb;
            i = zzb2;
        } else {
            zzbv.zzlf();
            int[] zzg = zzayh.zzg(zzabw);
            zzwu.zzpv();
            int zzb3 = zzbat.zzb(this.zzbwk, zzg[0]);
            zzwu.zzpv();
            i = zzbat.zzb(this.zzbwk, zzg[1]);
            i2 = zzb3;
        }
        if (this.zzdqf == zzb && this.zzdqg == zzb2 && this.zzdqi == i2 && this.zzdqj == i) {
            return false;
        }
        if (!(this.zzdqf == zzb && this.zzdqg == zzb2)) {
            z = true;
        }
        this.zzdqf = zzb;
        this.zzdqg = zzb2;
        this.zzdqi = i2;
        this.zzdqj = i;
        new zzaok(this).zza(zzb, zzb2, i2, i, this.zzbwk.density, this.zzbuv.getDefaultDisplay().getRotation());
        return z;
    }

    public final void zza(String str, Map<String, ?> map) {
        try {
            zza(str, zzbv.zzlf().zzn(map));
        } catch (JSONException unused) {
            zzaxz.zzeo("Could not convert parameters to JSON.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        return;
     */
    @android.annotation.TargetApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void evaluateJavascript(java.lang.String r2, android.webkit.ValueCallback<java.lang.String> r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isDestroyed()     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0014
            java.lang.String r2 = "#004 The webview is destroyed. Ignoring action."
            com.google.android.gms.internal.ads.zzaxz.zzeq(r2)     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0012
            r2 = 0
            r3.onReceiveValue(r2)     // Catch:{ all -> 0x0019 }
        L_0x0012:
            monitor-exit(r1)
            return
        L_0x0014:
            super.evaluateJavascript(r2, r3)     // Catch:{ all -> 0x0019 }
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbgt.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    private final synchronized void zzfc(String str) {
        if (!isDestroyed()) {
            loadUrl(str);
        } else {
            zzaxz.zzeo("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadUrl(String str) {
        if (!isDestroyed()) {
            try {
                super.loadUrl(str);
            } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e) {
                zzbv.zzlj().zza(e, "AdWebViewImpl.loadUrl");
                zzaxz.zzc("Could not call loadUrl. ", e);
            }
        } else {
            zzaxz.zzeo("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final synchronized void zzfd(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError | UnsatisfiedLinkError e) {
            zzbv.zzlj().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzaxz.zzc("Could not call loadUrl. ", e);
        }
    }

    public final synchronized void loadData(String str, String str2, String str3) {
        if (!isDestroyed()) {
            super.loadData(str, str2, str3);
        } else {
            zzaxz.zzeo("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!isDestroyed()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            zzaxz.zzeo("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void zzc(String str, String str2, @Nullable String str3) {
        String str4;
        if (!isDestroyed()) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcre)).booleanValue()) {
                str4 = zzbhi.zzc(str2, zzbhi.zzaex());
            } else {
                str4 = str2;
            }
            super.loadDataWithBaseURL(str, str4, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", str3);
            return;
        }
        zzaxz.zzeo("#004 The webview is destroyed. Ignoring action.");
    }

    @TargetApi(19)
    private final synchronized void zza(String str, ValueCallback<String> valueCallback) {
        if (!isDestroyed()) {
            evaluateJavascript(str, null);
        } else {
            zzaxz.zzeo("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final void zzfe(String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            if (zzyi() == null) {
                zzaep();
            }
            if (zzyi().booleanValue()) {
                zza(str, null);
                return;
            }
            String str2 = "javascript:";
            String valueOf = String.valueOf(str);
            zzfc(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return;
        }
        String str3 = "javascript:";
        String valueOf2 = String.valueOf(str);
        zzfc(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
    }

    public final void zzcg(String str) {
        zzfe(str);
    }

    private final synchronized void zzaep() {
        this.zzeiy = zzbv.zzlj().zzyi();
        if (this.zzeiy == null) {
            try {
                evaluateJavascript("(function(){})()", null);
                zza(Boolean.valueOf(true));
            } catch (IllegalStateException unused) {
                zza(Boolean.valueOf(false));
            }
        }
    }

    @VisibleForTesting
    private final void zza(Boolean bool) {
        synchronized (this) {
            this.zzeiy = bool;
        }
        zzbv.zzlj().zza(bool);
    }

    @VisibleForTesting
    private final synchronized Boolean zzyi() {
        return this.zzeiy;
    }

    public final void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(jSONObject2).length());
        sb.append(str);
        sb.append("(");
        sb.append(jSONObject2);
        sb.append(");");
        zzfe(sb.toString());
    }

    public final void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String str2 = "Dispatching AFMA event: ";
        String valueOf = String.valueOf(sb.toString());
        zzaxz.zzdn(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        zzfe(sb.toString());
    }

    public final void zzade() {
        zzaeq();
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzbob.zzdp);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public final void zzdh(int i) {
        if (i == 0) {
            zzaat.zza(this.zzfac.zzrf(), this.zzetv, "aebb2");
        }
        zzaeq();
        if (this.zzfac.zzrf() != null) {
            this.zzfac.zzrf().zzg("close_type", String.valueOf(i));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzbob.zzdp);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    private final void zzaeq() {
        zzaat.zza(this.zzfac.zzrf(), this.zzetv, "aeh2");
    }

    public final void zzvv() {
        if (this.zzfaa == null) {
            zzaat.zza(this.zzfac.zzrf(), this.zzetv, "aes2");
            this.zzfaa = zzaat.zzb(this.zzfac.zzrf());
            this.zzfac.zza("native:view_show", this.zzfaa);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzbob.zzdp);
        zza("onshow", (Map<String, ?>) hashMap);
    }

    public final void zzadf() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzlk().zzkk()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzlk().zzkj()));
        hashMap.put("device_volume", String.valueOf(zzaza.zzbb(getContext())));
        zza("volume", (Map<String, ?>) hashMap);
    }

    public final void zza(boolean z, long j) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("success", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        hashMap.put("duration", Long.toString(j));
        zza("onCacheAccessComplete", (Map<String, ?>) hashMap);
    }

    public final synchronized zzd zzadh() {
        return this.zzezl;
    }

    public final synchronized IObjectWrapper zzadp() {
        return this.zzezm;
    }

    public final synchronized zzd zzadi() {
        return this.zzfae;
    }

    public final synchronized zzbht zzadj() {
        return this.zzezn;
    }

    public final synchronized String zzadk() {
        return this.zzvv;
    }

    public final WebViewClient zzadm() {
        return this.zzezk;
    }

    public final synchronized boolean zzadn() {
        return this.zzezo;
    }

    public final zzcu zzado() {
        return this.zzdcf;
    }

    public final zzbbi zzabz() {
        return this.zzbob;
    }

    public final synchronized boolean zzadq() {
        return this.zzezp;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzbv.zzlf();
            zzayh.zza(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(str4).length());
            sb.append("Couldn't find an Activity to view url/mimetype: ");
            sb.append(str);
            sb.append(" / ");
            sb.append(str4);
            zzaxz.zzdn(sb.toString());
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzezk.zzaeb()) {
            synchronized (this) {
                if (this.zzezx != null) {
                    this.zzezx.zzc(motionEvent);
                }
            }
        } else {
            zzcu zzcu = this.zzdcf;
            if (zzcu != null) {
                zzcu.zza(motionEvent);
            }
        }
        if (isDestroyed()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float axisValue = motionEvent.getAxisValue(9);
        float axisValue2 = motionEvent.getAxisValue(10);
        if (motionEvent.getActionMasked() != 8 || ((axisValue <= BitmapDescriptorFactory.HUE_RED || canScrollVertically(-1)) && ((axisValue >= BitmapDescriptorFactory.HUE_RED || canScrollVertically(1)) && ((axisValue2 <= BitmapDescriptorFactory.HUE_RED || canScrollHorizontally(-1)) && (axisValue2 >= BitmapDescriptorFactory.HUE_RED || canScrollHorizontally(1)))))) {
            return super.onGenericMotionEvent(motionEvent);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01f9, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01d7 A[SYNTHETIC, Splitter:B:112:0x01d7] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0141  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:122:0x01fa=Splitter:B:122:0x01fa, B:64:0x00de=Splitter:B:64:0x00de} */
    @android.annotation.SuppressLint({"DrawAllocation"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.isDestroyed()     // Catch:{ all -> 0x01ff }
            r1 = 0
            if (r0 == 0) goto L_0x000d
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x000d:
            boolean r0 = r7.isInEditMode()     // Catch:{ all -> 0x01ff }
            if (r0 != 0) goto L_0x01fa
            boolean r0 = r7.zzezp     // Catch:{ all -> 0x01ff }
            if (r0 != 0) goto L_0x01fa
            com.google.android.gms.internal.ads.zzbht r0 = r7.zzezn     // Catch:{ all -> 0x01ff }
            boolean r0 = r0.zzafc()     // Catch:{ all -> 0x01ff }
            if (r0 == 0) goto L_0x0021
            goto L_0x01fa
        L_0x0021:
            com.google.android.gms.internal.ads.zzbht r0 = r7.zzezn     // Catch:{ all -> 0x01ff }
            boolean r0 = r0.zzafe()     // Catch:{ all -> 0x01ff }
            if (r0 == 0) goto L_0x002e
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x002e:
            com.google.android.gms.internal.ads.zzbht r0 = r7.zzezn     // Catch:{ all -> 0x01ff }
            boolean r0 = r0.zzafd()     // Catch:{ all -> 0x01ff }
            if (r0 == 0) goto L_0x0091
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzaan.zzcum     // Catch:{ all -> 0x01ff }
            com.google.android.gms.internal.ads.zzaak r1 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x01ff }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x01ff }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01ff }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01ff }
            if (r0 == 0) goto L_0x004d
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x004d:
            com.google.android.gms.internal.ads.zzbgw r0 = r7.zzabu()     // Catch:{ all -> 0x01ff }
            r1 = 0
            if (r0 == 0) goto L_0x0059
            float r0 = r0.getAspectRatio()     // Catch:{ all -> 0x01ff }
            goto L_0x005a
        L_0x0059:
            r0 = 0
        L_0x005a:
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0063
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x0063:
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01ff }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01ff }
            float r1 = (float) r9     // Catch:{ all -> 0x01ff }
            float r1 = r1 * r0
            int r1 = (int) r1     // Catch:{ all -> 0x01ff }
            float r2 = (float) r8     // Catch:{ all -> 0x01ff }
            float r2 = r2 / r0
            int r2 = (int) r2     // Catch:{ all -> 0x01ff }
            if (r9 != 0) goto L_0x007c
            if (r2 == 0) goto L_0x007c
            float r9 = (float) r2     // Catch:{ all -> 0x01ff }
            float r9 = r9 * r0
            int r1 = (int) r9     // Catch:{ all -> 0x01ff }
            r9 = r2
            goto L_0x0084
        L_0x007c:
            if (r8 != 0) goto L_0x0084
            if (r1 == 0) goto L_0x0084
            float r8 = (float) r1     // Catch:{ all -> 0x01ff }
            float r8 = r8 / r0
            int r2 = (int) r8     // Catch:{ all -> 0x01ff }
            r8 = r1
        L_0x0084:
            int r8 = java.lang.Math.min(r1, r8)     // Catch:{ all -> 0x01ff }
            int r9 = java.lang.Math.min(r2, r9)     // Catch:{ all -> 0x01ff }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x0091:
            com.google.android.gms.internal.ads.zzbht r0 = r7.zzezn     // Catch:{ all -> 0x01ff }
            boolean r0 = r0.isFluid()     // Catch:{ all -> 0x01ff }
            if (r0 == 0) goto L_0x00e3
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzaan.zzcur     // Catch:{ all -> 0x01ff }
            com.google.android.gms.internal.ads.zzaak r1 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x01ff }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x01ff }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01ff }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01ff }
            if (r0 != 0) goto L_0x00de
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR1()     // Catch:{ all -> 0x01ff }
            if (r0 != 0) goto L_0x00b2
            goto L_0x00de
        L_0x00b2:
            java.lang.String r0 = "/contentHeight"
            com.google.android.gms.internal.ads.zzbgu r1 = new com.google.android.gms.internal.ads.zzbgu     // Catch:{ all -> 0x01ff }
            r1.<init>(r7)     // Catch:{ all -> 0x01ff }
            r7.zza(r0, r1)     // Catch:{ all -> 0x01ff }
            java.lang.String r0 = "(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();"
            r7.zzfe(r0)     // Catch:{ all -> 0x01ff }
            android.util.DisplayMetrics r0 = r7.zzbwk     // Catch:{ all -> 0x01ff }
            float r0 = r0.density     // Catch:{ all -> 0x01ff }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01ff }
            int r1 = r7.zzezz     // Catch:{ all -> 0x01ff }
            r2 = -1
            if (r1 == r2) goto L_0x00d5
            int r9 = r7.zzezz     // Catch:{ all -> 0x01ff }
            float r9 = (float) r9     // Catch:{ all -> 0x01ff }
            float r9 = r9 * r0
            int r9 = (int) r9     // Catch:{ all -> 0x01ff }
            goto L_0x00d9
        L_0x00d5:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01ff }
        L_0x00d9:
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x00de:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x00e3:
            com.google.android.gms.internal.ads.zzbht r0 = r7.zzezn     // Catch:{ all -> 0x01ff }
            boolean r0 = r0.zzafb()     // Catch:{ all -> 0x01ff }
            if (r0 == 0) goto L_0x00f8
            android.util.DisplayMetrics r8 = r7.zzbwk     // Catch:{ all -> 0x01ff }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01ff }
            android.util.DisplayMetrics r9 = r7.zzbwk     // Catch:{ all -> 0x01ff }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01ff }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x00f8:
            int r0 = android.view.View.MeasureSpec.getMode(r8)     // Catch:{ all -> 0x01ff }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01ff }
            int r2 = android.view.View.MeasureSpec.getMode(r9)     // Catch:{ all -> 0x01ff }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01ff }
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r4) goto L_0x0118
            if (r0 != r3) goto L_0x0114
            goto L_0x0118
        L_0x0114:
            r0 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0119
        L_0x0118:
            r0 = r8
        L_0x0119:
            if (r2 == r4) goto L_0x011d
            if (r2 != r3) goto L_0x011e
        L_0x011d:
            r5 = r9
        L_0x011e:
            com.google.android.gms.internal.ads.zzbht r2 = r7.zzezn     // Catch:{ all -> 0x01ff }
            int r2 = r2.widthPixels     // Catch:{ all -> 0x01ff }
            r3 = 1
            if (r2 > r0) goto L_0x012e
            com.google.android.gms.internal.ads.zzbht r2 = r7.zzezn     // Catch:{ all -> 0x01ff }
            int r2 = r2.heightPixels     // Catch:{ all -> 0x01ff }
            if (r2 <= r5) goto L_0x012c
            goto L_0x012e
        L_0x012c:
            r2 = 0
            goto L_0x012f
        L_0x012e:
            r2 = 1
        L_0x012f:
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.zzaan.zzcxk     // Catch:{ all -> 0x01ff }
            com.google.android.gms.internal.ads.zzaak r6 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x01ff }
            java.lang.Object r4 = r6.zzd(r4)     // Catch:{ all -> 0x01ff }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x01ff }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01ff }
            if (r4 == 0) goto L_0x0167
            com.google.android.gms.internal.ads.zzbht r4 = r7.zzezn     // Catch:{ all -> 0x01ff }
            int r4 = r4.widthPixels     // Catch:{ all -> 0x01ff }
            float r4 = (float) r4     // Catch:{ all -> 0x01ff }
            float r6 = r7.zzezh     // Catch:{ all -> 0x01ff }
            float r4 = r4 / r6
            float r0 = (float) r0     // Catch:{ all -> 0x01ff }
            float r6 = r7.zzezh     // Catch:{ all -> 0x01ff }
            float r0 = r0 / r6
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0163
            com.google.android.gms.internal.ads.zzbht r0 = r7.zzezn     // Catch:{ all -> 0x01ff }
            int r0 = r0.heightPixels     // Catch:{ all -> 0x01ff }
            float r0 = (float) r0     // Catch:{ all -> 0x01ff }
            float r4 = r7.zzezh     // Catch:{ all -> 0x01ff }
            float r0 = r0 / r4
            float r4 = (float) r5     // Catch:{ all -> 0x01ff }
            float r5 = r7.zzezh     // Catch:{ all -> 0x01ff }
            float r4 = r4 / r5
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x0163
            r0 = 1
            goto L_0x0164
        L_0x0163:
            r0 = 0
        L_0x0164:
            if (r2 == 0) goto L_0x0167
            goto L_0x0168
        L_0x0167:
            r0 = r2
        L_0x0168:
            r2 = 8
            if (r0 == 0) goto L_0x01d7
            com.google.android.gms.internal.ads.zzbht r0 = r7.zzezn     // Catch:{ all -> 0x01ff }
            int r0 = r0.widthPixels     // Catch:{ all -> 0x01ff }
            float r0 = (float) r0     // Catch:{ all -> 0x01ff }
            float r4 = r7.zzezh     // Catch:{ all -> 0x01ff }
            float r0 = r0 / r4
            int r0 = (int) r0     // Catch:{ all -> 0x01ff }
            com.google.android.gms.internal.ads.zzbht r4 = r7.zzezn     // Catch:{ all -> 0x01ff }
            int r4 = r4.heightPixels     // Catch:{ all -> 0x01ff }
            float r4 = (float) r4     // Catch:{ all -> 0x01ff }
            float r5 = r7.zzezh     // Catch:{ all -> 0x01ff }
            float r4 = r4 / r5
            int r4 = (int) r4     // Catch:{ all -> 0x01ff }
            float r8 = (float) r8     // Catch:{ all -> 0x01ff }
            float r5 = r7.zzezh     // Catch:{ all -> 0x01ff }
            float r8 = r8 / r5
            int r8 = (int) r8     // Catch:{ all -> 0x01ff }
            float r9 = (float) r9     // Catch:{ all -> 0x01ff }
            float r5 = r7.zzezh     // Catch:{ all -> 0x01ff }
            float r9 = r9 / r5
            int r9 = (int) r9     // Catch:{ all -> 0x01ff }
            r5 = 103(0x67, float:1.44E-43)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ff }
            r6.<init>(r5)     // Catch:{ all -> 0x01ff }
            java.lang.String r5 = "Not enough space to show ad. Needs "
            r6.append(r5)     // Catch:{ all -> 0x01ff }
            r6.append(r0)     // Catch:{ all -> 0x01ff }
            java.lang.String r0 = "x"
            r6.append(r0)     // Catch:{ all -> 0x01ff }
            r6.append(r4)     // Catch:{ all -> 0x01ff }
            java.lang.String r0 = " dp, but only has "
            r6.append(r0)     // Catch:{ all -> 0x01ff }
            r6.append(r8)     // Catch:{ all -> 0x01ff }
            java.lang.String r8 = "x"
            r6.append(r8)     // Catch:{ all -> 0x01ff }
            r6.append(r9)     // Catch:{ all -> 0x01ff }
            java.lang.String r8 = " dp."
            r6.append(r8)     // Catch:{ all -> 0x01ff }
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x01ff }
            com.google.android.gms.internal.ads.zzaxz.zzeo(r8)     // Catch:{ all -> 0x01ff }
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01ff }
            if (r8 == r2) goto L_0x01c5
            r8 = 4
            r7.setVisibility(r8)     // Catch:{ all -> 0x01ff }
        L_0x01c5:
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01ff }
            boolean r8 = r7.zzezi     // Catch:{ all -> 0x01ff }
            if (r8 != 0) goto L_0x01f8
            com.google.android.gms.internal.ads.zzum r8 = r7.zzdvr     // Catch:{ all -> 0x01ff }
            com.google.android.gms.internal.ads.zzuo$zza$zzb r9 = com.google.android.gms.internal.ads.zzuo.zza.zzb.BANNER_SIZE_INVALID     // Catch:{ all -> 0x01ff }
            r8.zza(r9)     // Catch:{ all -> 0x01ff }
            r7.zzezi = r3     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x01d7:
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01ff }
            if (r8 == r2) goto L_0x01e0
            r7.setVisibility(r1)     // Catch:{ all -> 0x01ff }
        L_0x01e0:
            boolean r8 = r7.zzezj     // Catch:{ all -> 0x01ff }
            if (r8 != 0) goto L_0x01ed
            com.google.android.gms.internal.ads.zzum r8 = r7.zzdvr     // Catch:{ all -> 0x01ff }
            com.google.android.gms.internal.ads.zzuo$zza$zzb r9 = com.google.android.gms.internal.ads.zzuo.zza.zzb.BANNER_SIZE_VALID     // Catch:{ all -> 0x01ff }
            r8.zza(r9)     // Catch:{ all -> 0x01ff }
            r7.zzezj = r3     // Catch:{ all -> 0x01ff }
        L_0x01ed:
            com.google.android.gms.internal.ads.zzbht r8 = r7.zzezn     // Catch:{ all -> 0x01ff }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01ff }
            com.google.android.gms.internal.ads.zzbht r9 = r7.zzezn     // Catch:{ all -> 0x01ff }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01ff }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01ff }
        L_0x01f8:
            monitor-exit(r7)
            return
        L_0x01fa:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01ff }
            monitor-exit(r7)
            return
        L_0x01ff:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbgt.onMeasure(int, int):void");
    }

    public final void onGlobalLayout() {
        boolean zzaeo = zzaeo();
        zzd zzadh = zzadh();
        if (zzadh != null && zzaeo) {
            zzadh.zzvu();
        }
    }

    public final synchronized void zza(zzd zzd) {
        this.zzezl = zzd;
    }

    public final synchronized void zzaa(IObjectWrapper iObjectWrapper) {
        this.zzezm = iObjectWrapper;
    }

    public final synchronized void zzb(zzd zzd) {
        this.zzfae = zzd;
    }

    public final synchronized void zza(zzbht zzbht) {
        this.zzezn = zzbht;
        requestLayout();
    }

    public final synchronized void zzav(boolean z) {
        boolean z2 = z != this.zzezp;
        this.zzezp = z;
        zzaer();
        if (z2) {
            new zzaok(this).zzdc(z ? "expanded" : "default");
        }
    }

    public final void zzadv() {
        this.zzbuf.zzaam();
    }

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzbuf.onAttachedToWindow();
        }
        boolean z = this.zzezv;
        if (this.zzezk != null && this.zzezk.zzaeb()) {
            if (!this.zzezw) {
                OnGlobalLayoutListener zzaec = this.zzezk.zzaec();
                if (zzaec != null) {
                    zzbv.zzme();
                    zzbct.zza((View) this, zzaec);
                }
                OnScrollChangedListener zzaed = this.zzezk.zzaed();
                if (zzaed != null) {
                    zzbv.zzme();
                    zzbct.zza((View) this, zzaed);
                }
                this.zzezw = true;
            }
            zzaeo();
            z = true;
        }
        zzaz(z);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        synchronized (this) {
            if (!isDestroyed()) {
                this.zzbuf.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzezw && this.zzezk != null && this.zzezk.zzaeb() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                OnGlobalLayoutListener zzaec = this.zzezk.zzaec();
                if (zzaec != null) {
                    zzbv.zzlh().zza(getViewTreeObserver(), zzaec);
                }
                OnScrollChangedListener zzaed = this.zzezk.zzaed();
                if (zzaed != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzaed);
                }
                this.zzezw = false;
            }
        }
        zzaz(false);
    }

    public final void zzbo(Context context) {
        this.zzezf.setBaseContext(context);
        this.zzbuf.zzj(this.zzezf.zzabw());
    }

    public final synchronized void zzaf(boolean z) {
        if (this.zzezl != null) {
            this.zzezl.zza(this.zzezk.zzmu(), z);
        } else {
            this.zzezo = z;
        }
    }

    public final synchronized int getRequestedOrientation() {
        return this.zzezr;
    }

    public final synchronized void setRequestedOrientation(int i) {
        this.zzezr = i;
        if (this.zzezl != null) {
            this.zzezl.setRequestedOrientation(this.zzezr);
        }
    }

    public final Activity zzabw() {
        return this.zzezf.zzabw();
    }

    public final Context zzadg() {
        return this.zzezf.zzadg();
    }

    private final synchronized void zzaer() {
        if (!this.zzezp) {
            if (!this.zzezn.zzafb()) {
                if (VERSION.SDK_INT < 18) {
                    zzaxz.zzdn("Disabling hardware acceleration on an AdView.");
                    zzaes();
                    return;
                }
                zzaxz.zzdn("Enabling hardware acceleration on an AdView.");
                zzaet();
                return;
            }
        }
        zzaxz.zzdn("Enabling hardware acceleration on an overlay.");
        zzaet();
    }

    private final synchronized void zzaes() {
        if (!this.zzezq) {
            zzbv.zzlh().zzaa(this);
        }
        this.zzezq = true;
    }

    private final synchronized void zzaet() {
        if (this.zzezq) {
            zzbv.zzlh().zzz(this);
        }
        this.zzezq = false;
    }

    public final synchronized void destroy() {
        zzaev();
        this.zzbuf.zzaan();
        if (this.zzezl != null) {
            this.zzezl.close();
            this.zzezl.onDestroy();
            this.zzezl = null;
        }
        this.zzezm = null;
        this.zzezk.reset();
        if (!this.mIsDestroyed) {
            zzbv.zzmd();
            zzbfj.zzc(this);
            zzaeu();
            this.mIsDestroyed = true;
            zzaxz.v("Initiating WebView self destruct sequence in 3...");
            zzaxz.v("Loading blank page in WebView, 2...");
            zzfd("about:blank");
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.mIsDestroyed) {
                    this.zzezk.reset();
                    zzbv.zzmd();
                    zzbfj.zzc(this);
                    zzaeu();
                    zzyo();
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public final synchronized void zzadr() {
        zzaxz.v("Destroying WebView!");
        zzyo();
        zzayh.zzelc.post(new zzbgv(this));
    }

    private final synchronized void zzyo() {
        if (!this.zzfaf) {
            this.zzfaf = true;
            zzbv.zzlj().zzyo();
        }
    }

    public final synchronized boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    /* access modifiers changed from: protected */
    @TargetApi(21)
    public final void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                zzbgh zzbgh = this.zzezk;
                if (!(zzbgh == null || zzbgh.zzael() == null)) {
                    this.zzezk.zzael().zzjw();
                }
            }
        }
    }

    public final void zzadw() {
        if (this.zzfab == null) {
            this.zzfab = zzaat.zzb(this.zzfac.zzrf());
            this.zzfac.zza("native:view_load", this.zzfab);
        }
    }

    public final void onPause() {
        if (!isDestroyed()) {
            try {
                super.onPause();
            } catch (Exception e) {
                zzaxz.zzb("Could not pause webview.", e);
            }
        }
    }

    public final void onResume() {
        if (!isDestroyed()) {
            try {
                super.onResume();
            } catch (Exception e) {
                zzaxz.zzb("Could not resume webview.", e);
            }
        }
    }

    public final void zzadz() {
        zzaxz.v("Cannot add text view to inner AdWebView");
    }

    public final void zzay(boolean z) {
        this.zzezk.zzay(z);
    }

    public final void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Exception e) {
                zzaxz.zzb("Could not stop loading webview.", e);
            }
        }
    }

    public final synchronized void zzaw(boolean z) {
        this.zzezs = z;
    }

    public final synchronized boolean zzads() {
        return this.zzezs;
    }

    public final synchronized boolean zzadt() {
        return this.zzezt;
    }

    public final synchronized void zzjf() {
        this.zzezt = true;
        if (this.zzezg != null) {
            this.zzezg.zzjf();
        }
    }

    public final synchronized void zzjg() {
        this.zzezt = false;
        if (this.zzezg != null) {
            this.zzezg.zzjg();
        }
    }

    private final synchronized void zzaeu() {
        if (this.zzfag != null) {
            for (zzbfk release : this.zzfag.values()) {
                release.release();
            }
        }
        this.zzfag = null;
    }

    public final synchronized void zza(String str, zzbfk zzbfk) {
        if (this.zzfag == null) {
            this.zzfag = new HashMap();
        }
        this.zzfag.put(str, zzbfk);
    }

    public final synchronized zzbfk zzet(String str) {
        if (this.zzfag == null) {
            return null;
        }
        return (zzbfk) this.zzfag.get(str);
    }

    public final synchronized void zzfb(String str) {
        if (str == null) {
            str = "";
        }
        this.zzetu = str;
    }

    public final synchronized String zzabx() {
        return this.zzetu;
    }

    public final synchronized void zzacc() {
    }

    public final zzaay zzabv() {
        return this.zzetv;
    }

    public final zzaaz zzaby() {
        return this.zzfac;
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.zzfad = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public final OnClickListener getOnClickListener() {
        return (OnClickListener) this.zzfad.get();
    }

    public final synchronized void zzb(zzacb zzacb) {
        this.zzezx = zzacb;
    }

    public final synchronized zzacb zzadx() {
        return this.zzezx;
    }

    public final synchronized zzbgw zzabu() {
        return this.zzezu;
    }

    public final synchronized void zza(zzbgw zzbgw) {
        if (this.zzezu != null) {
            zzaxz.e("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzezu = zzbgw;
        }
    }

    public final synchronized boolean zzadu() {
        return this.zzezy > 0;
    }

    public final synchronized void zzax(boolean z) {
        this.zzezy += z ? 1 : -1;
        if (this.zzezy <= 0 && this.zzezl != null) {
            this.zzezl.zzvx();
        }
    }

    private final void zzaev() {
        zzaaz zzaaz = this.zzfac;
        if (zzaaz != null) {
            zzaba zzrf = zzaaz.zzrf();
            if (!(zzrf == null || zzbv.zzlj().zzyh() == null)) {
                zzbv.zzlj().zzyh().zza(zzrf);
            }
        }
    }

    public final void zzady() {
        setBackgroundColor(0);
    }

    public final void zzat(boolean z) {
        this.zzezk.zzat(z);
    }

    public final void zzvw() {
        zzd zzadh = zzadh();
        if (zzadh != null) {
            zzadh.zzvw();
        }
    }

    public final int zzaca() {
        return getMeasuredHeight();
    }

    public final int zzacb() {
        return getMeasuredWidth();
    }

    public final void zza(zzc zzc) {
        this.zzezk.zza(zzc);
    }

    public final void zzb(boolean z, int i) {
        this.zzezk.zzb(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzezk.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzezk.zza(z, i, str, str2);
    }

    public final void zza(zzsf zzsf) {
        synchronized (this) {
            this.zzezv = zzsf.zzuc;
        }
        zzaz(zzsf.zzuc);
    }

    private final void zzaz(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        zza("onAdVisibilityChanged", (Map<String, ?>) hashMap);
    }

    public final void zza(String str, zzu<? super zzbgg> zzu) {
        zzbgh zzbgh = this.zzezk;
        if (zzbgh != null) {
            zzbgh.zza(str, zzu);
        }
    }

    public final void zzb(String str, zzu<? super zzbgg> zzu) {
        zzbgh zzbgh = this.zzezk;
        if (zzbgh != null) {
            zzbgh.zzb(str, zzu);
        }
    }

    public final void zza(String str, Predicate<zzu<? super zzbgg>> predicate) {
        zzbgh zzbgh = this.zzezk;
        if (zzbgh != null) {
            zzbgh.zza(str, predicate);
        }
    }

    public final /* synthetic */ zzbhn zzadl() {
        return this.zzezk;
    }
}
