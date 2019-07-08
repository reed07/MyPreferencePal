package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.uacf.sync.engine.UacfScheduler;
import com.uacf.sync.engine.UacfSchedulerEngine;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesAnalyticsSyncSchedulerFactory implements Factory<UacfScheduler<AnalyticsSyncMode>> {
    private final Provider<UacfSchedulerEngine<AnalyticsSyncMode>> analyticsSyncServiceProvider;
    private final SyncModule module;

    public SyncModule_ProvidesAnalyticsSyncSchedulerFactory(SyncModule syncModule, Provider<UacfSchedulerEngine<AnalyticsSyncMode>> provider) {
        this.module = syncModule;
        this.analyticsSyncServiceProvider = provider;
    }

    public UacfScheduler<AnalyticsSyncMode> get() {
        return provideInstance(this.module, this.analyticsSyncServiceProvider);
    }

    public static UacfScheduler<AnalyticsSyncMode> provideInstance(SyncModule syncModule, Provider<UacfSchedulerEngine<AnalyticsSyncMode>> provider) {
        return proxyProvidesAnalyticsSyncScheduler(syncModule, (UacfSchedulerEngine) provider.get());
    }

    public static SyncModule_ProvidesAnalyticsSyncSchedulerFactory create(SyncModule syncModule, Provider<UacfSchedulerEngine<AnalyticsSyncMode>> provider) {
        return new SyncModule_ProvidesAnalyticsSyncSchedulerFactory(syncModule, provider);
    }

    public static UacfScheduler<AnalyticsSyncMode> proxyProvidesAnalyticsSyncScheduler(SyncModule syncModule, UacfSchedulerEngine<AnalyticsSyncMode> uacfSchedulerEngine) {
        return (UacfScheduler) Preconditions.checkNotNull(syncModule.providesAnalyticsSyncScheduler(uacfSchedulerEngine), "Cannot return null from a non-@Nullable @Provides method");
    }
}
