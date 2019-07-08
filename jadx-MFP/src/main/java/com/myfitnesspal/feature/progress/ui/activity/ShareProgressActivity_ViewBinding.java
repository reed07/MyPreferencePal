package com.myfitnesspal.feature.progress.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ShareProgressActivity_ViewBinding implements Unbinder {
    private ShareProgressActivity target;

    @UiThread
    public ShareProgressActivity_ViewBinding(ShareProgressActivity shareProgressActivity) {
        this(shareProgressActivity, shareProgressActivity.getWindow().getDecorView());
    }

    @UiThread
    public ShareProgressActivity_ViewBinding(ShareProgressActivity shareProgressActivity, View view) {
        this.target = shareProgressActivity;
        shareProgressActivity.progressbar = Utils.findRequiredView(view, R.id.progress, "field 'progressbar'");
        shareProgressActivity.captionSwitchContainer = view.findViewById(R.id.custom_caption_switch_container);
        shareProgressActivity.captionSwitch = (Switch) Utils.findOptionalViewAsType(view, R.id.custom_caption_switch, "field 'captionSwitch'", Switch.class);
        shareProgressActivity.pager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.view_pager, "field 'pager'", ViewPager.class);
        shareProgressActivity.instagram = view.findViewById(R.id.instagram_button);
        shareProgressActivity.instagramDivider = view.findViewById(R.id.instagram_divider);
        shareProgressActivity.facebook = view.findViewById(R.id.facebook_button);
        shareProgressActivity.facebookDivider = view.findViewById(R.id.facebook_divider);
        shareProgressActivity.twitter = view.findViewById(R.id.twitter_button);
        shareProgressActivity.dotsContainer = (LinearLayout) Utils.findOptionalViewAsType(view, R.id.dots_container, "field 'dotsContainer'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        ShareProgressActivity shareProgressActivity = this.target;
        if (shareProgressActivity != null) {
            this.target = null;
            shareProgressActivity.progressbar = null;
            shareProgressActivity.captionSwitchContainer = null;
            shareProgressActivity.captionSwitch = null;
            shareProgressActivity.pager = null;
            shareProgressActivity.instagram = null;
            shareProgressActivity.instagramDivider = null;
            shareProgressActivity.facebook = null;
            shareProgressActivity.facebookDivider = null;
            shareProgressActivity.twitter = null;
            shareProgressActivity.dotsContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
