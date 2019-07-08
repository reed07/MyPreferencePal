package com.google.android.gms.internal.ads;

import android.os.Build.VERSION;
import android.os.ConditionVariable;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class zzco {
    /* access modifiers changed from: private */
    public static final ConditionVariable zzqp = new ConditionVariable();
    protected static volatile zzur zzqq = null;
    private static volatile Random zzqs = null;
    /* access modifiers changed from: private */
    public zzdl zzqo;
    protected volatile Boolean zzqr;

    public zzco(zzdl zzdl) {
        this.zzqo = zzdl;
        zzdl.zzac().execute(new zzcp(this));
    }

    public final void zza(int i, int i2, long j) throws IOException {
        try {
            zzqp.block();
            if (this.zzqr.booleanValue() && zzqq != null) {
                zzbh zzbh = new zzbh();
                zzbh.zzdh = this.zzqo.zzsp.getPackageName();
                zzbh.zzdi = Long.valueOf(j);
                zzuv zzg = zzqq.zzg(zzbuz.zzb(zzbh));
                zzg.zzby(i2);
                zzg.zzbz(i);
                zzg.zzbd();
            }
        } catch (Exception unused) {
        }
    }

    public static int zzy() {
        try {
            if (VERSION.SDK_INT >= 21) {
                return ThreadLocalRandom.current().nextInt();
            }
            return zzz().nextInt();
        } catch (RuntimeException unused) {
            return zzz().nextInt();
        }
    }

    private static Random zzz() {
        if (zzqs == null) {
            synchronized (zzco.class) {
                if (zzqs == null) {
                    zzqs = new Random();
                }
            }
        }
        return zzqs;
    }
}
