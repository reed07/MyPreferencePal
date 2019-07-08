package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.measurement.internal.zzai.zza;
import java.lang.reflect.InvocationTargetException;

public final class zzq extends zzcr {
    private Boolean zzagw;
    @NonNull
    private zzs zzagx = zzr.zzagy;
    private Boolean zzyk;

    zzq(zzbw zzbw) {
        super(zzbw);
        zzai.zza(zzbw);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(@NonNull zzs zzs) {
        this.zzagx = zzs;
    }

    static String zzhy() {
        return (String) zzai.zzaiu.get();
    }

    @WorkerThread
    public final int zzaq(@Size String str) {
        return zzb(str, zzai.zzaji);
    }

    public final long zzhh() {
        zzgw();
        return 14711;
    }

    public final boolean zzdw() {
        if (this.zzyk == null) {
            synchronized (this) {
                if (this.zzyk == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzyk = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzyk == null) {
                        this.zzyk = Boolean.TRUE;
                        zzgt().zzjg().zzby("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzyk.booleanValue();
    }

    @WorkerThread
    public final long zza(String str, @NonNull zza<Long> zza) {
        if (str == null) {
            return ((Long) zza.get()).longValue();
        }
        String zzf = this.zzagx.zzf(str, zza.getKey());
        if (TextUtils.isEmpty(zzf)) {
            return ((Long) zza.get()).longValue();
        }
        try {
            return ((Long) zza.get(Long.valueOf(Long.parseLong(zzf)))).longValue();
        } catch (NumberFormatException unused) {
            return ((Long) zza.get()).longValue();
        }
    }

    @WorkerThread
    public final int zzb(String str, @NonNull zza<Integer> zza) {
        if (str == null) {
            return ((Integer) zza.get()).intValue();
        }
        String zzf = this.zzagx.zzf(str, zza.getKey());
        if (TextUtils.isEmpty(zzf)) {
            return ((Integer) zza.get()).intValue();
        }
        try {
            return ((Integer) zza.get(Integer.valueOf(Integer.parseInt(zzf)))).intValue();
        } catch (NumberFormatException unused) {
            return ((Integer) zza.get()).intValue();
        }
    }

    @WorkerThread
    public final double zzc(String str, @NonNull zza<Double> zza) {
        if (str == null) {
            return ((Double) zza.get()).doubleValue();
        }
        String zzf = this.zzagx.zzf(str, zza.getKey());
        if (TextUtils.isEmpty(zzf)) {
            return ((Double) zza.get()).doubleValue();
        }
        try {
            return ((Double) zza.get(Double.valueOf(Double.parseDouble(zzf)))).doubleValue();
        } catch (NumberFormatException unused) {
            return ((Double) zza.get()).doubleValue();
        }
    }

    @WorkerThread
    public final boolean zzd(String str, @NonNull zza<Boolean> zza) {
        if (str == null) {
            return ((Boolean) zza.get()).booleanValue();
        }
        String zzf = this.zzagx.zzf(str, zza.getKey());
        if (TextUtils.isEmpty(zzf)) {
            return ((Boolean) zza.get()).booleanValue();
        }
        return ((Boolean) zza.get(Boolean.valueOf(Boolean.parseBoolean(zzf)))).booleanValue();
    }

    public final boolean zze(String str, zza<Boolean> zza) {
        return zzd(str, zza);
    }

    public final boolean zza(zza<Boolean> zza) {
        return zzd(null, zza);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @VisibleForTesting
    public final Boolean zzar(@Size String str) {
        Preconditions.checkNotEmpty(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzgt().zzjg().zzby("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo == null) {
                zzgt().zzjg().zzby("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (applicationInfo.metaData == null) {
                zzgt().zzjg().zzby("Failed to load metadata: Metadata bundle is null");
                return null;
            } else if (!applicationInfo.metaData.containsKey(str)) {
                return null;
            } else {
                return Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
            }
        } catch (NameNotFoundException e) {
            zzgt().zzjg().zzg("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public final boolean zzhz() {
        zzgw();
        Boolean zzar = zzar("firebase_analytics_collection_deactivated");
        return zzar != null && zzar.booleanValue();
    }

    public final Boolean zzia() {
        zzgw();
        return zzar("firebase_analytics_collection_enabled");
    }

    public static long zzib() {
        return ((Long) zzai.zzajx.get()).longValue();
    }

    public static long zzic() {
        return ((Long) zzai.zzaix.get()).longValue();
    }

    public final String zzid() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            zzgt().zzjg().zzg("Could not find SystemProperties class", e);
            return "";
        } catch (NoSuchMethodException e2) {
            zzgt().zzjg().zzg("Could not find SystemProperties.get() method", e2);
            return "";
        } catch (IllegalAccessException e3) {
            zzgt().zzjg().zzg("Could not access SystemProperties.get()", e3);
            return "";
        } catch (InvocationTargetException e4) {
            zzgt().zzjg().zzg("SystemProperties.get() threw an exception", e4);
            return "";
        }
    }

    public static boolean zzie() {
        return ((Boolean) zzai.zzait.get()).booleanValue();
    }

    public final boolean zzas(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzagx.zzf(str, "gaia_collection_enabled"));
    }

    public final boolean zzat(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzagx.zzf(str, "measurement.event_sampling_enabled"));
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzau(String str) {
        return zzd(str, zzai.zzakh);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzav(String str) {
        return zzd(str, zzai.zzakj);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzaw(String str) {
        return zzd(str, zzai.zzakk);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzax(String str) {
        return zzd(str, zzai.zzakb);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final String zzay(String str) {
        zza<String> zza = zzai.zzakc;
        if (str == null) {
            return (String) zza.get();
        }
        return (String) zza.get(this.zzagx.zzf(str, zza.getKey()));
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzaz(String str) {
        return zzd(str, zzai.zzakl);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzba(String str) {
        return zzd(str, zzai.zzakm);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzbb(String str) {
        return zzd(str, zzai.zzako);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzbc(String str) {
        return zzd(str, zzai.zzakp);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzbd(String str) {
        return zzd(str, zzai.zzakq);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzbe(String str) {
        return zzd(str, zzai.zzaks);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzif() {
        if (this.zzagw == null) {
            this.zzagw = zzar("app_measurement_lite");
            if (this.zzagw == null) {
                this.zzagw = Boolean.valueOf(false);
            }
        }
        if (this.zzagw.booleanValue() || !this.zzada.zzkr()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzbf(String str) {
        return zzd(str, zzai.zzakr);
    }

    @WorkerThread
    static boolean zzig() {
        return ((Boolean) zzai.zzakt.get()).booleanValue();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzbg(String str) {
        return zzd(str, zzai.zzaku);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzbh(String str) {
        return zzd(str, zzai.zzakv);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzbi(String str) {
        return zzd(str, zzai.zzakw);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzbj(String str) {
        return zzd(str, zzai.zzakx);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zzbk(String str) {
        return zzd(str, zzai.zzalb);
    }

    public final /* bridge */ /* synthetic */ void zzgf() {
        super.zzgf();
    }

    public final /* bridge */ /* synthetic */ void zzgg() {
        super.zzgg();
    }

    public final /* bridge */ /* synthetic */ void zzgh() {
        super.zzgh();
    }

    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    public final /* bridge */ /* synthetic */ zzaa zzgp() {
        return super.zzgp();
    }

    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzaq zzgq() {
        return super.zzgq();
    }

    public final /* bridge */ /* synthetic */ zzfx zzgr() {
        return super.zzgr();
    }

    public final /* bridge */ /* synthetic */ zzbr zzgs() {
        return super.zzgs();
    }

    public final /* bridge */ /* synthetic */ zzas zzgt() {
        return super.zzgt();
    }

    public final /* bridge */ /* synthetic */ zzbd zzgu() {
        return super.zzgu();
    }

    public final /* bridge */ /* synthetic */ zzq zzgv() {
        return super.zzgv();
    }

    public final /* bridge */ /* synthetic */ zzn zzgw() {
        return super.zzgw();
    }
}
