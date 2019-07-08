package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement.Event;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzaww {
    private final AtomicReference<ThreadPoolExecutor> zzegt = new AtomicReference<>(null);
    private final Object zzegu = new Object();
    @Nullable
    @GuardedBy("mGmpAppIdLock")
    private String zzegv = null;
    @Nullable
    @GuardedBy("mGmpAppIdLock")
    private String zzegw = null;
    @VisibleForTesting
    private final AtomicBoolean zzegx = new AtomicBoolean(false);
    @VisibleForTesting
    private final AtomicInteger zzegy = new AtomicInteger(-1);
    private final AtomicReference<Object> zzegz = new AtomicReference<>(null);
    private final AtomicReference<Object> zzeha = new AtomicReference<>(null);
    private ConcurrentMap<String, Method> zzehb = new ConcurrentHashMap(9);
    private final AtomicReference<zzbik> zzehc = new AtomicReference<>(null);
    @GuardedBy("proxyReference")
    private final List<FutureTask> zzehd = new ArrayList();

    public final boolean zzv(Context context) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcqp)).booleanValue() || this.zzegx.get()) {
            return false;
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcqy)).booleanValue()) {
            return true;
        }
        if (this.zzegy.get() == -1) {
            zzwu.zzpv();
            if (!zzbat.zzc(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                zzwu.zzpv();
                if (zzbat.zzbh(context)) {
                    zzaxz.zzeo("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                    this.zzegy.set(0);
                }
            }
            this.zzegy.set(1);
        }
        if (this.zzegy.get() == 1) {
            return true;
        }
        return false;
    }

    public final void zzc(Context context, String str) {
        if (zzv(context)) {
            zzb(context, str, "beginAdUnitExposure");
        }
    }

    public final void zzd(Context context, String str) {
        if (zzv(context)) {
            zzb(context, str, "endAdUnitExposure");
        }
    }

    public final String zzw(Context context) {
        if (!zzv(context)) {
            return "";
        }
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzegz, true)) {
            return "";
        }
        try {
            String str = (String) zzj(context, "getCurrentScreenName").invoke(this.zzegz.get(), new Object[0]);
            if (str == null) {
                str = (String) zzj(context, "getCurrentScreenClass").invoke(this.zzegz.get(), new Object[0]);
            }
            return str != null ? str : "";
        } catch (Exception e) {
            zza(e, "getCurrentScreenName", false);
            return "";
        }
    }

    public final void zze(Context context, String str) {
        if (zzv(context) && (context instanceof Activity) && zza(context, "com.google.firebase.analytics.FirebaseAnalytics", this.zzeha, false)) {
            Method zzk = zzk(context, "setCurrentScreen");
            try {
                Activity activity = (Activity) context;
                zzk.invoke(this.zzeha.get(), new Object[]{activity, str, context.getPackageName()});
            } catch (Exception e) {
                zza(e, "setCurrentScreen", false);
            }
        }
    }

    @Nullable
    public final String zzx(Context context) {
        if (!zzv(context)) {
            return null;
        }
        synchronized (this.zzegu) {
            if (this.zzegv != null) {
                String str = this.zzegv;
                return str;
            }
            this.zzegv = (String) zza("getGmpAppId", context);
            String str2 = this.zzegv;
            return str2;
        }
    }

    @Nullable
    public final String zzy(Context context) {
        if (!zzv(context)) {
            return null;
        }
        long longValue = ((Long) zzwu.zzpz().zzd(zzaan.zzcqu)).longValue();
        if (longValue < 0) {
            return (String) zza("getAppInstanceId", context);
        }
        if (this.zzegt.get() == null) {
            AtomicReference<ThreadPoolExecutor> atomicReference = this.zzegt;
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(((Integer) zzwu.zzpz().zzd(zzaan.zzcqv)).intValue(), ((Integer) zzwu.zzpz().zzd(zzaan.zzcqv)).intValue(), 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), new zzawy(this));
            atomicReference.compareAndSet(null, threadPoolExecutor);
        }
        Future submit = ((ThreadPoolExecutor) this.zzegt.get()).submit(new zzawx(this, context));
        try {
            return (String) submit.get(longValue, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            submit.cancel(true);
            if (e instanceof TimeoutException) {
                return "TIME_OUT";
            }
            return null;
        }
    }

    @Nullable
    public final String zzz(Context context) {
        if (!zzv(context)) {
            return null;
        }
        Object zza = zza("generateEventId", context);
        if (zza != null) {
            return zza.toString();
        }
        return null;
    }

    @Nullable
    public final String zzaa(Context context) {
        if (!zzv(context)) {
            return null;
        }
        synchronized (this.zzegu) {
            if (this.zzegw != null) {
                String str = this.zzegw;
                return str;
            }
            this.zzegw = "fa";
            String str2 = this.zzegw;
            return str2;
        }
    }

    public final void zzf(Context context, String str) {
        zza(context, "_ac", str);
    }

    public final void zzg(Context context, String str) {
        zza(context, "_ai", str);
    }

    public final void zzh(Context context, String str) {
        zza(context, "_aq", str);
    }

    public final void zza(Context context, String str, String str2, String str3, int i) {
        if (zzv(context)) {
            Bundle zzf = zzf(str, false);
            zzf.putString("_ai", str2);
            zzf.putString("type", str3);
            zzf.putInt("value", i);
            zzb(context, Event.AD_REWARD, zzf);
            StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 75);
            sb.append("Log a Firebase reward video event, reward type: ");
            sb.append(str3);
            sb.append(", reward value: ");
            sb.append(i);
            zzaxz.v(sb.toString());
        }
    }

    public final void zza(Context context, String str, String str2) {
        if (zzv(context)) {
            zzb(context, str, zzf(str2, "_ac".equals(str)));
        }
    }

    private final void zzb(Context context, String str, Bundle bundle) {
        if (zzv(context) && zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzegz, true)) {
            Method zzab = zzab(context);
            try {
                zzab.invoke(this.zzegz.get(), new Object[]{"am", str, bundle});
            } catch (Exception e) {
                zza(e, "logEventInternal", true);
            }
        }
    }

    private static Bundle zzf(String str, boolean z) {
        Bundle bundle = new Bundle();
        try {
            bundle.putLong("_aeid", Long.parseLong(str));
        } catch (NullPointerException | NumberFormatException e) {
            String str2 = "Invalid event ID: ";
            String valueOf = String.valueOf(str);
            zzaxz.zzb(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        }
        if (z) {
            bundle.putInt("_r", 1);
        }
        return bundle;
    }

    private final Method zzab(Context context) {
        Method method = (Method) this.zzehb.get("logEventInternal");
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", new Class[]{String.class, String.class, Bundle.class});
            this.zzehb.put("logEventInternal", declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, "logEventInternal", true);
            return null;
        }
    }

    private final Method zzi(Context context, String str) {
        Method method = (Method) this.zzehb.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[]{String.class});
            this.zzehb.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzj(Context context, String str) {
        Method method = (Method) this.zzehb.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[0]);
            this.zzehb.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzk(Context context, String str) {
        Method method = (Method) this.zzehb.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(str, new Class[]{Activity.class, String.class, String.class});
            this.zzehb.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final void zzb(Context context, String str, String str2) {
        if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzegz, true)) {
            Method zzi = zzi(context, str2);
            try {
                zzi.invoke(this.zzegz.get(), new Object[]{str});
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 37 + String.valueOf(str).length());
                sb.append("Invoke Firebase method ");
                sb.append(str2);
                sb.append(", Ad Unit Id: ");
                sb.append(str);
                zzaxz.v(sb.toString());
            } catch (Exception e) {
                zza(e, str2, false);
            }
        }
    }

    private final Object zza(String str, Context context) {
        Object obj = null;
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzegz, true)) {
            return null;
        }
        try {
            obj = zzj(context, str).invoke(this.zzegz.get(), new Object[0]);
        } catch (Exception e) {
            zza(e, str, true);
        }
        return obj;
    }

    private final void zza(Exception exc, String str, boolean z) {
        if (!this.zzegx.get()) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 30);
            sb.append("Invoke Firebase method ");
            sb.append(str);
            sb.append(" error.");
            zzaxz.zzeo(sb.toString());
            if (z) {
                zzaxz.zzeo("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
                this.zzegx.set(true);
            }
        }
    }

    private final boolean zza(Context context, String str, AtomicReference<Object> atomicReference, boolean z) {
        if (atomicReference.get() == null) {
            try {
                atomicReference.compareAndSet(null, context.getClassLoader().loadClass(str).getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context}));
            } catch (Exception e) {
                zza(e, "getInstance", z);
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ String zzac(Context context) throws Exception {
        return (String) zza("getAppInstanceId", context);
    }
}
