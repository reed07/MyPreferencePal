package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: IMASDK */
public final class ri extends rz {
    private static final int[] a = new int[0];
    private final rv b;
    private final AtomicReference<rl> c;

    /* compiled from: IMASDK */
    public static final class a implements Parcelable {
        public static final Creator<a> CREATOR = new rn();
        public final int a;
        public final int[] b = new int[this.c];
        private final int c;

        a(Parcel parcel) {
            this.a = parcel.readInt();
            this.c = parcel.readByte();
            parcel.readIntArray(this.b);
        }

        public final int describeContents() {
            return 0;
        }

        public final int hashCode() {
            return (this.a * 31) + Arrays.hashCode(this.b);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && Arrays.equals(this.b, aVar.b);
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b.length);
            parcel.writeIntArray(this.b);
        }
    }

    public ri() {
        this(new rv());
    }

    protected static boolean a(int i, boolean z) {
        int i2 = i & 7;
        return i2 == 4 || (z && i2 == 3);
    }

    /* access modifiers changed from: private */
    public static int c(int i, int i2) {
        if (i == -1) {
            return i2 == -1 ? 0 : -1;
        }
        if (i2 == -1) {
            return 1;
        }
        return i - i2;
    }

    /* access modifiers changed from: private */
    public static int d(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        return i2 > i ? -1 : 0;
    }

    private ri(rv rvVar) {
        super(0);
        this.b = rvVar;
        this.c = new AtomicReference<>(rl.a);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x04cd, code lost:
        if (r0 != false) goto L_0x04db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x065e, code lost:
        if (r9 == 2) goto L_0x0662;
     */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x03f0  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x04f0  */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x04f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.ads.interactivemedia.v3.internal.ck[], com.google.ads.interactivemedia.v3.internal.rt[]> a(com.google.ads.interactivemedia.v3.internal.rp r37, int[][][] r38, int[] r39) throws com.google.ads.interactivemedia.v3.internal.aw {
        /*
            r36 = this;
            r0 = r36
            r1 = r37
            java.util.concurrent.atomic.AtomicReference<com.google.ads.interactivemedia.v3.internal.rl> r2 = r0.c
            java.lang.Object r2 = r2.get()
            com.google.ads.interactivemedia.v3.internal.rl r2 = (com.google.ads.interactivemedia.v3.internal.rl) r2
            int r3 = r37.a()
            int r4 = r37.a()
            com.google.ads.interactivemedia.v3.internal.ru[] r5 = new com.google.ads.interactivemedia.v3.internal.ru[r4]
            r7 = 0
            r8 = 0
            r9 = 0
        L_0x0019:
            r10 = 2
            if (r7 >= r4) goto L_0x02c3
            int r14 = r1.a(r7)
            if (r10 != r14) goto L_0x02ac
            if (r8 != 0) goto L_0x0290
            com.google.ads.interactivemedia.v3.internal.mz r8 = r1.b(r7)
            r14 = r38[r7]
            r15 = r39[r7]
            boolean r12 = r2.r
            if (r12 != 0) goto L_0x0173
            boolean r12 = r2.q
            if (r12 != 0) goto L_0x0173
            boolean r12 = r2.h
            if (r12 == 0) goto L_0x003b
            r12 = 24
            goto L_0x003d
        L_0x003b:
            r12 = 16
        L_0x003d:
            boolean r6 = r2.g
            if (r6 == 0) goto L_0x0047
            r6 = r15 & r12
            if (r6 == 0) goto L_0x0047
            r6 = 1
            goto L_0x0048
        L_0x0047:
            r6 = 0
        L_0x0048:
            r15 = 0
        L_0x0049:
            int r11 = r8.b
            if (r15 >= r11) goto L_0x0165
            com.google.ads.interactivemedia.v3.internal.mx r11 = r8.a(r15)
            r25 = r14[r15]
            int r13 = r2.b
            int r10 = r2.c
            int r0 = r2.d
            r27 = r3
            int r3 = r2.e
            r28 = r4
            int r4 = r2.i
            r29 = r9
            int r9 = r2.j
            boolean r1 = r2.k
            r30 = r5
            int r5 = r11.a
            r31 = r7
            r7 = 2
            if (r5 >= r7) goto L_0x0078
            int[] r0 = a
            r32 = r6
            r35 = r14
            goto L_0x0144
        L_0x0078:
            java.util.List r1 = a(r11, r4, r9, r1)
            int r4 = r1.size()
            if (r4 >= r7) goto L_0x008a
            int[] r0 = a
            r32 = r6
            r35 = r14
            goto L_0x0144
        L_0x008a:
            if (r6 != 0) goto L_0x0100
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            r32 = r6
            r5 = 0
            r7 = 0
            r9 = 0
        L_0x0096:
            int r6 = r1.size()
            if (r5 >= r6) goto L_0x00fb
            java.lang.Object r6 = r1.get(r5)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            com.google.ads.interactivemedia.v3.internal.bs r6 = r11.a(r6)
            java.lang.String r6 = r6.h
            boolean r17 = r4.add(r6)
            if (r17 == 0) goto L_0x00ec
            r33 = r4
            r34 = r7
            r35 = r14
            r4 = 0
            r7 = 0
        L_0x00ba:
            int r14 = r1.size()
            if (r4 >= r14) goto L_0x00e7
            java.lang.Object r14 = r1.get(r4)
            java.lang.Integer r14 = (java.lang.Integer) r14
            int r14 = r14.intValue()
            com.google.ads.interactivemedia.v3.internal.bs r17 = r11.a(r14)
            r19 = r25[r14]
            r18 = r6
            r20 = r12
            r21 = r13
            r22 = r10
            r23 = r0
            r24 = r3
            boolean r14 = a(r17, r18, r19, r20, r21, r22, r23, r24)
            if (r14 == 0) goto L_0x00e4
            int r7 = r7 + 1
        L_0x00e4:
            int r4 = r4 + 1
            goto L_0x00ba
        L_0x00e7:
            if (r7 <= r9) goto L_0x00f2
            r9 = r7
            r7 = r6
            goto L_0x00f4
        L_0x00ec:
            r33 = r4
            r34 = r7
            r35 = r14
        L_0x00f2:
            r7 = r34
        L_0x00f4:
            int r5 = r5 + 1
            r4 = r33
            r14 = r35
            goto L_0x0096
        L_0x00fb:
            r34 = r7
            r35 = r14
            goto L_0x0106
        L_0x0100:
            r32 = r6
            r35 = r14
            r34 = 0
        L_0x0106:
            int r4 = r1.size()
            r5 = 1
            int r4 = r4 - r5
        L_0x010c:
            if (r4 < 0) goto L_0x0136
            java.lang.Object r5 = r1.get(r4)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            com.google.ads.interactivemedia.v3.internal.bs r17 = r11.a(r5)
            r19 = r25[r5]
            r18 = r34
            r20 = r12
            r21 = r13
            r22 = r10
            r23 = r0
            r24 = r3
            boolean r5 = a(r17, r18, r19, r20, r21, r22, r23, r24)
            if (r5 != 0) goto L_0x0133
            r1.remove(r4)
        L_0x0133:
            int r4 = r4 + -1
            goto L_0x010c
        L_0x0136:
            int r0 = r1.size()
            r3 = 2
            if (r0 >= r3) goto L_0x0140
            int[] r0 = a
            goto L_0x0144
        L_0x0140:
            int[] r0 = com.google.ads.interactivemedia.v3.internal.vf.a(r1)
        L_0x0144:
            int r1 = r0.length
            if (r1 <= 0) goto L_0x014e
            com.google.ads.interactivemedia.v3.internal.ru r1 = new com.google.ads.interactivemedia.v3.internal.ru
            r1.<init>(r11, r0)
            r12 = r1
            goto L_0x0180
        L_0x014e:
            int r15 = r15 + 1
            r3 = r27
            r4 = r28
            r9 = r29
            r5 = r30
            r7 = r31
            r6 = r32
            r14 = r35
            r0 = r36
            r1 = r37
            r10 = 2
            goto L_0x0049
        L_0x0165:
            r27 = r3
            r28 = r4
            r30 = r5
            r31 = r7
            r29 = r9
            r35 = r14
            r12 = 0
            goto L_0x0180
        L_0x0173:
            r27 = r3
            r28 = r4
            r30 = r5
            r31 = r7
            r29 = r9
            r35 = r14
            r12 = 0
        L_0x0180:
            if (r12 != 0) goto L_0x0282
            r0 = 0
            r1 = 0
            r3 = 0
            r4 = 0
            r5 = -1
            r6 = -1
        L_0x0188:
            int r7 = r8.b
            if (r0 >= r7) goto L_0x0273
            com.google.ads.interactivemedia.v3.internal.mx r7 = r8.a(r0)
            int r9 = r2.i
            int r10 = r2.j
            boolean r11 = r2.k
            java.util.List r9 = a(r7, r9, r10, r11)
            r10 = r35[r0]
            r11 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r1
            r1 = 0
        L_0x01a2:
            int r12 = r7.a
            if (r1 >= r12) goto L_0x0264
            r12 = r10[r1]
            boolean r13 = r2.s
            boolean r12 = a(r12, r13)
            if (r12 == 0) goto L_0x0258
            com.google.ads.interactivemedia.v3.internal.bs r12 = r7.a(r1)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r1)
            boolean r13 = r9.contains(r13)
            if (r13 == 0) goto L_0x01f1
            int r13 = r12.m
            r14 = -1
            if (r13 == r14) goto L_0x01c9
            int r13 = r12.m
            int r15 = r2.b
            if (r13 > r15) goto L_0x01f1
        L_0x01c9:
            int r13 = r12.n
            if (r13 == r14) goto L_0x01d3
            int r13 = r12.n
            int r14 = r2.c
            if (r13 > r14) goto L_0x01f1
        L_0x01d3:
            float r13 = r12.o
            r14 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1))
            if (r13 == 0) goto L_0x01e4
            float r13 = r12.o
            int r14 = r2.d
            float r14 = (float) r14
            int r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1))
            if (r13 > 0) goto L_0x01f1
        L_0x01e4:
            int r13 = r12.d
            r14 = -1
            if (r13 == r14) goto L_0x01ef
            int r13 = r12.d
            int r14 = r2.e
            if (r13 > r14) goto L_0x01f1
        L_0x01ef:
            r13 = 1
            goto L_0x01f2
        L_0x01f1:
            r13 = 0
        L_0x01f2:
            if (r13 != 0) goto L_0x01ff
            boolean r14 = r2.f
            if (r14 == 0) goto L_0x01f9
            goto L_0x01ff
        L_0x01f9:
            r17 = r3
            r18 = r4
            goto L_0x025c
        L_0x01ff:
            if (r13 == 0) goto L_0x0203
            r14 = 2
            goto L_0x0204
        L_0x0203:
            r14 = 1
        L_0x0204:
            r15 = r10[r1]
            r17 = r3
            r3 = 0
            boolean r15 = a(r15, r3)
            if (r15 == 0) goto L_0x0211
            int r14 = r14 + 1000
        L_0x0211:
            if (r14 <= r5) goto L_0x0215
            r3 = 1
            goto L_0x0216
        L_0x0215:
            r3 = 0
        L_0x0216:
            if (r14 != r5) goto L_0x024a
            int r3 = r12.d
            int r3 = c(r3, r6)
            r18 = r4
            boolean r4 = r2.q
            if (r4 == 0) goto L_0x022c
            if (r3 == 0) goto L_0x022c
            if (r3 >= 0) goto L_0x022a
            r3 = 1
            goto L_0x024c
        L_0x022a:
            r3 = 0
            goto L_0x024c
        L_0x022c:
            int r3 = r12.a()
            if (r3 == r11) goto L_0x0237
            int r3 = c(r3, r11)
            goto L_0x023d
        L_0x0237:
            int r3 = r12.d
            int r3 = c(r3, r6)
        L_0x023d:
            if (r15 == 0) goto L_0x0244
            if (r13 == 0) goto L_0x0244
            if (r3 <= 0) goto L_0x0248
            goto L_0x0246
        L_0x0244:
            if (r3 >= 0) goto L_0x0248
        L_0x0246:
            r3 = 1
            goto L_0x024c
        L_0x0248:
            r3 = 0
            goto L_0x024c
        L_0x024a:
            r18 = r4
        L_0x024c:
            if (r3 == 0) goto L_0x025c
            int r6 = r12.d
            int r11 = r12.a()
            r4 = r1
            r3 = r7
            r5 = r14
            goto L_0x0260
        L_0x0258:
            r17 = r3
            r18 = r4
        L_0x025c:
            r3 = r17
            r4 = r18
        L_0x0260:
            int r1 = r1 + 1
            goto L_0x01a2
        L_0x0264:
            r17 = r3
            r18 = r4
            int r0 = r0 + 1
            r4 = r5
            r5 = r6
            r6 = r11
            r1 = r17
            r3 = r18
            goto L_0x0188
        L_0x0273:
            if (r1 != 0) goto L_0x0277
            r12 = 0
            goto L_0x0282
        L_0x0277:
            com.google.ads.interactivemedia.v3.internal.ru r12 = new com.google.ads.interactivemedia.v3.internal.ru
            r0 = 1
            int[] r4 = new int[r0]
            r0 = 0
            r4[r0] = r3
            r12.<init>(r1, r4)
        L_0x0282:
            r30[r31] = r12
            r0 = r30[r31]
            if (r0 == 0) goto L_0x028a
            r8 = 1
            goto L_0x028b
        L_0x028a:
            r8 = 0
        L_0x028b:
            r6 = r31
            r0 = r37
            goto L_0x029b
        L_0x0290:
            r27 = r3
            r28 = r4
            r30 = r5
            r29 = r9
            r6 = r7
            r0 = r37
        L_0x029b:
            com.google.ads.interactivemedia.v3.internal.mz r1 = r0.b(r6)
            int r1 = r1.b
            if (r1 <= 0) goto L_0x02a6
            r26 = 1
            goto L_0x02a8
        L_0x02a6:
            r26 = 0
        L_0x02a8:
            r1 = r29 | r26
            r9 = r1
            goto L_0x02b6
        L_0x02ac:
            r0 = r1
            r27 = r3
            r28 = r4
            r30 = r5
            r6 = r7
            r29 = r9
        L_0x02b6:
            int r7 = r6 + 1
            r1 = r0
            r3 = r27
            r4 = r28
            r5 = r30
            r0 = r36
            goto L_0x0019
        L_0x02c3:
            r0 = r1
            r27 = r3
            r30 = r5
            r29 = r9
            r1 = r4
            r3 = 0
            r4 = 0
            r5 = -1
        L_0x02ce:
            if (r3 >= r1) goto L_0x0439
            int r6 = r0.a(r3)
            r7 = 1
            if (r7 != r6) goto L_0x0429
            com.google.ads.interactivemedia.v3.internal.mz r6 = r0.b(r3)
            r7 = r38[r3]
            if (r29 != 0) goto L_0x02e1
            r8 = 1
            goto L_0x02e2
        L_0x02e1:
            r8 = 0
        L_0x02e2:
            r9 = 0
            r10 = -1
            r11 = 0
            r12 = -1
        L_0x02e6:
            int r13 = r6.b
            if (r9 >= r13) goto L_0x033d
            com.google.ads.interactivemedia.v3.internal.mx r13 = r6.a(r9)
            r14 = r7[r9]
            r39 = r10
            r15 = r12
            r10 = 0
            r12 = r11
        L_0x02f5:
            int r11 = r13.a
            if (r10 >= r11) goto L_0x0333
            r11 = r14[r10]
            r17 = r15
            boolean r15 = r2.s
            boolean r11 = a(r11, r15)
            if (r11 == 0) goto L_0x0326
            com.google.ads.interactivemedia.v3.internal.bs r11 = r13.a(r10)
            com.google.ads.interactivemedia.v3.internal.rk r15 = new com.google.ads.interactivemedia.v3.internal.rk
            r18 = r13
            r13 = r14[r10]
            r15.<init>(r11, r2, r13)
            boolean r11 = r15.a
            if (r11 != 0) goto L_0x031a
            boolean r11 = r2.n
            if (r11 == 0) goto L_0x0328
        L_0x031a:
            if (r12 == 0) goto L_0x0322
            int r11 = r15.compareTo(r12)
            if (r11 <= 0) goto L_0x0328
        L_0x0322:
            r11 = r9
            r12 = r15
            r15 = r10
            goto L_0x032c
        L_0x0326:
            r18 = r13
        L_0x0328:
            r11 = r39
            r15 = r17
        L_0x032c:
            int r10 = r10 + 1
            r39 = r11
            r13 = r18
            goto L_0x02f5
        L_0x0333:
            r17 = r15
            int r9 = r9 + 1
            r10 = r39
            r11 = r12
            r12 = r17
            goto L_0x02e6
        L_0x033d:
            r9 = -1
            if (r10 != r9) goto L_0x0349
            r28 = r1
            r17 = r2
            r18 = r3
            r12 = 0
            goto L_0x0405
        L_0x0349:
            com.google.ads.interactivemedia.v3.internal.mx r6 = r6.a(r10)
            boolean r9 = r2.r
            if (r9 != 0) goto L_0x03e7
            boolean r9 = r2.q
            if (r9 != 0) goto L_0x03e7
            if (r8 == 0) goto L_0x03e7
            r7 = r7[r10]
            boolean r8 = r2.o
            boolean r9 = r2.p
            java.util.HashSet r10 = new java.util.HashSet
            r10.<init>()
            r17 = r2
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x0367:
            int r2 = r6.a
            if (r13 >= r2) goto L_0x03b3
            com.google.ads.interactivemedia.v3.internal.bs r2 = r6.a(r13)
            com.google.ads.interactivemedia.v3.internal.rj r0 = new com.google.ads.interactivemedia.v3.internal.rj
            r28 = r1
            int r1 = r2.s
            r18 = r3
            int r3 = r2.t
            java.lang.String r2 = r2.h
            r0.<init>(r1, r3, r2)
            boolean r1 = r10.add(r0)
            if (r1 == 0) goto L_0x03a6
            r1 = 0
            r2 = 0
        L_0x0386:
            int r3 = r6.a
            if (r1 >= r3) goto L_0x039f
            com.google.ads.interactivemedia.v3.internal.bs r3 = r6.a(r1)
            r39 = r10
            r10 = r7[r1]
            boolean r3 = a(r3, r10, r0, r8, r9)
            if (r3 == 0) goto L_0x039a
            int r2 = r2 + 1
        L_0x039a:
            int r1 = r1 + 1
            r10 = r39
            goto L_0x0386
        L_0x039f:
            r39 = r10
            if (r2 <= r14) goto L_0x03a8
            r15 = r0
            r14 = r2
            goto L_0x03a8
        L_0x03a6:
            r39 = r10
        L_0x03a8:
            int r13 = r13 + 1
            r10 = r39
            r3 = r18
            r1 = r28
            r0 = r37
            goto L_0x0367
        L_0x03b3:
            r28 = r1
            r18 = r3
            r0 = 1
            if (r14 <= r0) goto L_0x03dc
            int[] r0 = new int[r14]
            r1 = 0
            r2 = 0
        L_0x03be:
            int r3 = r6.a
            if (r1 >= r3) goto L_0x03de
            com.google.ads.interactivemedia.v3.internal.bs r3 = r6.a(r1)
            r10 = r7[r1]
            java.lang.Object r13 = com.google.ads.interactivemedia.v3.internal.qi.a(r15)
            com.google.ads.interactivemedia.v3.internal.rj r13 = (com.google.ads.interactivemedia.v3.internal.rj) r13
            boolean r3 = a(r3, r10, r13, r8, r9)
            if (r3 == 0) goto L_0x03d9
            int r3 = r2 + 1
            r0[r2] = r1
            r2 = r3
        L_0x03d9:
            int r1 = r1 + 1
            goto L_0x03be
        L_0x03dc:
            int[] r0 = a
        L_0x03de:
            int r1 = r0.length
            if (r1 <= 0) goto L_0x03ed
            com.google.ads.interactivemedia.v3.internal.ru r1 = new com.google.ads.interactivemedia.v3.internal.ru
            r1.<init>(r6, r0)
            goto L_0x03ee
        L_0x03e7:
            r28 = r1
            r17 = r2
            r18 = r3
        L_0x03ed:
            r1 = 0
        L_0x03ee:
            if (r1 != 0) goto L_0x03fb
            com.google.ads.interactivemedia.v3.internal.ru r1 = new com.google.ads.interactivemedia.v3.internal.ru
            r0 = 1
            int[] r2 = new int[r0]
            r0 = 0
            r2[r0] = r12
            r1.<init>(r6, r2)
        L_0x03fb:
            java.lang.Object r0 = com.google.ads.interactivemedia.v3.internal.qi.a(r11)
            com.google.ads.interactivemedia.v3.internal.rk r0 = (com.google.ads.interactivemedia.v3.internal.rk) r0
            android.util.Pair r12 = android.util.Pair.create(r1, r0)
        L_0x0405:
            if (r12 == 0) goto L_0x042f
            if (r4 == 0) goto L_0x0415
            java.lang.Object r0 = r12.second
            com.google.ads.interactivemedia.v3.internal.rk r0 = (com.google.ads.interactivemedia.v3.internal.rk) r0
            int r0 = r0.compareTo(r4)
            if (r0 <= 0) goto L_0x042f
            r0 = -1
            goto L_0x0416
        L_0x0415:
            r0 = -1
        L_0x0416:
            if (r5 == r0) goto L_0x041b
            r0 = 0
            r30[r5] = r0
        L_0x041b:
            java.lang.Object r0 = r12.first
            com.google.ads.interactivemedia.v3.internal.ru r0 = (com.google.ads.interactivemedia.v3.internal.ru) r0
            r30[r18] = r0
            java.lang.Object r0 = r12.second
            com.google.ads.interactivemedia.v3.internal.rk r0 = (com.google.ads.interactivemedia.v3.internal.rk) r0
            r4 = r0
            r5 = r18
            goto L_0x042f
        L_0x0429:
            r28 = r1
            r17 = r2
            r18 = r3
        L_0x042f:
            int r3 = r18 + 1
            r2 = r17
            r1 = r28
            r0 = r37
            goto L_0x02ce
        L_0x0439:
            r28 = r1
            r17 = r2
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r28
            r1 = 0
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = -1
        L_0x0445:
            if (r1 >= r0) goto L_0x05df
            r4 = r37
            int r5 = r4.a(r1)
            switch(r5) {
                case 1: goto L_0x0566;
                case 2: goto L_0x0566;
                case 3: goto L_0x0461;
                default: goto L_0x0450;
            }
        L_0x0450:
            r28 = r0
            r15 = r17
            r0 = 0
            com.google.ads.interactivemedia.v3.internal.mz r5 = r4.b(r1)
            r6 = r38[r1]
            r8 = r0
            r7 = 0
            r9 = 0
            r10 = 0
            goto L_0x056f
        L_0x0461:
            com.google.ads.interactivemedia.v3.internal.mz r5 = r4.b(r1)
            r6 = r38[r1]
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x046b:
            int r11 = r5.b
            if (r7 >= r11) goto L_0x0520
            com.google.ads.interactivemedia.v3.internal.mx r11 = r5.a(r7)
            r12 = r6[r7]
            r13 = r10
            r10 = r9
            r9 = r8
            r8 = 0
        L_0x0479:
            int r14 = r11.a
            if (r8 >= r14) goto L_0x050c
            r14 = r12[r8]
            r28 = r0
            r15 = r17
            boolean r0 = r15.s
            boolean r0 = a(r14, r0)
            if (r0 == 0) goto L_0x04f8
            com.google.ads.interactivemedia.v3.internal.bs r0 = r11.a(r8)
            int r14 = r0.c
            r39 = r5
            int r5 = r15.y
            int r5 = ~r5
            r5 = r5 & r14
            r14 = r5 & 1
            if (r14 == 0) goto L_0x049d
            r14 = 1
            goto L_0x049e
        L_0x049d:
            r14 = 0
        L_0x049e:
            r5 = r5 & 2
            if (r5 == 0) goto L_0x04a6
            r17 = r6
            r5 = 1
            goto L_0x04a9
        L_0x04a6:
            r17 = r6
            r5 = 0
        L_0x04a9:
            java.lang.String r6 = r15.w
            int r6 = a(r0, r6)
            if (r6 > 0) goto L_0x04d9
            r18 = r9
            boolean r9 = r15.x
            if (r9 == 0) goto L_0x04d0
            java.lang.String r9 = r0.x
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L_0x04cc
            java.lang.String r0 = r0.x
            java.lang.String r9 = "und"
            boolean r0 = android.text.TextUtils.equals(r0, r9)
            if (r0 == 0) goto L_0x04ca
            goto L_0x04cc
        L_0x04ca:
            r0 = 0
            goto L_0x04cd
        L_0x04cc:
            r0 = 1
        L_0x04cd:
            if (r0 == 0) goto L_0x04d0
            goto L_0x04db
        L_0x04d0:
            if (r14 == 0) goto L_0x04d4
            r0 = 4
            goto L_0x04e7
        L_0x04d4:
            if (r5 == 0) goto L_0x04fe
            int r0 = r6 + 1
            goto L_0x04e7
        L_0x04d9:
            r18 = r9
        L_0x04db:
            if (r14 == 0) goto L_0x04e0
            r0 = 11
            goto L_0x04e6
        L_0x04e0:
            if (r5 != 0) goto L_0x04e5
            r0 = 8
            goto L_0x04e6
        L_0x04e5:
            r0 = 5
        L_0x04e6:
            int r0 = r0 + r6
        L_0x04e7:
            r5 = r12[r8]
            r6 = 0
            boolean r5 = a(r5, r6)
            if (r5 == 0) goto L_0x04f2
            int r0 = r0 + 1000
        L_0x04f2:
            if (r0 <= r13) goto L_0x04fe
            r13 = r0
            r10 = r8
            r9 = r11
            goto L_0x0500
        L_0x04f8:
            r39 = r5
            r17 = r6
            r18 = r9
        L_0x04fe:
            r9 = r18
        L_0x0500:
            int r8 = r8 + 1
            r5 = r39
            r6 = r17
            r0 = r28
            r17 = r15
            goto L_0x0479
        L_0x050c:
            r28 = r0
            r39 = r5
            r18 = r9
            r15 = r17
            r17 = r6
            int r7 = r7 + 1
            r9 = r10
            r10 = r13
            r8 = r18
            r17 = r15
            goto L_0x046b
        L_0x0520:
            r28 = r0
            r15 = r17
            if (r8 != 0) goto L_0x0528
            r12 = 0
            goto L_0x053b
        L_0x0528:
            com.google.ads.interactivemedia.v3.internal.ru r0 = new com.google.ads.interactivemedia.v3.internal.ru
            r5 = 1
            int[] r6 = new int[r5]
            r5 = 0
            r6[r5] = r9
            r0.<init>(r8, r6)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r10)
            android.util.Pair r12 = android.util.Pair.create(r0, r5)
        L_0x053b:
            if (r12 == 0) goto L_0x0561
            java.lang.Object r0 = r12.second
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            if (r0 <= r2) goto L_0x0561
            r0 = -1
            if (r3 == r0) goto L_0x054e
            r0 = 0
            r30[r3] = r0
            goto L_0x054f
        L_0x054e:
            r0 = 0
        L_0x054f:
            java.lang.Object r2 = r12.first
            com.google.ads.interactivemedia.v3.internal.ru r2 = (com.google.ads.interactivemedia.v3.internal.ru) r2
            r30[r1] = r2
            java.lang.Object r2 = r12.second
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r3 = r1
            r0 = 0
            goto L_0x05d7
        L_0x0561:
            r17 = r2
            r0 = 0
            goto L_0x05d5
        L_0x0566:
            r28 = r0
            r15 = r17
            r17 = r2
            r0 = 0
            goto L_0x05d5
        L_0x056f:
            int r11 = r5.b
            if (r7 >= r11) goto L_0x05c1
            com.google.ads.interactivemedia.v3.internal.mx r11 = r5.a(r7)
            r12 = r6[r7]
            r13 = r10
            r10 = r9
            r9 = r8
            r8 = 0
        L_0x057d:
            int r14 = r11.a
            if (r8 >= r14) goto L_0x05b8
            r14 = r12[r8]
            boolean r0 = r15.s
            boolean r0 = a(r14, r0)
            if (r0 == 0) goto L_0x05b0
            com.google.ads.interactivemedia.v3.internal.bs r0 = r11.a(r8)
            int r0 = r0.c
            r14 = 1
            r0 = r0 & r14
            if (r0 == 0) goto L_0x0597
            r0 = 1
            goto L_0x0598
        L_0x0597:
            r0 = 0
        L_0x0598:
            if (r0 == 0) goto L_0x059c
            r0 = 2
            goto L_0x059d
        L_0x059c:
            r0 = 1
        L_0x059d:
            r14 = r12[r8]
            r17 = r2
            r2 = 0
            boolean r14 = a(r14, r2)
            if (r14 == 0) goto L_0x05aa
            int r0 = r0 + 1000
        L_0x05aa:
            if (r0 <= r13) goto L_0x05b2
            r13 = r0
            r10 = r8
            r9 = r11
            goto L_0x05b2
        L_0x05b0:
            r17 = r2
        L_0x05b2:
            int r8 = r8 + 1
            r2 = r17
            r0 = 0
            goto L_0x057d
        L_0x05b8:
            r17 = r2
            int r7 = r7 + 1
            r8 = r9
            r9 = r10
            r10 = r13
            r0 = 0
            goto L_0x056f
        L_0x05c1:
            r17 = r2
            if (r8 != 0) goto L_0x05c8
            r0 = 0
            r12 = 0
            goto L_0x05d3
        L_0x05c8:
            com.google.ads.interactivemedia.v3.internal.ru r12 = new com.google.ads.interactivemedia.v3.internal.ru
            r0 = 1
            int[] r2 = new int[r0]
            r0 = 0
            r2[r0] = r9
            r12.<init>(r8, r2)
        L_0x05d3:
            r30[r1] = r12
        L_0x05d5:
            r2 = r17
        L_0x05d7:
            int r1 = r1 + 1
            r17 = r15
            r0 = r28
            goto L_0x0445
        L_0x05df:
            r15 = r17
            r0 = 0
            r4 = r37
            r1 = r27
            r2 = 0
        L_0x05e7:
            if (r2 >= r1) goto L_0x0615
            boolean r3 = r15.a(r2)
            if (r3 == 0) goto L_0x05f1
            r12 = 0
            goto L_0x0610
        L_0x05f1:
            com.google.ads.interactivemedia.v3.internal.mz r3 = r4.b(r2)
            boolean r5 = r15.a(r2, r3)
            if (r5 == 0) goto L_0x0612
            com.google.ads.interactivemedia.v3.internal.ri$a r5 = r15.b(r2, r3)
            if (r5 != 0) goto L_0x0603
            r12 = 0
            goto L_0x0610
        L_0x0603:
            com.google.ads.interactivemedia.v3.internal.ru r12 = new com.google.ads.interactivemedia.v3.internal.ru
            int r6 = r5.a
            com.google.ads.interactivemedia.v3.internal.mx r3 = r3.a(r6)
            int[] r5 = r5.b
            r12.<init>(r3, r5)
        L_0x0610:
            r30[r2] = r12
        L_0x0612:
            int r2 = r2 + 1
            goto L_0x05e7
        L_0x0615:
            r2 = r36
            com.google.ads.interactivemedia.v3.internal.rv r3 = r2.b
            com.google.ads.interactivemedia.v3.internal.sh r5 = r36.a()
            r6 = r30
            com.google.ads.interactivemedia.v3.internal.rt[] r3 = r3.a(r6, r5)
            com.google.ads.interactivemedia.v3.internal.ck[] r5 = new com.google.ads.interactivemedia.v3.internal.ck[r1]
            r6 = 0
        L_0x0626:
            if (r6 >= r1) goto L_0x0647
            boolean r7 = r15.a(r6)
            if (r7 != 0) goto L_0x063b
            int r7 = r4.a(r6)
            r8 = 6
            if (r7 == r8) goto L_0x0639
            r7 = r3[r6]
            if (r7 == 0) goto L_0x063b
        L_0x0639:
            r7 = 1
            goto L_0x063c
        L_0x063b:
            r7 = 0
        L_0x063c:
            if (r7 == 0) goto L_0x0641
            com.google.ads.interactivemedia.v3.internal.ck r12 = com.google.ads.interactivemedia.v3.internal.ck.a
            goto L_0x0642
        L_0x0641:
            r12 = 0
        L_0x0642:
            r5[r6] = r12
            int r6 = r6 + 1
            goto L_0x0626
        L_0x0647:
            int r1 = r15.t
            if (r1 == 0) goto L_0x06c1
            r6 = 0
            r7 = -1
            r8 = -1
        L_0x064e:
            int r9 = r37.a()
            if (r6 >= r9) goto L_0x06ac
            int r9 = r4.a(r6)
            r10 = r3[r6]
            r11 = 1
            if (r9 == r11) goto L_0x0661
            r11 = 2
            if (r9 != r11) goto L_0x06a6
            goto L_0x0662
        L_0x0661:
            r11 = 2
        L_0x0662:
            if (r10 == 0) goto L_0x06a6
            r12 = r38[r6]
            com.google.ads.interactivemedia.v3.internal.mz r13 = r4.b(r6)
            if (r10 != 0) goto L_0x066d
            goto L_0x0690
        L_0x066d:
            com.google.ads.interactivemedia.v3.internal.mx r14 = r10.f()
            int r13 = r13.a(r14)
            r14 = 0
        L_0x0676:
            int r15 = r10.g()
            if (r14 >= r15) goto L_0x068f
            r15 = r12[r13]
            int r16 = r10.b(r14)
            r15 = r15[r16]
            r0 = 32
            r15 = r15 & r0
            if (r15 == r0) goto L_0x068b
            r0 = 0
            goto L_0x0690
        L_0x068b:
            int r14 = r14 + 1
            r0 = 0
            goto L_0x0676
        L_0x068f:
            r0 = 1
        L_0x0690:
            if (r0 == 0) goto L_0x06a3
            r0 = 1
            if (r9 != r0) goto L_0x069c
            r9 = -1
            if (r8 == r9) goto L_0x069a
            r4 = 0
            goto L_0x06af
        L_0x069a:
            r8 = r6
            goto L_0x06a8
        L_0x069c:
            r9 = -1
            if (r7 == r9) goto L_0x06a1
            r4 = 0
            goto L_0x06af
        L_0x06a1:
            r7 = r6
            goto L_0x06a8
        L_0x06a3:
            r0 = 1
            r9 = -1
            goto L_0x06a8
        L_0x06a6:
            r0 = 1
            r9 = -1
        L_0x06a8:
            int r6 = r6 + 1
            r0 = 0
            goto L_0x064e
        L_0x06ac:
            r0 = 1
            r9 = -1
            r4 = 1
        L_0x06af:
            if (r8 == r9) goto L_0x06b4
            if (r7 == r9) goto L_0x06b4
            goto L_0x06b5
        L_0x06b4:
            r0 = 0
        L_0x06b5:
            r0 = r0 & r4
            if (r0 == 0) goto L_0x06c1
            com.google.ads.interactivemedia.v3.internal.ck r0 = new com.google.ads.interactivemedia.v3.internal.ck
            r0.<init>(r1)
            r5[r8] = r0
            r5[r7] = r0
        L_0x06c1:
            android.util.Pair r0 = android.util.Pair.create(r5, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ri.a(com.google.ads.interactivemedia.v3.internal.rp, int[][][], int[]):android.util.Pair");
    }

    private static boolean a(bs bsVar, String str, int i, int i2, int i3, int i4, int i5, int i6) {
        if (!a(i, false) || (i & i2) == 0 || ((str != null && !vf.a((Object) bsVar.h, (Object) str)) || ((bsVar.m != -1 && bsVar.m > i3) || ((bsVar.n != -1 && bsVar.n > i4) || ((bsVar.o != -1.0f && bsVar.o > ((float) i5)) || (bsVar.d != -1 && bsVar.d > i6)))))) {
            return false;
        }
        return true;
    }

    private static boolean a(bs bsVar, int i, rj rjVar, boolean z, boolean z2) {
        if (!a(i, false) || bsVar.s == -1 || bsVar.s != rjVar.a || ((!z && (bsVar.h == null || !TextUtils.equals(bsVar.h, rjVar.c))) || (!z2 && (bsVar.t == -1 || bsVar.t != rjVar.b)))) {
            return false;
        }
        return true;
    }

    protected static int a(bs bsVar, String str) {
        if (str == null) {
            return 0;
        }
        if (TextUtils.equals(str, bsVar.x)) {
            return 2;
        }
        if (bsVar.x == null || !bsVar.x.startsWith(str)) {
            return 0;
        }
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<java.lang.Integer> a(com.google.ads.interactivemedia.v3.internal.mx r12, int r13, int r14, boolean r15) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r12.a
            r0.<init>(r1)
            r1 = 0
            r2 = 0
        L_0x0009:
            int r3 = r12.a
            if (r2 >= r3) goto L_0x0017
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r0.add(r3)
            int r2 = r2 + 1
            goto L_0x0009
        L_0x0017:
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r13 == r2) goto L_0x00af
            if (r14 != r2) goto L_0x0020
            goto L_0x00af
        L_0x0020:
            r3 = 0
            r4 = 2147483647(0x7fffffff, float:NaN)
        L_0x0024:
            int r5 = r12.a
            r6 = 1
            if (r3 >= r5) goto L_0x0088
            com.google.ads.interactivemedia.v3.internal.bs r5 = r12.a(r3)
            int r7 = r5.m
            if (r7 <= 0) goto L_0x0085
            int r7 = r5.n
            if (r7 <= 0) goto L_0x0085
            int r7 = r5.m
            int r8 = r5.n
            if (r15 == 0) goto L_0x0049
            if (r7 <= r8) goto L_0x003f
            r9 = 1
            goto L_0x0040
        L_0x003f:
            r9 = 0
        L_0x0040:
            if (r13 <= r14) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r6 = 0
        L_0x0044:
            if (r9 == r6) goto L_0x0049
            r6 = r13
            r9 = r14
            goto L_0x004b
        L_0x0049:
            r9 = r13
            r6 = r14
        L_0x004b:
            int r10 = r7 * r6
            int r11 = r8 * r9
            if (r10 < r11) goto L_0x005b
            android.graphics.Point r6 = new android.graphics.Point
            int r7 = com.google.ads.interactivemedia.v3.internal.vf.a(r11, r7)
            r6.<init>(r9, r7)
            goto L_0x0065
        L_0x005b:
            android.graphics.Point r7 = new android.graphics.Point
            int r8 = com.google.ads.interactivemedia.v3.internal.vf.a(r10, r8)
            r7.<init>(r8, r6)
            r6 = r7
        L_0x0065:
            int r7 = r5.m
            int r8 = r5.n
            int r7 = r7 * r8
            int r8 = r5.m
            int r9 = r6.x
            float r9 = (float) r9
            r10 = 1065017672(0x3f7ae148, float:0.98)
            float r9 = r9 * r10
            int r9 = (int) r9
            if (r8 < r9) goto L_0x0085
            int r5 = r5.n
            int r6 = r6.y
            float r6 = (float) r6
            float r6 = r6 * r10
            int r6 = (int) r6
            if (r5 < r6) goto L_0x0085
            if (r7 >= r4) goto L_0x0085
            r4 = r7
        L_0x0085:
            int r3 = r3 + 1
            goto L_0x0024
        L_0x0088:
            if (r4 == r2) goto L_0x00ae
            int r13 = r0.size()
            int r13 = r13 - r6
        L_0x008f:
            if (r13 < 0) goto L_0x00ae
            java.lang.Object r14 = r0.get(r13)
            java.lang.Integer r14 = (java.lang.Integer) r14
            int r14 = r14.intValue()
            com.google.ads.interactivemedia.v3.internal.bs r14 = r12.a(r14)
            int r14 = r14.a()
            r15 = -1
            if (r14 == r15) goto L_0x00a8
            if (r14 <= r4) goto L_0x00ab
        L_0x00a8:
            r0.remove(r13)
        L_0x00ab:
            int r13 = r13 + -1
            goto L_0x008f
        L_0x00ae:
            return r0
        L_0x00af:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ri.a(com.google.ads.interactivemedia.v3.internal.mx, int, int, boolean):java.util.List");
    }
}
