package com.inmobi.commons.core.network;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/* compiled from: NetworkResponse */
public class d {
    private static final String e = "d";
    public byte[] a;
    public NetworkError b;
    public int c;
    public Map<String, List<String>> d;
    private String f;

    public final boolean a() {
        return this.b != null;
    }

    public final String b() {
        if (this.f == null) {
            this.f = a(this.a);
        }
        return this.f;
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public final void b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            this.a = new byte[0];
            return;
        }
        this.a = new byte[bArr.length];
        System.arraycopy(bArr, 0, this.a, 0, bArr.length);
    }

    public final long c() {
        try {
            if (this.f != null) {
                return ((long) this.f.length()) + 0;
            }
            return 0;
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in computing response size; ").append(e2.getMessage());
            return 0;
        }
    }
}
