package com.myfitnesspal.shared.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MfpFragment_MembersInjector implements MembersInjector<MfpFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public MfpFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static MembersInjector<MfpFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new MfpFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(MfpFragment mfpFragment) {
        injectBackgroundServiceHelper(mfpFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        injectGlide(mfpFragment, (Glide) this.glideProvider.get());
    }

    public static void injectBackgroundServiceHelper(MfpFragment mfpFragment, Lazy<BackgroundJobHelper> lazy) {
        mfpFragment.backgroundServiceHelper = lazy;
    }

    public static void injectGlide(MfpFragment mfpFragment, Glide glide) {
        mfpFragment.glide = glide;
    }
}
