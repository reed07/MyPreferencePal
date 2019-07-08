package com.myfitnesspal.feature.appgallery.ui;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class GoogleFitPermissionsFragment_MembersInjector implements MembersInjector<GoogleFitPermissionsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public GoogleFitPermissionsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
    }

    public static MembersInjector<GoogleFitPermissionsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2) {
        return new GoogleFitPermissionsFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(GoogleFitPermissionsFragment googleFitPermissionsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(googleFitPermissionsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(googleFitPermissionsFragment, (Glide) this.glideProvider.get());
    }
}
