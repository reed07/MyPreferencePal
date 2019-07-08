package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class qq implements Comparable<Long> {
    public final String a;
    public final qq b;
    public final long c;
    public final int d;
    public final long e;
    public final fa f;
    public final String g;
    public final String h;
    public final long i;
    public final long j;
    public final boolean k;

    public qq(String str, long j2, long j3, String str2, String str3) {
        String str4 = "";
        this(str, null, str4, 0, -1, -9223372036854775807L, null, str2, str3, j2, j3, false);
    }

    public qq(String str, qq qqVar, String str2, long j2, int i2, long j3, fa faVar, String str3, String str4, long j4, long j5, boolean z) {
        this.a = str;
        this.b = qqVar;
        this.c = j2;
        this.d = i2;
        this.e = j3;
        this.f = faVar;
        this.g = str3;
        this.h = str4;
        this.i = j4;
        this.j = j5;
        this.k = z;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        Long l = (Long) obj;
        if (this.e > l.longValue()) {
            return 1;
        }
        return this.e < l.longValue() ? -1 : 0;
    }
}
