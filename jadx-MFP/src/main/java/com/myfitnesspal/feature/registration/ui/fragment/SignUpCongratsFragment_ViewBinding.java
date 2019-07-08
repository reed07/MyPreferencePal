package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SignUpCongratsFragment_ViewBinding implements Unbinder {
    private SignUpCongratsFragment target;

    @UiThread
    public SignUpCongratsFragment_ViewBinding(SignUpCongratsFragment signUpCongratsFragment, View view) {
        this.target = signUpCongratsFragment;
        signUpCongratsFragment.startTracking = (TextView) Utils.findRequiredViewAsType(view, R.id.start_tracking, "field 'startTracking'", TextView.class);
        signUpCongratsFragment.energyValueText = (TextView) Utils.findRequiredViewAsType(view, R.id.calorie_count, "field 'energyValueText'", TextView.class);
        signUpCongratsFragment.remindersCheckBox = (CompoundButton) Utils.findRequiredViewAsType(view, R.id.chk_box_reminders, "field 'remindersCheckBox'", CompoundButton.class);
        signUpCongratsFragment.newsletterCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.newsletter_checkbox, "field 'newsletterCheckBox'", CheckBox.class);
        signUpCongratsFragment.newsLetterCheckBoxSubtext = (TextView) Utils.findRequiredViewAsType(view, R.id.checkbox_subtext, "field 'newsLetterCheckBoxSubtext'", TextView.class);
        signUpCongratsFragment.energyUnitsSpinner = (Spinner) Utils.findRequiredViewAsType(view, R.id.energy_units, "field 'energyUnitsSpinner'", Spinner.class);
    }

    @CallSuper
    public void unbind() {
        SignUpCongratsFragment signUpCongratsFragment = this.target;
        if (signUpCongratsFragment != null) {
            this.target = null;
            signUpCongratsFragment.startTracking = null;
            signUpCongratsFragment.energyValueText = null;
            signUpCongratsFragment.remindersCheckBox = null;
            signUpCongratsFragment.newsletterCheckBox = null;
            signUpCongratsFragment.newsLetterCheckBoxSubtext = null;
            signUpCongratsFragment.energyUnitsSpinner = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
