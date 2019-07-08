package com.myfitnesspal.feature.registration.ui.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.view.CustomLocalizedNumberEditText;
import com.myfitnesspal.shared.util.DecimalDigitsInputFilter;
import com.uacf.core.util.ViewUtils;

public class SignUpHeightWeightDialogFragmentBase extends CustomLayoutBaseDialogFragment {
    protected CustomLocalizedNumberEditText entryOne;
    protected TextView entryOneUnits;
    protected CustomLocalizedNumberEditText entryTwo;
    protected TextView entryTwoUnits;
    protected TextView error;
    protected Spinner units;
    protected View view;

    static /* synthetic */ void lambda$buildAlertDialog$0(DialogInterface dialogInterface, int i) {
    }

    /* access modifiers changed from: protected */
    public void initViews(int i) {
        this.view = LayoutInflater.from(getDialogContextThemeWrapper()).inflate(R.layout.signup_height_weight_picker_dialog, null);
        this.units = (Spinner) ViewUtils.findById(this.view, R.id.units);
        this.entryOne = (CustomLocalizedNumberEditText) ViewUtils.findById(this.view, R.id.entry_one);
        this.entryTwo = (CustomLocalizedNumberEditText) ViewUtils.findById(this.view, R.id.entry_two);
        this.entryOneUnits = (TextView) ViewUtils.findById(this.view, R.id.entry_one_unit);
        this.entryTwoUnits = (TextView) ViewUtils.findById(this.view, R.id.entry_two_unit);
        this.error = (TextView) ViewUtils.findById(this.view, R.id.error);
        this.error.setText(i);
    }

    /* access modifiers changed from: protected */
    public void setSpinnerAvailableUnits(Spinner spinner, String[] strArr) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.sign_up_spinner_selection, strArr);
        arrayAdapter.setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    /* access modifiers changed from: protected */
    public AlertDialog buildAlertDialog(int i, OnClickListener onClickListener) {
        AlertDialog create = new MfpAlertDialogBuilder(getActivity()).setPositiveButton((int) R.string.set, (DialogInterface.OnClickListener) $$Lambda$SignUpHeightWeightDialogFragmentBase$h0n33PT3x05mVSSf1NUrz2taCLQ.INSTANCE).setTitle(i).setView(this.view).create();
        create.getWindow().setSoftInputMode(4);
        create.show();
        create.getButton(-1).setOnClickListener(onClickListener);
        return create;
    }

    /* access modifiers changed from: protected */
    public void setUnitsItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.units.setOnItemSelectedListener(onItemSelectedListener);
    }

    /* access modifiers changed from: protected */
    public void setSelectedUnits(int i) {
        this.units.setSelection(i);
    }

    /* access modifiers changed from: protected */
    public final void setEditTextDigitCount(int i, int i2) {
        DecimalDigitsInputFilter decimalDigitsInputFilter = new DecimalDigitsInputFilter(i, i2);
        boolean z = false;
        InputFilter[] inputFilterArr = {decimalDigitsInputFilter};
        this.entryOne.setFilters(inputFilterArr);
        this.entryTwo.setFilters(inputFilterArr);
        this.entryOne.setAllowDecimal(i2 > 0);
        CustomLocalizedNumberEditText customLocalizedNumberEditText = this.entryTwo;
        if (i2 > 0) {
            z = true;
        }
        customLocalizedNumberEditText.setAllowDecimal(z);
    }

    /* access modifiers changed from: protected */
    public final void setEditTextDigitCount(int i, int i2, int i3, int i4) {
        CustomLocalizedNumberEditText customLocalizedNumberEditText = this.entryOne;
        DecimalDigitsInputFilter decimalDigitsInputFilter = new DecimalDigitsInputFilter(i, i2);
        boolean z = false;
        customLocalizedNumberEditText.setFilters(new InputFilter[]{decimalDigitsInputFilter});
        this.entryOne.setAllowDecimal(i2 > 0);
        this.entryTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(i3, i4)});
        CustomLocalizedNumberEditText customLocalizedNumberEditText2 = this.entryTwo;
        if (i4 > 0) {
            z = true;
        }
        customLocalizedNumberEditText2.setAllowDecimal(z);
    }
}
