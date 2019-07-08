package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;

@TargetApi(19)
/* compiled from: IMASDK */
final class dz {
    private final AudioTrack a;
    private final AudioTimestamp b = new AudioTimestamp();
    private long c;
    private long d;
    private long e;

    public dz(AudioTrack audioTrack) {
        this.a = audioTrack;
    }

    public final boolean a() {
        boolean timestamp = this.a.getTimestamp(this.b);
        if (timestamp) {
            long j = this.b.framePosition;
            if (this.d > j) {
                this.c++;
            }
            this.d = j;
            this.e = j + (this.c << 32);
        }
        return timestamp;
    }

    public final long b() {
        return this.b.nanoTime / 1000;
    }

    public final long c() {
        return this.e;
    }
}
