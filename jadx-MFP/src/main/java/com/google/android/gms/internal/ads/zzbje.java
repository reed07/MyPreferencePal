package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
final class zzbje implements BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    private final String packageName;
    @VisibleForTesting
    private zzbjf zzfcr;
    private final String zzfcs;
    private final LinkedBlockingQueue<zzbl> zzfct;
    private final HandlerThread zzfcu = new HandlerThread("GassClient");

    public zzbje(Context context, String str, String str2) {
        this.packageName = str;
        this.zzfcs = str2;
        this.zzfcu.start();
        this.zzfcr = new zzbjf(context, this.zzfcu.getLooper(), this, this);
        this.zzfct = new LinkedBlockingQueue<>();
        this.zzfcr.checkAvailabilityAndConnect();
    }

    public final zzbl zzdj(int i) {
        zzbl zzbl;
        try {
            zzbl = (zzbl) this.zzfct.poll(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            zzbl = null;
        }
        return zzbl == null ? zzafl() : zzbl;
    }

    private final zzbjk zzafk() {
        try {
            return this.zzfcr.zzafm();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }

    public final void onConnectionSuspended(int i) {
        try {
            this.zzfct.put(zzafl());
        } catch (InterruptedException unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0039, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        zzwi();
        r3.zzfcu.quit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0025, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r3.zzfct.put(zzafl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        zzwi();
        r3.zzfcu.quit();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0027 */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0025 A[ExcHandler: all (r4v4 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:2:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzbjk r4 = r3.zzafk()
            if (r4 == 0) goto L_0x0043
            com.google.android.gms.internal.ads.zzbjg r0 = new com.google.android.gms.internal.ads.zzbjg     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            java.lang.String r1 = r3.packageName     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            java.lang.String r2 = r3.zzfcs     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            r0.<init>(r1, r2)     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            com.google.android.gms.internal.ads.zzbji r4 = r4.zza(r0)     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            com.google.android.gms.internal.ads.zzbl r4 = r4.zzafn()     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.ads.zzbl> r0 = r3.zzfct     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            r0.put(r4)     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            r3.zzwi()
            android.os.HandlerThread r4 = r3.zzfcu
            r4.quit()
            return
        L_0x0025:
            r4 = move-exception
            goto L_0x0031
        L_0x0027:
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.ads.zzbl> r4 = r3.zzfct     // Catch:{ InterruptedException -> 0x003a, all -> 0x0025 }
            com.google.android.gms.internal.ads.zzbl r0 = zzafl()     // Catch:{ InterruptedException -> 0x003a, all -> 0x0025 }
            r4.put(r0)     // Catch:{ InterruptedException -> 0x003a, all -> 0x0025 }
            goto L_0x003a
        L_0x0031:
            r3.zzwi()
            android.os.HandlerThread r0 = r3.zzfcu
            r0.quit()
            throw r4
        L_0x003a:
            r3.zzwi()
            android.os.HandlerThread r4 = r3.zzfcu
            r4.quit()
            return
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbje.onConnected(android.os.Bundle):void");
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            this.zzfct.put(zzafl());
        } catch (InterruptedException unused) {
        }
    }

    private final void zzwi() {
        zzbjf zzbjf = this.zzfcr;
        if (zzbjf == null) {
            return;
        }
        if (zzbjf.isConnected() || this.zzfcr.isConnecting()) {
            this.zzfcr.disconnect();
        }
    }

    @VisibleForTesting
    private static zzbl zzafl() {
        zzbl zzbl = new zzbl();
        zzbl.zzeo = Long.valueOf(32768);
        return zzbl;
    }
}
