package com.myfitnesspal.feature.goals.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.MacroGoalsUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class EditMacroGoalsByGramsFragment extends MfpFragment {
    private static final int MAX_PICKER_VALUE = 999;
    private static final int PICKER_STEP_COUNT = 1;
    @BindView(2131362087)
    NumberPicker carbsPicker;
    @BindView(2131362248)
    TextView currentCarbIntake;
    @BindView(2131362249)
    TextView currentFatIntake;
    @BindView(2131362252)
    TextView currentProteinIntake;
    @BindView(2131363257)
    TextView energyTypeText;
    @BindView(2131362563)
    NumberPicker fatPicker;
    @BindView(2131362985)
    TextView macroNutrientMessage;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    private OnValueChangeListener onValueChangeListener = new OnValueChangeListener() {
        public void onValueChange(NumberPicker numberPicker, int i, int i2) {
            EditMacroGoalsByGramsFragment.this.updateDisplayedEnergyValues();
            EditMacroGoalsByGramsFragment.this.calculatePercentageBasedOnMacros();
        }
    };
    private float originalLocalizedEnergy;
    @BindView(2131363351)
    NumberPicker proteinPicker;
    @BindView(2131362984)
    TextView totalEnergy;
    @Inject
    Lazy<UserEnergyService> userEnergy;

    public static EditMacroGoalsByGramsFragment newInstance(float f, float f2, float f3, float f4, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putFloat(Extras.LOCALIZED_ENERGY, f);
        bundle.putFloat("carbs", f2);
        bundle.putFloat("protein", f3);
        bundle.putFloat("fat", f4);
        bundle.putBoolean(Extras.IS_PREV_MACROS_BY_GRAM, z);
        EditMacroGoalsByGramsFragment editMacroGoalsByGramsFragment = new EditMacroGoalsByGramsFragment();
        editMacroGoalsByGramsFragment.setArguments(bundle);
        return editMacroGoalsByGramsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.macros_nutrient_editor_grams, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setPickerBoundaries(this.proteinPicker, this.carbsPicker, this.fatPicker);
        ViewUtils.setPickerCommonProperties(this.proteinPicker);
        ViewUtils.setPickerCommonProperties(this.carbsPicker);
        ViewUtils.setPickerCommonProperties(this.fatPicker);
        Bundle arguments = getArguments();
        float f = arguments.getFloat(Extras.LOCALIZED_ENERGY, BitmapDescriptorFactory.HUE_RED);
        float f2 = arguments.getFloat("carbs", BitmapDescriptorFactory.HUE_RED);
        float f3 = arguments.getFloat("protein", BitmapDescriptorFactory.HUE_RED);
        float f4 = arguments.getFloat("fat", BitmapDescriptorFactory.HUE_RED);
        if (bundle != null) {
            f2 = bundle.getFloat("carbs", BitmapDescriptorFactory.HUE_RED);
            f3 = bundle.getFloat("protein", BitmapDescriptorFactory.HUE_RED);
            f4 = bundle.getFloat("fat", BitmapDescriptorFactory.HUE_RED);
        }
        this.originalLocalizedEnergy = f;
        setNearestGramValue(this.carbsPicker, f2);
        setNearestGramValue(this.proteinPicker, f3);
        setNearestGramValue(this.fatPicker, f4);
        updatePickerValues(f2, f3, f4);
        updateDisplayedEnergyValues();
        setPickerChangeListeners(this.proteinPicker, this.carbsPicker, this.fatPicker);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putFloat("carbs", (float) this.carbsPicker.getValue());
        bundle.putFloat("protein", (float) this.proteinPicker.getValue());
        bundle.putFloat("fat", (float) this.fatPicker.getValue());
    }

    private void setPickerBoundaries(NumberPicker... numberPickerArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 999; i++) {
            arrayList.add(Strings.toString(Integer.valueOf(i)));
        }
        String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        for (NumberPicker numberPicker : numberPickerArr) {
            numberPicker.setMaxValue(strArr.length - 1);
            numberPicker.setMinValue(0);
            numberPicker.setDisplayedValues(strArr);
        }
    }

    private void setNearestGramValue(NumberPicker numberPicker, float f) {
        numberPicker.setValue(Math.round(f));
    }

    private void setPickerChangeListeners(NumberPicker... numberPickerArr) {
        for (NumberPicker numberPicker : numberPickerArr) {
            numberPicker.setOnValueChangedListener(this.onValueChangeListener);
            numberPicker.setWrapSelectorWheel(false);
            numberPicker.setDescendantFocusability(393216);
        }
    }

    private void updatePickerValues(float f, float f2, float f3) {
        int round = Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMacroCarbohydratesPercentage(f, f2, f3));
        int round2 = Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMacroFatPercentage(f, f2, f3));
        int macroProteinPercentage = ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMacroProteinPercentage(round, round2);
        this.currentCarbIntake.setText(getString(R.string.percent_value, Integer.valueOf(round)));
        this.currentFatIntake.setText(getString(R.string.percent_value, Integer.valueOf(round2)));
        this.currentProteinIntake.setText(getString(R.string.percent_value, Integer.valueOf(macroProteinPercentage)));
    }

    /* access modifiers changed from: private */
    public void updateDisplayedEnergyValues() {
        boolean isCalories = ((UserEnergyService) this.userEnergy.get()).isCalories();
        this.macroNutrientMessage.setText(getResources().getString(isCalories ? R.string.original_goal_calories : R.string.original_goal_kilojoules, new Object[]{Integer.valueOf(Math.round(this.originalLocalizedEnergy))}));
        this.totalEnergy.setText(Strings.toString(Integer.valueOf(getTotalEnergyFromMacroValues())));
        this.energyTypeText.setText(isCalories ? R.string.goal_calories : R.string.goal_kilojoules);
    }

    private double caloriesToLocalizedEnergy(double d) {
        return ((UserEnergyService) this.userEnergy.get()).isCalories() ? d : UnitsUtils.getKilojoules(d);
    }

    private int getTotalEnergyFromMacroValues() {
        return (int) Math.round(caloriesToLocalizedEnergy((double) NutritionUtils.getCaloriesForMacros((float) this.carbsPicker.getValue(), (float) this.fatPicker.getValue(), (float) this.proteinPicker.getValue())));
    }

    /* access modifiers changed from: private */
    public void calculatePercentageBasedOnMacros() {
        updatePickerValues((float) this.carbsPicker.getValue(), (float) this.proteinPicker.getValue(), (float) this.fatPicker.getValue());
    }

    public MacroGoalsUpdatedEvent execute() {
        MacroGoalsUpdatedEvent macroGoalsUpdatedEvent = new MacroGoalsUpdatedEvent(true, (float) getTotalEnergyFromMacroValues(), (float) this.carbsPicker.getValue(), (float) this.proteinPicker.getValue(), (float) this.fatPicker.getValue(), new ApiJsonMapper().reverseMap((Object) getAnalyticsData()));
        return macroGoalsUpdatedEvent;
    }

    public Map<String, String> getAnalyticsData() {
        Bundle arguments = getArguments();
        float f = BundleUtils.getFloat(arguments, "protein");
        float f2 = BundleUtils.getFloat(arguments, "carbs");
        float f3 = BundleUtils.getFloat(arguments, "fat");
        HashMap hashMap = new HashMap();
        hashMap.put(Attributes.MACRO_SETTING_SAVED, "grams");
        hashMap.put(Attributes.MACRO_SETTING_CHANGE, BundleUtils.getBoolean(getArguments(), Extras.IS_PREV_MACROS_BY_GRAM) ? "no" : "yes");
        hashMap.put(Attributes.PROTEIN_SAVED, Strings.toString(Integer.valueOf(this.proteinPicker.getValue())));
        hashMap.put(Attributes.PROTEIN_CHANGE, Strings.toString(Integer.valueOf(this.proteinPicker.getValue() - Math.round(f))));
        hashMap.put(Attributes.CARB_SAVED, Strings.toString(Integer.valueOf(this.carbsPicker.getValue())));
        hashMap.put(Attributes.CARB_CHANGE, Strings.toString(Integer.valueOf(this.carbsPicker.getValue() - Math.round(f2))));
        hashMap.put(Attributes.FAT_SAVED, Strings.toString(Integer.valueOf(this.fatPicker.getValue())));
        hashMap.put(Attributes.FAT_CHANGE, Strings.toString(Integer.valueOf(this.fatPicker.getValue() - Math.round(f3))));
        return hashMap;
    }
}
