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

public final class AppDetailFragment_MembersInjector implements MembersInjector<AppDetailFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;

    public AppDetailFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ActionTrackingService> provider3, Provider<AppGalleryService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.actionTrackingServiceProvider = provider3;
        this.appGalleryServiceProvider = provider4;
    }

    public static MembersInjector<AppDetailFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ActionTrackingService> provider3, Provider<AppGalleryService> provider4) {
        return new AppDetailFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(AppDetailFragment appDetailFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(appDetailFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(appDetailFragment, (Glide) this.glideProvider.get());
        injectActionTrackingService(appDetailFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectAppGalleryService(appDetailFragment, DoubleCheck.lazy(this.appGalleryServiceProvider));
    }

    public static void injectActionTrackingService(AppDetailFragment appDetailFragment, Lazy<ActionTrackingService> lazy) {
        appDetailFragment.actionTrackingService = lazy;
    }

    public static void injectAppGalleryService(AppDetailFragment appDetailFragment, Lazy<AppGalleryService> lazy) {
        appDetailFragment.appGalleryService = lazy;
    }
}
