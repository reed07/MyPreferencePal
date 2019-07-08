package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.shared.service.syncv2.ops.ProductServiceOp;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesProductServiceOpFactory implements Factory<ProductServiceOp> {
    private final SyncModule module;
    private final Provider<ProductService> productServiceProvider;

    public SyncModule_ProvidesProductServiceOpFactory(SyncModule syncModule, Provider<ProductService> provider) {
        this.module = syncModule;
        this.productServiceProvider = provider;
    }

    public ProductServiceOp get() {
        return provideInstance(this.module, this.productServiceProvider);
    }

    public static ProductServiceOp provideInstance(SyncModule syncModule, Provider<ProductService> provider) {
        return proxyProvidesProductServiceOp(syncModule, DoubleCheck.lazy(provider));
    }

    public static SyncModule_ProvidesProductServiceOpFactory create(SyncModule syncModule, Provider<ProductService> provider) {
        return new SyncModule_ProvidesProductServiceOpFactory(syncModule, provider);
    }

    public static ProductServiceOp proxyProvidesProductServiceOp(SyncModule syncModule, Lazy<ProductService> lazy) {
        return (ProductServiceOp) Preconditions.checkNotNull(syncModule.providesProductServiceOp(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
