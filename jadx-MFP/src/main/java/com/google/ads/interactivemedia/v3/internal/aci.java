package com.google.ads.interactivemedia.v3.internal;

import android.os.AsyncTask;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.impl.data.y;

/* compiled from: IMASDK */
final class aci extends AsyncTask<String, Void, String> {
    private final AdsRequest a;
    private final String b;
    private final /* synthetic */ ach c;

    public aci(ach ach, AdsRequest adsRequest, String str) {
        this.c = ach;
        this.a = adsRequest;
        this.b = str;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:2|3|(1:5)|(2:7|(2:9|10))|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x004e */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String doInBackground(java.lang.String... r6) {
        /*
            r5 = this;
            r0 = 0
            r6 = r6[r0]
            com.google.ads.interactivemedia.v3.internal.ach r0 = r5.c
            java.lang.Object r0 = r0.j
            monitor-enter(r0)
            com.google.ads.interactivemedia.v3.internal.ach r1 = r5.c     // Catch:{ all -> 0x005e }
            com.google.ads.interactivemedia.v3.internal.afm r1 = r1.i     // Catch:{ all -> 0x005e }
            if (r1 != 0) goto L_0x0028
            com.google.ads.interactivemedia.v3.internal.ach r1 = r5.c     // Catch:{ all -> 0x005e }
            com.google.ads.interactivemedia.v3.internal.afm r2 = new com.google.ads.interactivemedia.v3.internal.afm     // Catch:{ all -> 0x005e }
            java.lang.String r3 = "a.3.11.2"
            com.google.ads.interactivemedia.v3.internal.ach r4 = r5.c     // Catch:{ all -> 0x005e }
            android.content.Context r4 = r4.c     // Catch:{ all -> 0x005e }
            com.google.ads.interactivemedia.v3.internal.afl r3 = com.google.ads.interactivemedia.v3.internal.afl.a(r3, r4)     // Catch:{ all -> 0x005e }
            r2.<init>(r3)     // Catch:{ all -> 0x005e }
            r1.i = r2     // Catch:{ all -> 0x005e }
        L_0x0028:
            if (r6 == 0) goto L_0x004e
            android.net.Uri r1 = android.net.Uri.parse(r6)     // Catch:{ all -> 0x005e }
            com.google.ads.interactivemedia.v3.internal.ach r2 = r5.c     // Catch:{ all -> 0x005e }
            com.google.ads.interactivemedia.v3.internal.afm r2 = r2.i     // Catch:{ all -> 0x005e }
            boolean r2 = r2.a(r1)     // Catch:{ all -> 0x005e }
            if (r2 == 0) goto L_0x004e
            com.google.ads.interactivemedia.v3.internal.ach r2 = r5.c     // Catch:{ afn -> 0x004e }
            com.google.ads.interactivemedia.v3.internal.afm r2 = r2.i     // Catch:{ afn -> 0x004e }
            com.google.ads.interactivemedia.v3.internal.ach r3 = r5.c     // Catch:{ afn -> 0x004e }
            android.content.Context r3 = r3.c     // Catch:{ afn -> 0x004e }
            android.net.Uri r1 = r2.a(r1, r3)     // Catch:{ afn -> 0x004e }
            java.lang.String r6 = r1.toString()     // Catch:{ afn -> 0x004e }
        L_0x004e:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            com.google.ads.interactivemedia.v3.internal.ach r0 = r5.c
            com.google.ads.interactivemedia.v3.internal.aet r1 = new com.google.ads.interactivemedia.v3.internal.aet
            android.content.Context r2 = r0.c
            r1.<init>(r2)
            r0.k = r1
            return r6
        L_0x005e:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.aci.doInBackground(java.lang.String[]):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        this.a.setAdTagUrl((String) obj);
        this.c.b.b(new ado(adq.adsLoader, adr.requestAds, this.b, y.create(this.a, this.c.c(), this.c.d(), this.c.l, this.c.e(), aes.a(this.c.c, this.c.m), this.c.k, this.c.a(this.a))));
    }
}
