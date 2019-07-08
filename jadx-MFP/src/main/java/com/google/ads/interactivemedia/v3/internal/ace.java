package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.api.player.VolumeProvider;
import com.google.ads.interactivemedia.v3.impl.data.ah;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: IMASDK */
public final class ace implements VideoAdPlayerCallback, aei {
    private final aeb a;
    private final String b;
    private final acg c;
    private final VolumeProvider d;
    private boolean e = false;

    public ace(aeb aeb, String str, acg acg, VolumeProvider volumeProvider) {
        this.a = aeb;
        this.b = str;
        this.c = acg;
        this.d = volumeProvider;
    }

    public final void onPlay() {
        this.e = false;
    }

    public final void onPause() {
        this.c.c();
        a(adr.pause, null);
    }

    public final void onLoaded() {
        a(adr.loaded, null);
    }

    public final void onResume() {
        this.c.b();
        a(adr.play, null);
    }

    public final void onEnded() {
        a(adr.end, null);
    }

    public final void onError() {
        a(adr.error, null);
    }

    public final void onBuffering() {
        a(adr.waiting, null);
    }

    public final void onVolumeChanged(int i) {
        a(adr.volumeChange, ah.builder().volumePercentage(i).build());
    }

    public final void a(VideoProgressUpdate videoProgressUpdate) {
        if (videoProgressUpdate != null && videoProgressUpdate.getDuration() > BitmapDescriptorFactory.HUE_RED) {
            if (!this.e && videoProgressUpdate.getCurrentTime() > BitmapDescriptorFactory.HUE_RED) {
                a(adr.start, ah.builder().volumePercentage(this.d.getVolume()).build());
                this.e = true;
            }
            a(adr.timeupdate, videoProgressUpdate);
        }
    }

    private final void a(adr adr, Object obj) {
        this.a.b(new ado(adq.videoDisplay, adr, this.b, obj));
    }
}
