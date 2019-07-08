package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzaxr {
    @GuardedBy("this")
    private BigInteger zzejv = BigInteger.ONE;
    @GuardedBy("this")
    private String zzejw = "0";

    public final synchronized String zzyv() {
        String bigInteger;
        bigInteger = this.zzejv.toString();
        this.zzejv = this.zzejv.add(BigInteger.ONE);
        this.zzejw = bigInteger;
        return bigInteger;
    }

    public final synchronized String zzyw() {
        return this.zzejw;
    }
}
