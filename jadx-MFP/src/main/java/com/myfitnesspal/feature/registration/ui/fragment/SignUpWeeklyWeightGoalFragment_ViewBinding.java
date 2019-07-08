package com.myfitnesspal.feature.registration.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SignUpWeeklyWeightGoalFragment_ViewBinding implements Unbinder {
    private SignUpWeeklyWeightGoalFragment target;

    @UiThread
    public SignUpWeeklyWeightGoalFragment_ViewBinding(SignUpWeeklyWeightGoalFragment signUpWeeklyWeightGoalFragment, View view) {
        this.target = signUpWeeklyWeightGoalFragment;
        signUpWeeklyWeightGoalFragment.loseWeeklyGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.lose_weekly_goal_group, "field 'loseWeeklyGroup'", RadioGroup.class);
        signUpWeeklyWeightGoalFragment.loseIncrement1 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.lose_two_pw, "field 'loseIncrement1'", RadioButton.class);
        signUpWeeklyWeightGoalFragment.loseIncrement2 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.lose_one_half_pw, "field 'loseIncrement2'", RadioButton.class);
        signUpWeeklyWeightGoalFragment.loseIncrement3 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.lose_one_pw, "field 'loseIncrement3'", RadioButton.class);
        signUpWeeklyWeightGoalFragment.loseIncrement4 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.lose_half_pw, "field 'loseIncrement4'", RadioButton.class);
        signUpWeeklyWeightGoalFragment.gainWeeklyGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.gain_weekly_goal_group, "field 'gainWeeklyGroup'", RadioGroup.class);
        signUpWeeklyWeightGoalFragment.gainIncrement1 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.gain_half_pw, "field 'gainIncrement1'", RadioButton.class);
        signUpWeeklyWeightGoalFragment.gainIncrement2 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.gain_one_pw, "field 'gainIncrement2'", RadioButton.class);
        signUpWeeklyWeightGoalFragment.goalWeight = (EditText) Utils.findOptionalViewAsType(view, R.id.goal_weight, "field 'goalWeight'", EditText.class);
        signUpWeeklyWeightGoalFragment.edError = (TextView) Utils.findOptionalViewAsType(view, R.id.error, "field 'edError'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SignUpWeeklyWeightGoalFragment signUpWeeklyWeightGoalFragment = this.target;
        if (signUpWeeklyWeightGoalFragment != null) {
            this.target = null;
            signUpWeeklyWeightGoalFragment.loseWeeklyGroup = null;
            signUpWeeklyWeightGoalFragment.loseIncrement1 = null;
            signUpWeeklyWeightGoalFragment.loseIncrement2 = null;
            signUpWeeklyWeightGoalFragment.loseIncrement3 = null;
            signUpWeeklyWeightGoalFragment.loseIncrement4 = null;
            signUpWeeklyWeightGoalFragment.gainWeeklyGroup = null;
            signUpWeeklyWeightGoalFragment.gainIncrement1 = null;
            signUpWeeklyWeightGoalFragment.gainIncrement2 = null;
            signUpWeeklyWeightGoalFragment.goalWeight = null;
            signUpWeeklyWeightGoalFragment.edError = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
