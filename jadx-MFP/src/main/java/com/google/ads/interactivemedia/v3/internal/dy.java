package com.google.ads.interactivemedia.v3.internal;

import android.media.AudioTrack;
import com.google.android.exoplayer2.DefaultRenderersFactory;

/* compiled from: IMASDK */
final class dy {
    private final dz a;
    private int b;
    private long c;
    private long d;
    private long e;
    private long f;

    public dy(AudioTrack audioTrack) {
        if (vf.a >= 19) {
            this.a = new dz(audioTrack);
            e();
            return;
        }
        this.a = null;
        a(3);
    }

    public final boolean a(long j) {
        dz dzVar = this.a;
        if (dzVar == null || j - this.e < this.d) {
            return false;
        }
        this.e = j;
        boolean a2 = dzVar.a();
        switch (this.b) {
            case 0:
                if (!a2) {
                    if (j - this.c > 500000) {
                        a(3);
                        break;
                    }
                } else if (this.a.b() < this.c) {
                    a2 = false;
                    break;
                } else {
                    this.f = this.a.c();
                    a(1);
                    break;
                }
                break;
            case 1:
                if (a2) {
                    if (this.a.c() > this.f) {
                        a(2);
                        break;
                    }
                } else {
                    e();
                    break;
                }
                break;
            case 2:
                if (!a2) {
                    e();
                    break;
                }
                break;
            case 3:
                if (a2) {
                    e();
                    break;
                }
                break;
            case 4:
                break;
            default:
                throw new IllegalStateException();
        }
        return a2;
    }

    public final void a() {
        a(4);
    }

    public final void b() {
        if (this.b == 4) {
            e();
        }
    }

    public final boolean c() {
        int i = this.b;
        return i == 1 || i == 2;
    }

    public final boolean d() {
        return this.b == 2;
    }

    public final void e() {
        if (this.a != null) {
            a(0);
        }
    }

    public final long f() {
        dz dzVar = this.a;
        if (dzVar != null) {
            return dzVar.b();
        }
        return -9223372036854775807L;
    }

    public final long g() {
        dz dzVar = this.a;
        if (dzVar != null) {
            return dzVar.c();
        }
        return -1;
    }

    private final void a(int i) {
        this.b = i;
        switch (i) {
            case 0:
                this.e = 0;
                this.f = -1;
                this.c = System.nanoTime() / 1000;
                this.d = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
                return;
            case 1:
                this.d = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
                return;
            case 2:
            case 3:
                this.d = 10000000;
                return;
            case 4:
                this.d = 500000;
                return;
            default:
                throw new IllegalStateException();
        }
    }
}
