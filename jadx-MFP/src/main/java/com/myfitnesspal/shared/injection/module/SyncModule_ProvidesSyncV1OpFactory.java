package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.LegacySyncManager;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.myfitnesspal.shared.service.syncv2.ops.SyncV1Op;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesSyncV1OpFactory implements Factory<SyncV1Op> {
    private final Provider<LegacySyncManager> legacySyncManagerProvider;
    private final SyncModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncUtil> syncUtilProvider;

    public SyncModule_ProvidesSyncV1OpFactory(SyncModule syncModule, Provider<Session> provider, Provider<SyncUtil> provider2, Provider<LegacySyncManager> provider3) {
        this.module = syncModule;
        this.sessionProvider = provider;
        this.syncUtilProvider = provider2;
        this.legacySyncManagerProvider = provider3;
    }

    public SyncV1Op get() {
        return provideInstance(this.module, this.sessionProvider, this.syncUtilProvider, this.legacySyncManagerProvider);
    }

    public static SyncV1Op provideInstance(SyncModule syncModule, Provider<Session> provider, Provider<SyncUtil> provider2, Provider<LegacySyncManager> provider3) {
        return proxyProvidesSyncV1Op(syncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), (LegacySyncManager) provider3.get());
    }

    public static SyncModule_ProvidesSyncV1OpFactory create(SyncModule syncModule, Provider<Session> provider, Provider<SyncUtil> provider2, Provider<LegacySyncManager> provider3) {
        return new SyncModule_ProvidesSyncV1OpFactory(syncModule, provider, provider2, provider3);
    }

    public static SyncV1Op proxyProvidesSyncV1Op(SyncModule syncModule, Lazy<Session> lazy, Lazy<SyncUtil> lazy2, LegacySyncManager legacySyncManager) {
        return (SyncV1Op) Preconditions.checkNotNull(syncModule.providesSyncV1Op(lazy, lazy2, legacySyncManager), "Cannot return null from a non-@Nullable @Provides method");
    }
}
