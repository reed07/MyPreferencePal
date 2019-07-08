package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbqm implements zzbup {
    private final zzbqk zzflo;

    public static zzbqm zza(zzbqk zzbqk) {
        if (zzbqk.zzfmf != null) {
            return zzbqk.zzfmf;
        }
        return new zzbqm(zzbqk);
    }

    private zzbqm(zzbqk zzbqk) {
        this.zzflo = (zzbqk) zzbrf.zza(zzbqk, "output");
    }

    public final int zzaly() {
        return zze.zzfqm;
    }

    public final void zzaf(int i, int i2) throws IOException {
        this.zzflo.zzy(i, i2);
    }

    public final void zzr(int i, long j) throws IOException {
        this.zzflo.zzj(i, j);
    }

    public final void zzs(int i, long j) throws IOException {
        this.zzflo.zzl(i, j);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzflo.zza(i, f);
    }

    public final void zzb(int i, double d) throws IOException {
        this.zzflo.zzb(i, d);
    }

    public final void zzag(int i, int i2) throws IOException {
        this.zzflo.zzv(i, i2);
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzflo.zzj(i, j);
    }

    public final void zzv(int i, int i2) throws IOException {
        this.zzflo.zzv(i, i2);
    }

    public final void zzl(int i, long j) throws IOException {
        this.zzflo.zzl(i, j);
    }

    public final void zzy(int i, int i2) throws IOException {
        this.zzflo.zzy(i, i2);
    }

    public final void zzj(int i, boolean z) throws IOException {
        this.zzflo.zzj(i, z);
    }

    public final void zzf(int i, String str) throws IOException {
        this.zzflo.zzf(i, str);
    }

    public final void zza(int i, zzbpu zzbpu) throws IOException {
        this.zzflo.zza(i, zzbpu);
    }

    public final void zzw(int i, int i2) throws IOException {
        this.zzflo.zzw(i, i2);
    }

    public final void zzx(int i, int i2) throws IOException {
        this.zzflo.zzx(i, i2);
    }

    public final void zzk(int i, long j) throws IOException {
        this.zzflo.zzk(i, j);
    }

    public final void zza(int i, Object obj, zzbtc zzbtc) throws IOException {
        this.zzflo.zza(i, (zzbsl) obj, zzbtc);
    }

    public final void zzb(int i, Object obj, zzbtc zzbtc) throws IOException {
        zzbqk zzbqk = this.zzflo;
        zzbsl zzbsl = (zzbsl) obj;
        zzbqk.zzu(i, 3);
        zzbtc.zza(zzbsl, zzbqk.zzfmf);
        zzbqk.zzu(i, 4);
    }

    public final void zzfm(int i) throws IOException {
        this.zzflo.zzu(i, 3);
    }

    public final void zzfn(int i) throws IOException {
        this.zzflo.zzu(i, 4);
    }

    public final void zzb(int i, Object obj) throws IOException {
        if (obj instanceof zzbpu) {
            this.zzflo.zzb(i, (zzbpu) obj);
        } else {
            this.zzflo.zzb(i, (zzbsl) obj);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzfe(((Integer) list.get(i4)).intValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzez(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzv(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzfh(((Integer) list.get(i4)).intValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzfc(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzy(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzbb(((Long) list.get(i4)).longValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzay(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzj(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzbc(((Long) list.get(i4)).longValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzay(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzj(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzbe(((Long) list.get(i4)).longValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzba(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzl(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzg(((Float) list.get(i4)).floatValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzf(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zza(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzc(((Double) list.get(i4)).doubleValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzb(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzb(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzfj(((Integer) list.get(i4)).intValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzez(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzv(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzbe(((Boolean) list.get(i4)).booleanValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzbd(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzj(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzbru) {
            zzbru zzbru = (zzbru) list;
            while (i2 < list.size()) {
                Object zzfp = zzbru.zzfp(i2);
                if (zzfp instanceof String) {
                    this.zzflo.zzf(i, (String) zzfp);
                } else {
                    this.zzflo.zza(i, (zzbpu) zzfp);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzf(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zzb(int i, List<zzbpu> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzflo.zza(i, (zzbpu) list.get(i2));
        }
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzff(((Integer) list.get(i4)).intValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzfa(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzw(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzfi(((Integer) list.get(i4)).intValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzfc(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzy(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzbf(((Long) list.get(i4)).longValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzba(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzl(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzfg(((Integer) list.get(i4)).intValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzfb(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzx(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzflo.zzu(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbqk.zzbd(((Long) list.get(i4)).longValue());
            }
            this.zzflo.zzfa(i3);
            while (i2 < list.size()) {
                this.zzflo.zzaz(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzflo.zzk(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzbtc zzbtc) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzbtc);
        }
    }

    public final void zzb(int i, List<?> list, zzbtc zzbtc) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzbtc);
        }
    }

    public final <K, V> void zza(int i, zzbse<K, V> zzbse, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.zzflo.zzu(i, 2);
            this.zzflo.zzfa(zzbsd.zza(zzbse, entry.getKey(), entry.getValue()));
            zzbsd.zza(this.zzflo, zzbse, entry.getKey(), entry.getValue());
        }
    }
}
