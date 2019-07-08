package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj.zzd;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzcv implements zzgr {
    private final zzct zzge;

    public static zzcv zza(zzct zzct) {
        if (zzct.zzgo != null) {
            return zzct.zzgo;
        }
        return new zzcv(zzct);
    }

    private zzcv(zzct zzct) {
        this.zzge = (zzct) zzdl.zza(zzct, "output");
    }

    public final int zzay() {
        return zzd.zzkl;
    }

    public final void zzm(int i, int i2) throws IOException {
        this.zzge.zzf(i, i2);
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzge.zza(i, j);
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzge.zzc(i, j);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzge.zza(i, f);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzge.zza(i, d);
    }

    public final void zzn(int i, int i2) throws IOException {
        this.zzge.zzc(i, i2);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzge.zza(i, j);
    }

    public final void zzc(int i, int i2) throws IOException {
        this.zzge.zzc(i, i2);
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzge.zzc(i, j);
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zzge.zzf(i, i2);
    }

    public final void zza(int i, boolean z) throws IOException {
        this.zzge.zza(i, z);
    }

    public final void zza(int i, String str) throws IOException {
        this.zzge.zza(i, str);
    }

    public final void zza(int i, zzce zzce) throws IOException {
        this.zzge.zza(i, zzce);
    }

    public final void zzd(int i, int i2) throws IOException {
        this.zzge.zzd(i, i2);
    }

    public final void zze(int i, int i2) throws IOException {
        this.zzge.zze(i, i2);
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzge.zzb(i, j);
    }

    public final void zza(int i, Object obj, zzff zzff) throws IOException {
        this.zzge.zza(i, (zzeq) obj, zzff);
    }

    public final void zzb(int i, Object obj, zzff zzff) throws IOException {
        zzct zzct = this.zzge;
        zzeq zzeq = (zzeq) obj;
        zzct.zzb(i, 3);
        zzff.zza(zzeq, zzct.zzgo);
        zzct.zzb(i, 4);
    }

    public final void zzab(int i) throws IOException {
        this.zzge.zzb(i, 3);
    }

    public final void zzac(int i) throws IOException {
        this.zzge.zzb(i, 4);
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzce) {
            this.zzge.zzb(i, (zzce) obj);
        } else {
            this.zzge.zza(i, (zzeq) obj);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzt(((Integer) list.get(i4)).intValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzo(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zzc(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzw(((Integer) list.get(i4)).intValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzr(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zze(((Long) list.get(i4)).longValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzb(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzf(((Long) list.get(i4)).longValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzb(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzh(((Long) list.get(i4)).longValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzd(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzb(((Float) list.get(i4)).floatValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zza(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zza(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzb(((Double) list.get(i4)).doubleValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zza(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zza(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzy(((Integer) list.get(i4)).intValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzo(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zzc(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzh(((Boolean) list.get(i4)).booleanValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzg(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zza(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzea) {
            zzea zzea = (zzea) list;
            while (i2 < list.size()) {
                Object zzad = zzea.zzad(i2);
                if (zzad instanceof String) {
                    this.zzge.zza(i, (String) zzad);
                } else {
                    this.zzge.zza(i, (zzce) zzad);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zza(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zzb(int i, List<zzce> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzge.zza(i, (zzce) list.get(i2));
        }
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzu(((Integer) list.get(i4)).intValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzp(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zzd(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzx(((Integer) list.get(i4)).intValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzr(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzi(((Long) list.get(i4)).longValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzd(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzv(((Integer) list.get(i4)).intValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzq(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zze(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzge.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzct.zzg(((Long) list.get(i4)).longValue());
            }
            this.zzge.zzp(i3);
            while (i2 < list.size()) {
                this.zzge.zzc(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzge.zzb(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzff zzff) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzff);
        }
    }

    public final void zzb(int i, List<?> list, zzff zzff) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzff);
        }
    }

    public final <K, V> void zza(int i, zzej<K, V> zzej, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.zzge.zzb(i, 2);
            this.zzge.zzp(zzdc.zza(zzej.zzly, 1, entry.getKey()) + zzdc.zza(zzej.zzlz, 2, entry.getValue()));
            zzct zzct = this.zzge;
            Object key = entry.getKey();
            Object value = entry.getValue();
            zzdc.zza(zzct, zzej.zzly, 1, key);
            zzdc.zza(zzct, zzej.zzlz, 2, value);
        }
    }
}
