package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;

/* compiled from: IMASDK */
final class adg extends ce {
    private final /* synthetic */ ade a;

    adg(ade ade) {
        this.a = ade;
    }

    public final void a(aw awVar) {
        this.a.d();
        String valueOf = String.valueOf(awVar);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
        sb.append("Player Error:");
        sb.append(valueOf);
        Log.e("IMA SDK", sb.toString());
    }

    public final void a(boolean z, int i) {
        switch (i) {
            case 2:
                for (VideoAdPlayerCallback onBuffering : this.a.g) {
                    onBuffering.onBuffering();
                }
                return;
            case 3:
                for (VideoAdPlayerCallback onLoaded : this.a.g) {
                    onLoaded.onLoaded();
                }
                return;
            case 4:
                for (VideoAdPlayerCallback onEnded : this.a.g) {
                    onEnded.onEnded();
                }
                break;
        }
    }
}
