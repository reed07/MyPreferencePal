package com.myfitnesspal.feature.registration.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SignUpFragmentBase_MembersInjector implements MembersInjector<SignUpFragmentBase> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<SignUpService> signUpServiceProvider;

    public SignUpFragmentBase_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.signUpServiceProvider = provider3;
        this.configServiceProvider = provider4;
    }

    public static MembersInjector<SignUpFragmentBase> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4) {
        return new SignUpFragmentBase_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(SignUpFragmentBase signUpFragmentBase) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(signUpFragmentBase, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(signUpFragmentBase, (Glide) this.glideProvider.get());
        injectSignUpService(signUpFragmentBase, (SignUpService) this.signUpServiceProvider.get());
        injectConfigService(signUpFragmentBase, DoubleCheck.lazy(this.configServiceProvider));
    }

    public static void injectSignUpService(SignUpFragmentBase signUpFragmentBase, SignUpService signUpService) {
        signUpFragmentBase.signUpService = signUpService;
    }

    public static void injectConfigService(SignUpFragmentBase signUpFragmentBase, Lazy<ConfigService> lazy) {
        signUpFragmentBase.configService = lazy;
    }
}
