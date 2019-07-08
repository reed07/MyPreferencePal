package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.service.ops.ExternalSyncOp;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.imagesync.ImageSyncMode;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.MfpSyncEngineDelegate;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.ops.AuthTokenOp;
import com.myfitnesspal.shared.service.syncv2.ops.ConfigOp;
import com.myfitnesspal.shared.service.syncv2.ops.FindAndCorrectFoodsWithMissingInfoOp;
import com.myfitnesspal.shared.service.syncv2.ops.GeoLocationOp;
import com.myfitnesspal.shared.service.syncv2.ops.MigrateTokenOp;
import com.myfitnesspal.shared.service.syncv2.ops.ProductServiceOp;
import com.myfitnesspal.shared.service.syncv2.ops.ReceiptsOp;
import com.myfitnesspal.shared.service.syncv2.ops.SyncV1Op;
import com.myfitnesspal.shared.service.syncv2.ops.UacfUserOp;
import com.myfitnesspal.shared.service.syncv2.ops.UserV2Op;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import com.myfitnesspal.shared.uacf.UacfRolloutUtil;
import com.squareup.otto.Bus;
import com.uacf.sync.engine.UacfScheduler;
import com.uacf.sync.engine.UacfSchedulerEngine;
import com.uacf.sync.provider.internal.model.SyncOpBase;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.configuration.sdk.ConfigurationOp;
import io.uacf.rollouts.sdk.RolloutsOp;
import javax.inject.Provider;

public final class SyncModule_ProvidesSyncServiceDelegateFactory implements Factory<MfpSyncEngineDelegate> {
    private final Provider<UacfScheduler<AnalyticsSyncMode>> analyticsSyncSchedulerProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<AuthTokenOp> authTokenOpProvider;
    private final Provider<ConfigOp> configOpProvider;
    private final Provider<ConfigurationOp> configurationOpProvider;
    private final Provider<FindAndCorrectFoodsWithMissingInfoOp> correctV2FoodsOpProvider;
    private final Provider<ExternalSyncOp> externalSyncOpProvider;
    private final Provider<GeoLocationOp> geoLocationOpProvider;
    private final Provider<UacfSchedulerEngine<ImageSyncMode>> imageSyncServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<MigrateTokenOp> migrateTokenOpProvider;
    private final SyncModule module;
    private final Provider<ProductServiceOp> productServiceOpProvider;
    private final Provider<ReceiptsOp> receiptsOpProvider;
    private final Provider<RolloutsOp> rolloutsOpProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<SyncV1Op> syncV1OpProvider;
    private final Provider<SyncOpBase> syncV2OpProvider;
    private final Provider<UacfConfigurationUtil> uacfConfigurationUtilProvider;
    private final Provider<UacfRolloutUtil> uacfRolloutUtilProvider;
    private final Provider<UacfUserOp> uacfUserOpProvider;
    private final Provider<UserV2Op> userV2OpProvider;

    public SyncModule_ProvidesSyncServiceDelegateFactory(SyncModule syncModule, Provider<Bus> provider, Provider<Session> provider2, Provider<AppIndexerBot> provider3, Provider<SyncV1Op> provider4, Provider<SyncOpBase> provider5, Provider<UserV2Op> provider6, Provider<AuthTokenOp> provider7, Provider<ConfigOp> provider8, Provider<ConfigurationOp> provider9, Provider<RolloutsOp> provider10, Provider<UacfUserOp> provider11, Provider<GeoLocationOp> provider12, Provider<ReceiptsOp> provider13, Provider<ExternalSyncOp> provider14, Provider<MigrateTokenOp> provider15, Provider<ProductServiceOp> provider16, Provider<FindAndCorrectFoodsWithMissingInfoOp> provider17, Provider<SyncService> provider18, Provider<UacfSchedulerEngine<ImageSyncMode>> provider19, Provider<UacfScheduler<AnalyticsSyncMode>> provider20, Provider<UacfConfigurationUtil> provider21, Provider<UacfRolloutUtil> provider22) {
        this.module = syncModule;
        this.messageBusProvider = provider;
        this.sessionProvider = provider2;
        this.appIndexerBotProvider = provider3;
        this.syncV1OpProvider = provider4;
        this.syncV2OpProvider = provider5;
        this.userV2OpProvider = provider6;
        this.authTokenOpProvider = provider7;
        this.configOpProvider = provider8;
        this.configurationOpProvider = provider9;
        this.rolloutsOpProvider = provider10;
        this.uacfUserOpProvider = provider11;
        this.geoLocationOpProvider = provider12;
        this.receiptsOpProvider = provider13;
        this.externalSyncOpProvider = provider14;
        this.migrateTokenOpProvider = provider15;
        this.productServiceOpProvider = provider16;
        this.correctV2FoodsOpProvider = provider17;
        this.syncServiceProvider = provider18;
        this.imageSyncServiceProvider = provider19;
        this.analyticsSyncSchedulerProvider = provider20;
        this.uacfConfigurationUtilProvider = provider21;
        this.uacfRolloutUtilProvider = provider22;
    }

