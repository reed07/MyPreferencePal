package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.syncv2.ops.ConfigOp;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesConfigOpFactory implements Factory<ConfigOp> {
    private final Provider<ConfigService> configServiceProvider;
    private final SyncModule module;

    public SyncModule_ProvidesConfigOpFactory(SyncModule syncModule, Provider<ConfigService> provider) {
        this.module = syncModule;
        this.configServiceProvider = provider;
    }

    public ConfigOp get() {
        return provideInstance(this.module, this.configServiceProvider);
    }

    public static ConfigOp provideInstance(SyncModule syncModule, Provider<ConfigService> provider) {
        return proxyProvidesConfigOp(syncModule, DoubleCheck.lazy(provider));
    }

    public static SyncModule_ProvidesConfigOpFactory create(SyncModule syncModule, Provider<ConfigService> provider) {
        return new SyncModule_ProvidesConfigOpFactory(syncModule, provider);
    }

    public static ConfigOp proxyProvidesConfigOp(SyncModule syncModule, Lazy<ConfigService> lazy) {
        return (ConfigOp) Preconditions.checkNotNull(syncModule.providesConfigOp(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
