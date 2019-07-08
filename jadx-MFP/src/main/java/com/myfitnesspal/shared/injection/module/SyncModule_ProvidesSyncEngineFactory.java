package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.syncv2.MfpSyncEngineDelegate;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesSyncEngineFactory implements Factory<SyncService> {
    private final Provider<Context> contextProvider;
    private final Provider<MfpSyncEngineDelegate> delegateProvider;
    private final Provider<Bus> messageBusProvider;
    private final SyncModule module;

    public SyncModule_ProvidesSyncEngineFactory(SyncModule syncModule, Provider<Context> provider, Provider<MfpSyncEngineDelegate> provider2, Provider<Bus> provider3) {
        this.module = syncModule;
        this.contextProvider = provider;
        this.delegateProvider = provider2;
        this.messageBusProvider = provider3;
    }

    public SyncService get() {
        return provideInstance(this.module, this.contextProvider, this.delegateProvider, this.messageBusProvider);
    }

    public static SyncService provideInstance(SyncModule syncModule, Provider<Context> provider, Provider<MfpSyncEngineDelegate> provider2, Provider<Bus> provider3) {
        return proxyProvidesSyncEngine(syncModule, (Context) provider.get(), (MfpSyncEngineDelegate) provider2.get(), DoubleCheck.lazy(provider3));
    }

    public static SyncModule_ProvidesSyncEngineFactory create(SyncModule syncModule, Provider<Context> provider, Provider<MfpSyncEngineDelegate> provider2, Provider<Bus> provider3) {
        return new SyncModule_ProvidesSyncEngineFactory(syncModule, provider, provider2, provider3);
    }

    public static SyncService proxyProvidesSyncEngine(SyncModule syncModule, Context context, MfpSyncEngineDelegate mfpSyncEngineDelegate, Lazy<Bus> lazy) {
        return (SyncService) Preconditions.checkNotNull(syncModule.providesSyncEngine(context, mfpSyncEngineDelegate, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
