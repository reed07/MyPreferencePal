package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.syncv2.ops.MfpSyncV2OpDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SyncModule_ProvidesSyncV2OpDelegateFactory implements Factory<MfpSyncV2OpDelegate> {
    private final SyncModule module;

    public SyncModule_ProvidesSyncV2OpDelegateFactory(SyncModule syncModule) {
        this.module = syncModule;
    }

    public MfpSyncV2OpDelegate get() {
        return provideInstance(this.module);
    }

    public static MfpSyncV2OpDelegate provideInstance(SyncModule syncModule) {
        return proxyProvidesSyncV2OpDelegate(syncModule);
    }

    public static SyncModule_ProvidesSyncV2OpDelegateFactory create(SyncModule syncModule) {
        return new SyncModule_ProvidesSyncV2OpDelegateFactory(syncModule);
    }

    public static MfpSyncV2OpDelegate proxyProvidesSyncV2OpDelegate(SyncModule syncModule) {
        return (MfpSyncV2OpDelegate) Preconditions.checkNotNull(syncModule.providesSyncV2OpDelegate(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
