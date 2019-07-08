package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;

public class FoodEditorMixinBase_ViewBinding implements Unbinder {
    private FoodEditorMixinBase target;

    @UiThread
    public FoodEditorMixinBase_ViewBinding(FoodEditorMixinBase foodEditorMixinBase, View view) {
        this.target = foodEditorMixinBase;
        foodEditorMixinBase.foodNameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.txtFoodName, "field 'foodNameTextView'", TextView.class);
        foodEditorMixinBase.foodDescRowView = Utils.findRequiredView(view, R.id.food_desc_container, "field 'foodDescRowView'");
        foodEditorMixinBase.foodDescriptionTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.txtFoodDescription, "field 'foodDescriptionTextView'", TextView.class);
        foodEditorMixinBase.verifiedBadge = (ImageView) Utils.findRequiredViewAsType(view, R.id.verified_badge, "field 'verifiedBadge'", ImageView.class);
        foodEditorMixinBase.dateRowView = Utils.findRequiredView(view, R.id.dateTableRow, "field 'dateRowView'");
        foodEditorMixinBase.mealRowView = Utils.findRequiredView(view, R.id.mealTableRow, "field 'mealRowView'");
        foodEditorMixinBase.mealNameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.txtMeal, "field 'mealNameTextView'", TextView.class);
        foodEditorMixinBase.dateTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.txtDate, "field 'dateTextView'", TextView.class);
        foodEditorMixinBase.noOfServingsRowView = Utils.findRequiredView(view, R.id.noOfServingsTableRow, "field 'noOfServingsRowView'");
        foodEditorMixinBase.noOfServingsTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.txtNoOfServings, "field 'noOfServingsTextView'", TextView.class);
        foodEditorMixinBase.servingSizeRowView = Utils.findRequiredView(view, R.id.servingSizeTableRow, "field 'servingSizeRowView'");
        foodEditorMixinBase.servingSizeTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.txtServingSize, "field 'servingSizeTextView'", TextView.class);
        foodEditorMixinBase.timestampRowView = (TimestampRowView) Utils.findRequiredViewAsType(view, R.id.timestamp_picker_row, "field 'timestampRowView'", TimestampRowView.class);
        foodEditorMixinBase.basedOnView = Utils.findRequiredView(view, R.id.food_based_on_container, "field 'basedOnView'");
        foodEditorMixinBase.basedOnTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.food_based_on_text, "field 'basedOnTextView'", TextView.class);
        foodEditorMixinBase.inputMealNameContainer = Utils.findRequiredView(view, R.id.input_meal_name_container, "field 'inputMealNameContainer'");
        foodEditorMixinBase.inputMealName = (EditText) Utils.findRequiredViewAsType(view, R.id.input_meal_name, "field 'inputMealName'", EditText.class);
        foodEditorMixinBase.ctaContainer = Utils.findRequiredView(view, R.id.cta_container, "field 'ctaContainer'");
        foodEditorMixinBase.ctaText = (TextView) Utils.findRequiredViewAsType(view, R.id.cta_text, "field 'ctaText'", TextView.class);
        foodEditorMixinBase.ctaButton = (TextView) Utils.findRequiredViewAsType(view, R.id.cta_button, "field 'ctaButton'", TextView.class);
        foodEditorMixinBase.mealItemsContainer = Utils.findRequiredView(view, R.id.meal_items_container, "field 'mealItemsContainer'");
        foodEditorMixinBase.mealItemsList = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.meal_items_list, "field 'mealItemsList'", ViewGroup.class);
        foodEditorMixinBase.mealNotesContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.meal_notes_container, "field 'mealNotesContainer'", ViewGroup.class);
        foodEditorMixinBase.fabActionPlus = (FloatingActionButton) Utils.findRequiredViewAsType(view, R.id.fab_action_plus, "field 'fabActionPlus'", FloatingActionButton.class);
        foodEditorMixinBase.headerContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.foodEditorHeaderContainer, "field 'headerContainer'", ViewGroup.class);
        foodEditorMixinBase.insightsContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.insight_container, "field 'insightsContainer'", ViewGroup.class);
        foodEditorMixinBase.sponsoredFoodRow = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.sponsoredFoodRow, "field 'sponsoredFoodRow'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        FoodEditorMixinBase foodEditorMixinBase = this.target;
        if (foodEditorMixinBase != null) {
            this.target = null;
            foodEditorMixinBase.foodNameTextView = null;
            foodEditorMixinBase.foodDescRowView = null;
            foodEditorMixinBase.foodDescriptionTextView = null;
            foodEditorMixinBase.verifiedBadge = null;
            foodEditorMixinBase.dateRowView = null;
            foodEditorMixinBase.mealRowView = null;
            foodEditorMixinBase.mealNameTextView = null;
            foodEditorMixinBase.dateTextView = null;
            foodEditorMixinBase.noOfServingsRowView = null;
            foodEditorMixinBase.noOfServingsTextView = null;
            foodEditorMixinBase.servingSizeRowView = null;
            foodEditorMixinBase.servingSizeTextView = null;
            foodEditorMixinBase.timestampRowView = null;
            foodEditorMixinBase.basedOnView = null;
            foodEditorMixinBase.basedOnTextView = null;
            foodEditorMixinBase.inputMealNameContainer = null;
            foodEditorMixinBase.inputMealName = null;
            foodEditorMixinBase.ctaContainer = null;
            foodEditorMixinBase.ctaText = null;
            foodEditorMixinBase.ctaButton = null;
            foodEditorMixinBase.mealItemsContainer = null;
            foodEditorMixinBase.mealItemsList = null;
            foodEditorMixinBase.mealNotesContainer = null;
            foodEditorMixinBase.fabActionPlus = null;
            foodEditorMixinBase.headerContainer = null;
            foodEditorMixinBase.insightsContainer = null;
            foodEditorMixinBase.sponsoredFoodRow = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
