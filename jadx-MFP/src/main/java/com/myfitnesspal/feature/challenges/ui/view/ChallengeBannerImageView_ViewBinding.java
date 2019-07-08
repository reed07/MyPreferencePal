package com.myfitnesspal.feature.challenges.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ChallengeBannerImageView_ViewBinding implements Unbinder {
    private ChallengeBannerImageView target;

    @UiThread
    public ChallengeBannerImageView_ViewBinding(ChallengeBannerImageView challengeBannerImageView) {
        this(challengeBannerImageView, challengeBannerImageView);
    }

    @UiThread
    public ChallengeBannerImageView_ViewBinding(ChallengeBannerImageView challengeBannerImageView, View view) {
        this.target = challengeBannerImageView;
        challengeBannerImageView.bannerImage = (ResizableImageView) Utils.findRequiredViewAsType(view, R.id.ivChallengeBanner, "field 'bannerImage'", ResizableImageView.class);
        challengeBannerImageView.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        ChallengeBannerImageView challengeBannerImageView = this.target;
        if (challengeBannerImageView != null) {
            this.target = null;
            challengeBannerImageView.bannerImage = null;
            challengeBannerImageView.progressBar = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
