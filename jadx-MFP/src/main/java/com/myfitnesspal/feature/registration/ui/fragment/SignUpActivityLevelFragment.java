package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Exercise.ActivityLevel;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class SignUpActivityLevelFragment extends SignUpFragmentBase {
    @Nullable
    @BindView(2131361854)
    View activeContainer;
    @Nullable
    @BindView(2131361859)
    View activityLevelQuestionSubtext;
    @Nullable
    @BindView(2131362908)
    View lightActiveContainer;
    @Inject
    SignUpModel model;
    @Nullable
    @BindView(2131363161)
    View notActiveContainer;
    @Nullable
    @BindView(2131363367)
    RadioButton radActive;
    @Nullable
    @BindView(2131363368)
    RadioButton radLightActive;
    @Nullable
    @BindView(2131363369)
    RadioButton radNotActive;
    @Nullable
    @BindView(2131363370)
    RadioButton radVeryActive;
    @Nullable
    @BindView(2131364142)
    View veryActiveContainer;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return Screens.SIGN_UP_ACTIVITY_LEVEL;
    }

    public static SignUpActivityLevelFragment newInstance() {
        return new SignUpActivityLevelFragment();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.sign_up_activity_level, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setTitle(R.string.activity_level, new Object[0]);
        initViews(this.model.getActivityLevel());
        initListeners();
    }

    private void initListeners() {
        this.notActiveContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SignUpActivityLevelFragment.this.setSelectionAndAdvance(ActivityLevel.SEDENTARY);
            }
        });
        this.lightActiveContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SignUpActivityLevelFragment.this.setSelectionAndAdvance(ActivityLevel.LIGHTLY_ACTIVE);
            }
        });
        this.activeContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SignUpActivityLevelFragment.this.setSelectionAndAdvance(ActivityLevel.ACTIVE);
            }
        });
        this.veryActiveContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SignUpActivityLevelFragment.this.setSelectionAndAdvance(ActivityLevel.VERY_ACTIVE);
            }
        });
    }

    private void initViews(String str) {
        boolean z = false;
        if (ConfigUtils.shouldUseNewActivityLevelQuestion(getConfigService())) {
            this.activityLevelQuestionSubtext.setVisibility(0);
        }
        RadioButton radioButton = this.radNotActive;
        if (Strings.isEmpty(str) || Strings.equals(str, ActivityLevel.SEDENTARY)) {
            z = true;
        }
        radioButton.setChecked(z);
        this.radLightActive.setChecked(Strings.equals(str, ActivityLevel.LIGHTLY_ACTIVE));
        this.radActive.setChecked(Strings.equals(str, ActivityLevel.ACTIVE));
        this.radVeryActive.setChecked(Strings.equals(str, ActivityLevel.VERY_ACTIVE));
        this.model.setActivityLevel(str);
    }

    /* access modifiers changed from: private */
    public void setSelectionAndAdvance(String str) {
        initViews(str);
        onValidated();
    }

    public void validate() {
        if (this.radNotActive.isChecked()) {
            this.model.setActivityLevel(ActivityLevel.SEDENTARY);
        } else if (this.radLightActive.isChecked()) {
            this.model.setActivityLevel(ActivityLevel.LIGHTLY_ACTIVE);
        } else if (this.radActive.isChecked()) {
            this.model.setActivityLevel(ActivityLevel.ACTIVE);
        } else if (this.radVeryActive.isChecked()) {
            this.model.setActivityLevel(ActivityLevel.VERY_ACTIVE);
        } else {
            showErrorDialog((int) R.string.select_activity_level);
            return;
        }
        onValidated();
    }
}
