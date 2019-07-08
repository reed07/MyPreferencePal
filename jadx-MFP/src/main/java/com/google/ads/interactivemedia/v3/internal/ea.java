package com.google.ads.interactivemedia.v3.internal;

import android.media.AudioTrack;
import android.os.SystemClock;
import java.lang.reflect.Method;

/* compiled from: IMASDK */
final class ea {
    private final eb a;
    private final long[] b;
    private AudioTrack c;
    private int d;
    private int e;
    private dy f;
    private int g;
    private boolean h;
    private long i;
    private long j;
    private long k;
    private Method l;
    private long m;
    private boolean n;
    private boolean o;
    private long p;
    private long q;
    private long r;
    private long s;
    private int t;
    private int u;
    private long v;
    private long w;
    private long x;
    private long y;

    public ea(eb ebVar) {
        this.a = (eb) qi.a(ebVar);
        if (vf.a >= 18) {
            try {
                this.l = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused) {
            }
        }
        this.b = new long[10];
    }

    public final void a(AudioTrack audioTrack, int i2, int i3, int i4) {
        this.c = audioTrack;
        this.d = i3;
        this.e = i4;
        this.f = new dy(audioTrack);
        this.g = audioTrack.getSampleRate();
        this.h = vf.a < 23 && (i2 == 5 || i2 == 6);
        this.o = vf.c(i2);
        this.i = this.o ? g((long) (i4 / i3)) : -9223372036854775807L;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.n = false;
        this.v = -9223372036854775807L;
        this.w = -9223372036854775807L;
        this.m = 0;
    }

    public final long a(boolean z) {
        long j2;
        if (((AudioTrack) qi.a(this.c)).getPlayState() == 3) {
            long f2 = f();
            if (f2 != 0) {
                long nanoTime = System.nanoTime() / 1000;
                if (nanoTime - this.k >= 30000) {
                    long[] jArr = this.b;
                    int i2 = this.t;
                    jArr[i2] = f2 - nanoTime;
                    this.t = (i2 + 1) % 10;
                    int i3 = this.u;
                    if (i3 < 10) {
                        this.u = i3 + 1;
                    }
                    this.k = nanoTime;
                    this.j = 0;
                    int i4 = 0;
                    while (true) {
                        int i5 = this.u;
                        if (i4 >= i5) {
                            break;
                        }
                        this.j += this.b[i4] / ((long) i5);
                        i4++;
                    }
                }
                if (!this.h) {
                    dy dyVar = (dy) qi.a(this.f);
                    if (dyVar.a(nanoTime)) {
                        long f3 = dyVar.f();
                        long g2 = dyVar.g();
                        if (Math.abs(f3 - nanoTime) > 5000000) {
                            this.a.b(g2, f3, nanoTime, f2);
                            dyVar.a();
                        } else if (Math.abs(g(g2) - f2) > 5000000) {
                            this.a.a(g2, f3, nanoTime, f2);
                            dyVar.a();
                        } else {
                            dyVar.b();
                        }
                    }
                    f(nanoTime);
                }
            }
        }
        long nanoTime2 = System.nanoTime() / 1000;
        dy dyVar2 = (dy) qi.a(this.f);
        if (dyVar2.c()) {
            long g3 = g(dyVar2.g());
            if (!dyVar2.d()) {
                return g3;
            }
            return g3 + (nanoTime2 - dyVar2.f());
        }
        if (this.u == 0) {
            j2 = f();
        } else {
            j2 = this.j + nanoTime2;
        }
        if (!z) {
            j2 -= this.m;
        }
        return j2;
    }

    public final void a() {
        ((dy) qi.a(this.f)).e();
    }

    public final boolean b() {
        return ((AudioTrack) qi.a(this.c)).getPlayState() == 3;
    }

    public final boolean a(long j2) {
        int playState = ((AudioTrack) qi.a(this.c)).getPlayState();
        if (this.h) {
            if (playState == 2) {
                this.n = false;
                return false;
            } else if (playState == 1 && g() == 0) {
                return false;
            }
        }
        boolean z = this.n;
        this.n = e(j2);
        if (z && !this.n && playState != 1) {
            eb ebVar = this.a;
            if (ebVar != null) {
                ebVar.a(this.e, at.a(this.i));
            }
        }
        return true;
    }

    public final int b(long j2) {
        return this.e - ((int) (j2 - (g() * ((long) this.d))));
    }

    public final boolean c(long j2) {
        return this.w != -9223372036854775807L && j2 > 0 && SystemClock.elapsedRealtime() - this.w >= 200;
    }

    public final void d(long j2) {
        this.x = g();
        this.v = SystemClock.elapsedRealtime() * 1000;
        this.y = j2;
    }

    public final boolean e(long j2) {
        if (j2 <= g()) {
            if (this.h && ((AudioTrack) qi.a(this.c)).getPlayState() == 2 && g() == 0) {
                return true;
            }
            return false;
        }
        return true;
    }

    public final boolean c() {
        e();
        if (this.v != -9223372036854775807L) {
            return false;
        }
        ((dy) qi.a(this.f)).e();
        return true;
    }

    public final void d() {
        e();
        this.c = null;
        this.f = null;
    }

    private final void f(long j2) {
        if (this.o) {
            Method method = this.l;
            if (method != null && j2 - this.p >= 500000) {
                try {
                    this.m = (((long) ((Integer) vf.a((Integer) method.invoke(qi.a(this.c), new Object[0]))).intValue()) * 1000) - this.i;
                    this.m = Math.max(this.m, 0);
                    if (this.m > 5000000) {
                        this.a.a(this.m);
                        this.m = 0;
                    }
                } catch (Exception unused) {
                    this.l = null;
                }
                this.p = j2;
            }
        }
    }

    private final long g(long j2) {
        return (j2 * 1000000) / ((long) this.g);
    }

    private final void e() {
        this.j = 0;
        this.u = 0;
        this.t = 0;
        this.k = 0;
    }

    private final long f() {
        return g(g());
    }

    private final long g() {
        AudioTrack audioTrack = (AudioTrack) qi.a(this.c);
        if (this.v != -9223372036854775807L) {
            return Math.min(this.y, this.x + ((((SystemClock.elapsedRealtime() * 1000) - this.v) * ((long) this.g)) / 1000000));
        }
        int playState = audioTrack.getPlayState();
        if (playState == 1) {
            return 0;
        }
        long playbackHeadPosition = 4294967295L & ((long) audioTrack.getPlaybackHeadPosition());
        if (this.h) {
            if (playState == 2 && playbackHeadPosition == 0) {
                this.s = this.q;
            }
            playbackHeadPosition += this.s;
        }
        if (vf.a <= 28) {
            if (playbackHeadPosition == 0 && this.q > 0 && playState == 3) {
                if (this.w == -9223372036854775807L) {
                    this.w = SystemClock.elapsedRealtime();
                }
                return this.q;
            }
            this.w = -9223372036854775807L;
        }
        if (this.q > playbackHeadPosition) {
            this.r++;
        }
        this.q = playbackHeadPosition;
        return playbackHeadPosition + (this.r << 32);
    }
}
