package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.PriorityQueue;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zztk {
    public static void zza(String[] strArr, int i, int i2, PriorityQueue<zztl> priorityQueue) {
        String[] strArr2 = strArr;
        int i3 = i2;
        if (strArr2.length < i3) {
            zza(i, zzb(strArr2, 0, strArr2.length), zza(strArr2, 0, strArr2.length), strArr2.length, priorityQueue);
            return;
        }
        long zzb = zzb(strArr2, 0, i3);
        zza(i, zzb, zza(strArr2, 0, i3), i2, priorityQueue);
        long zzb2 = zzb(16785407, i3 - 1);
        for (int i4 = 1; i4 < (strArr2.length - i3) + 1; i4++) {
            long j = zzb + 1073807359;
            zzb = (((((j - ((((((long) zzth.zzba(strArr2[i4 - 1])) + 2147483647L) % 1073807359) * zzb2) % 1073807359)) % 1073807359) * 16785407) % 1073807359) + ((((long) zzth.zzba(strArr2[(i4 + i3) - 1])) + 2147483647L) % 1073807359)) % 1073807359;
            zza(i, zzb, zza(strArr2, i4, i3), strArr2.length, priorityQueue);
        }
    }

    @VisibleForTesting
    private static void zza(int i, long j, String str, int i2, PriorityQueue<zztl> priorityQueue) {
        zztl zztl = new zztl(j, str, i2);
        if ((priorityQueue.size() != i || (((zztl) priorityQueue.peek()).zzbzg <= zztl.zzbzg && ((zztl) priorityQueue.peek()).value <= zztl.value)) && !priorityQueue.contains(zztl)) {
            priorityQueue.add(zztl);
            if (priorityQueue.size() > i) {
                priorityQueue.poll();
            }
        }
    }

    @VisibleForTesting
    private static String zza(String[] strArr, int i, int i2) {
        int i3 = i2 + i;
        if (strArr.length < i3) {
            zzaxz.e("Unable to construct shingle");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i4 = i3 - 1;
            if (i < i4) {
                sb.append(strArr[i]);
                sb.append(' ');
                i++;
            } else {
                sb.append(strArr[i4]);
                return sb.toString();
            }
        }
    }

    private static long zzb(String[] strArr, int i, int i2) {
        long zzba = (((long) zzth.zzba(strArr[0])) + 2147483647L) % 1073807359;
        for (int i3 = 1; i3 < i2; i3++) {
            zzba = (((zzba * 16785407) % 1073807359) + ((((long) zzth.zzba(strArr[i3])) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return zzba;
    }

    @VisibleForTesting
    private static long zzb(long j, int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return j;
        }
        if (i % 2 == 0) {
            return zzb((j * j) % 1073807359, i / 2) % 1073807359;
        }
        return (j * (zzb((j * j) % 1073807359, i / 2) % 1073807359)) % 1073807359;
    }
}
