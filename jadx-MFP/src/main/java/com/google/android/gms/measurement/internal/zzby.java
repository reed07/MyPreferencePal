package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public final class zzby extends zzak {
    /* access modifiers changed from: private */
    public final zzfn zzamx;
    private Boolean zzaql;
    @Nullable
    private String zzaqm;

    public zzby(zzfn zzfn) {
        this(zzfn, null);
    }

    private zzby(zzfn zzfn, @Nullable String str) {
        Preconditions.checkNotNull(zzfn);
        this.zzamx = zzfn;
        this.zzaqm = null;
    }

    @BinderThread
    public final void zzb(zzk zzk) {
        zzb(zzk, false);
        zze(new zzbz(this, zzk));
    }

    @BinderThread
    public final void zza(zzag zzag, zzk zzk) {
        Preconditions.checkNotNull(zzag);
        zzb(zzk, false);
        zze(new zzcj(this, zzag, zzk));
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final zzag zzb(zzag zzag, zzk zzk) {
        boolean z = false;
        if (!(!"_cmp".equals(zzag.name) || zzag.zzahu == null || zzag.zzahu.size() == 0)) {
            String string = zzag.zzahu.getString("_cis");
            if (!TextUtils.isEmpty(string) && (("referrer broadcast".equals(string) || "referrer API".equals(string)) && this.zzamx.zzgv().zzbe(zzk.packageName))) {
                z = true;
            }
        }
        if (!z) {
            return zzag;
        }
        this.zzamx.zzgt().zzjm().zzg("Event has been filtered ", zzag.toString());
        zzag zzag2 = new zzag("_cmpx", zzag.zzahu, zzag.origin, zzag.zzaig);
        return zzag2;
    }

    @BinderThread
    public final void zza(zzag zzag, String str, String str2) {
        Preconditions.checkNotNull(zzag);
        Preconditions.checkNotEmpty(str);
        zzc(str, true);
        zze(new zzck(this, zzag, str));
    }

    @BinderThread
    public final byte[] zza(zzag zzag, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzag);
        zzc(str, true);
        this.zzamx.zzgt().zzjn().zzg("Log and bundle. event", this.zzamx.zzgq().zzbt(zzag.name));
        long nanoTime = this.zzamx.zzbx().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzamx.zzgs().zzc((Callable<V>) new zzcl<V>(this, zzag, str)).get();
            if (bArr == null) {
                this.zzamx.zzgt().zzjg().zzg("Log and bundle returned null. appId", zzas.zzbw(str));
                bArr = new byte[0];
            }
            this.zzamx.zzgt().zzjn().zzd("Log and bundle processed. event, size, time_ms", this.zzamx.zzgq().zzbt(zzag.name), Integer.valueOf(bArr.length), Long.valueOf((this.zzamx.zzbx().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzamx.zzgt().zzjg().zzd("Failed to log and bundle. appId, event, error", zzas.zzbw(str), this.zzamx.zzgq().zzbt(zzag.name), e);
            return null;
        }
    }

    @BinderThread
    public final void zza(zzfu zzfu, zzk zzk) {
        Preconditions.checkNotNull(zzfu);
        zzb(zzk, false);
        if (zzfu.getValue() == null) {
            zze(new zzcm(this, zzfu, zzk));
        } else {
            zze(new zzcn(this, zzfu, zzk));
        }
    }

    @BinderThread
    public final List<zzfu> zza(zzk zzk, boolean z) {
        zzb(zzk, false);
        try {
            List<zzfw> list = (List) this.zzamx.zzgs().zzb((Callable<V>) new zzco<V>(this, zzk)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzfw zzfw : list) {
                if (z || !zzfx.zzcy(zzfw.name)) {
                    arrayList.add(new zzfu(zzfw));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzamx.zzgt().zzjg().zze("Failed to get user attributes. appId", zzas.zzbw(zzk.packageName), e);
            return null;
        }
    }

    @BinderThread
    public final void zza(zzk zzk) {
        zzb(zzk, false);
        zze(new zzcp(this, zzk));
    }

    @BinderThread
    private final void zzb(zzk zzk, boolean z) {
        Preconditions.checkNotNull(zzk);
        zzc(zzk.packageName, false);
        this.zzamx.zzgr().zzu(zzk.zzafi, zzk.zzafv);
    }

    @BinderThread
    private final void zzc(String str, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.zzaql == null) {
                        if (!"com.google.android.gms".equals(this.zzaqm) && !UidVerifier.isGooglePlayServicesUid(this.zzamx.getContext(), Binder.getCallingUid())) {
                            if (!GoogleSignatureVerifier.getInstance(this.zzamx.getContext()).isUidGoogleSigned(Binder.getCallingUid())) {
                                z2 = false;
                                this.zzaql = Boolean.valueOf(z2);
                            }
                        }
                        z2 = true;
                        this.zzaql = Boolean.valueOf(z2);
                    }
                    if (this.zzaql.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.zzamx.zzgt().zzjg().zzg("Measurement Service called with invalid calling package. appId", zzas.zzbw(str));
                    throw e;
                }
            }
            if (this.zzaqm == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zzamx.getContext(), Binder.getCallingUid(), str)) {
                this.zzaqm = str;
            }
            if (!str.equals(this.zzaqm)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
            return;
        }
        this.zzamx.zzgt().zzjg().zzby("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    @BinderThread
    public final void zza(long j, String str, String str2, String str3) {
        zzcq zzcq = new zzcq(this, str2, str3, str, j);
        zze(zzcq);
    }

    @BinderThread
    public final String zzc(zzk zzk) {
        zzb(zzk, false);
        return this.zzamx.zzh(zzk);
    }

    @BinderThread
    public final void zza(zzo zzo, zzk zzk) {
        Preconditions.checkNotNull(zzo);
        Preconditions.checkNotNull(zzo.zzags);
        zzb(zzk, false);
        zzo zzo2 = new zzo(zzo);
        zzo2.packageName = zzk.packageName;
        if (zzo.zzags.getValue() == null) {
            zze(new zzca(this, zzo2, zzk));
        } else {
            zze(new zzcb(this, zzo2, zzk));
        }
    }

    @BinderThread
    public final void zzb(zzo zzo) {
        Preconditions.checkNotNull(zzo);
        Preconditions.checkNotNull(zzo.zzags);
        zzc(zzo.packageName, true);
        zzo zzo2 = new zzo(zzo);
        if (zzo.zzags.getValue() == null) {
            zze(new zzcc(this, zzo2));
        } else {
            zze(new zzcd(this, zzo2));
        }
    }

    @BinderThread
    public final List<zzfu> zza(String str, String str2, boolean z, zzk zzk) {
        zzb(zzk, false);
        try {
            List<zzfw> list = (List) this.zzamx.zzgs().zzb((Callable<V>) new zzce<V>(this, zzk, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzfw zzfw : list) {
                if (z || !zzfx.zzcy(zzfw.name)) {
                    arrayList.add(new zzfu(zzfw));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzamx.zzgt().zzjg().zze("Failed to get user attributes. appId", zzas.zzbw(zzk.packageName), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzfu> zza(String str, String str2, String str3, boolean z) {
        zzc(str, true);
        try {
            List<zzfw> list = (List) this.zzamx.zzgs().zzb((Callable<V>) new zzcf<V>(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzfw zzfw : list) {
                if (z || !zzfx.zzcy(zzfw.name)) {
                    arrayList.add(new zzfu(zzfw));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzamx.zzgt().zzjg().zze("Failed to get user attributes. appId", zzas.zzbw(str), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzo> zza(String str, String str2, zzk zzk) {
        zzb(zzk, false);
        try {
            return (List) this.zzamx.zzgs().zzb((Callable<V>) new zzcg<V>(this, zzk, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzamx.zzgt().zzjg().zzg("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzo> zze(String str, String str2, String str3) {
        zzc(str, true);
        try {
            return (List) this.zzamx.zzgs().zzb((Callable<V>) new zzch<V>(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzamx.zzgt().zzjg().zzg("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final void zzd(zzk zzk) {
        zzc(zzk.packageName, false);
        zze(new zzci(this, zzk));
    }

    @VisibleForTesting
    private final void zze(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (!((Boolean) zzai.zzakn.get()).booleanValue() || !this.zzamx.zzgs().zzkf()) {
            this.zzamx.zzgs().zzc(runnable);
        } else {
            runnable.run();
        }
    }
}
