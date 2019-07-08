package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditNumServingsDialogFragment;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditNumServingsDialogFragment.OnServingSizeSelectedListener;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ViewUtils;

public class RecipeEditorMixin extends FoodEditorMixinBase<MfpRecipe> {
    private static final String SERVING_SIZE_DIALOG_TAG = EditNumServingsDialogFragment.class.getName();
    private OnServingSizeSelectedListener onServingSizeSelectedListener = new OnServingSizeSelectedListener() {
        public void onServingSizeSelected(float f) {
            RecipeEditorMixin.this.recipe.setServings(Double.valueOf((double) f));
            RecipeEditorMixin.this.renderRecipeInfo();
        }
    };
    /* access modifiers changed from: private */
    public final MfpRecipe recipe;

    public RecipeEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view, MfpRecipe mfpRecipe) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view);
        this.recipe = mfpRecipe;
    }

    public float getNutrientScale() {
        return this.recipe.getServings().floatValue();
    }

    public String getFoodItemName() {
        return this.recipe.getName();
    }

    public void renderView() {
        setName(this.recipe.getName());
        ViewUtils.setGone(this.servingSizeRowView);
        renderRecipeInfo();
    }

    /* access modifiers changed from: private */
    public void renderRecipeInfo() {
        renderNutritionDetails(this.recipe.getNutritionalContents());
        this.noOfServingsTextView.setText(NumberUtils.localeStringFromDouble(this.recipe.getServings().doubleValue()));
    }

    private void addFoodToRecipe(MfpRecipe mfpRecipe) {
        MfpFood mfpFood = mfpRecipe.getMfpFood();
        if (mfpFood == null) {
            ((LegacyAlertMixin) this.activity.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(null, getString(R.string.unable_fetch_food_for_recipe), getString(R.string.ok), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    RecipeEditorMixin.this.onItemSaveFailed();
                }
            }, null, null);
        } else {
            saveFoodToTarget(this.recipe, mfpFood);
        }
    }

    public void saveItemToTarget() {
        saveItemToTarget(this.recipe);
    }

    public void saveItemToTarget(MfpRecipe mfpRecipe) {
        addFoodToRecipe(mfpRecipe);
    }

    public void onServingsCountClick() {
        EditNumServingsDialogFragment newInstance = EditNumServingsDialogFragment.newInstance(this.recipe.getServings().floatValue(), true);
        newInstance.setOnServingSizeSelectedListener(this.onServingSizeSelectedListener);
        this.activity.showDialogFragment(newInstance, SERVING_SIZE_DIALOG_TAG);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!SERVING_SIZE_DIALOG_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((EditNumServingsDialogFragment) dialogFragment).setOnServingSizeSelectedListener(this.onServingSizeSelectedListener);
        return true;
    }

    private String getString(int i) {
        return this.activity.getString(i);
    }

    private void saveFoodToTarget(MfpRecipe mfpRecipe, MfpFood mfpFood) {
        TimeValue timeValue = null;
        saveFoodToTarget(mfpFood, 0, mfpRecipe.getServings().floatValue(), null);
        if (this.timestampPickerMixin.isFeatureEnabled()) {
            timeValue = TimeValue.Companion.fromTimestampOption(this.timestampPickerMixin.getSelectedOption());
        }
        getAnalytics().reportRecipeAddedToDiary(getMealName(), getDate(), timeValue);
        onItemSaved(mfpFood);
    }
}
