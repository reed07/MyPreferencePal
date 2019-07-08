package com.myfitnesspal.feature.goals.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class CalorieAdjustmentIntro_ViewBinding implements Unbinder {
    private CalorieAdjustmentIntro target;

    @UiThread
    public CalorieAdjustmentIntro_ViewBinding(CalorieAdjustmentIntro calorieAdjustmentIntro) {
        this(calorieAdjustmentIntro, calorieAdjustmentIntro.getWindow().getDecorView());
    }

    @UiThread
    public CalorieAdjustmentIntro_ViewBinding(CalorieAdjustmentIntro calorieAdjustmentIntro, View view) {
        this.target = calorieAdjustmentIntro;
        calorieAdjustmentIntro.appIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.partner_app_icon, "field 'appIcon'", ImageView.class);
        calorieAdjustmentIntro.adjustmentIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.calorie_icon, "field 'adjustmentIcon'", ImageView.class);
        calorieAdjustmentIntro.adjustmentContainer = Utils.findRequiredView(view, R.id.adjustment_container, "field 'adjustmentContainer'");
        calorieAdjustmentIntro.caloriesEarnedLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.calories_earned_label, "field 'caloriesEarnedLabel'", TextView.class);
        calorieAdjustmentIntro.caloriesEarned = (TextView) Utils.findRequiredViewAsType(view, R.id.calories_earned, "field 'caloriesEarned'", TextView.class);
        calorieAdjustmentIntro.partnerAppLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.partner_app_label, "field 'partnerAppLabel'", TextView.class);
        calorieAdjustmentIntro.negativeAdjustmentLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.negative_calorie_label, "field 'negativeAdjustmentLabel'", TextView.class);
        calorieAdjustmentIntro.learnMore = (Button) Utils.findRequiredViewAsType(view, R.id.learn_more, "field 'learnMore'", Button.class);
    }

    @CallSuper
    public void unbind() {
        CalorieAdjustmentIntro calorieAdjustmentIntro = this.target;
        if (calorieAdjustmentIntro != null) {
            this.target = null;
            calorieAdjustmentIntro.appIcon = null;
            calorieAdjustmentIntro.adjustmentIcon = null;
            calorieAdjustmentIntro.adjustmentContainer = null;
            calorieAdjustmentIntro.caloriesEarnedLabel = null;
            calorieAdjustmentIntro.caloriesEarned = null;
            calorieAdjustmentIntro.partnerAppLabel = null;
            calorieAdjustmentIntro.negativeAdjustmentLabel = null;
            calorieAdjustmentIntro.learnMore = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
