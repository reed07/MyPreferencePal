package com.google.android.gms.fitness.data;

import com.google.android.gms.internal.fitness.zzh;

final class zzg implements zzh<DataType> {
    public static final zzg zzan = new zzg();

    private zzg() {
    }

    public final boolean zzb(String str) {
        return zzm.zzc(str) != null;
    }

    private static Field zza(DataType dataType, int i) {
        return (Field) dataType.getFields().get(i);
    }

    public final /* synthetic */ int zzd(Object obj, int i) {
        return zza((DataType) obj, i).getFormat();
    }

    public final /* synthetic */ boolean zze(Object obj, int i) {
        return Boolean.TRUE.equals(zza((DataType) obj, i).isOptional());
    }

    public final /* synthetic */ String zzf(Object obj, int i) {
        return zza((DataType) obj, i).getName();
    }

    public final /* synthetic */ int zzc(Object obj) {
        return ((DataType) obj).getFields().size();
    }

    public final /* synthetic */ String zzd(Object obj) {
        return ((DataType) obj).getName();
    }
}
