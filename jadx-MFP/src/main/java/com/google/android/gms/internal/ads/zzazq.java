package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

public final class zzazq {
    public final int count;
    public final String name;
    private final double zzemq;
    private final double zzemr;
    public final double zzems;

    public zzazq(String str, double d, double d2, double d3, int i) {
        this.name = str;
        this.zzemr = d;
        this.zzemq = d2;
        this.zzems = d3;
        this.count = i;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("minBound", Double.valueOf(this.zzemr)).add("maxBound", Double.valueOf(this.zzemq)).add("percent", Double.valueOf(this.zzems)).add("count", Integer.valueOf(this.count)).toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzazq)) {
            return false;
        }
        zzazq zzazq = (zzazq) obj;
        if (Objects.equal(this.name, zzazq.name) && this.zzemq == zzazq.zzemq && this.zzemr == zzazq.zzemr && this.count == zzazq.count && Double.compare(this.zzems, zzazq.zzems) == 0) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, Double.valueOf(this.zzemq), Double.valueOf(this.zzemr), Double.valueOf(this.zzems), Integer.valueOf(this.count));
    }
}
