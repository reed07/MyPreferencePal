package com.myfitnesspal.feature.diary.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class CompleteDiaryActivity_ViewBinding implements Unbinder {
    private CompleteDiaryActivity target;

    @UiThread
    public CompleteDiaryActivity_ViewBinding(CompleteDiaryActivity completeDiaryActivity) {
        this(completeDiaryActivity, completeDiaryActivity.getWindow().getDecorView());
    }

    @UiThread
    public CompleteDiaryActivity_ViewBinding(CompleteDiaryActivity completeDiaryActivity, View view) {
        this.target = completeDiaryActivity;
        completeDiaryActivity.mainContainer = Utils.findRequiredView(view, R.id.main_container, "field 'mainContainer'");
        completeDiaryActivity.projectedWeightText = (TextView) Utils.findRequiredViewAsType(view, R.id.projected_weight_text, "field 'projectedWeightText'", TextView.class);
        completeDiaryActivity.getPremiumContainer = Utils.findRequiredView(view, R.id.get_premium_container, "field 'getPremiumContainer'");
        completeDiaryActivity.adsContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.medium_ads_container, "field 'adsContainer'", ViewGroup.class);
        completeDiaryActivity.disclaimerText = (TextView) Utils.findRequiredViewAsType(view, R.id.disclaimer_text, "field 'disclaimerText'", TextView.class);
        completeDiaryActivity.eatingDisorderText = (TextView) Utils.findRequiredViewAsType(view, R.id.eating_disorder_text, "field 'eatingDisorderText'", TextView.class);
        completeDiaryActivity.blueBanner = Utils.findRequiredView(view, R.id.blue_banner, "field 'blueBanner'");
    }

    @CallSuper
    public void unbind() {
        CompleteDiaryActivity completeDiaryActivity = this.target;
        if (completeDiaryActivity != null) {
            this.target = null;
            completeDiaryActivity.mainContainer = null;
            completeDiaryActivity.projectedWeightText = null;
            completeDiaryActivity.getPremiumContainer = null;
            completeDiaryActivity.adsContainer = null;
            completeDiaryActivity.disclaimerText = null;
            completeDiaryActivity.eatingDisorderText = null;
            completeDiaryActivity.blueBanner = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
