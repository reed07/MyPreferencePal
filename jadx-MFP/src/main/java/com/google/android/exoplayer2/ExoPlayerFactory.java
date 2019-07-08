package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.analytics.AnalyticsCollector.Factory;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter.Builder;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;

public final class ExoPlayerFactory {
    @Nullable
    private static BandwidthMeter singletonBandwidthMeter;

    private ExoPlayerFactory() {
    }

    @Deprecated
    public static SimpleExoPlayer newSimpleInstance(Context context, TrackSelector trackSelector, LoadControl loadControl) {
        return newSimpleInstance(context, (RenderersFactory) new DefaultRenderersFactory(context), trackSelector, loadControl);
    }

    @Deprecated
    public static SimpleExoPlayer newSimpleInstance(Context context, TrackSelector trackSelector, LoadControl loadControl, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        return newSimpleInstance(context, (RenderersFactory) new DefaultRenderersFactory(context), trackSelector, loadControl, drmSessionManager);
    }

    @Deprecated
    public static SimpleExoPlayer newSimpleInstance(Context context, TrackSelector trackSelector, LoadControl loadControl, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, int i) {
        return newSimpleInstance(context, (RenderersFactory) new DefaultRenderersFactory(context).setExtensionRendererMode(i), trackSelector, loadControl, drmSessionManager);
    }

    @Deprecated
    public static SimpleExoPlayer newSimpleInstance(Context context, TrackSelector trackSelector, LoadControl loadControl, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, int i, long j) {
        return newSimpleInstance(context, (RenderersFactory) new DefaultRenderersFactory(context).setExtensionRendererMode(i).setAllowedVideoJoiningTimeMs(j), trackSelector, loadControl, drmSessionManager);
    }

    public static SimpleExoPlayer newSimpleInstance(Context context) {
        return newSimpleInstance(context, (TrackSelector) new DefaultTrackSelector());
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, TrackSelector trackSelector) {
        return newSimpleInstance(context, (RenderersFactory) new DefaultRenderersFactory(context), trackSelector);
    }

    @Deprecated
    public static SimpleExoPlayer newSimpleInstance(RenderersFactory renderersFactory, TrackSelector trackSelector) {
        return newSimpleInstance((Context) null, renderersFactory, trackSelector, (LoadControl) new DefaultLoadControl());
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector) {
        return newSimpleInstance(context, renderersFactory, trackSelector, (LoadControl) new DefaultLoadControl());
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        return newSimpleInstance(context, renderersFactory, trackSelector, (LoadControl) new DefaultLoadControl(), drmSessionManager);
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl) {
        return newSimpleInstance(context, renderersFactory, trackSelector, loadControl, null, Util.getLooper());
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        return newSimpleInstance(context, renderersFactory, trackSelector, loadControl, drmSessionManager, Util.getLooper());
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, BandwidthMeter bandwidthMeter) {
        return newSimpleInstance(context, renderersFactory, trackSelector, loadControl, drmSessionManager, bandwidthMeter, new Factory(), Util.getLooper());
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Factory factory) {
        return newSimpleInstance(context, renderersFactory, trackSelector, loadControl, drmSessionManager, factory, Util.getLooper());
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Looper looper) {
        return newSimpleInstance(context, renderersFactory, trackSelector, loadControl, drmSessionManager, new Factory(), looper);
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Factory factory, Looper looper) {
        return newSimpleInstance(context, renderersFactory, trackSelector, loadControl, drmSessionManager, getDefaultBandwidthMeter(), factory, looper);
    }

    public static SimpleExoPlayer newSimpleInstance(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, BandwidthMeter bandwidthMeter, Factory factory, Looper looper) {
        SimpleExoPlayer simpleExoPlayer = new SimpleExoPlayer(context, renderersFactory, trackSelector, loadControl, drmSessionManager, bandwidthMeter, factory, looper);
        return simpleExoPlayer;
    }

    public static ExoPlayer newInstance(Renderer[] rendererArr, TrackSelector trackSelector) {
        return newInstance(rendererArr, trackSelector, new DefaultLoadControl());
    }

    public static ExoPlayer newInstance(Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl) {
        return newInstance(rendererArr, trackSelector, loadControl, Util.getLooper());
    }

    public static ExoPlayer newInstance(Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl, Looper looper) {
        return newInstance(rendererArr, trackSelector, loadControl, getDefaultBandwidthMeter(), looper);
    }

    public static ExoPlayer newInstance(Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl, BandwidthMeter bandwidthMeter, Looper looper) {
        ExoPlayerImpl exoPlayerImpl = new ExoPlayerImpl(rendererArr, trackSelector, loadControl, bandwidthMeter, Clock.DEFAULT, looper);
        return exoPlayerImpl;
    }

    private static synchronized BandwidthMeter getDefaultBandwidthMeter() {
        BandwidthMeter bandwidthMeter;
        synchronized (ExoPlayerFactory.class) {
            if (singletonBandwidthMeter == null) {
                singletonBandwidthMeter = new Builder().build();
            }
            bandwidthMeter = singletonBandwidthMeter;
        }
        return bandwidthMeter;
    }
}
