package com.myfitnesspal.feature.home.ui.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.model.BlogEntry;
import com.myfitnesspal.feature.home.model.MfpNewsFeedBlogImageEntry;
import com.myfitnesspal.feature.home.model.MfpNewsFeedBlogThumbnail;
import com.myfitnesspal.feature.home.model.MfpNewsFeedLinkDesc;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class SingleBlogNewsFeedCard extends BlogNewsFeedCardBase {
    private static final String BLOG_SOURCE = "blog_source";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static RequestOptions REQUEST_OPTIONS = new RequestOptions().fitCenter().centerCrop();
    private static final String TITLE = "title";
    @BindView(2131361947)
    ImageView blogImageView;
    @BindView(2131361948)
    TextView blogLinkTextView;
    @BindView(2131362297)
    TextView descriptionTextView;
    @BindView(2131362943)
    View loadingView;
    @BindView(2131363829)
    TextView timestampTextView;
    @BindView(2131363833)
    TextView titleTextView;

    public SingleBlogNewsFeedCard(ViewGroup viewGroup, NavigationHelper navigationHelper, Lazy<NewsFeedAnalyticsHelper> lazy) {
        super(R.layout.single_blog_newsfeed_item, viewGroup, navigationHelper, lazy);
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) newsFeedItem;
        final BlogEntry blogEntry = (BlogEntry) mfpNewsFeedActivityEntry.getEntryData();
        MfpNewsFeedBlogImageEntry post = blogEntry.getPost();
        final MfpNewsFeedLinkDesc link = post.getLink();
        if (link != null) {
            String postDescription = link.getPostDescription();
            String blogSource = link.getBlogSource();
            String webUrl = link.getWebUrl();
            ViewUtils.setVisible(Strings.notEmpty(postDescription), this.descriptionTextView);
            this.titleTextView.setText(link.getText());
            this.descriptionTextView.setText(postDescription);
            TextView textView = this.blogLinkTextView;
            if (Strings.isEmpty(blogSource)) {
                blogSource = this.context.getString(R.string.myfitnesspal_blog);
            }
            textView.setText(blogSource);
            setListenerForOpeningBlogPost(blogEntry, webUrl, this.blogImageView, "image");
            setListenerForOpeningBlogPost(blogEntry, webUrl, this.titleTextView, "title");
            setListenerForOpeningBlogPost(blogEntry, webUrl, this.descriptionTextView, "description");
            this.blogLinkTextView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    SingleBlogNewsFeedCard.this.handleBlogItemClick(link.getBlogSourceUrl());
                    ((NewsFeedAnalyticsHelper) SingleBlogNewsFeedCard.this.newsFeedAnalyticsHelper.get()).reportSingleBlogItemClicked(blogEntry, SingleBlogNewsFeedCard.BLOG_SOURCE);
                }
            });
            ViewUtils.increaseHitRectBy(this.context.getResources().getDimensionPixelSize(R.dimen.blog_source_additional_hit_area), this.blogLinkTextView);
        }
        this.timestampTextView.setText(this.context.getString(R.string.date_from_blog, new Object[]{getBlogDisplayDate(mfpNewsFeedActivityEntry.getCreatedAt())}));
        setupBlogImage(post.getThumbnail());
    }

    private void setListenerForOpeningBlogPost(final BlogEntry blogEntry, final String str, View view, final String str2) {
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SingleBlogNewsFeedCard.this.handleBlogItemClick(str);
                ((NewsFeedAnalyticsHelper) SingleBlogNewsFeedCard.this.newsFeedAnalyticsHelper.get()).reportSingleBlogItemClicked(blogEntry, str2);
            }
        });
    }

    private void setupBlogImage(MfpNewsFeedBlogThumbnail mfpNewsFeedBlogThumbnail) {
        if (mfpNewsFeedBlogThumbnail != null) {
            setupBlogImageViewParams();
            ViewUtils.setVisible(this.loadingView);
            Glide.with(this.context).load(getBestFitImageUrl(mfpNewsFeedBlogThumbnail)).apply(REQUEST_OPTIONS).listener(new RequestListener<Drawable>() {
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    ViewUtils.setGone(SingleBlogNewsFeedCard.this.loadingView);
                    return false;
                }

                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    ViewUtils.setGone(SingleBlogNewsFeedCard.this.loadingView);
                    return false;
                }
            }).into(this.blogImageView);
        }
    }

    private void setupBlogImageViewParams() {
        int i = (this.context.getResources().getDisplayMetrics().widthPixels * 2) / 4;
        LayoutParams layoutParams = this.blogImageView.getLayoutParams();
        layoutParams.height = i;
        this.blogImageView.setLayoutParams(layoutParams);
    }
}
