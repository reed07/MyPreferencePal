package com.google.android.gms.internal.ads;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

final class zzbsp<T> implements zzbtc<T> {
    private static final int[] zzfsg = new int[0];
    private static final Unsafe zzfsh = zzbua.zzape();
    private final int[] zzfsi;
    private final Object[] zzfsj;
    private final int zzfsk;
    private final int zzfsl;
    private final zzbsl zzfsm;
    private final boolean zzfsn;
    private final boolean zzfso;
    private final boolean zzfsp;
    private final boolean zzfsq;
    private final int[] zzfsr;
    private final int zzfss;
    private final int zzfst;
    private final zzbst zzfsu;
    private final zzbrv zzfsv;
    private final zzbtu<?, ?> zzfsw;
    private final zzbqr<?> zzfsx;
    private final zzbsg zzfsy;

    private zzbsp(int[] iArr, Object[] objArr, int i, int i2, zzbsl zzbsl, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzbst zzbst, zzbrv zzbrv, zzbtu<?, ?> zzbtu, zzbqr<?> zzbqr, zzbsg zzbsg) {
        this.zzfsi = iArr;
        this.zzfsj = objArr;
        this.zzfsk = i;
        this.zzfsl = i2;
        this.zzfso = zzbsl instanceof zzbrd;
        this.zzfsp = z;
        this.zzfsn = zzbqr != null && zzbqr.zzh(zzbsl);
        this.zzfsq = false;
        this.zzfsr = iArr2;
        this.zzfss = i3;
        this.zzfst = i4;
        this.zzfsu = zzbst;
        this.zzfsv = zzbrv;
        this.zzfsw = zzbtu;
        this.zzfsx = zzbqr;
        this.zzfsm = zzbsl;
        this.zzfsy = zzbsg;
    }

