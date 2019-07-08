package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class ck {
    public static final ck a = new ck(0);
    public final int b;

    public ck(int i) {
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b == ((ck) obj).b;
    }

    public final int hashCode() {
        return this.b;
    }
}
