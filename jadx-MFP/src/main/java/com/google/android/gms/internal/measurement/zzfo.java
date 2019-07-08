package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfo extends zzyc<zzfo> {
    private static volatile zzfo[] zzawi;
    public String name;
    public Boolean zzawj;
    public Boolean zzawk;
    public Integer zzawl;

    public static zzfo[] zzmv() {
        if (zzawi == null) {
            synchronized (zzyg.zzcfe) {
                if (zzawi == null) {
                    zzawi = new zzfo[0];
                }
            }
        }
        return zzawi;
    }

    public zzfo() {
        this.name = null;
        this.zzawj = null;
        this.zzawk = null;
        this.zzawl = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfo)) {
            return false;
        }
        zzfo zzfo = (zzfo) obj;
        String str = this.name;
        if (str == null) {
            if (zzfo.name != null) {
                return false;
            }
        } else if (!str.equals(zzfo.name)) {
            return false;
        }
        Boolean bool = this.zzawj;
        if (bool == null) {
            if (zzfo.zzawj != null) {
                return false;
            }
        } else if (!bool.equals(zzfo.zzawj)) {
            return false;
        }
        Boolean bool2 = this.zzawk;
        if (bool2 == null) {
            if (zzfo.zzawk != null) {
                return false;
            }
        } else if (!bool2.equals(zzfo.zzawk)) {
            return false;
        }
        Integer num = this.zzawl;
        if (num == null) {
            if (zzfo.zzawl != null) {
                return false;
            }
        } else if (!num.equals(zzfo.zzawl)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfo.zzcev == null || zzfo.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfo.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.zzawj;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.zzawk;
        int hashCode4 = (hashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Integer num = this.zzawl;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode5 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        String str = this.name;
        if (str != null) {
            zzya.zzb(1, str);
        }
        Boolean bool = this.zzawj;
        if (bool != null) {
            zzya.zzb(2, bool.booleanValue());
        }
        Boolean bool2 = this.zzawk;
        if (bool2 != null) {
            zzya.zzb(3, bool2.booleanValue());
        }
        Integer num = this.zzawl;
        if (num != null) {
            zzya.zzd(4, num.intValue());
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        String str = this.name;
        if (str != null) {
            zzf += zzya.zzc(1, str);
        }
        Boolean bool = this.zzawj;
        if (bool != null) {
            bool.booleanValue();
            zzf += zzya.zzbd(2) + 1;
        }
        Boolean bool2 = this.zzawk;
        if (bool2 != null) {
            bool2.booleanValue();
            zzf += zzya.zzbd(3) + 1;
        }
        Integer num = this.zzawl;
        return num != null ? zzf + zzya.zzh(4, num.intValue()) : zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                this.name = zzxz.readString();
            } else if (zzuj == 16) {
                this.zzawj = Boolean.valueOf(zzxz.zzup());
            } else if (zzuj == 24) {
                this.zzawk = Boolean.valueOf(zzxz.zzup());
            } else if (zzuj == 32) {
                this.zzawl = Integer.valueOf(zzxz.zzvb());
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
