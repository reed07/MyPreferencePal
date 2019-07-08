package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class fl {
    public static final fl a;
    /* access modifiers changed from: private */
    public final int b;
    /* access modifiers changed from: private */
    public final long c;
    /* access modifiers changed from: private */
    public final long d;

    private fl(int i, long j, long j2) {
        this.b = i;
        this.c = j;
        this.d = j2;
    }

    public static fl a(long j, long j2) {
        fl flVar = new fl(-1, j, j2);
        return flVar;
    }

    public static fl b(long j, long j2) {
        fl flVar = new fl(-2, j, j2);
        return flVar;
    }

    public static fl a(long j) {
        fl flVar = new fl(0, -9223372036854775807L, j);
        return flVar;
    }

    static {
        fl flVar = new fl(-3, -9223372036854775807L, -1);
        a = flVar;
    }
}
