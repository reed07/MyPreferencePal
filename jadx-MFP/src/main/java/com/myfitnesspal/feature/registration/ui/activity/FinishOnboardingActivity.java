package com.myfitnesspal.feature.registration.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.exception.RegistrationError;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.service.SignInService;
import com.myfitnesspal.feature.registration.task.SignInTask;
import com.myfitnesspal.feature.registration.task.SignInTask.CompletedEvent;
import com.myfitnesspal.feature.registration.ui.fragment.LoginUserPassFragment;
import com.myfitnesspal.feature.registration.ui.fragment.LoginUserPassFragment.EventListener;
import com.myfitnesspal.feature.registration.ui.fragment.LoginUserPassFragment.Mode;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.mixin.PleaseWaitMixin;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.squareup.otto.Subscribe;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import javax.inject.Inject;

public class FinishOnboardingActivity extends MfpActivity implements EventListener {
    private static final String ERROR_OFFLINE = "offline";
    private static final String EXTRA_EMAIL_ADDRESS = "extra_email_address";
    private static final String FRAGMENT_TAG = "login_sso_user_pass_fragment";
    private static final String GENERIC_ERROR_DIALOG_TAG = "generic_error_dialog";
    private static final int LOGIN_FACEBOOK = 101;
    private static final String LOGIN_FAILED_DIALOG_TAG = "login_failed_dialog";
    private static final int LOGIN_STANDARD = 100;
    public static int RESULT_LOGGED_IN_TO_NON_VERTICAL = 3;
    public static int RESULT_LOGGED_IN_TO_VERTICAL = 2;
    private LoginUserPassFragment fragment;
    @Inject
    Lazy<LoginModel> loginModel;
    @Inject
    Lazy<SignInService> signInService;

    public boolean shouldDisplayAds() {
        return false;
    }

    public static Intent newStartIntent(Context context, String str) {
        return new Intent(context, FinishOnboardingActivity.class).putExtra("extra_email_address", str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        registerMixin(new PleaseWaitMixin(this));
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_container);
        component().inject(this);
        if (bundle == null) {
            this.fragment = LoginUserPassFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, this.fragment, FRAGMENT_TAG).commit();
        } else {
            this.fragment = (LoginUserPassFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }
        this.fragment.setMode(Mode.FinishOnboarding);
        this.fragment.setListener(this);
        setResult(0);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 186) {
            if (i2 == -1) {
                Bundle extras = intent.getExtras();
                boolean z = AccessToken.getCurrentAccessToken() != null;
                String string = BundleUtils.getString(extras, FacebookLoginActivity.EXTRA_FACEBOOK_EMAIL_ADDRESS, (String) null);
                if (BundleUtils.getInt(extras, FacebookLoginActivity.EXTRA_UI_MODE, 0) != 0) {
                    throw new IllegalStateException("FacebookLoginActivity shouldn't be in any other ui mode when started from this activity");
                } else if (!z || !Strings.notEmpty(string)) {
                    getNavigationHelper().withIntent(FacebookLoginActivity.getStartIntentForSignup(this)).startActivity(RequestCodes.FACEBOOK_LOGIN);
                } else {
                    startSignIn(101, string, string);
                }
            } else {
                getAnalyticsService().reportEvent(Events.FACEBOOK_CONNECT_FAILED_OR_CANCELLED);
            }
        }
    }

    public void onForgotPasswordClicked() {
        getNavigationHelper().withIntent(ForgotPasswordActivity.newStartIntent(this)).startActivity();
    }

    public void onSignInWithFacebookClicked() {
        getAnalyticsService().reportEvent(Events.WELCOME_FACEBOOKBTN_CLICK);
        startActivityForResult(FacebookLoginActivity.getStartIntentForLogin(this), RequestCodes.FACEBOOK_LOGIN);
    }

    public void onSignUpWithFacebookClicked() {
        throw new UacfNotImplementedException();
    }

    public void onSignInButtonClicked(String str, String str2) {
        startSignIn(100, str, str2);
    }

    public void onSignUpWithEmailAtEndOfFlowClicked() {
        throw new UacfNotImplementedException();
    }

    @Subscribe
    public void onSignInTaskFinished(CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            setResult(RESULT_LOGGED_IN_TO_VERTICAL);
            finish();
            return;
        }
        RegistrationException registrationException = (RegistrationException) completedEvent.getFailure();
        if (registrationException.getReason() == RegistrationError.NoVerticalAccount) {
            setResult(RESULT_LOGGED_IN_TO_NON_VERTICAL, new Intent().putExtra("password", completedEvent.getPassword()));
            finish();
        } else if (registrationException.getReason() != RegistrationError.InvalidToken) {
            showLoginFailedDialog(registrationException.getReason().toString());
        } else if (ConnectivityUtil.isOffline().booleanValue()) {
            showLoginFailedDialog(ERROR_OFFLINE);
        } else {
            showLoginFailedDialog(R.string.login_error, R.string.auth_invalid_email_or_password);
        }
    }

    private void startSignIn(int i, String str, String str2) {
        ((PleaseWaitMixin) mixin(PleaseWaitMixin.class)).showPleaseWait();
        Session session = getSession();
        (i == 101 ? new SignInTask(session, this.signInService, str, ((LoginModel) this.loginModel.get()).getFacebookData()) : new SignInTask(session, this.signInService, str, str2)).setDedupeMode(DedupeMode.CancelExisting).setCacheMode(CacheMode.None).run(getRunner());
    }

    private void reportLoginFailure(String str) {
        getAnalyticsService().reportEvent(Events.LOGIN_UNSUCCESSFUL_ERROR_SEE, MapUtil.createMap("reason", str));
    }

    private void showLoginFailedDialog(int i, int i2) {
        ((PleaseWaitMixin) mixin(PleaseWaitMixin.class)).hidePleaseWait();
        reportLoginFailure(getString(i2));
        AlertDialogFragment newInstance = AlertDialogFragment.newInstance();
        ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setTitle(i)).setMessage(i2)).setPositiveText(R.string.dismiss, null);
        newInstance.setCancelable(false);
        showDialogFragment(newInstance, GENERIC_ERROR_DIALOG_TAG);
    }

    private void showLoginFailedDialog(String str) {
        ((PleaseWaitMixin) mixin(PleaseWaitMixin.class)).hidePleaseWait();
        reportLoginFailure(str);
        AlertDialogFragment newInstance = AlertDialogFragment.newInstance();
        ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setTitle(R.string.login_failed)).setMessage(getString(R.string.unable_to_connect_to_server_for_login))).setNegativeText(R.string.close, null);
        newInstance.setCancelable(false);
        showDialogFragment(newInstance, LOGIN_FAILED_DIALOG_TAG);
    }
}
