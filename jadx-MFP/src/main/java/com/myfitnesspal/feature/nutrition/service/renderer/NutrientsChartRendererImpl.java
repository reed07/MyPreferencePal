package com.myfitnesspal.feature.nutrition.service.renderer;

import android.content.Context;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.service.NutritionDetailsDelegate;
import com.uacf.core.util.Function1;
import dagger.Lazy;
import java.util.Date;
import java.util.List;

public class NutrientsChartRendererImpl extends CoreChartRendererBase {
    /* access modifiers changed from: private */
    public NutritionDetailsDelegate nutritionDetailsDelegate;

    /* access modifiers changed from: protected */
    public int getSpinnerContainerId() {
        return R.id.chart_spinner_container;
    }

    public NutrientsChartRendererImpl(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        super(context, lazy);
        this.nutritionDetailsDelegate = new NutritionDetailsDelegate(context, true);
    }

    /* access modifiers changed from: protected */
    public void constructDailyChart(ViewGroup viewGroup, Date date, String str, int i, int i2) {
        constructChart(viewGroup, date, false);
    }

    /* access modifiers changed from: protected */
    public void constructWeeklyChart(ViewGroup viewGroup, Date date, int i, int i2) {
        constructChart(viewGroup, date, true);
    }

    private void constructChart(final ViewGroup viewGroup, Date date, final boolean z) {
        removeSpinnerContainerBackground(viewGroup);
        getNutritionGraphService().renderNutritionSummary(date, this.nutritionDetailsDelegate, z, new Function1<List<NutrientEntry>>() {
            public void execute(List<NutrientEntry> list) throws RuntimeException {
                NutrientsChartRendererImpl.this.hideSpinnerContainer(viewGroup);
                viewGroup.addView(NutrientsChartRendererImpl.this.nutritionDetailsDelegate.buildNutrientLayout(list, z, false));
            }
        });
    }
}
