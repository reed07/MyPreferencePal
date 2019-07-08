package com.myfitnesspal.feature.deleteaccount.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class DeleteAccountActivity_ViewBinding implements Unbinder {
    private DeleteAccountActivity target;

    @UiThread
    public DeleteAccountActivity_ViewBinding(DeleteAccountActivity deleteAccountActivity) {
        this(deleteAccountActivity, deleteAccountActivity.getWindow().getDecorView());
    }

    @UiThread
    public DeleteAccountActivity_ViewBinding(DeleteAccountActivity deleteAccountActivity, View view) {
        this.target = deleteAccountActivity;
        deleteAccountActivity.howCancelPremiumText = Utils.findRequiredView(view, R.id.how_cancel_premium_text, "field 'howCancelPremiumText'");
        deleteAccountActivity.uaAccountDeleteText = Utils.findRequiredView(view, R.id.ua_account_delete_text, "field 'uaAccountDeleteText'");
        deleteAccountActivity.fixAccountText = Utils.findRequiredView(view, R.id.fix_account_text, "field 'fixAccountText'");
        deleteAccountActivity.fbLoginText = Utils.findRequiredView(view, R.id.fb_login_text, "field 'fbLoginText'");
        deleteAccountActivity.fbButton = Utils.findRequiredView(view, R.id.facebook_button, "field 'fbButton'");
        deleteAccountActivity.forgotPasswordText = Utils.findRequiredView(view, R.id.forgot_password_text, "field 'forgotPasswordText'");
        deleteAccountActivity.deleteAccountButton = Utils.findRequiredView(view, R.id.btn_delete_account, "field 'deleteAccountButton'");
        deleteAccountActivity.passwordInput = (EditText) Utils.findRequiredViewAsType(view, R.id.password_input, "field 'passwordInput'", EditText.class);
        deleteAccountActivity.enterPasswordText = (TextView) Utils.findRequiredViewAsType(view, R.id.enter_password_text, "field 'enterPasswordText'", TextView.class);
        deleteAccountActivity.fbButtonText = (TextView) Utils.findRequiredViewAsType(view, R.id.facebook_button_text, "field 'fbButtonText'", TextView.class);
        deleteAccountActivity.exportInformationText = (TextView) Utils.findRequiredViewAsType(view, R.id.export_information_text, "field 'exportInformationText'", TextView.class);
        deleteAccountActivity.deleteText = (TextView) Utils.findRequiredViewAsType(view, R.id.delete_text, "field 'deleteText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        DeleteAccountActivity deleteAccountActivity = this.target;
        if (deleteAccountActivity != null) {
            this.target = null;
            deleteAccountActivity.howCancelPremiumText = null;
            deleteAccountActivity.uaAccountDeleteText = null;
            deleteAccountActivity.fixAccountText = null;
            deleteAccountActivity.fbLoginText = null;
            deleteAccountActivity.fbButton = null;
            deleteAccountActivity.forgotPasswordText = null;
            deleteAccountActivity.deleteAccountButton = null;
            deleteAccountActivity.passwordInput = null;
            deleteAccountActivity.enterPasswordText = null;
            deleteAccountActivity.fbButtonText = null;
            deleteAccountActivity.exportInformationText = null;
            deleteAccountActivity.deleteText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
