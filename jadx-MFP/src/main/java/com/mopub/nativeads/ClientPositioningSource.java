package com.mopub.nativeads;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.nativeads.PositioningSource.PositioningListener;

class ClientPositioningSource implements PositioningSource {
    @NonNull
    private final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    @NonNull
    public final MoPubClientPositioning mPositioning;

    ClientPositioningSource(@NonNull MoPubClientPositioning moPubClientPositioning) {
        this.mPositioning = MoPubNativeAdPositioning.clone(moPubClientPositioning);
    }

    public void loadPositions(@NonNull String str, @NonNull final PositioningListener positioningListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                positioningListener.onLoad(ClientPositioningSource.this.mPositioning);
            }
        });
    }
}
