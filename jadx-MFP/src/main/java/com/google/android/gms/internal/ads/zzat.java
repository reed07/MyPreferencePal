package com.google.android.gms.internal.ads;

import java.io.FilterInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

final class zzat extends FilterInputStream {
    private final HttpURLConnection zzck;

    zzat(HttpURLConnection httpURLConnection) {
        super(zzas.zza(httpURLConnection));
        this.zzck = httpURLConnection;
    }

    public final void close() throws IOException {
        super.close();
        this.zzck.disconnect();
    }
}
