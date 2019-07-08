package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.LegacySyncManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.thumbprint.ui.sdk.ClientEmailVerificationStatus;
import javax.inject.Provider;

public final class SyncModule_ProvideClientEmailVerificationStatusFactory implements Factory<ClientEmailVerificationStatus> {
    private final Provider<LegacySyncManager> legacySyncManagerProvider;
    private final SyncModule module;
    private final Provider<Session> sessionProvider;

    public SyncModule_ProvideClientEmailVerificationStatusFactory(SyncModule syncModule, Provider<Session> provider, Provider<LegacySyncManager> provider2) {
        this.module = syncModule;
        this.sessionProvider = provider;
        this.legacySyncManagerProvider = provider2;
    }

    public ClientEmailVerificationStatus get() {
        return provideInstance(this.module, this.sessionProvider, this.legacySyncManagerProvider);
    }

    public static ClientEmailVerificationStatus provideInstance(SyncModule syncModule, Provider<Session> provider, Provider<LegacySyncManager> provider2) {
        return proxyProvideClientEmailVerificationStatus(syncModule, DoubleCheck.lazy(provider), (LegacySyncManager) provider2.get());
    }

    public static SyncModule_ProvideClientEmailVerificationStatusFactory create(SyncModule syncModule, Provider<Session> provider, Provider<LegacySyncManager> provider2) {
        return new SyncModule_ProvideClientEmailVerificationStatusFactory(syncModule, provider, provider2);
    }

    public static ClientEmailVerificationStatus proxyProvideClientEmailVerificationStatus(SyncModule syncModule, Lazy<Session> lazy, LegacySyncManager legacySyncManager) {
        return (ClientEmailVerificationStatus) Preconditions.checkNotNull(syncModule.provideClientEmailVerificationStatus(lazy, legacySyncManager), "Cannot return null from a non-@Nullable @Provides method");
    }
}
