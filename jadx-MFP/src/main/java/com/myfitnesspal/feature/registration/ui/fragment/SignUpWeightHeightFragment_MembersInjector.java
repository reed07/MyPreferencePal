package com.myfitnesspal.feature.registration.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SignUpWeightHeightFragment_MembersInjector implements MembersInjector<SignUpWeightHeightFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<SignUpModel> modelProvider;
    private final Provider<SignUpService> signUpServiceProvider;

    public SignUpWeightHeightFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.signUpServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.modelProvider = provider5;
    }

    public static MembersInjector<SignUpWeightHeightFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5) {
        SignUpWeightHeightFragment_MembersInjector signUpWeightHeightFragment_MembersInjector = new SignUpWeightHeightFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return signUpWeightHeightFragment_MembersInjector;
    }

    public void injectMembers(SignUpWeightHeightFragment signUpWeightHeightFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(signUpWeightHeightFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(signUpWeightHeightFragment, (Glide) this.glideProvider.get());
        SignUpFragmentBase_MembersInjector.injectSignUpService(signUpWeightHeightFragment, (SignUpService) this.signUpServiceProvider.get());
        SignUpFragmentBase_MembersInjector.injectConfigService(signUpWeightHeightFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectModel(signUpWeightHeightFragment, (SignUpModel) this.modelProvider.get());
    }

    public static void injectModel(SignUpWeightHeightFragment signUpWeightHeightFragment, SignUpModel signUpModel) {
        signUpWeightHeightFragment.model = signUpModel;
    }
}
