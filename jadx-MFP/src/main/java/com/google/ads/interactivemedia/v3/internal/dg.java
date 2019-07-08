package com.google.ads.interactivemedia.v3.internal;

import android.media.AudioManager.OnAudioFocusChangeListener;
import android.util.Log;

/* compiled from: IMASDK */
final class dg implements OnAudioFocusChangeListener {
    private final /* synthetic */ df a;

    private dg(df dfVar) {
        this.a = dfVar;
    }

    public final void onAudioFocusChange(int i) {
        if (i != 1) {
            switch (i) {
                case -3:
                    if (!this.a.d()) {
                        this.a.d = 3;
                        break;
                    } else {
                        this.a.d = 2;
                        break;
                    }
                case -2:
                    this.a.d = 2;
                    break;
                case -1:
                    this.a.d = -1;
                    break;
                default:
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unknown focus change type: ");
                    sb.append(i);
                    Log.w("AudioFocusManager", sb.toString());
                    return;
            }
        } else {
            this.a.d = 1;
        }
        switch (this.a.d) {
            case -1:
                this.a.c.b(-1);
                this.a.b(true);
                break;
            case 0:
            case 3:
                break;
            case 1:
                this.a.c.b(1);
                break;
            case 2:
                this.a.c.b(0);
                break;
            default:
                int b = this.a.d;
                StringBuilder sb2 = new StringBuilder(38);
                sb2.append("Unknown audio focus state: ");
                sb2.append(b);
                throw new IllegalStateException(sb2.toString());
        }
        float f = this.a.d == 3 ? 0.2f : 1.0f;
        if (this.a.e != f) {
            this.a.e = f;
            this.a.c.a();
        }
    }

    /* synthetic */ dg(df dfVar, byte b) {
        this(dfVar);
    }
}
