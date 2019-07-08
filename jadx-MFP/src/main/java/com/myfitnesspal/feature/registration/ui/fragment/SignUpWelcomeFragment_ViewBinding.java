package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SignUpWelcomeFragment_ViewBinding implements Unbinder {
    private SignUpWelcomeFragment target;

    @UiThread
    public SignUpWelcomeFragment_ViewBinding(SignUpWelcomeFragment signUpWelcomeFragment, View view) {
        this.target = signUpWelcomeFragment;
        signUpWelcomeFragment.continueButton = Utils.findRequiredView(view, R.id.continue_button, "field 'continueButton'");
    }

    @CallSuper
    public void unbind() {
        SignUpWelcomeFragment signUpWelcomeFragment = this.target;
        if (signUpWelcomeFragment != null) {
            this.target = null;
            signUpWelcomeFragment.continueButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
