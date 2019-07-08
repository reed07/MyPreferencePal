package com.myfitnesspal.feature.addentry.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;

public class AddFoodSummaryView_ViewBinding implements Unbinder {
    private AddFoodSummaryView target;

    @UiThread
    public AddFoodSummaryView_ViewBinding(AddFoodSummaryView addFoodSummaryView) {
        this(addFoodSummaryView, addFoodSummaryView.getWindow().getDecorView());
    }

    @UiThread
    public AddFoodSummaryView_ViewBinding(AddFoodSummaryView addFoodSummaryView, View view) {
        this.target = addFoodSummaryView;
        addFoodSummaryView.txtFoodName = (TextView) Utils.findRequiredViewAsType(view, R.id.txtFoodName, "field 'txtFoodName'", TextView.class);
        addFoodSummaryView.txtNoOfServings = (TextView) Utils.findRequiredViewAsType(view, R.id.txtNoOfServings, "field 'txtNoOfServings'", TextView.class);
        addFoodSummaryView.txtServingSize = (TextView) Utils.findRequiredViewAsType(view, R.id.txtServingSize, "field 'txtServingSize'", TextView.class);
        addFoodSummaryView.noOfServingsTableRow = Utils.findRequiredView(view, R.id.noOfServingsTableRow, "field 'noOfServingsTableRow'");
        addFoodSummaryView.txtMeal = (TextView) Utils.findRequiredViewAsType(view, R.id.txtMeal, "field 'txtMeal'", TextView.class);
        addFoodSummaryView.servingSizeTableRow = Utils.findRequiredView(view, R.id.servingSizeTableRow, "field 'servingSizeTableRow'");
        addFoodSummaryView.mealTableRow = Utils.findRequiredView(view, R.id.mealTableRow, "field 'mealTableRow'");
        addFoodSummaryView.insightContainer = Utils.findRequiredView(view, R.id.insight_container, "field 'insightContainer'");
        addFoodSummaryView.verifiedBadge = Utils.findRequiredView(view, R.id.verified_badge, "field 'verifiedBadge'");
        addFoodSummaryView.dateTableRow = Utils.findRequiredView(view, R.id.dateTableRow, "field 'dateTableRow'");
        addFoodSummaryView.txtDate = (TextView) Utils.findRequiredViewAsType(view, R.id.txtDate, "field 'txtDate'", TextView.class);
        addFoodSummaryView.timestampRowView = (TimestampRowView) Utils.findRequiredViewAsType(view, R.id.add_food_timestamp_layout, "field 'timestampRowView'", TimestampRowView.class);
        addFoodSummaryView.addFoodSummaryRoot = Utils.findRequiredView(view, R.id.add_food_summary_root, "field 'addFoodSummaryRoot'");
    }

    @CallSuper
    public void unbind() {
        AddFoodSummaryView addFoodSummaryView = this.target;
        if (addFoodSummaryView != null) {
            this.target = null;
            addFoodSummaryView.txtFoodName = null;
            addFoodSummaryView.txtNoOfServings = null;
            addFoodSummaryView.txtServingSize = null;
            addFoodSummaryView.noOfServingsTableRow = null;
            addFoodSummaryView.txtMeal = null;
            addFoodSummaryView.servingSizeTableRow = null;
            addFoodSummaryView.mealTableRow = null;
            addFoodSummaryView.insightContainer = null;
            addFoodSummaryView.verifiedBadge = null;
            addFoodSummaryView.dateTableRow = null;
            addFoodSummaryView.txtDate = null;
            addFoodSummaryView.timestampRowView = null;
            addFoodSummaryView.addFoodSummaryRoot = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
