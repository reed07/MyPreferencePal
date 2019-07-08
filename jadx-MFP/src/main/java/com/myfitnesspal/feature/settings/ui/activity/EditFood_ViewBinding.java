package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;

public class EditFood_ViewBinding implements Unbinder {
    private EditFood target;

    @UiThread
    public EditFood_ViewBinding(EditFood editFood) {
        this(editFood, editFood.getWindow().getDecorView());
    }

    @UiThread
    public EditFood_ViewBinding(EditFood editFood, View view) {
        this.target = editFood;
        editFood.editTxtBrand = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtBrand, "field 'editTxtBrand'", EditText.class);
        editFood.editTxtDescription = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtDescription, "field 'editTxtDescription'", EditText.class);
        editFood.editTxtServingSize = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtServingSize, "field 'editTxtServingSize'", EditText.class);
        editFood.editTxtServingsPerContainer = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtServingsPerContainer, "field 'editTxtServingsPerContainer'", CustomLocalizedNumberEditText.class);
        editFood.editTxtCalories = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtCalories, "field 'editTxtCalories'", CustomLocalizedNumberEditText.class);
        editFood.editTxtTotalFat = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtTotalFat, "field 'editTxtTotalFat'", CustomLocalizedNumberEditText.class);
        editFood.editTxtSaturatedFat = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtSaturatedFat, "field 'editTxtSaturatedFat'", CustomLocalizedNumberEditText.class);
        editFood.editTxtPolyunsaturatedFat = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtPolyunsaturatedFat, "field 'editTxtPolyunsaturatedFat'", CustomLocalizedNumberEditText.class);
        editFood.editTxtMonounsaturatedFat = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtMonounsaturatedFat, "field 'editTxtMonounsaturatedFat'", CustomLocalizedNumberEditText.class);
        editFood.editTxtTransFat = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtTransFat, "field 'editTxtTransFat'", CustomLocalizedNumberEditText.class);
        editFood.editTxtCholesterol = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtCholesterol, "field 'editTxtCholesterol'", CustomLocalizedNumberEditText.class);
        editFood.editTxtSodium = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtSodium, "field 'editTxtSodium'", CustomLocalizedNumberEditText.class);
        editFood.editTxtPotassium = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtPotassium, "field 'editTxtPotassium'", CustomLocalizedNumberEditText.class);
        editFood.editTxtTotalCarbohydrates = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtTotalCarbohydrates, "field 'editTxtTotalCarbohydrates'", CustomLocalizedNumberEditText.class);
        editFood.editTxtDietaryFibers = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtDietaryFibers, "field 'editTxtDietaryFibers'", CustomLocalizedNumberEditText.class);
        editFood.editTxtSugars = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtSugars, "field 'editTxtSugars'", CustomLocalizedNumberEditText.class);
        editFood.editTxtProtein = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtProtein, "field 'editTxtProtein'", CustomLocalizedNumberEditText.class);
        editFood.editTxtVitaminA = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtVitaminA, "field 'editTxtVitaminA'", CustomLocalizedNumberEditText.class);
        editFood.editTxtVitaminC = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtVitaminC, "field 'editTxtVitaminC'", CustomLocalizedNumberEditText.class);
        editFood.editTxtCalcium = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtCalcium, "field 'editTxtCalcium'", CustomLocalizedNumberEditText.class);
        editFood.editTxtIron = (CustomLocalizedNumberEditText) Utils.findRequiredViewAsType(view, R.id.editTxtIron, "field 'editTxtIron'", CustomLocalizedNumberEditText.class);
        editFood.energyTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.calories, "field 'energyTxt'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        EditFood editFood = this.target;
        if (editFood != null) {
            this.target = null;
            editFood.editTxtBrand = null;
            editFood.editTxtDescription = null;
            editFood.editTxtServingSize = null;
            editFood.editTxtServingsPerContainer = null;
            editFood.editTxtCalories = null;
            editFood.editTxtTotalFat = null;
            editFood.editTxtSaturatedFat = null;
            editFood.editTxtPolyunsaturatedFat = null;
            editFood.editTxtMonounsaturatedFat = null;
            editFood.editTxtTransFat = null;
            editFood.editTxtCholesterol = null;
            editFood.editTxtSodium = null;
            editFood.editTxtPotassium = null;
            editFood.editTxtTotalCarbohydrates = null;
            editFood.editTxtDietaryFibers = null;
            editFood.editTxtSugars = null;
            editFood.editTxtProtein = null;
            editFood.editTxtVitaminA = null;
            editFood.editTxtVitaminC = null;
            editFood.editTxtCalcium = null;
            editFood.editTxtIron = null;
            editFood.energyTxt = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
