package com.inmobi.rendering.a;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Click */
public final class a {
    int a;
    public String b;
    public Map<String, String> c;
    long d;
    long e;
    int f;
    AtomicBoolean g;
    boolean h;
    boolean i;

    a(String str, boolean z, boolean z2, int i2) {
        this(new Random().nextInt() & Integer.MAX_VALUE, str, new HashMap(), z, z2, i2, System.currentTimeMillis(), System.currentTimeMillis());
    }

    a(String str, Map<String, String> map, boolean z, int i2) {
        this(new Random().nextInt() & Integer.MAX_VALUE, str, map, z, false, i2, System.currentTimeMillis(), System.currentTimeMillis());
    }

    a(int i2, String str, Map<String, String> map, boolean z, boolean z2, int i3, long j, long j2) {
        this.a = i2;
        this.b = str;
        this.c = map;
        this.d = j;
        this.e = j2;
        this.f = i3;
        this.g = new AtomicBoolean(false);
        this.i = z;
        this.h = z2;
    }

    public final boolean a(long j) {
        return System.currentTimeMillis() - this.e > j * 1000;
    }
}
