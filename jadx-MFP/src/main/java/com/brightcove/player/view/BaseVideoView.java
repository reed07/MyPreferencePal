package com.brightcove.player.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import com.brightcove.player.analytics.Analytics;
import com.brightcove.player.captioning.BrightcoveCaptionFormat;
import com.brightcove.player.controller.BrightcoveAudioTracksController;
import com.brightcove.player.controller.BrightcoveClosedCaptioningController;
import com.brightcove.player.controller.DefaultSourceSelectionController;
import com.brightcove.player.controller.FullScreenController;
import com.brightcove.player.controller.MediaControlsVisibilityManager;
import com.brightcove.player.controller.VideoPlaybackController;
import com.brightcove.player.display.VideoDisplayComponent;
import com.brightcove.player.display.VideoStillDisplayComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventEmitterImpl;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.event.RegisteringEventEmitter;
import com.brightcove.player.management.BrightcovePluginManager;
import com.brightcove.player.mediacontroller.BrightcoveMediaController;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.Fields;
import com.brightcove.player.pictureinpicture.PictureInPictureManager;
import com.brightcove.player.util.EventUtil;
import com.brightcove.player.util.VideoUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ListensFor(events = {"activityCreated", "activitySaveInstanceState", "activityStarted", "adProgress", "bufferedUpdate", "captionsLanguages", "completed", "didLoadClosedCaptions", "didPause", "didPlay", "didSeekTo", "didSetSource", "didSetVideo", "didStop", "hideSeekControls", "progress", "seekTo", "showSeekControls", "sourceNotPlayable", "videoDurationChanged", "willChangeVideo", "willInterruptContent", "willResumeContent"})
@Emits(events = {"didChangeList", "didSelectSource", "error", "pause", "play", "prebufferNextVideo", "readyToPlay", "seekTo", "selectSource", "setSource", "setVideo", "stop", "willChangeVideo", "on360FrameAvailable", "projectionFormatChanged"})
public abstract class BaseVideoView extends FrameLayout implements MediaPlayerControl, Component {
    /* access modifiers changed from: private */
    public static final String TAG = "BaseVideoView";
    protected BrightcoveAudioTracksController brightcoveAudioTracksController;
    protected BrightcoveClosedCaptioningController brightcoveClosedCaptioningController;
    /* access modifiers changed from: private */
    public BrightcoveClosedCaptioningView brightcoveClosedCaptioningView;
    /* access modifiers changed from: private */
    public int bufferedPercent;
    /* access modifiers changed from: private */
    public int currentIndex;
    /* access modifiers changed from: private */
    public boolean currentlyPlaying;
    protected int duration;
    protected EventEmitter eventEmitter;
    private FullScreenController fullScreenController;
    /* access modifiers changed from: private */
    public boolean hasPendingPlay;
    /* access modifiers changed from: private */
    public boolean hasSetSource;
    protected ImageView imageView;
    protected Map<String, Integer> listenerTokens;
    /* access modifiers changed from: private */
    public MediaControlsVisibilityManager mediaControlsVisibilityManager;
    /* access modifiers changed from: private */
    public MediaControlsWrapper mediaControlsWrapper;
    /* access modifiers changed from: private */
    public OnCompletionListener onCompletionListener;
    private OnInfoListener onInfoListener;
    protected OnPreparedListener onPreparedListener;
    private List<OnVideoViewSizeChangedListener> onVideoViewSizeChangedListeners;
    protected VideoPlaybackController playbackController;
    protected int playheadPosition;
    protected BrightcovePluginManager pluginManager;
    protected DefaultSourceSelectionController sourceController;
    protected VideoDisplayComponent videoDisplay;
    protected VideoStillDisplayComponent videoStillDisplay;
    /* access modifiers changed from: private */
    public Map<Video, Source> videoToSourceMap;
    /* access modifiers changed from: private */
    public ArrayList<Video> videos;

    private class BrightcoveMediaControlsWrapper implements MediaControlsWrapper {
        private BrightcoveMediaController mediaController;

        public void attachMediaController() {
        }

        public MediaController getMediaController() {
            return null;
        }

        public void updatePausePlayState() {
        }

        public BrightcoveMediaControlsWrapper(BrightcoveMediaController brightcoveMediaController) {
            this.mediaController = brightcoveMediaController;
        }

        public BrightcoveMediaController getBrightcoveMediaController() {
            return this.mediaController;
        }

        public void hideMediaControls() {
            this.mediaController.hide();
        }

        public boolean isShowing() {
            return this.mediaController.isShowing();
        }

        public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
            throw new UnsupportedOperationException("The legacy media controls wrapper does not support the Brightcove media controller.");
        }

        public void showMediaControls() {
            this.mediaController.show();
        }

