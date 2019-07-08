package com.myfitnesspal.feature.nutrition.ui.fragment;

import android.os.Bundle;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.uacf.core.util.BundleUtils;

public abstract class FoodListSingleNutrientFragmentBase extends GraphViewFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        NutrientEntry nutrientEntry = (NutrientEntry) BundleUtils.getParcelable(getArguments(), Extras.NUTRIENT, NutrientEntry.class.getClassLoader());
        this.nutrientIndex = nutrientEntry.getNutrientIndex();
        this.nutrientName = nutrientEntry.getFieldLabel();
    }
}
