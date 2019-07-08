package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class MultiAddFoodEditorMixin extends FoodEditorMixin {
    public MultiAddFoodEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view, FoodEditorExtras foodEditorExtras) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view, foodEditorExtras);
    }

    public void saveItemToTarget() {
        addFoodToMultiAdd(getFood());
    }

    public void saveItemToTarget(MfpFood mfpFood) {
        addFoodToMultiAdd(mfpFood);
    }

    private void addFoodToMultiAdd(MfpFood mfpFood) {
        FoodEntry foodV2ToFakeFoodEntry = foodV2ToFakeFoodEntry(mfpFood);
        FoodV2Logging build = Builder.fromFood(foodV2ToFakeFoodEntry.getFood()).servingSizeIndex(getSelectedServingSizeIndex()).requestId(this.requestId).listType(this.foodEditorExtras != null ? this.foodEditorExtras.getFoodEditorAnalyticsExtras().getListType() : "").build();
        Bundle bundle = new Bundle();
        bundle.putParcelable("food_entry", foodV2ToFakeFoodEntry);
        bundle.putParcelable(Extras.FOOD_V2_LOGGING, build);
        bundle.putParcelableArrayList(Extras.PAIRED_FOODS, getPairedFoodEntries());
        this.onItemSavedListener.onItemSavedIgnoreStartIntent(-1, bundle);
    }
}
