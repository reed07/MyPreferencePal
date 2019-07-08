package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncOp;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class SyncModule_ProvidesAnalyticsSyncOpFactory implements Factory<AnalyticsSyncOp> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<String> clientIdProvider;
    private final Provider<UUID> deviceIdProvider;
    private final SyncModule module;
    private final Provider<MfpAnalyticsTaskQueue> taskQueueProvider;

    public SyncModule_ProvidesAnalyticsSyncOpFactory(SyncModule syncModule, Provider<MfpAnalyticsTaskQueue> provider, Provider<MfpV2Api> provider2, Provider<UUID> provider3, Provider<String> provider4, Provider<AuthTokenProvider> provider5) {
        this.module = syncModule;
        this.taskQueueProvider = provider;
        this.apiProvider = provider2;
        this.deviceIdProvider = provider3;
        this.clientIdProvider = provider4;
        this.authTokensProvider = provider5;
    }

    public AnalyticsSyncOp get() {
        return provideInstance(this.module, this.taskQueueProvider, this.apiProvider, this.deviceIdProvider, this.clientIdProvider, this.authTokensProvider);
    }

    public static AnalyticsSyncOp provideInstance(SyncModule syncModule, Provider<MfpAnalyticsTaskQueue> provider, Provider<MfpV2Api> provider2, Provider<UUID> provider3, Provider<String> provider4, Provider<AuthTokenProvider> provider5) {
        return proxyProvidesAnalyticsSyncOp(syncModule, DoubleCheck.lazy(provider), provider2, (UUID) provider3.get(), (String) provider4.get(), DoubleCheck.lazy(provider5));
    }

    public static SyncModule_ProvidesAnalyticsSyncOpFactory create(SyncModule syncModule, Provider<MfpAnalyticsTaskQueue> provider, Provider<MfpV2Api> provider2, Provider<UUID> provider3, Provider<String> provider4, Provider<AuthTokenProvider> provider5) {
        SyncModule_ProvidesAnalyticsSyncOpFactory syncModule_ProvidesAnalyticsSyncOpFactory = new SyncModule_ProvidesAnalyticsSyncOpFactory(syncModule, provider, provider2, provider3, provider4, provider5);
        return syncModule_ProvidesAnalyticsSyncOpFactory;
    }

    public static AnalyticsSyncOp proxyProvidesAnalyticsSyncOp(SyncModule syncModule, Lazy<MfpAnalyticsTaskQueue> lazy, Provider<MfpV2Api> provider, UUID uuid, String str, Lazy<AuthTokenProvider> lazy2) {
        return (AnalyticsSyncOp) Preconditions.checkNotNull(syncModule.providesAnalyticsSyncOp(lazy, provider, uuid, str, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
