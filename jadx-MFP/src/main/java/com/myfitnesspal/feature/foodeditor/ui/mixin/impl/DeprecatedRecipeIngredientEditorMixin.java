package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.BundleUtils;

@Deprecated
public class DeprecatedRecipeIngredientEditorMixin extends FoodEditorMixin {
    public static final String EXTRA_EVENT_SOURCE = "extra_event_source";
    public static final String EXTRA_MATCHED_INGREDIENT = "extra_matched_ingredient";
    public static final String EXTRA_ORIGINAL_ITEM_TEXT = "extra_original_item_text";
    public static final String EXTRA_UNMATCHED_INGREDIENT = "extra_unmatched_ingredient";
    private final String eventSource;
    private final MfpIngredient matchedIngredient;
    private final String originalItemText;
    private final MfpIngredientItem unmatchedIngredient;

    public DeprecatedRecipeIngredientEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view);
        Bundle extras = intent.getExtras();
        this.matchedIngredient = (MfpIngredient) BundleUtils.getParcelable(extras, EXTRA_MATCHED_INGREDIENT, MfpIngredient.class.getClassLoader());
        this.unmatchedIngredient = (MfpIngredientItem) BundleUtils.getParcelable(extras, EXTRA_UNMATCHED_INGREDIENT, MfpIngredientItem.class.getClassLoader());
        this.eventSource = BundleUtils.getString(extras, EXTRA_EVENT_SOURCE);
        this.originalItemText = BundleUtils.getString(extras, EXTRA_ORIGINAL_ITEM_TEXT);
        initDisplayValues(bundle);
    }

    public void saveItemToTarget() {
        writeResultAndNotifyListener();
    }

    public void saveItemToTarget(MfpFood mfpFood) {
        writeResultAndNotifyListener();
    }

    private void initDisplayValues(Bundle bundle) {
        if (bundle == null) {
            super.setSelectedServing(this.matchedIngredient.getServingSize(), this.matchedIngredient.getServings().floatValue());
        }
        MfpNutritionalContents nutritionalContents = this.matchedIngredient.getNutritionalContents();
        if (nutritionalContents == null) {
            nutritionalContents = this.matchedIngredient.getFood().getNutritionalContents();
        }
        super.setNutritionalContents(nutritionalContents);
    }

    private void writeResultAndNotifyListener() {
        this.matchedIngredient.setServings(Double.valueOf((double) getSelectedServingsAmount()));
        this.matchedIngredient.setServingSize(getSelectedServingSize());
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_MATCHED_INGREDIENT, this.matchedIngredient);
        bundle.putParcelable(EXTRA_UNMATCHED_INGREDIENT, this.unmatchedIngredient);
        bundle.putString(EXTRA_ORIGINAL_ITEM_TEXT, this.originalItemText);
        bundle.putString(EXTRA_EVENT_SOURCE, this.eventSource);
        onItemSaved(this.matchedIngredient.getFood(), -1, bundle);
    }
}
