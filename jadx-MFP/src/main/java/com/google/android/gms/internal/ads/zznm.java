package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class zznm {
    @Nullable
    public final String value;
    @NonNull
    public final String zzbdi;
    @Nullable
    private final String zzze;

    public zznm(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        this.zzbdi = str;
        this.value = str2;
        this.zzze = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zznm zznm = (zznm) obj;
        return zzqe.zza((Object) this.zzbdi, (Object) zznm.zzbdi) && zzqe.zza((Object) this.value, (Object) zznm.value) && zzqe.zza((Object) this.zzze, (Object) zznm.zzze);
    }

    public final int hashCode() {
        String str = this.zzbdi;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.value;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.zzze;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }
}
