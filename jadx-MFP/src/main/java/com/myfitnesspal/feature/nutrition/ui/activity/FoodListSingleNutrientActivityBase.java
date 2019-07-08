package com.myfitnesspal.feature.nutrition.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.ui.fragment.GraphViewFragment;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.uacf.core.util.ExtrasUtils;

public abstract class FoodListSingleNutrientActivityBase extends NutritionPremiumActivityBase {
    /* access modifiers changed from: protected */
    public abstract String getChartType();

    /* access modifiers changed from: protected */
    public abstract String getTitle(NutrientEntry nutrientEntry);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.nutrient_graph_activity);
        NutrientEntry nutrientEntry = (NutrientEntry) ExtrasUtils.getParcelable(getIntent(), Extras.NUTRIENT, NutrientEntry.class.getClassLoader());
        String string = ExtrasUtils.getString(getIntent(), Extras.EVENT_ID);
        int i = ExtrasUtils.getInt(getIntent(), Extras.MACRO_INDEX, -1);
        setTitle(getTitle(nutrientEntry));
        String chartType = getChartType();
        this.currentGraphViewFragment = (GraphViewFragment) getSupportFragmentManager().findFragmentByTag(chartType);
        if (this.currentGraphViewFragment == null) {
            this.currentGraphViewFragment = GraphViewFragment.newInstance(chartType, nutrientEntry, string, i);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.fragment_placeholder, this.currentGraphViewFragment, chartType);
            beginTransaction.commit();
        }
    }
}
