package com.myfitnesspal.feature.registration.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.event.SignUpHeightDialogCompleteEvent;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.model.unitconv.LocalizedLength;
import com.myfitnesspal.shared.model.unitconv.MajorMinorUnits;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import javax.inject.Inject;

public class SignUpHeightDialogFragment extends SignUpHeightWeightDialogFragmentBase {
    private static final int CENTIMETERS_INDEX = 1;
    private static final int CM_DECIMAL_COUNT = 0;
    private static final int CM_DIGIT_COUNT = 3;
    private static final String EXTRA_SELECTED_HEIGHT = "extra_selected_height";
    private static final String EXTRA_SELECTED_HEIGHT_UNIT = "extra_selected_height_unit";
    private static final int FEET_AND_INCHES_INDEX = 0;
    private static final int FEET_DECIMAL_COUNT = 0;
    private static final int FEET_DIGIT_COUNT = 1;
    private static final int INCHES_DECIMAL_COUNT = 0;
    private static final int INCHES_DIGIT_COUNT = 2;
    public static final int MAXIMUM_HEIGHT_INCHES = 96;
    public static final int MINIMUM_HEIGHT_INCHES = 36;
    @Inject
    SignUpModel model;
    private LocalizedLength selectedHeight;
    private Length selectedUnit;

    public static SignUpHeightDialogFragment newInstance() {
        return new SignUpHeightDialogFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        initViews(R.string.enter_valid_height);
        component().inject(this);
        this.selectedUnit = (Length) BundleUtils.getSerializable(bundle, EXTRA_SELECTED_HEIGHT_UNIT, this.model.getHeightUnit(), Length.class.getClassLoader());
        this.selectedHeight = (LocalizedLength) BundleUtils.getParcelable(bundle, EXTRA_SELECTED_HEIGHT, this.model.getCurrentHeight(), LocalizedLength.class.getClassLoader());
        setSpinnerAvailableUnits(this.units, getResources().getStringArray(R.array.height_units_array));
        setUnitsItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                SignUpHeightDialogFragment signUpHeightDialogFragment = SignUpHeightDialogFragment.this;
                signUpHeightDialogFragment.setHeightDialogUnits(i, signUpHeightDialogFragment.entryOne, SignUpHeightDialogFragment.this.entryOneUnits, SignUpHeightDialogFragment.this.entryTwo, SignUpHeightDialogFragment.this.entryTwoUnits);
            }
        });
        AlertDialog buildAlertDialog = buildAlertDialog(R.string.height_header, new OnClickListener() {
            public void onClick(View view) {
                SignUpHeightDialogFragment signUpHeightDialogFragment = SignUpHeightDialogFragment.this;
                if (!signUpHeightDialogFragment.setModelHeightFromViews(signUpHeightDialogFragment.entryOne, SignUpHeightDialogFragment.this.entryTwo)) {
                    ViewUtils.setVisible(true, SignUpHeightDialogFragment.this.error);
                    return;
                }
                SignUpHeightDialogFragment.this.messageBus.post(new SignUpHeightDialogCompleteEvent());
                SignUpHeightDialogFragment.this.dismiss();
            }
        });
        populateDialogHeight(this.entryOne, this.entryTwo);
        setSelectedUnits(this.selectedUnit == Length.FEET_INCHES ? 0 : 1);
        return buildAlertDialog;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(EXTRA_SELECTED_HEIGHT_UNIT, this.selectedUnit);
        bundle.putParcelable(EXTRA_SELECTED_HEIGHT, this.selectedHeight);
    }

    /* access modifiers changed from: private */
    public void setHeightDialogUnits(int i, EditText editText, TextView textView, EditText editText2, TextView textView2) {
        String str;
        parseSelectedHeightFromViews(editText, editText2);
        this.selectedUnit = i == 0 ? Length.FEET_INCHES : Length.CENTIMETERS;
        ViewUtils.setVisible(i == 0, editText2);
        ViewUtils.setVisible(i == 0, textView2);
        if (this.selectedUnit == Length.FEET_INCHES) {
            setEditTextDigitCount(1, 0, 2, 0);
            str = getString(R.string.ft);
        } else {
            setEditTextDigitCount(3, 0);
            str = getString(R.string.cm);
        }
        textView.setText(str);
        populateDialogHeight(editText, editText2);
    }

    private void parseSelectedHeightFromViews(EditText editText, EditText editText2) {
        LocalizedLength localizedLength;
        double tryParseDouble = NumberUtils.tryParseDouble(editText.getText().toString());
        double tryParseDouble2 = NumberUtils.tryParseDouble(editText2.getText().toString());
        if (this.selectedUnit == Length.FEET_INCHES) {
            localizedLength = LocalizedLength.fromFeetInches((double) Math.round(tryParseDouble), (double) Math.round(tryParseDouble2));
        } else {
            localizedLength = LocalizedLength.from((double) Math.round(tryParseDouble), this.selectedUnit);
        }
        this.selectedHeight = localizedLength;
    }

    /* access modifiers changed from: private */
    public boolean setModelHeightFromViews(EditText editText, EditText editText2) {
        parseSelectedHeightFromViews(editText, editText2);
        int ceil = (int) Math.ceil(this.selectedHeight.getValue(Length.INCHES));
        if (ceil < 36 || ceil > 96) {
            return false;
        }
        this.model.setHeightUnit(this.selectedUnit);
        this.model.setCurrentHeight(this.selectedHeight);
        return true;
    }

    private void populateDialogHeight(EditText editText, EditText editText2) {
        if (this.selectedHeight.getValue(this.selectedUnit) > 0.0d) {
            if (this.selectedUnit == Length.FEET_INCHES) {
                MajorMinorUnits majorMinorUnits = LocalizedLength.getMajorMinorUnits(this.selectedHeight, this.selectedUnit);
                editText.setText(NumberUtils.localeStringFromDoubleNoDecimal((double) majorMinorUnits.getMajorValue()));
                editText2.setText(NumberUtils.localeStringFromDoubleNoDecimal((double) majorMinorUnits.getMinorValue()));
            } else {
                editText.setText(NumberUtils.localeStringFromDoubleNoDecimal(this.selectedHeight.getValue(this.selectedUnit)));
            }
        }
        TextViewUtils.setCursorToEnd(this.entryOne);
    }
}
