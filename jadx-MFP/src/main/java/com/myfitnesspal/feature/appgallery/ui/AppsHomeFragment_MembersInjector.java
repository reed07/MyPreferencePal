package com.myfitnesspal.feature.appgallery.ui;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AppsHomeFragment_MembersInjector implements MembersInjector<AppsHomeFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public AppsHomeFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ActionTrackingService> provider3, Provider<AppGalleryService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.actionTrackingServiceProvider = provider3;
        this.appGalleryServiceProvider = provider4;
    }

    public static MembersInjector<AppsHomeFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ActionTrackingService> provider3, Provider<AppGalleryService> provider4) {
        return new AppsHomeFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(AppsHomeFragment appsHomeFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(appsHomeFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(appsHomeFragment, (Glide) this.glideProvider.get());
        injectActionTrackingService(appsHomeFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectAppGalleryService(appsHomeFragment, DoubleCheck.lazy(this.appGalleryServiceProvider));
    }

    public static void injectActionTrackingService(AppsHomeFragment appsHomeFragment, Lazy<ActionTrackingService> lazy) {
        appsHomeFragment.actionTrackingService = lazy;
    }

    public static void injectAppGalleryService(AppsHomeFragment appsHomeFragment, Lazy<AppGalleryService> lazy) {
        appsHomeFragment.appGalleryService = lazy;
    }
}
