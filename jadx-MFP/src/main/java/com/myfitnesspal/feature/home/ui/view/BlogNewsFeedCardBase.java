package com.myfitnesspal.feature.home.ui.view;

import android.view.ViewGroup;
import com.myfitnesspal.feature.blog.ui.activity.BlogActivity;
import com.myfitnesspal.feature.home.model.MfpNewsFeedBlogThumbnail;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.util.NewsFeedCardUtils;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Date;

public abstract class BlogNewsFeedCardBase extends RecyclerViewHolder<NewsFeedItem> {
    private static final String BLOG_MEDIUM = "app_newsfeed";
    private static final String BLOG_SOURCE = "mfp";
    private final NavigationHelper navigationHelper;
    protected final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;

    public BlogNewsFeedCardBase(int i, ViewGroup viewGroup, NavigationHelper navigationHelper2, Lazy<NewsFeedAnalyticsHelper> lazy) {
        super(i, viewGroup);
        this.navigationHelper = navigationHelper2;
        this.newsFeedAnalyticsHelper = lazy;
    }

    /* access modifiers changed from: protected */
    public void handleBlogItemClick(String str) {
        if (!Strings.isEmpty(str)) {
            this.navigationHelper.withIntent(BlogActivity.newStartIntent(this.navigationHelper.getContext(), str, "mfp", BLOG_MEDIUM)).startActivity();
        }
    }

    /* access modifiers changed from: protected */
    public String getBlogDisplayDate(Date date) {
        return DateTimeUtils.formatHumanReadableTime(this.context, date);
    }

    protected static String getBestFitImageUrl(MfpNewsFeedBlogThumbnail mfpNewsFeedBlogThumbnail) {
        return NewsFeedCardUtils.getBestFitImageUrl(mfpNewsFeedBlogThumbnail.getMainAsset(), mfpNewsFeedBlogThumbnail.getSizes());
    }
}
