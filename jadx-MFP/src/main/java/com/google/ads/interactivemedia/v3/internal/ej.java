package com.google.ads.interactivemedia.v3.internal;

import android.media.AudioAttributes;
import android.media.AudioAttributes.Builder;
import android.media.AudioFormat;
import android.media.AudioTrack;

/* compiled from: IMASDK */
final class ej {
    public final boolean a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final boolean i;
    public final boolean j;
    public final dj[] k;

    public ej(boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z2, boolean z3, dj[] djVarArr) {
        this.a = z;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        if (i8 == 0) {
            if (this.a) {
                int minBufferSize = AudioTrack.getMinBufferSize(this.e, this.f, this.g);
                qi.c(minBufferSize != -2);
                i8 = vf.a(minBufferSize << 2, ((int) b(250000)) * this.d, (int) Math.max((long) minBufferSize, b(750000) * ((long) this.d)));
            } else {
                int b2 = ef.c(this.g);
                if (this.g == 5) {
                    b2 <<= 1;
                }
                i8 = (int) ((((long) b2) * 250000) / 1000000);
            }
        }
        this.h = i8;
        this.i = z2;
        this.j = z3;
        this.k = djVarArr;
    }

    public final long a(long j2) {
        return (j2 * 1000000) / ((long) this.e);
    }

    private final long b(long j2) {
        return (j2 * ((long) this.e)) / 1000000;
    }

    public final AudioTrack a(boolean z, dc dcVar, int i2) throws dv {
        AudioTrack audioTrack;
        AudioAttributes audioAttributes;
        if (vf.a >= 21) {
            if (z) {
                audioAttributes = new Builder().setContentType(3).setFlags(16).setUsage(1).build();
            } else {
                audioAttributes = dcVar.a();
            }
            audioTrack = new AudioTrack(audioAttributes, new AudioFormat.Builder().setChannelMask(this.f).setEncoding(this.g).setSampleRate(this.e).build(), this.h, 1, i2 != 0 ? i2 : 0);
        } else {
            int f2 = vf.f(dcVar.c);
            if (i2 == 0) {
                audioTrack = new AudioTrack(f2, this.e, this.f, this.g, this.h, 1);
            } else {
                audioTrack = new AudioTrack(f2, this.e, this.f, this.g, this.h, 1, i2);
            }
        }
        int state = audioTrack.getState();
        if (state == 1) {
            return audioTrack;
        }
        try {
            audioTrack.release();
        } catch (Exception unused) {
        }
        throw new dv(state, this.e, this.f, this.h);
    }
}
