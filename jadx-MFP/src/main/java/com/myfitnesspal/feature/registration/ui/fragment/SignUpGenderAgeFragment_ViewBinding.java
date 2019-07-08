package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SignUpGenderAgeFragment_ViewBinding implements Unbinder {
    private SignUpGenderAgeFragment target;

    @UiThread
    public SignUpGenderAgeFragment_ViewBinding(SignUpGenderAgeFragment signUpGenderAgeFragment, View view) {
        this.target = signUpGenderAgeFragment;
        signUpGenderAgeFragment.male = (RadioButton) Utils.findRequiredViewAsType(view, R.id.male, "field 'male'", RadioButton.class);
        signUpGenderAgeFragment.female = (RadioButton) Utils.findRequiredViewAsType(view, R.id.female, "field 'female'", RadioButton.class);
        signUpGenderAgeFragment.birthDate = (EditText) Utils.findRequiredViewAsType(view, R.id.birthdate, "field 'birthDate'", EditText.class);
        signUpGenderAgeFragment.zipcode = (EditText) Utils.findRequiredViewAsType(view, R.id.zipcode, "field 'zipcode'", EditText.class);
        signUpGenderAgeFragment.countrySpinner = (Spinner) Utils.findRequiredViewAsType(view, R.id.country, "field 'countrySpinner'", Spinner.class);
        signUpGenderAgeFragment.countryAndZip = Utils.findRequiredView(view, R.id.lower_fields, "field 'countryAndZip'");
    }

    @CallSuper
    public void unbind() {
        SignUpGenderAgeFragment signUpGenderAgeFragment = this.target;
        if (signUpGenderAgeFragment != null) {
            this.target = null;
            signUpGenderAgeFragment.male = null;
            signUpGenderAgeFragment.female = null;
            signUpGenderAgeFragment.birthDate = null;
            signUpGenderAgeFragment.zipcode = null;
            signUpGenderAgeFragment.countrySpinner = null;
            signUpGenderAgeFragment.countryAndZip = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
