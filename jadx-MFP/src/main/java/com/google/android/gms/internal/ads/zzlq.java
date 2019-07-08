package com.google.android.gms.internal.ads;

final class zzlq {
    private int length;
    private int[] zzagt;
    private long[] zzagu;
    private long[] zzagw;
    private int[] zzapr;
    private int zzaxz = 1000;
    private int[] zzaya;
    private zzij[] zzayb;
    private zzfs[] zzayc;
    private int zzayd;
    private int zzaye;
    private int zzayf;
    private long zzayg;
    private long zzayh;
    private boolean zzayi;
    private boolean zzayj;
    private zzfs zzayk;

    public zzlq() {
        int i = this.zzaxz;
        this.zzaya = new int[i];
        this.zzagu = new long[i];
        this.zzagw = new long[i];
        this.zzapr = new int[i];
        this.zzagt = new int[i];
        this.zzayb = new zzij[i];
        this.zzayc = new zzfs[i];
        this.zzayg = Long.MIN_VALUE;
        this.zzayh = Long.MIN_VALUE;
        this.zzayj = true;
        this.zzayi = true;
    }

    public final void zzfi() {
        this.zzayd = 0;
        this.zzaye = 0;
        this.zzayf = 0;
        this.length = 0;
        this.zzayi = true;
    }

    public final void zzfj() {
        this.zzayg = Long.MIN_VALUE;
        this.zzayh = Long.MIN_VALUE;
    }

    public final int zzfk() {
        return this.zzayd + this.length;
    }

    public final long zzaq(int i) {
        int zzfk = zzfk() - i;
        zzpo.checkArgument(zzfk >= 0 && zzfk <= this.length);
        if (zzfk != 0) {
            this.length -= zzfk;
            int i2 = this.zzayf;
            int i3 = this.zzaxz;
            this.zzayf = ((i2 + i3) - zzfk) % i3;
            this.zzayh = Long.MIN_VALUE;
            for (int i4 = this.length - 1; i4 >= 0; i4--) {
                int i5 = (this.zzaye + i4) % this.zzaxz;
                this.zzayh = Math.max(this.zzayh, this.zzagw[i5]);
                if ((this.zzapr[i5] & 1) != 0) {
                    break;
                }
            }
            return this.zzagu[this.zzayf];
        } else if (this.zzayd == 0 && this.length == 0) {
            return 0;
        } else {
            int i6 = this.zzayf;
            if (i6 == 0) {
                i6 = this.zzaxz;
            }
            int i7 = i6 - 1;
            return this.zzagu[i7] + ((long) this.zzagt[i7]);
        }
    }

    public final int zzfl() {
        return this.zzayd;
    }

    public final synchronized boolean zzfm() {
        return this.length != 0;
    }

    public final synchronized zzfs zzfn() {
        if (this.zzayj) {
            return null;
        }
        return this.zzayk;
    }

