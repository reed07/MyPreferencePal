package com.myfitnesspal.feature.nutrition.ui.fragment;

import android.view.ViewGroup;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl.Attributes;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl.EventId;
import java.util.Date;

public class GraphsMacroViewFragment extends GraphViewFragment {
    /* access modifiers changed from: protected */
    public boolean addScrollViewAsParent() {
        return true;
    }

    public String getAnalyticsEventId() {
        return EventId.GRAPH_MACROS;
    }

    /* access modifiers changed from: protected */
    public void addMiniFoodListView(ViewGroup viewGroup, Date date) {
        ViewGroup viewGroup2 = viewGroup;
        Date date2 = date;
        addMiniFoodListView(viewGroup2, date2, 9, 0, true);
        addMiniFoodListView(viewGroup, date, 1, 1, false);
        addMiniFoodListView(viewGroup2, date2, 12, 2, false);
    }

    /* access modifiers changed from: protected */
    public String getAttribute() {
        return Attributes.getChartAttribute(isWeekly());
    }
}
