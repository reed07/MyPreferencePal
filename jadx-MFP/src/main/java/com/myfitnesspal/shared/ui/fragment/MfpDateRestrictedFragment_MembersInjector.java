package com.myfitnesspal.shared.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MfpDateRestrictedFragment_MembersInjector implements MembersInjector<MfpDateRestrictedFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public MfpDateRestrictedFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static MembersInjector<MfpDateRestrictedFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new MfpDateRestrictedFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(MfpDateRestrictedFragment mfpDateRestrictedFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(mfpDateRestrictedFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(mfpDateRestrictedFragment, (Glide) this.glideProvider.get());
    }
}
