package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzdi;

public final class zzfj extends zzfm {
    private final zzy zzatg;
    private final AlarmManager zzyt = ((AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM));
    private Integer zzyu;

    protected zzfj(zzfn zzfn) {
        super(zzfn);
        this.zzatg = new zzfk(this, zzfn.zzmh(), zzfn);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        this.zzyt.cancel(zzeo());
        if (VERSION.SDK_INT >= 24) {
            zzlr();
        }
        return false;
    }

    @TargetApi(24)
    private final void zzlr() {
        JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
        int jobId = getJobId();
        zzgt().zzjo().zzg("Cancelling job. JobID", Integer.valueOf(jobId));
        jobScheduler.cancel(jobId);
    }

    public final void zzh(long j) {
        zzcl();
        zzgw();
        Context context = getContext();
        if (!zzbm.zza(context)) {
            zzgt().zzjn().zzby("Receiver not registered/enabled");
        }
        if (!zzfx.zza(context, false)) {
            zzgt().zzjn().zzby("Service not registered/enabled");
        }
        cancel();
        long elapsedRealtime = zzbx().elapsedRealtime() + j;
        if (j < Math.max(0, ((Long) zzai.zzajr.get()).longValue()) && !this.zzatg.zzej()) {
            zzgt().zzjo().zzby("Scheduling upload with DelayedRunnable");
            this.zzatg.zzh(j);
        }
        zzgw();
        if (VERSION.SDK_INT >= 24) {
            zzgt().zzjo().zzby("Scheduling upload with JobScheduler");
            Context context2 = getContext();
            ComponentName componentName = new ComponentName(context2, "com.google.android.gms.measurement.AppMeasurementJobService");
            int jobId = getJobId();
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
            JobInfo build = new Builder(jobId, componentName).setMinimumLatency(j).setOverrideDeadline(j << 1).setExtras(persistableBundle).build();
            zzgt().zzjo().zzg("Scheduling job. JobID", Integer.valueOf(jobId));
            zzdi.zza(context2, build, "com.google.android.gms", "UploadAlarm");
            return;
        }
        zzgt().zzjo().zzby("Scheduling upload with AlarmManager");
        this.zzyt.setInexactRepeating(2, elapsedRealtime, Math.max(((Long) zzai.zzajm.get()).longValue(), j), zzeo());
    }

    private final int getJobId() {
        if (this.zzyu == null) {
            String str = "measurement";
            String valueOf = String.valueOf(getContext().getPackageName());
            this.zzyu = Integer.valueOf((valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).hashCode());
        }
        return this.zzyu.intValue();
    }

    public final void cancel() {
        zzcl();
        this.zzyt.cancel(zzeo());
        this.zzatg.cancel();
        if (VERSION.SDK_INT >= 24) {
            zzlr();
        }
    }

    private final PendingIntent zzeo() {
        Context context = getContext();
        return PendingIntent.getBroadcast(context, 0, new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), 0);
    }

    public final /* bridge */ /* synthetic */ zzft zzjr() {
        return super.zzjr();
    }

    public final /* bridge */ /* synthetic */ zzm zzjs() {
        return super.zzjs();
    }

    public final /* bridge */ /* synthetic */ zzt zzjt() {
        return super.zzjt();
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
