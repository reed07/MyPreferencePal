package com.myfitnesspal.feature.registration.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.registration.event.ExitSignUpEvent;
import com.myfitnesspal.feature.registration.event.GoToGoalEvent;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.LoginModel.FacebookData;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.task.FetchUacfIdTask;
import com.myfitnesspal.feature.registration.task.ValidateFacebookEmailAddressTask;
import com.myfitnesspal.feature.registration.task.ValidateFacebookEmailAddressTask.CompletedEvent;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpActivityLevelFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpCongratsFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpCredentialsFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpFragmentBase;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpGenderAgeFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpGoalTypeFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpMarketingOptInFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpUsernamePasswordEmailFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpWeeklyWeightGoalFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpWeightHeightFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpWelcomeFragment;
import com.myfitnesspal.shared.api.auth.SSO;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Registration;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.HideSoftInputEvent;
import com.myfitnesspal.shared.model.unitconv.LocalizedLength;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.v15.EmailUniquenessCheckObject;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.util.FragmentUtil;
import com.myfitnesspal.shared.util.Gender;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.myfitnesspal.shared.validation.Validator;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.identity.sdk.model.UacfLocation;
import com.uacf.identity.sdk.model.UacfVerticalAccountInfo;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;

public class SignUpActivity extends MfpActivity {
    public static final String EXTRA_EMAIL_ADDRESS = "extra_email_address";
    public static final String EXTRA_PASSWORD = "extra_password";
    public static final String EXTRA_REGISTRATION_MODE = "extra_registration_mode";
    private static final String EXTRA_REPORTED_EVENTS = "reported_events";
    private static final Map<Class<?>, String> FRAGMENT_TO_STEP_EVENT = new HashMap();
    private static final int MINIMUM_AGE_YEARS = 18;
    private static final String UNDERAGE_DIALOG_FRAGMENT = "underage_dialog_fragment";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Inject
    Lazy<ConfigService> configService;
    final Subject<UacfConsentStatus> consentStatusSubject = ReplaySubject.create();
    @Inject
    Lazy<ConsentsService> consentsService;
    @Inject
    @Named("emailValidator")
    Validator emailValidator;
    @Inject
    LoginModel loginModel;
    private DialogPositiveListener onUnderageDialogOkClickListener = new DialogPositiveListener() {
        public final void onClick(Object obj) {
            SignUpActivity.this.finish();
        }
    };
    private ArrayList<String> reportedSteps;
    @Inject
    SignUpModel signUpModel;
    @Inject
    SignUpService signUpService;

    public enum Mode {
        Full,
        Profile
    }

    public boolean shouldDisplayAds() {
        return false;
    }

