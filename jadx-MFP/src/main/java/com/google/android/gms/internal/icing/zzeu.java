package com.google.android.gms.internal.icing;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

final class zzeu<T> implements zzff<T> {
    private static final int[] zzmd = new int[0];
    private static final Unsafe zzme = zzgd.zzdt();
    private final int[] zzmf;
    private final Object[] zzmg;
    private final int zzmh;
    private final int zzmi;
    private final zzeq zzmj;
    private final boolean zzmk;
    private final boolean zzml;
    private final boolean zzmm;
    private final boolean zzmn;
    private final int[] zzmo;
    private final int zzmp;
    private final int zzmq;
    private final zzex zzmr;
    private final zzeb zzms;
    private final zzfx<?, ?> zzmt;
    private final zzcz<?> zzmu;
    private final zzel zzmv;

    private zzeu(int[] iArr, Object[] objArr, int i, int i2, zzeq zzeq, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzex zzex, zzeb zzeb, zzfx<?, ?> zzfx, zzcz<?> zzcz, zzel zzel) {
        this.zzmf = iArr;
        this.zzmg = objArr;
        this.zzmh = i;
        this.zzmi = i2;
        this.zzml = zzeq instanceof zzdj;
        this.zzmm = z;
        this.zzmk = zzcz != null && zzcz.zze(zzeq);
        this.zzmn = false;
        this.zzmo = iArr2;
        this.zzmp = i3;
        this.zzmq = i4;
        this.zzmr = zzex;
        this.zzms = zzeb;
        this.zzmt = zzfx;
        this.zzmu = zzcz;
        this.zzmj = zzeq;
        this.zzmv = zzel;
    }

