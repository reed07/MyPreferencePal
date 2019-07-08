package com.myfitnesspal.feature.addentry.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;

public class AddFoodSummaryViewV2_ViewBinding implements Unbinder {
    private AddFoodSummaryViewV2 target;

    @UiThread
    public AddFoodSummaryViewV2_ViewBinding(AddFoodSummaryViewV2 addFoodSummaryViewV2) {
        this(addFoodSummaryViewV2, addFoodSummaryViewV2.getWindow().getDecorView());
    }

    @UiThread
    public AddFoodSummaryViewV2_ViewBinding(AddFoodSummaryViewV2 addFoodSummaryViewV2, View view) {
        this.target = addFoodSummaryViewV2;
        addFoodSummaryViewV2.txtFoodName = (TextView) Utils.findRequiredViewAsType(view, R.id.txtFoodName, "field 'txtFoodName'", TextView.class);
        addFoodSummaryViewV2.txtNoOfServings = (TextView) Utils.findRequiredViewAsType(view, R.id.txtNoOfServings, "field 'txtNoOfServings'", TextView.class);
        addFoodSummaryViewV2.txtServingSize = (TextView) Utils.findRequiredViewAsType(view, R.id.txtServingSize, "field 'txtServingSize'", TextView.class);
        addFoodSummaryViewV2.verifiedBadge = Utils.findRequiredView(view, R.id.verified_badge, "field 'verifiedBadge'");
        addFoodSummaryViewV2.barcodeSection = Utils.findRequiredView(view, R.id.barcodefooter, "field 'barcodeSection'");
        addFoodSummaryViewV2.noOfServingsTableRow = Utils.findRequiredView(view, R.id.noOfServingsTableRow, "field 'noOfServingsTableRow'");
        addFoodSummaryViewV2.txtMeal = (TextView) Utils.findRequiredViewAsType(view, R.id.txtMeal, "field 'txtMeal'", TextView.class);
        addFoodSummaryViewV2.servingSizeTableRow = Utils.findRequiredView(view, R.id.servingSizeTableRow, "field 'servingSizeTableRow'");
        addFoodSummaryViewV2.mealTableRow = Utils.findRequiredView(view, R.id.mealTableRow, "field 'mealTableRow'");
        addFoodSummaryViewV2.insightContainer = Utils.findRequiredView(view, R.id.insight_container, "field 'insightContainer'");
        addFoodSummaryViewV2.dateTableRow = Utils.findRequiredView(view, R.id.dateTableRow, "field 'dateTableRow'");
        addFoodSummaryViewV2.txtDate = (TextView) Utils.findRequiredViewAsType(view, R.id.txtDate, "field 'txtDate'", TextView.class);
        addFoodSummaryViewV2.timestampRowView = (TimestampRowView) Utils.findRequiredViewAsType(view, R.id.add_food_timestamp_layout, "field 'timestampRowView'", TimestampRowView.class);
        addFoodSummaryViewV2.addFoodSummaryRoot = Utils.findRequiredView(view, R.id.add_food_summary_root, "field 'addFoodSummaryRoot'");
    }

    @CallSuper
    public void unbind() {
        AddFoodSummaryViewV2 addFoodSummaryViewV2 = this.target;
        if (addFoodSummaryViewV2 != null) {
            this.target = null;
            addFoodSummaryViewV2.txtFoodName = null;
            addFoodSummaryViewV2.txtNoOfServings = null;
            addFoodSummaryViewV2.txtServingSize = null;
            addFoodSummaryViewV2.verifiedBadge = null;
            addFoodSummaryViewV2.barcodeSection = null;
            addFoodSummaryViewV2.noOfServingsTableRow = null;
            addFoodSummaryViewV2.txtMeal = null;
            addFoodSummaryViewV2.servingSizeTableRow = null;
            addFoodSummaryViewV2.mealTableRow = null;
            addFoodSummaryViewV2.insightContainer = null;
            addFoodSummaryViewV2.dateTableRow = null;
            addFoodSummaryViewV2.txtDate = null;
            addFoodSummaryViewV2.timestampRowView = null;
            addFoodSummaryViewV2.addFoodSummaryRoot = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
