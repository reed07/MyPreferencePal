package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzaw;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.internal.measurement.zzco;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.android.gms.internal.measurement.zzdf;
import com.google.android.gms.internal.measurement.zzdh;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@VisibleForTesting
public final class GoogleAnalytics extends zza {
    private static List<Runnable> zzrl = new ArrayList();
    private boolean zzrm;
    private Set<zza> zzrn = new HashSet();
    private boolean zzro;
    private boolean zzrp;
    private volatile boolean zzrq;
    private boolean zzrr;

    interface zza {
        void zzc(Activity activity);

        void zzd(Activity activity);
    }

    @TargetApi(14)
    class zzb implements ActivityLifecycleCallbacks {
        zzb() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
        }

        public final void onActivityPaused(Activity activity) {
        }

        public final void onActivityResumed(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.zza(activity);
        }

        public final void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.zzb(activity);
        }
    }

    public final void zzq() {
        zzdh zzce = zzl().zzce();
        zzce.zzfr();
        if (zzce.zzfs()) {
            setDryRun(zzce.zzft());
        }
        zzce.zzfr();
        this.zzrm = true;
    }

    public final boolean isInitialized() {
        return this.zzrm;
    }

    @VisibleForTesting
    public GoogleAnalytics(zzaw zzaw) {
        super(zzaw);
    }

    @RequiresPermission
    public static GoogleAnalytics getInstance(Context context) {
        return zzaw.zzc(context).zzco();
    }

    public static void zzr() {
        synchronized (GoogleAnalytics.class) {
            if (zzrl != null) {
                for (Runnable run : zzrl) {
                    run.run();
                }
                zzrl = null;
            }
        }
    }

    public final void setDryRun(boolean z) {
        this.zzrp = z;
    }

    public final boolean isDryRunEnabled() {
        return this.zzrp;
    }

    @TargetApi(14)
    public final void enableAutoActivityReports(Application application) {
        if (!this.zzro) {
            application.registerActivityLifecycleCallbacks(new zzb());
            this.zzro = true;
        }
    }

    public final void reportActivityStart(Activity activity) {
        if (!this.zzro) {
            zza(activity);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void zza(Activity activity) {
        for (zza zzc : this.zzrn) {
            zzc.zzc(activity);
        }
    }

    public final void reportActivityStop(Activity activity) {
        if (!this.zzro) {
            zzb(activity);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void zzb(Activity activity) {
        for (zza zzd : this.zzrn) {
            zzd.zzd(activity);
        }
    }

    public final Tracker newTracker(String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzl(), str, null);
            tracker.zzq();
        }
        return tracker;
    }

    public final Tracker newTracker(int i) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzl(), null, null);
            if (i > 0) {
                zzdf zzdf = (zzdf) new zzdd(zzl()).zzq(i);
                if (zzdf != null) {
                    tracker.zza(zzdf);
                }
            }
            tracker.zzq();
        }
        return tracker;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zza zza2) {
        this.zzrn.add(zza2);
        Context context = zzl().getContext();
        if (context instanceof Application) {
            enableAutoActivityReports((Application) context);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zza zza2) {
        this.zzrn.remove(zza2);
    }

    public final void setAppOptOut(boolean z) {
        this.zzrq = z;
        if (this.zzrq) {
            zzl().zzcc().zzbr();
        }
    }

    public final boolean getAppOptOut() {
        return this.zzrq;
    }

    @Deprecated
    public final Logger getLogger() {
        return zzco.getLogger();
    }

    @Deprecated
    public final void setLogger(Logger logger) {
        zzco.setLogger(logger);
        if (!this.zzrr) {
            String str = (String) zzcf.zzyx.get();
            String str2 = (String) zzcf.zzyx.get();
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 112);
            sb.append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.");
            sb.append(str2);
            sb.append(" DEBUG");
            Log.i(str, sb.toString());
            this.zzrr = true;
        }
    }

    public final void setLocalDispatchPeriod(int i) {
        zzl().zzcc().setLocalDispatchPeriod(i);
    }

    public final void dispatchLocalHits() {
        zzl().zzcc().zzbs();
    }
}
