package com.myfitnesspal.feature.registration.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class LoginUserPassFragment_MembersInjector implements MembersInjector<LoginUserPassFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LoginModel> loginModelProvider;

    public LoginUserPassFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LoginModel> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.loginModelProvider = provider3;
    }

    public static MembersInjector<LoginUserPassFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LoginModel> provider3) {
        return new LoginUserPassFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(LoginUserPassFragment loginUserPassFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(loginUserPassFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(loginUserPassFragment, (Glide) this.glideProvider.get());
        injectLoginModel(loginUserPassFragment, DoubleCheck.lazy(this.loginModelProvider));
    }

    public static void injectLoginModel(LoginUserPassFragment loginUserPassFragment, Lazy<LoginModel> lazy) {
        loginUserPassFragment.loginModel = lazy;
    }
}
