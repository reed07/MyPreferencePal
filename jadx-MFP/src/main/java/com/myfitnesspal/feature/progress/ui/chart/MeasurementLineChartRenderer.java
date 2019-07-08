package com.myfitnesspal.feature.progress.ui.chart;

import android.view.ViewGroup;
import com.myfitnesspal.feature.nutrition.ui.view.CustomLineChart;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.RequiresActivityContext;
import java.util.List;

public interface MeasurementLineChartRenderer extends RequiresActivityContext {
    CustomLineChart renderLineChart(int i, List<ProgressEntryViewModel> list, ViewGroup viewGroup, Session session);

    void reset();
}
