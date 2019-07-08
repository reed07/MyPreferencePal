package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ViewFoodActivity_ViewBinding implements Unbinder {
    private ViewFoodActivity target;

    @UiThread
    public ViewFoodActivity_ViewBinding(ViewFoodActivity viewFoodActivity) {
        this(viewFoodActivity, viewFoodActivity.getWindow().getDecorView());
    }

    @UiThread
    public ViewFoodActivity_ViewBinding(ViewFoodActivity viewFoodActivity, View view) {
        this.target = viewFoodActivity;
        viewFoodActivity.viewTxtBrand = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtBrand, "field 'viewTxtBrand'", TextView.class);
        viewFoodActivity.viewTxtDescription = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtDescription, "field 'viewTxtDescription'", TextView.class);
        viewFoodActivity.viewTxtServingSize = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtServingSize, "field 'viewTxtServingSize'", TextView.class);
        viewFoodActivity.viewTxtServingsPerContainer = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtServingsPerContainer, "field 'viewTxtServingsPerContainer'", TextView.class);
        viewFoodActivity.viewTxtCalories = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtCalories, "field 'viewTxtCalories'", TextView.class);
        viewFoodActivity.viewTxtTotalFat = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtTotalFat, "field 'viewTxtTotalFat'", TextView.class);
        viewFoodActivity.viewTxtSaturatedFat = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtSaturatedFat, "field 'viewTxtSaturatedFat'", TextView.class);
        viewFoodActivity.viewTxtPolyunsaturatedFat = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtPolyunsaturatedFat, "field 'viewTxtPolyunsaturatedFat'", TextView.class);
        viewFoodActivity.viewTxtMonounsaturatedFat = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtMonounsaturatedFat, "field 'viewTxtMonounsaturatedFat'", TextView.class);
        viewFoodActivity.viewTxtTransFat = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtTransFat, "field 'viewTxtTransFat'", TextView.class);
        viewFoodActivity.viewTxtCholesterol = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtCholesterol, "field 'viewTxtCholesterol'", TextView.class);
        viewFoodActivity.viewTxtSodium = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtSodium, "field 'viewTxtSodium'", TextView.class);
        viewFoodActivity.viewTxtPotassium = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtPotassium, "field 'viewTxtPotassium'", TextView.class);
        viewFoodActivity.viewTxtTotalCarbohydrates = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtTotalCarbohydrates, "field 'viewTxtTotalCarbohydrates'", TextView.class);
        viewFoodActivity.viewTxtDietaryFibers = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtDietaryFibers, "field 'viewTxtDietaryFibers'", TextView.class);
        viewFoodActivity.viewTxtSugars = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtSugars, "field 'viewTxtSugars'", TextView.class);
        viewFoodActivity.viewTxtProtein = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtProtein, "field 'viewTxtProtein'", TextView.class);
        viewFoodActivity.viewTxtVitaminA = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtVitaminA, "field 'viewTxtVitaminA'", TextView.class);
        viewFoodActivity.viewTxtVitaminC = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtVitaminC, "field 'viewTxtVitaminC'", TextView.class);
        viewFoodActivity.viewTxtCalcium = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtCalcium, "field 'viewTxtCalcium'", TextView.class);
        viewFoodActivity.viewTxtIron = (TextView) Utils.findRequiredViewAsType(view, R.id.viewTxtIron, "field 'viewTxtIron'", TextView.class);
        viewFoodActivity.energyTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.calories, "field 'energyTxt'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ViewFoodActivity viewFoodActivity = this.target;
        if (viewFoodActivity != null) {
            this.target = null;
            viewFoodActivity.viewTxtBrand = null;
            viewFoodActivity.viewTxtDescription = null;
            viewFoodActivity.viewTxtServingSize = null;
            viewFoodActivity.viewTxtServingsPerContainer = null;
            viewFoodActivity.viewTxtCalories = null;
            viewFoodActivity.viewTxtTotalFat = null;
            viewFoodActivity.viewTxtSaturatedFat = null;
            viewFoodActivity.viewTxtPolyunsaturatedFat = null;
            viewFoodActivity.viewTxtMonounsaturatedFat = null;
            viewFoodActivity.viewTxtTransFat = null;
            viewFoodActivity.viewTxtCholesterol = null;
            viewFoodActivity.viewTxtSodium = null;
            viewFoodActivity.viewTxtPotassium = null;
            viewFoodActivity.viewTxtTotalCarbohydrates = null;
            viewFoodActivity.viewTxtDietaryFibers = null;
            viewFoodActivity.viewTxtSugars = null;
            viewFoodActivity.viewTxtProtein = null;
            viewFoodActivity.viewTxtVitaminA = null;
            viewFoodActivity.viewTxtVitaminC = null;
            viewFoodActivity.viewTxtCalcium = null;
            viewFoodActivity.viewTxtIron = null;
            viewFoodActivity.energyTxt = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
