package com.google.android.exoplayer2.source.ads;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.ads.AdsMediaSource.AdLoadException;
import com.google.android.exoplayer2.upstream.DataSpec;
import java.io.IOException;

public interface AdsLoader {

    public interface AdViewProvider {
        View[] getAdOverlayViews();

        ViewGroup getAdViewGroup();
    }

    public interface EventListener {

        /* renamed from: com.google.android.exoplayer2.source.ads.AdsLoader$EventListener$-CC reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onAdClicked(EventListener eventListener) {
            }

            public static void $default$onAdLoadError(EventListener eventListener, AdLoadException adLoadException, DataSpec dataSpec) {
            }

            public static void $default$onAdPlaybackState(EventListener eventListener, AdPlaybackState adPlaybackState) {
            }

            public static void $default$onAdTapped(EventListener eventListener) {
            }
        }

        void onAdClicked();

        void onAdLoadError(AdLoadException adLoadException, DataSpec dataSpec);

        void onAdPlaybackState(AdPlaybackState adPlaybackState);

        void onAdTapped();
    }

    void handlePrepareError(int i, int i2, IOException iOException);

    void release();

    void setPlayer(@Nullable Player player);

    void setSupportedContentTypes(int... iArr);

    void start(EventListener eventListener, AdViewProvider adViewProvider);

    void stop();
}
