package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import java.lang.ref.WeakReference;

public final class zzdu implements ActivityLifecycleCallbacks, OnAttachStateChangeListener, OnGlobalLayoutListener, OnScrollChangedListener {
    private static final Handler zztu = new Handler(Looper.getMainLooper());
    private final zzdl zzqo;
    private Application zzsg;
    private final Context zztv;
    private final PowerManager zztw;
    private final KeyguardManager zztx;
    private BroadcastReceiver zzty;
    private WeakReference<ViewTreeObserver> zztz;
    private WeakReference<View> zzua;
    private zzcz zzub;
    private boolean zzuc = false;
    private int zzud = -1;
    private long zzue = -3;

    public zzdu(zzdl zzdl, View view) {
        this.zzqo = zzdl;
        this.zztv = zzdl.zzsp;
        this.zztw = (PowerManager) this.zztv.getSystemService("power");
        this.zztx = (KeyguardManager) this.zztv.getSystemService("keyguard");
        Context context = this.zztv;
        if (context instanceof Application) {
            this.zzsg = (Application) context;
            this.zzub = new zzcz((Application) context, this);
        }
        zzd(view);
    }

    /* access modifiers changed from: 0000 */
    public final void zzd(View view) {
        WeakReference<View> weakReference = this.zzua;
        View view2 = weakReference != null ? (View) weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzua = new WeakReference<>(view);
        if (view != null) {
            if ((view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
            this.zzue = -2;
            return;
        }
        this.zzue = -3;
    }

    private final void zzap() {
        zztu.post(new zzdv(this));
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzud = -1;
        zze(view);
        zzar();
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzud = -1;
        zzar();
        zzap();
        zzf(view);
    }

    private final void zza(Activity activity, int i) {
        if (this.zzua != null) {
            Window window = activity.getWindow();
            if (window != null) {
                View peekDecorView = window.peekDecorView();
                View view = (View) this.zzua.get();
                if (!(view == null || peekDecorView == null || view.getRootView() != peekDecorView.getRootView())) {
                    this.zzud = i;
                }
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzar();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzar();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzar();
        zzap();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzar();
    }

    public final void onActivityStopped(Activity activity) {
        zzar();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzar();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzar();
    }

    public final void onGlobalLayout() {
        zzar();
    }

    public final void onScrollChanged() {
        zzar();
    }

    public final long zzaq() {
        if (this.zzue == -2 && this.zzua.get() == null) {
            this.zzue = -3;
        }
        return this.zzue;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0056, code lost:
        if (r4 == false) goto L_0x0059;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0087  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzar() {
        /*
            r9 = this;
            java.lang.ref.WeakReference<android.view.View> r0 = r9.zzua
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            r1 = 0
            if (r0 != 0) goto L_0x0015
            r2 = -3
            r9.zzue = r2
            r9.zzuc = r1
            return
        L_0x0015:
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            boolean r2 = r0.getGlobalVisibleRect(r2)
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>()
            boolean r3 = r0.getLocalVisibleRect(r3)
            com.google.android.gms.internal.ads.zzdl r4 = r9.zzqo
            boolean r4 = r4.zzaj()
            r5 = 1
            if (r4 != 0) goto L_0x005b
            android.app.KeyguardManager r4 = r9.zztx
            boolean r4 = r4.inKeyguardRestrictedInputMode()
            if (r4 == 0) goto L_0x0059
            android.app.Activity r4 = com.google.android.gms.internal.ads.zzds.zzc(r0)
            if (r4 == 0) goto L_0x0055
            android.view.Window r4 = r4.getWindow()
            if (r4 != 0) goto L_0x0046
            r4 = 0
            goto L_0x004a
        L_0x0046:
            android.view.WindowManager$LayoutParams r4 = r4.getAttributes()
        L_0x004a:
            if (r4 == 0) goto L_0x0055
            int r4 = r4.flags
            r6 = 524288(0x80000, float:7.34684E-40)
            r4 = r4 & r6
            if (r4 == 0) goto L_0x0055
            r4 = 1
            goto L_0x0056
        L_0x0055:
            r4 = 0
        L_0x0056:
            if (r4 == 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r4 = 0
            goto L_0x005c
        L_0x005b:
            r4 = 1
        L_0x005c:
            int r6 = r0.getWindowVisibility()
            int r7 = r9.zzud
            r8 = -1
            if (r7 == r8) goto L_0x0066
            r6 = r7
        L_0x0066:
            int r7 = r0.getVisibility()
            if (r7 != 0) goto L_0x0083
            boolean r0 = r0.isShown()
            if (r0 == 0) goto L_0x0083
            android.os.PowerManager r0 = r9.zztw
            boolean r0 = r0.isScreenOn()
            if (r0 == 0) goto L_0x0083
            if (r4 == 0) goto L_0x0083
            if (r3 == 0) goto L_0x0083
            if (r2 == 0) goto L_0x0083
            if (r6 != 0) goto L_0x0083
            r1 = 1
        L_0x0083:
            boolean r0 = r9.zzuc
            if (r0 == r1) goto L_0x0094
            if (r1 == 0) goto L_0x008e
            long r2 = android.os.SystemClock.elapsedRealtime()
            goto L_0x0090
        L_0x008e:
            r2 = -2
        L_0x0090:
            r9.zzue = r2
            r9.zzuc = r1
        L_0x0094:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdu.zzar():void");
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zztz = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzty == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzty = new zzdw(this);
            this.zztv.registerReceiver(this.zzty, intentFilter);
        }
        Application application = this.zzsg;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzub);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027 A[Catch:{ Exception -> 0x002d }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0031 A[SYNTHETIC, Splitter:B:17:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003c A[SYNTHETIC, Splitter:B:23:0x003c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzf(android.view.View r4) {
        /*
            r3 = this;
            r0 = 0
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r3.zztz     // Catch:{ Exception -> 0x001d }
            if (r1 == 0) goto L_0x001d
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r3.zztz     // Catch:{ Exception -> 0x001d }
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x001d }
            android.view.ViewTreeObserver r1 = (android.view.ViewTreeObserver) r1     // Catch:{ Exception -> 0x001d }
            if (r1 == 0) goto L_0x001b
            boolean r2 = r1.isAlive()     // Catch:{ Exception -> 0x001d }
            if (r2 == 0) goto L_0x001b
            r1.removeOnScrollChangedListener(r3)     // Catch:{ Exception -> 0x001d }
            r1.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x001d }
        L_0x001b:
            r3.zztz = r0     // Catch:{ Exception -> 0x001d }
        L_0x001d:
            android.view.ViewTreeObserver r4 = r4.getViewTreeObserver()     // Catch:{ Exception -> 0x002d }
            boolean r1 = r4.isAlive()     // Catch:{ Exception -> 0x002d }
            if (r1 == 0) goto L_0x002d
            r4.removeOnScrollChangedListener(r3)     // Catch:{ Exception -> 0x002d }
            r4.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x002d }
        L_0x002d:
            android.content.BroadcastReceiver r4 = r3.zzty
            if (r4 == 0) goto L_0x0038
            android.content.Context r1 = r3.zztv     // Catch:{ Exception -> 0x0036 }
            r1.unregisterReceiver(r4)     // Catch:{ Exception -> 0x0036 }
        L_0x0036:
            r3.zzty = r0
        L_0x0038:
            android.app.Application r4 = r3.zzsg
            if (r4 == 0) goto L_0x0042
            com.google.android.gms.internal.ads.zzcz r0 = r3.zzub     // Catch:{ Exception -> 0x0042 }
            r4.unregisterActivityLifecycleCallbacks(r0)     // Catch:{ Exception -> 0x0042 }
            return
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdu.zzf(android.view.View):void");
    }
}
