package com.google.android.gms.measurement.internal;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfj;
import com.google.android.gms.internal.measurement.zzfk;
import com.google.android.gms.internal.measurement.zzfl;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfz;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzm extends zzfm {
    zzm(zzfn zzfn) {
        super(zzfn);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x02de  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x02ff  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0338  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x039b  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x03f3  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0447  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x045b  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x046a  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x02c1  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzfr[] zza(java.lang.String r66, com.google.android.gms.internal.measurement.zzft[] r67, com.google.android.gms.internal.measurement.zzfz[] r68) {
        /*
            r65 = this;
            r7 = r65
            r15 = r66
            r13 = r67
            r14 = r68
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r66)
            java.util.HashSet r11 = new java.util.HashSet
            r11.<init>()
            android.support.v4.util.ArrayMap r12 = new android.support.v4.util.ArrayMap
            r12.<init>()
            android.support.v4.util.ArrayMap r10 = new android.support.v4.util.ArrayMap
            r10.<init>()
            android.support.v4.util.ArrayMap r9 = new android.support.v4.util.ArrayMap
            r9.<init>()
            android.support.v4.util.ArrayMap r8 = new android.support.v4.util.ArrayMap
            r8.<init>()
            android.support.v4.util.ArrayMap r6 = new android.support.v4.util.ArrayMap
            r6.<init>()
            com.google.android.gms.measurement.internal.zzq r0 = r65.zzgv()
            boolean r23 = r0.zzbb(r15)
            com.google.android.gms.measurement.internal.zzt r0 = r65.zzjt()
            java.util.Map r0 = r0.zzbp(r15)
            if (r0 == 0) goto L_0x018e
            java.util.Set r1 = r0.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0043:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0185
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzfx r4 = (com.google.android.gms.internal.measurement.zzfx) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            java.lang.Object r5 = r10.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            java.lang.Object r3 = r9.get(r3)
            java.util.BitSet r3 = (java.util.BitSet) r3
            if (r23 == 0) goto L_0x00b3
            r19 = r0
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            if (r4 == 0) goto L_0x00a5
            r20 = r1
            com.google.android.gms.internal.measurement.zzfs[] r1 = r4.zzayr
            if (r1 != 0) goto L_0x0083
            goto L_0x00a7
        L_0x0083:
            com.google.android.gms.internal.measurement.zzfs[] r1 = r4.zzayr
            r21 = r3
            int r3 = r1.length
            r22 = r11
            r11 = 0
        L_0x008b:
            if (r11 >= r3) goto L_0x00ab
            r24 = r3
            r3 = r1[r11]
            r25 = r1
            java.lang.Integer r1 = r3.zzawz
            if (r1 == 0) goto L_0x009e
            java.lang.Integer r1 = r3.zzawz
            java.lang.Long r3 = r3.zzaxa
            r0.put(r1, r3)
        L_0x009e:
            int r11 = r11 + 1
            r3 = r24
            r1 = r25
            goto L_0x008b
        L_0x00a5:
            r20 = r1
        L_0x00a7:
            r21 = r3
            r22 = r11
        L_0x00ab:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r8.put(r1, r0)
            goto L_0x00bc
        L_0x00b3:
            r19 = r0
            r20 = r1
            r21 = r3
            r22 = r11
            r0 = 0
        L_0x00bc:
            if (r5 != 0) goto L_0x00d7
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r10.put(r1, r5)
            java.util.BitSet r3 = new java.util.BitSet
            r3.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r9.put(r1, r3)
            goto L_0x00d9
        L_0x00d7:
            r3 = r21
        L_0x00d9:
            r1 = 0
        L_0x00da:
            long[] r11 = r4.zzayp
            int r11 = r11.length
            int r11 = r11 << 6
            if (r1 >= r11) goto L_0x012f
            long[] r11 = r4.zzayp
            boolean r11 = com.google.android.gms.measurement.internal.zzft.zza(r11, r1)
            if (r11 == 0) goto L_0x0114
            com.google.android.gms.measurement.internal.zzas r11 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r11 = r11.zzjo()
            r21 = r8
            java.lang.String r8 = "Filter already evaluated. audience ID, filter ID"
            r24 = r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r2)
            r25 = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r1)
            r11.zze(r8, r9, r10)
            r3.set(r1)
            long[] r8 = r4.zzayq
            boolean r8 = com.google.android.gms.measurement.internal.zzft.zza(r8, r1)
            if (r8 == 0) goto L_0x011a
            r5.set(r1)
            r8 = 1
            goto L_0x011b
        L_0x0114:
            r21 = r8
            r24 = r9
            r25 = r10
        L_0x011a:
            r8 = 0
        L_0x011b:
            if (r0 == 0) goto L_0x0126
            if (r8 != 0) goto L_0x0126
            java.lang.Integer r8 = java.lang.Integer.valueOf(r1)
            r0.remove(r8)
        L_0x0126:
            int r1 = r1 + 1
            r8 = r21
            r9 = r24
            r10 = r25
            goto L_0x00da
        L_0x012f:
            r21 = r8
            r24 = r9
            r25 = r10
            com.google.android.gms.internal.measurement.zzfr r1 = new com.google.android.gms.internal.measurement.zzfr
            r1.<init>()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            r12.put(r8, r1)
            r8 = 0
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r8)
            r1.zzawx = r9
            r1.zzaww = r4
            com.google.android.gms.internal.measurement.zzfx r4 = new com.google.android.gms.internal.measurement.zzfx
            r4.<init>()
            r1.zzawv = r4
            com.google.android.gms.internal.measurement.zzfx r4 = r1.zzawv
            long[] r5 = com.google.android.gms.measurement.internal.zzft.zza(r5)
            r4.zzayq = r5
            com.google.android.gms.internal.measurement.zzfx r4 = r1.zzawv
            long[] r3 = com.google.android.gms.measurement.internal.zzft.zza(r3)
            r4.zzayp = r3
            if (r23 == 0) goto L_0x0177
            com.google.android.gms.internal.measurement.zzfx r1 = r1.zzawv
            com.google.android.gms.internal.measurement.zzfs[] r0 = zzb(r0)
            r1.zzayr = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap
            r1.<init>()
            r6.put(r0, r1)
        L_0x0177:
            r0 = r19
            r1 = r20
            r8 = r21
            r11 = r22
            r9 = r24
            r10 = r25
            goto L_0x0043
        L_0x0185:
            r21 = r8
            r24 = r9
            r25 = r10
            r22 = r11
            goto L_0x0196
        L_0x018e:
            r21 = r8
            r24 = r9
            r25 = r10
            r22 = r11
        L_0x0196:
            if (r13 == 0) goto L_0x085b
            android.support.v4.util.ArrayMap r9 = new android.support.v4.util.ArrayMap
            r9.<init>()
            int r8 = r13.length
            r26 = 0
            r2 = r26
            r0 = 0
            r1 = 0
            r4 = 0
        L_0x01a5:
            if (r4 >= r8) goto L_0x084a
            r5 = r13[r4]
            java.lang.String r10 = r5.name
            com.google.android.gms.internal.measurement.zzfu[] r11 = r5.zzaxc
            r28 = r2
            com.google.android.gms.measurement.internal.zzq r2 = r65.zzgv()
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzai.zzaki
            boolean r2 = r2.zzd(r15, r3)
            r30 = 1
            if (r2 == 0) goto L_0x037f
            r65.zzjr()
            java.lang.String r2 = "_eid"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzft.zzb(r5, r2)
            r3 = r2
            java.lang.Long r3 = (java.lang.Long) r3
            if (r3 == 0) goto L_0x01cd
            r2 = 1
            goto L_0x01ce
        L_0x01cd:
            r2 = 0
        L_0x01ce:
            if (r2 == 0) goto L_0x01dc
            r32 = r4
            java.lang.String r4 = "_ep"
            boolean r4 = r10.equals(r4)
            if (r4 == 0) goto L_0x01de
            r4 = 1
            goto L_0x01df
        L_0x01dc:
            r32 = r4
        L_0x01de:
            r4 = 0
        L_0x01df:
            if (r4 == 0) goto L_0x0338
            r65.zzjr()
            java.lang.String r2 = "_en"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzft.zzb(r5, r2)
            r10 = r2
            java.lang.String r10 = (java.lang.String) r10
            boolean r2 = android.text.TextUtils.isEmpty(r10)
            if (r2 == 0) goto L_0x0206
            com.google.android.gms.measurement.internal.zzas r2 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjg()
            java.lang.String r4 = "Extra parameter without an event name. eventId"
            r2.zzg(r4, r3)
            r37 = r6
            r34 = 1
            goto L_0x0326
        L_0x0206:
            if (r0 == 0) goto L_0x021b
            if (r1 == 0) goto L_0x021b
            long r33 = r3.longValue()
            long r35 = r1.longValue()
            int r2 = (r33 > r35 ? 1 : (r33 == r35 ? 0 : -1))
            if (r2 == 0) goto L_0x0217
            goto L_0x021b
        L_0x0217:
            r4 = r0
            r33 = r1
            goto L_0x0247
        L_0x021b:
            com.google.android.gms.measurement.internal.zzt r2 = r65.zzjt()
            android.util.Pair r2 = r2.zza(r15, r3)
            if (r2 == 0) goto L_0x0315
            java.lang.Object r4 = r2.first
            if (r4 != 0) goto L_0x022b
            goto L_0x0315
        L_0x022b:
            java.lang.Object r0 = r2.first
            com.google.android.gms.internal.measurement.zzft r0 = (com.google.android.gms.internal.measurement.zzft) r0
            java.lang.Object r1 = r2.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            r65.zzjr()
            java.lang.String r4 = "_eid"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzft.zzb(r0, r4)
            java.lang.Long r4 = (java.lang.Long) r4
            r28 = r1
            r33 = r4
            r4 = r0
        L_0x0247:
            long r28 = r28 - r30
            int r0 = (r28 > r26 ? 1 : (r28 == r26 ? 0 : -1))
            if (r0 > 0) goto L_0x029a
            com.google.android.gms.measurement.internal.zzt r1 = r65.zzjt()
            r1.zzaf()
            com.google.android.gms.measurement.internal.zzas r0 = r1.zzgt()
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjo()
            java.lang.String r2 = "Clearing complex main event info. appId"
            r0.zzg(r2, r15)
            android.database.sqlite.SQLiteDatabase r0 = r1.getWritableDatabase()     // Catch:{ SQLiteException -> 0x027f }
            java.lang.String r2 = "delete from main_event_params where app_id=?"
            r17 = r4
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x027d }
            r18 = 0
            r4[r18] = r15     // Catch:{ SQLiteException -> 0x027b }
            r0.execSQL(r2, r4)     // Catch:{ SQLiteException -> 0x027b }
            r13 = r5
            r37 = r6
            r1 = r17
            r34 = 1
            goto L_0x02b3
        L_0x027b:
            r0 = move-exception
            goto L_0x0285
        L_0x027d:
            r0 = move-exception
            goto L_0x0283
        L_0x027f:
            r0 = move-exception
            r17 = r4
            r3 = 1
        L_0x0283:
            r18 = 0
        L_0x0285:
            com.google.android.gms.measurement.internal.zzas r1 = r1.zzgt()
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()
            java.lang.String r2 = "Error clearing complex main event"
            r1.zzg(r2, r0)
            r13 = r5
            r37 = r6
            r1 = r17
            r34 = 1
            goto L_0x02b3
        L_0x029a:
            r17 = r4
            r4 = 1
            r18 = 0
            com.google.android.gms.measurement.internal.zzt r1 = r65.zzjt()
            r2 = r66
            r13 = r5
            r34 = 1
            r4 = r28
            r37 = r6
            r6 = r17
            r1.zza(r2, r3, r4, r6)
            r1 = r17
        L_0x02b3:
            com.google.android.gms.internal.measurement.zzfu[] r0 = r1.zzaxc
            int r0 = r0.length
            int r2 = r11.length
            int r0 = r0 + r2
            com.google.android.gms.internal.measurement.zzfu[] r0 = new com.google.android.gms.internal.measurement.zzfu[r0]
            com.google.android.gms.internal.measurement.zzfu[] r2 = r1.zzaxc
            int r3 = r2.length
            r4 = 0
            r5 = 0
        L_0x02bf:
            if (r4 >= r3) goto L_0x02da
            r6 = r2[r4]
            r65.zzjr()
            r17 = r1
            java.lang.String r1 = r6.name
            com.google.android.gms.internal.measurement.zzfu r1 = com.google.android.gms.measurement.internal.zzft.zza(r13, r1)
            if (r1 != 0) goto L_0x02d5
            int r1 = r5 + 1
            r0[r5] = r6
            r5 = r1
        L_0x02d5:
            int r4 = r4 + 1
            r1 = r17
            goto L_0x02bf
        L_0x02da:
            r17 = r1
            if (r5 <= 0) goto L_0x02ff
            int r1 = r11.length
            r2 = 0
        L_0x02e0:
            if (r2 >= r1) goto L_0x02ec
            r3 = r11[r2]
            int r4 = r5 + 1
            r0[r5] = r3
            int r2 = r2 + 1
            r5 = r4
            goto L_0x02e0
        L_0x02ec:
            int r1 = r0.length
            if (r5 != r1) goto L_0x02f0
            goto L_0x02f6
        L_0x02f0:
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r5)
            com.google.android.gms.internal.measurement.zzfu[] r0 = (com.google.android.gms.internal.measurement.zzfu[]) r0
        L_0x02f6:
            r35 = r28
            r29 = r0
            r0 = r10
            r28 = r17
            goto L_0x038f
        L_0x02ff:
            com.google.android.gms.measurement.internal.zzas r0 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjj()
            java.lang.String r1 = "No unique parameters in main event. eventName"
            r0.zzg(r1, r10)
            r0 = r10
            r35 = r28
            r29 = r11
            r28 = r17
            goto L_0x038f
        L_0x0315:
            r37 = r6
            r34 = 1
            com.google.android.gms.measurement.internal.zzas r2 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjg()
            java.lang.String r4 = "Extra parameter without existing main event. eventName, eventId"
            r2.zze(r4, r10, r3)
        L_0x0326:
            r41 = r8
            r43 = r9
            r46 = r12
            r47 = r21
            r14 = r22
            r42 = r24
            r63 = r25
            r2 = r28
            goto L_0x082e
        L_0x0338:
            r13 = r5
            r37 = r6
            r34 = 1
            if (r2 == 0) goto L_0x0386
            r65.zzjr()
            java.lang.String r0 = "_epc"
            java.lang.Long r1 = java.lang.Long.valueOf(r26)
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzft.zzb(r13, r0)
            if (r0 != 0) goto L_0x034f
            r0 = r1
        L_0x034f:
            java.lang.Long r0 = (java.lang.Long) r0
            long r16 = r0.longValue()
            int r0 = (r16 > r26 ? 1 : (r16 == r26 ? 0 : -1))
            if (r0 > 0) goto L_0x0368
            com.google.android.gms.measurement.internal.zzas r0 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjj()
            java.lang.String r1 = "Complex event with zero extra param count. eventName"
            r0.zzg(r1, r10)
            r0 = r3
            goto L_0x0375
        L_0x0368:
            com.google.android.gms.measurement.internal.zzt r1 = r65.zzjt()
            r2 = r66
            r0 = r3
            r4 = r16
            r6 = r13
            r1.zza(r2, r3, r4, r6)
        L_0x0375:
            r33 = r0
            r0 = r10
            r29 = r11
            r28 = r13
            r35 = r16
            goto L_0x038f
        L_0x037f:
            r32 = r4
            r13 = r5
            r37 = r6
            r34 = 1
        L_0x0386:
            r33 = r1
            r35 = r28
            r28 = r0
            r0 = r10
            r29 = r11
        L_0x038f:
            com.google.android.gms.measurement.internal.zzt r1 = r65.zzjt()
            java.lang.String r2 = r13.name
            com.google.android.gms.measurement.internal.zzac r1 = r1.zzg(r15, r2)
            if (r1 != 0) goto L_0x03f3
            com.google.android.gms.measurement.internal.zzas r1 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjj()
            java.lang.String r2 = "Event aggregate wasn't created during raw event logging. appId, event"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzas.zzbw(r66)
            com.google.android.gms.measurement.internal.zzaq r4 = r65.zzgq()
            java.lang.String r4 = r4.zzbt(r0)
            r1.zze(r2, r3, r4)
            com.google.android.gms.measurement.internal.zzac r1 = new com.google.android.gms.measurement.internal.zzac
            java.lang.String r10 = r13.name
            r2 = 1
            r4 = 1
            java.lang.Long r6 = r13.zzaxd
            long r16 = r6.longValue()
            r30 = 0
            r6 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = r8
            r11 = r21
            r8 = r1
            r43 = r9
            r42 = r24
            r9 = r66
            r44 = r25
            r47 = r11
            r46 = r12
            r45 = r22
            r11 = r2
            r2 = r13
            r3 = r14
            r13 = r4
            r5 = r15
            r15 = r16
            r17 = r30
            r19 = r6
            r20 = r38
            r21 = r39
            r22 = r40
            r8.<init>(r9, r10, r11, r13, r15, r17, r19, r20, r21, r22)
            goto L_0x0434
        L_0x03f3:
            r41 = r8
            r43 = r9
            r46 = r12
            r2 = r13
            r3 = r14
            r5 = r15
            r47 = r21
            r45 = r22
            r42 = r24
            r44 = r25
            com.google.android.gms.measurement.internal.zzac r4 = new com.google.android.gms.measurement.internal.zzac
            java.lang.String r6 = r1.zztt
            java.lang.String r8 = r1.name
            long r9 = r1.zzahv
            long r51 = r9 + r30
            long r9 = r1.zzahw
            long r53 = r9 + r30
            long r9 = r1.zzahx
            long r11 = r1.zzahy
            java.lang.Long r13 = r1.zzahz
            java.lang.Long r14 = r1.zzaia
            java.lang.Long r15 = r1.zzaib
            java.lang.Boolean r1 = r1.zzaic
            r48 = r4
            r49 = r6
            r50 = r8
            r55 = r9
            r57 = r11
            r59 = r13
            r60 = r14
            r61 = r15
            r62 = r1
            r48.<init>(r49, r50, r51, r53, r55, r57, r59, r60, r61, r62)
            r1 = r4
        L_0x0434:
            com.google.android.gms.measurement.internal.zzt r4 = r65.zzjt()
            r4.zza(r1)
            long r8 = r1.zzahv
            r10 = r43
            java.lang.Object r1 = r10.get(r0)
            java.util.Map r1 = (java.util.Map) r1
            if (r1 != 0) goto L_0x045b
            com.google.android.gms.measurement.internal.zzt r1 = r65.zzjt()
            java.util.Map r1 = r1.zzl(r5, r0)
            if (r1 != 0) goto L_0x0456
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap
            r1.<init>()
        L_0x0456:
            r10.put(r0, r1)
            r11 = r1
            goto L_0x045c
        L_0x045b:
            r11 = r1
        L_0x045c:
            java.util.Set r1 = r11.keySet()
            java.util.Iterator r12 = r1.iterator()
        L_0x0464:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x0822
            java.lang.Object r1 = r12.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r13 = r1.intValue()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r14 = r45
            boolean r1 = r14.contains(r1)
            if (r1 == 0) goto L_0x0494
            com.google.android.gms.measurement.internal.zzas r1 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjo()
            java.lang.String r4 = "Skipping failed audience ID"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r13)
            r1.zzg(r4, r6)
            r45 = r14
            goto L_0x0464
        L_0x0494:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r15 = r46
            java.lang.Object r1 = r15.get(r1)
            com.google.android.gms.internal.measurement.zzfr r1 = (com.google.android.gms.internal.measurement.zzfr) r1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r6 = r44
            java.lang.Object r4 = r6.get(r4)
            java.util.BitSet r4 = (java.util.BitSet) r4
            r16 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)
            r43 = r10
            r10 = r42
            java.lang.Object r2 = r10.get(r2)
            java.util.BitSet r2 = (java.util.BitSet) r2
            if (r23 == 0) goto L_0x04dd
            r17 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)
            r18 = r12
            r12 = r47
            java.lang.Object r2 = r12.get(r2)
            java.util.Map r2 = (java.util.Map) r2
            r19 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)
            r7 = r37
            java.lang.Object r2 = r7.get(r2)
            java.util.Map r2 = (java.util.Map) r2
            goto L_0x04e8
        L_0x04dd:
            r17 = r2
            r18 = r12
            r7 = r37
            r12 = r47
            r2 = 0
            r19 = 0
        L_0x04e8:
            if (r1 != 0) goto L_0x0547
            com.google.android.gms.internal.measurement.zzfr r1 = new com.google.android.gms.internal.measurement.zzfr
            r1.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r15.put(r4, r1)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r34)
            r1.zzawx = r4
            java.util.BitSet r4 = new java.util.BitSet
            r4.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r6.put(r1, r4)
            java.util.BitSet r1 = new java.util.BitSet
            r1.<init>()
            r20 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)
            r10.put(r2, r1)
            if (r23 == 0) goto L_0x053c
            android.support.v4.util.ArrayMap r2 = new android.support.v4.util.ArrayMap
            r2.<init>()
            r17 = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r12.put(r1, r2)
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap
            r1.<init>()
            r19 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)
            r7.put(r2, r1)
            r37 = r7
            r2 = r17
            r17 = r19
            r7 = r1
            goto L_0x0551
        L_0x053c:
            r17 = r1
            r37 = r7
            r2 = r17
            r17 = r19
            r7 = r20
            goto L_0x0551
        L_0x0547:
            r20 = r2
            r37 = r7
            r2 = r17
            r17 = r19
            r7 = r20
        L_0x0551:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            java.lang.Object r1 = r11.get(r1)
            java.util.List r1 = (java.util.List) r1
            java.util.Iterator r19 = r1.iterator()
        L_0x055f:
            boolean r1 = r19.hasNext()
            if (r1 == 0) goto L_0x0809
            java.lang.Object r1 = r19.next()
            com.google.android.gms.internal.measurement.zzfj r1 = (com.google.android.gms.internal.measurement.zzfj) r1
            r20 = r2
            com.google.android.gms.measurement.internal.zzas r2 = r65.zzgt()
            r21 = r11
            r11 = 2
            boolean r2 = r2.isLoggable(r11)
            if (r2 == 0) goto L_0x05b1
            com.google.android.gms.measurement.internal.zzas r2 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjo()
            java.lang.String r11 = "Evaluating filter. audience, filter, event"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r13)
            java.lang.Integer r5 = r1.zzavm
            r44 = r6
            com.google.android.gms.measurement.internal.zzaq r6 = r65.zzgq()
            r47 = r12
            java.lang.String r12 = r1.zzavn
            java.lang.String r6 = r6.zzbt(r12)
            r2.zzd(r11, r3, r5, r6)
            com.google.android.gms.measurement.internal.zzas r2 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjo()
            java.lang.String r3 = "Filter definition"
            com.google.android.gms.measurement.internal.zzft r5 = r65.zzjr()
            java.lang.String r5 = r5.zza(r1)
            r2.zzg(r3, r5)
            goto L_0x05b5
        L_0x05b1:
            r44 = r6
            r47 = r12
        L_0x05b5:
            java.lang.Integer r2 = r1.zzavm
            if (r2 == 0) goto L_0x07ca
            java.lang.Integer r2 = r1.zzavm
            int r2 = r2.intValue()
            r11 = 256(0x100, float:3.59E-43)
            if (r2 <= r11) goto L_0x05c5
            goto L_0x07ca
        L_0x05c5:
            if (r23 == 0) goto L_0x0713
            if (r1 == 0) goto L_0x05d7
            java.lang.Boolean r2 = r1.zzavj
            if (r2 == 0) goto L_0x05d7
            java.lang.Boolean r2 = r1.zzavj
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x05d7
            r12 = 1
            goto L_0x05d8
        L_0x05d7:
            r12 = 0
        L_0x05d8:
            if (r1 == 0) goto L_0x05e9
            java.lang.Boolean r2 = r1.zzavk
            if (r2 == 0) goto L_0x05e9
            java.lang.Boolean r2 = r1.zzavk
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x05e9
            r22 = 1
            goto L_0x05eb
        L_0x05e9:
            r22 = 0
        L_0x05eb:
            java.lang.Integer r2 = r1.zzavm
            int r2 = r2.intValue()
            boolean r2 = r4.get(r2)
            if (r2 == 0) goto L_0x061c
            if (r12 != 0) goto L_0x061c
            if (r22 != 0) goto L_0x061c
            com.google.android.gms.measurement.internal.zzas r2 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjo()
            java.lang.String r3 = "Event filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r13)
            java.lang.Integer r1 = r1.zzavm
            r2.zze(r3, r5, r1)
            r2 = r20
            r11 = r21
            r6 = r44
            r12 = r47
            r3 = r68
            r5 = r66
            goto L_0x055f
        L_0x061c:
            r5 = r1
            r6 = r17
            r1 = r65
            r3 = r16
            r11 = r20
            r2 = r5
            r42 = r10
            r46 = r15
            r10 = r68
            r15 = r3
            r3 = r0
            r10 = r4
            r4 = r29
            r16 = r0
            r0 = r5
            r64 = r6
            r63 = r44
            r5 = r8
            java.lang.Boolean r1 = r1.zza(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzas r2 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjo()
            java.lang.String r3 = "Event filter result"
            if (r1 != 0) goto L_0x064c
            java.lang.String r4 = "null"
            goto L_0x064d
        L_0x064c:
            r4 = r1
        L_0x064d:
            r2.zzg(r3, r4)
            if (r1 != 0) goto L_0x0671
            java.lang.Integer r0 = java.lang.Integer.valueOf(r13)
            r14.add(r0)
            r4 = r10
            r2 = r11
            r0 = r16
            r11 = r21
            r10 = r42
            r12 = r47
            r6 = r63
            r17 = r64
            r3 = r68
            r5 = r66
            r16 = r15
            r15 = r46
            goto L_0x055f
        L_0x0671:
            java.lang.Integer r2 = r0.zzavm
            int r2 = r2.intValue()
            r11.set(r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x06fb
            java.lang.Integer r1 = r0.zzavm
            int r1 = r1.intValue()
            r10.set(r1)
            if (r12 != 0) goto L_0x068d
            if (r22 == 0) goto L_0x06e3
        L_0x068d:
            java.lang.Long r1 = r15.zzaxd
            if (r1 == 0) goto L_0x06e3
            if (r22 == 0) goto L_0x06ba
            java.lang.Integer r0 = r0.zzavm
            int r0 = r0.intValue()
            java.lang.Long r1 = r15.zzaxd
            long r1 = r1.longValue()
            zzb(r7, r0, r1)
            r4 = r10
            r2 = r11
            r0 = r16
            r11 = r21
            r10 = r42
            r12 = r47
            r6 = r63
            r17 = r64
            r3 = r68
            r5 = r66
            r16 = r15
            r15 = r46
            goto L_0x055f
        L_0x06ba:
            java.lang.Integer r0 = r0.zzavm
            int r0 = r0.intValue()
            java.lang.Long r1 = r15.zzaxd
            long r1 = r1.longValue()
            r12 = r64
            zza(r12, r0, r1)
            r4 = r10
            r2 = r11
            r17 = r12
            r0 = r16
            r11 = r21
            r10 = r42
            r12 = r47
            r6 = r63
            r3 = r68
            r5 = r66
            r16 = r15
            r15 = r46
            goto L_0x055f
        L_0x06e3:
            r4 = r10
            r2 = r11
            r0 = r16
            r11 = r21
            r10 = r42
            r12 = r47
            r6 = r63
            r17 = r64
            r3 = r68
            r5 = r66
            r16 = r15
            r15 = r46
            goto L_0x055f
        L_0x06fb:
            r4 = r10
            r2 = r11
            r0 = r16
            r11 = r21
            r10 = r42
            r12 = r47
            r6 = r63
            r17 = r64
            r3 = r68
            r5 = r66
            r16 = r15
            r15 = r46
            goto L_0x055f
        L_0x0713:
            r42 = r10
            r46 = r15
            r15 = r16
            r12 = r17
            r11 = r20
            r63 = r44
            r16 = r0
            r0 = r1
            r10 = r4
            java.lang.Integer r1 = r0.zzavm
            int r1 = r1.intValue()
            boolean r1 = r10.get(r1)
            if (r1 == 0) goto L_0x075a
            com.google.android.gms.measurement.internal.zzas r1 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjo()
            java.lang.String r2 = "Event filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r13)
            java.lang.Integer r0 = r0.zzavm
            r1.zze(r2, r3, r0)
            r4 = r10
            r2 = r11
            r17 = r12
            r0 = r16
            r11 = r21
            r10 = r42
            r12 = r47
            r6 = r63
            r3 = r68
            r5 = r66
            r16 = r15
            r15 = r46
            goto L_0x055f
        L_0x075a:
            r1 = r65
            r2 = r0
            r3 = r16
            r4 = r29
            r5 = r8
            java.lang.Boolean r1 = r1.zza(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzas r2 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjo()
            java.lang.String r3 = "Event filter result"
            if (r1 != 0) goto L_0x0775
            java.lang.String r4 = "null"
            goto L_0x0776
        L_0x0775:
            r4 = r1
        L_0x0776:
            r2.zzg(r3, r4)
            if (r1 != 0) goto L_0x079a
            java.lang.Integer r0 = java.lang.Integer.valueOf(r13)
            r14.add(r0)
            r4 = r10
            r2 = r11
            r17 = r12
            r0 = r16
            r11 = r21
            r10 = r42
            r12 = r47
            r6 = r63
            r3 = r68
            r5 = r66
            r16 = r15
            r15 = r46
            goto L_0x055f
        L_0x079a:
            java.lang.Integer r2 = r0.zzavm
            int r2 = r2.intValue()
            r11.set(r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x07b2
            java.lang.Integer r0 = r0.zzavm
            int r0 = r0.intValue()
            r10.set(r0)
        L_0x07b2:
            r4 = r10
            r2 = r11
            r17 = r12
            r0 = r16
            r11 = r21
            r10 = r42
            r12 = r47
            r6 = r63
            r3 = r68
            r5 = r66
            r16 = r15
            r15 = r46
            goto L_0x055f
        L_0x07ca:
            r42 = r10
            r46 = r15
            r15 = r16
            r12 = r17
            r11 = r20
            r63 = r44
            r16 = r0
            r0 = r1
            r10 = r4
            com.google.android.gms.measurement.internal.zzas r1 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjj()
            java.lang.String r2 = "Invalid event filter ID. appId, id"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzas.zzbw(r66)
            java.lang.Integer r0 = r0.zzavm
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.zze(r2, r3, r0)
            r4 = r10
            r2 = r11
            r17 = r12
            r0 = r16
            r11 = r21
            r10 = r42
            r12 = r47
            r6 = r63
            r3 = r68
            r5 = r66
            r16 = r15
            r15 = r46
            goto L_0x055f
        L_0x0809:
            r46 = r15
            r15 = r16
            r44 = r6
            r42 = r10
            r47 = r12
            r45 = r14
            r2 = r15
            r12 = r18
            r10 = r43
            r3 = r68
            r5 = r66
            r7 = r65
            goto L_0x0464
        L_0x0822:
            r43 = r10
            r63 = r44
            r14 = r45
            r0 = r28
            r1 = r33
            r2 = r35
        L_0x082e:
            int r4 = r32 + 1
            r13 = r67
            r22 = r14
            r6 = r37
            r8 = r41
            r24 = r42
            r9 = r43
            r12 = r46
            r21 = r47
            r25 = r63
            r7 = r65
            r14 = r68
            r15 = r66
            goto L_0x01a5
        L_0x084a:
            r37 = r6
            r46 = r12
            r47 = r21
            r14 = r22
            r42 = r24
            r63 = r25
            r34 = 1
            r1 = r68
            goto L_0x086b
        L_0x085b:
            r37 = r6
            r46 = r12
            r47 = r21
            r14 = r22
            r42 = r24
            r63 = r25
            r34 = 1
            r1 = r68
        L_0x086b:
            if (r1 == 0) goto L_0x0bf4
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            int r2 = r1.length
            r3 = 0
        L_0x0874:
            if (r3 >= r2) goto L_0x0bed
            r4 = r1[r3]
            java.lang.String r5 = r4.name
            java.lang.Object r5 = r0.get(r5)
            java.util.Map r5 = (java.util.Map) r5
            if (r5 != 0) goto L_0x089b
            com.google.android.gms.measurement.internal.zzt r5 = r65.zzjt()
            java.lang.String r6 = r4.name
            r7 = r66
            java.util.Map r5 = r5.zzm(r7, r6)
            if (r5 != 0) goto L_0x0895
            android.support.v4.util.ArrayMap r5 = new android.support.v4.util.ArrayMap
            r5.<init>()
        L_0x0895:
            java.lang.String r6 = r4.name
            r0.put(r6, r5)
            goto L_0x089d
        L_0x089b:
            r7 = r66
        L_0x089d:
            java.util.Set r6 = r5.keySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x08a5:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x0bdd
            java.lang.Object r8 = r6.next()
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)
            boolean r9 = r14.contains(r9)
            if (r9 == 0) goto L_0x08d1
            com.google.android.gms.measurement.internal.zzas r9 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r9 = r9.zzjo()
            java.lang.String r10 = "Skipping failed audience ID"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r9.zzg(r10, r8)
            goto L_0x08a5
        L_0x08d1:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)
            r10 = r46
            java.lang.Object r9 = r10.get(r9)
            com.google.android.gms.internal.measurement.zzfr r9 = (com.google.android.gms.internal.measurement.zzfr) r9
            java.lang.Integer r11 = java.lang.Integer.valueOf(r8)
            r12 = r63
            java.lang.Object r11 = r12.get(r11)
            java.util.BitSet r11 = (java.util.BitSet) r11
            java.lang.Integer r13 = java.lang.Integer.valueOf(r8)
            r15 = r42
            java.lang.Object r13 = r15.get(r13)
            java.util.BitSet r13 = (java.util.BitSet) r13
            if (r23 == 0) goto L_0x0916
            r67 = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r1 = r47
            java.lang.Object r0 = r1.get(r0)
            java.util.Map r0 = (java.util.Map) r0
            r16 = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r17 = r2
            r2 = r37
            java.lang.Object r0 = r2.get(r0)
            java.util.Map r0 = (java.util.Map) r0
            goto L_0x0921
        L_0x0916:
            r67 = r0
            r17 = r2
            r2 = r37
            r1 = r47
            r0 = 0
            r16 = 0
        L_0x0921:
            if (r9 != 0) goto L_0x0974
            com.google.android.gms.internal.measurement.zzfr r9 = new com.google.android.gms.internal.measurement.zzfr
            r9.<init>()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r8)
            r10.put(r11, r9)
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r34)
            r9.zzawx = r11
            java.util.BitSet r11 = new java.util.BitSet
            r11.<init>()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)
            r12.put(r9, r11)
            java.util.BitSet r13 = new java.util.BitSet
            r13.<init>()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)
            r15.put(r9, r13)
            if (r23 == 0) goto L_0x096e
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)
            r1.put(r9, r0)
            android.support.v4.util.ArrayMap r9 = new android.support.v4.util.ArrayMap
            r9.<init>()
            r16 = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r2.put(r0, r9)
            r37 = r2
            r0 = r16
            goto L_0x0979
        L_0x096e:
            r9 = r0
            r37 = r2
            r0 = r16
            goto L_0x0979
        L_0x0974:
            r9 = r0
            r37 = r2
            r0 = r16
        L_0x0979:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r8)
            java.lang.Object r2 = r5.get(r2)
            java.util.List r2 = (java.util.List) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x0987:
            boolean r16 = r2.hasNext()
            if (r16 == 0) goto L_0x0bc9
            java.lang.Object r16 = r2.next()
            r18 = r2
            r2 = r16
            com.google.android.gms.internal.measurement.zzfm r2 = (com.google.android.gms.internal.measurement.zzfm) r2
            r16 = r5
            com.google.android.gms.measurement.internal.zzas r5 = r65.zzgt()
            r19 = r6
            r6 = 2
            boolean r5 = r5.isLoggable(r6)
            if (r5 == 0) goto L_0x09df
            com.google.android.gms.measurement.internal.zzas r5 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r5 = r5.zzjo()
            java.lang.String r6 = "Evaluating filter. audience, filter, property"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r8)
            r21 = r1
            java.lang.Integer r1 = r2.zzavm
            r42 = r15
            com.google.android.gms.measurement.internal.zzaq r15 = r65.zzgq()
            r46 = r10
            java.lang.String r10 = r2.zzawc
            java.lang.String r10 = r15.zzbv(r10)
            r5.zzd(r6, r7, r1, r10)
            com.google.android.gms.measurement.internal.zzas r1 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjo()
            java.lang.String r5 = "Filter definition"
            com.google.android.gms.measurement.internal.zzft r6 = r65.zzjr()
            java.lang.String r6 = r6.zza(r2)
            r1.zzg(r5, r6)
            goto L_0x09e5
        L_0x09df:
            r21 = r1
            r46 = r10
            r42 = r15
        L_0x09e5:
            java.lang.Integer r1 = r2.zzavm
            if (r1 == 0) goto L_0x0b95
            java.lang.Integer r1 = r2.zzavm
            int r1 = r1.intValue()
            r5 = 256(0x100, float:3.59E-43)
            if (r1 <= r5) goto L_0x09f5
            goto L_0x0b95
        L_0x09f5:
            if (r23 == 0) goto L_0x0b04
            if (r2 == 0) goto L_0x0a07
            java.lang.Boolean r1 = r2.zzavj
            if (r1 == 0) goto L_0x0a07
            java.lang.Boolean r1 = r2.zzavj
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0a07
            r1 = 1
            goto L_0x0a08
        L_0x0a07:
            r1 = 0
        L_0x0a08:
            if (r2 == 0) goto L_0x0a18
            java.lang.Boolean r6 = r2.zzavk
            if (r6 == 0) goto L_0x0a18
            java.lang.Boolean r6 = r2.zzavk
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x0a18
            r6 = 1
            goto L_0x0a19
        L_0x0a18:
            r6 = 0
        L_0x0a19:
            java.lang.Integer r7 = r2.zzavm
            int r7 = r7.intValue()
            boolean r7 = r11.get(r7)
            if (r7 == 0) goto L_0x0a4c
            if (r1 != 0) goto L_0x0a4c
            if (r6 != 0) goto L_0x0a4c
            com.google.android.gms.measurement.internal.zzas r1 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjo()
            java.lang.String r6 = "Property filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r8)
            java.lang.Integer r2 = r2.zzavm
            r1.zze(r6, r7, r2)
            r5 = r16
            r2 = r18
            r6 = r19
            r1 = r21
            r15 = r42
            r10 = r46
            r7 = r66
            goto L_0x0987
        L_0x0a4c:
            r10 = r37
            r7 = r65
            java.lang.Boolean r15 = r7.zza(r2, r4)
            com.google.android.gms.measurement.internal.zzas r20 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r5 = r20.zzjo()
            r37 = r10
            java.lang.String r10 = "Property filter result"
            if (r15 != 0) goto L_0x0a69
            java.lang.String r20 = "null"
            r44 = r12
            r12 = r20
            goto L_0x0a6c
        L_0x0a69:
            r44 = r12
            r12 = r15
        L_0x0a6c:
            r5.zzg(r10, r12)
            if (r15 != 0) goto L_0x0a8a
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
            r14.add(r1)
            r5 = r16
            r2 = r18
            r6 = r19
            r1 = r21
            r15 = r42
            r12 = r44
            r10 = r46
            r7 = r66
            goto L_0x0987
        L_0x0a8a:
            java.lang.Integer r5 = r2.zzavm
            int r5 = r5.intValue()
            r13.set(r5)
            java.lang.Integer r5 = r2.zzavm
            int r5 = r5.intValue()
            boolean r10 = r15.booleanValue()
            r11.set(r5, r10)
            boolean r5 = r15.booleanValue()
            if (r5 == 0) goto L_0x0af2
            if (r1 != 0) goto L_0x0aaa
            if (r6 == 0) goto L_0x0af2
        L_0x0aaa:
            java.lang.Long r1 = r4.zzayw
            if (r1 == 0) goto L_0x0af2
            if (r6 == 0) goto L_0x0ad1
            java.lang.Integer r1 = r2.zzavm
            int r1 = r1.intValue()
            java.lang.Long r2 = r4.zzayw
            long r5 = r2.longValue()
            zzb(r9, r1, r5)
            r5 = r16
            r2 = r18
            r6 = r19
            r1 = r21
            r15 = r42
            r12 = r44
            r10 = r46
            r7 = r66
            goto L_0x0987
        L_0x0ad1:
            java.lang.Integer r1 = r2.zzavm
            int r1 = r1.intValue()
            java.lang.Long r2 = r4.zzayw
            long r5 = r2.longValue()
            zza(r0, r1, r5)
            r5 = r16
            r2 = r18
            r6 = r19
            r1 = r21
            r15 = r42
            r12 = r44
            r10 = r46
            r7 = r66
            goto L_0x0987
        L_0x0af2:
            r5 = r16
            r2 = r18
            r6 = r19
            r1 = r21
            r15 = r42
            r12 = r44
            r10 = r46
            r7 = r66
            goto L_0x0987
        L_0x0b04:
            r44 = r12
            r7 = r65
            java.lang.Integer r1 = r2.zzavm
            int r1 = r1.intValue()
            boolean r1 = r11.get(r1)
            if (r1 == 0) goto L_0x0b39
            com.google.android.gms.measurement.internal.zzas r1 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjo()
            java.lang.String r5 = "Property filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r8)
            java.lang.Integer r2 = r2.zzavm
            r1.zze(r5, r6, r2)
            r5 = r16
            r2 = r18
            r6 = r19
            r1 = r21
            r15 = r42
            r12 = r44
            r10 = r46
            r7 = r66
            goto L_0x0987
        L_0x0b39:
            java.lang.Boolean r1 = r7.zza(r2, r4)
            com.google.android.gms.measurement.internal.zzas r5 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r5 = r5.zzjo()
            java.lang.String r6 = "Property filter result"
            if (r1 != 0) goto L_0x0b4c
            java.lang.String r10 = "null"
            goto L_0x0b4d
        L_0x0b4c:
            r10 = r1
        L_0x0b4d:
            r5.zzg(r6, r10)
            if (r1 != 0) goto L_0x0b6b
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
            r14.add(r1)
            r5 = r16
            r2 = r18
            r6 = r19
            r1 = r21
            r15 = r42
            r12 = r44
            r10 = r46
            r7 = r66
            goto L_0x0987
        L_0x0b6b:
            java.lang.Integer r5 = r2.zzavm
            int r5 = r5.intValue()
            r13.set(r5)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0b83
            java.lang.Integer r1 = r2.zzavm
            int r1 = r1.intValue()
            r11.set(r1)
        L_0x0b83:
            r5 = r16
            r2 = r18
            r6 = r19
            r1 = r21
            r15 = r42
            r12 = r44
            r10 = r46
            r7 = r66
            goto L_0x0987
        L_0x0b95:
            r44 = r12
            r7 = r65
            com.google.android.gms.measurement.internal.zzas r0 = r65.zzgt()
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjj()
            java.lang.String r1 = "Invalid property filter ID. appId, id"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzas.zzbw(r66)
            java.lang.Integer r2 = r2.zzavm
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r0.zze(r1, r5, r2)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r14.add(r0)
            r0 = r67
            r5 = r16
            r2 = r17
            r6 = r19
            r47 = r21
            r63 = r44
            r1 = r68
            r7 = r66
            goto L_0x08a5
        L_0x0bc9:
            r7 = r65
            r0 = r67
            r47 = r1
            r46 = r10
            r63 = r12
            r42 = r15
            r2 = r17
            r1 = r68
            r7 = r66
            goto L_0x08a5
        L_0x0bdd:
            r67 = r0
            r17 = r2
            r21 = r47
            r44 = r63
            r7 = r65
            int r3 = r3 + 1
            r1 = r68
            goto L_0x0874
        L_0x0bed:
            r21 = r47
            r44 = r63
            r7 = r65
            goto L_0x0bfa
        L_0x0bf4:
            r21 = r47
            r44 = r63
            r7 = r65
        L_0x0bfa:
            int r0 = r44.size()
            com.google.android.gms.internal.measurement.zzfr[] r1 = new com.google.android.gms.internal.measurement.zzfr[r0]
            java.util.Set r0 = r44.keySet()
            java.util.Iterator r2 = r0.iterator()
            r3 = 0
        L_0x0c09:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0db8
            java.lang.Object r0 = r2.next()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            boolean r4 = r14.contains(r4)
            if (r4 != 0) goto L_0x0db4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            r5 = r46
            java.lang.Object r4 = r5.get(r4)
            com.google.android.gms.internal.measurement.zzfr r4 = (com.google.android.gms.internal.measurement.zzfr) r4
            if (r4 != 0) goto L_0x0c36
            com.google.android.gms.internal.measurement.zzfr r4 = new com.google.android.gms.internal.measurement.zzfr
            r4.<init>()
        L_0x0c36:
            int r6 = r3 + 1
            r1[r3] = r4
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            r4.zzavg = r3
            com.google.android.gms.internal.measurement.zzfx r3 = new com.google.android.gms.internal.measurement.zzfx
            r3.<init>()
            r4.zzawv = r3
            com.google.android.gms.internal.measurement.zzfx r3 = r4.zzawv
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)
            r9 = r44
            java.lang.Object r8 = r9.get(r8)
            java.util.BitSet r8 = (java.util.BitSet) r8
            long[] r8 = com.google.android.gms.measurement.internal.zzft.zza(r8)
            r3.zzayq = r8
            com.google.android.gms.internal.measurement.zzfx r3 = r4.zzawv
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)
            r10 = r42
            java.lang.Object r8 = r10.get(r8)
            java.util.BitSet r8 = (java.util.BitSet) r8
            long[] r8 = com.google.android.gms.measurement.internal.zzft.zza(r8)
            r3.zzayp = r8
            if (r23 == 0) goto L_0x0d0f
            com.google.android.gms.internal.measurement.zzfx r3 = r4.zzawv
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)
            r11 = r21
            java.lang.Object r8 = r11.get(r8)
            java.util.Map r8 = (java.util.Map) r8
            com.google.android.gms.internal.measurement.zzfs[] r8 = zzb(r8)
            r3.zzayr = r8
            com.google.android.gms.internal.measurement.zzfx r3 = r4.zzawv
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)
            r12 = r37
            java.lang.Object r8 = r12.get(r8)
            java.util.Map r8 = (java.util.Map) r8
            if (r8 != 0) goto L_0x0c9f
            r13 = 0
            com.google.android.gms.internal.measurement.zzfy[] r8 = new com.google.android.gms.internal.measurement.zzfy[r13]
            r67 = r2
            r46 = r5
            r15 = r8
            goto L_0x0d0c
        L_0x0c9f:
            r13 = 0
            int r15 = r8.size()
            com.google.android.gms.internal.measurement.zzfy[] r15 = new com.google.android.gms.internal.measurement.zzfy[r15]
            java.util.Set r16 = r8.keySet()
            java.util.Iterator r16 = r16.iterator()
            r17 = 0
        L_0x0cb0:
            boolean r18 = r16.hasNext()
            if (r18 == 0) goto L_0x0d08
            java.lang.Object r18 = r16.next()
            r13 = r18
            java.lang.Integer r13 = (java.lang.Integer) r13
            r67 = r2
            com.google.android.gms.internal.measurement.zzfy r2 = new com.google.android.gms.internal.measurement.zzfy
            r2.<init>()
            r2.zzawz = r13
            java.lang.Object r13 = r8.get(r13)
            java.util.List r13 = (java.util.List) r13
            if (r13 == 0) goto L_0x0cfa
            java.util.Collections.sort(r13)
            r46 = r5
            int r5 = r13.size()
            long[] r5 = new long[r5]
            java.util.Iterator r13 = r13.iterator()
            r18 = 0
        L_0x0ce0:
            boolean r19 = r13.hasNext()
            if (r19 == 0) goto L_0x0cf7
            java.lang.Object r19 = r13.next()
            java.lang.Long r19 = (java.lang.Long) r19
            int r20 = r18 + 1
            long r21 = r19.longValue()
            r5[r18] = r21
            r18 = r20
            goto L_0x0ce0
        L_0x0cf7:
            r2.zzayu = r5
            goto L_0x0cfc
        L_0x0cfa:
            r46 = r5
        L_0x0cfc:
            int r5 = r17 + 1
            r15[r17] = r2
            r2 = r67
            r17 = r5
            r5 = r46
            r13 = 0
            goto L_0x0cb0
        L_0x0d08:
            r67 = r2
            r46 = r5
        L_0x0d0c:
            r3.zzays = r15
            goto L_0x0d17
        L_0x0d0f:
            r67 = r2
            r46 = r5
            r11 = r21
            r12 = r37
        L_0x0d17:
            com.google.android.gms.measurement.internal.zzt r2 = r65.zzjt()
            com.google.android.gms.internal.measurement.zzfx r3 = r4.zzawv
            r2.zzcl()
            r2.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r66)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            int r4 = r3.zzvx()     // Catch:{ IOException -> 0x0d91 }
            byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x0d91 }
            int r5 = r4.length     // Catch:{ IOException -> 0x0d91 }
            r8 = 0
            com.google.android.gms.internal.measurement.zzya r5 = com.google.android.gms.internal.measurement.zzya.zzk(r4, r8, r5)     // Catch:{ IOException -> 0x0d8f }
            r3.zza(r5)     // Catch:{ IOException -> 0x0d8f }
            r5.zzza()     // Catch:{ IOException -> 0x0d8f }
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r5 = "app_id"
            r13 = r66
            r3.put(r5, r13)
            java.lang.String r5 = "audience_id"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3.put(r5, r0)
            java.lang.String r0 = "current_results"
            r3.put(r0, r4)
            android.database.sqlite.SQLiteDatabase r0 = r2.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0d7b }
            java.lang.String r4 = "audience_filter_values"
            r5 = 5
            r15 = 0
            long r3 = r0.insertWithOnConflict(r4, r15, r3, r5)     // Catch:{ SQLiteException -> 0x0d79 }
            r16 = -1
            int r0 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            if (r0 != 0) goto L_0x0da7
            com.google.android.gms.measurement.internal.zzas r0 = r2.zzgt()     // Catch:{ SQLiteException -> 0x0d79 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ SQLiteException -> 0x0d79 }
            java.lang.String r3 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzas.zzbw(r66)     // Catch:{ SQLiteException -> 0x0d79 }
            r0.zzg(r3, r4)     // Catch:{ SQLiteException -> 0x0d79 }
            goto L_0x0da7
        L_0x0d79:
            r0 = move-exception
            goto L_0x0d7d
        L_0x0d7b:
            r0 = move-exception
            r15 = 0
        L_0x0d7d:
            com.google.android.gms.measurement.internal.zzas r2 = r2.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjg()
            java.lang.String r3 = "Error storing filter results. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzas.zzbw(r66)
            r2.zze(r3, r4, r0)
            goto L_0x0da7
        L_0x0d8f:
            r0 = move-exception
            goto L_0x0d93
        L_0x0d91:
            r0 = move-exception
            r8 = 0
        L_0x0d93:
            r13 = r66
            r15 = 0
            com.google.android.gms.measurement.internal.zzas r2 = r2.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjg()
            java.lang.String r3 = "Configuration loss. Failed to serialize filter results. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzas.zzbw(r66)
            r2.zze(r3, r4, r0)
        L_0x0da7:
            r2 = r67
            r3 = r6
            r44 = r9
            r42 = r10
            r21 = r11
            r37 = r12
            goto L_0x0c09
        L_0x0db4:
            r13 = r66
            goto L_0x0c09
        L_0x0db8:
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r1, r3)
            com.google.android.gms.internal.measurement.zzfr[] r0 = (com.google.android.gms.internal.measurement.zzfr[]) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzm.zza(java.lang.String, com.google.android.gms.internal.measurement.zzft[], com.google.android.gms.internal.measurement.zzfz[]):com.google.android.gms.internal.measurement.zzfr[]");
    }

    private final Boolean zza(zzfj zzfj, String str, zzfu[] zzfuArr, long j) {
        zzfk[] zzfkArr;
        zzfk[] zzfkArr2;
        Boolean bool;
        if (zzfj.zzavq != null) {
            Boolean zza = zza(j, zzfj.zzavq);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        HashSet hashSet = new HashSet();
        for (zzfk zzfk : zzfj.zzavo) {
            if (TextUtils.isEmpty(zzfk.zzavv)) {
                zzgt().zzjj().zzg("null or empty param name in filter. event", zzgq().zzbt(str));
                return null;
            }
            hashSet.add(zzfk.zzavv);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzfu zzfu : zzfuArr) {
            if (hashSet.contains(zzfu.name)) {
                if (zzfu.zzaxg != null) {
                    arrayMap.put(zzfu.name, zzfu.zzaxg);
                } else if (zzfu.zzaup != null) {
                    arrayMap.put(zzfu.name, zzfu.zzaup);
                } else if (zzfu.zzamn != null) {
                    arrayMap.put(zzfu.name, zzfu.zzamn);
                } else {
                    zzgt().zzjj().zze("Unknown value for param. event, param", zzgq().zzbt(str), zzgq().zzbu(zzfu.name));
                    return null;
                }
            }
        }
        for (zzfk zzfk2 : zzfj.zzavo) {
            boolean equals = Boolean.TRUE.equals(zzfk2.zzavu);
            String str2 = zzfk2.zzavv;
            if (TextUtils.isEmpty(str2)) {
                zzgt().zzjj().zzg("Event has empty param name. event", zzgq().zzbt(str));
                return null;
            }
            Object obj = arrayMap.get(str2);
            if (obj instanceof Long) {
                if (zzfk2.zzavt == null) {
                    zzgt().zzjj().zze("No number filter for long param. event, param", zzgq().zzbt(str), zzgq().zzbu(str2));
                    return null;
                }
                Boolean zza2 = zza(((Long) obj).longValue(), zzfk2.zzavt);
                if (zza2 == null) {
                    return null;
                }
                if ((true ^ zza2.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof Double) {
                if (zzfk2.zzavt == null) {
                    zzgt().zzjj().zze("No number filter for double param. event, param", zzgq().zzbt(str), zzgq().zzbu(str2));
                    return null;
                }
                Boolean zza3 = zza(((Double) obj).doubleValue(), zzfk2.zzavt);
                if (zza3 == null) {
                    return null;
                }
                if ((true ^ zza3.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof String) {
                if (zzfk2.zzavs != null) {
                    bool = zza((String) obj, zzfk2.zzavs);
                } else if (zzfk2.zzavt != null) {
                    String str3 = (String) obj;
                    if (zzft.zzcs(str3)) {
                        bool = zza(str3, zzfk2.zzavt);
                    } else {
                        zzgt().zzjj().zze("Invalid param value for number filter. event, param", zzgq().zzbt(str), zzgq().zzbu(str2));
                        return null;
                    }
                } else {
                    zzgt().zzjj().zze("No filter for String param. event, param", zzgq().zzbt(str), zzgq().zzbu(str2));
                    return null;
                }
                if (bool == null) {
                    return null;
                }
                if ((true ^ bool.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj == null) {
                zzgt().zzjo().zze("Missing param for filter. event, param", zzgq().zzbt(str), zzgq().zzbu(str2));
                return Boolean.valueOf(false);
            } else {
                zzgt().zzjj().zze("Unknown param type. event, param", zzgq().zzbt(str), zzgq().zzbu(str2));
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private final Boolean zza(zzfm zzfm, zzfz zzfz) {
        zzfk zzfk = zzfm.zzawd;
        if (zzfk == null) {
            zzgt().zzjj().zzg("Missing property filter. property", zzgq().zzbv(zzfz.name));
            return null;
        }
        boolean equals = Boolean.TRUE.equals(zzfk.zzavu);
        if (zzfz.zzaxg != null) {
            if (zzfk.zzavt != null) {
                return zza(zza(zzfz.zzaxg.longValue(), zzfk.zzavt), equals);
            }
            zzgt().zzjj().zzg("No number filter for long property. property", zzgq().zzbv(zzfz.name));
            return null;
        } else if (zzfz.zzaup != null) {
            if (zzfk.zzavt != null) {
                return zza(zza(zzfz.zzaup.doubleValue(), zzfk.zzavt), equals);
            }
            zzgt().zzjj().zzg("No number filter for double property. property", zzgq().zzbv(zzfz.name));
            return null;
        } else if (zzfz.zzamn == null) {
            zzgt().zzjj().zzg("User property has no value, property", zzgq().zzbv(zzfz.name));
            return null;
        } else if (zzfk.zzavs != null) {
            return zza(zza(zzfz.zzamn, zzfk.zzavs), equals);
        } else {
            if (zzfk.zzavt == null) {
                zzgt().zzjj().zzg("No string or number filter defined. property", zzgq().zzbv(zzfz.name));
            } else if (zzft.zzcs(zzfz.zzamn)) {
                return zza(zza(zzfz.zzamn, zzfk.zzavt), equals);
            } else {
                zzgt().zzjj().zze("Invalid user property value for Numeric number filter. property, value", zzgq().zzbv(zzfz.name), zzfz.zzamn);
            }
            return null;
        }
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzfn zzfn) {
        String str2;
        List list;
        Preconditions.checkNotNull(zzfn);
        if (str == null || zzfn.zzawe == null || zzfn.zzawe.intValue() == 0) {
            return null;
        }
        if (zzfn.zzawe.intValue() == 6) {
            if (zzfn.zzawh == null || zzfn.zzawh.length == 0) {
                return null;
            }
        } else if (zzfn.zzawf == null) {
            return null;
        }
        int intValue = zzfn.zzawe.intValue();
        boolean z = zzfn.zzawg != null && zzfn.zzawg.booleanValue();
        if (z || intValue == 1 || intValue == 6) {
            str2 = zzfn.zzawf;
        } else {
            str2 = zzfn.zzawf.toUpperCase(Locale.ENGLISH);
        }
        if (zzfn.zzawh == null) {
            list = null;
        } else {
            String[] strArr = zzfn.zzawh;
            if (z) {
                list = Arrays.asList(strArr);
            } else {
                ArrayList arrayList = new ArrayList();
                for (String upperCase : strArr) {
                    arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
                }
                list = arrayList;
            }
        }
        return zza(str, intValue, z, str2, list, intValue == 1 ? str2 : null);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    zzgt().zzjj().zzg("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private final Boolean zza(long j, zzfl zzfl) {
        try {
            return zza(new BigDecimal(j), zzfl, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(double d, zzfl zzfl) {
        try {
            return zza(new BigDecimal(d), zzfl, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(String str, zzfl zzfl) {
        if (!zzft.zzcs(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzfl, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0072, code lost:
        if (r3 != null) goto L_0x0074;
     */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r7, com.google.android.gms.internal.measurement.zzfl r8, double r9) {
        /*
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            java.lang.Integer r0 = r8.zzavw
            r1 = 0
            if (r0 == 0) goto L_0x00f0
            java.lang.Integer r0 = r8.zzavw
            int r0 = r0.intValue()
            if (r0 != 0) goto L_0x0012
            goto L_0x00f0
        L_0x0012:
            java.lang.Integer r0 = r8.zzavw
            int r0 = r0.intValue()
            r2 = 4
            if (r0 != r2) goto L_0x0024
            java.lang.String r0 = r8.zzavz
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = r8.zzawa
            if (r0 != 0) goto L_0x0029
        L_0x0023:
            return r1
        L_0x0024:
            java.lang.String r0 = r8.zzavy
            if (r0 != 0) goto L_0x0029
            return r1
        L_0x0029:
            java.lang.Integer r0 = r8.zzavw
            int r0 = r0.intValue()
            java.lang.Integer r3 = r8.zzavw
            int r3 = r3.intValue()
            if (r3 != r2) goto L_0x005b
            java.lang.String r3 = r8.zzavz
            boolean r3 = com.google.android.gms.measurement.internal.zzft.zzcs(r3)
            if (r3 == 0) goto L_0x005a
            java.lang.String r3 = r8.zzawa
            boolean r3 = com.google.android.gms.measurement.internal.zzft.zzcs(r3)
            if (r3 != 0) goto L_0x0048
            goto L_0x005a
        L_0x0048:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.String r4 = r8.zzavz     // Catch:{ NumberFormatException -> 0x0059 }
            r3.<init>(r4)     // Catch:{ NumberFormatException -> 0x0059 }
            java.math.BigDecimal r4 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.String r8 = r8.zzawa     // Catch:{ NumberFormatException -> 0x0059 }
            r4.<init>(r8)     // Catch:{ NumberFormatException -> 0x0059 }
            r8 = r3
            r3 = r1
            goto L_0x006d
        L_0x0059:
            return r1
        L_0x005a:
            return r1
        L_0x005b:
            java.lang.String r3 = r8.zzavy
            boolean r3 = com.google.android.gms.measurement.internal.zzft.zzcs(r3)
            if (r3 != 0) goto L_0x0064
            return r1
        L_0x0064:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x00ef }
            java.lang.String r8 = r8.zzavy     // Catch:{ NumberFormatException -> 0x00ef }
            r3.<init>(r8)     // Catch:{ NumberFormatException -> 0x00ef }
            r8 = r1
            r4 = r8
        L_0x006d:
            if (r0 != r2) goto L_0x0072
            if (r8 != 0) goto L_0x0074
            return r1
        L_0x0072:
            if (r3 == 0) goto L_0x00ee
        L_0x0074:
            r2 = -1
            r5 = 0
            r6 = 1
            switch(r0) {
                case 1: goto L_0x00e2;
                case 2: goto L_0x00d6;
                case 3: goto L_0x008d;
                case 4: goto L_0x007b;
                default: goto L_0x007a;
            }
        L_0x007a:
            goto L_0x00ee
        L_0x007b:
            int r8 = r7.compareTo(r8)
            if (r8 == r2) goto L_0x0088
            int r7 = r7.compareTo(r4)
            if (r7 == r6) goto L_0x0088
            r5 = 1
        L_0x0088:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x008d:
            r0 = 0
            int r8 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r8 == 0) goto L_0x00ca
            java.math.BigDecimal r8 = new java.math.BigDecimal
            r8.<init>(r9)
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r1 = 2
            r0.<init>(r1)
            java.math.BigDecimal r8 = r8.multiply(r0)
            java.math.BigDecimal r8 = r3.subtract(r8)
            int r8 = r7.compareTo(r8)
            if (r8 != r6) goto L_0x00c5
            java.math.BigDecimal r8 = new java.math.BigDecimal
            r8.<init>(r9)
            java.math.BigDecimal r9 = new java.math.BigDecimal
            r9.<init>(r1)
            java.math.BigDecimal r8 = r8.multiply(r9)
            java.math.BigDecimal r8 = r3.add(r8)
            int r7 = r7.compareTo(r8)
            if (r7 != r2) goto L_0x00c5
            r5 = 1
        L_0x00c5:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00ca:
            int r7 = r7.compareTo(r3)
            if (r7 != 0) goto L_0x00d1
            r5 = 1
        L_0x00d1:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00d6:
            int r7 = r7.compareTo(r3)
            if (r7 != r6) goto L_0x00dd
            r5 = 1
        L_0x00dd:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00e2:
            int r7 = r7.compareTo(r3)
            if (r7 != r2) goto L_0x00e9
            r5 = 1
        L_0x00e9:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00ee:
            return r1
        L_0x00ef:
            return r1
        L_0x00f0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzm.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzfl, double):java.lang.Boolean");
    }

    private static zzfs[] zzb(Map<Integer, Long> map) {
        if (map == null) {
            return null;
        }
        int i = 0;
        zzfs[] zzfsArr = new zzfs[map.size()];
        for (Integer num : map.keySet()) {
            zzfs zzfs = new zzfs();
            zzfs.zzawz = num;
            zzfs.zzaxa = (Long) map.get(num);
            int i2 = i + 1;
            zzfsArr[i] = zzfs;
            i = i2;
        }
        return zzfsArr;
    }

    private static void zza(Map<Integer, Long> map, int i, long j) {
        Long l = (Long) map.get(Integer.valueOf(i));
        long j2 = j / 1000;
        if (l == null || j2 > l.longValue()) {
            map.put(Integer.valueOf(i), Long.valueOf(j2));
        }
    }

    private static void zzb(Map<Integer, List<Long>> map, int i, long j) {
        List list = (List) map.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList();
            map.put(Integer.valueOf(i), list);
        }
        list.add(Long.valueOf(j / 1000));
    }
}
