package com.myfitnesspal.feature.nutrition.service;

import android.view.ViewGroup;
import com.myfitnesspal.shared.util.RequiresActivityContext;
import java.util.Date;

public interface CoreChartRenderer extends RequiresActivityContext {
    void addDailyChart(ViewGroup viewGroup, Date date, String str, int i, int i2);

    void addWeeklyChart(ViewGroup viewGroup, Date date, String str, int i, int i2);

    int getGraphSubType();

    void setGraphSubType(int i);
}
