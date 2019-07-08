package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.util.Base64;
import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.net.URLDecoder;

/* compiled from: IMASDK */
public final class sm extends sj {
    private sr a;
    private int b;
    private byte[] c;

    public sm() {
        super(false);
    }

    public final long a(sr srVar) throws IOException {
        b(srVar);
        this.a = srVar;
        Uri uri = srVar.a;
        String scheme = uri.getScheme();
        if (!"data".equals(scheme)) {
            String str = "Unsupported scheme: ";
            String valueOf = String.valueOf(scheme);
            throw new ca(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        String[] a2 = vf.a(uri.getSchemeSpecificPart(), ",");
        if (a2.length == 2) {
            String str2 = a2[1];
            if (a2[0].contains(";base64")) {
                try {
                    this.c = Base64.decode(str2, 0);
                } catch (IllegalArgumentException e) {
                    String str3 = "Error while parsing Base64 encoded string: ";
                    String valueOf2 = String.valueOf(str2);
                    throw new ca(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3), e);
                }
            } else {
                this.c = vf.c(URLDecoder.decode(str2, C.ASCII_NAME));
            }
            c(srVar);
            return (long) this.c.length;
        }
        String valueOf3 = String.valueOf(uri);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf3).length() + 23);
        sb.append("Unexpected URI format: ");
        sb.append(valueOf3);
        throw new ca(sb.toString());
    }

    public final int a(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int length = this.c.length - this.b;
        if (length == 0) {
            return -1;
        }
        int min = Math.min(i2, length);
        System.arraycopy(this.c, this.b, bArr, i, min);
        this.b += min;
        a(min);
        return min;
    }

    public final Uri a() {
        sr srVar = this.a;
        if (srVar != null) {
            return srVar.a;
        }
        return null;
    }

    public final void c() throws IOException {
        if (this.c != null) {
            this.c = null;
            d();
        }
        this.a = null;
    }
}
