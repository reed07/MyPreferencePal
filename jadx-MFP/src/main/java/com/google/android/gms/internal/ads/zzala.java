package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzark
public final class zzala implements zzakp {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public final long mStartTime;
    private final zzalg zzbma;
    private final boolean zzbum;
    private final zzakr zzdmn;
    private final boolean zzdms;
    private final boolean zzdmt;
    private final zzasi zzdnh;
    /* access modifiers changed from: private */
    public final long zzdni;
    private final int zzdnj;
    /* access modifiers changed from: private */
    public boolean zzdnk = false;
    /* access modifiers changed from: private */
    public final Map<zzbcb<zzakx>, zzaku> zzdnl = new HashMap();
    private final String zzdnm;
    private List<zzakx> zzdnn = new ArrayList();

    public zzala(Context context, zzasi zzasi, zzalg zzalg, zzakr zzakr, boolean z, boolean z2, String str, long j, long j2, int i, boolean z3) {
        this.mContext = context;
        this.zzdnh = zzasi;
        this.zzbma = zzalg;
        this.zzdmn = zzakr;
        this.zzbum = z;
        this.zzdms = z2;
        this.zzdnm = str;
        this.mStartTime = j;
        this.zzdni = j2;
        this.zzdnj = 2;
        this.zzdmt = z3;
    }

