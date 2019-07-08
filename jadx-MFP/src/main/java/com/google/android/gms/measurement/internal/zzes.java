package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzes implements ServiceConnection, BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    final /* synthetic */ zzeb zzasl;
    /* access modifiers changed from: private */
    public volatile boolean zzasr;
    private volatile zzar zzass;

    protected zzes(zzeb zzeb) {
        this.zzasl = zzeb;
    }

    @WorkerThread
    public final void zzb(Intent intent) {
        this.zzasl.zzaf();
        Context context = this.zzasl.getContext();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzasr) {
                this.zzasl.zzgt().zzjo().zzby("Connection attempt already in progress");
                return;
            }
            this.zzasl.zzgt().zzjo().zzby("Using local app measurement service");
            this.zzasr = true;
            instance.bindService(context, intent, this.zzasl.zzase, 129);
        }
    }

    @WorkerThread
    public final void zzlk() {
        if (this.zzass != null && (this.zzass.isConnected() || this.zzass.isConnecting())) {
            this.zzass.disconnect();
        }
        this.zzass = null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r3.zzasl.zzgt().zzjg().zzby("Service connect failed to get IMeasurementService");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0063 */
    @android.support.annotation.MainThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r4, android.os.IBinder r5) {
        /*
            r3 = this;
            java.lang.String r4 = "MeasurementServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r4)
            monitor-enter(r3)
            r4 = 0
            if (r5 != 0) goto L_0x001f
            r3.zzasr = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzeb r4 = r3.zzasl     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzas r4 = r4.zzgt()     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzau r4 = r4.zzjg()     // Catch:{ all -> 0x001c }
            java.lang.String r5 = "Service connected with null binder"
            r4.zzby(r5)     // Catch:{ all -> 0x001c }
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x001c:
            r4 = move-exception
            goto L_0x009a
        L_0x001f:
            r0 = 0
            java.lang.String r1 = r5.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x0063 }
            java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
            boolean r2 = r2.equals(r1)     // Catch:{ RemoteException -> 0x0063 }
            if (r2 == 0) goto L_0x0053
            if (r5 != 0) goto L_0x002f
            goto L_0x0043
        L_0x002f:
            java.lang.String r1 = "com.google.android.gms.measurement.internal.IMeasurementService"
            android.os.IInterface r1 = r5.queryLocalInterface(r1)     // Catch:{ RemoteException -> 0x0063 }
            boolean r2 = r1 instanceof com.google.android.gms.measurement.internal.zzaj     // Catch:{ RemoteException -> 0x0063 }
            if (r2 == 0) goto L_0x003d
            com.google.android.gms.measurement.internal.zzaj r1 = (com.google.android.gms.measurement.internal.zzaj) r1     // Catch:{ RemoteException -> 0x0063 }
            r0 = r1
            goto L_0x0043
        L_0x003d:
            com.google.android.gms.measurement.internal.zzal r1 = new com.google.android.gms.measurement.internal.zzal     // Catch:{ RemoteException -> 0x0063 }
            r1.<init>(r5)     // Catch:{ RemoteException -> 0x0063 }
            r0 = r1
        L_0x0043:
            com.google.android.gms.measurement.internal.zzeb r5 = r3.zzasl     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.measurement.internal.zzas r5 = r5.zzgt()     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.measurement.internal.zzau r5 = r5.zzjo()     // Catch:{ RemoteException -> 0x0063 }
            java.lang.String r1 = "Bound to IMeasurementService interface"
            r5.zzby(r1)     // Catch:{ RemoteException -> 0x0063 }
            goto L_0x0072
        L_0x0053:
            com.google.android.gms.measurement.internal.zzeb r5 = r3.zzasl     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.measurement.internal.zzas r5 = r5.zzgt()     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.measurement.internal.zzau r5 = r5.zzjg()     // Catch:{ RemoteException -> 0x0063 }
            java.lang.String r2 = "Got binder with a wrong descriptor"
            r5.zzg(r2, r1)     // Catch:{ RemoteException -> 0x0063 }
            goto L_0x0072
        L_0x0063:
            com.google.android.gms.measurement.internal.zzeb r5 = r3.zzasl     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzas r5 = r5.zzgt()     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzau r5 = r5.zzjg()     // Catch:{ all -> 0x001c }
            java.lang.String r1 = "Service connect failed to get IMeasurementService"
            r5.zzby(r1)     // Catch:{ all -> 0x001c }
        L_0x0072:
            if (r0 != 0) goto L_0x008a
            r3.zzasr = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.common.stats.ConnectionTracker r4 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x0098 }
            com.google.android.gms.measurement.internal.zzeb r5 = r3.zzasl     // Catch:{ IllegalArgumentException -> 0x0098 }
            android.content.Context r5 = r5.getContext()     // Catch:{ IllegalArgumentException -> 0x0098 }
            com.google.android.gms.measurement.internal.zzeb r0 = r3.zzasl     // Catch:{ IllegalArgumentException -> 0x0098 }
            com.google.android.gms.measurement.internal.zzes r0 = r0.zzase     // Catch:{ IllegalArgumentException -> 0x0098 }
            r4.unbindService(r5, r0)     // Catch:{ IllegalArgumentException -> 0x0098 }
            goto L_0x0098
        L_0x008a:
            com.google.android.gms.measurement.internal.zzeb r4 = r3.zzasl     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzbr r4 = r4.zzgs()     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzet r5 = new com.google.android.gms.measurement.internal.zzet     // Catch:{ all -> 0x001c }
            r5.<init>(r3, r0)     // Catch:{ all -> 0x001c }
            r4.zzc(r5)     // Catch:{ all -> 0x001c }
        L_0x0098:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x009a:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzes.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zzasl.zzgt().zzjn().zzby("Service disconnected");
        this.zzasl.zzgs().zzc((Runnable) new zzeu(this, componentName));
    }

    @WorkerThread
    public final void zzll() {
        this.zzasl.zzaf();
        Context context = this.zzasl.getContext();
        synchronized (this) {
            if (this.zzasr) {
                this.zzasl.zzgt().zzjo().zzby("Connection attempt already in progress");
            } else if (this.zzass == null || (!this.zzass.isConnecting() && !this.zzass.isConnected())) {
                this.zzass = new zzar(context, Looper.getMainLooper(), this, this);
                this.zzasl.zzgt().zzjo().zzby("Connecting to remote service");
                this.zzasr = true;
                this.zzass.checkAvailabilityAndConnect();
            } else {
                this.zzasl.zzgt().zzjo().zzby("Already awaiting connection attempt");
            }
        }
    }

    @MainThread
    public final void onConnected(@Nullable Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                this.zzasl.zzgs().zzc((Runnable) new zzev(this, (zzaj) this.zzass.getService()));
            } catch (DeadObjectException | IllegalStateException unused) {
                this.zzass = null;
                this.zzasr = false;
            }
        }
    }

    @MainThread
    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zzasl.zzgt().zzjn().zzby("Service connection suspended");
        this.zzasl.zzgs().zzc((Runnable) new zzew(this));
    }

    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzas zzkj = this.zzasl.zzada.zzkj();
        if (zzkj != null) {
            zzkj.zzjj().zzg("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzasr = false;
            this.zzass = null;
        }
        this.zzasl.zzgs().zzc((Runnable) new zzex(this));
    }
}
