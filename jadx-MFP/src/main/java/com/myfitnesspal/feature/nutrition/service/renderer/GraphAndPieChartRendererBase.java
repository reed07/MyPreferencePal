package com.myfitnesspal.feature.nutrition.service.renderer;

import android.content.Context;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.service.ProgressReport;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import dagger.Lazy;
import java.util.Date;

public abstract class GraphAndPieChartRendererBase extends CoreChartRendererBase {
    /* access modifiers changed from: protected */
    public abstract String getChartType();

    /* access modifiers changed from: protected */
    public abstract Function2<DiaryDay, MfpDailyGoal> getDailyRenderChartLogic(ViewGroup viewGroup, String str, int i);

    /* access modifiers changed from: protected */
    public int getSpinnerContainerId() {
        return R.id.chart_spinner_container;
    }

    /* access modifiers changed from: protected */
    public abstract Function1<ProgressReport> getWeeklyRenderChartLogic(ViewGroup viewGroup, Date date, int i);

    protected GraphAndPieChartRendererBase(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        super(context, lazy);
    }

    /* access modifiers changed from: protected */
    public void constructDailyChart(ViewGroup viewGroup, Date date, String str, int i, int i2) {
        getNutritionGraphService().renderDailyChart(date, getDailyRenderChartLogic(viewGroup, str, i));
    }

    /* access modifiers changed from: protected */
    public void constructWeeklyChart(ViewGroup viewGroup, Date date, int i, int i2) {
        getNutritionGraphService().renderWeeklyChart(this.context, date, getChartType(), i, getWeeklyRenderChartLogic(viewGroup, date, i));
    }
}
