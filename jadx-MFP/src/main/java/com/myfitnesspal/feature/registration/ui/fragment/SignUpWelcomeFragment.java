package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;

public class SignUpWelcomeFragment extends SignUpFragmentBase {
    @BindView(2131362217)
    View continueButton;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return Screens.SIGN_UP_SSO_WELCOME;
    }

    public boolean shouldShowNextButtonInToolbar() {
        return false;
    }

    public static SignUpWelcomeFragment newInstance() {
        return new SignUpWelcomeFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sign_up_sso_welcome, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.continueButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignUpWelcomeFragment.this.onValidated();
            }
        });
        return inflate;
    }

    public void onResume() {
        super.onResume();
        setTitle(R.string.auth_sign_up_get_started, new Object[0]);
    }

    public void validate() {
        onValidated();
    }
}
