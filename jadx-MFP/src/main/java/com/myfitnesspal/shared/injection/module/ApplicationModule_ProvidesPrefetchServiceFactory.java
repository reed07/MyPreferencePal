package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.registration.service.PrefetchService;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesPrefetchServiceFactory implements Factory<PrefetchService> {
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<FriendService> friendServiceProvider;
    private final Provider<GeoLocationService> geoLocationServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final ApplicationModule module;
    private final Provider<NewsFeedService> newsFeedServiceProvider;
    private final Provider<ProductService> productServiceProvider;
    private final Provider<SubscriptionService> subscriptionServiceProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<SyncUtil> syncUtilProvider;
    private final Provider<UserApplicationSettingsService> userAppSettingsProvider;

    public ApplicationModule_ProvidesPrefetchServiceFactory(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<NewsFeedService> provider2, Provider<FriendService> provider3, Provider<ProductService> provider4, Provider<GeoLocationService> provider5, Provider<SyncUtil> provider6, Provider<SyncService> provider7, Provider<SubscriptionService> provider8, Provider<AppGalleryService> provider9, Provider<UserApplicationSettingsService> provider10) {
        this.module = applicationModule;
        this.localSettingsServiceProvider = provider;
        this.newsFeedServiceProvider = provider2;
        this.friendServiceProvider = provider3;
        this.productServiceProvider = provider4;
        this.geoLocationServiceProvider = provider5;
        this.syncUtilProvider = provider6;
        this.syncServiceProvider = provider7;
        this.subscriptionServiceProvider = provider8;
        this.appGalleryServiceProvider = provider9;
        this.userAppSettingsProvider = provider10;
    }

    public PrefetchService get() {
        return provideInstance(this.module, this.localSettingsServiceProvider, this.newsFeedServiceProvider, this.friendServiceProvider, this.productServiceProvider, this.geoLocationServiceProvider, this.syncUtilProvider, this.syncServiceProvider, this.subscriptionServiceProvider, this.appGalleryServiceProvider, this.userAppSettingsProvider);
    }

    public static PrefetchService provideInstance(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<NewsFeedService> provider2, Provider<FriendService> provider3, Provider<ProductService> provider4, Provider<GeoLocationService> provider5, Provider<SyncUtil> provider6, Provider<SyncService> provider7, Provider<SubscriptionService> provider8, Provider<AppGalleryService> provider9, Provider<UserApplicationSettingsService> provider10) {
        return proxyProvidesPrefetchService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10));
    }

    public static ApplicationModule_ProvidesPrefetchServiceFactory create(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<NewsFeedService> provider2, Provider<FriendService> provider3, Provider<ProductService> provider4, Provider<GeoLocationService> provider5, Provider<SyncUtil> provider6, Provider<SyncService> provider7, Provider<SubscriptionService> provider8, Provider<AppGalleryService> provider9, Provider<UserApplicationSettingsService> provider10) {
        ApplicationModule_ProvidesPrefetchServiceFactory applicationModule_ProvidesPrefetchServiceFactory = new ApplicationModule_ProvidesPrefetchServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
        return applicationModule_ProvidesPrefetchServiceFactory;
    }

    public static PrefetchService proxyProvidesPrefetchService(ApplicationModule applicationModule, Lazy<LocalSettingsService> lazy, Lazy<NewsFeedService> lazy2, Lazy<FriendService> lazy3, Lazy<ProductService> lazy4, Lazy<GeoLocationService> lazy5, Lazy<SyncUtil> lazy6, Lazy<SyncService> lazy7, Lazy<SubscriptionService> lazy8, Lazy<AppGalleryService> lazy9, Lazy<UserApplicationSettingsService> lazy10) {
        return (PrefetchService) Preconditions.checkNotNull(applicationModule.providesPrefetchService(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10), "Cannot return null from a non-@Nullable @Provides method");
    }
}
