package com.myfitnesspal.feature.registration.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.SpanUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class LoginUserPassFragment extends MfpFragment {
    private static final String EXTRA_REPORTED_VIEW_JOIN_EVENT = "extra_reported_join_event";
    private static final String EXTRA_REPORTED_VIEW_LOGIN_EVENT = "extra_reported_login_event";
    private static final String VALUE_EMAIL = "email";
    private static final String VALUE_FACEBOOK = "facebook";
    /* access modifiers changed from: private */
    public EventListener eventListener;
    @BindView(2131362551)
    View facebookButton;
    @BindView(2131362552)
    TextView facebookButtonText;
    @BindView(2131362652)
    View forgotPassword;
    @BindView(2131362960)
    TextView loginButton;
    @Inject
    Lazy<LoginModel> loginModel;
    private Mode mode = Mode.Login;
    @BindView(2131363247)
    EditText passwordEdit;
    private boolean reportedJoin;
    private boolean reportedLogin;
    private View rootView;
    @BindView(2131362554)
    View separatorAndFacebookButton;
    @BindView(2131364075)
    TextView underArmourAccountFaq;
    @BindView(2131364120)
    View userPassContainer;
    @BindView(2131362444)
    EditText usernameEdit;

    public interface EventListener {
        void onForgotPasswordClicked();

        void onSignInButtonClicked(String str, String str2);

        void onSignInWithFacebookClicked();

        void onSignUpWithEmailAtEndOfFlowClicked();

        void onSignUpWithFacebookClicked();
    }

    public enum Mode {
        Login,
        SignUp,
        FinishOnboarding
    }

    public static LoginUserPassFragment newInstance() {
        return new LoginUserPassFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.reportedJoin = BundleUtils.getBoolean(bundle, EXTRA_REPORTED_VIEW_JOIN_EVENT);
        this.reportedLogin = BundleUtils.getBoolean(bundle, EXTRA_REPORTED_VIEW_LOGIN_EVENT);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle == null) {
            rebindUi();
        }
    }

    public void onResume() {
        super.onResume();
        checkReportScreenViewedEvent();
    }

    public void updateEmailAddress(String str) {
        this.usernameEdit.setText(str);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.login_user_pass_fragment, viewGroup, false);
        ButterKnife.bind((Object) this, this.rootView);
        initListeners();
        return this.rootView;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_REPORTED_VIEW_JOIN_EVENT, this.reportedJoin);
        bundle.putBoolean(EXTRA_REPORTED_VIEW_LOGIN_EVENT, this.reportedLogin);
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z && this.rootView != null) {
            this.usernameEdit.setText(null);
            this.passwordEdit.setText(null);
        }
    }

    public void setMode(Mode mode2) {
        this.mode = mode2;
        rebindUi();
    }

    public void setListener(EventListener eventListener2) {
        this.eventListener = eventListener2;
    }

    private void initListeners() {
        AnonymousClass1 r0 = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                LoginUserPassFragment.this.updateLoginButtonState();
            }
        };
        this.usernameEdit.addTextChangedListener(r0);
        this.passwordEdit.addTextChangedListener(r0);
        this.passwordEdit.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 2) {
                    return false;
                }
                LoginUserPassFragment.this.raiseLoginOrSignUpEvent();
                return true;
            }
        });
        this.loginButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoginUserPassFragment.this.raiseLoginOrSignUpEvent();
            }
        });
        this.forgotPassword.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (LoginUserPassFragment.this.eventListener != null) {
                    LoginUserPassFragment.this.getAnalyticsService().reportEvent(Events.FORGOT_PASSWORD_LINK_CLICK);
                    LoginUserPassFragment.this.eventListener.onForgotPasswordClicked();
                }
            }
        });
        this.facebookButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (LoginUserPassFragment.this.eventListener != null) {
                    LoginUserPassFragment.this.raiseLoginOrSignUpWithFacebookEvent();
                }
            }
        });
    }

    private void rebindUi() {
        if (this.rootView != null) {
            checkReportScreenViewedEvent();
            prepareUaAccountFaq();
            ViewUtils.setVisible(getCountryService().getCurrentCountry().isFacebookSupported(), this.separatorAndFacebookButton);
            ViewUtils.setVisible(this.mode == Mode.Login, this.forgotPassword);
            if (this.mode == Mode.Login || this.mode == Mode.FinishOnboarding) {
                if (Strings.isEmpty(this.usernameEdit.getText().toString())) {
                    this.usernameEdit.setText(((LoginModel) this.loginModel.get()).getLastUsername());
                }
                if (!Strings.isEmpty(this.usernameEdit.getText().toString())) {
                    this.passwordEdit.requestFocus();
                }
                this.loginButton.setText(R.string.auth_button_login);
                this.facebookButtonText.setText(R.string.auth_button_login_with_facebook);
                if (this.mode == Mode.FinishOnboarding) {
                    this.usernameEdit.setEnabled(false);
                }
                ViewUtils.setVisible(this.userPassContainer);
            } else {
                this.loginButton.setText(R.string.auth_button_get_started);
                this.facebookButtonText.setText(R.string.auth_button_get_started_with_facebook);
                ViewUtils.setGone(this.userPassContainer);
                this.loginButton.setText(R.string.welcome_sign_up_email);
            }
            updateLoginButtonState();
        }
    }

    private void prepareUaAccountFaq() {
        if (this.mode == Mode.Login && ConfigUtils.isRushWeekLogInChangeEnabled(getConfigService())) {
            this.underArmourAccountFaq.setText(SpanUtils.fromHtml(getString(R.string.auth_login_ua_account_faq)));
            ViewUtils.setVisible(true, this.underArmourAccountFaq);
        } else if (this.mode != Mode.SignUp || !ConfigUtils.isRushWeekSignUpChangeEnabled(getConfigService())) {
            ViewUtils.setVisible(this.mode == Mode.SignUp, this.underArmourAccountFaq);
        } else {
            this.underArmourAccountFaq.setText(SpanUtils.fromHtml(getString(R.string.auth_sign_up_ua_account_faq)));
            ViewUtils.setVisible(true, this.underArmourAccountFaq);
        }
    }

    /* access modifiers changed from: private */
    public void updateLoginButtonState() {
        boolean z = this.mode == Mode.SignUp || (Strings.length((Object) this.usernameEdit.getText()) > 0 && Strings.length((Object) this.passwordEdit.getText()) > 0);
        this.loginButton.setEnabled(z);
        this.loginButton.setBackgroundResource(z ? R.drawable.material_login_button_selector : R.drawable.material_login_button_disabled);
    }

    /* access modifiers changed from: private */
    public void raiseLoginOrSignUpWithFacebookEvent() {
        if (this.eventListener != null) {
            getAnalyticsService().reportEvent(this.mode == Mode.SignUp ? Events.JOIN_SCREEN_SIGNUP_CLICK : Events.LOGIN_SCREEN_LOGIN_CLICK, MapUtil.createMap("type", "facebook"));
            if (this.mode == Mode.SignUp) {
                this.eventListener.onSignUpWithFacebookClicked();
            } else {
                this.eventListener.onSignInWithFacebookClicked();
            }
        }
    }

    /* access modifiers changed from: private */
    public void raiseLoginOrSignUpEvent() {
        if (this.eventListener != null) {
            String obj = this.usernameEdit.getText().toString();
            String obj2 = this.passwordEdit.getText().toString();
            if (this.mode == Mode.Login || this.mode == Mode.FinishOnboarding) {
                this.eventListener.onSignInButtonClicked(obj, obj2);
                return;
            }
            getAnalyticsService().reportEvent(Events.JOIN_SCREEN_SIGNUP_CLICK, MapUtil.createMap("type", "email"));
            this.eventListener.onSignUpWithEmailAtEndOfFlowClicked();
        }
    }

    private void checkReportScreenViewedEvent() {
        if (hasResumed() && isVisible()) {
            if (!this.reportedJoin && this.mode == Mode.SignUp) {
                getAnalyticsService().reportEvent(Screens.JOIN_SCREEN_SEE);
                this.reportedJoin = true;
            } else if (this.reportedLogin) {
            } else {
                if (this.mode == Mode.Login || this.mode == Mode.FinishOnboarding) {
                    getAnalyticsService().reportEvent(Screens.LOGIN_SCREEN_SEE);
                    this.reportedLogin = true;
                }
            }
        }
    }
}
