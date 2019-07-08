package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class aec {
    private final long a;

    aec(long j) {
        this.a = j;
    }

    public final long a() {
        return this.a;
    }

    public final int hashCode() {
        return (int) this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof aec)) {
            return false;
        }
        return this.a == ((aec) obj).a;
    }

    public final String toString() {
        long j = this.a;
        StringBuilder sb = new StringBuilder(56);
        sb.append("NativeBridgeConfig [adTimeUpdateMs=");
        sb.append(j);
        sb.append("]");
        return sb.toString();
    }
}
