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

public final class SignUpWeeklyWeightGoalFragment_MembersInjector implements MembersInjector<SignUpWeeklyWeightGoalFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<SignUpModel> modelProvider;
    private final Provider<SignUpService> signUpServiceProvider;

    public SignUpWeeklyWeightGoalFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.signUpServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.modelProvider = provider5;
    }

    public static MembersInjector<SignUpWeeklyWeightGoalFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SignUpService> provider3, Provider<ConfigService> provider4, Provider<SignUpModel> provider5) {
        SignUpWeeklyWeightGoalFragment_MembersInjector signUpWeeklyWeightGoalFragment_MembersInjector = new SignUpWeeklyWeightGoalFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return signUpWeeklyWeightGoalFragment_MembersInjector;
    }

    public void injectMembers(SignUpWeeklyWeightGoalFragment signUpWeeklyWeightGoalFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(signUpWeeklyWeightGoalFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(signUpWeeklyWeightGoalFragment, (Glide) this.glideProvider.get());
        SignUpFragmentBase_MembersInjector.injectSignUpService(signUpWeeklyWeightGoalFragment, (SignUpService) this.signUpServiceProvider.get());
        SignUpFragmentBase_MembersInjector.injectConfigService(signUpWeeklyWeightGoalFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectModel(signUpWeeklyWeightGoalFragment, (SignUpModel) this.modelProvider.get());
    }

    public static void injectModel(SignUpWeeklyWeightGoalFragment signUpWeeklyWeightGoalFragment, SignUpModel signUpModel) {
        signUpWeeklyWeightGoalFragment.model = signUpModel;
    }
}
