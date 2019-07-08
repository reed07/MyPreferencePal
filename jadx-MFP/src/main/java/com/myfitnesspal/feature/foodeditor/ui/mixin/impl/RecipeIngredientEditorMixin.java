package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.recipes.ui.activity.SearchRecipeIngredientActivity;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.ViewUtils;

public class RecipeIngredientEditorMixin extends FoodEditorMixin {
    public static final String EXTRA_ORIGINAL_INGREDIENT = "original_ingredient";
    public static final String EXTRA_ORIGINAL_INGREDIENT_ITEM = "original_ingredient_item";
    public static final String EXTRA_SHOW_REPLACE_BUTTON = "show_replace_button";
    public static final String EXTRA_UPDATED_FOOD = "updated_food";
    @BindView(2131362650)
    Button replaceIngredientButton;

    public RecipeIngredientEditorMixin(MfpActivity mfpActivity, OnItemSavedListener onItemSavedListener, Intent intent, Bundle bundle, View view) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view);
        setupReplaceButton();
    }

    public void renderView() {
        super.renderView();
        this.activity.setTitle(showReplaceButton() ? R.string.foodDetails : R.string.addFood);
        this.timestampRowView.setVisibility(8);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 199) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            saveItemToTarget((MfpFood) ExtrasUtils.getParcelable(intent, SearchRecipeIngredientActivity.EXTRA_SELECTED_FOOD, MfpFood.class.getClassLoader()));
        }
    }

    public void saveItemToTarget() {
        saveItemToTarget(getFood());
    }

    public void saveItemToTarget(MfpFood mfpFood) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("original_ingredient_item", getIngredientItemFromIntent());
        bundle.putParcelable("original_ingredient", getIngredientFromIntent());
        bundle.putParcelable(EXTRA_UPDATED_FOOD, mfpFood);
        this.onItemSavedListener.onItemSaved(-1, bundle);
    }

    private void setupReplaceButton() {
        ViewUtils.setVisible(showReplaceButton(), this.replaceIngredientButton);
        this.replaceIngredientButton.setText(R.string.replace_ingredient);
        this.replaceIngredientButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RecipeIngredientEditorMixin.this.activity.getNavigationHelper().withIntent(RecipeIngredientEditorMixin.this.getSearchIntent()).startActivity(RequestCodes.SEARCH_RECIPE_INGREDIENT);
            }
        });
    }

    /* access modifiers changed from: private */
    public Intent getSearchIntent() {
        MfpIngredient ingredientFromIntent = getIngredientFromIntent();
        if (ingredientFromIntent != null) {
            return SearchRecipeIngredientActivity.newStartIntentWithIngredient(this.activity, ingredientFromIntent);
        }
        return SearchRecipeIngredientActivity.newStartIntentWithIngredientItem(this.activity, getIngredientItemFromIntent());
    }

    private boolean showReplaceButton() {
        return ExtrasUtils.getBoolean(this.intent, EXTRA_SHOW_REPLACE_BUTTON);
    }

    private MfpIngredient getIngredientFromIntent() {
        return (MfpIngredient) ExtrasUtils.getParcelable(this.intent, "original_ingredient", MfpIngredient.class.getClassLoader());
    }

    private MfpIngredientItem getIngredientItemFromIntent() {
        return (MfpIngredientItem) ExtrasUtils.getParcelable(this.intent, "original_ingredient_item", MfpIngredientItem.class.getClassLoader());
    }
}
