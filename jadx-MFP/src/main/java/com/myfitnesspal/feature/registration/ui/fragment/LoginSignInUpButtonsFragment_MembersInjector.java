package com.myfitnesspal.feature.registration.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class LoginSignInUpButtonsFragment_MembersInjector implements MembersInjector<LoginSignInUpButtonsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public LoginSignInUpButtonsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static MembersInjector<LoginSignInUpButtonsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new LoginSignInUpButtonsFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(LoginSignInUpButtonsFragment loginSignInUpButtonsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(loginSignInUpButtonsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(loginSignInUpButtonsFragment, (Glide) this.glideProvider.get());
    }
}
