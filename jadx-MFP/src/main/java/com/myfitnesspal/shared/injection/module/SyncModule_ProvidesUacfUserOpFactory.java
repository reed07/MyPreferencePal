package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.syncv2.ops.UacfUserOp;
import com.uacf.identity.sdk.UacfIdentitySdk;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesUacfUserOpFactory implements Factory<UacfUserOp> {
    private final Provider<UacfIdentitySdk> identitySdkProvider;
    private final SyncModule module;

    public SyncModule_ProvidesUacfUserOpFactory(SyncModule syncModule, Provider<UacfIdentitySdk> provider) {
        this.module = syncModule;
        this.identitySdkProvider = provider;
    }

    public UacfUserOp get() {
        return provideInstance(this.module, this.identitySdkProvider);
    }

    public static UacfUserOp provideInstance(SyncModule syncModule, Provider<UacfIdentitySdk> provider) {
        return proxyProvidesUacfUserOp(syncModule, DoubleCheck.lazy(provider));
    }

    public static SyncModule_ProvidesUacfUserOpFactory create(SyncModule syncModule, Provider<UacfIdentitySdk> provider) {
        return new SyncModule_ProvidesUacfUserOpFactory(syncModule, provider);
    }

    public static UacfUserOp proxyProvidesUacfUserOp(SyncModule syncModule, Lazy<UacfIdentitySdk> lazy) {
        return (UacfUserOp) Preconditions.checkNotNull(syncModule.providesUacfUserOp(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
