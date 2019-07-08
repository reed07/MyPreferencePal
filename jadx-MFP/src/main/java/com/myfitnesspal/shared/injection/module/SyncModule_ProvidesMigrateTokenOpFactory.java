package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.service.syncv2.ops.MigrateTokenOp;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesMigrateTokenOpFactory implements Factory<MigrateTokenOp> {
    private final Provider<AuthTokenProvider> authTokenProvider;
    private final SyncModule module;

    public SyncModule_ProvidesMigrateTokenOpFactory(SyncModule syncModule, Provider<AuthTokenProvider> provider) {
        this.module = syncModule;
        this.authTokenProvider = provider;
    }

    public MigrateTokenOp get() {
        return provideInstance(this.module, this.authTokenProvider);
    }

    public static MigrateTokenOp provideInstance(SyncModule syncModule, Provider<AuthTokenProvider> provider) {
        return proxyProvidesMigrateTokenOp(syncModule, DoubleCheck.lazy(provider));
    }

    public static SyncModule_ProvidesMigrateTokenOpFactory create(SyncModule syncModule, Provider<AuthTokenProvider> provider) {
        return new SyncModule_ProvidesMigrateTokenOpFactory(syncModule, provider);
    }

    public static MigrateTokenOp proxyProvidesMigrateTokenOp(SyncModule syncModule, Lazy<AuthTokenProvider> lazy) {
        return (MigrateTokenOp) Preconditions.checkNotNull(syncModule.providesMigrateTokenOp(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
