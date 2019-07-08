package com.myfitnesspal.feature.video.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.brightcove.player.model.Video;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.video.model.VideoGalleryAdapterItem;
import com.myfitnesspal.feature.video.model.VideoGalleryDetailsItem;
import com.myfitnesspal.feature.video.model.VideoGalleryItem;
import com.myfitnesspal.feature.video.util.VideoAnalyticsHelper;
import com.myfitnesspal.feature.video.viewmodel.VideoViewModel;
import com.myfitnesspal.shared.ui.view.BlueClickableSpanNoUnderline;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class VideoGalleryAdapter extends Adapter<RecyclerViewHolder<VideoGalleryAdapterItem>> {
    /* access modifiers changed from: private */
    public static final RequestOptions IMAGE_OPTIONS = new RequestOptions().fitCenter().centerCrop().placeholder((int) R.drawable.video_placeholder).error(R.drawable.video_placeholder);
    private static final int VIDEO_DETAILS_ITEM_VIEW_TYPE = 1010;
    private static final int VIDEO_ITEM_VIEW_TYPE = 1110;
    /* access modifiers changed from: private */
    public final EventListener eventListener;
    /* access modifiers changed from: private */
    public final String flowId;
    /* access modifiers changed from: private */
    public final Lazy<PremiumService> premiumService;
    /* access modifiers changed from: private */
    public final Lazy<VideoAnalyticsHelper> videoAnalyticsHelper;
    private final VideoViewModel viewModel;

    public interface EventListener {
        void onBlogLinkClicked();

        void onSponsorInfoClicked(@Nullable String str, @Nullable String str2);

        void onVideoClicked(@NonNull Video video, int i);
    }

    final class VideoDetailsViewHolder extends RecyclerViewHolder<VideoGalleryAdapterItem> {
        @BindView(2131361948)
        TextView blogLink;
        @BindView(2131362340)
        View divide1;
        @BindView(2131362341)
        View divide2;
        @BindView(2131363282)
        TextView playlistName;
        @BindView(2131363697)
        View sponsorContainer;
        @BindView(2131363698)
        ImageView sponsorImage;
        @BindView(2131363699)
        TextView sponsorName;
        @BindView(2131364151)
        TextView videoName;

        VideoDetailsViewHolder(ViewGroup viewGroup) {
            super(R.layout.video_details_item, viewGroup);
        }

        public void setData(VideoGalleryAdapterItem videoGalleryAdapterItem, int i) {
            if (videoGalleryAdapterItem instanceof VideoGalleryDetailsItem) {
                VideoGalleryDetailsItem videoGalleryDetailsItem = (VideoGalleryDetailsItem) videoGalleryAdapterItem;
                final String playlistName2 = videoGalleryDetailsItem.getPlaylistName();
                this.videoName.setText(videoGalleryDetailsItem.getVideoName());
                this.playlistName.setText(playlistName2);
                final String stringProperty = videoGalleryDetailsItem.getStringProperty("sponsor");
                String stringProperty2 = videoGalleryDetailsItem.getStringProperty(VideoGalleryDetailsItem.SPONSOR_IMAGE_PROPERTY);
                final String stringProperty3 = videoGalleryDetailsItem.getStringProperty(VideoGalleryDetailsItem.SPONSOR_LINK_PROPERTY);
                if (!Strings.notEmpty(stringProperty) || !Strings.notEmpty(stringProperty2) || ((PremiumService) VideoGalleryAdapter.this.premiumService.get()).isPremiumSubscribed()) {
                    ViewUtils.setGone(this.divide1);
                    ViewUtils.setGone(this.divide2);
                    this.playlistName.setLayoutParams(new LayoutParams(-1, (int) this.context.getResources().getDimension(R.dimen.video_playlist_title_height)));
                    this.playlistName.setBackground(this.context.getResources().getDrawable(R.drawable.video_playlist_name_background));
                } else {
                    ViewUtils.setVisible(this.sponsorContainer);
                    this.sponsorName.setText(stringProperty);
                    StringBuilder sb = new StringBuilder();
                    sb.append(VideoGalleryDetailsItem.SPONSOR_URL_PATH);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("w_");
                    sb2.append(this.context.getResources().getDimensionPixelOffset(R.dimen.video_sponsor_size));
                    sb.append(sb2.toString());
                    sb.append(",");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("h_");
                    sb3.append(this.context.getResources().getDimensionPixelOffset(R.dimen.video_sponsor_size));
                    sb.append(sb3.toString());
                    sb.append("/");
                    sb.append(stringProperty2);
                    Glide.with(this.context).load(sb.toString()).apply(VideoGalleryAdapter.IMAGE_OPTIONS).into(this.sponsorImage);
                    if (Strings.notEmpty(stringProperty3)) {
                        this.sponsorContainer.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                VideoGalleryAdapter.this.eventListener.onSponsorInfoClicked(stringProperty, stringProperty3);
                                ((VideoAnalyticsHelper) VideoGalleryAdapter.this.videoAnalyticsHelper.get()).reportFranchiseSponsorLinkClicked(VideoGalleryAdapter.this.flowId, playlistName2, stringProperty);
                            }
                        });
                    }
                    ViewUtils.setVisible(this.divide1);
                    ViewUtils.setVisible(this.divide2);
                    this.playlistName.setLayoutParams(new LayoutParams(-1, -2));
                    this.playlistName.setBackgroundColor(this.context.getResources().getColor(R.color.transparent));
                    ((VideoAnalyticsHelper) VideoGalleryAdapter.this.videoAnalyticsHelper.get()).reportFranchiseSponsorLinkViewed(VideoGalleryAdapter.this.flowId, playlistName2, stringProperty);
                }
                this.blogLink.setMovementMethod(LinkMovementMethod.getInstance());
                this.blogLink.setText(VideoGalleryAdapter.this.getBlogSpannableStringBuilder(this.context));
                ViewUtils.increaseHitRectBy(this.context.getResources().getDimensionPixelSize(R.dimen.blog_source_additional_hit_area), this.blogLink);
            }
        }
    }

    public final class VideoDetailsViewHolder_ViewBinding implements Unbinder {
        private VideoDetailsViewHolder target;

        @UiThread
        public VideoDetailsViewHolder_ViewBinding(VideoDetailsViewHolder videoDetailsViewHolder, View view) {
            this.target = videoDetailsViewHolder;
            videoDetailsViewHolder.sponsorContainer = Utils.findRequiredView(view, R.id.sponsor_attribution_container, "field 'sponsorContainer'");
            videoDetailsViewHolder.sponsorImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.sponsor_attribution_image, "field 'sponsorImage'", ImageView.class);
            videoDetailsViewHolder.sponsorName = (TextView) Utils.findRequiredViewAsType(view, R.id.sponsor_name, "field 'sponsorName'", TextView.class);
            videoDetailsViewHolder.videoName = (TextView) Utils.findRequiredViewAsType(view, R.id.video_name, "field 'videoName'", TextView.class);
            videoDetailsViewHolder.playlistName = (TextView) Utils.findRequiredViewAsType(view, R.id.playlist_name, "field 'playlistName'", TextView.class);
            videoDetailsViewHolder.blogLink = (TextView) Utils.findRequiredViewAsType(view, R.id.blog_link, "field 'blogLink'", TextView.class);
            videoDetailsViewHolder.divide1 = Utils.findRequiredView(view, R.id.divider1, "field 'divide1'");
            videoDetailsViewHolder.divide2 = Utils.findRequiredView(view, R.id.divider2, "field 'divide2'");
        }

        public void unbind() {
            VideoDetailsViewHolder videoDetailsViewHolder = this.target;
            if (videoDetailsViewHolder != null) {
                this.target = null;
                videoDetailsViewHolder.sponsorContainer = null;
                videoDetailsViewHolder.sponsorImage = null;
                videoDetailsViewHolder.sponsorName = null;
                videoDetailsViewHolder.videoName = null;
                videoDetailsViewHolder.playlistName = null;
                videoDetailsViewHolder.blogLink = null;
                videoDetailsViewHolder.divide1 = null;
                videoDetailsViewHolder.divide2 = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    final class VideoViewHolder extends RecyclerViewHolder<VideoGalleryAdapterItem> {
        @BindView(2131364148)
        TextView duration;
        @BindView(2131364151)
        TextView name;
        @BindView(2131364152)
        ImageView videoThumbnail;

        VideoViewHolder(ViewGroup viewGroup) {
            super(R.layout.video_gallery_item, viewGroup);
        }

        public void setData(VideoGalleryAdapterItem videoGalleryAdapterItem, int i) {
            if (videoGalleryAdapterItem instanceof VideoGalleryItem) {
                VideoGalleryItem videoGalleryItem = (VideoGalleryItem) videoGalleryAdapterItem;
                if (videoGalleryItem.getVideo() != null) {
                    Glide.with(this.context).load(videoGalleryItem.getImageUrl()).apply(VideoGalleryAdapter.IMAGE_OPTIONS).into(this.videoThumbnail);
                    this.name.setText(videoGalleryItem.getName());
                    this.itemView.setTag(videoGalleryAdapterItem);
                    this.itemView.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (view.getTag() instanceof VideoGalleryItem) {
                                Video video = ((VideoGalleryItem) view.getTag()).getVideo();
                                int adapterPosition = VideoViewHolder.this.getAdapterPosition();
                                if (video != null && adapterPosition != -1) {
                                    VideoGalleryAdapter.this.eventListener.onVideoClicked(video, adapterPosition);
                                }
                            }
                        }
                    });
                    this.duration.setText(videoGalleryItem.getDuration());
                }
            }
        }
    }

    public final class VideoViewHolder_ViewBinding implements Unbinder {
        private VideoViewHolder target;

        @UiThread
        public VideoViewHolder_ViewBinding(VideoViewHolder videoViewHolder, View view) {
            this.target = videoViewHolder;
            videoViewHolder.videoThumbnail = (ImageView) Utils.findRequiredViewAsType(view, R.id.video_thumbnail, "field 'videoThumbnail'", ImageView.class);
            videoViewHolder.name = (TextView) Utils.findRequiredViewAsType(view, R.id.video_name, "field 'name'", TextView.class);
            videoViewHolder.duration = (TextView) Utils.findRequiredViewAsType(view, R.id.video_duration, "field 'duration'", TextView.class);
        }

        public void unbind() {
            VideoViewHolder videoViewHolder = this.target;
            if (videoViewHolder != null) {
                this.target = null;
                videoViewHolder.videoThumbnail = null;
                videoViewHolder.name = null;
                videoViewHolder.duration = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public int getItemViewType(int i) {
        return i == 0 ? VIDEO_DETAILS_ITEM_VIEW_TYPE : VIDEO_ITEM_VIEW_TYPE;
    }

    public VideoGalleryAdapter(@NonNull EventListener eventListener2, @NonNull VideoViewModel videoViewModel, @NonNull Lazy<PremiumService> lazy, @NonNull Lazy<VideoAnalyticsHelper> lazy2, @Nullable String str) {
        this.eventListener = eventListener2;
        this.viewModel = videoViewModel;
        this.premiumService = lazy;
        this.videoAnalyticsHelper = lazy2;
        this.flowId = str;
    }

    public int getItemCount() {
        return this.viewModel.getCount();
    }

    public RecyclerViewHolder<VideoGalleryAdapterItem> onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == VIDEO_DETAILS_ITEM_VIEW_TYPE) {
            return new VideoDetailsViewHolder(viewGroup);
        }
        if (i == VIDEO_ITEM_VIEW_TYPE) {
            return new VideoViewHolder(viewGroup);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unhandled view type: ");
        sb.append(i);
        throw new IllegalStateException(sb.toString());
    }

    public void onBindViewHolder(RecyclerViewHolder<VideoGalleryAdapterItem> recyclerViewHolder, int i) {
        recyclerViewHolder.setData(this.viewModel.getVideoGalleryItem(i), i);
    }

    /* access modifiers changed from: private */
    public SpannableStringBuilder getBlogSpannableStringBuilder(@NonNull Context context) {
        String string = context.getString(R.string.blog_from);
        String string2 = context.getString(R.string.myfitnesspal_blog);
        String format = String.format("%1s %2s", new Object[]{string, string2});
        int indexOf = format.indexOf(string2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
        spannableStringBuilder.setSpan(new BlueClickableSpanNoUnderline(context) {
            public void onClick(View view) {
                VideoGalleryAdapter.this.eventListener.onBlogLinkClicked();
            }
        }, indexOf, string2.length() + indexOf, 33);
        return spannableStringBuilder;
    }
}
