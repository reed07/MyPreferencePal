package com.myfitnesspal.shared.ui.fragment.impl;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;

import com.btothefifth.patched.R;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.ui.fragment.NutritionFactsFragmentBase;

import lanchon.dexpatcher.annotation.*;

@DexEdit(defaultAction = DexAction.IGNORE, contentOnly = true)
public class NewNutritionFactsFragment extends NutritionFactsFragmentBase {
    @DexReplace
    public int getLayoutResId() {
        return R.layout.new_nutrition_facts_us;
    }

    @DexWrap
    private void setMacros(float f, double d, double d2, double d3) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("mypreferencepal", Activity.MODE_PRIVATE);
        boolean showNetCarbs = preferences.getBoolean("show_net_carbs",false);
        if (showNetCarbs)
        {
            double netcarbs = getNutritionalContents().calculateNetCarbs().doubleValue() > 0.0d ? getNutritionalContents().calculateNetCarbs().doubleValue() * getScale() : 0.0d;
            setMacros(f,netcarbs,d2,d3);
        }
        else
            setMacros(f,d,d2,d3);
    }
}
