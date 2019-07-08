package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import javax.inject.Inject;

public class BarcodeMultiAddMixin extends FoodEditorMixin {
    @Inject
    Lazy<MultiAddFoodHelper> multiAddHelper;

    public BarcodeMultiAddMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view);
        component().inject(this);
    }

    public void saveItemToTarget() {
        addFoodToMultiAdd(getFood());
    }

    public void saveItemToTarget(MfpFood mfpFood) {
        addFoodToMultiAdd(mfpFood);
    }

    private void addFoodToMultiAdd(MfpFood mfpFood) {
        FoodEntry foodV2ToFakeFoodEntry = foodV2ToFakeFoodEntry(mfpFood);
        ((MultiAddFoodHelper) this.multiAddHelper.get()).addExternalItem(foodV2ToFakeFoodEntry, Builder.fromFood(foodV2ToFakeFoodEntry.getFood()).servingSizeIndex(getSelectedServingSizeIndex()).source(getSource()).requestId(this.requestId).build());
        Bundle bundle = new Bundle();
        bundle.putParcelable("food", mfpFood);
        onItemSaved(mfpFood, -1, bundle);
    }
}
