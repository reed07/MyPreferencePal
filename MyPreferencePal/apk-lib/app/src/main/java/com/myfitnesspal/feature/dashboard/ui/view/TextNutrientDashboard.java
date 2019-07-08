package com.myfitnesspal.feature.dashboard.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.model.Nutrient;
import com.myfitnesspal.shared.model.v1.DiaryDay;

import lanchon.dexpatcher.annotation.DexAction;
import lanchon.dexpatcher.annotation.DexAdd;
import lanchon.dexpatcher.annotation.DexEdit;
import lanchon.dexpatcher.annotation.DexIgnore;
import lanchon.dexpatcher.annotation.DexWrap;
import dagger.Lazy;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.Strings;

import static android.content.Context.MODE_PRIVATE;


@DexEdit(defaultAction = DexAction.IGNORE, contentOnly = true)
public class TextNutrientDashboard {
    @DexIgnore
    protected final Lazy<NutrientGoalsUtil> nutritionalGoalsUtil;
    private static final String DEFAULT_VALUE = "-";

    @DexIgnore
    public TextNutrientDashboard() {
        nutritionalGoalsUtil = null;
    }

    @DexWrap
    private void setNetRemainingValuesForMacro(TextView textView, TextView textView2, MfpDailyGoal mfpDailyGoal, DiaryDay diaryDay, Nutrient nutrient) {
        SharedPreferences preferences = textView.getContext().getSharedPreferences("mypreferencepal",MODE_PRIVATE);
        if (diaryDay!=null && mfpDailyGoal!= null && nutrient.equals(Nutrient.Carbohydrates) && preferences.getBoolean("show_net_carbs",false))
        {
            int nutrientIndex = nutrient.getNutrientIndex();
            int carbsLeft = Integer.valueOf(Math.round(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, nutrientIndex) - diaryDay.amountOfNutrientConsumed(nutrientIndex)));
            carbsLeft += diaryDay.amountOfNutrientConsumed(Nutrient.Fiber.getNutrientIndex());
            carbsLeft += diaryDay.amountOfNutrientConsumed(19);
            String str;
            if (mfpDailyGoal == null || diaryDay == null) {
                str = DEFAULT_VALUE;
            } else {
                str = Strings.toString(carbsLeft);
            }
            textView.setText(str);
            TextViewUtils.setText(textView2, formatProgressBarLabelWithUnits(nutrient));
        }
        else
            setNetRemainingValuesForMacro(textView,textView2,mfpDailyGoal,diaryDay,nutrient);


    }

    @DexIgnore
    public String formatProgressBarLabelWithUnits(Nutrient nutrient) {
        return null;
    }
}
