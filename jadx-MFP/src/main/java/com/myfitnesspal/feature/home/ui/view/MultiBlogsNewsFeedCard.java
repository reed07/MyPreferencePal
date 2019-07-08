package com.myfitnesspal.feature.home.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.model.MfpBlogDailySummary;
import com.myfitnesspal.feature.home.model.MfpNewsFeedBlogImageEntry;
import com.myfitnesspal.feature.home.model.MfpNewsFeedLinkDesc;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MultiBlogsNewsFeedCard extends BlogNewsFeedCardBase {
    @BindView(2131362934)
    ViewGroup blogsContainer;
    @BindView(2131363943)
    TextView timestampTextView;

    public MultiBlogsNewsFeedCard(ViewGroup viewGroup, NavigationHelper navigationHelper, Lazy<NewsFeedAnalyticsHelper> lazy) {
        super(R.layout.blog_in_newsfeed_swipeview, viewGroup, navigationHelper, lazy);
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) newsFeedItem;
        MfpBlogDailySummary mfpBlogDailySummary = (MfpBlogDailySummary) mfpNewsFeedActivityEntry.getEntryData();
        List posts = mfpBlogDailySummary != null ? mfpBlogDailySummary.getPosts() : null;
        setTimestamp(mfpNewsFeedActivityEntry.getCreatedAt());
        setBlogPosts(posts, mfpNewsFeedActivityEntry.getId());
    }

    public void setTimestamp(Date date) {
        this.timestampTextView.setText(String.format(this.context.getString(R.string.blog_posts_from), new Object[]{getBlogDisplayDate(date)}));
    }

    public void setBlogPosts(List<MfpNewsFeedBlogImageEntry> list, final String str) {
        LayoutInflater from = LayoutInflater.from(this.context);
        this.blogsContainer.removeAllViews();
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            for (int i = 0; i < list.size(); i++) {
                MfpNewsFeedBlogImageEntry mfpNewsFeedBlogImageEntry = (MfpNewsFeedBlogImageEntry) list.get(i);
                View inflate = from.inflate(R.layout.blog_in_newsfeed_item, this.blogsContainer, false);
                ImageView imageView = (ImageView) ViewUtils.findById(inflate, R.id.ivBlogThumbnail);
                TextView textView = (TextView) ViewUtils.findById(inflate, R.id.tvBlogTitle);
                final MfpNewsFeedLinkDesc link = mfpNewsFeedBlogImageEntry.getLink();
                Glide.with(this.context).load(getBestFitImageUrl(mfpNewsFeedBlogImageEntry.getThumbnail())).apply(new RequestOptions().fitCenter().centerCrop()).into(imageView);
                if (link != null) {
                    inflate.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            MultiBlogsNewsFeedCard.this.handleBlogItemClick(link.getWebUrl());
                            ((NewsFeedAnalyticsHelper) MultiBlogsNewsFeedCard.this.newsFeedAnalyticsHelper.get()).reportMultiBlogItemClicked(str);
                        }
                    });
                    textView.setText(link.getText());
                }
                this.blogsContainer.addView(inflate);
            }
        }
    }
}
