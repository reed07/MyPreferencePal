package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.constants.SignUpBmi;
import com.myfitnesspal.feature.registration.event.GoToGoalEvent;
import com.myfitnesspal.feature.registration.event.SignUpWeightDialogCompleteEvent;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.ui.dialog.SignUpWeightDialogFragment;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Registration;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight.FormatStringProvider;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight.FormatStringProvider.ValueDisplay;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import javax.inject.Inject;

public class SignUpWeeklyWeightGoalFragment extends SignUpFragmentBase {
    private static final String CHANGE_GOAL_TYPE_DIALOG = "change_goal_type";
    private static final String EXTRA_BMI_TYPE = "extra_bmi_type";
    private static final String EXTRA_EATING_DISORDER_ERROR = "extra_eating_disorder_error";
    private static double[] GAIN_INCREEMNTS_KILOS = {0.25d, 0.5d};
    private static double[] GAIN_INCREMENTS_POUNDS = {0.5d, 1.0d};
    private static double[] LOSE_INCREMENTS_KILOS = {1.0d, 0.75d, 0.5d, 0.25d};
    private static double[] LOSE_INCREMENTS_POUNDS = {2.0d, 1.5d, 1.0d, 0.5d};
    private SignUpBmi bmiType = SignUpBmi.Normal;
    private String eatingDisorderError = null;
    @Nullable
    @BindView(2131362493)
    TextView edError;
    @BindView(2131362695)
    RadioButton gainIncrement1;
    @BindView(2131362696)
    RadioButton gainIncrement2;
    @BindView(2131362697)
    RadioGroup gainWeeklyGroup;
    @Nullable
    @BindView(2131362717)
    EditText goalWeight;
    @BindView(2131362968)
    RadioButton loseIncrement1;
    @BindView(2131362966)
    RadioButton loseIncrement2;
    @BindView(2131362967)
    RadioButton loseIncrement3;
    @BindView(2131362965)
    RadioButton loseIncrement4;
    @BindView(2131362969)
    RadioGroup loseWeeklyGroup;
    @Inject
    SignUpModel model;
    private DialogPositiveListener onChangeMainGoalDialogClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            SignUpWeeklyWeightGoalFragment.this.postEvent(new GoToGoalEvent());
        }
    };
    private FormatStringProvider weightFormatter;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return Screens.SIGN_UP_WEEKLY_WEIGHT_GOAL;
    }

    public static SignUpWeeklyWeightGoalFragment newInstance() {
        return new SignUpWeeklyWeightGoalFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.sign_up_weekly_weight_loss_goal, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setTitle(R.string.weekly_goal, new Object[0]);
        this.weightFormatter = new FormatStringProvider(getContext(), ValueDisplay.TwoDecimal);
        if (bundle != null) {
            this.eatingDisorderError = bundle.getString(EXTRA_EATING_DISORDER_ERROR);
            this.bmiType = (SignUpBmi) BundleUtils.getSerializable(bundle, EXTRA_BMI_TYPE, SignUpBmi.Normal, SignUpBmi.class.getClassLoader());
        }
        initViews();
        initListeners();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(EXTRA_EATING_DISORDER_ERROR, this.eatingDisorderError);
        bundle.putSerializable(EXTRA_BMI_TYPE, this.bmiType);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!CHANGE_GOAL_TYPE_DIALOG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onChangeMainGoalDialogClickListener);
        return true;
    }

    public void validate() {
        int i = 0;
        double d = this.loseIncrement1.isChecked() ? LOSE_INCREMENTS_POUNDS[0] : this.loseIncrement2.isChecked() ? LOSE_INCREMENTS_POUNDS[1] : this.loseIncrement3.isChecked() ? LOSE_INCREMENTS_POUNDS[2] : this.loseIncrement4.isChecked() ? LOSE_INCREMENTS_POUNDS[3] : this.gainIncrement1.isChecked() ? GAIN_INCREMENTS_POUNDS[0] : this.gainIncrement2.isChecked() ? GAIN_INCREMENTS_POUNDS[1] : 0.0d;
        this.model.setWeeklyWeightGoal(LocalizedWeight.fromPounds(d));
        if (Strings.isEmpty((Object) this.goalWeight.getText())) {
            showErrorDialog((int) R.string.enter_goal_weight);
        } else if (this.model.getWeeklyWeightGoal().isZero()) {
            showErrorDialog((int) R.string.select_weekly_goal);
        } else {
            String goalType = this.model.getGoalType();
            double value = this.model.getCurrentWeight().getValue(Weight.POUNDS);
            double value2 = this.model.getGoalWeight().getValue(Weight.POUNDS);
            if (Strings.equals(goalType, Registration.LOSE)) {
                int i2 = (value2 > value ? 1 : (value2 == value ? 0 : -1));
                if (i2 > 0) {
                    i = R.string.selected_lose_weight_entered_gain;
                } else if (i2 == 0) {
                    i = R.string.selected_lose_weight_entered_equal;
                }
            } else if (Strings.equals(goalType, Registration.GAIN)) {
                if (value2 < value) {
                    i = R.string.selected_gain_weight_entered_loss;
                } else if (value2 == value) {
                    i = R.string.selected_gain_weight_entered_equal;
                }
            }
            if (i != 0) {
                showDialogFragment((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.alert)).setMessage(i)).setPositiveText(R.string.auth_sign_up_change_goal_type, this.onChangeMainGoalDialogClickListener)).setNegativeText(R.string.auth_sign_up_change_goal_weight, null), CHANGE_GOAL_TYPE_DIALOG);
                return;
            }
            this.model.setBmiType(this.bmiType);
            onValidated();
        }
    }

    @Subscribe
    public void onSignUpWeightDialogCompleteEvent(SignUpWeightDialogCompleteEvent signUpWeightDialogCompleteEvent) {
        this.eatingDisorderError = signUpWeightDialogCompleteEvent.getString();
        this.bmiType = signUpWeightDialogCompleteEvent.getBmiType();
        updateEatingDisorderVisibility();
        populateGoalAmountRadioButtons();
        populateGoalWeight();
    }

    private void populateGoalAmountRadioButtons() {
        String goalType = this.model.getGoalType();
        if (Strings.equals(goalType, Registration.LOSE)) {
            this.loseWeeklyGroup.setVisibility(0);
            this.gainWeeklyGroup.setVisibility(8);
            double[] dArr = this.model.getWeightUnit() == Weight.KILOGRAMS ? LOSE_INCREMENTS_KILOS : LOSE_INCREMENTS_POUNDS;
            setWeightDeltaLabel(this.loseIncrement1, R.string.sign_up_lose_increment, dArr[0]);
            setWeightDeltaLabel(this.loseIncrement2, R.string.sign_up_lose_increment, dArr[1]);
            setWeightDeltaLabel(this.loseIncrement3, R.string.sign_up_lose_increment_recommended, dArr[2]);
            setWeightDeltaLabel(this.loseIncrement4, R.string.sign_up_lose_increment, dArr[3]);
        } else if (Strings.equals(goalType, Registration.GAIN)) {
            this.loseWeeklyGroup.setVisibility(8);
            this.gainWeeklyGroup.setVisibility(0);
            double[] dArr2 = this.model.getWeightUnit() == Weight.KILOGRAMS ? GAIN_INCREEMNTS_KILOS : GAIN_INCREMENTS_POUNDS;
            setWeightDeltaLabel(this.gainIncrement1, R.string.sign_up_gain_increment, dArr2[0]);
            setWeightDeltaLabel(this.gainIncrement2, R.string.sign_up_gain_increment, dArr2[1]);
        }
    }

    private void selectGoalRadioButton() {
        String goalType = this.model.getGoalType();
        double value = this.model.getWeeklyWeightGoal().getValue(Weight.POUNDS);
        if (Strings.equals(goalType, Registration.LOSE)) {
            double[] dArr = LOSE_INCREMENTS_POUNDS;
            if (value == dArr[0]) {
                this.loseIncrement1.setChecked(true);
            } else if (value == dArr[1]) {
                this.loseIncrement2.setChecked(true);
            } else if (value == dArr[2]) {
                this.loseIncrement3.setChecked(true);
            } else if (value == dArr[3]) {
                this.loseIncrement4.setChecked(true);
            } else {
                this.loseIncrement3.setChecked(true);
            }
        } else if (Strings.equals(goalType, Registration.GAIN)) {
            if (value == GAIN_INCREMENTS_POUNDS[0]) {
                this.gainIncrement1.setChecked(true);
            }
            if (value == GAIN_INCREMENTS_POUNDS[1]) {
                this.gainIncrement2.setChecked(true);
            } else {
                this.gainIncrement1.setChecked(true);
            }
        }
    }

    private void updateEatingDisorderVisibility() {
        if (Strings.notEmpty(this.eatingDisorderError)) {
            this.edError.setText(this.eatingDisorderError);
            ViewUtils.setVisible(this.edError);
            return;
        }
        ViewUtils.setVisible(false, this.edError);
    }

    private void initViews() {
        updateEatingDisorderVisibility();
        populateGoalWeight();
        populateGoalAmountRadioButtons();
        selectGoalRadioButton();
    }

    private void initListeners() {
        this.goalWeight.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignUpWeeklyWeightGoalFragment.this.showDialogFragment(SignUpWeightDialogFragment.newInstance(WeightType.GOAL), Fragments.GOAL_WEIGHT_DIALOG);
            }
        });
        AnonymousClass2 r0 = new OnClickListener() {
            public void onClick(View view) {
                ViewUtils.selectRadioButton(SignUpWeeklyWeightGoalFragment.this.getView(), view.getId());
            }
        };
        this.loseIncrement2.setOnClickListener(r0);
        this.loseIncrement3.setOnClickListener(r0);
        this.loseIncrement1.setOnClickListener(r0);
        this.loseIncrement4.setOnClickListener(r0);
    }

    private void setWeightDeltaLabel(TextView textView, int i, double d) {
        Weight weightUnit = this.model.getWeightUnit();
        if (weightUnit == Weight.STONES_POUNDS || weightUnit == Weight.STONES) {
            weightUnit = Weight.POUNDS;
        }
        LocalizedWeight from = LocalizedWeight.from(d, weightUnit);
        if (i == R.string.sign_up_lose_increment_recommended) {
            textView.setText(setColorForRecommendedText(getString(i, LocalizedWeight.getDisplayString(this.weightFormatter, from, weightUnit))));
            return;
        }
        textView.setText(getString(i, LocalizedWeight.getDisplayString(this.weightFormatter, from, weightUnit)));
    }

    @NonNull
    private SpannableStringBuilder setColorForRecommendedText(@NonNull String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_secondary_text)), str.indexOf("\n") + 1, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    private void populateGoalWeight() {
        SignUpUtil.setWeightOrHint(this.goalWeight, (float) this.model.getGoalWeight().getValue(Weight.POUNDS), this.model.getWeightUnit());
    }
}
