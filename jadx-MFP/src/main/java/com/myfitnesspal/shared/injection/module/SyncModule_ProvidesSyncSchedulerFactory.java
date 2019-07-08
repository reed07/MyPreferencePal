package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.uacf.sync.engine.UacfScheduler;
import com.uacf.sync.engine.UacfSchedulerDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesSyncSchedulerFactory implements Factory<UacfScheduler<SyncType>> {
    private final Provider<UacfSchedulerDelegate<SyncType>> delegateProvider;
    private final SyncModule module;

    public SyncModule_ProvidesSyncSchedulerFactory(SyncModule syncModule, Provider<UacfSchedulerDelegate<SyncType>> provider) {
        this.module = syncModule;
        this.delegateProvider = provider;
    }

    public UacfScheduler<SyncType> get() {
        return provideInstance(this.module, this.delegateProvider);
    }

    public static UacfScheduler<SyncType> provideInstance(SyncModule syncModule, Provider<UacfSchedulerDelegate<SyncType>> provider) {
        return proxyProvidesSyncScheduler(syncModule, (UacfSchedulerDelegate) provider.get());
    }

    public static SyncModule_ProvidesSyncSchedulerFactory create(SyncModule syncModule, Provider<UacfSchedulerDelegate<SyncType>> provider) {
        return new SyncModule_ProvidesSyncSchedulerFactory(syncModule, provider);
    }

    public static UacfScheduler<SyncType> proxyProvidesSyncScheduler(SyncModule syncModule, UacfSchedulerDelegate<SyncType> uacfSchedulerDelegate) {
        return (UacfScheduler) Preconditions.checkNotNull(syncModule.providesSyncScheduler(uacfSchedulerDelegate), "Cannot return null from a non-@Nullable @Provides method");
    }
}
