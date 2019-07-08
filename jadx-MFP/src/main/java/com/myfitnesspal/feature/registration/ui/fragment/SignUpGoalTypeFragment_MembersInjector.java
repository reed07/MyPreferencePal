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

public final class SignUpGoalTypeFragment_MembersInjector implements MembersInjector<SignUpGoalTypeFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<SignUpModel> modelProvider;
    private final Provider<SignUpService> signUpServiceProvider;

    public SignUpGoalTypeFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.signUpServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.modelProvider = provider5;
    }

    public static MembersInjector<SignUpGoalTypeFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5) {
        SignUpGoalTypeFragment_MembersInjector signUpGoalTypeFragment_MembersInjector = new SignUpGoalTypeFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return signUpGoalTypeFragment_MembersInjector;
    }

    public void injectMembers(SignUpGoalTypeFragment signUpGoalTypeFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(signUpGoalTypeFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(signUpGoalTypeFragment, (Glide) this.glideProvider.get());
        SignUpFragmentBase_MembersInjector.injectSignUpService(signUpGoalTypeFragment, (SignUpService) this.signUpServiceProvider.get());
        SignUpFragmentBase_MembersInjector.injectConfigService(signUpGoalTypeFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectModel(signUpGoalTypeFragment, (SignUpModel) this.modelProvider.get());
    }

    public static void injectModel(SignUpGoalTypeFragment signUpGoalTypeFragment, SignUpModel signUpModel) {
        signUpGoalTypeFragment.model = signUpModel;
    }
}
