package com.myfitnesspal.shared.model.v1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.stetho.inspector.protocol.module.Database;
import com.myfitnesspal.feature.nutrition.model.Nutrient;

import java.util.List;

import lanchon.dexpatcher.annotation.DexAction;
import lanchon.dexpatcher.annotation.DexEdit;
import lanchon.dexpatcher.annotation.DexIgnore;
import lanchon.dexpatcher.annotation.DexWrap;

@DexEdit(defaultAction = DexAction.IGNORE, contentOnly = true)
public class DiaryDay {
    @DexIgnore
    public float amountOfNutrientConsumed(int i) {
        return 0.0f;
    }


    @DexWrap
    public float totalNutrientValueForMealName(String str, int i) {
        SharedPreferences preferences = this.getContext().getSharedPreferences("mypreferencepal",Activity.MODE_PRIVATE);
        if (i==9 && preferences.getBoolean("show_net_carbs", false))
            return totalNutrientValueForMealName(str,Nutrient.Carbohydrates.getNutrientIndex())
                    - totalNutrientValueForMealName(str,Nutrient.Fiber.getNutrientIndex())
                    - totalNutrientValueForMealName(str,19);
        else
            return totalNutrientValueForMealName(str,i);
    }



    @DexIgnore
    public Context getContext() {
        return null;
    }
}
