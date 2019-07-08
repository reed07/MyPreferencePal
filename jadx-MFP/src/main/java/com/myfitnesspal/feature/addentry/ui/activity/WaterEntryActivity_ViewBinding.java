package com.myfitnesspal.feature.addentry.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;

public class WaterEntryActivity_ViewBinding implements Unbinder {
    private WaterEntryActivity target;

    @UiThread
    public WaterEntryActivity_ViewBinding(WaterEntryActivity waterEntryActivity) {
        this(waterEntryActivity, waterEntryActivity.getWindow().getDecorView());
    }

    @UiThread
    public WaterEntryActivity_ViewBinding(WaterEntryActivity waterEntryActivity, View view) {
        this.target = waterEntryActivity;
        waterEntryActivity.sponsoredWaterBrandingContainer = Utils.findRequiredView(view, R.id.sponsored_water_branding_container, "field 'sponsoredWaterBrandingContainer'");
        waterEntryActivity.ivSponsoredWater = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_sponsored_water, "field 'ivSponsoredWater'", ImageView.class);
        waterEntryActivity.ivBroughtToYouBuy = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_brought_to_you, "field 'ivBroughtToYouBuy'", ImageView.class);
        waterEntryActivity.quickPicksContainer = Utils.findRequiredView(view, R.id.quick_picks_container, "field 'quickPicksContainer'");
        waterEntryActivity.changeUnit = Utils.findRequiredView(view, R.id.change_units_container, "field 'changeUnit'");
        waterEntryActivity.waterCupsSpinner = Utils.findRequiredView(view, R.id.water_cups_spinner, "field 'waterCupsSpinner'");
        waterEntryActivity.genericBottles = Utils.findRequiredView(view, R.id.generic_bottles, "field 'genericBottles'");
        waterEntryActivity.totalWaterToday = Utils.findRequiredView(view, R.id.total_water_today, "field 'totalWaterToday'");
        waterEntryActivity.waterValue = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.water_value, "field 'waterValue'", CustomLocalizedNumberEditText.class);
        waterEntryActivity.unit = (TextView) Utils.findRequiredViewAsType(view, R.id.unit, "field 'unit'", TextView.class);
        waterEntryActivity.quickPick1 = (Button) Utils.findRequiredViewAsType(view, R.id.quick_pick_1, "field 'quickPick1'", Button.class);
        waterEntryActivity.quickPick2 = (Button) Utils.findRequiredViewAsType(view, R.id.quick_pick_2, "field 'quickPick2'", Button.class);
        waterEntryActivity.quickPick3 = (Button) Utils.findRequiredViewAsType(view, R.id.quick_pick_3, "field 'quickPick3'", Button.class);
        waterEntryActivity.waterInsight = (TextView) Utils.findRequiredViewAsType(view, R.id.water_insight, "field 'waterInsight'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        WaterEntryActivity waterEntryActivity = this.target;
        if (waterEntryActivity != null) {
            this.target = null;
            waterEntryActivity.sponsoredWaterBrandingContainer = null;
            waterEntryActivity.ivSponsoredWater = null;
            waterEntryActivity.ivBroughtToYouBuy = null;
            waterEntryActivity.quickPicksContainer = null;
            waterEntryActivity.changeUnit = null;
            waterEntryActivity.waterCupsSpinner = null;
            waterEntryActivity.genericBottles = null;
            waterEntryActivity.totalWaterToday = null;
            waterEntryActivity.waterValue = null;
            waterEntryActivity.unit = null;
            waterEntryActivity.quickPick1 = null;
            waterEntryActivity.quickPick2 = null;
            waterEntryActivity.quickPick3 = null;
            waterEntryActivity.waterInsight = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
