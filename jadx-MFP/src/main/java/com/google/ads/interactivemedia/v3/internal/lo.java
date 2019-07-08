package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class lo {
    public final Object a;
    public final int b;
    public final int c;
    public final long d;
    public final int e;

    public lo(Object obj) {
        this(obj, -1);
    }

    private lo(Object obj, long j) {
        this(obj, -1, -1, -1, -1);
    }

    public lo(Object obj, long j, int i) {
        this(obj, -1, -1, j, i);
    }

    public lo(Object obj, int i, int i2, long j) {
        this(obj, i, i2, j, -1);
    }

    private lo(Object obj, int i, int i2, long j, int i3) {
        this.a = obj;
        this.b = i;
        this.c = i2;
        this.d = j;
        this.e = i3;
    }

    public final boolean a() {
        return this.b != -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        lo loVar = (lo) obj;
        return this.a.equals(loVar.a) && this.b == loVar.b && this.c == loVar.c && this.d == loVar.d && this.e == loVar.e;
    }

    public final int hashCode() {
        return ((((((((this.a.hashCode() + 527) * 31) + this.b) * 31) + this.c) * 31) + ((int) this.d)) * 31) + this.e;
    }
}
