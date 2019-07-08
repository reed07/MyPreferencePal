package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class cm {
    public static final cm a = new cm(0, 0);
    public static final cm b = a;
    public final long c;
    public final long d;

    public cm(long j, long j2) {
        boolean z = true;
        qi.b(j >= 0);
        if (j2 < 0) {
            z = false;
        }
        qi.b(z);
        this.c = j;
        this.d = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        cm cmVar = (cm) obj;
        return this.c == cmVar.c && this.d == cmVar.d;
    }

    public final int hashCode() {
        return (((int) this.c) * 31) + ((int) this.d);
    }

    static {
        new cm(Long.MAX_VALUE, Long.MAX_VALUE);
        new cm(Long.MAX_VALUE, 0);
        new cm(0, Long.MAX_VALUE);
    }
}
