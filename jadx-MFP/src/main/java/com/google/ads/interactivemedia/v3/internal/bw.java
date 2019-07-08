package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.DefaultLoadControl;

/* compiled from: IMASDK */
public class bw {
    private final sf a;
    private final long b;
    private final long c;
    private final long d;
    private final long e;
    private final int f;
    private final boolean g;
    private final uw h;
    private final long i;
    private final boolean j;
    private int k;
    private boolean l;

    public void a() {
        a(false);
    }

    public void a(ci[] ciVarArr, rw rwVar) {
        int i2 = this.f;
        if (i2 == -1) {
            i2 = b(ciVarArr, rwVar);
        }
        this.k = i2;
        this.a.a(this.k);
    }

    public void b() {
        a(true);
    }

    public void c() {
        a(true);
    }

    public sf d() {
        return this.a;
    }

    public long e() {
        return this.i;
    }

    public boolean f() {
        return this.j;
    }

    public boolean a(long j2, float f2) {
        boolean z = true;
        boolean z2 = this.a.e() >= this.k;
        boolean z3 = this.l;
        long j3 = this.b;
        if (f2 > 1.0f) {
            j3 = Math.min(vf.a(j3, f2), this.c);
        }
        if (j2 < j3) {
            if (!this.g && z2) {
                z = false;
            }
            this.l = z;
        } else if (j2 >= this.c || z2) {
            this.l = false;
        }
        if (this.h != null) {
            boolean z4 = this.l;
            if (z4 != z3) {
                if (z4) {
                    throw new NoSuchMethodError();
                }
                throw new NoSuchMethodError();
            }
        }
        return this.l;
    }

    public boolean a(long j2, float f2, boolean z) {
        long b2 = vf.b(j2, f2);
        long j3 = z ? this.e : this.d;
        return j3 <= 0 || b2 >= j3 || (!this.g && this.a.e() >= this.k);
    }

    public bw() {
        this(new sf(true, 65536));
    }

    @Deprecated
    public bw(sf sfVar) {
        this(sfVar, 15000, DefaultLoadControl.DEFAULT_MAX_BUFFER_MS, 2500, 5000, -1, true);
    }

    @Deprecated
    public bw(sf sfVar, int i2, int i3, int i4, int i5, int i6, boolean z) {
        this(sfVar, 15000, DefaultLoadControl.DEFAULT_MAX_BUFFER_MS, 2500, 5000, -1, true, null);
    }

    @Deprecated
    public bw(sf sfVar, int i2, int i3, int i4, int i5, int i6, boolean z, uw uwVar) {
        this(sfVar, i2, i3, i4, i5, i6, z, null, 0, false);
    }

    protected bw(sf sfVar, int i2, int i3, int i4, int i5, int i6, boolean z, uw uwVar, int i7, boolean z2) {
        a(i4, 0, "bufferForPlaybackMs", "0");
        a(i5, 0, "bufferForPlaybackAfterRebufferMs", "0");
        a(i2, i4, "minBufferMs", "bufferForPlaybackMs");
        a(i2, i5, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        a(i3, i2, "maxBufferMs", "minBufferMs");
        a(0, 0, "backBufferDurationMs", "0");
        this.a = sfVar;
        this.b = at.b((long) i2);
        this.c = at.b((long) i3);
        this.d = at.b((long) i4);
        this.e = at.b((long) i5);
        this.f = i6;
        this.g = z;
        this.h = uwVar;
        this.i = at.b(0);
        this.j = false;
    }

    protected static int b(ci[] ciVarArr, rw rwVar) {
        int i2 = 0;
        for (int i3 = 0; i3 < ciVarArr.length; i3++) {
            if (rwVar.a(i3) != null) {
                i2 += vf.g(ciVarArr[i3].a());
            }
        }
        return i2;
    }

    private void a(boolean z) {
        this.k = 0;
        if (this.h == null || !this.l) {
            this.l = false;
            if (z) {
                this.a.d();
                return;
            }
            return;
        }
        throw new NoSuchMethodError();
    }

    private static void a(int i2, int i3, String str, String str2) {
        boolean z = i2 >= i3;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" cannot be less than ");
        sb.append(str2);
        qi.a(z, (Object) sb.toString());
    }
}