    static {
        FRAGMENT_TO_STEP_EVENT.put(SignUpGoalTypeFragment.class, Events.SIGN_UP_FLOW_STEP_01_GOALS);
        FRAGMENT_TO_STEP_EVENT.put(SignUpActivityLevelFragment.class, Events.SIGN_UP_FLOW_STEP_02_ACTIVITY_LEVEL);
        FRAGMENT_TO_STEP_EVENT.put(SignUpGenderAgeFragment.class, Events.SIGN_UP_FLOW_STEP_03_GENDER_AGE);
        FRAGMENT_TO_STEP_EVENT.put(SignUpWeightHeightFragment.class, Events.SIGN_UP_FLOW_STEP_04_HEIGHT_WEIGHT);
        FRAGMENT_TO_STEP_EVENT.put(SignUpWeeklyWeightGoalFragment.class, Events.SIGN_UP_FLOW_STEP_05_WEEKLY_WEIGHT_GOAL);
        FRAGMENT_TO_STEP_EVENT.put(SignUpUsernamePasswordEmailFragment.class, Events.SIGN_UP_FLOW_STEP_06_CREATE_USERNAME);
        FRAGMENT_TO_STEP_EVENT.put(SignUpMarketingOptInFragment.class, Events.SIGN_UP_FLOW_STEP_07_MARKETING_OPT_IN);
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    public static Intent newStartIntent(Context context, Mode mode) {
        return newStartIntent(context, null, null, mode);
    }

    public static Intent newStartIntent(Context context, String str, String str2, Mode mode) {
        return new Intent(context, SignUpActivity.class).putExtra(EXTRA_EMAIL_ADDRESS, str).putExtra("extra_password", str2).putExtra(EXTRA_REGISTRATION_MODE, mode);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.new_registration);
        Bundle extras = getIntent().getExtras();
        getWindow().setSoftInputMode(3);
        if (bundle != null) {
            this.reportedSteps = BundleUtils.getStringArrayList(bundle, EXTRA_REPORTED_EVENTS);
        } else {
            getAnalyticsService().reportEvent(String.format(Locale.ENGLISH, Events.SIGN_UP_FLOW_FORMAT, new Object[]{Events.STARTED}));
            this.signUpModel.clear();
            this.signUpModel.setEmailAddress(extras.getString(EXTRA_EMAIL_ADDRESS));
            this.signUpModel.setPassword(extras.getString("extra_password"));
        }
        if (this.reportedSteps == null) {
            this.reportedSteps = new ArrayList<>();
        }
        initFromFacebookIfUserExists();
        if (bundle == null) {
            Mode currentMode = getCurrentMode();
            if (currentMode != Mode.Profile) {
                showInitialSignUpFragment(currentMode);
            } else if (!failIfUserIsUnderage()) {
                new FetchUacfIdTask(this.signUpModel.getEmailAddress(), currentMode).run(getRunner());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        clearDisposable();
        super.onDestroy();
    }

    public void clearDisposable() {
        if (!this.compositeDisposable.isDisposed()) {
            this.compositeDisposable.clear();
        }
    }

    private Mode getCurrentMode() {
        return (Mode) BundleUtils.getSerializable(EXTRA_REGISTRATION_MODE, Mode.Full, Mode.class.getClassLoader(), getIntent().getExtras());
    }

    private void showInitialSignUpFragment(Mode mode) {
        Fragment fragment;
        if (mode == Mode.Full) {
            fragment = SignUpGoalTypeFragment.newInstance();
        } else {
            fragment = SignUpWelcomeFragment.newInstance();
        }
        String name = fragment.getClass().getName();
        getSupportFragmentManager().beginTransaction().addToBackStack(name).setCustomAnimations(0, 0, R.anim.slide_in_left_100_medium, R.anim.slide_out_right_100_medium).replace(R.id.container, fragment, name).commit();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        SignUpFragmentBase visibleFragment = getVisibleFragment();
        if (visibleFragment != null && visibleFragment.shouldShowNextButtonInToolbar()) {
            addProminentActionItem(menu, R.string.next, new OnClickListener() {
                public final void onClick(View view) {
                    SignUpActivity.this.goToNextStep();
                }
            });
        }
        return true;
    }

    public void onUpPressed() {
        previousFragment();
    }

    public void onBackPressed() {
        previousFragment();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList(EXTRA_REPORTED_EVENTS, this.reportedSteps);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!UNDERAGE_DIALOG_FRAGMENT.equals(str)) {
            return false;
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onUnderageDialogOkClickListener);
        return true;
    }

    public final void onStepValidated() {
        nextFragment();
    }

    @Subscribe
    public void onExitSignUpEvent(ExitSignUpEvent exitSignUpEvent) {
        this.signUpModel.clear();
        getNavigationHelper().finishActivityAfterNavigation().withClearTopAndNewTask().setResultOk().withIntent(LoginActivity.newStartIntent(this)).startActivity();
    }

    @Subscribe
    public void onGoToGoalEvent(GoToGoalEvent goToGoalEvent) {
        getSupportFragmentManager().popBackStack(SignUpGoalTypeFragment.class.getName(), 0);
    }

    @Subscribe
    public void onValidateEmailAddressCompleted(CompletedEvent completedEvent) {
        if (!completedEvent.isFrom(getRunner())) {
            return;
        }
        if (!completedEvent.successful()) {
            getMessageBus().post(new AlertEvent(getString(R.string.unable_to_validate_email)));
        } else if (((EmailUniquenessCheckObject) completedEvent.getResult()).isUnique()) {
            this.signUpModel.setEmailAddress(completedEvent.getEmailAddress());
        }
    }

    @Subscribe
    public void onFetchUacfIdTaskCompletedEvent(FetchUacfIdTask.CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            populateModelWithUacfData((String) completedEvent.getResult());
            showInitialSignUpFragment(completedEvent.getMode());
        }
    }

