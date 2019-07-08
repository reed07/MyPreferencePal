package com.myfitnesspal.feature.progress.ui.chart;

import android.view.ViewGroup;
import com.myfitnesspal.shared.ui.view.StepsBarChart;
import com.myfitnesspal.shared.util.RequiresActivityContext;
import java.util.Date;
import java.util.Map;

public interface StepsBarChartRenderer extends RequiresActivityContext {
    StepsBarChart renderStepsBarChart(Map<Date, Integer> map, int i, int i2, ViewGroup viewGroup);

    void reset();
}
