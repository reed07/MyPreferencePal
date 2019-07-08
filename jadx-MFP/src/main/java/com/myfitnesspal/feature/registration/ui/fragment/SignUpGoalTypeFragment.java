package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Registration;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class SignUpGoalTypeFragment extends SignUpFragmentBase {
    @BindView(2131362694)
    RadioButton gain;
    @BindView(2131362964)
    RadioButton lose;
    @BindView(2131362990)
    RadioButton maintain;
    @Inject
    SignUpModel model;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return Screens.SIGN_UP_WEIGHT_GOAL;
    }

    public static SignUpGoalTypeFragment newInstance() {
        return new SignUpGoalTypeFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.sign_up_goal, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setTitle(R.string.weight_goal, new Object[0]);
        initViews();
        initListeners();
    }

    public void validate() {
        boolean z = true;
        if (this.lose.isChecked()) {
            this.model.setGoalType(Registration.LOSE);
        } else if (this.maintain.isChecked()) {
            this.model.setGoalType(Registration.MAINTAIN);
            this.model.setWeeklyWeightGoal(LocalizedWeight.fromPounds(0.0d));
        } else if (this.gain.isChecked()) {
            this.model.setGoalType(Registration.GAIN);
        } else {
            z = false;
        }
        if (z) {
            onValidated();
        } else {
            showErrorDialog((int) R.string.auth_sign_up_select_goal);
        }
    }

    private void initViews() {
        String goalType = this.model.getGoalType();
        if (goalType == null) {
            return;
        }
        if (Strings.equals(goalType, Registration.LOSE)) {
            this.lose.setChecked(true);
        } else if (Strings.equals(goalType, Registration.MAINTAIN)) {
            this.maintain.setChecked(true);
        } else if (Strings.equals(goalType, Registration.GAIN)) {
            this.gain.setChecked(true);
        }
    }

    private void initListeners() {
        this.lose.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SignUpGoalTypeFragment.lambda$initListeners$0(SignUpGoalTypeFragment.this, view);
            }
        });
        this.maintain.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SignUpGoalTypeFragment.lambda$initListeners$1(SignUpGoalTypeFragment.this, view);
            }
        });
        this.gain.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SignUpGoalTypeFragment.lambda$initListeners$2(SignUpGoalTypeFragment.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initListeners$0(SignUpGoalTypeFragment signUpGoalTypeFragment, View view) {
        if (signUpGoalTypeFragment.lose.isChecked()) {
            signUpGoalTypeFragment.validate();
        }
    }

    public static /* synthetic */ void lambda$initListeners$1(SignUpGoalTypeFragment signUpGoalTypeFragment, View view) {
        if (signUpGoalTypeFragment.maintain.isChecked()) {
            signUpGoalTypeFragment.validate();
        }
    }

    public static /* synthetic */ void lambda$initListeners$2(SignUpGoalTypeFragment signUpGoalTypeFragment, View view) {
        if (signUpGoalTypeFragment.gain.isChecked()) {
            signUpGoalTypeFragment.validate();
        }
    }
}
