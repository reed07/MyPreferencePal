package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

@zzark
final class zzaht {
    private static final zzahq zzdhy = zzahq.zzto();
    private static final float zzdhz = ((Float) zzwu.zzpz().zzd(zzaan.zzcsl)).floatValue();
    private static final long zzdia = ((Long) zzwu.zzpz().zzd(zzaan.zzcsj)).longValue();
    private static final float zzdib = ((Float) zzwu.zzpz().zzd(zzaan.zzcsm)).floatValue();
    private static final long zzdic = ((Long) zzwu.zzpz().zzd(zzaan.zzcsk)).longValue();

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean zztz() {
        /*
            com.google.android.gms.internal.ads.zzahq r0 = zzdhy
            int r0 = r0.zztv()
            com.google.android.gms.internal.ads.zzahq r1 = zzdhy
            int r1 = r1.zztw()
            com.google.android.gms.internal.ads.zzahq r2 = zzdhy
            int r2 = r2.zztu()
            com.google.android.gms.internal.ads.zzahq r3 = zzdhy
            int r3 = r3.zztt()
            int r2 = r2 + r3
            r3 = 1
            r4 = 2147483647(0x7fffffff, float:NaN)
            r5 = 0
            r7 = 0
            r8 = 16
            if (r0 >= r8) goto L_0x002f
            long r9 = zzdic
            int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r11 == 0) goto L_0x002f
            int r9 = zzc(r9, r0)
            goto L_0x003e
        L_0x002f:
            float r9 = zzdib
            int r10 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x003b
            float r10 = (float) r0
            float r9 = r9 * r10
            int r9 = (int) r9
            int r9 = r9 + r3
            goto L_0x003e
        L_0x003b:
            r9 = 2147483647(0x7fffffff, float:NaN)
        L_0x003e:
            if (r1 > r9) goto L_0x005a
            if (r0 >= r8) goto L_0x004d
            long r8 = zzdia
            int r1 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r1 == 0) goto L_0x004d
            int r4 = zzc(r8, r0)
            goto L_0x0057
        L_0x004d:
            float r1 = zzdhz
            int r5 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x0057
            float r0 = (float) r0
            float r1 = r1 * r0
            int r4 = (int) r1
        L_0x0057:
            if (r2 > r4) goto L_0x005a
            return r3
        L_0x005a:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaht.zztz():boolean");
    }

    @VisibleForTesting
    private static int zzc(long j, int i) {
        return (int) ((j >>> ((i % 16) * 4)) & 15);
    }
}
