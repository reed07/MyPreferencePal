package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.syncv2.ops.MfpSyncV2OpDelegate;
import com.uacf.sync.provider.internal.model.SyncOpBase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesSyncV2OpFactory implements Factory<SyncOpBase> {
    private final Provider<MfpSyncV2OpDelegate> delegateProvider;
    private final SyncModule module;

    public SyncModule_ProvidesSyncV2OpFactory(SyncModule syncModule, Provider<MfpSyncV2OpDelegate> provider) {
        this.module = syncModule;
        this.delegateProvider = provider;
    }

    public SyncOpBase get() {
        return provideInstance(this.module, this.delegateProvider);
    }

    public static SyncOpBase provideInstance(SyncModule syncModule, Provider<MfpSyncV2OpDelegate> provider) {
        return proxyProvidesSyncV2Op(syncModule, (MfpSyncV2OpDelegate) provider.get());
    }

    public static SyncModule_ProvidesSyncV2OpFactory create(SyncModule syncModule, Provider<MfpSyncV2OpDelegate> provider) {
        return new SyncModule_ProvidesSyncV2OpFactory(syncModule, provider);
    }

    public static SyncOpBase proxyProvidesSyncV2Op(SyncModule syncModule, MfpSyncV2OpDelegate mfpSyncV2OpDelegate) {
        return (SyncOpBase) Preconditions.checkNotNull(syncModule.providesSyncV2Op(mfpSyncV2OpDelegate), "Cannot return null from a non-@Nullable @Provides method");
    }
}
