package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class gb {
    public static final gb a = new gb(0, 0);
    public final long b;
    public final long c;

    public gb(long j, long j2) {
        this.b = j;
        this.c = j2;
    }

    public final String toString() {
        long j = this.b;
        long j2 = this.c;
        StringBuilder sb = new StringBuilder(60);
        sb.append("[timeUs=");
        sb.append(j);
        sb.append(", position=");
        sb.append(j2);
        sb.append("]");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gb gbVar = (gb) obj;
        return this.b == gbVar.b && this.c == gbVar.c;
    }

    public final int hashCode() {
        return (((int) this.b) * 31) + ((int) this.c);
    }
}
