package com.mopub.nativeads;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.TextureView;
import com.google.android.exoplayer2.DefaultLoadControl.Builder;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player.DefaultEventListener;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibilityTracker.VisibilityChecker;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.RepeatingHandlerRunnable;
import com.mopub.mobileads.VastTracker;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.network.TrackingRequest;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeVideoController extends DefaultEventListener implements OnAudioFocusChangeListener {
    public static final long RESUME_FINISHED_THRESHOLD = 750;
    public static final int STATE_BUFFERING = 2;
    public static final int STATE_CLEARED = 5;
    public static final int STATE_ENDED = 4;
    public static final int STATE_IDLE = 1;
    public static final int STATE_READY = 3;
    @NonNull
    private static final Map<Long, NativeVideoController> sManagerMap = new HashMap(4);
    private boolean mAppAudioEnabled;
    private boolean mAudioEnabled;
    @NonNull
    private AudioManager mAudioManager;
    @Nullable
    private MediaCodecAudioRenderer mAudioRenderer;
    /* access modifiers changed from: private */
    @NonNull
    public final Context mContext;
    @Nullable
    private volatile ExoPlayer mExoPlayer;
    private boolean mExoPlayerStateStartedFromIdle;
    @Nullable
    private BitmapDrawable mFinalFrame;
    @NonNull
    private final Handler mHandler;
    @Nullable
    private Listener mListener;
    @NonNull
    private final MoPubExoPlayerFactory mMoPubExoPlayerFactory;
    @NonNull
    private NativeVideoProgressRunnable mNativeVideoProgressRunnable;
    @Nullable
    private OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    @Nullable
    private WeakReference<Object> mOwnerRef;
    private boolean mPlayWhenReady;
    private int mPreviousExoPlayerState;
    @Nullable
    private Surface mSurface;
    @Nullable
    private TextureView mTextureView;
    @NonNull
    private VastVideoConfig mVastVideoConfig;
    @Nullable
    private MediaCodecVideoRenderer mVideoRenderer;

    public interface Listener {
        void onError(Exception exc);

        void onStateChanged(boolean z, int i);
    }

    @VisibleForTesting
    static class MoPubExoPlayerFactory {
        MoPubExoPlayerFactory() {
        }

        public ExoPlayer newInstance(@NonNull Renderer[] rendererArr, @NonNull TrackSelector trackSelector, @Nullable LoadControl loadControl) {
            return ExoPlayerFactory.newInstance(rendererArr, trackSelector, loadControl);
        }
    }

    static class NativeVideoProgressRunnable extends RepeatingHandlerRunnable {
        @NonNull
        private final Context mContext;
        private long mCurrentPosition;
        private long mDuration;
        @Nullable
        private ExoPlayer mExoPlayer;
        @Nullable
        private ProgressListener mProgressListener;
        private boolean mStopRequested;
        @Nullable
        private TextureView mTextureView;
        @NonNull
        private final VastVideoConfig mVastVideoConfig;
        @NonNull
        private final VisibilityChecker mVisibilityChecker;
        @NonNull
        private final List<VisibilityTrackingEvent> mVisibilityTrackingEvents;

        public interface ProgressListener {
            void updateProgress(int i);
        }

        NativeVideoProgressRunnable(@NonNull Context context, @NonNull Handler handler, @NonNull List<VisibilityTrackingEvent> list, @NonNull VastVideoConfig vastVideoConfig) {
            this(context, handler, list, new VisibilityChecker(), vastVideoConfig);
        }

        @VisibleForTesting
        NativeVideoProgressRunnable(@NonNull Context context, @NonNull Handler handler, @NonNull List<VisibilityTrackingEvent> list, @NonNull VisibilityChecker visibilityChecker, @NonNull VastVideoConfig vastVideoConfig) {
            super(handler);
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(handler);
            Preconditions.checkNotNull(list);
            Preconditions.checkNotNull(vastVideoConfig);
            this.mContext = context.getApplicationContext();
            this.mVisibilityTrackingEvents = list;
            this.mVisibilityChecker = visibilityChecker;
            this.mVastVideoConfig = vastVideoConfig;
            this.mDuration = -1;
            this.mStopRequested = false;
        }

        /* access modifiers changed from: 0000 */
        public void setExoPlayer(@Nullable ExoPlayer exoPlayer) {
            this.mExoPlayer = exoPlayer;
        }

        /* access modifiers changed from: 0000 */
        public void setTextureView(@Nullable TextureView textureView) {
            this.mTextureView = textureView;
        }

        /* access modifiers changed from: 0000 */
        public void setProgressListener(@Nullable ProgressListener progressListener) {
            this.mProgressListener = progressListener;
        }

        /* access modifiers changed from: 0000 */
        public void seekTo(long j) {
            this.mCurrentPosition = j;
        }

        /* access modifiers changed from: 0000 */
        public long getCurrentPosition() {
            return this.mCurrentPosition;
        }

        /* access modifiers changed from: 0000 */
        public long getDuration() {
            return this.mDuration;
        }

        /* access modifiers changed from: 0000 */
        public void requestStop() {
            this.mStopRequested = true;
        }

        /* access modifiers changed from: 0000 */
        public void checkImpressionTrackers(boolean z) {
            int i = 0;
            for (VisibilityTrackingEvent visibilityTrackingEvent : this.mVisibilityTrackingEvents) {
                if (visibilityTrackingEvent.isTracked) {
                    i++;
                } else {
                    if (!z) {
                        VisibilityChecker visibilityChecker = this.mVisibilityChecker;
                        TextureView textureView = this.mTextureView;
                        if (!visibilityChecker.isVisible(textureView, textureView, visibilityTrackingEvent.minimumPercentageVisible, visibilityTrackingEvent.minimumVisiblePx)) {
                        }
                    }
                    visibilityTrackingEvent.totalQualifiedPlayCounter = (int) (((long) visibilityTrackingEvent.totalQualifiedPlayCounter) + this.mUpdateIntervalMillis);
                    if (z || visibilityTrackingEvent.totalQualifiedPlayCounter >= visibilityTrackingEvent.totalRequiredPlayTimeMs) {
                        visibilityTrackingEvent.strategy.execute();
                        visibilityTrackingEvent.isTracked = true;
                        i++;
                    }
                }
            }
            if (i == this.mVisibilityTrackingEvents.size() && this.mStopRequested) {
                stop();
            }
        }

        public void doWork() {
            ExoPlayer exoPlayer = this.mExoPlayer;
            if (exoPlayer != null && exoPlayer.getPlayWhenReady()) {
                this.mCurrentPosition = this.mExoPlayer.getCurrentPosition();
                this.mDuration = this.mExoPlayer.getDuration();
                checkImpressionTrackers(false);
                ProgressListener progressListener = this.mProgressListener;
                if (progressListener != null) {
                    progressListener.updateProgress((int) ((((float) this.mCurrentPosition) / ((float) this.mDuration)) * 1000.0f));
                }
                List<VastTracker> untriggeredTrackersBefore = this.mVastVideoConfig.getUntriggeredTrackersBefore((int) this.mCurrentPosition, (int) this.mDuration);
                if (!untriggeredTrackersBefore.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    for (VastTracker vastTracker : untriggeredTrackersBefore) {
                        if (!vastTracker.isTracked()) {
                            arrayList.add(vastTracker.getContent());
                            vastTracker.setTracked();
                        }
                    }
                    TrackingRequest.makeTrackingHttpRequest((Iterable<String>) arrayList, this.mContext);
                }
            }
        }
    }

    static class VisibilityTrackingEvent {
        boolean isTracked;
        int minimumPercentageVisible;
        Integer minimumVisiblePx;
        OnTrackedStrategy strategy;
        int totalQualifiedPlayCounter;
        int totalRequiredPlayTimeMs;

        interface OnTrackedStrategy {
            void execute();
        }

        VisibilityTrackingEvent() {
        }
    }

    public void onLoadingChanged(boolean z) {
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    @NonNull
    public static NativeVideoController createForId(long j, @NonNull Context context, @NonNull List<VisibilityTrackingEvent> list, @NonNull VastVideoConfig vastVideoConfig) {
        NativeVideoController nativeVideoController = new NativeVideoController(context, list, vastVideoConfig);
        sManagerMap.put(Long.valueOf(j), nativeVideoController);
        return nativeVideoController;
    }

    @NonNull
    @VisibleForTesting
    public static NativeVideoController createForId(long j, @NonNull Context context, @NonNull VastVideoConfig vastVideoConfig, @NonNull NativeVideoProgressRunnable nativeVideoProgressRunnable, @NonNull MoPubExoPlayerFactory moPubExoPlayerFactory, @NonNull AudioManager audioManager) {
        NativeVideoController nativeVideoController = new NativeVideoController(context, vastVideoConfig, nativeVideoProgressRunnable, moPubExoPlayerFactory, audioManager);
        sManagerMap.put(Long.valueOf(j), nativeVideoController);
        return nativeVideoController;
    }

    @VisibleForTesting
    public static void setForId(long j, @NonNull NativeVideoController nativeVideoController) {
        sManagerMap.put(Long.valueOf(j), nativeVideoController);
    }

    @Nullable
    public static NativeVideoController getForId(long j) {
        return (NativeVideoController) sManagerMap.get(Long.valueOf(j));
    }

    @Nullable
    public static NativeVideoController remove(long j) {
        return (NativeVideoController) sManagerMap.remove(Long.valueOf(j));
    }

    private NativeVideoController(@NonNull Context context, @NonNull List<VisibilityTrackingEvent> list, @NonNull VastVideoConfig vastVideoConfig) {
        this(context, vastVideoConfig, new NativeVideoProgressRunnable(context, new Handler(Looper.getMainLooper()), list, vastVideoConfig), new MoPubExoPlayerFactory(), (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO));
    }

    private NativeVideoController(@NonNull Context context, @NonNull VastVideoConfig vastVideoConfig, @NonNull NativeVideoProgressRunnable nativeVideoProgressRunnable, @NonNull MoPubExoPlayerFactory moPubExoPlayerFactory, @NonNull AudioManager audioManager) {
        this.mPreviousExoPlayerState = 1;
        this.mExoPlayerStateStartedFromIdle = true;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastVideoConfig);
        Preconditions.checkNotNull(moPubExoPlayerFactory);
        Preconditions.checkNotNull(audioManager);
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mVastVideoConfig = vastVideoConfig;
        this.mNativeVideoProgressRunnable = nativeVideoProgressRunnable;
        this.mMoPubExoPlayerFactory = moPubExoPlayerFactory;
        this.mAudioManager = audioManager;
    }

    public void setListener(@Nullable Listener listener) {
        this.mListener = listener;
    }

    public void setProgressListener(@Nullable ProgressListener progressListener) {
        this.mNativeVideoProgressRunnable.setProgressListener(progressListener);
    }

    public void setOnAudioFocusChangeListener(@Nullable OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.mOnAudioFocusChangeListener = onAudioFocusChangeListener;
    }

    public void setPlayWhenReady(boolean z) {
        if (this.mPlayWhenReady != z) {
            this.mPlayWhenReady = z;
            setExoPlayWhenReady();
        }
    }

    public int getPlaybackState() {
        if (this.mExoPlayer == null) {
            return 5;
        }
        return this.mExoPlayer.getPlaybackState();
    }

    public void setAudioEnabled(boolean z) {
        this.mAudioEnabled = z;
        setExoAudio();
    }

    public void setAppAudioEnabled(boolean z) {
        if (this.mAppAudioEnabled != z) {
            this.mAppAudioEnabled = z;
            if (this.mAppAudioEnabled) {
                this.mAudioManager.requestAudioFocus(this, 3, 1);
            } else {
                this.mAudioManager.abandonAudioFocus(this);
            }
        }
    }

    public void setAudioVolume(float f) {
        if (this.mAudioEnabled) {
            setExoAudio(f);
        }
    }

    public void onAudioFocusChange(int i) {
        OnAudioFocusChangeListener onAudioFocusChangeListener = this.mOnAudioFocusChangeListener;
        if (onAudioFocusChangeListener != null) {
            onAudioFocusChangeListener.onAudioFocusChange(i);
        }
    }

    public void setTextureView(@NonNull TextureView textureView) {
        Preconditions.checkNotNull(textureView);
        this.mSurface = new Surface(textureView.getSurfaceTexture());
        this.mTextureView = textureView;
        this.mNativeVideoProgressRunnable.setTextureView(this.mTextureView);
        setExoSurface(this.mSurface);
    }

    public void prepare(@NonNull Object obj) {
        Preconditions.checkNotNull(obj);
        this.mOwnerRef = new WeakReference<>(obj);
        clearExistingPlayer();
        preparePlayer();
        setExoSurface(this.mSurface);
    }

    public void clear() {
        setPlayWhenReady(false);
        this.mSurface = null;
        clearExistingPlayer();
    }

    public void release(@NonNull Object obj) {
        Preconditions.checkNotNull(obj);
        WeakReference<Object> weakReference = this.mOwnerRef;
        if ((weakReference == null ? null : weakReference.get()) == obj) {
            clearExistingPlayer();
        }
    }

    public void onPlayerStateChanged(boolean z, int i) {
        if (i == 4 && this.mFinalFrame == null) {
            if (this.mExoPlayer == null || this.mSurface == null || this.mTextureView == null) {
                MoPubLog.w("onPlayerStateChanged called afer view has been recycled.");
                return;
            } else {
                this.mFinalFrame = new BitmapDrawable(this.mContext.getResources(), this.mTextureView.getBitmap());
                this.mNativeVideoProgressRunnable.requestStop();
            }
        }
        this.mPreviousExoPlayerState = i;
        if (i == 3) {
            this.mExoPlayerStateStartedFromIdle = false;
        } else if (i == 1) {
            this.mExoPlayerStateStartedFromIdle = true;
        }
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onStateChanged(z, i);
        }
    }

    public void seekTo(long j) {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.seekTo(j);
            this.mNativeVideoProgressRunnable.seekTo(j);
        }
    }

    public long getCurrentPosition() {
        return this.mNativeVideoProgressRunnable.getCurrentPosition();
    }

    public long getDuration() {
        return this.mNativeVideoProgressRunnable.getDuration();
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onError(exoPlaybackException);
            this.mNativeVideoProgressRunnable.requestStop();
        }
    }

    public void handleCtaClick(@NonNull Context context) {
        triggerImpressionTrackers();
        this.mVastVideoConfig.handleClickWithoutResult(context, 0);
    }

    public boolean hasFinalFrame() {
        return this.mFinalFrame != null;
    }

    @Nullable
    public Drawable getFinalFrame() {
        return this.mFinalFrame;
    }

    /* access modifiers changed from: 0000 */
    public void triggerImpressionTrackers() {
        this.mNativeVideoProgressRunnable.checkImpressionTrackers(true);
    }

    private void clearExistingPlayer() {
        if (this.mExoPlayer != null) {
            setExoSurface(null);
            this.mExoPlayer.stop();
            this.mExoPlayer.release();
            this.mExoPlayer = null;
            this.mNativeVideoProgressRunnable.stop();
            this.mNativeVideoProgressRunnable.setExoPlayer(null);
        }
    }

    private void preparePlayer() {
        if (this.mExoPlayer == null) {
            MediaCodecVideoRenderer mediaCodecVideoRenderer = new MediaCodecVideoRenderer(this.mContext, MediaCodecSelector.DEFAULT, 0, this.mHandler, null, 10);
            this.mVideoRenderer = mediaCodecVideoRenderer;
            this.mAudioRenderer = new MediaCodecAudioRenderer(this.mContext, MediaCodecSelector.DEFAULT);
            DefaultAllocator defaultAllocator = new DefaultAllocator(true, 65536, 32);
            Builder builder = new Builder();
            builder.setAllocator(defaultAllocator);
            this.mExoPlayer = this.mMoPubExoPlayerFactory.newInstance(new Renderer[]{this.mVideoRenderer, this.mAudioRenderer}, new DefaultTrackSelector(), builder.createDefaultLoadControl());
            this.mNativeVideoProgressRunnable.setExoPlayer(this.mExoPlayer);
            this.mExoPlayer.addListener(this);
            AnonymousClass1 r0 = new Factory() {
                public DataSource createDataSource() {
                    return new HttpDiskCompositeDataSource(NativeVideoController.this.mContext, "exo_demo");
                }
            };
            AnonymousClass2 r1 = new ExtractorsFactory() {
                public Extractor[] createExtractors() {
                    return new Extractor[]{new Mp4Extractor()};
                }
            };
            ExtractorMediaSource.Factory factory = new ExtractorMediaSource.Factory(r0);
            factory.setExtractorsFactory(r1);
            this.mExoPlayer.prepare(factory.createMediaSource(Uri.parse(this.mVastVideoConfig.getNetworkMediaFileUrl())));
            this.mNativeVideoProgressRunnable.startRepeating(50);
        }
        setExoAudio();
        setExoPlayWhenReady();
    }

    private void setExoPlayWhenReady() {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.setPlayWhenReady(this.mPlayWhenReady);
        }
    }

    private void setExoAudio() {
        setExoAudio(this.mAudioEnabled ? 1.0f : BitmapDescriptorFactory.HUE_RED);
    }

    private void setExoAudio(float f) {
        ExoPlayer exoPlayer = this.mExoPlayer;
        MediaCodecAudioRenderer mediaCodecAudioRenderer = this.mAudioRenderer;
        if (exoPlayer != null && mediaCodecAudioRenderer != null) {
            PlayerMessage createMessage = exoPlayer.createMessage(mediaCodecAudioRenderer);
            if (createMessage == null) {
                MoPubLog.d("ExoPlayer.createMessage returned null.");
            } else {
                createMessage.setType(2).setPayload(Float.valueOf(f)).send();
            }
        }
    }

    private void setExoSurface(@Nullable Surface surface) {
        ExoPlayer exoPlayer = this.mExoPlayer;
        MediaCodecVideoRenderer mediaCodecVideoRenderer = this.mVideoRenderer;
        if (exoPlayer != null && mediaCodecVideoRenderer != null) {
            PlayerMessage createMessage = exoPlayer.createMessage(mediaCodecVideoRenderer);
            if (createMessage == null) {
                MoPubLog.d("ExoPlayer.createMessage returned null.");
            } else {
                createMessage.setType(1).setPayload(surface).send();
            }
        }
    }
}
