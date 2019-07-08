package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;

@zzark
@TargetApi(16)
public final class zzbfw extends zzbfk implements zzbez {
    private String url;
    private boolean zzexf;
    private zzbes zzexl;
    private Exception zzexm;
    private boolean zzexn;

    public zzbfw(zzbdz zzbdz, zzbdy zzbdy) {
        super(zzbdz);
        this.zzexl = new zzbes(zzbdz.getContext(), zzbdy);
        this.zzexl.zza((zzbez) this);
    }

    public final void zzdd(int i) {
    }

    public final void zzp(int i, int i2) {
    }

    public final void zzb(boolean z, long j) {
        zzbdz zzbdz = (zzbdz) this.zzewo.get();
        if (zzbdz != null) {
            zzbcg.zzepo.execute(new zzbfx(zzbdz, z, j));
        }
    }

    public final void zza(String str, Exception exc) {
        this.zzexm = exc;
        zzaxz.zzc("Precache error", exc);
        zzfa(str);
    }

    public final void zzda(int i) {
        this.zzexl.zzacz().zzdg(i);
    }

    public final void zzcz(int i) {
        this.zzexl.zzacz().zzdf(i);
    }

    public final void zzdb(int i) {
        this.zzexl.zzacz().zzdb(i);
    }

    public final void zzdc(int i) {
        this.zzexl.zzacz().zzdc(i);
    }

    public final void release() {
        zzbes zzbes = this.zzexl;
        if (zzbes != null) {
            zzbes.zza((zzbez) null);
            this.zzexl.release();
        }
        super.release();
    }

