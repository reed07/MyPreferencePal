package com.google.android.gms.internal.measurement;

import com.google.android.gms.tagmanager.zzdi;
import com.google.firebase.appindexing.Indexable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

final class zzrq implements zzrr {
    private HttpURLConnection zzbor;
    private InputStream zzbos = null;

    zzrq() {
    }

    public final InputStream zzez(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(Indexable.MAX_STRING_LENGTH);
        httpURLConnection.setConnectTimeout(Indexable.MAX_STRING_LENGTH);
        this.zzbor = httpURLConnection;
        HttpURLConnection httpURLConnection2 = this.zzbor;
        int responseCode = httpURLConnection2.getResponseCode();
        if (responseCode == 200) {
            this.zzbos = httpURLConnection2.getInputStream();
            return this.zzbos;
        }
        StringBuilder sb = new StringBuilder(25);
        sb.append("Bad response: ");
        sb.append(responseCode);
        String sb2 = sb.toString();
        if (responseCode == 404) {
            throw new FileNotFoundException(sb2);
        } else if (responseCode == 503) {
            throw new zzrt(sb2);
        } else {
            throw new IOException(sb2);
        }
    }

    public final void close() {
        HttpURLConnection httpURLConnection = this.zzbor;
        try {
            if (this.zzbos != null) {
                this.zzbos.close();
            }
        } catch (IOException e) {
            String str = "HttpUrlConnectionNetworkClient: Error when closing http input stream: ";
            String valueOf = String.valueOf(e.getMessage());
            zzdi.zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e);
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }
}
