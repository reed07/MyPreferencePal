package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Handler;
import com.google.android.exoplayer2.DefaultRenderersFactory;

/* compiled from: IMASDK */
public class cl {
    private final Context a;

    public ci[] a(Handler handler, vu vuVar, dl dlVar) {
        vo voVar = new vo(this.a, ji.a, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, handler, vuVar, 50);
        return new ci[]{voVar, new en(this.a, ji.a, handler, dlVar)};
    }

    public cl(Context context) {
        this.a = context;
    }
}
