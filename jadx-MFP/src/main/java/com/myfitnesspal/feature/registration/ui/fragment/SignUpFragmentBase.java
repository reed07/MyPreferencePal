package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity;
import com.myfitnesspal.feature.registration.ui.view.PageIndicatorBar;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;

public abstract class SignUpFragmentBase extends MfpFragment {
    private static final String ERROR_DIALOG_TAG = "generic_error_dialog";
    private static final HashMap<Class<?>, Integer> FRAGMENT_TO_POSITION = new HashMap<>();
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog";
    private static final int PROGRESS_STEP_COUNT = 7;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    SignUpService signUpService;

    /* access modifiers changed from: protected */
    public abstract String getAnalyticsScreenName();

    public void onNavigatedBack() {
    }

    public boolean shouldShowNextButtonInToolbar() {
        return true;
    }

    public abstract void validate();

    static {
        FRAGMENT_TO_POSITION.put(SignUpGoalTypeFragment.class, Integer.valueOf(1));
        FRAGMENT_TO_POSITION.put(SignUpActivityLevelFragment.class, Integer.valueOf(2));
        FRAGMENT_TO_POSITION.put(SignUpGenderAgeFragment.class, Integer.valueOf(3));
        FRAGMENT_TO_POSITION.put(SignUpWeightHeightFragment.class, Integer.valueOf(4));
        FRAGMENT_TO_POSITION.put(SignUpWeeklyWeightGoalFragment.class, Integer.valueOf(5));
        FRAGMENT_TO_POSITION.put(SignUpUsernamePasswordEmailFragment.class, Integer.valueOf(6));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle == null) {
            sendScreenViewAnalytics();
        }
        updateProgressIndicatorBar(this, (PageIndicatorBar) ViewUtils.findById(getView(), R.id.page_indicator));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    /* access modifiers changed from: protected */
    public final void onValidated() {
        ((SignUpActivity) getActivity()).onStepValidated();
    }

    /* access modifiers changed from: protected */
    public void sendScreenViewAnalytics() {
        String analyticsScreenName = getAnalyticsScreenName();
        if (Strings.notEmpty(analyticsScreenName)) {
            getAnalyticsService().reportEvent(analyticsScreenName);
        }
    }

    /* access modifiers changed from: protected */
    public final void showErrorDialog(int i) {
        showErrorDialog(getString(i));
    }

    /* access modifiers changed from: protected */
    public final void showErrorDialog(String str) {
        showErrorDialog(str, null);
    }

    /* access modifiers changed from: protected */
    public final void showErrorDialog(String str, DialogPositiveListener dialogPositiveListener) {
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) getFragmentManager().findFragmentByTag(ERROR_DIALOG_TAG);
        if (alertDialogFragment != null) {
            alertDialogFragment.dismiss();
        }
        showDialogFragment((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setMessage(str)).setPositiveText(R.string.ok, null)).setTitle(R.string.error), ERROR_DIALOG_TAG);
    }

    /* access modifiers changed from: protected */
    public final void showProgressDialog(int i) {
        if (((ProgressDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag("progress_dialog")) == null) {
            showDialogFragment(ProgressDialogFragment.newInstance(i), "progress_dialog");
        }
    }

    /* access modifiers changed from: protected */
    public final void hideProgressDialog() {
        ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag("progress_dialog");
        if (progressDialogFragment != null) {
            progressDialogFragment.dismiss();
        }
    }

    private void updateProgressIndicatorBar(SignUpFragmentBase signUpFragmentBase, PageIndicatorBar pageIndicatorBar) {
        if (signUpFragmentBase != null && pageIndicatorBar != null) {
            if (FRAGMENT_TO_POSITION.containsKey(signUpFragmentBase.getClass())) {
                pageIndicatorBar.setProgressAndMax(((Integer) FRAGMENT_TO_POSITION.get(signUpFragmentBase.getClass())).intValue(), 7);
            } else {
                pageIndicatorBar.setVisibility(8);
            }
        }
    }
}
