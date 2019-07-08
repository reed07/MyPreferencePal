package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class j implements Serializable {
    private static final long serialVersionUID = -2102939945352398575L;
    private final byte[] a;
    private final String b;
    private final List<String> c;
    private String d;

    j(byte[] bArr, String str, List<String> list) {
        this.a = bArr;
        this.b = str;
        this.c = list;
    }

    public String a() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public void a(String str) {
        this.d = str;
    }

    public byte[] b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public List<String> d() {
        return Collections.unmodifiableList(this.c);
    }
}
