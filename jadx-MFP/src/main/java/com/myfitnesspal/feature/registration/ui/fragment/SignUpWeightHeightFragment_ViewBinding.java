package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SignUpWeightHeightFragment_ViewBinding implements Unbinder {
    private SignUpWeightHeightFragment target;

    @UiThread
    public SignUpWeightHeightFragment_ViewBinding(SignUpWeightHeightFragment signUpWeightHeightFragment, View view) {
        this.target = signUpWeightHeightFragment;
        signUpWeightHeightFragment.weight = (EditText) Utils.findRequiredViewAsType(view, R.id.current_weight, "field 'weight'", EditText.class);
        signUpWeightHeightFragment.height = (EditText) Utils.findRequiredViewAsType(view, R.id.height, "field 'height'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        SignUpWeightHeightFragment signUpWeightHeightFragment = this.target;
        if (signUpWeightHeightFragment != null) {
            this.target = null;
            signUpWeightHeightFragment.weight = null;
            signUpWeightHeightFragment.height = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
