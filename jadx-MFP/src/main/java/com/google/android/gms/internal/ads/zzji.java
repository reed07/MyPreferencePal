package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.ads.zzhp.zza;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public final class zzji implements zzhz {
    private static final zzic zzahq = new zzjj();
    private static final int zzaps = zzqe.zzam("seig");
    private static final byte[] zzapt = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, -12};
    private final int flags;
    private long zzaan;
    private final zzpx zzahx;
    private int zzajm;
    private int zzajn;
    private zzib zzajq;
    private final zzjs zzapu;
    private final SparseArray<zzjl> zzapv;
    private final zzpx zzapw;
    private final zzpx zzapx;
    private final zzpx zzapy;
    private final zzqb zzapz;
    private final zzpx zzaqa;
    private final byte[] zzaqb;
    private final Stack<zziw> zzaqc;
    private final LinkedList<zzjk> zzaqd;
    private int zzaqe;
    private int zzaqf;
    private long zzaqg;
    private int zzaqh;
    private zzpx zzaqi;
    private long zzaqj;
    private int zzaqk;
    private long zzaql;
    private zzjl zzaqm;
    private int zzaqn;
    private boolean zzaqo;
    private zzii zzaqp;
    private zzii[] zzaqq;
    private boolean zzaqr;

    public zzji() {
        this(0);
    }

    public final void release() {
    }

    public zzji(int i) {
        this(i, null);
    }

    private zzji(int i, zzqb zzqb) {
        this(i, null, null);
    }

    private zzji(int i, zzqb zzqb, zzjs zzjs) {
        this.flags = i;
        this.zzapz = null;
        this.zzapu = null;
        this.zzaqa = new zzpx(16);
        this.zzahx = new zzpx(zzpu.zzbhi);
        this.zzapw = new zzpx(5);
        this.zzapx = new zzpx();
        this.zzapy = new zzpx(1);
        this.zzaqb = new byte[16];
        this.zzaqc = new Stack<>();
        this.zzaqd = new LinkedList<>();
        this.zzapv = new SparseArray<>();
        this.zzaan = -9223372036854775807L;
        this.zzaql = -9223372036854775807L;
        zzei();
    }

    public final boolean zza(zzia zzia) throws IOException, InterruptedException {
        return zzjr.zzd(zzia);
    }

    public final void zza(zzib zzib) {
        this.zzajq = zzib;
    }

    public final void zzc(long j, long j2) {
        int size = this.zzapv.size();
        for (int i = 0; i < size; i++) {
            ((zzjl) this.zzapv.valueAt(i)).reset();
        }
        this.zzaqd.clear();
        this.zzaqk = 0;
        this.zzaqc.clear();
        zzei();
    }

    /* JADX WARNING: Removed duplicated region for block: B:259:0x0367 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x05fd A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x0004 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x0004 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(com.google.android.gms.internal.ads.zzia r27, com.google.android.gms.internal.ads.zzif r28) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
        L_0x0004:
            int r2 = r0.zzaqe
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r5 = 3
            r6 = 2
            r7 = 4
            r8 = 0
            r9 = 8
            r11 = 0
            switch(r2) {
                case 0: goto L_0x01dd;
                case 1: goto L_0x007d;
                case 2: goto L_0x0026;
                default: goto L_0x0015;
            }
        L_0x0015:
            if (r2 != r5) goto L_0x0462
            com.google.android.gms.internal.ads.zzjl r2 = r0.zzaqm
            if (r2 != 0) goto L_0x03d5
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r2 = r0.zzapv
            int r7 = r2.size()
            r12 = r3
            r4 = r8
            r3 = 0
            goto L_0x0379
        L_0x0026:
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r2 = r0.zzapv
            int r2 = r2.size()
            r6 = r3
            r3 = 0
        L_0x002e:
            if (r3 >= r2) goto L_0x0052
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r4 = r0.zzapv
            java.lang.Object r4 = r4.valueAt(r3)
            com.google.android.gms.internal.ads.zzjl r4 = (com.google.android.gms.internal.ads.zzjl) r4
            com.google.android.gms.internal.ads.zzju r4 = r4.zzaqt
            boolean r9 = r4.zzatj
            if (r9 == 0) goto L_0x004f
            long r9 = r4.zzasw
            int r12 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r12 >= 0) goto L_0x004f
            long r6 = r4.zzasw
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r4 = r0.zzapv
            java.lang.Object r4 = r4.valueAt(r3)
            com.google.android.gms.internal.ads.zzjl r4 = (com.google.android.gms.internal.ads.zzjl) r4
            r8 = r4
        L_0x004f:
            int r3 = r3 + 1
            goto L_0x002e
        L_0x0052:
            if (r8 != 0) goto L_0x0057
            r0.zzaqe = r5
            goto L_0x0004
        L_0x0057:
            long r2 = r27.getPosition()
            long r6 = r6 - r2
            int r2 = (int) r6
            if (r2 < 0) goto L_0x0075
            r1.zzw(r2)
            com.google.android.gms.internal.ads.zzju r2 = r8.zzaqt
            com.google.android.gms.internal.ads.zzpx r3 = r2.zzati
            byte[] r3 = r3.data
            int r4 = r2.zzath
            r1.readFully(r3, r11, r4)
            com.google.android.gms.internal.ads.zzpx r3 = r2.zzati
            r3.setPosition(r11)
            r2.zzatj = r11
            goto L_0x0004
        L_0x0075:
            com.google.android.gms.internal.ads.zzfx r1 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r2 = "Offset to encryption data was negative."
            r1.<init>(r2)
            throw r1
        L_0x007d:
            long r2 = r0.zzaqg
            int r3 = (int) r2
            int r2 = r0.zzaqh
            int r3 = r3 - r2
            com.google.android.gms.internal.ads.zzpx r2 = r0.zzaqi
            if (r2 == 0) goto L_0x01d1
            byte[] r2 = r2.data
            r1.readFully(r2, r9, r3)
            com.google.android.gms.internal.ads.zzix r2 = new com.google.android.gms.internal.ads.zzix
            int r3 = r0.zzaqf
            com.google.android.gms.internal.ads.zzpx r4 = r0.zzaqi
            r2.<init>(r3, r4)
            long r3 = r27.getPosition()
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r5 = r0.zzaqc
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x00ae
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r3 = r0.zzaqc
            java.lang.Object r3 = r3.peek()
            com.google.android.gms.internal.ads.zziw r3 = (com.google.android.gms.internal.ads.zziw) r3
            r3.zza(r2)
            goto L_0x01d4
        L_0x00ae:
            int r5 = r2.type
            int r8 = com.google.android.gms.internal.ads.zziv.zzama
            if (r5 != r8) goto L_0x0179
            com.google.android.gms.internal.ads.zzpx r2 = r2.zzaos
            r2.setPosition(r9)
            int r5 = r2.readInt()
            int r5 = com.google.android.gms.internal.ads.zziv.zzaf(r5)
            r2.zzbl(r7)
            long r8 = r2.zzhd()
            if (r5 != 0) goto L_0x00d7
            long r12 = r2.zzhd()
            long r14 = r2.zzhd()
            long r3 = r3 + r14
            r18 = r3
            r3 = r12
            goto L_0x00e3
        L_0x00d7:
            long r12 = r2.zzhh()
            long r14 = r2.zzhh()
            long r3 = r3 + r14
            r18 = r3
            r3 = r12
        L_0x00e3:
            r14 = 1000000(0xf4240, double:4.940656E-318)
            r12 = r3
            r16 = r8
            long r20 = com.google.android.gms.internal.ads.zzqe.zza(r12, r14, r16)
            r2.zzbl(r6)
            int r5 = r2.readUnsignedShort()
            int[] r6 = new int[r5]
            long[] r14 = new long[r5]
            long[] r15 = new long[r5]
            long[] r12 = new long[r5]
            r16 = r3
            r3 = r20
        L_0x0100:
            if (r11 >= r5) goto L_0x014f
            int r13 = r2.readInt()
            r22 = -2147483648(0xffffffff80000000, float:-0.0)
            r22 = r13 & r22
            if (r22 != 0) goto L_0x0147
            long r22 = r2.zzhd()
            r24 = 2147483647(0x7fffffff, float:NaN)
            r13 = r13 & r24
            r6[r11] = r13
            r14[r11] = r18
            r12[r11] = r3
            long r3 = r16 + r22
            r16 = 1000000(0xf4240, double:4.940656E-318)
            r10 = r12
            r12 = r3
            r25 = r14
            r7 = r15
            r14 = r16
            r16 = r8
            long r12 = com.google.android.gms.internal.ads.zzqe.zza(r12, r14, r16)
            r14 = r10[r11]
            long r14 = r12 - r14
            r7[r11] = r14
            r14 = 4
            r2.zzbl(r14)
            r14 = r6[r11]
            long r14 = (long) r14
            long r18 = r18 + r14
            int r11 = r11 + 1
            r16 = r3
            r15 = r7
            r3 = r12
            r14 = r25
            r7 = 4
            r12 = r10
            goto L_0x0100
        L_0x0147:
            com.google.android.gms.internal.ads.zzfx r1 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r2 = "Unhandled indirect reference"
            r1.<init>(r2)
            throw r1
        L_0x014f:
            r10 = r12
            r25 = r14
            r7 = r15
            java.lang.Long r2 = java.lang.Long.valueOf(r20)
            com.google.android.gms.internal.ads.zzhw r3 = new com.google.android.gms.internal.ads.zzhw
            r4 = r25
            r3.<init>(r6, r4, r7, r10)
            android.util.Pair r2 = android.util.Pair.create(r2, r3)
            java.lang.Object r3 = r2.first
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            r0.zzaql = r3
            com.google.android.gms.internal.ads.zzib r3 = r0.zzajq
            java.lang.Object r2 = r2.second
            com.google.android.gms.internal.ads.zzig r2 = (com.google.android.gms.internal.ads.zzig) r2
            r3.zza(r2)
            r2 = 1
            r0.zzaqr = r2
            goto L_0x01d4
        L_0x0179:
            int r3 = r2.type
            int r4 = com.google.android.gms.internal.ads.zziv.zzaog
            if (r3 != r4) goto L_0x01d4
            com.google.android.gms.internal.ads.zzpx r2 = r2.zzaos
            com.google.android.gms.internal.ads.zzii r3 = r0.zzaqp
            if (r3 == 0) goto L_0x01d4
            r3 = 12
            r2.setPosition(r3)
            r2.zzhi()
            r2.zzhi()
            long r8 = r2.zzhd()
            long r4 = r2.zzhd()
            r6 = 1000000(0xf4240, double:4.940656E-318)
            long r4 = com.google.android.gms.internal.ads.zzqe.zza(r4, r6, r8)
            r2.setPosition(r3)
            int r10 = r2.zzhb()
            com.google.android.gms.internal.ads.zzii r3 = r0.zzaqp
            r3.zza(r2, r10)
            long r2 = r0.zzaql
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c1
            com.google.android.gms.internal.ads.zzii r6 = r0.zzaqp
            long r7 = r2 + r4
            r9 = 1
            r11 = 0
            r12 = 0
            r6.zza(r7, r9, r10, r11, r12)
            goto L_0x01d4
        L_0x01c1:
            java.util.LinkedList<com.google.android.gms.internal.ads.zzjk> r2 = r0.zzaqd
            com.google.android.gms.internal.ads.zzjk r3 = new com.google.android.gms.internal.ads.zzjk
            r3.<init>(r4, r10)
            r2.addLast(r3)
            int r2 = r0.zzaqk
            int r2 = r2 + r10
            r0.zzaqk = r2
            goto L_0x01d4
        L_0x01d1:
            r1.zzw(r3)
        L_0x01d4:
            long r2 = r27.getPosition()
            r0.zzt(r2)
            goto L_0x0004
        L_0x01dd:
            int r2 = r0.zzaqh
            if (r2 != 0) goto L_0x0205
            com.google.android.gms.internal.ads.zzpx r2 = r0.zzaqa
            byte[] r2 = r2.data
            r3 = 1
            boolean r2 = r1.zza(r2, r11, r9, r3)
            if (r2 != 0) goto L_0x01ee
            goto L_0x0365
        L_0x01ee:
            r0.zzaqh = r9
            com.google.android.gms.internal.ads.zzpx r2 = r0.zzaqa
            r2.setPosition(r11)
            com.google.android.gms.internal.ads.zzpx r2 = r0.zzaqa
            long r2 = r2.zzhd()
            r0.zzaqg = r2
            com.google.android.gms.internal.ads.zzpx r2 = r0.zzaqa
            int r2 = r2.readInt()
            r0.zzaqf = r2
        L_0x0205:
            long r2 = r0.zzaqg
            r4 = 1
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x0221
            com.google.android.gms.internal.ads.zzpx r2 = r0.zzaqa
            byte[] r2 = r2.data
            r1.readFully(r2, r9, r9)
            int r2 = r0.zzaqh
            int r2 = r2 + r9
            r0.zzaqh = r2
            com.google.android.gms.internal.ads.zzpx r2 = r0.zzaqa
            long r2 = r2.zzhh()
            r0.zzaqg = r2
        L_0x0221:
            long r2 = r0.zzaqg
            int r4 = r0.zzaqh
            long r4 = (long) r4
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 < 0) goto L_0x0371
            long r2 = r27.getPosition()
            int r4 = r0.zzaqh
            long r4 = (long) r4
            long r2 = r2 - r4
            int r4 = r0.zzaqf
            int r5 = com.google.android.gms.internal.ads.zziv.zzamk
            if (r4 != r5) goto L_0x0254
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r4 = r0.zzapv
            int r4 = r4.size()
            r5 = 0
        L_0x023f:
            if (r5 >= r4) goto L_0x0254
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r7 = r0.zzapv
            java.lang.Object r7 = r7.valueAt(r5)
            com.google.android.gms.internal.ads.zzjl r7 = (com.google.android.gms.internal.ads.zzjl) r7
            com.google.android.gms.internal.ads.zzju r7 = r7.zzaqt
            r7.zzasu = r2
            r7.zzasw = r2
            r7.zzasv = r2
            int r5 = r5 + 1
            goto L_0x023f
        L_0x0254:
            int r4 = r0.zzaqf
            int r5 = com.google.android.gms.internal.ads.zziv.zzalh
            if (r4 != r5) goto L_0x0278
            r0.zzaqm = r8
            long r4 = r0.zzaqg
            long r2 = r2 + r4
            r0.zzaqj = r2
            boolean r2 = r0.zzaqr
            if (r2 != 0) goto L_0x0274
            com.google.android.gms.internal.ads.zzib r2 = r0.zzajq
            com.google.android.gms.internal.ads.zzih r3 = new com.google.android.gms.internal.ads.zzih
            long r4 = r0.zzaan
            r3.<init>(r4)
            r2.zza(r3)
            r2 = 1
            r0.zzaqr = r2
        L_0x0274:
            r0.zzaqe = r6
            goto L_0x0364
        L_0x0278:
            int r2 = r0.zzaqf
            int r3 = com.google.android.gms.internal.ads.zziv.zzamb
            if (r2 == r3) goto L_0x02a1
            int r3 = com.google.android.gms.internal.ads.zziv.zzamd
            if (r2 == r3) goto L_0x02a1
            int r3 = com.google.android.gms.internal.ads.zziv.zzame
            if (r2 == r3) goto L_0x02a1
            int r3 = com.google.android.gms.internal.ads.zziv.zzamf
            if (r2 == r3) goto L_0x02a1
            int r3 = com.google.android.gms.internal.ads.zziv.zzamg
            if (r2 == r3) goto L_0x02a1
            int r3 = com.google.android.gms.internal.ads.zziv.zzamk
            if (r2 == r3) goto L_0x02a1
            int r3 = com.google.android.gms.internal.ads.zziv.zzaml
            if (r2 == r3) goto L_0x02a1
            int r3 = com.google.android.gms.internal.ads.zziv.zzamm
            if (r2 == r3) goto L_0x02a1
            int r3 = com.google.android.gms.internal.ads.zziv.zzamp
            if (r2 != r3) goto L_0x029f
            goto L_0x02a1
        L_0x029f:
            r2 = 0
            goto L_0x02a2
        L_0x02a1:
            r2 = 1
        L_0x02a2:
            if (r2 == 0) goto L_0x02cd
            long r2 = r27.getPosition()
            long r4 = r0.zzaqg
            long r2 = r2 + r4
            r4 = 8
            long r2 = r2 - r4
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r4 = r0.zzaqc
            com.google.android.gms.internal.ads.zziw r5 = new com.google.android.gms.internal.ads.zziw
            int r6 = r0.zzaqf
            r5.<init>(r6, r2)
            r4.add(r5)
            long r4 = r0.zzaqg
            int r6 = r0.zzaqh
            long r6 = (long) r6
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x02c8
            r0.zzt(r2)
            goto L_0x0364
        L_0x02c8:
            r26.zzei()
            goto L_0x0364
        L_0x02cd:
            int r2 = r0.zzaqf
            int r3 = com.google.android.gms.internal.ads.zziv.zzams
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzamr
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzamc
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzama
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzamt
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzalw
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzalx
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzamo
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzaly
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzalz
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzamu
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzanc
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzand
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzanh
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzang
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzane
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzanf
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzamq
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzamn
            if (r2 == r3) goto L_0x0322
            int r3 = com.google.android.gms.internal.ads.zziv.zzaog
            if (r2 != r3) goto L_0x0320
            goto L_0x0322
        L_0x0320:
            r2 = 0
            goto L_0x0323
        L_0x0322:
            r2 = 1
        L_0x0323:
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            if (r2 == 0) goto L_0x0359
            int r2 = r0.zzaqh
            if (r2 != r9) goto L_0x0351
            long r5 = r0.zzaqg
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0349
            com.google.android.gms.internal.ads.zzpx r2 = new com.google.android.gms.internal.ads.zzpx
            int r3 = (int) r5
            r2.<init>(r3)
            r0.zzaqi = r2
            com.google.android.gms.internal.ads.zzpx r2 = r0.zzaqa
            byte[] r2 = r2.data
            com.google.android.gms.internal.ads.zzpx r3 = r0.zzaqi
            byte[] r3 = r3.data
            java.lang.System.arraycopy(r2, r11, r3, r11, r9)
            r2 = 1
            r0.zzaqe = r2
            goto L_0x0364
        L_0x0349:
            com.google.android.gms.internal.ads.zzfx r1 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r2 = "Leaf atom with length > 2147483647 (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x0351:
            com.google.android.gms.internal.ads.zzfx r1 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r2 = "Leaf atom defines extended atom size (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x0359:
            long r5 = r0.zzaqg
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0369
            r0.zzaqi = r8
            r2 = 1
            r0.zzaqe = r2
        L_0x0364:
            r11 = 1
        L_0x0365:
            if (r11 != 0) goto L_0x0004
            r1 = -1
            return r1
        L_0x0369:
            com.google.android.gms.internal.ads.zzfx r1 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r2 = "Skipping atom with length > 2147483647 (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x0371:
            com.google.android.gms.internal.ads.zzfx r1 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r2 = "Atom size less than header length (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x0379:
            if (r3 >= r7) goto L_0x039a
            java.lang.Object r10 = r2.valueAt(r3)
            com.google.android.gms.internal.ads.zzjl r10 = (com.google.android.gms.internal.ads.zzjl) r10
            int r14 = r10.zzaqy
            com.google.android.gms.internal.ads.zzju r15 = r10.zzaqt
            int r15 = r15.zzasx
            if (r14 == r15) goto L_0x0397
            com.google.android.gms.internal.ads.zzju r14 = r10.zzaqt
            long[] r14 = r14.zzasy
            int r15 = r10.zzaqy
            r15 = r14[r15]
            int r14 = (r15 > r12 ? 1 : (r15 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x0397
            r4 = r10
            r12 = r15
        L_0x0397:
            int r3 = r3 + 1
            goto L_0x0379
        L_0x039a:
            if (r4 != 0) goto L_0x03b8
            long r2 = r0.zzaqj
            long r4 = r27.getPosition()
            long r2 = r2 - r4
            int r3 = (int) r2
            if (r3 < 0) goto L_0x03b0
            r1.zzw(r3)
            r26.zzei()
            r3 = 0
            r5 = 0
            goto L_0x05fb
        L_0x03b0:
            com.google.android.gms.internal.ads.zzfx r1 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r2 = "Offset to end of mdat was negative."
            r1.<init>(r2)
            throw r1
        L_0x03b8:
            com.google.android.gms.internal.ads.zzju r2 = r4.zzaqt
            long[] r2 = r2.zzasy
            int r3 = r4.zzaqy
            r12 = r2[r3]
            long r2 = r27.getPosition()
            long r12 = r12 - r2
            int r2 = (int) r12
            if (r2 >= 0) goto L_0x03d0
            java.lang.String r2 = "FragmentedMp4Extractor"
            java.lang.String r3 = "Ignoring negative offset to sample data."
            android.util.Log.w(r2, r3)
            r2 = 0
        L_0x03d0:
            r1.zzw(r2)
            r0.zzaqm = r4
        L_0x03d5:
            com.google.android.gms.internal.ads.zzjl r2 = r0.zzaqm
            com.google.android.gms.internal.ads.zzju r2 = r2.zzaqt
            int[] r2 = r2.zzata
            com.google.android.gms.internal.ads.zzjl r3 = r0.zzaqm
            int r3 = r3.zzaqw
            r2 = r2[r3]
            r0.zzaqn = r2
            com.google.android.gms.internal.ads.zzjl r2 = r0.zzaqm
            com.google.android.gms.internal.ads.zzju r2 = r2.zzaqt
            boolean r2 = r2.zzate
            if (r2 == 0) goto L_0x0448
            com.google.android.gms.internal.ads.zzjl r2 = r0.zzaqm
            com.google.android.gms.internal.ads.zzju r3 = r2.zzaqt
            com.google.android.gms.internal.ads.zzpx r4 = r3.zzati
            com.google.android.gms.internal.ads.zzjf r7 = r3.zzast
            int r7 = r7.zzapo
            com.google.android.gms.internal.ads.zzjt r10 = r3.zzatg
            if (r10 == 0) goto L_0x03fc
            com.google.android.gms.internal.ads.zzjt r7 = r3.zzatg
            goto L_0x0402
        L_0x03fc:
            com.google.android.gms.internal.ads.zzjs r10 = r2.zzaqu
            com.google.android.gms.internal.ads.zzjt[] r10 = r10.zzasn
            r7 = r10[r7]
        L_0x0402:
            int r7 = r7.zzasr
            boolean[] r3 = r3.zzatf
            int r10 = r2.zzaqw
            boolean r3 = r3[r10]
            com.google.android.gms.internal.ads.zzpx r10 = r0.zzapy
            byte[] r10 = r10.data
            if (r3 == 0) goto L_0x0413
            r12 = 128(0x80, float:1.794E-43)
            goto L_0x0414
        L_0x0413:
            r12 = 0
        L_0x0414:
            r12 = r12 | r7
            byte r12 = (byte) r12
            r10[r11] = r12
            com.google.android.gms.internal.ads.zzpx r10 = r0.zzapy
            r10.setPosition(r11)
            com.google.android.gms.internal.ads.zzii r2 = r2.zzakw
            com.google.android.gms.internal.ads.zzpx r10 = r0.zzapy
            r12 = 1
            r2.zza(r10, r12)
            r2.zza(r4, r7)
            if (r3 != 0) goto L_0x042d
            int r7 = r7 + 1
            goto L_0x043e
        L_0x042d:
            int r3 = r4.readUnsignedShort()
            r10 = -2
            r4.zzbl(r10)
            int r3 = r3 * 6
            int r3 = r3 + r6
            r2.zza(r4, r3)
            int r7 = r7 + 1
            int r7 = r7 + r3
        L_0x043e:
            r0.zzajn = r7
            int r2 = r0.zzaqn
            int r3 = r0.zzajn
            int r2 = r2 + r3
            r0.zzaqn = r2
            goto L_0x044a
        L_0x0448:
            r0.zzajn = r11
        L_0x044a:
            com.google.android.gms.internal.ads.zzjl r2 = r0.zzaqm
            com.google.android.gms.internal.ads.zzjs r2 = r2.zzaqu
            int r2 = r2.zzasm
            r3 = 1
            if (r2 != r3) goto L_0x045d
            int r2 = r0.zzaqn
            int r2 = r2 - r9
            r0.zzaqn = r2
            r1.zzw(r9)
            r2 = 4
            goto L_0x045e
        L_0x045d:
            r2 = 4
        L_0x045e:
            r0.zzaqe = r2
            r0.zzajm = r11
        L_0x0462:
            com.google.android.gms.internal.ads.zzjl r2 = r0.zzaqm
            com.google.android.gms.internal.ads.zzju r2 = r2.zzaqt
            com.google.android.gms.internal.ads.zzjl r3 = r0.zzaqm
            com.google.android.gms.internal.ads.zzjs r3 = r3.zzaqu
            com.google.android.gms.internal.ads.zzjl r4 = r0.zzaqm
            com.google.android.gms.internal.ads.zzii r12 = r4.zzakw
            com.google.android.gms.internal.ads.zzjl r4 = r0.zzaqm
            int r4 = r4.zzaqw
            int r7 = r3.zzakx
            r9 = 1000(0x3e8, double:4.94E-321)
            if (r7 == 0) goto L_0x053c
            com.google.android.gms.internal.ads.zzpx r7 = r0.zzapw
            byte[] r7 = r7.data
            r7[r11] = r11
            r13 = 1
            r7[r13] = r11
            r7[r6] = r11
            int r6 = r3.zzakx
            int r6 = r6 + r13
            int r13 = r3.zzakx
            r14 = 4
            int r13 = 4 - r13
        L_0x048b:
            int r14 = r0.zzajn
            int r15 = r0.zzaqn
            if (r14 >= r15) goto L_0x054e
            int r14 = r0.zzajm
            if (r14 != 0) goto L_0x04d9
            r1.readFully(r7, r13, r6)
            com.google.android.gms.internal.ads.zzpx r14 = r0.zzapw
            r14.setPosition(r11)
            com.google.android.gms.internal.ads.zzpx r14 = r0.zzapw
            int r14 = r14.zzhg()
            r15 = 1
            int r14 = r14 - r15
            r0.zzajm = r14
            com.google.android.gms.internal.ads.zzpx r14 = r0.zzahx
            r14.setPosition(r11)
            com.google.android.gms.internal.ads.zzpx r14 = r0.zzahx
            r5 = 4
            r12.zza(r14, r5)
            com.google.android.gms.internal.ads.zzpx r14 = r0.zzapw
            r12.zza(r14, r15)
            com.google.android.gms.internal.ads.zzii[] r14 = r0.zzaqq
            if (r14 == 0) goto L_0x04c9
            com.google.android.gms.internal.ads.zzfs r14 = r3.zzaad
            java.lang.String r14 = r14.zzzj
            byte r15 = r7[r5]
            boolean r14 = com.google.android.gms.internal.ads.zzpu.zza(r14, r15)
            if (r14 == 0) goto L_0x04c9
            r14 = 1
            goto L_0x04ca
        L_0x04c9:
            r14 = 0
        L_0x04ca:
            r0.zzaqo = r14
            int r14 = r0.zzajn
            int r14 = r14 + 5
            r0.zzajn = r14
            int r14 = r0.zzaqn
            int r14 = r14 + r13
            r0.zzaqn = r14
            r5 = 3
            goto L_0x048b
        L_0x04d9:
            r5 = 4
            boolean r15 = r0.zzaqo
            if (r15 == 0) goto L_0x0526
            com.google.android.gms.internal.ads.zzpx r15 = r0.zzapx
            r15.reset(r14)
            com.google.android.gms.internal.ads.zzpx r14 = r0.zzapx
            byte[] r14 = r14.data
            int r15 = r0.zzajm
            r1.readFully(r14, r11, r15)
            com.google.android.gms.internal.ads.zzpx r14 = r0.zzapx
            int r15 = r0.zzajm
            r12.zza(r14, r15)
            int r14 = r0.zzajm
            com.google.android.gms.internal.ads.zzpx r15 = r0.zzapx
            byte[] r15 = r15.data
            com.google.android.gms.internal.ads.zzpx r5 = r0.zzapx
            int r5 = r5.limit()
            int r5 = com.google.android.gms.internal.ads.zzpu.zzb(r15, r5)
            com.google.android.gms.internal.ads.zzpx r15 = r0.zzapx
            java.lang.String r8 = "video/hevc"
            com.google.android.gms.internal.ads.zzfs r11 = r3.zzaad
            java.lang.String r11 = r11.zzzj
            boolean r8 = r8.equals(r11)
            r15.setPosition(r8)
            com.google.android.gms.internal.ads.zzpx r8 = r0.zzapx
            r8.zzbk(r5)
            long r15 = r2.zzal(r4)
            r8 = r6
            long r5 = r15 * r9
            com.google.android.gms.internal.ads.zzpx r11 = r0.zzapx
            com.google.android.gms.internal.ads.zzii[] r15 = r0.zzaqq
            com.google.android.gms.internal.ads.zzoc.zza(r5, r11, r15)
            goto L_0x052c
        L_0x0526:
            r8 = r6
            r5 = 0
            int r14 = r12.zza(r1, r14, r5)
        L_0x052c:
            int r5 = r0.zzajn
            int r5 = r5 + r14
            r0.zzajn = r5
            int r5 = r0.zzajm
            int r5 = r5 - r14
            r0.zzajm = r5
            r6 = r8
            r5 = 3
            r8 = 0
            r11 = 0
            goto L_0x048b
        L_0x053c:
            int r5 = r0.zzajn
            int r6 = r0.zzaqn
            if (r5 >= r6) goto L_0x054e
            int r6 = r6 - r5
            r5 = 0
            int r6 = r12.zza(r1, r6, r5)
            int r5 = r0.zzajn
            int r5 = r5 + r6
            r0.zzajn = r5
            goto L_0x053c
        L_0x054e:
            long r5 = r2.zzal(r4)
            long r5 = r5 * r9
            com.google.android.gms.internal.ads.zzqb r7 = r0.zzapz
            if (r7 != 0) goto L_0x05fe
            boolean r7 = r2.zzate
            if (r7 == 0) goto L_0x055f
            r11 = 1073741824(0x40000000, float:2.0)
            goto L_0x0560
        L_0x055f:
            r11 = 0
        L_0x0560:
            boolean[] r7 = r2.zzatd
            boolean r4 = r7[r4]
            r15 = r11 | r4
            boolean r4 = r2.zzate
            if (r4 == 0) goto L_0x058f
            com.google.android.gms.internal.ads.zzjt r4 = r2.zzatg
            if (r4 == 0) goto L_0x0572
            com.google.android.gms.internal.ads.zzjt r3 = r2.zzatg
            r8 = r3
            goto L_0x057b
        L_0x0572:
            com.google.android.gms.internal.ads.zzjt[] r3 = r3.zzasn
            com.google.android.gms.internal.ads.zzjf r4 = r2.zzast
            int r4 = r4.zzapo
            r3 = r3[r4]
            r8 = r3
        L_0x057b:
            com.google.android.gms.internal.ads.zzjl r3 = r0.zzaqm
            com.google.android.gms.internal.ads.zzjt r3 = r3.zzara
            if (r8 == r3) goto L_0x058a
            com.google.android.gms.internal.ads.zzij r3 = new com.google.android.gms.internal.ads.zzij
            byte[] r4 = r8.zzass
            r7 = 1
            r3.<init>(r7, r4)
            goto L_0x0591
        L_0x058a:
            com.google.android.gms.internal.ads.zzjl r3 = r0.zzaqm
            com.google.android.gms.internal.ads.zzij r3 = r3.zzaqz
            goto L_0x0591
        L_0x058f:
            r3 = 0
            r8 = 0
        L_0x0591:
            com.google.android.gms.internal.ads.zzjl r4 = r0.zzaqm
            r4.zzaqz = r3
            r4.zzara = r8
            int r4 = r0.zzaqn
            r17 = 0
            r13 = r5
            r16 = r4
            r18 = r3
            r12.zza(r13, r15, r16, r17, r18)
        L_0x05a3:
            java.util.LinkedList<com.google.android.gms.internal.ads.zzjk> r3 = r0.zzaqd
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x05c9
            java.util.LinkedList<com.google.android.gms.internal.ads.zzjk> r3 = r0.zzaqd
            java.lang.Object r3 = r3.removeFirst()
            com.google.android.gms.internal.ads.zzjk r3 = (com.google.android.gms.internal.ads.zzjk) r3
            int r4 = r0.zzaqk
            int r7 = r3.size
            int r4 = r4 - r7
            r0.zzaqk = r4
            com.google.android.gms.internal.ads.zzii r7 = r0.zzaqp
            long r8 = r3.zzaqs
            long r8 = r8 + r5
            r10 = 1
            int r11 = r3.size
            int r12 = r0.zzaqk
            r13 = 0
            r7.zza(r8, r10, r11, r12, r13)
            goto L_0x05a3
        L_0x05c9:
            com.google.android.gms.internal.ads.zzjl r3 = r0.zzaqm
            int r4 = r3.zzaqw
            r5 = 1
            int r4 = r4 + r5
            r3.zzaqw = r4
            com.google.android.gms.internal.ads.zzjl r3 = r0.zzaqm
            int r4 = r3.zzaqx
            int r4 = r4 + r5
            r3.zzaqx = r4
            com.google.android.gms.internal.ads.zzjl r3 = r0.zzaqm
            int r3 = r3.zzaqx
            int[] r2 = r2.zzasz
            com.google.android.gms.internal.ads.zzjl r4 = r0.zzaqm
            int r4 = r4.zzaqy
            r2 = r2[r4]
            if (r3 != r2) goto L_0x05f7
            com.google.android.gms.internal.ads.zzjl r2 = r0.zzaqm
            int r3 = r2.zzaqy
            int r3 = r3 + r5
            r2.zzaqy = r3
            com.google.android.gms.internal.ads.zzjl r2 = r0.zzaqm
            r3 = 0
            r2.zzaqx = r3
            r2 = 0
            r0.zzaqm = r2
            r2 = 3
            goto L_0x05f9
        L_0x05f7:
            r3 = 0
            r2 = 3
        L_0x05f9:
            r0.zzaqe = r2
        L_0x05fb:
            if (r5 == 0) goto L_0x0004
            return r3
        L_0x05fe:
            java.lang.NoSuchMethodError r1 = new java.lang.NoSuchMethodError
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzji.zza(com.google.android.gms.internal.ads.zzia, com.google.android.gms.internal.ads.zzif):int");
    }

    private final void zzei() {
        this.zzaqe = 0;
        this.zzaqh = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:160:0x03f4  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x03f8  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x03fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzt(long r55) throws com.google.android.gms.internal.ads.zzfx {
        /*
            r54 = this;
            r0 = r54
        L_0x0002:
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r1 = r0.zzaqc
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0718
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r1 = r0.zzaqc
            java.lang.Object r1 = r1.peek()
            com.google.android.gms.internal.ads.zziw r1 = (com.google.android.gms.internal.ads.zziw) r1
            long r1 = r1.zzaop
            int r3 = (r1 > r55 ? 1 : (r1 == r55 ? 0 : -1))
            if (r3 != 0) goto L_0x0718
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r1 = r0.zzaqc
            java.lang.Object r1 = r1.pop()
            com.google.android.gms.internal.ads.zziw r1 = (com.google.android.gms.internal.ads.zziw) r1
            int r2 = r1.type
            int r3 = com.google.android.gms.internal.ads.zziv.zzamb
            r4 = 0
            r5 = 12
            r6 = 4
            r7 = 8
            r9 = 1
            if (r2 != r3) goto L_0x01cc
            java.lang.String r2 = "Unexpected moov box."
            com.google.android.gms.internal.ads.zzpo.checkState(r9, r2)
            java.util.List<com.google.android.gms.internal.ads.zzix> r2 = r1.zzaoq
            com.google.android.gms.internal.ads.zzhp r2 = zzb(r2)
            int r3 = com.google.android.gms.internal.ads.zziv.zzamm
            com.google.android.gms.internal.ads.zziw r3 = r1.zzaj(r3)
            android.util.SparseArray r15 = new android.util.SparseArray
            r15.<init>()
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.util.List<com.google.android.gms.internal.ads.zzix> r12 = r3.zzaoq
            int r12 = r12.size()
            r16 = r10
            r10 = 0
        L_0x0051:
            if (r10 >= r12) goto L_0x00be
            java.util.List<com.google.android.gms.internal.ads.zzix> r11 = r3.zzaoq
            java.lang.Object r11 = r11.get(r10)
            com.google.android.gms.internal.ads.zzix r11 = (com.google.android.gms.internal.ads.zzix) r11
            int r13 = r11.type
            int r14 = com.google.android.gms.internal.ads.zziv.zzaly
            if (r13 != r14) goto L_0x0098
            com.google.android.gms.internal.ads.zzpx r11 = r11.zzaos
            r11.setPosition(r5)
            int r13 = r11.readInt()
            int r14 = r11.zzhg()
            int r14 = r14 - r9
            int r5 = r11.zzhg()
            int r8 = r11.zzhg()
            int r11 = r11.readInt()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            com.google.android.gms.internal.ads.zzjf r9 = new com.google.android.gms.internal.ads.zzjf
            r9.<init>(r14, r5, r8, r11)
            android.util.Pair r5 = android.util.Pair.create(r13, r9)
            java.lang.Object r8 = r5.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            java.lang.Object r5 = r5.second
            com.google.android.gms.internal.ads.zzjf r5 = (com.google.android.gms.internal.ads.zzjf) r5
            r15.put(r8, r5)
            goto L_0x00b8
        L_0x0098:
            int r5 = r11.type
            int r8 = com.google.android.gms.internal.ads.zziv.zzamn
            if (r5 != r8) goto L_0x00b8
            com.google.android.gms.internal.ads.zzpx r5 = r11.zzaos
            r5.setPosition(r7)
            int r8 = r5.readInt()
            int r8 = com.google.android.gms.internal.ads.zziv.zzaf(r8)
            if (r8 != 0) goto L_0x00b2
            long r8 = r5.zzhd()
            goto L_0x00b6
        L_0x00b2:
            long r8 = r5.zzhh()
        L_0x00b6:
            r16 = r8
        L_0x00b8:
            int r10 = r10 + 1
            r5 = 12
            r9 = 1
            goto L_0x0051
        L_0x00be:
            android.util.SparseArray r3 = new android.util.SparseArray
            r3.<init>()
            java.util.List<com.google.android.gms.internal.ads.zziw> r5 = r1.zzaor
            int r5 = r5.size()
            r8 = 0
        L_0x00ca:
            if (r8 >= r5) goto L_0x00fa
            java.util.List<com.google.android.gms.internal.ads.zziw> r9 = r1.zzaor
            java.lang.Object r9 = r9.get(r8)
            r10 = r9
            com.google.android.gms.internal.ads.zziw r10 = (com.google.android.gms.internal.ads.zziw) r10
            int r9 = r10.type
            int r11 = com.google.android.gms.internal.ads.zziv.zzamd
            if (r9 != r11) goto L_0x00f3
            int r9 = com.google.android.gms.internal.ads.zziv.zzamc
            com.google.android.gms.internal.ads.zzix r11 = r1.zzai(r9)
            r9 = 0
            r12 = r16
            r14 = r2
            r7 = r15
            r15 = r9
            com.google.android.gms.internal.ads.zzjs r9 = com.google.android.gms.internal.ads.zziy.zza(r10, r11, r12, r14, r15)
            if (r9 == 0) goto L_0x00f4
            int r10 = r9.id
            r3.put(r10, r9)
            goto L_0x00f4
        L_0x00f3:
            r7 = r15
        L_0x00f4:
            int r8 = r8 + 1
            r15 = r7
            r7 = 8
            goto L_0x00ca
        L_0x00fa:
            r7 = r15
            int r1 = r3.size()
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r2 = r0.zzapv
            int r2 = r2.size()
            if (r2 != 0) goto L_0x019b
            r2 = 0
        L_0x0108:
            if (r2 >= r1) goto L_0x013c
            java.lang.Object r5 = r3.valueAt(r2)
            com.google.android.gms.internal.ads.zzjs r5 = (com.google.android.gms.internal.ads.zzjs) r5
            com.google.android.gms.internal.ads.zzjl r8 = new com.google.android.gms.internal.ads.zzjl
            com.google.android.gms.internal.ads.zzib r9 = r0.zzajq
            int r10 = r5.type
            com.google.android.gms.internal.ads.zzii r9 = r9.zzb(r2, r10)
            r8.<init>(r9)
            int r9 = r5.id
            java.lang.Object r9 = r7.get(r9)
            com.google.android.gms.internal.ads.zzjf r9 = (com.google.android.gms.internal.ads.zzjf) r9
            r8.zza(r5, r9)
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r9 = r0.zzapv
            int r10 = r5.id
            r9.put(r10, r8)
            long r8 = r0.zzaan
            long r10 = r5.zzaan
            long r8 = java.lang.Math.max(r8, r10)
            r0.zzaan = r8
            int r2 = r2 + 1
            goto L_0x0108
        L_0x013c:
            int r1 = r0.flags
            r1 = r1 & r6
            if (r1 == 0) goto L_0x0163
            com.google.android.gms.internal.ads.zzii r1 = r0.zzaqp
            if (r1 != 0) goto L_0x0163
            com.google.android.gms.internal.ads.zzib r1 = r0.zzajq
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r2 = r0.zzapv
            int r2 = r2.size()
            com.google.android.gms.internal.ads.zzii r1 = r1.zzb(r2, r6)
            r0.zzaqp = r1
            com.google.android.gms.internal.ads.zzii r1 = r0.zzaqp
            java.lang.String r2 = "application/x-emsg"
            r5 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            com.google.android.gms.internal.ads.zzfs r2 = com.google.android.gms.internal.ads.zzfs.zza(r4, r2, r5)
            r1.zzf(r2)
        L_0x0163:
            int r1 = r0.flags
            r2 = 8
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0194
            com.google.android.gms.internal.ads.zzii[] r1 = r0.zzaqq
            if (r1 != 0) goto L_0x0194
            com.google.android.gms.internal.ads.zzib r1 = r0.zzajq
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r2 = r0.zzapv
            int r2 = r2.size()
            r3 = 1
            int r2 = r2 + r3
            r3 = 3
            com.google.android.gms.internal.ads.zzii r1 = r1.zzb(r2, r3)
            r2 = 0
            java.lang.String r3 = "application/cea-608"
            r4 = 0
            r5 = -1
            r6 = 0
            r7 = 0
            r8 = 0
            com.google.android.gms.internal.ads.zzfs r2 = com.google.android.gms.internal.ads.zzfs.zza(r2, r3, r4, r5, r6, r7, r8)
            r1.zzf(r2)
            r2 = 1
            com.google.android.gms.internal.ads.zzii[] r2 = new com.google.android.gms.internal.ads.zzii[r2]
            r3 = 0
            r2[r3] = r1
            r0.zzaqq = r2
        L_0x0194:
            com.google.android.gms.internal.ads.zzib r1 = r0.zzajq
            r1.zzdy()
            goto L_0x0002
        L_0x019b:
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r2 = r0.zzapv
            int r2 = r2.size()
            if (r2 != r1) goto L_0x01a6
            r19 = 1
            goto L_0x01a8
        L_0x01a6:
            r19 = 0
        L_0x01a8:
            com.google.android.gms.internal.ads.zzpo.checkState(r19)
            r2 = 0
        L_0x01ac:
            if (r2 >= r1) goto L_0x0002
            java.lang.Object r4 = r3.valueAt(r2)
            com.google.android.gms.internal.ads.zzjs r4 = (com.google.android.gms.internal.ads.zzjs) r4
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r5 = r0.zzapv
            int r6 = r4.id
            java.lang.Object r5 = r5.get(r6)
            com.google.android.gms.internal.ads.zzjl r5 = (com.google.android.gms.internal.ads.zzjl) r5
            int r6 = r4.id
            java.lang.Object r6 = r7.get(r6)
            com.google.android.gms.internal.ads.zzjf r6 = (com.google.android.gms.internal.ads.zzjf) r6
            r5.zza(r4, r6)
            int r2 = r2 + 1
            goto L_0x01ac
        L_0x01cc:
            int r2 = r1.type
            int r3 = com.google.android.gms.internal.ads.zziv.zzamk
            if (r2 != r3) goto L_0x0701
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r2 = r0.zzapv
            int r3 = r0.flags
            byte[] r5 = r0.zzaqb
            java.util.List<com.google.android.gms.internal.ads.zziw> r7 = r1.zzaor
            int r7 = r7.size()
            r8 = 0
        L_0x01df:
            if (r8 >= r7) goto L_0x06d1
            java.util.List<com.google.android.gms.internal.ads.zziw> r9 = r1.zzaor
            java.lang.Object r9 = r9.get(r8)
            com.google.android.gms.internal.ads.zziw r9 = (com.google.android.gms.internal.ads.zziw) r9
            int r10 = r9.type
            int r11 = com.google.android.gms.internal.ads.zziv.zzaml
            if (r10 != r11) goto L_0x06af
            int r10 = com.google.android.gms.internal.ads.zziv.zzalx
            com.google.android.gms.internal.ads.zzix r10 = r9.zzai(r10)
            com.google.android.gms.internal.ads.zzpx r10 = r10.zzaos
            r11 = 8
            r10.setPosition(r11)
            int r11 = r10.readInt()
            int r11 = com.google.android.gms.internal.ads.zziv.zzag(r11)
            int r12 = r10.readInt()
            r13 = r3 & 16
            if (r13 != 0) goto L_0x020d
            goto L_0x020e
        L_0x020d:
            r12 = 0
        L_0x020e:
            java.lang.Object r12 = r2.get(r12)
            com.google.android.gms.internal.ads.zzjl r12 = (com.google.android.gms.internal.ads.zzjl) r12
            if (r12 != 0) goto L_0x0217
            goto L_0x0263
        L_0x0217:
            r13 = r11 & 1
            if (r13 == 0) goto L_0x0227
            long r13 = r10.zzhh()
            com.google.android.gms.internal.ads.zzju r15 = r12.zzaqt
            r15.zzasv = r13
            com.google.android.gms.internal.ads.zzju r15 = r12.zzaqt
            r15.zzasw = r13
        L_0x0227:
            com.google.android.gms.internal.ads.zzjf r13 = r12.zzaqv
            r14 = r11 & 2
            if (r14 == 0) goto L_0x0234
            int r14 = r10.zzhg()
            r15 = 1
            int r14 = r14 - r15
            goto L_0x0236
        L_0x0234:
            int r14 = r13.zzapo
        L_0x0236:
            r15 = r11 & 8
            if (r15 == 0) goto L_0x023f
            int r15 = r10.zzhg()
            goto L_0x0241
        L_0x023f:
            int r15 = r13.duration
        L_0x0241:
            r16 = r11 & 16
            if (r16 == 0) goto L_0x024c
            int r16 = r10.zzhg()
            r4 = r16
            goto L_0x024e
        L_0x024c:
            int r4 = r13.size
        L_0x024e:
            r11 = r11 & 32
            if (r11 == 0) goto L_0x0257
            int r10 = r10.zzhg()
            goto L_0x0259
        L_0x0257:
            int r10 = r13.flags
        L_0x0259:
            com.google.android.gms.internal.ads.zzju r11 = r12.zzaqt
            com.google.android.gms.internal.ads.zzjf r13 = new com.google.android.gms.internal.ads.zzjf
            r13.<init>(r14, r15, r4, r10)
            r11.zzast = r13
            r4 = r12
        L_0x0263:
            if (r4 == 0) goto L_0x069e
            com.google.android.gms.internal.ads.zzju r10 = r4.zzaqt
            long r11 = r10.zzatk
            r4.reset()
            int r13 = com.google.android.gms.internal.ads.zziv.zzalw
            com.google.android.gms.internal.ads.zzix r13 = r9.zzai(r13)
            if (r13 == 0) goto L_0x0299
            r13 = r3 & 2
            if (r13 != 0) goto L_0x0299
            int r11 = com.google.android.gms.internal.ads.zziv.zzalw
            com.google.android.gms.internal.ads.zzix r11 = r9.zzai(r11)
            com.google.android.gms.internal.ads.zzpx r11 = r11.zzaos
            r12 = 8
            r11.setPosition(r12)
            int r12 = r11.readInt()
            int r12 = com.google.android.gms.internal.ads.zziv.zzaf(r12)
            r13 = 1
            if (r12 != r13) goto L_0x0295
            long r11 = r11.zzhh()
            goto L_0x0299
        L_0x0295:
            long r11 = r11.zzhd()
        L_0x0299:
            java.util.List<com.google.android.gms.internal.ads.zzix> r13 = r9.zzaoq
            int r14 = r13.size()
            r20 = r2
            r2 = 0
            r6 = 0
            r15 = 0
        L_0x02a4:
            if (r15 >= r14) goto L_0x02d2
            java.lang.Object r21 = r13.get(r15)
            r22 = r7
            r7 = r21
            com.google.android.gms.internal.ads.zzix r7 = (com.google.android.gms.internal.ads.zzix) r7
            r23 = r11
            int r11 = r7.type
            int r12 = com.google.android.gms.internal.ads.zziv.zzalz
            if (r11 != r12) goto L_0x02c9
            com.google.android.gms.internal.ads.zzpx r7 = r7.zzaos
            r11 = 12
            r7.setPosition(r11)
            int r7 = r7.zzhg()
            if (r7 <= 0) goto L_0x02cb
            int r2 = r2 + r7
            int r6 = r6 + 1
            goto L_0x02cb
        L_0x02c9:
            r11 = 12
        L_0x02cb:
            int r15 = r15 + 1
            r7 = r22
            r11 = r23
            goto L_0x02a4
        L_0x02d2:
            r22 = r7
            r23 = r11
            r7 = 0
            r11 = 12
            r4.zzaqy = r7
            r4.zzaqx = r7
            r4.zzaqw = r7
            com.google.android.gms.internal.ads.zzju r7 = r4.zzaqt
            r7.zzasx = r6
            r7.zzapk = r2
            int[] r12 = r7.zzasz
            if (r12 == 0) goto L_0x02ee
            int[] r12 = r7.zzasz
            int r12 = r12.length
            if (r12 >= r6) goto L_0x02f6
        L_0x02ee:
            long[] r12 = new long[r6]
            r7.zzasy = r12
            int[] r6 = new int[r6]
            r7.zzasz = r6
        L_0x02f6:
            int[] r6 = r7.zzata
            if (r6 == 0) goto L_0x02ff
            int[] r6 = r7.zzata
            int r6 = r6.length
            if (r6 >= r2) goto L_0x0317
        L_0x02ff:
            int r2 = r2 * 125
            int r2 = r2 / 100
            int[] r6 = new int[r2]
            r7.zzata = r6
            int[] r6 = new int[r2]
            r7.zzatb = r6
            long[] r6 = new long[r2]
            r7.zzatc = r6
            boolean[] r6 = new boolean[r2]
            r7.zzatd = r6
            boolean[] r2 = new boolean[r2]
            r7.zzatf = r2
        L_0x0317:
            r2 = 0
            r6 = 0
            r7 = 0
        L_0x031a:
            r25 = 0
            if (r2 >= r14) goto L_0x04b9
            java.lang.Object r18 = r13.get(r2)
            r11 = r18
            com.google.android.gms.internal.ads.zzix r11 = (com.google.android.gms.internal.ads.zzix) r11
            int r15 = r11.type
            int r12 = com.google.android.gms.internal.ads.zziv.zzalz
            if (r15 != r12) goto L_0x048a
            int r12 = r6 + 1
            com.google.android.gms.internal.ads.zzpx r11 = r11.zzaos
            r15 = 8
            r11.setPosition(r15)
            int r15 = r11.readInt()
            int r15 = com.google.android.gms.internal.ads.zziv.zzag(r15)
            r28 = r12
            com.google.android.gms.internal.ads.zzjs r12 = r4.zzaqu
            r29 = r13
            com.google.android.gms.internal.ads.zzju r13 = r4.zzaqt
            r30 = r14
            com.google.android.gms.internal.ads.zzjf r14 = r13.zzast
            int[] r0 = r13.zzasz
            int r31 = r11.zzhg()
            r0[r6] = r31
            long[] r0 = r13.zzasy
            r32 = r4
            r31 = r5
            long r4 = r13.zzasv
            r0[r6] = r4
            r0 = r15 & 1
            if (r0 == 0) goto L_0x0372
            long[] r0 = r13.zzasy
            r4 = r0[r6]
            r33 = r1
            int r1 = r11.readInt()
            r34 = r8
            r35 = r9
            long r8 = (long) r1
            long r4 = r4 + r8
            r0[r6] = r4
            goto L_0x0378
        L_0x0372:
            r33 = r1
            r34 = r8
            r35 = r9
        L_0x0378:
            r0 = r15 & 4
            if (r0 == 0) goto L_0x037e
            r0 = 1
            goto L_0x037f
        L_0x037e:
            r0 = 0
        L_0x037f:
            int r1 = r14.flags
            if (r0 == 0) goto L_0x0387
            int r1 = r11.zzhg()
        L_0x0387:
            r4 = r15 & 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x038d
            r4 = 1
            goto L_0x038e
        L_0x038d:
            r4 = 0
        L_0x038e:
            r5 = r15 & 512(0x200, float:7.175E-43)
            if (r5 == 0) goto L_0x0394
            r5 = 1
            goto L_0x0395
        L_0x0394:
            r5 = 0
        L_0x0395:
            r8 = r15 & 1024(0x400, float:1.435E-42)
            if (r8 == 0) goto L_0x039b
            r8 = 1
            goto L_0x039c
        L_0x039b:
            r8 = 0
        L_0x039c:
            r9 = r15 & 2048(0x800, float:2.87E-42)
            if (r9 == 0) goto L_0x03a2
            r9 = 1
            goto L_0x03a3
        L_0x03a2:
            r9 = 0
        L_0x03a3:
            long[] r15 = r12.zzaso
            if (r15 == 0) goto L_0x03c8
            long[] r15 = r12.zzaso
            int r15 = r15.length
            r36 = r1
            r1 = 1
            if (r15 != r1) goto L_0x03ca
            long[] r1 = r12.zzaso
            r15 = 0
            r37 = r1[r15]
            int r1 = (r37 > r25 ? 1 : (r37 == r25 ? 0 : -1))
            if (r1 != 0) goto L_0x03ca
            long[] r1 = r12.zzasp
            r37 = r1[r15]
            r39 = 1000(0x3e8, double:4.94E-321)
            r15 = r2
            long r1 = r12.zzcr
            r41 = r1
            long r25 = com.google.android.gms.internal.ads.zzqe.zza(r37, r39, r41)
            goto L_0x03cb
        L_0x03c8:
            r36 = r1
        L_0x03ca:
            r15 = r2
        L_0x03cb:
            int[] r1 = r13.zzata
            int[] r2 = r13.zzatb
            r37 = r10
            long[] r10 = r13.zzatc
            r38 = r15
            boolean[] r15 = r13.zzatd
            r39 = r15
            int r15 = r12.type
            r40 = r1
            r1 = 2
            if (r15 != r1) goto L_0x03e6
            r1 = r3 & 1
            if (r1 == 0) goto L_0x03e6
            r1 = 1
            goto L_0x03e7
        L_0x03e6:
            r1 = 0
        L_0x03e7:
            int[] r15 = r13.zzasz
            r15 = r15[r6]
            int r15 = r15 + r7
            r47 = r1
            r27 = r2
            long r1 = r12.zzcr
            if (r6 <= 0) goto L_0x03f8
            r12 = r7
            long r6 = r13.zzatk
            goto L_0x03fb
        L_0x03f8:
            r12 = r7
            r6 = r23
        L_0x03fb:
            if (r12 >= r15) goto L_0x0482
            if (r4 == 0) goto L_0x0408
            int r41 = r11.zzhg()
            r48 = r3
            r3 = r41
            goto L_0x040c
        L_0x0408:
            r48 = r3
            int r3 = r14.duration
        L_0x040c:
            if (r5 == 0) goto L_0x0417
            int r41 = r11.zzhg()
            r49 = r4
            r4 = r41
            goto L_0x041b
        L_0x0417:
            r49 = r4
            int r4 = r14.size
        L_0x041b:
            if (r12 != 0) goto L_0x0424
            if (r0 == 0) goto L_0x0424
            r50 = r0
            r0 = r36
            goto L_0x0433
        L_0x0424:
            if (r8 == 0) goto L_0x042f
            int r41 = r11.readInt()
            r50 = r0
            r0 = r41
            goto L_0x0433
        L_0x042f:
            r50 = r0
            int r0 = r14.flags
        L_0x0433:
            if (r9 == 0) goto L_0x0447
            r51 = r5
            int r5 = r11.readInt()
            int r5 = r5 * 1000
            r52 = r8
            r53 = r9
            long r8 = (long) r5
            long r8 = r8 / r1
            int r5 = (int) r8
            r27[r12] = r5
            goto L_0x0450
        L_0x0447:
            r51 = r5
            r52 = r8
            r53 = r9
            r5 = 0
            r27[r12] = r5
        L_0x0450:
            r43 = 1000(0x3e8, double:4.94E-321)
            r41 = r6
            r45 = r1
            long r8 = com.google.android.gms.internal.ads.zzqe.zza(r41, r43, r45)
            long r8 = r8 - r25
            r10[r12] = r8
            r40[r12] = r4
            r4 = 16
            int r0 = r0 >> r4
            r4 = 1
            r0 = r0 & r4
            if (r0 != 0) goto L_0x046d
            if (r47 == 0) goto L_0x046b
            if (r12 != 0) goto L_0x046d
        L_0x046b:
            r0 = 1
            goto L_0x046e
        L_0x046d:
            r0 = 0
        L_0x046e:
            r39[r12] = r0
            long r3 = (long) r3
            long r6 = r6 + r3
            int r12 = r12 + 1
            r3 = r48
            r4 = r49
            r0 = r50
            r5 = r51
            r8 = r52
            r9 = r53
            goto L_0x03fb
        L_0x0482:
            r48 = r3
            r13.zzatk = r6
            r7 = r15
            r6 = r28
            goto L_0x049f
        L_0x048a:
            r33 = r1
            r38 = r2
            r48 = r3
            r32 = r4
            r31 = r5
            r12 = r7
            r34 = r8
            r35 = r9
            r37 = r10
            r29 = r13
            r30 = r14
        L_0x049f:
            int r2 = r38 + 1
            r13 = r29
            r14 = r30
            r5 = r31
            r4 = r32
            r1 = r33
            r8 = r34
            r9 = r35
            r10 = r37
            r3 = r48
            r0 = r54
            r11 = 12
            goto L_0x031a
        L_0x04b9:
            r33 = r1
            r48 = r3
            r32 = r4
            r31 = r5
            r34 = r8
            r35 = r9
            r37 = r10
            int r0 = com.google.android.gms.internal.ads.zziv.zzanc
            com.google.android.gms.internal.ads.zzix r0 = r9.zzai(r0)
            if (r0 == 0) goto L_0x054e
            r4 = r32
            com.google.android.gms.internal.ads.zzjs r1 = r4.zzaqu
            com.google.android.gms.internal.ads.zzjt[] r1 = r1.zzasn
            r2 = r37
            com.google.android.gms.internal.ads.zzjf r3 = r2.zzast
            int r3 = r3.zzapo
            r1 = r1[r3]
            com.google.android.gms.internal.ads.zzpx r0 = r0.zzaos
            int r1 = r1.zzasr
            r3 = 8
            r0.setPosition(r3)
            int r4 = r0.readInt()
            int r4 = com.google.android.gms.internal.ads.zziv.zzag(r4)
            r5 = 1
            r4 = r4 & r5
            if (r4 != r5) goto L_0x04f5
            r0.zzbl(r3)
        L_0x04f5:
            int r3 = r0.readUnsignedByte()
            int r4 = r0.zzhg()
            int r5 = r2.zzapk
            if (r4 != r5) goto L_0x052b
            if (r3 != 0) goto L_0x0518
            boolean[] r3 = r2.zzatf
            r5 = 0
            r6 = 0
        L_0x0507:
            if (r5 >= r4) goto L_0x0527
            int r7 = r0.readUnsignedByte()
            int r6 = r6 + r7
            if (r7 <= r1) goto L_0x0512
            r7 = 1
            goto L_0x0513
        L_0x0512:
            r7 = 0
        L_0x0513:
            r3[r5] = r7
            int r5 = r5 + 1
            goto L_0x0507
        L_0x0518:
            if (r3 <= r1) goto L_0x051c
            r0 = 1
            goto L_0x051d
        L_0x051c:
            r0 = 0
        L_0x051d:
            int r3 = r3 * r4
            r1 = 0
            int r6 = r3 + 0
            boolean[] r3 = r2.zzatf
            java.util.Arrays.fill(r3, r1, r4, r0)
        L_0x0527:
            r2.zzak(r6)
            goto L_0x0550
        L_0x052b:
            com.google.android.gms.internal.ads.zzfx r0 = new com.google.android.gms.internal.ads.zzfx
            int r1 = r2.zzapk
            r2 = 41
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Length mismatch: "
            r3.append(r2)
            r3.append(r4)
            java.lang.String r2 = ", "
            r3.append(r2)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.<init>(r1)
            throw r0
        L_0x054e:
            r2 = r37
        L_0x0550:
            int r0 = com.google.android.gms.internal.ads.zziv.zzand
            com.google.android.gms.internal.ads.zzix r0 = r9.zzai(r0)
            if (r0 == 0) goto L_0x05a2
            com.google.android.gms.internal.ads.zzpx r0 = r0.zzaos
            r1 = 8
            r0.setPosition(r1)
            int r3 = r0.readInt()
            int r4 = com.google.android.gms.internal.ads.zziv.zzag(r3)
            r5 = 1
            r4 = r4 & r5
            if (r4 != r5) goto L_0x056e
            r0.zzbl(r1)
        L_0x056e:
            int r1 = r0.zzhg()
            if (r1 != r5) goto L_0x0589
            int r1 = com.google.android.gms.internal.ads.zziv.zzaf(r3)
            long r3 = r2.zzasw
            if (r1 != 0) goto L_0x0581
            long r0 = r0.zzhd()
            goto L_0x0585
        L_0x0581:
            long r0 = r0.zzhh()
        L_0x0585:
            long r3 = r3 + r0
            r2.zzasw = r3
            goto L_0x05a2
        L_0x0589:
            com.google.android.gms.internal.ads.zzfx r0 = new com.google.android.gms.internal.ads.zzfx
            r2 = 40
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Unexpected saio entry count: "
            r3.append(r2)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.<init>(r1)
            throw r0
        L_0x05a2:
            int r0 = com.google.android.gms.internal.ads.zziv.zzanh
            com.google.android.gms.internal.ads.zzix r0 = r9.zzai(r0)
            if (r0 == 0) goto L_0x05b0
            com.google.android.gms.internal.ads.zzpx r0 = r0.zzaos
            r1 = 0
            zza(r0, r1, r2)
        L_0x05b0:
            int r0 = com.google.android.gms.internal.ads.zziv.zzane
            com.google.android.gms.internal.ads.zzix r0 = r9.zzai(r0)
            int r1 = com.google.android.gms.internal.ads.zziv.zzanf
            com.google.android.gms.internal.ads.zzix r1 = r9.zzai(r1)
            if (r0 == 0) goto L_0x0658
            if (r1 == 0) goto L_0x0658
            com.google.android.gms.internal.ads.zzpx r0 = r0.zzaos
            com.google.android.gms.internal.ads.zzpx r1 = r1.zzaos
            r3 = 8
            r0.setPosition(r3)
            int r3 = r0.readInt()
            int r4 = r0.readInt()
            int r5 = zzaps
            if (r4 != r5) goto L_0x0655
            int r3 = com.google.android.gms.internal.ads.zziv.zzaf(r3)
            r4 = 1
            if (r3 != r4) goto L_0x05e0
            r3 = 4
            r0.zzbl(r3)
        L_0x05e0:
            int r0 = r0.readInt()
            if (r0 != r4) goto L_0x064d
            r0 = 8
            r1.setPosition(r0)
            int r0 = r1.readInt()
            int r3 = r1.readInt()
            int r5 = zzaps
            if (r3 != r5) goto L_0x064b
            int r0 = com.google.android.gms.internal.ads.zziv.zzaf(r0)
            if (r0 != r4) goto L_0x0610
            long r3 = r1.zzhd()
            int r0 = (r3 > r25 ? 1 : (r3 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x0608
            r0 = 4
            r3 = 2
            goto L_0x0619
        L_0x0608:
            com.google.android.gms.internal.ads.zzfx r0 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r1 = "Variable length decription in sgpd found (unsupported)"
            r0.<init>(r1)
            throw r0
        L_0x0610:
            r3 = 2
            if (r0 < r3) goto L_0x0618
            r0 = 4
            r1.zzbl(r0)
            goto L_0x0619
        L_0x0618:
            r0 = 4
        L_0x0619:
            long r4 = r1.zzhd()
            r6 = 1
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0643
            r1.zzbl(r3)
            int r3 = r1.readUnsignedByte()
            r4 = 1
            if (r3 != r4) goto L_0x065a
            int r3 = r1.readUnsignedByte()
            r5 = 16
            byte[] r6 = new byte[r5]
            r7 = 0
            r1.zze(r6, r7, r5)
            r2.zzate = r4
            com.google.android.gms.internal.ads.zzjt r1 = new com.google.android.gms.internal.ads.zzjt
            r1.<init>(r4, r3, r6)
            r2.zzatg = r1
            goto L_0x065a
        L_0x0643:
            com.google.android.gms.internal.ads.zzfx r0 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r1 = "Entry count in sgpd != 1 (unsupported)."
            r0.<init>(r1)
            throw r0
        L_0x064b:
            r0 = 4
            goto L_0x065a
        L_0x064d:
            com.google.android.gms.internal.ads.zzfx r0 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r1 = "Entry count in sbgp != 1 (unsupported)."
            r0.<init>(r1)
            throw r0
        L_0x0655:
            r0 = 4
            r4 = 1
            goto L_0x065a
        L_0x0658:
            r0 = 4
            r4 = 1
        L_0x065a:
            java.util.List<com.google.android.gms.internal.ads.zzix> r1 = r9.zzaoq
            int r1 = r1.size()
            r3 = 0
        L_0x0661:
            if (r3 >= r1) goto L_0x0698
            java.util.List<com.google.android.gms.internal.ads.zzix> r5 = r9.zzaoq
            java.lang.Object r5 = r5.get(r3)
            com.google.android.gms.internal.ads.zzix r5 = (com.google.android.gms.internal.ads.zzix) r5
            int r6 = r5.type
            int r7 = com.google.android.gms.internal.ads.zziv.zzang
            if (r6 != r7) goto L_0x068c
            com.google.android.gms.internal.ads.zzpx r5 = r5.zzaos
            r6 = 8
            r5.setPosition(r6)
            r7 = r31
            r8 = 0
            r10 = 16
            r5.zze(r7, r8, r10)
            byte[] r11 = zzapt
            boolean r11 = java.util.Arrays.equals(r7, r11)
            if (r11 == 0) goto L_0x0693
            zza(r5, r10, r2)
            goto L_0x0693
        L_0x068c:
            r7 = r31
            r6 = 8
            r8 = 0
            r10 = 16
        L_0x0693:
            int r3 = r3 + 1
            r31 = r7
            goto L_0x0661
        L_0x0698:
            r7 = r31
            r6 = 8
            r8 = 0
            goto L_0x06bf
        L_0x069e:
            r33 = r1
            r20 = r2
            r48 = r3
            r22 = r7
            r34 = r8
            r0 = 4
            r4 = 1
            r6 = 8
            r8 = 0
            r7 = r5
            goto L_0x06bf
        L_0x06af:
            r33 = r1
            r20 = r2
            r48 = r3
            r22 = r7
            r34 = r8
            r0 = 4
            r4 = 1
            r6 = 8
            r8 = 0
            r7 = r5
        L_0x06bf:
            int r1 = r34 + 1
            r8 = r1
            r5 = r7
            r2 = r20
            r7 = r22
            r1 = r33
            r3 = r48
            r0 = r54
            r4 = 0
            r6 = 4
            goto L_0x01df
        L_0x06d1:
            r8 = 0
            java.util.List<com.google.android.gms.internal.ads.zzix> r0 = r1.zzaoq
            com.google.android.gms.internal.ads.zzhp r0 = zzb(r0)
            if (r0 == 0) goto L_0x06fc
            r2 = r54
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r1 = r2.zzapv
            int r1 = r1.size()
        L_0x06e2:
            if (r8 >= r1) goto L_0x06fe
            android.util.SparseArray<com.google.android.gms.internal.ads.zzjl> r3 = r2.zzapv
            java.lang.Object r3 = r3.valueAt(r8)
            com.google.android.gms.internal.ads.zzjl r3 = (com.google.android.gms.internal.ads.zzjl) r3
            com.google.android.gms.internal.ads.zzii r4 = r3.zzakw
            com.google.android.gms.internal.ads.zzjs r3 = r3.zzaqu
            com.google.android.gms.internal.ads.zzfs r3 = r3.zzaad
            com.google.android.gms.internal.ads.zzfs r3 = r3.zza(r0)
            r4.zzf(r3)
            int r8 = r8 + 1
            goto L_0x06e2
        L_0x06fc:
            r2 = r54
        L_0x06fe:
            r0 = r2
            goto L_0x0002
        L_0x0701:
            r2 = r0
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r0 = r2.zzaqc
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0715
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r0 = r2.zzaqc
            java.lang.Object r0 = r0.peek()
            com.google.android.gms.internal.ads.zziw r0 = (com.google.android.gms.internal.ads.zziw) r0
            r0.zza(r1)
        L_0x0715:
            r0 = r2
            goto L_0x0002
        L_0x0718:
            r2 = r0
            r54.zzei()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzji.zzt(long):void");
    }

    private static void zza(zzpx zzpx, int i, zzju zzju) throws zzfx {
        zzpx.setPosition(i + 8);
        int zzag = zziv.zzag(zzpx.readInt());
        if ((zzag & 1) == 0) {
            boolean z = (zzag & 2) != 0;
            int zzhg = zzpx.zzhg();
            if (zzhg == zzju.zzapk) {
                Arrays.fill(zzju.zzatf, 0, zzhg, z);
                zzju.zzak(zzpx.zzhb());
                zzpx.zze(zzju.zzati.data, 0, zzju.zzath);
                zzju.zzati.setPosition(0);
                zzju.zzatj = false;
                return;
            }
            int i2 = zzju.zzapk;
            StringBuilder sb = new StringBuilder(41);
            sb.append("Length mismatch: ");
            sb.append(zzhg);
            sb.append(", ");
            sb.append(i2);
            throw new zzfx(sb.toString());
        }
        throw new zzfx("Overriding TrackEncryptionBox parameters is unsupported.");
    }

    private static zzhp zzb(List<zzix> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            zzix zzix = (zzix) list.get(i);
            if (zzix.type == zziv.zzamu) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] bArr = zzix.zzaos.data;
                UUID zze = zzjq.zze(bArr);
                if (zze == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new zza(zze, MimeTypes.VIDEO_MP4, bArr));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new zzhp((List<zza>) arrayList);
    }
}
