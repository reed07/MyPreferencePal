package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditFoodGroupServingsDialogFragment;
import com.myfitnesspal.feature.foodeditor.ui.dialog.EditFoodGroupServingsDialogFragment.OnServingSizeSelectedListener;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.restaurantlogging.model.FoodGroup;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class FoodGroupEditorMixin extends FoodEditorMixin {
    private static final String SERVING_SIZE_DIALOG_TAG = EditFoodGroupServingsDialogFragment.class.getName();
    /* access modifiers changed from: private */
    public final FoodGroup foodGroup;
    private OnServingSizeSelectedListener onServingSizeSelectedListener = new OnServingSizeSelectedListener() {
        public void onServingSizeSelected(float f, int i) {
            FoodGroupEditorMixin.this.foodGroup.setSelectedFoodIndex(i);
            MfpFood selectedFood = FoodGroupEditorMixin.this.foodGroup.getSelectedFood();
            selectedFood.setSelectedServingAmount(f);
            FoodGroupEditorMixin.this.setSelectedServingAmount(f);
            FoodGroupEditorMixin foodGroupEditorMixin = FoodGroupEditorMixin.this;
            foodGroupEditorMixin.setSelectedServingSize(foodGroupEditorMixin.getFirstServingSize());
            FoodGroupEditorMixin.this.setFood(selectedFood);
        }
    };

    public FoodGroupEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view, FoodGroup foodGroup2) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view, foodGroup2.getSelectedFood());
        this.foodGroup = foodGroup2;
        setSelectedServingSize(getFirstServingSize());
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!str.equals(SERVING_SIZE_DIALOG_TAG)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((EditFoodGroupServingsDialogFragment) dialogFragment).setOnServingSizeSelectedListener(this.onServingSizeSelectedListener);
        return true;
    }

    public void saveItemToTarget() {
        MfpFood selectedFood = this.foodGroup.getSelectedFood();
        saveFoodToTarget(selectedFood, 0, getSelectedServingsAmount(), null);
        getAnalytics().reportFoodAddedToDiary(((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getFlowId(), selectedFood, getMealName(), getBarcode(), getSource(), getDate(), this.timestampPickerMixin.isFeatureEnabled() ? TimeValue.Companion.fromTimestampOption(this.timestampPickerMixin.getSelectedOption()) : null, ((FoodFeedbackAnalyticsHelper) this.foodFeedbackAnalyticsHelper.get()).getCorrectedBy(this.foodFeedbackOptionsMixin.getFoodFromFeedback()), this.searchVersion);
        onItemSaved(selectedFood);
    }

    public void onServingsCountClick() {
        showEditFoodGroupServingsDialog(true);
    }

    public void onServingSizeClick() {
        showEditFoodGroupServingsDialog(false);
    }

    public int getCurrentBasedOnFoodIndex() {
        return this.foodGroup.getSelectedFoodIndex();
    }

    /* access modifiers changed from: private */
    public MfpServingSize getFirstServingSize() {
        return (MfpServingSize) this.foodGroup.getSelectedFood().getServingSizes().get(0);
    }

    private void showEditFoodGroupServingsDialog(boolean z) {
        EditFoodGroupServingsDialogFragment newInstance = EditFoodGroupServingsDialogFragment.newInstance(this.foodGroup, getCurrentBasedOnFoodIndex(), getSelectedServingsAmount(), z);
        newInstance.setOnServingSizeSelectedListener(this.onServingSizeSelectedListener);
        this.activity.showDialogFragment(newInstance, SERVING_SIZE_DIALOG_TAG);
    }
}
