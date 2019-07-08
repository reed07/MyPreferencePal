package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SignUpUsernamePasswordEmailFragment_ViewBinding implements Unbinder {
    private SignUpUsernamePasswordEmailFragment target;

    @UiThread
    public SignUpUsernamePasswordEmailFragment_ViewBinding(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, View view) {
        this.target = signUpUsernamePasswordEmailFragment;
        signUpUsernamePasswordEmailFragment.emailEdit = (EditText) Utils.findRequiredViewAsType(view, R.id.email_edit, "field 'emailEdit'", EditText.class);
        signUpUsernamePasswordEmailFragment.passwordEdit = (EditText) Utils.findRequiredViewAsType(view, R.id.password_edit, "field 'passwordEdit'", EditText.class);
        signUpUsernamePasswordEmailFragment.usernameEdit = (EditText) Utils.findRequiredViewAsType(view, R.id.username_edit, "field 'usernameEdit'", EditText.class);
        signUpUsernamePasswordEmailFragment.usernameTakenContainer = Utils.findRequiredView(view, R.id.username_taken_container, "field 'usernameTakenContainer'");
        signUpUsernamePasswordEmailFragment.suggestedNamesText = (TextView) Utils.findRequiredViewAsType(view, R.id.suggested_names, "field 'suggestedNamesText'", TextView.class);
        signUpUsernamePasswordEmailFragment.newsletterCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.newsletter_checkbox, "field 'newsletterCheckBox'", CheckBox.class);
        signUpUsernamePasswordEmailFragment.privacyCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.privacy_checkbox, "field 'privacyCheckBox'", CheckBox.class);
        signUpUsernamePasswordEmailFragment.consentErrorTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.consent_error_text, "field 'consentErrorTextView'", TextView.class);
        signUpUsernamePasswordEmailFragment.disclaimerText = (TextView) Utils.findRequiredViewAsType(view, R.id.disclaimer_text, "field 'disclaimerText'", TextView.class);
        signUpUsernamePasswordEmailFragment.disclaimerCheckbox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.disclaimer_checkbox_non_us, "field 'disclaimerCheckbox'", CheckBox.class);
        signUpUsernamePasswordEmailFragment.disclaimerCheckboxContainer = Utils.findRequiredView(view, R.id.disclaimer_checkbox_non_us_container, "field 'disclaimerCheckboxContainer'");
        signUpUsernamePasswordEmailFragment.disclaimerCheckboxText = (TextView) Utils.findRequiredViewAsType(view, R.id.disclaimer_checkbox_non_us_text, "field 'disclaimerCheckboxText'", TextView.class);
        signUpUsernamePasswordEmailFragment.emailPasswordContainer = Utils.findRequiredView(view, R.id.email_password_container, "field 'emailPasswordContainer'");
        signUpUsernamePasswordEmailFragment.signUpBtn = (Button) Utils.findRequiredViewAsType(view, R.id.sign_up, "field 'signUpBtn'", Button.class);
    }

    @CallSuper
    public void unbind() {
        SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment = this.target;
        if (signUpUsernamePasswordEmailFragment != null) {
            this.target = null;
            signUpUsernamePasswordEmailFragment.emailEdit = null;
            signUpUsernamePasswordEmailFragment.passwordEdit = null;
            signUpUsernamePasswordEmailFragment.usernameEdit = null;
            signUpUsernamePasswordEmailFragment.usernameTakenContainer = null;
            signUpUsernamePasswordEmailFragment.suggestedNamesText = null;
            signUpUsernamePasswordEmailFragment.newsletterCheckBox = null;
            signUpUsernamePasswordEmailFragment.privacyCheckBox = null;
            signUpUsernamePasswordEmailFragment.consentErrorTextView = null;
            signUpUsernamePasswordEmailFragment.disclaimerText = null;
            signUpUsernamePasswordEmailFragment.disclaimerCheckbox = null;
            signUpUsernamePasswordEmailFragment.disclaimerCheckboxContainer = null;
            signUpUsernamePasswordEmailFragment.disclaimerCheckboxText = null;
            signUpUsernamePasswordEmailFragment.emailPasswordContainer = null;
            signUpUsernamePasswordEmailFragment.signUpBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
