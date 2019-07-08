package com.google.ads.interactivemedia.v3.internal;

import android.media.AudioTrack;

/* compiled from: IMASDK */
final class eg extends Thread {
    private final /* synthetic */ AudioTrack a;
    private final /* synthetic */ ef b;

    eg(ef efVar, AudioTrack audioTrack) {
        this.b = efVar;
        this.a = audioTrack;
    }

    public final void run() {
        try {
            this.a.flush();
            this.a.release();
        } finally {
            this.b.j.open();
        }
    }
}
