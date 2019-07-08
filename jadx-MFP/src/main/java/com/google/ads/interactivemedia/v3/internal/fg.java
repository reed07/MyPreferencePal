package com.google.ads.interactivemedia.v3.internal;

import java.util.UUID;

/* compiled from: IMASDK */
public class fg {
    public final UUID a;
    public final byte[] b;
    public final boolean c;
    private final String d;
    private final String e;

    private fg(String str, String str2) {
        this.d = str;
        this.e = str2;
    }

    public static fg a(String str, String str2) {
        ho.a(str, "Name is null or empty");
        ho.a(str2, "Version is null or empty");
        return new fg(str, str2);
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }
}