    private static boolean zzfv(int i) {
        return (i & 536870912) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:132:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x02dc  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x02df  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x03c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.ads.zzbsp<T> zza(java.lang.Class<T> r33, com.google.android.gms.internal.ads.zzbsj r34, com.google.android.gms.internal.ads.zzbst r35, com.google.android.gms.internal.ads.zzbrv r36, com.google.android.gms.internal.ads.zzbtu<?, ?> r37, com.google.android.gms.internal.ads.zzbqr<?> r38, com.google.android.gms.internal.ads.zzbsg r39) {
        /*
            r0 = r34
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzbta
            if (r1 == 0) goto L_0x049a
            com.google.android.gms.internal.ads.zzbta r0 = (com.google.android.gms.internal.ads.zzbta) r0
            int r1 = r0.zzanz()
            int r2 = com.google.android.gms.internal.ads.zzbrd.zze.zzfqk
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0014
            r11 = 1
            goto L_0x0015
        L_0x0014:
            r11 = 0
        L_0x0015:
            java.lang.String r1 = r0.zzaoi()
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
            int[] r8 = zzfsg
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
            r32 = r16
            r16 = r15
            r15 = r32
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
            r32 = r16
            r16 = r3
            r3 = r32
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
            r32 = r16
            r16 = r9
            r9 = r12
            r12 = r32
        L_0x01a0:
            sun.misc.Unsafe r6 = zzfsh
            java.lang.Object[] r17 = r0.zzaoj()
            com.google.android.gms.internal.ads.zzbsl r18 = r0.zzaob()
            java.lang.Class r7 = r18.getClass()
            r18 = r10
            int r10 = r14 * 3
            int[] r10 = new int[r10]
            int r14 = r14 << r4
            java.lang.Object[] r14 = new java.lang.Object[r14]
            int r20 = r3 + r8
            r23 = r3
            r21 = r18
            r22 = r20
            r8 = 0
            r18 = 0
        L_0x01c2:
            if (r12 >= r2) goto L_0x0470
            int r24 = r12 + 1
            char r12 = r1.charAt(r12)
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r12 < r4) goto L_0x01f6
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
            r32 = r24
            r24 = r12
            r12 = r32
        L_0x01d9:
            int r27 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r4) goto L_0x01ef
            r4 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r26
            r24 = r24 | r4
            int r26 = r26 + 13
            r12 = r27
            r4 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01d9
        L_0x01ef:
            int r4 = r12 << r26
            r12 = r24 | r4
            r4 = r27
            goto L_0x01f8
        L_0x01f6:
            r4 = r24
        L_0x01f8:
            int r24 = r4 + 1
            char r4 = r1.charAt(r4)
            r26 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r2) goto L_0x022c
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r27 = 13
            r32 = r24
            r24 = r4
            r4 = r32
        L_0x020f:
            int r28 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r2) goto L_0x0225
            r2 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r27
            r24 = r24 | r2
            int r27 = r27 + 13
            r4 = r28
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x020f
        L_0x0225:
            int r2 = r4 << r27
            r4 = r24 | r2
            r2 = r28
            goto L_0x022e
        L_0x022c:
            r2 = r24
        L_0x022e:
            r24 = r3
            r3 = r4 & 255(0xff, float:3.57E-43)
            r27 = r11
            r11 = r4 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x023d
            int r11 = r8 + 1
            r15[r8] = r18
            r8 = r11
        L_0x023d:
            com.google.android.gms.internal.ads.zzbqx r11 = com.google.android.gms.internal.ads.zzbqx.MAP
            int r11 = r11.id()
            if (r3 <= r11) goto L_0x0302
            int r11 = r2 + 1
            char r2 = r1.charAt(r2)
            r28 = r8
            r8 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r8) goto L_0x0270
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r29 = 13
        L_0x0256:
            int r30 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r8) goto L_0x026b
            r8 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r29
            r2 = r2 | r8
            int r29 = r29 + 13
            r11 = r30
            r8 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0256
        L_0x026b:
            int r8 = r11 << r29
            r2 = r2 | r8
            r11 = r30
        L_0x0270:
            com.google.android.gms.internal.ads.zzbqx r8 = com.google.android.gms.internal.ads.zzbqx.MESSAGE
            int r8 = r8.id()
            int r8 = r8 + 51
            if (r3 == r8) goto L_0x02aa
            com.google.android.gms.internal.ads.zzbqx r8 = com.google.android.gms.internal.ads.zzbqx.GROUP
            int r8 = r8.id()
            int r8 = r8 + 51
            if (r3 != r8) goto L_0x0287
            r29 = r11
            goto L_0x02ac
        L_0x0287:
            com.google.android.gms.internal.ads.zzbqx r8 = com.google.android.gms.internal.ads.zzbqx.ENUM
            int r8 = r8.id()
            int r8 = r8 + 51
            if (r3 != r8) goto L_0x02a6
            r8 = r5 & 1
            r29 = r11
            r11 = 1
            if (r8 != r11) goto L_0x02a8
            int r8 = r18 / 3
            int r8 = r8 << r11
            int r8 = r8 + r11
            int r11 = r21 + 1
            r21 = r17[r21]
            r14[r8] = r21
            r21 = r11
            r11 = 1
            goto L_0x02b9
        L_0x02a6:
            r29 = r11
        L_0x02a8:
            r11 = 1
            goto L_0x02b9
        L_0x02aa:
            r29 = r11
        L_0x02ac:
            int r8 = r18 / 3
            r11 = 1
            int r8 = r8 << r11
            int r8 = r8 + r11
            int r25 = r21 + 1
            r21 = r17[r21]
            r14[r8] = r21
            r21 = r25
        L_0x02b9:
            int r2 = r2 << r11
            r8 = r17[r2]
            boolean r11 = r8 instanceof java.lang.reflect.Field
            if (r11 == 0) goto L_0x02c4
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            r11 = r9
            goto L_0x02cd
        L_0x02c4:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zza(r7, r8)
            r17[r2] = r8
            r11 = r9
        L_0x02cd:
            long r8 = r6.objectFieldOffset(r8)
            int r9 = (int) r8
            int r2 = r2 + 1
            r8 = r17[r2]
            r30 = r9
            boolean r9 = r8 instanceof java.lang.reflect.Field
            if (r9 == 0) goto L_0x02df
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x02e7
        L_0x02df:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zza(r7, r8)
            r17[r2] = r8
        L_0x02e7:
            long r8 = r6.objectFieldOffset(r8)
            int r2 = (int) r8
            r31 = r1
            r8 = r2
            r1 = r7
            r25 = r21
            r9 = r30
            r2 = 0
            r19 = 1
            r21 = r11
            r32 = r13
            r13 = r12
            r12 = r29
            r29 = r32
            goto L_0x042a
        L_0x0302:
            r28 = r8
            r11 = r9
            int r8 = r21 + 1
            r9 = r17[r21]
            java.lang.String r9 = (java.lang.String) r9
            java.lang.reflect.Field r9 = zza(r7, r9)
            com.google.android.gms.internal.ads.zzbqx r21 = com.google.android.gms.internal.ads.zzbqx.MESSAGE
            r29 = r13
            int r13 = r21.id()
            if (r3 == r13) goto L_0x03ad
            com.google.android.gms.internal.ads.zzbqx r13 = com.google.android.gms.internal.ads.zzbqx.GROUP
            int r13 = r13.id()
            if (r3 != r13) goto L_0x0323
            goto L_0x03ad
        L_0x0323:
            com.google.android.gms.internal.ads.zzbqx r13 = com.google.android.gms.internal.ads.zzbqx.MESSAGE_LIST
            int r13 = r13.id()
            if (r3 == r13) goto L_0x039c
            com.google.android.gms.internal.ads.zzbqx r13 = com.google.android.gms.internal.ads.zzbqx.GROUP_LIST
            int r13 = r13.id()
            if (r3 != r13) goto L_0x0338
            r21 = r11
            r11 = 1
            goto L_0x039f
        L_0x0338:
            com.google.android.gms.internal.ads.zzbqx r13 = com.google.android.gms.internal.ads.zzbqx.ENUM
            int r13 = r13.id()
            if (r3 == r13) goto L_0x0387
            com.google.android.gms.internal.ads.zzbqx r13 = com.google.android.gms.internal.ads.zzbqx.ENUM_LIST
            int r13 = r13.id()
            if (r3 == r13) goto L_0x0387
            com.google.android.gms.internal.ads.zzbqx r13 = com.google.android.gms.internal.ads.zzbqx.ENUM_LIST_PACKED
            int r13 = r13.id()
            if (r3 != r13) goto L_0x0351
            goto L_0x0387
        L_0x0351:
            com.google.android.gms.internal.ads.zzbqx r13 = com.google.android.gms.internal.ads.zzbqx.MAP
            int r13 = r13.id()
            if (r3 != r13) goto L_0x0383
            int r13 = r23 + 1
            r15[r23] = r18
            int r21 = r18 / 3
            r23 = 1
            int r21 = r21 << 1
            int r23 = r8 + 1
            r8 = r17[r8]
            r14[r21] = r8
            r8 = r4 & 2048(0x800, float:2.87E-42)
            if (r8 == 0) goto L_0x037b
            int r21 = r21 + 1
            int r8 = r23 + 1
            r23 = r17[r23]
            r14[r21] = r23
            r21 = r11
            r23 = r13
            r13 = r12
            goto L_0x03bb
        L_0x037b:
            r21 = r11
            r8 = r23
            r23 = r13
            r13 = r12
            goto L_0x03bb
        L_0x0383:
            r21 = r11
            r11 = 1
            goto L_0x03ba
        L_0x0387:
            r13 = r5 & 1
            r21 = r11
            r11 = 1
            if (r13 != r11) goto L_0x03ba
            int r13 = r18 / 3
            int r13 = r13 << r11
            int r13 = r13 + r11
            int r25 = r8 + 1
            r8 = r17[r8]
            r14[r13] = r8
            r13 = r12
            r8 = r25
            goto L_0x03bb
        L_0x039c:
            r21 = r11
            r11 = 1
        L_0x039f:
            int r13 = r18 / 3
            int r13 = r13 << r11
            int r13 = r13 + r11
            int r25 = r8 + 1
            r8 = r17[r8]
            r14[r13] = r8
            r13 = r12
            r8 = r25
            goto L_0x03bb
        L_0x03ad:
            r21 = r11
            r11 = 1
            int r13 = r18 / 3
            int r13 = r13 << r11
            int r13 = r13 + r11
            java.lang.Class r25 = r9.getType()
            r14[r13] = r25
        L_0x03ba:
            r13 = r12
        L_0x03bb:
            long r11 = r6.objectFieldOffset(r9)
            int r9 = (int) r11
            r11 = r5 & 1
            r12 = 1
            if (r11 != r12) goto L_0x0420
            com.google.android.gms.internal.ads.zzbqx r11 = com.google.android.gms.internal.ads.zzbqx.GROUP
            int r11 = r11.id()
            if (r3 > r11) goto L_0x0420
            int r11 = r2 + 1
            char r2 = r1.charAt(r2)
            r12 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r12) goto L_0x03f6
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r19 = 13
        L_0x03dc:
            int r30 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r12) goto L_0x03ee
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r19
            r2 = r2 | r11
            int r19 = r19 + 13
            r11 = r30
            goto L_0x03dc
        L_0x03ee:
            int r11 = r11 << r19
            r2 = r2 | r11
            r11 = r30
            r19 = 1
            goto L_0x03f8
        L_0x03f6:
            r19 = 1
        L_0x03f8:
            int r25 = r16 << 1
            int r30 = r2 / 32
            int r25 = r25 + r30
            r12 = r17[r25]
            r31 = r1
            boolean r1 = r12 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x040c
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
            r1 = r7
            r25 = r8
            goto L_0x0417
        L_0x040c:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zza(r7, r12)
            r17[r25] = r12
            r1 = r7
            r25 = r8
        L_0x0417:
            long r7 = r6.objectFieldOffset(r12)
            int r8 = (int) r7
            int r2 = r2 % 32
            r12 = r11
            goto L_0x042a
        L_0x0420:
            r31 = r1
            r1 = r7
            r25 = r8
            r19 = 1
            r12 = r2
            r2 = 0
            r8 = 0
        L_0x042a:
            r7 = 18
            if (r3 < r7) goto L_0x0438
            r7 = 49
            if (r3 > r7) goto L_0x0438
            int r7 = r22 + 1
            r15[r22] = r9
            r22 = r7
        L_0x0438:
            int r7 = r18 + 1
            r10[r18] = r13
            int r11 = r7 + 1
            r13 = r4 & 512(0x200, float:7.175E-43)
            if (r13 == 0) goto L_0x0445
            r13 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0446
        L_0x0445:
            r13 = 0
        L_0x0446:
            r4 = r4 & 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x044d
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x044e
        L_0x044d:
            r4 = 0
        L_0x044e:
            r4 = r4 | r13
            int r3 = r3 << 20
            r3 = r3 | r4
            r3 = r3 | r9
            r10[r7] = r3
            int r18 = r11 + 1
            int r2 = r2 << 20
            r2 = r2 | r8
            r10[r11] = r2
            r7 = r1
            r9 = r21
            r3 = r24
            r21 = r25
            r2 = r26
            r11 = r27
            r8 = r28
            r13 = r29
            r1 = r31
            r4 = 1
            goto L_0x01c2
        L_0x0470:
            r24 = r3
            r21 = r9
            r27 = r11
            r29 = r13
            com.google.android.gms.internal.ads.zzbsp r1 = new com.google.android.gms.internal.ads.zzbsp
            com.google.android.gms.internal.ads.zzbsl r0 = r0.zzaob()
            r12 = 0
            r5 = r1
            r6 = r10
            r7 = r14
            r8 = r21
            r9 = r29
            r10 = r0
            r13 = r15
            r14 = r24
            r15 = r20
            r16 = r35
            r17 = r36
            r18 = r37
            r19 = r38
            r20 = r39
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r1
        L_0x049a:
            com.google.android.gms.internal.ads.zzbtp r0 = (com.google.android.gms.internal.ads.zzbtp) r0
            r0.zzanz()
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Class, com.google.android.gms.internal.ads.zzbsj, com.google.android.gms.internal.ads.zzbst, com.google.android.gms.internal.ads.zzbrv, com.google.android.gms.internal.ads.zzbtu, com.google.android.gms.internal.ads.zzbqr, com.google.android.gms.internal.ads.zzbsg):com.google.android.gms.internal.ads.zzbsp");
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

    public final T newInstance() {
        return this.zzfsu.newInstance(this.zzfsm);
    }

    public final boolean equals(T t, T t2) {
        int length = this.zzfsi.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                int zzft = zzft(i);
                long j = (long) (zzft & 1048575);
                switch ((zzft & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || zzbua.zzl(t, j) != zzbua.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 1:
                        if (!zzc(t, t2, i) || zzbua.zzk(t, j) != zzbua.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 2:
                        if (!zzc(t, t2, i) || zzbua.zzl(t, j) != zzbua.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 3:
                        if (!zzc(t, t2, i) || zzbua.zzl(t, j) != zzbua.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 4:
                        if (!zzc(t, t2, i) || zzbua.zzk(t, j) != zzbua.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 5:
                        if (!zzc(t, t2, i) || zzbua.zzl(t, j) != zzbua.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 6:
                        if (!zzc(t, t2, i) || zzbua.zzk(t, j) != zzbua.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 7:
                        if (!zzc(t, t2, i) || zzbua.zzm(t, j) != zzbua.zzm(t2, j)) {
                            z = false;
                            break;
                        }
                    case 8:
                        if (!zzc(t, t2, i) || !zzbte.zze(zzbua.zzp(t, j), zzbua.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 9:
                        if (!zzc(t, t2, i) || !zzbte.zze(zzbua.zzp(t, j), zzbua.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 10:
                        if (!zzc(t, t2, i) || !zzbte.zze(zzbua.zzp(t, j), zzbua.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 11:
                        if (!zzc(t, t2, i) || zzbua.zzk(t, j) != zzbua.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 12:
                        if (!zzc(t, t2, i) || zzbua.zzk(t, j) != zzbua.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 13:
                        if (!zzc(t, t2, i) || zzbua.zzk(t, j) != zzbua.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 14:
                        if (!zzc(t, t2, i) || zzbua.zzl(t, j) != zzbua.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 15:
                        if (!zzc(t, t2, i) || zzbua.zzk(t, j) != zzbua.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 16:
                        if (!zzc(t, t2, i) || zzbua.zzl(t, j) != zzbua.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 17:
                        if (!zzc(t, t2, i) || !zzbte.zze(zzbua.zzp(t, j), zzbua.zzp(t2, j))) {
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
                        z = zzbte.zze(zzbua.zzp(t, j), zzbua.zzp(t2, j));
                        break;
                    case 50:
                        z = zzbte.zze(zzbua.zzp(t, j), zzbua.zzp(t2, j));
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
                        long zzfu = (long) (zzfu(i) & 1048575);
                        if (zzbua.zzk(t, zzfu) != zzbua.zzk(t2, zzfu) || !zzbte.zze(zzbua.zzp(t, j), zzbua.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                }
                if (!z) {
                    return false;
                }
                i += 3;
            } else if (!this.zzfsw.zzag(t).equals(this.zzfsw.zzag(t2))) {
                return false;
            } else {
                if (this.zzfsn) {
                    return this.zzfsx.zzq(t).equals(this.zzfsx.zzq(t2));
                }
                return true;
            }
        }
    }

    public final int hashCode(T t) {
        int length = this.zzfsi.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzft = zzft(i2);
            int i3 = this.zzfsi[i2];
            long j = (long) (1048575 & zzft);
            int i4 = 37;
            switch ((zzft & 267386880) >>> 20) {
                case 0:
                    i = (i * 53) + zzbrf.zzbi(Double.doubleToLongBits(zzbua.zzo(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzbua.zzn(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzbua.zzk(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzbua.zzk(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzbrf.zzbf(zzbua.zzm(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzbua.zzp(t, j)).hashCode();
                    break;
                case 9:
                    Object zzp = zzbua.zzp(t, j);
                    if (zzp != null) {
                        i4 = zzp.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzbua.zzp(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzbua.zzk(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzbua.zzk(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzbua.zzk(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzbua.zzk(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 17:
                    Object zzp2 = zzbua.zzp(t, j);
                    if (zzp2 != null) {
                        i4 = zzp2.hashCode();
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
                    i = (i * 53) + zzbua.zzp(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzbua.zzp(t, j).hashCode();
                    break;
                case 51:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbrf.zzbi(Double.doubleToLongBits(zzf(t, j)));
                        break;
                    }
                case 52:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzg(t, j));
                        break;
                    }
                case 53:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbrf.zzbi(zzi(t, j));
                        break;
                    }
                case 54:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbrf.zzbi(zzi(t, j));
                        break;
                    }
                case 55:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 56:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbrf.zzbi(zzi(t, j));
                        break;
                    }
                case 57:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 58:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbrf.zzbf(zzj(t, j));
                        break;
                    }
                case 59:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzbua.zzp(t, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbua.zzp(t, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbua.zzp(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 63:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 64:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 65:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbrf.zzbi(zzi(t, j));
                        break;
                    }
                case 66:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 67:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbrf.zzbi(zzi(t, j));
                        break;
                    }
                case 68:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbua.zzp(t, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzfsw.zzag(t).hashCode();
        return this.zzfsn ? (hashCode * 53) + this.zzfsx.zzq(t).hashCode() : hashCode;
    }

    public final void zzd(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzfsi.length; i += 3) {
                int zzft = zzft(i);
                long j = (long) (1048575 & zzft);
                int i2 = this.zzfsi[i];
                switch ((zzft & 267386880) >>> 20) {
                    case 0:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzo(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 1:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzn(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 2:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 3:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 4:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 5:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 6:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 7:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzm(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 8:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzp(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzp(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 11:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 12:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 13:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 14:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 15:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                            zze(t, i);
                            break;
                        }
                    case 16:
                        if (!zzd(t2, i)) {
                            break;
                        } else {
                            zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                            zze(t, i);
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
                        this.zzfsv.zza(t, t2, j);
                        break;
                    case 50:
                        zzbte.zza(this.zzfsy, t, t2, j);
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
                            zzbua.zza((Object) t, j, zzbua.zzp(t2, j));
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
                            zzbua.zza((Object) t, j, zzbua.zzp(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            if (!this.zzfsp) {
                zzbte.zza(this.zzfsw, t, t2);
                if (this.zzfsn) {
                    zzbte.zza(this.zzfsx, t, t2);
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private final void zza(T t, T t2, int i) {
        long zzft = (long) (zzft(i) & 1048575);
        if (zzd(t2, i)) {
            Object zzp = zzbua.zzp(t, zzft);
            Object zzp2 = zzbua.zzp(t2, zzft);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzbua.zza((Object) t, zzft, zzp2);
                    zze(t, i);
                }
                return;
            }
            zzbua.zza((Object) t, zzft, zzbrf.zzb(zzp, zzp2));
            zze(t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzft = zzft(i);
        int i2 = this.zzfsi[i];
        long j = (long) (zzft & 1048575);
        if (zza(t2, i2, i)) {
            Object zzp = zzbua.zzp(t, j);
            Object zzp2 = zzbua.zzp(t2, j);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzbua.zza((Object) t, j, zzp2);
                    zzb(t, i2, i);
                }
                return;
            }
            zzbua.zza((Object) t, j, zzbrf.zzb(zzp, zzp2));
            zzb(t, i2, i);
        }
    }

    public final int zzac(T t) {
        int i;
        int i2;
        long j;
        T t2 = t;
        int i3 = 267386880;
        if (this.zzfsp) {
            Unsafe unsafe = zzfsh;
            int i4 = 0;
            int i5 = 0;
            while (i4 < this.zzfsi.length) {
                int zzft = zzft(i4);
                int i6 = (zzft & i3) >>> 20;
                int i7 = this.zzfsi[i4];
                long j2 = (long) (zzft & 1048575);
                int i8 = (i6 < zzbqx.DOUBLE_LIST_PACKED.id() || i6 > zzbqx.SINT64_LIST_PACKED.id()) ? 0 : this.zzfsi[i4 + 2] & 1048575;
                switch (i6) {
                    case 0:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzc(i7, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzb(i7, (float) BitmapDescriptorFactory.HUE_RED);
                            break;
                        }
                    case 2:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzm(i7, zzbua.zzl(t2, j2));
                            break;
                        }
                    case 3:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzn(i7, zzbua.zzl(t2, j2));
                            break;
                        }
                    case 4:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzz(i7, zzbua.zzk(t2, j2));
                            break;
                        }
                    case 5:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzp(i7, 0);
                            break;
                        }
                    case 6:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzac(i7, 0);
                            break;
                        }
                    case 7:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzk(i7, true);
                            break;
                        }
                    case 8:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            Object zzp = zzbua.zzp(t2, j2);
                            if (!(zzp instanceof zzbpu)) {
                                i5 += zzbqk.zzg(i7, (String) zzp);
                                break;
                            } else {
                                i5 += zzbqk.zzc(i7, (zzbpu) zzp);
                                break;
                            }
                        }
                    case 9:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbte.zzc(i7, zzbua.zzp(t2, j2), zzfq(i4));
                            break;
                        }
                    case 10:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzc(i7, (zzbpu) zzbua.zzp(t2, j2));
                            break;
                        }
                    case 11:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzaa(i7, zzbua.zzk(t2, j2));
                            break;
                        }
                    case 12:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzae(i7, zzbua.zzk(t2, j2));
                            break;
                        }
                    case 13:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzad(i7, 0);
                            break;
                        }
                    case 14:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzq(i7, 0);
                            break;
                        }
                    case 15:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzab(i7, zzbua.zzk(t2, j2));
                            break;
                        }
                    case 16:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzo(i7, zzbua.zzl(t2, j2));
                            break;
                        }
                    case 17:
                        if (!zzd(t2, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzc(i7, (zzbsl) zzbua.zzp(t2, j2), zzfq(i4));
                            break;
                        }
                    case 18:
                        i5 += zzbte.zzw(i7, zze((Object) t2, j2), false);
                        break;
                    case 19:
                        i5 += zzbte.zzv(i7, zze((Object) t2, j2), false);
                        break;
                    case 20:
                        i5 += zzbte.zzo(i7, zze((Object) t2, j2), false);
                        break;
                    case 21:
                        i5 += zzbte.zzp(i7, zze((Object) t2, j2), false);
                        break;
                    case 22:
                        i5 += zzbte.zzs(i7, zze((Object) t2, j2), false);
                        break;
                    case 23:
                        i5 += zzbte.zzw(i7, zze((Object) t2, j2), false);
                        break;
                    case 24:
                        i5 += zzbte.zzv(i7, zze((Object) t2, j2), false);
                        break;
                    case 25:
                        i5 += zzbte.zzx(i7, zze((Object) t2, j2), false);
                        break;
                    case 26:
                        i5 += zzbte.zzc(i7, zze((Object) t2, j2));
                        break;
                    case 27:
                        i5 += zzbte.zzc(i7, zze((Object) t2, j2), zzfq(i4));
                        break;
                    case 28:
                        i5 += zzbte.zzd(i7, zze((Object) t2, j2));
                        break;
                    case 29:
                        i5 += zzbte.zzt(i7, zze((Object) t2, j2), false);
                        break;
                    case 30:
                        i5 += zzbte.zzr(i7, zze((Object) t2, j2), false);
                        break;
                    case 31:
                        i5 += zzbte.zzv(i7, zze((Object) t2, j2), false);
                        break;
                    case 32:
                        i5 += zzbte.zzw(i7, zze((Object) t2, j2), false);
                        break;
                    case 33:
                        i5 += zzbte.zzu(i7, zze((Object) t2, j2), false);
                        break;
                    case 34:
                        i5 += zzbte.zzq(i7, zze((Object) t2, j2), false);
                        break;
                    case 35:
                        int zzan = zzbte.zzan((List) unsafe.getObject(t2, j2));
                        if (zzan > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzan);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzan) + zzan;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzam = zzbte.zzam((List) unsafe.getObject(t2, j2));
                        if (zzam > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzam);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzam) + zzam;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zzaf = zzbte.zzaf((List) unsafe.getObject(t2, j2));
                        if (zzaf > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzaf);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzaf) + zzaf;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzag = zzbte.zzag((List) unsafe.getObject(t2, j2));
                        if (zzag > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzag);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzag) + zzag;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zzaj = zzbte.zzaj((List) unsafe.getObject(t2, j2));
                        if (zzaj > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzaj);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzaj) + zzaj;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzan2 = zzbte.zzan((List) unsafe.getObject(t2, j2));
                        if (zzan2 > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzan2);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzan2) + zzan2;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzam2 = zzbte.zzam((List) unsafe.getObject(t2, j2));
                        if (zzam2 > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzam2);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzam2) + zzam2;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzao = zzbte.zzao((List) unsafe.getObject(t2, j2));
                        if (zzao > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzao);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzao) + zzao;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzak = zzbte.zzak((List) unsafe.getObject(t2, j2));
                        if (zzak > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzak);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzak) + zzak;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzai = zzbte.zzai((List) unsafe.getObject(t2, j2));
                        if (zzai > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzai);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzai) + zzai;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzam3 = zzbte.zzam((List) unsafe.getObject(t2, j2));
                        if (zzam3 > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzam3);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzam3) + zzam3;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzan3 = zzbte.zzan((List) unsafe.getObject(t2, j2));
                        if (zzan3 > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzan3);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzan3) + zzan3;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzal = zzbte.zzal((List) unsafe.getObject(t2, j2));
                        if (zzal > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzal);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzal) + zzal;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzah = zzbte.zzah((List) unsafe.getObject(t2, j2));
                        if (zzah > 0) {
                            if (this.zzfsq) {
                                unsafe.putInt(t2, (long) i8, zzah);
                            }
                            i5 += zzbqk.zzfd(i7) + zzbqk.zzff(zzah) + zzah;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i5 += zzbte.zzd(i7, zze((Object) t2, j2), zzfq(i4));
                        break;
                    case 50:
                        i5 += this.zzfsy.zzb(i7, zzbua.zzp(t2, j2), zzfr(i4));
                        break;
                    case 51:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzc(i7, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzb(i7, (float) BitmapDescriptorFactory.HUE_RED);
                            break;
                        }
                    case 53:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzm(i7, zzi(t2, j2));
                            break;
                        }
                    case 54:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzn(i7, zzi(t2, j2));
                            break;
                        }
                    case 55:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzz(i7, zzh(t2, j2));
                            break;
                        }
                    case 56:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzp(i7, 0);
                            break;
                        }
                    case 57:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzac(i7, 0);
                            break;
                        }
                    case 58:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzk(i7, true);
                            break;
                        }
                    case 59:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            Object zzp2 = zzbua.zzp(t2, j2);
                            if (!(zzp2 instanceof zzbpu)) {
                                i5 += zzbqk.zzg(i7, (String) zzp2);
                                break;
                            } else {
                                i5 += zzbqk.zzc(i7, (zzbpu) zzp2);
                                break;
                            }
                        }
                    case 60:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbte.zzc(i7, zzbua.zzp(t2, j2), zzfq(i4));
                            break;
                        }
                    case 61:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzc(i7, (zzbpu) zzbua.zzp(t2, j2));
                            break;
                        }
                    case 62:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzaa(i7, zzh(t2, j2));
                            break;
                        }
                    case 63:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzae(i7, zzh(t2, j2));
                            break;
                        }
                    case 64:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzad(i7, 0);
                            break;
                        }
                    case 65:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzq(i7, 0);
                            break;
                        }
                    case 66:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzab(i7, zzh(t2, j2));
                            break;
                        }
                    case 67:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzo(i7, zzi(t2, j2));
                            break;
                        }
                    case 68:
                        if (!zza(t2, i7, i4)) {
                            break;
                        } else {
                            i5 += zzbqk.zzc(i7, (zzbsl) zzbua.zzp(t2, j2), zzfq(i4));
                            break;
                        }
                }
                i4 += 3;
                i3 = 267386880;
            }
            return i5 + zza(this.zzfsw, t2);
        }
        Unsafe unsafe2 = zzfsh;
        int i9 = 0;
        int i10 = 0;
        int i11 = -1;
        int i12 = 0;
        while (i9 < this.zzfsi.length) {
            int zzft2 = zzft(i9);
            int[] iArr = this.zzfsi;
            int i13 = iArr[i9];
            int i14 = (zzft2 & 267386880) >>> 20;
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
            } else if (!this.zzfsq || i14 < zzbqx.DOUBLE_LIST_PACKED.id() || i14 > zzbqx.SINT64_LIST_PACKED.id()) {
                i2 = 0;
                i = 0;
            } else {
                i2 = this.zzfsi[i9 + 2] & 1048575;
                i = 0;
            }
            long j3 = (long) (zzft2 & 1048575);
            switch (i14) {
                case 0:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzbqk.zzc(i13, 0.0d);
                        break;
                    }
                case 1:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzbqk.zzb(i13, (float) BitmapDescriptorFactory.HUE_RED);
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzbqk.zzm(i13, unsafe2.getLong(t2, j3));
                        break;
                    }
                case 3:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzbqk.zzn(i13, unsafe2.getLong(t2, j3));
                        break;
                    }
                case 4:
                    j = 0;
                    if ((i12 & i) == 0) {
                        break;
                    } else {
                        i10 += zzbqk.zzz(i13, unsafe2.getInt(t2, j3));
                        break;
                    }
                case 5:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        i10 += zzbqk.zzp(i13, 0);
                        break;
                    }
                case 6:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzac(i13, 0);
                        j = 0;
                        break;
                    }
                case 7:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzk(i13, true);
                        j = 0;
                        break;
                    }
                case 8:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        Object object = unsafe2.getObject(t2, j3);
                        if (!(object instanceof zzbpu)) {
                            i10 += zzbqk.zzg(i13, (String) object);
                            j = 0;
                            break;
                        } else {
                            i10 += zzbqk.zzc(i13, (zzbpu) object);
                            j = 0;
                            break;
                        }
                    }
                case 9:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbte.zzc(i13, unsafe2.getObject(t2, j3), zzfq(i9));
                        j = 0;
                        break;
                    }
                case 10:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzc(i13, (zzbpu) unsafe2.getObject(t2, j3));
                        j = 0;
                        break;
                    }
                case 11:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzaa(i13, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 12:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzae(i13, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 13:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzad(i13, 0);
                        j = 0;
                        break;
                    }
                case 14:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzq(i13, 0);
                        j = 0;
                        break;
                    }
                case 15:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzab(i13, unsafe2.getInt(t2, j3));
                        j = 0;
                        break;
                    }
                case 16:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzo(i13, unsafe2.getLong(t2, j3));
                        j = 0;
                        break;
                    }
                case 17:
                    if ((i12 & i) == 0) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzc(i13, (zzbsl) unsafe2.getObject(t2, j3), zzfq(i9));
                        j = 0;
                        break;
                    }
                case 18:
                    i10 += zzbte.zzw(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 19:
                    i10 += zzbte.zzv(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 20:
                    i10 += zzbte.zzo(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 21:
                    i10 += zzbte.zzp(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 22:
                    i10 += zzbte.zzs(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 23:
                    i10 += zzbte.zzw(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 24:
                    i10 += zzbte.zzv(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 25:
                    i10 += zzbte.zzx(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 26:
                    i10 += zzbte.zzc(i13, (List) unsafe2.getObject(t2, j3));
                    j = 0;
                    break;
                case 27:
                    i10 += zzbte.zzc(i13, (List) unsafe2.getObject(t2, j3), zzfq(i9));
                    j = 0;
                    break;
                case 28:
                    i10 += zzbte.zzd(i13, (List) unsafe2.getObject(t2, j3));
                    j = 0;
                    break;
                case 29:
                    i10 += zzbte.zzt(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 30:
                    i10 += zzbte.zzr(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 31:
                    i10 += zzbte.zzv(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 32:
                    i10 += zzbte.zzw(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 33:
                    i10 += zzbte.zzu(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 34:
                    i10 += zzbte.zzq(i13, (List) unsafe2.getObject(t2, j3), false);
                    j = 0;
                    break;
                case 35:
                    int zzan4 = zzbte.zzan((List) unsafe2.getObject(t2, j3));
                    if (zzan4 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzan4);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzan4) + zzan4;
                        j = 0;
                        break;
                    }
                case 36:
                    int zzam4 = zzbte.zzam((List) unsafe2.getObject(t2, j3));
                    if (zzam4 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzam4);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzam4) + zzam4;
                        j = 0;
                        break;
                    }
                case 37:
                    int zzaf2 = zzbte.zzaf((List) unsafe2.getObject(t2, j3));
                    if (zzaf2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzaf2);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzaf2) + zzaf2;
                        j = 0;
                        break;
                    }
                case 38:
                    int zzag2 = zzbte.zzag((List) unsafe2.getObject(t2, j3));
                    if (zzag2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzag2);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzag2) + zzag2;
                        j = 0;
                        break;
                    }
                case 39:
                    int zzaj2 = zzbte.zzaj((List) unsafe2.getObject(t2, j3));
                    if (zzaj2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzaj2);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzaj2) + zzaj2;
                        j = 0;
                        break;
                    }
                case 40:
                    int zzan5 = zzbte.zzan((List) unsafe2.getObject(t2, j3));
                    if (zzan5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzan5);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzan5) + zzan5;
                        j = 0;
                        break;
                    }
                case 41:
                    int zzam5 = zzbte.zzam((List) unsafe2.getObject(t2, j3));
                    if (zzam5 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzam5);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzam5) + zzam5;
                        j = 0;
                        break;
                    }
                case 42:
                    int zzao2 = zzbte.zzao((List) unsafe2.getObject(t2, j3));
                    if (zzao2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzao2);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzao2) + zzao2;
                        j = 0;
                        break;
                    }
                case 43:
                    int zzak2 = zzbte.zzak((List) unsafe2.getObject(t2, j3));
                    if (zzak2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzak2);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzak2) + zzak2;
                        j = 0;
                        break;
                    }
                case 44:
                    int zzai2 = zzbte.zzai((List) unsafe2.getObject(t2, j3));
                    if (zzai2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzai2);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzai2) + zzai2;
                        j = 0;
                        break;
                    }
                case 45:
                    int zzam6 = zzbte.zzam((List) unsafe2.getObject(t2, j3));
                    if (zzam6 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzam6);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzam6) + zzam6;
                        j = 0;
                        break;
                    }
                case 46:
                    int zzan6 = zzbte.zzan((List) unsafe2.getObject(t2, j3));
                    if (zzan6 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzan6);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzan6) + zzan6;
                        j = 0;
                        break;
                    }
                case 47:
                    int zzal2 = zzbte.zzal((List) unsafe2.getObject(t2, j3));
                    if (zzal2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzal2);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzal2) + zzal2;
                        j = 0;
                        break;
                    }
                case 48:
                    int zzah2 = zzbte.zzah((List) unsafe2.getObject(t2, j3));
                    if (zzah2 <= 0) {
                        j = 0;
                        break;
                    } else {
                        if (this.zzfsq) {
                            unsafe2.putInt(t2, (long) i2, zzah2);
                        }
                        i10 += zzbqk.zzfd(i13) + zzbqk.zzff(zzah2) + zzah2;
                        j = 0;
                        break;
                    }
                case 49:
                    i10 += zzbte.zzd(i13, (List) unsafe2.getObject(t2, j3), zzfq(i9));
                    j = 0;
                    break;
                case 50:
                    i10 += this.zzfsy.zzb(i13, unsafe2.getObject(t2, j3), zzfr(i9));
                    j = 0;
                    break;
                case 51:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzc(i13, 0.0d);
                        j = 0;
                        break;
                    }
                case 52:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzb(i13, (float) BitmapDescriptorFactory.HUE_RED);
                        j = 0;
                        break;
                    }
                case 53:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzm(i13, zzi(t2, j3));
                        j = 0;
                        break;
                    }
                case 54:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzn(i13, zzi(t2, j3));
                        j = 0;
                        break;
                    }
                case 55:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzz(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 56:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzp(i13, 0);
                        j = 0;
                        break;
                    }
                case 57:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzac(i13, 0);
                        j = 0;
                        break;
                    }
                case 58:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzk(i13, true);
                        j = 0;
                        break;
                    }
                case 59:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        Object object2 = unsafe2.getObject(t2, j3);
                        if (!(object2 instanceof zzbpu)) {
                            i10 += zzbqk.zzg(i13, (String) object2);
                            j = 0;
                            break;
                        } else {
                            i10 += zzbqk.zzc(i13, (zzbpu) object2);
                            j = 0;
                            break;
                        }
                    }
                case 60:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbte.zzc(i13, unsafe2.getObject(t2, j3), zzfq(i9));
                        j = 0;
                        break;
                    }
                case 61:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzc(i13, (zzbpu) unsafe2.getObject(t2, j3));
                        j = 0;
                        break;
                    }
                case 62:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzaa(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 63:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzae(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 64:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzad(i13, 0);
                        j = 0;
                        break;
                    }
                case 65:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzq(i13, 0);
                        j = 0;
                        break;
                    }
                case 66:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzab(i13, zzh(t2, j3));
                        j = 0;
                        break;
                    }
                case 67:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzo(i13, zzi(t2, j3));
                        j = 0;
                        break;
                    }
                case 68:
                    if (!zza(t2, i13, i9)) {
                        j = 0;
                        break;
                    } else {
                        i10 += zzbqk.zzc(i13, (zzbsl) unsafe2.getObject(t2, j3), zzfq(i9));
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
        int zza = i10 + zza(this.zzfsw, t2);
        if (this.zzfsn) {
            zza += this.zzfsx.zzq(t2).zzamj();
        }
        return zza;
    }

    private static <UT, UB> int zza(zzbtu<UT, UB> zzbtu, T t) {
        return zzbtu.zzac(zzbtu.zzag(t));
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zzbua.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0511  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x054f  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a27  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.ads.zzbup r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzaly()
            int r1 = com.google.android.gms.internal.ads.zzbrd.zze.zzfqn
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x0527
            com.google.android.gms.internal.ads.zzbtu<?, ?> r0 = r13.zzfsw
            zza(r0, (T) r14, r15)
            boolean r0 = r13.zzfsn
            if (r0 == 0) goto L_0x0030
            com.google.android.gms.internal.ads.zzbqr<?> r0 = r13.zzfsx
            com.google.android.gms.internal.ads.zzbqu r0 = r0.zzq(r14)
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
            int[] r7 = r13.zzfsi
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0037:
            if (r7 < 0) goto L_0x050f
            int r8 = r13.zzft(r7)
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
        L_0x0041:
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.ads.zzbqr<?> r10 = r13.zzfsx
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x005f
            com.google.android.gms.internal.ads.zzbqr<?> r10 = r13.zzfsx
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
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            com.google.android.gms.internal.ads.zzbtc r10 = r13.zzfq(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050b
        L_0x007d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzk(r9, r10)
            goto L_0x050b
        L_0x008e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzx(r9, r8)
            goto L_0x050b
        L_0x009f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzs(r9, r10)
            goto L_0x050b
        L_0x00b0:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzaf(r9, r8)
            goto L_0x050b
        L_0x00c1:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzag(r9, r8)
            goto L_0x050b
        L_0x00d2:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzw(r9, r8)
            goto L_0x050b
        L_0x00e3:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            com.google.android.gms.internal.ads.zzbpu r8 = (com.google.android.gms.internal.ads.zzbpu) r8
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x00f6:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            com.google.android.gms.internal.ads.zzbtc r10 = r13.zzfq(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050b
        L_0x010b:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050b
        L_0x011c:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzj(r14, r10)
            r15.zzj(r9, r8)
            goto L_0x050b
        L_0x012d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzy(r9, r8)
            goto L_0x050b
        L_0x013e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzl(r9, r10)
            goto L_0x050b
        L_0x014f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzv(r9, r8)
            goto L_0x050b
        L_0x0160:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050b
        L_0x0171:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzr(r9, r10)
            goto L_0x050b
        L_0x0182:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzg(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x0193:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzf(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x050b
        L_0x01a4:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x050b
        L_0x01af:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbtc r10 = r13.zzfq(r7)
            com.google.android.gms.internal.ads.zzbte.zzb(r9, r8, r15, r10)
            goto L_0x050b
        L_0x01c4:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zze(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01d5:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzj(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01e6:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzg(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01f7:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzl(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0208:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzm(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0219:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzi(r9, r8, r15, r4)
            goto L_0x050b
        L_0x022a:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzn(r9, r8, r15, r4)
            goto L_0x050b
        L_0x023b:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzk(r9, r8, r15, r4)
            goto L_0x050b
        L_0x024c:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzf(r9, r8, r15, r4)
            goto L_0x050b
        L_0x025d:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzh(r9, r8, r15, r4)
            goto L_0x050b
        L_0x026e:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzd(r9, r8, r15, r4)
            goto L_0x050b
        L_0x027f:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzc(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0290:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzb(r9, r8, r15, r4)
            goto L_0x050b
        L_0x02a1:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zza(r9, r8, r15, r4)
            goto L_0x050b
        L_0x02b2:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zze(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02c3:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzj(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02d4:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzg(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02e5:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzl(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02f6:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzm(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0307:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzi(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0318:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzb(r9, r8, r15)
            goto L_0x050b
        L_0x0329:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbtc r10 = r13.zzfq(r7)
            com.google.android.gms.internal.ads.zzbte.zza(r9, r8, r15, r10)
            goto L_0x050b
        L_0x033e:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zza(r9, r8, r15)
            goto L_0x050b
        L_0x034f:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzn(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0360:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzk(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0371:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzf(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0382:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzh(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0393:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzd(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03a4:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzc(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03b5:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zzb(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03c6:
            int[] r9 = r13.zzfsi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zzbte.zza(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03d7:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            com.google.android.gms.internal.ads.zzbtc r10 = r13.zzfq(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050b
        L_0x03ec:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10)
            r15.zzk(r9, r10)
            goto L_0x050b
        L_0x03fd:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10)
            r15.zzx(r9, r8)
            goto L_0x050b
        L_0x040e:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10)
            r15.zzs(r9, r10)
            goto L_0x050b
        L_0x041f:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10)
            r15.zzaf(r9, r8)
            goto L_0x050b
        L_0x0430:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10)
            r15.zzag(r9, r8)
            goto L_0x050b
        L_0x0441:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10)
            r15.zzw(r9, r8)
            goto L_0x050b
        L_0x0452:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            com.google.android.gms.internal.ads.zzbpu r8 = (com.google.android.gms.internal.ads.zzbpu) r8
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x0465:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            com.google.android.gms.internal.ads.zzbtc r10 = r13.zzfq(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050b
        L_0x047a:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050b
        L_0x048b:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.ads.zzbua.zzm(r14, r10)
            r15.zzj(r9, r8)
            goto L_0x050b
        L_0x049c:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10)
            r15.zzy(r9, r8)
            goto L_0x050b
        L_0x04ac:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10)
            r15.zzl(r9, r10)
            goto L_0x050b
        L_0x04bc:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10)
            r15.zzv(r9, r8)
            goto L_0x050b
        L_0x04cc:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050b
        L_0x04dc:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10)
            r15.zzr(r9, r10)
            goto L_0x050b
        L_0x04ec:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.ads.zzbua.zzn(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x04fc:
            boolean r10 = r13.zzd((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.ads.zzbua.zzo(r14, r10)
            r15.zzb(r9, r10)
        L_0x050b:
            int r7 = r7 + -3
            goto L_0x0037
        L_0x050f:
            if (r1 == 0) goto L_0x0526
            com.google.android.gms.internal.ads.zzbqr<?> r14 = r13.zzfsx
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
            boolean r0 = r13.zzfsp
            if (r0 == 0) goto L_0x0a42
            boolean r0 = r13.zzfsn
            if (r0 == 0) goto L_0x0546
            com.google.android.gms.internal.ads.zzbqr<?> r0 = r13.zzfsx
            com.google.android.gms.internal.ads.zzbqu r0 = r0.zzq(r14)
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
            int[] r7 = r13.zzfsi
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x054d:
            if (r1 >= r7) goto L_0x0a25
            int r9 = r13.zzft(r1)
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
        L_0x0557:
            if (r8 == 0) goto L_0x0575
            com.google.android.gms.internal.ads.zzbqr<?> r11 = r13.zzfsx
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x0575
            com.google.android.gms.internal.ads.zzbqr<?> r11 = r13.zzfsx
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
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            com.google.android.gms.internal.ads.zzbtc r11 = r13.zzfq(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a21
        L_0x0593:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzk(r10, r11)
            goto L_0x0a21
        L_0x05a4:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzx(r10, r9)
            goto L_0x0a21
        L_0x05b5:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzs(r10, r11)
            goto L_0x0a21
        L_0x05c6:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzaf(r10, r9)
            goto L_0x0a21
        L_0x05d7:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzag(r10, r9)
            goto L_0x0a21
        L_0x05e8:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzw(r10, r9)
            goto L_0x0a21
        L_0x05f9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            com.google.android.gms.internal.ads.zzbpu r9 = (com.google.android.gms.internal.ads.zzbpu) r9
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x060c:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            com.google.android.gms.internal.ads.zzbtc r11 = r13.zzfq(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a21
        L_0x0621:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a21
        L_0x0632:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzj(r14, r11)
            r15.zzj(r10, r9)
            goto L_0x0a21
        L_0x0643:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzy(r10, r9)
            goto L_0x0a21
        L_0x0654:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzl(r10, r11)
            goto L_0x0a21
        L_0x0665:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzv(r10, r9)
            goto L_0x0a21
        L_0x0676:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a21
        L_0x0687:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzr(r10, r11)
            goto L_0x0a21
        L_0x0698:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzg(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x06a9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzf(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0a21
        L_0x06ba:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0a21
        L_0x06c5:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbtc r11 = r13.zzfq(r1)
            com.google.android.gms.internal.ads.zzbte.zzb(r10, r9, r15, r11)
            goto L_0x0a21
        L_0x06da:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zze(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x06eb:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzj(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x06fc:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzg(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x070d:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzl(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x071e:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzm(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x072f:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzi(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0740:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzn(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0751:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzk(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0762:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzf(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0773:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzh(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0784:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzd(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0795:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzc(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07a6:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzb(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07b7:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zza(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07c8:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zze(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07d9:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzj(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07ea:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzg(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07fb:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzl(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x080c:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzm(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x081d:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzi(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x082e:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzb(r10, r9, r15)
            goto L_0x0a21
        L_0x083f:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbtc r11 = r13.zzfq(r1)
            com.google.android.gms.internal.ads.zzbte.zza(r10, r9, r15, r11)
            goto L_0x0a21
        L_0x0854:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zza(r10, r9, r15)
            goto L_0x0a21
        L_0x0865:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzn(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0876:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzk(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0887:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzf(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0898:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzh(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08a9:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzd(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08ba:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzc(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08cb:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzb(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08dc:
            int[] r10 = r13.zzfsi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zza(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08ed:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            com.google.android.gms.internal.ads.zzbtc r11 = r13.zzfq(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a21
        L_0x0902:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11)
            r15.zzk(r10, r11)
            goto L_0x0a21
        L_0x0913:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11)
            r15.zzx(r10, r9)
            goto L_0x0a21
        L_0x0924:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11)
            r15.zzs(r10, r11)
            goto L_0x0a21
        L_0x0935:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11)
            r15.zzaf(r10, r9)
            goto L_0x0a21
        L_0x0946:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11)
            r15.zzag(r10, r9)
            goto L_0x0a21
        L_0x0957:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11)
            r15.zzw(r10, r9)
            goto L_0x0a21
        L_0x0968:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            com.google.android.gms.internal.ads.zzbpu r9 = (com.google.android.gms.internal.ads.zzbpu) r9
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x097b:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            com.google.android.gms.internal.ads.zzbtc r11 = r13.zzfq(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a21
        L_0x0990:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a21
        L_0x09a1:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.ads.zzbua.zzm(r14, r11)
            r15.zzj(r10, r9)
            goto L_0x0a21
        L_0x09b2:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11)
            r15.zzy(r10, r9)
            goto L_0x0a21
        L_0x09c2:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11)
            r15.zzl(r10, r11)
            goto L_0x0a21
        L_0x09d2:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11)
            r15.zzv(r10, r9)
            goto L_0x0a21
        L_0x09e2:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a21
        L_0x09f2:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11)
            r15.zzr(r10, r11)
            goto L_0x0a21
        L_0x0a02:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.ads.zzbua.zzn(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x0a12:
            boolean r11 = r13.zzd((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.ads.zzbua.zzo(r14, r11)
            r15.zzb(r10, r11)
        L_0x0a21:
            int r1 = r1 + 3
            goto L_0x054d
        L_0x0a25:
            if (r8 == 0) goto L_0x0a3c
            com.google.android.gms.internal.ads.zzbqr<?> r1 = r13.zzfsx
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
            com.google.android.gms.internal.ads.zzbtu<?, ?> r0 = r13.zzfsw
            zza(r0, (T) r14, r15)
            return
        L_0x0a42:
            r13.zzb(r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbup):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:190:0x051c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r19, com.google.android.gms.internal.ads.zzbup r20) throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r0.zzfsn
            if (r3 == 0) goto L_0x0021
            com.google.android.gms.internal.ads.zzbqr<?> r3 = r0.zzfsx
            com.google.android.gms.internal.ads.zzbqu r3 = r3.zzq(r1)
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
            int[] r7 = r0.zzfsi
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzfsh
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002c:
            if (r5 >= r7) goto L_0x0516
            int r12 = r0.zzft(r5)
            int[] r13 = r0.zzfsi
            r14 = r13[r5]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r15 = r15 & r12
            int r15 = r15 >>> 20
            boolean r4 = r0.zzfsp
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
            com.google.android.gms.internal.ads.zzbqr<?> r4 = r0.zzfsx
            int r4 = r4.zza(r10)
            if (r4 > r14) goto L_0x0086
            com.google.android.gms.internal.ads.zzbqr<?> r4 = r0.zzfsx
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
            com.google.android.gms.internal.ads.zzbtc r9 = r0.zzfq(r5)
            r2.zzb(r14, r4, r9)
            r15 = 0
            goto L_0x0512
        L_0x00a3:
            r15 = 0
            goto L_0x0512
        L_0x00a6:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00b6
            long r12 = zzi(r1, r12)
            r2.zzk(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x00b6:
            r15 = 0
            goto L_0x0512
        L_0x00b9:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00c9
            int r4 = zzh(r1, r12)
            r2.zzx(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x00c9:
            r15 = 0
            goto L_0x0512
        L_0x00cc:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00dc
            long r12 = zzi(r1, r12)
            r2.zzs(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x00dc:
            r15 = 0
            goto L_0x0512
        L_0x00df:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x00ef
            int r4 = zzh(r1, r12)
            r2.zzaf(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x00ef:
            r15 = 0
            goto L_0x0512
        L_0x00f2:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0102
            int r4 = zzh(r1, r12)
            r2.zzag(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x0102:
            r15 = 0
            goto L_0x0512
        L_0x0105:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x0115
            int r4 = zzh(r1, r12)
            r2.zzw(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x0115:
            r15 = 0
            goto L_0x0512
        L_0x0118:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x012a
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.ads.zzbpu r4 = (com.google.android.gms.internal.ads.zzbpu) r4
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
            com.google.android.gms.internal.ads.zzbtc r9 = r0.zzfq(r5)
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
            boolean r4 = zzj(r1, r12)
            r2.zzj(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x0167:
            r15 = 0
            goto L_0x0512
        L_0x016a:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x017a
            int r4 = zzh(r1, r12)
            r2.zzy(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x017a:
            r15 = 0
            goto L_0x0512
        L_0x017d:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x018d
            long r12 = zzi(r1, r12)
            r2.zzl(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x018d:
            r15 = 0
            goto L_0x0512
        L_0x0190:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01a0
            int r4 = zzh(r1, r12)
            r2.zzv(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x01a0:
            r15 = 0
            goto L_0x0512
        L_0x01a3:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01b3
            long r12 = zzi(r1, r12)
            r2.zzj(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x01b3:
            r15 = 0
            goto L_0x0512
        L_0x01b6:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01c6
            long r12 = zzi(r1, r12)
            r2.zzr(r14, r12)
            r15 = 0
            goto L_0x0512
        L_0x01c6:
            r15 = 0
            goto L_0x0512
        L_0x01c9:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01d9
            float r4 = zzg(r1, r12)
            r2.zza(r14, r4)
            r15 = 0
            goto L_0x0512
        L_0x01d9:
            r15 = 0
            goto L_0x0512
        L_0x01dc:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x01ec
            double r12 = zzf(r1, r12)
            r2.zzb(r14, r12)
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
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbtc r12 = r0.zzfq(r5)
            com.google.android.gms.internal.ads.zzbte.zzb(r4, r9, r2, r12)
            r15 = 0
            goto L_0x0512
        L_0x020d:
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 1
            com.google.android.gms.internal.ads.zzbte.zze(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x021e:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzj(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x022f:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzg(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0240:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzl(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0251:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzm(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0262:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzi(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0273:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzn(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0284:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzk(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0295:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzf(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02a6:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzh(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02b7:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzd(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02c8:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzc(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02d9:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzb(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02ea:
            r14 = 1
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zza(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x02fb:
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 0
            com.google.android.gms.internal.ads.zzbte.zze(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x030c:
            r14 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzj(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x031d:
            r14 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzg(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x032e:
            r14 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzl(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x033f:
            r14 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzm(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0350:
            r14 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzi(r4, r9, r2, r14)
            r15 = 0
            goto L_0x0512
        L_0x0361:
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzb(r4, r9, r2)
            r15 = 0
            goto L_0x0512
        L_0x0371:
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbtc r12 = r0.zzfq(r5)
            com.google.android.gms.internal.ads.zzbte.zza(r4, r9, r2, r12)
            r15 = 0
            goto L_0x0512
        L_0x0385:
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zza(r4, r9, r2)
            r15 = 0
            goto L_0x0512
        L_0x0395:
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r15 = 0
            com.google.android.gms.internal.ads.zzbte.zzn(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03a5:
            r15 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzk(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03b5:
            r15 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzf(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03c5:
            r15 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzh(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03d5:
            r15 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzd(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03e5:
            r15 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzc(r4, r9, r2, r15)
            goto L_0x0512
        L_0x03f5:
            r15 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zzb(r4, r9, r2, r15)
            goto L_0x0512
        L_0x0405:
            r15 = 0
            int[] r4 = r0.zzfsi
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zzbte.zza(r4, r9, r2, r15)
            goto L_0x0512
        L_0x0415:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.ads.zzbtc r9 = r0.zzfq(r5)
            r2.zzb(r14, r4, r9)
            goto L_0x0512
        L_0x0427:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zzk(r14, r12)
            goto L_0x0512
        L_0x0435:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzx(r14, r4)
            goto L_0x0512
        L_0x0443:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zzs(r14, r12)
            goto L_0x0512
        L_0x0451:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzaf(r14, r4)
            goto L_0x0512
        L_0x045f:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzag(r14, r4)
            goto L_0x0512
        L_0x046d:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzw(r14, r4)
            goto L_0x0512
        L_0x047b:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.ads.zzbpu r4 = (com.google.android.gms.internal.ads.zzbpu) r4
            r2.zza(r14, r4)
            goto L_0x0512
        L_0x048b:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.ads.zzbtc r9 = r0.zzfq(r5)
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
            boolean r4 = com.google.android.gms.internal.ads.zzbua.zzm(r1, r12)
            r2.zzj(r14, r4)
            goto L_0x0512
        L_0x04b8:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzy(r14, r4)
            goto L_0x0512
        L_0x04c5:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zzl(r14, r12)
            goto L_0x0512
        L_0x04d2:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            int r4 = r8.getInt(r1, r12)
            r2.zzv(r14, r4)
            goto L_0x0512
        L_0x04df:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x0512
        L_0x04ec:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            long r12 = r8.getLong(r1, r12)
            r2.zzr(r14, r12)
            goto L_0x0512
        L_0x04f9:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            float r4 = com.google.android.gms.internal.ads.zzbua.zzn(r1, r12)
            r2.zza(r14, r4)
            goto L_0x0512
        L_0x0506:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0512
            double r12 = com.google.android.gms.internal.ads.zzbua.zzo(r1, r12)
            r2.zzb(r14, r12)
        L_0x0512:
            int r5 = r5 + 3
            goto L_0x002c
        L_0x0516:
            r17 = r10
            r4 = r17
        L_0x051a:
            if (r4 == 0) goto L_0x0530
            com.google.android.gms.internal.ads.zzbqr<?> r5 = r0.zzfsx
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
            com.google.android.gms.internal.ads.zzbtu<?, ?> r3 = r0.zzfsw
            zza(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zzb(java.lang.Object, com.google.android.gms.internal.ads.zzbup):void");
    }

    private final <K, V> void zza(zzbup zzbup, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzbup.zza(i, this.zzfsy.zzab(zzfr(i2)), this.zzfsy.zzx(obj));
        }
    }

    private static <UT, UB> void zza(zzbtu<UT, UB> zzbtu, T t, zzbup zzbup) throws IOException {
        zzbtu.zza(zzbtu.zzag(t), zzbup);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:153:?, code lost:
        r7.zza(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x05a2, code lost:
        if (r10 == null) goto L_0x05a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x05a4, code lost:
        r10 = r7.zzah(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x05ad, code lost:
        if (r7.zza(r10, r14) == false) goto L_0x05af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x05af, code lost:
        r14 = r12.zzfss;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x05b3, code lost:
        if (r14 < r12.zzfst) goto L_0x05b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x05b5, code lost:
        r10 = zza((java.lang.Object) r13, r12.zzfsr[r14], (UB) r10, r7);
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x05c0, code lost:
        if (r10 != null) goto L_0x05c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x05c2, code lost:
        r7.zzg(r13, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x05c5, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:152:0x059f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r13, com.google.android.gms.internal.ads.zzbtb r14, com.google.android.gms.internal.ads.zzbqq r15) throws java.io.IOException {
        /*
            r12 = this;
            if (r15 == 0) goto L_0x05de
            com.google.android.gms.internal.ads.zzbtu<?, ?> r7 = r12.zzfsw
            com.google.android.gms.internal.ads.zzbqr<?> r8 = r12.zzfsx
            r9 = 0
            r0 = r9
            r10 = r0
        L_0x0009:
            int r1 = r14.zzals()     // Catch:{ all -> 0x05c6 }
            int r2 = r12.zzfw(r1)     // Catch:{ all -> 0x05c6 }
            if (r2 >= 0) goto L_0x0079
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r2) goto L_0x002f
            int r14 = r12.zzfss
        L_0x001a:
            int r15 = r12.zzfst
            if (r14 >= r15) goto L_0x0029
            int[] r15 = r12.zzfsr
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza(r13, r15, (UB) r10, r7)
            int r14 = r14 + 1
            goto L_0x001a
        L_0x0029:
            if (r10 == 0) goto L_0x002e
            r7.zzg(r13, r10)
        L_0x002e:
            return
        L_0x002f:
            boolean r2 = r12.zzfsn     // Catch:{ all -> 0x05c6 }
            if (r2 != 0) goto L_0x0035
            r2 = r9
            goto L_0x003c
        L_0x0035:
            com.google.android.gms.internal.ads.zzbsl r2 = r12.zzfsm     // Catch:{ all -> 0x05c6 }
            java.lang.Object r1 = r8.zza(r15, r2, r1)     // Catch:{ all -> 0x05c6 }
            r2 = r1
        L_0x003c:
            if (r2 == 0) goto L_0x0053
            if (r0 != 0) goto L_0x0046
            com.google.android.gms.internal.ads.zzbqu r0 = r8.zzr(r13)     // Catch:{ all -> 0x05c6 }
            r11 = r0
            goto L_0x0047
        L_0x0046:
            r11 = r0
        L_0x0047:
            r0 = r8
            r1 = r14
            r3 = r15
            r4 = r11
            r5 = r10
            r6 = r7
            java.lang.Object r10 = r0.zza(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x05c6 }
            r0 = r11
            goto L_0x0009
        L_0x0053:
            r7.zza(r14)     // Catch:{ all -> 0x05c6 }
            if (r10 != 0) goto L_0x005c
            java.lang.Object r10 = r7.zzah(r13)     // Catch:{ all -> 0x05c6 }
        L_0x005c:
            boolean r1 = r7.zza(r10, r14)     // Catch:{ all -> 0x05c6 }
            if (r1 != 0) goto L_0x0009
            int r14 = r12.zzfss
        L_0x0064:
            int r15 = r12.zzfst
            if (r14 >= r15) goto L_0x0073
            int[] r15 = r12.zzfsr
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza(r13, r15, (UB) r10, r7)
            int r14 = r14 + 1
            goto L_0x0064
        L_0x0073:
            if (r10 == 0) goto L_0x0078
            r7.zzg(r13, r10)
        L_0x0078:
            return
        L_0x0079:
            int r3 = r12.zzft(r2)     // Catch:{ all -> 0x05c6 }
            r4 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r3
            int r4 = r4 >>> 20
            r5 = 1048575(0xfffff, float:1.469367E-39)
            switch(r4) {
                case 0: goto L_0x0573;
                case 1: goto L_0x0564;
                case 2: goto L_0x0555;
                case 3: goto L_0x0546;
                case 4: goto L_0x0537;
                case 5: goto L_0x0528;
                case 6: goto L_0x0519;
                case 7: goto L_0x050a;
                case 8: goto L_0x0502;
                case 9: goto L_0x04d1;
                case 10: goto L_0x04c2;
                case 11: goto L_0x04b3;
                case 12: goto L_0x0491;
                case 13: goto L_0x0482;
                case 14: goto L_0x0473;
                case 15: goto L_0x0464;
                case 16: goto L_0x0455;
                case 17: goto L_0x0424;
                case 18: goto L_0x0416;
                case 19: goto L_0x0408;
                case 20: goto L_0x03fa;
                case 21: goto L_0x03ec;
                case 22: goto L_0x03de;
                case 23: goto L_0x03d0;
                case 24: goto L_0x03c2;
                case 25: goto L_0x03b4;
                case 26: goto L_0x0392;
                case 27: goto L_0x0380;
                case 28: goto L_0x0372;
                case 29: goto L_0x0364;
                case 30: goto L_0x034f;
                case 31: goto L_0x0341;
                case 32: goto L_0x0333;
                case 33: goto L_0x0325;
                case 34: goto L_0x0317;
                case 35: goto L_0x0309;
                case 36: goto L_0x02fb;
                case 37: goto L_0x02ed;
                case 38: goto L_0x02df;
                case 39: goto L_0x02d1;
                case 40: goto L_0x02c3;
                case 41: goto L_0x02b5;
                case 42: goto L_0x02a7;
                case 43: goto L_0x0299;
                case 44: goto L_0x0284;
                case 45: goto L_0x0276;
                case 46: goto L_0x0268;
                case 47: goto L_0x025a;
                case 48: goto L_0x024c;
                case 49: goto L_0x023a;
                case 50: goto L_0x01f8;
                case 51: goto L_0x01e6;
                case 52: goto L_0x01d4;
                case 53: goto L_0x01c2;
                case 54: goto L_0x01b0;
                case 55: goto L_0x019e;
                case 56: goto L_0x018c;
                case 57: goto L_0x017a;
                case 58: goto L_0x0168;
                case 59: goto L_0x0160;
                case 60: goto L_0x012f;
                case 61: goto L_0x0121;
                case 62: goto L_0x010f;
                case 63: goto L_0x00ea;
                case 64: goto L_0x00d8;
                case 65: goto L_0x00c6;
                case 66: goto L_0x00b4;
                case 67: goto L_0x00a2;
                case 68: goto L_0x0090;
                default: goto L_0x0088;
            }
        L_0x0088:
            if (r10 != 0) goto L_0x0582
            java.lang.Object r10 = r7.zzaoy()     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0582
        L_0x0090:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbtc r5 = r12.zzfq(r2)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r5 = r14.zzb(r5, r15)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x00a2:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzali()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x00b4:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            int r5 = r14.zzalh()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x00c6:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzalg()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x00d8:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            int r5 = r14.zzalf()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x00ea:
            int r4 = r14.zzale()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbri r6 = r12.zzfs(r2)     // Catch:{ zzbrm -> 0x059f }
            if (r6 == 0) goto L_0x0101
            boolean r6 = r6.zzcb(r4)     // Catch:{ zzbrm -> 0x059f }
            if (r6 == 0) goto L_0x00fb
            goto L_0x0101
        L_0x00fb:
            java.lang.Object r10 = com.google.android.gms.internal.ads.zzbte.zza(r1, r4, r10, r7)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0101:
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r5, r3)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x010f:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            int r5 = r14.zzald()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0121:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbpu r5 = r14.zzalc()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x012f:
            boolean r4 = r12.zza((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            if (r4 == 0) goto L_0x014b
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbua.zzp(r13, r3)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbtc r6 = r12.zzfq(r2)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r6 = r14.zza(r6, r15)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbrf.zzb(r5, r6)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x015b
        L_0x014b:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbtc r5 = r12.zzfq(r2)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r5 = r14.zza(r5, r15)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
        L_0x015b:
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0160:
            r12.zza(r13, r3, r14)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0168:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            boolean r5 = r14.zzala()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x017a:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            int r5 = r14.zzakz()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x018c:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzaky()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x019e:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            int r5 = r14.zzakx()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x01b0:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzakv()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x01c2:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzakw()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x01d4:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            float r5 = r14.readFloat()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x01e6:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            double r5 = r14.readDouble()     // Catch:{ zzbrm -> 0x059f }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x01f8:
            java.lang.Object r1 = r12.zzfr(r2)     // Catch:{ zzbrm -> 0x059f }
            int r2 = r12.zzft(r2)     // Catch:{ zzbrm -> 0x059f }
            r2 = r2 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r4 = com.google.android.gms.internal.ads.zzbua.zzp(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            if (r4 != 0) goto L_0x0212
            com.google.android.gms.internal.ads.zzbsg r4 = r12.zzfsy     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r4 = r4.zzaa(r1)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r2, r4)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0229
        L_0x0212:
            com.google.android.gms.internal.ads.zzbsg r5 = r12.zzfsy     // Catch:{ zzbrm -> 0x059f }
            boolean r5 = r5.zzy(r4)     // Catch:{ zzbrm -> 0x059f }
            if (r5 == 0) goto L_0x0229
            com.google.android.gms.internal.ads.zzbsg r5 = r12.zzfsy     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r5 = r5.zzaa(r1)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbsg r6 = r12.zzfsy     // Catch:{ zzbrm -> 0x059f }
            r6.zzc(r5, r4)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r2, r5)     // Catch:{ zzbrm -> 0x059f }
            r4 = r5
        L_0x0229:
            com.google.android.gms.internal.ads.zzbsg r2 = r12.zzfsy     // Catch:{ zzbrm -> 0x059f }
            java.util.Map r2 = r2.zzw(r4)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbsg r3 = r12.zzfsy     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbse r1 = r3.zzab(r1)     // Catch:{ zzbrm -> 0x059f }
            r14.zza(r2, r1, r15)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x023a:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbtc r1 = r12.zzfq(r2)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbrv r2 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzbrm -> 0x059f }
            r14.zzb(r2, r1, r15)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x024c:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzae(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x025a:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzad(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0268:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzac(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0276:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzab(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0284:
            com.google.android.gms.internal.ads.zzbrv r4 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzbrm -> 0x059f }
            r14.zzaa(r3)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbri r2 = r12.zzfs(r2)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r10 = com.google.android.gms.internal.ads.zzbte.zza(r1, r3, r2, r10, r7)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0299:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzz(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x02a7:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzw(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x02b5:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzv(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x02c3:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzu(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x02d1:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzt(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x02df:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzr(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x02ed:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzs(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x02fb:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzq(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0309:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzp(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0317:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzae(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0325:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzad(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0333:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzac(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0341:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzab(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x034f:
            com.google.android.gms.internal.ads.zzbrv r4 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzbrm -> 0x059f }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzbrm -> 0x059f }
            r14.zzaa(r3)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbri r2 = r12.zzfs(r2)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r10 = com.google.android.gms.internal.ads.zzbte.zza(r1, r3, r2, r10, r7)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0364:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzz(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0372:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzy(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0380:
            com.google.android.gms.internal.ads.zzbtc r1 = r12.zzfq(r2)     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbrv r4 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            java.util.List r2 = r4.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zza(r2, r1, r15)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0392:
            boolean r1 = zzfv(r3)     // Catch:{ zzbrm -> 0x059f }
            if (r1 == 0) goto L_0x03a6
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzx(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x03a6:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.readStringList(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x03b4:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzw(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x03c2:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzv(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x03d0:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzu(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x03de:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzt(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x03ec:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzr(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x03fa:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzs(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0408:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzq(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0416:
            com.google.android.gms.internal.ads.zzbrv r1 = r12.zzfsv     // Catch:{ zzbrm -> 0x059f }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzbrm -> 0x059f }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbrm -> 0x059f }
            r14.zzp(r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0424:
            boolean r1 = r12.zzd((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            if (r1 == 0) goto L_0x0442
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbua.zzp(r13, r3)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbtc r2 = r12.zzfq(r2)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r2 = r14.zzb(r2, r15)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r2)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0442:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbtc r1 = r12.zzfq(r2)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r1 = r14.zzb(r1, r15)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0455:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzali()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0464:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            int r1 = r14.zzalh()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0473:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzalg()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0482:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            int r1 = r14.zzalf()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0491:
            int r4 = r14.zzale()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbri r6 = r12.zzfs(r2)     // Catch:{ zzbrm -> 0x059f }
            if (r6 == 0) goto L_0x04a8
            boolean r6 = r6.zzcb(r4)     // Catch:{ zzbrm -> 0x059f }
            if (r6 == 0) goto L_0x04a2
            goto L_0x04a8
        L_0x04a2:
            java.lang.Object r10 = com.google.android.gms.internal.ads.zzbte.zza(r1, r4, r10, r7)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x04a8:
            r1 = r3 & r5
            long r5 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zzb(r13, r5, r4)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x04b3:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            int r1 = r14.zzald()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x04c2:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbpu r1 = r14.zzalc()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x04d1:
            boolean r1 = r12.zzd((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            if (r1 == 0) goto L_0x04ef
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbua.zzp(r13, r3)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbtc r2 = r12.zzfq(r2)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r2 = r14.zza(r2, r15)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r2)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x04ef:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbtc r1 = r12.zzfq(r2)     // Catch:{ zzbrm -> 0x059f }
            java.lang.Object r1 = r14.zza(r1, r15)     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0502:
            r12.zza(r13, r3, r14)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x050a:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            boolean r1 = r14.zzala()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0519:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            int r1 = r14.zzakz()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0528:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzaky()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0537:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            int r1 = r14.zzakx()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0546:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzakv()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0555:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            long r5 = r14.zzakw()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0564:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            float r1 = r14.readFloat()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0573:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzbrm -> 0x059f }
            double r5 = r14.readDouble()     // Catch:{ zzbrm -> 0x059f }
            com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5)     // Catch:{ zzbrm -> 0x059f }
            r12.zze((T) r13, r2)     // Catch:{ zzbrm -> 0x059f }
            goto L_0x0009
        L_0x0582:
            boolean r1 = r7.zza(r10, r14)     // Catch:{ zzbrm -> 0x059f }
            if (r1 != 0) goto L_0x0009
            int r14 = r12.zzfss
        L_0x058a:
            int r15 = r12.zzfst
            if (r14 >= r15) goto L_0x0599
            int[] r15 = r12.zzfsr
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza(r13, r15, (UB) r10, r7)
            int r14 = r14 + 1
            goto L_0x058a
        L_0x0599:
            if (r10 == 0) goto L_0x059e
            r7.zzg(r13, r10)
        L_0x059e:
            return
        L_0x059f:
            r7.zza(r14)     // Catch:{ all -> 0x05c6 }
            if (r10 != 0) goto L_0x05a9
            java.lang.Object r1 = r7.zzah(r13)     // Catch:{ all -> 0x05c6 }
            r10 = r1
        L_0x05a9:
            boolean r1 = r7.zza(r10, r14)     // Catch:{ all -> 0x05c6 }
            if (r1 != 0) goto L_0x0009
            int r14 = r12.zzfss
        L_0x05b1:
            int r15 = r12.zzfst
            if (r14 >= r15) goto L_0x05c0
            int[] r15 = r12.zzfsr
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza(r13, r15, (UB) r10, r7)
            int r14 = r14 + 1
            goto L_0x05b1
        L_0x05c0:
            if (r10 == 0) goto L_0x05c5
            r7.zzg(r13, r10)
        L_0x05c5:
            return
        L_0x05c6:
            r14 = move-exception
            int r15 = r12.zzfss
        L_0x05c9:
            int r0 = r12.zzfst
            if (r15 >= r0) goto L_0x05d8
            int[] r0 = r12.zzfsr
            r0 = r0[r15]
            java.lang.Object r10 = r12.zza(r13, r0, (UB) r10, r7)
            int r15 = r15 + 1
            goto L_0x05c9
        L_0x05d8:
            if (r10 == 0) goto L_0x05dd
            r7.zzg(r13, r10)
        L_0x05dd:
            throw r14
        L_0x05de:
            java.lang.NullPointerException r13 = new java.lang.NullPointerException
            r13.<init>()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbtb, com.google.android.gms.internal.ads.zzbqq):void");
    }

    private static zzbtv zzad(Object obj) {
        zzbrd zzbrd = (zzbrd) obj;
        zzbtv zzbtv = zzbrd.zzfpu;
        if (zzbtv != zzbtv.zzaoz()) {
            return zzbtv;
        }
        zzbtv zzapa = zzbtv.zzapa();
        zzbrd.zzfpu = zzapa;
        return zzapa;
    }

    private static int zza(zzbtc zzbtc, byte[] bArr, int i, int i2, zzbpr zzbpr) throws IOException {
        int i3;
        int i4;
        int i5 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            i4 = zzbpq.zza((int) b, bArr, i5, zzbpr);
            i3 = zzbpr.zzfld;
        } else {
            i4 = i5;
            i3 = b;
        }
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzbrl.zzanc();
        }
        Object newInstance = zzbtc.newInstance();
        int i6 = i3 + i4;
        zzbtc.zza(newInstance, bArr, i4, i6, zzbpr);
        zzbtc.zzs(newInstance);
        zzbpr.zzflf = newInstance;
        return i6;
    }

    private static int zza(zzbtc zzbtc, byte[] bArr, int i, int i2, int i3, zzbpr zzbpr) throws IOException {
        zzbsp zzbsp = (zzbsp) zzbtc;
        Object newInstance = zzbsp.newInstance();
        int zza = zzbsp.zza((T) newInstance, bArr, i, i2, i3, zzbpr);
        zzbsp.zzs(newInstance);
        zzbpr.zzflf = newInstance;
        return zza;
    }

    private static int zza(zzbtc<?> zzbtc, int i, byte[] bArr, int i2, int i3, zzbrk<?> zzbrk, zzbpr zzbpr) throws IOException {
        int zza = zza((zzbtc) zzbtc, bArr, i2, i3, zzbpr);
        zzbrk.add(zzbpr.zzflf);
        while (zza < i3) {
            int zza2 = zzbpq.zza(bArr, zza, zzbpr);
            if (i != zzbpr.zzfld) {
                break;
            }
            zza = zza((zzbtc) zzbtc, bArr, zza2, i3, zzbpr);
            zzbrk.add(zzbpr.zzflf);
        }
        return zza;
    }

    private static int zza(byte[] bArr, int i, int i2, zzbuj zzbuj, Class<?> cls, zzbpr zzbpr) throws IOException {
        switch (zzbuj) {
            case BOOL:
                int zzb = zzbpq.zzb(bArr, i, zzbpr);
                zzbpr.zzflf = Boolean.valueOf(zzbpr.zzfle != 0);
                return zzb;
            case BYTES:
                return zzbpq.zze(bArr, i, zzbpr);
            case DOUBLE:
                zzbpr.zzflf = Double.valueOf(zzbpq.zzi(bArr, i));
                return i + 8;
            case FIXED32:
            case SFIXED32:
                zzbpr.zzflf = Integer.valueOf(zzbpq.zzg(bArr, i));
                return i + 4;
            case FIXED64:
            case SFIXED64:
                zzbpr.zzflf = Long.valueOf(zzbpq.zzh(bArr, i));
                return i + 8;
            case FLOAT:
                zzbpr.zzflf = Float.valueOf(zzbpq.zzj(bArr, i));
                return i + 4;
            case ENUM:
            case INT32:
            case UINT32:
                int zza = zzbpq.zza(bArr, i, zzbpr);
                zzbpr.zzflf = Integer.valueOf(zzbpr.zzfld);
                return zza;
            case INT64:
            case UINT64:
                int zzb2 = zzbpq.zzb(bArr, i, zzbpr);
                zzbpr.zzflf = Long.valueOf(zzbpr.zzfle);
                return zzb2;
            case MESSAGE:
                return zza(zzbsy.zzaog().zzf(cls), bArr, i, i2, zzbpr);
            case SINT32:
                int zza2 = zzbpq.zza(bArr, i, zzbpr);
                zzbpr.zzflf = Integer.valueOf(zzbqf.zzeu(zzbpr.zzfld));
                return zza2;
            case SINT64:
                int zzb3 = zzbpq.zzb(bArr, i, zzbpr);
                zzbpr.zzflf = Long.valueOf(zzbqf.zzax(zzbpr.zzfle));
                return zzb3;
            case STRING:
                return zzbpq.zzd(bArr, i, zzbpr);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzbpr zzbpr) throws IOException {
        return zzbpq.zza(i, bArr, i2, i3, zzad(obj), zzbpr);
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzbpr zzbpr) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i12 = i;
        int i13 = i2;
        int i14 = i3;
        int i15 = i5;
        int i16 = i6;
        long j3 = j2;
        zzbpr zzbpr2 = zzbpr;
        zzbrk zzbrk = (zzbrk) zzfsh.getObject(t2, j3);
        if (!zzbrk.zzaki()) {
            int size = zzbrk.size();
            zzbrk = zzbrk.zzel(size == 0 ? 10 : size << 1);
            zzfsh.putObject(t2, j3, zzbrk);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i15 == 2) {
                    zzbqn zzbqn = (zzbqn) zzbrk;
                    int zza = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i17 = zzbpr2.zzfld + zza;
                    while (zza < i17) {
                        zzbqn.zzd(zzbpq.zzi(bArr2, zza));
                        zza += 8;
                    }
                    if (zza == i17) {
                        return zza;
                    }
                    throw zzbrl.zzanc();
                } else if (i15 == 1) {
                    zzbqn zzbqn2 = (zzbqn) zzbrk;
                    zzbqn2.zzd(zzbpq.zzi(bArr, i));
                    int i18 = i12 + 8;
                    while (i18 < i13) {
                        int zza2 = zzbpq.zza(bArr2, i18, zzbpr2);
                        if (i14 != zzbpr2.zzfld) {
                            return i18;
                        }
                        zzbqn2.zzd(zzbpq.zzi(bArr2, zza2));
                        i18 = zza2 + 8;
                    }
                    return i18;
                }
                break;
            case 19:
            case 36:
                if (i15 == 2) {
                    zzbra zzbra = (zzbra) zzbrk;
                    int zza3 = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i19 = zzbpr2.zzfld + zza3;
                    while (zza3 < i19) {
                        zzbra.zzh(zzbpq.zzj(bArr2, zza3));
                        zza3 += 4;
                    }
                    if (zza3 == i19) {
                        return zza3;
                    }
                    throw zzbrl.zzanc();
                } else if (i15 == 5) {
                    zzbra zzbra2 = (zzbra) zzbrk;
                    zzbra2.zzh(zzbpq.zzj(bArr, i));
                    int i20 = i12 + 4;
                    while (i20 < i13) {
                        int zza4 = zzbpq.zza(bArr2, i20, zzbpr2);
                        if (i14 != zzbpr2.zzfld) {
                            return i20;
                        }
                        zzbra2.zzh(zzbpq.zzj(bArr2, zza4));
                        i20 = zza4 + 4;
                    }
                    return i20;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i15 == 2) {
                    zzbrz zzbrz = (zzbrz) zzbrk;
                    int zza5 = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i21 = zzbpr2.zzfld + zza5;
                    while (zza5 < i21) {
                        zza5 = zzbpq.zzb(bArr2, zza5, zzbpr2);
                        zzbrz.zzbj(zzbpr2.zzfle);
                    }
                    if (zza5 == i21) {
                        return zza5;
                    }
                    throw zzbrl.zzanc();
                } else if (i15 == 0) {
                    zzbrz zzbrz2 = (zzbrz) zzbrk;
                    int zzb = zzbpq.zzb(bArr2, i12, zzbpr2);
                    zzbrz2.zzbj(zzbpr2.zzfle);
                    while (zzb < i13) {
                        int zza6 = zzbpq.zza(bArr2, zzb, zzbpr2);
                        if (i14 != zzbpr2.zzfld) {
                            return zzb;
                        }
                        zzb = zzbpq.zzb(bArr2, zza6, zzbpr2);
                        zzbrz2.zzbj(zzbpr2.zzfle);
                    }
                    return zzb;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i15 == 2) {
                    return zzbpq.zza(bArr2, i12, zzbrk, zzbpr2);
                }
                if (i15 == 0) {
                    return zzbpq.zza(i3, bArr, i, i2, zzbrk, zzbpr);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i15 == 2) {
                    zzbrz zzbrz3 = (zzbrz) zzbrk;
                    int zza7 = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i22 = zzbpr2.zzfld + zza7;
                    while (zza7 < i22) {
                        zzbrz3.zzbj(zzbpq.zzh(bArr2, zza7));
                        zza7 += 8;
                    }
                    if (zza7 == i22) {
                        return zza7;
                    }
                    throw zzbrl.zzanc();
                } else if (i15 == 1) {
                    zzbrz zzbrz4 = (zzbrz) zzbrk;
                    zzbrz4.zzbj(zzbpq.zzh(bArr, i));
                    int i23 = i12 + 8;
                    while (i23 < i13) {
                        int zza8 = zzbpq.zza(bArr2, i23, zzbpr2);
                        if (i14 != zzbpr2.zzfld) {
                            return i23;
                        }
                        zzbrz4.zzbj(zzbpq.zzh(bArr2, zza8));
                        i23 = zza8 + 8;
                    }
                    return i23;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i15 == 2) {
                    zzbre zzbre = (zzbre) zzbrk;
                    int zza9 = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i24 = zzbpr2.zzfld + zza9;
                    while (zza9 < i24) {
                        zzbre.zzfo(zzbpq.zzg(bArr2, zza9));
                        zza9 += 4;
                    }
                    if (zza9 == i24) {
                        return zza9;
                    }
                    throw zzbrl.zzanc();
                } else if (i15 == 5) {
                    zzbre zzbre2 = (zzbre) zzbrk;
                    zzbre2.zzfo(zzbpq.zzg(bArr, i));
                    int i25 = i12 + 4;
                    while (i25 < i13) {
                        int zza10 = zzbpq.zza(bArr2, i25, zzbpr2);
                        if (i14 != zzbpr2.zzfld) {
                            return i25;
                        }
                        zzbre2.zzfo(zzbpq.zzg(bArr2, zza10));
                        i25 = zza10 + 4;
                    }
                    return i25;
                }
                break;
            case 25:
            case 42:
                if (i15 == 2) {
                    zzbps zzbps = (zzbps) zzbrk;
                    int zza11 = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i26 = zzbpr2.zzfld + zza11;
                    while (zza11 < i26) {
                        zza11 = zzbpq.zzb(bArr2, zza11, zzbpr2);
                        zzbps.addBoolean(zzbpr2.zzfle != 0);
                    }
                    if (zza11 == i26) {
                        return zza11;
                    }
                    throw zzbrl.zzanc();
                } else if (i15 == 0) {
                    zzbps zzbps2 = (zzbps) zzbrk;
                    int zzb2 = zzbpq.zzb(bArr2, i12, zzbpr2);
                    zzbps2.addBoolean(zzbpr2.zzfle != 0);
                    while (zzb2 < i13) {
                        int zza12 = zzbpq.zza(bArr2, zzb2, zzbpr2);
                        if (i14 != zzbpr2.zzfld) {
                            return zzb2;
                        }
                        zzb2 = zzbpq.zzb(bArr2, zza12, zzbpr2);
                        zzbps2.addBoolean(zzbpr2.zzfle != 0);
                    }
                    return zzb2;
                }
                break;
            case 26:
                if (i15 == 2) {
                    if ((j & 536870912) == 0) {
                        int zza13 = zzbpq.zza(bArr2, i12, zzbpr2);
                        int i27 = zzbpr2.zzfld;
                        if (i27 >= 0) {
                            if (i27 == 0) {
                                zzbrk.add("");
                            } else {
                                zzbrk.add(new String(bArr2, zza13, i27, zzbrf.UTF_8));
                                zza13 += i27;
                            }
                            while (i9 < i13) {
                                int zza14 = zzbpq.zza(bArr2, i9, zzbpr2);
                                if (i14 != zzbpr2.zzfld) {
                                    return i9;
                                }
                                i9 = zzbpq.zza(bArr2, zza14, zzbpr2);
                                int i28 = zzbpr2.zzfld;
                                if (i28 < 0) {
                                    throw zzbrl.zzand();
                                } else if (i28 == 0) {
                                    zzbrk.add("");
                                } else {
                                    zzbrk.add(new String(bArr2, i9, i28, zzbrf.UTF_8));
                                    i9 += i28;
                                }
                            }
                            return i9;
                        }
                        throw zzbrl.zzand();
                    }
                    int zza15 = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i29 = zzbpr2.zzfld;
                    if (i29 >= 0) {
                        if (i29 == 0) {
                            zzbrk.add("");
                        } else {
                            int i30 = zza15 + i29;
                            if (zzbuc.zzm(bArr2, zza15, i30)) {
                                zzbrk.add(new String(bArr2, zza15, i29, zzbrf.UTF_8));
                                zza15 = i30;
                            } else {
                                throw zzbrl.zzank();
                            }
                        }
                        while (i8 < i13) {
                            int zza16 = zzbpq.zza(bArr2, i8, zzbpr2);
                            if (i14 != zzbpr2.zzfld) {
                                return i8;
                            }
                            i8 = zzbpq.zza(bArr2, zza16, zzbpr2);
                            int i31 = zzbpr2.zzfld;
                            if (i31 < 0) {
                                throw zzbrl.zzand();
                            } else if (i31 == 0) {
                                zzbrk.add("");
                            } else {
                                int i32 = i8 + i31;
                                if (zzbuc.zzm(bArr2, i8, i32)) {
                                    zzbrk.add(new String(bArr2, i8, i31, zzbrf.UTF_8));
                                    i8 = i32;
                                } else {
                                    throw zzbrl.zzank();
                                }
                            }
                        }
                        return i8;
                    }
                    throw zzbrl.zzand();
                }
                break;
            case 27:
                if (i15 == 2) {
                    return zza(zzfq(i16), i3, bArr, i, i2, zzbrk, zzbpr);
                }
                break;
            case 28:
                if (i15 == 2) {
                    int zza17 = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i33 = zzbpr2.zzfld;
                    if (i33 < 0) {
                        throw zzbrl.zzand();
                    } else if (i33 <= bArr2.length - zza17) {
                        if (i33 == 0) {
                            zzbrk.add(zzbpu.zzfli);
                        } else {
                            zzbrk.add(zzbpu.zzi(bArr2, zza17, i33));
                            zza17 += i33;
                        }
                        while (i10 < i13) {
                            int zza18 = zzbpq.zza(bArr2, i10, zzbpr2);
                            if (i14 != zzbpr2.zzfld) {
                                return i10;
                            }
                            i10 = zzbpq.zza(bArr2, zza18, zzbpr2);
                            int i34 = zzbpr2.zzfld;
                            if (i34 < 0) {
                                throw zzbrl.zzand();
                            } else if (i34 > bArr2.length - i10) {
                                throw zzbrl.zzanc();
                            } else if (i34 == 0) {
                                zzbrk.add(zzbpu.zzfli);
                            } else {
                                zzbrk.add(zzbpu.zzi(bArr2, i10, i34));
                                i10 += i34;
                            }
                        }
                        return i10;
                    } else {
                        throw zzbrl.zzanc();
                    }
                }
                break;
            case 30:
            case 44:
                if (i15 == 2) {
                    i11 = zzbpq.zza(bArr2, i12, zzbrk, zzbpr2);
                } else if (i15 == 0) {
                    i11 = zzbpq.zza(i3, bArr, i, i2, zzbrk, zzbpr);
                }
                zzbrd zzbrd = (zzbrd) t2;
                zzbtv zzbtv = zzbrd.zzfpu;
                if (zzbtv == zzbtv.zzaoz()) {
                    zzbtv = null;
                }
                zzbtv zzbtv2 = (zzbtv) zzbte.zza(i4, zzbrk, zzfs(i16), zzbtv, this.zzfsw);
                if (zzbtv2 != null) {
                    zzbrd.zzfpu = zzbtv2;
                }
                return i11;
            case 33:
            case 47:
                if (i15 == 2) {
                    zzbre zzbre3 = (zzbre) zzbrk;
                    int zza19 = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i35 = zzbpr2.zzfld + zza19;
                    while (zza19 < i35) {
                        zza19 = zzbpq.zza(bArr2, zza19, zzbpr2);
                        zzbre3.zzfo(zzbqf.zzeu(zzbpr2.zzfld));
                    }
                    if (zza19 == i35) {
                        return zza19;
                    }
                    throw zzbrl.zzanc();
                } else if (i15 == 0) {
                    zzbre zzbre4 = (zzbre) zzbrk;
                    int zza20 = zzbpq.zza(bArr2, i12, zzbpr2);
                    zzbre4.zzfo(zzbqf.zzeu(zzbpr2.zzfld));
                    while (zza20 < i13) {
                        int zza21 = zzbpq.zza(bArr2, zza20, zzbpr2);
                        if (i14 != zzbpr2.zzfld) {
                            return zza20;
                        }
                        zza20 = zzbpq.zza(bArr2, zza21, zzbpr2);
                        zzbre4.zzfo(zzbqf.zzeu(zzbpr2.zzfld));
                    }
                    return zza20;
                }
                break;
            case 34:
            case 48:
                if (i15 == 2) {
                    zzbrz zzbrz5 = (zzbrz) zzbrk;
                    int zza22 = zzbpq.zza(bArr2, i12, zzbpr2);
                    int i36 = zzbpr2.zzfld + zza22;
                    while (zza22 < i36) {
                        zza22 = zzbpq.zzb(bArr2, zza22, zzbpr2);
                        zzbrz5.zzbj(zzbqf.zzax(zzbpr2.zzfle));
                    }
                    if (zza22 == i36) {
                        return zza22;
                    }
                    throw zzbrl.zzanc();
                } else if (i15 == 0) {
                    zzbrz zzbrz6 = (zzbrz) zzbrk;
                    int zzb3 = zzbpq.zzb(bArr2, i12, zzbpr2);
                    zzbrz6.zzbj(zzbqf.zzax(zzbpr2.zzfle));
                    while (zzb3 < i13) {
                        int zza23 = zzbpq.zza(bArr2, zzb3, zzbpr2);
                        if (i14 != zzbpr2.zzfld) {
                            return zzb3;
                        }
                        zzb3 = zzbpq.zzb(bArr2, zza23, zzbpr2);
                        zzbrz6.zzbj(zzbqf.zzax(zzbpr2.zzfle));
                    }
                    return zzb3;
                }
                break;
            case 49:
                if (i15 == 3) {
                    zzbtc zzfq = zzfq(i16);
                    int i37 = (i14 & -8) | 4;
                    int zza24 = zza(zzfq, bArr, i, i2, i37, zzbpr);
                    zzbrk.add(zzbpr2.zzflf);
                    while (zza24 < i13) {
                        int zza25 = zzbpq.zza(bArr2, zza24, zzbpr2);
                        if (i14 != zzbpr2.zzfld) {
                            return zza24;
                        }
                        zza24 = zza(zzfq, bArr, zza25, i2, i37, zzbpr);
                        zzbrk.add(zzbpr2.zzflf);
                    }
                    return zza24;
                }
                break;
        }
        return i12;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r8, byte[] r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.ads.zzbpr r15) throws java.io.IOException {
        /*
            r7 = this;
            sun.misc.Unsafe r0 = zzfsh
            java.lang.Object r12 = r7.zzfr(r12)
            java.lang.Object r1 = r0.getObject(r8, r13)
            com.google.android.gms.internal.ads.zzbsg r2 = r7.zzfsy
            boolean r2 = r2.zzy(r1)
            if (r2 == 0) goto L_0x0021
            com.google.android.gms.internal.ads.zzbsg r2 = r7.zzfsy
            java.lang.Object r2 = r2.zzaa(r12)
            com.google.android.gms.internal.ads.zzbsg r3 = r7.zzfsy
            r3.zzc(r2, r1)
            r0.putObject(r8, r13, r2)
            r1 = r2
        L_0x0021:
            com.google.android.gms.internal.ads.zzbsg r8 = r7.zzfsy
            com.google.android.gms.internal.ads.zzbse r8 = r8.zzab(r12)
            com.google.android.gms.internal.ads.zzbsg r12 = r7.zzfsy
            java.util.Map r12 = r12.zzw(r1)
            int r10 = com.google.android.gms.internal.ads.zzbpq.zza(r9, r10, r15)
            int r13 = r15.zzfld
            if (r13 < 0) goto L_0x0096
            int r14 = r11 - r10
            if (r13 > r14) goto L_0x0096
            int r13 = r13 + r10
            K r14 = r8.zzfsa
            V r0 = r8.zzfsc
        L_0x003e:
            if (r10 >= r13) goto L_0x008b
            int r1 = r10 + 1
            byte r10 = r9[r10]
            if (r10 >= 0) goto L_0x004e
            int r1 = com.google.android.gms.internal.ads.zzbpq.zza(r10, r9, r1, r15)
            int r10 = r15.zzfld
            r2 = r1
            goto L_0x004f
        L_0x004e:
            r2 = r1
        L_0x004f:
            int r1 = r10 >>> 3
            r3 = r10 & 7
            switch(r1) {
                case 1: goto L_0x0071;
                case 2: goto L_0x0057;
                default: goto L_0x0056;
            }
        L_0x0056:
            goto L_0x0086
        L_0x0057:
            com.google.android.gms.internal.ads.zzbuj r1 = r8.zzfsb
            int r1 = r1.zzapk()
            if (r3 != r1) goto L_0x0086
            com.google.android.gms.internal.ads.zzbuj r4 = r8.zzfsb
            V r10 = r8.zzfsc
            java.lang.Class r5 = r10.getClass()
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza(r1, r2, r3, r4, r5, r6)
            java.lang.Object r0 = r15.zzflf
            goto L_0x003e
        L_0x0071:
            com.google.android.gms.internal.ads.zzbuj r1 = r8.zzfrz
            int r1 = r1.zzapk()
            if (r3 != r1) goto L_0x0086
            com.google.android.gms.internal.ads.zzbuj r4 = r8.zzfrz
            r5 = 0
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza(r1, r2, r3, r4, r5, r6)
            java.lang.Object r14 = r15.zzflf
            goto L_0x003e
        L_0x0086:
            int r10 = com.google.android.gms.internal.ads.zzbpq.zza(r10, r9, r2, r11, r15)
            goto L_0x003e
        L_0x008b:
            if (r10 != r13) goto L_0x0091
            r12.put(r14, r0)
            return r13
        L_0x0091:
            com.google.android.gms.internal.ads.zzbrl r8 = com.google.android.gms.internal.ads.zzbrl.zzanj()
            throw r8
        L_0x0096:
            com.google.android.gms.internal.ads.zzbrl r8 = com.google.android.gms.internal.ads.zzbrl.zzanc()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.ads.zzbpr):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01a1, code lost:
        r12.putInt(r1, r13, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, com.google.android.gms.internal.ads.zzbpr r29) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r2 = r21
            r8 = r22
            r5 = r23
            r9 = r26
            r6 = r28
            r11 = r29
            sun.misc.Unsafe r12 = zzfsh
            int[] r7 = r0.zzfsi
            int r13 = r6 + 2
            r7 = r7[r13]
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r7 & r13
            long r13 = (long) r7
            r7 = 5
            r15 = 2
            switch(r25) {
                case 51: goto L_0x0191;
                case 52: goto L_0x0181;
                case 53: goto L_0x0171;
                case 54: goto L_0x0171;
                case 55: goto L_0x0161;
                case 56: goto L_0x0150;
                case 57: goto L_0x0140;
                case 58: goto L_0x0127;
                case 59: goto L_0x00f3;
                case 60: goto L_0x00c5;
                case 61: goto L_0x00b8;
                case 62: goto L_0x0161;
                case 63: goto L_0x008a;
                case 64: goto L_0x0140;
                case 65: goto L_0x0150;
                case 66: goto L_0x0075;
                case 67: goto L_0x0060;
                case 68: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x01a5
        L_0x0028:
            r7 = 3
            if (r5 != r7) goto L_0x01a5
            r2 = r2 & -8
            r7 = r2 | 4
            com.google.android.gms.internal.ads.zzbtc r2 = r0.zzfq(r6)
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r7
            r7 = r29
            int r2 = zza(r2, r3, r4, r5, r6, r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x004b
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x004c
        L_0x004b:
            r15 = 0
        L_0x004c:
            if (r15 != 0) goto L_0x0055
            java.lang.Object r3 = r11.zzflf
            r12.putObject(r1, r9, r3)
            goto L_0x01a1
        L_0x0055:
            java.lang.Object r3 = r11.zzflf
            java.lang.Object r3 = com.google.android.gms.internal.ads.zzbrf.zzb(r15, r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a1
        L_0x0060:
            if (r5 != 0) goto L_0x01a5
            int r2 = com.google.android.gms.internal.ads.zzbpq.zzb(r3, r4, r11)
            long r3 = r11.zzfle
            long r3 = com.google.android.gms.internal.ads.zzbqf.zzax(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a1
        L_0x0075:
            if (r5 != 0) goto L_0x01a5
            int r2 = com.google.android.gms.internal.ads.zzbpq.zza(r3, r4, r11)
            int r3 = r11.zzfld
            int r3 = com.google.android.gms.internal.ads.zzbqf.zzeu(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a1
        L_0x008a:
            if (r5 != 0) goto L_0x01a5
            int r3 = com.google.android.gms.internal.ads.zzbpq.zza(r3, r4, r11)
            int r4 = r11.zzfld
            com.google.android.gms.internal.ads.zzbri r5 = r0.zzfs(r6)
            if (r5 == 0) goto L_0x00ae
            boolean r5 = r5.zzcb(r4)
            if (r5 == 0) goto L_0x009f
            goto L_0x00ae
        L_0x009f:
            com.google.android.gms.internal.ads.zzbtv r1 = zzad(r17)
            long r4 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r1.zzc(r2, r4)
            r2 = r3
            goto L_0x01a6
        L_0x00ae:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r12.putObject(r1, r9, r2)
            r2 = r3
            goto L_0x01a1
        L_0x00b8:
            if (r5 != r15) goto L_0x01a5
            int r2 = com.google.android.gms.internal.ads.zzbpq.zze(r3, r4, r11)
            java.lang.Object r3 = r11.zzflf
            r12.putObject(r1, r9, r3)
            goto L_0x01a1
        L_0x00c5:
            if (r5 != r15) goto L_0x01a5
            com.google.android.gms.internal.ads.zzbtc r2 = r0.zzfq(r6)
            r5 = r20
            int r2 = zza(r2, r3, r4, r5, r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00dc
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00dd
        L_0x00dc:
            r15 = 0
        L_0x00dd:
            if (r15 != 0) goto L_0x00e5
            java.lang.Object r3 = r11.zzflf
            r12.putObject(r1, r9, r3)
            goto L_0x00ee
        L_0x00e5:
            java.lang.Object r3 = r11.zzflf
            java.lang.Object r3 = com.google.android.gms.internal.ads.zzbrf.zzb(r15, r3)
            r12.putObject(r1, r9, r3)
        L_0x00ee:
            r12.putInt(r1, r13, r8)
            goto L_0x01a6
        L_0x00f3:
            if (r5 != r15) goto L_0x01a5
            int r2 = com.google.android.gms.internal.ads.zzbpq.zza(r3, r4, r11)
            int r4 = r11.zzfld
            if (r4 != 0) goto L_0x0103
            java.lang.String r3 = ""
            r12.putObject(r1, r9, r3)
            goto L_0x0122
        L_0x0103:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r24 & r5
            if (r5 == 0) goto L_0x0117
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.ads.zzbuc.zzm(r3, r2, r5)
            if (r5 == 0) goto L_0x0112
            goto L_0x0117
        L_0x0112:
            com.google.android.gms.internal.ads.zzbrl r1 = com.google.android.gms.internal.ads.zzbrl.zzank()
            throw r1
        L_0x0117:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.ads.zzbrf.UTF_8
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            int r2 = r2 + r4
        L_0x0122:
            r12.putInt(r1, r13, r8)
            goto L_0x01a6
        L_0x0127:
            if (r5 != 0) goto L_0x01a5
            int r2 = com.google.android.gms.internal.ads.zzbpq.zzb(r3, r4, r11)
            long r3 = r11.zzfle
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0137
            r15 = 1
            goto L_0x0138
        L_0x0137:
            r15 = 0
        L_0x0138:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r15)
            r12.putObject(r1, r9, r3)
            goto L_0x01a1
        L_0x0140:
            if (r5 != r7) goto L_0x01a5
            int r2 = com.google.android.gms.internal.ads.zzbpq.zzg(r18, r19)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x01a1
        L_0x0150:
            r2 = 1
            if (r5 != r2) goto L_0x01a5
            long r2 = com.google.android.gms.internal.ads.zzbpq.zzh(r18, r19)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 8
            goto L_0x01a1
        L_0x0161:
            if (r5 != 0) goto L_0x01a5
            int r2 = com.google.android.gms.internal.ads.zzbpq.zza(r3, r4, r11)
            int r3 = r11.zzfld
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a1
        L_0x0171:
            if (r5 != 0) goto L_0x01a5
            int r2 = com.google.android.gms.internal.ads.zzbpq.zzb(r3, r4, r11)
            long r3 = r11.zzfle
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a1
        L_0x0181:
            if (r5 != r7) goto L_0x01a5
            float r2 = com.google.android.gms.internal.ads.zzbpq.zzj(r18, r19)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x01a1
        L_0x0191:
            r2 = 1
            if (r5 != r2) goto L_0x01a5
            double r2 = com.google.android.gms.internal.ads.zzbpq.zzi(r18, r19)
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 8
        L_0x01a1:
            r12.putInt(r1, r13, r8)
            goto L_0x01a6
        L_0x01a5:
            r2 = r4
        L_0x01a6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.ads.zzbpr):int");
    }

    private final zzbtc zzfq(int i) {
        int i2 = (i / 3) << 1;
        zzbtc zzbtc = (zzbtc) this.zzfsj[i2];
        if (zzbtc != null) {
            return zzbtc;
        }
        zzbtc zzf = zzbsy.zzaog().zzf((Class) this.zzfsj[i2 + 1]);
        this.zzfsj[i2] = zzf;
        return zzf;
    }

    private final Object zzfr(int i) {
        return this.zzfsj[(i / 3) << 1];
    }

    private final zzbri zzfs(int i) {
        return (zzbri) this.zzfsj[((i / 3) << 1) + 1];
    }

    /* JADX WARNING: type inference failed for: r29v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v11, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r5v4, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r0v14, types: [int] */
    /* JADX WARNING: type inference failed for: r1v10, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r19v1 */
    /* JADX WARNING: type inference failed for: r8v3 */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r25v0 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r25v1 */
    /* JADX WARNING: type inference failed for: r2v10, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v8, types: [int] */
    /* JADX WARNING: type inference failed for: r7v11 */
    /* JADX WARNING: type inference failed for: r12v7 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r7v13 */
    /* JADX WARNING: type inference failed for: r2v13, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r7v16 */
    /* JADX WARNING: type inference failed for: r2v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v11, types: [int] */
    /* JADX WARNING: type inference failed for: r25v2 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r7v19 */
    /* JADX WARNING: type inference failed for: r25v3 */
    /* JADX WARNING: type inference failed for: r1v24, types: [int] */
    /* JADX WARNING: type inference failed for: r2v19, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r8v8 */
    /* JADX WARNING: type inference failed for: r7v21 */
    /* JADX WARNING: type inference failed for: r8v9 */
    /* JADX WARNING: type inference failed for: r12v11, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r8v10 */
    /* JADX WARNING: type inference failed for: r12v12, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r8v11 */
    /* JADX WARNING: type inference failed for: r12v13, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v19 */
    /* JADX WARNING: type inference failed for: r8v12 */
    /* JADX WARNING: type inference failed for: r12v14, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: type inference failed for: r12v15, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v21 */
    /* JADX WARNING: type inference failed for: r8v14 */
    /* JADX WARNING: type inference failed for: r12v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v22 */
    /* JADX WARNING: type inference failed for: r8v15 */
    /* JADX WARNING: type inference failed for: r12v17, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v23 */
    /* JADX WARNING: type inference failed for: r8v16 */
    /* JADX WARNING: type inference failed for: r12v18, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v24 */
    /* JADX WARNING: type inference failed for: r8v17 */
    /* JADX WARNING: type inference failed for: r12v19, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v25 */
    /* JADX WARNING: type inference failed for: r8v18 */
    /* JADX WARNING: type inference failed for: r12v20, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v26 */
    /* JADX WARNING: type inference failed for: r8v19, types: [int] */
    /* JADX WARNING: type inference failed for: r12v21, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v27 */
    /* JADX WARNING: type inference failed for: r3v29 */
    /* JADX WARNING: type inference failed for: r8v20 */
    /* JADX WARNING: type inference failed for: r12v22, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v30 */
    /* JADX WARNING: type inference failed for: r8v21 */
    /* JADX WARNING: type inference failed for: r12v23 */
    /* JADX WARNING: type inference failed for: r12v24, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v31 */
    /* JADX WARNING: type inference failed for: r8v23 */
    /* JADX WARNING: type inference failed for: r12v25 */
    /* JADX WARNING: type inference failed for: r1v70, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v25 */
    /* JADX WARNING: type inference failed for: r3v33 */
    /* JADX WARNING: type inference failed for: r12v26 */
    /* JADX WARNING: type inference failed for: r8v26 */
    /* JADX WARNING: type inference failed for: r12v27 */
    /* JADX WARNING: type inference failed for: r7v23 */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r3v34, types: [int] */
    /* JADX WARNING: type inference failed for: r5v19 */
    /* JADX WARNING: type inference failed for: r12v28 */
    /* JADX WARNING: type inference failed for: r3v35 */
    /* JADX WARNING: type inference failed for: r12v29 */
    /* JADX WARNING: type inference failed for: r12v30 */
    /* JADX WARNING: type inference failed for: r12v31 */
    /* JADX WARNING: type inference failed for: r12v32 */
    /* JADX WARNING: type inference failed for: r8v28 */
    /* JADX WARNING: type inference failed for: r12v33 */
    /* JADX WARNING: type inference failed for: r8v29 */
    /* JADX WARNING: type inference failed for: r12v34 */
    /* JADX WARNING: type inference failed for: r8v30 */
    /* JADX WARNING: type inference failed for: r12v35 */
    /* JADX WARNING: type inference failed for: r8v31 */
    /* JADX WARNING: type inference failed for: r12v36 */
    /* JADX WARNING: type inference failed for: r8v32 */
    /* JADX WARNING: type inference failed for: r12v37 */
    /* JADX WARNING: type inference failed for: r8v33 */
    /* JADX WARNING: type inference failed for: r12v38 */
    /* JADX WARNING: type inference failed for: r8v34 */
    /* JADX WARNING: type inference failed for: r12v39 */
    /* JADX WARNING: type inference failed for: r12v40 */
    /* JADX WARNING: type inference failed for: r8v35 */
    /* JADX WARNING: type inference failed for: r12v41 */
    /* JADX WARNING: type inference failed for: r8v36 */
    /* JADX WARNING: type inference failed for: r12v42 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v11, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r29v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
  assigns: []
  uses: []
  mth insns count: 633
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 50 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r28, byte[] r29, int r30, int r31, int r32, com.google.android.gms.internal.ads.zzbpr r33) throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r33
            sun.misc.Unsafe r10 = zzfsh
            r16 = 0
            r0 = r30
            r1 = -1
            r2 = 0
            r3 = 0
            r6 = 0
            r7 = -1
        L_0x0017:
            if (r0 >= r13) goto L_0x04d7
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0028
            int r0 = com.google.android.gms.internal.ads.zzbpq.zza(r0, r12, r3, r9)
            int r3 = r9.zzfld
            r4 = r0
            r5 = r3
            goto L_0x002a
        L_0x0028:
            r5 = r0
            r4 = r3
        L_0x002a:
            int r3 = r5 >>> 3
            r0 = r5 & 7
            r8 = 3
            if (r3 <= r1) goto L_0x0039
            int r2 = r2 / r8
            int r1 = r15.zzai(r3, r2)
            r2 = r1
            r1 = -1
            goto L_0x003f
        L_0x0039:
            int r1 = r15.zzfw(r3)
            r2 = r1
            r1 = -1
        L_0x003f:
            if (r2 != r1) goto L_0x0050
            r18 = r3
            r2 = r4
            r17 = r6
            r24 = r7
            r26 = r10
            r6 = r11
            r19 = 0
            r7 = r5
            goto L_0x048b
        L_0x0050:
            int[] r1 = r15.zzfsi
            int r18 = r2 + 1
            r8 = r1[r18]
            r18 = 267386880(0xff00000, float:2.3665827E-29)
            r18 = r8 & r18
            int r11 = r18 >>> 20
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r5
            r5 = r8 & r18
            long r12 = (long) r5
            r5 = 17
            r20 = r8
            if (r11 > r5) goto L_0x035e
            int r5 = r2 + 2
            r1 = r1[r5]
            int r5 = r1 >>> 20
            r8 = 1
            int r22 = r8 << r5
            r1 = r1 & r18
            if (r1 == r7) goto L_0x0085
            r5 = -1
            if (r7 == r5) goto L_0x007e
            long r8 = (long) r7
            r10.putInt(r14, r8, r6)
        L_0x007e:
            long r6 = (long) r1
            int r6 = r10.getInt(r14, r6)
            r7 = r1
            goto L_0x0086
        L_0x0085:
            r5 = -1
        L_0x0086:
            r1 = 5
            switch(r11) {
                case 0: goto L_0x032a;
                case 1: goto L_0x0305;
                case 2: goto L_0x02dc;
                case 3: goto L_0x02dc;
                case 4: goto L_0x02b7;
                case 5: goto L_0x0287;
                case 6: goto L_0x0260;
                case 7: goto L_0x0230;
                case 8: goto L_0x01fd;
                case 9: goto L_0x01bd;
                case 10: goto L_0x0195;
                case 11: goto L_0x02b7;
                case 12: goto L_0x014a;
                case 13: goto L_0x0260;
                case 14: goto L_0x0287;
                case 15: goto L_0x011f;
                case 16: goto L_0x00ee;
                case 17: goto L_0x0098;
                default: goto L_0x008a;
            }
        L_0x008a:
            r9 = r2
            r18 = r3
            r13 = r4
            r8 = r19
            r11 = r33
            r12 = r29
            r19 = -1
            goto L_0x0350
        L_0x0098:
            r8 = 3
            if (r0 != r8) goto L_0x00e0
            int r0 = r3 << 3
            r8 = r0 | 4
            com.google.android.gms.internal.ads.zzbtc r0 = r15.zzfq(r2)
            r1 = r29
            r9 = r2
            r2 = r4
            r18 = r3
            r3 = r31
            r4 = r8
            r8 = r19
            r19 = -1
            r5 = r33
            int r0 = zza(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x00c2
            r11 = r33
            java.lang.Object r1 = r11.zzflf
            r10.putObject(r14, r12, r1)
            goto L_0x00d1
        L_0x00c2:
            r11 = r33
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r11.zzflf
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r2)
            r10.putObject(r14, r12, r1)
        L_0x00d1:
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            r12 = r29
            r13 = r31
            goto L_0x0017
        L_0x00e0:
            r9 = r2
            r18 = r3
            r8 = r19
            r11 = r33
            r19 = -1
            r13 = r4
            r12 = r29
            goto L_0x0350
        L_0x00ee:
            r9 = r2
            r18 = r3
            r8 = r19
            r11 = r33
            r19 = -1
            if (r0 != 0) goto L_0x011a
            r2 = r12
            r12 = r29
            int r13 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r4, r11)
            long r0 = r11.zzfle
            long r4 = com.google.android.gms.internal.ads.zzbqf.zzax(r0)
            r0 = r10
            r1 = r28
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r0 = r13
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x011a:
            r12 = r29
            r13 = r4
            goto L_0x0350
        L_0x011f:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r11 = r33
            r12 = r29
            r19 = -1
            if (r0 != 0) goto L_0x0147
            int r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r4, r11)
            int r1 = r11.zzfld
            int r1 = com.google.android.gms.internal.ads.zzbqf.zzeu(r1)
            r10.putInt(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x0147:
            r13 = r4
            goto L_0x0350
        L_0x014a:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r11 = r33
            r12 = r29
            r19 = -1
            if (r0 != 0) goto L_0x0192
            int r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r4, r11)
            int r1 = r11.zzfld
            com.google.android.gms.internal.ads.zzbri r4 = r15.zzfs(r9)
            if (r4 == 0) goto L_0x0182
            boolean r4 = r4.zzcb(r1)
            if (r4 == 0) goto L_0x016b
            goto L_0x0182
        L_0x016b:
            com.google.android.gms.internal.ads.zzbtv r2 = zzad(r28)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzc(r8, r1)
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x0182:
            r10.putInt(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x0192:
            r13 = r4
            goto L_0x0350
        L_0x0195:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r11 = r33
            r12 = r29
            r19 = -1
            if (r0 != r1) goto L_0x01ba
            int r0 = com.google.android.gms.internal.ads.zzbpq.zze(r12, r4, r11)
            java.lang.Object r1 = r11.zzflf
            r10.putObject(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x01ba:
            r13 = r4
            goto L_0x0350
        L_0x01bd:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r11 = r33
            r12 = r29
            r19 = -1
            if (r0 != r1) goto L_0x01f8
            com.google.android.gms.internal.ads.zzbtc r0 = r15.zzfq(r9)
            r13 = r31
            int r0 = zza(r0, r12, r4, r13, r11)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x01e0
            java.lang.Object r1 = r11.zzflf
            r10.putObject(r14, r2, r1)
            goto L_0x01ed
        L_0x01e0:
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r11.zzflf
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r4)
            r10.putObject(r14, r2, r1)
        L_0x01ed:
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            goto L_0x0017
        L_0x01f8:
            r13 = r31
            r13 = r4
            goto L_0x0350
        L_0x01fd:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r11 = r33
            r12 = r29
            r13 = r31
            r19 = -1
            if (r0 != r1) goto L_0x022d
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r20 & r0
            if (r0 != 0) goto L_0x0219
            int r0 = com.google.android.gms.internal.ads.zzbpq.zzc(r12, r4, r11)
            goto L_0x021d
        L_0x0219:
            int r0 = com.google.android.gms.internal.ads.zzbpq.zzd(r12, r4, r11)
        L_0x021d:
            java.lang.Object r1 = r11.zzflf
            r10.putObject(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            goto L_0x0017
        L_0x022d:
            r13 = r4
            goto L_0x0350
        L_0x0230:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r11 = r33
            r12 = r29
            r13 = r31
            r19 = -1
            if (r0 != 0) goto L_0x025d
            int r0 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r4, r11)
            long r4 = r11.zzfle
            r20 = 0
            int r1 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r1 == 0) goto L_0x024e
            r1 = 1
            goto L_0x024f
        L_0x024e:
            r1 = 0
        L_0x024f:
            com.google.android.gms.internal.ads.zzbua.zza(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            goto L_0x0017
        L_0x025d:
            r13 = r4
            goto L_0x0350
        L_0x0260:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r11 = r33
            r12 = r29
            r13 = r31
            r19 = -1
            if (r0 != r1) goto L_0x0284
            int r0 = com.google.android.gms.internal.ads.zzbpq.zzg(r12, r4)
            r10.putInt(r14, r2, r0)
            int r0 = r4 + 4
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            goto L_0x0017
        L_0x0284:
            r13 = r4
            goto L_0x0350
        L_0x0287:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 1
            r11 = r33
            r12 = r29
            r13 = r31
            r19 = -1
            if (r0 != r1) goto L_0x02b4
            long r20 = com.google.android.gms.internal.ads.zzbpq.zzh(r12, r4)
            r0 = r10
            r1 = r28
            r13 = r4
            r4 = r20
            r0.putLong(r1, r2, r4)
            int r0 = r13 + 8
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x02b4:
            r13 = r4
            goto L_0x0350
        L_0x02b7:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r11 = r33
            r12 = r29
            r19 = -1
            r13 = r4
            if (r0 != 0) goto L_0x0350
            int r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r13, r11)
            int r1 = r11.zzfld
            r10.putInt(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x02dc:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r11 = r33
            r12 = r29
            r19 = -1
            r13 = r4
            if (r0 != 0) goto L_0x0350
            int r13 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r13, r11)
            long r4 = r11.zzfle
            r0 = r10
            r1 = r28
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r0 = r13
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x0305:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r11 = r33
            r12 = r29
            r19 = -1
            r13 = r4
            if (r0 != r1) goto L_0x0350
            float r0 = com.google.android.gms.internal.ads.zzbpq.zzj(r12, r13)
            com.google.android.gms.internal.ads.zzbua.zza(r14, r2, r0)
            int r0 = r13 + 4
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x032a:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 1
            r11 = r33
            r12 = r29
            r19 = -1
            r13 = r4
            if (r0 != r1) goto L_0x0350
            double r0 = com.google.android.gms.internal.ads.zzbpq.zzi(r12, r13)
            com.google.android.gms.internal.ads.zzbua.zza(r14, r2, r0)
            int r0 = r13 + 8
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
            r11 = r32
            r13 = r31
            goto L_0x0017
        L_0x0350:
            r17 = r6
            r24 = r7
            r7 = r8
            r19 = r9
            r26 = r10
            r2 = r13
            r6 = r32
            goto L_0x048b
        L_0x035e:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r12 = r29
            r19 = -1
            r13 = r4
            r1 = 27
            if (r11 != r1) goto L_0x03be
            r1 = 2
            if (r0 != r1) goto L_0x03b1
            java.lang.Object r0 = r10.getObject(r14, r2)
            com.google.android.gms.internal.ads.zzbrk r0 = (com.google.android.gms.internal.ads.zzbrk) r0
            boolean r1 = r0.zzaki()
            if (r1 != 0) goto L_0x0390
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0385
            r1 = 10
            goto L_0x0387
        L_0x0385:
            int r1 = r1 << 1
        L_0x0387:
            com.google.android.gms.internal.ads.zzbrk r0 = r0.zzel(r1)
            r10.putObject(r14, r2, r0)
            r5 = r0
            goto L_0x0391
        L_0x0390:
            r5 = r0
        L_0x0391:
            com.google.android.gms.internal.ads.zzbtc r0 = r15.zzfq(r9)
            r1 = r8
            r2 = r29
            r3 = r13
            r4 = r31
            r17 = r6
            r6 = r33
            int r0 = zza(r0, r1, r2, r3, r4, r5, r6)
            r3 = r8
            r2 = r9
            r6 = r17
            r1 = r18
            r11 = r32
            r13 = r31
            r9 = r33
            goto L_0x0017
        L_0x03b1:
            r17 = r6
            r24 = r7
            r25 = r8
            r19 = r9
            r26 = r10
            r15 = r13
            goto L_0x0463
        L_0x03be:
            r17 = r6
            r1 = 49
            if (r11 > r1) goto L_0x0416
            r6 = r20
            long r5 = (long) r6
            r4 = r0
            r0 = r27
            r1 = r28
            r22 = r2
            r2 = r29
            r3 = r13
            r30 = r4
            r4 = r31
            r20 = r5
            r5 = r8
            r6 = r18
            r24 = r7
            r7 = r30
            r25 = r8
            r15 = -1
            r8 = r9
            r19 = r9
            r26 = r10
            r9 = r20
            r15 = r32
            r15 = r13
            r12 = r22
            r14 = r33
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x03fc
            r2 = r0
            r7 = r25
            r6 = r32
            goto L_0x048b
        L_0x03fc:
            r12 = r29
            r9 = r33
            r6 = r17
            r1 = r18
            r2 = r19
            r7 = r24
            r3 = r25
            r10 = r26
            r11 = r32
            r13 = r31
            r14 = r28
            r15 = r27
            goto L_0x0017
        L_0x0416:
            r30 = r0
            r22 = r2
            r24 = r7
            r25 = r8
            r19 = r9
            r26 = r10
            r15 = r13
            r6 = r20
            r0 = 50
            if (r11 != r0) goto L_0x0469
            r7 = r30
            r0 = 2
            if (r7 != r0) goto L_0x0463
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r19
            r6 = r22
            r8 = r33
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x0449
            r2 = r0
            r7 = r25
            r6 = r32
            goto L_0x048b
        L_0x0449:
            r12 = r29
            r9 = r33
            r6 = r17
            r1 = r18
            r2 = r19
            r7 = r24
            r3 = r25
            r10 = r26
            r11 = r32
            r13 = r31
            r14 = r28
            r15 = r27
            goto L_0x0017
        L_0x0463:
            r2 = r15
            r7 = r25
            r6 = r32
            goto L_0x048b
        L_0x0469:
            r7 = r30
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r25
            r8 = r6
            r6 = r18
            r9 = r11
            r10 = r22
            r12 = r19
            r13 = r33
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x04bc
            r2 = r0
            r7 = r25
            r6 = r32
        L_0x048b:
            if (r7 != r6) goto L_0x0497
            if (r6 != 0) goto L_0x0490
            goto L_0x0497
        L_0x0490:
            r3 = r7
            r1 = r17
            r0 = r24
            r4 = -1
            goto L_0x04e4
        L_0x0497:
            r0 = r7
            r1 = r29
            r3 = r31
            r4 = r28
            r5 = r33
            int r0 = zza(r0, r1, r2, r3, r4, r5)
            r12 = r29
            r9 = r33
            r11 = r6
            r3 = r7
            r6 = r17
            r1 = r18
            r2 = r19
            r7 = r24
            r10 = r26
            r13 = r31
            r14 = r28
            r15 = r27
            goto L_0x0017
        L_0x04bc:
            r7 = r25
            r12 = r29
            r9 = r33
            r3 = r7
            r6 = r17
            r1 = r18
            r2 = r19
            r7 = r24
            r10 = r26
            r11 = r32
            r13 = r31
            r14 = r28
            r15 = r27
            goto L_0x0017
        L_0x04d7:
            r17 = r6
            r24 = r7
            r26 = r10
            r6 = r11
            r2 = r0
            r1 = r17
            r0 = r24
            r4 = -1
        L_0x04e4:
            if (r0 == r4) goto L_0x04ef
            long r4 = (long) r0
            r7 = r26
            r0 = r28
            r7.putInt(r0, r4, r1)
            goto L_0x04f1
        L_0x04ef:
            r0 = r28
        L_0x04f1:
            r1 = 0
            r4 = r27
            int r5 = r4.zzfss
        L_0x04f6:
            int r7 = r4.zzfst
            if (r5 >= r7) goto L_0x0509
            int[] r7 = r4.zzfsr
            r7 = r7[r5]
            com.google.android.gms.internal.ads.zzbtu<?, ?> r8 = r4.zzfsw
            java.lang.Object r1 = r4.zza(r0, r7, (UB) r1, r8)
            com.google.android.gms.internal.ads.zzbtv r1 = (com.google.android.gms.internal.ads.zzbtv) r1
            int r5 = r5 + 1
            goto L_0x04f6
        L_0x0509:
            if (r1 == 0) goto L_0x0510
            com.google.android.gms.internal.ads.zzbtu<?, ?> r5 = r4.zzfsw
            r5.zzg(r0, r1)
        L_0x0510:
            if (r6 != 0) goto L_0x051c
            r0 = r31
            if (r2 != r0) goto L_0x0517
            goto L_0x0522
        L_0x0517:
            com.google.android.gms.internal.ads.zzbrl r0 = com.google.android.gms.internal.ads.zzbrl.zzanj()
            throw r0
        L_0x051c:
            r0 = r31
            if (r2 > r0) goto L_0x0523
            if (r3 != r6) goto L_0x0523
        L_0x0522:
            return r2
        L_0x0523:
            com.google.android.gms.internal.ads.zzbrl r0 = com.google.android.gms.internal.ads.zzbrl.zzanj()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.ads.zzbpr):int");
    }

    /* JADX WARNING: type inference failed for: r29v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r2v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v5, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r17v0, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r0v7, types: [int] */
    /* JADX WARNING: type inference failed for: r1v4, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r2v7, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v3, types: [int] */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r2v10, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r2v13, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v5, types: [int] */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r1v14, types: [int] */
    /* JADX WARNING: type inference failed for: r2v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r17v1 */
    /* JADX WARNING: type inference failed for: r3v13, types: [int] */
    /* JADX WARNING: type inference failed for: r17v2 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: type inference failed for: r12v12 */
    /* JADX WARNING: type inference failed for: r12v13 */
    /* JADX WARNING: type inference failed for: r12v14 */
    /* JADX WARNING: type inference failed for: r12v15 */
    /* JADX WARNING: type inference failed for: r12v16 */
    /* JADX WARNING: type inference failed for: r12v17 */
    /* JADX WARNING: type inference failed for: r12v18 */
    /* JADX WARNING: type inference failed for: r12v19 */
    /* JADX WARNING: type inference failed for: r12v20 */
    /* JADX WARNING: type inference failed for: r12v21 */
    /* JADX WARNING: type inference failed for: r12v22 */
    /* JADX WARNING: type inference failed for: r12v23 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v5, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r29v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
  assigns: []
  uses: []
  mth insns count: 402
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 16 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.ads.zzbpr r32) throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            boolean r0 = r15.zzfsp
            if (r0 == 0) goto L_0x0358
            sun.misc.Unsafe r9 = zzfsh
            r10 = -1
            r16 = 0
            r0 = r30
            r1 = -1
            r2 = 0
        L_0x0017:
            if (r0 >= r13) goto L_0x034f
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0029
            int r0 = com.google.android.gms.internal.ads.zzbpq.zza(r0, r12, r3, r11)
            int r3 = r11.zzfld
            r8 = r0
            r17 = r3
            goto L_0x002c
        L_0x0029:
            r17 = r0
            r8 = r3
        L_0x002c:
            int r7 = r17 >>> 3
            r6 = r17 & 7
            if (r7 <= r1) goto L_0x003a
            int r2 = r2 / 3
            int r0 = r15.zzai(r7, r2)
            r4 = r0
            goto L_0x003f
        L_0x003a:
            int r0 = r15.zzfw(r7)
            r4 = r0
        L_0x003f:
            if (r4 != r10) goto L_0x004c
            r24 = r7
            r2 = r8
            r18 = r9
            r19 = 0
            r26 = -1
            goto L_0x031b
        L_0x004c:
            int[] r0 = r15.zzfsi
            int r1 = r4 + 1
            r5 = r0[r1]
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r3 = r0 >>> 20
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r1 = (long) r0
            r0 = 17
            r10 = 2
            if (r3 > r0) goto L_0x0225
            r0 = 1
            switch(r3) {
                case 0: goto L_0x0208;
                case 1: goto L_0x01ea;
                case 2: goto L_0x01c9;
                case 3: goto L_0x01c9;
                case 4: goto L_0x01ac;
                case 5: goto L_0x018b;
                case 6: goto L_0x016e;
                case 7: goto L_0x0149;
                case 8: goto L_0x0123;
                case 9: goto L_0x00f3;
                case 10: goto L_0x00d7;
                case 11: goto L_0x01ac;
                case 12: goto L_0x00ba;
                case 13: goto L_0x016e;
                case 14: goto L_0x018b;
                case 15: goto L_0x0099;
                case 16: goto L_0x0070;
                default: goto L_0x0065;
            }
        L_0x0065:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x02fc
        L_0x0070:
            if (r6 != 0) goto L_0x008e
            int r6 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r8, r11)
            r19 = r1
            long r0 = r11.zzfle
            long r21 = com.google.android.gms.internal.ads.zzbqf.zzax(r0)
            r0 = r9
            r2 = r19
            r1 = r28
            r10 = r4
            r4 = r21
            r0.putLong(r1, r2, r4)
            r0 = r6
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0017
        L_0x008e:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x02fc
        L_0x0099:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x00af
            int r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r8, r11)
            int r1 = r11.zzfld
            int r1 = com.google.android.gms.internal.ads.zzbqf.zzeu(r1)
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0017
        L_0x00af:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x02fc
        L_0x00ba:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x00cc
            int r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r8, r11)
            int r1 = r11.zzfld
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0017
        L_0x00cc:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x02fc
        L_0x00d7:
            r2 = r1
            if (r6 != r10) goto L_0x00e8
            int r0 = com.google.android.gms.internal.ads.zzbpq.zze(r12, r8, r11)
            java.lang.Object r1 = r11.zzflf
            r9.putObject(r14, r2, r1)
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0017
        L_0x00e8:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x02fc
        L_0x00f3:
            r2 = r1
            if (r6 != r10) goto L_0x0118
            com.google.android.gms.internal.ads.zzbtc r0 = r15.zzfq(r4)
            int r0 = zza(r0, r12, r8, r13, r11)
            java.lang.Object r1 = r9.getObject(r14, r2)
            if (r1 != 0) goto L_0x010a
            java.lang.Object r1 = r11.zzflf
            r9.putObject(r14, r2, r1)
            goto L_0x0113
        L_0x010a:
            java.lang.Object r5 = r11.zzflf
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r5)
            r9.putObject(r14, r2, r1)
        L_0x0113:
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0017
        L_0x0118:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x02fc
        L_0x0123:
            r2 = r1
            if (r6 != r10) goto L_0x013e
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r5
            if (r0 != 0) goto L_0x0130
            int r0 = com.google.android.gms.internal.ads.zzbpq.zzc(r12, r8, r11)
            goto L_0x0134
        L_0x0130:
            int r0 = com.google.android.gms.internal.ads.zzbpq.zzd(r12, r8, r11)
        L_0x0134:
            java.lang.Object r1 = r11.zzflf
            r9.putObject(r14, r2, r1)
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0017
        L_0x013e:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x02fc
        L_0x0149:
            r2 = r1
            if (r6 != 0) goto L_0x0163
            int r1 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r8, r11)
            long r5 = r11.zzfle
            r19 = 0
            int r8 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r8 == 0) goto L_0x0159
            goto L_0x015a
        L_0x0159:
            r0 = 0
        L_0x015a:
            com.google.android.gms.internal.ads.zzbua.zza(r14, r2, r0)
            r0 = r1
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0017
        L_0x0163:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x02fc
        L_0x016e:
            r2 = r1
            r0 = 5
            if (r6 != r0) goto L_0x0180
            int r0 = com.google.android.gms.internal.ads.zzbpq.zzg(r12, r8)
            r9.putInt(r14, r2, r0)
            int r0 = r8 + 4
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0017
        L_0x0180:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x02fc
        L_0x018b:
            r2 = r1
            if (r6 != r0) goto L_0x01a1
            long r5 = com.google.android.gms.internal.ads.zzbpq.zzh(r12, r8)
            r0 = r9
            r1 = r28
            r10 = r4
            r4 = r5
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0017
        L_0x01a1:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x02fc
        L_0x01ac:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x01be
            int r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r8, r11)
            int r1 = r11.zzfld
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0017
        L_0x01be:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x02fc
        L_0x01c9:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x01df
            int r6 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r8, r11)
            long r4 = r11.zzfle
            r0 = r9
            r1 = r28
            r0.putLong(r1, r2, r4)
            r0 = r6
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0017
        L_0x01df:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x02fc
        L_0x01ea:
            r2 = r1
            r10 = r4
            r0 = 5
            if (r6 != r0) goto L_0x01fd
            float r0 = com.google.android.gms.internal.ads.zzbpq.zzj(r12, r8)
            com.google.android.gms.internal.ads.zzbua.zza(r14, r2, r0)
            int r0 = r8 + 4
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0017
        L_0x01fd:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x02fc
        L_0x0208:
            r2 = r1
            r10 = r4
            if (r6 != r0) goto L_0x021a
            double r0 = com.google.android.gms.internal.ads.zzbpq.zzi(r12, r8)
            com.google.android.gms.internal.ads.zzbua.zza(r14, r2, r0)
            int r0 = r8 + 8
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0017
        L_0x021a:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x02fc
        L_0x0225:
            r0 = 27
            if (r3 != r0) goto L_0x0270
            if (r6 != r10) goto L_0x0265
            java.lang.Object r0 = r9.getObject(r14, r1)
            com.google.android.gms.internal.ads.zzbrk r0 = (com.google.android.gms.internal.ads.zzbrk) r0
            boolean r3 = r0.zzaki()
            if (r3 != 0) goto L_0x024b
            int r3 = r0.size()
            if (r3 != 0) goto L_0x0240
            r3 = 10
            goto L_0x0242
        L_0x0240:
            int r3 = r3 << 1
        L_0x0242:
            com.google.android.gms.internal.ads.zzbrk r0 = r0.zzel(r3)
            r9.putObject(r14, r1, r0)
            r5 = r0
            goto L_0x024c
        L_0x024b:
            r5 = r0
        L_0x024c:
            com.google.android.gms.internal.ads.zzbtc r0 = r15.zzfq(r4)
            r1 = r17
            r2 = r29
            r3 = r8
            r19 = r4
            r4 = r31
            r6 = r32
            int r0 = zza(r0, r1, r2, r3, r4, r5, r6)
            r1 = r7
            r2 = r19
            r10 = -1
            goto L_0x0017
        L_0x0265:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x02fc
        L_0x0270:
            r19 = r4
            r0 = 49
            if (r3 > r0) goto L_0x02bb
            long r4 = (long) r5
            r0 = r27
            r20 = r1
            r1 = r28
            r2 = r29
            r10 = r3
            r3 = r8
            r22 = r4
            r4 = r31
            r5 = r17
            r30 = r6
            r6 = r7
            r24 = r7
            r7 = r30
            r15 = r8
            r8 = r19
            r18 = r9
            r25 = r10
            r26 = -1
            r9 = r22
            r11 = r25
            r12 = r20
            r14 = r32
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x02a8
            r2 = r0
            goto L_0x031b
        L_0x02a8:
            r14 = r28
            r12 = r29
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = -1
            r13 = r31
            r15 = r27
            goto L_0x0017
        L_0x02bb:
            r20 = r1
            r25 = r3
            r30 = r6
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            r0 = 50
            r9 = r25
            if (r9 != r0) goto L_0x02fe
            r7 = r30
            if (r7 != r10) goto L_0x02fc
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r19
            r6 = r20
            r8 = r32
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x02e9
            r2 = r0
            goto L_0x031b
        L_0x02e9:
            r14 = r28
            r12 = r29
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = -1
            r13 = r31
            r15 = r27
            goto L_0x0017
        L_0x02fc:
            r2 = r15
            goto L_0x031b
        L_0x02fe:
            r7 = r30
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r8 = r5
            r5 = r17
            r6 = r24
            r10 = r20
            r12 = r19
            r13 = r32
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x033c
            r2 = r0
        L_0x031b:
            r0 = r17
            r1 = r29
            r3 = r31
            r4 = r28
            r5 = r32
            int r0 = zza(r0, r1, r2, r3, r4, r5)
            r14 = r28
            r12 = r29
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = -1
            r13 = r31
            r15 = r27
            goto L_0x0017
        L_0x033c:
            r14 = r28
            r12 = r29
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = -1
            r13 = r31
            r15 = r27
            goto L_0x0017
        L_0x034f:
            r4 = r13
            if (r0 != r4) goto L_0x0353
            return
        L_0x0353:
            com.google.android.gms.internal.ads.zzbrl r0 = com.google.android.gms.internal.ads.zzbrl.zzanj()
            throw r0
        L_0x0358:
            r4 = r13
            r5 = 0
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r30
            r4 = r31
            r6 = r32
            r0.zza((T) r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.zzbpr):void");
    }

    public final void zzs(T t) {
        int i;
        int i2 = this.zzfss;
        while (true) {
            i = this.zzfst;
            if (i2 >= i) {
                break;
            }
            long zzft = (long) (zzft(this.zzfsr[i2]) & 1048575);
            Object zzp = zzbua.zzp(t, zzft);
            if (zzp != null) {
                zzbua.zza((Object) t, zzft, this.zzfsy.zzz(zzp));
            }
            i2++;
        }
        int length = this.zzfsr.length;
        while (i < length) {
            this.zzfsv.zzb(t, (long) this.zzfsr[i]);
            i++;
        }
        this.zzfsw.zzs(t);
        if (this.zzfsn) {
            this.zzfsx.zzs(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzbtu<UT, UB> zzbtu) {
        int i2 = this.zzfsi[i];
        Object zzp = zzbua.zzp(obj, (long) (zzft(i) & 1048575));
        if (zzp == null) {
            return ub;
        }
        zzbri zzfs = zzfs(i);
        if (zzfs == null) {
            return ub;
        }
        return zza(i, i2, this.zzfsy.zzw(zzp), zzfs, ub, zzbtu);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzbri zzbri, UB ub, zzbtu<UT, UB> zzbtu) {
        zzbse zzab = this.zzfsy.zzab(zzfr(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!zzbri.zzcb(((Integer) entry.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzbtu.zzaoy();
                }
                zzbqb zzen = zzbpu.zzen(zzbsd.zza(zzab, entry.getKey(), entry.getValue()));
                try {
                    zzbsd.zza(zzen.zzakt(), zzab, entry.getKey(), entry.getValue());
                    zzbtu.zza(ub, i2, zzen.zzaks());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0104, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzae(T r14) {
        /*
            r13 = this;
            r0 = 0
            r1 = -1
            r1 = 0
            r2 = -1
            r3 = 0
        L_0x0005:
            int r4 = r13.zzfss
            r5 = 1
            if (r1 >= r4) goto L_0x0108
            int[] r4 = r13.zzfsr
            r4 = r4[r1]
            int[] r6 = r13.zzfsi
            r6 = r6[r4]
            int r7 = r13.zzft(r4)
            boolean r8 = r13.zzfsp
            r9 = 1048575(0xfffff, float:1.469367E-39)
            if (r8 != 0) goto L_0x0035
            int[] r8 = r13.zzfsi
            int r10 = r4 + 2
            r8 = r8[r10]
            r10 = r8 & r9
            int r8 = r8 >>> 20
            int r8 = r5 << r8
            if (r10 == r2) goto L_0x0036
            sun.misc.Unsafe r2 = zzfsh
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
            com.google.android.gms.internal.ads.zzbsg r6 = r13.zzfsy
            r7 = r7 & r9
            long r7 = (long) r7
            java.lang.Object r7 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r7)
            java.util.Map r6 = r6.zzx(r7)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00b3
            java.lang.Object r4 = r13.zzfr(r4)
            com.google.android.gms.internal.ads.zzbsg r7 = r13.zzfsy
            com.google.android.gms.internal.ads.zzbse r4 = r7.zzab(r4)
            com.google.android.gms.internal.ads.zzbuj r4 = r4.zzfsb
            com.google.android.gms.internal.ads.zzbuo r4 = r4.zzapj()
            com.google.android.gms.internal.ads.zzbuo r7 = com.google.android.gms.internal.ads.zzbuo.MESSAGE
            if (r4 != r7) goto L_0x00b3
            r4 = 0
            java.util.Collection r6 = r6.values()
            java.util.Iterator r6 = r6.iterator()
        L_0x0094:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00b3
            java.lang.Object r7 = r6.next()
            if (r4 != 0) goto L_0x00ac
            com.google.android.gms.internal.ads.zzbsy r4 = com.google.android.gms.internal.ads.zzbsy.zzaog()
            java.lang.Class r8 = r7.getClass()
            com.google.android.gms.internal.ads.zzbtc r4 = r4.zzf(r8)
        L_0x00ac:
            boolean r7 = r4.zzae(r7)
            if (r7 != 0) goto L_0x0094
            r5 = 0
        L_0x00b3:
            if (r5 != 0) goto L_0x0104
            return r0
        L_0x00b6:
            boolean r5 = r13.zza((T) r14, r6, r4)
            if (r5 == 0) goto L_0x0104
            com.google.android.gms.internal.ads.zzbtc r4 = r13.zzfq(r4)
            boolean r4 = zza(r14, r7, r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x00c7:
            r6 = r7 & r9
            long r6 = (long) r6
            java.lang.Object r6 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r6)
            java.util.List r6 = (java.util.List) r6
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00f0
            com.google.android.gms.internal.ads.zzbtc r4 = r13.zzfq(r4)
            r7 = 0
        L_0x00db:
            int r8 = r6.size()
            if (r7 >= r8) goto L_0x00f0
            java.lang.Object r8 = r6.get(r7)
            boolean r8 = r4.zzae(r8)
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
            com.google.android.gms.internal.ads.zzbtc r4 = r13.zzfq(r4)
            boolean r4 = zza(r14, r7, r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x0104:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0108:
            boolean r1 = r13.zzfsn
            if (r1 == 0) goto L_0x0119
            com.google.android.gms.internal.ads.zzbqr<?> r1 = r13.zzfsx
            com.google.android.gms.internal.ads.zzbqu r14 = r1.zzq(r14)
            boolean r14 = r14.isInitialized()
            if (r14 != 0) goto L_0x0119
            return r0
        L_0x0119:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zzae(java.lang.Object):boolean");
    }

    private static boolean zza(Object obj, int i, zzbtc zzbtc) {
        return zzbtc.zzae(zzbua.zzp(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzbup zzbup) throws IOException {
        if (obj instanceof String) {
            zzbup.zzf(i, (String) obj);
        } else {
            zzbup.zza(i, (zzbpu) obj);
        }
    }

    private final void zza(Object obj, int i, zzbtb zzbtb) throws IOException {
        if (zzfv(i)) {
            zzbua.zza(obj, (long) (i & 1048575), (Object) zzbtb.zzalb());
        } else if (this.zzfso) {
            zzbua.zza(obj, (long) (i & 1048575), (Object) zzbtb.readString());
        } else {
            zzbua.zza(obj, (long) (i & 1048575), (Object) zzbtb.zzalc());
        }
    }

    private final int zzft(int i) {
        return this.zzfsi[i + 1];
    }

    private final int zzfu(int i) {
        return this.zzfsi[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzbua.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzbua.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzbua.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzbua.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzbua.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zzd(t, i) == zzd(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzfsp) {
            return zzd(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zzd(T t, int i) {
        if (this.zzfsp) {
            int zzft = zzft(i);
            long j = (long) (zzft & 1048575);
            switch ((zzft & 267386880) >>> 20) {
                case 0:
                    return zzbua.zzo(t, j) != 0.0d;
                case 1:
                    return zzbua.zzn(t, j) != BitmapDescriptorFactory.HUE_RED;
                case 2:
                    return zzbua.zzl(t, j) != 0;
                case 3:
                    return zzbua.zzl(t, j) != 0;
                case 4:
                    return zzbua.zzk(t, j) != 0;
                case 5:
                    return zzbua.zzl(t, j) != 0;
                case 6:
                    return zzbua.zzk(t, j) != 0;
                case 7:
                    return zzbua.zzm(t, j);
                case 8:
                    Object zzp = zzbua.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzbpu) {
                        return !zzbpu.zzfli.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzbua.zzp(t, j) != null;
                case 10:
                    return !zzbpu.zzfli.equals(zzbua.zzp(t, j));
                case 11:
                    return zzbua.zzk(t, j) != 0;
                case 12:
                    return zzbua.zzk(t, j) != 0;
                case 13:
                    return zzbua.zzk(t, j) != 0;
                case 14:
                    return zzbua.zzl(t, j) != 0;
                case 15:
                    return zzbua.zzk(t, j) != 0;
                case 16:
                    return zzbua.zzl(t, j) != 0;
                case 17:
                    return zzbua.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzfu = zzfu(i);
            return (zzbua.zzk(t, (long) (zzfu & 1048575)) & (1 << (zzfu >>> 20))) != 0;
        }
    }

    private final void zze(T t, int i) {
        if (!this.zzfsp) {
            int zzfu = zzfu(i);
            long j = (long) (zzfu & 1048575);
            zzbua.zzb((Object) t, j, zzbua.zzk(t, j) | (1 << (zzfu >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzbua.zzk(t, (long) (zzfu(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzbua.zzb((Object) t, (long) (zzfu(i2) & 1048575), i);
    }

    private final int zzfw(int i) {
        if (i < this.zzfsk || i > this.zzfsl) {
            return -1;
        }
        return zzaj(i, 0);
    }

    private final int zzai(int i, int i2) {
        if (i < this.zzfsk || i > this.zzfsl) {
            return -1;
        }
        return zzaj(i, i2);
    }

    private final int zzaj(int i, int i2) {
        int length = (this.zzfsi.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzfsi[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
