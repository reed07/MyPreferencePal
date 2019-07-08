package com.brightcove.player.display;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.brightcove.player.Constants;
import com.brightcove.player.analytics.Analytics.Fields;
import com.brightcove.player.captioning.BrightcoveCaptionFormat;
import com.brightcove.player.config.AllocatorConfig;
import com.brightcove.player.config.LoadControlConfig;
import com.brightcove.player.config.LoadControlConfig.Builder;
import com.brightcove.player.dash.BrightcoveDashManifestParser;
import com.brightcove.player.dash.OfflineDashManifestParser;
import com.brightcove.player.drm.BrightcoveDrmSession;
import com.brightcove.player.drm.BrightcoveMediaDrmCallback;
import com.brightcove.player.drm.ExoPlayerDrmSessionManager;
import com.brightcove.player.drm.WidevineMediaDrmCallback;
import com.brightcove.player.edge.OfflineStoreManager;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.event.RegisteringEventEmitter;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;
import com.brightcove.player.network.ConnectivityMonitor;
import com.brightcove.player.network.PlayerBandwidthMeter;
import com.brightcove.player.offline.MultiDataSource;
import com.brightcove.player.render.TrackSelectorHelper;
import com.brightcove.player.util.Convert;
import com.brightcove.player.util.EventUtil;
import com.brightcove.player.util.MediaSourceUtil;
import com.brightcove.player.util.NumberUtil;
import com.brightcove.player.view.RenderView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener;
import com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener.CC;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaSourceEventListener.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener.MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector.Parameters;
import com.google.android.exoplayer2.trackselection.FixedTrackSelection;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection.Factory;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.BandwidthMeter.EventListener;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.HttpDataSource.BaseFactory;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@ListensFor(events = {"selectAudioTrack", "selectClosedCaptionTrack", "on360FrameAvailable", "videoDurationChanged"})
@Emits(events = {"addAnalyticsBaseParams", "analyticsVideoEngagement", "audioTracks", "bufferingCompleted", "bufferingStarted", "caption", "captionsLanguages", "toggleClosedCaptions", "renditionChanged"})
@SuppressLint({"ViewConstructor"})
@RequiresApi
public class ExoPlayerVideoDisplayComponent extends VideoDisplayComponent {
    public static final String BUFFERED_POSITION = "bufferedPosition";
    public static final String EXOPLAYER_FORMAT = "exoplayerFormat";
    private static final String EXOPLAYER_ON = "exoplayer";
    private static final String FEATURE = "feature";
    public static final String IN_MANIFEST_CC_URI_STR = "brightcove://in-manifest";
    private static final long PLAY_EVENT_POLL_INTERVAL = 50;
    public static final String RENDITION_CHANGED = "renditionChanged";
    private static final String TAG = "ExoPlayer2VideoDisplay";
    public static final int TYPE_AUDIO = 1;
    public static final int TYPE_METADATA = 4;
    public static final int TYPE_TEXT = 3;
    public static final int TYPE_VIDEO = 2;
    public static final String UNKNOWN_CC = "unknownCC";
    public static ResourceBundle resourceBundle;
    private final MediaSourceEventListener adaptiveMediaSourceEventListener;
    private final AudioRendererEventListener audioRendererEventListener;
    private final EventListener bandwidthMeterEventListener;
    /* access modifiers changed from: private */
    public CaptionListener captionListener;
    /* access modifiers changed from: private */
    public InfoListener debugListener;
    private final DefaultDrmSessionEventListener drmEventListener;
    private BrightcoveDrmSession drmSessionManager;
    /* access modifiers changed from: private */
    public SimpleExoPlayer exoPlayer;
    /* access modifiers changed from: private */
    public final Player.EventListener exoplayerEventListener;
    private boolean hasEmittedAudioTracks;
    private boolean hasEmittedCaptionsLanguages;
    /* access modifiers changed from: private */
    public boolean hasPlaybackStarted;
    /* access modifiers changed from: private */
    public Id3MetadataListener id3MetadataListener;
    /* access modifiers changed from: private */
    public boolean inErrorState;
    /* access modifiers changed from: private */
    public InternalErrorListener internalErrorListener;
    /* access modifiers changed from: private */
    public boolean isPlaying;
    boolean isSeekable;
    private LoadControlConfig mLoadControlConfig;
    /* access modifiers changed from: private */
    public Handler mainHandler;
    private long maxBufferDurationToSwitchDown;
    /* access modifiers changed from: private */
    public long maxPosition;
    /* access modifiers changed from: private */
    public MetadataListener metadataListener;
    private MetadataOutput metadataRendererListener;
    private long minBufferDurationToSwitchUp;
    private int minBufferMs;
    /* access modifiers changed from: private */
    public long minPosition;
    private int minRebufferMs;
    private int peakBitrate;
    private Period periodHolder;
    /* access modifiers changed from: private */
    public final PlayerBandwidthMeter playerBandwidthMeter;
    private final Runnable playerStatePoller;
    /* access modifiers changed from: private */
    public final AtomicBoolean prepared;
    private boolean previousPlayWhenReady;
    private int previousPlaybackState;
    private int previousState;
    /* access modifiers changed from: private */
    public Handler progressHandler;
    private long resumePosition;
    private int resumeWindow;
    private final TextOutput textRendererListener;
    /* access modifiers changed from: private */
    public DefaultTrackSelector trackSelector;
    /* access modifiers changed from: private */
    public TrackSelectorHelper trackSelectorHelper;
    /* access modifiers changed from: private */
    public Format videoFormat;
    private final VideoRendererEventListener videoRendererEventListener;
    private Window windowHolder;

    public interface CaptionListener {
        void onCues(List<Cue> list);
    }

    private class ExoPlayerOnCompletedListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnCompletedListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnCompletedListener:");
            if (ExoPlayerVideoDisplayComponent.this.exoPlayer != null) {
                ExoPlayerVideoDisplayComponent exoPlayerVideoDisplayComponent = ExoPlayerVideoDisplayComponent.this;
                exoPlayerVideoDisplayComponent.playheadPosition = 0;
                exoPlayerVideoDisplayComponent.seekTo(0);
                ExoPlayerVideoDisplayComponent.this.pause();
            }
            if (ExoPlayerVideoDisplayComponent.this.nextSource != null) {
                final UUID randomUUID = UUID.randomUUID();
                ExoPlayerVideoDisplayComponent.this.eventEmitter.once(EventType.WILL_CHANGE_VIDEO, new com.brightcove.player.event.EventListener() {
                    @Default
                    public void processEvent(Event event) {
                        Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnCompletedListener: WILL_CHANGE_VIDEO");
                        if (randomUUID.equals(event.properties.get(AbstractEvent.UUID))) {
                            ExoPlayerVideoDisplayComponent.this.destroyPlayer();
                            String str = ExoPlayerVideoDisplayComponent.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("ExoPlayerOnCompletedListener: currentSource = ");
                            sb.append(ExoPlayerVideoDisplayComponent.this.getCurrentSource());
                            sb.append(", nextSource = ");
                            sb.append(ExoPlayerVideoDisplayComponent.this.nextSource);
                            Log.v(str, sb.toString());
                            ExoPlayerVideoDisplayComponent.this.setVideoSource(ExoPlayerVideoDisplayComponent.this.nextVideo, ExoPlayerVideoDisplayComponent.this.nextSource);
                            ExoPlayerVideoDisplayComponent.this.nextVideo = null;
                            ExoPlayerVideoDisplayComponent.this.nextSource = null;
                            ExoPlayerVideoDisplayComponent.this.eventEmitter.once(EventType.DID_SET_SOURCE, new com.brightcove.player.event.EventListener() {
                                @Default
                                public void processEvent(Event event) {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("video", ExoPlayerVideoDisplayComponent.this.getCurrentVideo());
                                    ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.PLAY, hashMap);
                                }
                            });
                            ExoPlayerVideoDisplayComponent.this.openCurrentVideoSource();
                        }
                    }
                });
                HashMap hashMap = new HashMap();
                hashMap.put(AbstractEvent.CURRENT_VIDEO, ExoPlayerVideoDisplayComponent.this.getCurrentVideo());
                hashMap.put(AbstractEvent.NEXT_VIDEO, ExoPlayerVideoDisplayComponent.this.nextVideo);
                hashMap.put(AbstractEvent.UUID, randomUUID);
                ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.WILL_CHANGE_VIDEO, hashMap);
            }
        }
    }

    private class ExoPlayerOnPauseListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnPauseListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnPauseListener");
            if (ExoPlayerVideoDisplayComponent.this.exoPlayer != null) {
                ExoPlayerVideoDisplayComponent.this.pause();
            }
        }
    }

    private class ExoPlayerOnPlayListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnPlayListener() {
        }

        @Default
        public void processEvent(Event event) {
            final int i;
            Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnPlayListener:");
            ExoPlayerVideoDisplayComponent.this.hasPlaybackStarted = false;
            if (ExoPlayerVideoDisplayComponent.this.getCurrentSource() == null) {
                Log.e(ExoPlayerVideoDisplayComponent.TAG, "Source has not been set yet.");
                return;
            }
            if (event.properties.containsKey(AbstractEvent.PLAYHEAD_POSITION)) {
                i = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
            } else {
                String str = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("ExoPlayerOnPlayListener: playheadPosition = ");
                sb.append(ExoPlayerVideoDisplayComponent.this.playheadPosition);
                Log.v(str, sb.toString());
                i = ExoPlayerVideoDisplayComponent.this.playheadPosition;
            }
            if (ExoPlayerVideoDisplayComponent.this.exoPlayer != null) {
                ExoPlayerVideoDisplayComponent.this.play((long) i);
                return;
            }
            ExoPlayerVideoDisplayComponent.this.eventEmitter.once(EventType.DID_SET_SOURCE, new com.brightcove.player.event.EventListener() {
                @Default
                public void processEvent(Event event) {
                    if (ExoPlayerVideoDisplayComponent.this.exoPlayer != null) {
                        ExoPlayerVideoDisplayComponent.this.play((long) i);
                    }
                }
            });
            ExoPlayerVideoDisplayComponent.this.openCurrentVideoSource();
        }
    }

    private class ExoPlayerOnPrebufferNextVideoListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnPrebufferNextVideoListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnPrebufferNextVideoListener:");
            ExoPlayerVideoDisplayComponent.this.nextVideo = (Video) event.properties.get("video");
            ExoPlayerVideoDisplayComponent.this.nextSource = (Source) event.properties.get("source");
        }
    }

    private class ExoPlayerOnSeekListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnSeekListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnSeekListener");
            if (event.properties.containsKey(AbstractEvent.SEEK_POSITION)) {
                final int integerProperty = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
                if (!(ExoPlayerVideoDisplayComponent.this.exoPlayer != null && (!ExoPlayerVideoDisplayComponent.this.isLive() ? ExoPlayerVideoDisplayComponent.this.getDuration() != -1 : ExoPlayerVideoDisplayComponent.this.maxPosition != -1))) {
                    ExoPlayerVideoDisplayComponent.this.eventEmitter.once(EventType.DID_SET_SOURCE, new com.brightcove.player.event.EventListener() {
                        @Default
                        public void processEvent(Event event) {
                            if (ExoPlayerVideoDisplayComponent.this.exoPlayer == null) {
                                return;
                            }
                            if (ExoPlayerVideoDisplayComponent.this.getDuration() == -1) {
                                ExoPlayerVideoDisplayComponent.this.eventEmitter.once(EventType.VIDEO_DURATION_CHANGED, new com.brightcove.player.event.EventListener(this, integerProperty) {
                                    private final /* synthetic */ AnonymousClass1 f$0;
                                    private final /* synthetic */ int f$1;

                                    public final 
/*
Method generation error in method: com.brightcove.player.display.-$$Lambda$ExoPlayerVideoDisplayComponent$ExoPlayerOnSeekListener$1$PXZmyBA6Cexekp-jlFr9DeLDqcY.processEvent(com.brightcove.player.event.Event):null, dex: classes.dex
                                    java.lang.NullPointerException
                                    	at jadx.core.codegen.ClassGen.useType(ClassGen.java:442)
                                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:109)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                                    	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:237)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:224)
                                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                                    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                                    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                                    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                                    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                                    
*/
                                });
                            } else {
                                ExoPlayerVideoDisplayComponent.this.seekTo((long) integerProperty);
                            }
                        }
                    });
                    if (ExoPlayerVideoDisplayComponent.this.getCurrentVideo() != null) {
                        ExoPlayerVideoDisplayComponent.this.openCurrentVideoSource();
                    }
                } else if (integerProperty != -1) {
                    ExoPlayerVideoDisplayComponent.this.seekTo((long) integerProperty);
                } else {
                    String str = ExoPlayerVideoDisplayComponent.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid seek position: ");
                    sb.append(integerProperty);
                    Log.e(str, sb.toString());
                }
            } else {
                Log.e(ExoPlayerVideoDisplayComponent.TAG, "Seek event must pass the seekPosition property.");
            }
        }
    }

    private class ExoPlayerOnSetSourceListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnSetSourceListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnSetSourceListener");
            ExoPlayerVideoDisplayComponent.this.destroyPlayer();
            Source source = (Source) event.properties.get("source");
            ExoPlayerVideoDisplayComponent.this.setVideoSource((Video) event.properties.get("video"), source);
            if (source != null && source.getUrl() != null) {
                ExoPlayerVideoDisplayComponent.this.openCurrentVideoSource();
            }
        }
    }

    private class ExoPlayerOnSetVolumeListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnSetVolumeListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnSetVolumeListener:");
            float floatValue = ((Float) event.properties.get("volume")).floatValue();
            if (floatValue < BitmapDescriptorFactory.HUE_RED || floatValue > 1.0f) {
                Log.e(ExoPlayerVideoDisplayComponent.TAG, "The volume setting is out of the legal range. (0.0f-1.0f)");
            }
            ExoPlayerVideoDisplayComponent.this.exoPlayer.setVolume(floatValue);
        }
    }

    private class ExoPlayerOnStopListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnStopListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnStopListener:");
            if (ExoPlayerVideoDisplayComponent.this.exoPlayer != null) {
                HashMap hashMap = new HashMap();
                hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(NumberUtil.safeLongToInt(ExoPlayerVideoDisplayComponent.this.exoPlayer.getCurrentPosition())));
                hashMap.put("video", ExoPlayerVideoDisplayComponent.this.getCurrentVideo());
                ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.DID_STOP, hashMap);
            }
            ExoPlayerVideoDisplayComponent.this.destroyPlayer();
        }
    }

    private class ExoPlayerOnWillInterruptContentListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnWillInterruptContentListener() {
        }

        @Default
        public void processEvent(Event event) {
            String str = ExoPlayerVideoDisplayComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("ExoPlayerOnWillInterruptContentListener: hasSurface = ");
            sb.append(ExoPlayerVideoDisplayComponent.this.hasSurface);
            sb.append(", playheadPosition = ");
            sb.append(ExoPlayerVideoDisplayComponent.this.playheadPosition);
            Log.v(str, sb.toString());
            if (ExoPlayerVideoDisplayComponent.this.exoPlayer != null) {
                ExoPlayerVideoDisplayComponent.this.exoPlayer.removeListener(ExoPlayerVideoDisplayComponent.this.exoplayerEventListener);
                ExoPlayerVideoDisplayComponent.this.exoPlayer.setPlayWhenReady(false);
                ExoPlayerVideoDisplayComponent.this.isPlaying = false;
                ExoPlayerVideoDisplayComponent.this.stopUpdater();
            }
            if (ExoPlayerVideoDisplayComponent.this.renderView != null) {
                ExoPlayerVideoDisplayComponent.this.renderView.setVisibility(4);
            }
            ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.DID_INTERRUPT_CONTENT);
        }
    }

    private class ExoPlayerOnWillResumeContentListener implements com.brightcove.player.event.EventListener {
        private ExoPlayerOnWillResumeContentListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(ExoPlayerVideoDisplayComponent.TAG, "ExoPlayerOnWillResumeContentListener:");
            if (ExoPlayerVideoDisplayComponent.this.renderView != null) {
                ExoPlayerVideoDisplayComponent.this.renderView.setVisibility(0);
            }
            Event event2 = (Event) event.properties.get(AbstractEvent.ORIGINAL_EVENT);
            if (event2 != null) {
                ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(event2.getType(), event2.properties);
            }
            ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.DID_RESUME_CONTENT);
        }
    }

    @Deprecated
    public interface Id3MetadataListener {
        void onId3Metadata(List<Id3Frame> list);
    }

    public interface InfoListener {
        void onAudioFormatEnabled(Format format, int i, long j);

        void onBandwidthSample(int i, long j, long j2);

        void onDecoderInitialized(String str, long j, long j2);

        void onDroppedFrames(int i, long j);

        void onLoadCompleted(int i, long j, int i2, int i3, Format format, long j2, long j3, long j4, long j5);

        void onLoadStarted(int i, int i2, int i3, Format format, long j, long j2);

        void onVideoFormatEnabled(Format format, int i, long j);
    }

    public interface InternalErrorListener {
        void onAudioTrackUnderrun(int i, long j, long j2);

        void onDrmSessionManagerError(Exception exc);

        void onLoadError(int i, IOException iOException);

        void onPlayerError(Exception exc);
    }

    public interface MetadataListener {
        void onMetadata(Metadata metadata);
    }

    private class OfflineTrackSelector extends DefaultTrackSelector {
        private static final int SELECTION_REASON_OFFLINE = 10001;

        public OfflineTrackSelector(Factory factory) {
            super(factory);
        }

        /* access modifiers changed from: protected */
        public TrackSelection[] selectAllTracks(MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, Parameters parameters) throws ExoPlaybackException {
            if (ConnectivityMonitor.getInstance(ExoPlayerVideoDisplayComponent.this.context).isConnected() || !ExoPlayerVideoDisplayComponent.this.getCurrentSourceOrFail().isLocal()) {
                return super.selectAllTracks(mappedTrackInfo, iArr, iArr2, parameters);
            }
            OfflineStoreManager instance = OfflineStoreManager.getInstance(ExoPlayerVideoDisplayComponent.this.context);
            Object[] objArr = new Object[1];
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ExoPlayerVideoDisplayComponent.this.mainHandler.post(new Runnable(objArr, countDownLatch) {
                private final /* synthetic */ Object[] f$1;
                private final /* synthetic */ CountDownLatch f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    OfflineTrackSelector.lambda$selectAllTracks$1(OfflineTrackSelector.this, this.f$1, this.f$2);
                }
            });
            try {
                countDownLatch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Log.e(ExoPlayerVideoDisplayComponent.TAG, "Waiting to get manifest was interrupted", e);
            }
            int rendererCount = mappedTrackInfo.getRendererCount();
            TrackSelection[] trackSelectionArr = new TrackSelection[rendererCount];
            TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCount];
            for (int i = 0; i < rendererCount; i++) {
                trackGroupArrayArr[i] = mappedTrackInfo.getTrackGroups(i);
            }
            for (int i2 = 0; i2 < rendererCount; i2++) {
                trackSelectionArr[i2] = selectOfflineTrack(instance, objArr[0], mappedTrackInfo.getRendererType(i2), trackGroupArrayArr);
            }
            return trackSelectionArr;
        }

        public static /* synthetic */ void lambda$selectAllTracks$1(OfflineTrackSelector offlineTrackSelector, Object[] objArr, CountDownLatch countDownLatch) {
            objArr[0] = ExoPlayerVideoDisplayComponent.this.exoPlayer.getCurrentManifest();
            countDownLatch.countDown();
        }

        @Nullable
        private TrackSelection selectOfflineTrack(OfflineStoreManager offlineStoreManager, Object obj, int i, TrackGroupArray[] trackGroupArrayArr) {
            for (TrackGroupArray trackGroupArray : trackGroupArrayArr) {
                for (int i2 = 0; i2 < trackGroupArray.length; i2++) {
                    TrackGroup trackGroup = trackGroupArray.get(i2);
                    for (int i3 = 0; i3 < trackGroup.length; i3++) {
                        Format format = trackGroup.getFormat(i3);
                        if (MediaSourceUtil.findTrackType(format) == i) {
                            String findRenditionUrl = MediaSourceUtil.findRenditionUrl(obj, i, format);
                            if (!TextUtils.isEmpty(findRenditionUrl) && offlineStoreManager.findOfflineAssetUri(Uri.parse(findRenditionUrl)) != null) {
                                return new FixedTrackSelection(trackGroup, i3, 10001, null);
                            }
                        }
                    }
                }
            }
            return null;
        }
    }

    private class OnFrameAvailableListener implements com.brightcove.player.event.EventListener {
        private OnFrameAvailableListener() {
        }

        @Default
        public void processEvent(Event event) {
            if (!ExoPlayerVideoDisplayComponent.this.prepared.get()) {
                ExoPlayerVideoDisplayComponent exoPlayerVideoDisplayComponent = ExoPlayerVideoDisplayComponent.this;
                exoPlayerVideoDisplayComponent.preparePlayer(exoPlayerVideoDisplayComponent.getMediaSource(), true);
            }
        }
    }

    private class OnSelectAudioTrackListener implements com.brightcove.player.event.EventListener {
        private OnSelectAudioTrackListener() {
        }

        @Default
        public void processEvent(Event event) {
            String str = (String) event.properties.get(AbstractEvent.SELECTED_TRACK);
            String str2 = ExoPlayerVideoDisplayComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnSelectAudioListener: track = ");
            sb.append(str);
            Log.v(str2, sb.toString());
            if (ExoPlayerVideoDisplayComponent.this.trackSelectorHelper != null) {
                ExoPlayerVideoDisplayComponent.this.trackSelectorHelper.selectAudio(str);
            }
        }
    }

    private class OnSelectClosedCaptionTrackListener implements com.brightcove.player.event.EventListener {
        private OnSelectClosedCaptionTrackListener() {
        }

        @Default
        public void processEvent(Event event) {
            Object obj = event.properties.get(AbstractEvent.CAPTION_FORMAT);
            Object obj2 = event.properties.get(AbstractEvent.CAPTION_URI);
            if ((obj instanceof BrightcoveCaptionFormat) && (obj2 instanceof Uri) && ExoPlayerVideoDisplayComponent.this.trackSelectorHelper != null) {
                BrightcoveCaptionFormat brightcoveCaptionFormat = (BrightcoveCaptionFormat) obj;
                if (obj2.toString().startsWith(ExoPlayerVideoDisplayComponent.IN_MANIFEST_CC_URI_STR)) {
                    ExoPlayerVideoDisplayComponent.this.trackSelectorHelper.selectCaption(brightcoveCaptionFormat);
                } else {
                    ExoPlayerVideoDisplayComponent.this.trackSelectorHelper.disableTrack(3);
                }
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TrackType {
    }

    static {
        try {
            resourceBundle = ResourceBundle.getBundle("BrightcoveExoPlayerMessages");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public BrightcoveDrmSession getBrightcoveDrmSession() {
        return this.drmSessionManager;
    }

    public ExoPlayerVideoDisplayComponent(RenderView renderView, EventEmitter eventEmitter) {
        super(renderView, eventEmitter);
        this.previousState = -1;
        this.minBufferDurationToSwitchUp = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
        this.maxBufferDurationToSwitchDown = 20000;
        this.minBufferMs = 2500;
        this.minRebufferMs = 5000;
        this.periodHolder = new Period();
        this.windowHolder = new Window();
        this.isSeekable = false;
        this.prepared = new AtomicBoolean();
        this.drmEventListener = new DefaultDrmSessionEventListener() {
            public /* synthetic */ void onDrmSessionAcquired() {
                CC.$default$onDrmSessionAcquired(this);
            }

            public /* synthetic */ void onDrmSessionReleased() {
                CC.$default$onDrmSessionReleased(this);
            }

            public void onDrmKeysLoaded() {
                Log.v(ExoPlayerVideoDisplayComponent.TAG, " - onDrmKeysLoaded");
            }

            public void onDrmKeysRestored() {
                Log.v(ExoPlayerVideoDisplayComponent.TAG, " - onDrmKeysRestored");
            }

            public void onDrmKeysRemoved() {
                Log.v(ExoPlayerVideoDisplayComponent.TAG, " - onDrmKeysRemoved");
            }

            public void onDrmSessionManagerError(Exception exc) {
                ExoPlayerVideoDisplayComponent.this.emitErrorEvent("onDrmSessionManagerError", exc);
                if (ExoPlayerVideoDisplayComponent.this.internalErrorListener != null) {
                    ExoPlayerVideoDisplayComponent.this.internalErrorListener.onDrmSessionManagerError(exc);
                }
            }
        };
        this.exoplayerEventListener = new Player.EventListener() {
            public void onLoadingChanged(boolean z) {
            }

            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            }

            public void onRepeatModeChanged(int i) {
            }

            public void onSeekProcessed() {
            }

            public void onShuffleModeEnabledChanged(boolean z) {
            }

            public void onTimelineChanged(Timeline timeline, Object obj, int i) {
                ExoPlayerVideoDisplayComponent.this.emitDurationChanged();
            }

            public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                ExoPlayerVideoDisplayComponent.this.trackSelectorHelper.updateTracksSelectionArray(trackSelectionArray);
            }

            public void onPlayerStateChanged(boolean z, int i) {
                if (ExoPlayerVideoDisplayComponent.this.fromSeekPosition != -1 && i == 3) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("video", ExoPlayerVideoDisplayComponent.this.getCurrentVideo());
                    hashMap.put("source", ExoPlayerVideoDisplayComponent.this.getCurrentSource());
                    hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(NumberUtil.safeLongToInt(ExoPlayerVideoDisplayComponent.this.exoPlayer.getCurrentPosition())));
                    hashMap.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(ExoPlayerVideoDisplayComponent.this.seekPosition));
                    hashMap.put(AbstractEvent.FROM_SEEK_POSITION, Integer.valueOf(ExoPlayerVideoDisplayComponent.this.fromSeekPosition));
                    ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.DID_SEEK_TO, hashMap);
                    ExoPlayerVideoDisplayComponent.this.fromSeekPosition = -1;
                }
                ExoPlayerVideoDisplayComponent.this.reportPlayerState();
            }

            public void onPlayerError(ExoPlaybackException exoPlaybackException) {
                ExoPlayerVideoDisplayComponent.this.emitErrorEvent("onPlayerError", exoPlaybackException);
                ExoPlayerVideoDisplayComponent.this.eventEmitter.emit("error", Collections.singletonMap("error", exoPlaybackException));
                ExoPlayerVideoDisplayComponent.this.inErrorState = true;
                if (ExoPlayerVideoDisplayComponent.isBehindLiveWindow(exoPlaybackException)) {
                    ExoPlayerVideoDisplayComponent.this.clearResumePosition();
                    Video currentVideo = ExoPlayerVideoDisplayComponent.this.getCurrentVideo();
                    Source currentSource = ExoPlayerVideoDisplayComponent.this.getCurrentSource();
                    if (!(currentVideo == null || currentSource == null)) {
                        ExoPlayerVideoDisplayComponent.this.initializePlayer(currentVideo, currentSource);
                    }
                } else {
                    ExoPlayerVideoDisplayComponent.this.emitSourceNotPlayable(exoPlaybackException);
                }
                if (ExoPlayerVideoDisplayComponent.this.internalErrorListener != null) {
                    ExoPlayerVideoDisplayComponent.this.internalErrorListener.onPlayerError(exoPlaybackException);
                }
            }

            public void onPositionDiscontinuity(int i) {
                if (ExoPlayerVideoDisplayComponent.this.inErrorState) {
                    ExoPlayerVideoDisplayComponent.this.updateResumePosition();
                }
            }
        };
        this.videoRendererEventListener = new VideoRendererEventListener() {
            public void onRenderedFirstFrame(Surface surface) {
            }

            public void onVideoDisabled(DecoderCounters decoderCounters) {
            }

            public void onVideoEnabled(DecoderCounters decoderCounters) {
            }

            public void onVideoInputFormatChanged(Format format) {
            }

            public void onVideoDecoderInitialized(String str, long j, long j2) {
                if (ExoPlayerVideoDisplayComponent.this.debugListener != null) {
                    ExoPlayerVideoDisplayComponent.this.debugListener.onDecoderInitialized(str, j, j2);
                }
            }

            public void onDroppedFrames(int i, long j) {
                String str = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onDroppedFrames: count: ");
                sb.append(i);
                sb.append(", elapsed: ");
                sb.append(j);
                Log.v(str, sb.toString());
                if (ExoPlayerVideoDisplayComponent.this.debugListener != null) {
                    ExoPlayerVideoDisplayComponent.this.debugListener.onDroppedFrames(i, j);
                }
            }

            public void onVideoSizeChanged(int i, int i2, int i3, float f) {
                String str = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onVideoSizeChanged: width: ");
                sb.append(i);
                sb.append(", height: ");
                sb.append(i2);
                sb.append(", unappliedRotationDegrees = ");
                sb.append(i3);
                sb.append(", pixelWidthHeightRatio = ");
                sb.append(f);
                sb.append(", render view width = ");
                sb.append(ExoPlayerVideoDisplayComponent.this.renderView.getWidth());
                sb.append(", render view height = ");
                sb.append(ExoPlayerVideoDisplayComponent.this.renderView.getHeight());
                Log.v(str, sb.toString());
                if (i > 0 && i2 > 0 && !(i == ExoPlayerVideoDisplayComponent.this.renderView.getVideoWidth() && i2 == ExoPlayerVideoDisplayComponent.this.renderView.getVideoHeight())) {
                    ExoPlayerVideoDisplayComponent.this.renderView.setVideoSize(i, i2);
                    HashMap hashMap = new HashMap();
                    hashMap.put("width", Integer.valueOf(i));
                    hashMap.put("height", Integer.valueOf(i2));
                    ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.VIDEO_SIZE_KNOWN, hashMap);
                }
                Source currentSource = ExoPlayerVideoDisplayComponent.this.getCurrentSource();
                if (currentSource != null && currentSource.getDeliveryType().equals(DeliveryType.MP4)) {
                    ExoPlayerVideoDisplayComponent.this.emitDurationChanged();
                }
            }
        };
        this.audioRendererEventListener = new AudioRendererEventListener() {
            public void onAudioDisabled(DecoderCounters decoderCounters) {
            }

            public void onAudioEnabled(DecoderCounters decoderCounters) {
            }

            public void onAudioInputFormatChanged(Format format) {
            }

            public void onAudioSessionId(int i) {
            }

            public void onAudioDecoderInitialized(String str, long j, long j2) {
                if (ExoPlayerVideoDisplayComponent.this.debugListener != null) {
                    ExoPlayerVideoDisplayComponent.this.debugListener.onDecoderInitialized(str, j, j2);
                }
            }

            public void onAudioSinkUnderrun(int i, long j, long j2) {
                String str = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onAudioTrackUnderrun: bufferSize = ");
                sb.append(i);
                sb.append(", bufferSizeMs = ");
                sb.append(j);
                sb.append(", elapsedSinceLastFeedMs = ");
                sb.append(j2);
                Log.e(str, sb.toString());
                if (ExoPlayerVideoDisplayComponent.this.internalErrorListener != null) {
                    ExoPlayerVideoDisplayComponent.this.internalErrorListener.onAudioTrackUnderrun(i, j, j2);
                }
            }
        };
        this.textRendererListener = new TextOutput() {
            public void onCues(List<Cue> list) {
                String str = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onCues: ");
                sb.append(list);
                Log.v(str, sb.toString());
                int rendererIndex = ExoPlayerVideoDisplayComponent.this.trackSelectorHelper != null ? ExoPlayerVideoDisplayComponent.this.trackSelectorHelper.getRendererIndex(3) : -1;
                boolean z = (rendererIndex == -1 || ExoPlayerVideoDisplayComponent.this.trackSelector == null || ExoPlayerVideoDisplayComponent.this.trackSelector.getParameters().getRendererDisabled(rendererIndex)) ? false : true;
                if (ExoPlayerVideoDisplayComponent.this.captionListener != null && z) {
                    ExoPlayerVideoDisplayComponent.this.captionListener.onCues(list);
                }
                if (list != null && !list.isEmpty()) {
                    ExoPlayerVideoDisplayComponent.this.maybeEmitAvailableCaptions(true);
                    long currentPosition = ExoPlayerVideoDisplayComponent.this.exoPlayer == null ? 0 : ExoPlayerVideoDisplayComponent.this.exoPlayer.getCurrentPosition();
                    for (Cue cue : list) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("text", cue.text.toString());
                        hashMap.put(AbstractEvent.ALIGNMENT, cue.textAlignment);
                        hashMap.put(AbstractEvent.LINE, Float.valueOf(cue.line));
                        hashMap.put(AbstractEvent.LINE_TYPE, Integer.valueOf(cue.lineType));
                        hashMap.put(AbstractEvent.LINE_ANCHOR, Integer.valueOf(cue.lineAnchor));
                        hashMap.put("position", Float.valueOf(cue.position));
                        hashMap.put(AbstractEvent.POSITION_ANCHOR, Integer.valueOf(cue.positionAnchor));
                        hashMap.put(AbstractEvent.SIZE, Float.valueOf(cue.size));
                        hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(NumberUtil.safeLongToInt(currentPosition)));
                        ExoPlayerVideoDisplayComponent.this.eventEmitter.emit("caption", hashMap);
                    }
                } else if (z) {
                    ExoPlayerVideoDisplayComponent.this.eventEmitter.emit("caption", Collections.singletonMap("text", ""));
                }
            }
        };
        this.metadataRendererListener = new MetadataOutput() {
            public void onMetadata(Metadata metadata) {
                if (!ExoPlayerVideoDisplayComponent.this.trackSelector.getParameters().getRendererDisabled(4)) {
                    if (ExoPlayerVideoDisplayComponent.this.metadataListener != null) {
                        ExoPlayerVideoDisplayComponent.this.metadataListener.onMetadata(metadata);
                    }
                    if (ExoPlayerVideoDisplayComponent.this.id3MetadataListener != null) {
                        ArrayList arrayList = new ArrayList();
                        if (metadata != null) {
                            for (int i = 0; i < metadata.length(); i++) {
                                if (metadata.get(i) instanceof Id3Frame) {
                                    arrayList.add((Id3Frame) metadata.get(i));
                                }
                            }
                        }
                        ExoPlayerVideoDisplayComponent.this.id3MetadataListener.onId3Metadata(arrayList);
                    }
                }
            }
        };
        this.bandwidthMeterEventListener = new EventListener() {
            public void onBandwidthSample(int i, long j, long j2) {
                String str = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onBandwidthSample: elapsedMs: ");
                sb.append(i);
                sb.append(", bytes: ");
                sb.append(j);
                sb.append(", bitrateEstimate: ");
                sb.append(j2);
                Log.v(str, sb.toString());
                if (ExoPlayerVideoDisplayComponent.this.debugListener != null) {
                    ExoPlayerVideoDisplayComponent.this.debugListener.onBandwidthSample(i, j, j2);
                }
            }
        };
        this.adaptiveMediaSourceEventListener = new MediaSourceEventListener() {
            public void onMediaPeriodCreated(int i, MediaPeriodId mediaPeriodId) {
            }

            public void onMediaPeriodReleased(int i, MediaPeriodId mediaPeriodId) {
            }

            public void onReadingStarted(int i, MediaPeriodId mediaPeriodId) {
            }

            public void onLoadStarted(int i, @Nullable MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                String str;
                Format format = mediaLoadData.trackFormat;
                int i2 = mediaLoadData.trackType;
                int i3 = mediaLoadData.dataType;
                int i4 = mediaLoadData.trackSelectionReason;
                long j = mediaLoadData.mediaStartTimeMs;
                long j2 = mediaLoadData.mediaEndTimeMs;
                if (format == null) {
                    str = "null";
                } else {
                    str = Integer.toString(format.bitrate);
                }
                String str2 = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onLoadStarted: sourceId: ");
                sb.append(i2);
                sb.append(", type: ");
                sb.append(i3);
                sb.append(", trigger: ");
                sb.append(i4);
                sb.append(", bitrate: ");
                sb.append(str);
                sb.append(", mediaStartTimeMs: ");
                sb.append(j);
                sb.append(", mediaEndTimeMs: ");
                sb.append(j2);
                Log.v(str2, sb.toString());
                if (ExoPlayerVideoDisplayComponent.this.debugListener != null) {
                    ExoPlayerVideoDisplayComponent.this.debugListener.onLoadStarted(i2, i3, i4, format, j, j2);
                }
            }

            public void onLoadCompleted(int i, @Nullable MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                String str;
                long j;
                long j2;
                int i2;
                LoadEventInfo loadEventInfo2 = loadEventInfo;
                MediaLoadData mediaLoadData2 = mediaLoadData;
                Format format = mediaLoadData2.trackFormat;
                int i3 = mediaLoadData2.trackType;
                int i4 = mediaLoadData2.dataType;
                int i5 = mediaLoadData2.trackSelectionReason;
                long j3 = mediaLoadData2.mediaStartTimeMs;
                long j4 = mediaLoadData2.mediaEndTimeMs;
                long j5 = loadEventInfo2.bytesLoaded;
                long j6 = loadEventInfo2.elapsedRealtimeMs;
                long j7 = loadEventInfo2.loadDurationMs;
                if (format == null) {
                    str = "null";
                } else {
                    str = Integer.toString(format.bitrate);
                }
                long j8 = j7;
                String str2 = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                long j9 = j6;
                sb.append("onLoadCompleted: sourceId: ");
                sb.append(i3);
                sb.append(", bytesLoaded: ");
                sb.append(j5);
                sb.append(", type: ");
                sb.append(i4);
                sb.append(", bitrate: ");
                sb.append(str);
                sb.append(", startTime: ");
                sb.append(j3);
                sb.append(", endTime: ");
                sb.append(j4);
                Log.v(str2, sb.toString());
                if (ExoPlayerVideoDisplayComponent.this.debugListener != null) {
                    j2 = j8;
                    j = j5;
                    i2 = i4;
                    ExoPlayerVideoDisplayComponent.this.debugListener.onLoadCompleted(i3, j5, i4, i5, format, j3, j4, j9, j2);
                } else {
                    j2 = j8;
                    j = j5;
                    i2 = i4;
                }
                if (i2 == 1) {
                    if (ExoPlayerVideoDisplayComponent.this.exoPlayer != null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put(ExoPlayerVideoDisplayComponent.BUFFERED_POSITION, Long.valueOf(ExoPlayerVideoDisplayComponent.this.exoPlayer.getBufferedPosition()));
                        hashMap.put(AbstractEvent.PERCENT_COMPLETE, Integer.valueOf(ExoPlayerVideoDisplayComponent.this.exoPlayer.getBufferedPercentage()));
                        ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.BUFFERED_UPDATE, hashMap);
                        if (j2 < 1000) {
                            j2 = 1000;
                        }
                        long j10 = (j * 8) / (j2 / 1000);
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put(AbstractEvent.FORWARD_BUFFER_SECONDS, Long.valueOf(ExoPlayerVideoDisplayComponent.this.exoPlayer.getBufferedPosition() / 1000));
                        hashMap2.put(AbstractEvent.MEASURED_BPS, Long.valueOf(j10));
                        hashMap2.put(AbstractEvent.MEDIA_BYTES_TRANSFERRED, Long.valueOf(ExoPlayerVideoDisplayComponent.this.playerBandwidthMeter.getTotalBytesTransferred()));
                        ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.ANALYTICS_VIDEO_ENGAGEMENT, hashMap2);
                        return;
                    }
                    return;
                }
            }

            public void onLoadCanceled(int i, @Nullable MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                int i2 = mediaLoadData.trackType;
                long j = loadEventInfo.bytesLoaded;
                String str = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onLoadCanceled: trackType: ");
                sb.append(i2);
                sb.append(", bytesLoaded: ");
                sb.append(j);
                Log.v(str, sb.toString());
            }

            public void onLoadError(int i, @Nullable MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
                int i2 = mediaLoadData.trackType;
                String str = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onLoadError track type:");
                sb.append(i2);
                Log.e(str, sb.toString(), iOException);
                ExoPlayerVideoDisplayComponent exoPlayerVideoDisplayComponent = ExoPlayerVideoDisplayComponent.this;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("onLoadError: sourceId: ");
                sb2.append(i2);
                exoPlayerVideoDisplayComponent.emitErrorEvent(sb2.toString(), iOException);
                if (ExoPlayerVideoDisplayComponent.this.internalErrorListener != null) {
                    ExoPlayerVideoDisplayComponent.this.internalErrorListener.onLoadError(i2, iOException);
                }
                ExoPlayerVideoDisplayComponent.this.reportPlayerState();
            }

            public void onUpstreamDiscarded(int i, MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
                long j = mediaLoadData.mediaStartTimeMs;
                long j2 = mediaLoadData.mediaEndTimeMs;
                String str = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onUpstreamDiscarded: sourceId: ");
                sb.append(i);
                sb.append(", mediaStartTimeMs: ");
                sb.append(j);
                sb.append(", mediaEndTimeMs: ");
                sb.append(j2);
                Log.v(str, sb.toString());
            }

            public void onDownstreamFormatChanged(int i, @Nullable MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
                String str;
                String str2;
                int i2;
                String str3;
                int i3;
                Format format = mediaLoadData.trackFormat;
                int i4 = mediaLoadData.trackType;
                int i5 = mediaLoadData.trackSelectionReason;
                long j = mediaLoadData.mediaStartTimeMs;
                if (format == null) {
                    str = "null";
                } else {
                    str = Integer.toString(format.bitrate);
                }
                String str4 = ExoPlayerVideoDisplayComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onDownstreamFormatChanged: sourceId: ");
                sb.append(i4);
                sb.append(", bitrate: ");
                sb.append(str);
                sb.append(", trigger: ");
                sb.append(i5);
                sb.append(", mediaTimeMs: ");
                sb.append(j);
                Log.v(str4, sb.toString());
                HashMap hashMap = new HashMap();
                hashMap.put("video", ExoPlayerVideoDisplayComponent.this.getCurrentVideo());
                hashMap.put("source", ExoPlayerVideoDisplayComponent.this.getCurrentSource());
                hashMap.put(ExoPlayerVideoDisplayComponent.EXOPLAYER_FORMAT, format);
                ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(ExoPlayerVideoDisplayComponent.RENDITION_CHANGED, hashMap);
                HashMap hashMap2 = new HashMap();
                Object currentManifest = ExoPlayerVideoDisplayComponent.this.exoPlayer == null ? null : ExoPlayerVideoDisplayComponent.this.exoPlayer.getCurrentManifest();
                if (format == null) {
                    str2 = "";
                } else {
                    str2 = MediaSourceUtil.findRenditionUrl(currentManifest, i4, format);
                }
                hashMap2.put(AbstractEvent.RENDITION_URL, Convert.toString(str2));
                String str5 = AbstractEvent.RENDITION_INDICATED_BPS;
                int i6 = 0;
                if (format == null) {
                    i2 = 0;
                } else {
                    i2 = format.bitrate;
                }
                hashMap2.put(str5, Integer.valueOf(i2));
                String str6 = AbstractEvent.RENDITION_MIME_TYPE;
                if (format == null) {
                    str3 = "";
                } else {
                    str3 = format.containerMimeType;
                }
                hashMap2.put(str6, str3);
                String str7 = AbstractEvent.RENDITION_HEIGHT;
                if (format == null) {
                    i3 = 0;
                } else {
                    i3 = format.height;
                }
                hashMap2.put(str7, Integer.valueOf(i3));
                String str8 = AbstractEvent.RENDITION_WIDTH;
                if (format != null) {
                    i6 = format.width;
                }
                hashMap2.put(str8, Integer.valueOf(i6));
                hashMap2.put(AbstractEvent.MEDIA_BYTES_TRANSFERRED, Long.valueOf(ExoPlayerVideoDisplayComponent.this.playerBandwidthMeter.getTotalBytesTransferred()));
                ExoPlayerVideoDisplayComponent.this.eventEmitter.emit(EventType.ANALYTICS_VIDEO_ENGAGEMENT, hashMap2);
                if (ExoPlayerVideoDisplayComponent.this.debugListener != null) {
                    if (i4 == 2) {
                        ExoPlayerVideoDisplayComponent.this.videoFormat = format;
                        ExoPlayerVideoDisplayComponent.this.debugListener.onVideoFormatEnabled(format, i5, j);
                    } else if (i4 == 1) {
                        ExoPlayerVideoDisplayComponent.this.debugListener.onAudioFormatEnabled(format, i5, j);
                    }
                }
            }
        };
        this.playerStatePoller = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:21:0x00ed  */
            /* JADX WARNING: Removed duplicated region for block: B:22:0x00f3  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    boolean r0 = r0.isPlaying
                    r1 = 1
                    if (r0 == 0) goto L_0x00ea
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    com.google.android.exoplayer2.SimpleExoPlayer r0 = r0.exoPlayer
                    if (r0 == 0) goto L_0x00ea
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    com.google.android.exoplayer2.SimpleExoPlayer r0 = r0.exoPlayer
                    int r0 = r0.getPlaybackState()
                    r2 = 3
                    if (r0 != r2) goto L_0x00ea
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    com.google.android.exoplayer2.SimpleExoPlayer r2 = r0.exoPlayer
                    long r2 = r2.getCurrentPosition()
                    int r2 = com.brightcove.player.util.NumberUtil.safeLongToInt(r2)
                    r0.playheadPosition = r2
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    boolean r0 = r0.isLive()
                    if (r0 == 0) goto L_0x0040
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r2 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    int r3 = r2.playheadPosition
                    long r3 = (long) r3
                    long r2 = r2.getRelativeLivePlayheadPosition(r3)
                    goto L_0x0045
                L_0x0040:
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r2 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    int r2 = r2.playheadPosition
                    long r2 = (long) r2
                L_0x0045:
                    r4 = 0
                    int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                    if (r6 < 0) goto L_0x00ea
                    java.util.HashMap r4 = new java.util.HashMap
                    r4.<init>()
                    java.lang.String r5 = "video"
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r6 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    com.brightcove.player.model.Video r6 = r6.getCurrentVideo()
                    r4.put(r5, r6)
                    java.lang.String r5 = "source"
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r6 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    com.brightcove.player.model.Source r6 = r6.getCurrentSource()
                    r4.put(r5, r6)
                    java.lang.String r5 = "playheadPosition"
                    int r2 = com.brightcove.player.util.NumberUtil.safeLongToInt(r2)
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                    r4.put(r5, r2)
                    java.lang.String r2 = "duration"
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r3 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    int r3 = r3.getDuration()
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                    r4.put(r2, r3)
                    if (r0 == 0) goto L_0x00aa
                    java.lang.String r0 = "minPosition"
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r2 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    long r2 = r2.minPosition
                    int r2 = com.brightcove.player.util.NumberUtil.safeLongToInt(r2)
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                    r4.put(r0, r2)
                    java.lang.String r0 = "maxPosition"
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r2 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    long r2 = r2.maxPosition
                    int r2 = com.brightcove.player.util.NumberUtil.safeLongToInt(r2)
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                    r4.put(r0, r2)
                L_0x00aa:
                    java.lang.String r0 = "forwardBufferSeconds"
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r2 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    com.google.android.exoplayer2.SimpleExoPlayer r2 = r2.exoPlayer
                    long r2 = r2.getBufferedPosition()
                    r5 = 1000(0x3e8, double:4.94E-321)
                    long r2 = r2 / r5
                    java.lang.Long r2 = java.lang.Long.valueOf(r2)
                    r4.put(r0, r2)
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    boolean r0 = r0.hasPlaybackStarted
                    if (r0 == 0) goto L_0x00d4
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    com.brightcove.player.event.EventEmitter r0 = r0.eventEmitter
                    java.lang.String r2 = "progress"
                    r0.emit(r2, r4)
                    goto L_0x00eb
                L_0x00d4:
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    com.brightcove.player.event.EventEmitter r0 = r0.eventEmitter
                    java.lang.String r2 = "didPlay"
                    r0.emit(r2, r4)
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    r0.hasPlaybackStarted = r1
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    r0.stopUpdater()
                    goto L_0x00eb
                L_0x00ea:
                    r1 = 0
                L_0x00eb:
                    if (r1 == 0) goto L_0x00f3
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r0 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    int r0 = r0.progressInterval
                    long r0 = (long) r0
                    goto L_0x00f5
                L_0x00f3:
                    r0 = 50
                L_0x00f5:
                    com.brightcove.player.display.ExoPlayerVideoDisplayComponent r2 = com.brightcove.player.display.ExoPlayerVideoDisplayComponent.this
                    android.os.Handler r2 = r2.progressHandler
                    r2.postDelayed(r7, r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.display.ExoPlayerVideoDisplayComponent.AnonymousClass10.run():void");
            }
        };
        this.mLoadControlConfig = new Builder().build();
        this.eventEmitter = RegisteringEventEmitter.build(super.getEventEmitter(), getClass());
        eventEmitter.on(EventType.DID_SET_ANALYTICS_BASE_PARAMS, new com.brightcove.player.event.EventListener() {
            public void processEvent(Event event) {
                String str = (String) ((Map) event.properties.get(Fields.BASE_PARAMS)).get("feature");
                if (str == null || !str.equals(ExoPlayerVideoDisplayComponent.EXOPLAYER_ON)) {
                    ExoPlayerVideoDisplayComponent.this.emitAddAnalyticsBaseParams();
                }
            }
        });
        addListener(EventType.SELECT_AUDIO_TRACK, new OnSelectAudioTrackListener());
        addListener(EventType.SELECT_CLOSED_CAPTION_TRACK, new OnSelectClosedCaptionTrackListener());
        this.mainHandler = new Handler();
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        }
        this.playerBandwidthMeter = new PlayerBandwidthMeter(this.context, this.mainHandler, this.bandwidthMeterEventListener);
    }

    @Nullable
    public DefaultTrackSelector getTrackSelector() {
        return this.trackSelector;
    }

    @Nullable
    public TrackSelectorHelper getTrackSelectorHelper() {
        return this.trackSelectorHelper;
    }

    public void setTrackSelector(@NonNull DefaultTrackSelector defaultTrackSelector) {
        this.trackSelector = defaultTrackSelector;
    }

    /* access modifiers changed from: private */
    public void emitAddAnalyticsBaseParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("feature", EXOPLAYER_ON);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(Fields.BASE_PARAMS, hashMap);
        this.eventEmitter.emit(EventType.ADD_ANALYTICS_BASE_PARAMS, hashMap2);
    }

    /* access modifiers changed from: protected */
    public void initializeListeners() {
        addListener(EventType.PLAY, new ExoPlayerOnPlayListener());
        addListener(EventType.SEEK_TO, new ExoPlayerOnSeekListener());
        addListener(EventType.PAUSE, new ExoPlayerOnPauseListener());
        addListener(EventType.SET_SOURCE, new ExoPlayerOnSetSourceListener());
        addListener(EventType.STOP, new ExoPlayerOnStopListener());
        addListener(EventType.PREBUFFER_NEXT_VIDEO, new ExoPlayerOnPrebufferNextVideoListener());
        addListener("completed", new ExoPlayerOnCompletedListener());
        addListener(EventType.WILL_INTERRUPT_CONTENT, new ExoPlayerOnWillInterruptContentListener());
        addListener(EventType.WILL_RESUME_CONTENT, new ExoPlayerOnWillResumeContentListener());
        addListener(EventType.SET_VOLUME, new ExoPlayerOnSetVolumeListener());
        addListener(EventType.WILL_CHANGE_VIDEO, new OnWillChangeVideoListener());
        addListener(EventType.ON_FRAME_AVAILABLE, new OnFrameAvailableListener());
    }

    /* access modifiers changed from: protected */
    public void startUpdater() {
        stopUpdater();
        this.progressHandler.post(this.playerStatePoller);
    }

    /* access modifiers changed from: protected */
    public void stopUpdater() {
        if (this.progressHandler != null) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("stopUpdater: ");
            sb.append(this.progressHandler);
            Log.v(str, sb.toString());
            this.progressHandler.removeCallbacks(this.playerStatePoller);
        }
    }

    public MediaPlayer getMediaPlayer() {
        throw new UnsupportedOperationException();
    }

    public boolean hasDvr() {
        return isLive() && this.exoPlayer.isCurrentWindowSeekable() && this.isSeekable;
    }

    public boolean isLive() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        return simpleExoPlayer != null && simpleExoPlayer.isCurrentWindowDynamic();
    }

    public boolean isInLiveEdge() {
        return isLive() && getRelativeLivePlayheadPosition((long) this.playheadPosition) - ((long) getLiveEdge()) > -1000;
    }

    public long getPlayerCurrentPosition() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return -9223372036854775807L;
        }
        return simpleExoPlayer.getCurrentPosition();
    }

    public int getLiveEdge() {
        updateLiveWindowRange();
        return NumberUtil.safeLongToInt(this.maxPosition);
    }

    public ExoPlayer getExoPlayer() {
        return this.exoPlayer;
    }

    public int getPlayerState() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer != null) {
            return simpleExoPlayer.getPlaybackState();
        }
        return -1;
    }

    public void setDebugListener(InfoListener infoListener) {
        this.debugListener = infoListener;
    }

    public void setCaptionListener(CaptionListener captionListener2) {
        this.captionListener = captionListener2;
    }

    public void setMetadataListener(MetadataListener metadataListener2) {
        this.metadataListener = metadataListener2;
    }

    @Deprecated
    public void setMetadataListener(Id3MetadataListener id3MetadataListener2) {
        this.id3MetadataListener = id3MetadataListener2;
    }

    public void setInternalErrorListener(InternalErrorListener internalErrorListener2) {
        this.internalErrorListener = internalErrorListener2;
    }

    private void createPlayer(@NonNull Video video, @Nullable Source source) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("createPlayer: ");
        sb.append(Source.getSourceUrl(source));
        Log.v(str, sb.toString());
        getRenderView().setProjectionFormat(video.getProjectionFormat());
        if (this.resumePosition < 1) {
            this.playerBandwidthMeter.resetTotalBytesTransferred();
        }
        clearResumePosition();
        initializePlayer(video, source);
    }

    /* access modifiers changed from: private */
    public void initializePlayer(@NonNull Video video, @Nullable Source source) {
        if (this.exoPlayer == null) {
            AdaptiveTrackSelection.Factory factory = new AdaptiveTrackSelection.Factory();
            if (this.trackSelector == null) {
                this.trackSelector = new OfflineTrackSelector(factory);
            }
            updateTrackSelectorPeakBitrate();
            this.exoPlayer = createExoPlayer(video, source);
            this.trackSelectorHelper = new TrackSelectorHelper(this.exoPlayer, this.trackSelector);
            this.videoFormat = null;
            reportPlayerState();
            this.playheadPosition = 0;
            this.minPosition = -1;
            this.maxPosition = -1;
            this.hasPlaybackStarted = false;
            this.hasEmittedCaptionsLanguages = false;
            this.hasEmittedAudioTracks = false;
            stopUpdater();
            this.progressHandler = new Handler(Looper.getMainLooper());
            this.exoPlayer.addListener(this.exoplayerEventListener);
            this.exoPlayer.setVideoDebugListener(this.videoRendererEventListener);
            this.exoPlayer.setAudioDebugListener(this.audioRendererEventListener);
            this.exoPlayer.addMetadataOutput(this.metadataRendererListener);
            this.exoPlayer.addTextOutput(this.textRendererListener);
        }
        this.inErrorState = false;
        MediaSource mediaSource = getMediaSource();
        boolean z = this.resumeWindow != -1;
        if (z) {
            this.exoPlayer.seekTo(this.resumeWindow, this.resumePosition);
        }
        if (isCurrentVideo360Mode()) {
            this.prepared.set(false);
        } else {
            preparePlayer(mediaSource, !z);
        }
    }

    /* access modifiers changed from: protected */
    public void openVideo(@NonNull Video video, @Nullable Source source) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("openVideo: ");
        sb.append(Source.getSourceUrl(source));
        Log.v(str, sb.toString());
        destroyPlayer();
        createPlayer(video, source);
    }

    /* access modifiers changed from: protected */
    public void destroyPlayer() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("destroyPlayer: exoPlayer = ");
        sb.append(this.exoPlayer);
        Log.v(str, sb.toString());
        if (this.exoPlayer != null) {
            stopUpdater();
            this.playheadPosition = NumberUtil.safeLongToInt(this.exoPlayer.getCurrentPosition());
            updateResumePosition();
            this.renderView.release();
            this.exoPlayer.release();
            this.exoPlayer = null;
            this.trackSelectorHelper = null;
            this.drmSessionManager = null;
            this.fromSeekPosition = -1;
            this.prepared.set(false);
        }
    }

    private static String buildLanguageString(Format format) {
        return (TextUtils.isEmpty(format.language) || C.LANGUAGE_UNDETERMINED.equals(format.language)) ? "" : format.language;
    }

    /* access modifiers changed from: private */
    public MediaSource getMediaSource() {
        MediaSource mediaSource;
        Source currentSourceOrFail = getCurrentSourceOrFail();
        Video currentVideoOrFail = getCurrentVideoOrFail();
        DeliveryType deliveryType = currentSourceOrFail.getDeliveryType();
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(com.brightcove.player.C.HTTP_USER_AGENT, this.playerBandwidthMeter);
        Map map = (Map) currentVideoOrFail.getProperties().get("headers");
        if (map != null && !map.isEmpty()) {
            defaultHttpDataSourceFactory.getDefaultRequestProperties().set(map);
        }
        DataSource.Factory buildDataSourceFactory = buildDataSourceFactory(defaultHttpDataSourceFactory, true);
        switch (deliveryType) {
            case DASH:
                DashManifestParser brightcoveDashManifestParser = new BrightcoveDashManifestParser();
                if (currentSourceOrFail.isLocal()) {
                    brightcoveDashManifestParser = new OfflineDashManifestParser(brightcoveDashManifestParser, this.context);
                }
                mediaSource = new DashMediaSource.Factory(new DefaultDashChunkSource.Factory(buildDataSourceFactory), buildDataSourceFactory(defaultHttpDataSourceFactory, false)).setManifestParser(brightcoveDashManifestParser).setLoadErrorHandlingPolicy(new DefaultLoadErrorHandlingPolicy()).setLivePresentationDelayMs(30000, false).createMediaSource(Uri.parse(currentSourceOrFail.getUrl()));
                break;
            case HLS:
                mediaSource = new HlsMediaSource.Factory(buildDataSourceFactory).createMediaSource(Uri.parse(currentSourceOrFail.getUrl()));
                break;
            case MP4:
                mediaSource = new ExtractorMediaSource.Factory(buildDataSourceFactory).createMediaSource(Uri.parse(currentSourceOrFail.getUrl()));
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unsupported type: ");
                sb.append(deliveryType);
                throw new IllegalStateException(sb.toString());
        }
        mediaSource.addEventListener(this.mainHandler, this.adaptiveMediaSourceEventListener);
        return mediaSource;
    }

    /* access modifiers changed from: private */
    public void preparePlayer(MediaSource mediaSource, boolean z) {
        if (mediaSource != null) {
            pushSurface();
            this.exoPlayer.prepare(mediaSource, z, false);
            boolean z2 = true;
            this.prepared.set(true);
            Source currentSource = getCurrentSource();
            Video currentVideo = getCurrentVideo();
            if (currentSource == null || !currentSource.isLocal()) {
                z2 = false;
            }
            if (z2 && currentVideo != null) {
                List list = (List) currentVideo.getProperties().get(Video.Fields.CAPTION_SOURCES);
                if (list != null) {
                    list.clear();
                }
            }
            EventUtil.emit(this.eventEmitter, EventType.DID_SET_SOURCE, getCurrentVideo(), getCurrentSource());
        }
    }

    private void pushSurface() {
        Surface surface = this.hasSurface ? this.renderView.getSurface() : null;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("pushSurface: surface = ");
        sb.append(surface);
        Log.v(str, sb.toString());
        this.exoPlayer.setVideoSurface(surface);
    }

    /* access modifiers changed from: private */
    public void reportPlayerState() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer != null) {
            boolean playWhenReady = simpleExoPlayer.getPlayWhenReady();
            int playerState = getPlayerState();
            if (!(this.previousPlayWhenReady == playWhenReady && this.previousPlaybackState == playerState)) {
                switch (playerState) {
                    case 1:
                        this.previousState = playerState;
                        break;
                    case 2:
                        this.previousState = playerState;
                        HashMap hashMap = new HashMap();
                        hashMap.put("video", getCurrentVideo());
                        this.eventEmitter.emit(EventType.BUFFERING_STARTED, hashMap);
                        break;
                    case 3:
                        this.playheadPosition = NumberUtil.safeLongToInt(this.exoPlayer.getCurrentPosition());
                        if (this.previousState != 3 || playWhenReady) {
                            int i = this.previousState;
                            if (i == 3) {
                                play((long) this.playheadPosition);
                            } else if (i == 2) {
                                HashMap hashMap2 = new HashMap();
                                hashMap2.put("video", getCurrentVideo());
                                this.eventEmitter.emit(EventType.BUFFERING_COMPLETED, hashMap2);
                            }
                        } else {
                            pause();
                        }
                        maybeEmitAvailableCaptions(false);
                        maybeEmitAvailableAudioTracks();
                        this.previousState = playerState;
                        break;
                    case 4:
                        if (playWhenReady) {
                            HashMap hashMap3 = new HashMap();
                            hashMap3.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(NumberUtil.safeLongToInt(this.exoPlayer.getCurrentPosition())));
                            hashMap3.put("video", getCurrentVideo());
                            hashMap3.put("duration", Integer.valueOf(getDuration()));
                            this.eventEmitter.emit("completed", hashMap3);
                        }
                        this.previousState = playerState;
                        break;
                }
            }
            this.previousPlayWhenReady = playWhenReady;
            this.previousPlaybackState = playerState;
        }
    }

    /* access modifiers changed from: private */
    public void play(long j) {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.addListener(this.exoplayerEventListener);
        }
        if (this.fromSeekPosition != -1) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("play: fromSeekPosition = ");
            sb.append(this.fromSeekPosition);
            Log.v(str, sb.toString());
        } else if (j >= 0 && Math.abs(j - ((long) this.playheadPosition)) > 1000) {
            seekTo(j);
        }
        if (isLive() && !this.isPlaying && this.exoPlayer.getCurrentPosition() < 0) {
            emitDurationChanged();
            seekTo((long) getLiveEdge());
        }
        this.exoPlayer.setPlayWhenReady(true);
        this.isPlaying = true;
        startUpdater();
    }

    /* access modifiers changed from: private */
    public void pause() {
        this.exoPlayer.removeListener(this.exoplayerEventListener);
        this.exoPlayer.setPlayWhenReady(false);
        this.isPlaying = false;
        stopUpdater();
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(NumberUtil.safeLongToInt(this.exoPlayer.getCurrentPosition())));
        hashMap.put("video", getCurrentVideo());
        this.eventEmitter.emit(EventType.DID_PAUSE, hashMap);
    }

    /* access modifiers changed from: private */
    public void seekTo(long j) {
        int i;
        this.fromSeekPosition = this.playheadPosition;
        this.seekPosition = NumberUtil.safeLongToInt(j);
        if (j <= 0 || !isLive()) {
            i = NumberUtil.safeLongToInt(j);
        } else {
            long j2 = this.maxPosition;
            if (j > j2) {
                j = j2;
            }
            i = NumberUtil.safeLongToInt(getRealLivePlayheadPosition(j));
        }
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.seekTo((long) i);
        }
    }

    /* access modifiers changed from: private */
    public int getDuration() {
        int i = -1;
        if (isLive()) {
            return -1;
        }
        long duration = this.exoPlayer.getDuration();
        if (duration >= 0) {
            i = NumberUtil.safeLongToInt(duration);
        }
        return i < 0 ? NumberUtil.safeLongToInt(this.maxPosition) : i;
    }

    public Looper getPlaybackLooper() {
        return this.exoPlayer.getPlaybackLooper();
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    /* access modifiers changed from: private */
    public void emitDurationChanged() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer != null && simpleExoPlayer.getDuration() != -9223372036854775807L) {
            HashMap hashMap = new HashMap();
            hashMap.put("video", getCurrentVideo());
            hashMap.put("source", getCurrentSource());
            hashMap.put("duration", Integer.valueOf(getDuration()));
            long currentPosition = this.exoPlayer.getCurrentPosition();
            if (isLive()) {
                currentPosition = getRelativeLivePlayheadPosition(currentPosition);
            }
            hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(NumberUtil.safeLongToInt(currentPosition)));
            if (isLive()) {
                updateLiveWindowRange();
                hashMap.put(AbstractEvent.MIN_POSITION, Integer.valueOf(NumberUtil.safeLongToInt(this.minPosition)));
                hashMap.put(AbstractEvent.MAX_POSITION, Integer.valueOf(NumberUtil.safeLongToInt(this.maxPosition)));
            }
            this.eventEmitter.emit(EventType.VIDEO_DURATION_CHANGED, hashMap);
        }
    }

    private void updateLiveWindowRange() {
        long j;
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        Timeline currentTimeline = simpleExoPlayer == null ? null : simpleExoPlayer.getCurrentTimeline();
        if (isLive() && currentTimeline != null && !currentTimeline.isEmpty()) {
            currentTimeline.getWindow(this.exoPlayer.getCurrentWindowIndex(), this.windowHolder);
            currentTimeline.getPeriod(this.exoPlayer.getCurrentPeriodIndex(), this.periodHolder);
            long defaultPositionMs = this.windowHolder.getDefaultPositionMs();
            this.minPosition = getLiveElapseTime();
            if (defaultPositionMs == -9223372036854775807L) {
                j = 0;
            } else {
                j = this.minPosition + defaultPositionMs;
            }
            this.maxPosition = j;
            this.isSeekable = defaultPositionMs != 0;
        }
    }

    /* access modifiers changed from: private */
    public void emitSourceNotPlayable(Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("video", getCurrentVideo());
        hashMap.put("source", getCurrentSource());
        hashMap.put("error", exc);
        hashMap.put(AbstractEvent.ERROR_MESSAGE, exc.getLocalizedMessage());
        this.eventEmitter.emit(EventType.SOURCE_NOT_PLAYABLE, hashMap);
    }

    private void maybeEmitAvailableAudioTracks() {
        if (!this.hasEmittedAudioTracks && this.trackSelectorHelper != null) {
            HashMap hashMap = new HashMap();
            if (!this.trackSelectorHelper.getAvailableFormatList(1).isEmpty()) {
                Source currentSource = getCurrentSource();
                ArrayList arrayList = new ArrayList(this.trackSelectorHelper.getAudioTracksIndexMap(this.context, currentSource == null ? DeliveryType.UNKNOWN : currentSource.getDeliveryType(), currentSource != null && currentSource.isLocal()).values());
                String selectedAudioLanguage = this.trackSelectorHelper.getSelectedAudioLanguage();
                hashMap.put(AbstractEvent.TRACKS, arrayList);
                if (selectedAudioLanguage != null) {
                    hashMap.put(AbstractEvent.SELECTED_TRACK, selectedAudioLanguage);
                }
                this.eventEmitter.emit(EventType.AUDIO_TRACKS, hashMap);
                this.hasEmittedAudioTracks = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void maybeEmitAvailableCaptions(boolean z) {
        Pair pair;
        Pair pair2;
        if (!this.hasEmittedCaptionsLanguages && this.trackSelectorHelper != null) {
            HashMap hashMap = new HashMap();
            List<Format> availableFormatList = this.trackSelectorHelper.getAvailableFormatList(3);
            if (!availableFormatList.isEmpty()) {
                Video currentVideoOrFail = getCurrentVideoOrFail();
                Source currentSource = getCurrentSource();
                if (currentSource != null && currentSource.isLocal()) {
                    availableFormatList = this.trackSelectorHelper.findOfflineFormatList(this.context, availableFormatList);
                }
                List list = (List) currentVideoOrFail.getProperties().get(Video.Fields.CAPTION_SOURCES);
                if (list == null) {
                    list = new ArrayList();
                    currentVideoOrFail.getProperties().put(Video.Fields.CAPTION_SOURCES, list);
                }
                ArrayList arrayList = new ArrayList();
                Uri parse = Uri.parse(IN_MANIFEST_CC_URI_STR);
                for (Format format : availableFormatList) {
                    String buildLanguageString = buildLanguageString(format);
                    String str = format.sampleMimeType;
                    BrightcoveCaptionFormat brightcoveCaptionFormat = null;
                    if (buildLanguageString == null || buildLanguageString.isEmpty()) {
                        if (str != null && str.contains("608")) {
                            if (z) {
                                buildLanguageString = resourceBundle.getString(UNKNOWN_CC);
                                pair = Pair.create(Uri.EMPTY, BrightcoveCaptionFormat.createCaptionFormat(str, resourceBundle.getString(UNKNOWN_CC)));
                            } else {
                                this.trackSelectorHelper.enableTrack(3);
                            }
                        }
                        pair = null;
                    } else {
                        this.trackSelectorHelper.disableTrack(3);
                        pair = Pair.create(parse, BrightcoveCaptionFormat.createCaptionFormat(str, buildLanguageString));
                    }
                    if (pair == null) {
                        pair2 = null;
                    } else {
                        pair2 = BrightcoveCaptionFormat.findMatchingLanguageIgnoreMimeType(list, (BrightcoveCaptionFormat) pair.second);
                    }
                    if (pair2 != null) {
                        brightcoveCaptionFormat = (BrightcoveCaptionFormat) pair2.second;
                    }
                    if (brightcoveCaptionFormat == null) {
                        if (pair != null) {
                            list.add(pair);
                        }
                        if (buildLanguageString != null && !buildLanguageString.isEmpty()) {
                            arrayList.add(buildLanguageString);
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    hashMap.put(AbstractEvent.LANGUAGES, arrayList);
                    this.eventEmitter.emit(EventType.CAPTIONS_LANGUAGES, hashMap);
                    this.hasEmittedCaptionsLanguages = true;
                } else if (!list.isEmpty()) {
                    this.hasEmittedCaptionsLanguages = true;
                }
            }
        }
    }

    public Format getVideoFormat() {
        return this.videoFormat;
    }

    public int getPeakBitrate() {
        return this.peakBitrate;
    }

    public void setPeakBitrate(int i) {
        this.peakBitrate = i;
        updateTrackSelectorPeakBitrate();
    }

    private void updateTrackSelectorPeakBitrate() {
        int i = this.peakBitrate;
        if (i <= 0) {
            i = Integer.MAX_VALUE;
        }
        DefaultTrackSelector trackSelector2 = getTrackSelector();
        if (trackSelector2 != null) {
            trackSelector2.setParameters(trackSelector2.buildUponParameters().setMaxVideoBitrate(i).build());
        }
    }

    public BandwidthMeter getBandwidthMeter() {
        return this.playerBandwidthMeter.getDelegate();
    }

    public void setBandwidthMeter(BandwidthMeter bandwidthMeter) {
        this.playerBandwidthMeter.setDelegate(bandwidthMeter);
    }

    @Deprecated
    public long getMinBufferDurationToSwitchUp() {
        return this.minBufferDurationToSwitchUp;
    }

    @Deprecated
    public void setMinBufferDurationToSwitchUp(long j) {
        this.minBufferDurationToSwitchUp = j;
    }

    @Deprecated
    public long getMaxBufferDurationToSwitchDown() {
        return this.maxBufferDurationToSwitchDown;
    }

    @Deprecated
    public void setMaxBufferDurationToSwitchDown(long j) {
        this.maxBufferDurationToSwitchDown = j;
    }

    @Deprecated
    public int getMinBufferMs() {
        return this.minBufferMs;
    }

    @Deprecated
    public void setMinBufferMs(int i) {
        this.minBufferMs = i;
    }

    @Deprecated
    public int getMinRebufferMs() {
        return this.minRebufferMs;
    }

    @Deprecated
    public void setMinRebufferMs(int i) {
        this.minRebufferMs = i;
    }

    public LoadControlConfig getLoadControlConfig() {
        return this.mLoadControlConfig;
    }

    public void setLoadControlConfig(@NonNull LoadControlConfig loadControlConfig) {
        this.mLoadControlConfig = loadControlConfig;
    }

    private LoadControl createLoadControl() {
        AllocatorConfig allocatorConfig = this.mLoadControlConfig.getAllocatorConfig();
        return new DefaultLoadControl.Builder().setAllocator(new DefaultAllocator(allocatorConfig.isTrimOnReset(), allocatorConfig.getIndividualAllocationSize(), allocatorConfig.getInitialAllocationCount())).setBufferDurationsMs(this.mLoadControlConfig.getMinBufferMs(), this.mLoadControlConfig.getMaxBufferMs(), this.mLoadControlConfig.getBufferForPlaybackMs(), this.mLoadControlConfig.getBufferForPlaybackAfterRebufferMs()).setTargetBufferBytes(this.mLoadControlConfig.getTargetBufferBytes()).setPrioritizeTimeOverSizeThresholds(this.mLoadControlConfig.isPrioritizeTimeOverSizeThresholds()).createDefaultLoadControl();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "surfaceCreated");
        this.hasSurface = true;
        if (this.exoPlayer != null) {
            pushSurface();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("surfaceChanged: w = ");
        sb.append(i2);
        sb.append(", h = ");
        sb.append(i3);
        Log.d(str, sb.toString());
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "surfaceDestroyed");
        this.hasSurface = false;
        if (this.exoPlayer != null) {
            pushSurface();
            pause();
        }
    }

    @NonNull
    private SimpleExoPlayer createExoPlayer(@NonNull Video video, @Nullable Source source) {
        Map map;
        if ((source != null && source.hasKeySystem(Source.Fields.WIDEVINE_KEY_SYSTEM)) || video.getProperties().containsKey(BrightcoveMediaDrmCallback.DEFAULT_URL)) {
            try {
                UUID uuid = Constants.WIDEVINE_UUID;
                FrameworkMediaDrm newInstance = FrameworkMediaDrm.newInstance(Constants.WIDEVINE_UUID);
                Map properties = video.getProperties();
                if (source == null) {
                    map = new HashMap();
                } else {
                    map = source.getProperties();
                }
                ExoPlayerDrmSessionManager exoPlayerDrmSessionManager = new ExoPlayerDrmSessionManager(uuid, newInstance, WidevineMediaDrmCallback.create(properties, map), null, this.mainHandler, this.drmEventListener);
                this.drmSessionManager = exoPlayerDrmSessionManager;
                byte[] offlinePlaybackLicenseKey = video.getOfflinePlaybackLicenseKey();
                if (offlinePlaybackLicenseKey != null) {
                    exoPlayerDrmSessionManager.setMode(0, offlinePlaybackLicenseKey);
                }
                return ExoPlayerFactory.newSimpleInstance(this.context, (RenderersFactory) new DefaultRenderersFactory(this.context), (TrackSelector) this.trackSelector, createLoadControl(), (DrmSessionManager<FrameworkMediaCrypto>) exoPlayerDrmSessionManager, (BandwidthMeter) this.playerBandwidthMeter);
            } catch (UnsupportedDrmException e) {
                emitErrorEvent("DRM Error", e);
            }
        }
        return ExoPlayerFactory.newSimpleInstance(this.context, (RenderersFactory) new DefaultRenderersFactory(this.context), (TrackSelector) this.trackSelector, createLoadControl(), null, (BandwidthMeter) this.playerBandwidthMeter);
    }

    private DataSource.Factory buildDataSourceFactory(BaseFactory baseFactory, boolean z) {
        return new MultiDataSource.Factory(this.context, baseFactory, z ? this.playerBandwidthMeter : null);
    }

    /* access modifiers changed from: private */
    public long getRelativeLivePlayheadPosition(long j) {
        if (isLive()) {
            return j + getLiveElapseTime();
        }
        return -1;
    }

    private long getRealLivePlayheadPosition(long j) {
        if (isLive()) {
            return j - getLiveElapseTime();
        }
        return -1;
    }

    private long getLiveElapseTime() {
        Timeline currentTimeline = this.exoPlayer.getCurrentTimeline();
        if (currentTimeline.isEmpty() || !isLive()) {
            return 0;
        }
        currentTimeline.getPeriod(this.exoPlayer.getCurrentPeriodIndex(), this.periodHolder);
        return Math.abs(this.periodHolder.getPositionInWindowMs());
    }

    /* access modifiers changed from: private */
    public static boolean isBehindLiveWindow(ExoPlaybackException exoPlaybackException) {
        if (exoPlaybackException.type != 0) {
            return false;
        }
        for (Throwable sourceException = exoPlaybackException.getSourceException(); sourceException != null; sourceException = sourceException.getCause()) {
            if (sourceException instanceof BehindLiveWindowException) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void updateResumePosition() {
        this.resumeWindow = this.exoPlayer.getCurrentWindowIndex();
        this.resumePosition = Math.max(0, this.exoPlayer.getContentPosition());
    }

    /* access modifiers changed from: private */
    public void clearResumePosition() {
        this.resumeWindow = -1;
        this.resumePosition = -9223372036854775807L;
    }
}
