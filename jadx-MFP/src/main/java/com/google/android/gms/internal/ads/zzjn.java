package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public final class zzjn implements zzhz, zzig {
    private static final zzic zzahq = new zzjo();
    private static final int zzasf = zzqe.zzam("qt  ");
    private long zzaan;
    private final zzpx zzahx = new zzpx(zzpu.zzbhi);
    private final zzpx zzahy = new zzpx(4);
    private int zzajm;
    private int zzajn;
    private zzib zzajq;
    private final zzpx zzaqa = new zzpx(16);
    private final Stack<zziw> zzaqc = new Stack<>();
    private int zzaqe;
    private int zzaqf;
    private long zzaqg;
    private int zzaqh;
    private zzpx zzaqi;
    private zzjp[] zzasg;
    private boolean zzash;

    public final void release() {
    }

    public final boolean zzdw() {
        return true;
    }

    public final boolean zza(zzia zzia) throws IOException, InterruptedException {
        return zzjr.zze(zzia);
    }

    public final void zza(zzib zzib) {
        this.zzajq = zzib;
    }

    public final void zzc(long j, long j2) {
        this.zzaqc.clear();
        this.zzaqh = 0;
        this.zzajn = 0;
        this.zzajm = 0;
        if (j == 0) {
            zzei();
            return;
        }
        zzjp[] zzjpArr = this.zzasg;
        if (zzjpArr != null) {
            for (zzjp zzjp : zzjpArr) {
                zzjv zzjv = zzjp.zzasi;
                int zzu = zzjv.zzu(j2);
                if (zzu == -1) {
                    zzu = zzjv.zzv(j2);
                }
                zzjp.zzapm = zzu;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:151:0x0198 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02af A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0006 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0006 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(com.google.android.gms.internal.ads.zzia r25, com.google.android.gms.internal.ads.zzif r26) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
        L_0x0006:
            int r3 = r0.zzaqe
            r5 = 262144(0x40000, double:1.295163E-318)
            r8 = -1
            r9 = 8
            r10 = 1
            switch(r3) {
                case 0: goto L_0x019a;
                case 1: goto L_0x0114;
                case 2: goto L_0x0018;
                default: goto L_0x0012;
            }
        L_0x0012:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0018:
            r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r3 = 0
            r9 = -1
        L_0x001f:
            com.google.android.gms.internal.ads.zzjp[] r14 = r0.zzasg
            int r15 = r14.length
            if (r3 >= r15) goto L_0x003d
            r14 = r14[r3]
            int r15 = r14.zzapm
            com.google.android.gms.internal.ads.zzjv r7 = r14.zzasi
            int r7 = r7.zzapk
            if (r15 == r7) goto L_0x003a
            com.google.android.gms.internal.ads.zzjv r7 = r14.zzasi
            long[] r7 = r7.zzagu
            r14 = r7[r15]
            int r7 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r7 >= 0) goto L_0x003a
            r9 = r3
            r12 = r14
        L_0x003a:
            int r3 = r3 + 1
            goto L_0x001f
        L_0x003d:
            if (r9 != r8) goto L_0x0040
            return r8
        L_0x0040:
            r3 = r14[r9]
            com.google.android.gms.internal.ads.zzii r7 = r3.zzasj
            int r8 = r3.zzapm
            com.google.android.gms.internal.ads.zzjv r9 = r3.zzasi
            long[] r9 = r9.zzagu
            r12 = r9[r8]
            com.google.android.gms.internal.ads.zzjv r9 = r3.zzasi
            int[] r9 = r9.zzagt
            r9 = r9[r8]
            com.google.android.gms.internal.ads.zzjs r14 = r3.zzaqu
            int r14 = r14.zzasm
            if (r14 != r10) goto L_0x005d
            r14 = 8
            long r12 = r12 + r14
            int r9 = r9 + -8
        L_0x005d:
            long r14 = r25.getPosition()
            long r14 = r12 - r14
            int r4 = r0.zzajn
            long r10 = (long) r4
            long r14 = r14 + r10
            r10 = 0
            int r4 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
            if (r4 < 0) goto L_0x0110
            int r4 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r4 < 0) goto L_0x0073
            goto L_0x0110
        L_0x0073:
            int r2 = (int) r14
            r1.zzw(r2)
            com.google.android.gms.internal.ads.zzjs r2 = r3.zzaqu
            int r2 = r2.zzakx
            if (r2 == 0) goto L_0x00d7
            com.google.android.gms.internal.ads.zzpx r2 = r0.zzahy
            byte[] r2 = r2.data
            r4 = 0
            r2[r4] = r4
            r5 = 1
            r2[r5] = r4
            r5 = 2
            r2[r5] = r4
            com.google.android.gms.internal.ads.zzjs r2 = r3.zzaqu
            int r2 = r2.zzakx
            com.google.android.gms.internal.ads.zzjs r4 = r3.zzaqu
            int r4 = r4.zzakx
            r5 = 4
            int r4 = 4 - r4
        L_0x0095:
            int r5 = r0.zzajn
            if (r5 >= r9) goto L_0x00d4
            int r5 = r0.zzajm
            if (r5 != 0) goto L_0x00c4
            com.google.android.gms.internal.ads.zzpx r5 = r0.zzahy
            byte[] r5 = r5.data
            r1.readFully(r5, r4, r2)
            com.google.android.gms.internal.ads.zzpx r5 = r0.zzahy
            r6 = 0
            r5.setPosition(r6)
            com.google.android.gms.internal.ads.zzpx r5 = r0.zzahy
            int r5 = r5.zzhg()
            r0.zzajm = r5
            com.google.android.gms.internal.ads.zzpx r5 = r0.zzahx
            r5.setPosition(r6)
            com.google.android.gms.internal.ads.zzpx r5 = r0.zzahx
            r10 = 4
            r7.zza(r5, r10)
            int r5 = r0.zzajn
            int r5 = r5 + r10
            r0.zzajn = r5
            int r9 = r9 + r4
            goto L_0x0095
        L_0x00c4:
            r6 = 0
            int r5 = r7.zza(r1, r5, r6)
            int r6 = r0.zzajn
            int r6 = r6 + r5
            r0.zzajn = r6
            int r6 = r0.zzajm
            int r6 = r6 - r5
            r0.zzajm = r6
            goto L_0x0095
        L_0x00d4:
            r20 = r9
            goto L_0x00ef
        L_0x00d7:
            int r2 = r0.zzajn
            if (r2 >= r9) goto L_0x00ed
            int r2 = r9 - r2
            r4 = 0
            int r2 = r7.zza(r1, r2, r4)
            int r4 = r0.zzajn
            int r4 = r4 + r2
            r0.zzajn = r4
            int r4 = r0.zzajm
            int r4 = r4 - r2
            r0.zzajm = r4
            goto L_0x00d7
        L_0x00ed:
            r20 = r9
        L_0x00ef:
            com.google.android.gms.internal.ads.zzjv r1 = r3.zzasi
            long[] r1 = r1.zzatl
            r17 = r1[r8]
            com.google.android.gms.internal.ads.zzjv r1 = r3.zzasi
            int[] r1 = r1.zzapr
            r19 = r1[r8]
            r21 = 0
            r22 = 0
            r16 = r7
            r16.zza(r17, r19, r20, r21, r22)
            int r1 = r3.zzapm
            r4 = 1
            int r1 = r1 + r4
            r3.zzapm = r1
            r1 = 0
            r0.zzajn = r1
            r0.zzajm = r1
            return r1
        L_0x0110:
            r4 = 1
            r2.zzaha = r12
            return r4
        L_0x0114:
            long r3 = r0.zzaqg
            int r7 = r0.zzaqh
            long r7 = (long) r7
            long r3 = r3 - r7
            long r7 = r25.getPosition()
            long r7 = r7 + r3
            com.google.android.gms.internal.ads.zzpx r10 = r0.zzaqi
            if (r10 == 0) goto L_0x0175
            byte[] r5 = r10.data
            int r6 = r0.zzaqh
            int r4 = (int) r3
            r1.readFully(r5, r6, r4)
            int r3 = r0.zzaqf
            int r4 = com.google.android.gms.internal.ads.zziv.zzala
            if (r3 != r4) goto L_0x0158
            com.google.android.gms.internal.ads.zzpx r3 = r0.zzaqi
            r3.setPosition(r9)
            int r4 = r3.readInt()
            int r5 = zzasf
            if (r4 != r5) goto L_0x0140
            r3 = 1
            goto L_0x0155
        L_0x0140:
            r4 = 4
            r3.zzbl(r4)
        L_0x0144:
            int r4 = r3.zzhb()
            if (r4 <= 0) goto L_0x0154
            int r4 = r3.readInt()
            int r5 = zzasf
            if (r4 != r5) goto L_0x0144
            r3 = 1
            goto L_0x0155
        L_0x0154:
            r3 = 0
        L_0x0155:
            r0.zzash = r3
            goto L_0x017d
        L_0x0158:
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r3 = r0.zzaqc
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x017d
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r3 = r0.zzaqc
            java.lang.Object r3 = r3.peek()
            com.google.android.gms.internal.ads.zziw r3 = (com.google.android.gms.internal.ads.zziw) r3
            com.google.android.gms.internal.ads.zzix r4 = new com.google.android.gms.internal.ads.zzix
            int r5 = r0.zzaqf
            com.google.android.gms.internal.ads.zzpx r6 = r0.zzaqi
            r4.<init>(r5, r6)
            r3.zza(r4)
            goto L_0x017d
        L_0x0175:
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 >= 0) goto L_0x017f
            int r4 = (int) r3
            r1.zzw(r4)
        L_0x017d:
            r3 = 0
            goto L_0x0187
        L_0x017f:
            long r5 = r25.getPosition()
            long r5 = r5 + r3
            r2.zzaha = r5
            r3 = 1
        L_0x0187:
            r0.zzt(r7)
            if (r3 == 0) goto L_0x0194
            int r3 = r0.zzaqe
            r4 = 2
            if (r3 == r4) goto L_0x0194
            r23 = 1
            goto L_0x0196
        L_0x0194:
            r23 = 0
        L_0x0196:
            if (r23 == 0) goto L_0x0006
            r3 = 1
            return r3
        L_0x019a:
            r3 = 1
            int r4 = r0.zzaqh
            if (r4 != 0) goto L_0x01c4
            com.google.android.gms.internal.ads.zzpx r4 = r0.zzaqa
            byte[] r4 = r4.data
            r5 = 0
            boolean r4 = r1.zza(r4, r5, r9, r3)
            if (r4 != 0) goto L_0x01ad
            r3 = 0
            goto L_0x02ad
        L_0x01ad:
            r0.zzaqh = r9
            com.google.android.gms.internal.ads.zzpx r3 = r0.zzaqa
            r3.setPosition(r5)
            com.google.android.gms.internal.ads.zzpx r3 = r0.zzaqa
            long r3 = r3.zzhd()
            r0.zzaqg = r3
            com.google.android.gms.internal.ads.zzpx r3 = r0.zzaqa
            int r3 = r3.readInt()
            r0.zzaqf = r3
        L_0x01c4:
            long r3 = r0.zzaqg
            r5 = 1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x01e0
            com.google.android.gms.internal.ads.zzpx r3 = r0.zzaqa
            byte[] r3 = r3.data
            r1.readFully(r3, r9, r9)
            int r3 = r0.zzaqh
            int r3 = r3 + r9
            r0.zzaqh = r3
            com.google.android.gms.internal.ads.zzpx r3 = r0.zzaqa
            long r3 = r3.zzhh()
            r0.zzaqg = r3
        L_0x01e0:
            int r3 = r0.zzaqf
            int r4 = com.google.android.gms.internal.ads.zziv.zzamb
            if (r3 == r4) goto L_0x01fd
            int r4 = com.google.android.gms.internal.ads.zziv.zzamd
            if (r3 == r4) goto L_0x01fd
            int r4 = com.google.android.gms.internal.ads.zziv.zzame
            if (r3 == r4) goto L_0x01fd
            int r4 = com.google.android.gms.internal.ads.zziv.zzamf
            if (r3 == r4) goto L_0x01fd
            int r4 = com.google.android.gms.internal.ads.zziv.zzamg
            if (r3 == r4) goto L_0x01fd
            int r4 = com.google.android.gms.internal.ads.zziv.zzamp
            if (r3 != r4) goto L_0x01fb
            goto L_0x01fd
        L_0x01fb:
            r3 = 0
            goto L_0x01fe
        L_0x01fd:
            r3 = 1
        L_0x01fe:
            if (r3 == 0) goto L_0x022c
            long r3 = r25.getPosition()
            long r5 = r0.zzaqg
            long r3 = r3 + r5
            int r5 = r0.zzaqh
            long r5 = (long) r5
            long r3 = r3 - r5
            java.util.Stack<com.google.android.gms.internal.ads.zziw> r5 = r0.zzaqc
            com.google.android.gms.internal.ads.zziw r6 = new com.google.android.gms.internal.ads.zziw
            int r7 = r0.zzaqf
            r6.<init>(r7, r3)
            r5.add(r6)
            long r5 = r0.zzaqg
            int r7 = r0.zzaqh
            long r9 = (long) r7
            int r7 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r7 != 0) goto L_0x0226
            r0.zzt(r3)
            r3 = 1
            goto L_0x02ad
        L_0x0226:
            r24.zzei()
            r3 = 1
            goto L_0x02ad
        L_0x022c:
            int r3 = r0.zzaqf
            int r4 = com.google.android.gms.internal.ads.zziv.zzamr
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzamc
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzams
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzamt
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzanm
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzann
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzano
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzamq
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzanp
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzanq
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzanr
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzans
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzant
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzamo
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzala
            if (r3 == r4) goto L_0x0271
            int r4 = com.google.android.gms.internal.ads.zziv.zzaoa
            if (r3 != r4) goto L_0x026f
            goto L_0x0271
        L_0x026f:
            r3 = 0
            goto L_0x0272
        L_0x0271:
            r3 = 1
        L_0x0272:
            if (r3 == 0) goto L_0x02a7
            int r3 = r0.zzaqh
            if (r3 != r9) goto L_0x027a
            r3 = 1
            goto L_0x027b
        L_0x027a:
            r3 = 0
        L_0x027b:
            com.google.android.gms.internal.ads.zzpo.checkState(r3)
            long r3 = r0.zzaqg
            r5 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x0289
            r3 = 1
            goto L_0x028a
        L_0x0289:
            r3 = 0
        L_0x028a:
            com.google.android.gms.internal.ads.zzpo.checkState(r3)
            com.google.android.gms.internal.ads.zzpx r3 = new com.google.android.gms.internal.ads.zzpx
            long r4 = r0.zzaqg
            int r5 = (int) r4
            r3.<init>(r5)
            r0.zzaqi = r3
            com.google.android.gms.internal.ads.zzpx r3 = r0.zzaqa
            byte[] r3 = r3.data
            com.google.android.gms.internal.ads.zzpx r4 = r0.zzaqi
            byte[] r4 = r4.data
            r5 = 0
            java.lang.System.arraycopy(r3, r5, r4, r5, r9)
            r3 = 1
            r0.zzaqe = r3
            goto L_0x02ad
        L_0x02a7:
            r3 = 1
            r4 = 0
            r0.zzaqi = r4
            r0.zzaqe = r3
        L_0x02ad:
            if (r3 != 0) goto L_0x0006
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjn.zza(com.google.android.gms.internal.ads.zzia, com.google.android.gms.internal.ads.zzif):int");
    }

    public final long getDurationUs() {
        return this.zzaan;
    }

    public final long zzr(long j) {
        long j2 = Long.MAX_VALUE;
        for (zzjp zzjp : this.zzasg) {
            zzjv zzjv = zzjp.zzasi;
            int zzu = zzjv.zzu(j);
            if (zzu == -1) {
                zzu = zzjv.zzv(j);
            }
            long j3 = zzjv.zzagu[zzu];
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    private final void zzei() {
        this.zzaqe = 0;
        this.zzaqh = 0;
    }

    private final void zzt(long j) throws zzfx {
        while (!this.zzaqc.isEmpty() && ((zziw) this.zzaqc.peek()).zzaop == j) {
            zziw zziw = (zziw) this.zzaqc.pop();
            if (zziw.type == zziv.zzamb) {
                long j2 = -9223372036854775807L;
                ArrayList arrayList = new ArrayList();
                zzki zzki = null;
                zzid zzid = new zzid();
                zzix zzai = zziw.zzai(zziv.zzaoa);
                if (zzai != null) {
                    zzki = zziy.zza(zzai, this.zzash);
                    if (zzki != null) {
                        zzid.zzb(zzki);
                    }
                }
                for (int i = 0; i < zziw.zzaor.size(); i++) {
                    zziw zziw2 = (zziw) zziw.zzaor.get(i);
                    if (zziw2.type == zziv.zzamd) {
                        zzjs zza = zziy.zza(zziw2, zziw.zzai(zziv.zzamc), -9223372036854775807L, (zzhp) null, this.zzash);
                        if (zza != null) {
                            zzjv zza2 = zziy.zza(zza, zziw2.zzaj(zziv.zzame).zzaj(zziv.zzamf).zzaj(zziv.zzamg), zzid);
                            if (zza2.zzapk != 0) {
                                zzjp zzjp = new zzjp(zza, zza2, this.zzajq.zzb(i, zza.type));
                                zzfs zzj = zza.zzaad.zzj(zza2.zzapp + 30);
                                if (zza.type == 1) {
                                    if (zzid.zzea()) {
                                        zzj = zzj.zza(zzid.zzzw, zzid.zzzx);
                                    }
                                    if (zzki != null) {
                                        zzj = zzj.zza(zzki);
                                    }
                                }
                                zzjp.zzasj.zzf(zzj);
                                j2 = Math.max(j2, zza.zzaan);
                                arrayList.add(zzjp);
                            }
                        }
                    }
                }
                this.zzaan = j2;
                this.zzasg = (zzjp[]) arrayList.toArray(new zzjp[arrayList.size()]);
                this.zzajq.zzdy();
                this.zzajq.zza(this);
                this.zzaqc.clear();
                this.zzaqe = 2;
            } else if (!this.zzaqc.isEmpty()) {
                ((zziw) this.zzaqc.peek()).zza(zziw);
            }
        }
        if (this.zzaqe != 2) {
            zzei();
        }
    }
}
