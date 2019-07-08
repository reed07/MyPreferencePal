package com.google.android.gms.fitness.data;

import com.google.android.gms.internal.fitness.zzg;
import com.google.android.gms.internal.fitness.zzh;
import java.util.concurrent.TimeUnit;

final class zzf implements zzg<DataPoint, DataType> {
    public static final zzf zzam = new zzf();

    private zzf() {
    }

    public final zzh<DataType> zzb() {
        return zzg.zzan;
    }

    public final /* synthetic */ double zza(Object obj, int i) {
        return (double) ((DataPoint) obj).zzb(i).asFloat();
    }

    public final /* synthetic */ int zzb(Object obj, int i) {
        return ((DataPoint) obj).zzb(i).asInt();
    }

    public final /* synthetic */ boolean zzc(Object obj, int i) {
        return ((DataPoint) obj).zzb(i).isSet();
    }

    public final /* synthetic */ long zza(Object obj, TimeUnit timeUnit) {
        DataPoint dataPoint = (DataPoint) obj;
        return dataPoint.getEndTime(timeUnit) - dataPoint.getStartTime(timeUnit);
    }

    public final /* synthetic */ Object zza(Object obj) {
        return ((DataPoint) obj).getDataType();
    }

    public final /* synthetic */ String zzb(Object obj) {
        return ((DataPoint) obj).getDataType().getName();
    }
}
