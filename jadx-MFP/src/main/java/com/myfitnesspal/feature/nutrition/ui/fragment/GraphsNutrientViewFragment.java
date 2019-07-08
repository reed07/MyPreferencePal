package com.myfitnesspal.feature.nutrition.ui.fragment;

import android.view.ViewGroup;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl.Attributes;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl.EventId;
import java.util.Date;

public class GraphsNutrientViewFragment extends GraphViewFragment {
    /* access modifiers changed from: protected */
    public void addMiniFoodListView(ViewGroup viewGroup, Date date) {
    }

    /* access modifiers changed from: protected */
    public boolean addScrollViewAsParent() {
        return true;
    }

    public String getAnalyticsEventId() {
        return EventId.GRAPH_NUTRIENTS_SUMMARY;
    }

    /* access modifiers changed from: protected */
    public String getAttribute() {
        return Attributes.getChartAttribute(isWeekly());
    }
}
