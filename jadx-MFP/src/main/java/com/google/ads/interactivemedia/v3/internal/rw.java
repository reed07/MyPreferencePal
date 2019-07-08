package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

/* compiled from: IMASDK */
public final class rw {
    public final int a;
    private final rt[] b;
    private int c;

    public rw(rt... rtVarArr) {
        this.b = rtVarArr;
        this.a = rtVarArr.length;
    }

    public final rt a(int i) {
        return this.b[i];
    }

    public final rt[] a() {
        return (rt[]) this.b.clone();
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b) + 527;
        }
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.b, ((rw) obj).b);
    }
}
