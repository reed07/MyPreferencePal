package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzyc;
import java.io.IOException;

public abstract class zzyc<M extends zzyc<M>> extends zzyi {
    protected zzye zzcev;

    /* access modifiers changed from: protected */
    public int zzf() {
        if (this.zzcev == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzcev.size(); i2++) {
            i += this.zzcev.zzcf(i2).zzf();
        }
        return i;
    }

    public void zza(zzya zzya) throws IOException {
        if (this.zzcev != null) {
            for (int i = 0; i < this.zzcev.size(); i++) {
                this.zzcev.zzcf(i).zza(zzya);
            }
        }
    }

    public final <T> T zza(zzyd<M, T> zzyd) {
        zzye zzye = this.zzcev;
        if (zzye == null) {
            return null;
        }
        zzyf zzce = zzye.zzce(zzyd.tag >>> 3);
        if (zzce == null) {
            return null;
        }
        return zzce.zzb(zzyd);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzxz zzxz, int i) throws IOException {
        int position = zzxz.getPosition();
        if (!zzxz.zzaq(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzyk zzyk = new zzyk(i, zzxz.zzs(position, zzxz.getPosition() - position));
        zzyf zzyf = null;
        zzye zzye = this.zzcev;
        if (zzye == null) {
            this.zzcev = new zzye();
        } else {
            zzyf = zzye.zzce(i2);
        }
        if (zzyf == null) {
            zzyf = new zzyf();
            this.zzcev.zza(i2, zzyf);
        }
        zzyf.zza(zzyk);
        return true;
    }

    public final /* synthetic */ zzyi zzzb() throws CloneNotSupportedException {
        return (zzyc) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzyc zzyc = (zzyc) super.clone();
        zzyg.zza(this, zzyc);
        return zzyc;
    }
}
