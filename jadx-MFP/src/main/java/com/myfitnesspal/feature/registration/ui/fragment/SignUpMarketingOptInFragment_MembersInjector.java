package com.myfitnesspal.feature.registration.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SignUpMarketingOptInFragment_MembersInjector implements MembersInjector<SignUpMarketingOptInFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final Provider<SignUpModel> signUpModelProvider;
    private final Provider<SignUpService> signUpServiceProvider;

    public SignUpMarketingOptInFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5, Provider<LoginModel> provider6) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.signUpServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.signUpModelProvider = provider5;
        this.loginModelProvider = provider6;
    }

    public static MembersInjector<SignUpMarketingOptInFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5, Provider<LoginModel> provider6) {
        SignUpMarketingOptInFragment_MembersInjector signUpMarketingOptInFragment_MembersInjector = new SignUpMarketingOptInFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return signUpMarketingOptInFragment_MembersInjector;
    }

    public void injectMembers(SignUpMarketingOptInFragment signUpMarketingOptInFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(signUpMarketingOptInFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(signUpMarketingOptInFragment, (Glide) this.glideProvider.get());
        SignUpFragmentBase_MembersInjector.injectSignUpService(signUpMarketingOptInFragment, (SignUpService) this.signUpServiceProvider.get());
        SignUpFragmentBase_MembersInjector.injectConfigService(signUpMarketingOptInFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectSignUpModel(signUpMarketingOptInFragment, (SignUpModel) this.signUpModelProvider.get());
        injectLoginModel(signUpMarketingOptInFragment, (LoginModel) this.loginModelProvider.get());
        injectSignUpService(signUpMarketingOptInFragment, DoubleCheck.lazy(this.signUpServiceProvider));
    }

    public static void injectSignUpModel(SignUpMarketingOptInFragment signUpMarketingOptInFragment, SignUpModel signUpModel) {
        signUpMarketingOptInFragment.signUpModel = signUpModel;
    }

    public static void injectLoginModel(SignUpMarketingOptInFragment signUpMarketingOptInFragment, LoginModel loginModel) {
        signUpMarketingOptInFragment.loginModel = loginModel;
    }

    public static void injectSignUpService(SignUpMarketingOptInFragment signUpMarketingOptInFragment, Lazy<SignUpService> lazy) {
        signUpMarketingOptInFragment.signUpService = lazy;
    }
}
