package com.myfitnesspal.feature.registration.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SignUpCongratsFragment_MembersInjector implements MembersInjector<SignUpCongratsFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<RemindersService> remindersServiceProvider;
    private final Provider<SignUpService> signUpServiceProvider;

    public SignUpCongratsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<RemindersService> provider5, Provider<ActionTrackingService> provider6, Provider<PremiumService> provider7, Provider<LocalSettingsService> provider8, Provider<NutrientGoalsUtil> provider9, Provider<CountryService> provider10) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.signUpServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.remindersServiceProvider = provider5;
        this.actionTrackingServiceProvider = provider6;
        this.premiumServiceProvider = provider7;
        this.localSettingsServiceProvider = provider8;
        this.nutrientGoalsUtilProvider = provider9;
        this.countryServiceProvider = provider10;
    }

    public static MembersInjector<SignUpCongratsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<RemindersService> provider5, Provider<ActionTrackingService> provider6, Provider<PremiumService> provider7, Provider<LocalSettingsService> provider8, Provider<NutrientGoalsUtil> provider9, Provider<CountryService> provider10) {
        SignUpCongratsFragment_MembersInjector signUpCongratsFragment_MembersInjector = new SignUpCongratsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
        return signUpCongratsFragment_MembersInjector;
    }

    public void injectMembers(SignUpCongratsFragment signUpCongratsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(signUpCongratsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(signUpCongratsFragment, (Glide) this.glideProvider.get());
        SignUpFragmentBase_MembersInjector.injectSignUpService(signUpCongratsFragment, (SignUpService) this.signUpServiceProvider.get());
        SignUpFragmentBase_MembersInjector.injectConfigService(signUpCongratsFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectRemindersService(signUpCongratsFragment, DoubleCheck.lazy(this.remindersServiceProvider));
        injectActionTrackingService(signUpCongratsFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectPremiumService(signUpCongratsFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectLocalSettingsService(signUpCongratsFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectNutrientGoalsUtil(signUpCongratsFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectCountryService(signUpCongratsFragment, DoubleCheck.lazy(this.countryServiceProvider));
        injectConfigService(signUpCongratsFragment, DoubleCheck.lazy(this.configServiceProvider));
    }

    public static void injectRemindersService(SignUpCongratsFragment signUpCongratsFragment, Lazy<RemindersService> lazy) {
        signUpCongratsFragment.remindersService = lazy;
    }

    public static void injectActionTrackingService(SignUpCongratsFragment signUpCongratsFragment, Lazy<ActionTrackingService> lazy) {
        signUpCongratsFragment.actionTrackingService = lazy;
    }

    public static void injectPremiumService(SignUpCongratsFragment signUpCongratsFragment, Lazy<PremiumService> lazy) {
        signUpCongratsFragment.premiumService = lazy;
    }

    public static void injectLocalSettingsService(SignUpCongratsFragment signUpCongratsFragment, Lazy<LocalSettingsService> lazy) {
        signUpCongratsFragment.localSettingsService = lazy;
    }

    public static void injectNutrientGoalsUtil(SignUpCongratsFragment signUpCongratsFragment, Lazy<NutrientGoalsUtil> lazy) {
        signUpCongratsFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectCountryService(SignUpCongratsFragment signUpCongratsFragment, Lazy<CountryService> lazy) {
        signUpCongratsFragment.countryService = lazy;
    }

    public static void injectConfigService(SignUpCongratsFragment signUpCongratsFragment, Lazy<ConfigService> lazy) {
        signUpCongratsFragment.configService = lazy;
    }
}
