package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.task.SignUpTask;
import com.myfitnesspal.feature.registration.task.SignUpTask.CompletedEvent;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.event.UpdateGoalsSetEvent;
import com.myfitnesspal.shared.model.User;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import javax.inject.Inject;

public class SignUpMarketingOptInFragment extends SignUpFragmentBase {
    private static final String COUNTRY_CANADA = "Canada";
    private static final String EXTRA_OPT_IN = "extra_opt_in";
    public static final String MODE_EXTRA = "marketing_opt_in_mode";
    private static final String REACTIVATION_DO_NOT_RECEIVE_EMAILS_TAPPED = "reactivation_do_not_receive_emails_button_tapped";
    private static final String REACTIVATION_INTERSTITIAL_DISPLAYED = "reactivation_marketing_optin_interstitial_displayed";
    private static final String REACTIVATION_RECEIVE_EMAILS_TAPPED = "reactivation_receive_emails_button_tapped";
    private static final String SIGN_UP_ERROR_CODE = "sign_up_error_code";
    private static final String SIGN_UP_ERROR_STRING = "sign_up_error_string";
    @BindView(2131362330)
    TextView disclaimerCanada;
    private boolean isSignUpMode;
    @Inject
    LoginModel loginModel;
    private Mode mode;
    private boolean optIn;
    @BindView(2131363597)
    Button sendMeEmails;
    @Inject
    SignUpModel signUpModel;
    @Inject
    Lazy<SignUpService> signUpService;
    @BindView(2131363667)
    TextView skip;
    @BindView(2131364073)
    TextView uaAddress;
    @BindView(2131364224)
    TextView withdrawCanada;

    public enum Mode {
        SignUp,
        Reactivation
    }

    public boolean shouldShowNextButtonInToolbar() {
        return false;
    }

    public void validate() {
    }

    public static SignUpMarketingOptInFragment newInstance(Mode mode2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MODE_EXTRA, mode2);
        SignUpMarketingOptInFragment signUpMarketingOptInFragment = new SignUpMarketingOptInFragment();
        signUpMarketingOptInFragment.setArguments(bundle);
        return signUpMarketingOptInFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.marketing_opt_in, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mode = (Mode) BundleUtils.getSerializable(getArguments(), MODE_EXTRA, Mode.class.getClassLoader());
        this.isSignUpMode = this.mode == Mode.SignUp;
        setTitle(this.isSignUpMode ? R.string.emailTxt : R.string.subscriptions, new Object[0]);
        if (!this.isSignUpMode) {
            getAnalyticsService().reportEvent(REACTIVATION_INTERSTITIAL_DISPLAYED);
        }
        if (bundle != null) {
            this.optIn = bundle.getBoolean(EXTRA_OPT_IN);
        }
        checkForCanadaAndSetWording();
        initListeners();
    }

    private void checkForCanadaAndSetWording() {
        boolean z = (!this.isSignUpMode || !Strings.equals(this.signUpModel.getCountryName(), COUNTRY_CANADA)) ? !this.isSignUpMode && Strings.equals(getSession().getUser().getProfile().getCountryName(), getString(R.string.country_CA)) : true;
        if (z) {
            this.sendMeEmails.setText(R.string.yes_send_me_emails_canada);
            this.skip.setText(R.string.dont_send_emails_canada);
            SignUpUtil.setupCanadaDisclaimerText(this.disclaimerCanada, getNavigationHelper(), R.string.joining_terms_disclaimer_canada);
        }
        ViewUtils.setVisible(z, this.withdrawCanada, this.disclaimerCanada, this.uaAddress);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_OPT_IN, this.optIn);
    }

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        if (this.isSignUpMode) {
            return Screens.SIGN_UP_MARKETING_OPT_IN;
        }
        return null;
    }

    private void initListeners() {
        this.sendMeEmails.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignUpMarketingOptInFragment.this.processOptInAndMoveAhead(true);
            }
        });
        this.skip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignUpMarketingOptInFragment.this.processOptInAndMoveAhead(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void processOptInAndMoveAhead(boolean z) {
        this.optIn = z;
        if (this.isSignUpMode) {
            startRegistration();
            return;
        }
        updateOptInUserProperty();
        getAnalyticsService().reportEvent(this.optIn ? REACTIVATION_RECEIVE_EMAILS_TAPPED : REACTIVATION_DO_NOT_RECEIVE_EMAILS_TAPPED);
        postEvent(new UpdateGoalsSetEvent());
    }

    private void startRegistration() {
        showProgressDialog(R.string.creating_account);
        new SignUpTask((SignUpService) this.signUpService.get(), this.signUpModel, this.loginModel).setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.UseExisting).run(getRunner());
    }

    private void updateOptInUserProperty() {
        User user = getSession().getUser();
        boolean z = this.optIn;
        user.setShouldUpdateGoalsAndUpdateProperty(false);
        user.updateNewsletterSettings(z);
        if (this.isSignUpMode) {
            getAnalyticsService().reportEvent(z ? Events.OPTED_IN : Events.OPT_OUT);
        }
    }

    @Subscribe
    public void onSignUpCompleted(CompletedEvent completedEvent) {
        String str;
        hideProgressDialog();
        if (completedEvent.successful()) {
            updateOptInUserProperty();
            onValidated();
            return;
        }
        switch (((RegistrationException) completedEvent.getFailure()).getReason()) {
            case SyncFailure:
                str = getString(R.string.unable_to_create_account);
                break;
            case DeviceOffline:
                str = getString(R.string.offline_msg);
                break;
            default:
                str = getString(R.string.error_during_registration);
                break;
        }
        getAnalyticsService().reportEvent(Events.SIGN_UP_FAILED, MapUtil.createMap(SIGN_UP_ERROR_STRING, str, SIGN_UP_ERROR_CODE, String.valueOf(((RegistrationException) completedEvent.getFailure()).getReason())));
        showErrorDialog(str);
    }
}
