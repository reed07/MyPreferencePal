package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.atomic.AtomicReference;

public final class zzaq extends zzcs {
    private static final AtomicReference<String[]> zzalr = new AtomicReference<>();
    private static final AtomicReference<String[]> zzals = new AtomicReference<>();
    private static final AtomicReference<String[]> zzalt = new AtomicReference<>();

    zzaq(zzbw zzbw) {
        super(zzbw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    private final boolean zzjf() {
        zzgw();
        return this.zzada.zzkn() && this.zzada.zzgt().isLoggable(3);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzbt(String str) {
        if (str == null) {
            return null;
        }
        if (!zzjf()) {
            return str;
        }
        return zza(str, zzcu.zzaqu, zzcu.zzaqt, zzalr);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzbu(String str) {
        if (str == null) {
            return null;
        }
        if (!zzjf()) {
            return str;
        }
        return zza(str, zzcv.zzaqw, zzcv.zzaqv, zzals);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzbv(String str) {
        if (str == null) {
            return null;
        }
        if (!zzjf()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, zzcw.zzaqy, zzcw.zzaqx, zzalt);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("experiment_id");
        sb.append("(");
        sb.append(str);
        sb.append(")");
        return sb.toString();
    }

    @Nullable
    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (zzfx.zzv(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = (String[]) atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(strArr2[i]);
                        sb.append("(");
                        sb.append(strArr[i]);
                        sb.append(")");
                        strArr3[i] = sb.toString();
                    }
                    str2 = strArr3[i];
                }
                return str2;
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzb(zzag zzag) {
        if (zzag == null) {
            return null;
        }
        if (!zzjf()) {
            return zzag.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("origin=");
        sb.append(zzag.origin);
        sb.append(",name=");
        sb.append(zzbt(zzag.name));
        sb.append(",params=");
        sb.append(zzb(zzag.zzahu));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zza(zzab zzab) {
        if (zzab == null) {
            return null;
        }
        if (!zzjf()) {
            return zzab.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Event{appId='");
        sb.append(zzab.zztt);
        sb.append("', name='");
        sb.append(zzbt(zzab.name));
        sb.append("', params=");
        sb.append(zzb(zzab.zzahu));
        sb.append("}");
        return sb.toString();
    }

    @Nullable
    private final String zzb(zzad zzad) {
        if (zzad == null) {
            return null;
        }
        if (!zzjf()) {
            return zzad.toString();
        }
        return zzd(zzad.zziy());
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzd(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zzjf()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            if (sb.length() != 0) {
                sb.append(", ");
            } else {
                sb.append("Bundle[{");
            }
            sb.append(zzbu(str));
            sb.append("=");
            sb.append(bundle.get(str));
        }
        sb.append("}]");
        return sb.toString();
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
