package com.google.android.gms.internal.ads;

import android.media.AudioTrack;

final class zzgt extends Thread {
    private final /* synthetic */ AudioTrack zzadw;
    private final /* synthetic */ zzgs zzadx;

    zzgt(zzgs zzgs, AudioTrack audioTrack) {
        this.zzadx = zzgs;
        this.zzadw = audioTrack;
    }

    public final void run() {
        try {
            this.zzadw.flush();
            this.zzadw.release();
        } finally {
            this.zzadx.zzacb.open();
        }
    }
}
