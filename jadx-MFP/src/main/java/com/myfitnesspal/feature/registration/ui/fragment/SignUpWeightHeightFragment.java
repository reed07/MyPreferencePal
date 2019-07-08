package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.event.SignUpHeightDialogCompleteEvent;
import com.myfitnesspal.feature.registration.event.SignUpWeightDialogCompleteEvent;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.ui.dialog.SignUpHeightDialogFragment;
import com.myfitnesspal.feature.registration.ui.dialog.SignUpWeightDialogFragment;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Registration;
import com.myfitnesspal.shared.model.unitconv.LocalizedLength;
import com.myfitnesspal.shared.model.unitconv.MajorMinorUnits;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;

public class SignUpWeightHeightFragment extends SignUpFragmentBase {
    @BindView(2131362736)
    EditText height;
    @Inject
    SignUpModel model;
    @BindView(2131362254)
    EditText weight;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return Screens.SIGN_UP_HEIGHT_WEIGHT;
    }

    public static SignUpWeightHeightFragment newInstance() {
        return new SignUpWeightHeightFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.sign_up_height_weight, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setTitle(R.string.you_info, new Object[0]);
        setListeners();
    }

    public void onResume() {
        super.onResume();
        populateHeight();
        populateWeight();
    }

    public void validate() {
        if (this.model.getCurrentHeight().isZero()) {
            showErrorDialog(getString(R.string.enter_valid_height));
        } else if (this.model.getCurrentWeight().isZero()) {
            showErrorDialog(getString(R.string.enter_valid_weight));
        } else {
            getImmHelper().hideSoftInput();
            if (Registration.MAINTAIN.equals(this.model.getGoalType())) {
                SignUpModel signUpModel = this.model;
                signUpModel.setGoalWeight(signUpModel.getCurrentWeight());
            }
            onValidated();
        }
    }

    @Subscribe
    public void onSignUpWeightDialogCompleteEvent(SignUpWeightDialogCompleteEvent signUpWeightDialogCompleteEvent) {
        populateWeight();
    }

    @Subscribe
    public void onSignUpHeightDialogCompleteEvent(SignUpHeightDialogCompleteEvent signUpHeightDialogCompleteEvent) {
        populateHeight();
    }

    private void setListeners() {
        this.height.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignUpWeightHeightFragment.this.showDialogFragment(SignUpHeightDialogFragment.newInstance(), Fragments.CURRENT_HEIGHT_DIALOG);
            }
        });
        this.weight.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignUpWeightHeightFragment.this.showDialogFragment(SignUpWeightDialogFragment.newInstance(WeightType.CURRENT), Fragments.CURRENT_WEIGHT_DIALOG);
            }
        });
    }

    private void populateHeight() {
        String str;
        Length heightUnit = this.model.getHeightUnit();
        LocalizedLength currentHeight = this.model.getCurrentHeight();
        if (heightUnit == Length.FEET_INCHES) {
            MajorMinorUnits majorMinorUnits = LocalizedLength.getMajorMinorUnits(currentHeight, Length.FEET_INCHES);
            str = String.format(getString(R.string.ft_in_format), new Object[]{majorMinorUnits.getMajorDisplayValue(), majorMinorUnits.getMinorDisplayValue()});
        } else {
            str = LocalizedLength.getDisplayString(getContext(), currentHeight, heightUnit);
        }
        if (currentHeight.getValue(Length.INCHES) <= 0.0d) {
            this.height.setHint(str);
        } else {
            this.height.setText(str);
        }
    }

    private void populateWeight() {
        SignUpUtil.setWeightOrHint(this.weight, (float) this.model.getCurrentWeight().getValue(Weight.POUNDS), this.model.getWeightUnit());
    }
}
