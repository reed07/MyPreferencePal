package com.myfitnesspal.feature.registration.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity.Mode;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.feature.walkthrough.ui.activity.WalkthroughLoggingActivity;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.SignUpUpsell;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events.PreGDPROnBoarding;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.uacf.consentservices.sdk.UacfConsent;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;

public class SignUpCongratsFragment extends SignUpFragmentBase {
    private static final String EXTRA_ENERGY_UNIT = "extra_energy_unit";
    private static final String EXTRA_FB_DATA_VALID = "extra_fb_data_valid";
    private static final String KEY_MARKETING_OPTIN = "marketing_optin";
    private static final String KEY_REMINDER_CHECKBOX_CHECKED = "reminders_checkbox_checked";
    private static final String OFF = "off";
    private static final String ON = "on";
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<ConfigService> configService;
    private Disposable consentStatusDisposable;
    @Inject
    Lazy<CountryService> countryService;
    /* access modifiers changed from: private */
    public Energy energyUnit;
    @BindView(2131362476)
    Spinner energyUnitsSpinner;
    @BindView(2131362056)
    TextView energyValueText;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @BindView(2131362135)
    TextView newsLetterCheckBoxSubtext;
    @BindView(2131363144)
    CheckBox newsletterCheckBox;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    private String onBoardingType;
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131362139)
    CompoundButton remindersCheckBox;
    @Inject
    Lazy<RemindersService> remindersService;
    @BindView(2131363721)
    TextView startTracking;
    private UacfConsentStatus uacfConsentStatus;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return null;
    }

    public boolean shouldShowNextButtonInToolbar() {
        return false;
    }

    public void validate() {
    }

    public static SignUpCongratsFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_FB_DATA_VALID, z);
        SignUpCongratsFragment signUpCongratsFragment = new SignUpCongratsFragment();
        signUpCongratsFragment.setArguments(bundle);
        return signUpCongratsFragment;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        if (bundle == null) {
            getAnalyticsService().reportEvent(Screens.SIGN_UP_CONGRATS, MapUtil.createMap(KEY_MARKETING_OPTIN, String.valueOf(getSession().getUser().getUserV1().allNewslettersEnabled())));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.sign_up_congrats, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        boolean z = false;
        setTitle(R.string.account_created, new Object[0]);
        ((MfpActivity) getActivity()).getToolbar().setNavigationIcon((Drawable) null);
        if (getArguments() != null) {
            this.onBoardingType = SignUpUtil.getOnBoardingType(getArguments().getBoolean(EXTRA_FB_DATA_VALID), (Mode) BundleUtils.getSerializable(getActivity().getIntent().getExtras(), SignUpActivity.EXTRA_REGISTRATION_MODE, Mode.class.getClassLoader()));
        }
        User user = getSession().getUser();
        if (user.isLoggedIn()) {
            Calendar instance = Calendar.getInstance();
            ((ActionTrackingService) this.actionTrackingService.get()).registerEvent("sign_up");
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent("sign_up", "user", getSession().getUser().getUsername());
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent("sign_up", "date", String.valueOf(instance.getTime().getTime()));
            if (bundle == null) {
                this.energyUnit = Energy.fromInt(user.getEnergyUnitPreference());
            } else {
                this.energyUnit = (Energy) BundleUtils.getSerializable(bundle, EXTRA_ENERGY_UNIT, Energy.class.getClassLoader());
            }
            initListeners();
            initViews();
            ((LocalSettingsService) this.localSettingsService.get()).setIsNewUser(true);
            fetchConsentsStatus();
        } else {
            getSession().logoutAndNavigateToLoginActivity();
        }
        if (((PremiumService) this.premiumService.get()).isPremiumAvailableGeographically() && getConfigService().isVariantEnabled(SignUpUpsell.NAME)) {
            z = true;
        }
        if (z) {
            this.startTracking.setText(R.string.continueBtn);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(EXTRA_ENERGY_UNIT, this.energyUnit);
    }

    private void initListeners() {
        this.startTracking.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SignUpCongratsFragment.lambda$initListeners$0(SignUpCongratsFragment.this, view);
            }
        });
        this.energyUnitsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int energyUnitPreference = SignUpCongratsFragment.this.getSession().getUser().getEnergyUnitPreference();
                SignUpCongratsFragment.this.energyUnit = i == 0 ? Energy.CALORIES : Energy.KILOJOULES;
                int value = SignUpCongratsFragment.this.energyUnit.getValue();
                if (energyUnitPreference != value) {
                    SignUpCongratsFragment.this.getAnalyticsService().reportEvent(Events.SIGN_UP_CHANGE_ENERGY, new Builder().put("unit", SignUpCongratsFragment.this.energyUnit.name().toLowerCase()).build());
                }
                SignUpCongratsFragment.this.getSession().getUser().setEnergyUnitPreference(value);
                SignUpCongratsFragment.this.redrawEnergyValue();
            }
        });
    }

    public static /* synthetic */ void lambda$initListeners$0(SignUpCongratsFragment signUpCongratsFragment, View view) {
        signUpCongratsFragment.reportFinalAnalyticsAndStartSync();
        signUpCongratsFragment.recordNewsLettersSetting();
        signUpCongratsFragment.getAnalyticsService().reportEvent(Events.CONGRATS_START_TRACKING, MapUtil.createMap(KEY_REMINDER_CHECKBOX_CHECKED, String.valueOf(signUpCongratsFragment.remindersCheckBox.isChecked())));
        signUpCongratsFragment.reportPreGDPRAnalytics();
        signUpCongratsFragment.getNavigationHelper().withClearTopAndNewTask().setResultOk().finishActivityAfterNavigation().withIntent(WalkthroughLoggingActivity.newStartIntent(signUpCongratsFragment.getActivity())).startActivity();
    }

    private void initViews() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.sign_up_congrats_spinner_item, new String[]{getString(R.string.calories), getString(R.string.kilojoules)});
        arrayAdapter.setDropDownViewResource(R.layout.sign_up_spinner_drop_item);
        this.energyUnitsSpinner.setAdapter(arrayAdapter);
        redrawEnergyValue();
    }

    /* access modifiers changed from: private */
    public void redrawEnergyValue() {
        this.energyValueText.setText(LocalizedEnergy.getDisplayStringWithoutUnits(LocalizedEnergy.fromCalories((double) ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getDefaultEnergyGoal(Energy.CALORIES)), this.energyUnit));
    }

    /* access modifiers changed from: protected */
    public void sendScreenViewAnalytics() {
        super.sendScreenViewAnalytics();
        getAnalyticsService().reportEvent(String.format(Locale.ENGLISH, Events.SIGN_UP_FLOW_FORMAT, new Object[]{"Completed"}));
    }

    private void reportFinalAnalyticsAndStartSync() {
        boolean isChecked = this.remindersCheckBox.isChecked();
        getAnalyticsService().reportEvent(isChecked ? Events.SIGN_UP_REMINDERS_CHECKBOX_CHECKED : Events.SIGN_UP_REMINDERS_CHECKBOX_UNCHECKED);
        ((RemindersService) this.remindersService.get()).addDefaultRemindersIfNecessary(isChecked);
        postEvent(new StartSyncEvent());
    }

    private void recordNewsLettersSetting() {
        boolean isChecked = this.newsletterCheckBox.isChecked();
        getSession().getUser().updateNewsletterSettings(isChecked);
        getAnalyticsService().reportEvent(isChecked ? Events.OPTED_IN : Events.OPT_OUT);
    }

    private void setupMarketingCheckBox() {
        setTextBasedOnCountryforCheckBox();
        ViewUtils.setVisible(true, this.newsletterCheckBox);
        this.newsletterCheckBox.setChecked(shouldCheckMarketingCheckbox());
    }

    private void setTextBasedOnCountryforCheckBox() {
        if (this.newsletterCheckBox != null) {
            String countryCode = getSession().getUser().getLocationPreferences().getCountryCode();
            char c = 65535;
            int hashCode = countryCode.hashCode();
            if (hashCode != 2142) {
                if (hashCode == 2407 && countryCode.equals(Country.KOREA_SOUTH_SHORT)) {
                    c = 0;
                }
            } else if (countryCode.equals(Country.CANADA_SHORT)) {
                c = 1;
            }
            switch (c) {
                case 0:
                    this.newsletterCheckBox.setText(R.string.marketing_checkbox_text_korea);
                    return;
                case 1:
                    this.newsletterCheckBox.setText(R.string.marketing_checkbox_text_canada);
                    ViewUtils.setVisible(true, this.newsLetterCheckBoxSubtext);
                    SignUpUtil.setupCanadaDisclaimerText(this.newsLetterCheckBoxSubtext, getNavigationHelper(), R.string.marketing_checkbox_subtext_canada);
                    return;
                default:
                    this.newsletterCheckBox.setText(R.string.marketing_checkbox_text);
                    return;
            }
        }
    }

    private boolean shouldCheckMarketingCheckbox() {
        UacfConsentStatus uacfConsentStatus2 = this.uacfConsentStatus;
        return uacfConsentStatus2 != null && uacfConsentStatus2.getConsentStandard().equals(Country.UNITED_STATES_SHORT);
    }

    private void fetchConsentsStatus() {
        FragmentActivity activity = getActivity();
        if (activity instanceof SignUpActivity) {
            SignUpActivity signUpActivity = (SignUpActivity) activity;
            this.consentStatusDisposable = signUpActivity.getConsentStatusSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
                public final void accept(Object obj) {
                    SignUpCongratsFragment.lambda$fetchConsentsStatus$1(SignUpCongratsFragment.this, (UacfConsentStatus) obj);
                }
            }, new Consumer(signUpActivity) {
                private final /* synthetic */ SignUpActivity f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(Object obj) {
                    SignUpCongratsFragment.lambda$fetchConsentsStatus$3(SignUpCongratsFragment.this, this.f$1, (Throwable) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$fetchConsentsStatus$1(SignUpCongratsFragment signUpCongratsFragment, UacfConsentStatus uacfConsentStatus2) throws Exception {
        signUpCongratsFragment.uacfConsentStatus = uacfConsentStatus2;
        signUpCongratsFragment.setupMarketingCheckBox();
        signUpCongratsFragment.reportRequiredConsentsToAnalytics(uacfConsentStatus2);
        signUpCongratsFragment.setBusy(false);
    }

    public static /* synthetic */ void lambda$fetchConsentsStatus$3(SignUpCongratsFragment signUpCongratsFragment, SignUpActivity signUpActivity, Throwable th) throws Exception {
        signUpCongratsFragment.setBusy(false);
        signUpCongratsFragment.showErrorDialog(signUpCongratsFragment.getString(R.string.error_during_registration), new DialogPositiveListener(signUpActivity) {
            private final /* synthetic */ SignUpActivity f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(Object obj) {
                SignUpCongratsFragment.lambda$null$2(SignUpCongratsFragment.this, this.f$1, obj);
            }
        });
    }

    public static /* synthetic */ void lambda$null$2(SignUpCongratsFragment signUpCongratsFragment, SignUpActivity signUpActivity, Object obj) {
        signUpCongratsFragment.setBusy(true);
        signUpActivity.clearDisposable();
        signUpActivity.fetchConsentStatus(signUpCongratsFragment.getSession().getUser().getLocationPreferences().getCountryCode());
    }

    public void onDestroyView() {
        super.onDestroyView();
        Disposable disposable = this.consentStatusDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.consentStatusDisposable.dispose();
        }
    }

    private void reportRequiredConsentsToAnalytics(UacfConsentStatus uacfConsentStatus2) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (UacfConsent uacfConsent : uacfConsentStatus2.getConsents()) {
            if (uacfConsent.isRequired()) {
                i++;
                arrayList.add(uacfConsent.getId());
            }
        }
        if (!Strings.isEmpty(this.onBoardingType)) {
            getAnalyticsService().reportRequiredConsents(this.onBoardingType, i, (String[]) arrayList.toArray(new String[0]));
        }
    }

    private void reportPreGDPRAnalytics() {
        String str;
        String[] strArr = new String[4];
        strArr[0] = PreGDPROnBoarding.MARKETING_EMAIL;
        strArr[1] = getSession().getUser().isMarketingOptinEnabled() ? "on" : "off";
        strArr[2] = "reminders";
        strArr[3] = this.remindersCheckBox.isChecked() ? "on" : "off";
        Map createMap = MapUtil.createMap(strArr);
        String countryName = getSession().getUser().getProfile().getCountryName();
        String str2 = PreGDPROnBoarding.COUNTRY;
        if (countryName != null) {
            str = getCountryService().getShortNameFromLongName(countryName);
        } else {
            str = getCountryService().getCurrentCountry().getShortName();
        }
        createMap.put(str2, str.toUpperCase());
        if (!Strings.isEmpty(this.onBoardingType)) {
            createMap.put("type", this.onBoardingType);
        }
        getAnalyticsService().reportEvent(PreGDPROnBoarding.NAME, createMap);
    }
}
