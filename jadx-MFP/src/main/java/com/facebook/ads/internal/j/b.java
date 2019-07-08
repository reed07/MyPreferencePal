package com.facebook.ads.internal.j;

public class b {
    public final int a;
    public final String b;
    public final String c;

    public b(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append(" ");
        sb.append(this.c);
        return sb.toString();
    }
}
