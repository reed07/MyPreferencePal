package com.myfitnesspal.feature.nutrition.ui.fragment;

import android.view.ViewGroup;
import com.myfitnesspal.feature.nutrition.event.GraphSubTypeChanged;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl.Attributes;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl.EventId;
import com.squareup.otto.Subscribe;
import java.util.Date;

public class GraphsCalorieViewFragment extends GraphViewFragment {
    /* access modifiers changed from: protected */
    public boolean addScrollViewAsParent() {
        return true;
    }

    public String getAnalyticsEventId() {
        return EventId.GRAPH_CALORIES;
    }

    /* access modifiers changed from: protected */
    public void addMiniFoodListView(ViewGroup viewGroup, Date date) {
        addMiniFoodListView(viewGroup, date, 0);
    }

    /* access modifiers changed from: protected */
    public String getAttribute() {
        return Attributes.getChartAttribute(isWeekly(), getGraphSubType());
    }

    @Subscribe
    public void onGraphSubTypeChangedEvent(GraphSubTypeChanged graphSubTypeChanged) {
        this.coreChartRenderer.setGraphSubType(graphSubTypeChanged.getGraphSubType());
        this.contentPagerAdapter.notifyDataSetChanged();
    }
}
