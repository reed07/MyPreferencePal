package com.myfitnesspal.feature.home.ui.view;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.brightcove.player.display.ExoPlayerVideoDisplayComponent;
import com.brightcove.player.edge.Catalog;
import com.brightcove.player.edge.VideoListener;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveMediaController;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.blog.ui.activity.BlogActivity;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.model.VideoEntry;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.video.activity.VideoActivity;
import com.myfitnesspal.feature.video.util.VideoUtil;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.BlueClickableSpanNoUnderline;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class VideoViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    private static final String BLOG_MEDIUM = "app_newsfeed_video";
    private static final String BLOG_SOURCE = "mfp";
    private static final int BRIGHTCOVE_VIDEO_PEAK_BITRATE = 520000;
    private static final String IMAGE = "image";
    private static final RequestOptions IMAGE_OPTIONS = new RequestOptions().fitCenter().centerCrop().placeholder((int) R.drawable.video_placeholder).error(R.drawable.video_placeholder);
    private static final String URL_BLOG_SOURCE = "blog_source";
    private static final String VIDEO_BLOG = "video/";
    @BindView(2131361948)
    TextView blogLinkTextView;
    private final Lazy<ConfigService> configService;
    /* access modifiers changed from: private */
    public final String flowId;
    @BindView(2131364150)
    ViewGroup imageViewContainer;
    @BindView(2131363084)
    View mute;
    /* access modifiers changed from: private */
    public final NavigationHelper navigationHelper;
    /* access modifiers changed from: private */
    public final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    @BindView(2131363833)
    TextView titleTextView;
    private final Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    @Nullable
    private Video video;
    @BindView(2131364147)
    ConstraintLayout videoContainer;
    @BindView(2131364149)
    ImageView videoImageView;
    @BindView(2131364146)
    BrightcoveExoPlayerVideoView videoView;

    public VideoViewHolder(ViewGroup viewGroup, @NonNull NavigationHelper navigationHelper2, @NonNull Lazy<NewsFeedAnalyticsHelper> lazy, @NonNull Lazy<UserApplicationSettingsService> lazy2, @NonNull Lazy<ConfigService> lazy3, @Nullable String str) {
        super(R.layout.video_newsfeed_item, viewGroup);
        this.navigationHelper = navigationHelper2;
        this.newsFeedAnalyticsHelper = lazy;
        this.userApplicationSettingsService = lazy2;
        this.configService = lazy3;
        this.flowId = str;
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        VideoEntry videoEntry = (VideoEntry) ((MfpNewsFeedActivityEntry) newsFeedItem).getEntryData();
        this.titleTextView.setText(videoEntry.getTitle());
        this.blogLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        this.blogLinkTextView.setText(getBlogSpannableStringBuilder(videoEntry, i));
        ViewUtils.increaseHitRectBy(this.context.getResources().getDimensionPixelSize(R.dimen.blog_source_additional_hit_area), this.blogLinkTextView);
        this.itemView.setTag(videoEntry);
        setUpVideoContainer(videoEntry, i, VideoUtil.isVideoAutoPlayOn(this.userApplicationSettingsService, this.configService));
    }

    public void initVideo() {
        this.videoView.add(this.video);
    }

    public void start() {
        this.videoView.start();
    }

    public void pause() {
        this.videoView.pause();
    }

    public void stop() {
        this.videoView.stopPlayback();
        this.videoView.clear();
    }

    private SpannableStringBuilder getBlogSpannableStringBuilder(@NonNull final VideoEntry videoEntry, final int i) {
        String string = this.context.getString(R.string.blog_from);
        String string2 = this.context.getString(R.string.myfitnesspal_blog);
        String format = String.format("%1s %2s", new Object[]{string, string2});
        int indexOf = format.indexOf(string2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
        spannableStringBuilder.setSpan(new BlueClickableSpanNoUnderline(this.context) {
            public void onClick(View view) {
                ((NewsFeedAnalyticsHelper) VideoViewHolder.this.newsFeedAnalyticsHelper.get()).reportSingleVideoBlogItemClicked(VideoViewHolder.this.flowId, i, videoEntry.getTitle(), VideoViewHolder.URL_BLOG_SOURCE);
                VideoViewHolder.this.navigationHelper.withIntent(BlogActivity.newStartIntent(VideoViewHolder.this.navigationHelper.getContext(), VideoViewHolder.VIDEO_BLOG, "mfp", VideoViewHolder.BLOG_MEDIUM)).startActivity();
            }
        }, indexOf, string2.length() + indexOf, 33);
        return spannableStringBuilder;
    }

    private void setUpVideoContainer(@NonNull VideoEntry videoEntry, int i, boolean z) {
        if (z) {
            showAutoPlayState(videoEntry);
        } else {
            showNoAutoPlayState(videoEntry);
        }
        this.videoContainer.setOnClickListener(new OnClickListener(i, videoEntry) {
            private final /* synthetic */ int f$1;
            private final /* synthetic */ VideoEntry f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                VideoViewHolder.lambda$setUpVideoContainer$0(VideoViewHolder.this, this.f$1, this.f$2, view);
            }
        });
    }

    public static /* synthetic */ void lambda$setUpVideoContainer$0(VideoViewHolder videoViewHolder, @NonNull int i, VideoEntry videoEntry, View view) {
        videoViewHolder.stop();
        ((NewsFeedAnalyticsHelper) videoViewHolder.newsFeedAnalyticsHelper.get()).reportSingleVideoBlogItemClicked(videoViewHolder.flowId, i, videoEntry.getTitle(), "image");
        videoViewHolder.navigationHelper.withIntent(VideoActivity.newStartIntent(videoViewHolder.getContext(), videoEntry.getVideoId(), videoEntry.getPlaylistId(), videoEntry.getThumbnail(), videoViewHolder.flowId)).withActivityOptions(videoViewHolder.videoContainer, R.string.transition_name_video).startActivity();
    }

    private void configureVideoPlayer() {
        ((ExoPlayerVideoDisplayComponent) this.videoView.getVideoDisplay()).setPeakBitrate(BRIGHTCOVE_VIDEO_PEAK_BITRATE);
        this.videoView.setMediaController((BrightcoveMediaController) null);
        this.videoView.getEventEmitter().on(EventType.DID_PLAY, new EventListener() {
            public final void processEvent(Event event) {
                VideoViewHolder.lambda$configureVideoPlayer$1(VideoViewHolder.this, event);
            }
        });
        this.videoView.getEventEmitter().on("completed", new EventListener() {
            public final void processEvent(Event event) {
                VideoViewHolder.this.videoView.start();
            }
        });
        this.videoView.setOnPreparedListener(new OnPreparedListener() {
            public final void onPrepared(MediaPlayer mediaPlayer) {
                VideoViewHolder.lambda$configureVideoPlayer$3(VideoViewHolder.this, mediaPlayer);
            }
        });
    }

    public static /* synthetic */ void lambda$configureVideoPlayer$1(VideoViewHolder videoViewHolder, Event event) {
        ViewUtils.setGone(videoViewHolder.imageViewContainer);
        ViewUtils.setGone(videoViewHolder.videoImageView);
        ViewUtils.setVisible(videoViewHolder.videoView);
    }

    public static /* synthetic */ void lambda$configureVideoPlayer$3(VideoViewHolder videoViewHolder, MediaPlayer mediaPlayer) {
        ExoPlayer exoPlayer = ((ExoPlayerVideoDisplayComponent) videoViewHolder.videoView.getVideoDisplay()).getExoPlayer();
        if (exoPlayer instanceof SimpleExoPlayer) {
            ((SimpleExoPlayer) exoPlayer).setVolume(BitmapDescriptorFactory.HUE_RED);
        }
    }

    /* access modifiers changed from: private */
    public void playVideo(Video video2) {
        configureVideoPlayer();
    }

    private void showNoAutoPlayState(@NonNull VideoEntry videoEntry) {
        ViewUtils.setGone(this.videoView);
        ViewUtils.setVisible(this.videoImageView);
        ViewUtils.setVisible(this.imageViewContainer);
        ViewUtils.setGone(this.mute);
        Glide.with(this.context).load(videoEntry.getThumbnail()).apply(IMAGE_OPTIONS).into(this.videoImageView);
    }

    private void showAutoPlayState(@NonNull VideoEntry videoEntry) {
        ViewUtils.setVisible(this.imageViewContainer);
        ViewUtils.setVisible(this.videoImageView);
        ViewUtils.setVisible(this.videoView);
        ViewUtils.setVisible(this.mute);
        Glide.with(this.context).load(videoEntry.getThumbnail()).apply(IMAGE_OPTIONS).into(this.videoImageView);
        this.videoView.clear();
        this.video = Video.createVideo(videoEntry.getVideoUrl());
        Video video2 = this.video;
        if (video2 == null) {
            new Catalog(this.videoView.getEventEmitter(), this.context.getString(R.string.video_account), this.context.getString(R.string.video_policy)).findVideoByID(videoEntry.getVideoId(), new VideoListener() {
                public void onVideo(Video video) {
                    VideoViewHolder.this.playVideo(video);
                }

                public void onError(String str) {
                    VideoViewHolder.this.showErrorState();
                }
            });
        } else {
            playVideo(video2);
        }
    }

    /* access modifiers changed from: private */
    public void showErrorState() {
        ViewUtils.setGone(this.videoView);
        ViewUtils.setVisible(this.videoImageView);
        ViewUtils.setVisible(this.imageViewContainer);
        ViewUtils.setGone(this.mute);
    }

    public int getVideoViewHeight() {
        return this.videoView.getHeight();
    }
}
