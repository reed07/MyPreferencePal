package com.myfitnesspal.feature.home.ui.view;

import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboard.Type;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import dagger.Lazy;
import java.util.Calendar;

public class DailySummaryViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    final Lazy<NutrientDashboardRenderer> nutrientDashboardRenderer;

    public DailySummaryViewHolder(ViewGroup viewGroup, Lazy<NutrientDashboardRenderer> lazy) {
        super(R.layout.home_summary, viewGroup);
        this.nutrientDashboardRenderer = lazy;
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        render();
    }

    private void render() {
        ((NutrientDashboardRenderer) this.nutrientDashboardRenderer.get()).render((MfpActivity) this.context, Type.Home, Calendar.getInstance(), (ViewGroup) getParent(), null);
    }

    public void reset() {
        ((NutrientDashboardRenderer) this.nutrientDashboardRenderer.get()).reset();
        render();
    }

    public void onResume() {
        ((NutrientDashboardRenderer) this.nutrientDashboardRenderer.get()).resume();
    }

    public void onPause() {
        ((NutrientDashboardRenderer) this.nutrientDashboardRenderer.get()).pause();
    }
}