    public final synchronized long zzfc() {
        return Math.max(this.zzayg, this.zzayh);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0023, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int zza(com.google.android.gms.internal.ads.zzfu r5, com.google.android.gms.internal.ads.zzho r6, boolean r7, boolean r8, com.google.android.gms.internal.ads.zzfs r9, com.google.android.gms.internal.ads.zzlr r10) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.zzfm()     // Catch:{ all -> 0x00a6 }
            r1 = -5
            r2 = -3
            r3 = -4
            if (r0 != 0) goto L_0x0024
            if (r8 == 0) goto L_0x0012
            r5 = 4
            r6.setFlags(r5)     // Catch:{ all -> 0x00a6 }
            monitor-exit(r4)
            return r3
        L_0x0012:
            com.google.android.gms.internal.ads.zzfs r6 = r4.zzayk     // Catch:{ all -> 0x00a6 }
            if (r6 == 0) goto L_0x0022
            if (r7 != 0) goto L_0x001c
            com.google.android.gms.internal.ads.zzfs r6 = r4.zzayk     // Catch:{ all -> 0x00a6 }
            if (r6 == r9) goto L_0x0022
        L_0x001c:
            com.google.android.gms.internal.ads.zzfs r6 = r4.zzayk     // Catch:{ all -> 0x00a6 }
            r5.zzaad = r6     // Catch:{ all -> 0x00a6 }
            monitor-exit(r4)
            return r1
        L_0x0022:
            monitor-exit(r4)
            return r2
        L_0x0024:
            if (r7 != 0) goto L_0x009c
            com.google.android.gms.internal.ads.zzfs[] r7 = r4.zzayc     // Catch:{ all -> 0x00a6 }
            int r8 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            r7 = r7[r8]     // Catch:{ all -> 0x00a6 }
            if (r7 == r9) goto L_0x002f
            goto L_0x009c
        L_0x002f:
            java.nio.ByteBuffer r5 = r6.zzdd     // Catch:{ all -> 0x00a6 }
            r7 = 0
            r8 = 1
            if (r5 != 0) goto L_0x0037
            r5 = 1
            goto L_0x0038
        L_0x0037:
            r5 = 0
        L_0x0038:
            if (r5 == 0) goto L_0x003c
            monitor-exit(r4)
            return r2
        L_0x003c:
            long[] r5 = r4.zzagw     // Catch:{ all -> 0x00a6 }
            int r9 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            r0 = r5[r9]     // Catch:{ all -> 0x00a6 }
            r6.zzago = r0     // Catch:{ all -> 0x00a6 }
            int[] r5 = r4.zzapr     // Catch:{ all -> 0x00a6 }
            int r9 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            r5 = r5[r9]     // Catch:{ all -> 0x00a6 }
            r6.setFlags(r5)     // Catch:{ all -> 0x00a6 }
            int[] r5 = r4.zzagt     // Catch:{ all -> 0x00a6 }
            int r9 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            r5 = r5[r9]     // Catch:{ all -> 0x00a6 }
            r10.size = r5     // Catch:{ all -> 0x00a6 }
            long[] r5 = r4.zzagu     // Catch:{ all -> 0x00a6 }
            int r9 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            r0 = r5[r9]     // Catch:{ all -> 0x00a6 }
            r10.zzapb = r0     // Catch:{ all -> 0x00a6 }
            com.google.android.gms.internal.ads.zzij[] r5 = r4.zzayb     // Catch:{ all -> 0x00a6 }
            int r9 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            r5 = r5[r9]     // Catch:{ all -> 0x00a6 }
            r10.zzajw = r5     // Catch:{ all -> 0x00a6 }
            long r0 = r4.zzayg     // Catch:{ all -> 0x00a6 }
            long r5 = r6.zzago     // Catch:{ all -> 0x00a6 }
            long r5 = java.lang.Math.max(r0, r5)     // Catch:{ all -> 0x00a6 }
            r4.zzayg = r5     // Catch:{ all -> 0x00a6 }
            int r5 = r4.length     // Catch:{ all -> 0x00a6 }
            int r5 = r5 - r8
            r4.length = r5     // Catch:{ all -> 0x00a6 }
            int r5 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            int r5 = r5 + r8
            r4.zzaye = r5     // Catch:{ all -> 0x00a6 }
            int r5 = r4.zzayd     // Catch:{ all -> 0x00a6 }
            int r5 = r5 + r8
            r4.zzayd = r5     // Catch:{ all -> 0x00a6 }
            int r5 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            int r6 = r4.zzaxz     // Catch:{ all -> 0x00a6 }
            if (r5 != r6) goto L_0x0086
            r4.zzaye = r7     // Catch:{ all -> 0x00a6 }
        L_0x0086:
            int r5 = r4.length     // Catch:{ all -> 0x00a6 }
            if (r5 <= 0) goto L_0x0091
            long[] r5 = r4.zzagu     // Catch:{ all -> 0x00a6 }
            int r6 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            r6 = r5[r6]     // Catch:{ all -> 0x00a6 }
            goto L_0x0098
        L_0x0091:
            long r5 = r10.zzapb     // Catch:{ all -> 0x00a6 }
            int r7 = r10.size     // Catch:{ all -> 0x00a6 }
            long r7 = (long) r7     // Catch:{ all -> 0x00a6 }
            long r6 = r5 + r7
        L_0x0098:
            r10.zzayl = r6     // Catch:{ all -> 0x00a6 }
            monitor-exit(r4)
            return r3
        L_0x009c:
            com.google.android.gms.internal.ads.zzfs[] r6 = r4.zzayc     // Catch:{ all -> 0x00a6 }
            int r7 = r4.zzaye     // Catch:{ all -> 0x00a6 }
            r6 = r6[r7]     // Catch:{ all -> 0x00a6 }
            r5.zzaad = r6     // Catch:{ all -> 0x00a6 }
            monitor-exit(r4)
            return r1
        L_0x00a6:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlq.zza(com.google.android.gms.internal.ads.zzfu, com.google.android.gms.internal.ads.zzho, boolean, boolean, com.google.android.gms.internal.ads.zzfs, com.google.android.gms.internal.ads.zzlr):int");
    }

