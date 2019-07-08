package com.brightcove.player.display;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import com.brightcove.player.analytics.Analytics;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.RendererConfig;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.ProjectionFormat;
import com.brightcove.player.util.Convert;
import com.brightcove.player.util.ErrorUtil;
import com.brightcove.player.util.EventUtil;
import com.brightcove.player.view.RenderView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.constants.Constants.Goals;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ListensFor(events = {"completed", "didSetSource", "pause", "play", "prebufferNextVideo", "seekTo", "setSource", "stop", "videoDurationChanged", "willChangeVideo", "willInterruptContent", "willResumeContent", "readyToPlay", "setVolume", "didSetVideo", "on360FrameAvailable"})
@Emits(events = {"bufferedUpdate", "completed", "didInterruptContent", "didPause", "didPlay", "didResumeContent", "didSeekTo", "didSetSource", "didStop", "error", "pause", "play", "progress", "sourceNotPlayable", "stop", "videoDurationChanged", "videoSizeKnown", "willChangeVideo", "readyToPlay"})
public class VideoDisplayComponent extends AbstractComponent {
    private static final int DEFAULT_PROGRESS_INTERVAL = 500;
    private static final String EMITTED_DID_SET_SOURCE = "emittedDidSetSource";
    private static final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(1);
    protected static final int ON_PLAY_SEEK_THRESHOLD = 1000;
    private static final int PLAYER_STATE_CREATED = 2;
    private static final int PLAYER_STATE_IDLE = 1;
    private static final int PLAYER_STATE_PREPARED = 3;
    /* access modifiers changed from: private */
    public static final String TAG = "VideoDisplayComponent";
    private Analytics analytics;
    protected String captionsPath;
    protected Context context;
    /* access modifiers changed from: private */
    @Nullable
    public Source currentSource;
    /* access modifiers changed from: private */
    @Nullable
    public Video currentVideo;
    protected int fromSeekPosition = -1;
    /* access modifiers changed from: private */
    public boolean hasErrored;
    /* access modifiers changed from: private */
    public boolean hasPlaybackStarted;
    /* access modifiers changed from: private */
    public boolean hasPrepared;
    protected boolean hasSurface;
    protected boolean isFrameAvailable;
    protected RendererConfig mRendererConfig;
    /* access modifiers changed from: private */
    public MediaPlayer mediaPlayer;
    protected Source nextSource;
    protected Video nextVideo;
    final OnBufferingUpdateListener onBufferingUpdateListener = new OnBufferingUpdateListener() {
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            HashMap hashMap = new HashMap();
            hashMap.put(AbstractEvent.PERCENT_COMPLETE, Integer.valueOf(i));
            VideoDisplayComponent.this.eventEmitter.emit(EventType.BUFFERED_UPDATE, hashMap);
        }
    };
    OnCompletedListener onCompletedListener;
    final OnCompletionListener onCompletionListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (!VideoDisplayComponent.this.hasErrored) {
                HashMap hashMap = new HashMap();
                if (mediaPlayer != null && VideoDisplayComponent.this.hasPrepared && VideoDisplayComponent.this.hasSurface) {
                    hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(mediaPlayer.getDuration()));
                    hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
                }
                hashMap.put("video", VideoDisplayComponent.this.currentVideo);
                VideoDisplayComponent.this.eventEmitter.emit("completed", hashMap);
            }
            if (VideoDisplayComponent.this.currentSource != null && VideoDisplayComponent.this.currentSource.getDeliveryType() == DeliveryType.HLS) {
                VideoDisplayComponent.this.destroyPlayer();
            }
        }
    };
    final OnErrorListener onErrorListener = new OnErrorListener() {
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            if (i == 1) {
                switch (i2) {
                    case -1014:
                    case -1013:
                    case -1012:
                        break;
                    default:
                        VideoDisplayComponent.this.error(i, i2);
                        break;
                }
            } else if (i != 100) {
                VideoDisplayComponent.this.error(i, i2);
            }
            return false;
        }
    };
    final OnInfoListener onInfoListener = new OnInfoListener() {
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (i == 1) {
                Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_UNKNOWN");
            } else if (i == 3) {
                Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_VIDEO_RENDERING_START");
            } else if (i != 900) {
                switch (i) {
                    case ALERT_VALUE:
                        Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
                        break;
                    case 701:
                        Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_BUFFERING_START");
                        break;
                    case 702:
                        Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_BUFFERING_END");
                        break;
                    case Goals.BMI_CONVERSION_FACTOR /*703*/:
                        Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH");
                        break;
                    default:
                        switch (i) {
                            case EMERGENCY_VALUE:
                                Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_BAD_INTERLEAVING");
                                break;
                            case 801:
                                Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_NOT_SEEKABLE");
                                break;
                            case 802:
                                Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_METADATA_UPDATE");
                                break;
                            default:
                                String access$1700 = VideoDisplayComponent.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("unknown MediaPlayer info: what = ");
                                sb.append(i);
                                Log.i(access$1700, sb.toString());
                                break;
                        }
                }
            } else {
                Log.i(VideoDisplayComponent.TAG, "MEDIA_INFO_TIMED_TEXT_ERROR");
            }
            return true;
        }
    };
    OnPauseListener onPauseListener;
    OnPlayListener onPlayListener;
    OnPrebufferNextVideoListener onPrebufferNextVideoListener;
    final OnPreparedListener onPreparedListener = new OnPreparedListener() {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<VideoDisplayComponent> cls = VideoDisplayComponent.class;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            if (!VideoDisplayComponent.this.hasErrored) {
                VideoDisplayComponent.this.hasPrepared = true;
                if (VideoDisplayComponent.this.currentSource.getProperties().get(VideoDisplayComponent.EMITTED_DID_SET_SOURCE) == null) {
                    EventUtil.emit(VideoDisplayComponent.this.eventEmitter, EventType.DID_SET_SOURCE, VideoDisplayComponent.this.currentVideo, VideoDisplayComponent.this.currentSource);
                }
                VideoDisplayComponent.this.emitVideoSize(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
                HashMap hashMap = new HashMap();
                hashMap.put("video", VideoDisplayComponent.this.currentVideo);
                hashMap.put("source", VideoDisplayComponent.this.currentSource);
                hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
                VideoDisplayComponent.this.eventEmitter.emit(EventType.VIDEO_DURATION_CHANGED, hashMap);
                VideoDisplayComponent.this.playerState = 3;
            }
        }
    };
    final OnSeekCompleteListener onSeekCompleteListener = new OnSeekCompleteListener() {
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            String access$1700 = VideoDisplayComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onSeekComplete: fromSeekPosition = ");
            sb.append(VideoDisplayComponent.this.fromSeekPosition);
            sb.append(", seekPosition = ");
            sb.append(VideoDisplayComponent.this.seekPosition);
            Log.v(access$1700, sb.toString());
            if (VideoDisplayComponent.this.hasSurface && mediaPlayer.getCurrentPosition() < VideoDisplayComponent.this.seekPosition && !VideoDisplayComponent.this.reseeking) {
                mediaPlayer.seekTo(VideoDisplayComponent.this.seekPosition);
                VideoDisplayComponent.this.reseeking = true;
            } else if (VideoDisplayComponent.this.fromSeekPosition != -1) {
                HashMap hashMap = new HashMap();
                if (VideoDisplayComponent.this.hasSurface) {
                    hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(mediaPlayer.getCurrentPosition()));
                }
                hashMap.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(VideoDisplayComponent.this.seekPosition));
                hashMap.put(AbstractEvent.FROM_SEEK_POSITION, Integer.valueOf(VideoDisplayComponent.this.fromSeekPosition));
                hashMap.put("video", VideoDisplayComponent.this.currentVideo);
                VideoDisplayComponent.this.eventEmitter.emit(EventType.DID_SEEK_TO, hashMap);
                VideoDisplayComponent videoDisplayComponent = VideoDisplayComponent.this;
                videoDisplayComponent.fromSeekPosition = -1;
                videoDisplayComponent.reseeking = false;
            }
        }
    };
    OnSeekListener onSeekListener;
    OnSetSourceListener onSetSourceListener;
    OnSetVolumeListener onSetVolumeListener;
    OnStopListener onStopListener;
    final OnVideoSizeChangedListener onVideoSizeChangedListener = new OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            if (i != 0 && i2 != 0) {
                VideoDisplayComponent.this.emitVideoSize(i, i2);
            }
        }
    };
    OnWillInterruptContentListener onWillInterruptContentListener;
    OnWillResumeContentListener onWillResumeContentListener;
    /* access modifiers changed from: private */
    public int playerState = 1;
    protected int playheadPosition;
    protected int progressInterval = 500;
    protected RenderView renderView;
    protected boolean reseeking;
    protected int seekPosition;
    protected ScheduledFuture<?> updater;

    private class OnCompletedListener implements EventListener {
        private OnCompletedListener() {
        }

        @Default
        public void processEvent(Event event) {
            if (VideoDisplayComponent.this.nextSource != null) {
                final UUID randomUUID = UUID.randomUUID();
                VideoDisplayComponent.this.eventEmitter.once(EventType.WILL_CHANGE_VIDEO, new EventListener() {
                    @Default
                    public void processEvent(Event event) {
                        Log.v(VideoDisplayComponent.TAG, "OnCompletedListener: WILL_CHANGE_VIDEO");
                        if (event.properties.get(AbstractEvent.UUID).equals(randomUUID)) {
                            VideoDisplayComponent.this.destroyPlayer();
                            String access$1700 = VideoDisplayComponent.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("OnCompletedListener: currentSource = ");
                            sb.append(VideoDisplayComponent.this.currentSource);
                            sb.append(", nextSource = ");
                            sb.append(VideoDisplayComponent.this.nextSource);
                            Log.v(access$1700, sb.toString());
                            VideoDisplayComponent.this.setVideoSource(VideoDisplayComponent.this.nextVideo, VideoDisplayComponent.this.nextSource);
                            VideoDisplayComponent.this.nextVideo = null;
                            VideoDisplayComponent.this.nextSource = null;
                            VideoDisplayComponent.this.eventEmitter.once(EventType.DID_SET_SOURCE, new EventListener() {
                                @Default
                                public void processEvent(Event event) {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("video", VideoDisplayComponent.this.currentVideo);
                                    VideoDisplayComponent.this.eventEmitter.emit(EventType.PLAY, hashMap);
                                }
                            });
                            VideoDisplayComponent.this.openCurrentVideoSource();
                        }
                    }
                });
                HashMap hashMap = new HashMap();
                hashMap.put(AbstractEvent.CURRENT_VIDEO, VideoDisplayComponent.this.currentVideo);
                hashMap.put(AbstractEvent.NEXT_VIDEO, VideoDisplayComponent.this.nextVideo);
                hashMap.put(AbstractEvent.UUID, randomUUID);
                VideoDisplayComponent.this.eventEmitter.emit(EventType.WILL_CHANGE_VIDEO, hashMap);
            }
        }
    }

    private class OnFrameAvailableListener implements EventListener {
        private OnFrameAvailableListener() {
        }

        @Default
        public void processEvent(Event event) {
            VideoDisplayComponent videoDisplayComponent = VideoDisplayComponent.this;
            videoDisplayComponent.isFrameAvailable = true;
            if (videoDisplayComponent.playerState == 1) {
                VideoDisplayComponent videoDisplayComponent2 = VideoDisplayComponent.this;
                videoDisplayComponent2.createPlayer(videoDisplayComponent2.currentVideo, VideoDisplayComponent.this.currentSource);
            }
        }
    }

    private class OnPauseListener implements EventListener {
        private OnPauseListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(VideoDisplayComponent.TAG, "OnPauseListener");
            if (VideoDisplayComponent.this.mediaPlayer != null && VideoDisplayComponent.this.hasPrepared && VideoDisplayComponent.this.hasSurface && VideoDisplayComponent.this.mediaPlayer.isPlaying()) {
                VideoDisplayComponent.this.mediaPlayer.pause();
                HashMap hashMap = new HashMap();
                hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(VideoDisplayComponent.this.mediaPlayer.getCurrentPosition()));
                hashMap.put("video", VideoDisplayComponent.this.currentVideo);
                VideoDisplayComponent.this.eventEmitter.emit(EventType.DID_PAUSE, hashMap);
            }
        }
    }

    private class OnPlayListener implements EventListener {
        private OnPlayListener() {
        }

        @Default
        public void processEvent(Event event) {
            final int i;
            String access$1700 = VideoDisplayComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnPlayListener: mediaPlayer = ");
            sb.append(VideoDisplayComponent.this.mediaPlayer);
            sb.append(", hasPrepared = ");
            sb.append(VideoDisplayComponent.this.hasPrepared);
            sb.append(", hasSurface = ");
            sb.append(VideoDisplayComponent.this.hasSurface);
            Log.v(access$1700, sb.toString());
            VideoDisplayComponent.this.hasPlaybackStarted = false;
            if (VideoDisplayComponent.this.currentSource != null) {
                if (event.properties.containsKey(AbstractEvent.PLAYHEAD_POSITION)) {
                    i = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
                } else {
                    String access$17002 = VideoDisplayComponent.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("OnPlayListener: playheadPosition = ");
                    sb2.append(VideoDisplayComponent.this.playheadPosition);
                    Log.v(access$17002, sb2.toString());
                    i = VideoDisplayComponent.this.playheadPosition;
                }
                if (VideoDisplayComponent.this.mediaPlayer == null) {
                    VideoDisplayComponent.this.playheadPosition = 0;
                    Log.v(VideoDisplayComponent.TAG, "OnPlayListener: MediaPlayer was null - creating a new one.");
                    VideoDisplayComponent.this.eventEmitter.once(EventType.VIDEO_DURATION_CHANGED, new EventListener() {
                        @Default
                        public void processEvent(Event event) {
                            VideoDisplayComponent.this.play(i);
                        }
                    });
                    VideoDisplayComponent.this.openCurrentVideoSource();
                } else if (!VideoDisplayComponent.this.hasPrepared) {
                    Log.v(VideoDisplayComponent.TAG, "OnPlayListener: MediaPlayer has not been prepared yet.");
                    VideoDisplayComponent.this.eventEmitter.once(EventType.DID_SET_SOURCE, new EventListener() {
                        @Default
                        public void processEvent(Event event) {
                            VideoDisplayComponent.this.play(i);
                        }
                    });
                } else if (!VideoDisplayComponent.this.hasSurface) {
                    Log.v(VideoDisplayComponent.TAG, "OnPlayListener: Surface is not available yet.");
                    VideoDisplayComponent.this.eventEmitter.once(EventType.READY_TO_PLAY, new EventListener() {
                        @Default
                        public void processEvent(Event event) {
                            VideoDisplayComponent.this.play(i);
                        }
                    });
                } else if (!VideoDisplayComponent.this.mediaPlayer.isPlaying()) {
                    VideoDisplayComponent.this.play(i);
                } else {
                    Log.w(VideoDisplayComponent.TAG, "Already playing.");
                }
            } else {
                Log.e(VideoDisplayComponent.TAG, "Source has not been set yet.");
            }
        }
    }

    private class OnPrebufferNextVideoListener implements EventListener {
        private OnPrebufferNextVideoListener() {
        }

        @Default
        public void processEvent(Event event) {
            VideoDisplayComponent.this.nextVideo = (Video) event.properties.get("video");
            VideoDisplayComponent.this.nextSource = (Source) event.properties.get("source");
        }
    }

    private class OnSeekListener implements EventListener {
        private OnSeekListener() {
        }

        @Default
        public void processEvent(Event event) {
            String access$1700 = VideoDisplayComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnSeekListener: mediaPlayer = ");
            sb.append(VideoDisplayComponent.this.mediaPlayer);
            Log.v(access$1700, sb.toString());
            if (event.properties.containsKey(AbstractEvent.SEEK_POSITION)) {
                final int integerProperty = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
                String access$17002 = VideoDisplayComponent.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("OnSeekListener: position = ");
                sb2.append(integerProperty);
                Log.v(access$17002, sb2.toString());
                if (VideoDisplayComponent.this.mediaPlayer == null || !VideoDisplayComponent.this.hasPrepared || !VideoDisplayComponent.this.hasSurface) {
                    VideoDisplayComponent.this.eventEmitter.once(EventType.VIDEO_DURATION_CHANGED, new EventListener() {
                        @Default
                        public void processEvent(Event event) {
                            VideoDisplayComponent.this.mediaPlayer.seekTo(integerProperty);
                        }
                    });
                    VideoDisplayComponent.this.openCurrentVideoSource();
                    return;
                }
                VideoDisplayComponent videoDisplayComponent = VideoDisplayComponent.this;
                videoDisplayComponent.fromSeekPosition = videoDisplayComponent.playheadPosition;
                VideoDisplayComponent videoDisplayComponent2 = VideoDisplayComponent.this;
                videoDisplayComponent2.seekPosition = integerProperty;
                videoDisplayComponent2.mediaPlayer.seekTo(integerProperty);
                return;
            }
            Log.e(VideoDisplayComponent.TAG, "Seek event must pass the seekPosition property.");
        }
    }

    protected class OnSetSourceListener implements EventListener {
        protected OnSetSourceListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(VideoDisplayComponent.TAG, "OnSetSourceListener");
            VideoDisplayComponent.this.destroyPlayer();
            VideoDisplayComponent.this.currentVideo = (Video) event.properties.get("video");
            VideoDisplayComponent.this.currentSource = (Source) event.properties.get("source");
            if (VideoDisplayComponent.this.currentSource != null && VideoDisplayComponent.this.currentSource.getUrl() != null) {
                if (VideoDisplayComponent.this.currentSource.getDeliveryType() == DeliveryType.HLS || VideoDisplayComponent.this.currentSource.getDeliveryType() == DeliveryType.WVM) {
                    VideoDisplayComponent.this.currentSource.getProperties().put(VideoDisplayComponent.EMITTED_DID_SET_SOURCE, Boolean.valueOf(true));
                    EventUtil.emit(VideoDisplayComponent.this.eventEmitter, EventType.DID_SET_SOURCE, VideoDisplayComponent.this.currentVideo, VideoDisplayComponent.this.currentSource);
                    return;
                }
                VideoDisplayComponent.this.openCurrentVideoSource();
            }
        }
    }

    private class OnSetVolumeListener implements EventListener {
        private OnSetVolumeListener() {
        }

        @Default
        public void processEvent(Event event) {
            String access$1700 = VideoDisplayComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnSetVolumeListener: mediaPlayer = ");
            sb.append(VideoDisplayComponent.this.mediaPlayer);
            Log.v(access$1700, sb.toString());
            if (!event.properties.containsKey(AbstractEvent.LEFT_VOLUME) || !event.properties.containsKey(AbstractEvent.RIGHT_VOLUME)) {
                Log.e(VideoDisplayComponent.TAG, "SET_VOLUME requires LEFT_VOLUME and RIGHT_VOLUME properties.");
                return;
            }
            float floatValue = ((Float) event.properties.get(AbstractEvent.LEFT_VOLUME)).floatValue();
            float floatValue2 = ((Float) event.properties.get(AbstractEvent.RIGHT_VOLUME)).floatValue();
            String access$17002 = VideoDisplayComponent.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("OnSetVolumeListener: leftVolume = ");
            sb2.append(floatValue);
            sb2.append(" rightVolume = ");
            sb2.append(floatValue2);
            Log.v(access$17002, sb2.toString());
            if (floatValue < BitmapDescriptorFactory.HUE_RED || floatValue > 1.0f || floatValue2 < BitmapDescriptorFactory.HUE_RED || floatValue2 > 1.0f) {
                String access$17003 = VideoDisplayComponent.TAG;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("LEFT_VOLUME and RIGHT_VOLUME must be between 0.0f and 1.0f: ");
                sb3.append(floatValue);
                sb3.append(", ");
                sb3.append(floatValue2);
                Log.e(access$17003, sb3.toString());
                return;
            }
            VideoDisplayComponent.this.mediaPlayer.setVolume(floatValue, floatValue2);
        }
    }

    private class OnStopListener implements EventListener {
        private OnStopListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(VideoDisplayComponent.TAG, "OnStopListener");
            if (VideoDisplayComponent.this.mediaPlayer != null) {
                HashMap hashMap = new HashMap();
                hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(VideoDisplayComponent.this.mediaPlayer.getCurrentPosition()));
                hashMap.put("video", VideoDisplayComponent.this.currentVideo);
                VideoDisplayComponent.this.eventEmitter.emit(EventType.DID_STOP, hashMap);
            }
            VideoDisplayComponent.this.destroyPlayer();
        }
    }

    protected class OnWillChangeVideoListener implements EventListener {
        protected OnWillChangeVideoListener() {
        }

        @Default
        public void processEvent(Event event) {
            VideoDisplayComponent.this.playheadPosition = 0;
        }
    }

    private class OnWillInterruptContentListener implements EventListener {
        private OnWillInterruptContentListener() {
        }

        @Default
        public void processEvent(Event event) {
            String access$1700 = VideoDisplayComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnWillInterruptContentListener: mediaPlayer = ");
            sb.append(VideoDisplayComponent.this.mediaPlayer);
            sb.append(", hasPrepared = ");
            sb.append(VideoDisplayComponent.this.hasPrepared);
            sb.append(", hasSurface = ");
            sb.append(VideoDisplayComponent.this.hasSurface);
            sb.append(", currentSource = ");
            sb.append(VideoDisplayComponent.this.currentSource);
            Log.v(access$1700, sb.toString());
            if (VideoDisplayComponent.this.mediaPlayer != null && VideoDisplayComponent.this.hasPrepared && VideoDisplayComponent.this.hasSurface && VideoDisplayComponent.this.mediaPlayer.isPlaying()) {
                Log.v(VideoDisplayComponent.TAG, "OnWillInterruptContentListener: isPlaying");
                if (VideoDisplayComponent.this.currentSource == null || VideoDisplayComponent.this.currentSource.getDeliveryType() == DeliveryType.HLS) {
                    int i = VideoDisplayComponent.this.playheadPosition;
                    VideoDisplayComponent.this.destroyPlayer();
                    VideoDisplayComponent.this.playheadPosition = i;
                } else {
                    VideoDisplayComponent.this.mediaPlayer.pause();
                }
            }
            VideoDisplayComponent.this.renderView.setVisibility(4);
            VideoDisplayComponent.this.eventEmitter.emit(EventType.DID_INTERRUPT_CONTENT);
        }
    }

    private class OnWillResumeContentListener implements EventListener {
        private OnWillResumeContentListener() {
        }

        @Default
        public void processEvent(Event event) {
            VideoDisplayComponent.this.renderView.setVisibility(0);
            Event event2 = (Event) event.properties.get(AbstractEvent.ORIGINAL_EVENT);
            String access$1700 = VideoDisplayComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnWillResumeContentListener: originalEvent = ");
            sb.append(event2);
            Log.v(access$1700, sb.toString());
            if (event2 != null) {
                VideoDisplayComponent.this.eventEmitter.emit(event2.getType(), event2.properties);
            }
            VideoDisplayComponent.this.eventEmitter.emit(EventType.DID_RESUME_CONTENT);
        }
    }

    public int getLiveEdge() {
        return 0;
    }

    public boolean hasDvr() {
        return false;
    }

    public boolean isInLiveEdge() {
        return false;
    }

    public boolean isLive() {
        return false;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public VideoDisplayComponent(RenderView renderView2, EventEmitter eventEmitter) {
        super(eventEmitter, VideoDisplayComponent.class);
        if (renderView2 == null || eventEmitter == null) {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.RENDER_VIEW_AND_EVENT_EMITTER_REQUIRED));
        }
        boolean z = false;
        if (renderView2 instanceof View) {
            z = ((View) renderView2).isInEditMode();
        }
        if (!z) {
            this.analytics = new Analytics(eventEmitter, renderView2.getContext());
        }
        this.renderView = renderView2;
        this.context = renderView2.getContext();
        setHolderType();
        initializeListeners();
    }

    /* access modifiers changed from: protected */
    public void setVideoSource(@Nullable Video video, @Nullable Source source) {
        this.currentVideo = video;
        this.currentSource = source;
    }

    @Nullable
    public Video getCurrentVideo() {
        return this.currentVideo;
    }

    @Nullable
    public Source getCurrentSource() {
        return this.currentSource;
    }

    @NonNull
    public Video getCurrentVideoOrFail() {
        Video video = this.currentVideo;
        if (video != null) {
            return video;
        }
        throw new IllegalStateException("Video is null");
    }

    @NonNull
    public Source getCurrentSourceOrFail() {
        Source source = this.currentSource;
        if (source != null) {
            return source;
        }
        throw new IllegalStateException("Source is null");
    }

    private void setHolderType() {
        if (VERSION.SDK_INT <= 10) {
            SurfaceHolder holder = this.renderView.getHolder();
            if (holder != null) {
                holder.setType(3);
            }
        }
    }

    public void setRendererConfig(RendererConfig rendererConfig) {
        this.mRendererConfig = rendererConfig;
    }

    public long getPlayerCurrentPosition() {
        long j;
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 == null) {
            j = -9223372036854775807L;
        } else {
            j = (long) mediaPlayer2.getCurrentPosition();
        }
        if (j < 0) {
            return -9223372036854775807L;
        }
        return j;
    }

    public void setProgressInterval(int i) {
        if (i > 500) {
            throw new IllegalArgumentException("The PROGRESS event needs to be emitted at least every 500 milliseconds. Please call setProgressInterval with a number less than or equal to 500.");
        } else if (i >= 50) {
            this.progressInterval = i;
        } else {
            throw new IllegalArgumentException("Emitting the PROGRESS event more rapidly than every 50 milliseconds is detrimental to performance. Please call setProgressInterval with a number greater than 50.");
        }
    }

    /* access modifiers changed from: protected */
    public void initializeListeners() {
        this.onSetSourceListener = new OnSetSourceListener();
        this.onPlayListener = new OnPlayListener();
        this.onPauseListener = new OnPauseListener();
        this.onSeekListener = new OnSeekListener();
        this.onStopListener = new OnStopListener();
        this.onPrebufferNextVideoListener = new OnPrebufferNextVideoListener();
        this.onCompletedListener = new OnCompletedListener();
        this.onWillInterruptContentListener = new OnWillInterruptContentListener();
        this.onWillResumeContentListener = new OnWillResumeContentListener();
        this.onSetVolumeListener = new OnSetVolumeListener();
        addListener(EventType.SET_SOURCE, this.onSetSourceListener);
        addListener(EventType.PLAY, this.onPlayListener);
        addListener(EventType.SEEK_TO, this.onSeekListener);
        addListener(EventType.PAUSE, this.onPauseListener);
        addListener(EventType.STOP, this.onStopListener);
        addListener(EventType.PREBUFFER_NEXT_VIDEO, this.onPrebufferNextVideoListener);
        addListener("completed", this.onCompletedListener);
        addListener(EventType.WILL_INTERRUPT_CONTENT, this.onWillInterruptContentListener);
        addListener(EventType.WILL_RESUME_CONTENT, this.onWillResumeContentListener);
        addListener(EventType.SET_VOLUME, this.onSetVolumeListener);
        addListener(EventType.WILL_CHANGE_VIDEO, new OnWillChangeVideoListener());
        addListener(EventType.ON_FRAME_AVAILABLE, new OnFrameAvailableListener());
    }

    /* access modifiers changed from: protected */
    public void startUpdater() {
        if (this.updater == null) {
            Log.v(TAG, "startUpdater");
            this.updater = EXECUTOR.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    try {
                        if (VideoDisplayComponent.this.mediaPlayer != null && VideoDisplayComponent.this.hasPrepared && VideoDisplayComponent.this.hasSurface && VideoDisplayComponent.this.mediaPlayer.isPlaying() && VideoDisplayComponent.this.mediaPlayer.getCurrentPosition() >= 0) {
                            HashMap hashMap = new HashMap(4);
                            hashMap.put("video", VideoDisplayComponent.this.currentVideo);
                            hashMap.put("source", VideoDisplayComponent.this.currentSource);
                            VideoDisplayComponent.this.playheadPosition = VideoDisplayComponent.this.mediaPlayer.getCurrentPosition();
                            hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(VideoDisplayComponent.this.playheadPosition));
                            hashMap.put("duration", Integer.valueOf(VideoDisplayComponent.this.mediaPlayer.getDuration()));
                            if (VideoDisplayComponent.this.playheadPosition > 0 && !VideoDisplayComponent.this.hasPlaybackStarted) {
                                VideoDisplayComponent.this.eventEmitter.emit(EventType.DID_PLAY, hashMap);
                                VideoDisplayComponent.this.hasPlaybackStarted = true;
                            }
                            VideoDisplayComponent.this.eventEmitter.emit("progress", hashMap);
                        }
                    } catch (IllegalStateException e) {
                        VideoDisplayComponent.this.destroyPlayer();
                        String access$1700 = VideoDisplayComponent.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Media player position sampled when it was in an invalid state: ");
                        sb.append(e.getMessage());
                        Log.e(access$1700, sb.toString(), e);
                        VideoDisplayComponent.this.eventEmitter.emit("error", Collections.singletonMap("error", e));
                    } catch (Exception e2) {
                        VideoDisplayComponent.this.destroyPlayer();
                        String access$17002 = VideoDisplayComponent.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Error monitoring playback progress");
                        sb2.append(e2.getMessage());
                        Log.e(access$17002, sb2.toString(), e2);
                        VideoDisplayComponent.this.eventEmitter.emit("error", Collections.singletonMap("error", e2));
                    }
                }
            }, 0, (long) this.progressInterval, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: protected */
    public void stopUpdater() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("stopUpdater: ");
        sb.append(this.updater);
        Log.v(str, sb.toString());
        ScheduledFuture<?> scheduledFuture = this.updater;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.updater = null;
        }
    }

    public Analytics getAnalytics() {
        return this.analytics;
    }

    public RenderView getRenderView() {
        return this.renderView;
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    /* access modifiers changed from: protected */
    public void openCurrentVideoSource() {
        Video video = this.currentVideo;
        if (video == null) {
            emitErrorEvent("Current video is null");
        } else {
            openVideo(video, this.currentSource);
        }
    }

    /* access modifiers changed from: protected */
    public void openVideo(@NonNull final Video video, @Nullable final Source source) {
        if (!TextUtils.isEmpty(Convert.toString(Source.getSourceUrl(source)).trim())) {
            this.renderView.setProjectionFormat(video.getProjectionFormat());
            destroyPlayer();
            this.hasPlaybackStarted = false;
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("openVideo: hasSurface = ");
            sb.append(this.hasSurface);
            Log.v(str, sb.toString());
            if (!this.hasSurface) {
                this.eventEmitter.once(EventType.READY_TO_PLAY, new EventListener() {
                    public void processEvent(Event event) {
                        VideoDisplayComponent.this.createPlayer(video, source);
                    }
                });
            } else if (isCurrentVideo360Mode() && !this.isFrameAvailable) {
            } else {
                if (this.renderView.getSurface() != null) {
                    createPlayer(video, source);
                } else {
                    Log.e(TAG, "openVideo: null surface");
                }
            }
        } else {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.INVALID_URL));
        }
    }

    protected static Map<String, String> getHeaders(Video video, Source source) {
        Map<String, String> map;
        try {
            map = (Map) video.getProperties().get("headers");
        } catch (Exception e) {
            Log.e(TAG, "Failed to use Video headers.", e);
            map = null;
        }
        try {
            Map<String, String> map2 = (Map) source.getProperties().get("headers");
            if (map2 != null) {
                if (map != null) {
                    map.putAll(map2);
                } else {
                    map = map2;
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "Failed to use Source headers.", e2);
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("headers = ");
        sb.append(map);
        Log.v(str, sb.toString());
        return map;
    }

    /* access modifiers changed from: private */
    public void createPlayer(Video video, Source source) {
        Log.v(TAG, "createPlayer");
        SurfaceHolder holder = this.renderView.getHolder();
        try {
            this.mediaPlayer = new MediaPlayer();
            if (holder != null) {
                this.mediaPlayer.setDisplay(holder);
            } else {
                setSurface(this.mediaPlayer, this.renderView.getSurface());
            }
            this.mediaPlayer.setOnPreparedListener(this.onPreparedListener);
            this.mediaPlayer.setOnVideoSizeChangedListener(this.onVideoSizeChangedListener);
            this.mediaPlayer.setOnCompletionListener(this.onCompletionListener);
            this.mediaPlayer.setOnSeekCompleteListener(this.onSeekCompleteListener);
            this.mediaPlayer.setOnBufferingUpdateListener(this.onBufferingUpdateListener);
            this.mediaPlayer.setOnErrorListener(this.onErrorListener);
            this.mediaPlayer.setOnInfoListener(this.onInfoListener);
            this.mediaPlayer.setScreenOnWhilePlaying(true);
            if (VERSION.SDK_INT >= 14) {
                setDataSource(this.mediaPlayer, this.context, Uri.parse(source.getUrl()), getHeaders(video, source));
            } else {
                if (getHeaders(video, source) != null) {
                    Log.w(TAG, "Headers ignored below API level 14");
                }
                this.mediaPlayer.setDataSource(this.context, Uri.parse(source.getUrl()));
            }
            this.mediaPlayer.prepareAsync();
            this.playheadPosition = 0;
            this.playerState = 2;
        } catch (IOException e) {
            Log.e(TAG, "IOException trying to play video", e);
            this.eventEmitter.emit("error", Collections.singletonMap("error", e));
        }
    }

    @SuppressLint({"NewApi"})
    @TargetApi(14)
    private void setDataSource(MediaPlayer mediaPlayer2, Context context2, Uri uri, Map<String, String> map) throws IOException {
        mediaPlayer2.setDataSource(context2, uri, map);
    }

    /* access modifiers changed from: protected */
    public void destroyPlayer() {
        stopUpdater();
        Source source = this.currentSource;
        if (source != null) {
            source.getProperties().remove(EMITTED_DID_SET_SOURCE);
        }
        if (this.mediaPlayer != null) {
            Log.i(TAG, "Shutting down current MediaPlayer");
            this.renderView.release();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
            this.hasPrepared = false;
            this.hasErrored = false;
        }
        this.playerState = 1;
        this.isFrameAvailable = false;
    }

    /* access modifiers changed from: private */
    public void play(final int i) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("play: position = ");
        sb.append(i);
        sb.append(", playheadPosition = ");
        sb.append(this.playheadPosition);
        Log.v(str, sb.toString());
        if (this.hasSurface) {
            SurfaceHolder holder = this.renderView.getHolder();
            if (holder != null) {
                this.mediaPlayer.setDisplay(holder);
            } else {
                setSurface(this.mediaPlayer, this.renderView.getSurface());
            }
            if (this.fromSeekPosition != -1) {
                String str2 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("play: fromSeekPosition = ");
                sb2.append(this.fromSeekPosition);
                Log.v(str2, sb2.toString());
            } else if (i >= 0 && Math.abs(i - this.playheadPosition) > 1000) {
                this.seekPosition = i;
                this.mediaPlayer.seekTo(i);
            }
            if (this.updater == null) {
                startUpdater();
            }
            this.mediaPlayer.start();
            return;
        }
        Log.v(TAG, "play: Surface is not available yet.");
        this.eventEmitter.once(EventType.READY_TO_PLAY, new EventListener() {
            @Default
            public void processEvent(Event event) {
                VideoDisplayComponent.this.play(i);
            }
        });
    }

    @SuppressLint({"NewApi"})
    @TargetApi(14)
    private void setSurface(MediaPlayer mediaPlayer2, Surface surface) {
        if (VERSION.SDK_INT >= 14) {
            mediaPlayer2.setSurface(surface);
        }
    }

    /* access modifiers changed from: protected */
    public void emitVideoSize(int i, int i2) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("emitVideoSize: ");
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        sb.append(", ");
        sb.append(this.renderView.getWidth());
        sb.append(", ");
        sb.append(this.renderView.getHeight());
        Log.v(str, sb.toString());
        if (i > 0 && i2 > 0) {
            if (i != this.renderView.getVideoWidth() || i2 != this.renderView.getVideoHeight()) {
                this.renderView.setVideoSize(i, i2);
                HashMap hashMap = new HashMap();
                hashMap.put("width", Integer.valueOf(i));
                hashMap.put("height", Integer.valueOf(i2));
                this.eventEmitter.emit(EventType.VIDEO_SIZE_KNOWN, hashMap);
            }
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "surfaceCreated");
        this.hasSurface = true;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Log.d(TAG, "surfaceChanged");
        if (!(this.mediaPlayer == null || surfaceHolder == null || isCurrentVideo360Mode())) {
            if (surfaceHolder.getSurface() != null) {
                this.mediaPlayer.setDisplay(surfaceHolder);
            } else {
                String message = ErrorUtil.getMessage(ErrorUtil.INVALID_SURFACE);
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("surfaceChanged: ");
                sb.append(message);
                Log.e(str, sb.toString());
                this.eventEmitter.emit("error", Collections.singletonMap(AbstractEvent.ERROR_MESSAGE, message));
            }
        }
        this.hasSurface = true;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "surfaceDestroyed");
        if (this.mediaPlayer != null) {
            if (Boolean.valueOf(System.getProperty("releaseWhenSurfaceDestroyed")).booleanValue()) {
                destroyPlayer();
            } else if (this.mediaPlayer.isPlaying()) {
                Source source = this.currentSource;
                if (source == null || source.getDeliveryType() == DeliveryType.HLS) {
                    destroyPlayer();
                } else {
                    this.mediaPlayer.pause();
                }
            }
        }
        this.hasSurface = false;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        surfaceCreated(null);
    }

    public void onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        surfaceDestroyed(null);
    }

    /* access modifiers changed from: private */
    public void error(int i, int i2) {
        this.hasErrored = true;
        HashMap hashMap = new HashMap();
        hashMap.put("video", this.currentVideo);
        hashMap.put("source", this.currentSource);
        hashMap.put("errorCode", Integer.valueOf(i));
        hashMap.put(AbstractEvent.ERROR_EXTRA, Integer.valueOf(i2));
        String str = AbstractEvent.ERROR_MESSAGE;
        StringBuilder sb = new StringBuilder();
        sb.append("MediaPlayer ");
        sb.append(ErrorUtil.getMessage("error"));
        hashMap.put(str, sb.toString());
        this.eventEmitter.emit(EventType.SOURCE_NOT_PLAYABLE, hashMap);
    }

    /* access modifiers changed from: protected */
    public void emitErrorEvent(String str) {
        Log.e(TAG, str);
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.ERROR_MESSAGE, str);
        hashMap.put("video", this.currentVideo);
        hashMap.put("source", this.currentSource);
        this.eventEmitter.emit("error", hashMap);
    }

    /* access modifiers changed from: protected */
    public void emitErrorEvent(String str, Exception exc) {
        Log.e(TAG, str, exc);
        HashMap hashMap = new HashMap();
        hashMap.put("video", this.currentVideo);
        hashMap.put("source", this.currentSource);
        hashMap.put("error", exc);
        hashMap.put(AbstractEvent.ERROR_MESSAGE, str);
        this.eventEmitter.emit("error", hashMap);
    }

    public boolean isCurrentVideo360Mode() {
        Video video = this.currentVideo;
        return (video == null || video.getProjectionFormat() == ProjectionFormat.NORMAL) ? false : true;
    }
}
