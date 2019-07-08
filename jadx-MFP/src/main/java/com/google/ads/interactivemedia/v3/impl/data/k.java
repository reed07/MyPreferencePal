package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
final class k extends x {
    private final double end;
    private final boolean played;
    private final double start;

    k(double d, double d2, boolean z) {
        this.start = d;
        this.end = d2;
        this.played = z;
    }

    public final double start() {
        return this.start;
    }

    public final double end() {
        return this.end;
    }

    public final boolean played() {
        return this.played;
    }

    public final String toString() {
        double d = this.start;
        double d2 = this.end;
        boolean z = this.played;
        StringBuilder sb = new StringBuilder(88);
        sb.append("CuePointData{start=");
        sb.append(d);
        sb.append(", end=");
        sb.append(d2);
        sb.append(", played=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return Double.doubleToLongBits(this.start) == Double.doubleToLongBits(xVar.start()) && Double.doubleToLongBits(this.end) == Double.doubleToLongBits(xVar.end()) && this.played == xVar.played();
    }

    public final int hashCode() {
        return (this.played ? 1231 : 1237) ^ ((((((int) ((Double.doubleToLongBits(this.start) >>> 32) ^ Double.doubleToLongBits(this.start))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.end) >>> 32) ^ Double.doubleToLongBits(this.end)))) * 1000003);
    }
}
