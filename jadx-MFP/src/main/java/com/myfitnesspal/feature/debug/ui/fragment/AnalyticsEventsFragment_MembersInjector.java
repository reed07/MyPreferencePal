package com.myfitnesspal.feature.debug.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AnalyticsEventsFragment_MembersInjector implements MembersInjector<AnalyticsEventsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public AnalyticsEventsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static MembersInjector<AnalyticsEventsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new AnalyticsEventsFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(AnalyticsEventsFragment analyticsEventsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(analyticsEventsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(analyticsEventsFragment, (Glide) this.glideProvider.get());
    }
}
