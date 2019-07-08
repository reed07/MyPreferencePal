package com.myfitnesspal.shared.ui.activity.impl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class DisconnectFacebook_ViewBinding implements Unbinder {
    private DisconnectFacebook target;

    @UiThread
    public DisconnectFacebook_ViewBinding(DisconnectFacebook disconnectFacebook) {
        this(disconnectFacebook, disconnectFacebook.getWindow().getDecorView());
    }

    @UiThread
    public DisconnectFacebook_ViewBinding(DisconnectFacebook disconnectFacebook, View view) {
        this.target = disconnectFacebook;
        disconnectFacebook.passwordField = (EditText) Utils.findRequiredViewAsType(view, R.id.password_field, "field 'passwordField'", EditText.class);
        disconnectFacebook.confirmField = (EditText) Utils.findRequiredViewAsType(view, R.id.confirm_password_field, "field 'confirmField'", EditText.class);
        disconnectFacebook.switcher = (ViewSwitcher) Utils.findRequiredViewAsType(view, R.id.switcher, "field 'switcher'", ViewSwitcher.class);
        disconnectFacebook.errorText = (TextView) Utils.findRequiredViewAsType(view, R.id.txt_error, "field 'errorText'", TextView.class);
        disconnectFacebook.btnContinue = (Button) Utils.findRequiredViewAsType(view, R.id.btn_continue, "field 'btnContinue'", Button.class);
    }

    @CallSuper
    public void unbind() {
        DisconnectFacebook disconnectFacebook = this.target;
        if (disconnectFacebook != null) {
            this.target = null;
            disconnectFacebook.passwordField = null;
            disconnectFacebook.confirmField = null;
            disconnectFacebook.switcher = null;
            disconnectFacebook.errorText = null;
            disconnectFacebook.btnContinue = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
