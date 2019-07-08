package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class by {
    public final lo a;
    public final long b;
    public final long c;
    public final long d;
    public final long e;
    public final boolean f;
    public final boolean g;

    by(lo loVar, long j, long j2, long j3, long j4, boolean z, boolean z2) {
        this.a = loVar;
        this.b = j;
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = z;
        this.g = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        by byVar = (by) obj;
        return this.b == byVar.b && this.c == byVar.c && this.d == byVar.d && this.e == byVar.e && this.f == byVar.f && this.g == byVar.g && vf.a((Object) this.a, (Object) byVar.a);
    }

    public final int hashCode() {
        return ((((((((((((this.a.hashCode() + 527) * 31) + ((int) this.b)) * 31) + ((int) this.c)) * 31) + ((int) this.d)) * 31) + ((int) this.e)) * 31) + (this.f ? 1 : 0)) * 31) + (this.g ? 1 : 0);
    }
}
