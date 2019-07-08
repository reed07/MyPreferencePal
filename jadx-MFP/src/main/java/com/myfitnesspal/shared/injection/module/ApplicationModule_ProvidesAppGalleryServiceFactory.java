package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.caching.Cache;
import com.uacf.core.util.DeviceInfo;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesAppGalleryServiceFactory implements Factory<AppGalleryService> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final Provider<Cache<ApiResponse<MfpPlatformApp>>> mfpPlatformAppsCacheProvider;
    private final Provider<MfpV2Api> mfpPlatformAppsProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesAppGalleryServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<Cache<ApiResponse<MfpPlatformApp>>> provider2, Provider<DeviceInfo> provider3, Provider<Session> provider4, Provider<ConfigService> provider5) {
        this.module = applicationModule;
        this.mfpPlatformAppsProvider = provider;
        this.mfpPlatformAppsCacheProvider = provider2;
        this.deviceInfoProvider = provider3;
        this.sessionProvider = provider4;
        this.configServiceProvider = provider5;
    }

    public AppGalleryService get() {
        return provideInstance(this.module, this.mfpPlatformAppsProvider, this.mfpPlatformAppsCacheProvider, this.deviceInfoProvider, this.sessionProvider, this.configServiceProvider);
    }

    public static AppGalleryService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<Cache<ApiResponse<MfpPlatformApp>>> provider2, Provider<DeviceInfo> provider3, Provider<Session> provider4, Provider<ConfigService> provider5) {
        return proxyProvidesAppGalleryService(applicationModule, provider, (Cache) provider2.get(), (DeviceInfo) provider3.get(), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static ApplicationModule_ProvidesAppGalleryServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<Cache<ApiResponse<MfpPlatformApp>>> provider2, Provider<DeviceInfo> provider3, Provider<Session> provider4, Provider<ConfigService> provider5) {
        ApplicationModule_ProvidesAppGalleryServiceFactory applicationModule_ProvidesAppGalleryServiceFactory = new ApplicationModule_ProvidesAppGalleryServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5);
        return applicationModule_ProvidesAppGalleryServiceFactory;
    }

    public static AppGalleryService proxyProvidesAppGalleryService(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Cache<ApiResponse<MfpPlatformApp>> cache, DeviceInfo deviceInfo, Lazy<Session> lazy, Lazy<ConfigService> lazy2) {
        return (AppGalleryService) Preconditions.checkNotNull(applicationModule.providesAppGalleryService(provider, cache, deviceInfo, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
