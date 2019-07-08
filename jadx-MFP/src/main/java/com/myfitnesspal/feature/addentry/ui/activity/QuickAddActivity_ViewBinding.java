package com.myfitnesspal.feature.addentry.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;

public class QuickAddActivity_ViewBinding implements Unbinder {
    private QuickAddActivity target;

    @UiThread
    public QuickAddActivity_ViewBinding(QuickAddActivity quickAddActivity) {
        this(quickAddActivity, quickAddActivity.getWindow().getDecorView());
    }

    @UiThread
    public QuickAddActivity_ViewBinding(QuickAddActivity quickAddActivity, View view) {
        this.target = quickAddActivity;
        quickAddActivity.tvCaloriesLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.tvCaloriesLabel, "field 'tvCaloriesLabel'", TextView.class);
        quickAddActivity.tvQuickCalories = (TextView) Utils.findRequiredViewAsType(view, R.id.tvQuickCalories, "field 'tvQuickCalories'", TextView.class);
        quickAddActivity.tvQuickCarbs = (TextView) Utils.findRequiredViewAsType(view, R.id.tvQuickCarbs, "field 'tvQuickCarbs'", TextView.class);
        quickAddActivity.tvQuickFat = (TextView) Utils.findRequiredViewAsType(view, R.id.tvQuickFat, "field 'tvQuickFat'", TextView.class);
        quickAddActivity.tvQuickProtein = (TextView) Utils.findRequiredViewAsType(view, R.id.tvQuickProtein, "field 'tvQuickProtein'", TextView.class);
        quickAddActivity.tvRecalculateLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.tvRecalculateLabel, "field 'tvRecalculateLabel'", TextView.class);
        quickAddActivity.rlQuickCalories = Utils.findRequiredView(view, R.id.rlQuickCalories, "field 'rlQuickCalories'");
        quickAddActivity.rlQuickCarbs = Utils.findRequiredView(view, R.id.quick_add_carbs_layout, "field 'rlQuickCarbs'");
        quickAddActivity.rlQuickFat = Utils.findRequiredView(view, R.id.quick_add_fat_layout, "field 'rlQuickFat'");
        quickAddActivity.rlQuickProtein = Utils.findRequiredView(view, R.id.quick_add_protein_layout, "field 'rlQuickProtein'");
        quickAddActivity.carbLock = Utils.findRequiredView(view, R.id.carb_lock, "field 'carbLock'");
        quickAddActivity.fatLock = Utils.findRequiredView(view, R.id.fat_lock, "field 'fatLock'");
        quickAddActivity.proteinLock = Utils.findRequiredView(view, R.id.protein_lock, "field 'proteinLock'");
        quickAddActivity.timeLayout = (TimestampRowView) Utils.findRequiredViewAsType(view, R.id.quick_add_time_layout, "field 'timeLayout'", TimestampRowView.class);
    }

    @CallSuper
    public void unbind() {
        QuickAddActivity quickAddActivity = this.target;
        if (quickAddActivity != null) {
            this.target = null;
            quickAddActivity.tvCaloriesLabel = null;
            quickAddActivity.tvQuickCalories = null;
            quickAddActivity.tvQuickCarbs = null;
            quickAddActivity.tvQuickFat = null;
            quickAddActivity.tvQuickProtein = null;
            quickAddActivity.tvRecalculateLabel = null;
            quickAddActivity.rlQuickCalories = null;
            quickAddActivity.rlQuickCarbs = null;
            quickAddActivity.rlQuickFat = null;
            quickAddActivity.rlQuickProtein = null;
            quickAddActivity.carbLock = null;
            quickAddActivity.fatLock = null;
            quickAddActivity.proteinLock = null;
            quickAddActivity.timeLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
