package com.myfitnesspal.feature.nutrition.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import javax.inject.Inject;

public class FoodListsActivity extends FoodListSingleNutrientActivityBase {
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;

    /* access modifiers changed from: protected */
    public String getChartType() {
        return Types.FOOD_LISTS;
    }

    public static Intent newStartIntent(Context context, NutrientEntry nutrientEntry, String str, int i) {
        return new Intent(context, FoodListsActivity.class).putExtra(Extras.NUTRIENT, nutrientEntry).putExtra(Extras.EVENT_ID, str).putExtra(Extras.MACRO_INDEX, i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public String getTitle(NutrientEntry nutrientEntry) {
        return String.format(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getNutrientString(LocalizedStrings.HIGHEST_IN, nutrientEntry.getNutrientIndex(), this.userEnergyService), new Object[]{nutrientEntry.getFieldLabel()});
    }
}
