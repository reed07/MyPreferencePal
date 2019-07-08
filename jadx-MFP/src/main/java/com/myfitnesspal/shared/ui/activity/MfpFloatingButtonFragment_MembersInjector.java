package com.myfitnesspal.shared.ui.activity;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MfpFloatingButtonFragment_MembersInjector implements MembersInjector<MfpFloatingButtonFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public MfpFloatingButtonFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static MembersInjector<MfpFloatingButtonFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new MfpFloatingButtonFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(MfpFloatingButtonFragment mfpFloatingButtonFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(mfpFloatingButtonFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(mfpFloatingButtonFragment, (Glide) this.glideProvider.get());
    }
}
