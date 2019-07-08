package com.myfitnesspal.feature.registration.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class LoginPleaseWaitFragment_MembersInjector implements MembersInjector<LoginPleaseWaitFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public LoginPleaseWaitFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static MembersInjector<LoginPleaseWaitFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new LoginPleaseWaitFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(LoginPleaseWaitFragment loginPleaseWaitFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(loginPleaseWaitFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(loginPleaseWaitFragment, (Glide) this.glideProvider.get());
    }
}
