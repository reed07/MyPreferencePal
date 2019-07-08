package com.myfitnesspal.feature.goals.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EditMacroGoalsByPercentFragment_ViewBinding implements Unbinder {
    private EditMacroGoalsByPercentFragment target;

    @UiThread
    public EditMacroGoalsByPercentFragment_ViewBinding(EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment, View view) {
        this.target = editMacroGoalsByPercentFragment;
        editMacroGoalsByPercentFragment.carbValueLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.current_carb_intake, "field 'carbValueLabel'", TextView.class);
        editMacroGoalsByPercentFragment.fatValueLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.current_fat_intake, "field 'fatValueLabel'", TextView.class);
        editMacroGoalsByPercentFragment.proteinValueLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.current_protein_intake, "field 'proteinValueLabel'", TextView.class);
        editMacroGoalsByPercentFragment.totalPercentage = (TextView) Utils.findRequiredViewAsType(view, R.id.macronutrient_percent_total, "field 'totalPercentage'", TextView.class);
        editMacroGoalsByPercentFragment.proteinPicker = (NumberPicker) Utils.findRequiredViewAsType(view, R.id.protein_picker, "field 'proteinPicker'", NumberPicker.class);
        editMacroGoalsByPercentFragment.carbsPicker = (NumberPicker) Utils.findRequiredViewAsType(view, R.id.carbs_picker, "field 'carbsPicker'", NumberPicker.class);
        editMacroGoalsByPercentFragment.fatPicker = (NumberPicker) Utils.findRequiredViewAsType(view, R.id.fat_picker, "field 'fatPicker'", NumberPicker.class);
    }

    @CallSuper
    public void unbind() {
        EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment = this.target;
        if (editMacroGoalsByPercentFragment != null) {
            this.target = null;
            editMacroGoalsByPercentFragment.carbValueLabel = null;
            editMacroGoalsByPercentFragment.fatValueLabel = null;
            editMacroGoalsByPercentFragment.proteinValueLabel = null;
            editMacroGoalsByPercentFragment.totalPercentage = null;
            editMacroGoalsByPercentFragment.proteinPicker = null;
            editMacroGoalsByPercentFragment.carbsPicker = null;
            editMacroGoalsByPercentFragment.fatPicker = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