    public MfpSyncEngineDelegate get() {
        SyncModule syncModule = this.module;
        return provideInstance(syncModule, this.messageBusProvider, this.sessionProvider, this.appIndexerBotProvider, this.syncV1OpProvider, this.syncV2OpProvider, this.userV2OpProvider, this.authTokenOpProvider, this.configOpProvider, this.configurationOpProvider, this.rolloutsOpProvider, this.uacfUserOpProvider, this.geoLocationOpProvider, this.receiptsOpProvider, this.externalSyncOpProvider, this.migrateTokenOpProvider, this.productServiceOpProvider, this.correctV2FoodsOpProvider, this.syncServiceProvider, this.imageSyncServiceProvider, this.analyticsSyncSchedulerProvider, this.uacfConfigurationUtilProvider, this.uacfRolloutUtilProvider);
    }

    public static MfpSyncEngineDelegate provideInstance(SyncModule syncModule, Provider<Bus> provider, Provider<Session> provider2, Provider<AppIndexerBot> provider3, Provider<SyncV1Op> provider4, Provider<SyncOpBase> provider5, Provider<UserV2Op> provider6, Provider<AuthTokenOp> provider7, Provider<ConfigOp> provider8, Provider<ConfigurationOp> provider9, Provider<RolloutsOp> provider10, Provider<UacfUserOp> provider11, Provider<GeoLocationOp> provider12, Provider<ReceiptsOp> provider13, Provider<ExternalSyncOp> provider14, Provider<MigrateTokenOp> provider15, Provider<ProductServiceOp> provider16, Provider<FindAndCorrectFoodsWithMissingInfoOp> provider17, Provider<SyncService> provider18, Provider<UacfSchedulerEngine<ImageSyncMode>> provider19, Provider<UacfScheduler<AnalyticsSyncMode>> provider20, Provider<UacfConfigurationUtil> provider21, Provider<UacfRolloutUtil> provider22) {
        return proxyProvidesSyncServiceDelegate(syncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, DoubleCheck.lazy(provider18), DoubleCheck.lazy(provider19), DoubleCheck.lazy(provider20), DoubleCheck.lazy(provider21), DoubleCheck.lazy(provider22));
    }

    public static SyncModule_ProvidesSyncServiceDelegateFactory create(SyncModule syncModule, Provider<Bus> provider, Provider<Session> provider2, Provider<AppIndexerBot> provider3, Provider<SyncV1Op> provider4, Provider<SyncOpBase> provider5, Provider<UserV2Op> provider6, Provider<AuthTokenOp> provider7, Provider<ConfigOp> provider8, Provider<ConfigurationOp> provider9, Provider<RolloutsOp> provider10, Provider<UacfUserOp> provider11, Provider<GeoLocationOp> provider12, Provider<ReceiptsOp> provider13, Provider<ExternalSyncOp> provider14, Provider<MigrateTokenOp> provider15, Provider<ProductServiceOp> provider16, Provider<FindAndCorrectFoodsWithMissingInfoOp> provider17, Provider<SyncService> provider18, Provider<UacfSchedulerEngine<ImageSyncMode>> provider19, Provider<UacfScheduler<AnalyticsSyncMode>> provider20, Provider<UacfConfigurationUtil> provider21, Provider<UacfRolloutUtil> provider22) {
        SyncModule_ProvidesSyncServiceDelegateFactory syncModule_ProvidesSyncServiceDelegateFactory = new SyncModule_ProvidesSyncServiceDelegateFactory(syncModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22);
        return syncModule_ProvidesSyncServiceDelegateFactory;
    }

    public static MfpSyncEngineDelegate proxyProvidesSyncServiceDelegate(SyncModule syncModule, Lazy<Bus> lazy, Lazy<Session> lazy2, Lazy<AppIndexerBot> lazy3, Provider<SyncV1Op> provider, Provider<SyncOpBase> provider2, Provider<UserV2Op> provider3, Provider<AuthTokenOp> provider4, Provider<ConfigOp> provider5, Provider<ConfigurationOp> provider6, Provider<RolloutsOp> provider7, Provider<UacfUserOp> provider8, Provider<GeoLocationOp> provider9, Provider<ReceiptsOp> provider10, Provider<ExternalSyncOp> provider11, Provider<MigrateTokenOp> provider12, Provider<ProductServiceOp> provider13, Provider<FindAndCorrectFoodsWithMissingInfoOp> provider14, Lazy<SyncService> lazy4, Lazy<UacfSchedulerEngine<ImageSyncMode>> lazy5, Lazy<UacfScheduler<AnalyticsSyncMode>> lazy6, Lazy<UacfConfigurationUtil> lazy7, Lazy<UacfRolloutUtil> lazy8) {
        return (MfpSyncEngineDelegate) Preconditions.checkNotNull(syncModule.providesSyncServiceDelegate(lazy, lazy2, lazy3, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, lazy4, lazy5, lazy6, lazy7, lazy8), "Cannot return null from a non-@Nullable @Provides method");
    }
}
