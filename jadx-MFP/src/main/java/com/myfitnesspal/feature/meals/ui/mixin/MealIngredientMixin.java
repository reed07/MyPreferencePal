package com.myfitnesspal.feature.meals.ui.mixin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixin;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixinBase;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class MealIngredientMixin extends FoodEditorMixin {
    @Inject
    Lazy<MultiAddFoodHelper> multiAddHelper;

    public MealIngredientMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view);
        component().inject(this);
    }

    public void renderView() {
        super.renderView();
        ViewUtils.setGone(this.mealRowView);
        ViewUtils.setGone(this.dateRowView);
        ViewUtils.setGone(this.timestampRowView);
        if (isEditingMealIngredient()) {
            this.activity.setTitle(R.string.edit_food);
            this.foodFeedbackOptionsMixin.showReportFood(false);
        }
    }

    public void saveItemToTarget() {
        saveFoodForMealFood(getFood());
    }

    public void saveItemToTarget(MfpFood mfpFood) {
        saveFoodForMealFood(mfpFood);
    }

    private void saveFoodForMealFood(MfpFood mfpFood) {
        Bundle bundle = new Bundle();
        FoodEntry foodEntryFromMfpFood = getFoodEntryFromMfpFood(mfpFood, getSelectedServingSizeIndex(), getSelectedServingsAmount());
        if (!((MultiAddFoodHelper) this.multiAddHelper.get()).isMultiAddModeOn() || isEditingMealIngredient()) {
            int intValue = BundleUtils.getInt(FoodEditorMixinBase.EXTRA_MEAL_INGREDIENT_INDEX, Integer.valueOf(Integer.MIN_VALUE), this.intent.getExtras()).intValue();
            if (intValue != Integer.MIN_VALUE) {
                bundle.putInt(FoodEditorMixinBase.EXTRA_MEAL_INGREDIENT_INDEX, intValue);
            }
        } else {
            ((MultiAddFoodHelper) this.multiAddHelper.get()).addExternalItem(foodEntryFromMfpFood, null);
        }
        bundle.putParcelable("food", mfpFood);
        bundle.putParcelable("food_entry", foodEntryFromMfpFood);
        onItemSaved(mfpFood, -1, bundle);
    }

    private boolean isEditingMealIngredient() {
        return BundleUtils.getBoolean(FoodEditorMixinBase.EXTRA_IS_EDITING_MEAL_INGREDIENT, Boolean.valueOf(false), this.intent.getExtras()).booleanValue();
    }
}
