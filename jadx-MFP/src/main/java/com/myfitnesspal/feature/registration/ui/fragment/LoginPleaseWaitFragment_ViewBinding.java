package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class LoginPleaseWaitFragment_ViewBinding implements Unbinder {
    private LoginPleaseWaitFragment target;

    @UiThread
    public LoginPleaseWaitFragment_ViewBinding(LoginPleaseWaitFragment loginPleaseWaitFragment, View view) {
        this.target = loginPleaseWaitFragment;
        loginPleaseWaitFragment.textView = (TextView) Utils.findRequiredViewAsType(view, R.id.please_wait_message, "field 'textView'", TextView.class);
        loginPleaseWaitFragment.progressView = Utils.findRequiredView(view, R.id.please_wait_progress, "field 'progressView'");
    }

    @CallSuper
    public void unbind() {
        LoginPleaseWaitFragment loginPleaseWaitFragment = this.target;
        if (loginPleaseWaitFragment != null) {
            this.target = null;
            loginPleaseWaitFragment.textView = null;
            loginPleaseWaitFragment.progressView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
