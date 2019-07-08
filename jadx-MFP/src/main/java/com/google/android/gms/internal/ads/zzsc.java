package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

@zzark
@TargetApi(14)
public final class zzsc implements ActivityLifecycleCallbacks, OnAttachStateChangeListener, OnGlobalLayoutListener, OnScrollChangedListener {
    private static final long zzbwd = ((Long) zzwu.zzpz().zzd(zzaan.zzcsu)).longValue();
    private zzbai zzbua = new zzbai(zzbwd);
    private final Context zzbup;
    private final WindowManager zzbuv;
    private final PowerManager zzbuw;
    private final KeyguardManager zzbux;
    private boolean zzbve = false;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzbvf;
    private final Rect zzbvi;
    private Application zzbwe;
    private WeakReference<ViewTreeObserver> zzbwf;
    private WeakReference<View> zzbwg;
    private zzsh zzbwh;
    private int zzbwi = -1;
    private final HashSet<zzsg> zzbwj = new HashSet<>();
    private final DisplayMetrics zzbwk;

    public zzsc(Context context, View view) {
        this.zzbup = context.getApplicationContext();
        this.zzbuv = (WindowManager) context.getSystemService("window");
        this.zzbuw = (PowerManager) this.zzbup.getSystemService("power");
        this.zzbux = (KeyguardManager) context.getSystemService("keyguard");
        Context context2 = this.zzbup;
        if (context2 instanceof Application) {
            this.zzbwe = (Application) context2;
            this.zzbwh = new zzsh((Application) context2, this);
        }
        this.zzbwk = context.getResources().getDisplayMetrics();
        this.zzbvi = new Rect();
        this.zzbvi.right = this.zzbuv.getDefaultDisplay().getWidth();
        this.zzbvi.bottom = this.zzbuv.getDefaultDisplay().getHeight();
        WeakReference<View> weakReference = this.zzbwg;
        View view2 = weakReference != null ? (View) weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzbwg = new WeakReference<>(view);
        if (view != null) {
            if (zzbv.zzlh().isAttachedToWindow(view)) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
        }
    }

    public final void zza(zzsg zzsg) {
        this.zzbwj.add(zzsg);
        zzbv(3);
    }

    public final void zzb(zzsg zzsg) {
        this.zzbwj.remove(zzsg);
    }

    private final void zzap() {
        zzbv.zzlf();
        zzayh.zzelc.post(new zzsd(this));
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzbwi = -1;
        zze(view);
        zzbv(3);
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzbwi = -1;
        zzbv(3);
        zzap();
        zzf(view);
    }

    private final void zza(Activity activity, int i) {
        if (this.zzbwg != null) {
            Window window = activity.getWindow();
            if (window != null) {
                View peekDecorView = window.peekDecorView();
                View view = (View) this.zzbwg.get();
                if (!(view == null || peekDecorView == null || view.getRootView() != peekDecorView.getRootView())) {
                    this.zzbwi = i;
                }
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzbv(3);
        zzap();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzbv(3);
        zzap();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzbv(3);
        zzap();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzbv(3);
        zzap();
    }

    public final void onActivityStopped(Activity activity) {
        zzbv(3);
        zzap();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzbv(3);
        zzap();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzbv(3);
        zzap();
    }

    public final void onGlobalLayout() {
        zzbv(2);
        zzap();
    }

    public final void onScrollChanged() {
        zzbv(1);
    }

    public final void zznh() {
        zzbv(4);
    }

    /* access modifiers changed from: private */
    public final void zzbv(int i) {
        boolean z;
        boolean z2;
        int i2 = i;
        if (this.zzbwj.size() != 0) {
            WeakReference<View> weakReference = this.zzbwg;
            if (weakReference != null) {
                View view = (View) weakReference.get();
                boolean z3 = i2 == 1;
                boolean z4 = view == null;
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                Rect rect3 = new Rect();
                Rect rect4 = new Rect();
                int[] iArr = new int[2];
                int[] iArr2 = new int[2];
                if (view != null) {
                    boolean globalVisibleRect = view.getGlobalVisibleRect(rect2);
                    boolean localVisibleRect = view.getLocalVisibleRect(rect3);
                    view.getHitRect(rect4);
                    try {
                        view.getLocationOnScreen(iArr);
                        view.getLocationInWindow(iArr2);
                    } catch (Exception e) {
                        zzaxz.zzb("Failure getting view location.", e);
                    }
                    rect.left = iArr[0];
                    rect.top = iArr[1];
                    rect.right = rect.left + view.getWidth();
                    rect.bottom = rect.top + view.getHeight();
                    z2 = globalVisibleRect;
                    z = localVisibleRect;
                } else {
                    z2 = false;
                    z = false;
                }
                int windowVisibility = view != null ? view.getWindowVisibility() : 8;
                int i3 = this.zzbwi;
                if (i3 != -1) {
                    windowVisibility = i3;
                }
                boolean z5 = !z4 && zzbv.zzlf().zza(view, this.zzbuw, this.zzbux) && z2 && z && windowVisibility == 0;
                if (z3 && !this.zzbua.tryAcquire() && z5 == this.zzbve) {
                    return;
                }
                if (z5 || this.zzbve || i2 != 1) {
                    zzsf zzsf = new zzsf(zzbv.zzlm().elapsedRealtime(), this.zzbuw.isScreenOn(), view != null ? zzbv.zzlh().isAttachedToWindow(view) : false, view != null ? view.getWindowVisibility() : 8, zza(this.zzbvi), zza(rect), zza(rect2), z2, zza(rect3), z, zza(rect4), this.zzbwk.density, z5);
                    Iterator it = this.zzbwj.iterator();
                    while (it.hasNext()) {
                        ((zzsg) it.next()).zza(zzsf);
                    }
                    this.zzbve = z5;
                }
            }
        }
    }

    private final Rect zza(Rect rect) {
        return new Rect(zzbw(rect.left), zzbw(rect.top), zzbw(rect.right), zzbw(rect.bottom));
    }

    private final int zzbw(int i) {
        return (int) (((float) i) / this.zzbwk.density);
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzbwf = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzbvf == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzbvf = new zzse(this);
            zzbv.zzmc().zza(this.zzbup, this.zzbvf, intentFilter);
        }
        Application application = this.zzbwe;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzbwh);
            } catch (Exception e) {
                zzaxz.zzb("Error registering activity lifecycle callbacks.", e);
            }
        }
    }

    private final void zzf(View view) {
        try {
            if (this.zzbwf != null) {
                ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzbwf.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zzbwf = null;
            }
        } catch (Exception e) {
            zzaxz.zzb("Error while unregistering listeners from the last ViewTreeObserver.", e);
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception e2) {
            zzaxz.zzb("Error while unregistering listeners from the ViewTreeObserver.", e2);
        }
        if (this.zzbvf != null) {
            try {
                zzbv.zzmc().zza(this.zzbup, this.zzbvf);
            } catch (IllegalStateException e3) {
                zzaxz.zzb("Failed trying to unregister the receiver", e3);
            } catch (Exception e4) {
                zzbv.zzlj().zza(e4, "ActiveViewUnit.stopScreenStatusMonitoring");
            }
            this.zzbvf = null;
        }
        Application application = this.zzbwe;
        if (application != null) {
            try {
                application.unregisterActivityLifecycleCallbacks(this.zzbwh);
            } catch (Exception e5) {
                zzaxz.zzb("Error registering activity lifecycle callbacks.", e5);
            }
        }
    }
}
