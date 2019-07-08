package com.myfitnesspal.feature.nutrition.ui.fragment;

import android.os.Bundle;
import android.view.ViewGroup;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl.Attributes;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.uacf.core.util.BundleUtils;
import java.util.Date;

public class FoodListsFragment extends FoodListSingleNutrientFragmentBase {
    private String eventId;
    private int macroIndex;

    /* access modifiers changed from: protected */
    public void addMiniFoodListView(ViewGroup viewGroup, Date date) {
    }

    /* access modifiers changed from: protected */
    public boolean addScrollViewAsParent() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldReportAnalyticsEvent() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.eventId = BundleUtils.getString(arguments, Extras.EVENT_ID);
        this.macroIndex = BundleUtils.getInt(arguments, Extras.MACRO_INDEX, -1);
    }

    public String getAnalyticsEventId() {
        return this.eventId;
    }

    /* access modifiers changed from: protected */
    public String getAttribute() {
        return Attributes.getFoodListAttribute(this.isWeekly, this.macroIndex);
    }
}