    private void populateModelWithUacfData(String str) {
        UacfVerticalAccountInfo currentUserAccount = SSO.getSdk().getCurrentUserAccount();
        if (currentUserAccount != null) {
            UacfLocation location = currentUserAccount.getLocation();
            Double height = currentUserAccount.getHeight();
            Double weight = currentUserAccount.getWeight();
            SignUpModel signUpModel2 = this.signUpModel;
            if (!Strings.notEmpty(str)) {
                str = currentUserAccount.getUacfUserId();
            }
            signUpModel2.setUacfUserId(str);
            this.signUpModel.setBirthday(currentUserAccount.getBirthdate());
            this.signUpModel.setGender(Gender.fromString(currentUserAccount.getGender()));
            if (location != null) {
                this.signUpModel.setZipCode(location.getPostalCode());
                this.signUpModel.setCountryName(location.getCountry());
                this.signUpModel.setRegion(location.getRegion());
                fetchConsentStatus(this.signUpModel.getCountryName());
            }
            if (height != null && height.doubleValue() > 0.0d) {
                this.signUpModel.setCurrentHeight(validateHeight(LocalizedLength.fromCentimeters(height.doubleValue() * 100.0d)));
            }
            if (weight != null && weight.doubleValue() > 0.0d) {
                this.signUpModel.setCurrentWeight(validateWeight(LocalizedWeight.fromKilograms(weight.doubleValue())));
            }
        }
    }

    private LocalizedLength validateHeight(LocalizedLength localizedLength) {
        double value = localizedLength.getValue(Length.INCHES);
        if (value > 96.0d || value < 36.0d) {
            return null;
        }
        return localizedLength;
    }

    private LocalizedWeight validateWeight(LocalizedWeight localizedWeight) {
        double value = localizedWeight.getValue(Weight.POUNDS);
        if (value > 999.0d || value < 30.0d) {
            return null;
        }
        return localizedWeight;
    }

    public void goToNextStep() {
        getImmHelper().hideSoftInput();
        SignUpFragmentBase visibleFragment = getVisibleFragment();
        if (visibleFragment != null) {
            visibleFragment.validate();
        }
    }

    private SignUpFragmentBase getVisibleFragment() {
        return (SignUpFragmentBase) FragmentUtil.getVisibleFragment(getSupportFragmentManager(), R.id.container);
    }

    private void reportStepCompleted(Class<?> cls) {
        String str = (String) FRAGMENT_TO_STEP_EVENT.get(cls);
        if (str != null && !this.reportedSteps.contains(str)) {
            getAnalyticsService().reportEvent(str);
            this.reportedSteps.add(str);
        }
    }

