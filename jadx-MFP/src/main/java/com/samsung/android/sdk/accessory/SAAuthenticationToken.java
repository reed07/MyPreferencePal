package com.samsung.android.sdk.accessory;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class SAAuthenticationToken {
    public static final int AUTHENTICATION_TYPE_CERTIFICATE_X509 = 1548;
    public static final int AUTHENTICATION_TYPE_NONE = 1547;
    private byte[] a;
    private int b;

    SAAuthenticationToken(int i, byte[] bArr) {
        if (bArr != null) {
            this.a = Arrays.copyOf(bArr, bArr.length);
        }
        this.b = i;
    }

    public int getAuthenticationType() {
        return this.b;
    }

    public byte[] getKey() {
        return this.a;
    }

    public String toString() {
        String str;
        try {
            str = this.a != null ? new String(this.a, "ISO-8859-1") : null;
        } catch (UnsupportedEncodingException unused) {
            str = "Failed to encode Key!! charsetISO-8859-1 is not supported";
        }
        StringBuilder sb = new StringBuilder("Type:");
        sb.append(this.b);
        sb.append(" Key:");
        sb.append(str);
        return sb.toString();
    }
}
