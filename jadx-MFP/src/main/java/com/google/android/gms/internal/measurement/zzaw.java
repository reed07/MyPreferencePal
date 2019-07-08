package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.lang.Thread.UncaughtExceptionHandler;

@SuppressLint({"StaticFieldLeak"})
public class zzaw {
    private static volatile zzaw zzwb;
    private final Context zzri;
    private final Clock zzrz = DefaultClock.getInstance();
    private final Context zzwc;
    private final zzbx zzwd = new zzbx(this);
    private final zzcp zzwe;
    private final zzk zzwf;
    private final zzal zzwg;
    private final zzcc zzwh;
    private final zzdh zzwi;
    private final zzct zzwj;
    private final GoogleAnalytics zzwk;
    private final zzbo zzwl;
    private final zzak zzwm;
    private final zzbh zzwn;
    private final zzcb zzwo;

    private zzaw(zzay zzay) {
        Context applicationContext = zzay.getApplicationContext();
        Preconditions.checkNotNull(applicationContext, "Application context can't be null");
        Context zzcm = zzay.zzcm();
        Preconditions.checkNotNull(zzcm);
        this.zzri = applicationContext;
        this.zzwc = zzcm;
        zzcp zzcp = new zzcp(this);
        zzcp.zzq();
        this.zzwe = zzcp;
        zzcp zzby = zzby();
        String str = zzav.VERSION;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 134);
        sb.append("Google Analytics ");
        sb.append(str);
        sb.append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4");
        zzby.zzs(sb.toString());
        zzct zzct = new zzct(this);
        zzct.zzq();
        this.zzwj = zzct;
        zzdh zzdh = new zzdh(this);
        zzdh.zzq();
        this.zzwi = zzdh;
        zzal zzal = new zzal(this, zzay);
        zzbo zzbo = new zzbo(this);
        zzak zzak = new zzak(this);
        zzbh zzbh = new zzbh(this);
        zzcb zzcb = new zzcb(this);
        zzk zzb = zzk.zzb(applicationContext);
        zzb.zza((UncaughtExceptionHandler) new zzax(this));
        this.zzwf = zzb;
        GoogleAnalytics googleAnalytics = new GoogleAnalytics(this);
        zzbo.zzq();
        this.zzwl = zzbo;
        zzak.zzq();
        this.zzwm = zzak;
        zzbh.zzq();
        this.zzwn = zzbh;
        zzcb.zzq();
        this.zzwo = zzcb;
        zzcc zzcc = new zzcc(this);
        zzcc.zzq();
        this.zzwh = zzcc;
        zzal.zzq();
        this.zzwg = zzal;
        googleAnalytics.zzq();
        this.zzwk = googleAnalytics;
        zzal.start();
    }

    public static zzaw zzc(Context context) {
        Preconditions.checkNotNull(context);
        if (zzwb == null) {
            synchronized (zzaw.class) {
                if (zzwb == null) {
                    Clock instance = DefaultClock.getInstance();
                    long elapsedRealtime = instance.elapsedRealtime();
                    zzaw zzaw = new zzaw(new zzay(context));
                    zzwb = zzaw;
                    GoogleAnalytics.zzr();
                    long elapsedRealtime2 = instance.elapsedRealtime() - elapsedRealtime;
                    long longValue = ((Long) zzcf.zzaal.get()).longValue();
                    if (elapsedRealtime2 > longValue) {
                        zzaw.zzby().zzc("Slow initialization (ms)", Long.valueOf(elapsedRealtime2), Long.valueOf(longValue));
                    }
                }
            }
        }
        return zzwb;
    }

    public final Context getContext() {
        return this.zzri;
    }

    public final Context zzcm() {
        return this.zzwc;
    }

    public final Clock zzbx() {
        return this.zzrz;
    }

    public final zzbx zzbz() {
        return this.zzwd;
    }

    public final zzcp zzby() {
        zza(this.zzwe);
        return this.zzwe;
    }

    public final zzcp zzcn() {
        return this.zzwe;
    }

    public final zzk zzca() {
        Preconditions.checkNotNull(this.zzwf);
        return this.zzwf;
    }

    public final zzal zzcc() {
        zza(this.zzwg);
        return this.zzwg;
    }

    public final zzcc zzcd() {
        zza(this.zzwh);
        return this.zzwh;
    }

    public final GoogleAnalytics zzco() {
        Preconditions.checkNotNull(this.zzwk);
        Preconditions.checkArgument(this.zzwk.isInitialized(), "Analytics instance not initialized");
        return this.zzwk;
    }

    public final zzdh zzce() {
        zza(this.zzwi);
        return this.zzwi;
    }

    public final zzct zzcf() {
        zza(this.zzwj);
        return this.zzwj;
    }

    public final zzct zzcp() {
        zzct zzct = this.zzwj;
        if (zzct == null || !zzct.isInitialized()) {
            return null;
        }
        return this.zzwj;
    }

    public final zzak zzcq() {
        zza(this.zzwm);
        return this.zzwm;
    }

    public final zzbo zzcr() {
        zza(this.zzwl);
        return this.zzwl;
    }

    public final zzbh zzci() {
        zza(this.zzwn);
        return this.zzwn;
    }

    public final zzcb zzcj() {
        return this.zzwo;
    }

    private static void zza(zzau zzau) {
        Preconditions.checkNotNull(zzau, "Analytics service not created/initialized");
        Preconditions.checkArgument(zzau.isInitialized(), "Analytics service not initialized");
    }
}
