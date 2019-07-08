package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public final class zzyy {
    /* access modifiers changed from: private */
    public boolean zzbli = false;
    /* access modifiers changed from: private */
    public int zzcjs = -1;
    /* access modifiers changed from: private */
    public String zzcjt;
    /* access modifiers changed from: private */
    public int zzcjv = -1;
    /* access modifiers changed from: private */
    public int zzcjy = -1;
    /* access modifiers changed from: private */
    public String zzcjz;
    /* access modifiers changed from: private */
    public String zzckb;
    /* access modifiers changed from: private */
    public final Bundle zzckd = new Bundle();
    /* access modifiers changed from: private */
    public String zzckf;
    /* access modifiers changed from: private */
    public boolean zzckh;
    /* access modifiers changed from: private */
    public final Bundle zzclz = new Bundle();
    /* access modifiers changed from: private */
    public final HashSet<String> zzcmf = new HashSet<>();
    /* access modifiers changed from: private */
    public final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzcmg = new HashMap<>();
    /* access modifiers changed from: private */
    public final HashSet<String> zzcmh = new HashSet<>();
    /* access modifiers changed from: private */
    public final HashSet<String> zzcmi = new HashSet<>();
    /* access modifiers changed from: private */
    public Date zzih;
    /* access modifiers changed from: private */
    public Location zzil;

    public final void zzbd(String str) {
        this.zzcmf.add(str);
    }

    @Deprecated
    public final void zza(NetworkExtras networkExtras) {
        if (networkExtras instanceof AdMobExtras) {
            zza(AdMobAdapter.class, ((AdMobExtras) networkExtras).getExtras());
        } else {
            this.zzcmg.put(networkExtras.getClass(), networkExtras);
        }
    }

    public final void zza(Class<? extends MediationAdapter> cls, Bundle bundle) {
        this.zzclz.putBundle(cls.getName(), bundle);
    }

    public final void zzb(Class<? extends CustomEvent> cls, Bundle bundle) {
        if (this.zzclz.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            this.zzclz.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
        }
        this.zzclz.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(cls.getName(), bundle);
    }

    public final void zzbe(String str) {
        this.zzcmh.add(str);
    }

    public final void zzbf(String str) {
        this.zzcmh.remove(str);
    }

    @Deprecated
    public final void zza(Date date) {
        this.zzih = date;
    }

    public final void zzbg(String str) {
        this.zzckb = str;
    }

    @Deprecated
    public final void zzch(int i) {
        this.zzcjv = i;
    }

    public final void zzb(Location location) {
        this.zzil = location;
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzbli = z;
    }

    public final void zzbh(String str) {
        this.zzcjz = str;
    }

    public final void zzbi(String str) {
        this.zzckf = str;
    }

    public final void zzu(boolean z) {
        this.zzcjy = z ? 1 : 0;
    }

    public final void zze(String str, String str2) {
        this.zzckd.putString(str, str2);
    }

    public final void zzbj(String str) {
        this.zzcmi.add(str);
    }

    @Deprecated
    public final void zzv(boolean z) {
        this.zzckh = z;
    }

    public final void zzci(int i) {
        if (i == -1 || i == 0 || i == 1) {
            this.zzcjs = i;
        }
    }

    public final void zzbk(String str) {
        if ("G".equals(str) || "PG".equals(str) || "T".equals(str) || "MA".equals(str)) {
            this.zzcjt = str;
        }
    }
}
