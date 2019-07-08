package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class LoginUserPassFragment_ViewBinding implements Unbinder {
    private LoginUserPassFragment target;

    @UiThread
    public LoginUserPassFragment_ViewBinding(LoginUserPassFragment loginUserPassFragment, View view) {
        this.target = loginUserPassFragment;
        loginUserPassFragment.usernameEdit = (EditText) Utils.findRequiredViewAsType(view, R.id.email_address_edit, "field 'usernameEdit'", EditText.class);
        loginUserPassFragment.passwordEdit = (EditText) Utils.findRequiredViewAsType(view, R.id.password_edit, "field 'passwordEdit'", EditText.class);
        loginUserPassFragment.facebookButtonText = (TextView) Utils.findRequiredViewAsType(view, R.id.facebook_button_text, "field 'facebookButtonText'", TextView.class);
        loginUserPassFragment.facebookButton = Utils.findRequiredView(view, R.id.facebook_button, "field 'facebookButton'");
        loginUserPassFragment.forgotPassword = Utils.findRequiredView(view, R.id.forgot_password_button, "field 'forgotPassword'");
        loginUserPassFragment.loginButton = (TextView) Utils.findRequiredViewAsType(view, R.id.login_button, "field 'loginButton'", TextView.class);
        loginUserPassFragment.separatorAndFacebookButton = Utils.findRequiredView(view, R.id.facebook_section, "field 'separatorAndFacebookButton'");
        loginUserPassFragment.underArmourAccountFaq = (TextView) Utils.findRequiredViewAsType(view, R.id.under_armour_account, "field 'underArmourAccountFaq'", TextView.class);
        loginUserPassFragment.userPassContainer = Utils.findRequiredView(view, R.id.user_pass_container, "field 'userPassContainer'");
    }

    @CallSuper
    public void unbind() {
        LoginUserPassFragment loginUserPassFragment = this.target;
        if (loginUserPassFragment != null) {
            this.target = null;
            loginUserPassFragment.usernameEdit = null;
            loginUserPassFragment.passwordEdit = null;
            loginUserPassFragment.facebookButtonText = null;
            loginUserPassFragment.facebookButton = null;
            loginUserPassFragment.forgotPassword = null;
            loginUserPassFragment.loginButton = null;
            loginUserPassFragment.separatorAndFacebookButton = null;
            loginUserPassFragment.underArmourAccountFaq = null;
            loginUserPassFragment.userPassContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
