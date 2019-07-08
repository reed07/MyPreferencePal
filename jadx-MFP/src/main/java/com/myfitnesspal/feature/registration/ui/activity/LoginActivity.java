package com.myfitnesspal.feature.registration.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.registration.exception.RegistrationError;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.service.SignInService;
import com.myfitnesspal.feature.registration.task.SignInTask;
import com.myfitnesspal.feature.registration.task.SignInTask.CompletedEvent;
import com.myfitnesspal.feature.registration.task.SignInTask.Type;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity.Mode;
import com.myfitnesspal.feature.registration.ui.fragment.LoginPleaseWaitFragment;
import com.myfitnesspal.feature.registration.ui.fragment.LoginSignInUpButtonsFragment;
import com.myfitnesspal.feature.registration.ui.fragment.LoginSignInUpButtonsFragment.EventListener;
import com.myfitnesspal.feature.registration.ui.fragment.LoginUserPassFragment;
import com.myfitnesspal.feature.registration.util.PasswordResetHelper;
import com.myfitnesspal.shared.api.auth.SSO;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Login;
import com.myfitnesspal.shared.constants.Constants.Performance;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.event.PerformanceTimerStopEvent;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.myfitnesspal.shared.util.FragmentUtil;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import javax.inject.Inject;

public class LoginActivity extends MfpActivity {
    private static final String ACCOUNT_EXISTS_DIALOG_FRAGMENT_TAG = "account_exists_dialog";
    private static final String ATTRIBUTE_KEY_REASON = "reason";
    private static final String ATTRIBUTE_VALUE_NO_EMAIL = "no_email";
    private static final String ERROR_DIALOG_FRAGMENT_TAG = "error_dialog_fragment";
    private static final String ERROR_OFFLINE = "offline";
    private static final String EVENT_FB_LOG_IN_FAILED = "fb_login_failed";
    private static final String EVENT_FB_SIGN_UP_FAILED = "fb_signup_failed";
    private static final String EVENT_UACF_LOGIN_SUCCESS = "uacf_login_success";
    public static final String EXTRA_CURRENT_UI_MODE = "current_ui_mode";
    private static final String EXTRA_INITIAL_UI_MODE = "extra_initial_ui_mode";
    public static final String EXTRA_LOGIN_TYPE = "extra_login_type";
    public static final String EXTRA_PASSWORD = "extra_password";
    public static final String EXTRA_USERNAME = "extra_username";
    private static final String GENERIC_ERROR_DIALOG_TAG = "generic_error_dialog";
    private static final String KEY_BUTTON_CLICK = "button_click";
    private static final String LOGIN_CONTROLS_FRAGMENT_TAG = "login";
    private static final int LOGIN_FACEBOOK = 101;
    private static final String LOGIN_FAILED_DIALOG_TAG = "login_failed_dialog";
    private static final int LOGIN_STANDARD = 100;
    private static final String PLEASE_WAIT_FRAGMENT_TAG = "please_wait";
    private static final int UI_MODE_EMPTY = 0;
    private static final int UI_MODE_PLEASE_WAIT = 4;
    private static final int UI_MODE_SIGN_IN_SIGN_UP_BUTTONS = 1;
    private static final int UI_MODE_SIGN_IN_USERNAME_PASSWORD = 2;
    private static final int UI_MODE_SIGN_UP_EMAIL_OR_FACEBOOK = 3;
    private static final String VALUE_CANCEL = "cancel";
    private static final String VALUE_LOGIN = "login";
    private static final String WELCOME_CONTROLS_FRAGMENT_TAG = "welcome_controls";
    @Inject
    Lazy<AdvancedDebuggingUtil> advancedDebuggingUtil;
    private int currentUiMode;
    @Inject
    Lazy<DiaryService> diaryService;
    private Handler handler = new Handler();
    /* access modifiers changed from: private */
    public int lastLoginType = 100;
    private int lastNonWaitUiMode = 1;
    /* access modifiers changed from: private */
    public String lastPassword;
    /* access modifiers changed from: private */
    public String lastUsername;
    @Inject
    Lazy<LoginModel> loginModel;
    private EventListener loginSignUpFragmentEventListener = new EventListener() {
        public void onSignUpClicked() {
            if (LoginActivity.this.getCountryService().getCurrentCountry().isFacebookSupported()) {
                LoginActivity.this.setUiMode(3);
                return;
            }
            LoginActivity.this.getAnalyticsService().reportEvent(Events.WELCOME_SIGNUPEMAILBTN_CLICK);
            LoginActivity.this.getNavigationHelper().withIntent(SignUpActivity.newStartIntent(LoginActivity.this)).startActivity(7);
        }

        public void onSignInClicked() {
            LoginActivity.this.setUiMode(2);
        }
    };
    private LoginUserPassFragment.EventListener loginUserPassFragmentEventListener = new LoginUserPassFragment.EventListener() {
        public void onForgotPasswordClicked() {
            LoginActivity.this.getNavigationHelper().withIntent(ForgotPasswordActivity.newStartIntent(LoginActivity.this)).startActivity();
        }

        public void onSignInWithFacebookClicked() {
            LoginActivity.this.getAnalyticsService().reportEvent(Events.WELCOME_FACEBOOKBTN_CLICK);
            LoginActivity loginActivity = LoginActivity.this;
            loginActivity.startActivityForResult(FacebookLoginActivity.getStartIntentForLogin(loginActivity), RequestCodes.FACEBOOK_LOGIN);
        }

        public void onSignUpWithFacebookClicked() {
            LoginActivity.this.getAnalyticsService().reportEvent(Events.WELCOME_FACEBOOKBTN_CLICK);
            LoginActivity loginActivity = LoginActivity.this;
            loginActivity.startActivityForResult(FacebookLoginActivity.getStartIntentForSignup(loginActivity), RequestCodes.FACEBOOK_LOGIN);
        }

        public void onSignInButtonClicked(String str, String str2) {
            ((LoginModel) LoginActivity.this.loginModel.get()).setLastUsername(str);
            LoginActivity.this.startSignIn(100, str, str2);
        }

        public void onSignUpWithEmailAtEndOfFlowClicked() {
            ((LoginModel) LoginActivity.this.loginModel.get()).clearFacebookData();
            ((LoginModel) LoginActivity.this.loginModel.get()).setLastUsername(null);
            ((LoginModel) LoginActivity.this.loginModel.get()).setUsername(null);
            LoginActivity.this.getNavigationHelper().withIntent(SignUpActivity.newStartIntent(LoginActivity.this, Mode.Full)).startActivity(7);
        }
    };
    private DialogNegativeListener onAccountExistsCancelButtonPressed = new DialogNegativeListener() {
        public final void onClick() {
            
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.registration.ui.activity.LoginActivity
                  0x0000: IGET  (r0v0 com.myfitnesspal.feature.registration.ui.activity.LoginActivity) = (r1v0 'this' com.myfitnesspal.feature.registration.ui.activity.-$$Lambda$LoginActivity$wpJ7fv_F0_WsYhUD7oaOJlgIr1k A[THIS]) com.myfitnesspal.feature.registration.ui.activity.-$$Lambda$LoginActivity$wpJ7fv_F0_WsYhUD7oaOJlgIr1k.f$0 com.myfitnesspal.feature.registration.ui.activity.LoginActivity) com.myfitnesspal.feature.registration.ui.activity.LoginActivity.lambda$new$3(com.myfitnesspal.feature.registration.ui.activity.LoginActivity):void type: STATIC in method: com.myfitnesspal.feature.registration.ui.activity.-$$Lambda$LoginActivity$wpJ7fv_F0_WsYhUD7oaOJlgIr1k.onClick():void, dex: classes3.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:95)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:469)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.ClassGen.addInsnBody(ClassGen.java:435)
                	at jadx.core.codegen.ClassGen.addField(ClassGen.java:376)
                	at jadx.core.codegen.ClassGen.addFields(ClassGen.java:346)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:223)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                	... 31 more
                Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.Class.forName0(Native Method)
                	at java.base/java.lang.Class.forName(Unknown Source)
                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                	... 50 more
                */
            /*
                this = this;
                com.myfitnesspal.feature.registration.ui.activity.LoginActivity r0 = com.myfitnesspal.feature.registration.ui.activity.LoginActivity.this
                
                // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.registration.ui.activity.LoginActivity) com.myfitnesspal.feature.registration.ui.activity.LoginActivity.lambda$new$3(com.myfitnesspal.feature.registration.ui.activity.LoginActivity):void type: STATIC
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.registration.ui.activity.$$Lambda$LoginActivity$wpJ7fv_F0_WsYhUD7oaOJlgIr1k.onClick():void");
        }
    };
    private DialogPositiveListener onAccountExistsLogInButtonPressed = new DialogPositiveListener() {
        public void onClick(Object obj) {
            LoginActivity.this.getAnalyticsService().reportEvent(Events.LOGIN_UA_ACCOUNT_EXISTS_CLICK, MapUtil.createMap(LoginActivity.KEY_BUTTON_CLICK, Event.LOGIN));
            LoginActivity.this.userPassFragment.updateEmailAddress(((LoginModel) LoginActivity.this.loginModel.get()).getUsername());
            LoginActivity.this.setUiMode(2);
        }
    };
    private DialogPositiveListener onRetryLoginButtonListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            LoginActivity loginActivity = LoginActivity.this;
            loginActivity.startSignIn(loginActivity.lastLoginType, LoginActivity.this.lastUsername, LoginActivity.this.lastPassword);
        }
    };
    @Inject
    Lazy<PasswordResetHelper> passwordResetHelper;
    private LoginPleaseWaitFragment pleaseWaitFragment;
    @Inject
    Lazy<SignInService> signInService;
    private SignInTask signInTask = null;
    private LoginSignInUpButtonsFragment signInUpButtonsFragment;
    /* access modifiers changed from: private */
    public LoginUserPassFragment userPassFragment;

    public String getAnalyticsScreenTag() {
        return Screens.WELCOME;
    }

    public boolean onSearchRequested() {
        return false;
    }

    public boolean shouldDisplayAds() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, LoginActivity.class).addFlags(268468224);
    }

    public static Intent newStartIntentFromWelcome(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    public static Intent newStartIntentForEmailLogin(Context context) {
        return newStartIntent(context).putExtra(EXTRA_INITIAL_UI_MODE, 2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.login);
        if (bundle == null) {
            getAnalyticsService().reportEvent(Events.START_SCREEN_SEE);
            this.currentUiMode = getIntent().getExtras().getInt(EXTRA_INITIAL_UI_MODE, 1);
        } else {
            this.currentUiMode = bundle.getInt(EXTRA_CURRENT_UI_MODE, 1);
            this.lastUsername = bundle.getString(EXTRA_USERNAME);
            this.lastPassword = bundle.getString("extra_password");
            this.lastLoginType = bundle.getInt(EXTRA_LOGIN_TYPE, 100);
        }
        initUi();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (LOGIN_FAILED_DIALOG_TAG.equals(str)) {
            ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onRetryLoginButtonListener);
            return true;
        } else if (!ACCOUNT_EXISTS_DIALOG_FRAGMENT_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        } else {
            AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
            alertDialogFragment.setPositiveListener(this.onAccountExistsLogInButtonPressed);
            alertDialogFragment.setNegativeListener(this.onAccountExistsCancelButtonPressed);
            return true;
        }
    }

    public void onResume() {
        super.onResume();
        if (this.signInTask != null || !getSession().getUser().isLoggedIn()) {
            setUiMode(this.currentUiMode);
            postEvent(new PerformanceTimerStopEvent(Performance.SESSION_START));
            return;
        }
        onLoginSuccessful();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 7) {
            if (i == 186) {
                if (i2 == -1) {
                    AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                    Bundle extras = intent.getExtras();
                    String string = BundleUtils.getString(extras, FacebookLoginActivity.EXTRA_FACEBOOK_EMAIL_ADDRESS, (String) null);
                    int i3 = BundleUtils.getInt(extras, FacebookLoginActivity.EXTRA_UI_MODE, 0);
                    if (i3 == 0) {
                        if (currentAccessToken != null) {
                            startSignIn(101, string, string);
                        } else {
                            getNavigationHelper().withIntent(FacebookLoginActivity.getStartIntentForSignup(this)).startActivity(RequestCodes.FACEBOOK_LOGIN);
                        }
                    } else if (i3 == 1) {
                        boolean z = BundleUtils.getBoolean(extras, FacebookLoginActivity.EXTRA_MFP_ACCOUNT_EXISTS);
                        boolean z2 = BundleUtils.getBoolean(extras, FacebookLoginActivity.EXTRA_UACF_ACCOUNT_EXISTS);
                        String string2 = BundleUtils.getString(extras, FacebookLoginActivity.EXTRA_FACEBOOK_MFP_USERNAME);
                        if (!z2 && !z && Strings.isEmpty(string2)) {
                            getNavigationHelper().withIntent(SignUpActivity.newStartIntent(this, Mode.Full)).startActivity(7);
                        } else if (currentAccessToken == null || Strings.notEmpty(string)) {
                            startSignIn(101, string, string);
                        }
                    }
                } else if (i2 == 2) {
                    this.handler.post(new Runnable(intent) {
                        private final /* synthetic */ Intent f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            LoginActivity.this.showAccountExistsDialog(BundleUtils.getString(this.f$1.getExtras(), FacebookLoginActivity.EXTRA_FACEBOOK_EMAIL_ADDRESS, (String) null), R.string.auth_dialog_social_email_address_taken_message);
                        }
                    });
                } else if (i2 == 3) {
                    showNoFacebookEmailDialog(ExtrasUtils.getInt(intent, FacebookLoginActivity.EXTRA_UI_MODE, 1));
                } else if (i2 != 0 || !ConnectivityUtil.isOffline().booleanValue()) {
                    getAnalyticsService().reportEvent(Events.FACEBOOK_CONNECT_FAILED_OR_CANCELLED);
                } else {
                    getAnalyticsService().reportEvent(Events.FACEBOOK_CONNECT_FAILED_OR_CANCELLED);
                    this.handler.post(new Runnable() {
                        public final void run() {
                            LoginActivity.this.showLoginFailedDialog(LoginActivity.ERROR_OFFLINE);
                        }
                    });
                }
            }
        } else if (i2 == -1) {
            getNavigationHelper().finishActivityAfterNavigation().withIntent(HomeActivity.newStartIntent(this)).startActivity();
        } else {
            setUiMode(3);
        }
    }

    public void onBackPressed() {
        switch (this.currentUiMode) {
            case 2:
            case 3:
                setUiMode(1);
                return;
            case 4:
                return;
            default:
                setResult(0);
                finish();
                return;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(EXTRA_CURRENT_UI_MODE, this.currentUiMode);
        bundle.putString(EXTRA_USERNAME, this.lastUsername);
        bundle.putString("extra_password", this.lastPassword);
        bundle.putInt(EXTRA_LOGIN_TYPE, this.lastLoginType);
    }

    @Subscribe
    public void onSignInTaskFinished(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            this.signInTask = null;
            if (!completedEvent.successful()) {
                RegistrationException registrationException = (RegistrationException) completedEvent.getFailure();
                RegistrationError reason = registrationException.getReason();
                if (reason == RegistrationError.NoVerticalAccount) {
                    String accountEmail = SSO.getAccountEmail(SSO.getSdk());
                    if (Strings.isEmpty(accountEmail)) {
                        accountEmail = completedEvent.getUsername();
                    }
                    getNavigationHelper().withIntent(SignUpActivity.newStartIntent(this, accountEmail, completedEvent.getPassword(), Mode.Profile)).startActivity(7);
                    getAnalyticsService().reportEvent(EVENT_UACF_LOGIN_SUCCESS);
                } else if (completedEvent.getType() == Type.Facebook && reason == RegistrationError.InvalidToken) {
                    if (Strings.isEmpty(((LoginModel) this.loginModel.get()).getFacebookData().getEmail())) {
                        setUiMode(2);
                        showNoFacebookEmailDialog(0);
                        return;
                    }
                    getNavigationHelper().withIntent(SignUpActivity.newStartIntent(this)).startActivity(7);
                } else if (reason == RegistrationError.SyncFailure) {
                    UacfScheduleException syncException = registrationException.getSyncException();
                    int statusCode = syncException.getStatusCode();
                    if (statusCode == 2) {
                        showLoginFailedDialog(R.string.login_error, R.string.please_reenter_password_or_username);
                    } else if (statusCode != 7) {
                        switch (statusCode) {
                            case 4:
                                showLoginFailedDialog(R.string.account_deleted_title, R.string.account_deleted);
                                break;
                            case 5:
                                showLoginFailedDialog(R.string.account_replaced_login, R.string.account_replaced);
                                break;
                            default:
                                showLoginFailedDialog(String.format("SyncV1 status code: %s", new Object[]{Integer.valueOf(syncException.getStatusCode())}));
                                break;
                        }
                    } else {
                        showPasswordResetDialog(syncException.getStatusMessage());
                    }
                } else if (registrationException.getReason() != RegistrationError.InvalidToken) {
                    showLoginFailedDialog(registrationException.getReason().toString());
                } else if (ConnectivityUtil.isOffline().booleanValue()) {
                    showLoginFailedDialog(ERROR_OFFLINE);
                } else {
                    showLoginFailedDialog(R.string.login_error, R.string.auth_invalid_email_or_password);
                }
            } else {
                onLoginSuccessful();
            }
        }
    }

    private void showNoFacebookEmailDialog(int i) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        getAnalyticsService().reportEvent(z ? EVENT_FB_SIGN_UP_FAILED : EVENT_FB_LOG_IN_FAILED, MapUtil.createMap("reason", ATTRIBUTE_VALUE_NO_EMAIL));
        this.handler.post(new Runnable(((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(z ? R.string.unable_sign_up_fb_title : R.string.unable_log_in_fb_title)).setMessage(z ? R.string.unable_sign_up_fb_message : R.string.unable_log_in_fb_message)).setPositiveText(R.string.ok, null)) {
            private final /* synthetic */ DialogFragment f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                LoginActivity.this.showDialogFragment(this.f$1, "error_dialog_fragment");
            }
        });
    }

    /* access modifiers changed from: private */
    public void startSignIn(int i, String str, String str2) {
        if (i == 101 || (!Strings.isEmpty(str) && !Strings.isEmpty(str2))) {
            showPleaseWait();
            this.lastLoginType = i;
            this.lastUsername = str;
            this.lastPassword = str2;
            Session session = getSession();
            this.signInTask = i == 101 ? new SignInTask(session, this.signInService, str, ((LoginModel) this.loginModel.get()).getFacebookData()) : new SignInTask(session, this.signInService, str, str2);
            this.signInTask.setDedupeMode(DedupeMode.CancelExisting).setCacheMode(CacheMode.None).run(getRunner());
            return;
        }
        ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(getString(R.string.enter_username_or_password));
        setUiMode(this.lastNonWaitUiMode);
    }

    private void initUi() {
        ((AdvancedDebuggingUtil) this.advancedDebuggingUtil.get()).setUpVersionHandlerOn((TextView) findById(R.id.advanced_debugging));
        updateNotificationText();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.userPassFragment = (LoginUserPassFragment) supportFragmentManager.findFragmentByTag(Event.LOGIN);
        if (this.userPassFragment == null) {
            this.userPassFragment = LoginUserPassFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.full_screen_fragment_container, this.userPassFragment, Event.LOGIN).hide(this.userPassFragment).commitAllowingStateLoss();
        }
        this.pleaseWaitFragment = (LoginPleaseWaitFragment) supportFragmentManager.findFragmentByTag(PLEASE_WAIT_FRAGMENT_TAG);
        if (this.pleaseWaitFragment == null) {
            this.pleaseWaitFragment = LoginPleaseWaitFragment.newInstance();
        }
        this.signInUpButtonsFragment = (LoginSignInUpButtonsFragment) supportFragmentManager.findFragmentByTag(WELCOME_CONTROLS_FRAGMENT_TAG);
        if (this.signInUpButtonsFragment == null) {
            this.signInUpButtonsFragment = LoginSignInUpButtonsFragment.newInstance();
        }
        this.signInUpButtonsFragment.setEventListener(this.loginSignUpFragmentEventListener);
        this.userPassFragment.setListener(this.loginUserPassFragmentEventListener);
    }

    /* access modifiers changed from: private */
    public void setUiMode(int i) {
        boolean z = true;
        boolean z2 = i == 2 || i == 3;
        int i2 = this.currentUiMode;
        boolean z3 = i2 == 2 || i2 == 3;
        int i3 = z2 ? R.anim.slide_in_bottom_100_short : 0;
        int i4 = z3 ? R.anim.slide_out_bottom_100_short : 0;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(i3, i4);
        ViewUtils.setVisible(true, findViewById(R.id.center_fragment_container));
        setToolbarVisible(false);
        LoginUserPassFragment loginUserPassFragment = this.userPassFragment;
        switch (i) {
            case 0:
                beginTransaction.hide(loginUserPassFragment);
                ViewUtils.setVisible(false, findViewById(R.id.center_fragment_container));
                break;
            case 1:
                beginTransaction.hide(loginUserPassFragment);
                FragmentUtil.replace(supportFragmentManager, beginTransaction, R.id.center_fragment_container, this.signInUpButtonsFragment, WELCOME_CONTROLS_FRAGMENT_TAG);
                break;
            case 2:
                setToolbarVisible(true);
                beginTransaction.show(loginUserPassFragment);
                setTitle(R.string.auth_title_log_in);
                break;
            case 3:
                setToolbarVisible(true);
                beginTransaction.show(loginUserPassFragment);
                setTitle(R.string.auth_title_sign_up);
                break;
            case 4:
                beginTransaction.hide(loginUserPassFragment);
                FragmentUtil.replace(supportFragmentManager, beginTransaction, R.id.center_fragment_container, this.pleaseWaitFragment, PLEASE_WAIT_FRAGMENT_TAG);
                break;
        }
        z = false;
        beginTransaction.commitAllowingStateLoss();
        supportFragmentManager.executePendingTransactions();
        if (z) {
            this.userPassFragment.setMode(i == 2 ? LoginUserPassFragment.Mode.Login : LoginUserPassFragment.Mode.SignUp);
        } else {
            getImmHelper().hideSoftInput();
        }
        if (i != 4) {
            this.lastNonWaitUiMode = i;
        }
        this.currentUiMode = i;
    }

    private void updateNotificationText() {
        TextView textView = (TextView) findById(R.id.login_notification_text);
        ViewUtils.setVisible(false, textView);
        if (ExtrasUtils.hasExtra(getIntent(), Extras.WELCOME_NOTIFICATION)) {
            String string = getString(ExtrasUtils.getInt(getIntent(), Extras.WELCOME_NOTIFICATION));
            ViewUtils.setVisible(textView);
            textView.setText(string);
        }
    }

    private void moveToPrefetchActivity() {
        ((DiaryService) this.diaryService.get()).clearDiaryDayCache();
        startActivity(PrefetchActivity.getStartIntent(this, true));
        overridePendingTransition(0, 0);
        finish();
    }

    private void hidePleaseWait() {
        setUiMode(this.lastNonWaitUiMode);
    }

    private void showPleaseWait() {
        setUiMode(4);
    }

    private void setToolbarVisible(boolean z) {
        if (z) {
            setTitle(getString(R.string.welcome_sign_up));
        }
        ViewUtils.setVisible(z, getToolbar());
    }

    private void onLoginSuccessful() {
        setResult(-1);
        if (Login.OAUTH2.equals(getIntent().getAction())) {
            finish();
        } else {
            moveToPrefetchActivity();
        }
    }

    private void reportLoginFailure(String str) {
        getAnalyticsService().reportEvent(Events.LOGIN_UNSUCCESSFUL_ERROR_SEE, MapUtil.createMap("reason", str, "auth_type", ((LoginModel) this.loginModel.get()).getSignInAuthType()));
    }

    private void showPasswordResetDialog(String str) {
        hidePleaseWait();
        ((PasswordResetHelper) this.passwordResetHelper.get()).showDialog(this, ((PasswordResetHelper) this.passwordResetHelper.get()).createDataFromJson(str));
    }

    private void showLoginFailedDialog(int i, int i2) {
        hidePleaseWait();
        reportLoginFailure(getString(i2));
        AlertDialogFragment newInstance = AlertDialogFragment.newInstance();
        ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setTitle(i)).setMessage(i2)).setPositiveText(R.string.dismiss, null);
        newInstance.setCancelable(false);
        showDialogFragment(newInstance, GENERIC_ERROR_DIALOG_TAG);
    }

    /* access modifiers changed from: private */
    public void showAccountExistsDialog(String str, int i) {
        ((LoginModel) this.loginModel.get()).setUsername(str);
        ((LoginModel) this.loginModel.get()).setLastUsername(str);
        AlertDialogFragment newInstance = AlertDialogFragment.newInstance();
        ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setTitle(R.string.auth_dialog_account_exists_title)).setMessage(i)).setNegativeText(R.string.cancel, this.onAccountExistsCancelButtonPressed)).setPositiveText(R.string.auth_button_login, this.onAccountExistsLogInButtonPressed);
        newInstance.setCancelable(false);
        getAnalyticsService().reportEvent(Events.LOGIN_UA_ACCOUNT_EXISTS);
        showDialogFragment(newInstance, ACCOUNT_EXISTS_DIALOG_FRAGMENT_TAG);
    }

    /* access modifiers changed from: private */
    public void showLoginFailedDialog(String str) {
        hidePleaseWait();
        reportLoginFailure(str);
        AlertDialogFragment newInstance = AlertDialogFragment.newInstance();
        ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setTitle(R.string.login_failed)).setMessage(getString(R.string.unable_to_connect_to_server_for_login))).setPositiveText(R.string.try_again, this.onRetryLoginButtonListener)).setNegativeText(R.string.close, null);
        newInstance.setCancelable(false);
        showDialogFragment(newInstance, LOGIN_FAILED_DIALOG_TAG);
    }
}