    public final zzakx zzh(List<zzakq> list) {
        zzaxz.zzdn("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzwf zzwf = this.zzdnh.zzbst;
        int[] iArr = new int[2];
        if (zzwf.zzckm != null) {
            zzbv.zzlz();
            if (zzakz.zza(this.zzdnm, iArr)) {
                int i = 0;
                int i2 = iArr[0];
                int i3 = iArr[1];
                zzwf[] zzwfArr = zzwf.zzckm;
                int length = zzwfArr.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    zzwf zzwf2 = zzwfArr[i];
                    if (i2 == zzwf2.width && i3 == zzwf2.height) {
                        zzwf = zzwf2;
                        break;
                    }
                    i++;
                }
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzakq zzakq = (zzakq) it.next();
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(zzakq.zzdkv);
            zzaxz.zzen(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            Iterator it2 = zzakq.zzdkw.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                Context context = this.mContext;
                zzalg zzalg = this.zzbma;
                zzakr zzakr = this.zzdmn;
                zzwb zzwb = this.zzdnh.zzdwg;
                zzbbi zzbbi = this.zzdnh.zzbsp;
                boolean z = this.zzbum;
                boolean z2 = this.zzdms;
                zzacp zzacp = this.zzdnh.zzbti;
                Iterator it3 = it;
                List<String> list2 = this.zzdnh.zzbtt;
                Iterator it4 = it2;
                ArrayList arrayList2 = arrayList;
                boolean z3 = z2;
                boolean z4 = z;
                zzaku zzaku = new zzaku(context, str2, zzalg, zzakr, zzakq, zzwb, zzwf, zzbbi, z4, z3, zzacp, list2, this.zzdnh.zzdwu, this.zzdnh.zzdxp, this.zzdmt);
                zzbcb zza = zzayf.zza(new zzalb(this, zzaku));
                this.zzdnl.put(zza, zzaku);
                ArrayList arrayList3 = arrayList2;
                arrayList3.add(zza);
                it = it3;
                arrayList = arrayList3;
                it2 = it4;
            }
        }
        ArrayList arrayList4 = arrayList;
        if (this.zzdnj != 2) {
            return zzi(arrayList4);
        }
        return zzj(arrayList4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r4.hasNext() == false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = (com.google.android.gms.internal.ads.zzbcb) r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = (com.google.android.gms.internal.ads.zzakx) r0.get();
        r3.zzdnn.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r1 == null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        if (r1.zzdna != 0) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        zza(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        com.google.android.gms.internal.ads.zzaxz.zzc("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        zza(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0048, code lost:
        return new com.google.android.gms.internal.ads.zzakx(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r4 = r4.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzakx zzi(java.util.List<com.google.android.gms.internal.ads.zzbcb<com.google.android.gms.internal.ads.zzakx>> r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzdnk     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x000f
            com.google.android.gms.internal.ads.zzakx r4 = new com.google.android.gms.internal.ads.zzakx     // Catch:{ all -> 0x0049 }
            r1 = -1
            r4.<init>(r1)     // Catch:{ all -> 0x0049 }
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            return r4
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            java.util.Iterator r4 = r4.iterator()
        L_0x0014:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x003e
            java.lang.Object r0 = r4.next()
            com.google.android.gms.internal.ads.zzbcb r0 = (com.google.android.gms.internal.ads.zzbcb) r0
            java.lang.Object r1 = r0.get()     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            com.google.android.gms.internal.ads.zzakx r1 = (com.google.android.gms.internal.ads.zzakx) r1     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            java.util.List<com.google.android.gms.internal.ads.zzakx> r2 = r3.zzdnn     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            r2.add(r1)     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            if (r1 == 0) goto L_0x0014
            int r2 = r1.zzdna     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            if (r2 != 0) goto L_0x0014
            r3.zza(r0)     // Catch:{ InterruptedException -> 0x0037, ExecutionException -> 0x0035 }
            return r1
        L_0x0035:
            r0 = move-exception
            goto L_0x0038
        L_0x0037:
            r0 = move-exception
        L_0x0038:
            java.lang.String r1 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.ads.zzaxz.zzc(r1, r0)
            goto L_0x0014
        L_0x003e:
            r4 = 0
            r3.zza(r4)
            com.google.android.gms.internal.ads.zzakx r4 = new com.google.android.gms.internal.ads.zzakx
            r0 = 1
            r4.<init>(r0)
            return r4
        L_0x0049:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzala.zzi(java.util.List):com.google.android.gms.internal.ads.zzakx");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r13.zzdmn.zzdmb == -1) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r0 = r13.zzdmn.zzdmb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r0 = 10000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r14 = r14.iterator();
        r3 = null;
        r1 = r0;
        r0 = null;
        r4 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        if (r14.hasNext() == false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        r5 = (com.google.android.gms.internal.ads.zzbcb) r14.next();
        r6 = com.google.android.gms.ads.internal.zzbv.zzlm().currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        if (r1 != 0) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r5.isDone() == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r10 = (com.google.android.gms.internal.ads.zzakx) r5.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0050, code lost:
        r10 = (com.google.android.gms.internal.ads.zzakx) r5.get(r1, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        r13.zzdnn.add(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005d, code lost:
        if (r10 == null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
        if (r10.zzdna != 0) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0063, code lost:
        r11 = r10.zzdnf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0065, code lost:
        if (r11 == null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006b, code lost:
        if (r11.zzur() <= r4) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0071, code lost:
        r4 = r11.zzur();
        r3 = r5;
        r0 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0074, code lost:
        r1 = java.lang.Math.max(r1 - (com.google.android.gms.ads.internal.zzbv.zzlm().currentTimeMillis() - r6), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0083, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0085, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        com.google.android.gms.internal.ads.zzaxz.zzc("Exception while processing an adapter; continuing with other adapters", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0091, code lost:
        r1 = java.lang.Math.max(r1 - (com.google.android.gms.ads.internal.zzbv.zzlm().currentTimeMillis() - r6), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a0, code lost:
        java.lang.Math.max(r1 - (com.google.android.gms.ads.internal.zzbv.zzlm().currentTimeMillis() - r6), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ad, code lost:
        throw r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ae, code lost:
        zza(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b1, code lost:
        if (r0 != null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b9, code lost:
        return new com.google.android.gms.internal.ads.zzakx(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ba, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzakx zzj(java.util.List<com.google.android.gms.internal.ads.zzbcb<com.google.android.gms.internal.ads.zzakx>> r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.mLock
            monitor-enter(r0)
            boolean r1 = r13.zzdnk     // Catch:{ all -> 0x00bb }
            r2 = -1
            if (r1 == 0) goto L_0x000f
            com.google.android.gms.internal.ads.zzakx r14 = new com.google.android.gms.internal.ads.zzakx     // Catch:{ all -> 0x00bb }
            r14.<init>(r2)     // Catch:{ all -> 0x00bb }
            monitor-exit(r0)     // Catch:{ all -> 0x00bb }
            return r14
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x00bb }
            com.google.android.gms.internal.ads.zzakr r0 = r13.zzdmn
            long r0 = r0.zzdmb
            r3 = -1
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x001f
            com.google.android.gms.internal.ads.zzakr r0 = r13.zzdmn
            long r0 = r0.zzdmb
            goto L_0x0021
        L_0x001f:
            r0 = 10000(0x2710, double:4.9407E-320)
        L_0x0021:
            java.util.Iterator r14 = r14.iterator()
            r3 = 0
            r1 = r0
            r0 = r3
            r4 = -1
        L_0x0029:
            boolean r5 = r14.hasNext()
            if (r5 == 0) goto L_0x00ae
            java.lang.Object r5 = r14.next()
            com.google.android.gms.internal.ads.zzbcb r5 = (com.google.android.gms.internal.ads.zzbcb) r5
            com.google.android.gms.common.util.Clock r6 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r6 = r6.currentTimeMillis()
            r8 = 0
            int r10 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x0050
            boolean r10 = r5.isDone()     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            if (r10 == 0) goto L_0x0050
            java.lang.Object r10 = r5.get()     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            com.google.android.gms.internal.ads.zzakx r10 = (com.google.android.gms.internal.ads.zzakx) r10     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            goto L_0x0058
        L_0x0050:
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            java.lang.Object r10 = r5.get(r1, r10)     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            com.google.android.gms.internal.ads.zzakx r10 = (com.google.android.gms.internal.ads.zzakx) r10     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
        L_0x0058:
            java.util.List<com.google.android.gms.internal.ads.zzakx> r11 = r13.zzdnn     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            r11.add(r10)     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            if (r10 == 0) goto L_0x0074
            int r11 = r10.zzdna     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            if (r11 != 0) goto L_0x0074
            com.google.android.gms.internal.ads.zzalp r11 = r10.zzdnf     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            if (r11 == 0) goto L_0x0074
            int r12 = r11.zzur()     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            if (r12 <= r4) goto L_0x0074
            int r0 = r11.zzur()     // Catch:{ InterruptedException -> 0x008b, ExecutionException -> 0x0089, RemoteException -> 0x0087, TimeoutException -> 0x0085 }
            r4 = r0
            r3 = r5
            r0 = r10
        L_0x0074:
            com.google.android.gms.common.util.Clock r5 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r10 = r5.currentTimeMillis()
            long r10 = r10 - r6
            long r1 = r1 - r10
            long r1 = java.lang.Math.max(r1, r8)
            goto L_0x0029
        L_0x0083:
            r14 = move-exception
            goto L_0x00a0
        L_0x0085:
            r5 = move-exception
            goto L_0x008c
        L_0x0087:
            r5 = move-exception
            goto L_0x008c
        L_0x0089:
            r5 = move-exception
            goto L_0x008c
        L_0x008b:
            r5 = move-exception
        L_0x008c:
            java.lang.String r10 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.ads.zzaxz.zzc(r10, r5)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.common.util.Clock r5 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r10 = r5.currentTimeMillis()
            long r10 = r10 - r6
            long r1 = r1 - r10
            long r1 = java.lang.Math.max(r1, r8)
            goto L_0x0029
        L_0x00a0:
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r3 = r0.currentTimeMillis()
            long r3 = r3 - r6
            long r1 = r1 - r3
            java.lang.Math.max(r1, r8)
            throw r14
        L_0x00ae:
            r13.zza(r3)
            if (r0 != 0) goto L_0x00ba
            com.google.android.gms.internal.ads.zzakx r14 = new com.google.android.gms.internal.ads.zzakx
            r0 = 1
            r14.<init>(r0)
            return r14
        L_0x00ba:
            return r0
        L_0x00bb:
            r14 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00bb }
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzala.zzj(java.util.List):com.google.android.gms.internal.ads.zzakx");
    }

    private final void zza(zzbcb<zzakx> zzbcb) {
        zzayh.zzelc.post(new zzalc(this, zzbcb));
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzdnk = true;
            for (zzaku cancel : this.zzdnl.values()) {
                cancel.cancel();
            }
        }
    }

    public final List<zzakx> zzui() {
        return this.zzdnn;
    }
}
