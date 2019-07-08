package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.ServiceConnection;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzbc implements ServiceConnection {
    final /* synthetic */ zzba zzxa;
    private volatile zzcl zzxb;
    private volatile boolean zzxc;

    protected zzbc(zzba zzba) {
        this.zzxa = zzba;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:9|10|11|12|13|(1:15)) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0075, code lost:
        return r0;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x005e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzcl zzda() {
        /*
            r6 = this;
            com.google.android.gms.analytics.zzk.zzaf()
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.google.android.gms.analytics.service.START"
            r0.<init>(r1)
            android.content.ComponentName r1 = new android.content.ComponentName
            java.lang.String r2 = "com.google.android.gms"
            java.lang.String r3 = "com.google.android.gms.analytics.service.AnalyticsService"
            r1.<init>(r2, r3)
            r0.setComponent(r1)
            com.google.android.gms.internal.measurement.zzba r1 = r6.zzxa
            android.content.Context r1 = r1.getContext()
            java.lang.String r2 = "app_package_name"
            java.lang.String r3 = r1.getPackageName()
            r0.putExtra(r2, r3)
            com.google.android.gms.common.stats.ConnectionTracker r2 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()
            monitor-enter(r6)
            r3 = 0
            r6.zzxb = r3     // Catch:{ all -> 0x0076 }
            r4 = 1
            r6.zzxc = r4     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.measurement.zzba r4 = r6.zzxa     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.measurement.zzbc r4 = r4.zzww     // Catch:{ all -> 0x0076 }
            r5 = 129(0x81, float:1.81E-43)
            boolean r0 = r2.bindService(r1, r0, r4, r5)     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.measurement.zzba r1 = r6.zzxa     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = "Bind to service requested"
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0076 }
            r1.zza(r2, r4)     // Catch:{ all -> 0x0076 }
            r1 = 0
            if (r0 != 0) goto L_0x004e
            r6.zzxc = r1     // Catch:{ all -> 0x0076 }
            monitor-exit(r6)     // Catch:{ all -> 0x0076 }
            return r3
        L_0x004e:
            com.google.android.gms.internal.measurement.zzcg<java.lang.Long> r0 = com.google.android.gms.internal.measurement.zzcf.zzaag     // Catch:{ InterruptedException -> 0x005e }
            java.lang.Object r0 = r0.get()     // Catch:{ InterruptedException -> 0x005e }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ InterruptedException -> 0x005e }
            long r4 = r0.longValue()     // Catch:{ InterruptedException -> 0x005e }
            r6.wait(r4)     // Catch:{ InterruptedException -> 0x005e }
            goto L_0x0065
        L_0x005e:
            com.google.android.gms.internal.measurement.zzba r0 = r6.zzxa     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = "Wait for service connect was interrupted"
            r0.zzt(r2)     // Catch:{ all -> 0x0076 }
        L_0x0065:
            r6.zzxc = r1     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.measurement.zzcl r0 = r6.zzxb     // Catch:{ all -> 0x0076 }
            r6.zzxb = r3     // Catch:{ all -> 0x0076 }
            if (r0 != 0) goto L_0x0074
            com.google.android.gms.internal.measurement.zzba r1 = r6.zzxa     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = "Successfully bound to service but never got onServiceConnected callback"
            r1.zzu(r2)     // Catch:{ all -> 0x0076 }
        L_0x0074:
            monitor-exit(r6)     // Catch:{ all -> 0x0076 }
            return r0
        L_0x0076:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0076 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbc.zzda():com.google.android.gms.internal.measurement.zzcl");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:22|23) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:10|11|12|(2:(1:15)(2:16|(1:18)(1:19))|20)(1:21)|(2:25|26)(3:27|28|(1:30)(1:31))|32|33|34|35) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r2.zzxa.zzu("Service connect failed to get IAnalyticsService");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x004b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0084 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r3, android.os.IBinder r4) {
        /*
            r2 = this;
            java.lang.String r3 = "AnalyticsServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r3)
            monitor-enter(r2)
            if (r4 != 0) goto L_0x0017
            com.google.android.gms.internal.measurement.zzba r3 = r2.zzxa     // Catch:{ all -> 0x0014 }
            java.lang.String r4 = "Service connected with null binder"
            r3.zzu(r4)     // Catch:{ all -> 0x0014 }
            r2.notifyAll()     // Catch:{ all -> 0x008d }
            monitor-exit(r2)     // Catch:{ all -> 0x008d }
            return
        L_0x0014:
            r3 = move-exception
            goto L_0x0089
        L_0x0017:
            r3 = 0
            java.lang.String r0 = r4.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x004b }
            java.lang.String r1 = "com.google.android.gms.analytics.internal.IAnalyticsService"
            boolean r1 = r1.equals(r0)     // Catch:{ RemoteException -> 0x004b }
            if (r1 == 0) goto L_0x0043
            if (r4 != 0) goto L_0x0027
            goto L_0x003b
        L_0x0027:
            java.lang.String r0 = "com.google.android.gms.analytics.internal.IAnalyticsService"
            android.os.IInterface r0 = r4.queryLocalInterface(r0)     // Catch:{ RemoteException -> 0x004b }
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzcl     // Catch:{ RemoteException -> 0x004b }
            if (r1 == 0) goto L_0x0035
            com.google.android.gms.internal.measurement.zzcl r0 = (com.google.android.gms.internal.measurement.zzcl) r0     // Catch:{ RemoteException -> 0x004b }
            r3 = r0
            goto L_0x003b
        L_0x0035:
            com.google.android.gms.internal.measurement.zzcm r0 = new com.google.android.gms.internal.measurement.zzcm     // Catch:{ RemoteException -> 0x004b }
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x004b }
            r3 = r0
        L_0x003b:
            com.google.android.gms.internal.measurement.zzba r4 = r2.zzxa     // Catch:{ RemoteException -> 0x004b }
            java.lang.String r0 = "Bound to IAnalyticsService interface"
            r4.zzq(r0)     // Catch:{ RemoteException -> 0x004b }
            goto L_0x0052
        L_0x0043:
            com.google.android.gms.internal.measurement.zzba r4 = r2.zzxa     // Catch:{ RemoteException -> 0x004b }
            java.lang.String r1 = "Got binder with a wrong descriptor"
            r4.zze(r1, r0)     // Catch:{ RemoteException -> 0x004b }
            goto L_0x0052
        L_0x004b:
            com.google.android.gms.internal.measurement.zzba r4 = r2.zzxa     // Catch:{ all -> 0x0014 }
            java.lang.String r0 = "Service connect failed to get IAnalyticsService"
            r4.zzu(r0)     // Catch:{ all -> 0x0014 }
        L_0x0052:
            if (r3 != 0) goto L_0x0068
            com.google.android.gms.common.stats.ConnectionTracker r3 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x0084 }
            com.google.android.gms.internal.measurement.zzba r4 = r2.zzxa     // Catch:{ IllegalArgumentException -> 0x0084 }
            android.content.Context r4 = r4.getContext()     // Catch:{ IllegalArgumentException -> 0x0084 }
            com.google.android.gms.internal.measurement.zzba r0 = r2.zzxa     // Catch:{ IllegalArgumentException -> 0x0084 }
            com.google.android.gms.internal.measurement.zzbc r0 = r0.zzww     // Catch:{ IllegalArgumentException -> 0x0084 }
            r3.unbindService(r4, r0)     // Catch:{ IllegalArgumentException -> 0x0084 }
            goto L_0x0084
        L_0x0068:
            boolean r4 = r2.zzxc     // Catch:{ all -> 0x0014 }
            if (r4 != 0) goto L_0x0082
            com.google.android.gms.internal.measurement.zzba r4 = r2.zzxa     // Catch:{ all -> 0x0014 }
            java.lang.String r0 = "onServiceConnected received after the timeout limit"
            r4.zzt(r0)     // Catch:{ all -> 0x0014 }
            com.google.android.gms.internal.measurement.zzba r4 = r2.zzxa     // Catch:{ all -> 0x0014 }
            com.google.android.gms.analytics.zzk r4 = r4.zzca()     // Catch:{ all -> 0x0014 }
            com.google.android.gms.internal.measurement.zzbd r0 = new com.google.android.gms.internal.measurement.zzbd     // Catch:{ all -> 0x0014 }
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0014 }
            r4.zza(r0)     // Catch:{ all -> 0x0014 }
            goto L_0x0084
        L_0x0082:
            r2.zzxb = r3     // Catch:{ all -> 0x0014 }
        L_0x0084:
            r2.notifyAll()     // Catch:{ all -> 0x008d }
            monitor-exit(r2)     // Catch:{ all -> 0x008d }
            return
        L_0x0089:
            r2.notifyAll()     // Catch:{ all -> 0x008d }
            throw r3     // Catch:{ all -> 0x008d }
        L_0x008d:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x008d }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbc.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("AnalyticsServiceConnection.onServiceDisconnected");
        this.zzxa.zzca().zza((Runnable) new zzbe(this, componentName));
    }
}
