package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryListContainer;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.caching.Cache;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNewsFeedServiceFactory implements Factory<NewsFeedService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<Cache<MfpNewsFeedActivityEntryListContainer>> feedCacheProvider;
    private final Provider<MfpInformationApi> mfpInformationApiProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesNewsFeedServiceFactory(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2, Provider<Session> provider3, Provider<Cache<MfpNewsFeedActivityEntryListContainer>> provider4) {
        this.module = applicationModule;
        this.mfpInformationApiProvider = provider;
        this.apiProvider = provider2;
        this.sessionProvider = provider3;
        this.feedCacheProvider = provider4;
    }

    public NewsFeedService get() {
        return provideInstance(this.module, this.mfpInformationApiProvider, this.apiProvider, this.sessionProvider, this.feedCacheProvider);
    }

    public static NewsFeedService provideInstance(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2, Provider<Session> provider3, Provider<Cache<MfpNewsFeedActivityEntryListContainer>> provider4) {
        return proxyProvidesNewsFeedService(applicationModule, provider, provider2, DoubleCheck.lazy(provider3), (Cache) provider4.get());
    }

    public static ApplicationModule_ProvidesNewsFeedServiceFactory create(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2, Provider<Session> provider3, Provider<Cache<MfpNewsFeedActivityEntryListContainer>> provider4) {
        ApplicationModule_ProvidesNewsFeedServiceFactory applicationModule_ProvidesNewsFeedServiceFactory = new ApplicationModule_ProvidesNewsFeedServiceFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesNewsFeedServiceFactory;
    }

    public static NewsFeedService proxyProvidesNewsFeedService(ApplicationModule applicationModule, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2, Lazy<Session> lazy, Cache<MfpNewsFeedActivityEntryListContainer> cache) {
        return (NewsFeedService) Preconditions.checkNotNull(applicationModule.providesNewsFeedService(provider, provider2, lazy, cache), "Cannot return null from a non-@Nullable @Provides method");
    }
}
