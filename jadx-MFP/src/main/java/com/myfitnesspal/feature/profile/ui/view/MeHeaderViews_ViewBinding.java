package com.myfitnesspal.feature.profile.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class MeHeaderViews_ViewBinding implements Unbinder {
    private MeHeaderViews target;

    @UiThread
    public MeHeaderViews_ViewBinding(MeHeaderViews meHeaderViews, View view) {
        this.target = meHeaderViews;
        meHeaderViews.toolbarTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
        meHeaderViews.appbarTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.toolbar_username, "field 'appbarTitle'", TextView.class);
        meHeaderViews.toolbarImage = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.toolbar_image, "field 'toolbarImage'", MfpImageView.class);
        meHeaderViews.appbarImage = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.large_profile_image, "field 'appbarImage'", MfpImageView.class);
        meHeaderViews.appbarLocation = (TextView) Utils.findRequiredViewAsType(view, R.id.toolbar_location, "field 'appbarLocation'", TextView.class);
        meHeaderViews.appbarStreak = (TextView) Utils.findRequiredViewAsType(view, R.id.toolbar_streak, "field 'appbarStreak'", TextView.class);
        meHeaderViews.premiumButton = Utils.findRequiredView(view, R.id.toolbar_go_premium, "field 'premiumButton'");
        meHeaderViews.toolbarContainer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.large_toolbar_container, "field 'toolbarContainer'", RelativeLayout.class);
        meHeaderViews.toolbarLayout = (AppBarLayout) Utils.findRequiredViewAsType(view, R.id.toolbar_container, "field 'toolbarLayout'", AppBarLayout.class);
    }

    @CallSuper
    public void unbind() {
        MeHeaderViews meHeaderViews = this.target;
        if (meHeaderViews != null) {
            this.target = null;
            meHeaderViews.toolbarTitle = null;
            meHeaderViews.appbarTitle = null;
            meHeaderViews.toolbarImage = null;
            meHeaderViews.appbarImage = null;
            meHeaderViews.appbarLocation = null;
            meHeaderViews.appbarStreak = null;
            meHeaderViews.premiumButton = null;
            meHeaderViews.toolbarContainer = null;
            meHeaderViews.toolbarLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
