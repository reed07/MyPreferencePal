package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

public final class zzdy extends zzf {
    @VisibleForTesting
    protected zzdx zzart;
    private volatile zzdx zzaru;
    private zzdx zzarv;
    private final Map<Activity, zzdx> zzarw = new ArrayMap();
    private zzdx zzarx;
    private String zzary;

    public zzdy(zzbw zzbw) {
        super(zzbw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    @WorkerThread
    public final zzdx zzle() {
        zzcl();
        zzaf();
        return this.zzart;
    }

    public final void setCurrentScreen(@NonNull Activity activity, @Nullable @Size String str, @Nullable @Size String str2) {
        if (this.zzaru == null) {
            zzgt().zzjj().zzby("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzarw.get(activity) == null) {
            zzgt().zzjj().zzby("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zzcq(activity.getClass().getCanonicalName());
            }
            boolean equals = this.zzaru.zzarq.equals(str2);
            boolean zzv = zzfx.zzv(this.zzaru.zzuw, str);
            if (equals && zzv) {
                zzgt().zzjl().zzby("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                zzgt().zzjj().zzg("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                zzgt().zzjo().zze("Setting current screen to name, class", str == null ? "null" : str, str2);
                zzdx zzdx = new zzdx(str, str2, zzgr().zzmj());
                this.zzarw.put(activity, zzdx);
                zza(activity, zzdx, true);
            } else {
                zzgt().zzjj().zzg("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    public final zzdx zzlf() {
        zzgg();
        return this.zzaru;
    }

    @MainThread
    private final void zza(Activity activity, zzdx zzdx, boolean z) {
        zzdx zzdx2 = this.zzaru == null ? this.zzarv : this.zzaru;
        if (zzdx.zzarq == null) {
            zzdx = new zzdx(zzdx.zzuw, zzcq(activity.getClass().getCanonicalName()), zzdx.zzarr);
        }
        this.zzarv = this.zzaru;
        this.zzaru = zzdx;
        zzgs().zzc((Runnable) new zzdz(this, z, zzdx2, zzdx));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(@NonNull zzdx zzdx, boolean z) {
        zzgi().zzm(zzbx().elapsedRealtime());
        if (zzgo().zza(zzdx.zzars, z)) {
            zzdx.zzars = false;
        }
    }

    public static void zza(zzdx zzdx, Bundle bundle, boolean z) {
        if (bundle == null || zzdx == null || (bundle.containsKey("_sc") && !z)) {
            if (bundle != null && zzdx == null && z) {
                bundle.remove("_sn");
                bundle.remove("_sc");
                bundle.remove("_si");
            }
            return;
        }
        if (zzdx.zzuw != null) {
            bundle.putString("_sn", zzdx.zzuw);
        } else {
            bundle.remove("_sn");
        }
        bundle.putString("_sc", zzdx.zzarq);
        bundle.putLong("_si", zzdx.zzarr);
    }

    @WorkerThread
    public final void zza(String str, zzdx zzdx) {
        zzaf();
        synchronized (this) {
            if (this.zzary == null || this.zzary.equals(str) || zzdx != null) {
                this.zzary = str;
                this.zzarx = zzdx;
            }
        }
    }

    @VisibleForTesting
    private static String zzcq(String str) {
        String[] split = str.split("\\.");
        String str2 = split.length > 0 ? split[split.length - 1] : "";
        return str2.length() > 100 ? str2.substring(0, 100) : str2;
    }

    @MainThread
    private final zzdx zze(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zzdx zzdx = (zzdx) this.zzarw.get(activity);
        if (zzdx != null) {
            return zzdx;
        }
        zzdx zzdx2 = new zzdx(null, zzcq(activity.getClass().getCanonicalName()), zzgr().zzmj());
        this.zzarw.put(activity, zzdx2);
        return zzdx2;
    }

    @MainThread
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("com.google.app_measurement.screen_service");
            if (bundle2 != null) {
                this.zzarw.put(activity, new zzdx(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
            }
        }
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        zza(activity, zze(activity), false);
        zza zzgi = zzgi();
        zzgi.zzgs().zzc((Runnable) new zzd(zzgi, zzgi.zzbx().elapsedRealtime()));
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        zzdx zze = zze(activity);
        this.zzarv = this.zzaru;
        this.zzaru = null;
        zzgs().zzc((Runnable) new zzea(this, zze));
    }

    @MainThread
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (bundle != null) {
            zzdx zzdx = (zzdx) this.zzarw.get(activity);
            if (zzdx != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong("id", zzdx.zzarr);
                bundle2.putString("name", zzdx.zzuw);
                bundle2.putString("referrer_name", zzdx.zzarq);
                bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
            }
        }
    }

    @MainThread
    public final void onActivityDestroyed(Activity activity) {
        this.zzarw.remove(activity);
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

    public final /* bridge */ /* synthetic */ zza zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzda zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzam zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzeb zzgl() {
        return super.zzgl();
    }

    public final /* bridge */ /* synthetic */ zzdy zzgm() {
        return super.zzgm();
    }

    public final /* bridge */ /* synthetic */ zzao zzgn() {
        return super.zzgn();
    }

    public final /* bridge */ /* synthetic */ zzfd zzgo() {
        return super.zzgo();
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
