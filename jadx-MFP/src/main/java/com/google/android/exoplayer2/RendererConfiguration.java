package com.google.android.exoplayer2;

import android.support.annotation.Nullable;

public final class RendererConfiguration {
    public static final RendererConfiguration DEFAULT = new RendererConfiguration(0);
    public final int tunnelingAudioSessionId;

    public RendererConfiguration(int i) {
        this.tunnelingAudioSessionId = i;
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.tunnelingAudioSessionId != ((RendererConfiguration) obj).tunnelingAudioSessionId) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.tunnelingAudioSessionId;
    }
}
