package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.google.android.exoplayer2.source.ExtractorMediaSource;

/* compiled from: IMASDK */
public final class mn {
    private final so a;
    private ft b;
    private ti c = new ti();
    private int d = ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;

    public mn(so soVar, ft ftVar) {
        this.a = soVar;
        this.b = ftVar;
    }

    public final mm a(Uri uri) {
        mm mmVar = new mm(uri, this.a, this.b, this.c, null, this.d, null);
        return mmVar;
    }
}
