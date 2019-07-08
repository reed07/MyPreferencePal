package com.google.ads.interactivemedia.v3.internal;

import android.media.AudioTrack;

/* compiled from: IMASDK */
final class eh extends Thread {
    private final /* synthetic */ AudioTrack a;

    eh(AudioTrack audioTrack) {
        this.a = audioTrack;
    }

    public final void run() {
        this.a.release();
    }
}
