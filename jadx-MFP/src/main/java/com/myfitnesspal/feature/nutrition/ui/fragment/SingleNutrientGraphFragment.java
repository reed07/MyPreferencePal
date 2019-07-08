package com.myfitnesspal.feature.nutrition.ui.fragment;

import android.view.ViewGroup;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl.Attributes;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl.EventId;
import java.util.Date;

public class SingleNutrientGraphFragment extends FoodListSingleNutrientFragmentBase {
    /* access modifiers changed from: protected */
    public boolean addScrollViewAsParent() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldReportAnalyticsEvent() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void addMiniFoodListView(ViewGroup viewGroup, Date date) {
        addMiniFoodListView(viewGroup, date, this.nutrientIndex);
    }

    public String getAnalyticsEventId() {
        return EventId.getNutrientGraphEventId(this.nutrientIndex);
    }

    /* access modifiers changed from: protected */
    public String getAttribute() {
        return Attributes.getChartAttribute(isWeekly());
    }
}
