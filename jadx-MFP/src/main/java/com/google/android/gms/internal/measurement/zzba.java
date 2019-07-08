package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.os.RemoteException;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;

@VisibleForTesting
public final class zzba extends zzau {
    /* access modifiers changed from: private */
    public final zzbc zzww = new zzbc(this);
    private zzcl zzwx;
    private final zzbz zzwy;
    private final zzdc zzwz;

    protected zzba(zzaw zzaw) {
        super(zzaw);
        this.zzwz = new zzdc(zzaw.zzbx());
        this.zzwy = new zzbb(this, zzaw);
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
    }

    public final boolean isConnected() {
        zzk.zzaf();
        zzcl();
        return this.zzwx != null;
    }

    public final boolean zzb(zzck zzck) {
        String str;
        Preconditions.checkNotNull(zzck);
        zzk.zzaf();
        zzcl();
        zzcl zzcl = this.zzwx;
        if (zzcl == null) {
            return false;
        }
        if (zzck.zzet()) {
            str = zzbx.zzed();
        } else {
            str = zzbx.zzee();
        }
        try {
            zzcl.zza(zzck.zzcw(), zzck.zzer(), str, Collections.emptyList());
            zzcy();
            return true;
        } catch (RemoteException unused) {
            zzq("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    public final boolean zzcx() {
        zzk.zzaf();
        zzcl();
        zzcl zzcl = this.zzwx;
        if (zzcl == null) {
            return false;
        }
        try {
            zzcl.zzbr();
            zzcy();
            return true;
        } catch (RemoteException unused) {
            zzq("Failed to clear hits from AnalyticsService");
            return false;
        }
    }

    private final void zzcy() {
        this.zzwz.start();
        this.zzwy.zzh(((Long) zzcf.zzaaf.get()).longValue());
    }

    public final boolean connect() {
        zzk.zzaf();
        zzcl();
        if (this.zzwx != null) {
            return true;
        }
        zzcl zzda = this.zzww.zzda();
        if (zzda == null) {
            return false;
        }
        this.zzwx = zzda;
        zzcy();
        return true;
    }

    /* access modifiers changed from: private */
    public final void zza(zzcl zzcl) {
        zzk.zzaf();
        this.zzwx = zzcl;
        zzcy();
        zzcc().onServiceConnected();
    }

    public final void disconnect() {
        zzk.zzaf();
        zzcl();
        try {
            ConnectionTracker.getInstance().unbindService(getContext(), this.zzww);
        } catch (IllegalStateException unused) {
        } catch (IllegalArgumentException unused2) {
        }
        if (this.zzwx != null) {
            this.zzwx = null;
            zzcc().zzbu();
        }
    }

    /* access modifiers changed from: private */
    public final void onServiceDisconnected(ComponentName componentName) {
        zzk.zzaf();
        if (this.zzwx != null) {
            this.zzwx = null;
            zza("Disconnected from device AnalyticsService", componentName);
            zzcc().zzbu();
        }
    }

    /* access modifiers changed from: private */
    public final void zzcz() {
        zzk.zzaf();
        if (isConnected()) {
            zzq("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }
}
