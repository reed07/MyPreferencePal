package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SignUpActivityLevelFragment_ViewBinding implements Unbinder {
    private SignUpActivityLevelFragment target;

    @UiThread
    public SignUpActivityLevelFragment_ViewBinding(SignUpActivityLevelFragment signUpActivityLevelFragment, View view) {
        this.target = signUpActivityLevelFragment;
        signUpActivityLevelFragment.activityLevelQuestionSubtext = view.findViewById(R.id.activity_level_question_subtext);
        signUpActivityLevelFragment.radNotActive = (RadioButton) Utils.findOptionalViewAsType(view, R.id.radioNotActive, "field 'radNotActive'", RadioButton.class);
        signUpActivityLevelFragment.notActiveContainer = view.findViewById(R.id.not_active_container);
        signUpActivityLevelFragment.radLightActive = (RadioButton) Utils.findOptionalViewAsType(view, R.id.radioLightActive, "field 'radLightActive'", RadioButton.class);
        signUpActivityLevelFragment.lightActiveContainer = view.findViewById(R.id.light_active_container);
        signUpActivityLevelFragment.radActive = (RadioButton) Utils.findOptionalViewAsType(view, R.id.radioActive, "field 'radActive'", RadioButton.class);
        signUpActivityLevelFragment.activeContainer = view.findViewById(R.id.active_container);
        signUpActivityLevelFragment.radVeryActive = (RadioButton) Utils.findOptionalViewAsType(view, R.id.radioVeryActive, "field 'radVeryActive'", RadioButton.class);
        signUpActivityLevelFragment.veryActiveContainer = view.findViewById(R.id.very_active_container);
    }

    @CallSuper
    public void unbind() {
        SignUpActivityLevelFragment signUpActivityLevelFragment = this.target;
        if (signUpActivityLevelFragment != null) {
            this.target = null;
            signUpActivityLevelFragment.activityLevelQuestionSubtext = null;
            signUpActivityLevelFragment.radNotActive = null;
            signUpActivityLevelFragment.notActiveContainer = null;
            signUpActivityLevelFragment.radLightActive = null;
            signUpActivityLevelFragment.lightActiveContainer = null;
            signUpActivityLevelFragment.radActive = null;
            signUpActivityLevelFragment.activeContainer = null;
            signUpActivityLevelFragment.radVeryActive = null;
            signUpActivityLevelFragment.veryActiveContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
