package com.myfitnesspal.feature.registration.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SignUpUsernamePasswordEmailFragment_MembersInjector implements MembersInjector<SignUpUsernamePasswordEmailFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<ConsentsAnalyticsHelper> consentsAnalyticsHelperProvider;
    private final Provider<ConsentsService> consentsServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final Provider<SignUpModel> signUpModelProvider;
    private final Provider<SignUpService> signUpServiceProvider;
    private final Provider<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelperProvider;

    public SignUpUsernamePasswordEmailFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5, Provider<LoginModel> provider6, Provider<CountryService> provider7, Provider<ConsentsService> provider8, Provider<ConsentsAnalyticsHelper> provider9, Provider<UpdatedTermsAnalyticsHelper> provider10) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.signUpServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.signUpModelProvider = provider5;
        this.loginModelProvider = provider6;
        this.countryServiceProvider = provider7;
        this.consentsServiceProvider = provider8;
        this.consentsAnalyticsHelperProvider = provider9;
        this.updatedTermsAnalyticsHelperProvider = provider10;
    }

    public static MembersInjector<SignUpUsernamePasswordEmailFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5, Provider<LoginModel> provider6, Provider<CountryService> provider7, Provider<ConsentsService> provider8, Provider<ConsentsAnalyticsHelper> provider9, Provider<UpdatedTermsAnalyticsHelper> provider10) {
        SignUpUsernamePasswordEmailFragment_MembersInjector signUpUsernamePasswordEmailFragment_MembersInjector = new SignUpUsernamePasswordEmailFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
        return signUpUsernamePasswordEmailFragment_MembersInjector;
    }

    public void injectMembers(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(signUpUsernamePasswordEmailFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(signUpUsernamePasswordEmailFragment, (Glide) this.glideProvider.get());
        SignUpFragmentBase_MembersInjector.injectSignUpService(signUpUsernamePasswordEmailFragment, (SignUpService) this.signUpServiceProvider.get());
        SignUpFragmentBase_MembersInjector.injectConfigService(signUpUsernamePasswordEmailFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectSignUpModel(signUpUsernamePasswordEmailFragment, (SignUpModel) this.signUpModelProvider.get());
        injectLoginModel(signUpUsernamePasswordEmailFragment, (LoginModel) this.loginModelProvider.get());
        injectCountryService(signUpUsernamePasswordEmailFragment, DoubleCheck.lazy(this.countryServiceProvider));
        injectConsentsService(signUpUsernamePasswordEmailFragment, DoubleCheck.lazy(this.consentsServiceProvider));
        injectConsentsAnalyticsHelper(signUpUsernamePasswordEmailFragment, DoubleCheck.lazy(this.consentsAnalyticsHelperProvider));
        injectUpdatedTermsAnalyticsHelper(signUpUsernamePasswordEmailFragment, DoubleCheck.lazy(this.updatedTermsAnalyticsHelperProvider));
    }

    public static void injectSignUpModel(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, SignUpModel signUpModel) {
        signUpUsernamePasswordEmailFragment.signUpModel = signUpModel;
    }

    public static void injectLoginModel(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, LoginModel loginModel) {
        signUpUsernamePasswordEmailFragment.loginModel = loginModel;
    }

    public static void injectCountryService(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, Lazy<CountryService> lazy) {
        signUpUsernamePasswordEmailFragment.countryService = lazy;
    }

    public static void injectConsentsService(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, Lazy<ConsentsService> lazy) {
        signUpUsernamePasswordEmailFragment.consentsService = lazy;
    }

    public static void injectConsentsAnalyticsHelper(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, Lazy<ConsentsAnalyticsHelper> lazy) {
        signUpUsernamePasswordEmailFragment.consentsAnalyticsHelper = lazy;
    }

    public static void injectUpdatedTermsAnalyticsHelper(SignUpUsernamePasswordEmailFragment signUpUsernamePasswordEmailFragment, Lazy<UpdatedTermsAnalyticsHelper> lazy) {
        signUpUsernamePasswordEmailFragment.updatedTermsAnalyticsHelper = lazy;
    }
}
