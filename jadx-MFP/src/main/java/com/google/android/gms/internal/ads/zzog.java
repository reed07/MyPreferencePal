package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicReference;

public final class zzog extends zzoj {
    private static final int[] zzbem = new int[0];
    private final zzon zzben;
    private final AtomicReference<zzoh> zzbeo;

    public zzog() {
        this(null);
    }

    private static int zze(int i, int i2) {
        if (i == -1) {
            return i2 == -1 ? 0 : -1;
        }
        if (i2 == -1) {
            return 1;
        }
        return i - i2;
    }

    private static boolean zze(int i, boolean z) {
        int i2 = i & 3;
        return i2 == 3 || (z && i2 == 2);
    }

    private zzog(zzon zzon) {
        this.zzben = null;
        this.zzbeo = new AtomicReference<>(new zzoh());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0185, code lost:
        if (r9.zzzf <= r15) goto L_0x018a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzom[] zza(com.google.android.gms.internal.ads.zzga[] r36, com.google.android.gms.internal.ads.zzma[] r37, int[][][] r38) throws com.google.android.gms.internal.ads.zzff {
        /*
            r35 = this;
            r0 = r36
            int r1 = r0.length
            com.google.android.gms.internal.ads.zzom[] r2 = new com.google.android.gms.internal.ads.zzom[r1]
            r3 = r35
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.internal.ads.zzoh> r4 = r3.zzbeo
            java.lang.Object r4 = r4.get()
            com.google.android.gms.internal.ads.zzoh r4 = (com.google.android.gms.internal.ads.zzoh) r4
            r6 = 0
            r7 = 0
        L_0x0011:
            r8 = 2
            if (r6 >= r1) goto L_0x0262
            r12 = r0[r6]
            int r12 = r12.getTrackType()
            if (r8 != r12) goto L_0x024c
            if (r7 != 0) goto L_0x023f
            r7 = r37[r6]
            r12 = r38[r6]
            int r13 = r4.zzbet
            int r14 = r4.zzbeu
            int r15 = r4.zzbev
            int r10 = r4.viewportWidth
            int r8 = r4.viewportHeight
            boolean r5 = r4.zzbey
            boolean r9 = r4.zzbew
            boolean r11 = r4.zzbex
            r24 = r1
            r20 = r4
            r0 = 0
            r3 = 0
            r4 = 0
            r21 = 0
            r22 = -1
            r23 = -1
        L_0x003f:
            int r1 = r7.length
            if (r3 >= r1) goto L_0x021d
            com.google.android.gms.internal.ads.zzlz r1 = r7.zzau(r3)
            r25 = r7
            java.util.ArrayList r7 = new java.util.ArrayList
            r26 = r2
            int r2 = r1.length
            r7.<init>(r2)
            r27 = r6
            r2 = 0
        L_0x0055:
            int r6 = r1.length
            if (r2 >= r6) goto L_0x0063
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            r7.add(r6)
            int r2 = r2 + 1
            goto L_0x0055
        L_0x0063:
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r10 == r2) goto L_0x0139
            if (r8 != r2) goto L_0x006c
            goto L_0x0139
        L_0x006c:
            r28 = r0
            r6 = 0
        L_0x006f:
            int r0 = r1.length
            if (r6 >= r0) goto L_0x0103
            com.google.android.gms.internal.ads.zzfs r0 = r1.zzat(r6)
            r29 = r4
            int r4 = r0.width
            if (r4 <= 0) goto L_0x00e9
            int r4 = r0.height
            if (r4 <= 0) goto L_0x00e9
            int r4 = r0.width
            r30 = r9
            int r9 = r0.height
            if (r5 == 0) goto L_0x00a3
            if (r4 <= r9) goto L_0x008f
            r31 = r5
            r5 = 1
            goto L_0x0092
        L_0x008f:
            r31 = r5
            r5 = 0
        L_0x0092:
            if (r10 <= r8) goto L_0x0098
            r32 = r8
            r8 = 1
            goto L_0x009b
        L_0x0098:
            r32 = r8
            r8 = 0
        L_0x009b:
            if (r5 == r8) goto L_0x00a7
            r5 = r10
            r33 = r5
            r8 = r32
            goto L_0x00ac
        L_0x00a3:
            r31 = r5
            r32 = r8
        L_0x00a7:
            r8 = r10
            r33 = r8
            r5 = r32
        L_0x00ac:
            int r10 = r4 * r5
            r34 = r15
            int r15 = r9 * r8
            if (r10 < r15) goto L_0x00bf
            android.graphics.Point r5 = new android.graphics.Point
            int r4 = com.google.android.gms.internal.ads.zzqe.zzf(r15, r4)
            r5.<init>(r8, r4)
            r4 = r5
            goto L_0x00c8
        L_0x00bf:
            android.graphics.Point r4 = new android.graphics.Point
            int r8 = com.google.android.gms.internal.ads.zzqe.zzf(r10, r9)
            r4.<init>(r8, r5)
        L_0x00c8:
            int r5 = r0.width
            int r8 = r0.height
            int r5 = r5 * r8
            int r8 = r0.width
            int r9 = r4.x
            float r9 = (float) r9
            r10 = 1065017672(0x3f7ae148, float:0.98)
            float r9 = r9 * r10
            int r9 = (int) r9
            if (r8 < r9) goto L_0x00f3
            int r0 = r0.height
            int r4 = r4.y
            float r4 = (float) r4
            float r4 = r4 * r10
            int r4 = (int) r4
            if (r0 < r4) goto L_0x00f3
            if (r5 >= r2) goto L_0x00f3
            r2 = r5
            goto L_0x00f3
        L_0x00e9:
            r31 = r5
            r32 = r8
            r30 = r9
            r33 = r10
            r34 = r15
        L_0x00f3:
            int r6 = r6 + 1
            r4 = r29
            r9 = r30
            r5 = r31
            r8 = r32
            r10 = r33
            r15 = r34
            goto L_0x006f
        L_0x0103:
            r29 = r4
            r31 = r5
            r32 = r8
            r30 = r9
            r33 = r10
            r34 = r15
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r2 == r0) goto L_0x0147
            int r0 = r7.size()
            r4 = 1
            int r0 = r0 - r4
        L_0x011a:
            if (r0 < 0) goto L_0x0147
            java.lang.Object r4 = r7.get(r0)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            com.google.android.gms.internal.ads.zzfs r4 = r1.zzat(r4)
            int r4 = r4.zzce()
            r5 = -1
            if (r4 == r5) goto L_0x0133
            if (r4 <= r2) goto L_0x0136
        L_0x0133:
            r7.remove(r0)
        L_0x0136:
            int r0 = r0 + -1
            goto L_0x011a
        L_0x0139:
            r28 = r0
            r29 = r4
            r31 = r5
            r32 = r8
            r30 = r9
            r33 = r10
            r34 = r15
        L_0x0147:
            r0 = r12[r3]
            r5 = r21
            r6 = r22
            r8 = r23
            r4 = r29
            r2 = 0
        L_0x0152:
            int r9 = r1.length
            if (r2 >= r9) goto L_0x01ff
            r9 = r0[r2]
            boolean r9 = zze(r9, r11)
            if (r9 == 0) goto L_0x01eb
            com.google.android.gms.internal.ads.zzfs r9 = r1.zzat(r2)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            boolean r10 = r7.contains(r10)
            if (r10 == 0) goto L_0x018c
            int r10 = r9.width
            r15 = -1
            if (r10 == r15) goto L_0x0175
            int r10 = r9.width
            if (r10 > r13) goto L_0x018c
        L_0x0175:
            int r10 = r9.height
            if (r10 == r15) goto L_0x017d
            int r10 = r9.height
            if (r10 > r14) goto L_0x018c
        L_0x017d:
            int r10 = r9.zzzf
            if (r10 == r15) goto L_0x0188
            int r10 = r9.zzzf
            r15 = r34
            if (r10 > r15) goto L_0x018e
            goto L_0x018a
        L_0x0188:
            r15 = r34
        L_0x018a:
            r10 = 1
            goto L_0x018f
        L_0x018c:
            r15 = r34
        L_0x018e:
            r10 = 0
        L_0x018f:
            if (r10 != 0) goto L_0x019b
            if (r30 == 0) goto L_0x0194
            goto L_0x019b
        L_0x0194:
            r23 = r0
            r21 = r1
            r22 = r4
            goto L_0x01f3
        L_0x019b:
            if (r10 == 0) goto L_0x01a3
            r21 = r1
            r22 = r4
            r1 = 2
            goto L_0x01a8
        L_0x01a3:
            r21 = r1
            r22 = r4
            r1 = 1
        L_0x01a8:
            r4 = r0[r2]
            r23 = r0
            r0 = 0
            boolean r4 = zze(r4, r0)
            if (r4 == 0) goto L_0x01b5
            int r1 = r1 + 1000
        L_0x01b5:
            if (r1 <= r5) goto L_0x01b9
            r0 = 1
            goto L_0x01ba
        L_0x01b9:
            r0 = 0
        L_0x01ba:
            if (r1 != r5) goto L_0x01dd
            int r0 = r9.zzce()
            if (r0 == r6) goto L_0x01cb
            int r0 = r9.zzce()
            int r0 = zze(r0, r6)
            goto L_0x01d1
        L_0x01cb:
            int r0 = r9.zzzf
            int r0 = zze(r0, r8)
        L_0x01d1:
            if (r4 == 0) goto L_0x01d8
            if (r10 == 0) goto L_0x01d8
            if (r0 <= 0) goto L_0x01dc
            goto L_0x01da
        L_0x01d8:
            if (r0 >= 0) goto L_0x01dc
        L_0x01da:
            r0 = 1
            goto L_0x01dd
        L_0x01dc:
            r0 = 0
        L_0x01dd:
            if (r0 == 0) goto L_0x01f3
            int r8 = r9.zzzf
            int r6 = r9.zzce()
            r5 = r1
            r28 = r2
            r4 = r21
            goto L_0x01f5
        L_0x01eb:
            r23 = r0
            r21 = r1
            r22 = r4
            r15 = r34
        L_0x01f3:
            r4 = r22
        L_0x01f5:
            int r2 = r2 + 1
            r34 = r15
            r1 = r21
            r0 = r23
            goto L_0x0152
        L_0x01ff:
            r22 = r4
            r15 = r34
            int r3 = r3 + 1
            r21 = r5
            r23 = r8
            r7 = r25
            r2 = r26
            r0 = r28
            r9 = r30
            r5 = r31
            r8 = r32
            r10 = r33
            r22 = r6
            r6 = r27
            goto L_0x003f
        L_0x021d:
            r28 = r0
            r26 = r2
            r29 = r4
            r27 = r6
            if (r29 != 0) goto L_0x022a
            r16 = 0
            goto L_0x0235
        L_0x022a:
            com.google.android.gms.internal.ads.zzoi r10 = new com.google.android.gms.internal.ads.zzoi
            r1 = r28
            r0 = r29
            r10.<init>(r0, r1)
            r16 = r10
        L_0x0235:
            r26[r27] = r16
            r0 = r26[r27]
            if (r0 == 0) goto L_0x023d
            r7 = 1
            goto L_0x0247
        L_0x023d:
            r7 = 0
            goto L_0x0247
        L_0x023f:
            r24 = r1
            r26 = r2
            r20 = r4
            r27 = r6
        L_0x0247:
            r0 = r37[r27]
            int r0 = r0.length
            goto L_0x0254
        L_0x024c:
            r24 = r1
            r26 = r2
            r20 = r4
            r27 = r6
        L_0x0254:
            int r6 = r27 + 1
            r4 = r20
            r1 = r24
            r2 = r26
            r0 = r36
            r3 = r35
            goto L_0x0011
        L_0x0262:
            r26 = r2
            r20 = r4
            r0 = r1
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x026a:
            if (r1 >= r0) goto L_0x046f
            r4 = r36[r1]
            int r4 = r4.getTrackType()
            switch(r4) {
                case 1: goto L_0x035b;
                case 2: goto L_0x034e;
                case 3: goto L_0x028f;
                default: goto L_0x0275;
            }
        L_0x0275:
            r24 = r0
            r0 = r2
            r6 = r20
            r2 = 0
            r5 = -1
            r17 = 2
            r4 = r36[r1]
            r4.getTrackType()
            r4 = r37[r1]
            r7 = r38[r1]
            boolean r8 = r6.zzbex
            r10 = r2
            r9 = 0
            r11 = 0
            r12 = 0
            goto L_0x03fa
        L_0x028f:
            if (r3 != 0) goto L_0x0341
            r3 = r37[r1]
            r4 = r38[r1]
            r6 = r20
            boolean r7 = r6.zzbex
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
        L_0x029d:
            int r13 = r3.length
            if (r9 >= r13) goto L_0x0325
            com.google.android.gms.internal.ads.zzlz r13 = r3.zzau(r9)
            r14 = r4[r9]
            r15 = r12
            r12 = r11
            r11 = r10
            r10 = 0
        L_0x02ab:
            int r5 = r13.length
            if (r10 >= r5) goto L_0x0318
            r5 = r14[r10]
            boolean r5 = zze(r5, r7)
            if (r5 == 0) goto L_0x030b
            com.google.android.gms.internal.ads.zzfs r5 = r13.zzat(r10)
            int r8 = r5.zzzz
            r19 = 1
            r8 = r8 & 1
            if (r8 == 0) goto L_0x02c7
            r24 = r0
            r8 = 1
            goto L_0x02ca
        L_0x02c7:
            r24 = r0
            r8 = 0
        L_0x02ca:
            int r0 = r5.zzzz
            r17 = 2
            r0 = r0 & 2
            if (r0 == 0) goto L_0x02d6
            r22 = r3
            r0 = 1
            goto L_0x02d9
        L_0x02d6:
            r22 = r3
            r0 = 0
        L_0x02d9:
            r3 = 0
            boolean r23 = zza(r5, r3)
            if (r23 == 0) goto L_0x02ea
            if (r8 == 0) goto L_0x02e4
            r8 = 6
            goto L_0x02fa
        L_0x02e4:
            if (r0 != 0) goto L_0x02e8
            r8 = 5
            goto L_0x02fa
        L_0x02e8:
            r8 = 4
            goto L_0x02fa
        L_0x02ea:
            if (r8 == 0) goto L_0x02ee
            r8 = 3
            goto L_0x02fa
        L_0x02ee:
            if (r0 == 0) goto L_0x0311
            r0 = 0
            boolean r3 = zza(r5, r0)
            if (r3 == 0) goto L_0x02f9
            r8 = 2
            goto L_0x02fa
        L_0x02f9:
            r8 = 1
        L_0x02fa:
            r0 = r14[r10]
            r3 = 0
            boolean r0 = zze(r0, r3)
            if (r0 == 0) goto L_0x0305
            int r8 = r8 + 1000
        L_0x0305:
            if (r8 <= r15) goto L_0x0311
            r15 = r8
            r12 = r10
            r11 = r13
            goto L_0x0311
        L_0x030b:
            r24 = r0
            r22 = r3
            r17 = 2
        L_0x0311:
            int r10 = r10 + 1
            r3 = r22
            r0 = r24
            goto L_0x02ab
        L_0x0318:
            r24 = r0
            r22 = r3
            r17 = 2
            int r9 = r9 + 1
            r10 = r11
            r11 = r12
            r12 = r15
            goto L_0x029d
        L_0x0325:
            r24 = r0
            r17 = 2
            if (r10 != 0) goto L_0x032d
            r0 = 0
            goto L_0x0332
        L_0x032d:
            com.google.android.gms.internal.ads.zzoi r0 = new com.google.android.gms.internal.ads.zzoi
            r0.<init>(r10, r11)
        L_0x0332:
            r26[r1] = r0
            r0 = r26[r1]
            if (r0 == 0) goto L_0x033a
            r0 = 1
            goto L_0x033b
        L_0x033a:
            r0 = 0
        L_0x033b:
            r3 = r0
            r0 = 0
            r18 = 1
            goto L_0x0467
        L_0x0341:
            r24 = r0
            r6 = r20
            r17 = 2
            r20 = r2
            r0 = 0
            r18 = 1
            goto L_0x0465
        L_0x034e:
            r24 = r0
            r6 = r20
            r17 = 2
            r20 = r2
            r0 = 0
            r18 = 1
            goto L_0x0465
        L_0x035b:
            r24 = r0
            r6 = r20
            r17 = 2
            if (r2 != 0) goto L_0x03f2
            r0 = r37[r1]
            r2 = r38[r1]
            boolean r4 = r6.zzbex
            r5 = 0
            r7 = -1
            r8 = -1
            r9 = 0
        L_0x036d:
            int r10 = r0.length
            if (r5 >= r10) goto L_0x03d4
            com.google.android.gms.internal.ads.zzlz r10 = r0.zzau(r5)
            r11 = r2[r5]
            r12 = r9
            r9 = r8
            r8 = r7
            r7 = 0
        L_0x037b:
            int r13 = r10.length
            if (r7 >= r13) goto L_0x03c9
            r13 = r11[r7]
            boolean r13 = zze(r13, r4)
            if (r13 == 0) goto L_0x03c1
            com.google.android.gms.internal.ads.zzfs r13 = r10.zzat(r7)
            r14 = r11[r7]
            int r15 = r13.zzzz
            r19 = 1
            r15 = r15 & 1
            if (r15 == 0) goto L_0x039a
            r16 = r2
            r2 = 0
            r15 = 1
            goto L_0x039e
        L_0x039a:
            r16 = r2
            r2 = 0
            r15 = 0
        L_0x039e:
            boolean r13 = zza(r13, r2)
            if (r13 == 0) goto L_0x03ac
            if (r15 == 0) goto L_0x03a9
            r13 = 4
            r15 = 0
            goto L_0x03b3
        L_0x03a9:
            r13 = 3
            r15 = 0
            goto L_0x03b3
        L_0x03ac:
            if (r15 == 0) goto L_0x03b1
            r13 = 2
            r15 = 0
            goto L_0x03b3
        L_0x03b1:
            r13 = 1
            r15 = 0
        L_0x03b3:
            boolean r14 = zze(r14, r15)
            if (r14 == 0) goto L_0x03bb
            int r13 = r13 + 1000
        L_0x03bb:
            if (r13 <= r12) goto L_0x03c4
            r8 = r5
            r9 = r7
            r12 = r13
            goto L_0x03c4
        L_0x03c1:
            r16 = r2
            r2 = 0
        L_0x03c4:
            int r7 = r7 + 1
            r2 = r16
            goto L_0x037b
        L_0x03c9:
            r16 = r2
            r2 = 0
            int r5 = r5 + 1
            r7 = r8
            r8 = r9
            r9 = r12
            r2 = r16
            goto L_0x036d
        L_0x03d4:
            r2 = 0
            r5 = -1
            if (r7 != r5) goto L_0x03da
            r10 = r2
            goto L_0x03e3
        L_0x03da:
            com.google.android.gms.internal.ads.zzlz r0 = r0.zzau(r7)
            com.google.android.gms.internal.ads.zzoi r10 = new com.google.android.gms.internal.ads.zzoi
            r10.<init>(r0, r8)
        L_0x03e3:
            r26[r1] = r10
            r0 = r26[r1]
            if (r0 == 0) goto L_0x03eb
            r0 = 1
            goto L_0x03ec
        L_0x03eb:
            r0 = 0
        L_0x03ec:
            r2 = r0
            r0 = 0
            r18 = 1
            goto L_0x0467
        L_0x03f2:
            r0 = r2
            r20 = r0
            r0 = 0
            r18 = 1
            goto L_0x0465
        L_0x03fa:
            int r13 = r4.length
            if (r9 >= r13) goto L_0x0455
            com.google.android.gms.internal.ads.zzlz r13 = r4.zzau(r9)
            r14 = r7[r9]
            r15 = r12
            r12 = r11
            r11 = r10
            r10 = 0
        L_0x0408:
            int r2 = r13.length
            if (r10 >= r2) goto L_0x0446
            r2 = r14[r10]
            boolean r2 = zze(r2, r8)
            if (r2 == 0) goto L_0x043b
            com.google.android.gms.internal.ads.zzfs r2 = r13.zzat(r10)
            int r2 = r2.zzzz
            r18 = 1
            r2 = r2 & 1
            if (r2 == 0) goto L_0x0422
            r2 = 1
            goto L_0x0423
        L_0x0422:
            r2 = 0
        L_0x0423:
            if (r2 == 0) goto L_0x0427
            r2 = 2
            goto L_0x0428
        L_0x0427:
            r2 = 1
        L_0x0428:
            r5 = r14[r10]
            r20 = r0
            r0 = 0
            boolean r5 = zze(r5, r0)
            if (r5 == 0) goto L_0x0435
            int r2 = r2 + 1000
        L_0x0435:
            if (r2 <= r15) goto L_0x0440
            r15 = r2
            r12 = r10
            r11 = r13
            goto L_0x0440
        L_0x043b:
            r20 = r0
            r0 = 0
            r18 = 1
        L_0x0440:
            int r10 = r10 + 1
            r0 = r20
            r5 = -1
            goto L_0x0408
        L_0x0446:
            r20 = r0
            r0 = 0
            r18 = 1
            int r9 = r9 + 1
            r10 = r11
            r11 = r12
            r12 = r15
            r0 = r20
            r2 = 0
            r5 = -1
            goto L_0x03fa
        L_0x0455:
            r20 = r0
            r0 = 0
            r18 = 1
            if (r10 != 0) goto L_0x045e
            r2 = 0
            goto L_0x0463
        L_0x045e:
            com.google.android.gms.internal.ads.zzoi r2 = new com.google.android.gms.internal.ads.zzoi
            r2.<init>(r10, r11)
        L_0x0463:
            r26[r1] = r2
        L_0x0465:
            r2 = r20
        L_0x0467:
            int r1 = r1 + 1
            r20 = r6
            r0 = r24
            goto L_0x026a
        L_0x046f:
            return r26
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzog.zza(com.google.android.gms.internal.ads.zzga[], com.google.android.gms.internal.ads.zzma[], int[][][]):com.google.android.gms.internal.ads.zzom[]");
    }

    private static boolean zza(zzfs zzfs, String str) {
        return str != null && TextUtils.equals(str, zzqe.zzai(zzfs.zzaaa));
    }
}
