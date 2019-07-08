package com.myfitnesspal.feature.nutrition.ui.activity;

import android.content.Context;
import android.content.Intent;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;

public class NutrientGraphActivity extends FoodListSingleNutrientActivityBase {
    /* access modifiers changed from: protected */
    public String getChartType() {
        return Types.SINGLE_NUTRIENT;
    }

    public static Intent newStartIntent(Context context, NutrientEntry nutrientEntry) {
        return new Intent(context, NutrientGraphActivity.class).putExtra(Extras.NUTRIENT, nutrientEntry);
    }

    /* access modifiers changed from: protected */
    public String getTitle(NutrientEntry nutrientEntry) {
        return nutrientEntry.getFieldLabel();
    }
}
