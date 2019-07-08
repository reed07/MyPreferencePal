package com.myfitnesspal.feature.video.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.Observable;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.amazon.device.ads.AdRegistration;
import com.brightcove.ima.GoogleIMAComponent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveMediaController;
import com.brightcove.player.model.Video;
import com.brightcove.player.util.LifecycleUtil;
import com.brightcove.player.view.BrightcoveVideoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.login.widget.ToolTipPopup;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.blog.ui.activity.BlogActivity;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.video.adapter.VideoGalleryAdapter;
import com.myfitnesspal.feature.video.adapter.VideoGalleryAdapter.EventListener;
import com.myfitnesspal.feature.video.model.VideoGalleryAdapterItem;
import com.myfitnesspal.feature.video.util.VideoAnalyticsHelper;
import com.myfitnesspal.feature.video.util.VideoUtil;
import com.myfitnesspal.feature.video.viewmodel.VideoViewModel;
import com.myfitnesspal.feature.video.viewmodel.VideoViewModel.Property;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.util.ActivityUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Map;
import javax.inject.Inject;

public class VideoActivity extends MfpActivity {
    private static final String AUTO_PLAY_SOURCE = "bcsdk://com.myfitnesspal.android.autoplay";
    private static final String CLICK_TO_PLAY_SOURCE = "bcsdk://com.myfitnesspal.android.clicktoplay";
    private static final int CONTROLS_ANIMATION_DELAY_MS = 2000;
    private static final String EVENT_SCREEN_BROWSE_VIDEOS = "browse_videos";
    private static final String EXTRA_BLOG_MEDIUM = "app_gallery_video";
    private static final String EXTRA_BLOG_SOURCE = "mfp";
    public static final String EXTRA_FLOW_ID = "flow_id";
    private static final String EXTRA_PLAYLIST_ID = "extra_playlist_id";
    private static final String EXTRA_VIDEO_BLOG = "video/";
    private static final String EXTRA_VIDEO_ID = "extra_video_id";
    private static final String EXTRA_VIDEO_THUMBNAIL = "extra_video_thumbnail";
    private static final RequestOptions IMAGE_OPTIONS = new RequestOptions().fitCenter().centerCrop().placeholder((int) R.drawable.video_placeholder).error(R.drawable.video_placeholder);
    private static final double NANOS_PER_MILSECOND = 1000000.0d;
    private static final int NEXT_VIDEO_INDEX = 1;
    private static final int NEXT_VIDEO_TIME_INTERVAL = 1000;
    private static final int NEXT_VIDEO_TIME_TOTAL = 6000;
    @BindView(2131361855)
    View activeVideoErrorContainer;
    @BindView(2131361856)
    TextView activeVideoErrorMsg;
    @BindView(2131361863)
    ViewGroup adView;
    private final EventListener adapterListener = new EventListener() {
        public void onVideoClicked(@NonNull Video video, int i) {
            VideoActivity.this.viewModel.onVideoChanged(video, i);
            
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE  (wrap: com.myfitnesspal.feature.video.activity.VideoActivity
                  0x0009: IGET  (r0v2 com.myfitnesspal.feature.video.activity.VideoActivity) = (r7v0 'this' com.myfitnesspal.feature.video.activity.VideoActivity$2 A[THIS]) com.myfitnesspal.feature.video.activity.VideoActivity.2.this$0 com.myfitnesspal.feature.video.activity.VideoActivity) com.myfitnesspal.feature.video.activity.VideoActivity.access$208(com.myfitnesspal.feature.video.activity.VideoActivity):int type: STATIC in method: com.myfitnesspal.feature.video.activity.VideoActivity.2.onVideoClicked(com.brightcove.player.model.Video, int):void, dex: classes4.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
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
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:95)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:469)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.ClassGen.addInsnBody(ClassGen.java:435)
                	at jadx.core.codegen.ClassGen.addField(ClassGen.java:376)
                	at jadx.core.codegen.ClassGen.addFields(ClassGen.java:346)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:223)
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
                	... 31 more
                Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.Class.forName0(Native Method)
                	at java.base/java.lang.Class.forName(Unknown Source)
                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                	... 49 more
                */
            /*
                this = this;
                com.myfitnesspal.feature.video.activity.VideoActivity r0 = com.myfitnesspal.feature.video.activity.VideoActivity.this
                com.myfitnesspal.feature.video.viewmodel.VideoViewModel r0 = r0.viewModel
                r0.onVideoChanged(r8, r9)
                com.myfitnesspal.feature.video.activity.VideoActivity r0 = com.myfitnesspal.feature.video.activity.VideoActivity.this
                
                // error: 0x000b: INVOKE  (r0 I:com.myfitnesspal.feature.video.activity.VideoActivity) com.myfitnesspal.feature.video.activity.VideoActivity.access$208(com.myfitnesspal.feature.video.activity.VideoActivity):int type: STATIC
                com.myfitnesspal.feature.video.activity.VideoActivity r0 = com.myfitnesspal.feature.video.activity.VideoActivity.this
                dagger.Lazy<com.myfitnesspal.feature.video.util.VideoAnalyticsHelper> r0 = r0.videoAnalyticsHelper
                java.lang.Object r0 = r0.get()
                r1 = r0
                com.myfitnesspal.feature.video.util.VideoAnalyticsHelper r1 = (com.myfitnesspal.feature.video.util.VideoAnalyticsHelper) r1
                com.myfitnesspal.feature.video.activity.VideoActivity r0 = com.myfitnesspal.feature.video.activity.VideoActivity.this
                java.lang.String r2 = r0.flowId
                java.lang.String r3 = r8.getName()
                com.myfitnesspal.feature.video.activity.VideoActivity r8 = com.myfitnesspal.feature.video.activity.VideoActivity.this
                com.myfitnesspal.feature.video.viewmodel.VideoViewModel r8 = r8.viewModel
                java.lang.String r4 = r8.getVideoFranchise()
                com.myfitnesspal.feature.video.activity.VideoActivity r8 = com.myfitnesspal.feature.video.activity.VideoActivity.this
                com.myfitnesspal.feature.video.viewmodel.VideoViewModel r8 = r8.viewModel
                java.lang.String r5 = r8.getSponsorName()
                r6 = r9
                r1.reportListItemClicked(r2, r3, r4, r5, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.video.activity.VideoActivity.AnonymousClass2.onVideoClicked(com.brightcove.player.model.Video, int):void");
        }

        public void onSponsorInfoClicked(@Nullable String str, @Nullable String str2) {
            VideoActivity.this.getNavigationHelper().withIntent(FullScreenWebView.newStartIntentForSponsor(VideoActivity.this, str, str2)).startActivity();
        }

        public void onBlogLinkClicked() {
            VideoActivity.this.getNavigationHelper().withIntent(BlogActivity.newStartIntent(VideoActivity.this, VideoActivity.EXTRA_VIDEO_BLOG, "mfp", VideoActivity.EXTRA_BLOG_MEDIUM)).startActivity();
        }
    };
    @Inject
    Lazy<AudioManager> audioManager;
    @Inject
    Lazy<ConfigService> configService;
    private CountDownTimer countDownTimer;
    @BindView(2131362458)
    View emptyListView;
    /* access modifiers changed from: private */
    public EventEmitter eventEmitter;
    /* access modifiers changed from: private */
    public String flowId;
    /* access modifiers changed from: private */
    public GoogleIMAComponent googleIMAComponent;
    @BindView(2131364150)
    ViewGroup imageViewContainer;
    boolean isAdPaused = false;
    private int lastItemCount = 0;
    private LifecycleUtil lifecycleUtil;
    @BindView(2131362923)
    View listContainer;
    /* access modifiers changed from: private */
    public int numberOfVideoClicks;
    private OnAudioFocusChangeListener onAudioFocusChangeListener = $$Lambda$VideoActivity$QSmO1KBYePRLxtwtyhudV5wsrhc.INSTANCE;
    @Inject
    Lazy<PremiumService> premiumService;
    @Nullable
    @BindView(2131362943)
    ProgressBar progressBar;
    private long startTime;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    @Inject
    Lazy<VideoAnalyticsHelper> videoAnalyticsHelper;
    @BindView(2131364147)
    ConstraintLayout videoContainer;
    @BindView(2131364149)
    ImageView videoImageView;
    @BindView(2131364146)
    BrightcoveVideoView videoView;
    @Nullable
    @BindView(2131364154)
    RecyclerView videosList;
    /* access modifiers changed from: private */
    public VideoViewModel viewModel;

    static /* synthetic */ void lambda$new$6(int i) {
    }

    public String getAnalyticsScreenTag() {
        return EVENT_SCREEN_BROWSE_VIDEOS;
    }

    public boolean showToolbar() {
        return false;
    }

    public static Intent newStartIntent(@NonNull Context context, @NonNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        return new Intent(context, VideoActivity.class).putExtra(EXTRA_VIDEO_ID, str).putExtra(EXTRA_PLAYLIST_ID, str2).putExtra(EXTRA_VIDEO_THUMBNAIL, str3).putExtra("flow_id", str4);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.video_activity);
        ButterKnife.bind((Activity) this);
        AdRegistration.getInstance(getAdsSettings().getAmazonAppId(), this);
        this.flowId = BundleUtils.getString(getIntent().getExtras(), "flow_id", "");
        initVideoView();
        if (!((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
            setupGoogleIMA();
        }
        initViewModel();
        LifecycleUtil lifecycleUtil2 = this.lifecycleUtil;
        if (lifecycleUtil2 == null || lifecycleUtil2.baseVideoView != this.videoView) {
            this.lifecycleUtil = new LifecycleUtil(this.videoView);
            this.lifecycleUtil.onCreate(bundle, this);
        }
        this.videosList.setLayoutManager(new LinearLayoutManager(this));
        this.videosList.setHasFixedSize(true);
        RecyclerView recyclerView = this.videosList;
        VideoGalleryAdapter videoGalleryAdapter = new VideoGalleryAdapter(this.adapterListener, this.viewModel, this.premiumService, this.videoAnalyticsHelper, this.flowId);
        recyclerView.setAdapter(videoGalleryAdapter);
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.lifecycleUtil.onConfigurationChanged(configuration);
        super.onConfigurationChanged(configuration);
        this.videoView.post(new Runnable(configuration) {
            private final /* synthetic */ Configuration f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                VideoActivity.lambda$onConfigurationChanged$0(VideoActivity.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ void lambda$onConfigurationChanged$0(VideoActivity videoActivity, Configuration configuration) {
        if (configuration.orientation == 2) {
            videoActivity.toggleFullScreen(true);
        } else if (configuration.orientation == 1) {
            videoActivity.toggleFullScreen(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.lifecycleUtil.activityOnStart();
        this.startTime = System.nanoTime();
        if (ActivityUtils.isInMultiWindow(this)) {
            this.lifecycleUtil.activityOnResume();
            this.viewModel.loadIfNotLoaded(new VideoGalleryAdapterItem[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!ActivityUtils.isInMultiWindow(this)) {
            this.lifecycleUtil.activityOnResume();
            this.viewModel.loadIfNotLoaded(new VideoGalleryAdapterItem[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        this.lifecycleUtil.onRestart();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (!ActivityUtils.isInMultiWindow(this)) {
            this.lifecycleUtil.activityOnPause();
            ((AudioManager) this.audioManager.get()).abandonAudioFocus(this.onAudioFocusChangeListener);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.lifecycleUtil.activityOnDestroy();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (ActivityUtils.isInMultiWindow(this)) {
            this.lifecycleUtil.activityOnPause();
            ((AudioManager) this.audioManager.get()).abandonAudioFocus(this.onAudioFocusChangeListener);
        }
        ViewUtils.setGone(this.progressBar);
        this.lifecycleUtil.activityOnStop();
        double nanoTime = ((double) (System.nanoTime() - this.startTime)) / NANOS_PER_MILSECOND;
        Video activeVideo = this.viewModel.getActiveVideo();
        ((VideoAnalyticsHelper) this.videoAnalyticsHelper.get()).reportVideoPlayerExit(this.flowId, activeVideo != null ? activeVideo.getName() : "", this.viewModel.getVideoFranchise(), this.viewModel.getSponsorName(), nanoTime, this.numberOfVideoClicks);
        this.numberOfVideoClicks = 0;
    }

    public void onSaveInstanceState(final Bundle bundle) {
        this.videoView.getEventEmitter().on(EventType.ACTIVITY_SAVE_INSTANCE_STATE, new com.brightcove.player.event.EventListener() {
            @Default
            public void processEvent(Event event) {
                VideoActivity.super.onSaveInstanceState(bundle);
            }
        });
        this.lifecycleUtil.activityOnSaveInstanceState(bundle);
    }

    private void initViewModel() {
        this.viewModel = (VideoViewModel) getViewModel();
        if (this.viewModel == null) {
            String string = BundleUtils.getString(getIntent().getExtras(), EXTRA_VIDEO_ID, "");
            String string2 = BundleUtils.getString(getIntent().getExtras(), EXTRA_PLAYLIST_ID, "");
            String string3 = BundleUtils.getString(getIntent().getExtras(), EXTRA_VIDEO_THUMBNAIL, "");
            ViewUtils.setVisible(this.imageViewContainer);
            ViewUtils.setVisible(this.videoImageView);
            ViewUtils.setGone(this.activeVideoErrorContainer);
            Glide.with((FragmentActivity) this).load(string3).apply(IMAGE_OPTIONS).into(this.videoImageView);
            this.viewModel = (VideoViewModel) setViewModel(new VideoViewModel(getRunner(), string, string2, string3));
        }
        this.lastItemCount = this.viewModel.getCount();
        this.viewModel.setAdParams(getAdsSettings().getVideoAdParams(), getString(R.string.video_ad_amazon_slot_uuid));
        this.viewModel.setEventEmitter(this.videoView.getEventEmitter());
        this.viewModel.loadIfNotLoaded(new VideoGalleryAdapterItem[0]);
        if (this.viewModel.getState() == State.Loaded && this.viewModel.getCount() == 0) {
            showEmptyListState();
        }
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        super.onViewModelPropertyChanged(observable, i);
        if (i == Property.LOAD_STATE) {
            updateAdapter();
            State state = this.viewModel.getState();
            if (this.viewModel.getCount() == 0 && (state == State.Error || state == State.Loaded)) {
                showEmptyListState();
            }
            if (state == State.Loading) {
                ViewUtils.setVisible(this.videosList);
                ViewUtils.setGone(this.emptyListView);
            }
        } else if (i == Property.VIDEO_LOADED) {
            Video activeVideo = this.viewModel.getActiveVideo();
            if (activeVideo != null) {
                ViewUtils.setVisible(this.imageViewContainer);
                ViewUtils.setVisible(this.videoImageView);
                ViewUtils.setVisible(this.videoView);
                ViewUtils.setGone(this.activeVideoErrorContainer);
                Glide.with((FragmentActivity) this).load(this.viewModel.getVideoThumbnail()).apply(IMAGE_OPTIONS).into(this.videoImageView);
                this.videoView.add(activeVideo);
                this.videoView.start();
            } else {
                showEmptyVideoState();
            }
        } else if (i == Property.VIDEO_FAILED_LOAD) {
            showEmptyVideoState();
        } else if (i == Property.VIDEO_CHANGED) {
            this.videoView.getBrightcoveMediaController().getBrightcoveControlBar().setVisibility(4);
            this.videosList.getAdapter().notifyItemChanged(0);
            this.videosList.getAdapter().notifyItemRemoved(this.viewModel.getIndexOfCurrentlySelectedVideo());
            this.videosList.getAdapter().notifyItemRangeChanged(this.viewModel.getIndexOfCurrentlySelectedVideo(), this.viewModel.getCount());
            this.videoView.clear();
            startVideo();
        }
        super.onViewModelPropertyChanged(observable, i);
    }

    private void initVideoView() {
        BrightcoveVideoView brightcoveVideoView = this.videoView;
        brightcoveVideoView.setMediaController(new BrightcoveMediaController(brightcoveVideoView, R.layout.custom_video_controller));
        this.videoView.getAnalytics().setSource(VideoUtil.isVideoAutoPlayOn(this.userApplicationSettingsService, this.configService) ? AUTO_PLAY_SOURCE : CLICK_TO_PLAY_SOURCE);
        initControls();
        this.videoView.finishInitialization();
    }

    private void initControls() {
        this.eventEmitter = this.videoView.getEventEmitter();
        initListeners(this.eventEmitter);
        this.videoView.getBrightcoveMediaController().setShowHideTimeout(2000);
        Button button = (Button) this.videoView.findViewById(R.id.play);
        button.setActivated(!this.videoView.isPlaying());
        button.setOnClickListener(new OnClickListener(button) {
            private final /* synthetic */ Button f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                VideoActivity.lambda$initControls$1(VideoActivity.this, this.f$1, view);
            }
        });
        ((TextView) this.videoView.findViewById(R.id.next_counter)).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                VideoActivity.lambda$initControls$2(VideoActivity.this, view);
            }
        });
        ((Button) this.videoView.findViewById(R.id.close)).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                VideoActivity.this.onBackPressed();
            }
        });
    }

    public static /* synthetic */ void lambda$initControls$1(VideoActivity videoActivity, Button button, View view) {
        if (videoActivity.viewModel.isInRewind()) {
            CountDownTimer countDownTimer2 = videoActivity.countDownTimer;
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
            }
            videoActivity.viewModel.setInRewind(false);
            videoActivity.videoView.clear();
            videoActivity.startVideo();
            videoActivity.videoView.getBrightcoveMediaController().getBrightcoveControlBar().setVisibility(4);
        } else if (videoActivity.videoView.isPlaying()) {
            button.setActivated(true);
            videoActivity.videoView.getEventEmitter().emit(EventType.PAUSE);
        } else {
            button.setActivated(false);
            videoActivity.videoView.getEventEmitter().emit(EventType.PLAY);
        }
    }

    public static /* synthetic */ void lambda$initControls$2(VideoActivity videoActivity, View view) {
        videoActivity.viewModel.setInRewind(false);
        CountDownTimer countDownTimer2 = videoActivity.countDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        videoActivity.hideRewindUI();
        videoActivity.playNextVideo();
    }

    public void onBackPressed() {
        BrightcoveVideoView brightcoveVideoView = this.videoView;
        if (brightcoveVideoView != null) {
            brightcoveVideoView.pause();
            this.videoView.stopPlayback();
        }
        super.onBackPressed();
    }

    private void initListeners(@NonNull EventEmitter eventEmitter2) {
        $$Lambda$VideoActivity$rTtCPyDHnfWRNi8yuzG2_pNKrgU r1 = new com.brightcove.player.event.EventListener((Button) this.videoView.findViewById(R.id.play)) {
            private final /* synthetic */ Button f$1;

            {
                this.f$1 = r2;
            }

            public final void processEvent(Event event) {
                VideoActivity.lambda$initListeners$4(VideoActivity.this, this.f$1, event);
            }
        };
        eventEmitter2.on(EventType.DID_PLAY, r1);
        eventEmitter2.on(EventType.CONFIGURATION_CHANGED, r1);
        eventEmitter2.on("completed", r1);
        eventEmitter2.on(EventType.REWIND, r1);
        eventEmitter2.on(EventType.AD_STARTED, r1);
        eventEmitter2.on(EventType.AD_COMPLETED, r1);
        eventEmitter2.on(EventType.AD_BREAK_COMPLETED, r1);
        eventEmitter2.on(EventType.AD_PAUSED, r1);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$initListeners$4(com.myfitnesspal.feature.video.activity.VideoActivity r5, android.widget.Button r6, com.brightcove.player.event.Event r7) {
        /*
            java.lang.String r7 = r7.getType()
            int r0 = r7.hashCode()
            r1 = 3
            r2 = 4
            r3 = 0
            r4 = 1
            switch(r0) {
                case -2023283490: goto L_0x004c;
                case -1806521551: goto L_0x0042;
                case -1402931637: goto L_0x0038;
                case -1274581282: goto L_0x002e;
                case -1245394161: goto L_0x0024;
                case -81067672: goto L_0x001a;
                case 1656958035: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x0056
        L_0x0010:
            java.lang.String r0 = "didPlay"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0056
            r7 = 0
            goto L_0x0057
        L_0x001a:
            java.lang.String r0 = "adCompleted"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0056
            r7 = 4
            goto L_0x0057
        L_0x0024:
            java.lang.String r0 = "adBreakCompleted"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0056
            r7 = 5
            goto L_0x0057
        L_0x002e:
            java.lang.String r0 = "adStarted"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0056
            r7 = 3
            goto L_0x0057
        L_0x0038:
            java.lang.String r0 = "completed"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0056
            r7 = 2
            goto L_0x0057
        L_0x0042:
            java.lang.String r0 = "adPaused"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0056
            r7 = 6
            goto L_0x0057
        L_0x004c:
            java.lang.String r0 = "configurationChanged"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0056
            r7 = 1
            goto L_0x0057
        L_0x0056:
            r7 = -1
        L_0x0057:
            switch(r7) {
                case 0: goto L_0x00ac;
                case 1: goto L_0x00a8;
                case 2: goto L_0x0095;
                case 3: goto L_0x007c;
                case 4: goto L_0x006d;
                case 5: goto L_0x006d;
                case 6: goto L_0x005c;
                default: goto L_0x005a;
            }
        L_0x005a:
            goto L_0x00f9
        L_0x005c:
            com.brightcove.player.view.BrightcoveVideoView r6 = r5.videoView
            com.brightcove.player.mediacontroller.BrightcoveMediaController r6 = r6.getBrightcoveMediaController()
            com.brightcove.player.mediacontroller.BrightcoveControlBar r6 = r6.getBrightcoveControlBar()
            r6.setVisibility(r2)
            r5.isAdPaused = r4
            goto L_0x00f9
        L_0x006d:
            com.brightcove.player.view.BrightcoveVideoView r6 = r5.videoView
            com.brightcove.player.mediacontroller.BrightcoveMediaController r6 = r6.getBrightcoveMediaController()
            com.brightcove.player.mediacontroller.BrightcoveControlBar r6 = r6.getBrightcoveControlBar()
            r6.setVisibility(r2)
            goto L_0x00f9
        L_0x007c:
            android.view.ViewGroup r6 = r5.imageViewContainer
            com.uacf.core.util.ViewUtils.setGone(r6)
            android.widget.ImageView r6 = r5.videoImageView
            com.uacf.core.util.ViewUtils.setGone(r6)
            android.view.View[] r6 = new android.view.View[r4]
            com.brightcove.player.view.BrightcoveVideoView r7 = r5.videoView
            r6[r3] = r7
            com.uacf.core.util.ViewUtils.setVisible(r6)
            android.view.View r6 = r5.activeVideoErrorContainer
            com.uacf.core.util.ViewUtils.setGone(r6)
            goto L_0x00f9
        L_0x0095:
            com.myfitnesspal.feature.video.viewmodel.VideoViewModel r6 = r5.viewModel
            r6.setInRewind(r4)
            com.brightcove.player.view.BrightcoveVideoView r6 = r5.videoView
            r6.pause()
            com.brightcove.player.view.BrightcoveVideoView r6 = r5.videoView
            r6.stopPlayback()
            r5.toggleVideoRewind()
            goto L_0x00f9
        L_0x00a8:
            r5.initControls()
            goto L_0x00f9
        L_0x00ac:
            boolean r7 = r5.isAdPaused
            if (r7 == 0) goto L_0x00b7
            android.view.ViewGroup r7 = r5.adView
            com.uacf.core.util.ViewUtils.setGone(r7)
            r5.isAdPaused = r3
        L_0x00b7:
            android.view.ViewGroup r7 = r5.imageViewContainer
            com.uacf.core.util.ViewUtils.setGone(r7)
            android.widget.ImageView r7 = r5.videoImageView
            com.uacf.core.util.ViewUtils.setGone(r7)
            android.view.View[] r7 = new android.view.View[r4]
            com.brightcove.player.view.BrightcoveVideoView r0 = r5.videoView
            r7[r3] = r0
            com.uacf.core.util.ViewUtils.setVisible(r7)
            android.view.View r7 = r5.activeVideoErrorContainer
            com.uacf.core.util.ViewUtils.setGone(r7)
            com.brightcove.player.view.BrightcoveVideoView r7 = r5.videoView
            boolean r7 = r7.isPlaying()
            r7 = r7 ^ r4
            r6.setActivated(r7)
            r5.toggleVideoRewind()
            r5.toggleVideoTitle()
            com.brightcove.player.view.BrightcoveVideoView r6 = r5.videoView
            com.brightcove.player.mediacontroller.BrightcoveMediaController r6 = r6.getBrightcoveMediaController()
            com.brightcove.player.mediacontroller.BrightcoveControlBar r6 = r6.getBrightcoveControlBar()
            r6.setVisibility(r2)
            dagger.Lazy<android.media.AudioManager> r6 = r5.audioManager
            java.lang.Object r6 = r6.get()
            android.media.AudioManager r6 = (android.media.AudioManager) r6
            android.media.AudioManager$OnAudioFocusChangeListener r7 = r5.onAudioFocusChangeListener
            r6.requestAudioFocus(r7, r1, r4)
        L_0x00f9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.video.activity.VideoActivity.lambda$initListeners$4(com.myfitnesspal.feature.video.activity.VideoActivity, android.widget.Button, com.brightcove.player.event.Event):void");
    }

    private void updateAdapter() {
        if (this.viewModel.getState() == State.Loaded) {
            ViewUtils.setGone(this.progressBar);
            if (this.viewModel.getCount() > 0) {
                this.videosList.getAdapter().notifyItemRangeInserted(this.lastItemCount, this.viewModel.getCount());
                this.lastItemCount = this.viewModel.getCount();
            }
        } else if (this.viewModel.getState() == State.Loading) {
            ViewUtils.setVisible(this.progressBar);
        }
    }

    private void showEmptyListState() {
        ViewUtils.setGone(this.progressBar);
        TextView textView = (TextView) this.emptyListView.findViewById(R.id.list_error_message);
        if (this.viewModel.isOnline()) {
            textView.setText(R.string.video_error_message);
        } else {
            textView.setText(R.string.video_error_internet_message);
        }
        ViewUtils.setVisible(this.emptyListView);
        ViewUtils.setGone(this.videosList);
    }

    private void showEmptyVideoState() {
        TextView textView = (TextView) this.activeVideoErrorContainer.findViewById(R.id.active_video_error_msg);
        ((Button) this.activeVideoErrorContainer.findViewById(R.id.close_on_error)).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                VideoActivity.this.onBackPressed();
            }
        });
        ViewUtils.setGone(this.imageViewContainer);
        ViewUtils.setGone(this.videoView);
        ViewUtils.setVisible(this.activeVideoErrorContainer);
        textView.setText(ActivityUtils.isLandscape(this) ? R.string.video_error_message_lands : R.string.video_error_message);
    }

    private void toggleFullScreen(boolean z) {
        if (z) {
            ViewUtils.setGone(this.listContainer);
            this.videoContainer.setLayoutParams(new LayoutParams(-1, -1));
        } else {
            this.videoContainer.setLayoutParams(new LayoutParams(-1, -2));
            ViewUtils.setVisible(this.listContainer);
        }
        initControls();
        toggleVideoRewind();
        toggleVideoTitle();
    }

    private void toggleVideoTitle() {
        Video activeVideo = this.viewModel.getActiveVideo();
        TextView textView = (TextView) this.videoView.findViewById(R.id.video_title);
        if (activeVideo != null) {
            textView.setText(activeVideo.getName());
        }
        ViewUtils.setVisible(ActivityUtils.isLandscape(this), 4, textView);
    }

    private void toggleVideoRewind() {
        TextView textView = (TextView) this.videoView.findViewById(R.id.next_counter);
        CountDownTimer countDownTimer2 = this.countDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        ViewUtils.setGone(textView);
        final TextView textView2 = textView;
        AnonymousClass3 r1 = new CountDownTimer(ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME, 1000) {
            public void onTick(long j) {
                textView2.setText(VideoActivity.this.getString(R.string.next_video_plays_in, new Object[]{Long.valueOf(j / 1000)}));
            }

            public void onFinish() {
                VideoActivity.this.viewModel.setInRewind(false);
                VideoActivity.this.hideRewindUI();
                ViewUtils.setVisible(false, 4, textView2);
                VideoActivity.this.playNextVideo();
            }
        };
        this.countDownTimer = r1;
        if (this.viewModel.isInRewind()) {
            showRewindUI();
            if (this.viewModel.getCount() > 1) {
                ViewUtils.setVisible(textView);
                this.countDownTimer.start();
            }
            this.videoView.getBrightcoveMediaController().show();
            return;
        }
        hideRewindUI();
    }

    private void startVideo() {
        Video activeVideo = this.viewModel.getActiveVideo();
        ViewUtils.setVisible(this.imageViewContainer);
        ViewUtils.setVisible(this.videoImageView);
        ViewUtils.setVisible(this.videoView);
        ViewUtils.setGone(this.activeVideoErrorContainer);
        Glide.with((FragmentActivity) this).load(this.viewModel.getVideoThumbnail()).apply(IMAGE_OPTIONS).into(this.videoImageView);
        if (activeVideo != null) {
            this.videoView.add(activeVideo);
            this.videoView.start();
        }
    }

    /* access modifiers changed from: private */
    public void hideRewindUI() {
        Button button = (Button) this.videoView.findViewById(R.id.play);
        BrightcoveMediaController brightcoveMediaController = this.videoView.getBrightcoveMediaController();
        brightcoveMediaController.setShowHideTimeout(2000);
        brightcoveMediaController.getBrightcoveControlBar().setBackground(getResources().getDrawable(R.drawable.transparent_background));
        button.setBackground(getResources().getDrawable(R.drawable.video_play_pause_btn));
    }

    private void showRewindUI() {
        ViewUtils.setGone(this.imageViewContainer);
        ViewUtils.setGone(this.videoImageView);
        ViewUtils.setVisible(this.videoView);
        ViewUtils.setGone(this.activeVideoErrorContainer);
        ((Button) this.videoView.findViewById(R.id.play)).setBackground(getResources().getDrawable(R.drawable.ic_reload));
        BrightcoveMediaController brightcoveMediaController = this.videoView.getBrightcoveMediaController();
        brightcoveMediaController.setShowHideTimeout(Integer.MAX_VALUE);
        brightcoveMediaController.getBrightcoveControlBar().setBackground(getResources().getDrawable(R.drawable.video_reload_overlay));
    }

    /* access modifiers changed from: private */
    public void playNextVideo() {
        Video videoAt = this.viewModel.getVideoAt(1);
        if (videoAt != null) {
            this.viewModel.onVideoChanged(videoAt, 1);
            this.videoView.getBrightcoveMediaController().getBrightcoveControlBar().setVisibility(4);
            ((VideoAnalyticsHelper) this.videoAnalyticsHelper.get()).reportListItemAutoPlayed(this.flowId, videoAt.getName(), this.viewModel.getVideoFranchise(), this.viewModel.getSponsorName());
            return;
        }
        this.videoView.getEventEmitter().emit(EventType.REWIND);
    }

    private void setupGoogleIMA() {
        final ImaSdkFactory instance = ImaSdkFactory.getInstance();
        this.eventEmitter.on("adsRequestForVideo", new com.brightcove.player.event.EventListener() {
            public void processEvent(Event event) {
                Map adsMap = VideoActivity.this.viewModel.getAdsMap();
                Video activeVideo = VideoActivity.this.viewModel.getActiveVideo();
                if (activeVideo != null) {
                    String id = activeVideo.getId();
                    if (adsMap.containsKey(id)) {
                        AdDisplayContainer createAdDisplayContainer = instance.createAdDisplayContainer();
                        createAdDisplayContainer.setPlayer(VideoActivity.this.googleIMAComponent.getVideoAdPlayer());
                        createAdDisplayContainer.setAdContainer(VideoActivity.this.adView);
                        ViewUtils.setVisible(VideoActivity.this.adView);
                        ArrayList arrayList = new ArrayList(1);
                        AdsRequest createAdsRequest = instance.createAdsRequest();
                        createAdsRequest.setAdTagUrl((String) adsMap.get(id));
                        createAdsRequest.setAdDisplayContainer(createAdDisplayContainer);
                        arrayList.add(createAdsRequest);
                        event.properties.put("adsRequests", arrayList);
                        VideoActivity.this.eventEmitter.respond(event);
                    }
                }
            }
        });
        this.googleIMAComponent = new GoogleIMAComponent(this.videoView, this.eventEmitter);
    }
}
