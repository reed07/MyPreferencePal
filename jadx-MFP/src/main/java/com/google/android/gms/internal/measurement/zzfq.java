package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfq extends zzyc<zzfq> {
    private static volatile zzfq[] zzawt;
    public String value;
    public String zzoj;

    public static zzfq[] zzmw() {
        if (zzawt == null) {
            synchronized (zzyg.zzcfe) {
                if (zzawt == null) {
                    zzawt = new zzfq[0];
                }
            }
        }
        return zzawt;
    }

    public zzfq() {
        this.zzoj = null;
        this.value = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfq)) {
            return false;
        }
        zzfq zzfq = (zzfq) obj;
        String str = this.zzoj;
        if (str == null) {
            if (zzfq.zzoj != null) {
                return false;
            }
        } else if (!str.equals(zzfq.zzoj)) {
            return false;
        }
        String str2 = this.value;
        if (str2 == null) {
            if (zzfq.value != null) {
                return false;
            }
        } else if (!str2.equals(zzfq.value)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfq.zzcev == null || zzfq.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfq.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzoj;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.value;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode3 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        String str = this.zzoj;
        if (str != null) {
            zzya.zzb(1, str);
        }
        String str2 = this.value;
        if (str2 != null) {
            zzya.zzb(2, str2);
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        String str = this.zzoj;
        if (str != null) {
            zzf += zzya.zzc(1, str);
        }
        String str2 = this.value;
        return str2 != null ? zzf + zzya.zzc(2, str2) : zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                this.zzoj = zzxz.readString();
            } else if (zzuj == 18) {
                this.value = zzxz.readString();
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
