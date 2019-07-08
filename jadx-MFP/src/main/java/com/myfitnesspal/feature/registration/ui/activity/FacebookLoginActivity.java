package com.myfitnesspal.feature.registration.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.event.UnderageRegistrationFailureEvent;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.task.ValidateEmailAddressTask;
import com.myfitnesspal.feature.registration.task.ValidateEmailAddressTask.CompletedEvent;
import com.myfitnesspal.shared.constants.Constants.Facebook.Login;
import com.myfitnesspal.shared.service.facebook.FacebookService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.util.OrientationUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import javax.inject.Inject;

public class FacebookLoginActivity extends MfpActivity {
    private static final String ERROR_DIALOG_FRAGMENT_TAG = "error_dialog_fragment";
    public static final String EXTRA_FACEBOOK_EMAIL_ADDRESS = "extra_facebook_email_address";
    public static final String EXTRA_FACEBOOK_MFP_USERNAME = "extra_facebook_mfp_username";
    public static final String EXTRA_MFP_ACCOUNT_EXISTS = "extra_mfp_account_exists";
    public static final String EXTRA_UACF_ACCOUNT_EXISTS = "extra_uacf_account_exists";
    public static final String EXTRA_UI_MODE = "extra_start_type";
    public static final String EXTRA_VERIFY_ACCESS_TOKEN = "extra_verify_access_token";
    public static final int RESULT_ACCOUNT_EXISTS = 2;
    public static final int RESULT_NO_EMAIL = 3;
    public static final int UI_MODE_LOGIN = 0;
    public static final int UI_MODE_SIGNUP = 1;
    public static final int UI_MODE_VERIFY = 2;
    private CallbackManager callbackManager;
    @Inject
    Lazy<FacebookService> facebookService;
    private FacebookCallback<LoginResult> fbLoginCallback;
    @Inject
    LoginModel loginModel;
    /* access modifiers changed from: private */
    public String mfpUsername;
    private DialogPositiveListener onErrorDialogButtonClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            FacebookLoginActivity.this.setResult(0);
            FacebookLoginActivity.this.finish();
        }
    };
    @Inject
    Lazy<SignUpService> signUpService;
    private boolean started = false;
    /* access modifiers changed from: private */
    public int uiMode = 0;

    public boolean shouldDisplayAds() {
        return false;
    }

    public boolean showToolbar() {
        return false;
    }

    public static Intent getStartIntentForLogin(Context context) {
        return new Intent(context, FacebookLoginActivity.class).putExtra(EXTRA_UI_MODE, 0);
    }

    public static Intent getStartIntentForSignup(Context context) {
        return new Intent(context, FacebookLoginActivity.class).putExtra(EXTRA_UI_MODE, 1);
    }

    public static Intent getStartIntentForVerification(Context context) {
        return new Intent(context, FacebookLoginActivity.class).putExtra(EXTRA_UI_MODE, 2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.progress_overlay_activity);
        component().inject(this);
        setResult(0);
        this.uiMode = getIntent().getExtras().getInt(EXTRA_UI_MODE, 0);
        setupFacebookCallbacks();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!"error_dialog_fragment".equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onErrorDialogButtonClickListener);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.callbackManager.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LoginManager instance = LoginManager.getInstance();
        if (instance != null && !this.started) {
            instance.registerCallback(this.callbackManager, this.fbLoginCallback);
            OrientationUtils.lockOrientation(getActivity());
            instance.logInWithReadPermissions((Activity) this, Login.PERMISSIONS);
            this.started = true;
        }
    }

    /* access modifiers changed from: private */
    public void startFacebookConnect() {
        AnonymousClass1 r0 = new Function1<String>() {
            public void execute(String str) throws RuntimeException {
                FacebookLoginActivity.this.mfpUsername = str;
                if (FacebookLoginActivity.this.loginModel.getFacebookData() != null) {
                    FacebookLoginActivity.this.onFacebookConnectSuccess();
                    return;
                }
                FacebookLoginActivity.this.setResult(0);
                FacebookLoginActivity.this.finish();
            }
        };
        AnonymousClass2 r1 = new Function2<Integer, String>() {
            public void execute(Integer num, String str) {
                FacebookLoginActivity.this.onFacebookConnectFail(num.intValue(), str);
            }
        };
        if (this.uiMode == 1) {
            ((FacebookService) this.facebookService.get()).connectEmailCompulsory(this, r0, r1);
        } else {
            ((FacebookService) this.facebookService.get()).connect(this, r0, r1);
        }
    }

    /* access modifiers changed from: private */
    public void onFacebookConnectSuccess() {
        new ValidateEmailAddressTask(this.signUpService, this.loginModel.getFacebookData().getEmail(), null).setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.CancelExisting).run(getRunner());
    }

    private void finishWithSuccess(String str, CompletedEvent completedEvent) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_FACEBOOK_EMAIL_ADDRESS, str);
        intent.putExtra(EXTRA_FACEBOOK_MFP_USERNAME, this.mfpUsername);
        intent.putExtra(EXTRA_UI_MODE, this.uiMode);
        if (completedEvent != null) {
            intent.putExtra(EXTRA_MFP_ACCOUNT_EXISTS, completedEvent.mfpAccountExists());
            intent.putExtra(EXTRA_UACF_ACCOUNT_EXISTS, completedEvent.uacfAccountExists());
        }
        setResult(-1, intent);
        finish();
    }

    private void finishWithAccountExists(String str) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_FACEBOOK_EMAIL_ADDRESS, str);
        setResult(2, intent);
        finish();
    }

    @Subscribe
    public void onValidateEmailForSignUpCompleted(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            int i = this.uiMode;
            if (i == 1) {
                if (!completedEvent.mfpAccountExists()) {
                    finishWithSuccess(completedEvent.getEmailAddress(), completedEvent);
                    return;
                }
                this.loginModel.clearFacebookData();
                finishWithAccountExists(completedEvent.getEmailAddress());
            } else if (i == 0) {
                finishWithSuccess(completedEvent.getEmailAddress(), completedEvent);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onFacebookConnectFail(int i, String str) {
        int i2;
        String str2;
        switch (i) {
            case 2000:
                postEvent(new UnderageRegistrationFailureEvent());
                break;
            case 2001:
                setResult(3, new Intent().putExtra(EXTRA_UI_MODE, this.uiMode));
                finish();
                break;
            default:
                if (!Strings.notEmpty(str)) {
                    str = getString(R.string.failAssociateFacebookUser);
                }
                str2 = str;
                i2 = R.string.dismiss;
                break;
        }
        str2 = null;
        i2 = R.string.ok;
        if (Strings.notEmpty(str2) && Strings.notEmpty((Object) Integer.valueOf(i2))) {
            AlertDialogFragmentBase positiveText = ((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.error)).setMessage(str2)).setPositiveText(i2, this.onErrorDialogButtonClickListener);
            positiveText.setCancelable(false);
            showDialogFragment(positiveText, "error_dialog_fragment");
        }
    }

    private void setupFacebookCallbacks() {
        this.callbackManager = Factory.create();
        this.fbLoginCallback = new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                Ln.d("SUCCESS", new Object[0]);
                OrientationUtils.unlockOrientation(FacebookLoginActivity.this.getActivity());
                if (FacebookLoginActivity.this.uiMode == 2) {
                    FacebookLoginActivity.this.returnAccessTokenResultForVerification(loginResult.getAccessToken());
                } else {
                    FacebookLoginActivity.this.startFacebookConnect();
                }
            }

            public void onCancel() {
                Ln.d("CANCEL", new Object[0]);
                OrientationUtils.unlockOrientation(FacebookLoginActivity.this.getActivity());
                FacebookLoginActivity.this.finish();
            }

            public void onError(FacebookException facebookException) {
                Ln.d("ERROR: %s", facebookException.toString());
                if (!(facebookException instanceof FacebookAuthorizationException) || AccessToken.getCurrentAccessToken() == null) {
                    OrientationUtils.unlockOrientation(FacebookLoginActivity.this.getActivity());
                    FacebookLoginActivity.this.finish();
                    return;
                }
                LoginManager.getInstance().logOut();
                LoginManager.getInstance().logInWithReadPermissions((Activity) FacebookLoginActivity.this, Login.PERMISSIONS);
            }
        };
    }

    /* access modifiers changed from: private */
    public void returnAccessTokenResultForVerification(AccessToken accessToken) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_VERIFY_ACCESS_TOKEN, accessToken.getToken());
        setResult(-1, intent);
        finish();
    }
}