    private void nextFragment() {
        SignUpFragmentBase visibleFragment = getVisibleFragment();
        Fragment newInstance = visibleFragment instanceof SignUpWelcomeFragment ? SignUpGoalTypeFragment.newInstance() : null;
        if (visibleFragment instanceof SignUpGoalTypeFragment) {
            newInstance = SignUpActivityLevelFragment.newInstance();
        } else if (visibleFragment instanceof SignUpActivityLevelFragment) {
            newInstance = SignUpGenderAgeFragment.newInstance();
        } else if (visibleFragment instanceof SignUpGenderAgeFragment) {
            newInstance = SignUpWeightHeightFragment.newInstance();
        } else if (visibleFragment instanceof SignUpWeightHeightFragment) {
            reportStepCompleted(SignUpWeightHeightFragment.class);
            String goalType = this.signUpModel.getGoalType();
            if (Strings.equals(goalType, Registration.LOSE) || Strings.equals(goalType, Registration.GAIN)) {
                newInstance = SignUpWeeklyWeightGoalFragment.newInstance();
            } else if (Strings.equals(goalType, Registration.MAINTAIN)) {
                newInstance = newUsernameFragment();
            }
        } else if (visibleFragment instanceof SignUpWeeklyWeightGoalFragment) {
            newInstance = newUsernameFragment();
        } else if (visibleFragment instanceof SignUpUsernamePasswordEmailFragment) {
            newInstance = ((SignUpCredentialsFragment) visibleFragment).isMarketingOptInEnabled() ? SignUpMarketingOptInFragment.newInstance(com.myfitnesspal.feature.registration.ui.fragment.SignUpMarketingOptInFragment.Mode.SignUp) : SignUpCongratsFragment.newInstance(this.loginModel.getFacebookData().isValid());
        } else if (visibleFragment instanceof SignUpMarketingOptInFragment) {
            newInstance = SignUpCongratsFragment.newInstance(this.loginModel.getFacebookData().isValid());
        }
        if (newInstance != null) {
            reportStepCompleted(visibleFragment.getClass());
            String name = newInstance.getClass().getName();
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right_100_medium, R.anim.slide_out_left_100_medium, R.anim.slide_in_left_100_medium, R.anim.slide_out_right_100_medium).addToBackStack(name).replace(R.id.container, newInstance, name).commit();
        }
    }

    private SignUpFragmentBase newUsernameFragment() {
        return SignUpUsernamePasswordEmailFragment.newInstance(getCurrentMode());
    }

    private void previousFragment() {
        postEvent(new HideSoftInputEvent());
        SignUpFragmentBase visibleFragment = getVisibleFragment();
        if (visibleFragment == null || (visibleFragment instanceof SignUpWelcomeFragment) || (visibleFragment instanceof SignUpGoalTypeFragment) || (visibleFragment instanceof SignUpCongratsFragment)) {
            setResult(0);
            finish();
            return;
        }
        visibleFragment.onNavigatedBack();
        getSupportFragmentManager().popBackStack();
    }

    private void initFromFacebookIfUserExists() {
        FacebookData facebookData = this.loginModel.getFacebookData();
        if (facebookData.isValid()) {
            if (this.emailValidator.validate(facebookData.getEmail())) {
                new ValidateFacebookEmailAddressTask(this.signUpService, facebookData.getEmail()).setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.CancelExisting).run(getRunner());
            }
            if (facebookData.getBirthday() != null) {
                this.signUpModel.setBirthday(facebookData.getBirthday());
            }
            if (facebookData.getGender() != null) {
                this.signUpModel.setGender(Strings.toString(facebookData.getGender()));
            }
        }
    }

    private boolean failIfUserIsUnderage() {
        Date birthday = this.signUpModel.getBirthday();
        if (birthday != null) {
            Calendar instance = Calendar.getInstance();
            instance.add(1, -18);
            if (isDateAfter(birthday, instance.getTime(), 0)) {
                getAnalyticsService().reportEvent(Events.UNDERAGE_REGISTRATION_FAILED);
                AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.underage_registration_fail)).setTitle(R.string.error)).setPositiveText(R.string.ok, this.onUnderageDialogOkClickListener);
                alertDialogFragment.setCancelable(false);
                showDialogFragment(alertDialogFragment, UNDERAGE_DIALOG_FRAGMENT);
                return true;
            }
        }
        return false;
    }

    private static boolean isDateAfter(Date date, Date date2, long j) {
        return new Date(date.getTime() + (j * 1000)).after(date2);
    }

    public Subject<UacfConsentStatus> getConsentStatusSubject() {
        return this.consentStatusSubject;
    }

    public void fetchConsentStatus(String str) {
        this.compositeDisposable.add(((ConsentsService) this.consentsService.get()).getConsentStatusFromCountryName(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                SignUpActivity.lambda$fetchConsentStatus$2(SignUpActivity.this, (UacfConsentStatus) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                SignUpActivity.this.consentStatusSubject.onError((Throwable) obj);
            }
        }));
    }

    public static /* synthetic */ void lambda$fetchConsentStatus$2(SignUpActivity signUpActivity, UacfConsentStatus uacfConsentStatus) throws Exception {
        if (uacfConsentStatus != null) {
            signUpActivity.consentStatusSubject.onNext(uacfConsentStatus);
        } else {
            signUpActivity.consentStatusSubject.onError(new Throwable("Consent Status is empty"));
        }
    }
}
