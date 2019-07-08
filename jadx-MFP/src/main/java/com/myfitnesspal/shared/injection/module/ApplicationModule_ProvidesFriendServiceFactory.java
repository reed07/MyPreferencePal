package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v1.MfpActionApi;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.caching.Cache;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFriendServiceFactory implements Factory<FriendService> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Cache<List<UserSummaryObject>>> cacheProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MfpActionApi> mfpActionApiProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesFriendServiceFactory(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<BackgroundJobHelper> provider2, Provider<Cache<List<UserSummaryObject>>> provider3, Provider<LocalSettingsService> provider4, Provider<Session> provider5) {
        this.module = applicationModule;
        this.mfpActionApiProvider = provider;
        this.backgroundServiceHelperProvider = provider2;
        this.cacheProvider = provider3;
        this.localSettingsServiceProvider = provider4;
        this.sessionProvider = provider5;
    }

    public FriendService get() {
        return provideInstance(this.module, this.mfpActionApiProvider, this.backgroundServiceHelperProvider, this.cacheProvider, this.localSettingsServiceProvider, this.sessionProvider);
    }

    public static FriendService provideInstance(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<BackgroundJobHelper> provider2, Provider<Cache<List<UserSummaryObject>>> provider3, Provider<LocalSettingsService> provider4, Provider<Session> provider5) {
        return proxyProvidesFriendService(applicationModule, provider, DoubleCheck.lazy(provider2), (Cache) provider3.get(), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static ApplicationModule_ProvidesFriendServiceFactory create(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Provider<BackgroundJobHelper> provider2, Provider<Cache<List<UserSummaryObject>>> provider3, Provider<LocalSettingsService> provider4, Provider<Session> provider5) {
        ApplicationModule_ProvidesFriendServiceFactory applicationModule_ProvidesFriendServiceFactory = new ApplicationModule_ProvidesFriendServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5);
        return applicationModule_ProvidesFriendServiceFactory;
    }

    public static FriendService proxyProvidesFriendService(ApplicationModule applicationModule, Provider<MfpActionApi> provider, Lazy<BackgroundJobHelper> lazy, Cache<List<UserSummaryObject>> cache, Lazy<LocalSettingsService> lazy2, Lazy<Session> lazy3) {
        return (FriendService) Preconditions.checkNotNull(applicationModule.providesFriendService(provider, lazy, cache, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
