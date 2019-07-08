package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class jd {
    private final String a;
    private final int b;
    private final int c;
    private int d;
    private String e;

    public jd(int i, int i2) {
        this(Integer.MIN_VALUE, 0, 1);
    }

    public jd(int i, int i2, int i3) {
        String str;
        if (i != Integer.MIN_VALUE) {
            StringBuilder sb = new StringBuilder(12);
            sb.append(i);
            sb.append("/");
            str = sb.toString();
        } else {
            str = "";
        }
        this.a = str;
        this.b = i2;
        this.c = i3;
        this.d = Integer.MIN_VALUE;
    }

    public final void a() {
        int i = this.d;
        this.d = i == Integer.MIN_VALUE ? this.b : i + this.c;
        String str = this.a;
        int i2 = this.d;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11);
        sb.append(str);
        sb.append(i2);
        this.e = sb.toString();
    }

    public final int b() {
        d();
        return this.d;
    }

    public final String c() {
        d();
        return this.e;
    }

    private final void d() {
        if (this.d == Integer.MIN_VALUE) {
            throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
        }
    }
}