    /* access modifiers changed from: protected */
    public final String zzey(String str) {
        String valueOf = String.valueOf("cache:");
        String valueOf2 = String.valueOf(super.zzey(str));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0078, code lost:
        if (r11.zzexm == null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007a, code lost:
        r1 = "badUrl";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007e, code lost:
        throw r11.zzexm;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007f, code lost:
        r1 = "externalAbort";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0088, code lost:
        throw new java.io.IOException("Abort requested before buffering finished. ");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzex(java.lang.String r34) {
        /*
            r33 = this;
            r11 = r33
            r12 = r34
            r11.url = r12
            java.lang.String r13 = r33.zzey(r34)
            java.lang.String r14 = "error"
            android.net.Uri r0 = android.net.Uri.parse(r34)     // Catch:{ Exception -> 0x0156 }
            com.google.android.gms.internal.ads.zzbes r1 = r11.zzexl     // Catch:{ Exception -> 0x0156 }
            java.lang.String r2 = r11.zzeiz     // Catch:{ Exception -> 0x0156 }
            r1.zza(r0, r2)     // Catch:{ Exception -> 0x0156 }
            java.lang.ref.WeakReference r0 = r11.zzewo     // Catch:{ Exception -> 0x0156 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0156 }
            com.google.android.gms.internal.ads.zzbdz r0 = (com.google.android.gms.internal.ads.zzbdz) r0     // Catch:{ Exception -> 0x0156 }
            if (r0 == 0) goto L_0x0028
            r0.zza(r13, r11)     // Catch:{ Exception -> 0x0025 }
            goto L_0x0028
        L_0x0025:
            r0 = move-exception
            goto L_0x0159
        L_0x0028:
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzbv.zzlm()     // Catch:{ Exception -> 0x0156 }
            long r16 = r0.currentTimeMillis()     // Catch:{ Exception -> 0x0156 }
            com.google.android.gms.internal.ads.zzaac<java.lang.Long> r1 = com.google.android.gms.internal.ads.zzaan.zzcox     // Catch:{ Exception -> 0x0156 }
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ Exception -> 0x0156 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ Exception -> 0x0156 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ Exception -> 0x0156 }
            long r9 = r1.longValue()     // Catch:{ Exception -> 0x0156 }
            com.google.android.gms.internal.ads.zzaac<java.lang.Long> r1 = com.google.android.gms.internal.ads.zzaan.zzcow     // Catch:{ Exception -> 0x0156 }
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ Exception -> 0x0156 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ Exception -> 0x0156 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ Exception -> 0x0156 }
            long r1 = r1.longValue()     // Catch:{ Exception -> 0x0156 }
            r3 = 1000(0x3e8, double:4.94E-321)
            long r6 = r1 * r3
            com.google.android.gms.internal.ads.zzaac<java.lang.Integer> r1 = com.google.android.gms.internal.ads.zzaan.zzcov     // Catch:{ Exception -> 0x0156 }
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ Exception -> 0x0156 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ Exception -> 0x0156 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Exception -> 0x0156 }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x0156 }
            long r4 = (long) r1     // Catch:{ Exception -> 0x0156 }
            r1 = -1
        L_0x0067:
            monitor-enter(r33)     // Catch:{ Exception -> 0x0156 }
            long r18 = r0.currentTimeMillis()     // Catch:{ all -> 0x014f }
            long r18 = r18 - r16
            int r3 = (r18 > r6 ? 1 : (r18 == r6 ? 0 : -1))
            if (r3 > 0) goto L_0x0122
            boolean r3 = r11.zzexf     // Catch:{ all -> 0x014f }
            if (r3 == 0) goto L_0x0089
            java.lang.Exception r0 = r11.zzexm     // Catch:{ all -> 0x0154 }
            if (r0 == 0) goto L_0x007f
            java.lang.String r1 = "badUrl"
            java.lang.Exception r0 = r11.zzexm     // Catch:{ all -> 0x0148 }
            throw r0     // Catch:{ all -> 0x0148 }
        L_0x007f:
            java.lang.String r1 = "externalAbort"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0148 }
            java.lang.String r2 = "Abort requested before buffering finished. "
            r0.<init>(r2)     // Catch:{ all -> 0x0148 }
            throw r0     // Catch:{ all -> 0x0148 }
        L_0x0089:
            boolean r3 = r11.zzexn     // Catch:{ all -> 0x014f }
            r18 = 1
            if (r3 == 0) goto L_0x0092
            monitor-exit(r33)     // Catch:{ all -> 0x0154 }
            goto L_0x00f3
        L_0x0092:
            com.google.android.gms.internal.ads.zzbes r3 = r11.zzexl     // Catch:{ all -> 0x014f }
            com.google.android.gms.internal.ads.zzfg r3 = r3.zzacw()     // Catch:{ all -> 0x014f }
            if (r3 == 0) goto L_0x0116
            r20 = r14
            long r14 = r3.getDuration()     // Catch:{ all -> 0x014b }
            r21 = 0
            int r8 = (r14 > r21 ? 1 : (r14 == r21 ? 0 : -1))
            if (r8 <= 0) goto L_0x00f8
            long r23 = r3.getBufferedPosition()     // Catch:{ all -> 0x014b }
            int r3 = (r23 > r1 ? 1 : (r23 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x00d5
            int r1 = (r23 > r21 ? 1 : (r23 == r21 ? 0 : -1))
            if (r1 <= 0) goto L_0x00b4
            r8 = 1
            goto L_0x00b5
        L_0x00b4:
            r8 = 0
        L_0x00b5:
            int r25 = com.google.android.gms.internal.ads.zzbes.zzacx()     // Catch:{ all -> 0x014b }
            int r26 = com.google.android.gms.internal.ads.zzbes.zzacy()     // Catch:{ all -> 0x014b }
            r1 = r33
            r2 = r34
            r3 = r13
            r27 = r4
            r4 = r23
            r29 = r6
            r6 = r14
            r31 = r9
            r9 = r25
            r10 = r26
            r1.zza(r2, r3, r4, r6, r8, r9, r10)     // Catch:{ all -> 0x014b }
            r1 = r23
            goto L_0x00db
        L_0x00d5:
            r27 = r4
            r29 = r6
            r31 = r9
        L_0x00db:
            int r3 = (r23 > r14 ? 1 : (r23 == r14 ? 0 : -1))
            if (r3 < 0) goto L_0x00e4
            r11.zzc(r12, r13, r14)     // Catch:{ all -> 0x014b }
            monitor-exit(r33)     // Catch:{ all -> 0x014b }
            goto L_0x00f3
        L_0x00e4:
            com.google.android.gms.internal.ads.zzbes r3 = r11.zzexl     // Catch:{ all -> 0x014b }
            long r3 = r3.getBytesTransferred()     // Catch:{ all -> 0x014b }
            int r5 = (r3 > r27 ? 1 : (r3 == r27 ? 0 : -1))
            if (r5 < 0) goto L_0x00f4
            int r3 = (r23 > r21 ? 1 : (r23 == r21 ? 0 : -1))
            if (r3 <= 0) goto L_0x00f4
            monitor-exit(r33)     // Catch:{ all -> 0x014b }
        L_0x00f3:
            return r18
        L_0x00f4:
            r3 = r1
            r1 = r31
            goto L_0x00fe
        L_0x00f8:
            r27 = r4
            r29 = r6
            r3 = r1
            r1 = r9
        L_0x00fe:
            r11.wait(r1)     // Catch:{ InterruptedException -> 0x010c }
            monitor-exit(r33)     // Catch:{ all -> 0x014b }
            r9 = r1
            r1 = r3
            r14 = r20
            r4 = r27
            r6 = r29
            goto L_0x0067
        L_0x010c:
            java.lang.String r1 = "interrupted"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0148 }
            java.lang.String r2 = "Wait interrupted."
            r0.<init>(r2)     // Catch:{ all -> 0x0148 }
            throw r0     // Catch:{ all -> 0x0148 }
        L_0x0116:
            r20 = r14
            java.lang.String r1 = "exoPlayerReleased"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0148 }
            java.lang.String r2 = "ExoPlayer was released during preloading."
            r0.<init>(r2)     // Catch:{ all -> 0x0148 }
            throw r0     // Catch:{ all -> 0x0148 }
        L_0x0122:
            r29 = r6
            r20 = r14
            java.lang.String r1 = "downloadTimeout"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0148 }
            r2 = 47
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0148 }
            r3.<init>(r2)     // Catch:{ all -> 0x0148 }
            java.lang.String r2 = "Timeout reached. Limit: "
            r3.append(r2)     // Catch:{ all -> 0x0148 }
            r4 = r29
            r3.append(r4)     // Catch:{ all -> 0x0148 }
            java.lang.String r2 = " ms"
            r3.append(r2)     // Catch:{ all -> 0x0148 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0148 }
            r0.<init>(r2)     // Catch:{ all -> 0x0148 }
            throw r0     // Catch:{ all -> 0x0148 }
        L_0x0148:
            r0 = move-exception
            r14 = r1
            goto L_0x0152
        L_0x014b:
            r0 = move-exception
            r14 = r20
            goto L_0x0152
        L_0x014f:
            r0 = move-exception
            r20 = r14
        L_0x0152:
            monitor-exit(r33)     // Catch:{ all -> 0x0154 }
            throw r0     // Catch:{ Exception -> 0x0025 }
        L_0x0154:
            r0 = move-exception
            goto L_0x0152
        L_0x0156:
            r0 = move-exception
            r20 = r14
        L_0x0159:
            java.lang.String r1 = r0.getMessage()
            java.lang.String r2 = java.lang.String.valueOf(r34)
            int r2 = r2.length()
            int r2 = r2 + 34
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Failed to preload url "
            r3.append(r2)
            r3.append(r12)
            java.lang.String r2 = " Exception: "
            r3.append(r2)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.google.android.gms.internal.ads.zzaxz.zzeo(r1)
            r33.release()
            java.lang.String r0 = zzb(r14, r0)
            r11.zza(r12, r13, r14, r0)
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbfw.zzex(java.lang.String):boolean");
    }

    public final void abort() {
        zzfa(null);
    }

    private final void zzfa(String str) {
        synchronized (this) {
            this.zzexf = true;
            notify();
            release();
        }
        String str2 = this.url;
        if (str2 != null) {
            String zzey = zzey(str2);
            Exception exc = this.zzexm;
            if (exc != null) {
                zza(this.url, zzey, "badUrl", zzb(str, exc));
                return;
            }
            zza(this.url, zzey, "externalAbort", "Programmatic precache abort.");
        }
    }

    public final zzbes zzadd() {
        synchronized (this) {
            this.zzexn = true;
            notify();
        }
        this.zzexl.zza((zzbez) null);
        zzbes zzbes = this.zzexl;
        this.zzexl = null;
        return zzbes;
    }

    private static String zzb(String str, Exception exc) {
        String canonicalName = exc.getClass().getCanonicalName();
        String message = exc.getMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(canonicalName).length() + String.valueOf(message).length());
        sb.append(str);
        sb.append("/");
        sb.append(canonicalName);
        sb.append(":");
        sb.append(message);
        return sb.toString();
    }
}
