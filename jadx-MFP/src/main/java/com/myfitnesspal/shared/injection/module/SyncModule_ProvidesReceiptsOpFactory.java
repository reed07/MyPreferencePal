package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.shared.service.syncv2.ops.ReceiptsOp;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesReceiptsOpFactory implements Factory<ReceiptsOp> {
    private final SyncModule module;
    private final Provider<SubscriptionService> subscriptionServiceProvider;

    public SyncModule_ProvidesReceiptsOpFactory(SyncModule syncModule, Provider<SubscriptionService> provider) {
        this.module = syncModule;
        this.subscriptionServiceProvider = provider;
    }

    public ReceiptsOp get() {
        return provideInstance(this.module, this.subscriptionServiceProvider);
    }

    public static ReceiptsOp provideInstance(SyncModule syncModule, Provider<SubscriptionService> provider) {
        return proxyProvidesReceiptsOp(syncModule, DoubleCheck.lazy(provider));
    }

    public static SyncModule_ProvidesReceiptsOpFactory create(SyncModule syncModule, Provider<SubscriptionService> provider) {
        return new SyncModule_ProvidesReceiptsOpFactory(syncModule, provider);
    }

    public static ReceiptsOp proxyProvidesReceiptsOp(SyncModule syncModule, Lazy<SubscriptionService> lazy) {
        return (ReceiptsOp) Preconditions.checkNotNull(syncModule.providesReceiptsOp(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
