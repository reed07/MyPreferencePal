package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzsi;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class zzbw implements zzct {
    private static volatile zzbw zzapj;
    private final boolean zzadg;
    private final String zzadi;
    private final long zzago;
    private final zzn zzaih;
    private final String zzapk;
    private final String zzapl;
    private final zzq zzapm;
    private final zzbd zzapn;
    private final zzas zzapo;
    private final zzbr zzapp;
    private final zzfd zzapq;
    private final AppMeasurement zzapr;
    private final zzfx zzaps;
    private final zzaq zzapt;
    private final zzdy zzapu;
    private final zzda zzapv;
    private final zza zzapw;
    private zzao zzapx;
    private zzeb zzapy;
    private zzaa zzapz;
    private zzam zzaqa;
    private zzbj zzaqb;
    private Boolean zzaqc;
    private long zzaqd;
    private volatile Boolean zzaqe;
    @VisibleForTesting
    private Boolean zzaqf;
    @VisibleForTesting
    private Boolean zzaqg;
    private int zzaqh;
    private AtomicInteger zzaqi = new AtomicInteger(0);
    private final Context zzri;
    private final Clock zzrz;
    private boolean zzvz = false;

    private zzbw(zzcz zzcz) {
        Preconditions.checkNotNull(zzcz);
        this.zzaih = new zzn(zzcz.zzri);
        zzai.zza(this.zzaih);
        this.zzri = zzcz.zzri;
        this.zzadi = zzcz.zzadi;
        this.zzapk = zzcz.zzapk;
        this.zzapl = zzcz.zzapl;
        this.zzadg = zzcz.zzadg;
        this.zzaqe = zzcz.zzaqe;
        zzan zzan = zzcz.zzaqz;
        if (!(zzan == null || zzan.zzadj == null)) {
            Object obj = zzan.zzadj.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzaqf = (Boolean) obj;
            }
            Object obj2 = zzan.zzadj.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzaqg = (Boolean) obj2;
            }
        }
        zzsi.zzae(this.zzri);
        this.zzrz = DefaultClock.getInstance();
        this.zzago = this.zzrz.currentTimeMillis();
        this.zzapm = new zzq(this);
        zzbd zzbd = new zzbd(this);
        zzbd.zzq();
        this.zzapn = zzbd;
        zzas zzas = new zzas(this);
        zzas.zzq();
        this.zzapo = zzas;
        zzfx zzfx = new zzfx(this);
        zzfx.zzq();
        this.zzaps = zzfx;
        zzaq zzaq = new zzaq(this);
        zzaq.zzq();
        this.zzapt = zzaq;
        this.zzapw = new zza(this);
        zzdy zzdy = new zzdy(this);
        zzdy.zzq();
        this.zzapu = zzdy;
        zzda zzda = new zzda(this);
        zzda.zzq();
        this.zzapv = zzda;
        this.zzapr = new AppMeasurement(this);
        zzfd zzfd = new zzfd(this);
        zzfd.zzq();
        this.zzapq = zzfd;
        zzbr zzbr = new zzbr(this);
        zzbr.zzq();
        this.zzapp = zzbr;
        zzn zzn = this.zzaih;
        if (this.zzri.getApplicationContext() instanceof Application) {
            zzda zzgj = zzgj();
            if (zzgj.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzgj.getContext().getApplicationContext();
                if (zzgj.zzara == null) {
                    zzgj.zzara = new zzdu(zzgj, null);
                }
                application.unregisterActivityLifecycleCallbacks(zzgj.zzara);
                application.registerActivityLifecycleCallbacks(zzgj.zzara);
                zzgj.zzgt().zzjo().zzby("Registered activity lifecycle callback");
            }
        } else {
            zzgt().zzjj().zzby("Application context is not an Application");
        }
        this.zzapp.zzc((Runnable) new zzbx(this, zzcz));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzcz zzcz) {
        String str;
        zzau zzau;
        zzgs().zzaf();
        zzq.zzhy();
        zzaa zzaa = new zzaa(this);
        zzaa.zzq();
        this.zzapz = zzaa;
        zzam zzam = new zzam(this);
        zzam.zzq();
        this.zzaqa = zzam;
        zzao zzao = new zzao(this);
        zzao.zzq();
        this.zzapx = zzao;
        zzeb zzeb = new zzeb(this);
        zzeb.zzq();
        this.zzapy = zzeb;
        this.zzaps.zzgx();
        this.zzapn.zzgx();
        this.zzaqb = new zzbj(this);
        this.zzaqa.zzgx();
        zzgt().zzjm().zzg("App measurement is starting up, version", Long.valueOf(this.zzapm.zzhh()));
        zzn zzn = this.zzaih;
        zzgt().zzjm().zzby("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzn zzn2 = this.zzaih;
        String zzal = zzam.zzal();
        if (TextUtils.isEmpty(this.zzadi)) {
            if (zzgr().zzcz(zzal)) {
                zzau = zzgt().zzjm();
                str = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzau = zzgt().zzjm();
                String str2 = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
                String valueOf = String.valueOf(zzal);
                str = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
            }
            zzau.zzby(str);
        }
        zzgt().zzjn().zzby("Debug-level message logging enabled");
        if (this.zzaqh != this.zzaqi.get()) {
            zzgt().zzjg().zze("Not all components initialized", Integer.valueOf(this.zzaqh), Integer.valueOf(this.zzaqi.get()));
        }
        this.zzvz = true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void start() {
        zzgs().zzaf();
        if (zzgu().zzanc.get() == 0) {
            zzgu().zzanc.set(this.zzrz.currentTimeMillis());
        }
        if (Long.valueOf(zzgu().zzanh.get()).longValue() == 0) {
            zzgt().zzjo().zzg("Persisting first open", Long.valueOf(this.zzago));
            zzgu().zzanh.set(this.zzago);
        }
        if (zzkv()) {
            zzn zzn = this.zzaih;
            if (!TextUtils.isEmpty(zzgk().getGmpAppId()) || !TextUtils.isEmpty(zzgk().zzhb())) {
                zzgr();
                if (zzfx.zza(zzgk().getGmpAppId(), zzgu().zzjv(), zzgk().zzhb(), zzgu().zzjw())) {
                    zzgt().zzjm().zzby("Rechecking which service to use due to a GMP App Id change");
                    zzgu().zzjy();
                    zzgn().resetAnalyticsData();
                    this.zzapy.disconnect();
                    this.zzapy.zzdj();
                    zzgu().zzanh.set(this.zzago);
                    zzgu().zzanj.zzcd(null);
                }
                zzgu().zzcb(zzgk().getGmpAppId());
                zzgu().zzcc(zzgk().zzhb());
                if (this.zzapm.zzbi(zzgk().zzal())) {
                    this.zzapq.zzaj(this.zzago);
                }
            }
            zzgj().zzcp(zzgu().zzanj.zzkd());
            zzn zzn2 = this.zzaih;
            if (!TextUtils.isEmpty(zzgk().getGmpAppId()) || !TextUtils.isEmpty(zzgk().zzhb())) {
                boolean isEnabled = isEnabled();
                if (!zzgu().zzkc() && !this.zzapm.zzhz()) {
                    zzgu().zzi(!isEnabled);
                }
                if (!this.zzapm.zzba(zzgk().zzal()) || isEnabled) {
                    zzgj().zzld();
                }
                zzgl().zza(new AtomicReference<>());
            }
        } else if (isEnabled()) {
            if (!zzgr().zzx("android.permission.INTERNET")) {
                zzgt().zzjg().zzby("App is missing INTERNET permission");
            }
            if (!zzgr().zzx("android.permission.ACCESS_NETWORK_STATE")) {
                zzgt().zzjg().zzby("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzn zzn3 = this.zzaih;
            if (!Wrappers.packageManager(this.zzri).isCallerInstantApp() && !this.zzapm.zzif()) {
                if (!zzbm.zza(this.zzri)) {
                    zzgt().zzjg().zzby("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzfx.zza(this.zzri, false)) {
                    zzgt().zzjg().zzby("AppMeasurementService not registered/enabled");
                }
            }
            zzgt().zzjg().zzby("Uploading is not possible. App measurement disabled");
        }
    }

    public final zzn zzgw() {
        return this.zzaih;
    }

    public final zzq zzgv() {
        return this.zzapm;
    }

    public final zzbd zzgu() {
        zza((zzcr) this.zzapn);
        return this.zzapn;
    }

    public final zzas zzgt() {
        zza((zzcs) this.zzapo);
        return this.zzapo;
    }

    public final zzas zzkj() {
        zzas zzas = this.zzapo;
        if (zzas == null || !zzas.isInitialized()) {
            return null;
        }
        return this.zzapo;
    }

    public final zzbr zzgs() {
        zza((zzcs) this.zzapp);
        return this.zzapp;
    }

    public final zzfd zzgo() {
        zza((zzf) this.zzapq);
        return this.zzapq;
    }

    public final zzbj zzkk() {
        return this.zzaqb;
    }

    /* access modifiers changed from: 0000 */
    public final zzbr zzkl() {
        return this.zzapp;
    }

    public final zzda zzgj() {
        zza((zzf) this.zzapv);
        return this.zzapv;
    }

    public final AppMeasurement zzkm() {
        return this.zzapr;
    }

    public final zzfx zzgr() {
        zza((zzcr) this.zzaps);
        return this.zzaps;
    }

    public final zzaq zzgq() {
        zza((zzcr) this.zzapt);
        return this.zzapt;
    }

    public final zzao zzgn() {
        zza((zzf) this.zzapx);
        return this.zzapx;
    }

    public final Context getContext() {
        return this.zzri;
    }

    public final boolean zzkn() {
        return TextUtils.isEmpty(this.zzadi);
    }

    public final String zzko() {
        return this.zzadi;
    }

    public final String zzkp() {
        return this.zzapk;
    }

    public final String zzkq() {
        return this.zzapl;
    }

    public final boolean zzkr() {
        return this.zzadg;
    }

    public final Clock zzbx() {
        return this.zzrz;
    }

    public final zzdy zzgm() {
        zza((zzf) this.zzapu);
        return this.zzapu;
    }

    public final zzeb zzgl() {
        zza((zzf) this.zzapy);
        return this.zzapy;
    }

    public final zzaa zzgp() {
        zza((zzcs) this.zzapz);
        return this.zzapz;
    }

    public final zzam zzgk() {
        zza((zzf) this.zzaqa);
        return this.zzaqa;
    }

    public final zza zzgi() {
        zza zza = this.zzapw;
        if (zza != null) {
            return zza;
        }
        throw new IllegalStateException("Component not created");
    }

    public static zzbw zza(Context context, zzan zzan) {
        if (zzan != null && (zzan.origin == null || zzan.zzadi == null)) {
            zzan zzan2 = new zzan(zzan.zzade, zzan.zzadf, zzan.zzadg, zzan.zzadh, null, null, zzan.zzadj);
            zzan = zzan2;
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzapj == null) {
            synchronized (zzbw.class) {
                if (zzapj == null) {
                    zzapj = new zzbw(new zzcz(context, zzan));
                }
            }
        } else if (!(zzan == null || zzan.zzadj == null || !zzan.zzadj.containsKey("dataCollectionDefaultEnabled"))) {
            zzapj.zzd(zzan.zzadj.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zzapj;
    }

    private final void zzcl() {
        if (!this.zzvz) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    private static void zza(zzcs zzcs) {
        if (zzcs == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzcs.isInitialized()) {
            String valueOf = String.valueOf(zzcs.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzf zzf) {
        if (zzf == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzf.isInitialized()) {
            String valueOf = String.valueOf(zzf.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzcr zzcr) {
        if (zzcr == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzd(boolean z) {
        this.zzaqe = Boolean.valueOf(z);
    }

    @WorkerThread
    public final boolean zzks() {
        return this.zzaqe != null && this.zzaqe.booleanValue();
    }

    @WorkerThread
    public final boolean isEnabled() {
        boolean z;
        zzgs().zzaf();
        zzcl();
        if (this.zzapm.zza(zzai.zzale)) {
            if (this.zzapm.zzhz()) {
                return false;
            }
            Boolean bool = this.zzaqg;
            if (bool != null && bool.booleanValue()) {
                return false;
            }
            Boolean zzjz = zzgu().zzjz();
            if (zzjz != null) {
                return zzjz.booleanValue();
            }
            Boolean zzia = this.zzapm.zzia();
            if (zzia != null) {
                return zzia.booleanValue();
            }
            Boolean bool2 = this.zzaqf;
            if (bool2 != null) {
                return bool2.booleanValue();
            }
            if (GoogleServices.isMeasurementExplicitlyDisabled()) {
                return false;
            }
            if (!this.zzapm.zza(zzai.zzala) || this.zzaqe == null) {
                return true;
            }
            return this.zzaqe.booleanValue();
        } else if (this.zzapm.zzhz()) {
            return false;
        } else {
            Boolean zzia2 = this.zzapm.zzia();
            if (zzia2 != null) {
                z = zzia2.booleanValue();
            } else {
                z = !GoogleServices.isMeasurementExplicitlyDisabled();
                if (z && this.zzaqe != null && ((Boolean) zzai.zzala.get()).booleanValue()) {
                    z = this.zzaqe.booleanValue();
                }
            }
            return zzgu().zzh(z);
        }
    }

    /* access modifiers changed from: 0000 */
    public final long zzkt() {
        Long valueOf = Long.valueOf(zzgu().zzanh.get());
        if (valueOf.longValue() == 0) {
            return this.zzago;
        }
        return Math.min(this.zzago, valueOf.longValue());
    }

    /* access modifiers changed from: 0000 */
    public final void zzgg() {
        zzn zzn = this.zzaih;
    }

    /* access modifiers changed from: 0000 */
    public final void zzgf() {
        zzn zzn = this.zzaih;
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzcs zzcs) {
        this.zzaqh++;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzf zzf) {
        this.zzaqh++;
    }

    /* access modifiers changed from: 0000 */
    public final void zzku() {
        this.zzaqi.incrementAndGet();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final boolean zzkv() {
        zzcl();
        zzgs().zzaf();
        Boolean bool = this.zzaqc;
        if (bool == null || this.zzaqd == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzrz.elapsedRealtime() - this.zzaqd) > 1000)) {
            this.zzaqd = this.zzrz.elapsedRealtime();
            zzn zzn = this.zzaih;
            boolean z = true;
            this.zzaqc = Boolean.valueOf(zzgr().zzx("android.permission.INTERNET") && zzgr().zzx("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzri).isCallerInstantApp() || this.zzapm.zzif() || (zzbm.zza(this.zzri) && zzfx.zza(this.zzri, false))));
            if (this.zzaqc.booleanValue()) {
                if (!zzgr().zzu(zzgk().getGmpAppId(), zzgk().zzhb()) && TextUtils.isEmpty(zzgk().zzhb())) {
                    z = false;
                }
                this.zzaqc = Boolean.valueOf(z);
            }
        }
        return this.zzaqc.booleanValue();
    }
}
