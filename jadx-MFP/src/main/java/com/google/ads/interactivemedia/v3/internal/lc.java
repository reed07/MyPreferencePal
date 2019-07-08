package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class lc implements Comparable<lc> {
    public final int a = -1;
    public final int b = -1;
    public final int c = -1;

    public lc(int i, int i2, int i3) {
    }

    public final String toString() {
        int i = this.a;
        int i2 = this.b;
        int i3 = this.c;
        StringBuilder sb = new StringBuilder(35);
        sb.append(i);
        sb.append(".");
        sb.append(i2);
        sb.append(".");
        sb.append(i3);
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        lc lcVar = (lc) obj;
        return this.a == lcVar.a && this.b == lcVar.b && this.c == lcVar.c;
    }

    public final int hashCode() {
        return (((this.a * 31) + this.b) * 31) + this.c;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        lc lcVar = (lc) obj;
        int i = this.a - lcVar.a;
        if (i != 0) {
            return i;
        }
        int i2 = this.b - lcVar.b;
        return i2 == 0 ? this.c - lcVar.c : i2;
    }
}
