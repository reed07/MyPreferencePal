package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.SortedSet;

/* compiled from: IMASDK */
public final class ada implements aei {
    private final SortedSet<Float> a;
    private aeb b;
    private String c;
    private float d = BitmapDescriptorFactory.HUE_RED;

    public ada(aeb aeb, SortedSet<Float> sortedSet, String str) {
        this.b = aeb;
        this.c = str;
        this.a = sortedSet;
    }

    public final void a(VideoProgressUpdate videoProgressUpdate) {
        SortedSet sortedSet;
        if (videoProgressUpdate != null && videoProgressUpdate.getDuration() >= BitmapDescriptorFactory.HUE_RED) {
            float currentTime = videoProgressUpdate.getCurrentTime();
            float f = this.d;
            if (f < currentTime) {
                sortedSet = this.a.subSet(Float.valueOf(f), Float.valueOf(currentTime));
            } else {
                sortedSet = this.a.subSet(Float.valueOf(currentTime), Float.valueOf(this.d));
            }
            boolean z = !sortedSet.isEmpty();
            this.d = videoProgressUpdate.getCurrentTime();
            if (z) {
                this.b.b(new ado(adq.contentTimeUpdate, adr.contentTimeUpdate, this.c, videoProgressUpdate));
            }
        }
    }
}
