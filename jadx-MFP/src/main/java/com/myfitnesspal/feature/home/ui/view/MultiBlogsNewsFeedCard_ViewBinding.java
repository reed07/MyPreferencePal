package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MultiBlogsNewsFeedCard_ViewBinding implements Unbinder {
    private MultiBlogsNewsFeedCard target;

    @UiThread
    public MultiBlogsNewsFeedCard_ViewBinding(MultiBlogsNewsFeedCard multiBlogsNewsFeedCard, View view) {
        this.target = multiBlogsNewsFeedCard;
        multiBlogsNewsFeedCard.timestampTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tvTitleTimestamp, "field 'timestampTextView'", TextView.class);
        multiBlogsNewsFeedCard.blogsContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.llHorizontal, "field 'blogsContainer'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        MultiBlogsNewsFeedCard multiBlogsNewsFeedCard = this.target;
        if (multiBlogsNewsFeedCard != null) {
            this.target = null;
            multiBlogsNewsFeedCard.timestampTextView = null;
            multiBlogsNewsFeedCard.blogsContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
