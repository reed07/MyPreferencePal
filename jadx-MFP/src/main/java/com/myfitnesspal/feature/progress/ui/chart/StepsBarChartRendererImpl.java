package com.myfitnesspal.feature.progress.ui.chart;

import android.content.Context;
import android.view.ViewGroup;
import com.myfitnesspal.feature.nutrition.service.CoreRendererBase;
import com.myfitnesspal.shared.ui.view.StepsBarChart;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class StepsBarChartRendererImpl extends CoreRendererBase implements StepsBarChartRenderer {
    private StepsBarChart chart;

    public StepsBarChartRendererImpl(Context context) {
        super(context);
    }

    public StepsBarChart renderStepsBarChart(Map<Date, Integer> map, int i, int i2, ViewGroup viewGroup) {
        reset();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Date date : map.keySet()) {
            arrayList.add(date);
            arrayList2.add(map.get(date));
        }
        float max = ((float) Math.max(i, i2)) + 1000.0f;
        float f = max <= 5000.0f ? 500.0f : max <= 11000.0f ? 1000.0f : max <= 18000.0f ? 2000.0f : 5000.0f;
        StepsBarChart stepsBarChart = new StepsBarChart(this.activity, viewGroup, arrayList, arrayList2, (double) (max - ((float) i)), f);
        this.chart = stepsBarChart;
        return this.chart;
    }

    public void reset() {
        StepsBarChart stepsBarChart = this.chart;
        if (stepsBarChart != null) {
            stepsBarChart.destroy();
            this.chart = null;
        }
    }
}
