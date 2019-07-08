package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzjw implements zzhz {
    private static final int zzatm = zzqe.zzam("RCC\u0001");
    private int version;
    private final zzfs zzaad;
    private int zzajn;
    private int zzaqe = 0;
    private zzii zzasj;
    private final zzpx zzatn = new zzpx(9);
    private long zzato;
    private int zzatp;

    public zzjw(zzfs zzfs) {
        this.zzaad = zzfs;
    }

    public final void release() {
    }

    public final void zza(zzib zzib) {
        zzib.zza(new zzih(-9223372036854775807L));
        this.zzasj = zzib.zzb(0, 3);
        zzib.zzdy();
        this.zzasj.zzf(this.zzaad);
    }

    public final boolean zza(zzia zzia) throws IOException, InterruptedException {
        this.zzatn.reset();
        zzia.zza(this.zzatn.data, 0, 8);
        if (this.zzatn.readInt() == zzatm) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0091 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(com.google.android.gms.internal.ads.zzia r11, com.google.android.gms.internal.ads.zzif r12) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r10 = this;
        L_0x0000:
            int r12 = r10.zzaqe
            r0 = -1
            r1 = 1
            r2 = 0
            switch(r12) {
                case 0: goto L_0x00ad;
                case 1: goto L_0x0042;
                case 2: goto L_0x000e;
                default: goto L_0x0008;
            }
        L_0x0008:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            r11.<init>()
            throw r11
        L_0x000e:
            int r12 = r10.zzatp
            if (r12 <= 0) goto L_0x0031
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            r12.reset()
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            byte[] r12 = r12.data
            r0 = 3
            r11.readFully(r12, r2, r0)
            com.google.android.gms.internal.ads.zzii r12 = r10.zzasj
            com.google.android.gms.internal.ads.zzpx r3 = r10.zzatn
            r12.zza(r3, r0)
            int r12 = r10.zzajn
            int r12 = r12 + r0
            r10.zzajn = r12
            int r12 = r10.zzatp
            int r12 = r12 - r1
            r10.zzatp = r12
            goto L_0x000e
        L_0x0031:
            int r7 = r10.zzajn
            if (r7 <= 0) goto L_0x003f
            com.google.android.gms.internal.ads.zzii r3 = r10.zzasj
            long r4 = r10.zzato
            r6 = 1
            r8 = 0
            r9 = 0
            r3.zza(r4, r6, r7, r8, r9)
        L_0x003f:
            r10.zzaqe = r1
            return r2
        L_0x0042:
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            r12.reset()
            int r12 = r10.version
            if (r12 != 0) goto L_0x0068
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            byte[] r12 = r12.data
            r3 = 5
            boolean r12 = r11.zza(r12, r2, r3, r1)
            if (r12 != 0) goto L_0x0058
            r1 = 0
            goto L_0x008a
        L_0x0058:
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            long r3 = r12.zzhd()
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r5
            r5 = 45
            long r3 = r3 / r5
            r10.zzato = r3
            goto L_0x0080
        L_0x0068:
            if (r12 != r1) goto L_0x0094
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            byte[] r12 = r12.data
            r3 = 9
            boolean r12 = r11.zza(r12, r2, r3, r1)
            if (r12 != 0) goto L_0x0078
            r1 = 0
            goto L_0x008a
        L_0x0078:
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            long r3 = r12.readLong()
            r10.zzato = r3
        L_0x0080:
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            int r12 = r12.readUnsignedByte()
            r10.zzatp = r12
            r10.zzajn = r2
        L_0x008a:
            if (r1 == 0) goto L_0x0091
            r12 = 2
            r10.zzaqe = r12
            goto L_0x0000
        L_0x0091:
            r10.zzaqe = r2
            return r0
        L_0x0094:
            com.google.android.gms.internal.ads.zzfx r11 = new com.google.android.gms.internal.ads.zzfx
            r0 = 39
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Unsupported version number: "
            r1.append(r0)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r11.<init>(r12)
            throw r11
        L_0x00ad:
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            r12.reset()
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            byte[] r12 = r12.data
            r3 = 8
            boolean r12 = r11.zza(r12, r2, r3, r1)
            if (r12 == 0) goto L_0x00da
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            int r12 = r12.readInt()
            int r2 = zzatm
            if (r12 != r2) goto L_0x00d2
            com.google.android.gms.internal.ads.zzpx r12 = r10.zzatn
            int r12 = r12.readUnsignedByte()
            r10.version = r12
            r2 = 1
            goto L_0x00da
        L_0x00d2:
            java.io.IOException r11 = new java.io.IOException
            java.lang.String r12 = "Input not RawCC"
            r11.<init>(r12)
            throw r11
        L_0x00da:
            if (r2 == 0) goto L_0x00e0
            r10.zzaqe = r1
            goto L_0x0000
        L_0x00e0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjw.zza(com.google.android.gms.internal.ads.zzia, com.google.android.gms.internal.ads.zzif):int");
    }

    public final void zzc(long j, long j2) {
        this.zzaqe = 0;
    }
}