        public void toggleMediaControlsVisibility() {
            if (isShowing()) {
                hideMediaControls();
            } else {
                showMediaControls();
            }
        }
    }

    private class LegacyMediaControlsWrapper implements MediaControlsWrapper {
        private MediaController mediaController;
        private boolean showing;

        public BrightcoveMediaController getBrightcoveMediaController() {
            return null;
        }

        public LegacyMediaControlsWrapper(MediaController mediaController2) {
            this.mediaController = mediaController2;
            attachMediaController();
        }

        public MediaController getMediaController() {
            return this.mediaController;
        }

        public void hideMediaControls() {
            if (BaseVideoView.this.canShowMediaControls()) {
                MediaController mediaController2 = this.mediaController;
                if (mediaController2 != null) {
                    mediaController2.hide();
                    this.showing = false;
                }
            }
        }

        public boolean isShowing() {
            return this.showing;
        }

        public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
            MediaController mediaController2 = this.mediaController;
            if (mediaController2 != null) {
                mediaController2.setMediaPlayer(mediaPlayerControl);
            }
        }

        public void showMediaControls() {
            if (BaseVideoView.this.canShowMediaControls()) {
                MediaController mediaController2 = this.mediaController;
                if (mediaController2 != null) {
                    mediaController2.show();
                    this.showing = true;
                }
            }
        }

        public void toggleMediaControlsVisibility() {
            if (this.mediaController != null && BaseVideoView.this.canShowMediaControls()) {
                if (this.mediaController.isShowing()) {
                    this.mediaController.hide();
                } else {
                    this.mediaController.show();
                }
            }
        }

        public void updatePausePlayState() {
            MediaController mediaController2 = this.mediaController;
            if (mediaController2 != null) {
                mediaController2.setMediaPlayer(BaseVideoView.this);
            }
        }

        public void attachMediaController() {
            Log.v(BaseVideoView.TAG, "attachMediaController...");
            MediaController mediaController2 = this.mediaController;
            if (mediaController2 != null) {
                mediaController2.setMediaPlayer(BaseVideoView.this);
                this.mediaController.setAnchorView(BaseVideoView.this.getParent() instanceof View ? (View) BaseVideoView.this.getParent() : BaseVideoView.this);
                if (BaseVideoView.this.hasPlayer()) {
                    this.mediaController.setEnabled(true);
                    BaseVideoView.this.mediaControlsVisibilityManager.setVisibilityState();
                    showMediaControls();
                    if (BaseVideoView.this.playheadPosition > 0) {
                        Log.v(BaseVideoView.TAG, "MediaController is quietly jumping to where we left off.");
                    }
                }
            }
        }
    }

    private interface MediaControlsWrapper {
        void attachMediaController();

        BrightcoveMediaController getBrightcoveMediaController();

        MediaController getMediaController();

        void hideMediaControls();

        boolean isShowing();

        void setMediaPlayer(MediaPlayerControl mediaPlayerControl);

        void showMediaControls();

        void toggleMediaControlsVisibility();

        void updatePausePlayState();
    }

    private class NoMediaControlsWrapper implements MediaControlsWrapper {
        public void attachMediaController() {
        }

        public BrightcoveMediaController getBrightcoveMediaController() {
            return null;
        }

        public MediaController getMediaController() {
            return null;
        }

        public void hideMediaControls() {
        }

        public boolean isShowing() {
            return false;
        }

        public void showMediaControls() {
        }

        public void toggleMediaControlsVisibility() {
        }

        public void updatePausePlayState() {
        }

        public NoMediaControlsWrapper() {
        }

        public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
            throw new UnsupportedOperationException("The no media controls wrapper does not support the Brightcove media controller.");
        }
    }

    protected class OnProgressListener implements EventListener {
        protected OnProgressListener() {
        }

        @Default
        public void processEvent(Event event) {
            if (BaseVideoView.this.isPlaying()) {
                int integerProperty = event.getIntegerProperty("duration");
                if (integerProperty > -1 && integerProperty != BaseVideoView.this.duration) {
                    Log.v(BaseVideoView.TAG, String.format(Locale.getDefault(), "Changing duration to %d.", new Object[]{Integer.valueOf(integerProperty)}));
                    BaseVideoView baseVideoView = BaseVideoView.this;
                    baseVideoView.duration = integerProperty;
                    if (baseVideoView.mediaControlsWrapper instanceof LegacyMediaControlsWrapper) {
                        BaseVideoView.this.showMediaController();
                    }
                }
                int integerProperty2 = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
                if (integerProperty2 > -1 && integerProperty2 != BaseVideoView.this.playheadPosition) {
                    BaseVideoView.this.playheadPosition = integerProperty2;
                }
            }
        }
    }

    public interface OnVideoViewSizeChangedListener {
        void onVideoViewSizeChange(int i, int i2, int i3, int i4);
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract boolean canShowMediaControls();

    /* access modifiers changed from: protected */
    public abstract VideoDisplayComponent createVideoDisplayComponent(EventEmitter eventEmitter2);

    public int getAudioSessionId() {
        return 0;
    }

    @Nullable
    public abstract RenderView getRenderView();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public boolean isHlsRecommended() {
        return false;
    }

    public void seekToLive() {
    }

    public BaseVideoView(Context context) {
        super(context);
        this.videos = new ArrayList<>();
        this.videoToSourceMap = new HashMap();
        this.currentIndex = -1;
        this.listenerTokens = new HashMap();
        init(context);
    }

    public BaseVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.videos = new ArrayList<>();
        this.videoToSourceMap = new HashMap();
        this.currentIndex = -1;
        this.listenerTokens = new HashMap();
        init(context);
    }

    public void addOnVideoViewSizeChangedListener(OnVideoViewSizeChangedListener onVideoViewSizeChangedListener) {
        if (this.onVideoViewSizeChangedListeners == null) {
            this.onVideoViewSizeChangedListeners = new ArrayList();
        }
        this.onVideoViewSizeChangedListeners.add(onVideoViewSizeChangedListener);
    }

    public void removeOnVideoViewSizeChangedListener(OnVideoViewSizeChangedListener onVideoViewSizeChangedListener) {
        List<OnVideoViewSizeChangedListener> list = this.onVideoViewSizeChangedListeners;
        if (list != null) {
            list.remove(onVideoViewSizeChangedListener);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        List<OnVideoViewSizeChangedListener> list = this.onVideoViewSizeChangedListeners;
        if (list != null) {
            for (OnVideoViewSizeChangedListener onVideoViewSizeChange : list) {
                onVideoViewSizeChange.onVideoViewSizeChange(i, i2, i3, i4);
            }
        }
    }

    public void requestLayout() {
        super.requestLayout();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.i(TAG, "onTouchEvent");
        if (!getPictureInPictureManager().isInPictureInPictureMode()) {
            toggleMediaControlsVisibility();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        Log.i(TAG, "init");
        resetMetaData();
        this.playheadPosition = -1;
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.imageView = new ImageView(context);
        addView(this.imageView);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setLayoutParams: ");
        sb.append(layoutParams);
        Log.v(str, sb.toString());
        super.setLayoutParams(layoutParams);
        if (layoutParams != null) {
            setChildLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: protected */
    public void setChildLayoutParams(LayoutParams layoutParams) {
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height);
        layoutParams2.gravity = 17;
        this.imageView.setLayoutParams(layoutParams2);
        BrightcoveClosedCaptioningView brightcoveClosedCaptioningView2 = this.brightcoveClosedCaptioningView;
        if (brightcoveClosedCaptioningView2 != null) {
            brightcoveClosedCaptioningView2.setLayoutParams(layoutParams2);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        finishInitialization();
    }

    public void finishInitialization() {
        if (this.eventEmitter == null) {
            setEventEmitter(new EventEmitterImpl());
        }
        this.mediaControlsVisibilityManager = new MediaControlsVisibilityManager(this);
        this.mediaControlsVisibilityManager.initListeners(this.eventEmitter);
        this.fullScreenController = new FullScreenController(this);
    }

    /* access modifiers changed from: protected */
    public void resetMetaData() {
        this.bufferedPercent = 0;
        this.playheadPosition = 0;
        this.duration = -1;
        setCurrentlyPlaying(false);
    }

    public MediaController getMediaController() {
        MediaControlsWrapper mediaControlsWrapper2 = this.mediaControlsWrapper;
        if (mediaControlsWrapper2 != null) {
            return mediaControlsWrapper2.getMediaController();
        }
        return null;
    }

    public BrightcoveMediaController getBrightcoveMediaController() {
        MediaControlsWrapper mediaControlsWrapper2 = this.mediaControlsWrapper;
        if (mediaControlsWrapper2 != null) {
            return mediaControlsWrapper2.getBrightcoveMediaController();
        }
        return null;
    }

    @NonNull
    public PictureInPictureManager getPictureInPictureManager() {
        return PictureInPictureManager.getInstance();
    }

    /* access modifiers changed from: protected */
    public DefaultSourceSelectionController createSourceSelectionController(EventEmitter eventEmitter2) {
        return new DefaultSourceSelectionController(eventEmitter2);
    }

    public void setEventEmitter(EventEmitter eventEmitter2) {
        this.eventEmitter = RegisteringEventEmitter.build(eventEmitter2, getClass());
        this.playbackController = new VideoPlaybackController(eventEmitter2);
        this.sourceController = createSourceSelectionController(eventEmitter2);
        this.videoStillDisplay = new VideoStillDisplayComponent(this.imageView, eventEmitter2);
        this.pluginManager = new BrightcovePluginManager(eventEmitter2);
        this.brightcoveClosedCaptioningController = new BrightcoveClosedCaptioningController(this, getContext());
        this.videoDisplay = createVideoDisplayComponent(eventEmitter2);
        this.brightcoveAudioTracksController = new BrightcoveAudioTracksController(this, getContext());
        initListeners();
    }

    public EventEmitter getEventEmitter() {
        EventEmitter eventEmitter2 = this.eventEmitter;
        if (eventEmitter2 == null) {
            return null;
        }
        return ((RegisteringEventEmitter) eventEmitter2).getRootEmitter();
    }

    public int getMeasuredVideoWidth() {
        return getRenderView().getMeasuredVideoWidth();
    }

    public int getMeasuredVideoHeight() {
        return getRenderView().getMeasuredVideoHeight();
    }

    public int getDuration() {
        return this.duration;
    }

    public int getCurrentPosition() {
        return this.playheadPosition;
    }

    public int getBufferPercentage() {
        return this.bufferedPercent;
    }

    public boolean isFullScreen() {
        return this.fullScreenController.isFullScreen();
    }

    /* access modifiers changed from: protected */
    public void initListeners() {
        addListener(EventType.SOURCE_NOT_PLAYABLE, new EventListener() {
            @Default
            public void processEvent(Event event) {
                BaseVideoView.this.eventEmitter.emit(EventType.STOP);
            }
        });
        addListener(EventType.VIDEO_DURATION_CHANGED, new EventListener() {
            @Default
            public void processEvent(Event event) {
                int integerProperty = event.getIntegerProperty("duration");
                if (integerProperty > 0) {
                    Log.v(BaseVideoView.TAG, String.format(Locale.getDefault(), "videoDurationChanged: changing duration to %d.", new Object[]{Integer.valueOf(integerProperty)}));
                    BaseVideoView baseVideoView = BaseVideoView.this;
                    baseVideoView.duration = integerProperty;
                    baseVideoView.showMediaController();
                }
            }
        });
        OnProgressListener onProgressListener = new OnProgressListener();
        addListener("progress", onProgressListener);
        addListener(EventType.AD_PROGRESS, onProgressListener);
        addListener(EventType.DID_SET_SOURCE, new EventListener() {
            @Default
            public void processEvent(Event event) {
                BaseVideoView.this.onPrepared();
                Video video = (Video) event.properties.get("video");
                if (video != null && !video.equals(BaseVideoView.this.getCurrentVideo()) && BaseVideoView.this.videos.indexOf(video) >= 0) {
                    
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002b: INVOKE  (wrap: com.brightcove.player.view.BaseVideoView
                          0x0029: IGET  (r3v10 com.brightcove.player.view.BaseVideoView) = (r2v0 'this' com.brightcove.player.view.BaseVideoView$3 A[THIS]) com.brightcove.player.view.BaseVideoView.3.this$0 com.brightcove.player.view.BaseVideoView) com.brightcove.player.view.BaseVideoView.access$408(com.brightcove.player.view.BaseVideoView):int type: STATIC in method: com.brightcove.player.view.BaseVideoView.3.processEvent(com.brightcove.player.event.Event):void, dex: classes.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
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
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                        	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                        	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                        Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                        	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                        	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                        	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                        	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                        	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                        	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                        	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                        	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                        	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                        	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                        	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                        	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                        	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                        	... 43 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 61 more
                        */
                    /*
                        this = this;
                        com.brightcove.player.view.BaseVideoView r0 = com.brightcove.player.view.BaseVideoView.this
                        r0.onPrepared()
                        java.util.Map<java.lang.String, java.lang.Object> r3 = r3.properties
                        java.lang.String r0 = "video"
                        java.lang.Object r3 = r3.get(r0)
                        com.brightcove.player.model.Video r3 = (com.brightcove.player.model.Video) r3
                        if (r3 == 0) goto L_0x002e
                        com.brightcove.player.view.BaseVideoView r0 = com.brightcove.player.view.BaseVideoView.this
                        com.brightcove.player.model.Video r0 = r0.getCurrentVideo()
                        boolean r0 = r3.equals(r0)
                        if (r0 != 0) goto L_0x002e
                        com.brightcove.player.view.BaseVideoView r0 = com.brightcove.player.view.BaseVideoView.this
                        java.util.ArrayList r0 = r0.videos
                        int r3 = r0.indexOf(r3)
                        if (r3 < 0) goto L_0x002e
                        com.brightcove.player.view.BaseVideoView r3 = com.brightcove.player.view.BaseVideoView.this
                        
                        // error: 0x002b: INVOKE  (r3 I:com.brightcove.player.view.BaseVideoView) com.brightcove.player.view.BaseVideoView.access$408(com.brightcove.player.view.BaseVideoView):int type: STATIC
                    L_0x002e:
                        com.brightcove.player.view.BaseVideoView r3 = com.brightcove.player.view.BaseVideoView.this
                        r0 = 1
                        r3.hasSetSource = r0
                        com.brightcove.player.view.BaseVideoView r3 = com.brightcove.player.view.BaseVideoView.this
                        boolean r3 = r3.hasPendingPlay
                        if (r3 == 0) goto L_0x005b
                        com.brightcove.player.view.BaseVideoView r3 = com.brightcove.player.view.BaseVideoView.this
                        r0 = 0
                        r3.hasPendingPlay = r0
                        java.util.HashMap r3 = new java.util.HashMap
                        r3.<init>()
                        java.lang.String r0 = "video"
                        com.brightcove.player.view.BaseVideoView r1 = com.brightcove.player.view.BaseVideoView.this
                        com.brightcove.player.model.Video r1 = r1.getCurrentVideo()
                        r3.put(r0, r1)
                        com.brightcove.player.view.BaseVideoView r0 = com.brightcove.player.view.BaseVideoView.this
                        com.brightcove.player.event.EventEmitter r0 = r0.eventEmitter
                        java.lang.String r1 = "play"
                        r0.emit(r1, r3)
                    L_0x005b:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.view.BaseVideoView.AnonymousClass3.processEvent(com.brightcove.player.event.Event):void");
                }
            });
            addListener(EventType.DID_SET_VIDEO, new EventListener() {
                @Default
                public void processEvent(Event event) {
                    Video video = (Video) event.properties.get("video");
                    if (BaseVideoView.this.brightcoveClosedCaptioningView != null) {
                        BaseVideoView.this.brightcoveClosedCaptioningView.clear();
                    }
                    BaseVideoView.this.setupClosedCaptioningRendering(video);
                }
            });
            addListener("completed", new EventListener() {
                public void processEvent(Event event) {
                    if (BaseVideoView.this.onCompletionListener != null) {
                        BaseVideoView.this.onCompletionListener.onCompletion(null);
                    }
                    BaseVideoView baseVideoView = BaseVideoView.this;
                    baseVideoView.playheadPosition = 0;
                    baseVideoView.setCurrentlyPlaying(false);
                }
            });
            addListener(EventType.BUFFERED_UPDATE, new EventListener() {
                public void processEvent(Event event) {
                    BaseVideoView.this.bufferedPercent = event.getIntegerProperty(AbstractEvent.PERCENT_COMPLETE);
                }
            });
            addListener(EventType.SEEK_TO, new EventListener() {
                public void processEvent(Event event) {
                    int integerProperty = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
                    if (integerProperty > -1) {
                        BaseVideoView.this.playheadPosition = integerProperty;
                    }
                }
            });
            addListener(EventType.DID_SEEK_TO, new EventListener() {
                public void processEvent(Event event) {
                    int integerProperty = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
                    if (integerProperty > -1) {
                        BaseVideoView.this.playheadPosition = integerProperty;
                    }
                }
            });
            addListener(EventType.DID_PLAY, new EventListener() {
                public void processEvent(Event event) {
                    BaseVideoView.this.setCurrentlyPlaying(true);
                    BaseVideoView.this.prebufferNextVideo();
                }
            });
            addListener(EventType.DID_STOP, new EventListener() {
                public void processEvent(Event event) {
                    BaseVideoView.this.resetMetaData();
                }
            });
            addListener(EventType.DID_PAUSE, new EventListener() {
                public void processEvent(Event event) {
                    BaseVideoView.this.setCurrentlyPlaying(false);
                }
            });
            addListener(EventType.WILL_INTERRUPT_CONTENT, new EventListener() {
                public void processEvent(Event event) {
                    BaseVideoView.this.currentlyPlaying = false;
                    if (BaseVideoView.this.mediaControlsWrapper != null) {
                        BaseVideoView.this.mediaControlsWrapper.hideMediaControls();
                        BaseVideoView.this.eventEmitter.once("progress", new EventListener() {
                            public void processEvent(Event event) {
                                BaseVideoView.this.showMediaController();
                            }
                        });
                    }
                }
            });
            addListener(EventType.WILL_RESUME_CONTENT, new EventListener() {
                public void processEvent(Event event) {
                    BaseVideoView.this.currentlyPlaying = true;
                }
            });
            addListener(EventType.DID_LOAD_CLOSED_CAPTIONS, new EventListener() {
                @Default
                public void processEvent(Event event) {
                    if (BaseVideoView.this.brightcoveClosedCaptioningView == null) {
                        return;
                    }
                    if (event.properties.containsKey(AbstractEvent.TTML_DOCUMENT) || event.properties.containsKey(AbstractEvent.WEBVTT_DOCUMENT)) {
                        if (BaseVideoView.this.brightcoveClosedCaptioningView.getParent() == null) {
                            BaseVideoView baseVideoView = BaseVideoView.this;
                            baseVideoView.addView(baseVideoView.brightcoveClosedCaptioningView);
                        }
                    } else if (BaseVideoView.this.brightcoveClosedCaptioningView.getParent() != null) {
                        BaseVideoView baseVideoView2 = BaseVideoView.this;
                        baseVideoView2.removeView(baseVideoView2.brightcoveClosedCaptioningView);
                    }
                }
            });
            addListener(EventType.CAPTIONS_LANGUAGES, new EventListener() {
                @Default
                public void processEvent(Event event) {
                    List list = (List) event.properties.get(AbstractEvent.LANGUAGES);
                    if (list == null || list.isEmpty()) {
                        BaseVideoView.this.disableClosedCaptioningRendering();
                    } else {
                        BaseVideoView.this.setupClosedCaptioningRendering();
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public void onPrepared() {
            OnPreparedListener onPreparedListener2 = this.onPreparedListener;
            if (onPreparedListener2 != null) {
                onPreparedListener2.onPrepared(this.videoDisplay.getMediaPlayer());
            }
            if (this.onInfoListener != null) {
                this.videoDisplay.getMediaPlayer().setOnInfoListener(this.onInfoListener);
            }
        }

        public void setOnCompletionListener(OnCompletionListener onCompletionListener2) {
            this.onCompletionListener = onCompletionListener2;
        }

        public void clearOnCompletionListener() {
            this.onCompletionListener = null;
        }

        public void setOnInfoListener(OnInfoListener onInfoListener2) {
            this.onInfoListener = onInfoListener2;
        }

        public void setOnPreparedListener(OnPreparedListener onPreparedListener2) {
            this.onPreparedListener = onPreparedListener2;
        }

        public void clearOnPreparedListener() {
            this.onPreparedListener = null;
        }

        public void setMediaController(BrightcoveMediaController brightcoveMediaController) {
            this.mediaControlsWrapper = brightcoveMediaController != null ? new BrightcoveMediaControlsWrapper(brightcoveMediaController) : new NoMediaControlsWrapper();
        }

        public void setMediaController(MediaController mediaController) {
            this.mediaControlsWrapper = mediaController != null ? new LegacyMediaControlsWrapper(mediaController) : new NoMediaControlsWrapper();
        }

        public void toggleMediaControlsVisibility() {
            MediaControlsWrapper mediaControlsWrapper2 = this.mediaControlsWrapper;
            if (mediaControlsWrapper2 == null) {
                showMediaController();
            } else {
                mediaControlsWrapper2.toggleMediaControlsVisibility();
            }
        }

        /* access modifiers changed from: protected */
        public boolean hasPlayer() {
            return this.videoDisplay.getMediaPlayer() != null;
        }

        /* access modifiers changed from: private */
        public void showMediaController() {
            if (this.mediaControlsWrapper == null) {
                this.mediaControlsWrapper = new BrightcoveMediaControlsWrapper(new BrightcoveMediaController(this));
            }
            this.mediaControlsWrapper.showMediaControls();
        }

        public void onControllerHide() {
            Log.i(TAG, "onControllerHide");
        }

        public boolean isPlaying() {
            return this.currentlyPlaying;
        }

        /* access modifiers changed from: private */
        public void setCurrentlyPlaying(boolean z) {
            this.currentlyPlaying = z;
            MediaControlsWrapper mediaControlsWrapper2 = this.mediaControlsWrapper;
            if (mediaControlsWrapper2 != null) {
                mediaControlsWrapper2.updatePausePlayState();
            }
            setKeepScreenOn(this.currentlyPlaying);
        }

        public void start() {
            if (this.hasSetSource) {
                this.hasPendingPlay = false;
                HashMap hashMap = new HashMap();
                hashMap.put("video", getCurrentVideo());
                this.eventEmitter.emit(EventType.PLAY, hashMap);
            } else if (!this.videos.isEmpty()) {
                this.hasPendingPlay = true;
            } else {
                Log.e(TAG, "No video to play.");
            }
        }

        public void pause() {
            HashMap hashMap = new HashMap();
            hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(this.playheadPosition));
            hashMap.put("video", getCurrentVideo());
            this.eventEmitter.emit(EventType.PAUSE, hashMap);
        }

        public void stopPlayback() {
            HashMap hashMap = new HashMap();
            hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(this.playheadPosition));
            hashMap.put("video", getCurrentVideo());
            this.eventEmitter.emit(EventType.STOP, hashMap);
        }

        public void seekTo(int i) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Seeking to ");
            sb.append(i);
            Log.d(str, sb.toString());
            HashMap hashMap = new HashMap();
            hashMap.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(i));
            hashMap.put("video", getCurrentVideo());
            this.playheadPosition = i;
            this.eventEmitter.emit(EventType.SEEK_TO, hashMap);
        }

        public Video setVideoPath(String str) {
            if (hasNextVideo()) {
                this.eventEmitter.emit(EventType.PREBUFFER_NEXT_VIDEO);
            }
            this.videos.clear();
            this.videoToSourceMap.clear();
            Video createVideo = Video.createVideo(str);
            add(createVideo);
            return createVideo;
        }

        public Video setVideoURI(Uri uri) {
            return setVideoPath(uri.toString());
        }

        public Video setVideoPath(String str, Map<String, String> map) {
            if (hasNextVideo()) {
                this.eventEmitter.emit(EventType.PREBUFFER_NEXT_VIDEO);
            }
            this.videos.clear();
            this.videoToSourceMap.clear();
            Video createVideo = Video.createVideo(str);
            VideoUtil.addCaptions(createVideo, map);
            add(createVideo);
            return createVideo;
        }

        public void add(Video video) {
            add(this.videos.size(), video);
        }

        public void add(int i, Video video) throws IndexOutOfBoundsException {
            this.videos.add(i, video);
            emitDidChangeList();
            updateIndex(i, 1);
        }

        private void updateIndex(int i, int i2) {
            int i3 = this.currentIndex;
            if (i3 == -1 || i3 == i) {
                setCurrentIndexPrivate(i);
            } else if (i < i3) {
                this.currentIndex = i3 + i2;
            } else if (i3 >= 0 && i3 + 1 == i && this.currentlyPlaying) {
                prebufferNextVideo();
            }
        }

        public void addAll(Collection<Video> collection) {
            addAll(this.videos.size(), collection);
        }

        public void addAll(int i, Collection<Video> collection) throws IndexOutOfBoundsException {
            this.videos.addAll(i, collection);
            emitDidChangeList();
            updateIndex(i, collection.size());
        }

        public Video get(int i) {
            return (Video) this.videos.get(i);
        }

        public int getCurrentIndex() {
            return this.currentIndex;
        }

        public Video getCurrentVideo() {
            int i = this.currentIndex;
            if (i < 0 || i >= this.videos.size()) {
                return null;
            }
            return (Video) this.videos.get(this.currentIndex);
        }

        public void setCurrentIndex(int i) throws IndexOutOfBoundsException {
            if (i != this.currentIndex) {
                if (i < 0 || i >= this.videos.size()) {
                    throw new IndexOutOfBoundsException();
                }
                setCurrentIndexPrivate(i);
            }
        }

        private void setCurrentIndexPrivate(final int i) {
            if (i == -1) {
                this.hasSetSource = false;
                if (this.currentIndex == -1) {
                    return;
                }
            }
            this.hasSetSource = false;
            final int nextId = Event.getNextId();
            this.eventEmitter.once(EventType.WILL_CHANGE_VIDEO, new EventListener() {
                @Default
                public void processEvent(Event event) {
                    if (event.getId() == nextId) {
                        BaseVideoView.this.resetMetaData();
                        BaseVideoView.this.currentIndex = i;
                        Video video = (Video) event.properties.get(AbstractEvent.NEXT_VIDEO);
                        if (video != null) {
                            if (BaseVideoView.this.mediaControlsWrapper == null) {
                                BaseVideoView baseVideoView = BaseVideoView.this;
                                baseVideoView.mediaControlsWrapper = new BrightcoveMediaControlsWrapper(new BrightcoveMediaController(baseVideoView));
                            }
                            EventUtil.emit(BaseVideoView.this.eventEmitter, EventType.SET_VIDEO, video);
                            return;
                        }
                        BaseVideoView.this.eventEmitter.emit(EventType.SET_SOURCE);
                    } else if (event.getId() < nextId) {
                        BaseVideoView.this.eventEmitter.once(EventType.WILL_CHANGE_VIDEO, this);
                    }
                }
            });
            HashMap hashMap = new HashMap();
            hashMap.put("index", Integer.valueOf(this.currentIndex));
            hashMap.put(AbstractEvent.CURRENT_VIDEO, getCurrentVideo());
            Video video = null;
            if (i >= 0 && i < this.videos.size()) {
                video = (Video) this.videos.get(i);
            }
            hashMap.put(AbstractEvent.NEXT_VIDEO, video);
            hashMap.put("id", Integer.valueOf(nextId));
            this.eventEmitter.emit(EventType.WILL_CHANGE_VIDEO, hashMap);
        }

        public List<Video> getList() {
            return Collections.unmodifiableList(this.videos);
        }

        public void remove(int i) throws IndexOutOfBoundsException {
            this.videoToSourceMap.remove((Video) this.videos.remove(i));
            int i2 = this.currentIndex;
            if (i2 > i) {
                this.currentIndex = i2 - 1;
            } else if (this.videos.isEmpty()) {
                setCurrentIndexPrivate(-1);
            } else {
                int i3 = this.currentIndex;
                if (i3 == i) {
                    if (i == this.videos.size()) {
                        setCurrentIndex(i - 1);
                    } else if (i < this.videos.size()) {
                        setCurrentIndexPrivate(i);
                    }
                } else if (i3 + 1 == i && this.currentlyPlaying) {
                    prebufferNextVideo();
                }
            }
            emitDidChangeList();
        }

        public void clear() {
            boolean z = this.videos.size() > 0;
            this.videos.clear();
            this.videoToSourceMap.clear();
            setCurrentIndexPrivate(-1);
            if (z) {
                emitDidChangeList();
            }
        }

        public VideoPlaybackController getPlaybackController() {
            return this.playbackController;
        }

        public DefaultSourceSelectionController getSourceController() {
            return this.sourceController;
        }

        public VideoDisplayComponent getVideoDisplay() {
            return this.videoDisplay;
        }

        public VideoStillDisplayComponent getVideoStillDisplay() {
            return this.videoStillDisplay;
        }

        public Analytics getAnalytics() {
            VideoDisplayComponent videoDisplayComponent = this.videoDisplay;
            if (videoDisplayComponent != null) {
                return videoDisplayComponent.getAnalytics();
            }
            return null;
        }

        private void emitDidChangeList() {
            HashMap hashMap = new HashMap();
            hashMap.put(AbstractEvent.LIST, this.videos);
            this.eventEmitter.emit(EventType.DID_CHANGE_LIST, hashMap);
        }

        /* access modifiers changed from: private */
        public void prebufferNextVideo() {
            if (hasNextVideo()) {
                final Video video = (Video) this.videos.get(this.currentIndex + 1);
                Source source = (Source) this.videoToSourceMap.get(video);
                if (source != null) {
                    EventUtil.emit(this.eventEmitter, EventType.PREBUFFER_NEXT_VIDEO, video, source);
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("video", video);
                this.eventEmitter.request(EventType.SELECT_SOURCE, hashMap, new EventListener() {
                    public void processEvent(Event event) {
                        Source source = (Source) event.properties.get("source");
                        BaseVideoView.this.videoToSourceMap.put(video, source);
                        EventUtil.emit(BaseVideoView.this.eventEmitter, EventType.DID_SELECT_SOURCE, video, source);
                        EventUtil.emit(BaseVideoView.this.eventEmitter, EventType.PREBUFFER_NEXT_VIDEO, video, source);
                    }
                });
                return;
            }
            this.eventEmitter.emit(EventType.PREBUFFER_NEXT_VIDEO);
        }

        private boolean hasNextVideo() {
            return this.videos.size() > 1 && this.currentIndex + 1 < this.videos.size();
        }

        @TargetApi(16)
        public void addSubtitleSource(Uri uri, BrightcoveCaptionFormat brightcoveCaptionFormat) {
            Video currentVideo = getCurrentVideo();
            final Pair create = Pair.create(uri, brightcoveCaptionFormat);
            if (currentVideo != null) {
                addSubtitlePair(currentVideo, create);
            } else {
                this.eventEmitter.once(EventType.WILL_CHANGE_VIDEO, new EventListener() {
                    public void processEvent(Event event) {
                        Video video = (Video) event.properties.get(AbstractEvent.NEXT_VIDEO);
                        if (video != null) {
                            BaseVideoView.this.addSubtitlePair(video, create);
                        }
                    }
                });
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0065  */
        /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
        @android.annotation.TargetApi(16)
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setSubtitleLocale(java.lang.String r5) {
            /*
                r4 = this;
                com.brightcove.player.model.Video r0 = r4.getCurrentVideo()
                java.util.Map r0 = r0.getProperties()
                java.lang.String r1 = "captionSources"
                java.lang.Object r0 = r0.get(r1)
                java.util.List r0 = (java.util.List) r0
                if (r0 == 0) goto L_0x0062
                java.util.Iterator r0 = r0.iterator()
            L_0x0016:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0062
                java.lang.Object r1 = r0.next()
                android.util.Pair r1 = (android.util.Pair) r1
                java.lang.Object r2 = r1.first
                android.net.Uri r2 = (android.net.Uri) r2
                android.net.Uri r3 = android.net.Uri.EMPTY
                boolean r2 = r2.equals(r3)
                if (r2 != 0) goto L_0x0016
                java.lang.Object r2 = r1.second
                com.brightcove.player.captioning.BrightcoveCaptionFormat r2 = (com.brightcove.player.captioning.BrightcoveCaptionFormat) r2
                java.lang.String r2 = r2.language()
                boolean r2 = r2.equals(r5)
                if (r2 == 0) goto L_0x0016
                com.brightcove.player.controller.BrightcoveClosedCaptioningController r0 = r4.brightcoveClosedCaptioningController
                r0.setLocaleCode(r5)
                r0 = 1
                com.brightcove.player.controller.BrightcoveClosedCaptioningController r2 = r4.brightcoveClosedCaptioningController
                com.brightcove.player.captioning.LoadCaptionsService r2 = r2.getLoadCaptionsService()
                if (r2 == 0) goto L_0x005a
                java.lang.Object r3 = r1.first
                android.net.Uri r3 = (android.net.Uri) r3
                java.lang.Object r1 = r1.second
                com.brightcove.player.captioning.BrightcoveCaptionFormat r1 = (com.brightcove.player.captioning.BrightcoveCaptionFormat) r1
                java.lang.String r1 = r1.type()
                r2.loadCaptions(r3, r1)
                goto L_0x0063
            L_0x005a:
                java.lang.String r1 = TAG
                java.lang.String r2 = "setSubtitleLocale: LoadCaptionsService is null"
                android.util.Log.e(r1, r2)
                goto L_0x0063
            L_0x0062:
                r0 = 0
            L_0x0063:
                if (r0 != 0) goto L_0x0080
                java.lang.String r0 = TAG
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "setSubtitleLocale: subtitle for locale,"
                r1.append(r2)
                r1.append(r5)
                java.lang.String r5 = ", not found."
                r1.append(r5)
                java.lang.String r5 = r1.toString()
                android.util.Log.e(r0, r5)
            L_0x0080:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.view.BaseVideoView.setSubtitleLocale(java.lang.String):void");
        }

        public BrightcoveClosedCaptioningView getClosedCaptioningView() {
            return this.brightcoveClosedCaptioningView;
        }

        public BrightcoveClosedCaptioningController getClosedCaptioningController() {
            return this.brightcoveClosedCaptioningController;
        }

        public BrightcoveAudioTracksController getAudioTracksController() {
            return this.brightcoveAudioTracksController;
        }

        public void setClosedCaptioningEnabled(boolean z) {
            this.brightcoveClosedCaptioningController.saveClosedCaptioningState(z);
        }

        /* access modifiers changed from: private */
        public void addSubtitlePair(Video video, Pair<Uri, BrightcoveCaptionFormat> pair) {
            List list = (List) video.getProperties().get(Fields.CAPTION_SOURCES);
            if (list == null) {
                list = new ArrayList();
                video.getProperties().put(Fields.CAPTION_SOURCES, list);
            }
            list.add(pair);
        }

        public void addListener(String str, EventListener eventListener) {
            this.listenerTokens.put(str, Integer.valueOf(this.eventEmitter.on(str, eventListener)));
        }

        public void removeListener(String str) {
            this.eventEmitter.off(str, ((Integer) this.listenerTokens.get(str)).intValue());
        }

        public void removeListeners() {
            for (String str : this.listenerTokens.keySet()) {
                this.eventEmitter.off(str, ((Integer) this.listenerTokens.get(str)).intValue());
            }
            this.listenerTokens.clear();
        }

        /* access modifiers changed from: protected */
        public void setupClosedCaptioningRendering(Video video) {
            Log.v(TAG, "setupClosedCaptioningRendering");
            BrightcoveClosedCaptioningController brightcoveClosedCaptioningController2 = this.brightcoveClosedCaptioningController;
            if (brightcoveClosedCaptioningController2 == null) {
                return;
            }
            if (brightcoveClosedCaptioningController2.checkIfCaptionsExist(video)) {
                setupClosedCaptioningRendering();
            } else {
                disableClosedCaptioningRendering();
            }
        }

        public void setupClosedCaptioningRendering() {
            Log.v(TAG, "Setting up the Closed Captioning View.");
            if (this.brightcoveClosedCaptioningView == null) {
                this.brightcoveClosedCaptioningView = new BrightcoveClosedCaptioningView(getContext());
                this.brightcoveClosedCaptioningView.initialize(this.eventEmitter, this);
                addView(this.brightcoveClosedCaptioningView);
            }
        }

        public void disableClosedCaptioningRendering() {
            Log.v(TAG, "Disabling Closed Captioning View.");
            BrightcoveClosedCaptioningView brightcoveClosedCaptioningView2 = this.brightcoveClosedCaptioningView;
            if (brightcoveClosedCaptioningView2 != null) {
                brightcoveClosedCaptioningView2.clear();
                removeView(this.brightcoveClosedCaptioningView);
                this.brightcoveClosedCaptioningView = null;
            }
        }

        /* access modifiers changed from: protected */
        public void emitErrorEvent(String str) {
            Log.e(TAG, str);
            HashMap hashMap = new HashMap();
            hashMap.put(AbstractEvent.ERROR_MESSAGE, str);
            this.eventEmitter.emit("error", hashMap);
        }

        public ImageView getStillView() {
            return this.imageView;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            BrightcoveMediaController brightcoveMediaController = getBrightcoveMediaController();
            boolean dispatchKeyEvent = brightcoveMediaController != null ? brightcoveMediaController.dispatchKeyEvent(keyEvent) : false;
            return dispatchKeyEvent ? dispatchKeyEvent : super.dispatchKeyEvent(keyEvent);
        }
    }
