package com.google.android.gms.internal.fitness;

import android.support.annotation.Nullable;
import java.util.concurrent.TimeUnit;

public final class zzj {
    @Nullable
    public static <DP, DT> String zza(DP dp, zzg<DP, DT> zzg) {
        double d;
        zzh zzb = zzg.zzb();
        if (!zzb.zzb(zzg.zzb(dp))) {
            return null;
        }
        Object zza = zzg.zza(dp);
        for (int i = 0; i < zzb.zzc(zza); i++) {
            String zzf = zzb.zzf(zza, i);
            if (zzg.zzc(dp, i)) {
                double zzd = (double) zzb.zzd(zza, i);
                if (zzd == 1.0d) {
                    d = (double) zzg.zzb(dp, i);
                } else if (zzd == 2.0d) {
                    d = zzg.zza(dp, i);
                } else {
                    continue;
                }
                zzm zzk = zzk.zzs().zzk(zzf);
                if (zzk != null && !zzk.zza(d)) {
                    return "Field out of range";
                }
                zzm zza2 = zzk.zzs().zza(zzb.zzd(zza), zzf);
                if (zza2 != null) {
                    long zza3 = zzg.zza(dp, TimeUnit.NANOSECONDS);
                    if (zza3 == 0) {
                        if (d == 0.0d) {
                            return null;
                        }
                        return "DataPoint out of range";
                    } else if (!zza2.zza(d / ((double) zza3))) {
                        return "DataPoint out of range";
                    }
                } else {
                    continue;
                }
            } else if (!zzb.zze(zza, i) && !zzk.zzep.contains(zzf)) {
                return String.valueOf(zzf).concat(" not set");
            }
        }
        return null;
    }
}
