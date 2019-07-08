package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfv extends zzyc<zzfv> {
    public zzfw[] zzaxh;

    public zzfv() {
        this.zzaxh = zzfw.zznb();
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfv)) {
            return false;
        }
        zzfv zzfv = (zzfv) obj;
        if (!zzyg.equals((Object[]) this.zzaxh, (Object[]) zzfv.zzaxh)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfv.zzcev == null || zzfv.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfv.zzcev);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzyg.hashCode((Object[]) this.zzaxh)) * 31) + ((this.zzcev == null || this.zzcev.isEmpty()) ? 0 : this.zzcev.hashCode());
    }

    public final void zza(zzya zzya) throws IOException {
        zzfw[] zzfwArr = this.zzaxh;
        if (zzfwArr != null && zzfwArr.length > 0) {
            int i = 0;
            while (true) {
                zzfw[] zzfwArr2 = this.zzaxh;
                if (i >= zzfwArr2.length) {
                    break;
                }
                zzfw zzfw = zzfwArr2[i];
                if (zzfw != null) {
                    zzya.zza(1, (zzyi) zzfw);
                }
                i++;
            }
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        zzfw[] zzfwArr = this.zzaxh;
        if (zzfwArr != null && zzfwArr.length > 0) {
            int i = 0;
            while (true) {
                zzfw[] zzfwArr2 = this.zzaxh;
                if (i >= zzfwArr2.length) {
                    break;
                }
                zzfw zzfw = zzfwArr2[i];
                if (zzfw != null) {
                    zzf += zzya.zzb(1, (zzyi) zzfw);
                }
                i++;
            }
        }
        return zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                int zzb = zzyl.zzb(zzxz, 10);
                zzfw[] zzfwArr = this.zzaxh;
                int length = zzfwArr == null ? 0 : zzfwArr.length;
                zzfw[] zzfwArr2 = new zzfw[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzaxh, 0, zzfwArr2, 0, length);
                }
                while (length < zzfwArr2.length - 1) {
                    zzfwArr2[length] = new zzfw();
                    zzxz.zza((zzyi) zzfwArr2[length]);
                    zzxz.zzuj();
                    length++;
                }
                zzfwArr2[length] = new zzfw();
                zzxz.zza((zzyi) zzfwArr2[length]);
                this.zzaxh = zzfwArr2;
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
