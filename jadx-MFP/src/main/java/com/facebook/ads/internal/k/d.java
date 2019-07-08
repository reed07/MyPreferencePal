package com.facebook.ads.internal.k;

import java.util.Map;

public class d {
    private double a;
    private double b = (((double) System.currentTimeMillis()) / 1000.0d);
    private String c;
    private Map<String, String> d;

    public d(double d2, String str, Map<String, String> map) {
        this.a = d2;
        this.c = str;
        this.d = map;
    }

    public String a() {
        return "debug";
    }

    public double b() {
        return this.b;
    }

    public double c() {
        return this.a;
    }

    public String d() {
        return this.c;
    }

    public Map<String, String> e() {
        return this.d;
    }
}
