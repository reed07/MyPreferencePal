package com.myfitnesspal.feature.explore.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class PrototypeExploreFragment_MembersInjector implements MembersInjector<PrototypeExploreFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public PrototypeExploreFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static MembersInjector<PrototypeExploreFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new PrototypeExploreFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(PrototypeExploreFragment prototypeExploreFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(prototypeExploreFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(prototypeExploreFragment, (Glide) this.glideProvider.get());
    }
}
