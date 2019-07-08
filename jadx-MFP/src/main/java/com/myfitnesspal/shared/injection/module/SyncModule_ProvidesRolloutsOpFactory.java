package com.myfitnesspal.shared.injection.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.rollouts.sdk.RolloutsOp;

public final class SyncModule_ProvidesRolloutsOpFactory implements Factory<RolloutsOp> {
    private final SyncModule module;

    public SyncModule_ProvidesRolloutsOpFactory(SyncModule syncModule) {
        this.module = syncModule;
    }

    public RolloutsOp get() {
        return provideInstance(this.module);
    }

    public static RolloutsOp provideInstance(SyncModule syncModule) {
        return proxyProvidesRolloutsOp(syncModule);
    }

    public static SyncModule_ProvidesRolloutsOpFactory create(SyncModule syncModule) {
        return new SyncModule_ProvidesRolloutsOpFactory(syncModule);
    }

    public static RolloutsOp proxyProvidesRolloutsOp(SyncModule syncModule) {
        return (RolloutsOp) Preconditions.checkNotNull(syncModule.providesRolloutsOp(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
