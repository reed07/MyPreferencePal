package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.ads.zzki.zza;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.util.ArrayList;
import java.util.List;

final class zziy {
    private static final int zzaob = zzqe.zzam("meta");
    private static final int zzaot = zzqe.zzam("vide");
    private static final int zzaou = zzqe.zzam("soun");
    private static final int zzaov = zzqe.zzam("text");
    private static final int zzaow = zzqe.zzam("sbtl");
    private static final int zzaox = zzqe.zzam("subt");
    private static final int zzaoy = zzqe.zzam("clcp");
    private static final int zzaoz = zzqe.zzam(C.CENC_TYPE_cenc);

    /* JADX WARNING: Removed duplicated region for block: B:187:0x03cd  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x03d7  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x03dd  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x03e0  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x0436  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x0589  */
    /* JADX WARNING: Removed duplicated region for block: B:393:0x0858 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0859  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzjs zza(com.google.android.gms.internal.ads.zziw r47, com.google.android.gms.internal.ads.zzix r48, long r49, com.google.android.gms.internal.ads.zzhp r51, boolean r52) throws com.google.android.gms.internal.ads.zzfx {
        /*
            r0 = r47
            r15 = r51
            int r1 = com.google.android.gms.internal.ads.zziv.zzame
            com.google.android.gms.internal.ads.zziw r1 = r0.zzaj(r1)
            int r2 = com.google.android.gms.internal.ads.zziv.zzams
            com.google.android.gms.internal.ads.zzix r2 = r1.zzai(r2)
            com.google.android.gms.internal.ads.zzpx r2 = r2.zzaos
            r14 = 16
            r2.setPosition(r14)
            int r2 = r2.readInt()
            int r3 = zzaou
            r4 = 4
            r11 = -1
            if (r2 != r3) goto L_0x0023
            r10 = 1
            goto L_0x0043
        L_0x0023:
            int r3 = zzaot
            if (r2 != r3) goto L_0x0029
            r10 = 2
            goto L_0x0043
        L_0x0029:
            int r3 = zzaov
            if (r2 == r3) goto L_0x0042
            int r3 = zzaow
            if (r2 == r3) goto L_0x0042
            int r3 = zzaox
            if (r2 == r3) goto L_0x0042
            int r3 = zzaoy
            if (r2 != r3) goto L_0x003a
            goto L_0x0042
        L_0x003a:
            int r3 = zzaob
            if (r2 != r3) goto L_0x0040
            r10 = 4
            goto L_0x0043
        L_0x0040:
            r10 = -1
            goto L_0x0043
        L_0x0042:
            r10 = 3
        L_0x0043:
            r8 = 0
            if (r10 != r11) goto L_0x0047
            return r8
        L_0x0047:
            int r2 = com.google.android.gms.internal.ads.zziv.zzamo
            com.google.android.gms.internal.ads.zzix r2 = r0.zzai(r2)
            com.google.android.gms.internal.ads.zzpx r2 = r2.zzaos
            r7 = 8
            r2.setPosition(r7)
            int r3 = r2.readInt()
            int r3 = com.google.android.gms.internal.ads.zziv.zzaf(r3)
            if (r3 != 0) goto L_0x0061
            r5 = 8
            goto L_0x0063
        L_0x0061:
            r5 = 16
        L_0x0063:
            r2.zzbl(r5)
            int r5 = r2.readInt()
            r2.zzbl(r4)
            int r6 = r2.getPosition()
            if (r3 != 0) goto L_0x0075
            r12 = 4
            goto L_0x0077
        L_0x0075:
            r12 = 8
        L_0x0077:
            r9 = 0
        L_0x0078:
            if (r9 >= r12) goto L_0x0088
            byte[] r8 = r2.data
            int r20 = r6 + r9
            byte r8 = r8[r20]
            if (r8 == r11) goto L_0x0084
            r6 = 0
            goto L_0x0089
        L_0x0084:
            int r9 = r9 + 1
            r8 = 0
            goto L_0x0078
        L_0x0088:
            r6 = 1
        L_0x0089:
            r20 = 0
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r6 == 0) goto L_0x0097
            r2.zzbl(r12)
            r11 = r8
            goto L_0x00aa
        L_0x0097:
            if (r3 != 0) goto L_0x009e
            long r22 = r2.zzhd()
            goto L_0x00a2
        L_0x009e:
            long r22 = r2.zzhh()
        L_0x00a2:
            int r3 = (r22 > r20 ? 1 : (r22 == r20 ? 0 : -1))
            if (r3 != 0) goto L_0x00a8
            r11 = r8
            goto L_0x00aa
        L_0x00a8:
            r11 = r22
        L_0x00aa:
            r2.zzbl(r14)
            int r3 = r2.readInt()
            int r6 = r2.readInt()
            r2.zzbl(r4)
            int r4 = r2.readInt()
            int r2 = r2.readInt()
            r14 = 65536(0x10000, float:9.18355E-41)
            r13 = -65536(0xffffffffffff0000, float:NaN)
            if (r3 != 0) goto L_0x00cf
            if (r6 != r14) goto L_0x00cf
            if (r4 != r13) goto L_0x00cf
            if (r2 != 0) goto L_0x00cf
            r2 = 90
            goto L_0x00e6
        L_0x00cf:
            if (r3 != 0) goto L_0x00da
            if (r6 != r13) goto L_0x00da
            if (r4 != r14) goto L_0x00da
            if (r2 != 0) goto L_0x00da
            r2 = 270(0x10e, float:3.78E-43)
            goto L_0x00e6
        L_0x00da:
            if (r3 != r13) goto L_0x00e5
            if (r6 != 0) goto L_0x00e5
            if (r4 != 0) goto L_0x00e5
            if (r2 != r13) goto L_0x00e5
            r2 = 180(0xb4, float:2.52E-43)
            goto L_0x00e6
        L_0x00e5:
            r2 = 0
        L_0x00e6:
            com.google.android.gms.internal.ads.zzje r14 = new com.google.android.gms.internal.ads.zzje
            r14.<init>(r5, r11, r2)
            int r2 = (r49 > r8 ? 1 : (r49 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x00f8
            long r2 = r14.zzcs
            r26 = r2
            r2 = r48
            goto L_0x00fc
        L_0x00f8:
            r2 = r48
            r26 = r49
        L_0x00fc:
            com.google.android.gms.internal.ads.zzpx r2 = r2.zzaos
            r2.setPosition(r7)
            int r3 = r2.readInt()
            int r3 = com.google.android.gms.internal.ads.zziv.zzaf(r3)
            if (r3 != 0) goto L_0x010e
            r3 = 8
            goto L_0x0110
        L_0x010e:
            r3 = 16
        L_0x0110:
            r2.zzbl(r3)
            long r32 = r2.zzhd()
            int r2 = (r26 > r8 ? 1 : (r26 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x011e
            r26 = r8
            goto L_0x0129
        L_0x011e:
            r28 = 1000000(0xf4240, double:4.940656E-318)
            r30 = r32
            long r2 = com.google.android.gms.internal.ads.zzqe.zza(r26, r28, r30)
            r26 = r2
        L_0x0129:
            int r2 = com.google.android.gms.internal.ads.zziv.zzamf
            com.google.android.gms.internal.ads.zziw r2 = r1.zzaj(r2)
            int r3 = com.google.android.gms.internal.ads.zziv.zzamg
            com.google.android.gms.internal.ads.zziw r2 = r2.zzaj(r3)
            int r3 = com.google.android.gms.internal.ads.zziv.zzamr
            com.google.android.gms.internal.ads.zzix r1 = r1.zzai(r3)
            com.google.android.gms.internal.ads.zzpx r1 = r1.zzaos
            r1.setPosition(r7)
            int r3 = r1.readInt()
            int r3 = com.google.android.gms.internal.ads.zziv.zzaf(r3)
            if (r3 != 0) goto L_0x014d
            r4 = 8
            goto L_0x014f
        L_0x014d:
            r4 = 16
        L_0x014f:
            r1.zzbl(r4)
            long r4 = r1.zzhd()
            if (r3 != 0) goto L_0x015a
            r3 = 4
            goto L_0x015c
        L_0x015a:
            r3 = 8
        L_0x015c:
            r1.zzbl(r3)
            int r1 = r1.readUnsignedShort()
            int r3 = r1 >> 10
            r3 = r3 & 31
            int r3 = r3 + 96
            char r3 = (char) r3
            int r6 = r1 >> 5
            r6 = r6 & 31
            int r6 = r6 + 96
            char r6 = (char) r6
            r1 = r1 & 31
            int r1 = r1 + 96
            char r1 = (char) r1
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r9 = 3
            r8.<init>(r9)
            r8.append(r3)
            r8.append(r6)
            r8.append(r1)
            java.lang.String r1 = r8.toString()
            java.lang.Long r3 = java.lang.Long.valueOf(r4)
            android.util.Pair r13 = android.util.Pair.create(r3, r1)
            int r1 = com.google.android.gms.internal.ads.zziv.zzamt
            com.google.android.gms.internal.ads.zzix r1 = r2.zzai(r1)
            com.google.android.gms.internal.ads.zzpx r12 = r1.zzaos
            int r22 = r14.id
            int r28 = r14.zzzo
            java.lang.Object r1 = r13.second
            r11 = r1
            java.lang.String r11 = (java.lang.String) r11
            r1 = 12
            r12.setPosition(r1)
            int r9 = r12.readInt()
            com.google.android.gms.internal.ads.zzjb r8 = new com.google.android.gms.internal.ads.zzjb
            r8.<init>(r9)
            r6 = 0
        L_0x01b5:
            if (r6 >= r9) goto L_0x07e1
            int r5 = r12.getPosition()
            int r4 = r12.readInt()
            if (r4 <= 0) goto L_0x01c3
            r1 = 1
            goto L_0x01c4
        L_0x01c3:
            r1 = 0
        L_0x01c4:
            java.lang.String r2 = "childAtomSize should be positive"
            com.google.android.gms.internal.ads.zzpo.checkArgument(r1, r2)
            int r1 = r12.readInt()
            int r2 = com.google.android.gms.internal.ads.zziv.zzalb
            if (r1 == r2) goto L_0x05d5
            int r2 = com.google.android.gms.internal.ads.zziv.zzalc
            if (r1 == r2) goto L_0x05d5
            int r2 = com.google.android.gms.internal.ads.zziv.zzamz
            if (r1 == r2) goto L_0x05d5
            int r2 = com.google.android.gms.internal.ads.zziv.zzanl
            if (r1 == r2) goto L_0x05d5
            int r2 = com.google.android.gms.internal.ads.zziv.zzald
            if (r1 == r2) goto L_0x05d5
            int r2 = com.google.android.gms.internal.ads.zziv.zzale
            if (r1 == r2) goto L_0x05d5
            int r2 = com.google.android.gms.internal.ads.zziv.zzalf
            if (r1 == r2) goto L_0x05d5
            int r2 = com.google.android.gms.internal.ads.zziv.zzaok
            if (r1 == r2) goto L_0x05d5
            int r2 = com.google.android.gms.internal.ads.zziv.zzaol
            if (r1 != r2) goto L_0x01f3
            goto L_0x05d5
        L_0x01f3:
            int r2 = com.google.android.gms.internal.ads.zziv.zzali
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzana
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzaln
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzalp
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzalr
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzalu
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzals
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzalt
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzany
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzanz
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzall
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzalm
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzalj
            if (r1 == r2) goto L_0x0348
            int r2 = com.google.android.gms.internal.ads.zziv.zzaoo
            if (r1 != r2) goto L_0x022d
            goto L_0x0348
        L_0x022d:
            int r2 = com.google.android.gms.internal.ads.zziv.zzanj
            if (r1 == r2) goto L_0x028f
            int r2 = com.google.android.gms.internal.ads.zziv.zzanu
            if (r1 == r2) goto L_0x028f
            int r2 = com.google.android.gms.internal.ads.zziv.zzanv
            if (r1 == r2) goto L_0x028f
            int r2 = com.google.android.gms.internal.ads.zziv.zzanw
            if (r1 == r2) goto L_0x028f
            int r2 = com.google.android.gms.internal.ads.zziv.zzanx
            if (r1 != r2) goto L_0x0242
            goto L_0x028f
        L_0x0242:
            int r2 = com.google.android.gms.internal.ads.zziv.zzaon
            if (r1 != r2) goto L_0x0271
            java.lang.String r1 = java.lang.Integer.toString(r22)
            java.lang.String r2 = "application/x-camera-motion"
            r3 = 0
            r7 = -1
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r3, r7, r15)
            r8.zzaad = r1
            r29 = r4
            r23 = r5
            r25 = r6
            r0 = r8
            r24 = r9
            r19 = r10
            r34 = r11
            r46 = r12
            r45 = r13
            r18 = r14
            r16 = -1
            r17 = 0
            r30 = 16
            r31 = 3
            goto L_0x07c4
        L_0x0271:
            r3 = 0
            r29 = r4
            r23 = r5
            r25 = r6
            r0 = r8
            r24 = r9
            r19 = r10
            r34 = r11
            r46 = r12
            r45 = r13
            r18 = r14
            r16 = -1
            r17 = 0
            r30 = 16
            r31 = 3
            goto L_0x07c4
        L_0x028f:
            r3 = 0
            r7 = -1
            int r2 = r5 + 8
            r19 = 8
            int r2 = r2 + 8
            r12.setPosition(r2)
            r29 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r2 = com.google.android.gms.internal.ads.zziv.zzanj
            if (r1 != r2) goto L_0x02ad
            java.lang.String r1 = "application/ttml+xml"
            r2 = r1
            r17 = r3
            r3 = 1
            r7 = 0
            r19 = 8
            goto L_0x02ef
        L_0x02ad:
            int r2 = com.google.android.gms.internal.ads.zziv.zzanu
            if (r1 != r2) goto L_0x02c8
            java.lang.String r1 = "application/x-quicktime-tx3g"
            int r2 = r4 + -8
            r19 = 8
            int r2 = r2 + -8
            byte[] r3 = new byte[r2]
            r7 = 0
            r12.zze(r3, r7, r2)
            java.util.List r2 = java.util.Collections.singletonList(r3)
            r17 = r2
            r3 = 1
            r2 = r1
            goto L_0x02ef
        L_0x02c8:
            r7 = 0
            r19 = 8
            int r2 = com.google.android.gms.internal.ads.zziv.zzanv
            if (r1 != r2) goto L_0x02d6
            java.lang.String r1 = "application/x-mp4-vtt"
            r2 = r1
            r3 = 1
            r17 = 0
            goto L_0x02ef
        L_0x02d6:
            int r2 = com.google.android.gms.internal.ads.zziv.zzanw
            if (r1 != r2) goto L_0x02e3
            java.lang.String r1 = "application/ttml+xml"
            r2 = r1
            r29 = r20
            r3 = 1
            r17 = 0
            goto L_0x02ef
        L_0x02e3:
            int r2 = com.google.android.gms.internal.ads.zziv.zzanx
            if (r1 != r2) goto L_0x0342
            java.lang.String r1 = "application/x-mp4-cea-608"
            r3 = 1
            r8.zzapi = r3
            r2 = r1
            r17 = 0
        L_0x02ef:
            java.lang.String r1 = java.lang.Integer.toString(r22)
            r18 = 0
            r34 = -1
            r35 = 0
            r36 = -1
            r24 = 1
            r37 = 0
            r3 = r18
            r18 = r14
            r14 = r4
            r4 = r34
            r38 = r5
            r5 = r35
            r39 = r6
            r6 = r11
            r19 = 0
            r31 = -1
            r7 = r36
            r40 = r8
            r8 = r51
            r24 = r9
            r19 = r10
            r9 = r29
            r0 = r11
            r48 = r13
            r13 = -1
            r11 = r17
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11)
            r11 = r40
            r11.zzaad = r1
            r45 = r48
            r34 = r0
            r0 = r11
            r46 = r12
            r29 = r14
            r23 = r38
            r25 = r39
            r16 = -1
            r17 = 0
            r30 = 16
            r31 = 3
            goto L_0x07c4
        L_0x0342:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L_0x0348:
            r38 = r5
            r39 = r6
            r24 = r9
            r19 = r10
            r0 = r11
            r48 = r13
            r18 = r14
            r13 = -1
            r14 = r4
            r11 = r8
            r10 = r38
            int r5 = r10 + 8
            r9 = 8
            int r5 = r5 + r9
            r12.setPosition(r5)
            if (r52 == 0) goto L_0x036d
            int r2 = r12.readUnsignedShort()
            r3 = 6
            r12.zzbl(r3)
            goto L_0x0371
        L_0x036d:
            r12.zzbl(r9)
            r2 = 0
        L_0x0371:
            if (r2 == 0) goto L_0x03ae
            r8 = 1
            if (r2 != r8) goto L_0x0378
            r7 = 2
            goto L_0x03b0
        L_0x0378:
            r7 = 2
            if (r2 != r7) goto L_0x0397
            r2 = 16
            r12.zzbl(r2)
            long r2 = r12.readLong()
            double r2 = java.lang.Double.longBitsToDouble(r2)
            long r2 = java.lang.Math.round(r2)
            int r3 = (int) r2
            int r2 = r12.zzhg()
            r4 = 20
            r12.zzbl(r4)
            goto L_0x03c5
        L_0x0397:
            r45 = r48
            r34 = r0
            r23 = r10
            r0 = r11
            r46 = r12
            r29 = r14
            r25 = r39
            r16 = -1
            r17 = 0
            r30 = 16
            r31 = 3
            goto L_0x07c4
        L_0x03ae:
            r7 = 2
            r8 = 1
        L_0x03b0:
            int r3 = r12.readUnsignedShort()
            r4 = 6
            r12.zzbl(r4)
            int r4 = r12.zzhf()
            if (r2 != r8) goto L_0x03c3
            r2 = 16
            r12.zzbl(r2)
        L_0x03c3:
            r2 = r3
            r3 = r4
        L_0x03c5:
            int r4 = r12.getPosition()
            int r5 = com.google.android.gms.internal.ads.zziv.zzana
            if (r1 != r5) goto L_0x03d7
            r6 = r39
            int r1 = zza(r12, r10, r14, r11, r6)
            r12.setPosition(r4)
            goto L_0x03d9
        L_0x03d7:
            r6 = r39
        L_0x03d9:
            int r5 = com.google.android.gms.internal.ads.zziv.zzaln
            if (r1 != r5) goto L_0x03e0
            java.lang.String r1 = "audio/ac3"
            goto L_0x042a
        L_0x03e0:
            int r5 = com.google.android.gms.internal.ads.zziv.zzalp
            if (r1 != r5) goto L_0x03e7
            java.lang.String r1 = "audio/eac3"
            goto L_0x042a
        L_0x03e7:
            int r5 = com.google.android.gms.internal.ads.zziv.zzalr
            if (r1 != r5) goto L_0x03ee
            java.lang.String r1 = "audio/vnd.dts"
            goto L_0x042a
        L_0x03ee:
            int r5 = com.google.android.gms.internal.ads.zziv.zzals
            if (r1 == r5) goto L_0x0428
            int r5 = com.google.android.gms.internal.ads.zziv.zzalt
            if (r1 != r5) goto L_0x03f7
            goto L_0x0428
        L_0x03f7:
            int r5 = com.google.android.gms.internal.ads.zziv.zzalu
            if (r1 != r5) goto L_0x03fe
            java.lang.String r1 = "audio/vnd.dts.hd;profile=lbr"
            goto L_0x042a
        L_0x03fe:
            int r5 = com.google.android.gms.internal.ads.zziv.zzany
            if (r1 != r5) goto L_0x0405
            java.lang.String r1 = "audio/3gpp"
            goto L_0x042a
        L_0x0405:
            int r5 = com.google.android.gms.internal.ads.zziv.zzanz
            if (r1 != r5) goto L_0x040c
            java.lang.String r1 = "audio/amr-wb"
            goto L_0x042a
        L_0x040c:
            int r5 = com.google.android.gms.internal.ads.zziv.zzall
            if (r1 == r5) goto L_0x0425
            int r5 = com.google.android.gms.internal.ads.zziv.zzalm
            if (r1 != r5) goto L_0x0415
            goto L_0x0425
        L_0x0415:
            int r5 = com.google.android.gms.internal.ads.zziv.zzalj
            if (r1 != r5) goto L_0x041c
            java.lang.String r1 = "audio/mpeg"
            goto L_0x042a
        L_0x041c:
            int r5 = com.google.android.gms.internal.ads.zziv.zzaoo
            if (r1 != r5) goto L_0x0423
            java.lang.String r1 = "audio/alac"
            goto L_0x042a
        L_0x0423:
            r1 = 0
            goto L_0x042a
        L_0x0425:
            java.lang.String r1 = "audio/raw"
            goto L_0x042a
        L_0x0428:
            java.lang.String r1 = "audio/vnd.dts.hd"
        L_0x042a:
            r16 = r2
            r17 = r3
            r5 = r4
            r29 = 0
            r4 = r1
        L_0x0432:
            int r1 = r5 - r10
            if (r1 >= r14) goto L_0x057a
            r12.setPosition(r5)
            int r3 = r12.readInt()
            if (r3 <= 0) goto L_0x0441
            r1 = 1
            goto L_0x0442
        L_0x0441:
            r1 = 0
        L_0x0442:
            java.lang.String r2 = "childAtomSize should be positive"
            com.google.android.gms.internal.ads.zzpo.checkArgument(r1, r2)
            int r1 = r12.readInt()
            int r2 = com.google.android.gms.internal.ads.zziv.zzamj
            if (r1 == r2) goto L_0x04f9
            if (r52 == 0) goto L_0x0457
            int r2 = com.google.android.gms.internal.ads.zziv.zzalk
            if (r1 != r2) goto L_0x0457
            goto L_0x04f9
        L_0x0457:
            int r2 = com.google.android.gms.internal.ads.zziv.zzalo
            if (r1 != r2) goto L_0x0478
            int r1 = r5 + 8
            r12.setPosition(r1)
            java.lang.String r1 = java.lang.Integer.toString(r22)
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzgg.zza(r12, r1, r0, r15)
            r11.zzaad = r1
            r13 = r3
            r41 = r4
            r4 = r5
            r43 = r6
            r44 = r10
            r15 = r11
            r11 = 0
            r30 = 2
            goto L_0x04f6
        L_0x0478:
            int r2 = com.google.android.gms.internal.ads.zziv.zzalq
            if (r1 != r2) goto L_0x0499
            int r1 = r5 + 8
            r12.setPosition(r1)
            java.lang.String r1 = java.lang.Integer.toString(r22)
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzgg.zzb(r12, r1, r0, r15)
            r11.zzaad = r1
            r13 = r3
            r41 = r4
            r4 = r5
            r43 = r6
            r44 = r10
            r15 = r11
            r11 = 0
            r30 = 2
            goto L_0x04f6
        L_0x0499:
            int r2 = com.google.android.gms.internal.ads.zziv.zzalv
            if (r1 != r2) goto L_0x04d3
            java.lang.String r1 = java.lang.Integer.toString(r22)
            r30 = 0
            r31 = -1
            r34 = -1
            r35 = 0
            r36 = 0
            r2 = r4
            r13 = r3
            r3 = r30
            r41 = r4
            r4 = r31
            r42 = r5
            r5 = r34
            r43 = r6
            r6 = r16
            r30 = 2
            r7 = r17
            r8 = r35
            r9 = r51
            r44 = r10
            r10 = r36
            r15 = r11
            r11 = r0
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r15.zzaad = r1
            r4 = r42
            r11 = 0
            goto L_0x04f6
        L_0x04d3:
            r13 = r3
            r41 = r4
            r42 = r5
            r43 = r6
            r44 = r10
            r15 = r11
            r30 = 2
            int r2 = com.google.android.gms.internal.ads.zziv.zzaoo
            if (r1 != r2) goto L_0x04f3
            byte[] r1 = new byte[r13]
            r4 = r42
            r12.setPosition(r4)
            r11 = 0
            r12.zze(r1, r11, r13)
            r29 = r1
            r10 = -1
            goto L_0x0568
        L_0x04f3:
            r4 = r42
            r11 = 0
        L_0x04f6:
            r10 = -1
            goto L_0x0568
        L_0x04f9:
            r13 = r3
            r41 = r4
            r4 = r5
            r43 = r6
            r44 = r10
            r15 = r11
            r11 = 0
            r30 = 2
            int r2 = com.google.android.gms.internal.ads.zziv.zzamj
            if (r1 != r2) goto L_0x050c
            r1 = r4
            r10 = -1
            goto L_0x0533
        L_0x050c:
            int r1 = r12.getPosition()
        L_0x0510:
            int r2 = r1 - r4
            if (r2 >= r13) goto L_0x0531
            r12.setPosition(r1)
            int r2 = r12.readInt()
            if (r2 <= 0) goto L_0x051f
            r3 = 1
            goto L_0x0520
        L_0x051f:
            r3 = 0
        L_0x0520:
            java.lang.String r5 = "childAtomSize should be positive"
            com.google.android.gms.internal.ads.zzpo.checkArgument(r3, r5)
            int r3 = r12.readInt()
            int r5 = com.google.android.gms.internal.ads.zziv.zzamj
            if (r3 != r5) goto L_0x052f
            r10 = -1
            goto L_0x0533
        L_0x052f:
            int r1 = r1 + r2
            goto L_0x0510
        L_0x0531:
            r1 = -1
            r10 = -1
        L_0x0533:
            if (r1 == r10) goto L_0x0564
            android.util.Pair r1 = zzb(r12, r1)
            java.lang.Object r2 = r1.first
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.second
            r29 = r1
            byte[] r29 = (byte[]) r29
            java.lang.String r1 = "audio/mp4a-latm"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0566
            android.util.Pair r1 = com.google.android.gms.internal.ads.zzpp.zzf(r29)
            java.lang.Object r3 = r1.first
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            java.lang.Object r1 = r1.second
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            r16 = r1
            r17 = r3
            goto L_0x0566
        L_0x0564:
            r2 = r41
        L_0x0566:
            r41 = r2
        L_0x0568:
            int r5 = r4 + r13
            r11 = r15
            r4 = r41
            r6 = r43
            r10 = r44
            r7 = 2
            r8 = 1
            r9 = 8
            r13 = -1
            r15 = r51
            goto L_0x0432
        L_0x057a:
            r41 = r4
            r43 = r6
            r44 = r10
            r15 = r11
            r10 = -1
            r11 = 0
            r30 = 2
            com.google.android.gms.internal.ads.zzfs r1 = r15.zzaad
            if (r1 != 0) goto L_0x05bd
            r2 = r41
            if (r2 == 0) goto L_0x05bd
            java.lang.String r1 = "audio/raw"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0597
            r8 = 2
            goto L_0x0598
        L_0x0597:
            r8 = -1
        L_0x0598:
            java.lang.String r1 = java.lang.Integer.toString(r22)
            r3 = 0
            r4 = -1
            r5 = -1
            if (r29 != 0) goto L_0x05a3
            r9 = 0
            goto L_0x05a8
        L_0x05a3:
            java.util.List r6 = java.util.Collections.singletonList(r29)
            r9 = r6
        L_0x05a8:
            r13 = 0
            r6 = r16
            r7 = r17
            r16 = -1
            r10 = r51
            r17 = 0
            r11 = r13
            r13 = r12
            r12 = r0
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r15.zzaad = r1
            goto L_0x05c2
        L_0x05bd:
            r13 = r12
            r16 = -1
            r17 = 0
        L_0x05c2:
            r45 = r48
            r34 = r0
            r46 = r13
            r29 = r14
            r0 = r15
            r25 = r43
            r23 = r44
            r30 = 16
            r31 = 3
            goto L_0x07c4
        L_0x05d5:
            r44 = r5
            r43 = r6
            r15 = r8
            r24 = r9
            r19 = r10
            r0 = r11
            r48 = r13
            r18 = r14
            r16 = -1
            r17 = 0
            r14 = r4
            r13 = r12
            r12 = r44
            int r5 = r12 + 8
            r11 = 8
            int r5 = r5 + r11
            r13.setPosition(r5)
            r10 = 16
            r13.zzbl(r10)
            int r6 = r13.readUnsignedShort()
            int r7 = r13.readUnsignedShort()
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 50
            r13.zzbl(r3)
            int r3 = r13.getPosition()
            int r4 = com.google.android.gms.internal.ads.zziv.zzamz
            if (r1 != r4) goto L_0x0619
            r9 = r43
            int r1 = zza(r13, r12, r14, r15, r9)
            r13.setPosition(r3)
            goto L_0x061b
        L_0x0619:
            r9 = r43
        L_0x061b:
            r2 = 0
            r4 = 0
            r23 = 1065353216(0x3f800000, float:1.0)
            r29 = -1
            r30 = 0
            r31 = 0
        L_0x0625:
            int r5 = r3 - r12
            if (r5 >= r14) goto L_0x0781
            r13.setPosition(r3)
            int r5 = r13.getPosition()
            int r8 = r13.readInt()
            if (r8 != 0) goto L_0x0644
            int r34 = r13.getPosition()
            int r10 = r34 - r12
            if (r10 == r14) goto L_0x063f
            goto L_0x0644
        L_0x063f:
            r34 = r0
            r11 = 3
            goto L_0x0784
        L_0x0644:
            if (r8 <= 0) goto L_0x0648
            r10 = 1
            goto L_0x0649
        L_0x0648:
            r10 = 0
        L_0x0649:
            java.lang.String r11 = "childAtomSize should be positive"
            com.google.android.gms.internal.ads.zzpo.checkArgument(r10, r11)
            int r10 = r13.readInt()
            int r11 = com.google.android.gms.internal.ads.zziv.zzamh
            if (r10 != r11) goto L_0x067f
            if (r2 != 0) goto L_0x065a
            r2 = 1
            goto L_0x065b
        L_0x065a:
            r2 = 0
        L_0x065b:
            com.google.android.gms.internal.ads.zzpo.checkState(r2)
            java.lang.String r2 = "video/avc"
            int r5 = r5 + 8
            r13.setPosition(r5)
            com.google.android.gms.internal.ads.zzqh r5 = com.google.android.gms.internal.ads.zzqh.zzg(r13)
            java.util.List<byte[]> r10 = r5.zzzl
            int r11 = r5.zzakx
            r15.zzakx = r11
            if (r4 != 0) goto L_0x0676
            float r5 = r5.zzbhq
            r23 = r5
        L_0x0676:
            r34 = r0
            r36 = r1
            r30 = r10
            r11 = 3
            goto L_0x0776
        L_0x067f:
            int r11 = com.google.android.gms.internal.ads.zziv.zzami
            if (r10 != r11) goto L_0x06a6
            if (r2 != 0) goto L_0x0687
            r2 = 1
            goto L_0x0688
        L_0x0687:
            r2 = 0
        L_0x0688:
            com.google.android.gms.internal.ads.zzpo.checkState(r2)
            java.lang.String r2 = "video/hevc"
            int r5 = r5 + 8
            r13.setPosition(r5)
            com.google.android.gms.internal.ads.zzqn r5 = com.google.android.gms.internal.ads.zzqn.zzi(r13)
            java.util.List<byte[]> r10 = r5.zzzl
            int r5 = r5.zzakx
            r15.zzakx = r5
            r34 = r0
            r36 = r1
            r30 = r10
            r11 = 3
            goto L_0x0776
        L_0x06a6:
            int r11 = com.google.android.gms.internal.ads.zziv.zzaom
            if (r10 != r11) goto L_0x06c4
            if (r2 != 0) goto L_0x06ae
            r2 = 1
            goto L_0x06af
        L_0x06ae:
            r2 = 0
        L_0x06af:
            com.google.android.gms.internal.ads.zzpo.checkState(r2)
            int r2 = com.google.android.gms.internal.ads.zziv.zzaok
            if (r1 != r2) goto L_0x06ba
            java.lang.String r2 = "video/x-vnd.on2.vp8"
            goto L_0x06bd
        L_0x06ba:
            java.lang.String r2 = "video/x-vnd.on2.vp9"
        L_0x06bd:
            r34 = r0
            r36 = r1
            r11 = 3
            goto L_0x0776
        L_0x06c4:
            int r11 = com.google.android.gms.internal.ads.zziv.zzalg
            if (r10 != r11) goto L_0x06da
            if (r2 != 0) goto L_0x06cc
            r2 = 1
            goto L_0x06cd
        L_0x06cc:
            r2 = 0
        L_0x06cd:
            com.google.android.gms.internal.ads.zzpo.checkState(r2)
            java.lang.String r2 = "video/3gpp"
            r34 = r0
            r36 = r1
            r11 = 3
            goto L_0x0776
        L_0x06da:
            int r11 = com.google.android.gms.internal.ads.zziv.zzamj
            if (r10 != r11) goto L_0x06fe
            if (r2 != 0) goto L_0x06e2
            r2 = 1
            goto L_0x06e3
        L_0x06e2:
            r2 = 0
        L_0x06e3:
            com.google.android.gms.internal.ads.zzpo.checkState(r2)
            android.util.Pair r2 = zzb(r13, r5)
            java.lang.Object r5 = r2.first
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r2 = r2.second
            byte[] r2 = (byte[]) r2
            java.util.List r30 = java.util.Collections.singletonList(r2)
            r34 = r0
            r36 = r1
            r2 = r5
            r11 = 3
            goto L_0x0776
        L_0x06fe:
            int r11 = com.google.android.gms.internal.ads.zziv.zzani
            if (r10 != r11) goto L_0x071a
            int r5 = r5 + 8
            r13.setPosition(r5)
            int r4 = r13.zzhg()
            int r5 = r13.zzhg()
            float r4 = (float) r4
            float r5 = (float) r5
            float r23 = r4 / r5
            r34 = r0
            r36 = r1
            r4 = 1
            r11 = 3
            goto L_0x0776
        L_0x071a:
            int r11 = com.google.android.gms.internal.ads.zziv.zzaoi
            if (r10 != r11) goto L_0x074f
            int r10 = r5 + 8
        L_0x0720:
            int r11 = r10 - r5
            if (r11 >= r8) goto L_0x0747
            r13.setPosition(r10)
            int r11 = r13.readInt()
            r34 = r0
            int r0 = r13.readInt()
            r36 = r1
            int r1 = com.google.android.gms.internal.ads.zziv.zzaoj
            if (r0 != r1) goto L_0x0741
            byte[] r0 = r13.data
            int r11 = r11 + r10
            byte[] r0 = java.util.Arrays.copyOfRange(r0, r10, r11)
            r31 = r0
            goto L_0x074d
        L_0x0741:
            int r10 = r10 + r11
            r0 = r34
            r1 = r36
            goto L_0x0720
        L_0x0747:
            r34 = r0
            r36 = r1
            r31 = 0
        L_0x074d:
            r11 = 3
            goto L_0x0776
        L_0x074f:
            r34 = r0
            r36 = r1
            int r0 = com.google.android.gms.internal.ads.zziv.zzaoh
            if (r10 != r0) goto L_0x0775
            int r0 = r13.readUnsignedByte()
            r11 = 3
            r13.zzbl(r11)
            if (r0 != 0) goto L_0x0776
            int r0 = r13.readUnsignedByte()
            switch(r0) {
                case 0: goto L_0x0772;
                case 1: goto L_0x076f;
                case 2: goto L_0x076c;
                case 3: goto L_0x0769;
                default: goto L_0x0768;
            }
        L_0x0768:
            goto L_0x0776
        L_0x0769:
            r29 = 3
            goto L_0x0776
        L_0x076c:
            r29 = 2
            goto L_0x0776
        L_0x076f:
            r29 = 1
            goto L_0x0776
        L_0x0772:
            r29 = 0
            goto L_0x0776
        L_0x0775:
            r11 = 3
        L_0x0776:
            int r3 = r3 + r8
            r0 = r34
            r1 = r36
            r10 = 16
            r11 = 8
            goto L_0x0625
        L_0x0781:
            r34 = r0
            r11 = 3
        L_0x0784:
            if (r2 == 0) goto L_0x07b5
            java.lang.String r1 = java.lang.Integer.toString(r22)
            r3 = 0
            r4 = -1
            r5 = -1
            r8 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 0
            r25 = r9
            r9 = r30
            r30 = 16
            r10 = r28
            r35 = 3
            r11 = r23
            r23 = r12
            r12 = r31
            r45 = r48
            r46 = r13
            r31 = 3
            r13 = r29
            r29 = r14
            r14 = r0
            r0 = r15
            r15 = r51
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r0.zzaad = r1
            goto L_0x07c4
        L_0x07b5:
            r45 = r48
            r25 = r9
            r23 = r12
            r46 = r13
            r29 = r14
            r0 = r15
            r30 = 16
            r31 = 3
        L_0x07c4:
            int r5 = r23 + r29
            r1 = r46
            r1.setPosition(r5)
            int r6 = r25 + 1
            r15 = r51
            r8 = r0
            r12 = r1
            r14 = r18
            r10 = r19
            r9 = r24
            r11 = r34
            r13 = r45
            r0 = r47
            r7 = 8
            goto L_0x01b5
        L_0x07e1:
            r0 = r8
            r19 = r10
            r45 = r13
            r18 = r14
            r17 = 0
            int r1 = com.google.android.gms.internal.ads.zziv.zzamp
            r2 = r47
            com.google.android.gms.internal.ads.zziw r1 = r2.zzaj(r1)
            if (r1 == 0) goto L_0x084f
            int r2 = com.google.android.gms.internal.ads.zziv.zzamq
            com.google.android.gms.internal.ads.zzix r1 = r1.zzai(r2)
            if (r1 != 0) goto L_0x07fd
            goto L_0x084f
        L_0x07fd:
            com.google.android.gms.internal.ads.zzpx r1 = r1.zzaos
            r2 = 8
            r1.setPosition(r2)
            int r2 = r1.readInt()
            int r2 = com.google.android.gms.internal.ads.zziv.zzaf(r2)
            int r3 = r1.zzhg()
            long[] r4 = new long[r3]
            long[] r5 = new long[r3]
            r6 = 0
        L_0x0815:
            if (r6 >= r3) goto L_0x0848
            r7 = 1
            if (r2 != r7) goto L_0x081f
            long r8 = r1.zzhh()
            goto L_0x0823
        L_0x081f:
            long r8 = r1.zzhd()
        L_0x0823:
            r4[r6] = r8
            if (r2 != r7) goto L_0x082c
            long r8 = r1.readLong()
            goto L_0x0831
        L_0x082c:
            int r8 = r1.readInt()
            long r8 = (long) r8
        L_0x0831:
            r5[r6] = r8
            short r8 = r1.readShort()
            if (r8 != r7) goto L_0x0840
            r8 = 2
            r1.zzbl(r8)
            int r6 = r6 + 1
            goto L_0x0815
        L_0x0840:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unsupported media rate."
            r0.<init>(r1)
            throw r0
        L_0x0848:
            android.util.Pair r1 = android.util.Pair.create(r4, r5)
            r2 = r1
            r1 = 0
            goto L_0x0854
        L_0x084f:
            r1 = 0
            android.util.Pair r2 = android.util.Pair.create(r1, r1)
        L_0x0854:
            com.google.android.gms.internal.ads.zzfs r3 = r0.zzaad
            if (r3 != 0) goto L_0x0859
            return r1
        L_0x0859:
            com.google.android.gms.internal.ads.zzjs r1 = new com.google.android.gms.internal.ads.zzjs
            int r16 = r18.id
            r3 = r45
            java.lang.Object r3 = r3.first
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            com.google.android.gms.internal.ads.zzfs r5 = r0.zzaad
            int r6 = r0.zzapi
            com.google.android.gms.internal.ads.zzjt[] r7 = r0.zzaph
            int r0 = r0.zzakx
            java.lang.Object r8 = r2.first
            r28 = r8
            long[] r28 = (long[]) r28
            java.lang.Object r2 = r2.second
            r29 = r2
            long[] r29 = (long[]) r29
            r15 = r1
            r17 = r19
            r18 = r3
            r20 = r32
            r22 = r26
            r24 = r5
            r25 = r6
            r26 = r7
            r27 = r0
            r15.<init>(r16, r17, r18, r20, r22, r24, r25, r26, r27, r28, r29)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zziy.zza(com.google.android.gms.internal.ads.zziw, com.google.android.gms.internal.ads.zzix, long, com.google.android.gms.internal.ads.zzhp, boolean):com.google.android.gms.internal.ads.zzjs");
    }

    public static zzjv zza(zzjs zzjs, zziw zziw, zzid zzid) throws zzfx {
        zzja zzja;
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        long[] jArr;
        int[] iArr2;
        long[] jArr2;
        long j;
        boolean z2;
        int[] iArr3;
        int i5;
        long[] jArr3;
        int[] iArr4;
        int[] iArr5;
        int[] iArr6;
        int i6;
        int i7;
        zzja zzja2;
        int i8;
        int i9;
        int i10;
        int i11;
        zzjs zzjs2 = zzjs;
        zziw zziw2 = zziw;
        zzid zzid2 = zzid;
        zzix zzai = zziw2.zzai(zziv.zzanq);
        if (zzai != null) {
            zzja = new zzjc(zzai);
        } else {
            zzix zzai2 = zziw2.zzai(zziv.zzanr);
            if (zzai2 != null) {
                zzja = new zzjd(zzai2);
            } else {
                throw new zzfx("Track has no sample table size information");
            }
        }
        int zzef = zzja.zzef();
        if (zzef == 0) {
            zzjv zzjv = new zzjv(new long[0], new int[0], 0, new long[0], new int[0]);
            return zzjv;
        }
        zzix zzai3 = zziw2.zzai(zziv.zzans);
        if (zzai3 == null) {
            zzai3 = zziw2.zzai(zziv.zzant);
            z = true;
        } else {
            z = false;
        }
        zzpx zzpx = zzai3.zzaos;
        zzpx zzpx2 = zziw2.zzai(zziv.zzanp).zzaos;
        zzpx zzpx3 = zziw2.zzai(zziv.zzanm).zzaos;
        zzix zzai4 = zziw2.zzai(zziv.zzann);
        zzpx zzpx4 = zzai4 != null ? zzai4.zzaos : null;
        zzix zzai5 = zziw2.zzai(zziv.zzano);
        zzpx zzpx5 = zzai5 != null ? zzai5.zzaos : null;
        zziz zziz = new zziz(zzpx2, zzpx, z);
        zzpx3.setPosition(12);
        int zzhg = zzpx3.zzhg() - 1;
        int zzhg2 = zzpx3.zzhg();
        int zzhg3 = zzpx3.zzhg();
        if (zzpx5 != null) {
            zzpx5.setPosition(12);
            i = zzpx5.zzhg();
        } else {
            i = 0;
        }
        int i12 = -1;
        if (zzpx4 != null) {
            zzpx4.setPosition(12);
            i2 = zzpx4.zzhg();
            if (i2 > 0) {
                i12 = zzpx4.zzhg() - 1;
            } else {
                zzpx4 = null;
            }
        } else {
            i2 = 0;
        }
        long j2 = 0;
        if (!(zzja.zzeh() && MimeTypes.AUDIO_RAW.equals(zzjs2.zzaad.zzzj) && zzhg == 0 && i == 0 && i2 == 0)) {
            jArr2 = new long[zzef];
            iArr = new int[zzef];
            jArr = new long[zzef];
            int i13 = i2;
            iArr2 = new int[zzef];
            int i14 = i13;
            zzpx zzpx6 = zzpx3;
            int i15 = zzhg3;
            int i16 = i;
            int i17 = i12;
            long j3 = 0;
            long j4 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = zzhg2;
            int i23 = zzhg;
            int i24 = 0;
            while (i24 < zzef) {
                long j5 = j3;
                int i25 = i18;
                while (i25 == 0) {
                    zzpo.checkState(zziz.zzee());
                    int i26 = i23;
                    int i27 = i15;
                    j5 = zziz.zzapb;
                    i25 = zziz.zzapa;
                    i23 = i26;
                    i15 = i27;
                }
                int i28 = i23;
                int i29 = i15;
                if (zzpx5 != null) {
                    while (i21 == 0 && i16 > 0) {
                        i21 = zzpx5.zzhg();
                        i20 = zzpx5.readInt();
                        i16--;
                    }
                    i21--;
                    i6 = i20;
                } else {
                    i6 = i20;
                }
                jArr2[i24] = j5;
                iArr[i24] = zzja.zzeg();
                if (iArr[i24] > i19) {
                    i7 = zzef;
                    i19 = iArr[i24];
                    zzja2 = zzja;
                } else {
                    i7 = zzef;
                    zzja2 = zzja;
                }
                jArr[i24] = j4 + ((long) i6);
                iArr2[i24] = zzpx4 == null ? 1 : 0;
                if (i24 == i17) {
                    iArr2[i24] = 1;
                    int i30 = i14 - 1;
                    if (i30 > 0) {
                        i9 = i30;
                        i8 = zzpx4.zzhg() - 1;
                        i10 = i29;
                    } else {
                        i9 = i30;
                        i8 = i17;
                        i10 = i29;
                    }
                } else {
                    i9 = i14;
                    i8 = i17;
                    i10 = i29;
                }
                j4 += (long) i10;
                i22--;
                if (i22 != 0 || i28 <= 0) {
                    i11 = i28;
                } else {
                    i11 = i28 - 1;
                    i22 = zzpx6.zzhg();
                    i10 = zzpx6.zzhg();
                }
                int i31 = i11;
                i24++;
                i17 = i8;
                zzef = i7;
                i18 = i25 - 1;
                i20 = i6;
                i23 = i31;
                j3 = j5 + ((long) iArr[i24]);
                zzja zzja3 = zzja2;
                i15 = i10;
                i14 = i9;
                zzja = zzja3;
            }
            i4 = zzef;
            int i32 = i23;
            zzpo.checkArgument(i21 == 0);
            while (i16 > 0) {
                zzpo.checkArgument(zzpx5.zzhg() == 0);
                zzpx5.readInt();
                i16--;
            }
            if (i14 == 0 && i22 == 0 && i18 == 0 && i32 == 0) {
                zzjs2 = zzjs;
            } else {
                int i33 = i14;
                zzjs2 = zzjs;
                int i34 = zzjs2.id;
                StringBuilder sb = new StringBuilder(215);
                sb.append("Inconsistent stbl box for track ");
                sb.append(i34);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i33);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(i22);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i18);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i32);
                Log.w("AtomParsers", sb.toString());
            }
            j = j4;
            i3 = i19;
        } else {
            i4 = zzef;
            zzja zzja4 = zzja;
            long[] jArr4 = new long[zziz.length];
            int[] iArr7 = new int[zziz.length];
            while (zziz.zzee()) {
                jArr4[zziz.index] = zziz.zzapb;
                iArr7[zziz.index] = zziz.zzapa;
            }
            int zzeg = zzja4.zzeg();
            long j6 = (long) zzhg3;
            int i35 = 8192 / zzeg;
            int i36 = 0;
            for (int zzf : iArr7) {
                i36 += zzqe.zzf(zzf, i35);
            }
            long[] jArr5 = new long[i36];
            int[] iArr8 = new int[i36];
            long[] jArr6 = new long[i36];
            int[] iArr9 = new int[i36];
            int i37 = 0;
            int i38 = 0;
            int i39 = 0;
            int i40 = 0;
            while (i37 < iArr7.length) {
                int i41 = iArr7[i37];
                long j7 = jArr4[i37];
                int i42 = i38;
                int i43 = i40;
                while (i41 > 0) {
                    int min = Math.min(i35, i41);
                    jArr5[i39] = j7;
                    iArr8[i39] = zzeg * min;
                    long[] jArr7 = jArr4;
                    i43 = Math.max(i43, iArr8[i39]);
                    int[] iArr10 = iArr7;
                    jArr6[i39] = ((long) i42) * j6;
                    iArr9[i39] = 1;
                    j7 += (long) iArr8[i39];
                    i42 += min;
                    i41 -= min;
                    i39++;
                    jArr4 = jArr7;
                    iArr7 = iArr10;
                }
                long[] jArr8 = jArr4;
                int[] iArr11 = iArr7;
                i37++;
                i40 = i43;
                i38 = i42;
            }
            zzjh zzjh = new zzjh(jArr5, iArr8, i40, jArr6, iArr9);
            jArr2 = zzjh.zzagu;
            iArr = zzjh.zzagt;
            int i44 = zzjh.zzapp;
            jArr = zzjh.zzapq;
            iArr2 = zzjh.zzapr;
            i3 = i44;
            j = 0;
        }
        if (zzjs2.zzaso == null || zzid.zzea()) {
            long[] jArr9 = jArr2;
            int[] iArr12 = iArr2;
            int[] iArr13 = iArr;
            zzqe.zza(jArr, 1000000, zzjs2.zzcr);
            zzjv zzjv2 = new zzjv(jArr9, iArr13, i3, jArr, iArr12);
            return zzjv2;
        }
        if (zzjs2.zzaso.length == 1 && zzjs2.type == 1 && jArr.length >= 2) {
            long j8 = zzjs2.zzasp[0];
            long zza = zzqe.zza(zzjs2.zzaso[0], zzjs2.zzcr, zzjs2.zzasl) + j8;
            if (jArr[0] <= j8 && j8 < jArr[1] && jArr[jArr.length - 1] < zza && zza <= j) {
                long j9 = j - zza;
                long zza2 = zzqe.zza(j8 - jArr[0], (long) zzjs2.zzaad.zzzu, zzjs2.zzcr);
                long zza3 = zzqe.zza(j9, (long) zzjs2.zzaad.zzzu, zzjs2.zzcr);
                if (!(zza2 == 0 && zza3 == 0) && zza2 <= 2147483647L && zza3 <= 2147483647L) {
                    int i45 = (int) zza2;
                    zzid zzid3 = zzid;
                    zzid3.zzzw = i45;
                    zzid3.zzzx = (int) zza3;
                    zzqe.zza(jArr, 1000000, zzjs2.zzcr);
                    zzjv zzjv3 = new zzjv(jArr2, iArr, i3, jArr, iArr2);
                    return zzjv3;
                }
            }
        }
        if (zzjs2.zzaso.length == 1) {
            char c = 0;
            if (zzjs2.zzaso[0] == 0) {
                int i46 = 0;
                while (i46 < jArr.length) {
                    jArr[i46] = zzqe.zza(jArr[i46] - zzjs2.zzasp[c], 1000000, zzjs2.zzcr);
                    i46++;
                    c = 0;
                }
                zzjv zzjv4 = new zzjv(jArr2, iArr, i3, jArr, iArr2);
                return zzjv4;
            }
        }
        boolean z3 = zzjs2.type == 1;
        int i47 = 0;
        boolean z4 = false;
        int i48 = 0;
        int i49 = 0;
        while (i47 < zzjs2.zzaso.length) {
            long j10 = zzjs2.zzasp[i47];
            if (j10 != -1) {
                iArr6 = iArr;
                long zza4 = zzqe.zza(zzjs2.zzaso[i47], zzjs2.zzcr, zzjs2.zzasl);
                int zzb = zzqe.zzb(jArr, j10, true, true);
                int zzb2 = zzqe.zzb(jArr, j10 + zza4, z3, false);
                i48 += zzb2 - zzb;
                z4 |= i49 != zzb;
                i49 = zzb2;
            } else {
                iArr6 = iArr;
            }
            i47++;
            iArr = iArr6;
        }
        int[] iArr14 = iArr;
        boolean z5 = (i48 != i4) | z4;
        long[] jArr10 = z5 ? new long[i48] : jArr2;
        int[] iArr15 = z5 ? new int[i48] : iArr14;
        if (z5) {
            i3 = 0;
        }
        int[] iArr16 = z5 ? new int[i48] : iArr2;
        long[] jArr11 = new long[i48];
        int i50 = i3;
        int i51 = 0;
        int i52 = 0;
        while (i51 < zzjs2.zzaso.length) {
            long j11 = zzjs2.zzasp[i51];
            long j12 = zzjs2.zzaso[i51];
            if (j11 != -1) {
                int[] iArr17 = iArr16;
                i5 = i51;
                long zza5 = zzqe.zza(j12, zzjs2.zzcr, zzjs2.zzasl) + j11;
                int zzb3 = zzqe.zzb(jArr, j11, true, true);
                int zzb4 = zzqe.zzb(jArr, zza5, z3, false);
                if (z5) {
                    int i53 = zzb4 - zzb3;
                    System.arraycopy(jArr2, zzb3, jArr10, i52, i53);
                    iArr4 = iArr14;
                    System.arraycopy(iArr4, zzb3, iArr15, i52, i53);
                    z2 = z3;
                    iArr5 = iArr17;
                    System.arraycopy(iArr2, zzb3, iArr5, i52, i53);
                } else {
                    iArr4 = iArr14;
                    z2 = z3;
                    iArr5 = iArr17;
                }
                int i54 = i50;
                while (zzb3 < zzb4) {
                    long[] jArr12 = jArr2;
                    int[] iArr18 = iArr2;
                    long j13 = j11;
                    jArr11[i52] = zzqe.zza(j2, 1000000, zzjs2.zzasl) + zzqe.zza(jArr[zzb3] - j11, 1000000, zzjs2.zzcr);
                    if (z5 && iArr15[i52] > i54) {
                        i54 = iArr4[zzb3];
                    }
                    i52++;
                    zzb3++;
                    jArr2 = jArr12;
                    j11 = j13;
                    iArr2 = iArr18;
                }
                jArr3 = jArr2;
                iArr3 = iArr2;
                i50 = i54;
            } else {
                iArr4 = iArr14;
                z2 = z3;
                jArr3 = jArr2;
                iArr3 = iArr2;
                iArr5 = iArr16;
                i5 = i51;
            }
            j2 += j12;
            i51 = i5 + 1;
            iArr16 = iArr5;
            jArr2 = jArr3;
            iArr2 = iArr3;
            z3 = z2;
            iArr14 = iArr4;
        }
        int[] iArr19 = iArr16;
        boolean z6 = false;
        for (int i55 = 0; i55 < iArr19.length && !z6; i55++) {
            z6 |= (iArr19[i55] & 1) != 0;
        }
        if (z6) {
            zzjv zzjv5 = new zzjv(jArr10, iArr15, i50, jArr11, iArr19);
            return zzjv5;
        }
        throw new zzfx("The edited sample sequence does not contain a sync sample.");
    }

    public static zzki zza(zzix zzix, boolean z) {
        if (z) {
            return null;
        }
        zzpx zzpx = zzix.zzaos;
        zzpx.setPosition(8);
        while (zzpx.zzhb() >= 8) {
            int position = zzpx.getPosition();
            int readInt = zzpx.readInt();
            if (zzpx.readInt() == zziv.zzaob) {
                zzpx.setPosition(position);
                int i = position + readInt;
                zzpx.zzbl(12);
                while (true) {
                    if (zzpx.getPosition() >= i) {
                        break;
                    }
                    int position2 = zzpx.getPosition();
                    int readInt2 = zzpx.readInt();
                    if (zzpx.readInt() == zziv.zzaoc) {
                        zzpx.setPosition(position2);
                        int i2 = position2 + readInt2;
                        zzpx.zzbl(8);
                        ArrayList arrayList = new ArrayList();
                        while (zzpx.getPosition() < i2) {
                            zza zzd = zzjm.zzd(zzpx);
                            if (zzd != null) {
                                arrayList.add(zzd);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            return new zzki((List<? extends zza>) arrayList);
                        }
                    } else {
                        zzpx.zzbl(readInt2 - 8);
                    }
                }
                return null;
            }
            zzpx.zzbl(readInt - 8);
        }
        return null;
    }

    private static Pair<String, byte[]> zzb(zzpx zzpx, int i) {
        zzpx.setPosition(i + 8 + 4);
        zzpx.zzbl(1);
        zzc(zzpx);
        zzpx.zzbl(2);
        int readUnsignedByte = zzpx.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            zzpx.zzbl(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            zzpx.zzbl(zzpx.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            zzpx.zzbl(2);
        }
        zzpx.zzbl(1);
        zzc(zzpx);
        String str = null;
        switch (zzpx.readUnsignedByte()) {
            case 32:
                str = MimeTypes.VIDEO_MP4V;
                break;
            case 33:
                str = MimeTypes.VIDEO_H264;
                break;
            case 35:
                str = MimeTypes.VIDEO_H265;
                break;
            case 64:
            case 102:
            case 103:
            case 104:
                str = MimeTypes.AUDIO_AAC;
                break;
            case 107:
                return Pair.create(MimeTypes.AUDIO_MPEG, null);
            case 165:
                str = MimeTypes.AUDIO_AC3;
                break;
            case 166:
                str = MimeTypes.AUDIO_E_AC3;
                break;
            case 169:
            case 172:
                return Pair.create(MimeTypes.AUDIO_DTS, null);
            case RequestCodes.CHALLENGES /*170*/:
            case RequestCodes.CHALLENGE_DETAIL /*171*/:
                return Pair.create(MimeTypes.AUDIO_DTS_HD, null);
        }
        zzpx.zzbl(12);
        zzpx.zzbl(1);
        int zzc = zzc(zzpx);
        byte[] bArr = new byte[zzc];
        zzpx.zze(bArr, 0, zzc);
        return Pair.create(str, bArr);
    }

    private static int zza(zzpx zzpx, int i, int i2, zzjb zzjb, int i3) {
        Object obj;
        zzpx zzpx2 = zzpx;
        int position = zzpx.getPosition();
        while (true) {
            boolean z = false;
            if (position - i >= i2) {
                return 0;
            }
            zzpx.setPosition(position);
            int readInt = zzpx.readInt();
            zzpo.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (zzpx.readInt() == zziv.zzamv) {
                int i4 = position + 8;
                Pair pair = null;
                Object obj2 = null;
                Object obj3 = null;
                boolean z2 = false;
                while (i4 - position < readInt) {
                    zzpx.setPosition(i4);
                    int readInt2 = zzpx.readInt();
                    int readInt3 = zzpx.readInt();
                    if (readInt3 == zziv.zzanb) {
                        obj2 = Integer.valueOf(zzpx.readInt());
                    } else if (readInt3 == zziv.zzamw) {
                        zzpx.zzbl(4);
                        z2 = zzpx.readInt() == zzaoz;
                    } else if (readInt3 == zziv.zzamx) {
                        int i5 = i4 + 8;
                        while (true) {
                            if (i5 - i4 >= readInt2) {
                                obj = null;
                                break;
                            }
                            zzpx.setPosition(i5);
                            int readInt4 = zzpx.readInt();
                            if (zzpx.readInt() == zziv.zzamy) {
                                zzpx.zzbl(6);
                                boolean z3 = zzpx.readUnsignedByte() == 1;
                                int readUnsignedByte = zzpx.readUnsignedByte();
                                byte[] bArr = new byte[16];
                                zzpx.zze(bArr, 0, 16);
                                obj = new zzjt(z3, readUnsignedByte, bArr);
                            } else {
                                i5 += readInt4;
                            }
                        }
                        obj3 = obj;
                    }
                    i4 += readInt2;
                }
                if (z2) {
                    zzpo.checkArgument(obj2 != null, "frma atom is mandatory");
                    if (obj3 != null) {
                        z = true;
                    }
                    zzpo.checkArgument(z, "schi->tenc atom is mandatory");
                    pair = Pair.create(obj2, obj3);
                }
                if (pair != null) {
                    zzjb.zzaph[i3] = (zzjt) pair.second;
                    return ((Integer) pair.first).intValue();
                }
            }
            zzjb zzjb2 = zzjb;
            position += readInt;
        }
    }

    private static int zzc(zzpx zzpx) {
        int readUnsignedByte = zzpx.readUnsignedByte();
        int i = readUnsignedByte & Statements.GetOwnedFoodIdsDateDescending;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = zzpx.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & Statements.GetOwnedFoodIdsDateDescending);
        }
        return i;
    }
}