    public final synchronized long zzfo() {
        if (!zzfm()) {
            return -1;
        }
        int i = ((this.zzaye + this.length) - 1) % this.zzaxz;
        this.zzaye = (this.zzaye + this.length) % this.zzaxz;
        this.zzayd += this.length;
        this.length = 0;
        return this.zzagu[i] + ((long) this.zzagt[i]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0060, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized long zzd(long r9, boolean r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.zzfm()     // Catch:{ all -> 0x0061 }
            r1 = -1
            if (r0 == 0) goto L_0x005f
            long[] r0 = r8.zzagw     // Catch:{ all -> 0x0061 }
            int r3 = r8.zzaye     // Catch:{ all -> 0x0061 }
            r3 = r0[r3]     // Catch:{ all -> 0x0061 }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0014
            goto L_0x005f
        L_0x0014:
            long r3 = r8.zzayh     // Catch:{ all -> 0x0061 }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x001e
            if (r11 != 0) goto L_0x001e
            monitor-exit(r8)
            return r1
        L_0x001e:
            r11 = 0
            int r0 = r8.zzaye     // Catch:{ all -> 0x0061 }
            r3 = -1
            r11 = -1
            r4 = 0
        L_0x0024:
            int r5 = r8.zzayf     // Catch:{ all -> 0x0061 }
            if (r0 == r5) goto L_0x0041
            long[] r5 = r8.zzagw     // Catch:{ all -> 0x0061 }
            r6 = r5[r0]     // Catch:{ all -> 0x0061 }
            int r5 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r5 > 0) goto L_0x0041
            int[] r5 = r8.zzapr     // Catch:{ all -> 0x0061 }
            r5 = r5[r0]     // Catch:{ all -> 0x0061 }
            r5 = r5 & 1
            if (r5 == 0) goto L_0x0039
            r11 = r4
        L_0x0039:
            int r0 = r0 + 1
            int r5 = r8.zzaxz     // Catch:{ all -> 0x0061 }
            int r0 = r0 % r5
            int r4 = r4 + 1
            goto L_0x0024
        L_0x0041:
            if (r11 != r3) goto L_0x0045
            monitor-exit(r8)
            return r1
        L_0x0045:
            int r9 = r8.zzaye     // Catch:{ all -> 0x0061 }
            int r9 = r9 + r11
            int r10 = r8.zzaxz     // Catch:{ all -> 0x0061 }
            int r9 = r9 % r10
            r8.zzaye = r9     // Catch:{ all -> 0x0061 }
            int r9 = r8.zzayd     // Catch:{ all -> 0x0061 }
            int r9 = r9 + r11
            r8.zzayd = r9     // Catch:{ all -> 0x0061 }
            int r9 = r8.length     // Catch:{ all -> 0x0061 }
            int r9 = r9 - r11
            r8.length = r9     // Catch:{ all -> 0x0061 }
            long[] r9 = r8.zzagu     // Catch:{ all -> 0x0061 }
            int r10 = r8.zzaye     // Catch:{ all -> 0x0061 }
            r10 = r9[r10]     // Catch:{ all -> 0x0061 }
            monitor-exit(r8)
            return r10
        L_0x005f:
            monitor-exit(r8)
            return r1
        L_0x0061:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlq.zzd(long, boolean):long");
    }

    public final synchronized boolean zzh(zzfs zzfs) {
        if (zzfs == null) {
            this.zzayj = true;
            return false;
        }
        this.zzayj = false;
        if (zzqe.zza((Object) zzfs, (Object) this.zzayk)) {
            return false;
        }
        this.zzayk = zzfs;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00ea, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(long r6, int r8, long r9, int r11, com.google.android.gms.internal.ads.zzij r12) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.zzayi     // Catch:{ all -> 0x00eb }
            r1 = 0
            if (r0 == 0) goto L_0x000e
            r0 = r8 & 1
            if (r0 != 0) goto L_0x000c
            monitor-exit(r5)
            return
        L_0x000c:
            r5.zzayi = r1     // Catch:{ all -> 0x00eb }
        L_0x000e:
            boolean r0 = r5.zzayj     // Catch:{ all -> 0x00eb }
            r2 = 1
            if (r0 != 0) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            com.google.android.gms.internal.ads.zzpo.checkState(r0)     // Catch:{ all -> 0x00eb }
            r5.zzac(r6)     // Catch:{ all -> 0x00eb }
            long[] r0 = r5.zzagw     // Catch:{ all -> 0x00eb }
            int r3 = r5.zzayf     // Catch:{ all -> 0x00eb }
            r0[r3] = r6     // Catch:{ all -> 0x00eb }
            long[] r6 = r5.zzagu     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzayf     // Catch:{ all -> 0x00eb }
            r6[r7] = r9     // Catch:{ all -> 0x00eb }
            int[] r6 = r5.zzagt     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzayf     // Catch:{ all -> 0x00eb }
            r6[r7] = r11     // Catch:{ all -> 0x00eb }
            int[] r6 = r5.zzapr     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzayf     // Catch:{ all -> 0x00eb }
            r6[r7] = r8     // Catch:{ all -> 0x00eb }
            com.google.android.gms.internal.ads.zzij[] r6 = r5.zzayb     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzayf     // Catch:{ all -> 0x00eb }
            r6[r7] = r12     // Catch:{ all -> 0x00eb }
            com.google.android.gms.internal.ads.zzfs[] r6 = r5.zzayc     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzayf     // Catch:{ all -> 0x00eb }
            com.google.android.gms.internal.ads.zzfs r8 = r5.zzayk     // Catch:{ all -> 0x00eb }
            r6[r7] = r8     // Catch:{ all -> 0x00eb }
            int[] r6 = r5.zzaya     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzayf     // Catch:{ all -> 0x00eb }
            r6[r7] = r1     // Catch:{ all -> 0x00eb }
            int r6 = r5.length     // Catch:{ all -> 0x00eb }
            int r6 = r6 + r2
            r5.length = r6     // Catch:{ all -> 0x00eb }
            int r6 = r5.length     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzaxz     // Catch:{ all -> 0x00eb }
            if (r6 != r7) goto L_0x00dc
            int r6 = r5.zzaxz     // Catch:{ all -> 0x00eb }
            int r6 = r6 + 1000
            int[] r7 = new int[r6]     // Catch:{ all -> 0x00eb }
            long[] r8 = new long[r6]     // Catch:{ all -> 0x00eb }
            long[] r9 = new long[r6]     // Catch:{ all -> 0x00eb }
            int[] r10 = new int[r6]     // Catch:{ all -> 0x00eb }
            int[] r11 = new int[r6]     // Catch:{ all -> 0x00eb }
            com.google.android.gms.internal.ads.zzij[] r12 = new com.google.android.gms.internal.ads.zzij[r6]     // Catch:{ all -> 0x00eb }
            com.google.android.gms.internal.ads.zzfs[] r0 = new com.google.android.gms.internal.ads.zzfs[r6]     // Catch:{ all -> 0x00eb }
            int r2 = r5.zzaxz     // Catch:{ all -> 0x00eb }
            int r3 = r5.zzaye     // Catch:{ all -> 0x00eb }
            int r2 = r2 - r3
            long[] r3 = r5.zzagu     // Catch:{ all -> 0x00eb }
            int r4 = r5.zzaye     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r3, r4, r8, r1, r2)     // Catch:{ all -> 0x00eb }
            long[] r3 = r5.zzagw     // Catch:{ all -> 0x00eb }
            int r4 = r5.zzaye     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r3, r4, r9, r1, r2)     // Catch:{ all -> 0x00eb }
            int[] r3 = r5.zzapr     // Catch:{ all -> 0x00eb }
            int r4 = r5.zzaye     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r3, r4, r10, r1, r2)     // Catch:{ all -> 0x00eb }
            int[] r3 = r5.zzagt     // Catch:{ all -> 0x00eb }
            int r4 = r5.zzaye     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r3, r4, r11, r1, r2)     // Catch:{ all -> 0x00eb }
            com.google.android.gms.internal.ads.zzij[] r3 = r5.zzayb     // Catch:{ all -> 0x00eb }
            int r4 = r5.zzaye     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r3, r4, r12, r1, r2)     // Catch:{ all -> 0x00eb }
            com.google.android.gms.internal.ads.zzfs[] r3 = r5.zzayc     // Catch:{ all -> 0x00eb }
            int r4 = r5.zzaye     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r3, r4, r0, r1, r2)     // Catch:{ all -> 0x00eb }
            int[] r3 = r5.zzaya     // Catch:{ all -> 0x00eb }
            int r4 = r5.zzaye     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r3, r4, r7, r1, r2)     // Catch:{ all -> 0x00eb }
            int r3 = r5.zzaye     // Catch:{ all -> 0x00eb }
            long[] r4 = r5.zzagu     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r4, r1, r8, r2, r3)     // Catch:{ all -> 0x00eb }
            long[] r4 = r5.zzagw     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r4, r1, r9, r2, r3)     // Catch:{ all -> 0x00eb }
            int[] r4 = r5.zzapr     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r4, r1, r10, r2, r3)     // Catch:{ all -> 0x00eb }
            int[] r4 = r5.zzagt     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r4, r1, r11, r2, r3)     // Catch:{ all -> 0x00eb }
            com.google.android.gms.internal.ads.zzij[] r4 = r5.zzayb     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r4, r1, r12, r2, r3)     // Catch:{ all -> 0x00eb }
            com.google.android.gms.internal.ads.zzfs[] r4 = r5.zzayc     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r4, r1, r0, r2, r3)     // Catch:{ all -> 0x00eb }
            int[] r4 = r5.zzaya     // Catch:{ all -> 0x00eb }
            java.lang.System.arraycopy(r4, r1, r7, r2, r3)     // Catch:{ all -> 0x00eb }
            r5.zzagu = r8     // Catch:{ all -> 0x00eb }
            r5.zzagw = r9     // Catch:{ all -> 0x00eb }
            r5.zzapr = r10     // Catch:{ all -> 0x00eb }
            r5.zzagt = r11     // Catch:{ all -> 0x00eb }
            r5.zzayb = r12     // Catch:{ all -> 0x00eb }
            r5.zzayc = r0     // Catch:{ all -> 0x00eb }
            r5.zzaya = r7     // Catch:{ all -> 0x00eb }
            r5.zzaye = r1     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzaxz     // Catch:{ all -> 0x00eb }
            r5.zzayf = r7     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzaxz     // Catch:{ all -> 0x00eb }
            r5.length = r7     // Catch:{ all -> 0x00eb }
            r5.zzaxz = r6     // Catch:{ all -> 0x00eb }
            monitor-exit(r5)
            return
        L_0x00dc:
            int r6 = r5.zzayf     // Catch:{ all -> 0x00eb }
            int r6 = r6 + r2
            r5.zzayf = r6     // Catch:{ all -> 0x00eb }
            int r6 = r5.zzayf     // Catch:{ all -> 0x00eb }
            int r7 = r5.zzaxz     // Catch:{ all -> 0x00eb }
            if (r6 != r7) goto L_0x00e9
            r5.zzayf = r1     // Catch:{ all -> 0x00eb }
        L_0x00e9:
            monitor-exit(r5)
            return
        L_0x00eb:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlq.zza(long, int, long, int, com.google.android.gms.internal.ads.zzij):void");
    }

    public final synchronized void zzac(long j) {
        this.zzayh = Math.max(this.zzayh, j);
    }
}
