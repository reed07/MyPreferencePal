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
import com.myfitnesspal.feature.goals.event.ExerciseCaloriesUpdatedEvent;
import com.myfitnesspal.feature.goals.event.MacroGoalsUpdatedEvent;
import com.myfitnesspal.feature.goals.event.PickerValuesUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.squareup.otto.Bus;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class EditMacroGoalsByPercentFragment extends MfpFragment {
    private static final String EXTRA_CARB_PICKER;
    private static final String EXTRA_DISPLAY_TYPE;
    private static final String EXTRA_FAT_PICKER;
    private static final String EXTRA_PARENT;
    private static final String EXTRA_PROTEIN_PICKER;
    private static final int MAX_PICKER_VALUE = 100;
    private static final int PICKER_STEP_COUNT = 5;
    private static final String TAG = EditMacroGoalsByPercentFragment.class.getCanonicalName();
    @BindView(2131362248)
    TextView carbValueLabel;
    @BindView(2131362087)
    NumberPicker carbsPicker;
    private float currentLocalizedEnergy;
    @BindView(2131362563)
    NumberPicker fatPicker;
    @BindView(2131362249)
    TextView fatValueLabel;
    private Mode mode;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    private OnValueChangeListener onPickerChangeListener = new OnValueChangeListener() {
        public void onValueChange(NumberPicker numberPicker, int i, int i2) {
            EditMacroGoalsByPercentFragment.this.calculatePercentTotal();
            EditMacroGoalsByPercentFragment.this.recalculateNutrientValueLabels();
        }
    };
    private String parent;
    private List<String> pickerValues;
    @BindView(2131363351)
    NumberPicker proteinPicker;
    @BindView(2131362252)
    TextView proteinValueLabel;
    @Inject
    Lazy<Session> session;
    @BindView(2131362984)
    TextView totalPercentage;
    @Inject
    Lazy<UserEnergyService> userEnergy;

    public enum Mode {
        ByPercent,
        ByValue
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append(".PARENT");
        EXTRA_PARENT = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TAG);
        sb2.append(".DISPLAY_TYPE");
        EXTRA_DISPLAY_TYPE = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(TAG);
        sb3.append(".CARB_PICKER");
        EXTRA_CARB_PICKER = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(TAG);
        sb4.append(".PROTEIN_PICKER");
        EXTRA_PROTEIN_PICKER = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(TAG);
        sb5.append(".FAT_PICKER");
        EXTRA_FAT_PICKER = sb5.toString();
    }

    public static EditMacroGoalsByPercentFragment newInstance(Mode mode2, String str, float f, float f2, float f3, float f4, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_PARENT, str);
        bundle.putFloat(Extras.LOCALIZED_ENERGY, f);
        bundle.putFloat("carbs", f2);
        bundle.putFloat("protein", f3);
        bundle.putFloat("fat", f4);
        bundle.putSerializable(EXTRA_DISPLAY_TYPE, mode2);
        bundle.putBoolean(Extras.IS_PREV_MACROS_BY_GRAM, z);
        EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment = new EditMacroGoalsByPercentFragment();
        editMacroGoalsByPercentFragment.setArguments(bundle);
        return editMacroGoalsByPercentFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.macros_nutrient_editor_percent, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putFloat(EXTRA_CARB_PICKER, (float) getPickerValue(this.carbsPicker));
        bundle.putFloat(EXTRA_PROTEIN_PICKER, (float) getPickerValue(this.proteinPicker));
        bundle.putFloat(EXTRA_FAT_PICKER, (float) getPickerValue(this.fatPicker));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setPickerBoundaries(this.proteinPicker, this.carbsPicker, this.fatPicker);
        ViewUtils.setPickerCommonProperties(this.proteinPicker);
        ViewUtils.setPickerCommonProperties(this.carbsPicker);
        ViewUtils.setPickerCommonProperties(this.fatPicker);
        Bundle arguments = getArguments();
        float f = BundleUtils.getFloat(arguments, "carbs", (float) BitmapDescriptorFactory.HUE_RED);
        float f2 = BundleUtils.getFloat(arguments, "protein", (float) BitmapDescriptorFactory.HUE_RED);
        float f3 = BundleUtils.getFloat(arguments, "fat", (float) BitmapDescriptorFactory.HUE_RED);
        this.mode = (Mode) arguments.getSerializable(EXTRA_DISPLAY_TYPE);
        this.parent = BundleUtils.getString(arguments, EXTRA_PARENT);
        this.currentLocalizedEnergy = BundleUtils.getFloat(arguments, Extras.LOCALIZED_ENERGY);
        if (bundle != null) {
            setNearestValue(this.carbsPicker, bundle.getFloat(EXTRA_CARB_PICKER));
            setNearestValue(this.proteinPicker, bundle.getFloat(EXTRA_PROTEIN_PICKER));
            setNearestValue(this.fatPicker, bundle.getFloat(EXTRA_FAT_PICKER));
            recalculateNutrientValueLabels();
        } else if (this.mode == Mode.ByPercent) {
            setNearestValue(this.carbsPicker, f);
            setNearestValue(this.proteinPicker, f2);
            setNearestValue(this.fatPicker, f3);
        } else {
            setNearestValue(this.proteinPicker, ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMacroProteinPercentage(f, f2, f3));
            setNearestValue(this.carbsPicker, ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMacroCarbohydratesPercentage(f, f2, f3));
            setNearestValue(this.fatPicker, ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMacroFatPercentage(f, f2, f3));
            setNutrientValueLabels(f, f2, f3);
        }
        setPickerChangeListeners(this.proteinPicker, this.carbsPicker, this.fatPicker);
        calculatePercentTotal();
    }

    private void setPickerChangeListeners(NumberPicker... numberPickerArr) {
        for (NumberPicker onValueChangedListener : numberPickerArr) {
            onValueChangedListener.setOnValueChangedListener(this.onPickerChangeListener);
        }
    }

    /* access modifiers changed from: private */
    public void recalculateNutrientValueLabels() {
        float newCalculatedCarbohydrateValue = getNewCalculatedCarbohydrateValue();
        float newCalculatedProteinValue = getNewCalculatedProteinValue();
        float newCalculatedFatValue = getNewCalculatedFatValue();
        this.carbValueLabel.setText(formatGrams((int) newCalculatedCarbohydrateValue));
        this.proteinValueLabel.setText(formatGrams((int) newCalculatedProteinValue));
        this.fatValueLabel.setText(formatGrams((int) newCalculatedFatValue));
    }

    private void setNutrientValueLabels(float f, float f2, float f3) {
        if (this.mode == Mode.ByPercent) {
            ViewUtils.setVisible(false, this.carbValueLabel);
            ViewUtils.setVisible(false, this.proteinValueLabel);
            ViewUtils.setVisible(false, this.fatValueLabel);
            return;
        }
        this.carbValueLabel.setText(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getBase5CarbohydratesForDisplay(f, f2, f3));
        this.proteinValueLabel.setText(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getBase5ProteinForDisplay(f, f2, f3));
        this.fatValueLabel.setText(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getBase5FatForDisplay(f, f2, f3));
    }

    private String formatGrams(int i) {
        return String.format("%s %s", new Object[]{Integer.valueOf(i), getResources().getString(R.string.gram_abbreviation)});
    }

    private float calorieEnergy() {
        if (((UserEnergyService) this.userEnergy.get()).isCalories()) {
            return this.currentLocalizedEnergy;
        }
        return (float) UnitsUtils.getCalories((double) this.currentLocalizedEnergy);
    }

    private float getNewCalculatedFatValue() {
        return (float) Math.round(((calorieEnergy() * ((float) getPickerValue(this.fatPicker))) / 100.0f) / 9.0f);
    }

    private float getNewCalculatedProteinValue() {
        return (float) Math.round(((calorieEnergy() * ((float) getPickerValue(this.proteinPicker))) / 100.0f) / 4.0f);
    }

    private float getNewCalculatedCarbohydrateValue() {
        return (float) Math.round(((calorieEnergy() * ((float) getPickerValue(this.carbsPicker))) / 100.0f) / 4.0f);
    }

    private int getPickerValue(NumberPicker numberPicker) {
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

    private void setNearestValue(NumberPicker numberPicker, float f) {
        numberPicker.setValue(this.pickerValues.indexOf(Strings.toString(Integer.valueOf(Math.round(f / 5.0f) * 5))));
    }

    /* access modifiers changed from: private */
    public void calculatePercentTotal() {
        int i;
        int currentMacronutrientTotalPercent = getCurrentMacronutrientTotalPercent();
        boolean z = true;
        this.totalPercentage.setText(String.format("%s%%", new Object[]{Integer.valueOf(currentMacronutrientTotalPercent)}));
        TextView textView = this.totalPercentage;
        if (currentMacronutrientTotalPercent == 100) {
            i = getResources().getColor(R.color.android_green);
        } else {
            i = getResources().getColor(R.color.red_light);
        }
        textView.setTextColor(i);
        Bus messageBus = getMessageBus();
        if (currentMacronutrientTotalPercent != 100) {
            z = false;
        }
        messageBus.post(new PickerValuesUpdatedEvent(z));
    }

    public int getCurrentMacronutrientTotalPercent() {
        return Math.round((float) ((this.carbsPicker.getValue() * 5) + (this.fatPicker.getValue() * 5) + (this.proteinPicker.getValue() * 5)));
    }

    public MacroGoalsUpdatedEvent execute() {
        if (Strings.equals(this.parent, Extras.EXERCISE_CALORIES_UPDATED)) {
            getMessageBus().post(new ExerciseCaloriesUpdatedEvent(this.carbsPicker.getValue() * 5, this.proteinPicker.getValue() * 5, this.fatPicker.getValue() * 5));
            return null;
        }
        MacroGoalsUpdatedEvent macroGoalsUpdatedEvent = new MacroGoalsUpdatedEvent(false, this.currentLocalizedEnergy, getNewCalculatedCarbohydrateValue(), getNewCalculatedProteinValue(), getNewCalculatedFatValue(), new ApiJsonMapper().reverseMap((Object) getAnalyticsData()));
        return macroGoalsUpdatedEvent;
    }

    private Map<String, String> getAnalyticsData() {
        Bundle arguments = getArguments();
        float f = arguments.getFloat("protein");
        float f2 = arguments.getFloat("carbs");
        float f3 = arguments.getFloat("fat");
        HashMap hashMap = new HashMap();
        hashMap.put(Attributes.MACRO_SETTING_SAVED, "percent");
        hashMap.put(Attributes.MACRO_SETTING_CHANGE, BundleUtils.getBoolean(getArguments(), Extras.IS_PREV_MACROS_BY_GRAM) ? "yes" : "no");
        hashMap.put(Attributes.PROTEIN_SAVED, Strings.toString(Integer.valueOf(getPickerValue(this.proteinPicker))));
        hashMap.put(Attributes.PROTEIN_CHANGE, Strings.toString(Integer.valueOf(getPickerValue(this.proteinPicker) - Math.round((float) ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getBase5MacroProteinPercentage(f2, f, f3)))));
        hashMap.put(Attributes.CARB_SAVED, Strings.toString(Integer.valueOf(getPickerValue(this.carbsPicker))));
        hashMap.put(Attributes.CARB_CHANGE, Strings.toString(Integer.valueOf(getPickerValue(this.carbsPicker) - Math.round((float) ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getBase5MacroCarbohydratesPercentage(f2, f, f3)))));
        hashMap.put(Attributes.FAT_SAVED, Strings.toString(Integer.valueOf(getPickerValue(this.fatPicker))));
        hashMap.put(Attributes.FAT_CHANGE, Strings.toString(Integer.valueOf(getPickerValue(this.fatPicker) - Math.round((float) ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getBase5MacroFatPercentage(f2, f, f3)))));
        return hashMap;
    }
}
