package com.myfitnesspal.feature.goals.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EditMacroGoalsByGramsFragment_ViewBinding implements Unbinder {
    private EditMacroGoalsByGramsFragment target;

    @UiThread
    public EditMacroGoalsByGramsFragment_ViewBinding(EditMacroGoalsByGramsFragment editMacroGoalsByGramsFragment, View view) {
        this.target = editMacroGoalsByGramsFragment;
        editMacroGoalsByGramsFragment.currentCarbIntake = (TextView) Utils.findRequiredViewAsType(view, R.id.current_carb_intake, "field 'currentCarbIntake'", TextView.class);
        editMacroGoalsByGramsFragment.currentFatIntake = (TextView) Utils.findRequiredViewAsType(view, R.id.current_fat_intake, "field 'currentFatIntake'", TextView.class);
        editMacroGoalsByGramsFragment.currentProteinIntake = (TextView) Utils.findRequiredViewAsType(view, R.id.current_protein_intake, "field 'currentProteinIntake'", TextView.class);
        editMacroGoalsByGramsFragment.totalEnergy = (TextView) Utils.findRequiredViewAsType(view, R.id.macronutrient_percent_total, "field 'totalEnergy'", TextView.class);
        editMacroGoalsByGramsFragment.macroNutrientMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.macronutrients_message, "field 'macroNutrientMessage'", TextView.class);
        editMacroGoalsByGramsFragment.energyTypeText = (TextView) Utils.findRequiredViewAsType(view, R.id.percent_total, "field 'energyTypeText'", TextView.class);
        editMacroGoalsByGramsFragment.proteinPicker = (NumberPicker) Utils.findRequiredViewAsType(view, R.id.protein_picker, "field 'proteinPicker'", NumberPicker.class);
        editMacroGoalsByGramsFragment.carbsPicker = (NumberPicker) Utils.findRequiredViewAsType(view, R.id.carbs_picker, "field 'carbsPicker'", NumberPicker.class);
        editMacroGoalsByGramsFragment.fatPicker = (NumberPicker) Utils.findRequiredViewAsType(view, R.id.fat_picker, "field 'fatPicker'", NumberPicker.class);
    }

    @CallSuper
    public void unbind() {
        EditMacroGoalsByGramsFragment editMacroGoalsByGramsFragment = this.target;
        if (editMacroGoalsByGramsFragment != null) {
            this.target = null;
            editMacroGoalsByGramsFragment.currentCarbIntake = null;
            editMacroGoalsByGramsFragment.currentFatIntake = null;
            editMacroGoalsByGramsFragment.currentProteinIntake = null;
            editMacroGoalsByGramsFragment.totalEnergy = null;
            editMacroGoalsByGramsFragment.macroNutrientMessage = null;
            editMacroGoalsByGramsFragment.energyTypeText = null;
            editMacroGoalsByGramsFragment.proteinPicker = null;
            editMacroGoalsByGramsFragment.carbsPicker = null;
            editMacroGoalsByGramsFragment.fatPicker = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
