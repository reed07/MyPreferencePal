package com.myfitnesspal.feature.goals.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.MacroGoalsUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.myfitnesspal.shared.util.Toaster;
import com.squareup.otto.Bus;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MacroNutrientEditorDialog extends CustomLayoutBaseDialogFragment {
    private static final String ENERGY_KEY = "energy_value";
    private static final int MAX_PICKER_VALUE = 100;
    private static final int PICKER_STEP_COUNT = 5;
    NumberPicker carbsPicker;
    TextView currentCarbIntake;
    TextView currentFatIntake;
    TextView currentProteinIntake;
    NumberPicker fatPicker;
    /* access modifiers changed from: private */
    public float localizedEnergyValue;
    @Inject
    Lazy<NutrientGoalsUtil> ngu;
    private List<String> pickerValues;
    Button positiveButton;
    NumberPicker proteinPicker;
    TextView totalPercentage;

    public static MacroNutrientEditorDialog getInstance(float f) {
        MacroNutrientEditorDialog macroNutrientEditorDialog = new MacroNutrientEditorDialog();
        Bundle bundle = new Bundle();
        bundle.putFloat("energy_value", f);
        macroNutrientEditorDialog.setArguments(bundle);
        return macroNutrientEditorDialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        View inflate = LayoutInflater.from(dialogContextThemeWrapper).inflate(R.layout.macro_nutrient_editor_fragment, null);
        this.localizedEnergyValue = BundleUtils.getFloat(getArguments(), "energy_value", (float) BitmapDescriptorFactory.HUE_RED);
        this.proteinPicker = (NumberPicker) ViewUtils.findById(inflate, R.id.protein_picker);
        this.carbsPicker = (NumberPicker) ViewUtils.findById(inflate, R.id.carbs_picker);
        this.fatPicker = (NumberPicker) ViewUtils.findById(inflate, R.id.fat_picker);
        this.totalPercentage = (TextView) ViewUtils.findById(inflate, R.id.macronutrient_percent_total);
        this.currentCarbIntake = (TextView) ViewUtils.findById(inflate, R.id.current_carb_intake);
        this.currentProteinIntake = (TextView) ViewUtils.findById(inflate, R.id.current_protein_intake);
        this.currentFatIntake = (TextView) ViewUtils.findById(inflate, R.id.current_fat_intake);
        setPickerBoundaries(this.proteinPicker, this.carbsPicker, this.fatPicker);
        setNearestValue(this.proteinPicker, ((NutrientGoalsUtil) this.ngu.get()).getMacroProteinPercentage());
        setNearestValue(this.carbsPicker, ((NutrientGoalsUtil) this.ngu.get()).getMacroCarbohydratesPercentage());
        setNearestValue(this.fatPicker, ((NutrientGoalsUtil) this.ngu.get()).getMacroFatPercentage());
        ViewUtils.setPickerCommonProperties(this.proteinPicker);
        ViewUtils.setPickerCommonProperties(this.carbsPicker);
        ViewUtils.setPickerCommonProperties(this.fatPicker);
        setCurrentIntakeValues();
        calculatePercentTotal();
        setPickerChangeListeners(this.proteinPicker, this.carbsPicker, this.fatPicker);
        return new MfpAlertDialogBuilder(dialogContextThemeWrapper).setTitle((int) R.string.macronutrients).setView(inflate).setPositiveButton((int) R.string.setBtn, (OnClickListener) null).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create();
    }

    private void setCurrentIntakeValues() {
        this.currentCarbIntake.setText(((NutrientGoalsUtil) this.ngu.get()).getBase5CarbohydratesForDisplay());
        this.currentProteinIntake.setText(((NutrientGoalsUtil) this.ngu.get()).getBase5ProteinForDisplay());
        this.currentFatIntake.setText(((NutrientGoalsUtil) this.ngu.get()).getBase5FatForDisplay());
    }

    public void onStart() {
        super.onStart();
        final AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null) {
            this.positiveButton = alertDialog.getButton(-1);
            this.positiveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MacroNutrientEditorDialog.this.getCurrentMacronutrientTotalPercent() == 100) {
                        Bus access$500 = MacroNutrientEditorDialog.this.messageBus;
                        MacroGoalsUpdatedEvent macroGoalsUpdatedEvent = new MacroGoalsUpdatedEvent(false, MacroNutrientEditorDialog.this.localizedEnergyValue, MacroNutrientEditorDialog.this.getNewCalculatedCarbohydrateValue(), MacroNutrientEditorDialog.this.getNewCalculatedProteinValue(), MacroNutrientEditorDialog.this.getNewCalculatedFatValue(), null);
                        access$500.post(macroGoalsUpdatedEvent);
                        alertDialog.dismiss();
                    } else if (MacroNutrientEditorDialog.this.getActivity() != null) {
                        Toaster.showLong((Activity) MacroNutrientEditorDialog.this.getActivity(), (int) R.string.macronutrients_calculation_message);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void calculatePercentTotal() {
        int i;
        Resources resources;
        int currentMacronutrientTotalPercent = getCurrentMacronutrientTotalPercent();
        boolean z = true;
        this.totalPercentage.setText(getString(R.string.percent_value, Integer.valueOf(currentMacronutrientTotalPercent)));
        TextView textView = this.totalPercentage;
        if (currentMacronutrientTotalPercent == 100) {
            resources = getResources();
            i = R.color.android_green;
        } else {
            resources = getResources();
            i = R.color.red_light;
        }
        textView.setTextColor(resources.getColor(i));
        Button button = this.positiveButton;
        if (button != null) {
            if (currentMacronutrientTotalPercent != 100) {
                z = false;
            }
            button.setEnabled(z);
        }
    }

    /* access modifiers changed from: private */
    public int getCurrentMacronutrientTotalPercent() {
        return Math.round((float) ((this.carbsPicker.getValue() * 5) + (this.fatPicker.getValue() * 5) + (this.proteinPicker.getValue() * 5)));
    }

    private void setNearestValue(NumberPicker numberPicker, float f) {
        numberPicker.setValue(this.pickerValues.indexOf(Strings.toString(Integer.valueOf(Math.round(f / 5.0f) * 5))));
    }

    private void setPickerChangeListeners(NumberPicker... numberPickerArr) {
        for (NumberPicker numberPicker : numberPickerArr) {
            numberPicker.setOnValueChangedListener(new OnValueChangeListener() {
                public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                    MacroNutrientEditorDialog.this.calculatePercentTotal();
                    MacroNutrientEditorDialog.this.recalculateMacronutrientIntake();
                }
            });
            ViewUtils.setPickerCommonProperties(numberPicker);
        }
    }

    /* access modifiers changed from: private */
    public void recalculateMacronutrientIntake() {
        if (getPickersTotalPercent() == 100) {
            float newCalculatedCarbohydrateValue = getNewCalculatedCarbohydrateValue();
            float newCalculatedProteinValue = getNewCalculatedProteinValue();
            float newCalculatedFatValue = getNewCalculatedFatValue();
            this.currentCarbIntake.setText(formatGrams((int) newCalculatedCarbohydrateValue));
            this.currentProteinIntake.setText(formatGrams((int) newCalculatedProteinValue));
            this.currentFatIntake.setText(formatGrams((int) newCalculatedFatValue));
            return;
        }
        this.currentCarbIntake.setText("-");
        this.currentProteinIntake.setText("-");
        this.currentFatIntake.setText("-");
    }

    /* access modifiers changed from: private */
    public float getNewCalculatedFatValue() {
        return NumberUtils.roundToNearestPlace(NutritionUtils.caloriesToGramsFat(((NutrientGoalsUtil) this.ngu.get()).getTotalMacronutrientIntake()) * (((float) getBase5Value(this.fatPicker)) / 100.0f), 1.0f);
    }

    /* access modifiers changed from: private */
    public float getNewCalculatedProteinValue() {
        return NumberUtils.roundToNearestPlace(NutritionUtils.caloriesToGramsProtein(((NutrientGoalsUtil) this.ngu.get()).getTotalMacronutrientIntake()) * (((float) getBase5Value(this.proteinPicker)) / 100.0f), 1.0f);
    }

    /* access modifiers changed from: private */
    public float getNewCalculatedCarbohydrateValue() {
        return NumberUtils.roundToNearestPlace(NutritionUtils.caloriesToGramsCarbs(((NutrientGoalsUtil) this.ngu.get()).getTotalMacronutrientIntake()) * (((float) getBase5Value(this.carbsPicker)) / 100.0f), 1.0f);
    }

    private int getPickersTotalPercent() {
        return (this.proteinPicker.getValue() * 5) + (this.carbsPicker.getValue() * 5) + (this.fatPicker.getValue() * 5);
    }

    private String formatGrams(int i) {
        return String.format("%s %s", new Object[]{Integer.valueOf(i), getResources().getString(R.string.gram_abbreviation)});
    }

    private int getBase5Value(NumberPicker numberPicker) {
        return numberPicker.getValue() * 5;
    }

    private void setPickerBoundaries(NumberPicker... numberPickerArr) {
        this.pickerValues = new ArrayList();
        for (int i = 0; i <= 100; i += 5) {
            this.pickerValues.add(Strings.toString(Integer.valueOf(i)));
        }
        List<String> list = this.pickerValues;
        String[] strArr = (String[]) list.toArray(new String[list.size()]);
        for (NumberPicker numberPicker : numberPickerArr) {
            numberPicker.setMaxValue(strArr.length - 1);
            numberPicker.setMinValue(0);
            numberPicker.setDisplayedValues(strArr);
        }
    }
}
