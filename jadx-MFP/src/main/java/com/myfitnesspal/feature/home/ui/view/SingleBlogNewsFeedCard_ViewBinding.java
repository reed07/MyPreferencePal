package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SingleBlogNewsFeedCard_ViewBinding implements Unbinder {
    private SingleBlogNewsFeedCard target;

    @UiThread
    public SingleBlogNewsFeedCard_ViewBinding(SingleBlogNewsFeedCard singleBlogNewsFeedCard, View view) {
        this.target = singleBlogNewsFeedCard;
        singleBlogNewsFeedCard.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'titleTextView'", TextView.class);
        singleBlogNewsFeedCard.timestampTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.timestamp, "field 'timestampTextView'", TextView.class);
        singleBlogNewsFeedCard.blogLinkTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.blog_link, "field 'blogLinkTextView'", TextView.class);
        singleBlogNewsFeedCard.descriptionTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.description, "field 'descriptionTextView'", TextView.class);
        singleBlogNewsFeedCard.blogImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.blog_image, "field 'blogImageView'", ImageView.class);
        singleBlogNewsFeedCard.loadingView = Utils.findRequiredView(view, R.id.loading, "field 'loadingView'");
    }

    @CallSuper
    public void unbind() {
        SingleBlogNewsFeedCard singleBlogNewsFeedCard = this.target;
        if (singleBlogNewsFeedCard != null) {
            this.target = null;
            singleBlogNewsFeedCard.titleTextView = null;
            singleBlogNewsFeedCard.timestampTextView = null;
            singleBlogNewsFeedCard.blogLinkTextView = null;
            singleBlogNewsFeedCard.descriptionTextView = null;
            singleBlogNewsFeedCard.blogImageView = null;
            singleBlogNewsFeedCard.loadingView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
