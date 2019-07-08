package com.google.ads.interactivemedia.v3.internal;

import android.os.AsyncTask;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.impl.data.y;

/* compiled from: IMASDK */
final class ack extends AsyncTask<Void, Void, String> {
    private final StreamRequest a;
    private final String b;
    private final /* synthetic */ ach c;

    public ack(ach ach, StreamRequest streamRequest, String str) {
        this.c = ach;
        this.a = streamRequest;
        this.b = str;
    }

    private final String a() {
        String a2;
        synchronized (this.c.j) {
            if (this.c.i == null) {
                this.c.i = new afm(afl.a("a.3.11.2", this.c.c));
            }
            a2 = this.c.i.a().a(this.c.c);
        }
        ach ach = this.c;
        ach.k = new aet(ach.c);
        return a2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        this.c.b.b(new ado(adq.adsLoader, adr.requestStream, this.b, y.createFromStreamRequest(this.a, this.c.c(), this.c.d(), this.c.l, this.c.e(), aes.a(this.c.c, this.c.m), (String) obj, this.c.k, this.c.a(this.a))));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }
}
