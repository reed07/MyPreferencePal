package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.db.table.SyncPointersTable;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.SyncPointerService;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideSyncPointerServiceFactory implements Factory<SyncPointerService> {
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncPointersTable> syncPointersTableProvider;
    private final Provider<SyncUtil> syncUtilProvider;

    public ApplicationModule_ProvideSyncPointerServiceFactory(ApplicationModule applicationModule, Provider<SyncPointersTable> provider, Provider<Session> provider2, Provider<SyncUtil> provider3) {
        this.module = applicationModule;
        this.syncPointersTableProvider = provider;
        this.sessionProvider = provider2;
        this.syncUtilProvider = provider3;
    }

    public SyncPointerService get() {
        return provideInstance(this.module, this.syncPointersTableProvider, this.sessionProvider, this.syncUtilProvider);
    }

    public static SyncPointerService provideInstance(ApplicationModule applicationModule, Provider<SyncPointersTable> provider, Provider<Session> provider2, Provider<SyncUtil> provider3) {
        return proxyProvideSyncPointerService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvideSyncPointerServiceFactory create(ApplicationModule applicationModule, Provider<SyncPointersTable> provider, Provider<Session> provider2, Provider<SyncUtil> provider3) {
        return new ApplicationModule_ProvideSyncPointerServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static SyncPointerService proxyProvideSyncPointerService(ApplicationModule applicationModule, Lazy<SyncPointersTable> lazy, Lazy<Session> lazy2, Lazy<SyncUtil> lazy3) {
        return (SyncPointerService) Preconditions.checkNotNull(applicationModule.provideSyncPointerService(lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
