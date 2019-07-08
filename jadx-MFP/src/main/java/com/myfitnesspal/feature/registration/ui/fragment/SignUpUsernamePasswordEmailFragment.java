package com.myfitnesspal.feature.registration.ui.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.model.ConsentsViewModel;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelperImpl;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.IdmEmailUniquenessCheck;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.feature.registration.task.SignUpTask;
import com.myfitnesspal.feature.registration.task.SignUpTask.CompletedEvent;
import com.myfitnesspal.feature.registration.task.ValidateUsernameAndEmailTask;
import com.myfitnesspal.feature.registration.task.ValidateUsernameTask;
import com.myfitnesspal.feature.registration.ui.activity.FinishOnboardingActivity;
import com.myfitnesspal.feature.registration.ui.activity.LoginActivity;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity.Mode;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.feature.registration.util.SignUpUtil.PrivacyAndMarketingType;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.model.v15.UsernameValidationObject;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.uacf.consentservices.sdk.UacfConsentResponseStatus;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class SignUpUsernamePasswordEmailFragment extends SignUpFragmentBase implements SignUpCredentialsFragment {
    private static final String ACCOUNT_EXISTS_DIALOG_FRAGMENT_TAG = "account_exists_dialog";
    private static final int EMAIL_MFP_TAKEN = 1;
    private static final int EMAIL_NOT_TAKEN = 0;
    private static final int EMAIL_UACF_TAKEN = 2;
    private static final String EXTRA_EMAIL_VALIDATION_FLAGS = "email_validation_flags";
    public static final String EXTRA_PASSWORD = "password";
    private static final String EXTRA_SIGN_UP_MODE_ORDINAL = "sign_up_mode_ordinal";
    private static final String KEY_BUTTON_CLICK = "button_click";
    private static final int MINIMUM_PASSWORD_LENGTH = 6;
    private static final String SIGN_UP_ERROR_CODE = "sign_up_error_code";
    private static final String SIGN_UP_ERROR_STRING = "sign_up_error_string";
    private static final String VALUE_CANCEL = "cancel";
    private static final String VALUE_LOGIN = "login";
    @BindView(2131362200)
    TextView consentErrorTextView;
    private Disposable consentStatusDisposable;
    @Inject
    Lazy<ConsentsAnalyticsHelper> consentsAnalyticsHelper;
    @Inject
    Lazy<ConsentsService> consentsService;
    @Inject
    Lazy<CountryService> countryService;
    @BindView(2131362331)
    CheckBox disclaimerCheckbox;
    @BindView(2131362332)
    View disclaimerCheckboxContainer;
    @BindView(2131362333)
    TextView disclaimerCheckboxText;
    @BindView(2131362335)
    TextView disclaimerText;
    @BindView(2131362445)
    EditText emailEdit;
    @BindView(2131362447)
    View emailPasswordContainer;
    private int emailValidationFlags = 0;
    private boolean isMarketingInterstitialEnabled;
    @Inject
    LoginModel loginModel;
    @BindView(2131363144)
    CheckBox newsletterCheckBox;
    private DialogNegativeListener onAccountExistsCancelButtonPressed = new DialogNegativeListener() {
        public final void onClick() {
            SignUpUsernamePasswordEmailFragment.lambda$new$6(SignUpUsernamePasswordEmailFragment.this);
        }
    };
    private DialogPositiveListener onAccountExistsLogInButtonPressed = new DialogPositiveListener() {
        public void onClick(Object obj) {
            boolean access$200 = SignUpUsernamePasswordEmailFragment.this.isMfpAccountTaken();
            SignUpUsernamePasswordEmailFragment.this.getAnalyticsService().reportEvent(access$200 ? Events.MFP_ACCOUNT_EXISTS_CLICK : Events.LOGIN_UA_ACCOUNT_EXISTS_CLICK, MapUtil.createMap(SignUpUsernamePasswordEmailFragment.KEY_BUTTON_CLICK, "login"));
            String obj2 = SignUpUsernamePasswordEmailFragment.this.emailEdit.getText().toString();
            SignUpUsernamePasswordEmailFragment.this.loginModel.setUsername(obj2);
            SignUpUsernamePasswordEmailFragment.this.loginModel.setLastUsername(obj2);
            if (access$200) {
                SignUpUsernamePasswordEmailFragment.this.getNavigationHelper().withIntent(LoginActivity.newStartIntentForEmailLogin(SignUpUsernamePasswordEmailFragment.this.getContext())).finishActivityAfterNavigation().startActivity();
            } else {
                SignUpUsernamePasswordEmailFragment.this.getNavigationHelper().withIntent(FinishOnboardingActivity.newStartIntent(SignUpUsernamePasswordEmailFragment.this.getContext(), obj2)).fromFragment(SignUpUsernamePasswordEmailFragment.this).startActivity(26);
            }
            SignUpUsernamePasswordEmailFragment.this.showProgressDialog(R.string.please_wait);
        }
    };
    @BindView(2131363247)
    EditText passwordEdit;
    @BindView(2131363307)
    CheckBox privacyCheckBox;
    @BindView(2131363664)
    Button signUpBtn;
    private Mode signUpMode;
    @Inject
    SignUpModel signUpModel;
    @BindView(2131363754)
    TextView suggestedNamesText;
    private UacfConsentStatus uacfConsentStatus;
    @Inject
    Lazy<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelper;
    @BindView(2131364122)
    EditText usernameEdit;
    @BindView(2131364123)
    View usernameTakenContainer;

    enum UsernameCheckContext {
        PrePopulate,
        SignUp
    }

    static /* synthetic */ void lambda$validate$0(DialogInterface dialogInterface, int i) {
    }

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return Screens.SIGN_UP_SSO_USERNAME;
    }

    public boolean shouldShowNextButtonInToolbar() {
        return false;
    }

    public static SignUpUsernamePasswordEmailFragment newInstance(Mode mode) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_SIGN_UP_MODE_ORDINAL, mode.ordinal());
        SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment = new SignUpUsernamePasswordEmailFragment();
        signUpUsernamePasswordEmailFragment.setArguments(bundle);
        return signUpUsernamePasswordEmailFragment;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        this.signUpMode = Mode.values()[BundleUtils.getInt(getArguments(), EXTRA_SIGN_UP_MODE_ORDINAL)];
        if (bundle != null) {
            this.emailValidationFlags = bundle.getInt(EXTRA_EMAIL_VALIDATION_FLAGS, 0);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(EXTRA_EMAIL_VALIDATION_FLAGS, this.emailValidationFlags);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sign_up_sso_username, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initUi(bundle);
        initListeners();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!ACCOUNT_EXISTS_DIALOG_FRAGMENT_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
        alertDialogFragment.setPositiveListener(this.onAccountExistsLogInButtonPressed);
        alertDialogFragment.setNegativeListener(this.onAccountExistsCancelButtonPressed);
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 26) {
            if (i2 == FinishOnboardingActivity.RESULT_LOGGED_IN_TO_VERTICAL) {
                getActivity().setResult(-1);
                getActivity().finish();
            } else if (i2 == FinishOnboardingActivity.RESULT_LOGGED_IN_TO_NON_VERTICAL) {
                finishSignUp(ExtrasUtils.getString(intent, "password"));
            }
        } else if (i == 206 && i2 == -1) {
            onValidated();
        }
    }

    public boolean isMarketingOptInEnabled() {
        return this.isMarketingInterstitialEnabled;
    }

    public void validate() {
        getImmHelper().hideSoftInput();
        boolean z = this.signUpMode != Mode.Profile;
        String obj = this.passwordEdit.getText().toString();
        String obj2 = this.emailEdit.getText().toString();
        if (z) {
            if (obj.length() < 6) {
                showErrorDialog((int) R.string.auth_dialog_password_too_short);
                return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(obj2).matches()) {
                showErrorDialog((int) R.string.auth_dialog_invalid_email);
                return;
            }
        }
        String strings = Strings.toString(this.usernameEdit.getText());
        if (strings.length() < 4) {
            showErrorDialog(getString(R.string.username_too_short));
        } else if (strings.length() > 30) {
            showErrorDialog(getString(R.string.username_too_long));
        } else if (!ViewUtils.isVisible(this.disclaimerCheckboxContainer) || this.disclaimerCheckbox.isChecked()) {
            if (z) {
                this.signUpModel.setPassword(obj);
                startEmailAndUsernameValidation(UsernameCheckContext.SignUp, strings, obj2);
            } else {
                startUsernameValidationCheck(UsernameCheckContext.SignUp, strings);
            }
        } else {
            ((ConsentsAnalyticsHelper) this.consentsAnalyticsHelper.get()).reportOnBoardingTOSNotChecked();
            new MfpAlertDialogBuilder(getContext()).setMessage((int) R.string.signup_agree_terms).setPositiveButton((int) R.string.ok, (OnClickListener) $$Lambda$SignUpUsernamePasswordEmailFragment$ww9MY1swv1gnO5UKNLErGcS9Vvs.INSTANCE).show();
        }
    }

    @Subscribe
    public void onSignUpCompletedEvent(CompletedEvent completedEvent) {
        String str;
        if (completedEvent.isFrom(getRunner())) {
            hideProgressDialog();
            if (completedEvent.successful()) {
                onValidated();
            } else {
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
    }

    @Subscribe
    public void onValidateUsernameAndEmailCompleted(ValidateUsernameAndEmailTask.CompletedEvent<UsernameCheckContext> completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            boolean z = true;
            if (completedEvent.successful()) {
                UsernameValidationObject usernameCheckResult = completedEvent.getUsernameCheckResult();
                if (!usernameCheckResult.isValid()) {
                    reportUserNameTakenAndPopulateUsernameSuggestions(usernameCheckResult);
                    return;
                }
                hideSuggestedUsernames();
                IdmEmailUniquenessCheck emailCheckResult = completedEvent.getEmailCheckResult();
                this.signUpModel.setUacfUserId(emailCheckResult.getUacfUserId());
                this.emailValidationFlags = 0;
                if (emailCheckResult.isMfpEmailTaken()) {
                    this.emailValidationFlags |= 1;
                }
                if (emailCheckResult.isUacfEmailTaken()) {
                    this.emailValidationFlags |= 2;
                }
                if (this.emailValidationFlags != 0) {
                    showAccountExistsDialog();
                } else {
                    finishSignUp(Strings.toString(this.passwordEdit.getText()));
                    z = false;
                }
            } else {
                showErrorDialog((int) R.string.unable_to_validate_username);
                setValidationCheckmark(0);
            }
            if (z) {
                hideProgressDialog();
            }
        }
    }

    @Subscribe
    public void onValidateUsernameCompleted(ValidateUsernameTask.CompletedEvent<UsernameCheckContext> completedEvent) {
        if (completedEvent.isFrom(getRunner()) && completedEvent.successful()) {
            UsernameValidationObject usernameValidationObject = (UsernameValidationObject) completedEvent.getResult();
            if (completedEvent.getCallContext() == UsernameCheckContext.PrePopulate) {
                if (!Strings.notEmpty(this.usernameEdit.getText().toString())) {
                    if (usernameValidationObject.isValid()) {
                        this.usernameEdit.setText(completedEvent.getUsername());
                    } else if (usernameValidationObject.getSuggestedUsernames().size() > 0) {
                        this.usernameEdit.setText((CharSequence) usernameValidationObject.getSuggestedUsernames().get(0));
                    }
                }
            } else if (!usernameValidationObject.isValid()) {
                reportUserNameTakenAndPopulateUsernameSuggestions(usernameValidationObject);
                hideProgressDialog();
            } else {
                hideSuggestedUsernames();
                finishSignUp(null);
            }
        }
    }

    private void initUi(Bundle bundle) {
        setTitle(R.string.details, new Object[0]);
        PrivacyAndMarketingType privacyAndMarketingType = SignUpUtil.getPrivacyAndMarketingType(getCountryService(), this.signUpModel);
        ViewUtils.setVisible(true, this.signUpBtn);
        this.signUpBtn.setEnabled(false);
        setBusy(true);
        this.isMarketingInterstitialEnabled = false;
        fetchConsentsStatus();
        if (bundle == null) {
            this.newsletterCheckBox.setChecked(privacyAndMarketingType == PrivacyAndMarketingType.USPrivacyAndMarketing);
            String emailAddress = this.signUpModel.getEmailAddress();
            if (Strings.notEmpty(emailAddress)) {
                this.emailEdit.setText(emailAddress);
            }
        }
        boolean z = this.signUpMode == Mode.Profile;
        ViewUtils.setVisible(!z, this.emailPasswordContainer);
        if (bundle == null && z) {
            startUsernameValidationCheck(UsernameCheckContext.PrePopulate, extractUsernameFromEmailAddress(this.signUpModel.getEmailAddress()));
        }
    }

    private void initDisclaimerForGDPR() {
        if (this.uacfConsentStatus != null) {
            SignUpUtil.setupDisclaimerTextForGDPR(this.disclaimerText, getNavigationHelper(), R.string.signup_disclaimer_us, this.updatedTermsAnalyticsHelper, ConsentsAnalyticsHelperImpl.CREATE_USERNAME_SEE);
            SignUpUtil.setupDisclaimerTextForGDPR(this.disclaimerCheckboxText, getNavigationHelper(), R.string.signup_disclaimer_non_us, this.updatedTermsAnalyticsHelper, ConsentsAnalyticsHelperImpl.CREATE_USERNAME_SEE);
            boolean z = true;
            ViewUtils.setVisible(this.uacfConsentStatus.getConsentStandard().equals(Country.UNITED_STATES_SHORT), this.disclaimerText);
            ViewUtils.setVisible(!this.uacfConsentStatus.getConsentStandard().equals(Country.UNITED_STATES_SHORT), this.disclaimerCheckboxContainer);
            ConsentsAnalyticsHelper consentsAnalyticsHelper2 = (ConsentsAnalyticsHelper) this.consentsAnalyticsHelper.get();
            if (this.disclaimerCheckboxContainer.getVisibility() != 0) {
                z = false;
            }
            consentsAnalyticsHelper2.reportCreateUsernameSee(z);
        }
    }

    private void initListeners() {
        this.privacyCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SignUpUsernamePasswordEmailFragment.lambda$initListeners$1(SignUpUsernamePasswordEmailFragment.this, compoundButton, z);
            }
        });
        this.usernameEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                SignUpUsernamePasswordEmailFragment.lambda$initListeners$2(SignUpUsernamePasswordEmailFragment.this, view, z);
            }
        });
        this.usernameEdit.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpUsernamePasswordEmailFragment.this.setValidationCheckmark(0);
            }
        });
        this.emailEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                SignUpUsernamePasswordEmailFragment.lambda$initListeners$3(SignUpUsernamePasswordEmailFragment.this, view, z);
            }
        });
        this.signUpBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SignUpUsernamePasswordEmailFragment.lambda$initListeners$5(SignUpUsernamePasswordEmailFragment.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initListeners$1(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, CompoundButton compoundButton, boolean z) {
        if (z) {
            ViewUtils.setGone(signUpUsernamePasswordEmailFragment.consentErrorTextView);
        }
    }

    public static /* synthetic */ void lambda$initListeners$2(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, View view, boolean z) {
        if (z) {
            signUpUsernamePasswordEmailFragment.usernameEdit.getBackground().clearColorFilter();
            if (ViewUtils.isVisible(signUpUsernamePasswordEmailFragment.usernameTakenContainer)) {
                signUpUsernamePasswordEmailFragment.setValidationCheckmark(0);
                signUpUsernamePasswordEmailFragment.hideSuggestedUsernames();
            }
        } else if (Strings.isEmpty(Strings.toString(signUpUsernamePasswordEmailFragment.usernameEdit.getText()))) {
            signUpUsernamePasswordEmailFragment.setValidationCheckmark(0);
        }
    }

    public static /* synthetic */ void lambda$initListeners$3(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, View view, boolean z) {
        if (!z) {
            signUpUsernamePasswordEmailFragment.startUsernameValidationCheck(UsernameCheckContext.PrePopulate, extractUsernameFromEmailAddress(signUpUsernamePasswordEmailFragment.emailEdit.getText().toString()));
        }
    }

    public static /* synthetic */ void lambda$initListeners$5(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, View view) {
        FragmentActivity activity = signUpUsernamePasswordEmailFragment.getActivity();
        if (activity instanceof SignUpActivity) {
            SignUpActivity signUpActivity = (SignUpActivity) activity;
            UacfConsentStatus uacfConsentStatus2 = signUpUsernamePasswordEmailFragment.uacfConsentStatus;
            if (uacfConsentStatus2 == null) {
                signUpUsernamePasswordEmailFragment.showErrorDialog(signUpUsernamePasswordEmailFragment.getString(R.string.error_during_registration), new DialogPositiveListener(signUpActivity) {
                    private final /* synthetic */ SignUpActivity f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(Object obj) {
                        SignUpUsernamePasswordEmailFragment.lambda$null$4(SignUpUsernamePasswordEmailFragment.this, this.f$1, obj);
                    }
                });
                return;
            }
            signUpUsernamePasswordEmailFragment.signUpModel.setConsentsMatrixVersion(uacfConsentStatus2.getConsentMatrixVersion());
            if (signUpUsernamePasswordEmailFragment.uacfConsentStatus.getUacfConsentResponseStatus() != UacfConsentResponseStatus.NEW_USER) {
                ((ConsentsService) signUpUsernamePasswordEmailFragment.consentsService.get()).storeConsentData(signUpUsernamePasswordEmailFragment.uacfConsentStatus, ((CountryService) signUpUsernamePasswordEmailFragment.countryService.get()).getShortNameFromLongName(signUpUsernamePasswordEmailFragment.signUpModel.getCountryName()));
            }
            signUpActivity.goToNextStep();
        }
    }

    public static /* synthetic */ void lambda$null$4(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, SignUpActivity signUpActivity, Object obj) {
        signUpUsernamePasswordEmailFragment.setBusy(true);
        signUpActivity.clearDisposable();
        signUpActivity.fetchConsentStatus(signUpUsernamePasswordEmailFragment.signUpModel.getCountryName());
    }

    private void startUsernameValidationCheck(UsernameCheckContext usernameCheckContext, String str) {
        int length = str.length();
        if (length >= 4 && length <= 30) {
            if (usernameCheckContext == UsernameCheckContext.SignUp) {
                showProgressDialog(R.string.please_wait);
                setValidationCheckmark(0);
            }
            new ValidateUsernameTask(usernameCheckContext, this.signUpService, str).setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.CancelExisting).run(getRunner());
        }
    }

    private void finishSignUp(String str) {
        String str2;
        this.signUpModel.setUsername(Strings.toString(this.usernameEdit.getText()));
        if (this.signUpMode != Mode.Profile) {
            this.signUpModel.setEmailAddress(Strings.toString(this.emailEdit.getText()));
            this.signUpModel.setPassword(str);
        }
        if (this.isMarketingInterstitialEnabled) {
            hideProgressDialog();
            onValidated();
            return;
        }
        this.signUpModel.save();
        this.loginModel.save();
        String countryName = this.signUpModel.getCountryName();
        if (Strings.isEmpty(countryName)) {
            str2 = ((CountryService) this.countryService.get()).getCurrentCountry().getShortName();
        } else {
            str2 = ((CountryService) this.countryService.get()).getShortNameFromLongName(countryName);
        }
        UacfConsentStatus uacfConsentStatus2 = this.uacfConsentStatus;
        if (uacfConsentStatus2 == null || uacfConsentStatus2.getUacfConsentResponseStatus() != UacfConsentResponseStatus.NEW_USER) {
            startSignUpTask(this.signUpService, this.signUpModel, this.loginModel);
            return;
        }
        hideProgressDialog();
        getNavigationHelper().withIntent(ConsentsActivity.newStartIntent(getContext(), ConsentsViewModel.Mode.NEW, str2, Strings.toString(this.signUpModel.getPassword()))).fromFragment(this).startActivity(RequestCodes.CONSENTS);
    }

    private void startSignUpTask(SignUpService signUpService, SignUpModel signUpModel2, LoginModel loginModel2) {
        ((ConsentsAnalyticsHelper) this.consentsAnalyticsHelper.get()).reportOnBoardingConsentSkip();
        new SignUpTask(signUpService, signUpModel2, loginModel2).setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.UseExisting).run(getRunner());
    }

    private void startEmailAndUsernameValidation(UsernameCheckContext usernameCheckContext, String str, String str2) {
        if (usernameCheckContext == UsernameCheckContext.SignUp) {
            showProgressDialog(R.string.please_wait);
            setValidationCheckmark(0);
        }
        new ValidateUsernameAndEmailTask(usernameCheckContext, this.signUpService, str, str2).setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.CancelExisting).run(getRunner());
    }

    /* access modifiers changed from: private */
    public void setValidationCheckmark(int i) {
        this.usernameEdit.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
    }

    /* access modifiers changed from: private */
    public void hideSuggestedUsernames() {
        ViewUtils.setVisible(false, this.usernameTakenContainer);
    }

    private void reportUserNameTakenAndPopulateUsernameSuggestions(UsernameValidationObject usernameValidationObject) {
        getAnalyticsService().reportEvent(Events.USERNAME_IS_TAKEN);
        List<String> suggestedUsernames = usernameValidationObject.getSuggestedUsernames();
        if (suggestedUsernames.size() > 0) {
            getAnalyticsService().reportEvent(Events.USERNAME_SUGGESTIONS_DISPLAYED);
        }
        String join = Strings.join(", ", (Collection<T>) suggestedUsernames);
        SpannableString spannableString = new SpannableString(join);
        for (final String str : suggestedUsernames) {
            int indexOf = join.indexOf(str);
            if (indexOf >= 0) {
                AnonymousClass2 r4 = new ClickableSpan() {
                    public void onClick(View view) {
                        SignUpUsernamePasswordEmailFragment.this.usernameEdit.setText(str);
                        SignUpUsernamePasswordEmailFragment.this.usernameEdit.getBackground().clearColorFilter();
                        SignUpUsernamePasswordEmailFragment.this.signUpModel.setUsername(str);
                        SignUpUsernamePasswordEmailFragment.this.hideSuggestedUsernames();
                        SignUpUsernamePasswordEmailFragment.this.getAnalyticsService().reportEvent(Events.USERNAME_SUGGESTION_SELECTED);
                    }

                    public void updateDrawState(TextPaint textPaint) {
                        textPaint.setUnderlineText(false);
                    }
                };
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.blue));
                int length = str.length() + indexOf;
                spannableString.setSpan(r4, indexOf, length, 33);
                spannableString.setSpan(foregroundColorSpan, indexOf, length, 33);
            }
        }
        this.suggestedNamesText.setLinksClickable(true);
        this.suggestedNamesText.setMovementMethod(LinkMovementMethod.getInstance());
        this.suggestedNamesText.setText(spannableString, BufferType.SPANNABLE);
        ViewUtils.setVisible(true, this.usernameTakenContainer);
        hideProgressDialog();
    }

    private void showAccountExistsDialog() {
        AlertDialogFragment newInstance = AlertDialogFragment.newInstance();
        boolean isMfpAccountTaken = isMfpAccountTaken();
        ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setTitle(R.string.auth_dialog_account_exists_title)).setMessage(isMfpAccountTaken ? R.string.auth_dialog_legacy_account_exists : R.string.auth_dialog_account_exists_message)).setNegativeText(R.string.cancel, this.onAccountExistsCancelButtonPressed)).setPositiveText(R.string.auth_button_login, this.onAccountExistsLogInButtonPressed);
        newInstance.setCancelable(false);
        getAnalyticsService().reportEvent(isMfpAccountTaken ? Events.MFP_ACCOUNT_EXISTS : Events.LOGIN_UA_ACCOUNT_EXISTS);
        showDialogFragment(newInstance, ACCOUNT_EXISTS_DIALOG_FRAGMENT_TAG);
    }

    /* access modifiers changed from: private */
    public boolean isMfpAccountTaken() {
        return (this.emailValidationFlags & 1) != 0;
    }

    public static /* synthetic */ void lambda$new$6(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment) {
        signUpUsernamePasswordEmailFragment.getAnalyticsService().reportEvent(signUpUsernamePasswordEmailFragment.isMfpAccountTaken() ? Events.MFP_ACCOUNT_EXISTS_CLICK : Events.LOGIN_UA_ACCOUNT_EXISTS_CLICK, MapUtil.createMap(KEY_BUTTON_CLICK, VALUE_CANCEL));
    }

    private static String extractUsernameFromEmailAddress(String str) {
        return (Strings.isEmpty(str) || !str.contains("@")) ? "" : Strings.onlyCertainChars(str.substring(0, str.indexOf("@")), "a-zA-Z0-9_");
    }

    private void fetchConsentsStatus() {
        FragmentActivity activity = getActivity();
        if (activity instanceof SignUpActivity) {
            SignUpActivity signUpActivity = (SignUpActivity) activity;
            this.consentStatusDisposable = signUpActivity.getConsentStatusSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
                public final void accept(Object obj) {
                    SignUpUsernamePasswordEmailFragment.lambda$fetchConsentsStatus$7(SignUpUsernamePasswordEmailFragment.this, (UacfConsentStatus) obj);
                }
            }, new Consumer(signUpActivity) {
                private final /* synthetic */ SignUpActivity f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(Object obj) {
                    SignUpUsernamePasswordEmailFragment.lambda$fetchConsentsStatus$9(SignUpUsernamePasswordEmailFragment.this, this.f$1, (Throwable) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$fetchConsentsStatus$7(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, UacfConsentStatus uacfConsentStatus2) throws Exception {
        signUpUsernamePasswordEmailFragment.uacfConsentStatus = uacfConsentStatus2;
        signUpUsernamePasswordEmailFragment.setBusy(false);
        signUpUsernamePasswordEmailFragment.signUpBtn.setEnabled(true);
        signUpUsernamePasswordEmailFragment.initDisclaimerForGDPR();
        ((ConsentsAnalyticsHelper) signUpUsernamePasswordEmailFragment.consentsAnalyticsHelper.get()).reportOnBoardingConsentsResponse(signUpUsernamePasswordEmailFragment.uacfConsentStatus.getConsents().size());
    }

    public static /* synthetic */ void lambda$fetchConsentsStatus$9(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, SignUpActivity signUpActivity, Throwable th) throws Exception {
        ((ConsentsAnalyticsHelper) signUpUsernamePasswordEmailFragment.consentsAnalyticsHelper.get()).reportOnBoardingConsentsError();
        signUpUsernamePasswordEmailFragment.setBusy(false);
        if (signUpUsernamePasswordEmailFragment.isAdded()) {
            signUpUsernamePasswordEmailFragment.showErrorDialog(signUpUsernamePasswordEmailFragment.getString(R.string.error_during_registration), new DialogPositiveListener(signUpActivity) {
                private final /* synthetic */ SignUpActivity f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(Object obj) {
                    SignUpUsernamePasswordEmailFragment.lambda$null$8(SignUpUsernamePasswordEmailFragment.this, this.f$1, obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$null$8(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, SignUpActivity signUpActivity, Object obj) {
        signUpUsernamePasswordEmailFragment.setBusy(true);
        signUpActivity.clearDisposable();
        signUpActivity.fetchConsentStatus(signUpUsernamePasswordEmailFragment.signUpModel.getCountryName());
    }

    public void onDestroyView() {
        super.onDestroyView();
        Disposable disposable = this.consentStatusDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.consentStatusDisposable.dispose();
        }
    }
}
