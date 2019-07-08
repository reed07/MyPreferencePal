package com.google.android.gms.internal.measurement;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.common.internal.Preconditions;

public final class zzcc extends zzau {
    private boolean zzyr;
    private boolean zzys;
    private final AlarmManager zzyt = ((AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM));
    private Integer zzyu;

    protected zzcc(zzaw zzaw) {
        super(zzaw);
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
        try {
            cancel();
            if (zzbx.zzea() > 0) {
                Context context = getContext();
                ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsReceiver"), 0);
                if (receiverInfo != null && receiverInfo.enabled) {
                    zzq("Receiver registered for local dispatch.");
                    this.zzyr = true;
                }
            }
        } catch (NameNotFoundException unused) {
        }
    }

    public final boolean zzem() {
        return this.zzyr;
    }

    public final boolean zzej() {
        return this.zzys;
    }

    public final void zzen() {
        zzcl();
        Preconditions.checkState(this.zzyr, "Receiver not registered");
        long zzea = zzbx.zzea();
        if (zzea > 0) {
            cancel();
            long elapsedRealtime = zzbx().elapsedRealtime() + zzea;
            this.zzys = true;
            ((Boolean) zzcf.zzaam.get()).booleanValue();
            if (VERSION.SDK_INT >= 24) {
                zzq("Scheduling upload with JobScheduler");
                Context context = getContext();
                ComponentName componentName = new ComponentName(context, "com.google.android.gms.analytics.AnalyticsJobService");
                int jobId = getJobId();
                PersistableBundle persistableBundle = new PersistableBundle();
                persistableBundle.putString("action", "com.google.android.gms.analytics.ANALYTICS_DISPATCH");
                JobInfo build = new Builder(jobId, componentName).setMinimumLatency(zzea).setOverrideDeadline(zzea << 1).setExtras(persistableBundle).build();
                zza("Scheduling job. JobID", Integer.valueOf(jobId));
                zzdi.zza(context, build, "com.google.android.gms", "DispatchAlarm");
                return;
            }
            zzq("Scheduling upload with AlarmManager");
            this.zzyt.setInexactRepeating(2, elapsedRealtime, zzea, zzeo());
        }
    }

    private final PendingIntent zzeo() {
        Context context = getContext();
        return PendingIntent.getBroadcast(context, 0, new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH").setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsReceiver")), 0);
    }

    public final void cancel() {
        this.zzys = false;
        this.zzyt.cancel(zzeo());
        if (VERSION.SDK_INT >= 24) {
            JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
            int jobId = getJobId();
            zza("Cancelling job. JobID", Integer.valueOf(jobId));
            jobScheduler.cancel(jobId);
        }
    }

    private final int getJobId() {
        if (this.zzyu == null) {
            String str = "analytics";
            String valueOf = String.valueOf(getContext().getPackageName());
            this.zzyu = Integer.valueOf((valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).hashCode());
        }
        return this.zzyu.intValue();
    }
}
