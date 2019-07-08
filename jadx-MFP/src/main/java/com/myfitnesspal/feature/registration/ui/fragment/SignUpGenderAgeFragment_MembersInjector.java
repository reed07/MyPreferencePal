package com.myfitnesspal.feature.registration.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SignUpGenderAgeFragment_MembersInjector implements MembersInjector<SignUpGenderAgeFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<SignUpModel> modelProvider;
    private final Provider<SignUpService> signUpServiceProvider;

    public SignUpGenderAgeFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.signUpServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.modelProvider = provider5;
    }

    public static MembersInjector<SignUpGenderAgeFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5) {
        SignUpGenderAgeFragment_MembersInjector signUpGenderAgeFragment_MembersInjector = new SignUpGenderAgeFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return signUpGenderAgeFragment_MembersInjector;
    }

    public void injectMembers(SignUpGenderAgeFragment signUpGenderAgeFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(signUpGenderAgeFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(signUpGenderAgeFragment, (Glide) this.glideProvider.get());
        SignUpFragmentBase_MembersInjector.injectSignUpService(signUpGenderAgeFragment, (SignUpService) this.signUpServiceProvider.get());
        SignUpFragmentBase_MembersInjector.injectConfigService(signUpGenderAgeFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectModel(signUpGenderAgeFragment, (SignUpModel) this.modelProvider.get());
        injectSignUpService(signUpGenderAgeFragment, DoubleCheck.lazy(this.signUpServiceProvider));
    }

    public static void injectModel(SignUpGenderAgeFragment signUpGenderAgeFragment, SignUpModel signUpModel) {
        signUpGenderAgeFragment.model = signUpModel;
    }

    public static void injectSignUpService(SignUpGenderAgeFragment signUpGenderAgeFragment, Lazy<SignUpService> lazy) {
        signUpGenderAgeFragment.signUpService = lazy;
    }
}
