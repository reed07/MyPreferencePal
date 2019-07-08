package com.myfitnesspal.shared.ui.fragment.impl;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.fragment.NutritionFactsFragmentBase_ViewBinding;
import com.myfitnesspal.shared.ui.view.CustomMacroProgressBar;
import com.myfitnesspal.shared.ui.view.MacroDetails;
import com.myfitnesspal.shared.ui.view.MacroWheelWithText;

public class NewNutritionFactsFragment_ViewBinding extends NutritionFactsFragmentBase_ViewBinding {
    private NewNutritionFactsFragment target;

    @UiThread
    public NewNutritionFactsFragment_ViewBinding(NewNutritionFactsFragment newNutritionFactsFragment, View view) {
        super(newNutritionFactsFragment, view);
        this.target = newNutritionFactsFragment;
        newNutritionFactsFragment.nutritionFactsPrem = view.findViewById(R.id.nutritionFactsPremium);
        newNutritionFactsFragment.detailsCarbs = (MacroDetails) Utils.findOptionalViewAsType(view, R.id.details_carbs, "field 'detailsCarbs'", MacroDetails.class);
        newNutritionFactsFragment.detailsFat = (MacroDetails) Utils.findOptionalViewAsType(view, R.id.details_fat, "field 'detailsFat'", MacroDetails.class);
        newNutritionFactsFragment.detailsProtein = (MacroDetails) Utils.findOptionalViewAsType(view, R.id.details_protein, "field 'detailsProtein'", MacroDetails.class);
        newNutritionFactsFragment.macroWheelWithText = (MacroWheelWithText) Utils.findOptionalViewAsType(view, R.id.macroWheelWithText, "field 'macroWheelWithText'", MacroWheelWithText.class);
        newNutritionFactsFragment.percentDailyGoalsContainer = view.findViewById(R.id.percent_daily_goals_container);
        newNutritionFactsFragment.goPremiumCta = (TextView) Utils.findOptionalViewAsType(view, R.id.go_premium_cta, "field 'goPremiumCta'", TextView.class);
        newNutritionFactsFragment.caloriesProgress = (CustomMacroProgressBar) Utils.findOptionalViewAsType(view, R.id.progress_calories, "field 'caloriesProgress'", CustomMacroProgressBar.class);
        newNutritionFactsFragment.carbsProgress = (CustomMacroProgressBar) Utils.findOptionalViewAsType(view, R.id.progress_carbs, "field 'carbsProgress'", CustomMacroProgressBar.class);
        newNutritionFactsFragment.fatProgress = (CustomMacroProgressBar) Utils.findOptionalViewAsType(view, R.id.progress_fat, "field 'fatProgress'", CustomMacroProgressBar.class);
        newNutritionFactsFragment.proteinProgress = (CustomMacroProgressBar) Utils.findOptionalViewAsType(view, R.id.progress_protein, "field 'proteinProgress'", CustomMacroProgressBar.class);
    }

    public void unbind() {
        NewNutritionFactsFragment newNutritionFactsFragment = this.target;
        if (newNutritionFactsFragment != null) {
            this.target = null;
            newNutritionFactsFragment.nutritionFactsPrem = null;
            newNutritionFactsFragment.detailsCarbs = null;
            newNutritionFactsFragment.detailsFat = null;
            newNutritionFactsFragment.detailsProtein = null;
            newNutritionFactsFragment.macroWheelWithText = null;
            newNutritionFactsFragment.percentDailyGoalsContainer = null;
            newNutritionFactsFragment.goPremiumCta = null;
            newNutritionFactsFragment.caloriesProgress = null;
            newNutritionFactsFragment.carbsProgress = null;
            newNutritionFactsFragment.fatProgress = null;
            newNutritionFactsFragment.proteinProgress = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
