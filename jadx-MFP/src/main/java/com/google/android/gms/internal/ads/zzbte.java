package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzbte {
    private static final Class<?> zzftf = zzaon();
    private static final zzbtu<?, ?> zzftg = zzbg(false);
    private static final zzbtu<?, ?> zzfth = zzbg(true);
    private static final zzbtu<?, ?> zzfti = new zzbtw();

    public static void zzg(Class<?> cls) {
        if (!zzbrd.class.isAssignableFrom(cls)) {
            Class<?> cls2 = zzftf;
            if (cls2 != null && !cls2.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
            }
        }
    }

    public static void zza(int i, List<Double> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzbup zzbup) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzbpu> list, zzbup zzbup) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzbup zzbup, zzbtc zzbtc) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zza(i, list, zzbtc);
        }
    }

    public static void zzb(int i, List<?> list, zzbup zzbup, zzbtc zzbtc) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzb(i, list, zzbtc);
        }
    }

    static int zzaf(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            i = 0;
            while (i2 < size) {
                i += zzbqk.zzbb(zzbrz.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbqk.zzbb(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzaf(list) + (list.size() * zzbqk.zzfd(i));
    }

    static int zzag(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            i = 0;
            while (i2 < size) {
                i += zzbqk.zzbc(zzbrz.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbqk.zzbc(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzag(list) + (size * zzbqk.zzfd(i));
    }

    static int zzah(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            i = 0;
            while (i2 < size) {
                i += zzbqk.zzbd(zzbrz.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbqk.zzbd(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzah(list) + (size * zzbqk.zzfd(i));
    }

    static int zzai(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            i = 0;
            while (i2 < size) {
                i += zzbqk.zzfj(zzbre.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbqk.zzfj(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzai(list) + (size * zzbqk.zzfd(i));
    }

    static int zzaj(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            i = 0;
            while (i2 < size) {
                i += zzbqk.zzfe(zzbre.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbqk.zzfe(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaj(list) + (size * zzbqk.zzfd(i));
    }

    static int zzak(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            i = 0;
            while (i2 < size) {
                i += zzbqk.zzff(zzbre.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbqk.zzff(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzak(list) + (size * zzbqk.zzfd(i));
    }

    static int zzal(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            i = 0;
            while (i2 < size) {
                i += zzbqk.zzfg(zzbre.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbqk.zzfg(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzal(list) + (size * zzbqk.zzfd(i));
    }

    static int zzam(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbqk.zzac(i, 0);
    }

    static int zzan(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbqk.zzp(i, 0);
    }

    static int zzao(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbqk.zzk(i, true);
    }

    static int zzc(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzfd = zzbqk.zzfd(i) * size;
        if (list instanceof zzbru) {
            zzbru zzbru = (zzbru) list;
            while (i4 < size) {
                Object zzfp = zzbru.zzfp(i4);
                if (zzfp instanceof zzbpu) {
                    i3 = zzbqk.zzao((zzbpu) zzfp);
                } else {
                    i3 = zzbqk.zzfy((String) zzfp);
                }
                zzfd += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzbpu) {
                    i2 = zzbqk.zzao((zzbpu) obj);
                } else {
                    i2 = zzbqk.zzfy((String) obj);
                }
                zzfd += i2;
                i4++;
            }
        }
        return zzfd;
    }

    static int zzc(int i, Object obj, zzbtc zzbtc) {
        if (obj instanceof zzbrs) {
            return zzbqk.zza(i, (zzbrs) obj);
        }
        return zzbqk.zzb(i, (zzbsl) obj, zzbtc);
    }

    static int zzc(int i, List<?> list, zzbtc zzbtc) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzfd = zzbqk.zzfd(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzbrs) {
                i2 = zzbqk.zza((zzbrs) obj);
            } else {
                i2 = zzbqk.zzb((zzbsl) obj, zzbtc);
            }
            zzfd += i2;
        }
        return zzfd;
    }

    static int zzd(int i, List<zzbpu> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzfd = size * zzbqk.zzfd(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzfd += zzbqk.zzao((zzbpu) list.get(i2));
        }
        return zzfd;
    }

    static int zzd(int i, List<zzbsl> list, zzbtc zzbtc) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbqk.zzc(i, (zzbsl) list.get(i3), zzbtc);
        }
        return i2;
    }

    public static zzbtu<?, ?> zzaok() {
        return zzftg;
    }

    public static zzbtu<?, ?> zzaol() {
        return zzfth;
    }

    public static zzbtu<?, ?> zzaom() {
        return zzfti;
    }

    private static zzbtu<?, ?> zzbg(boolean z) {
        try {
            Class zzaoo = zzaoo();
            if (zzaoo == null) {
                return null;
            }
            return (zzbtu) zzaoo.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzaon() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzaoo() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zza(zzbsg zzbsg, T t, T t2, long j) {
        zzbua.zza((Object) t, j, zzbsg.zzc(zzbua.zzp(t, j), zzbua.zzp(t2, j)));
    }

    static <T, FT extends zzbqw<FT>> void zza(zzbqr<FT> zzbqr, T t, T t2) {
        zzbqu zzq = zzbqr.zzq(t2);
        if (!zzq.isEmpty()) {
            zzbqr.zzr(t).zza(zzq);
        }
    }

    static <T, UT, UB> void zza(zzbtu<UT, UB> zzbtu, T t, T t2) {
        zzbtu.zzf(t, zzbtu.zzh(zzbtu.zzag(t), zzbtu.zzag(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzbri zzbri, UB ub, zzbtu<UT, UB> zzbtu) {
        UB ub2;
        if (zzbri == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzbri.zzcb(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub2 = zza(i, intValue, ub2, zzbtu);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            ub2 = ub;
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzbri.zzcb(intValue2)) {
                    UB zza = zza(i, intValue2, ub2, zzbtu);
                    it.remove();
                    ub2 = zza;
                }
            }
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzbtu<UT, UB> zzbtu) {
        if (ub == null) {
            ub = zzbtu.zzaoy();
        }
        zzbtu.zza(ub, i, (long) i2);
        return ub;
    }
}
