package com.myfitnesspal.feature.registration.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.constants.SignUpBmi;
import com.myfitnesspal.feature.registration.event.SignUpWeightDialogCompleteEvent;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Registration;
import com.myfitnesspal.shared.model.unitconv.LocalizedLength;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.unitconv.MajorMinorUnits;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.myfitnesspal.shared.util.UpdateWeightProxy;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class SignUpWeightDialogFragment extends SignUpHeightWeightDialogFragmentBase {
    private static final String EXTRA_SELECTED_WEIGHT = "extra_selected_weight";
    private static final String EXTRA_SELECTED_WEIGHT_UNIT = "extra_selected_weight_unit";
    private static final int KILOGRAMS_DECIMAL_COUNT = 1;
    private static final int KILOGRAMS_DIGIT_COUNT = 3;
    private static final int KILOGRAMS_INDEX = 0;
    public static final int MAXIMUM_WEIGHT_POUNDS = 999;
    public static final int MINIMUM_WEIGHT_POUNDS = 30;
    private static final int POUNDS_DECIMAL_COUNT = 1;
    private static final int POUNDS_DIGIT_COUNT = 3;
    private static final int POUNDS_INDEX = 1;
    private static final int STONES_DECIMAL_COUNT = 0;
    private static final int STONES_DIGIT_COUNT = 2;
    private static final int STONE_INDEX = 2;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    SignUpModel model;
    private Weight selectedUnit;
    private LocalizedWeight selectedValue;
    /* access modifiers changed from: private */
    public WeightType weightType;

    public static SignUpWeightDialogFragment newInstance(WeightType weightType2) {
        if (weightType2 == WeightType.CURRENT || weightType2 == WeightType.GOAL) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Extras.WEIGHT_TYPE, weightType2);
            SignUpWeightDialogFragment signUpWeightDialogFragment = new SignUpWeightDialogFragment();
            signUpWeightDialogFragment.setArguments(bundle);
            return signUpWeightDialogFragment;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("weightType ");
        sb.append(weightType2);
        sb.append(" not supported");
        throw new IllegalArgumentException(sb.toString());
    }

    private void hydrateFieldsFrom(Bundle bundle) {
        this.weightType = (WeightType) bundle.getSerializable(Extras.WEIGHT_TYPE);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        component().inject(this);
        hydrateFieldsFrom(getArguments());
        this.selectedUnit = (Weight) BundleUtils.getSerializable(bundle, EXTRA_SELECTED_WEIGHT_UNIT, this.model.getWeightUnit(), Weight.class.getClassLoader());
        this.selectedValue = (LocalizedWeight) BundleUtils.getParcelable(bundle, EXTRA_SELECTED_WEIGHT, null, LocalizedWeight.class.getClassLoader());
        if (this.selectedValue == null) {
            this.selectedValue = this.weightType == WeightType.CURRENT ? this.model.getCurrentWeight() : this.model.getGoalWeight();
        }
        initViews(R.string.enter_valid_weight);
        setSpinnerAvailableUnits(this.units, getResources().getStringArray(R.array.weight_units_array));
        setUnitsItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                SignUpWeightDialogFragment signUpWeightDialogFragment = SignUpWeightDialogFragment.this;
                signUpWeightDialogFragment.setWeightDialogUnits(i, signUpWeightDialogFragment.entryOne, SignUpWeightDialogFragment.this.entryOneUnits, SignUpWeightDialogFragment.this.entryTwo, SignUpWeightDialogFragment.this.entryTwoUnits);
            }
        });
        AlertDialog buildAlertDialog = buildAlertDialog(Strings.equals((Object) this.weightType, (Object) WeightType.CURRENT) ? R.string.current_weight : R.string.goal_weight, new OnClickListener() {
            public void onClick(View view) {
                if (Strings.equals((Object) SignUpWeightDialogFragment.this.weightType, (Object) WeightType.CURRENT)) {
                    SignUpWeightDialogFragment signUpWeightDialogFragment = SignUpWeightDialogFragment.this;
                    if (!signUpWeightDialogFragment.saveCurrentWeight(signUpWeightDialogFragment.entryOne, SignUpWeightDialogFragment.this.entryTwo)) {
                        ViewUtils.setVisible(SignUpWeightDialogFragment.this.error);
                        return;
                    }
                    SignUpWeightDialogFragment.this.setResultAndDismiss(null, SignUpBmi.Normal);
                    return;
                }
                SignUpWeightDialogFragment signUpWeightDialogFragment2 = SignUpWeightDialogFragment.this;
                signUpWeightDialogFragment2.verifyAndSaveValidGoalWeight(signUpWeightDialogFragment2.entryOne, SignUpWeightDialogFragment.this.entryTwo);
            }
        });
        populateDialogWeightEditFields(this.entryOne, this.entryTwo);
        int i = this.selectedUnit == Weight.KILOGRAMS ? 0 : this.selectedUnit == Weight.POUNDS ? 1 : 2;
        setSelectedUnits(i);
        return buildAlertDialog;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(EXTRA_SELECTED_WEIGHT_UNIT, this.selectedUnit);
        bundle.putParcelable(EXTRA_SELECTED_WEIGHT, this.selectedValue);
    }

    /* access modifiers changed from: private */
    public void setResultAndDismiss(String str, SignUpBmi signUpBmi) {
        this.messageBus.post(new SignUpWeightDialogCompleteEvent(str, signUpBmi));
        dismiss();
    }

    /* access modifiers changed from: private */
    public void setWeightDialogUnits(int i, EditText editText, TextView textView, EditText editText2, TextView textView2) {
        String str;
        this.selectedValue = parseWeightFromEditFields(editText, editText2);
        ViewUtils.setVisible(i == 2, editText2);
        ViewUtils.setVisible(i == 2, textView2);
        if (i == 0) {
            str = getString(R.string.kg);
            this.selectedUnit = Weight.KILOGRAMS;
            setEditTextDigitCount(3, 1);
        } else if (i == 1) {
            str = getString(R.string.lbs);
            this.selectedUnit = Weight.POUNDS;
            setEditTextDigitCount(3, 1);
        } else {
            str = getString(R.string.st);
            textView2.setText(getString(R.string.lbs));
            this.selectedUnit = Weight.STONES_POUNDS;
            setEditTextDigitCount(2, 0);
        }
        textView.setText(str);
        populateDialogWeightEditFields(editText, editText2);
    }

    private LocalizedWeight parseWeightFromEditFields(EditText editText, EditText editText2) {
        double tryParseDouble = NumberUtils.tryParseDouble(editText.getText().toString());
        double tryParseDouble2 = NumberUtils.tryParseDouble(editText2.getText().toString());
        if (this.selectedUnit == Weight.STONES_POUNDS) {
            return LocalizedWeight.fromStonePounds(tryParseDouble, tryParseDouble2);
        }
        return LocalizedWeight.from(tryParseDouble, this.selectedUnit);
    }

    private void populateDialogWeightEditFields(EditText editText, EditText editText2) {
        if (this.selectedValue.getValue(Weight.POUNDS) > 0.0d) {
            switch (this.selectedUnit) {
                case KILOGRAMS:
                case POUNDS:
                    editText.setText(NumberUtils.localeStringFromDouble(this.selectedValue.getValue(this.selectedUnit), 1));
                    break;
                case STONES:
                case STONES_POUNDS:
                    MajorMinorUnits majorMinorUnits = LocalizedWeight.getMajorMinorUnits(this.selectedValue, this.selectedUnit);
                    editText.setText(NumberUtils.localeStringFromDoubleNoDecimal((double) majorMinorUnits.getMajorValue()));
                    editText2.setText(NumberUtils.localeStringFromDoubleNoDecimal((double) majorMinorUnits.getMinorValue()));
                    break;
            }
        }
        TextViewUtils.setCursorToEnd(this.entryOne);
    }

    private boolean isWeightWithinValidRange(LocalizedWeight localizedWeight) {
        int ceil = (int) Math.ceil(localizedWeight.getValue(Weight.POUNDS));
        return ceil >= 30 && ceil <= 999;
    }

    /* access modifiers changed from: private */
    public void verifyAndSaveValidGoalWeight(EditText editText, EditText editText2) {
        LocalizedLength currentHeight = this.model.getCurrentHeight();
        LocalizedWeight parseWeightFromEditFields = parseWeightFromEditFields(editText, editText2);
        if (!isWeightWithinValidRange(parseWeightFromEditFields)) {
            ViewUtils.setVisible(this.error);
            return;
        }
        Weight weight = this.selectedUnit;
        double value = this.model.getCurrentWeight().getValue(Weight.POUNDS);
        currentHeight.getValue(Length.INCHES);
        double value2 = parseWeightFromEditFields.getValue(Weight.POUNDS);
        Tuple2 validateAppropriateGoalWeightEntryOrSuggestRange = new UpdateWeightProxy().validateAppropriateGoalWeightEntryOrSuggestRange(parseWeightFromEditFields.getValue(Weight.POUNDS), currentHeight.getValue(Length.INCHES));
        LocalizedWeight localizedWeight = (LocalizedWeight) validateAppropriateGoalWeightEntryOrSuggestRange.getItem1();
        LocalizedWeight localizedWeight2 = (LocalizedWeight) validateAppropriateGoalWeightEntryOrSuggestRange.getItem2();
        double value3 = localizedWeight.getValue(Weight.POUNDS);
        boolean z = value3 == localizedWeight2.getValue(Weight.POUNDS) && value3 == value2;
        if (value2 <= 0.0d) {
            ViewUtils.setVisible(this.error);
            return;
        }
        this.model.setWeightUnit(this.selectedUnit);
        this.model.setWaterUnit(getWaterUnitForWeightUnit(this.selectedUnit));
        this.model.setGoalWeight(parseWeightFromEditFields);
        if (z) {
            setResultAndDismiss(null, SignUpBmi.Normal);
        } else {
            SignUpBmi signUpBmi = SignUpBmi.Over;
            if (value2 < value) {
                ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.ED_SIGNUP_SHOW_LOW_GOAL_WEIGHT_WARNING);
                signUpBmi = SignUpBmi.Under;
            }
            if (weight == Weight.POUNDS) {
                String displayStringWithoutUnit = LocalizedWeight.getDisplayStringWithoutUnit(getContext(), localizedWeight, weight);
                setResultAndDismiss(getString(R.string.a_normal_weight_range_pounds, displayStringWithoutUnit, LocalizedWeight.getDisplayStringWithoutUnit(getContext(), localizedWeight2, weight), displayStringWithoutUnit), signUpBmi);
            } else if (weight == Weight.KILOGRAMS) {
                String displayStringWithoutUnit2 = LocalizedWeight.getDisplayStringWithoutUnit(getContext(), localizedWeight, weight);
                setResultAndDismiss(getString(R.string.a_normal_weight_range_kilos, displayStringWithoutUnit2, LocalizedWeight.getDisplayStringWithoutUnit(getContext(), localizedWeight2, weight), displayStringWithoutUnit2), signUpBmi);
            } else if (weight == Weight.STONES_POUNDS) {
                MajorMinorUnits majorMinorUnits = LocalizedWeight.getMajorMinorUnits(localizedWeight, Weight.STONES_POUNDS);
                MajorMinorUnits majorMinorUnits2 = LocalizedWeight.getMajorMinorUnits(localizedWeight2, Weight.STONES_POUNDS);
                setResultAndDismiss(getString(R.string.a_normal_weight_range_stones, majorMinorUnits.getMajorDisplayValue(), majorMinorUnits.getMinorDisplayValue(), majorMinorUnits2.getMajorDisplayValue(), majorMinorUnits2.getMinorDisplayValue(), majorMinorUnits.getMajorDisplayValue(), majorMinorUnits.getMinorDisplayValue()), signUpBmi);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean saveCurrentWeight(EditText editText, EditText editText2) {
        LocalizedWeight parseWeightFromEditFields = parseWeightFromEditFields(editText, editText2);
        if (parseWeightFromEditFields.isZero() || !isWeightWithinValidRange(parseWeightFromEditFields)) {
            return false;
        }
        this.model.setWeightUnit(this.selectedUnit);
        this.model.setWaterUnit(getWaterUnitForWeightUnit(this.selectedUnit));
        this.model.setCurrentWeight(parseWeightFromEditFields);
        if (Registration.MAINTAIN.equals(this.model.getGoalType())) {
            this.model.setGoalWeight(parseWeightFromEditFields);
        }
        return true;
    }

    private static Water getWaterUnitForWeightUnit(Weight weight) {
        return weight == Weight.KILOGRAMS ? Water.MILLILITERS : Water.FL_OZ;
    }
}
