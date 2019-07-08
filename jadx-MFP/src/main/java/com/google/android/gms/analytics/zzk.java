package com.google.android.gms.analytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzac;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zzx;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressLint({"StaticFieldLeak"})
@VisibleForTesting
public final class zzk {
    private static volatile zzk zzsm;
    private final Context zzri;
    /* access modifiers changed from: private */
    public final List<zzn> zzsn = new CopyOnWriteArrayList();
    private final zze zzso = new zze();
    private final zza zzsp = new zza();
    private volatile zzx zzsq;
    /* access modifiers changed from: private */
    public UncaughtExceptionHandler zzsr;

    class zza extends ThreadPoolExecutor {
        public zza() {
            super(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
            setThreadFactory(new zzb(null));
            allowCoreThreadTimeOut(true);
        }

        /* access modifiers changed from: protected */
        public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return new zzm(this, runnable, t);
        }
    }

    static class zzb implements ThreadFactory {
        private static final AtomicInteger zzsv = new AtomicInteger();

        private zzb() {
        }

        public final Thread newThread(Runnable runnable) {
            int incrementAndGet = zzsv.incrementAndGet();
            StringBuilder sb = new StringBuilder(23);
            sb.append("measurement-");
            sb.append(incrementAndGet);
            return new zzc(runnable, sb.toString());
        }

        /* synthetic */ zzb(zzl zzl) {
            this();
        }
    }

    static class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        public final void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    @VisibleForTesting
    private zzk(Context context) {
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzri = applicationContext;
    }

    public static zzk zzb(Context context) {
        Preconditions.checkNotNull(context);
        if (zzsm == null) {
            synchronized (zzk.class) {
                if (zzsm == null) {
                    zzsm = new zzk(context);
                }
            }
        }
        return zzsm;
    }

    public final zzx zzad() {
        if (this.zzsq == null) {
            synchronized (this) {
                if (this.zzsq == null) {
                    zzx zzx = new zzx();
                    PackageManager packageManager = this.zzri.getPackageManager();
                    String packageName = this.zzri.getPackageName();
                    zzx.setAppId(packageName);
                    zzx.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.zzri.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (NameNotFoundException unused) {
                        String str2 = "GAv4";
                        String str3 = "Error retrieving package info: appName set to ";
                        String valueOf = String.valueOf(packageName);
                        Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    }
                    zzx.setAppName(packageName);
                    zzx.setAppVersion(str);
                    this.zzsq = zzx;
                }
            }
        }
        return this.zzsq;
    }

    public final zzac zzae() {
        DisplayMetrics displayMetrics = this.zzri.getResources().getDisplayMetrics();
        zzac zzac = new zzac();
        zzac.setLanguage(zzdg.zza(Locale.getDefault()));
        zzac.zzuh = displayMetrics.widthPixels;
        zzac.zzui = displayMetrics.heightPixels;
        return zzac;
    }

    /* access modifiers changed from: 0000 */
    public final void zze(zzg zzg) {
        if (zzg.zzaa()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        } else if (!zzg.zzx()) {
            zzg zzs = zzg.zzs();
            zzs.zzy();
            this.zzsp.execute(new zzl(this, zzs));
        } else {
            throw new IllegalStateException("Measurement can only be submitted once");
        }
    }

    public final Context getContext() {
        return this.zzri;
    }

    public static void zzaf() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final void zza(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzsr = uncaughtExceptionHandler;
    }

    public final <V> Future<V> zza(Callable<V> callable) {
        Preconditions.checkNotNull(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzsp.submit(callable);
        }
        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    public final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        this.zzsp.submit(runnable);
    }

    /* access modifiers changed from: private */
    public static void zzb(zzg zzg) {
        Preconditions.checkNotMainThread("deliver should be called from worker thread");
        Preconditions.checkArgument(zzg.zzx(), "Measurement must be submitted");
        List<zzo> zzu = zzg.zzu();
        if (!zzu.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (zzo zzo : zzu) {
                Uri zzo2 = zzo.zzo();
                if (!hashSet.contains(zzo2)) {
                    hashSet.add(zzo2);
                    zzo.zzb(zzg);
                }
            }
        }
    }
}
