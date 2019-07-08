package com.inmobi.ads;

import android.media.MediaPlayer;

/* compiled from: NativeMediaPlayer */
public final class av extends MediaPlayer {
    private static final Object d = new Object();
    private static av e;
    private static int f = 0;
    int a = 0;
    int b = 0;
    private av c;

    public static av a() {
        synchronized (d) {
            if (e == null) {
                return new av();
            }
            av avVar = e;
            e = avVar.c;
            avVar.c = null;
            f--;
            return avVar;
        }
    }

    public final void b() {
        if (!(3 == this.a)) {
            synchronized (d) {
                if (f < 5) {
                    this.c = e;
                    e = this;
                    f++;
                }
            }
        }
    }
}
