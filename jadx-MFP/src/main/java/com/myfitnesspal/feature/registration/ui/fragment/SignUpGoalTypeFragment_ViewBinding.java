package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SignUpGoalTypeFragment_ViewBinding implements Unbinder {
    private SignUpGoalTypeFragment target;

    @UiThread
    public SignUpGoalTypeFragment_ViewBinding(SignUpGoalTypeFragment signUpGoalTypeFragment, View view) {
        this.target = signUpGoalTypeFragment;
        signUpGoalTypeFragment.lose = (RadioButton) Utils.findRequiredViewAsType(view, R.id.lose, "field 'lose'", RadioButton.class);
        signUpGoalTypeFragment.maintain = (RadioButton) Utils.findRequiredViewAsType(view, R.id.maintain, "field 'maintain'", RadioButton.class);
        signUpGoalTypeFragment.gain = (RadioButton) Utils.findRequiredViewAsType(view, R.id.gain, "field 'gain'", RadioButton.class);
    }

    @CallSuper
    public void unbind() {
        SignUpGoalTypeFragment signUpGoalTypeFragment = this.target;
        if (signUpGoalTypeFragment != null) {
            this.target = null;
            signUpGoalTypeFragment.lose = null;
            signUpGoalTypeFragment.maintain = null;
            signUpGoalTypeFragment.gain = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
