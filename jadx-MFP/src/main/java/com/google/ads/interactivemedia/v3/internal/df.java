package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: IMASDK */
public final class df {
    private final AudioManager a;
    private final dg b;
    /* access modifiers changed from: private */
    public final dh c;
    /* access modifiers changed from: private */
    public int d;
    /* access modifiers changed from: private */
    public float e = 1.0f;
    private AudioFocusRequest f;

    public df(Context context, dh dhVar) {
        this.a = (AudioManager) context.getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.c = dhVar;
        this.b = new dg(this, 0);
        this.d = 0;
    }

    /* access modifiers changed from: private */
    public final boolean d() {
        return false;
    }

    public final float a() {
        return this.e;
    }

    public final int a(boolean z) {
        if (z) {
            return c();
        }
        return -1;
    }

    public final int a(boolean z, int i) {
        if (!z) {
            b(false);
            return -1;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = c();
        } else if (z) {
            return i2;
        } else {
            return -1;
        }
        return i2;
    }

    public final void b() {
        b(true);
    }

    private final int c() {
        if (this.d != 0) {
            b(true);
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public final void b(boolean z) {
        if (this.d != 0) {
            if (vf.a >= 26) {
                AudioFocusRequest audioFocusRequest = this.f;
                if (audioFocusRequest != null) {
                    this.a.abandonAudioFocusRequest(audioFocusRequest);
                }
            } else {
                this.a.abandonAudioFocus(this.b);
            }
            this.d = 0;
        }
    }
}
