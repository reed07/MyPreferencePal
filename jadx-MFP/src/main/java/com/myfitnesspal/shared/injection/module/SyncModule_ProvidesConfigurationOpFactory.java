package com.myfitnesspal.shared.injection.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.configuration.sdk.ConfigurationOp;

public final class SyncModule_ProvidesConfigurationOpFactory implements Factory<ConfigurationOp> {
    private final SyncModule module;

    public SyncModule_ProvidesConfigurationOpFactory(SyncModule syncModule) {
        this.module = syncModule;
    }

    public ConfigurationOp get() {
        return provideInstance(this.module);
    }

    public static ConfigurationOp provideInstance(SyncModule syncModule) {
        return proxyProvidesConfigurationOp(syncModule);
    }

    public static SyncModule_ProvidesConfigurationOpFactory create(SyncModule syncModule) {
        return new SyncModule_ProvidesConfigurationOpFactory(syncModule);
    }

    public static ConfigurationOp proxyProvidesConfigurationOp(SyncModule syncModule) {
        return (ConfigurationOp) Preconditions.checkNotNull(syncModule.providesConfigurationOp(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