    /* JADX WARNING: Removed duplicated region for block: B:165:0x0381  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x03e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.icing.zzeu<T> zza(java.lang.Class<T> r35, com.google.android.gms.internal.icing.zzeo r36, com.google.android.gms.internal.icing.zzex r37, com.google.android.gms.internal.icing.zzeb r38, com.google.android.gms.internal.icing.zzfx<?, ?> r39, com.google.android.gms.internal.icing.zzcz<?> r40, com.google.android.gms.internal.icing.zzel r41) {
        /*
            r0 = r36
            boolean r1 = r0 instanceof com.google.android.gms.internal.icing.zzfe
            if (r1 == 0) goto L_0x0454
            com.google.android.gms.internal.icing.zzfe r0 = (com.google.android.gms.internal.icing.zzfe) r0
            int r1 = r0.zzcr()
            int r2 = com.google.android.gms.internal.icing.zzdj.zzd.zzkj
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0014
            r11 = 1
            goto L_0x0015
        L_0x0014:
            r11 = 0
        L_0x0015:
            java.lang.String r1 = r0.zzda()
            int r2 = r1.length()
            char r5 = r1.charAt(r3)
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r7) goto L_0x003f
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r8 = r5
            r5 = 1
            r9 = 13
        L_0x002c:
            int r10 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r7) goto L_0x003c
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r9
            r8 = r8 | r5
            int r9 = r9 + 13
            r5 = r10
            goto L_0x002c
        L_0x003c:
            int r5 = r5 << r9
            r5 = r5 | r8
            goto L_0x0040
        L_0x003f:
            r10 = 1
        L_0x0040:
            int r8 = r10 + 1
            char r9 = r1.charAt(r10)
            if (r9 < r7) goto L_0x005f
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x004c:
            int r12 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x005c
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r9 = r9 | r8
            int r10 = r10 + 13
            r8 = r12
            goto L_0x004c
        L_0x005c:
            int r8 = r8 << r10
            r9 = r9 | r8
            goto L_0x0060
        L_0x005f:
            r12 = r8
        L_0x0060:
            if (r9 != 0) goto L_0x006e
            int[] r8 = zzmd
            r15 = r8
            r8 = 0
            r9 = 0
            r10 = 0
            r13 = 0
            r14 = 0
            r16 = 0
            goto L_0x01a0
        L_0x006e:
            int r8 = r12 + 1
            char r9 = r1.charAt(r12)
            if (r9 < r7) goto L_0x008e
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x007a:
            int r12 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x008a
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r9 = r9 | r8
            int r10 = r10 + 13
            r8 = r12
            goto L_0x007a
        L_0x008a:
            int r8 = r8 << r10
            r8 = r8 | r9
            r9 = r8
            goto L_0x008f
        L_0x008e:
            r12 = r8
        L_0x008f:
            int r8 = r12 + 1
            char r10 = r1.charAt(r12)
            if (r10 < r7) goto L_0x00ae
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x009b:
            int r13 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x00ab
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r12
            r10 = r10 | r8
            int r12 = r12 + 13
            r8 = r13
            goto L_0x009b
        L_0x00ab:
            int r8 = r8 << r12
            r10 = r10 | r8
            goto L_0x00af
        L_0x00ae:
            r13 = r8
        L_0x00af:
            int r8 = r13 + 1
            char r12 = r1.charAt(r13)
            if (r12 < r7) goto L_0x00cf
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00bb:
            int r14 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x00cb
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r13
            r12 = r12 | r8
            int r13 = r13 + 13
            r8 = r14
            goto L_0x00bb
        L_0x00cb:
            int r8 = r8 << r13
            r8 = r8 | r12
            r12 = r8
            goto L_0x00d0
        L_0x00cf:
            r14 = r8
        L_0x00d0:
            int r8 = r14 + 1
            char r13 = r1.charAt(r14)
            if (r13 < r7) goto L_0x00f0
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00dc:
            int r15 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x00ec
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r14
            r13 = r13 | r8
            int r14 = r14 + 13
            r8 = r15
            goto L_0x00dc
        L_0x00ec:
            int r8 = r8 << r14
            r8 = r8 | r13
            r13 = r8
            goto L_0x00f1
        L_0x00f0:
            r15 = r8
        L_0x00f1:
            int r8 = r15 + 1
            char r14 = r1.charAt(r15)
            if (r14 < r7) goto L_0x0113
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x00fd:
            int r16 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x010e
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r15
            r14 = r14 | r8
            int r15 = r15 + 13
            r8 = r16
            goto L_0x00fd
        L_0x010e:
            int r8 = r8 << r15
            r8 = r8 | r14
            r14 = r8
            r8 = r16
        L_0x0113:
            int r15 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x0136
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x011f:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0131
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r8 = r8 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x011f
        L_0x0131:
            int r15 = r15 << r16
            r8 = r8 | r15
            r15 = r17
        L_0x0136:
            int r16 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0162
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r34 = r16
            r16 = r15
            r15 = r34
        L_0x0148:
            int r18 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x015b
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r17
            r16 = r16 | r15
            int r17 = r17 + 13
            r15 = r18
            goto L_0x0148
        L_0x015b:
            int r15 = r15 << r17
            r15 = r16 | r15
            r3 = r18
            goto L_0x0164
        L_0x0162:
            r3 = r16
        L_0x0164:
            int r16 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x018f
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r34 = r16
            r16 = r3
            r3 = r34
        L_0x0176:
            int r18 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x0189
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r17
            r16 = r16 | r3
            int r17 = r17 + 13
            r3 = r18
            goto L_0x0176
        L_0x0189:
            int r3 = r3 << r17
            r3 = r16 | r3
            r16 = r18
        L_0x018f:
            int r17 = r3 + r8
            int r15 = r17 + r15
            int[] r15 = new int[r15]
            int r17 = r9 << 1
            int r10 = r17 + r10
            r34 = r16
            r16 = r9
            r9 = r12
            r12 = r34
        L_0x01a0:
            sun.misc.Unsafe r6 = zzme
            java.lang.Object[] r17 = r0.zzdb()
            com.google.android.gms.internal.icing.zzeq r18 = r0.zzct()
            java.lang.Class r7 = r18.getClass()
            r18 = r10
            int r10 = r14 * 3
            int[] r10 = new int[r10]
            int r14 = r14 << r4
            java.lang.Object[] r14 = new java.lang.Object[r14]
            int r20 = r3 + r8
            r22 = r3
            r23 = r20
            r8 = 0
            r21 = 0
        L_0x01c0:
            if (r12 >= r2) goto L_0x042a
            int r24 = r12 + 1
            char r12 = r1.charAt(r12)
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r12 < r4) goto L_0x01f4
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
            r34 = r24
            r24 = r12
            r12 = r34
        L_0x01d7:
            int r27 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r4) goto L_0x01ed
            r4 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r26
            r24 = r24 | r4
            int r26 = r26 + 13
            r12 = r27
            r4 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01d7
        L_0x01ed:
            int r4 = r12 << r26
            r12 = r24 | r4
            r4 = r27
            goto L_0x01f6
        L_0x01f4:
            r4 = r24
        L_0x01f6:
            int r24 = r4 + 1
            char r4 = r1.charAt(r4)
            r26 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r2) goto L_0x022a
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r27 = 13
            r34 = r24
            r24 = r4
            r4 = r34
        L_0x020d:
            int r28 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r2) goto L_0x0223
            r2 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r27
            r24 = r24 | r2
            int r27 = r27 + 13
            r4 = r28
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x020d
        L_0x0223:
            int r2 = r4 << r27
            r4 = r24 | r2
            r2 = r28
            goto L_0x022c
        L_0x022a:
            r2 = r24
        L_0x022c:
            r24 = r3
            r3 = r4 & 255(0xff, float:3.57E-43)
            r27 = r11
            r11 = r4 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x023b
            int r11 = r8 + 1
            r15[r8] = r21
            r8 = r11
        L_0x023b:
            r11 = 51
            r30 = r8
            if (r3 < r11) goto L_0x02e4
            int r11 = r2 + 1
            char r2 = r1.charAt(r2)
            r8 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r8) goto L_0x026a
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r32 = 13
        L_0x0250:
            int r33 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r8) goto L_0x0265
            r8 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r32
            r2 = r2 | r8
            int r32 = r32 + 13
            r11 = r33
            r8 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0250
        L_0x0265:
            int r8 = r11 << r32
            r2 = r2 | r8
            r11 = r33
        L_0x026a:
            int r8 = r3 + -51
            r32 = r11
            r11 = 9
            if (r8 == r11) goto L_0x0290
            r11 = 17
            if (r8 != r11) goto L_0x0277
            goto L_0x0290
        L_0x0277:
            r11 = 12
            if (r8 != r11) goto L_0x028e
            r8 = r5 & 1
            r11 = 1
            if (r8 != r11) goto L_0x028e
            int r8 = r21 / 3
            int r8 = r8 << r11
            int r8 = r8 + r11
            int r11 = r18 + 1
            r18 = r17[r18]
            r14[r8] = r18
            r18 = r11
            r11 = 1
            goto L_0x029d
        L_0x028e:
            r11 = 1
            goto L_0x029d
        L_0x0290:
            int r8 = r21 / 3
            r11 = 1
            int r8 = r8 << r11
            int r8 = r8 + r11
            int r25 = r18 + 1
            r18 = r17[r18]
            r14[r8] = r18
            r18 = r25
        L_0x029d:
            int r2 = r2 << r11
            r8 = r17[r2]
            boolean r11 = r8 instanceof java.lang.reflect.Field
            if (r11 == 0) goto L_0x02a8
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            r11 = r9
            goto L_0x02b1
        L_0x02a8:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zza(r7, r8)
            r17[r2] = r8
            r11 = r9
        L_0x02b1:
            long r8 = r6.objectFieldOffset(r8)
            int r9 = (int) r8
            int r2 = r2 + 1
            r8 = r17[r2]
            r28 = r9
            boolean r9 = r8 instanceof java.lang.reflect.Field
            if (r9 == 0) goto L_0x02c3
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x02cb
        L_0x02c3:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zza(r7, r8)
            r17[r2] = r8
        L_0x02cb:
            long r8 = r6.objectFieldOffset(r8)
            int r2 = (int) r8
            r31 = r1
            r8 = r2
            r1 = r7
            r25 = r18
            r9 = r28
            r2 = 0
            r19 = 1
            r28 = r11
            r18 = r13
            r13 = r12
            r12 = r32
            goto L_0x03f2
        L_0x02e4:
            r11 = r9
            int r8 = r18 + 1
            r9 = r17[r18]
            java.lang.String r9 = (java.lang.String) r9
            java.lang.reflect.Field r9 = zza(r7, r9)
            r18 = r13
            r13 = 9
            if (r3 == r13) goto L_0x0369
            r13 = 17
            if (r3 != r13) goto L_0x02fb
            goto L_0x0369
        L_0x02fb:
            r13 = 27
            if (r3 == r13) goto L_0x0358
            r13 = 49
            if (r3 != r13) goto L_0x0304
            goto L_0x0358
        L_0x0304:
            r13 = 12
            if (r3 == r13) goto L_0x0343
            r13 = 30
            if (r3 == r13) goto L_0x0343
            r13 = 44
            if (r3 != r13) goto L_0x0311
            goto L_0x0343
        L_0x0311:
            r13 = 50
            if (r3 != r13) goto L_0x033f
            int r13 = r22 + 1
            r15[r22] = r21
            int r22 = r21 / 3
            r25 = 1
            int r22 = r22 << 1
            int r28 = r8 + 1
            r8 = r17[r8]
            r14[r22] = r8
            r8 = r4 & 2048(0x800, float:2.87E-42)
            if (r8 == 0) goto L_0x0337
            int r22 = r22 + 1
            int r8 = r28 + 1
            r28 = r17[r28]
            r14[r22] = r28
            r28 = r11
            r22 = r13
            r13 = r12
            goto L_0x0377
        L_0x0337:
            r22 = r13
            r8 = r28
            r28 = r11
            r13 = r12
            goto L_0x0377
        L_0x033f:
            r28 = r11
            r11 = 1
            goto L_0x0376
        L_0x0343:
            r13 = r5 & 1
            r28 = r11
            r11 = 1
            if (r13 != r11) goto L_0x0376
            int r13 = r21 / 3
            int r13 = r13 << r11
            int r13 = r13 + r11
            int r25 = r8 + 1
            r8 = r17[r8]
            r14[r13] = r8
            r13 = r12
            r8 = r25
            goto L_0x0377
        L_0x0358:
            r28 = r11
            r11 = 1
            int r13 = r21 / 3
            int r13 = r13 << r11
            int r13 = r13 + r11
            int r25 = r8 + 1
            r8 = r17[r8]
            r14[r13] = r8
            r13 = r12
            r8 = r25
            goto L_0x0377
        L_0x0369:
            r28 = r11
            r11 = 1
            int r13 = r21 / 3
            int r13 = r13 << r11
            int r13 = r13 + r11
            java.lang.Class r25 = r9.getType()
            r14[r13] = r25
        L_0x0376:
            r13 = r12
        L_0x0377:
            long r11 = r6.objectFieldOffset(r9)
            int r9 = (int) r11
            r11 = r5 & 1
            r12 = 1
            if (r11 != r12) goto L_0x03d7
            r11 = 17
            if (r3 > r11) goto L_0x03d7
            int r11 = r2 + 1
            char r2 = r1.charAt(r2)
            r12 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r12) goto L_0x03ae
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r19 = 13
        L_0x0394:
            int r29 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r12) goto L_0x03a6
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r19
            r2 = r2 | r11
            int r19 = r19 + 13
            r11 = r29
            goto L_0x0394
        L_0x03a6:
            int r11 = r11 << r19
            r2 = r2 | r11
            r11 = r29
            r19 = 1
            goto L_0x03b0
        L_0x03ae:
            r19 = 1
        L_0x03b0:
            int r25 = r16 << 1
            int r29 = r2 / 32
            int r25 = r25 + r29
            r12 = r17[r25]
            r31 = r1
            boolean r1 = r12 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x03c4
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
            r1 = r7
            r25 = r8
            goto L_0x03cf
        L_0x03c4:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zza(r7, r12)
            r17[r25] = r12
            r1 = r7
            r25 = r8
        L_0x03cf:
            long r7 = r6.objectFieldOffset(r12)
            int r8 = (int) r7
            int r2 = r2 % 32
            goto L_0x03e1
        L_0x03d7:
            r31 = r1
            r1 = r7
            r25 = r8
            r19 = 1
            r11 = r2
            r2 = 0
            r8 = 0
        L_0x03e1:
            r7 = 18
            if (r3 < r7) goto L_0x03f1
            r7 = 49
            if (r3 > r7) goto L_0x03f1
            int r7 = r23 + 1
            r15[r23] = r9
            r23 = r7
            r12 = r11
            goto L_0x03f2
        L_0x03f1:
            r12 = r11
        L_0x03f2:
            int r7 = r21 + 1
            r10[r21] = r13
            int r11 = r7 + 1
            r13 = r4 & 512(0x200, float:7.175E-43)
            if (r13 == 0) goto L_0x03ff
            r13 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0400
        L_0x03ff:
            r13 = 0
        L_0x0400:
            r4 = r4 & 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x0407
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x0408
        L_0x0407:
            r4 = 0
        L_0x0408:
            r4 = r4 | r13
            int r3 = r3 << 20
            r3 = r3 | r4
            r3 = r3 | r9
            r10[r7] = r3
            int r21 = r11 + 1
            int r2 = r2 << 20
            r2 = r2 | r8
            r10[r11] = r2
            r7 = r1
            r13 = r18
            r3 = r24
            r18 = r25
            r2 = r26
            r11 = r27
            r9 = r28
            r8 = r30
            r1 = r31
            r4 = 1
            goto L_0x01c0
        L_0x042a:
            r24 = r3
            r28 = r9
            r27 = r11
            r18 = r13
            com.google.android.gms.internal.icing.zzeu r1 = new com.google.android.gms.internal.icing.zzeu
            com.google.android.gms.internal.icing.zzeq r0 = r0.zzct()
            r12 = 0
            r5 = r1
            r6 = r10
            r7 = r14
            r8 = r28
            r9 = r18
            r10 = r0
            r13 = r15
            r14 = r24
            r15 = r20
            r16 = r37
            r17 = r38
            r18 = r39
            r19 = r40
            r20 = r41
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r1
        L_0x0454:
            com.google.android.gms.internal.icing.zzfs r0 = (com.google.android.gms.internal.icing.zzfs) r0
            r0.zzcr()
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzeu.zza(java.lang.Class, com.google.android.gms.internal.icing.zzeo, com.google.android.gms.internal.icing.zzex, com.google.android.gms.internal.icing.zzeb, com.google.android.gms.internal.icing.zzfx, com.google.android.gms.internal.icing.zzcz, com.google.android.gms.internal.icing.zzel):com.google.android.gms.internal.icing.zzeu");
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    public final boolean equals(T t, T t2) {
        int length = this.zzmf.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                int zzag = zzag(i);
                long j = (long) (zzag & 1048575);
                switch ((zzag & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzgd.zzn(t, j)) != Double.doubleToLongBits(zzgd.zzn(t2, j))) {
                            z = false;
                            break;
                        }
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzgd.zzm(t, j)) != Float.floatToIntBits(zzgd.zzm(t2, j))) {
                            z = false;
                            break;
                        }
                    case 2:
                        if (!zzc(t, t2, i) || zzgd.zzk(t, j) != zzgd.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 3:
                        if (!zzc(t, t2, i) || zzgd.zzk(t, j) != zzgd.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 4:
                        if (!zzc(t, t2, i) || zzgd.zzj(t, j) != zzgd.zzj(t2, j)) {
                            z = false;
                            break;
                        }
                    case 5:
                        if (!zzc(t, t2, i) || zzgd.zzk(t, j) != zzgd.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 6:
                        if (!zzc(t, t2, i) || zzgd.zzj(t, j) != zzgd.zzj(t2, j)) {
                            z = false;
                            break;
                        }
                    case 7:
                        if (!zzc(t, t2, i) || zzgd.zzl(t, j) != zzgd.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 8:
                        if (!zzc(t, t2, i) || !zzfh.zzd(zzgd.zzo(t, j), zzgd.zzo(t2, j))) {
                            z = false;
                            break;
                        }
                    case 9:
                        if (!zzc(t, t2, i) || !zzfh.zzd(zzgd.zzo(t, j), zzgd.zzo(t2, j))) {
                            z = false;
                            break;
                        }
                    case 10:
                        if (!zzc(t, t2, i) || !zzfh.zzd(zzgd.zzo(t, j), zzgd.zzo(t2, j))) {
                            z = false;
                            break;
                        }
                    case 11:
                        if (!zzc(t, t2, i) || zzgd.zzj(t, j) != zzgd.zzj(t2, j)) {
                            z = false;
                            break;
                        }
                    case 12:
                        if (!zzc(t, t2, i) || zzgd.zzj(t, j) != zzgd.zzj(t2, j)) {
                            z = false;
                            break;
                        }
                    case 13:
                        if (!zzc(t, t2, i) || zzgd.zzj(t, j) != zzgd.zzj(t2, j)) {
                            z = false;
                            break;
                        }
                    case 14:
                        if (!zzc(t, t2, i) || zzgd.zzk(t, j) != zzgd.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 15:
                        if (!zzc(t, t2, i) || zzgd.zzj(t, j) != zzgd.zzj(t2, j)) {
                            z = false;
                            break;
                        }
                    case 16:
                        if (!zzc(t, t2, i) || zzgd.zzk(t, j) != zzgd.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 17:
                        if (!zzc(t, t2, i) || !zzfh.zzd(zzgd.zzo(t, j), zzgd.zzo(t2, j))) {
                            z = false;
                            break;
                        }
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        z = zzfh.zzd(zzgd.zzo(t, j), zzgd.zzo(t2, j));
                        break;
                    case 50:
                        z = zzfh.zzd(zzgd.zzo(t, j), zzgd.zzo(t2, j));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                        long zzah = (long) (zzah(i) & 1048575);
                        if (zzgd.zzj(t, zzah) != zzgd.zzj(t2, zzah) || !zzfh.zzd(zzgd.zzo(t, j), zzgd.zzo(t2, j))) {
                            z = false;
                            break;
                        }
                }
                if (!z) {
                    return false;
                }
                i += 3;
            } else if (!this.zzmt.zzo(t).equals(this.zzmt.zzo(t2))) {
                return false;
            } else {
                if (this.zzmk) {
                    return this.zzmu.zzc(t).equals(this.zzmu.zzc(t2));
                }
                return true;
            }
        }
    }

    public final int hashCode(T t) {
        int length = this.zzmf.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzag = zzag(i2);
            int i3 = this.zzmf[i2];
            long j = (long) (1048575 & zzag);
            int i4 = 37;
            switch ((zzag & 267386880) >>> 20) {
                case 0:
                    i = (i * 53) + zzdl.zzk(Double.doubleToLongBits(zzgd.zzn(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzgd.zzm(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzdl.zzk(zzgd.zzk(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzdl.zzk(zzgd.zzk(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzgd.zzj(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzdl.zzk(zzgd.zzk(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzgd.zzj(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzdl.zzi(zzgd.zzl(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzgd.zzo(t, j)).hashCode();
                    break;
                case 9:
                    Object zzo = zzgd.zzo(t, j);
                    if (zzo != null) {
                        i4 = zzo.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzgd.zzo(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzgd.zzj(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzgd.zzj(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzgd.zzj(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzdl.zzk(zzgd.zzk(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzgd.zzj(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzdl.zzk(zzgd.zzk(t, j));
                    break;
                case 17:
                    Object zzo2 = zzgd.zzo(t, j);
                    if (zzo2 != null) {
                        i4 = zzo2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + zzgd.zzo(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzgd.zzo(t, j).hashCode();
                    break;
                case 51:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdl.zzk(Double.doubleToLongBits(zze(t, j)));
                        break;
                    }
                case 52:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzf(t, j));
                        break;
                    }
                case 53:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdl.zzk(zzh(t, j));
                        break;
                    }
                case 54:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdl.zzk(zzh(t, j));
                        break;
                    }
                case 55:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzg(t, j);
                        break;
                    }
                case 56:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdl.zzk(zzh(t, j));
                        break;
                    }
                case 57:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzg(t, j);
                        break;
                    }
                case 58:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdl.zzi(zzi(t, j));
                        break;
                    }
                case 59:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzgd.zzo(t, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgd.zzo(t, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgd.zzo(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzg(t, j);
                        break;
                    }
                case 63:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzg(t, j);
                        break;
                    }
                case 64:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzg(t, j);
                        break;
                    }
                case 65:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdl.zzk(zzh(t, j));
                        break;
                    }
                case 66:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzg(t, j);
                        break;
                    }
                case 67:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdl.zzk(zzh(t, j));
                        break;
                    }
                case 68:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzgd.zzo(t, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzmt.zzo(t).hashCode();
        return this.zzmk ? (hashCode * 53) + this.zzmu.zzc(t).hashCode() : hashCode;
    }

    public final void zzc(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzmf.length; i += 3) {
                int zzag = zzag(i);
                long j = (long) (1048575 & zzag);
                int i2 = this.zzmf[i];
                switch ((zzag & 267386880) >>> 20) {
                    case 0:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzn(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 1:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzm(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 2:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 3:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 4:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 5:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 6:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 7:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 8:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzo(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzo(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 11:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 12:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 13:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 14:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 15:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 16:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzms.zza(t, t2, j);
                        break;
                    case 50:
                        zzfh.zza(this.zzmv, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzo(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 60:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zzgd.zza((Object) t, j, zzgd.zzo(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            if (!this.zzmm) {
                zzfh.zza(this.zzmt, t, t2);
                if (this.zzmk) {
                    zzfh.zza(this.zzmu, t, t2);
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private final void zza(T t, T t2, int i) {
        long zzag = (long) (zzag(i) & 1048575);
        if (zza(t2, i)) {
            Object zzo = zzgd.zzo(t, zzag);
            Object zzo2 = zzgd.zzo(t2, zzag);
            if (zzo == null || zzo2 == null) {
                if (zzo2 != null) {
                    zzgd.zza((Object) t, zzag, zzo2);
                    zzb(t, i);
                }
                return;
            }
            zzgd.zza((Object) t, zzag, zzdl.zza(zzo, zzo2));
            zzb(t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzag = zzag(i);
        int i2 = this.zzmf[i];
        long j = (long) (zzag & 1048575);
        if (zza(t2, i2, i)) {
            Object zzo = zzgd.zzo(t, j);
            Object zzo2 = zzgd.zzo(t2, j);
            if (zzo == null || zzo2 == null) {
                if (zzo2 != null) {
                    zzgd.zza((Object) t, j, zzo2);
                    zzb(t, i2, i);
                }
                return;
            }
            zzgd.zza((Object) t, j, zzdl.zza(zzo, zzo2));
            zzb(t, i2, i);
        }
    }

    public final int zzl(T t) {
        int i;
        int i2;
        long j;
        T t2 = t;
        int i3 = 267386880;
        if (this.zzmm) {
            Unsafe unsafe = zzme;
            int i4 = 0;
            int i5 = 0;
            while (i4 < this.zzmf.length) {
                int zzag = zzag(i4);
                int i6 = (zzag & i3) >>> 20;
                int i7 = this.zzmf[i4];
                long j2 = (long) (zzag & 1048575);
                int i8 = (i6 < zzdf.DOUBLE_LIST_PACKED.id() || i6 > zzdf.SINT64_LIST_PACKED.id()) ? 0 : this.zzmf[i4 + 2] & 1048575;
                switch (i6) {
                    case 0:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzb(i7, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzb(i7, (float) BitmapDescriptorFactory.HUE_RED);
                            break;
                        }
                    case 2:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzd(i7, zzgd.zzk(t2, j2));
                            break;
                        }
                    case 3:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zze(i7, zzgd.zzk(t2, j2));
                            break;
                        }
                    case 4:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzg(i7, zzgd.zzj(t2, j2));
                            break;
                        }
                    case 5:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzg(i7, 0);
                            break;
                        }
                    case 6:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzj(i7, 0);
                            break;
                        }
                    case 7:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzb(i7, true);
                            break;
                        }
                    case 8:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            Object zzo = zzgd.zzo(t2, j2);
                            if (!(zzo instanceof zzce)) {
                                i5 += zzct.zzb(i7, (String) zzo);
                                break;
                            } else {
                                i5 += zzct.zzc(i7, (zzce) zzo);
                                break;
                            }
                        }
                    case 9:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzfh.zzc(i7, zzgd.zzo(t2, j2), zzae(i4));
                            break;
                        }
                    case 10:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzc(i7, (zzce) zzgd.zzo(t2, j2));
                            break;
                        }
                    case 11:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzh(i7, zzgd.zzj(t2, j2));
                            break;
                        }
                    case 12:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzl(i7, zzgd.zzj(t2, j2));
                            break;
                        }
                    case 13:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzk(i7, 0);
                            break;
                        }
                    case 14:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzh(i7, 0);
                            break;
                        }
                    case 15:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzi(i7, zzgd.zzj(t2, j2));
                            break;
                        }
                    case 16:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzf(i7, zzgd.zzk(t2, j2));
                            break;
                        }
                    case 17:
                        if (!zza(t2, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzc(i7, (zzeq) zzgd.zzo(t2, j2), zzae(i4));
                            break;
                        }
                    case 18:
                        i5 += zzfh.zzw(i7, zzd(t2, j2), false);
                        break;
                    case 19:
                        i5 += zzfh.zzv(i7, zzd(t2, j2), false);
                        break;
                    case 20:
                        i5 += zzfh.zzo(i7, zzd(t2, j2), false);
                        break;
                    case 21:
                        i5 += zzfh.zzp(i7, zzd(t2, j2), false);
                        break;
                    case 22:
                        i5 += zzfh.zzs(i7, zzd(t2, j2), false);
                        break;
                    case 23:
                        i5 += zzfh.zzw(i7, zzd(t2, j2), false);
                        break;
                    case 24:
                        i5 += zzfh.zzv(i7, zzd(t2, j2), false);
                        break;
                    case 25:
                        i5 += zzfh.zzx(i7, zzd(t2, j2), false);
                        break;
                    case 26:
                        i5 += zzfh.zzc(i7, zzd(t2, j2));
                        break;
                    case 27:
                        i5 += zzfh.zzc(i7, zzd(t2, j2), zzae(i4));
                        break;
                    case 28:
                        i5 += zzfh.zzd(i7, zzd(t2, j2));
                        break;
                    case 29:
                        i5 += zzfh.zzt(i7, zzd(t2, j2), false);
                        break;
                    case 30:
                        i5 += zzfh.zzr(i7, zzd(t2, j2), false);
                        break;
                    case 31:
                        i5 += zzfh.zzv(i7, zzd(t2, j2), false);
                        break;
                    case 32:
                        i5 += zzfh.zzw(i7, zzd(t2, j2), false);
                        break;
                    case 33:
                        i5 += zzfh.zzu(i7, zzd(t2, j2), false);
                        break;
                    case 34:
                        i5 += zzfh.zzq(i7, zzd(t2, j2), false);
                        break;
                    case 35:
                        int zzi = zzfh.zzi((List) unsafe.getObject(t2, j2));
                        if (zzi > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzi);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzi) + zzi;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzh = zzfh.zzh((List) unsafe.getObject(t2, j2));
                        if (zzh > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzh);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzh) + zzh;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zza = zzfh.zza((List) unsafe.getObject(t2, j2));
                        if (zza > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zza);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zza) + zza;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzb = zzfh.zzb((List) unsafe.getObject(t2, j2));
                        if (zzb > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzb);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzb) + zzb;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zze = zzfh.zze((List) unsafe.getObject(t2, j2));
                        if (zze > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zze);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zze) + zze;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzi2 = zzfh.zzi((List) unsafe.getObject(t2, j2));
                        if (zzi2 > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzi2);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzi2) + zzi2;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzh2 = zzfh.zzh((List) unsafe.getObject(t2, j2));
                        if (zzh2 > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzh2);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzh2) + zzh2;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzj = zzfh.zzj((List) unsafe.getObject(t2, j2));
                        if (zzj > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzj);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzj) + zzj;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzf = zzfh.zzf((List) unsafe.getObject(t2, j2));
                        if (zzf > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzf);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzf) + zzf;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzd = zzfh.zzd((List) unsafe.getObject(t2, j2));
                        if (zzd > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzd);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzd) + zzd;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzh3 = zzfh.zzh((List) unsafe.getObject(t2, j2));
                        if (zzh3 > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzh3);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzh3) + zzh3;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzi3 = zzfh.zzi((List) unsafe.getObject(t2, j2));
                        if (zzi3 > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzi3);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzi3) + zzi3;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzg = zzfh.zzg((List) unsafe.getObject(t2, j2));
                        if (zzg > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzg);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzg) + zzg;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzc = zzfh.zzc((List) unsafe.getObject(t2, j2));
                        if (zzc > 0) {
                            if (this.zzmn) {
                                unsafe.putInt(t2, (long) i8, zzc);
                            }
                            i5 += zzct.zzs(i7) + zzct.zzu(zzc) + zzc;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i5 += zzfh.zzd(i7, zzd(t2, j2), zzae(i4));
                        break;
                    case 50:
                        i5 += this.zzmv.zzb(i7, zzgd.zzo(t2, j2), zzaf(i4));
                        break;
                    case 51:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzb(i7, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzb(i7, (float) BitmapDescriptorFactory.HUE_RED);
                            break;
                        }
                    case 53:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzd(i7, zzh(t2, j2));
                            break;
                        }
                    case 54:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zze(i7, zzh(t2, j2));
                            break;
                        }
                    case 55:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzg(i7, zzg(t2, j2));
                            break;
                        }
                    case 56:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzg(i7, 0);
                            break;
                        }
                    case 57:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzj(i7, 0);
                            break;
                        }
                    case 58:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzb(i7, true);
                            break;
                        }
                    case 59:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            Object zzo2 = zzgd.zzo(t2, j2);
                            if (!(zzo2 instanceof zzce)) {
                                i5 += zzct.zzb(i7, (String) zzo2);
                                break;
                            } else {
                                i5 += zzct.zzc(i7, (zzce) zzo2);
                                break;
                            }
                        }
                    case 60:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzfh.zzc(i7, zzgd.zzo(t2, j2), zzae(i4));
                            break;
                        }
                    case 61:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzc(i7, (zzce) zzgd.zzo(t2, j2));
                            break;
                        }
                    case 62:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzh(i7, zzg(t2, j2));
                            break;
                        }
                    case 63:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzl(i7, zzg(t2, j2));
                            break;
                        }
                    case 64:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzk(i7, 0);
                            break;
                        }
                    case 65:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzh(i7, 0);
                            break;
                        }
                    case 66:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzi(i7, zzg(t2, j2));
                            break;
                        }
                    case 67:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzf(i7, zzh(t2, j2));
                            break;
                        }
                    case 68:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzct.zzc(i7, (zzeq) zzgd.zzo(t2, j2), zzae(i4));
                            break;
                        }
                }
                i4 += 3;
                i3 = 267386880;
            }
            return i5 + zza(this.zzmt, t2);
        }
        Unsafe unsafe2 = zzme;
        int i9 = 0;
        int i10 = 0;
        int i11 = -1;
        int i12 = 0;
        while (i9 < this.zzmf.length) {
            int zzag2 = zzag(i9);
            int[] iArr = this.zzmf;
            int i13 = iArr[i9];
            int i14 = (zzag2 & 267386880) >>> 20;
            if (i14 <= 17) {
                i2 = iArr[i9 + 2];
                int i15 = i2 & 1048575;
                i = 1 << (i2 >>> 20);
                if (i15 != i11) {
                    i12 = unsafe2.getInt(t2, (long) i15);
                } else {
                    i15 = i11;
                }
                i11 = i15;
            } else if (!this.zzmn || i14 < zzdf.DOUBLE_LIST_PACKED.id() || i14 > zzdf.SINT64_LIST_PACKED.id()) {
                i2 = 0;
                i = 0;
            } else {
                i2 = this.zzmf[i9 + 2] & 1048575;
                i = 0;
            }
            long j3 = (long) (zzag2 & 1048575);
            switch (i14) {
                case 0:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzct.zzb(i13, 0.0d);
                        break;
                    }
                case 1:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzct.zzb(i13, (float) BitmapDescriptorFactory.HUE_RED);
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzct.zzd(i13, unsafe2.getLong(t2, j3));
                        break;
                    }
                case 3:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzct.zze(i13, unsafe2.getLong(t2, j3));
                        break;
                    }
                case 4:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzct.zzg(i13, unsafe2.getInt(t2, j3));
                        break;
                    }
                case 5:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        i10 += zzct.zzg(i13, 0);
                        break;
                    }
                case 6:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzj(i13, 0);
                        j = 0;
                        break;
                    }
                case 7:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzb(i13, true);
                        j = 0;
                        break;
                    }
                case 8:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        Object object = unsafe2.getObject(t2, j3);
                        if (!(object instanceof zzce)) {
                            i10 += zzct.zzb(i13, (String) object);
                            j = 0;
                            break;
                        } else {
                            i10 += zzct.zzc(i13, (zzce) object);
                            j = 0;
                            break;
                        }
                    }
                case 9:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzfh.zzc(i13, unsafe2.getObject(t2, j3), zzae(i9));
                        j = 0;
                        break;
                    }
                case 10:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzc(i13, (zzce) unsafe2.getObject(t2, j3));
                        j = 0;
                        break;
                    }
                case 11:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzh(i13, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 12:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzl(i13, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 13:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzk(i13, 0);
                        j = 0;
                        break;
                    }
                case 14:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzh(i13, 0);
                        j = 0;
                        break;
                    }
                case 15:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzi(i13, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 16:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzf(i13, unsafe2.getLong(t2, j3));
                        j = 0;
                        break;
                    }
                case 17:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzc(i13, (zzeq) unsafe2.getObject(t2, j3), zzae(i9));
                        j = 0;
                        break;
                    }
                case 18:
                    i10 += zzfh.zzw(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 19:
                    i10 += zzfh.zzv(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 20:
                    i10 += zzfh.zzo(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 21:
                    i10 += zzfh.zzp(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 22:
                    i10 += zzfh.zzs(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 23:
                    i10 += zzfh.zzw(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 24:
                    i10 += zzfh.zzv(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 25:
                    i10 += zzfh.zzx(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 26:
                    i10 += zzfh.zzc(i13, (List) unsafe2.getObject(t2, j3));
                    j = 0;
                    break;
                case 27:
                    i10 += zzfh.zzc(i13, (List) unsafe2.getObject(t2, j3), zzae(i9));
                    j = 0;
                    break;
                case 28:
                    i10 += zzfh.zzd(i13, (List) unsafe2.getObject(t2, j3));
                    j = 0;
                    break;
                case 29:
                    i10 += zzfh.zzt(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 30:
                    i10 += zzfh.zzr(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 31:
                    i10 += zzfh.zzv(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 32:
                    i10 += zzfh.zzw(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 33:
                    i10 += zzfh.zzu(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 34:
                    i10 += zzfh.zzq(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 35:
                    int zzi4 = zzfh.zzi((List) unsafe2.getObject(t2, j3));
                    if (zzi4 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzi4);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzi4) + zzi4;
                        j = 0;
                        break;
                    }
                case 36:
                    int zzh4 = zzfh.zzh((List) unsafe2.getObject(t2, j3));
                    if (zzh4 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzh4);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzh4) + zzh4;
                        j = 0;
                        break;
                    }
                case 37:
                    int zza2 = zzfh.zza((List) unsafe2.getObject(t2, j3));
                    if (zza2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zza2);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zza2) + zza2;
                        j = 0;
                        break;
                    }
                case 38:
                    int zzb2 = zzfh.zzb((List) unsafe2.getObject(t2, j3));
                    if (zzb2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzb2);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzb2) + zzb2;
                        j = 0;
                        break;
                    }
                case 39:
                    int zze2 = zzfh.zze((List) unsafe2.getObject(t2, j3));
                    if (zze2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zze2);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zze2) + zze2;
                        j = 0;
                        break;
                    }
                case 40:
                    int zzi5 = zzfh.zzi((List) unsafe2.getObject(t2, j3));
                    if (zzi5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzi5);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzi5) + zzi5;
                        j = 0;
                        break;
                    }
                case 41:
                    int zzh5 = zzfh.zzh((List) unsafe2.getObject(t2, j3));
                    if (zzh5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzh5);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzh5) + zzh5;
                        j = 0;
                        break;
                    }
                case 42:
                    int zzj2 = zzfh.zzj((List) unsafe2.getObject(t2, j3));
                    if (zzj2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzj2);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzj2) + zzj2;
                        j = 0;
                        break;
                    }
                case 43:
                    int zzf2 = zzfh.zzf((List) unsafe2.getObject(t2, j3));
                    if (zzf2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzf2);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzf2) + zzf2;
                        j = 0;
                        break;
                    }
                case 44:
                    int zzd2 = zzfh.zzd((List) unsafe2.getObject(t2, j3));
                    if (zzd2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzd2);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzd2) + zzd2;
                        j = 0;
                        break;
                    }
                case 45:
                    int zzh6 = zzfh.zzh((List) unsafe2.getObject(t2, j3));
                    if (zzh6 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzh6);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzh6) + zzh6;
                        j = 0;
                        break;
                    }
                case 46:
                    int zzi6 = zzfh.zzi((List) unsafe2.getObject(t2, j3));
                    if (zzi6 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzi6);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzi6) + zzi6;
                        j = 0;
                        break;
                    }
                case 47:
                    int zzg2 = zzfh.zzg((List) unsafe2.getObject(t2, j3));
                    if (zzg2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzg2);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzg2) + zzg2;
                        j = 0;
                        break;
                    }
                case 48:
                    int zzc2 = zzfh.zzc((List) unsafe2.getObject(t2, j3));
                    if (zzc2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzmn) {
                            unsafe2.putInt(t2, (long) i2, zzc2);
                        }
                        i10 += zzct.zzs(i13) + zzct.zzu(zzc2) + zzc2;
                        j = 0;
                        break;
                    }
                case 49:
                    i10 += zzfh.zzd(i13, (List) unsafe2.getObject(t2, j3), zzae(i9));
                    j = 0;
                    break;
                case 50:
                    i10 += this.zzmv.zzb(i13, unsafe2.getObject(t2, j3), zzaf(i9));
                    j = 0;
                    break;
                case 51:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzb(i13, 0.0d);
                        j = 0;
                        break;
                    }
                case 52:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzb(i13, (float) BitmapDescriptorFactory.HUE_RED);
                        j = 0;
                        break;
                    }
                case 53:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzd(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 54:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zze(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 55:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzg(i13, zzg(t2, j3));
                        j = 0;
                        break;
                    }
                case 56:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzg(i13, 0);
                        j = 0;
                        break;
                    }
                case 57:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzj(i13, 0);
                        j = 0;
                        break;
                    }
                case 58:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzb(i13, true);
                        j = 0;
                        break;
                    }
                case 59:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        Object object2 = unsafe2.getObject(t2, j3);
                        if (!(object2 instanceof zzce)) {
                            i10 += zzct.zzb(i13, (String) object2);
                            j = 0;
                            break;
                        } else {
                            i10 += zzct.zzc(i13, (zzce) object2);
                            j = 0;
                            break;
                        }
                    }
                case 60:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzfh.zzc(i13, unsafe2.getObject(t2, j3), zzae(i9));
                        j = 0;
                        break;
                    }
                case 61:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzc(i13, (zzce) unsafe2.getObject(t2, j3));
                        j = 0;
                        break;
                    }
                case 62:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzh(i13, zzg(t2, j3));
                        j = 0;
                        break;
                    }
                case 63:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzl(i13, zzg(t2, j3));
                        j = 0;
                        break;
                    }
                case 64:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzk(i13, 0);
                        j = 0;
                        break;
                    }
                case 65:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzh(i13, 0);
                        j = 0;
                        break;
                    }
                case 66:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzi(i13, zzg(t2, j3));
                        j = 0;
                        break;
                    }
                case 67:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzf(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 68:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzct.zzc(i13, (zzeq) unsafe2.getObject(t2, j3), zzae(i9));
                        j = 0;
                        break;
                    }
                default:
                    j = 0;
                    break;
            }
            i9 += 3;
            long j4 = j;
        }
        int zza3 = i10 + zza(this.zzmt, t2);
        if (this.zzmk) {
            zza3 += this.zzmu.zzc(t2).zzbi();
        }
        return zza3;
    }

    private static <UT, UB> int zza(zzfx<UT, UB> zzfx, T t) {
        return zzfx.zzl(zzfx.zzo(t));
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzgd.zzo(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0511  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x054f  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a27  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.icing.zzgr r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzay()
            int r1 = com.google.android.gms.internal.icing.zzdj.zzd.zzkm
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x0527
            com.google.android.gms.internal.icing.zzfx<?, ?> r0 = r13.zzmt
            zza(r0, (T) r14, r15)
            boolean r0 = r13.zzmk
            if (r0 == 0) goto L_0x0030
            com.google.android.gms.internal.icing.zzcz<?> r0 = r13.zzmu
            com.google.android.gms.internal.icing.zzdc r0 = r0.zzc(r14)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0030
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0032
        L_0x0030:
            r0 = r3
            r1 = r0
        L_0x0032:
            int[] r7 = r13.zzmf
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0037:
            if (r7 < 0) goto L_0x050f
            int r8 = r13.zzag(r7)
            int[] r9 = r13.zzmf
            r9 = r9[r7]
        L_0x0041:
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.icing.zzcz<?> r10 = r13.zzmu
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x005f
            com.google.android.gms.internal.icing.zzcz<?> r10 = r13.zzmu
            r10.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005d
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0041
        L_0x005d:
            r1 = r3
            goto L_0x0041
        L_0x005f:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x04fc;
                case 1: goto L_0x04ec;
                case 2: goto L_0x04dc;
                case 3: goto L_0x04cc;
                case 4: goto L_0x04bc;
                case 5: goto L_0x04ac;
                case 6: goto L_0x049c;
                case 7: goto L_0x048b;
                case 8: goto L_0x047a;
                case 9: goto L_0x0465;
                case 10: goto L_0x0452;
                case 11: goto L_0x0441;
                case 12: goto L_0x0430;
                case 13: goto L_0x041f;
                case 14: goto L_0x040e;
                case 15: goto L_0x03fd;
                case 16: goto L_0x03ec;
                case 17: goto L_0x03d7;
                case 18: goto L_0x03c6;
                case 19: goto L_0x03b5;
                case 20: goto L_0x03a4;
                case 21: goto L_0x0393;
                case 22: goto L_0x0382;
                case 23: goto L_0x0371;
                case 24: goto L_0x0360;
                case 25: goto L_0x034f;
                case 26: goto L_0x033e;
                case 27: goto L_0x0329;
                case 28: goto L_0x0318;
                case 29: goto L_0x0307;
                case 30: goto L_0x02f6;
                case 31: goto L_0x02e5;
                case 32: goto L_0x02d4;
                case 33: goto L_0x02c3;
                case 34: goto L_0x02b2;
                case 35: goto L_0x02a1;
                case 36: goto L_0x0290;
                case 37: goto L_0x027f;
                case 38: goto L_0x026e;
                case 39: goto L_0x025d;
                case 40: goto L_0x024c;
                case 41: goto L_0x023b;
                case 42: goto L_0x022a;
                case 43: goto L_0x0219;
                case 44: goto L_0x0208;
                case 45: goto L_0x01f7;
                case 46: goto L_0x01e6;
                case 47: goto L_0x01d5;
                case 48: goto L_0x01c4;
                case 49: goto L_0x01af;
                case 50: goto L_0x01a4;
                case 51: goto L_0x0193;
                case 52: goto L_0x0182;
                case 53: goto L_0x0171;
                case 54: goto L_0x0160;
                case 55: goto L_0x014f;
                case 56: goto L_0x013e;
                case 57: goto L_0x012d;
                case 58: goto L_0x011c;
                case 59: goto L_0x010b;
                case 60: goto L_0x00f6;
                case 61: goto L_0x00e3;
                case 62: goto L_0x00d2;
                case 63: goto L_0x00c1;
                case 64: goto L_0x00b0;
                case 65: goto L_0x009f;
                case 66: goto L_0x008e;
                case 67: goto L_0x007d;
                case 68: goto L_0x0068;
                default: goto L_0x0066;
            }
        L_0x0066:
            goto L_0x050b
        L_0x0068:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzff r10 = r13.zzae(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050b
        L_0x007d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x050b
        L_0x008e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050b
        L_0x009f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050b
        L_0x00b0:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050b
        L_0x00c1:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x050b
        L_0x00d2:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x050b
        L_0x00e3:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzce r8 = (com.google.android.gms.internal.icing.zzce) r8
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x00f6:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzff r10 = r13.zzae(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050b
        L_0x010b:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050b
        L_0x011c:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzi(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x012d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050b
        L_0x013e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050b
        L_0x014f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzc(r9, r8)
            goto L_0x050b
        L_0x0160:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050b
        L_0x0171:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050b
        L_0x0182:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzf(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x0193:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zze(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050b
        L_0x01a4:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x050b
        L_0x01af:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzff r10 = r13.zzae(r7)
            com.google.android.gms.internal.icing.zzfh.zzb(r9, r8, r15, r10)
            goto L_0x050b
        L_0x01c4:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zze(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01d5:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzj(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01e6:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzg(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01f7:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzl(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0208:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzm(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0219:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzi(r9, r8, r15, r4)
            goto L_0x050b
        L_0x022a:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzn(r9, r8, r15, r4)
            goto L_0x050b
        L_0x023b:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzk(r9, r8, r15, r4)
            goto L_0x050b
        L_0x024c:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzf(r9, r8, r15, r4)
            goto L_0x050b
        L_0x025d:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzh(r9, r8, r15, r4)
            goto L_0x050b
        L_0x026e:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzd(r9, r8, r15, r4)
            goto L_0x050b
        L_0x027f:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzc(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0290:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzb(r9, r8, r15, r4)
            goto L_0x050b
        L_0x02a1:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zza(r9, r8, r15, r4)
            goto L_0x050b
        L_0x02b2:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zze(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02c3:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzj(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02d4:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzg(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02e5:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzl(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02f6:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzm(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0307:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzi(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0318:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzb(r9, r8, r15)
            goto L_0x050b
        L_0x0329:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzff r10 = r13.zzae(r7)
            com.google.android.gms.internal.icing.zzfh.zza(r9, r8, r15, r10)
            goto L_0x050b
        L_0x033e:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zza(r9, r8, r15)
            goto L_0x050b
        L_0x034f:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzn(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0360:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzk(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0371:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzf(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0382:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzh(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0393:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzd(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03a4:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzc(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03b5:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zzb(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03c6:
            int[] r9 = r13.zzmf
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfh.zza(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03d7:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzff r10 = r13.zzae(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050b
        L_0x03ec:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x050b
        L_0x03fd:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050b
        L_0x040e:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050b
        L_0x041f:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050b
        L_0x0430:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x050b
        L_0x0441:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x050b
        L_0x0452:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzce r8 = (com.google.android.gms.internal.icing.zzce) r8
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x0465:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzff r10 = r13.zzae(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050b
        L_0x047a:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050b
        L_0x048b:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.icing.zzgd.zzl(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x049c:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050b
        L_0x04ac:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050b
        L_0x04bc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r10)
            r15.zzc(r9, r8)
            goto L_0x050b
        L_0x04cc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050b
        L_0x04dc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050b
        L_0x04ec:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.icing.zzgd.zzm(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x04fc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.icing.zzgd.zzn(r14, r10)
            r15.zza(r9, r10)
        L_0x050b:
            int r7 = r7 + -3
            goto L_0x0037
        L_0x050f:
            if (r1 == 0) goto L_0x0526
            com.google.android.gms.internal.icing.zzcz<?> r14 = r13.zzmu
            r14.zza(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x0524
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x050f
        L_0x0524:
            r1 = r3
            goto L_0x050f
        L_0x0526:
            return
        L_0x0527:
            boolean r0 = r13.zzmm
            if (r0 == 0) goto L_0x0a42
            boolean r0 = r13.zzmk
            if (r0 == 0) goto L_0x0546
            com.google.android.gms.internal.icing.zzcz<?> r0 = r13.zzmu
            com.google.android.gms.internal.icing.zzdc r0 = r0.zzc(r14)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0546
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0548
        L_0x0546:
            r0 = r3
            r1 = r0
        L_0x0548:
            int[] r7 = r13.zzmf
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x054d:
            if (r1 >= r7) goto L_0x0a25
            int r9 = r13.zzag(r1)
            int[] r10 = r13.zzmf
            r10 = r10[r1]
        L_0x0557:
            if (r8 == 0) goto L_0x0575
            com.google.android.gms.internal.icing.zzcz<?> r11 = r13.zzmu
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x0575
            com.google.android.gms.internal.icing.zzcz<?> r11 = r13.zzmu
            r11.zza(r15, r8)
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x0573
            java.lang.Object r8 = r0.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x0557
        L_0x0573:
            r8 = r3
            goto L_0x0557
        L_0x0575:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0a12;
                case 1: goto L_0x0a02;
                case 2: goto L_0x09f2;
                case 3: goto L_0x09e2;
                case 4: goto L_0x09d2;
                case 5: goto L_0x09c2;
                case 6: goto L_0x09b2;
                case 7: goto L_0x09a1;
                case 8: goto L_0x0990;
                case 9: goto L_0x097b;
                case 10: goto L_0x0968;
                case 11: goto L_0x0957;
                case 12: goto L_0x0946;
                case 13: goto L_0x0935;
                case 14: goto L_0x0924;
                case 15: goto L_0x0913;
                case 16: goto L_0x0902;
                case 17: goto L_0x08ed;
                case 18: goto L_0x08dc;
                case 19: goto L_0x08cb;
                case 20: goto L_0x08ba;
                case 21: goto L_0x08a9;
                case 22: goto L_0x0898;
                case 23: goto L_0x0887;
                case 24: goto L_0x0876;
                case 25: goto L_0x0865;
                case 26: goto L_0x0854;
                case 27: goto L_0x083f;
                case 28: goto L_0x082e;
                case 29: goto L_0x081d;
                case 30: goto L_0x080c;
                case 31: goto L_0x07fb;
                case 32: goto L_0x07ea;
                case 33: goto L_0x07d9;
                case 34: goto L_0x07c8;
                case 35: goto L_0x07b7;
                case 36: goto L_0x07a6;
                case 37: goto L_0x0795;
                case 38: goto L_0x0784;
                case 39: goto L_0x0773;
                case 40: goto L_0x0762;
                case 41: goto L_0x0751;
                case 42: goto L_0x0740;
                case 43: goto L_0x072f;
                case 44: goto L_0x071e;
                case 45: goto L_0x070d;
                case 46: goto L_0x06fc;
                case 47: goto L_0x06eb;
                case 48: goto L_0x06da;
                case 49: goto L_0x06c5;
                case 50: goto L_0x06ba;
                case 51: goto L_0x06a9;
                case 52: goto L_0x0698;
                case 53: goto L_0x0687;
                case 54: goto L_0x0676;
                case 55: goto L_0x0665;
                case 56: goto L_0x0654;
                case 57: goto L_0x0643;
                case 58: goto L_0x0632;
                case 59: goto L_0x0621;
                case 60: goto L_0x060c;
                case 61: goto L_0x05f9;
                case 62: goto L_0x05e8;
                case 63: goto L_0x05d7;
                case 64: goto L_0x05c6;
                case 65: goto L_0x05b5;
                case 66: goto L_0x05a4;
                case 67: goto L_0x0593;
                case 68: goto L_0x057e;
                default: goto L_0x057c;
            }
        L_0x057c:
            goto L_0x0a21
        L_0x057e:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzff r11 = r13.zzae(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a21
        L_0x0593:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0a21
        L_0x05a4:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a21
        L_0x05b5:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a21
        L_0x05c6:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a21
        L_0x05d7:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0a21
        L_0x05e8:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0a21
        L_0x05f9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzce r9 = (com.google.android.gms.internal.icing.zzce) r9
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x060c:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzff r11 = r13.zzae(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a21
        L_0x0621:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a21
        L_0x0632:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzi(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x0643:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a21
        L_0x0654:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a21
        L_0x0665:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzc(r10, r9)
            goto L_0x0a21
        L_0x0676:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a21
        L_0x0687:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a21
        L_0x0698:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzf(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x06a9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zze(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a21
        L_0x06ba:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0a21
        L_0x06c5:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzff r11 = r13.zzae(r1)
            com.google.android.gms.internal.icing.zzfh.zzb(r10, r9, r15, r11)
            goto L_0x0a21
        L_0x06da:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zze(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x06eb:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzj(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x06fc:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzg(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x070d:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzl(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x071e:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzm(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x072f:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzi(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0740:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzn(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0751:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzk(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0762:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzf(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0773:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzh(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0784:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzd(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0795:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzc(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07a6:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzb(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07b7:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zza(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07c8:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zze(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07d9:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzj(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07ea:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzg(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07fb:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzl(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x080c:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzm(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x081d:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzi(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x082e:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzb(r10, r9, r15)
            goto L_0x0a21
        L_0x083f:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzff r11 = r13.zzae(r1)
            com.google.android.gms.internal.icing.zzfh.zza(r10, r9, r15, r11)
            goto L_0x0a21
        L_0x0854:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zza(r10, r9, r15)
            goto L_0x0a21
        L_0x0865:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzn(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0876:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzk(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0887:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzf(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0898:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzh(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08a9:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzd(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08ba:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzc(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08cb:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzb(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08dc:
            int[] r10 = r13.zzmf
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zza(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08ed:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzff r11 = r13.zzae(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a21
        L_0x0902:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0a21
        L_0x0913:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a21
        L_0x0924:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a21
        L_0x0935:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a21
        L_0x0946:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0a21
        L_0x0957:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0a21
        L_0x0968:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzce r9 = (com.google.android.gms.internal.icing.zzce) r9
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x097b:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzff r11 = r13.zzae(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a21
        L_0x0990:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a21
        L_0x09a1:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.icing.zzgd.zzl(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x09b2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a21
        L_0x09c2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a21
        L_0x09d2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgd.zzj(r14, r11)
            r15.zzc(r10, r9)
            goto L_0x0a21
        L_0x09e2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a21
        L_0x09f2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgd.zzk(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a21
        L_0x0a02:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.icing.zzgd.zzm(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x0a12:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.icing.zzgd.zzn(r14, r11)
            r15.zza(r10, r11)
        L_0x0a21:
            int r1 = r1 + 3
            goto L_0x054d
        L_0x0a25:
            if (r8 == 0) goto L_0x0a3c
            com.google.android.gms.internal.icing.zzcz<?> r1 = r13.zzmu
            r1.zza(r15, r8)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0a3a
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r8 = r1
            goto L_0x0a25
        L_0x0a3a:
            r8 = r3
            goto L_0x0a25
        L_0x0a3c:
            com.google.android.gms.internal.icing.zzfx<?, ?> r0 = r13.zzmt
            zza(r0, (T) r14, r15)
            return
        L_0x0a42:
            r13.zzb((T) r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzeu.zza(java.lang.Object, com.google.android.gms.internal.icing.zzgr):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:190:0x051c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r19, com.google.android.gms.internal.icing.zzgr r20) throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r0.zzmk
            if (r3 == 0) goto L_0x0021
            com.google.android.gms.internal.icing.zzcz<?> r3 = r0.zzmu
            com.google.android.gms.internal.icing.zzdc r3 = r3.zzc(r1)
            boolean r5 = r3.isEmpty()
            if (r5 != 0) goto L_0x0021
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0023
        L_0x0021:
            r3 = 0
            r5 = 0
        L_0x0023:
            r6 = -1
            int[] r7 = r0.zzmf
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzme
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002c:
            if (r5 >= r7) goto L_0x0516
            int r12 = r0.zzag(r5)
            int[] r13 = r0.zzmf
            r14 = r13[r5]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r15 = r15 & r12
            int r15 = r15 >>> 20
            boolean r4 = r0.zzmm
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r4 != 0) goto L_0x0062
            r4 = 17
            if (r15 > r4) goto L_0x0062
            int r4 = r5 + 2
            r4 = r13[r4]
            r13 = r4 & r16
            if (r13 == r6) goto L_0x0056
            r17 = r10
            long r9 = (long) r13
            int r11 = r8.getInt(r1, r9)
            goto L_0x0059
        L_0x0056:
            r17 = r10
            r13 = r6
        L_0x0059:
            int r4 = r4 >>> 20
            r6 = 1
            int r9 = r6 << r4
            r6 = r13
            r10 = r17
            goto L_0x0067
        L_0x0062:
            r17 = r10
            r10 = r17
            r9 = 0
        L_0x0067:
            if (r10 == 0) goto L_0x0086
            com.google.android.gms.internal.icing.zzcz<?> r4 = r0.zzmu
            int r4 = r4.zza(r10)
            if (r4 > r14) goto L_0x0086
            com.google.android.gms.internal.icing.zzcz<?> r4 = r0.zzmu
            r4.zza(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0084
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x0067
        L_0x0084:
            r10 = 0
            goto L_0x0067
        L_0x0086:
            r4 = r12 & r16
            long r12 = (long) r4
            switch(r15) {
                case 0: goto L_0x0506;
                case 1: goto L_0x04f9;
                case 2: goto L_0x04ec;
                case 3: goto L_0x04df;
                case 4: goto L_0x04d2;
                case 5: goto L_0x04c5;
                case 6: goto L_0x04b8;
                case 7: goto L_0x04ab;
                case 8: goto L_0x049d;
                case 9: goto L_0x048b;
                case 10: goto L_0x047b;
                case 11: goto L_0x046d;
                case 12: goto L_0x045f;
                case 13: goto L_0x0451;
                case 14: goto L_0x0443;
                case 15: goto L_0x0435;
                case 16: goto L_0x0427;
                case 17: goto L_0x0415;
                case 18: goto L_0x0405;
                case 19: goto L_0x03f5;
                case 20: goto L_0x03e5;
                case 21: goto L_0x03d5;
                case 22: goto L_0x03c5;
                case 23: goto L_0x03b5;
                case 24: goto L_0x03a5;
                case 25: goto L_0x0395;
                case 26: goto L_0x0385;
                case 27: goto L_0x0371;
                case 28: goto L_0x0361;
                case 29: goto L_0x0350;
                case 30: goto L_0x033f;
                case 31: goto L_0x032e;
                case 32: goto L_0x031d;
                case 33: goto L_0x030c;
                case 34: goto L_0x02fb;
                case 35: goto L_0x02ea;
                case 36: goto L_0x02d9;
                case 37: goto L_0x02c8;
                case 38: goto L_0x02b7;
                case 39: goto L_0x02a6;
                case 40: goto L_0x0295;
                case 41: goto L_0x0284;
                case 42: goto L_0x0273;
                case 43: goto L_0x0262;
                case 44: goto L_0x0251;
                case 45: goto L_0x0240;
                case 46: goto L_0x022f;
                case 47: goto L_0x021e;
                case 48: goto L_0x020d;
                case 49: goto L_0x01f9;
                case 50: goto L_0x01ef;
                case 51: goto L_0x01dc;
                case 52: goto L_0x01c9;
                case 53: goto L_0x01b6;
                case 54: goto L_0x01a3;
                case 55: goto L_0x0190;
                case 56: goto L_0x017d;
                case 57: goto L_0x016a;
                case 58: goto L_0x0157;
                case 59: goto L_0x0144;
                case 60: goto L_0x012d;
                case 61: goto L_0x0118;
                case 62: goto L_0x0105;
                case 63: goto L_0x00f2;
                case 64: goto L_0x00df;
                case 65: goto L_0x00cc;
                case 66: goto L_0x00b9;
                case 67: goto L_0x00a6;
                case 68: goto L_0x008f;
                default: goto L_0x008c;
            }
        L_0x008c:
            r15 = 0
            goto L_0x0512
        L_0x008f:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00a3
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.icing.zzff r9 = r0.zzae(r5)
            r2.zzb(r14, r4, r9)
            r15 = 0
            goto L_0x0512
        L_0x00a3:
            r15 = 0
            goto L_0x0512
        L_0x00a6:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00b6
            long r12 = zzh(r1, r12)
            r2.zzb(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x00b6:
            r15 = 0
            goto L_0x0512
        L_0x00b9:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00c9
            int r4 = zzg(r1, r12)
            r2.zze(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x00c9:
            r15 = 0
            goto L_0x0512
        L_0x00cc:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00dc
            long r12 = zzh(r1, r12)
            r2.zzj(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x00dc:
            r15 = 0
            goto L_0x0512
        L_0x00df:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00ef
            int r4 = zzg(r1, r12)
            r2.zzm(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x00ef:
            r15 = 0
            goto L_0x0512
        L_0x00f2:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0102
            int r4 = zzg(r1, r12)
            r2.zzn(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x0102:
            r15 = 0
            goto L_0x0512
        L_0x0105:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0115
            int r4 = zzg(r1, r12)
            r2.zzd(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x0115:
            r15 = 0
            goto L_0x0512
        L_0x0118:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x012a
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.icing.zzce r4 = (com.google.android.gms.internal.icing.zzce) r4
            r2.zza(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x012a:
            r15 = 0
            goto L_0x0512
        L_0x012d:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0141
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.icing.zzff r9 = r0.zzae(r5)
            r2.zza(r14, r4, r9)
            r15 = 0
            goto L_0x0512
        L_0x0141:
            r15 = 0
            goto L_0x0512
        L_0x0144:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0154
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza(r14, r4, r2)
            r15 = 0
            goto L_0x0512
        L_0x0154:
            r15 = 0
            goto L_0x0512
        L_0x0157:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0167
            boolean r4 = zzi(r1, r12)
            r2.zza(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x0167:
            r15 = 0
            goto L_0x0512
        L_0x016a:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x017a
            int r4 = zzg(r1, r12)
            r2.zzf(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x017a:
            r15 = 0
            goto L_0x0512
        L_0x017d:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x018d
            long r12 = zzh(r1, r12)
            r2.zzc(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x018d:
            r15 = 0
            goto L_0x0512
        L_0x0190:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01a0
            int r4 = zzg(r1, r12)
            r2.zzc(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x01a0:
            r15 = 0
            goto L_0x0512
        L_0x01a3:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01b3
            long r12 = zzh(r1, r12)
            r2.zza(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x01b3:
            r15 = 0
            goto L_0x0512
        L_0x01b6:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01c6
            long r12 = zzh(r1, r12)
            r2.zzi(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x01c6:
            r15 = 0
            goto L_0x0512
        L_0x01c9:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01d9
            float r4 = zzf(r1, r12)
            r2.zza(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x01d9:
            r15 = 0
            goto L_0x0512
        L_0x01dc:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01ec
            double r12 = zze(r1, r12)
            r2.zza(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x01ec:
            r15 = 0
            goto L_0x0512
        L_0x01ef:
            java.lang.Object r4 = r8.getObject(r1, r12)
            r0.zza(r2, r14, r4, r5)
            r15 = 0
            goto L_0x0512
        L_0x01f9:
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzff r12 = r0.zzae(r5)
            com.google.android.gms.internal.icing.zzfh.zzb(r4, r9, r2, r12)
            r15 = 0
            goto L_0x0512
        L_0x020d:
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 1
            com.google.android.gms.internal.icing.zzfh.zze(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x021e:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzj(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x022f:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzg(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0240:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzl(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0251:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzm(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0262:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzi(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0273:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzn(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0284:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzk(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0295:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzf(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02a6:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzh(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02b7:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzd(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02c8:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzc(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02d9:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzb(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02ea:
            r14 = 1
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zza(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02fb:
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 0
            com.google.android.gms.internal.icing.zzfh.zze(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x030c:
            r14 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzj(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x031d:
            r14 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzg(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x032e:
            r14 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzl(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x033f:
            r14 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzm(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0350:
            r14 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzi(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0361:
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzb(r4, r9, r2)
            r15 = 0
            goto L_0x0512
        L_0x0371:
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzff r12 = r0.zzae(r5)
            com.google.android.gms.internal.icing.zzfh.zza(r4, r9, r2, r12)
            r15 = 0
            goto L_0x0512
        L_0x0385:
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zza(r4, r9, r2)
            r15 = 0
            goto L_0x0512
        L_0x0395:
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r15 = 0
            com.google.android.gms.internal.icing.zzfh.zzn(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03a5:
            r15 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzk(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03b5:
            r15 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzf(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03c5:
            r15 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzh(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03d5:
            r15 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzd(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03e5:
            r15 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzc(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03f5:
            r15 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zzb(r4, r9, r2, r15)
            goto L_0x0512
        L_0x0405:
            r15 = 0
            int[] r4 = r0.zzmf
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfh.zza(r4, r9, r2, r15)
            goto L_0x0512
        L_0x0415:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.icing.zzff r9 = r0.zzae(r5)
            r2.zzb(r14, r4, r9)
            goto L_0x0512
        L_0x0427:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zzb(r14, r12)
            goto L_0x0512
        L_0x0435:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zze(r14, r4)
            goto L_0x0512
        L_0x0443:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x0512
        L_0x0451:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzm(r14, r4)
            goto L_0x0512
        L_0x045f:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzn(r14, r4)
            goto L_0x0512
        L_0x046d:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzd(r14, r4)
            goto L_0x0512
        L_0x047b:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.icing.zzce r4 = (com.google.android.gms.internal.icing.zzce) r4
            r2.zza(r14, r4)
            goto L_0x0512
        L_0x048b:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.icing.zzff r9 = r0.zzae(r5)
            r2.zza(r14, r4, r9)
            goto L_0x0512
        L_0x049d:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza(r14, r4, r2)
            goto L_0x0512
        L_0x04ab:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            boolean r4 = com.google.android.gms.internal.icing.zzgd.zzl(r1, r12)
            r2.zza(r14, r4)
            goto L_0x0512
        L_0x04b8:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x0512
        L_0x04c5:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zzc(r14, r12)
            goto L_0x0512
        L_0x04d2:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzc(r14, r4)
            goto L_0x0512
        L_0x04df:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zza(r14, r12)
            goto L_0x0512
        L_0x04ec:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zzi(r14, r12)
            goto L_0x0512
        L_0x04f9:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            float r4 = com.google.android.gms.internal.icing.zzgd.zzm(r1, r12)
            r2.zza(r14, r4)
            goto L_0x0512
        L_0x0506:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            double r12 = com.google.android.gms.internal.icing.zzgd.zzn(r1, r12)
            r2.zza(r14, r12)
        L_0x0512:
            int r5 = r5 + 3
            goto L_0x002c
        L_0x0516:
            r17 = r10
            r4 = r17
        L_0x051a:
            if (r4 == 0) goto L_0x0530
            com.google.android.gms.internal.icing.zzcz<?> r5 = r0.zzmu
            r5.zza(r2, r4)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x052e
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x051a
        L_0x052e:
            r4 = 0
            goto L_0x051a
        L_0x0530:
            com.google.android.gms.internal.icing.zzfx<?, ?> r3 = r0.zzmt
            zza(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzeu.zzb(java.lang.Object, com.google.android.gms.internal.icing.zzgr):void");
    }

    private final <K, V> void zza(zzgr zzgr, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzgr.zza(i, this.zzmv.zzk(zzaf(i2)), this.zzmv.zzi(obj));
        }
    }

    private static <UT, UB> void zza(zzfx<UT, UB> zzfx, T t, zzgr zzgr) throws IOException {
        zzfx.zza(zzfx.zzo(t), zzgr);
    }

    private final zzff zzae(int i) {
        int i2 = (i / 3) << 1;
        zzff zzff = (zzff) this.zzmg[i2];
        if (zzff != null) {
            return zzff;
        }
        zzff zze = zzfc.zzcy().zze((Class) this.zzmg[i2 + 1]);
        this.zzmg[i2] = zze;
        return zze;
    }

    private final Object zzaf(int i) {
        return this.zzmg[(i / 3) << 1];
    }

    public final void zze(T t) {
        int i;
        int i2 = this.zzmp;
        while (true) {
            i = this.zzmq;
            if (i2 >= i) {
                break;
            }
            long zzag = (long) (zzag(this.zzmo[i2]) & 1048575);
            Object zzo = zzgd.zzo(t, zzag);
            if (zzo != null) {
                zzgd.zza((Object) t, zzag, this.zzmv.zzj(zzo));
            }
            i2++;
        }
        int length = this.zzmo.length;
        while (i < length) {
            this.zzms.zza(t, (long) this.zzmo[i]);
            i++;
        }
        this.zzmt.zze(t);
        if (this.zzmk) {
            this.zzmu.zze((Object) t);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0104, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzm(T r14) {
        /*
            r13 = this;
            r0 = 0
            r1 = -1
            r1 = 0
            r2 = -1
            r3 = 0
        L_0x0005:
            int r4 = r13.zzmp
            r5 = 1
            if (r1 >= r4) goto L_0x0108
            int[] r4 = r13.zzmo
            r4 = r4[r1]
            int[] r6 = r13.zzmf
            r6 = r6[r4]
            int r7 = r13.zzag(r4)
            boolean r8 = r13.zzmm
            r9 = 1048575(0xfffff, float:1.469367E-39)
            if (r8 != 0) goto L_0x0035
            int[] r8 = r13.zzmf
            int r10 = r4 + 2
            r8 = r8[r10]
            r10 = r8 & r9
            int r8 = r8 >>> 20
            int r8 = r5 << r8
            if (r10 == r2) goto L_0x0036
            sun.misc.Unsafe r2 = zzme
            long r11 = (long) r10
            int r2 = r2.getInt(r14, r11)
            r3 = r2
            r2 = r10
            goto L_0x0036
        L_0x0035:
            r8 = 0
        L_0x0036:
            r10 = 268435456(0x10000000, float:2.5243549E-29)
            r10 = r10 & r7
            if (r10 == 0) goto L_0x003d
            r10 = 1
            goto L_0x003e
        L_0x003d:
            r10 = 0
        L_0x003e:
            if (r10 == 0) goto L_0x0047
            boolean r10 = r13.zza((T) r14, r4, r3, r8)
            if (r10 != 0) goto L_0x0047
            return r0
        L_0x0047:
            r10 = 267386880(0xff00000, float:2.3665827E-29)
            r10 = r10 & r7
            int r10 = r10 >>> 20
            r11 = 9
            if (r10 == r11) goto L_0x00f3
            r11 = 17
            if (r10 == r11) goto L_0x00f3
            r8 = 27
            if (r10 == r8) goto L_0x00c7
            r8 = 60
            if (r10 == r8) goto L_0x00b6
            r8 = 68
            if (r10 == r8) goto L_0x00b6
            switch(r10) {
                case 49: goto L_0x00c7;
                case 50: goto L_0x0065;
                default: goto L_0x0063;
            }
        L_0x0063:
            goto L_0x0104
        L_0x0065:
            com.google.android.gms.internal.icing.zzel r6 = r13.zzmv
            r7 = r7 & r9
            long r7 = (long) r7
            java.lang.Object r7 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r7)
            java.util.Map r6 = r6.zzi(r7)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00b3
            java.lang.Object r4 = r13.zzaf(r4)
            com.google.android.gms.internal.icing.zzel r7 = r13.zzmv
            com.google.android.gms.internal.icing.zzej r4 = r7.zzk(r4)
            com.google.android.gms.internal.icing.zzgl r4 = r4.zzlz
            com.google.android.gms.internal.icing.zzgq r4 = r4.zzdx()
            com.google.android.gms.internal.icing.zzgq r7 = com.google.android.gms.internal.icing.zzgq.MESSAGE
            if (r4 != r7) goto L_0x00b3
            r4 = 0
            java.util.Collection r6 = r6.values()
            java.util.Iterator r6 = r6.iterator()
        L_0x0094:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00b3
            java.lang.Object r7 = r6.next()
            if (r4 != 0) goto L_0x00ac
            com.google.android.gms.internal.icing.zzfc r4 = com.google.android.gms.internal.icing.zzfc.zzcy()
            java.lang.Class r8 = r7.getClass()
            com.google.android.gms.internal.icing.zzff r4 = r4.zze(r8)
        L_0x00ac:
            boolean r7 = r4.zzm(r7)
            if (r7 != 0) goto L_0x0094
            r5 = 0
        L_0x00b3:
            if (r5 != 0) goto L_0x0104
            return r0
        L_0x00b6:
            boolean r5 = r13.zza((T) r14, r6, r4)
            if (r5 == 0) goto L_0x0104
            com.google.android.gms.internal.icing.zzff r4 = r13.zzae(r4)
            boolean r4 = zza(r14, r7, r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x00c7:
            r6 = r7 & r9
            long r6 = (long) r6
            java.lang.Object r6 = com.google.android.gms.internal.icing.zzgd.zzo(r14, r6)
            java.util.List r6 = (java.util.List) r6
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00f0
            com.google.android.gms.internal.icing.zzff r4 = r13.zzae(r4)
            r7 = 0
        L_0x00db:
            int r8 = r6.size()
            if (r7 >= r8) goto L_0x00f0
            java.lang.Object r8 = r6.get(r7)
            boolean r8 = r4.zzm(r8)
            if (r8 != 0) goto L_0x00ed
            r5 = 0
            goto L_0x00f0
        L_0x00ed:
            int r7 = r7 + 1
            goto L_0x00db
        L_0x00f0:
            if (r5 != 0) goto L_0x0104
            return r0
        L_0x00f3:
            boolean r5 = r13.zza((T) r14, r4, r3, r8)
            if (r5 == 0) goto L_0x0104
            com.google.android.gms.internal.icing.zzff r4 = r13.zzae(r4)
            boolean r4 = zza(r14, r7, r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x0104:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0108:
            boolean r1 = r13.zzmk
            if (r1 == 0) goto L_0x0119
            com.google.android.gms.internal.icing.zzcz<?> r1 = r13.zzmu
            com.google.android.gms.internal.icing.zzdc r14 = r1.zzc(r14)
            boolean r14 = r14.isInitialized()
            if (r14 != 0) goto L_0x0119
            return r0
        L_0x0119:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzeu.zzm(java.lang.Object):boolean");
    }

    private static boolean zza(Object obj, int i, zzff zzff) {
        return zzff.zzm(zzgd.zzo(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzgr zzgr) throws IOException {
        if (obj instanceof String) {
            zzgr.zza(i, (String) obj);
        } else {
            zzgr.zza(i, (zzce) obj);
        }
    }

    private final int zzag(int i) {
        return this.zzmf[i + 1];
    }

    private final int zzah(int i) {
        return this.zzmf[i + 2];
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zzgd.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zzgd.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zzgd.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zzgd.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zzgd.zzo(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzmm) {
            return zza(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzmm) {
            int zzag = zzag(i);
            long j = (long) (zzag & 1048575);
            switch ((zzag & 267386880) >>> 20) {
                case 0:
                    return zzgd.zzn(t, j) != 0.0d;
                case 1:
                    return zzgd.zzm(t, j) != BitmapDescriptorFactory.HUE_RED;
                case 2:
                    return zzgd.zzk(t, j) != 0;
                case 3:
                    return zzgd.zzk(t, j) != 0;
                case 4:
                    return zzgd.zzj(t, j) != 0;
                case 5:
                    return zzgd.zzk(t, j) != 0;
                case 6:
                    return zzgd.zzj(t, j) != 0;
                case 7:
                    return zzgd.zzl(t, j);
                case 8:
                    Object zzo = zzgd.zzo(t, j);
                    if (zzo instanceof String) {
                        return !((String) zzo).isEmpty();
                    }
                    if (zzo instanceof zzce) {
                        return !zzce.zzfx.equals(zzo);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzgd.zzo(t, j) != null;
                case 10:
                    return !zzce.zzfx.equals(zzgd.zzo(t, j));
                case 11:
                    return zzgd.zzj(t, j) != 0;
                case 12:
                    return zzgd.zzj(t, j) != 0;
                case 13:
                    return zzgd.zzj(t, j) != 0;
                case 14:
                    return zzgd.zzk(t, j) != 0;
                case 15:
                    return zzgd.zzj(t, j) != 0;
                case 16:
                    return zzgd.zzk(t, j) != 0;
                case 17:
                    return zzgd.zzo(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzah = zzah(i);
            return (zzgd.zzj(t, (long) (zzah & 1048575)) & (1 << (zzah >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        if (!this.zzmm) {
            int zzah = zzah(i);
            long j = (long) (zzah & 1048575);
            zzgd.zza((Object) t, j, zzgd.zzj(t, j) | (1 << (zzah >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzgd.zzj(t, (long) (zzah(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzgd.zza((Object) t, (long) (zzah(i2) & 1048575), i);
    }
}
