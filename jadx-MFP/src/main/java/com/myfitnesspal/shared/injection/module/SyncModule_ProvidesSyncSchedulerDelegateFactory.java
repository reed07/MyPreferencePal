package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.uacf.sync.engine.UacfSchedulerDelegate;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesSyncSchedulerDelegateFactory implements Factory<UacfSchedulerDelegate<SyncType>> {
    private final SyncModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;

    public SyncModule_ProvidesSyncSchedulerDelegateFactory(SyncModule syncModule, Provider<Session> provider, Provider<SyncService> provider2) {
        this.module = syncModule;
        this.sessionProvider = provider;
        this.syncServiceProvider = provider2;
    }

    public UacfSchedulerDelegate<SyncType> get() {
        return provideInstance(this.module, this.sessionProvider, this.syncServiceProvider);
    }

    public static UacfSchedulerDelegate<SyncType> provideInstance(SyncModule syncModule, Provider<Session> provider, Provider<SyncService> provider2) {
        return proxyProvidesSyncSchedulerDelegate(syncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static SyncModule_ProvidesSyncSchedulerDelegateFactory create(SyncModule syncModule, Provider<Session> provider, Provider<SyncService> provider2) {
        return new SyncModule_ProvidesSyncSchedulerDelegateFactory(syncModule, provider, provider2);
    }

    public static UacfSchedulerDelegate<SyncType> proxyProvidesSyncSchedulerDelegate(SyncModule syncModule, Lazy<Session> lazy, Lazy<SyncService> lazy2) {
        return (UacfSchedulerDelegate) Preconditions.checkNotNull(syncModule.providesSyncSchedulerDelegate(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
