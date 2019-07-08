package com.myfitnesspal.feature.home.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ImageReportingActivity_ViewBinding implements Unbinder {
    private ImageReportingActivity target;

    @UiThread
    public ImageReportingActivity_ViewBinding(ImageReportingActivity imageReportingActivity) {
        this(imageReportingActivity, imageReportingActivity.getWindow().getDecorView());
    }

    @UiThread
    public ImageReportingActivity_ViewBinding(ImageReportingActivity imageReportingActivity, View view) {
        this.target = imageReportingActivity;
        imageReportingActivity.tvFlaggingSexuallyExplicit = Utils.findRequiredView(view, R.id.tv_flagging_sexually_explicit, "field 'tvFlaggingSexuallyExplicit'");
        imageReportingActivity.tvFlaggingHateViolence = Utils.findRequiredView(view, R.id.tv_flagging_hate_violence, "field 'tvFlaggingHateViolence'");
        imageReportingActivity.tvFlaggingSpamAds = Utils.findRequiredView(view, R.id.tv_flagging_spam_ads, "field 'tvFlaggingSpamAds'");
        imageReportingActivity.tvFlaggingDontLike = Utils.findRequiredView(view, R.id.tv_flagging_dont_like, "field 'tvFlaggingDontLike'");
        imageReportingActivity.tvFlaggingNotFood = Utils.findRequiredView(view, R.id.tv_flagging_not_food, "field 'tvFlaggingNotFood'");
    }

    @CallSuper
    public void unbind() {
        ImageReportingActivity imageReportingActivity = this.target;
        if (imageReportingActivity != null) {
            this.target = null;
            imageReportingActivity.tvFlaggingSexuallyExplicit = null;
            imageReportingActivity.tvFlaggingHateViolence = null;
            imageReportingActivity.tvFlaggingSpamAds = null;
            imageReportingActivity.tvFlaggingDontLike = null;
            imageReportingActivity.tvFlaggingNotFood = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
