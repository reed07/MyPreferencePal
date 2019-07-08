package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;

/* compiled from: IMASDK */
public final class adb extends aeg {
    private final ContentProgressProvider a;

    public adb(ContentProgressProvider contentProgressProvider, long j) {
        super(j);
        this.a = contentProgressProvider;
    }

    public final VideoProgressUpdate a() {
        VideoProgressUpdate contentProgress = this.a.getContentProgress();
        if (contentProgress != null) {
            return contentProgress;
        }
        Log.w("IMASDK", "ContentProgressProvider.getContentProgress() is null. Use VideoProgressUpdate.VIDEO_TIME_NOT_READY instead.");
        return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
    }
}
