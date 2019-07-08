package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncOp;
import com.uacf.sync.engine.UacfSchedulerEngine;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesAnalyticsSyncServiceFactory implements Factory<UacfSchedulerEngine<AnalyticsSyncMode>> {
    private final Provider<AnalyticsSyncOp> analyticsOpProvider;
    private final Provider<Context> contextProvider;
    private final SyncModule module;

    public SyncModule_ProvidesAnalyticsSyncServiceFactory(SyncModule syncModule, Provider<Context> provider, Provider<AnalyticsSyncOp> provider2) {
        this.module = syncModule;
        this.contextProvider = provider;
        this.analyticsOpProvider = provider2;
    }

    public UacfSchedulerEngine<AnalyticsSyncMode> get() {
        return provideInstance(this.module, this.contextProvider, this.analyticsOpProvider);
    }

    public static UacfSchedulerEngine<AnalyticsSyncMode> provideInstance(SyncModule syncModule, Provider<Context> provider, Provider<AnalyticsSyncOp> provider2) {
        return proxyProvidesAnalyticsSyncService(syncModule, (Context) provider.get(), provider2);
    }

    public static SyncModule_ProvidesAnalyticsSyncServiceFactory create(SyncModule syncModule, Provider<Context> provider, Provider<AnalyticsSyncOp> provider2) {
        return new SyncModule_ProvidesAnalyticsSyncServiceFactory(syncModule, provider, provider2);
    }

    public static UacfSchedulerEngine<AnalyticsSyncMode> proxyProvidesAnalyticsSyncService(SyncModule syncModule, Context context, Provider<AnalyticsSyncOp> provider) {
        return (UacfSchedulerEngine) Preconditions.checkNotNull(syncModule.providesAnalyticsSyncService(context, provider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
