package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

@zzark
public final class zzbep implements zzov {
    private boolean isOpen;
    private Uri uri;
    private InputStream zzevf;
    private final zzov zzevg;
    @Nullable
    private final zzpn<zzov> zzevh;
    private final zzbeq zzevi;
    private final Context zzsp;

    public zzbep(Context context, zzov zzov, zzpn<zzov> zzpn, zzbeq zzbeq) {
        this.zzsp = context;
        this.zzevg = zzov;
        this.zzevh = zzpn;
        this.zzevi = zzbeq;
    }

    public final void close() throws IOException {
        if (this.isOpen) {
            this.isOpen = false;
            this.uri = null;
            InputStream inputStream = this.zzevf;
            if (inputStream != null) {
                IOUtils.closeQuietly((Closeable) inputStream);
                this.zzevf = null;
            } else {
                this.zzevg.close();
            }
            zzpn<zzov> zzpn = this.zzevh;
            if (zzpn != null) {
                zzpn.zze(this);
                return;
            }
            return;
        }
        throw new IOException("Attempt to close an already closed CacheDataSource.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x009d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r4.cancel(true);
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r4.cancel(true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00d4, code lost:
        r4 = com.google.android.gms.ads.internal.zzbv.zzlm().elapsedRealtime() - r9;
        r1.zzevi.zzb(false, r4);
        r2 = new java.lang.StringBuilder(44);
        r2.append("Cache connection took ");
        r2.append(r4);
        r2.append("ms");
        com.google.android.gms.internal.ads.zzaxz.v(r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00fc, code lost:
        r2 = com.google.android.gms.ads.internal.zzbv.zzlm().elapsedRealtime() - r9;
        r1.zzevi.zzb(false, r2);
        r4 = new java.lang.StringBuilder(44);
        r4.append("Cache connection took ");
        r4.append(r2);
        r4.append("ms");
        com.google.android.gms.internal.ads.zzaxz.v(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0123, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:18:0x009f, B:21:0x00d1] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x009f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00d1 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x00d1=Splitter:B:21:0x00d1, B:18:0x009f=Splitter:B:18:0x009f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zza(com.google.android.gms.internal.ads.zzoz r19) throws java.io.IOException {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            boolean r2 = r1.isOpen
            if (r2 != 0) goto L_0x0168
            r2 = 1
            r1.isOpen = r2
            android.net.Uri r3 = r0.uri
            r1.uri = r3
            com.google.android.gms.internal.ads.zzpn<com.google.android.gms.internal.ads.zzov> r3 = r1.zzevh
            if (r3 == 0) goto L_0x0016
            r3.zza(r1, r0)
        L_0x0016:
            android.net.Uri r3 = r0.uri
            com.google.android.gms.internal.ads.zzty r3 = com.google.android.gms.internal.ads.zzty.zzd(r3)
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.zzaan.zzcvv
            com.google.android.gms.internal.ads.zzaak r5 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r4 = r5.zzd(r4)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            r5 = -1
            if (r4 == 0) goto L_0x0124
            if (r3 == 0) goto L_0x0142
            long r7 = r0.zzaha
            r3.zzcab = r7
            boolean r4 = r3.zzcaa
            if (r4 == 0) goto L_0x0047
            com.google.android.gms.internal.ads.zzaac<java.lang.Long> r4 = com.google.android.gms.internal.ads.zzaan.zzcvx
            com.google.android.gms.internal.ads.zzaak r7 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r4 = r7.zzd(r4)
            java.lang.Long r4 = (java.lang.Long) r4
            goto L_0x0053
        L_0x0047:
            com.google.android.gms.internal.ads.zzaac<java.lang.Long> r4 = com.google.android.gms.internal.ads.zzaan.zzcvw
            com.google.android.gms.internal.ads.zzaak r7 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r4 = r7.zzd(r4)
            java.lang.Long r4 = (java.lang.Long) r4
        L_0x0053:
            long r7 = r4.longValue()
            com.google.android.gms.common.util.Clock r4 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r9 = r4.elapsedRealtime()
            com.google.android.gms.ads.internal.zzbv.zzmb()
            android.content.Context r4 = r1.zzsp
            java.util.concurrent.Future r4 = com.google.android.gms.internal.ads.zzul.zza(r4, r3)
            r11 = 0
            r12 = 44
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ ExecutionException | TimeoutException -> 0x00d1, InterruptedException -> 0x009f }
            java.lang.Object r7 = r4.get(r7, r13)     // Catch:{ ExecutionException | TimeoutException -> 0x00d1, InterruptedException -> 0x009f }
            java.io.InputStream r7 = (java.io.InputStream) r7     // Catch:{ ExecutionException | TimeoutException -> 0x00d1, InterruptedException -> 0x009f }
            r1.zzevf = r7     // Catch:{ ExecutionException | TimeoutException -> 0x00d1, InterruptedException -> 0x009f }
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r3 = r0.elapsedRealtime()
            long r3 = r3 - r9
            com.google.android.gms.internal.ads.zzbeq r0 = r1.zzevi
            r0.zzb(r2, r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r12)
            java.lang.String r2 = "Cache connection took "
            r0.append(r2)
            r0.append(r3)
            java.lang.String r2 = "ms"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ads.zzaxz.v(r0)
            return r5
        L_0x009d:
            r0 = move-exception
            goto L_0x00fc
        L_0x009f:
            r4.cancel(r2)     // Catch:{ all -> 0x009d }
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x009d }
            r2.interrupt()     // Catch:{ all -> 0x009d }
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r4 = r2.elapsedRealtime()
            long r4 = r4 - r9
            com.google.android.gms.internal.ads.zzbeq r2 = r1.zzevi
            r2.zzb(r11, r4)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r12)
            java.lang.String r6 = "Cache connection took "
            r2.append(r6)
            r2.append(r4)
            java.lang.String r4 = "ms"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            com.google.android.gms.internal.ads.zzaxz.v(r2)
            goto L_0x0142
        L_0x00d1:
            r4.cancel(r2)     // Catch:{ all -> 0x009d }
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r4 = r2.elapsedRealtime()
            long r4 = r4 - r9
            com.google.android.gms.internal.ads.zzbeq r2 = r1.zzevi
            r2.zzb(r11, r4)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r12)
            java.lang.String r6 = "Cache connection took "
            r2.append(r6)
            r2.append(r4)
            java.lang.String r4 = "ms"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            com.google.android.gms.internal.ads.zzaxz.v(r2)
            goto L_0x0142
        L_0x00fc:
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r2 = r2.elapsedRealtime()
            long r2 = r2 - r9
            com.google.android.gms.internal.ads.zzbeq r4 = r1.zzevi
            r4.zzb(r11, r2)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r12)
            java.lang.String r5 = "Cache connection took "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = "ms"
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.google.android.gms.internal.ads.zzaxz.v(r2)
            throw r0
        L_0x0124:
            r2 = 0
            if (r3 == 0) goto L_0x0133
            long r7 = r0.zzaha
            r3.zzcab = r7
            com.google.android.gms.internal.ads.zztq r2 = com.google.android.gms.ads.internal.zzbv.zzll()
            com.google.android.gms.internal.ads.zztv r2 = r2.zza(r3)
        L_0x0133:
            if (r2 == 0) goto L_0x0142
            boolean r4 = r2.zzoe()
            if (r4 == 0) goto L_0x0142
            java.io.InputStream r0 = r2.zzof()
            r1.zzevf = r0
            return r5
        L_0x0142:
            if (r3 == 0) goto L_0x0161
            com.google.android.gms.internal.ads.zzoz r2 = new com.google.android.gms.internal.ads.zzoz
            java.lang.String r3 = r3.url
            android.net.Uri r8 = android.net.Uri.parse(r3)
            byte[] r9 = r0.zzbft
            long r10 = r0.zzbfu
            long r12 = r0.zzaha
            long r14 = r0.zzcc
            java.lang.String r3 = r0.zzcb
            int r0 = r0.flags
            r7 = r2
            r16 = r3
            r17 = r0
            r7.<init>(r8, r9, r10, r12, r14, r16, r17)
            r0 = r2
        L_0x0161:
            com.google.android.gms.internal.ads.zzov r2 = r1.zzevg
            long r2 = r2.zza(r0)
            return r2
        L_0x0168:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r2 = "Attempt to open an already open CacheDataSource."
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbep.zza(com.google.android.gms.internal.ads.zzoz):long");
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (this.isOpen) {
            InputStream inputStream = this.zzevf;
            if (inputStream != null) {
                i3 = inputStream.read(bArr, i, i2);
            } else {
                i3 = this.zzevg.read(bArr, i, i2);
            }
            zzpn<zzov> zzpn = this.zzevh;
            if (zzpn != null) {
                zzpn.zzc(this, i3);
            }
            return i3;
        }
        throw new IOException("Attempt to read closed CacheDataSource.");
    }

    public final Uri getUri() {
        return this.uri;
    }
}
